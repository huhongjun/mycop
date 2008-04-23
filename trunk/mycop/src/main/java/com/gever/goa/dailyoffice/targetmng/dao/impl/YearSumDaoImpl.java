package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.targetmng.dao.YearSumDao;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * Title: 年度总结Dao接口实现类 Description: 年度总结Dao接口实现类 Copyright: Copyright (c) 2004
 * Company: 天行软件
 *
 * @author Hu.Walker
 * @version 1.0
 */

public class YearSumDaoImpl
    extends BaseDAO
    implements YearSumDao {
    private static String QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
         + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
         + "audit_flag,audit_opinion,check_flag,"
         + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE "
         + "a left join t_user b on a.user_code=b.id where 1=1 ";

    public void setOracleSQL() {
        QUERY_SQL = "SELECT serial_num,current_year,current_month,current_work,"
            + "target_type,dept_code,user_code,create_date,auditor,audit_date,"
            + "audit_flag,audit_opinion,check_flag,"
            + "checker,check_date,check_opinion,target_content FROM DO_TARGET_MANAGE "
            + "a, t_user b  where a.user_code=b.id(+) ";
    }

    /**
     *重载BaseDAO中更新的方法
     * @param vo VOInterface
     * @throws DefaultException
     * @return int
     *  add by lihaidong
     */
    public int update(VOInterface vo) throws DefaultException {
        if (!super.isOracle()) {
            return super.update(vo);
        }
        int row = 0;
        SQLHelper helper = new DefaultSQLHelper(this.dbData);
        try {
            helper.begin();
            helper.setAutoID(false);
            helper.delete(vo);
            row = helper.insert(vo);
            helper.commit();
        } catch (DefaultException e) {
            helper.rollback();
            throw new DefaultException("更新出错");
        }
        finally {
            helper.end();
        }
        return row;

    }

    //QUERY_SQL--定义翻页时用的查询语句
    public YearSumDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return QUERY_SQL;
    }
}
