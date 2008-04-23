package com.gever.goa.dailyoffice.workreport.util;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.workreport.vo.DoWorkReportTreeVO;

/**
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description:�����㱨�ڵ����� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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

  private static String NotSend_WhereStr = "SEND_FLAG = 0"; //δ���͵�sql����
  private static String Sended_WhereStr = "SEND_FLAG = 1"; //���͵�sql����
  private static String NotCheck_WhereStr =
      "(DO_WORK_REPORT.CHECKER IS NULL OR DO_WORK_REPORT.CHECKER='')"; //δ��˵�sql����
  private static String Checked_WhereStr =
      "(DO_WORK_REPORT.CHECKER IS NOT NULL AND DO_WORK_REPORT.CHECKER<>'')"; //�Ѿ���˵�sql����

  private String Checker_WhereStr; //��������˵�sql����
  private String UserCode_WhereStr; //�����û�user_code��sql����
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
   * ����userid��ʼ��ToCheck_WhereStr��UserCode_WhereStr
   */
  private void initSQLStr() {
    this.UserCode_WhereStr = "DO_WORK_REPORT.USER_CODE=" + this.userID;
    this.Checker_WhereStr = "DO_WORK_REPORT.RENDER='" + this.userID + "'";
  }

  /**
   * ���DoWorkReportTreeVO������Ϊ�ڵ�
   */

  public void initAllWhereTypes() {
    DoWorkReportTreeVO btvo1 = new DoWorkReportTreeVO();
    btvo1.setNodeid("0");
    btvo1.setNodename("���ڱ༭");
    btvo1.setMemo(" AND " + UserCode_WhereStr + " AND " +
                  NotSend_WhereStr);
    btvo1.setOtherProperty(null);
    allWhereTypes.add(btvo1);

    DoWorkReportTreeVO btvo2 = new DoWorkReportTreeVO();
    btvo2.setNodeid("1");
    btvo2.setNodename("δ������");
    btvo2.setMemo(" AND " + UserCode_WhereStr + " AND " +
                  Sended_WhereStr +
                  " AND " + NotCheck_WhereStr);
    btvo2.setOtherProperty(null);
    allWhereTypes.add(btvo2);

    DoWorkReportTreeVO btvo3 = new DoWorkReportTreeVO();
    btvo3.setNodeid("2");
    btvo3.setNodename("�ѱ�����");
    btvo3.setMemo(" AND " + UserCode_WhereStr + " AND " +
                  Sended_WhereStr +
                  " AND " + Checked_WhereStr);
    btvo3.setOtherProperty(null);
    allWhereTypes.add(btvo3);

    DoWorkReportTreeVO btvo4 = new DoWorkReportTreeVO();
    btvo4.setNodeid("3");
    btvo4.setNodename("��������");
    btvo4.setMemo(" AND " + Sended_WhereStr + " AND " +
                  Checker_WhereStr + " AND " + NotCheck_WhereStr);
    btvo4.setOtherProperty(null);
    allWhereTypes.add(btvo4);

    DoWorkReportTreeVO btvo5 = new DoWorkReportTreeVO();
    btvo5.setNodeid("4");
    btvo5.setNodename("��������");
    btvo5.setMemo(" AND " + Sended_WhereStr + " AND " +
                  Checker_WhereStr + " AND " + Checked_WhereStr);
    btvo5.setOtherProperty(null);
    allWhereTypes.add(btvo5);

  }

}
