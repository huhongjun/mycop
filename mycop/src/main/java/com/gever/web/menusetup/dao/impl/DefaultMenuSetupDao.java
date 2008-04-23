package com.gever.web.menusetup.dao.impl;

import java.util.*;

import com.gever.config.Constants;
import com.gever.exception.*;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.sqlhelper.*;
import com.gever.util.log.*;
import com.gever.web.homepage.vo.*;
import com.gever.web.menusetup.dao.MenuSetupDao;
import com.gever.web.menusetup.dao.MenuSetupFactory;
import com.gever.web.menusetup.dao.SQLFactory;

import org.apache.commons.lang.NumberUtils;

/**
 * <p>Title: 定制菜单实现类</p>
 * <p>Description: 有</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class DefaultMenuSetupDao implements MenuSetupDao {
//    private static String QUERY_ALL = "SELECT a.NodeID,a.NodeName,a.ParentID,a.Link,a.IsFolder,a.IsHided,a.isMenu,a.OrderID,(SELECT COUNT(*) AS CNT FROM T_USER_MENU AS B WHERE a.nodeid<>b.nodeid  AND parentid=a.nodeid and b.empid=? and b.isMenu='1' and b.flag='1' )as nextnodenum FROM T_USER_MENU as a WHERE (EmpID=?) AND a.Flag='1' and a.isMenu='1' ORDER BY a.OrderID";
    protected static final String PATH_SPLIT = "-->"; //层次目录分隔
    protected static final String OPTION_SPLIT = "&nbsp;&nbsp;"; //层次目录分隔
    protected static SQLFactory sqlFactory = MenuSetupFactory.getInstance().getSQLFactory();
    protected static Log log = Log.getInstance(DefaultMenuSetupDao.class);
    protected SQLHelper helper = null;
    protected String dbData = "gdp";
    protected Collection menuDatas = null;

    public DefaultMenuSetupDao() {
        this.dbData = Constants.DATA_SOURCE;
        helper = new DefaultSQLHelper(dbData);
        menuDatas = new ArrayList();
    }

    public DefaultMenuSetupDao(String dbData) {
        this.dbData = dbData;
        helper = new DefaultSQLHelper(dbData);
        menuDatas = new ArrayList();
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
     * 查寻所有的
     * @param userId 当前用户ID
     * @return 返回所有菜单列表
     * @throws DAOException
     */
    public Collection queryAll(String userId) throws DAOException {

        Collection cData = new ArrayList();
//        StringBuffer sbSql = new StringBuffer();
//
//        sbSql.append("SELECT a.NodeID,a.NodeName,a.ParentID,a.Link, ");
//        sbSql.append("       a.IsFolder,a.IsHided,a.isMenu,a.OrderID,");
//        sbSql.append("  (SELECT COUNT(*) AS CNT FROM T_USER_MENU AS B ");
//        sbSql.append("   WHERE a.nodeid<>b.nodeid  AND b.parentid=a.nodeid ");
//        sbSql.append("       and b.empid=? and b.isMenu='1' and b.flag='1' )as nextnodenum ");
//        sbSql.append("FROM T_USER_MENU as a ");
//        sbSql.append("WHERE (EmpID=?) AND ");
//        sbSql.append("      a.Flag='1' and a.isMenu='1'  ORDER BY a.OrderID ");
//        Log.setUselog4j(false);
        String[] arySql = new String[2];
        arySql[0] = sqlFactory.get("QUERY_ALL");//sbSql.toString();
        arySql[1] = "String,String";
        String[] values = new String[2];
        values[0] = userId;
        values[1] = userId;
        UserMenuVO vo = new UserMenuVO();

        int next = 0;
        String strPathName = "";
        int pos = -1;
        List aryData = new ArrayList();
        try {
            cData = (ArrayList) helper.query(arySql, values, vo,
                                             DataTypes.RS_LIST_VO);
            aryData = (ArrayList) cData;
            for (int idx = 0; idx < aryData.size(); idx++) {
                vo = new UserMenuVO();
                vo = (UserMenuVO) aryData.get(idx);
                vo.setNodeid(nullToString(vo.getNodeid()).trim());
                vo.setParentid(nullToString(vo.getParentid()).trim());
                vo.setIsfolder(nullToString(vo.getIsfolder()).trim());
                vo.setNextNodeNum(vo.getNextnodenum().trim());
                next = NumberUtils.stringToInt(vo.getNextnodenum(), 0);

                this.curLevel = 0;   //当前层级
                strPathName = getPathName(vo.getNodeid(), aryData);
                pos = strPathName.lastIndexOf(PATH_SPLIT);
                if (pos > 0)
                    strPathName = strPathName.substring(0, pos);
                vo.setPathname(strPathName);

                this.nextLevel = 0;
                this.level = 0;
                this.sumNum =0;
                this.hideNum = 0;

                vo.setLevel(String.valueOf(this.curLevel));
                getNextLevel(vo, aryData);
                vo.setNextlevel(String.valueOf(this.nextLevel));
                vo.setSumnum(String.valueOf(sumNum));
                vo.setHidenum(String.valueOf(hideNum));
                aryData.set(idx, vo);
            }

        } catch (DefaultException e) {
            throw new DAOException("ORG_MenuSetup_001", e);
        }
        return aryData;
    }

    private int nextLevel = 0;  //下级层数
    private int sumNum = 0;  //总菜单数量
    private int hideNum = 0;  //隐藏菜单
    private List aryMenusAll = new ArrayList();

    /**
     * 得到下级层数(递归)
     * @param menuVo 当前的vo的对象
     * @param allMenus 所有的菜单对象
     */
    private void getNextLevel(UserMenuVO menuVo, List allMenus) {

        List aryDir = getNextMenu(menuVo.getNodeid(), allMenus);
        sumNum +=aryDir.size();
        if (aryDir.size() <= 0){
            return;
        }

        ++nextLevel;  //层级+1
        for (int idx = 0; idx < aryDir.size(); idx++) {
            UserMenuVO vo = new UserMenuVO();
            vo = (UserMenuVO) aryDir.get(idx);
            if ("1".equals(vo.getIshided())){  //隐蔽菜单
                ++hideNum;
            }

            getNextLevel(vo, allMenus);
        }

    }
    /**
     * 查寻第一级菜单
     * @param allMenu 所有菜单列表
     * @return 一级菜单列表
     */
    public Collection queryFirstMenus(Collection allMenu) {
        StringBuffer sbSql = new StringBuffer();
        Collection firstMenu = new ArrayList();
        List aryResult = (ArrayList) allMenu;
        UserMenuVO vo = new UserMenuVO();
        int num = 0;
        for (int idx = 0; idx < aryResult.size(); idx++) {
            vo = new UserMenuVO();
            vo = (UserMenuVO) aryResult.get(idx);
            if ("0".equals(vo.getParentid()))  //如果是根节点的
                firstMenu.add(vo);
        }
        return firstMenu;
    }

    /**
     * 查寻下级菜单
     * @param userId 当前用户ID
     * @param nodeId 当前节点ID
     * @return 下级菜单列表
     * @throws DAOException
     */
    public Collection queryNextMenus(String userId, String nodeId) throws
        DAOException {
        Collection nextMenu = new ArrayList();
        StringBuffer sbSql = new StringBuffer();

        sbSql.append("SELECT NodeID,NodeName,ParentID,Link, ");
        sbSql.append("       IsFolder,IsHided,isMenu,OrderID,memo ");
        sbSql.append("FROM T_USER_MENU ");
        sbSql.append("WHERE (EmpID=?) AND parentid = ?  AND  flag='1' and isMenu='1' order by OrderID");
        String[] arySql = new String[2];
        arySql[0] = sbSql.toString();
        arySql[1] = "String,String";
        String[] values = new String[2];
        values[0] = userId;
        values[1] = nodeId;
        UserMenuVO vo = new UserMenuVO();

        //查寻数据
        try {
            nextMenu = (ArrayList) helper.query(arySql, values, vo,
                                                DataTypes.RS_LIST_VO);
        } catch (DefaultException e) {
            throw new DAOException("ORG_MenuSetup_002", e);
        }
        return nextMenu;

    }

    /**
     * 新增菜单
     * @param menuVo 当前菜单VO对象
     * @return 修改成功记录数
     * @throws DAOException
     */
    public int update(UserMenuVO menuVo) throws DAOException {

        StringBuffer sbSql = new StringBuffer();
        StringBuffer sbWhere = new StringBuffer();
        sbSql.append(" UPDATE T_USER_MENU SET NODENAME = ? ");
        sbSql.append(" WHERE NODEID =? AND EMPID=?");
        String columns = "nodename,nodeid,empid,";

        int intRet = -1;
        try {
            intRet = helper.execUpdate(sbSql.toString(), menuVo, columns);

        } catch (DefaultException e) {
            throw new DAOException("ORG_MenuSetup_004", e);
        }
        return intRet;
    }

    /**
     * 隐蔽菜单
     * @param menuVo 当前菜单VO对象
     * @return 修改成功记录数
     * @throws DAOException
     */
    public int insert(UserMenuVO menuVo) throws DAOException {
        helper.setAutoID(false);
        StringBuffer sbSql = new StringBuffer();
//        sbSql.append("SELECT MAX(CAST(NODEID AS INT))+1 AS  cnt , ");
//        sbSql.append("MAX(CAST(ORDERID AS INT))+1 AS ord ");
		sbSql.append(sqlFactory.get("INSERT"));
        sbSql.append(" FROM T_USER_MENU ");
        sbSql.append("WHERE EMPID='").append(menuVo.getEmpid()).append("'");
        int intRet = -1;

        try {
//            System.out.println(sbSql.toString());
            Map map = (HashMap) helper.query(sbSql.toString(),
                                             DataTypes.RS_SINGLE_MAP);
            String nodeid = (String) map.get("cnt");
            menuVo.setNodeid(nodeid);
            menuVo.setLinkmode("0");
            menuVo.setIsmenu("1");
            menuVo.setOrderid( (String) map.get("ord"));
            intRet = helper.insert(menuVo);
        } catch (DefaultException e) {
            throw new DAOException("ORG_MenuSetup_003", e);
        }
        return intRet;
    }

    /**
     * 删除菜单
     * @param userId 当前菜单VO对象
     * @param delMenus 要删除的菜单
     * @return 修改成功记录数
     * @throws DAOException
     */
    public int delete(String userId, String[] delMenus) throws DAOException {
        int intRet = -1;
        StringBuffer sbSql = new StringBuffer();
        StringBuffer sbWhere = new StringBuffer();

        sbSql.append(" UPDATE T_USER_MENU SET ISMENU ='0' ");
        sbSql.append(" WHERE EMPID= '").append(userId).append("'");
        sbSql.append(" AND (");
        String strWhere = " 1<>1)";
        for (int idx = 0; idx < delMenus.length; idx++) {
            sbWhere.append(" nodeid ='").append(delMenus[idx]).append("'");
            if (idx < delMenus.length - 1)
                sbWhere.append(" or ");
            else
                sbWhere.append(")");
        }
        if (delMenus.length >0)
            strWhere = sbWhere.toString();
        sbSql.append(strWhere);

        try {
            helper.execUpdate(sbSql.toString());

        } catch (DefaultException e) {
            throw new DAOException("ORG_MenuSetup_004", e);
        }

        return intRet;
    }

    /**
     * 删除用户时删除菜单中用户关联数据
     * @author Hu.Walker
     * @param  userId 当前菜单VO对象
     * @return void
     * @throws DAOException
     */
    public void deleteByUserID(String[] userId) throws DAOException {
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM T_USER_MENU WHERE EMPID='");
        
        for(int i=0; i<userId.length; i++){
            sql.append(userId[i] + "' OR EMPID=");
        }
        sql.append("'-100'");
        
        try {
            helper.execUpdate(sql.toString());

        } catch (DefaultException e) {
            throw new DAOException("ORG_MenuSetup_deleteByUserID", e);
        }
    }

    /**
     * 按主键查询
     * @param menuVo 当前的vo对象
     * @return VO对象
     * @throws DAOException
     */
    public UserMenuVO queryByPk(UserMenuVO menuVo) throws DAOException {
        try {
            return (UserMenuVO) helper.queryByPK(menuVo);
        } catch (DefaultException e) {
            throw new DAOException("ORG_MenuSetup_005", e);
        }
    }

    /**
     * 查寻可以删除的资料
     * @param userId 当前用户ID
     * @return 可以删除的资料
     * @throws DAOException
     */
    public Collection queryByDelete(String userId) throws DAOException {
        Collection cData = new ArrayList();
        StringBuffer sbSql = new StringBuffer();

        cData = queryAll(userId);
        UserMenuVO vo = null;
        List aryData = (ArrayList) cData;
        int pos = -1;
        List menus = new ArrayList();
        int next = 0;
        for (int idx = 0; idx < aryData.size(); idx++) {
            vo = new UserMenuVO();
            vo = (UserMenuVO) aryData.get(idx);
            next = NumberUtils.stringToInt(vo.getNextnodenum(),0);
            if ("1".equals(vo.getIsfolder()) && Integer.parseInt(vo.getNodeid()) > 12 && next == 0  ){
               menus.add(vo);
           }
            aryData.set(idx, vo);
        }

        return menus;
    }
    int curLevel = 0;
    /**
     * 递归算法得到路径完整名称
     * @param strNodeID 当前节点
     * @param aryDir 整个列表(用于查找)
     * @return 最终的路径名
     */
    private String getPathName(String strNodeID, List aryDir) {
        //如果已经是根节点,
        if (strNodeID == null || "0".equals(strNodeID)||"".equals(strNodeID)) {
            return "";
        }

        String strRet = new String();
        for (int idx = 0; idx < aryDir.size(); idx++) {
            UserMenuVO vo = new UserMenuVO();
            vo = (UserMenuVO) aryDir.get(idx);
            if (strNodeID.equals(vo.getNodeid().trim())) {
                strRet = vo.getNodename();
                ++curLevel;
                strRet = getPathName(vo.getParentid(), aryDir) + strRet +
                    PATH_SPLIT;
                break;
            }
        }
        return strRet;
    }

    /**
     * 隐蔽菜单
     * @param showMenus 菜单id列表
     * @param hideMenus 隐藏菜单
     * @param userId 用户ID列表
     * @return 修改成功记录数
     * @throws DAOException
     */
    public int hide(String[] showMenus, String[] hideMenus, String userId) throws
        DAOException {
        int intRet = -1;
        //容错处理
        if (showMenus == null) {
            showMenus = new String[0];
        }
        if (hideMenus == null) {
            hideMenus = new String[0];
        }

        if (showMenus.length + hideMenus.length <= 0)
            return intRet;
        StringBuffer sbSql = new StringBuffer();
        sbSql.append("UPDATE T_USER_MENU SET ISHIDED =?, ORDERID = ?");
        sbSql.append(" WHERE EMPID= ?");
        sbSql.append(" AND NODEID=?");
        String types = "String,long,String,String";
        String values[] = new String[4];
        try {
            helper.begin();
            //显示的菜单
            for (int idx = 0; idx < showMenus.length; idx++) {
                values[0] = "0";
                values[1] = String.valueOf(idx);
                values[2] = userId;
                values[3] = showMenus[idx];
                intRet = 0;
                intRet += helper.execUpdate(sbSql.toString(), values, types);
            }
            //隐茂的菜单
            for (int idx = 0; idx < hideMenus.length; idx++) {
                values[0] = "1";
                values[1] = String.valueOf(idx);
                values[2] = userId;
                values[3] = hideMenus[idx];
                intRet = 0;
                intRet += helper.execUpdate(sbSql.toString(), values, types);
            }
            helper.commit();

        } catch (DefaultException e) {
            helper.rollback();
            throw new DAOException("ORG_MenuSetup_006", e);
        } finally {
            try {
                helper.end();
            } catch (DefaultException e) {
                throw new DAOException("ORG_MenuSetup_006", e);
            }
        }
        return intRet;
    }

    /**
     * 重新设置菜单
     * @param userId 当前用户id
     * @return 返回是否成功
     * @throws DAOException
     */
    public boolean resetMenus(String userId) throws DAOException {
        StringBuffer sbSql = new StringBuffer();
        boolean bRet = true;
        try {
            helper.begin();
            sbSql.append(" DELETE FROM T_USER_MENU WHERE EMPID=");
            sbSql.append("'").append(userId).append("'");
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(" INSERT INTO T_USER_MENU(EMPID,NODEID,NODENAME,PARENTID,");
            sbSql.append("LINK,LINKMODE,ISFOLDER,ISHIDED,ORDERID,ISMENU,FLAG,MEMO) ");
            sbSql.append(" SELECT '").append(userId).append("' AS EMPID,NODEID,NODENAME,PARENTID,");
            sbSql.append("LINK,LINKMODE,ISFOLDER,ISHIDED,ORDERID,ISMENU,FLAG,MEMO");
            sbSql.append(" FROM T_USER_MENU WHERE EMPID = '-1'");
            
			helper.execUpdate(sbSql.toString());
        } catch (DefaultException e) {
            helper.rollback();
            bRet = false;
            throw new DAOException("ORG_MenuSetup_004", e);

        } finally {
            try {
                helper.end();
            } catch (DefaultException e) {
                bRet = false;
                throw new DAOException("ORG_MenuSetup_004", e);
            }
        }
        return bRet;

    }
    /**
     * 重新设置系统默认菜单  2004-11-25 杨帆添加
     * @param userId 当前用户id
     * @return 返回是否成功
     * @throws DAOException
     */
    public boolean resetDefaultMenus(String userId) throws DAOException {
        StringBuffer sbSql = new StringBuffer();
        boolean bRet = true;
        try {
            helper.begin();
            sbSql.append(" DELETE FROM T_USER_MENU WHERE EMPID=");
            sbSql.append("'").append(userId).append("'");
            helper.execUpdate(sbSql.toString());

            sbSql.setLength(0);
            sbSql.append(" INSERT INTO T_USER_MENU(EMPID,NODEID,NODENAME,");
            sbSql.append(" PARENTID,LINK, ISFOLDER ,ORDERID,FLAG) ");
            sbSql.append(" SELECT '").append(userId).append("' AS EMPID,");
            sbSql.append(" CAST(A.id AS CHAR(10)),A.NAME, ");
            sbSql.append(" CAST(A.parent_id AS CHAR(20)),A.link as link,");
            sbSql.append(" case when (Select COUNT(*) AS CNT  ");
            sbSql.append(" FROM T_RESOURCE B WHERE A.id<>B.id ");
            sbSql.append(" AND B.parent_id=A.id ) >0 then '1' else '0' ");
            sbSql.append(" end AS ISFOLDER,A.ID AS ORDERID,'1'");
            sbSql.append(" FROM T_RESOURCE A ");

           helper.execUpdate(sbSql.toString());
           helper.commit(); //事实提交

        } catch (DefaultException e) {
            helper.rollback();
            bRet = false;
            throw new DAOException("ORG_MenuSetup_004", e);

        } finally {
            try {
                helper.end();
            } catch (DefaultException e) {
                bRet = false;
                throw new DAOException("ORG_MenuSetup_004", e);
            }
        }
        return bRet;

    }

    /**
     * 移动菜单
     * @param menuIds 菜单列表
     * @param userId 当前用户ID
     * @param nodeId 当前节点
     * @return 搬移的数据
     * @throws DAOException
     */
    public int move(String[] menuIds, String userId, String nodeId) throws
        DAOException {
        int intRet = -1;
        //容错处理
        if (menuIds == null) {
            menuIds = new String[0];
        }

        if (menuIds.length <= 0)
            return intRet;

        StringBuffer sbSql = new StringBuffer();
        StringBuffer sbWhere = new StringBuffer();
        sbSql.append(" UPDATE T_USER_MENU SET parentid =? ,ORDERID = ?");
        sbSql.append(" WHERE EMPID= ? and nodeid=? ");

        String[] values = new String[4];
        String types = "String,long,String,String";
        try {
            helper.begin();
            for (int idx = 0; idx < menuIds.length; idx++) {
                values[0] = nodeId;
                values[1] = String.valueOf(idx);
                values[2] = userId;
                values[3] = menuIds[idx];
                intRet = 0;
                intRet += helper.execUpdate(sbSql.toString(), values, types);
            }
            helper.commit();
        } catch (DefaultException e) {
            helper.rollback();
            throw new DAOException("ORG_MenuSetup_007", e);
        } finally {
            try {
                helper.end();
            } catch (DefaultException e) {
                throw new DAOException("ORG_MenuSetup_007", e);
            }
        }
        return intRet;
    }

    /**
     * 得到下级菜单
     * @param strNodeID 当前用户节点
     * @param aryFiles 所有文件
     * @return 取得下级菜单数据
     */
    public List getNextMenu(String strNodeID, List aryFiles) {
        List aryTmp = new ArrayList();
        if (aryFiles.size() <= 0)
            return aryTmp;

        for (int row = 0; row < aryFiles.size(); row++) {
            UserMenuVO fileView = new UserMenuVO();
            fileView = (UserMenuVO) aryFiles.get(row);
            if (strNodeID.equals(fileView.getParentid().trim())) {
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
            level = 0;
            if (strNodeID.equals(fileView.getParentid())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到像Tree
     * @param allMenus 当前用户ID
     * @return Option HTML
     * @throws DAOException
     */
    public String getTreeOption(Collection allMenus) throws DAOException {

        StringBuffer sbOption = new StringBuffer();
        sbOption.append(" <select name=\"allMenuSel\" ");
        sbOption.append("style=\"width:200\" ");
        sbOption.append("onchange=\"javascript:onChangeDir(this.value);\">");
        if (allMenus.size() < 1)
            return sbOption.toString();
        this.aryMenusAll = new ArrayList();

        List firstMenu = (ArrayList)this.queryFirstMenus(allMenus);
        UserMenuVO vo = new UserMenuVO();
        sbOption.append("<option value=-1>请选择</option>");

        List aryList = (ArrayList) allMenus;
        for (int idx = 0; idx < aryList.size(); idx++) {
            vo = new UserMenuVO();
            vo = (UserMenuVO) aryList.get(idx);
            level = 1;
            if ("1".equals(vo.getIsfolder())) {
                aryMenusAll.add(vo);
            }
        }
        for (int idx = 0; idx < firstMenu.size(); idx++) {
            vo = new UserMenuVO();
            vo = (UserMenuVO) firstMenu.get(idx);
            level = 1;
            if ("1".equals(vo.getIsfolder())) {
                aryMenusAll.add(vo);
            }

            sbOption.append("<option value=");
            sbOption.append(vo.getNodeid());
            sbOption.append(">").append(vo.getNodename());
            sbOption.append("</option>");
            getOptionValue(vo, sbOption);
        }
        sbOption.append("</select>");

        return sbOption.toString();

    }

    private  int level = 0; //当前层级
    /**
     * 组织option
     * @param menuVo 当前菜单vo对象
     * @param sbOption 字串处理
     */
    private boolean bIsOk = false;
    private void getOptionValue(UserMenuVO menuVo, StringBuffer sbOption) {

        if ("0".equals(menuVo.getIsfolder())) {
            --level;
            return;
        }
        List aryDir = getNextMenu(menuVo.getNodeid(), this.aryMenusAll);
        if (aryDir.size() <= 0 && "1".equals(menuVo.getIsfolder())) {
            return;
        }
        ++level;
        for (int idx = 0; idx < aryDir.size(); idx++) {
            UserMenuVO vo = new UserMenuVO();
            vo = (UserMenuVO) aryDir.get(idx);
            //菜单显示文本

            sbOption.append("<option value=");
            sbOption.append(vo.getNodeid());
            sbOption.append(">").append(getSpace(NumberUtils.stringToInt(vo.getLevel())));
            sbOption.append(vo.getNodename());
            sbOption.append("</option>");
            getOptionValue(vo, sbOption);


        }

    }

    /**
     * 得到空格数
     * @param num 第几级
     * @return 要得到多少个空格
     */
    private String getSpace(int num) {
        StringBuffer sbSpace = new StringBuffer();
        for (int idx = 0; idx < num; idx++)
            sbSpace.append(this.OPTION_SPLIT);
        return sbSpace.toString();
    }

    /**
     * 得到所有的节点的(01-->0101--->010101)
     * @param strNodeID 当前节点
     * @param aryDir 相对应的菜单数
     * @return (01-->0101--->010101)
     */
    public String getFullNodeid(String strNodeID, List aryDir) {
        //如果已经是根节点,
        if (strNodeID == null || "0".equals(strNodeID) || "".equals(strNodeID)) {
            return "";
        }

        String strRet = new String();
        for (int idx = 0; idx < aryDir.size(); idx++) {
            UserMenuVO vo = new UserMenuVO();
            vo = (UserMenuVO) aryDir.get(idx);
            if (strNodeID.equals(vo.getNodeid().trim())) {
                strRet = vo.getNodeid();
                strRet = getFullNodeid(vo.getParentid(), aryDir) + strRet +
                    PATH_SPLIT;
                break;
            }
        }
        return strRet;
    }

}
