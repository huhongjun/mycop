package com.gever.goa.dailyoffice.targetmng.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gever.exception.DefaultException;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.struts.action.BaseAction;
import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;

/**
 *
 * <p>Title: ������ </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class TargetConstant extends BaseDAO {

    //Ŀ��������ͳ���
    public final static int WEEK_TARGET_TYPE = 1; //ÿ��Ŀ��
    public final static int MONTH_TARGET_TYPE = 2; //�¶�Ŀ��
    public final static int MONTH_SUM_TYPE = 3; //�¶��ܽ�
    public final static int YEAR_TARGET_TYPE = 4; //���Ŀ��
    public final static int YEAR_SUM_TYPE = 5; //����ܽ�

    /**
     * 1������ʾ�����ܱ��棬2������ʾ�±��棬3������ʾ�걨��
     */
    //�ܱ���
    public final static int WEEK_QTY_REPORT = 101; //�����ܱ�
    public final static int WEEK_PRJ_REPORT = 102; //��Ŀ�ܱ�
    public final static int WEEK_HUM_RES_REPORT = 103; //�����ܱ�

    //�±���
    public final static int MONTH_QTY_REPORT = 201; //�����±�
    public final static int MONTH_PRJ_REPORT = 202; //��Ŀ�±�
    public final static int MONTH_HUM_RES_REPORT = 203; //�����±�
    public final static int MONTH_INFO_SAFE_REPORT = 204; //�Ű��±�

    //�걨��
    public final static int YEAR_REPORT=301;//��ȱ���
    public final static int YEAR_QTY_REPORT = 302; //�����걨
    public final static int YEAR_PRODUCT_REPORT=303;//��Ʒ�걨
    public final static int YEAR_HUM_RES_REPORT=304;//�����걨
    public final static int YEAR_TECH_REPORT=305;   //�����걨
    public final static int YEAR_SALE_REPORT=306;   //Ӫ���걨
    public final static int YEAR_INFO_SAFE_REPORT=307;//�Ű��걨
    private String targetTypeName = ""; //Ŀ����������
    private String typeFlagName="";

    public TargetConstant() {
    }

    /**
     * �õ�Ŀ�������
     * @return int
     */

    public int getTargetType(int targetType) {
        int tempType = 0;
        switch (targetType) {
            case 101:
                tempType = WEEK_QTY_REPORT;
                targetTypeName = "�����ܱ�";
                break;
            case 102:
                tempType = WEEK_PRJ_REPORT;
                targetTypeName = "��Ŀ�ܱ�";
                break;
            case 103:
                tempType = WEEK_HUM_RES_REPORT;
                targetTypeName = "�����ܱ�";
                break;
            case 201:
                tempType = MONTH_QTY_REPORT;
                targetTypeName = "�����±�";
                break;
            case 202:
                tempType = MONTH_PRJ_REPORT;
                targetTypeName = "��Ŀ�±�";
                break;
            case 203:
                tempType = MONTH_HUM_RES_REPORT;
                targetTypeName = "�����±�";
                break;
            case 204:
                tempType = MONTH_INFO_SAFE_REPORT;
                targetTypeName = "�Ű��±�";
                break;
            case 301:
                tempType = YEAR_REPORT;
                targetTypeName = "��ȱ���";
                break;
            case 302:
                tempType = YEAR_QTY_REPORT;
                targetTypeName = "�����걨";
                break;
            case 303:
                tempType = YEAR_PRODUCT_REPORT;
                targetTypeName = "��Ʒ�걨";
                break;
            case 304:
                tempType = YEAR_HUM_RES_REPORT;
                targetTypeName = "�����걨";
                break;
            case 305:
                tempType = YEAR_TECH_REPORT;
                targetTypeName = "�����걨";
                break;
            case 306:
                tempType = YEAR_SALE_REPORT;
                targetTypeName = "Ӫ���걨";
                break;
            case 307:
                tempType = YEAR_INFO_SAFE_REPORT;
                targetTypeName = "�Ű��걨";
                break;

        }
        return tempType;
    }

    /**
     * ����Ŀ������
     * @param targetType String
     * @return  �������ͣ�ֵΪ1��ʾΪ�ܣ�2��ʾΪ�£�3��ʾΪ��
     */

    public String getTargetType(String targetType) {
        String type ="";
        targetType = targetType.substring(0, 1);
        if (targetType.equals("1")) {
            typeFlagName="�ܱ���";
            type="1";
        }
        else if (targetType.equals("2")) {
            typeFlagName="�±���";
            type="2";
        }
        else if (targetType.equals("3")) {
            typeFlagName="�걨��";
            type="3";
        }
        return type;

    }
    /**
     * �жϱ����Ƿ����
     * @return boolean
     */
    public String judgeReportIsExsit(String year ,String month ,String week,String targetType) throws DefaultException,
        SQLException {
        String existFlag = "0";
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
           dbData);
       Connection conn = cp.getConnection();
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       StringBuffer sbSql = new StringBuffer();
       BaseAction bAction=new BaseAction(); //��DB2��ORACLE�жԿձ�ʾ�Ĳ�һ��������Ҫ����BaseAction�еķ���
       sbSql.append("select SERIAL_NUM from DO_TARGET_MANAGE where ");
       if(StringUtils.isNull(year)){
         sbSql.append("( CURRENT_YEAR='' or CURRENT_YEAR IS NULL)");
       }else{
         sbSql.append("CURRENT_YEAR="+ "'"+ year +"'");
       }
       if(StringUtils.isNull(month)){
         sbSql.append(" and ").append("(CURRENT_MONTH='' OR CURRENT_MONTH IS NULL)");
       }else{
         sbSql.append("and CURRENT_MONTH="+"'"+month+"'");
       }
       if(StringUtils.isNull(week)){
         sbSql.append(" and ").append("CURRENT_WORK='' OR CURRENT_WORK IS NULL)");
       }else{
         sbSql.append(" and CURRENT_WORK="+"'"+week+"'");
       }
       sbSql.append("and TARGET_TYPE='" + targetType + "' ");
    //   System.out.println("The query sql is ========"+sbSql.toString());
       try {
           pstmt = conn.prepareStatement(sbSql.toString());
           rs = pstmt.executeQuery();
           if (rs.next()) {
            //   System.out.println("The serialNmu is *****"+rs.getString(1));
               existFlag = "1";
           }
           rs.close();
           pstmt.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       finally {
           rs.close();
           pstmt.close();
           conn.close();
       }
       return existFlag;
    }

    /**
     * �õ�������������
     * @param targetType Strign
     * @return String
     */
    public String getTypeFlagName(String targetType){
        this.getTargetType(targetType);
        return this.typeFlagName;
    }

    /**
     * �õ�Ŀ�����͵�����
     * @param targetType int
     * @return String
     */
    public String getTargetTypeName(int targetType) {
        this.getTargetType(targetType);
        return this.targetTypeName;
    }

    /**
     * ����Ŀ���������õ�ǰ�꣬�£���
     * @param targetType int
     * @return String
     */
    public static String getCurDate(String targetName) {
        String curYear = DateTimeUtils.getCurrentDate().substring(0, 4);
        String curMonth = "  ";
        String curWeek = " ";
        String curDate = "";
        if (targetName.indexOf("��") != -1) {
            curMonth = DateTimeUtils.getCurrentDate().substring(5, 7);
            curDate = curYear + "-" + curMonth + "-" + DateTimeUtils.getCurrentWeek();
        }
        else if (targetName.indexOf("��") != -1) {
            curMonth = DateTimeUtils.getCurrentDate().substring(5, 7);
            curDate = curYear + "-" + curMonth + "-" + curWeek;
        }
        else if (targetName.indexOf("��") != -1) {
            curDate = curYear + "-" + curMonth + "-" + curWeek;
        }
        return curDate;

    }

    public static void main(String args[]) {
        TargetConstant tc = new TargetConstant();
       // System.out.println("-----------current date is------");
        //System.out.println(tc.getCurDate("����ܽ�"));
      //  System.out.println("The lenth is:" + tc.getCurDate("����ܽ�").length());
      //  System.out.println("The night char is:" +
       //                    tc.getCurDate("����ܽ�").substring(5, 7));
    }

}
