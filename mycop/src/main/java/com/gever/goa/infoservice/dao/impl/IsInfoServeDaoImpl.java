package com.gever.goa.infoservice.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.dao.MessageDao;
import com.gever.goa.dailyoffice.message.dao.MessageFactory;
import com.gever.goa.infoservice.dao.IsInfoServeDao;
import com.gever.goa.infoservice.vo.DeptInfoTreeVO;
import com.gever.goa.infoservice.vo.DeptreeVO;
import com.gever.goa.infoservice.vo.IsDepartmentVO;
import com.gever.goa.infoservice.vo.IsInfoServeVO;
import com.gever.goa.infoservice.vo.ManageSystemTreeVO;
import com.gever.goa.infoservice.vo.OrgInfoTreeVO;
import com.gever.goa.infoservice.vo.TradeNewsTreeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.sysman.api.OrganizationUtil;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 组织信息DAO实现类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsInfoServeDaoImpl extends BaseDAO implements IsInfoServeDao {
    //取得查询条件
    private static StringBuffer querySQL = new StringBuffer();

    //树的查询
    private static StringBuffer treeSQL = new StringBuffer();

    //部门树的查询
    private static String deptinfoTreeSql =
        "SELECT IS_DOTYPE.INFO_TYPE as nodeid," +
        "IS_DOTYPE.INFO_TYPE as nodename," +
        "CASE WHEN(SELECT IS_DOTYPE.TYPELEVEL FROM IS_DOTYPE TEMPTABLE WHERE IS_DOTYPE.INFO_TYPE=TEMPTABLE.INFO_TYPE)=1 THEN '1' ELSE '0' END as isfolder," +
        "&deptid& as memo " + "FROM IS_DOTYPE " + "WHERE 1=1";

    //gw add 部门信息树查询SQL 取得部门人员与其对应的部门列表
    private static String depTreeSql = " Select dep.id as nodeid,dep.name as nodename,'1' as isfolder from T_DEPARTMENT dep,T_user us,T_Department_Person dep_us where dep.id=dep_us.department_id and dep_us.id=us.id ";

    //delBySelect_list_sql
    private static String DELBYSELECT_LIST_SQL = " SELECT serial_num, file_path from is_info_serve where ";

    //queryByUserId_SQL
    private static String QUERYBYUSERID_SQL = " Select dep.* from T_DEPARTMENT  dep,T_user  us,T_Department_Person  dep_us where ";

    static {
        querySQL.append(" SELECT ");
        querySQL.append(" IS_INFO_SERVE.SERIAL_NUM, ");
        querySQL.append(" IS_INFO_SERVE.TITLE, ");
        querySQL.append(" IS_INFO_SERVE.INFO_TYPE, ");
        querySQL.append(" IS_INFO_SERVE.DEPT, ");
        querySQL.append(" IS_INFO_SERVE.USER_CODE, ");
        querySQL.append(" IS_INFO_SERVE.CREATE_DATE, ");
        querySQL.append(" IS_INFO_SERVE.FILE_PATH, ");
        querySQL.append(" IS_INFO_SERVE.FILE_NAME, ");
        querySQL.append(" IS_INFO_SERVE.WORD_CONTENT, ");
        querySQL.append(" IS_INFO_SERVE.CONTENT, ");
        querySQL.append(" IS_INFO_SERVE.HINT_FLAG, ");
        querySQL.append(" IS_INFO_SERVE.SEND_FLAG, ");
        querySQL.append(" IS_INFO_SERVE.EDITOR_TYPE, ");
        querySQL.append(" IS_INFO_SERVE.INFO_FLAG, ");
        querySQL.append(" is_info_serve.show_flag, ");
        querySQL.append(" T_USER.NAME AS USER_NAME ");
        querySQL.append(" FROM ");
        querySQL.append(" IS_INFO_SERVE ");
        querySQL.append(
            " LEFT JOIN IS_DOTYPE ON (IS_INFO_SERVE.INFO_TYPE=IS_DOTYPE.INFO_TYPE) ");
        querySQL.append(
            " LEFT JOIN T_USER ON (IS_INFO_SERVE.USER_CODE=T_USER.ID) ");
        querySQL.append(" WHERE 1=1 ");
    }

    static {
        treeSQL.append(" SELECT ");
        treeSQL.append(" info_type as nodeid, remark as nodename, ");
        treeSQL.append(" case when ");
        treeSQL.append("( Select count(*) from IS_DOTYPE b ");
        treeSQL.append(" where b.parent_type=a.info_type )>0 ");
        treeSQL.append(" then 1 else 0 end as isfolder, ");
        treeSQL.append(" moduleflag as memo");
        treeSQL.append(" from is_dotype a where 1=1 ");
    }

    //GW ADD 获取文件路径
    private String realPath = "";

    public IsInfoServeDaoImpl(String dbData) {
        super(dbData);
    }

    /**
     * 连接oracle数据库SQL
     */
    public void setOracleSQL() {
        querySQL.setLength(0);
        querySQL.append(" SELECT ");
        querySQL.append(" IS_INFO_SERVE.SERIAL_NUM, ");
        querySQL.append(" IS_INFO_SERVE.TITLE, ");
        querySQL.append(" IS_INFO_SERVE.info_type, ");
        querySQL.append(" IS_INFO_SERVE.DEPT, ");
        querySQL.append(" IS_INFO_SERVE.USER_CODE, ");
        querySQL.append(" IS_INFO_SERVE.CREATE_DATE, ");
        querySQL.append(" IS_INFO_SERVE.FILE_PATH, ");
        querySQL.append(" IS_INFO_SERVE.FILE_NAME, ");
        querySQL.append(" IS_INFO_SERVE.WORD_CONTENT, ");
        querySQL.append(" IS_INFO_SERVE.CONTENT, ");
        querySQL.append(" IS_INFO_SERVE.HINT_FLAG, ");
        querySQL.append(" IS_INFO_SERVE.SEND_FLAG, ");
        querySQL.append(" IS_INFO_SERVE.EDITOR_TYPE, ");
        querySQL.append(" IS_INFO_SERVE.INFO_FLAG, ");
        querySQL.append(" is_info_serve.show_flag, ");
        querySQL.append(" T_USER.NAME AS USER_NAME ");
        querySQL.append(" FROM ");
        querySQL.append(" IS_INFO_SERVE,IS_DOTYPE,T_USER ");
        querySQL.append(
            " where IS_INFO_SERVE.INFO_TYPE=IS_DOTYPE.INFO_TYPE(+) ");
        querySQL.append(" and IS_INFO_SERVE.USER_CODE=T_USER.ID(+) ");
    }

    public String getPageSql() {
        return querySQL.toString();
    }

    /**
     * @param 重载insert方法
     */
    public int insert(VOInterface vo) throws DefaultException {
        int retInt = 0;
        IsInfoServeVO iisVO = (IsInfoServeVO) vo;
        iisVO.setUser_code(super.userID);

        if (iisVO.getSend_flag().equals("1")) {
            iisVO.setCreate_date(DateTimeUtils.getCurrentDate());
        }

        super.insert(iisVO);
        retInt = this.sendMessage(iisVO);

        return retInt;
    }

    public int update(VOInterface vo) throws DefaultException {
        if (!super.isOracle()) {
            int retInt = 0;
            IsInfoServeVO iisVO = (IsInfoServeVO) vo;
            iisVO.setUser_code(super.userID);

            if (iisVO.getSend_flag().equals("1")) {
                iisVO.setCreate_date(DateTimeUtils.getCurrentDate());
            }

            super.update(iisVO);
            retInt = this.sendMessage(iisVO);

            return retInt;
        }

        SQLHelper helper = new DefaultSQLHelper(dbData);

        int iRet = -1;
        IsInfoServeVO iisVO = (IsInfoServeVO) vo;
        iisVO.setUser_code(super.userID);

        if (iisVO.getSend_flag().equals("1")) {
            iisVO.setCreate_date(DateTimeUtils.getCurrentDate());
        }

        super.update(iisVO);
        iRet = this.sendMessage(iisVO);

        //toDo
        try {
            helper.begin();
            helper.delete(vo);
            helper.setAutoID(false);
            iRet = helper.insert(vo);
        } catch (DefaultException e) {
            helper.rollback();
            throw new DefaultException(e);
        } finally {
            helper.end();
        }

        return iRet;

        //        int retInt = 0;
        //        IsInfoServeVO iisVO = (IsInfoServeVO) vo;
        //        iisVO.setUser_code(super.userID);
        //
        //        if (iisVO.getSend_flag().equals("1")) {
        //            iisVO.setCreate_date(DateUtil.getCurrentDate());
        //        }
        //
        //        super.update(iisVO);
        //
        //        retInt = this.sendMessage(iisVO);
        //
        //        return retInt;
    }

    public String getTreeSQL() {
        return treeSQL.toString();
    }

    public String getDepTreeSql() {
        return depTreeSql.toString();
    }

    public List getDepTreeData(String userid) throws DefaultException {
        List treeData = new ArrayList();
        DeptreeVO vo = new DeptreeVO();
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String sql = depTreeSql;

        if ((userid != null) && !userid.equals("")) {
            //            System.out.println(userid + "-----------------------");
            sql += ("and us.id=" + userid);
        }

        treeData = helper.queryByListVo(sql, vo);

        return treeData;
    }

    public List getTreeData(String paraFlag, String nodeID)
        throws DefaultException {
        List treeData = new ArrayList();

        OrgInfoTreeVO orgInfoTreeVO = new OrgInfoTreeVO();
        DeptInfoTreeVO deptInfoTreeVO = new DeptInfoTreeVO();
        TradeNewsTreeVO tradeNewsTreeVO = new TradeNewsTreeVO();
        ManageSystemTreeVO manageSystemTreeVO = new ManageSystemTreeVO();

        SQLHelper helper = helper = new DefaultSQLHelper(dbData);

        if ("2".equals(nodeID) || StringUtils.isNull(nodeID)) {
            nodeID = "";
        }

        String sql = this.getTreeSQL();

        //      转换字符
        if (!StringUtils.isNull(nodeID)) {
            sql += (" and parent_type='" + nodeID + "'");
        } else {
            sql += " and ( parent_type='' or parent_type is null ) ";
        }

        sql += (" and moduleflag ='" + paraFlag + "'");

        //        	System.out.println("______________________________________________________");
        //        	System.out.println(sql);
        //        	System.out.println("______________________________________________________");
        //        if(paraFlag!=null) {
        if (paraFlag.equals("2")) {
            treeData = (ArrayList) helper.queryByListVo(sql, orgInfoTreeVO);
        } else if (paraFlag.equals("3")) {
            treeData = (ArrayList) helper.queryByListVo(sql, deptInfoTreeVO);
        } else if (paraFlag.equals("5")) {
            treeData = (ArrayList) helper.queryByListVo(sql, tradeNewsTreeVO);
        } else {
            treeData = (ArrayList) helper.queryByListVo(sql, manageSystemTreeVO);
        }

        //        }
        return treeData;
    }

    public List getTreeData(String paraFlag, String nodeID, String deptid)
        throws DefaultException {
        if (StringUtils.isNull(deptid)) {
            return getTreeData(paraFlag, nodeID);
        }

        List treeData = null;

        String sql = "";

        SQLHelper helper = new DefaultSQLHelper(dbData);

        if ("2".equals(nodeID) || StringUtils.isNull(nodeID)) {
            nodeID = "";
        }

        //      转换字符
        if (!StringUtils.isNull(nodeID)) {
            sql += (" and parent_type='" + nodeID + "'");
        } else {
            sql += " and ( parent_type='' or parent_type is null ) ";
        }

        sql += (" and moduleflag ='" + paraFlag + "'");

        if (paraFlag.equals("2")) {
            OrgInfoTreeVO orgInfoTreeVO = new OrgInfoTreeVO();
            sql = this.getTreeSQL() + sql;
            treeData = (List) helper.queryByListVo(sql, orgInfoTreeVO);
        } else if (paraFlag.equals("3")) {
            DeptInfoTreeVO deptInfoTreeVO = new DeptInfoTreeVO();
            sql = deptinfoTreeSql + sql;

            //            System.out.println(sql);
            //            System.out.println(deptid);
            sql = sql.replaceAll("&deptid&", deptid);

            //            System.out.println(sql);
            treeData = (List) helper.queryByListVo(sql, deptInfoTreeVO);
        }

        return treeData;
    }

    /**
     *
     * @param 发送短消息通知iisVO
     * @return
     * @throws DefaultException
     */
    private int sendMessage(IsInfoServeVO iisVO) throws DefaultException {
        int retInt = 0;

        if (iisVO.getSend_flag().equals("1") &&
                iisVO.getHint_flag().equals("1")) {
            MessageDao messageDao = MessageFactory.getInstance()
                                                  .createMessageDao(super.dbData);
            String userCode = iisVO.getUser_code();
            String sendTime = DateTimeUtils.getCurrentDateTime();
            String receiver = "";
            String webUrl = "";
            String memo = "";

            if (iisVO.getInfo_flag().equals("2")) {
                receiver = new OrganizationUtil().getAllUserIDs();
                webUrl = "/infoservice/orginfoframe.jsp";
            } else {
                receiver = new OrganizationUtil().getUserIDsForDepartmentOfUserByUser(Integer.parseInt(
                            super.userID));
                webUrl = "/infoservice/deptinfoframe.jsp";
            }

            try {
                retInt = messageDao.sendMessage(userCode, sendTime,
                        iisVO.getTitle(), receiver, webUrl, memo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return retInt;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    /**
     * @param 删除文件操作
     */
    public void deleteFile(String path) {
        if (!"null".equals(path) && !"".equals(path)) {
            File fileName = new File(this.realPath + path);

            if (fileName.exists()) {
                fileName.delete();
            }
        }
    }

    public int delBySelect(String[] aryPk, VOInterface vo)
        throws DefaultException {
        int intRet = 0;
        SQLHelper helper = new DefaultSQLHelper(super.dbData);

        try {
            helper.begin();

            String name = "";
            String value = "";

            for (int idx = 0; idx < aryPk.length; idx++) {
                IsInfoServeVO iisvo = (IsInfoServeVO) getInstanceVO(vo);
                StringTokenizer stk = new StringTokenizer(aryPk[idx], "::");
                StringTokenizer stkPk = new StringTokenizer(iisvo.getPkFields(),
                        ",");

                while (stk.hasMoreTokens()) {
                    name = stkPk.nextToken();
                    value = stk.nextToken();
                    iisvo.setValue(name, value);
                }

                IsInfoServeVO view = new IsInfoServeVO();
                List list = helper.queryByListVo(DELBYSELECT_LIST_SQL +
                        "serial_num='" + iisvo.getSerial_num() + "'", view);

                if (list.size() > 0) {
                    view = (IsInfoServeVO) list.get(0);
                    intRet += helper.delete(view);
                }

                helper.commit();
                this.deleteFile(view.getFile_path());
            }

            helper.commit();
        } catch (DefaultException e) {
            intRet = -1;
            helper.rollback();
            throw new DefaultException("删除出错");
        } finally {
            helper.end();
        }

        return intRet;
    }

    /**
     * @param 用户按部门分类
     */
    public List queryByUserId(String userId, VOInterface vo)
        throws DefaultException {
        List queryByUserId;
        IsDepartmentVO iisVO = new IsDepartmentVO();

        SQLHelper helper = helper = new DefaultSQLHelper(dbData);

        String sql = QUERYBYUSERID_SQL +
            "dep.id=dep_us.department_id and dep_us.id=us.id and us.id=" +
            userId;
        queryByUserId = (List) helper.queryByListVo(sql, iisVO);

        return queryByUserId;
    }
}
