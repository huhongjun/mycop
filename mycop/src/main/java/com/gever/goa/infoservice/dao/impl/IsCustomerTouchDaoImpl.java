package com.gever.goa.infoservice.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsCustomerTouchDao;
import com.gever.goa.infoservice.vo.IsCustomerTouchVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 客户联系人DAO实现类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsCustomerTouchDaoImpl extends BaseDAO
    implements IsCustomerTouchDao {
    //update更新语句
    private static String UPDATE_SQL = " UPDATE IS_CUSTOMER_TOUCH SET ";

    //  数据重复查询
    private static String REPEAT_SQL = " SELECT count(*) as cnt FROM IS_CUSTOMER_TOUCH WHERE ";

    //  得到查询语句
    private static StringBuffer querySQL = new StringBuffer();

    static {
        querySQL.append(" SELECT ");
        querySQL.append(" IS_CUSTOMER_TOUCH.CUSTOMER, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.CUSTOMER_CODE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.SEX_CODE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.JOB, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.LOVE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.WORK_PHONE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.HOME_PHONE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.MOBILE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.E_MAIL, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.ADDRESS, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.REMARK, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.BIRTHDAY ");
        querySQL.append(" FROM ");
        querySQL.append(" IS_CUSTOMER_TOUCH ");
        querySQL.append(
            " LEFT JOIN IS_CUSTOMER ON IS_CUSTOMER_TOUCH.CUSTOMER=IS_CUSTOMER.CUSTOMER_CODE ");
        querySQL.append(" WHERE ");
        querySQL.append(" 1=1 ");
    }

    public IsCustomerTouchDaoImpl(String dbData) {
        super(dbData);
    }

    /**
     * 连接oracle数据库SQL
     */
    public void setOracleSQL() {
        querySQL.setLength(0);
        querySQL.append(" SELECT ");
        querySQL.append(" IS_CUSTOMER_TOUCH.CUSTOMER, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.CUSTOMER_CODE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.SEX_CODE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.JOB, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.LOVE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.WORK_PHONE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.HOME_PHONE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.MOBILE, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.E_MAIL, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.ADDRESS, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.REMARK, ");
        querySQL.append(" IS_CUSTOMER_TOUCH.BIRTHDAY ");
        querySQL.append(" FROM ");
        querySQL.append(" is_customer_touch, is_customer ");
        querySQL.append(
            " WHERE is_customer_touch.customer = is_customer.customer_code(+) ");
    }

    public String getPageSql() {
        return querySQL.toString();
    }

    /**
     * 重载 insert方法
     */
    public int insert(VOInterface vo) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        IsCustomerTouchVO ictVO = (IsCustomerTouchVO) vo;
        String pkName = vo.getPkFields();
        pkName = toPkFld(pkName);
        vo.setValue(pkName, ictVO.getCustomer());
        helper.setAutoID(false);

        if (isRepeat((IsCustomerTouchVO) vo)) {
            throw new DefaultException("PK repeat!");
        } else {
            int iRet = helper.insert(vo);

            return iRet;
        }
    }

    /**
     * 重载 update
     */
    public int update(VOInterface VO) throws DefaultException {
        SQLHelper helper = new DefaultSQLHelper(dbData);
        IsCustomerTouchVO vo = (IsCustomerTouchVO) VO;

        String sql = UPDATE_SQL;
        if (!super.isOracle()) {

        if (!"".equals(vo.getCustomer())) {
            sql = sql + "CUSTOMER='" + vo.getCustomer() + "'";
        }

        if (!"".equals(vo.getCustomer_code())) {
            sql = sql + ",CUSTOMER_CODE='" + vo.getCustomer_code() + "'";
        }

        if (!"".equals(vo.getSex_code())) {
            sql = sql + ",SEX_CODE='" + vo.getSex_code() + "'";
        }

        if (!"".equals(vo.getJob())) {
            sql = sql + ",JOB='" + vo.getJob() + "'";
        }

        if (!"".equals(vo.getLove())) {
            sql = sql + ",LOVE='" + vo.getLove() + "'";
        }

        if (!"".equals(vo.getWork_phone())) {
            sql = sql + ",WORK_PHONE='" + vo.getWork_phone() + "'";
        }

        if (!"".equals(vo.getHome_phone())) {
            sql = sql + ",HOME_PHONE='" + vo.getHome_phone() + "'";
        }

        if (!"".equals(vo.getMobile())) {
            sql = sql + ",MOBILE='" + vo.getMobile() + "'";
        }

        if (!"".equals(vo.getE_mail())) {
            sql = sql + ",E_MAIL='" + vo.getE_mail() + "'";
        }

        if (!"".equals(vo.getAddress())) {
            sql = sql + ",ADDRESS='" + vo.getAddress() + "'";
        }

        if (!"".equals(vo.getRemark())) {
            sql = sql + ",REMARK='" + vo.getRemark() + "'";
        }

        if (!"".equals(vo.getBirthday())) {
            sql = sql + ",BIRTHDAY='" + vo.getBirthday() + "'";
        }

        sql = sql + " where " + "customer='" + vo.getCustomerbak() + "'";

        return helper.execUpdate(sql.toString());
        }
        if (!"".equals(vo.getCustomer())) {
            sql = sql + "CUSTOMER='" + vo.getCustomer() + "'";
        }

        if (!"".equals(vo.getCustomer_code())) {
            sql = sql + ",CUSTOMER_CODE='" + vo.getCustomer_code() + "'";
        }

        if (!"".equals(vo.getSex_code())) {
            sql = sql + ",SEX_CODE='" + vo.getSex_code() + "'";
        }

        if (!"".equals(vo.getJob())) {
            sql = sql + ",JOB='" + vo.getJob() + "'";
        }

        if (!"".equals(vo.getLove())) {
            sql = sql + ",LOVE='" + vo.getLove() + "'";
        }

        if (!"".equals(vo.getWork_phone())) {
            sql = sql + ",WORK_PHONE='" + vo.getWork_phone() + "'";
        }

        if (!"".equals(vo.getHome_phone())) {
            sql = sql + ",HOME_PHONE='" + vo.getHome_phone() + "'";
        }

        if (!"".equals(vo.getMobile())) {
            sql = sql + ",MOBILE='" + vo.getMobile() + "'";
        }

        if (!"".equals(vo.getE_mail())) {
            sql = sql + ",E_MAIL='" + vo.getE_mail() + "'";
        }

        if (!"".equals(vo.getAddress())) {
            sql = sql + ",ADDRESS='" + vo.getAddress() + "'";
        }

        if (!"".equals(vo.getRemark())) {
            sql = sql + ",REMARK='" + vo.getRemark() + "'";
        }

        if (!"".equals(vo.getBirthday())) {
            sql = sql + ",BIRTHDAY=to_date('" + vo.getBirthday() +"'"+",'yyyy-mm-dd'"+ ")";
        }

        sql = sql + " where " + "customer='" + vo.getCustomerbak() + "'";

        return helper.execUpdate(sql.toString());
    }

    //    定义一个查询主键重复的方法
    private boolean isRepeat(IsCustomerTouchVO vo) throws DefaultException {
        String sql = REPEAT_SQL + " customer='" + vo.getCustomer() + "'";
        SQLHelper helper = new DefaultSQLHelper(dbData);
        boolean bRet = false;
        Map map = (HashMap) helper.query(sql,
                com.gever.jdbc.sqlhelper.DataTypes.RS_SINGLE_MAP);

        if (com.gever.util.NumberUtil.stringToLong((String) map.get("cnt")) > 0) {
            bRet = true;
        }

        return bRet;
    }
}
