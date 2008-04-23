package com.gever.goa.dailyoffice.worklog.action;

import com.gever.struts.form.BaseForm;

/**
 * Title:人月目标Form类
 * Description: 人月目标Form类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class PersonMonthTargetForm extends BaseForm {
    private String username; //用户中文名
    private String cellcontent; //报表内容
    private String cellname; //临时报表路径名
    private String searchYear; //查询年
    private String searchMonth; //查询月
    private String searchUserName; //查询用户姓名
    private String deptnodeid; //用来获取从树那边传过来的部门ID
    private String deptnodename; //用来获取从树那边传过来的部门ID对应的部门中文名
    private String deptname; //用来显示部门中文名
    private String postname; //用来显示岗位中文名
    private String creatorFlag;
    private String isCancel ;  //用来标识取消审核
    private String auditFlag;
    private String auditorid;
    private String auditorname;
    private String viewFlag;


    //用来判断是否是创建人-创建人拥有修改和删除权限
    private String reportTemplateName; //用来获取从报表模板表获取过来模板路径
    public PersonMonthTargetForm() {
    }

    public String getCellcontent() {
        return cellcontent;
    }

    public String getCellname() {
        return cellname;
    }

    public void setCellcontent(String cellcontent) {
        this.cellcontent = cellcontent;
    }

    public void setCellname(String cellname) {
        this.cellname = cellname;
    }

    public String getSearchMonth() {
        return searchMonth;
    }

    public String getSearchUserName() {
        return searchUserName;
    }

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchMonth(String searchMonth) {
        this.searchMonth = searchMonth;
    }

    public void setSearchUserName(String searchUserName) {
        this.searchUserName = searchUserName;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getDeptname() {
        return deptname;
    }

    public String getDeptnodeid() {
        return deptnodeid;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public void setDeptnodeid(String deptnodeid) {
        this.deptnodeid = deptnodeid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatorFlag() {
        return creatorFlag;
    }

    public void setCreatorFlag(String creatorFlag) {
        this.creatorFlag = creatorFlag;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getReportTemplateName() {
        return reportTemplateName;
    }

    public void setReportTemplateName(String reportTemplateName) {
        this.reportTemplateName = reportTemplateName;
    }

    public String getDeptnodename() {
        return deptnodename;
    }

    public void setDeptnodename(String deptnodename) {
        this.deptnodename = deptnodename;
    }
    public String getAuditFlag() {
        return auditFlag;
    }
    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }
    public String getAuditorid() {
        return auditorid;
    }
    public void setAuditorid(String auditorid) {
        this.auditorid = auditorid;
    }
    public String getAuditorname() {
        return auditorname;
    }
    public void setAuditorname(String auditorname) {
        this.auditorname = auditorname;
    }
    public String getIsCancel() {
        return isCancel;
    }
    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }
  public String getViewFlag() {
    return viewFlag;
  }
  public void setViewFlag(String viewFlag) {
    this.viewFlag = viewFlag;
  }
}
