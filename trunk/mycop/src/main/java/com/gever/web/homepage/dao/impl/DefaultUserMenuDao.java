package com.gever.web.homepage.dao.impl;

import java.util.*;
import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;

import org.w3c.dom.*;

import com.gever.jdbc.sqlhelper.DataTypes;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;

import javax.xml.parsers.*;
import com.gever.util.NumberUtil;
import com.gever.util.log.Log;
import com.gever.web.homepage.dao.UserMenuDao;
import com.gever.web.homepage.vo.UserMenuVO;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.impl.DefaultResource;
import com.gever.config.Constants;


/**
 * <p>Title: 用户界面菜单处理(UserMenuDao的实现类)</p>
 * <p>Description: 包括取资料及重新设置界面资料</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

public class DefaultUserMenuDao implements UserMenuDao {

    protected Log log = Log.getInstance(DefaultUserMenuDao.class);
    protected SQLHelper helper = null;
    protected String dbData = "gdp";
    protected static final String PATH_SPLIT = "-->"; //层次目录分隔
    protected List aryMenusAll = new ArrayList(); //备份的菜单项
    protected List aryMenusTwo = new ArrayList(); //备份的菜单项

    public DefaultUserMenuDao() {
        this.dbData = Constants.DATA_SOURCE;
        helper = new DefaultSQLHelper(dbData);
    }

    public DefaultUserMenuDao(String dbData) {
        this.dbData = dbData;
        helper = new DefaultSQLHelper(dbData);
    }

    /**
     * null对象转换为空对象
     * @param value 对象
     * @return String对象
     */
    private String nullToString(Object value) {
        String strRet = (String) value;
        if (null == strRet) {
            return strRet = "";
        }
        return strRet;
    }

    /**
     * 查出该用户所有的菜单资料
     * @param userID 用户ID号
     * @return 菜单列表
     * @throws DefaultException
     */
    public List queryAll(String userID) throws DAOException {

        StringBuffer sbSql = new StringBuffer();
        List aryData = new ArrayList();
        String[] values = new String[1];
        values[0] = userID;
        UserMenuVO vo = new UserMenuVO();

        sbSql.append("SELECT NodeID,NodeName,ParentID,Link, ");
        sbSql.append("       IsFolder,IsHided,isMenu,OrderID,memo ");
        sbSql.append("FROM T_USER_MENU ");
        sbSql.append("WHERE (EmpID=?) AND (IsHided='0')");
        sbSql.append("  and isMenu='1'   and Flag='1' ");
        sbSql.append(" ORDER BY parentId, OrderID ");

        String[] arySql = new String[2];
        arySql[0] = sbSql.toString();
        arySql[1] = "String";

        try {
            aryData = (ArrayList) helper.query(arySql, values, vo,
                                               DataTypes.RS_LIST_VO);
            
            log.console(this.getClass(),"ResultSet Array size",String.valueOf(aryData.size()));

            //---重新设置过它的完整路径名称,主要是因为它的路径节节相扣----
            String strPathName = new String();
            int pos = -1;

            for (int idx = 0; idx < aryData.size(); idx++) {
                vo = new UserMenuVO();
                vo = (UserMenuVO) aryData.get(idx);
                vo.setNodeid(nullToString(vo.getNodeid()).trim());
                vo.setParentid(nullToString(vo.getParentid()).trim());
                vo.setIsfolder(nullToString(vo.getIsfolder()).trim());

                if ("0".equals(vo.getIsfolder().trim())) {
                    strPathName = getPathName(vo.getNodeid(), aryData);
                    pos = strPathName.lastIndexOf(PATH_SPLIT);
                    if (pos > 0)
                        strPathName = strPathName.substring(0, pos);
                    vo.setPathname(strPathName);

                }
                aryData.set(idx, vo);
            }
            this.setAryMenusAll(aryData); //备份起当前的所有菜单
            resetMenus(aryData); //读取当前的一级,二级菜单
            return this.getAryMenusAll();
        } catch (DefaultException e) {
            throw new DAOException("ORG_Homepage_001", e);
        }

    }


    /**
     * 递归算法得到路径完整名称
     * @param strNodeID 当前节点
     * @param aryDir 整个列表(用于查找)
     * @return 最终的路径名
     */
    private String getPathName(String strNodeID, List aryDir) {
        //如果已经是根节点,
        if (strNodeID == null || "".equals(strNodeID)) {
            return "";
        }

        String strRet = new String();
        for (int idx = 0; idx < aryDir.size(); idx++) {
            UserMenuVO vo = new UserMenuVO();
            vo = (UserMenuVO) aryDir.get(idx);
            if (strNodeID.equals(vo.getNodeid().trim())) {
                strRet = vo.getNodename();
                strRet = getPathName(vo.getParentid(), aryDir) + strRet +
                    PATH_SPLIT;
                break;
            }
        }
        return strRet;
    }

    /**
     * 重置 一级,二级菜单
     * @param aryList 菜单列表
     */
    private void resetMenus(List aryList) {
        List aryMenus = new ArrayList();
        List aryFiles = new ArrayList();

        //取得第一层菜单
        for (int idx = 0; idx < aryList.size(); idx++) {
            UserMenuVO view = new UserMenuVO();
            view = (UserMenuVO) aryList.get(idx);
            if ("0".equals(view.getParentid()) && "1".equals(view.getIsfolder())) {
                aryMenus.add(view);
            } else {
                aryFiles.add(view);
            }
        }

        List aryNext = new ArrayList();
        List aryTmp = new ArrayList();
        //得到第二层的菜单
        for (int idx = 0; idx < aryMenus.size(); idx++) {
            UserMenuVO view = new UserMenuVO();
            view = (UserMenuVO) aryMenus.get(idx);
            aryNext = getNextMenu(view.getNodeid(), aryFiles);
            aryTmp.addAll(aryNext);
        }
        //叠加第一层菜单后面
        this.setAryMenusTwo(aryTmp);

    }

    /**
     * 得到下一级菜单数据
     * @param strNodeID 当前菜单节点
     * @param aryFiles 所有菜单节点
     * @return 下一级菜单列表
     */
    private List getNextMenu(String strNodeID, List aryFiles) {
        List aryTmp = new ArrayList();
        if (aryFiles.size() <= 0)
            return aryTmp;

        for (int row = 0; row < aryFiles.size(); row++) {
            UserMenuVO fileView = new UserMenuVO();
            fileView = (UserMenuVO) aryFiles.get(row);
            if (strNodeID.equals(fileView.getParentid())) {
                aryTmp.add(fileView);
            }
        }
        return aryTmp;
    }

    /**
     * 得到下一级菜单数据
     * @param strNodeID 当前菜单节点
     * @param aryFiles 所有菜单节点
     * @return 下一级菜单列表
     */
    private boolean hasNextMenu(String strNodeID, List aryFiles) {
        List aryTmp = new ArrayList();
        if (aryFiles.size() <= 0)
            return false;

        for (int row = 0; row < aryFiles.size(); row++) {
            UserMenuVO fileView = new UserMenuVO();
            fileView = (UserMenuVO) aryFiles.get(row);
            log.showLog("id=" + strNodeID + " pareid=" + fileView.getParentid() +
                        "***");
            if (strNodeID.equals(fileView.getParentid())) {

                return true;
            }
        }

        return false;
    }

    /**
     * 查出该用户的所有菜单(二级菜单(含以下)的主要是一个下拉菜单,
     * 客户处理时需要xml文档中的Document对象)
     * @param userID 用户ID号
     * @return Document对象(菜单)
     * @throws DefaultException
     */
    public Document queryMenus(String userID) throws DAOException {
        //为解析XML作准备，创建DocumentBuilderFactory实例,指定DocumentBuilder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {

            log.showLog(pce.getMessage());
            throw new DAOException("ORG_Homepage_003");
        }

        Document doc = null;
        doc = db.newDocument();

        //下面是建立XML文档内容的过程，先建立根元素"根元素"
        Element root = doc.createElement("root");
        //根元素添加上文档
        doc.appendChild(root);
        //加入空菜单项
        Element menu = doc.createElement("menu");
        menu.setAttribute("id", "gevermenunull");
        root.appendChild(menu);
        Element menuItem = doc.createElement("menuitem");
        menuItem.setAttribute("text", "(空)");
        menu.appendChild(menuItem);
        //加入菜单自定义属性
        Element customUrl = doc.createElement("customattribute");
        customUrl.setAttribute("name", "url");
        customUrl.setAttribute("value", "");
        menuItem.appendChild(customUrl);

        for (int idx = 0; idx < this.aryMenusTwo.size(); idx++) {
            UserMenuVO view = new UserMenuVO();
            //添加menu
            view = (UserMenuVO) aryMenusTwo.get(idx);
            if ("1".equals(view.getIsfolder()))
                setXMLElement(view, doc, root);
        }

        return doc;

    }

    /**
     * 递归算法得到路径名
     * @param menuView 当前菜单对象
     * @param doc xml中document对象
     * @param root xml中document对象中根目录
     */
    private void setXMLElement(UserMenuVO menuView, Document doc, Element root) {
        //防止文件菜单
        if ("0".equals(menuView.getIsfolder())) {
            return;
        }

        List aryDir = getNextMenu(menuView.getNodeid(), this.aryMenusAll);
        Element menu = doc.createElement("menu");
        menu.setAttribute("id", "gevermenu" + menuView.getNodeid().trim());
        root.appendChild(menu);
        Element menuItem = doc.createElement("menuitem");
        //防止空菜单项
        if (aryDir.size() <= 0 && "1".equals(menuView.getIsfolder())) {

            menuItem.setAttribute("text", " (空) ");
            menu.appendChild(menuItem);
            //加入菜单自定义属性
            Element customUrl = doc.createElement("customattribute");
            customUrl.setAttribute("name", "url");
            customUrl.setAttribute("value", "");
            menuItem.appendChild(customUrl);
            return;
        }

        for (int idx = 0; idx < aryDir.size(); idx++) {
            UserMenuVO view = new UserMenuVO();
            view = (UserMenuVO) aryDir.get(idx);
            //菜单显示文本
            menuItem = doc.createElement("menuitem");
            menuItem.setAttribute("text", view.getNodename());

            if ("1".equals(view.getIsfolder())) {
                if (hasNextMenu(view.getNodeid(), this.aryMenusAll)) //判断是否为空菜单项
                    menuItem.setAttribute("sid", "gevermenu" + view.getNodeid());
                else
                    menuItem.setAttribute("sid", "gevermenunull");
            }
            menu.appendChild(menuItem);

            //加入菜单自定义属性
            Element customUrl = doc.createElement("customattribute");
            customUrl.setAttribute("name", "url");
            customUrl.setAttribute("value", view.getLink());
            menuItem.appendChild(customUrl);

            Element customPathName = doc.createElement("customattribute");
            customPathName.setAttribute("name", "pathname");
            customPathName.setAttribute("value", view.getPathname());
            menuItem.appendChild(customPathName);

            setXMLElement(view, doc, root); //递归

        }

    }

  /**
   * 当用户重新更改过权限时
   * 杨帆 2004-12-01 修改 重置菜单时保持用户自定义菜单不变
   * @param userID 当前用户ID
   * @param resourceList 当前用户对应资源资料
   * @return 设置菜单是否成功
   * @throws DefaultException
   */
  public boolean resetUserMenus(String userID, Collection resourceList) throws
      DAOException {
    boolean bRet = true;
    //log.showLog("------------start---");
    String strMethod =
        "resetUserMenus(String userID, Collection moduleList)\n";
//        List aryResult =  resourceList;

    StringBuffer sbSql = new StringBuffer();

    try {
      helper.begin();
      //先统计出数据
      sbSql.setLength(0);
      sbSql.append("select * from T_USER_MENU where empid='");
      sbSql.append(userID).append("'");
      UserMenuVO mVo = new UserMenuVO();
      List oldMenus = (ArrayList)helper.query(sbSql.toString(),
                                                  mVo,DataTypes.RS_LIST_VO);

      //删除未修改的数据
      sbSql.setLength(0);
      sbSql.append(" delete from  T_USER_MENU  WHERE EMPID='");
      sbSql.append(userID).append("' and (nodeid  in (select char(id) from T_RESOURCE) ");
      sbSql.append(" and parentid in (select char(parent_id) from T_RESOURCE)) ");
      int i = helper.execUpdate(sbSql.toString());

      sbSql.setLength(0);
      sbSql.append(" INSERT INTO T_USER_MENU(EMPID,NODEID,NODENAME,");
      sbSql.append("        PARENTID,LINK, ISFOLDER ,ORDERID) ");
      sbSql.append("SELECT '").append(userID).append("'AS EMPID,");
      sbSql.append("    CAST(A.id AS CHAR(10)),A.NAME, ");
      sbSql.append(
          "     CAST (A.parent_id AS CHAR(20)),A.link as link,");
      sbSql.append("      case when (Select COUNT(*) AS CNT  ");
      sbSql.append("      FROM T_RESOURCE B WHERE A.id<>B.id ");
      sbSql.append(
          "      AND B.parent_id=A.id ) >0 then '1' else '0' ");
      sbSql.append("     end AS ISFOLDER,A.ID AS ORDERID ");
      sbSql.append(" FROM T_RESOURCE  A ");
      sbSql.append(" where char(A.id) not in (");
      sbSql.append("select nodeid from T_USER_MENU where empid='");
      sbSql.append(userID).append("')");
      //log.showLog("------sbSql----1-" + sbSql.toString());
      helper.execUpdate(sbSql.toString());

      //更新orderid保持原来顺序不变
      Iterator it = oldMenus.iterator();
      while(it.hasNext()) {
        mVo = (UserMenuVO)it.next();
        sbSql.setLength(0);
        sbSql.append("update T_USER_MENU set orderid=");
        sbSql.append(mVo.getOrderid());
        sbSql.append(" where empid='").append(userID).append("' and nodeid='");
        sbSql.append(mVo.getNodeid()).append("'");
        helper.execUpdate(sbSql.toString());
      }

      //查出所有的非用户自定义菜单的资料
      List aryMenus = new ArrayList();
      UserMenuVO menuVo = new UserMenuVO();
      sbSql.setLength(0);
      sbSql.append("SELECT EMPID,NODEID,NODENAME,");
      sbSql.append("       PARENTID,LINK, ISFOLDER ,ORDERID, ");
      sbSql.append("       FLAG,MEMO ");
      sbSql.append("FROM T_USER_MENU where empid='").append(userID).append("'");
      sbSql.append(" and nodeid in (select char(id) from T_RESOURCE)");
      aryMenus = (ArrayList) helper.query(sbSql.toString(), menuVo,
                                          DataTypes.RS_LIST_VO);

      //根据比对,赋值给权限
      StringBuffer sbWhere = new StringBuffer();
      sbSql.setLength(0);
      sbSql.append(" UPDATE T_USER_MENU SET FLAG='1' WHERE EMPID ='");
      sbSql.append(userID).append("'");
      sbSql.append(" AND (");
      //log.showLog("------sbSql---3--" + sbSql.toString());
      int iCount = 0;
      if (resourceList == null) {
        resourceList = new ArrayList();
      }
      java.util.Iterator aryResult = resourceList.iterator();
      while (aryResult.hasNext()) {

        //for (int idx = 0; idx < aryResult.size(); idx++) {
        Resource vo = new DefaultResource();
        vo = (DefaultResource) aryResult.next();
        for (int row = 0; row < aryMenus.size(); row++) {
          menuVo = new UserMenuVO();
          menuVo = (UserMenuVO) aryMenus.get(row);
          if (menuVo.getNodeid().trim().equals(String.valueOf(vo.getId()))) {
            sbWhere.append(" nodeid = '").append(vo.getId());
            sbWhere.append("' OR");
            iCount++;
            break;
          }
        }
      }

      //log.showLog("------sbSql---4--" + sbSql.toString());

      if (iCount <= 0) {
        //容错处理
        bRet = false;
        sbWhere.append(" 1<> 1)");
        sbSql.append(sbWhere.toString());
      }
      else {
        //去掉 最后面那个or
        sbSql.append(sbWhere.toString().substring(0,
                                                  sbWhere.toString().length() -
                                                  2));
        sbSql.append(")");
      }

      helper.execUpdate(sbSql.toString());

      helper.commit(); //事实提交

    }
    catch (DefaultException e) {
      e.printStackTrace();
      e.printStackTrace(System.err);
      helper.rollback();
      bRet = false;
      throw new DAOException("ORG_Homepage_002", e);
    }
    finally {
      try {
        helper.end();
      }
      catch (DefaultException e) {
        bRet = false;
        throw new DAOException("ORG_Homepage_002", e);
      }

    }
    return bRet;

  }

