/*
 * �������� 2004-4-27
 *
 */
package com.gever.jdbc.database.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * MySQL���ݿⷽ��
 * @author Hu.Walker
 */
public class MySQLDialect extends Dialect {
    /* ���� Javadoc��
     * @see com.gever.database.dialect.Dialect#getLimitString(java.lang.String)
     */
    public String getLimitString(String sql) {
        StringBuffer pagingSelect = new StringBuffer(100);
        pagingSelect.append(sql);
        pagingSelect.append(" limit ?, ?");

        return pagingSelect.toString();
    }

    /* ���� Javadoc��
     * @see com.gever.database.dialect.Dialect#setStatementPageValue(java.sql.PreparedStatement, int, int, int, int)
     */
    public PreparedStatement setStatementPageValue(PreparedStatement pstmt,
        int start, int startValue, int end, int endValue)
        throws SQLException {
        if ((start < 0) || (end < 0)) {
            throw new SQLException("����start �� end ����Ϊ����.");
        }

        if (startValue < 0) {
            startValue = 0;
        }
        if (endValue < 0) {
            endValue = 15;
        }

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        if (startValue > endValue) {
            int temp = startValue;
            startValue = endValue;
            endValue = temp;
        }

        pstmt.setInt(start, startValue);
        pstmt.setInt(end, endValue - startValue);

        return pstmt;
    }
}
