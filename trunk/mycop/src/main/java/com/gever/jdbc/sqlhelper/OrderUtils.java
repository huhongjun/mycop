package com.gever.jdbc.sqlhelper;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class OrderUtils {
    public OrderUtils() {
    }

    public static String procOrderBy(String sql, String orderFld, String type) {
        StringBuffer sb = new StringBuffer(100);
        //�ݴ���
        if (sql.length() < 10 || orderFld == null || "".equals(orderFld))
            return sql;

        sb.append(procAllSql(sql.toLowerCase(), orderFld.toLowerCase(), type));
        return sb.toString();
    }

    private static final String ORDER = "order ";
    private static final String BY = "by ";
    private static final String ORDER_BY = " order by ";
    private static final String FROM = " from ";

    /**
     * �ҵ���ƥ������ŵ�:��Ϊ���ſ��������Ƶ�Ƕ��
     * @param strExpression ���ʽ
     * @return �ұ����ŵĹ��λ��
     */
    private static int findRightPos(String strExpression) {

        int rightCount = 1;
        int leftCount = 0;
        int pos = -1;

        for (int idx = strExpression.length() - 1; idx >= 0; idx--) {
            if (strExpression.charAt(idx) == ')') {
                ++rightCount;
            } else if (strExpression.charAt(idx) == '(') {
                //System.out.println("--(--"+leftCount );
                ++leftCount;
            }
            if (leftCount == rightCount) {
                pos = idx;
                break;
            }
        }
        return pos;
    }

    private static String getEndOrderBySql(String sql, String orderFld,
                                           String type) {
        StringBuffer sb = new StringBuffer();
        sb.append(sql).append(ORDER_BY);
        sb.append(orderFld).append(" ").append(type);
        return sb.toString();
    }

    private static String procAllSql(String sql, String orderFld, String type) {

        StringBuffer sb = new StringBuffer();
        String sub = sql;
        int fromPos = sub.lastIndexOf(FROM);
        int orderPos = sub.lastIndexOf(ORDER);
        int bracketPos = sub.lastIndexOf(")");
        // System.out.println("----------1----0---" + sql);
        //û��order by �Ĵ���
        if (orderPos <= fromPos) {
            return getEndOrderBySql(sql, orderFld, type);
        }

        //û��order by �Ĵ���
        if (orderPos <= bracketPos) {

            int startPos = findRightPos(sql.substring(0, bracketPos - 1));
            sub = sql.substring(startPos + 1, bracketPos);
            // System.out.println("sub--->" + sub);
            return getEndOrderBySql(sql, orderFld, type);
        } else if (bracketPos > 0) {
            int startPos = findRightPos(sql.substring(0, bracketPos));
            sub = sql.substring(startPos, bracketPos);
            //System.out.println("sub--->" + sub);
            if (orderPos < startPos) {
            }
        }

        //��order by �Ĵ���
        String notOrderSql = sql.substring(0, orderPos);
        return getEndOrderBySql(notOrderSql, orderFld, type);

    }

}