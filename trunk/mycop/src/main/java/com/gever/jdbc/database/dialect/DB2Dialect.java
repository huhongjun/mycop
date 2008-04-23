/*
 * �������� 2004-5-18
 */
package com.gever.jdbc.database.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * @author Hu.Walker
 */
public class DB2Dialect extends Dialect {
    private static String DB2_SUM = "sum";
    private static String DB2_COUNT = "count";
    private static String DB2_AVG = "avg";
    private static String DB2_MAX = "max";
    private static String DB2_MIN = "min";

    private static Set DB2FunctionSet() {
        Set DB2Function = new HashSet();
        DB2Function.add(DB2_SUM);
        DB2Function.add(DB2_COUNT);
        DB2Function.add(DB2_AVG);
        DB2Function.add(DB2_MAX);
        DB2Function.add(DB2_MIN);

        return DB2Function;
    }

    /* ���� Javadoc��
     * @see com.gever.database.dialect.Dialect#getLimitString(java.lang.String)
     */
    public String getLimitString(String sql) {
        String lowerQueryString = sql.toLowerCase();
        String splitQueryString = null;

        //�ҳ�QueryString���ֶο�ʼλ��
        String afterSelect = sql.substring("select ".length());

        //�ҳ�ORDER BY��λ��
        int indexOrderBy = lowerQueryString.indexOf(" order by ");

        //�Ƿ����Ӳ�ѯ
        boolean hassubselect = lowerQueryString.matches(
                "select .* from \\(select .*");

        //���û���Ӳ�ѯ
        if (!hassubselect) {
            //��ѯ�������DB2�ľۼ�����,������ҳ�﷨����.ֱ�ӷ��ز�ѯQueryString
            if (hasDB2Function(lowerQueryString)) {
                splitQueryString = sql;
            }
            //���QueryString�а���ORDER BY,���ORDER BY������ȡ�����ŵ���ҳSQLǰ��,���к�ʹ�����ORDER BY��������
            else if (indexOrderBy != -1) {
                afterSelect = sql.substring("select ".length(), indexOrderBy).trim();
                String orderby = sql.substring(indexOrderBy + 1).trim();
                splitQueryString = new StringBuffer().append(
                        "SELECT * FROM ( SELECT ROW_NUMBER() OVER (")
                                                     .append(orderby)
                                                     .append(") AS linkno ,")
                                                     .append(afterSelect)
                                                     .append(") AS TMPTAB WHERE linkno BETWEEN ?+1 AND ?")
                                                     .toString();
            } else {
                //���QueryString��û��ORDER BY����,���кŰ��յڶ�������
                splitQueryString = new StringBuffer().append(
                        "SELECT * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY 2 ASC) AS linkno,")
                                                     .append(afterSelect)
                                                     .append(") AS TMPTAB WHERE linkno BETWEEN ?+1 AND ?")
                                                     .toString();
            }
        } else {
            //�����Ӳ�ѯ��QueryString�ķ�ҳSQL�﷨����
            splitQueryString = new StringBuffer().append(
                    "SELECT * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY 2 ASC) AS linkno,")
                                                 .append(afterSelect)
                                                 .append(") AS TMPTAB WHERE linkno BETWEEN ?+1 AND ?")
                                                 .toString();
        }

        return splitQueryString;
    }

    private static boolean hasDB2Function(String fields) {
        //���ַ���fieldsȥƥ��DB2FunctionSet��DB2�ۼ���������
        for (Iterator iter = DB2FunctionSet().iterator(); iter.hasNext();) {
            String item = (String) iter.next();

            if (fields.indexOf("select " + item + "(") != -1) {
                return true;
            }
        }

        return false;
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
        pstmt.setInt(end, endValue);

        return pstmt;
    }
}
