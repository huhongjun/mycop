/*
 * 创建日期 2004-5-18
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

    /* （非 Javadoc）
     * @see com.gever.database.dialect.Dialect#getLimitString(java.lang.String)
     */
    public String getLimitString(String sql) {
        String lowerQueryString = sql.toLowerCase();
        String splitQueryString = null;

        //找出QueryString中字段开始位置
        String afterSelect = sql.substring("select ".length());

        //找出ORDER BY的位置
        int indexOrderBy = lowerQueryString.indexOf(" order by ");

        //是否含有子查询
        boolean hassubselect = lowerQueryString.matches(
                "select .* from \\(select .*");

        //如果没有子查询
        if (!hassubselect) {
            //查询中如果有DB2的聚集函数,则不做分页语法处理.直接返回查询QueryString
            if (hasDB2Function(lowerQueryString)) {
                splitQueryString = sql;
            }
            //如果QueryString中包含ORDER BY,则把ORDER BY部分提取出来放到分页SQL前面,让行号使用这个ORDER BY进行排序
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
                //如果QueryString中没有ORDER BY部分,则行号按照第二列排序
                splitQueryString = new StringBuffer().append(
                        "SELECT * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY 2 ASC) AS linkno,")
                                                     .append(afterSelect)
                                                     .append(") AS TMPTAB WHERE linkno BETWEEN ?+1 AND ?")
                                                     .toString();
            }
        } else {
            //带有子查询的QueryString的分页SQL语法处理
            splitQueryString = new StringBuffer().append(
                    "SELECT * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY 2 ASC) AS linkno,")
                                                 .append(afterSelect)
                                                 .append(") AS TMPTAB WHERE linkno BETWEEN ?+1 AND ?")
                                                 .toString();
        }

        return splitQueryString;
    }

    private static boolean hasDB2Function(String fields) {
        //用字符串fields去匹配DB2FunctionSet中DB2聚集函数集合
        for (Iterator iter = DB2FunctionSet().iterator(); iter.hasNext();) {
            String item = (String) iter.next();

            if (fields.indexOf("select " + item + "(") != -1) {
                return true;
            }
        }

        return false;
    }

    /* （非 Javadoc）
     * @see com.gever.database.dialect.Dialect#setStatementPageValue(java.sql.PreparedStatement, int, int, int, int)
     */
    public PreparedStatement setStatementPageValue(PreparedStatement pstmt,
        int start, int startValue, int end, int endValue)
        throws SQLException {
        if ((start < 0) || (end < 0)) {
            throw new SQLException("参数start 或 end 不能为负数.");
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
