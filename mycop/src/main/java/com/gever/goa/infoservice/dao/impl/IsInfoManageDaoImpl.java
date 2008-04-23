package com.gever.goa.infoservice.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsInfoManageDao;
import com.gever.goa.infoservice.vo.DocumentInformationTreeVO;
import com.gever.goa.infoservice.vo.IsInfoManageVO;
import com.gever.goa.infoservice.vo.LawTreeVO;
import com.gever.goa.infoservice.vo.PublicInfoTreeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 公共信息DAO实现类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsInfoManageDaoImpl extends BaseDAO implements IsInfoManageDao {
    //取得查询条件
    private static StringBuffer querySQL = new StringBuffer();

    //树的查询
    private static StringBuffer treeSQL = new StringBuffer();

    //更新语句update
    private static String UPDATE_SQL = " UPDATE IS_INFO_MANAGE SET title=?,info_type=?,user_code=?,create_date=?,file_path=?,file_name=?,url=?,content=?,info_levle=?,info_flag=? where ";

    //根据MODULEFLAG查询
    private static String QUERYBYMODULEFLAG_SQL = " select info_type from is_info_manage where ";

    //delbyselect_list_sql
    private static String DELBYSELECT_LIST_SQL = " SELECT title,info_type, file_path from is_info_manage where ";

    //  数据重复查询
    private static String REPEAT_SQL = " SELECT count(*) as cnt FROM is_info_manage WHERE ";

    static {
        querySQL.append(" SELECT ");
        querySQL.append(" IS_INFO_MANAGE.TITLE, ");
        querySQL.append(" IS_INFO_MANAGE.INFO_TYPE, ");
        querySQL.append(" IS_INFO_MANAGE.USER_CODE, ");
        querySQL.append(" IS_INFO_MANAGE.CREATE_DATE, ");
        querySQL.append(" IS_INFO_MANAGE.FILE_PATH, ");
        querySQL.append(" IS_INFO_MANAGE.FILE_NAME, ");
        querySQL.append(" IS_INFO_MANAGE.URL, ");
        querySQL.append(" IS_INFO_MANAGE.CONTENT, ");
        querySQL.append(" IS_INFO_MANAGE.INFO_LEVLE, ");
        querySQL.append(" IS_INFO_MANAGE.INFO_FLAG, ");
        querySQL.append(" IS_DOTYPE.PARENT_TYPE, ");
        querySQL.append(" T_USER.NAME AS USER_NAME ");
        querySQL.append(" FROM ");
        querySQL.append(" IS_INFO_MANAGE ");
        querySQL.append(
            " LEFT JOIN IS_DOTYPE ON(IS_INFO_MANAGE.INFO_TYPE=IS_DOTYPE.INFO_TYPE) ");
        querySQL.append(
            " LEFT JOIN T_USER ON(IS_INFO_MANAGE.USER_CODE=T_USER.ID) ");
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

    public IsInfoManageDaoImpl() {
    }

    public IsInfoManageDaoImpl(String dbData) {
        super(dbData);
    }

    /**
     * 连接oracle数据库SQL
     */
    public void setOracleSQL() {
        querySQL.setLength(0);
        querySQL.append(" SELECT ");
        querySQL.append(" IS_INFO_MANAGE.TITLE, ");
        querySQL.append(" IS_INFO_MANAGE.INFO_TYPE, ");
        querySQL.append(" IS_INFO_MANAGE.USER_CODE, ");
        querySQL.append(" IS_INFO_MANAGE.CREATE_DATE, ");
        querySQL.append(" IS_INFO_MANAGE.FILE_PATH, ");
        querySQL.append(" IS_INFO_MANAGE.FILE_NAME, ");
        querySQL.append(" IS_INFO_MANAGE.URL, ");
        querySQL.append(" IS_INFO_MANAGE.CONTENT, ");
        querySQL.append(" IS_INFO_MANAGE.INFO_LEVLE, ");
        querySQL.append(" IS_INFO_MANAGE.INFO_FLAG, ");
        querySQL.append(" IS_DOTYPE.PARENT_TYPE, ");
        querySQL.append(" T_USER.NAME AS USER_NAME ");
        querySQL.append(" FROM ");
        querySQL.append(" is_info_manage, is_dotype, t_user ");
        querySQL.append(
            " WHERE is_info_manage.info_type = is_dotype.info_type(+) ");
        querySQL.append(" AND is_info_manage.user_code = t_user.ID(+) ");
    }

    public String getPageSql() {
        return querySQL.toString();
    }

    /**
     * 重载
     */
    public int insert(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        IsInfoManageVO iimVO = (IsInfoManageVO) vo;
        iimVO.setUser_code(super.userID);
        iimVO.setCreate_date(DateTimeUtils.getCurrentDate());
        helper.setAutoID(false);

        //        int iRet = 0;
        //
        //        try {
        //            iRet = helper.insert(vo);
        //        } catch (Exception e) {
        //            // TODO: handle exception
        //            throw new DefaultException("主题名称重复，请重新输入！", e);
        //        }
        //
        //        return iRet;
        if (isRepeat((IsInfoManageVO) vo)) {
            throw new DefaultException("PK repeat!");
        } else {
            int iRet = helper.insert(vo);

            return iRet;
        }
    }

    public int update(VOInterface vo) throws DefaultException {
        if (!super.isOracle()) {
            SQLHelper helper = new DefaultSQLHelper(dbData);
            IsInfoManageVO iimVO = (IsInfoManageVO) vo;
            iimVO.setUser_code(super.userID);
            iimVO.setCreate_date(DateTimeUtils.getCurrentDate());

            String strsql = UPDATE_SQL + "title='" + iimVO.getOld_title() +
                "' and info_type='" + iimVO.getOld_info_type() + "'";
            helper.execUpdate(strsql, vo,
                "title,info_type,user_code,create_date,file_path,file_name,url,content,info_levle,info_flag,");

            if (checkUpdate(vo) != 1) {
                throw new DefaultException("goa_sys_002");
            }

            return helper.update(vo);
        }

        SQLHelper helper = new DefaultSQLHelper(dbData);

        int iRet = -1;
        IsInfoManageVO iimVO = (IsInfoManageVO) vo;
        iimVO.setUser_code(super.userID);
        iimVO.setCreate_date(DateTimeUtils.getCurrentDate());

        String strsql = UPDATE_SQL + "title='" + iimVO.getOld_title() +
            "' and info_type='" + iimVO.getOld_info_type() + "'";
        helper.execUpdate(strsql, vo,
            "title,info_type,user_code,create_date,file_path,file_name,url,content,info_levle,info_flag,");

        if (checkUpdate(vo) != 1) {
            throw new DefaultException("goa_sys_002");
        }

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
    }

    //  定义一个查询主键重复的方法
    private boolean isRepeat(IsInfoManageVO vo) throws DefaultException {
        String sql = REPEAT_SQL + " title='" + vo.getTitle() + "'";
        SQLHelper helper = new DefaultSQLHelper(dbData);
        boolean bRet = false;
        Map map = (HashMap) helper.query(sql,
                com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);

        if (com.gever.util.NumberUtil.stringToLong((String) map.get("cnt")) > 0) {
            bRet = true;
        }

        return bRet;
    }

    public List queryByModuleFlag(String paraFlag, VOInterface vo)
        throws DefaultException {
        List queryByModuleFlag = new ArrayList();
        IsInfoManageVO isinfomanageVO = new IsInfoManageVO();

        SQLHelper helper = helper = new DefaultSQLHelper(dbData);
        String sql = QUERYBYMODULEFLAG_SQL + "moduleflag='" + paraFlag + "'";
        queryByModuleFlag = (ArrayList) helper.queryByListVo(sql, isinfomanageVO);

        return queryByModuleFlag;
    }

    public String getTreeSQL() {
        return treeSQL.toString();
    }

    public List getTreeData(String paraFlag, String nodeID)
        throws DefaultException {
        List treeData = new ArrayList();

        //公共信息，法律法规，文档资料
        PublicInfoTreeVO publicInfoTreeVO = new PublicInfoTreeVO();
        LawTreeVO lawTreeVO = new LawTreeVO();
        DocumentInformationTreeVO documentInformationTreeVO = new DocumentInformationTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData);

        if ("0".equals(nodeID) || StringUtils.isNull(nodeID)) {
            nodeID = "";
        }

        String sql = this.getTreeSQL();

        if (!StringUtils.isNull(nodeID)) {
            sql += (" and parent_type='" + nodeID + "'");
        } else {
            sql += " and ( parent_type='' or parent_type is null ) ";
        }

        sql += (" and moduleflag ='" + paraFlag + "'");
        
        log.console(this.getClass(),"getTreeData()->SQL:",sql);

        if (paraFlag.equals("0")) {
            treeData = (ArrayList) helper.queryByListVo(sql, publicInfoTreeVO);
        } else if (paraFlag.equals("1")) {
            treeData = (ArrayList) helper.queryByListVo(sql, lawTreeVO);
        } else {
            treeData = (ArrayList) helper.queryByListVo(sql,
                    documentInformationTreeVO);
        }

        return treeData;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    //GW ADD 删除文件操作
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
                IsInfoManageVO iimvo = (IsInfoManageVO) getInstanceVO(vo);
                StringTokenizer stk = new StringTokenizer(aryPk[idx], "::");
                StringTokenizer stkPk = new StringTokenizer(iimvo.getPkFields(),
                        ",");

                while (stk.hasMoreTokens()) {
                    name = stkPk.nextToken();
                    value = stk.nextToken();
                    iimvo.setValue(name, value);
                }

                IsInfoManageVO view = new IsInfoManageVO();
                List list = helper.queryByListVo(DELBYSELECT_LIST_SQL +
                        "title='" + iimvo.getTitle() + "'", view);

                if (list.size() > 0) {
                    view = (IsInfoManageVO) list.get(0);
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

    public static void main(String[] args) {
        IsInfoManageDaoImpl untitled11 = new IsInfoManageDaoImpl();
        System.out.println(untitled11.getTreeSQL());
    }
}
