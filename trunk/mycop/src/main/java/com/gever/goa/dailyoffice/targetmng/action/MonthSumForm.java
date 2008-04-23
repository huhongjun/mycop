package com.gever.goa.dailyoffice.targetmng.action;

import com.gever.struts.form.BaseForm;

/**
 * Title:月度总结Form类
 * Description: 月度总结Form类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class MonthSumForm extends BaseForm {
    private String username; //用户中文名
    private String cellcontent; //报表内容
    private String cellname; //临时报表路径名
    private String searchYear; //查询年
    private String searchMonth; //查询月
    private String auditorname; //用来存取审核人中文名
    private String auditorid; //用来存取审核人ID
    private String checkername; //用来存取审批人中文名
    private String checkerid; //用来存取审批人ID
    private String auditFlag; //用来设置是否有审核权限
    private String checkFlag; //用来设置是否有审批权限
    private String editFlag; //用来判断是否有修改权限
    private String delFlag; //用来判断是否有删除权限
    private String deptnodeid; //用来获取从树那边传过来的部门ID
    private String deptnodename; //用来获取从树那边传过来的部门ID对应的部门中文名
    private String deptname; //用来显示部门中文名
    private String unitname; //用来获取单位名称以便显示在修改和查看页面上
    private String creatorFlag; //用来判断当前人是否是创建人，创建人才有修改权限
    private String isReadOnlyTemplateFlag; //用来判断是否当前人可以修改模板内容
    private String reportTemplateName; //用来获取从报表模板表获取过来模板路径
    private String modifyFlag;  //用来标志是否显示修改按扭
    private String isCancel;  //用来标志取消审核和取消审批
    private String isSendMsg;
    public MonthSumForm() {
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

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchMonth(String searchMonth) {
        this.searchMonth = searchMonth;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getAuditFlag() {
        return auditFlag;
    }

    public String getAuditorid() {
        return auditorid;
    }

    public String getAuditorname() {
        return auditorname;
    }

    public String getCheckerid() {
        return checkerid;
    }

    public String getCheckername() {
        return checkername;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getDeptname() {
        return deptname;
    }

    public String getDeptnodeid() {
        return deptnodeid;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }

    public void setAuditorid(String auditorid) {
        this.auditorid = auditorid;
    }

    public void setAuditorname(String auditorname) {
        this.auditorname = auditorname;
    }

    public void setCheckerid(String checkerid) {
        this.checkerid = checkerid;
    }

    public void setCheckername(String checkername) {
        this.checkername = checkername;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public void setDeptnodeid(String deptnodeid) {
        this.deptnodeid = deptnodeid;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getCreatorFlag() {
        return creatorFlag;
    }

    public void setCreatorFlag(String creatorFlag) {
        this.creatorFlag = creatorFlag;
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

    public String getIsReadOnlyTemplateFlag() {
        return isReadOnlyTemplateFlag;
    }

    public void setIsReadOnlyTemplateFlag(String isReadOnlyTemplateFlag) {
        this.isReadOnlyTemplateFlag = isReadOnlyTemplateFlag;
    }
    public String getModifyFlag() {
        return modifyFlag;
    }
    public void setModifyFlag(String modifyFlag) {
        this.modifyFlag = modifyFlag;
    }
    public String getIsCancel() {
        return isCancel;
    }
    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }
  public String getIsSendMsg() {
    return isSendMsg;
  }
  public void setIsSendMsg(String isSendMsg) {
    this.isSendMsg = isSendMsg;
  }
}
