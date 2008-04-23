package com.gever.goa.infoservice.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsStandardModelDao;
import com.gever.goa.infoservice.vo.IsStandardModelTreeVO;
import com.gever.goa.infoservice.vo.IsStandardModelVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.DateTimeUtils;
import com.gever.util.IdMng;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 标准模板DAO实现类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsStandardModelDaoImpl extends BaseDAO
    implements IsStandardModelDao {
    //新增动作中更新模板类型发布标志SQL 
    private static String INSERT_UPDATEFLAG_SQL = " UPDATE IS_STANDARD_MODEL SET ISSUE_FLAG=0 WHERE ";

    //更新动作中更新模板类型发布标志SQL
    private static String UPDATE_UPDATEFLAG_SQL = " UPDATE IS_STANDARD_MODEL SET ISSUE_FLAG=0 WHERE ";

    //delBySelect_LIST_SQL
    private static String DELBYSELECT_LIST_SQL = " SELECT model_code, file_path from is_standard_model where ";

    //取得查询参数
    private static StringBuffer querySQL = new StringBuffer();

    //动态树的查询
    private static StringBuffer treeSQL = new StringBuffer();

    static {
        querySQL.append(" SELECT ");
        querySQL.append(" IS_STANDARD_MODEL.MODEL_CODE, ");
        querySQL.append(" IS_STANDARD_MODEL.MODEL_TYPE, ");
        querySQL.append(" IS_STANDARD_MODEL.CREATE_DATE, ");
        querySQL.append(" IS_STANDARD_MODEL.ISSUE_FLAG, ");
        querySQL.append(" IS_STANDARD_MODEL.MODEL_NAME, ");
        querySQL.append(" IS_STANDARD_MODEL.USER_CODE, ");
        querySQL.append(" IS_STANDARD_MODEL.FILE_PATH, ");
        querySQL.append(" IS_STANDARD_MODEL.FILE_NAME, ");
        querySQL.append(" IS_STANDARD_MODEL.WORD_CONTENT, ");
        querySQL.append(" IS_STANDARD_MODEL.CONTENT, ");
        querySQL.append(" IS_STANDARD_MODEL.EDITOR_TYPE, ");
        querySQL.append(" IS_STANDARD_MODEL.INFO_TYPE, ");
        querySQL.append(" T_USER.NAME AS USER_NAME ");
        querySQL.append(" FROM ");
        querySQL.append(" IS_STANDARD_MODEL ");
        querySQL.append(
            " LEFT JOIN IS_DOTYPE ON (IS_STANDARD_MODEL.INFO_TYPE=IS_DOTYPE.INFO_TYPE) ");
        querySQL.append(
            " LEFT JOIN T_USER ON IS_STANDARD_MODEL.USER_CODE=T_USER.ID ");
        querySQL.append(" WHERE ");
        querySQL.append(" 1=1 ");
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

    public IsStandardModelDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return querySQL.toString();
    }

    /**
     * 连接oracle数据库SQL
     */
    public void setOracleSQL() {
        querySQL.setLength(0);
        querySQL.append(" SELECT ");
        querySQL.append(" IS_STANDARD_MODEL.MODEL_CODE, ");
        querySQL.append(" IS_STANDARD_MODEL.MODEL_TYPE, ");
        querySQL.append(" IS_STANDARD_MODEL.CREATE_DATE, ");
        querySQL.append(" IS_STANDARD_MODEL.ISSUE_FLAG, ");
        querySQL.append(" IS_STANDARD_MODEL.MODEL_NAME, ");
        querySQL.append(" IS_STANDARD_MODEL.USER_CODE, ");
        querySQL.append(" IS_STANDARD_MODEL.FILE_PATH, ");
        querySQL.append(" IS_STANDARD_MODEL.FILE_NAME, ");
        querySQL.append(" IS_STANDARD_MODEL.WORD_CONTENT, ");
        querySQL.append(" IS_STANDARD_MODEL.CONTENT, ");
        querySQL.append(" IS_STANDARD_MODEL.EDITOR_TYPE, ");
        querySQL.append(" IS_STANDARD_MODEL.INFO_TYPE, ");
        querySQL.append(" T_USER.NAME AS USER_NAME ");
        querySQL.append(" FROM ");
        querySQL.append(" is_dotype, is_standard_model, t_user ");
        querySQL.append(
            " where is_standard_model.info_type = is_dotype.info_type(+) ");
        querySQL.append(" and is_standard_model.user_code = t_user.id(+) ");
    }

    /**
     * 重载
     */
    public int insert(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        IsStandardModelVO ismVO = (IsStandardModelVO) vo;
        String tmpinfotype = ismVO.getInfo_type();

        //更新模板类型发布标志
        if ("1".equals(ismVO.getIssue_flag())) {
            if ("".equals(tmpinfotype) || (tmpinfotype == null)) {
                String sql = INSERT_UPDATEFLAG_SQL + "MODEL_TYPE=" +
                    ismVO.getModel_type();
                helper.execUpdate(sql);
            } else {
                String sql = INSERT_UPDATEFLAG_SQL + "info_type='" +
                    tmpinfotype + "'";

                helper.execUpdate(sql);

                //                System.out.println("====================================");
                //                System.out.println(sql);
                //                System.out.println("====================================");
            }
        }

        //新增模板
        ismVO.setModel_code(IdMng.getModuleID(super.userID));
        ismVO.setUser_code(super.userID);
        ismVO.setCreate_date(DateTimeUtils.getCurrentDate());
        helper.setAutoID(false);

        int iRet = helper.insert(ismVO);

        return iRet;
    }

    public int update(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        IsStandardModelVO ismVO = (IsStandardModelVO) vo;
        String tmpinfotype = ismVO.getInfo_type();

        //更新模板类型发布标志
        if ("1".equals(ismVO.getIssue_flag())) {
            if ("".equals(tmpinfotype) || (tmpinfotype == null)) {
                String sql = INSERT_UPDATEFLAG_SQL + "MODEL_TYPE=" +
                    ismVO.getModel_type();
                helper.execUpdate(sql);
            } else {
                String sql = INSERT_UPDATEFLAG_SQL + "info_type='" +
                    tmpinfotype + "'";
                helper.execUpdate(sql);
            }
        }

        //修改模板
        ismVO.setUser_name(super.userID);
        ismVO.setCreate_date(DateTimeUtils.getCurrentDate());

        if (checkUpdate(vo) != 1) {
            throw new DefaultException("goa_sys_002");
        }

        return helper.update(vo);
    }

    //带String infotype参数的getTemplate方法
    public VOInterface getTemplate(int templateType, String tmpInfotype)
        throws DefaultException {
        IsStandardModelVO ismVO = new IsStandardModelVO();
        SQLHelper helper = new DefaultSQLHelper(dbData);

        //tmpInfotype=ismVO.getInfo_type();
        String sql = querySQL + " AND is_standard_model.issue_flag = 1" +
            " AND is_standard_model.model_type =" + templateType +
            " AND is_standard_model.info_type = '" + tmpInfotype + "'";
        System.out.println("*****************************************" +
            querySQL);

        List list = (List) helper.query(sql);

        if (list.iterator().hasNext()) {
            Map hm = (Map) (list.iterator().next());
            ismVO.setModel_code(String.valueOf(hm.get("model_code")));
            ismVO.setModel_type(String.valueOf(hm.get("model_type")));
            ismVO.setCreate_date(String.valueOf(hm.get("create_date")));
            ismVO.setIssue_flag(String.valueOf(hm.get("issue_flag")));
            ismVO.setModel_name(String.valueOf(hm.get("model_name")));
            ismVO.setUser_code(String.valueOf(hm.get("user_code")));
            ismVO.setFile_name(String.valueOf(hm.get("file_name")));
            ismVO.setFile_path(String.valueOf(hm.get("file_path")));
            ismVO.setEditor_type(String.valueOf(hm.get("editor_type")));
            ismVO.setUser_name(String.valueOf(hm.get("user_name")));
        }

        return ismVO;
    }

    public VOInterface getTemplate(int templateType) throws DefaultException {
        IsStandardModelVO ismVO = new IsStandardModelVO();
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String sql = querySQL +
            " AND IS_STANDARD_MODEL.ISSUE_FLAG=1 AND IS_STANDARD_MODEL.MODEL_TYPE=" +
            templateType;
        log.showLog("-----------" + querySQL);

        List list = (List) helper.query(sql);

        if (list.iterator().hasNext()) {
            Map hm = (Map) (list.iterator().next());
            ismVO.setModel_code(String.valueOf(hm.get("model_code")));
            ismVO.setModel_type(String.valueOf(hm.get("model_type")));
            ismVO.setCreate_date(String.valueOf(hm.get("create_date")));
            ismVO.setIssue_flag(String.valueOf(hm.get("issue_flag")));
            ismVO.setModel_name(String.valueOf(hm.get("model_name")));
            ismVO.setUser_code(String.valueOf(hm.get("user_code")));
            ismVO.setFile_name(String.valueOf(hm.get("file_name")));
            ismVO.setFile_path(String.valueOf(hm.get("file_path")));
            ismVO.setEditor_type(String.valueOf(hm.get("editor_type")));
            ismVO.setUser_name(String.valueOf(hm.get("user_name")));
        }

        return ismVO;
    }

    public String getTreeSQL() {
        return treeSQL.toString();
    }

    //得到动态数的数据
    public List getTreeData(String paraFlag, String nodeID)
        throws DefaultException {
        List treeData = new ArrayList();
        IsStandardModelTreeVO vo = new IsStandardModelTreeVO();

        SQLHelper helper = helper = new DefaultSQLHelper(dbData);

        if ("2".equals(nodeID) || StringUtils.isNull(nodeID)) {
            nodeID = "";
        }

        String sql = this.getTreeSQL();

        if (!StringUtils.isNull(nodeID)) {
            sql += (" and parent_type='" + nodeID + "'");
        } else {
            sql += " and ( parent_type='' or parent_type is null ) ";
        }

        sql += (" and moduleflag ='" + paraFlag + "'");

        if (paraFlag.equals("2")) {
            treeData = (ArrayList) helper.queryByListVo(sql, vo);
        } else if (paraFlag.equals("3")) {
            treeData = (ArrayList) helper.queryByListVo(sql, vo);
        } else if (paraFlag.equals("4")) {
            treeData = (ArrayList) helper.queryByListVo(sql, vo);
        } else if (paraFlag.equals("5")) {
            treeData = (ArrayList) helper.queryByListVo(sql, vo);
        } else {
            treeData = (ArrayList) helper.queryByListVo(sql, vo);
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
                IsStandardModelVO ismvo = (IsStandardModelVO) getInstanceVO(vo);
                StringTokenizer stk = new StringTokenizer(aryPk[idx], "::");
                StringTokenizer stkPk = new StringTokenizer(ismvo.getPkFields(),
                        ",");

                while (stk.hasMoreTokens()) {
                    name = stkPk.nextToken();
                    value = stk.nextToken();
                    ismvo.setValue(name, value);
                }

                IsStandardModelVO view = new IsStandardModelVO();
                List list = helper.queryByListVo(DELBYSELECT_LIST_SQL +
                        "model_code='" + ismvo.getModel_code() + "'", view);

                if (list.size() > 0) {
                    view = (IsStandardModelVO) list.get(0);
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
}
