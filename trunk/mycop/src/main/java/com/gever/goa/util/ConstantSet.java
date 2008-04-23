package com.gever.goa.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gever.exception.DefaultException;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.struts.action.BaseAction;
import com.gever.util.StringUtils;

/**
 * <p>Title: 程序中用到的常用变量通用方法类</p>
 * <p>Description: 程序中用到的常用变量通用方法类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public final class ConstantSet extends BaseDAO {
    public ConstantSet() {
    }

    //获取当前用户ID-主要用来临时给列表页面获取当前用户作判断用
    public static String CurUserID = "";

    //目标管理
    public final static String YearTargetType = "4";
    public final static String YearSumType = "5";
    public final static String MonthTargetType = "2";
    public final static String MonthSumType = "3";
    public final static String WeekTargetType = "1";

    //个人目标
    public final static String PersonYearTargetType = "3";
    public final static String PersonMonthTargetType = "2";
    public final static String PersonWeekTargetType = "1";

    //用来固定报表模板对应nodeid
    public final static int WorkLogTemplate = 1;
    public final static int PersonWeekTargetTemplate = 2;
    public final static int PersonMonthTargetTemplate = 3;
    public final static int PersonYearTargetTemplate = 4;
    public final static int WeekTargetTemplate = 5;
    public final static int MonthTargetTemplate = 6;
    public final static int MonthSumTemplate = 7;
    public final static int YearTargetTemplate = 8;
    public final static int YearSumTemplate = 9;
    public final static int FiveYearLayoutTemplate = 10;
    public final static int WorkReportTemplate = 11;
    public final static int ReportTargetTemplate=15;

    /**
     * 得到参数得到对应的目标类型
     * @param targetType String
     * @return String
     * add by lihaidong
     */

    public static String judgeTargetType(String targetType){
        String tempTargetType="";
        if(targetType.equalsIgnoreCase(WeekTargetType)){
            tempTargetType=WeekTargetType;
        }else if(targetType.equalsIgnoreCase(MonthTargetType)){
            tempTargetType=MonthSumType;
        }else if(targetType.equalsIgnoreCase(MonthSumType)){
            tempTargetType=MonthSumType;
        }else if(targetType.equalsIgnoreCase(YearTargetType)){
            tempTargetType=YearTargetType;
        }else if(targetType.equalsIgnoreCase(YearSumType)){
            tempTargetType=YearSumType;
        }
        return tempTargetType;
    }

    /**
     * 通过当前记录的审核和审批两个状态来显示审核审批状态的中文
     * @param audit_flag 审核状态
     * @param check_flag 审批状态
     * @return String 可能的审核审批状况
     * @throws DefaultException
     */
    public static String getAuditCheckName(String audit_flag, String check_flag) throws DefaultException {
        String retAuditCheckName = "";
        //排列组合
        //audit_flag-----0表示未审核,1表示已通过审核,2表示未通过审核
        //check_flag-----0表示未审批,1表示已通过审批,2表示未通过审批
        //如果审批通过则不能修改,审批不通过则审核人可以修改
        int audit_check_flag = 0;
        if ("0".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 0; //未审核
        }
        if ("1".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 1; //审核通过未审批
        }
        if ("2".equals(audit_flag) && "0".equals(check_flag)) {
            audit_check_flag = 2; //审核未通过
        }
        if ("1".equals(audit_flag) && "1".equals(check_flag)) {
            audit_check_flag = 3; //审批通过
        }
        if ("1".equals(audit_flag) && "2".equals(check_flag)) {
            audit_check_flag = 4; //审批未通过
        }
        switch (audit_check_flag) {
            case 0:
                retAuditCheckName = "未审核";
                break;
            case 1:
                retAuditCheckName = "审核通过未审批";
                break;
            case 2:
                retAuditCheckName = "审核未通过";
                break;
            case 3:
                retAuditCheckName = "审批通过";
                break;
            case 4:
                retAuditCheckName = "审批未通过";
                break;
            default:
                retAuditCheckName = "";
                break;
        }
        return retAuditCheckName;
    }

    /**
     * 判断当前人是否拥有删除权限--只有创建人才有删除权限
     * @param curUser 当前用户
     * @param user_code 创建人
     * @return int 1----拥有删除权限--0--不拥有删除权限
     * @throws DefaultException
     */
    public static int judgeDelTag(String curUser, String user_code) throws DefaultException {
        int tag = 0;
        if (curUser.equals(user_code)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断当前人是否拥有修改权限--只有创建人才有修改权限,并且要保证没有通过审核
     * @param curUser 当前用户
     * @param user_code 创建人
     * @param audit_flag 审核状态
     * @return int 1----拥有修改权限--0--不拥有修改权限
     * @throws DefaultException
     */
    public static int judgeEditTag(String curUser, String user_code, String audit_flag) throws DefaultException {
        int tag = 0;
        if (curUser.equals(user_code) && "0".equals(audit_flag) || "2".equals(audit_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断当前人是否拥有审核权限--只有当前人为审核人时才具有审核权限，并且要保证审核和审批都没有通过
     * @param curUser 当前用户ID
     * @param auditor 审核人ID
     * @param audit_flag 审核状态
     * @param check_flag 审批状态
     * @return int 1----拥有审核权限--0--不拥有审核权限
     * @throws DefaultException
     */
    public static int judgeAuditTag(String curUser, String auditor, String audit_flag, String check_flag) throws
        DefaultException {
        int tag = 0;
        if (curUser.equals(auditor) && "0".equals(audit_flag) && "0".equals(check_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断当前人是否拥有审批权限--只有当前人为审批人时才具有审批权限，并且要保证审核已通过而审批没有通过
     * @param curUser 当前用户ID
     * @param checker 审批人ID
     * @param audit_flag 审核状态
     * @param check_flag 审批状态
     * @return int 1----拥有审核权限--0--不拥有审核权限
     * @throws DefaultException
     */
    public static int judgeCheckTag(String curUser, String checker, String audit_flag, String check_flag) throws
        DefaultException {
        int tag = 0;
        if (curUser.equals(checker) && "1".equals(audit_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断当前人是否拥有批准权限--只有当前人为批准人时才具有批准权限，并且要保证批准还没有开始
     * @param curUser 当前用户ID
     * @param approve 批准人ID
     * @param approve_flag 批准状态
     * @return int 1----拥有批准权限--0--不拥有批准权限
     * @throws DefaultException
     */
    public static int judgeApproveTag(String curUser, String approve, String approve_flag) throws
        DefaultException {
        int tag = 0;
        if (curUser.equals(approve) && "0".equals(approve_flag)) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断当前人是否是创建人--只有创建人才有修改和删除权限-这里只用在个人目标中-无需审核
     * @param curUser 当前用户
     * @param user_code 创建人
     * @return int 1----拥有修改删除权限--0--不拥有修改删除权限
     * @throws DefaultException
     */
    public static int judgeCreatorTag(String curUser, String user_code,String auditor,String audit_flag) throws DefaultException {
        int tag = 0;
        if ((curUser.equals(user_code)||curUser.equals(auditor))&&!audit_flag.equals("1")) {
            tag = 1;
        }
        return tag;
    }
    /**
     * 判断人周，人月，人年目标审核权限
     * @param curUser String
     * @param auditor String
     * @param audit_flag String
     * @return 0 表示没有审核权限，1表示有审核权限
     */
    public static int judgeAuditFlag(String curUser,String auditor,String audit_flag){
        int auditFlag=0;
        if(curUser.equals(auditor)&&!"1".equals(audit_flag)){
            auditFlag=1;
        }
        return auditFlag;
    }

    /**
     * 用于目标管理中带审核和审批处理的情况下判断当前人是否可以在查看页面上显示修改按钮
     * 不光创建人才有修改权限,审核人和审批人也具有修改权限
     * @param curUser 当前用户
     * @param user_code 创建人
     * @return int 1----拥有修改删除权限--0--不拥有修改删除权限
     * @throws DefaultException
     */
    public static int judgeEditTagForView(String curUserID, String make_person, String check_flag, String checker,
                                          String audit_flag, String auditor) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !"1".equals(audit_flag)) {
            //如果当前用户就是创建人，并且审核没有通过，那么当前人是可以修改的
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(auditor) && !"1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审核人,如果审核没有通过并且审批也没有通过,则审核人可修改
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(checker) && "1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审批人,如果审核通过了,审批没有通过的话,则审批人可修改
            tag = 1;
        } else if (curUserID.equals(make_person) && "1".equals(audit_flag) && !"1".equals(check_flag)) {
            //如果当前用户就是创建人，并且审核也通过了，但是审批没有通过，这时也应该允许用户修改-这是一种特例
            //即用户自己提交给自己审核和审批，这一点我在这里设定为允许-如果不允许的话需要控制其选择人员权限
            tag = 1;
        }

        return tag;
    }

    /**
     * 因为测试时要求改变为区别对待查看页面上的修改按钮
     * 判断条件：如果当前人是创建人，则显示修改按钮
     * 如果当前人不是创建人，则判断是否为审核人或审批人
     * 如果当前人不是创建人，而是审核人或审批人，如果条件合适，则显示审核或审批按钮
     * flag=0-------------对应按钮不显示
     * flag=1-------------对应修改按钮
     * flag=2-------------对应审核按钮
     * flag=3-------------对应审批按钮
     * 不光创建人才有修改权限,审核人和审批人也具有修改权限
     * @param curUser 当前用户
     * @param user_code 创建人
     * @return int 3-----拥有审批权限----2----拥有审核权限---1----拥有修改权限--0--不拥有修改权限
     * @throws DefaultException
     */
    public static int judgeEditTagForViewOfNew(String curUserID, String make_person, String check_flag, String checker,
                                               String audit_flag, String auditor) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !"1".equals(audit_flag) && !curUserID.equals(auditor)) {
            //如果当前用户就是创建人，并且当前用户不是审核人,并且审核没有通过，那么当前人是可以修改的
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(auditor) && !"1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审核人,如果审核没有通过并且审批也没有通过,则审核人可修改
            tag = 2;
        } else if (!curUserID.equals(make_person) && curUserID.equals(checker) && "1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审批人,如果审核通过了,审批没有通过的话,则审批人可修改
            tag = 3;
        } else if (curUserID.equals(make_person) && curUserID.equals(auditor) && !"1".equals(audit_flag)) {
            //如果当前用户就是创建人，而且是审核人,并且审核没有通过了，这时也应该允许用户审核-这是一种特例
            //即用户自己提交给自己审核和审批，这一点我在这里设定为允许-如果不允许的话需要控制其选择人员权限
            tag = 2;
        } else if (curUserID.equals(make_person) && curUserID.equals(checker) && "1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //如果当前用户就是创建人，并且是审批人,并且审核也通过了，但是审批没有通过，这时应该允许用户审批-这是一种特例
            //即用户自己提交给自己审核和审批，这一点我在这里设定为允许-如果不允许的话需要控制其选择人员权限
            tag = 3;
        }

        return tag;
    }
    /**
     * 判断是否可以修改
     * @param curUser
     * @param make_person
     * @param auditor
     * @param checker
     * @param audit_flag
     * @return
     * @throws DefaultException
     */
    public static int  judgeModify(String curUser,String make_person,String auditor,String checker, String audit_flag,String check_flag) throws DefaultException{
        int modify_flag=0;
        if(curUser.equals(make_person)&&"0".equals(audit_flag)&&"0".equals(check_flag)){
            modify_flag=1;
        }else if(curUser.equals(auditor)&& "0".equals(audit_flag)&&"0".equals(check_flag)){
            modify_flag=1;
        }else if(curUser.equals(checker)&&"1".equals(audit_flag)&&"0".equals(check_flag)){
            modify_flag=1;
        }
        return modify_flag;
    }
    /**
     * 判断是否显示取消审核，取消审批按钮
     * 1 表示 显示取消审核按钮，2表示显示审批按钮，0表示不显示
     * @param curUser
     * @param auditor
     * @param checker
     * @param audit_flag
     * @param check_flag
     * @return
     */
    public static int judgeIsCancel(String curUser,String auditor,String checker,String audit_flag,String check_flag){
        int cancel_flag=0;
        if(curUser.equals(auditor)&&"1".equals(audit_flag)&&"0".equals(check_flag)){
            cancel_flag=1;
        }else if(curUser.equals(checker)&&"1".equals(audit_flag)&&"1".equals(check_flag)){
            cancel_flag=2;
        }
        /*else if(curUser.equals(auditor)&&curUser.equals(checker)&&"1".equals(check_flag)&&"1".equals(audit_flag)){
            cancel_flag=2;
        }else if(curUser.equals(auditor)&&curUser.equals(checker)&&"0".equals(check_flag)&&"1".equals(audit_flag)){
            cancel_flag=1;
        }*/
        return cancel_flag;
    }

    /**
     * 用于目标管理中带审核和审批处理的情况下判断当前人是否可以在修改修改页面上显示的模板
     * 不光创建人才有修改权限,审核人和审批人也具有修改权限
     * 业务上认为审核人和审批人是可以修改提交过来的目标模板的
     * 实际上我觉得审批人最好是不能修改，因为它作为最后一级的话
     * 不同意的话就打回去，是不需要修改的-这里还是认为审批人也可修改模板
     * @param curUser 当前用户
     * @param user_code 创建人
     * @param auditor 审核者
     * @param audit_flag 审核标志
     * @param checker 审批者
     * @param check_flag 审批标志
     * @return int 1----拥有修改删除权限--0--不拥有修改删除权限
     * @throws DefaultException
     */
    public static int judgeReadOnlyTemplateTag(String curUserID, String user_code, String check_flag, String checker,
                                               String audit_flag, String auditor) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(user_code)) {
            //如果当前用户就是创建人，并且审核没有通过，那么当前人是可以修改的
            tag = 1;
        } else if (!curUserID.equals(user_code) && curUserID.equals(auditor)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审核人,如果审核没有通过并且审批也没有通过,则审核人可修改
            tag = 1;
        } else if (!curUserID.equals(user_code) && curUserID.equals(checker) && "1".equals(audit_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审批人,如果审核通过了,审批没有通过的话,则审批人可修改
            tag = 1;
        } else if (curUserID.equals(user_code) && "1".equals(audit_flag) && !"1".equals(check_flag)) {
            //如果当前用户就是创建人，并且审核也通过了，但是审批没有通过，这时也应该允许用户修改-这是一种特例
            //即用户自己提交给自己审核和审批，这一点我在这里设定为允许-如果不允许的话需要控制其选择人员权限
            tag = 1;
        }

        return tag;
    }

    /**
     * 用于五年规划中带审批处理的情况下判断当前人是否可以在修改修改页面上显示的模板
     * 不光创建人才有修改权限,审批人也具有修改权限
     * 业务上认为审批人是可以修改提交过来的目标模板的
     * 实际上我觉得审批人最好是不能修改，因为它作为最后一级的话
     * 不同意的话就打回去，是不需要修改的-这里还是认为审批人也可修改模板
     * @param curUser 当前用户
     * @param user_code 创建人
     * @param approve 审批者
     * @param approve_flag 审批标志
     * @return int 1----拥有修改删除权限--0--不拥有修改删除权限
     * @throws DefaultException
     */
    public static int judgeFiveYearLayoutReadOnlyTemplateTag(String curUserID, String user_code, String approve_flag,
        String approve) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(user_code)) {
            //如果当前用户就是创建人，并且审批没有通过，那么当前人是可以修改的
            tag = 1;
        } else if (!curUserID.equals(user_code) && curUserID.equals(approve)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审批人,如果审批没有通过的话,则审批人可修改
            tag = 1;
        }
        return tag;
    }

    /**
     * 判断目标是否已经存在于数据库中
     * @param year--当前填写的年度
     * @param month--当前填写的月度
     * @param week--当前填写的周
     * @param type--当前的目标类型
     * @param deptcode--当前的填写人所在部门
     * @param usercode--当前填写人
     * @return String 相对应的字符串-1-数据库中已经存在该项
     * @throws DefaultException
     */
    public String queryTargetIsExist(String year, String month, String week, String type, String deptcode,
                                     String usercode) throws
        Exception {
        String existFlag = "0";
        /*
        System.out.println("-----------The current year is---------"+year);
        System.out.println("-----------The current month is---------"+month);
        System.out.println("-----------The current week is-----------"+week);
        */

        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //SQLHelper helper = new DefaultSQLHelper(super.dbData);
        StringBuffer sbSql = new StringBuffer();
        BaseAction bAction=new BaseAction(); //因DB2和ORACLE中对空表示的不一样，所以要调用BaseAction中的方法
        sbSql.append("select SERIAL_NUM from DO_TARGET_MANAGE where ");
        if(StringUtils.isNull(year)){
          sbSql.append(bAction.getIsNull("CURRENT_YEAR"));
        }else{
          sbSql.append("CURRENT_YEAR="+ "'"+ year +"'");
        }
        if(StringUtils.isNull(month)){
          sbSql.append(" and ").append(bAction.getIsNull("CURRENT_MONTH"));
        }else{
          sbSql.append("and CURRENT_MONTH="+"'"+month+"'");
        }
        if(StringUtils.isNull(week)){
          sbSql.append(" and ").append(bAction.getIsNull("CURRENT_WORK"));
        }else{
          sbSql.append(" and CURRENT_WORK="+"'"+week+"'");
        }
        sbSql.append("and TARGET_TYPE='" + type + "' ");
        sbSql.append("and DEPT_CODE='" + deptcode + "' ");
        sbSql.append("and USER_CODE=" + Integer.parseInt(usercode) + " ");
        System.out.println("-------------The query sql is ---------"+sbSql.toString());
        try {
            pstmt = conn.prepareStatement(sbSql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                existFlag = "1"; //1--------存在该项
                //throw new DefaultException("您已经填写了该项，不能再新增了，可选择编辑该项！");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            //System.out.println("ConstantSet queryTargetIsExist error::" + e.getMessage());
        }
        finally {
            rs.close();
            pstmt.close();
            conn.close();
        }

        return existFlag;
    }

    /**
     * 判断五年规划是否存在
     * @param curYear String
     * @param dept String
     * @return String
     */
    public String queryFiveYearsTargetIsExist(String curYear,String dept) throws
      DefaultException, SQLException {
       String existFlag="0";
       ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
           dbData);
       Connection conn = cp.getConnection();
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       //SQLHelper helper = new DefaultSQLHelper(super.dbData);
       StringBuffer sbSql = new StringBuffer();
       sbSql.append("select SERIAL_NUM from do_fiveyear_layout ");
       sbSql.append("CURRENT_YEAR=").append(curYear);
       rs=pstmt.executeQuery(sbSql.toString());
       if(rs.next()){
         existFlag="1";
       }
       rs.close();
       pstmt.close();
       conn.close();
       return existFlag;
     }




    /**
     * 判断个人目标是否已经存在于数据库中
     * @param year--当前填写的年度
     * @param month--当前填写的月度
     * @param week--当前填写的周
     * @param type--当前的目标类型
     * @param deptcode--当前的填写人所在部门
     * @param usercode--当前填写人
     * @return String 相对应的字符串-1-数据库中已经存在该项
     * @throws DefaultException
     */
    public String queryPersonTargetIsExist(String year, String month, String week, String type, String deptcode,
                                           String usercode) throws
        Exception {

        String existFlag = "0";
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //SQLHelper helper = new DefaultSQLHelper(super.dbData);
        StringBuffer sbSql = new StringBuffer();
        sbSql.append("select SERIAL_NUM from DO_PERSONAL_TARGET where CURRENT_YEAR='" + year + "' ");
        sbSql.append("and CURRENT_MONTH='" + month + "' ");
        sbSql.append("and CURRENT_WORK='" + week + "' ");
        sbSql.append("and TARGET_TYPE=" + type + " ");
        sbSql.append("and DEPT_CODE='" + deptcode + "' ");
        sbSql.append("and USER_CODE=" + Integer.parseInt(usercode) + " ");
        try {
            pstmt = conn.prepareStatement(sbSql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                existFlag = "1"; //1--------存在该项
                //throw new DefaultException("您已经填写了该项，不能再新增了，可选择编辑该项！");
            }
        } catch (Exception e) {
           // System.out.println("ConstantSet queryPersonTargetIsExist error::" + e.getMessage());
        }
        finally {
            rs.close();
            pstmt.close();
            conn.close();
        }

        return existFlag;
    }

    /**
     * 用在公文管理中判断当前公文是否办结
     * @param serial_number 当前公文系列号
     * @return int 1----已办结--0--未办结
     * @throws DefaultException
     */
    public int judgeDocCheckTag(String serial_number) throws DefaultException {

        int tag = 0;

        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("SELECT CHECK_FLAG FROM AM_SEND_MANAGE ");
        sbSql.append("WHERE CHECK_FLAG = 1 "); //办结标志为1
        sbSql.append("AND SERIAL_NUM = '" + serial_number + "' ");
        ArrayList ary = (ArrayList) sqlHelper.query(sbSql.toString());
        if (ary.size() > 0) {
            tag = 1;
        }
        return tag;
    }



    /**
     * 用在公文管理中判断当前公文是否已登记
     * @param serial_number 当前公文系列号
     * @return int 1----已登记--0--未登记
     * @throws DefaultException
     */
    public int judgeDocRegTag(String serial_number) throws DefaultException {

        int tag = 0;

        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("SELECT REGISTER_DATE FROM AM_SEND_MANAGE ");
        sbSql.append("WHERE REGISTER_DATE is not null "); //如果登记日期存在说明已登记
        sbSql.append("AND SERIAL_NUM = '" + serial_number + "' ");
        ArrayList ary = (ArrayList) sqlHelper.query(sbSql.toString());
        if (ary.size() > 0) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 用在公文管理中判断当前公文是否办结
     * author 杨其文
     * @param serial_number 当前公文系列号
     * @return int 1----已办结--0--未办结
     * @throws DefaultException
     */
    public int judgDocCheckTag(String serial_number) throws DefaultException {

        int tag = 0;

        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("SELECT CHECK_FLAG FROM AM_RECEIVE_MANAGE ");
        sbSql.append("WHERE CHECK_FLAG = 1 "); //办结标志为1
        sbSql.append("AND SERIAL_NUM = '" + serial_number + "' ");
        ArrayList ary = (ArrayList) sqlHelper.query(sbSql.toString());
               if (ary.size() > 0) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 用在收文管理中判断当前公文是否已登记
     * author 杨其文
     * @param serial_number 当前公文系列号
     * @return int 1----已登记--0--未登记
     * @throws DefaultException
     */

    public int judgDocRegTag(String serial_number) throws DefaultException {

        int tag = 0;

        SQLHelper sqlHelper = new DefaultSQLHelper(this.dbData);
        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("SELECT REGISTER_DATE FROM AM_RECEIVE_MANAGE ");
        sbSql.append("WHERE REGISTER_DATE is not null "); //如果登记日期存在说明已登记
        sbSql.append("AND SERIAL_NUM = '" + serial_number + "' ");

        ArrayList ary = (ArrayList) sqlHelper.query(sbSql.toString());
        if (ary.size() > 0) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 用在公文管理中判断当前人对该公文是否具有修改和删除权限
     * 判断条件--未办结,是创建人自己，未进入流转
     * @param curUserID 当前登陆用户ID号
     * @param make_person 当前公文创建人ID号
     * @param check_flag 当前公文是否办结标志-0-未办结
     * @param process_id 当前公文是否进入流程的对应流程ID
     * @return int 1----可修改删除--0--不可修改删除
     * @throws DefaultException
     */
    public int judgeDocEditDeleteTag(String curUserID, String make_person, String check_flag, String process_id) throws
        DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && "0".equals(check_flag) && StringUtils.isNull(process_id) == true) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 用在目标管理中判断当前人对该记录是否具有修改和删除权限
     * 判断条件--当前人是创建人自己,并且未进入审核或者审核未通过-check_flag不为1,
     * 这里认为审核未通过也可再让创建人重新修改
     * 因为这里将审核和审批都放在修改页面处理
     * 并且需求上确定了上一级别的人员对下一级别的人员提交过来的记录都有修改权限
     * 所以审核人审批人也可有修改权限
     * 需求上确定如果审核人审核通过后,审核人不可再次审核
     * 需求上确定如果审批人审批通过后,审批人不可再次审批
     * @param curUserID 当前登陆用户ID号
     * @param make_person 当前目标或规划创建人ID号
     * @param check_flag 审批标志
     * @param checker 审批人
     * @param audit_flag 审核标志
     * @param auditor 审核人
     * @return int 1----可修改删除--0--不可修改删除
     * @throws DefaultException
     */
    public static int judgeTargetEditDeleteTag(String curUserID, String make_person, String check_flag, String checker,
                                               String audit_flag, String auditor) throws
        DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !"1".equals(audit_flag)) {
            //如果当前用户就是创建人，并且审核没有通过，那么当前人是可以修改的
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(auditor) && !"1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审核人,如果审核没有通过并且审批也没有通过,则审核人可修改
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(checker) && "1".equals(audit_flag) &&
                   !"1".equals(check_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审批人,如果审核通过了,审批没有通过的话,则审批人可修改
            tag = 1;
        } else if (curUserID.equals(make_person) && "1".equals(audit_flag) && !"1".equals(check_flag)) {
            //如果当前用户就是创建人，并且审核也通过了，但是审批没有通过，这时也应该允许用户修改-这是一种特例
            //即用户自己提交给自己审核和审批，这一点我在这里设定为允许-如果不允许的话需要控制其选择人员权限
            tag = 1;
        }
        return tag;
    }

    /**
     * 用在五年规划中判断当前人对该五年规划是否具有修改和删除权限
     * @param curUserID 当前登陆用户ID号
     * @param make_person 当前五年规划创建人ID号
     * @param approve_flag 当前五年规划是否审批通过标志-1-通过-0-未通过
     * @param process_id 当前公文是否进入流程的对应流程ID
     * @return int 1----可修改删除--0--不可修改删除
     * @throws DefaultException
     */

    public static int judgeFiveYearLayoutEditDeleteTag(String curUserID, String make_person, String approve_flag,
        String approver) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !"1".equals(approve_flag)) {
            //如果当前用户就是创建人，并且审批没有通过，那么当前人是可以修改的
            tag = 1;
        } else if (!curUserID.equals(make_person) && curUserID.equals(approver) && !"1".equals(approve_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审批人,如果审批没有通过,则审批人可修改
            tag = 1;
        }
        return tag;
    }
    /**
     *
     * @param curUser
     * @param make_person
     * @param approver
     * @param approv_flag
     * @return
     * @throws DefalutException
     */
    public static int judgeFiveYearModifyFlag(String curUser,String make_person,String approver,String approve_flag)throws DefaultException{
        int modify_flag=0;
        if(curUser.equals(make_person)&&"0".equals(approve_flag)&&!curUser.equals(approver)){
            modify_flag=1;
        }else if(!curUser.equals(make_person)&& curUser.equals(approver)&&"0".equals(approve_flag)){
            modify_flag=1;
        }else if(curUser.equals(make_person)&&curUser.equals(approver)&&"0".equals(approve_flag)){
            modify_flag=1;
        }
        return modify_flag;
    }
    /**
     * 判断五年规划中，用户是否取消审批
     * 2 表示取消审批
     * @param curUser
     * @param approver
     * @param approve_flag
     * @return
     */
    public static int judgeFiveYearIsCancel(String curUser,String approver,String approve_flag){
        int isCancel_flag=0;
        if(curUser.equals(approver)&&"1".equals(approve_flag)){
            isCancel_flag=2;
        }
        return isCancel_flag;
    }

    /**
     * 用在五年规划查看中判断当前人对该五年规划显示修改还是审批按钮
     * @param curUserID 当前登陆用户ID号
     * @param make_person 当前五年规划创建人ID号
     * @param approve_flag 当前五年规划是否审批通过标志-1-通过-0-未通过
     * @param process_id 当前公文是否进入流程的对应流程ID
     * @return int 2---可审批------1----可修改删除--0--不可修改删除
     * @throws DefaultException
     */

    public static int judgeFiveYearLayoutEditDeleteTagOfNew(String curUserID, String make_person, String approve_flag,
        String approver) throws DefaultException {
        int tag = 0;
        if (curUserID.equals(make_person) && !curUserID.equals(approver) && !"1".equals(approve_flag)) {
            //如果当前用户就是创建人，但不是审批人,并且审批没有通过，那么当前人是可以修改的
            tag = 1;//---显示修改按钮
        } else if (!curUserID.equals(make_person) && curUserID.equals(approver) && !"1".equals(approve_flag)) {
            //如果当前用户不是创建人，那么需要判断当前人是否是审批人,如果审批没有通过,则审批人可修改
            tag = 2;//---显示审批按钮
        } else if (curUserID.equals(make_person) && curUserID.equals(approver) && !"1".equals(approve_flag)) {
            //如果当前用户就是创建人，并且当前人也是审批人，并且审批没有通过,那么当前人有审批权限
            tag = 2;//---显示审批按钮
        }
        return tag;
    }

    /**
     * 用在公文管理中判断当前人对该公文是否具有修改和删除权限
     * author:杨其文
     * 判断条件--未办结,是创建人自己，未进入流转
     * @param curUserID 当前登陆用户ID号
     * @param make_person 当前公文创建人ID号
     * @param check_flag 当前公文是否办结标志-0-未办结
     * @param process_id 当前公文是否进入流程的对应流程ID
     * @return int 1----可修改删除--0--不可修改删除
     * @throws DefaultException
     */

    public int judgDocEditDeleteTag(String check_flag, String process_id) throws DefaultException {
        int tag = 0;
        if ("0".equals(check_flag) && StringUtils.isNull(process_id) == true) {
            tag = 1;
        }
        return tag;
    }

    /**
     *
     * @param curUserID String判断是公文提交后否可以修改
     * @param user_code String
     * @param approve_flag String
     * @param approve String
     * @throws DefaultException杨其文
     * @return int
     */
    public int judgeReceiveView(String check_flag, String process_id) throws DefaultException {
        int tag = 0;
        if ("0".equals(check_flag) && StringUtils.isNull(process_id) == true) {
            tag = 1;
        }
        return tag;
    }

    /**
     * 从单位信息表中获取单位名称-这个表永远只存一条记录
     * @return 单位名称
     * @throws Exception
     */
    public String getUnitName() throws Exception {
        String unitName = "";
        ConnectionProvider cp = ConnectionProviderFactory.getConnectionProvider(this.
            dbData);
        Connection conn = cp.getConnection();
        java.sql.PreparedStatement pstmt = null;
        ResultSet rs = null;

        StringBuffer sbSql = new StringBuffer(); ;
        sbSql.append("select UNIT_NAME from SYS_UNIT_INFO ");
        try {
            pstmt = conn.prepareStatement(sbSql.toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                unitName = rs.getString("UNIT_NAME");
            }
        } catch (Exception e) {
           // System.out.println("ConstantSet queryPersonTargetIsExist error::" + e.getMessage());
        }
        finally {
            rs.close();
            pstmt.close();
            conn.close();
        }

        return unitName;
    }

}
