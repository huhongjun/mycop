package com.gever.goa.dailyoffice.worklog.action;

import java.util.ArrayList;

import com.gever.struts.form.BaseForm;

/**
 * Title:团队日志Form类
 * Description: 团队日志Form类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class TeamWorkLogSetForm extends BaseForm {
    private String outPutHtml = ""; //主要是存储输出到部门日志及全体日志的查询的html
    private String listOutPutHtml = "";//团队设置后团队日志列表页面的html
    private String searchBeginTime=""; //查询开始时间
    private String searchEndTime=""; //查询结束时间
    private String searchUserName="";
    private String searchUserCode="";
    private String searchWriteLogDate="";//查询日志填写日期
    private String teamtype=""; //团队类型
    private ArrayList deptList = new ArrayList();
    private ArrayList deptUserList = new ArrayList();
    public TeamWorkLogSetForm() {
    }

    public String getOutPutHtml() {
        return outPutHtml;
    }

    public String getSearchBeginTime() {
        return searchBeginTime;
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setOutPutHtml(String outPutHtml) {
        this.outPutHtml = outPutHtml;
    }

    public void setSearchBeginTime(String searchBeginTime) {
        this.searchBeginTime = searchBeginTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public ArrayList getDeptList() {
        return deptList;
    }

    public void setDeptList(ArrayList deptList) {
        this.deptList = deptList;
    }

    public ArrayList getDeptUserList() {
        return deptUserList;
    }

    public void setDeptUserList(ArrayList deptUserList) {
        this.deptUserList = deptUserList;
    }

    public String getTeamtype() {
        return teamtype;
    }

    public void setTeamtype(String teamtype) {
        this.teamtype = teamtype;
    }
  public String getListOutPutHtml() {
    return listOutPutHtml;
  }
  public void setListOutPutHtml(String listOutPutHtml) {
    this.listOutPutHtml = listOutPutHtml;
  }
    public String getSearchWriteLogDate() {
        return searchWriteLogDate;
    }
    public void setSearchWriteLogDate(String searchWriteLogDate) {
        this.searchWriteLogDate = searchWriteLogDate;
    }
  public String getSearchUserName() {
    return searchUserName;
  }
  public void setSearchUserName(String searchUserName) {
    this.searchUserName = searchUserName;
  }
  public String getSearchUserCode() {
    return searchUserCode;
  }
  public void setSearchUserCode(String searchUserCode) {
    this.searchUserCode = searchUserCode;
  }
}