/*   杨帆 2004-12-02注释 由于此方法引起用户登录或权限改变时改变用户自定义菜单，因此改写此方法，具体实现见上一方法
    public boolean resetUserMenus(String userID, Collection resourceList) throws
        DAOException {
        boolean bRet = true;
        //log.showLog("------------start---");
        String strMethod =
            "resetUserMenus(String userID, Collection moduleList)\n";
//        List aryResult =  resourceList;

        StringBuffer sbSql = new StringBuffer();

        try {
            helper.begin();
            //先统计出数据
            sbSql.setLength(0);
           sbSql.append(" delete from  T_USER_MENU  WHERE EMPID ='");
           sbSql.append(userID).append("'  ");
           helper.execUpdate(sbSql.toString());
           sbSql.setLength(0);

            sbSql.append(" SELECT COUNT(*) AS CNT FROM T_USER_MENU where EMPid='");
            sbSql.append(userID).append("'");

            Map map = (HashMap) helper.query(sbSql.toString(),
                                             DataTypes.RS_SINGLE_MAP);
            long lngCnt = NumberUtil.stringToLong( (String) map.get("cnt"), 0);
            //log.showLog("------sbSql-----" + sbSql.toString());
            //如果小于或者0的时候,需要从资源表读取数据


            if (lngCnt <= 0l) {
                sbSql.setLength(0);
                sbSql.append(" INSERT INTO T_USER_MENU(EMPID,NODEID,NODENAME,");
                sbSql.append("        PARENTID,LINK, ISFOLDER ,ORDERID) ");
                sbSql.append("SELECT '").append(userID).append("'AS EMPID,");
                sbSql.append("    CAST(a.id AS CHAR(10)),a.NAME, ");
                sbSql.append(
                    "     CAST (a.parent_id AS CHAR(20)),a.link as link,");
                sbSql.append("      case when (Select COUNT(*) AS CNT  ");
                sbSql.append("      FROM T_RESOURCE b WHERE a.id<>b.id ");
                sbSql.append(
                    "      AND b.parent_id=a.id ) >0 then '1' else '0' ");
                sbSql.append("     end AS ISFOLDER,A.ID AS ORDERID ");
                sbSql.append(" FROM T_RESOURCE as A ");
                //log.showLog("------sbSql----1-" + sbSql.toString());
                helper.execUpdate(sbSql.toString());

            } else {
                sbSql.setLength(0);
                sbSql.append(" UPDATE T_USER_MENU SET FLAG='0' WHERE EMPID ='");
                sbSql.append(userID).append("' and isFolder='0' ");
                helper.execUpdate(sbSql.toString());
            }

            //查出所有的菜单的资料
            List aryMenus = new ArrayList();
            UserMenuVO menuVo = new UserMenuVO();

            sbSql.setLength(0);
            sbSql.append("SELECT EMPID,NODEID,NODENAME,");
            sbSql.append("       PARENTID,LINK, ISFOLDER ,ORDERID, ");
            sbSql.append("       FLAG,MEMO ");
            sbSql.append("FROM T_USER_MENU where empid='").append(userID).append("'");

            aryMenus = (ArrayList) helper.query(sbSql.toString(), menuVo,
                                                DataTypes.RS_LIST_VO);


            //根据比对,赋值给权限
            StringBuffer sbWhere = new StringBuffer();
            sbSql.setLength(0);
            sbSql.append(" UPDATE T_USER_MENU SET FLAG='1' WHERE EMPID ='");
            sbSql.append(userID).append("'");
            sbSql.append(" AND (");
            //log.showLog("------sbSql---3--" + sbSql.toString());
            int iCount = 0;
            if (resourceList == null){
                resourceList = new ArrayList();
            }
            java.util.Iterator aryResult = resourceList.iterator();
            while(aryResult.hasNext()){

            //for (int idx = 0; idx < aryResult.size(); idx++) {
                Resource vo = new DefaultResource();
                vo = (DefaultResource) aryResult.next();
                for (int row = 0; row < aryMenus.size(); row++) {
                    menuVo = new UserMenuVO();
                    menuVo = (UserMenuVO) aryMenus.get(row);
                    if (menuVo.getNodeid().trim().equals(String.valueOf(vo.getId()))) {
                        sbWhere.append(" nodeid = '").append(vo.getId());
                        sbWhere.append("' OR");
                        iCount++;
                        break;
                    }
                }
            }

            //log.showLog("------sbSql---4--" + sbSql.toString());

            if (iCount <= 0) {
                //容错处理
                bRet = false;
                sbWhere.append(" 1<> 1)");
                sbSql.append(sbWhere.toString());
            } else {
                //去掉 最后面那个or
                sbSql.append(sbWhere.toString().substring(0,
                    sbWhere.toString().length() - 2));
                sbSql.append(")");
            }


            helper.execUpdate(sbSql.toString());

            helper.commit(); //事实提交

        } catch (DefaultException e) {
            e.printStackTrace();
          e.printStackTrace(System.err);
            helper.rollback();
            bRet = false;
            throw new DAOException("ORG_Homepage_002", e);
        } finally {
            try {
                helper.end();
            } catch (DefaultException e) {
                bRet = false;
                throw new DAOException("ORG_Homepage_002", e);
            }

        }
        return bRet;

    }
*/
    /**
     * 查出该用户所有的菜单资料
     * @param userID 用户ID号
     * @return 菜单列表
     * @throws DefaultException
     */
    public boolean isHaveAcl(String userID) throws DAOException {
        boolean bRet = false;
        StringBuffer sbSql = new StringBuffer();
        List aryData = new ArrayList();
        String[] values = new String[1];
        values[0] = userID;
        UserMenuVO vo = new UserMenuVO();

        sbSql.append("SELECT count(NodeID) as cnt ");
        sbSql.append("FROM T_USER_MENU ");
        sbSql.append("WHERE (EmpID=?) AND (IsHided='0')");
        sbSql.append("  and isMenu='1'   and Flag='1' ");

        String[] arySql = new String[2];
        arySql[0] = sbSql.toString();
        arySql[1] = "String";

        try {

            Map mapCount = (HashMap) helper.query(arySql, values, vo,
                                                  DataTypes.RS_SINGLE_MAP);
            long lngCnt = NumberUtil.stringToLong(mapCount.get("cnt").toString(),
                                                  0);
            if (lngCnt > 0) {
                bRet = true;
            }

        } catch (DefaultException e) {
            throw new DAOException("ORG_Homepage_001", e);
        }
        return bRet;
    }



    public void setAryMenusTwo(List aryMenusTwo) {
        this.aryMenusTwo = aryMenusTwo;
    }

    public List getAryMenusTwo() {
        return aryMenusTwo;
    }

    public List getAryMenusAll() {
        return aryMenusAll;
    }

    public void setAryMenusAll(List aryMenusAll) {
        this.aryMenusAll = aryMenusAll;
    }

}
