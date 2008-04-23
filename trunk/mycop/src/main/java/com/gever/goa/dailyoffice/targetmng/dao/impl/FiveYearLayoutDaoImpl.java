package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.targetmng.dao.FiveYearLayoutDao;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.vo.VOInterface;

/**
 * Title: 五年规划Dao接口实现类 Description: 五年规划Dao接口实现类 Copyright: Copyright (c) 2004
 * Company: 天行软件
 *
 * @author Hu.Walker
 * @version 1.0
 */

public class FiveYearLayoutDaoImpl extends BaseDAO implements FiveYearLayoutDao {

    private static String QUERY_SQL =
            "SELECT serial_num,user_code,create_date,"
            + " approve,approve_date,content,approve_flag FROM DO_FIVEYEAR_LAYOUT "
            + "a left join t_user b on a.user_code=b.id where 1=1 ";


    public void setOracleSQL(){
        QUERY_SQL =
            "SELECT serial_num,user_code,create_date,"
            + " approve,approve_date,content,approve_flag FROM DO_FIVEYEAR_LAYOUT "
            + "a , t_user b  where a.user_code=b.id(+) ";
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
        }
        catch (DefaultException e) {
            helper.rollback();
            throw new DefaultException("更新出错");
        }
        finally {
            helper.end();
        }
        return row;

    }

    //QUERY_SQL--定义翻页时用的查询语句
    public FiveYearLayoutDaoImpl(String dbData) {
        super(dbData);
    }

    public String getPageSql() {
        return QUERY_SQL;
    }
}
