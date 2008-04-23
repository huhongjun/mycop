package com.gever.goa.infoservice.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsCustomerDao;
import com.gever.goa.infoservice.vo.IsCustomerSecTreeVO;
import com.gever.goa.infoservice.vo.IsCustomerTreeVO;
import com.gever.goa.infoservice.vo.IsCustomerVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 客户管理DAO实现类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company:</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsCustomerDaoImpl extends BaseDAO implements IsCustomerDao {
    //	得到查询语句
    private static StringBuffer querySQL = new StringBuffer();

    //状态树查询
    private static String statusTreeSQL = " SELECT FLAG as nodeid,STATUS as nodename,'1' as isfolder  from IS_CUSTOMER_STATUS ";

    //类型树
    private static String typeTreeSQL = " SELECT TYPE_CODE as nodeid,TYPE_NAME as nodename,'1' as isfolder  from IS_CUSTOMER_TYPE ";
    private static String DELBYSELECT_SQL1 = " delete from is_customer_touch where ";
    private static String DELBYSELECT_SQL2 = " delete from is_customer where ";

    //  数据重复查询
    private static String REPEAT_SQL = " SELECT count(*) as cnt FROM is_customer WHERE ";

    static {
        querySQL.append(" SELECT ");
        querySQL.append(" IS_CUSTOMER.CUSTOMER_CODE, ");
        querySQL.append(" IS_CUSTOMER.CUSTOMER_NAME, ");
        querySQL.append(" IS_CUSTOMER.PHONE, ");
        querySQL.append(" IS_CUSTOMER.FAX, ");
        querySQL.append(" IS_CUSTOMER.E_MAIL, ");
        querySQL.append(" IS_CUSTOMER.HOME_PAGE, ");
        querySQL.append(" IS_CUSTOMER.ADDRESS, ");
        querySQL.append(" IS_CUSTOMER.POST_CODE, ");
        querySQL.append(" IS_CUSTOMER.SECRET_LEVEL, ");
        querySQL.append(" IS_CUSTOMER.REMARK, ");
        querySQL.append(" IS_CUSTOMER.OPEN_BANK, ");
        querySQL.append(" IS_CUSTOMER.ACCOUNT, ");
        querySQL.append(" IS_CUSTOMER.FLAG, ");
        querySQL.append(" IS_CUSTOMER.TYPE_CODE,");
        querySQL.append(" IS_CUSTOMER.MEMORY");
        querySQL.append(" FROM ");
        querySQL.append(" IS_CUSTOMER ");
        querySQL.append(" WHERE 1=1 ");
    }

    public IsCustomerDaoImpl(String dbData) {
        super(dbData);
    }

    /**
     * 连接oracle数据库SQL
     */
    public void setOracleSQL() {
    }

    public String getPageSql() {
        return querySQL.toString();
    }

    /**
     * 重载
     */
    public int insert(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        IsCustomerVO icVO = (IsCustomerVO) vo;
        String pkName = vo.getPkFields();
        pkName = toPkFld(pkName);
        vo.setValue(pkName, icVO.getCustomer_code());
        helper.setAutoID(false);

        //        int iRet = helper.insert(vo);
        //
        //        return iRet;
        if (isRepeat((IsCustomerVO) vo)) {
            throw new DefaultException("PK repeat!");
        } else {
            int iRet = helper.insert(vo);

            return iRet;
        }
    }

    //  定义一个查询主键重复的方法
    private boolean isRepeat(IsCustomerVO vo) throws DefaultException {
        String sql = REPEAT_SQL + " customer_code='" + vo.getCustomer_code() +
            "'";
        SQLHelper helper = new DefaultSQLHelper(dbData);
        boolean bRet = false;
        Map map = (HashMap) helper.query(sql,
                com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);

        if (com.gever.util.NumberUtil.stringToLong((String) map.get("cnt")) > 0) {
            bRet = true;
        }

        return bRet;
    }

    //重载deleteBySelect
    public int delBySelect(String[] aryPk, VOInterface vo)
        throws DefaultException {
        int intRet = 0;
        SQLHelper helper = new DefaultSQLHelper(dbData);
        ;

        String pks = StringUtils.arrayToString(aryPk);
        pks = formatPK(pks);

        String sql1 = DELBYSELECT_SQL1 + "customer_code in(" + pks + ")";
        String sql2 = DELBYSELECT_SQL2 + "customer_code in(" + pks + ")";

        try {
            helper.begin();
            helper.execUpdate(sql1);
            helper.execUpdate(sql2);
            helper.commit();
        } catch (DefaultException e) {
            intRet = -1;
            helper.rollback();
            throw new DefaultException("sys_del_002");
        } finally {
            helper.end();
        }

        return intRet;
    }

    /**
     * 得到树的清单
     * @return 树列表
     * @throws DefaultException
     */
    public List getStatusTreeData() throws DefaultException {
        List statusTreeData = new ArrayList();
        IsCustomerTreeVO vo1 = new IsCustomerTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData);
        statusTreeData = (ArrayList) helper.queryByListVo(statusTreeSQL.toString(),
                vo1);

        return statusTreeData;
    }

    public List getTypeTreeData() throws DefaultException {
        List typeTreeData = new ArrayList();
        IsCustomerSecTreeVO vo2 = new IsCustomerSecTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData);
        typeTreeData = (ArrayList) helper.queryByListVo(typeTreeSQL.toString(),
                vo2);

        return typeTreeData;
    }

    public VOInterface queryByPK(VOInterface vo) throws DefaultException {
        return (VOInterface) super.queryByPk(vo);
    }

    //替换字符
    private String formatPK(String pks) {
        String retStr;
        retStr = "'" + pks.replaceAll(",", "','") + "'";

        return retStr;
    }
}
