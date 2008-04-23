package com.gever.goa.dailyoffice.calendararrange.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.gever.goa.dailyoffice.calendararrange.vo.CalendarRightVO;
import com.gever.goa.dailyoffice.calendararrange.vo.CalendarUserVO;
import com.gever.struts.form.BaseForm;

/**
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class CalendarArrangeForm
    extends BaseForm {
  private Calendar calendar;
  private List calendarList = new ArrayList();
  private int calendarYear;
  private int calendarMonth;
  private int calendarWeek;
  private String listDate="";
  private String listType="";//�ж�����б������.�ǰ��»��ǰ���
  private String rightType = "0"; //�����ճ̰��ŵ�����.����������޸�
  private CalendarUserVO calendarUserVO;//�ճ̰�����
  private CalendarRightVO calendarRightVO;//�ճ̰���
  private String departmentId="-1";
  private List calendarUserList;
  private List deptCalendarUserList;
  private String messageAction="";
  public Calendar getCalendar() {
    return calendar;
  }
  public List getCalendarList() {
    return calendarList;
  }
  public int getCalendarMonth() {
    return calendarMonth;
  }
  public CalendarRightVO getCalendarRightVO() {
    return calendarRightVO;
  }
  public List getCalendarUserList() {
    return calendarUserList;
  }
  public CalendarUserVO getCalendarUserVO() {
    return calendarUserVO;
  }
  public int getCalendarWeek() {
    return calendarWeek;
  }
  public int getCalendarYear() {
    return calendarYear;
  }
  public String getDepartmentId() {
    return departmentId;
  }
  public List getDeptCalendarUserList() {
    return deptCalendarUserList;
  }
  public String getListDate() {
    return listDate;
  }
  public String getListType() {
    return listType;
  }
  public String getRightType() {
    return rightType;
  }
  public void setRightType(String rightType) {
    this.rightType = rightType;
  }
  public void setListType(String listType) {
    this.listType = listType;
  }
  public void setListDate(String listDate) {
    this.listDate = listDate;
  }
  public void setDeptCalendarUserList(List deptCalendarUserList) {
    this.deptCalendarUserList = deptCalendarUserList;
  }
  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }
  public void setCalendarYear(int calendarYear) {
    this.calendarYear = calendarYear;
  }
  public void setCalendarWeek(int calendarWeek) {
    this.calendarWeek = calendarWeek;
  }
  public void setCalendarUserVO(CalendarUserVO calendarUserVO) {
    this.calendarUserVO = calendarUserVO;
  }
  public void setCalendarUserList(List calendarUserList) {
    this.calendarUserList = calendarUserList;
  }
  public void setCalendarRightVO(CalendarRightVO calendarRightVO) {
    this.calendarRightVO = calendarRightVO;
  }
  public void setCalendarMonth(int calendarMonth) {
    this.calendarMonth = calendarMonth;
  }
  public void setCalendarList(List calendarList) {
    this.calendarList = calendarList;
  }
  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }
  public String getMessageAction() {
    return messageAction;
  }
  public void setMessageAction(String messageAction) {
    this.messageAction = messageAction;
  }


}
