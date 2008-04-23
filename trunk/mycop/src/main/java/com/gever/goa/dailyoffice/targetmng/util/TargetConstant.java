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
 * <p>Title: 常量类 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class TargetConstant extends BaseDAO {

    //目标管理类型常量
    public final static int WEEK_TARGET_TYPE = 1; //每周目标
    public final static int MONTH_TARGET_TYPE = 2; //月度目标
    public final static int MONTH_SUM_TYPE = 3; //月度总结
    public final static int YEAR_TARGET_TYPE = 4; //年度目标
    public final static int YEAR_SUM_TYPE = 5; //年度总结

    /**
     * 1××表示××周报告，2××表示月报告，3××表示年报告
     */
    //周报告
    public final static int WEEK_QTY_REPORT = 101; //质量周报
    public final static int WEEK_PRJ_REPORT = 102; //项目周报
    public final static int WEEK_HUM_RES_REPORT = 103; //人资周报

    //月报告
    public final static int MONTH_QTY_REPORT = 201; //质量月报
    public final static int MONTH_PRJ_REPORT = 202; //项目月报
    public final static int MONTH_HUM_RES_REPORT = 203; //人资月报
    public final static int MONTH_INFO_SAFE_REPORT = 204; //信安月报

    //年报告
    public final static int YEAR_REPORT=301;//年度报告
    public final static int YEAR_QTY_REPORT = 302; //质量年报
    public final static int YEAR_PRODUCT_REPORT=303;//产品年报
    public final static int YEAR_HUM_RES_REPORT=304;//人资年报
    public final static int YEAR_TECH_REPORT=305;   //技术年报
    public final static int YEAR_SALE_REPORT=306;   //营销年报
    public final static int YEAR_INFO_SAFE_REPORT=307;//信安年报
    private String targetTypeName = ""; //目标类型名字
    private String typeFlagName="";

    public TargetConstant() {
    }

    /**
     * 得到目标的类型
     * @return int
     */

    public int getTargetType(int targetType) {
        int tempType = 0;
        switch (targetType) {
            case 101:
                tempType = WEEK_QTY_REPORT;
                targetTypeName = "质量周报";
                break;
            case 102:
                tempType = WEEK_PRJ_REPORT;
                targetTypeName = "项目周报";
                break;
            case 103:
                tempType = WEEK_HUM_RES_REPORT;
                targetTypeName = "人资周报";
                break;
            case 201:
                tempType = MONTH_QTY_REPORT;
                targetTypeName = "质量月报";
                break;
            case 202:
                tempType = MONTH_PRJ_REPORT;
                targetTypeName = "项目月报";
                break;
            case 203:
                tempType = MONTH_HUM_RES_REPORT;
                targetTypeName = "人资月报";
                break;
            case 204:
                tempType = MONTH_INFO_SAFE_REPORT;
                targetTypeName = "信安月报";
                break;
            case 301:
                tempType = YEAR_REPORT;
                targetTypeName = "年度报告";
                break;
            case 302:
                tempType = YEAR_QTY_REPORT;
                targetTypeName = "质量年报";
                break;
            case 303:
                tempType = YEAR_PRODUCT_REPORT;
                targetTypeName = "产品年报";
                break;
            case 304:
                tempType = YEAR_HUM_RES_REPORT;
                targetTypeName = "人资年报";
                break;
            case 305:
                tempType = YEAR_TECH_REPORT;
                targetTypeName = "技术年报";
                break;
            case 306:
                tempType = YEAR_SALE_REPORT;
                targetTypeName = "营销年报";
                break;
            case 307:
                tempType = YEAR_INFO_SAFE_REPORT;
                targetTypeName = "信安年报";
                break;

        }
        return tempType;
    }

    /**
     * 设置目标类型
     * @param targetType String
     * @return  设置类型，值为1表示为周，2表示为月，3表示为年
     */

    public String getTargetType(String targetType) {
        String type ="";
        targetType = targetType.substring(0, 1);
        if (targetType.equals("1")) {
            typeFlagName="周报告";
            type="1";
        }
        else if (targetType.equals("2")) {
            typeFlagName="月报告";
            type="2";
        }
        else if (targetType.equals("3")) {
            typeFlagName="年报告";
            type="3";
        }
        return type;

    }
    /**
     * 判断报告是否存在
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
       BaseAction bAction=new BaseAction(); //因DB2和ORACLE中对空表示的不一样，所以要调用BaseAction中的方法
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
     * 得到报告类型名字
     * @param targetType Strign
     * @return String
     */
    public String getTypeFlagName(String targetType){
        this.getTargetType(targetType);
        return this.typeFlagName;
    }

    /**
     * 得到目标类型的名字
     * @param targetType int
     * @return String
     */
    public String getTargetTypeName(int targetType) {
        this.getTargetType(targetType);
        return this.targetTypeName;
    }

    /**
     * 根据目标类型设置当前年，月，周
     * @param targetType int
     * @return String
     */
    public static String getCurDate(String targetName) {
        String curYear = DateTimeUtils.getCurrentDate().substring(0, 4);
        String curMonth = "  ";
        String curWeek = " ";
        String curDate = "";
        if (targetName.indexOf("周") != -1) {
            curMonth = DateTimeUtils.getCurrentDate().substring(5, 7);
            curDate = curYear + "-" + curMonth + "-" + DateTimeUtils.getCurrentWeek();
        }
        else if (targetName.indexOf("月") != -1) {
            curMonth = DateTimeUtils.getCurrentDate().substring(5, 7);
            curDate = curYear + "-" + curMonth + "-" + curWeek;
        }
        else if (targetName.indexOf("年") != -1) {
            curDate = curYear + "-" + curMonth + "-" + curWeek;
        }
        return curDate;

    }

    public static void main(String args[]) {
        TargetConstant tc = new TargetConstant();
       // System.out.println("-----------current date is------");
        //System.out.println(tc.getCurDate("年度总结"));
      //  System.out.println("The lenth is:" + tc.getCurDate("年度总结").length());
      //  System.out.println("The night char is:" +
       //                    tc.getCurDate("年度总结").substring(5, 7));
    }

}
