package com.gever.goa.dailyoffice.workreport.util;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.workreport.vo.DoWorkReportTreeVO;

/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description:工作汇报节点树类 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class WorkReportWhereTypes {
  public static String QueryAll_DB2 =
      "SELECT SERIAL_NUM,CREATE_DATE,DO_WORK_REPORT.USER_CODE AS USER_CODE,BEGIN_TIME,END_TIME,DO_WORK_REPORT.RENDER AS RENDER,DO_WORK_REPORT.CHECKER AS CHECKER,CHECK_DATE,CONTENT,SEND_FLAG,A.NAME AS USER_NAME,B.NAME AS RENDER_NAME,C.NAME AS CHECKER_NAME FROM DO_WORK_REPORT" +
      " LEFT OUTER JOIN T_USER AS A ON DO_WORK_REPORT.USER_CODE=A.ID " +
      " LEFT OUTER JOIN T_USER AS B ON DO_WORK_REPORT.RENDER=CAST(B.ID AS CHAR(20)) " +
      " LEFT OUTER JOIN T_USER AS C ON DO_WORK_REPORT.CHECKER=CAST(C.ID AS CHAR(20)) " +
      " WHERE 1=1";

  public static String QueryAll_Oracle =
      "SELECT SERIAL_NUM,CREATE_DATE,DO_WORK_REPORT.USER_CODE AS USER_CODE,BEGIN_TIME,END_TIME,DO_WORK_REPORT.RENDER AS RENDER,DO_WORK_REPORT.CHECKER AS CHECKER,CHECK_DATE,SEND_FLAG,A.NAME AS USER_NAME,B.NAME AS RENDER_NAME,C.NAME AS CHECKER_NAME,CONTENT FROM DO_WORK_REPORT,T_USER  A,T_USER B,T_USER C" +
      " WHERE DO_WORK_REPORT.USER_CODE=A.ID (+)" +
      " AND cast(DO_WORK_REPORT.RENDER as integer)=B.ID(+)" +
      " AND cast(DO_WORK_REPORT.CHECKER as integer)=C.ID(+)";

  private static String NotSend_WhereStr = "SEND_FLAG = 0"; //未发送的sql条件
  private static String Sended_WhereStr = "SEND_FLAG = 1"; //发送的sql条件
  private static String NotCheck_WhereStr =
      "(DO_WORK_REPORT.CHECKER IS NULL OR DO_WORK_REPORT.CHECKER='')"; //未审核的sql条件
  private static String Checked_WhereStr =
      "(DO_WORK_REPORT.CHECKER IS NOT NULL AND DO_WORK_REPORT.CHECKER<>'')"; //已经审核的sql条件

  private String Checker_WhereStr; //限制审核人的sql条件
  private String UserCode_WhereStr; //限制用户user_code的sql条件
  private String userID;
  private String dbtype;


  public WorkReportWhereTypes(String userID) {
    this.userID = userID;
    initSQLStr();
    this.initAllWhereTypes();

  }

  private List allWhereTypes = new ArrayList();
  public List getAllWhereTypes() {
    return allWhereTypes;
  }

  public DoWorkReportTreeVO getWhereTypeById(int listtypeid) throws
      DefaultException {
    return (DoWorkReportTreeVO) allWhereTypes.get(listtypeid);
  }

  public String getWhereStrById(int listtypeid) throws
      DefaultException {
    return ( (DoWorkReportTreeVO) allWhereTypes.get(listtypeid)).getMemo();
  }

  /**
   * 根据userid初始化ToCheck_WhereStr和UserCode_WhereStr
   */
  private void initSQLStr() {
    this.UserCode_WhereStr = "DO_WORK_REPORT.USER_CODE=" + this.userID;
    this.Checker_WhereStr = "DO_WORK_REPORT.RENDER='" + this.userID + "'";
  }

  /**
   * 添加DoWorkReportTreeVO对象做为节点
   */

  public void initAllWhereTypes() {
    DoWorkReportTreeVO btvo1 = new DoWorkReportTreeVO();
    btvo1.setNodeid("0");
    btvo1.setNodename("正在编辑");
    btvo1.setMemo(" AND " + UserCode_WhereStr + " AND " +
                  NotSend_WhereStr);
    btvo1.setOtherProperty(null);
    allWhereTypes.add(btvo1);

    DoWorkReportTreeVO btvo2 = new DoWorkReportTreeVO();
    btvo2.setNodeid("1");
    btvo2.setNodename("未被审批");
    btvo2.setMemo(" AND " + UserCode_WhereStr + " AND " +
                  Sended_WhereStr +
                  " AND " + NotCheck_WhereStr);
    btvo2.setOtherProperty(null);
    allWhereTypes.add(btvo2);

    DoWorkReportTreeVO btvo3 = new DoWorkReportTreeVO();
    btvo3.setNodeid("2");
    btvo3.setNodename("已被审批");
    btvo3.setMemo(" AND " + UserCode_WhereStr + " AND " +
                  Sended_WhereStr +
                  " AND " + Checked_WhereStr);
    btvo3.setOtherProperty(null);
    allWhereTypes.add(btvo3);

    DoWorkReportTreeVO btvo4 = new DoWorkReportTreeVO();
    btvo4.setNodeid("3");
    btvo4.setNodename("需您审批");
    btvo4.setMemo(" AND " + Sended_WhereStr + " AND " +
                  Checker_WhereStr + " AND " + NotCheck_WhereStr);
    btvo4.setOtherProperty(null);
    allWhereTypes.add(btvo4);

    DoWorkReportTreeVO btvo5 = new DoWorkReportTreeVO();
    btvo5.setNodeid("4");
    btvo5.setNodename("您已审批");
    btvo5.setMemo(" AND " + Sended_WhereStr + " AND " +
                  Checker_WhereStr + " AND " + Checked_WhereStr);
    btvo5.setOtherProperty(null);
    allWhereTypes.add(btvo5);

  }

}
