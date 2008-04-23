/*
 * 创建日期 2004-4-27
 *
 */
package com.gever.jdbc.database.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 数据库方言,封装数据库特有语法
 * @author Hu.Walker
 */
public abstract class Dialect {
    /**
     * 大多数据库都有分页的特殊语法,这个方法的目的就是将一般的sql查询语句转换为分页语句
     * @param sql 源SQL
     * @return 分页SQL,至少两个placeHolder(?),开始记录和欲查询的记录数目
     */
    public String getLimitString(String sql) {
        return sql;
    }

    public PreparedStatement setStatementPageValue(PreparedStatement pstmt,
        int start, int startValue, int end, int endValue)
        throws SQLException {
        pstmt.setInt(start, startValue);
        pstmt.setInt(end, endValue);

        return pstmt;
    }
    
}
