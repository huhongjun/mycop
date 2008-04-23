/*
 * �������� 2004-4-27
 *
 */
package com.gever.jdbc.database.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Oracle���ݿⷽ��
 * @author Hu.Walker
 */
public class OracleDialect extends Dialect {
    /* ���� Javadoc��
     * @see com.gever.database.Dialect#getLimitString(java.lang.String)
     */
    public String getLimitString(String sql) {
        StringBuffer pagingSelect = new StringBuffer(100);
        pagingSelect.append(
            "select * from ( select row_.*, rownum rownum_ from ( ");
        pagingSelect.append(sql);
        pagingSelect.append(" ) row_ where rownum <= ?) where rownum_ > ?");

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

        pstmt.setInt(start, endValue);
        pstmt.setInt(end, startValue);

        return pstmt;
    }
}
