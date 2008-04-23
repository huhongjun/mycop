package com.gever.goa.dailyoffice.reportmgr.action;

import com.gever.struts.form.BaseForm;

/**
 *
 * <p>Title:目标管理ActionForm </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class TargetForm extends BaseForm {
    private String username; //用户中文名
    private String deptname; //用来显示部门中文名
    private String cellcontent; //报表内容
    private String cellname; //临时报表路径名
    private String searchYear; //查询年
    private String searchMonth;//查询月
    private String searchWeek; //周查询
    private String auditorname; //用来存取审核人中文名
    private String auditorid; //用来存取审核人ID
    private String checkername; //用来存取审批人中文名
    private String checkerid; //用来存取审批人ID
    private String auditFlag; //用来设置是否有审核权限
    private String checkFlag; //用来设置是否有审批权限
    private String editFlag; //用来判断是否有修改权限
    private String delFlag; //用来判断是否有删除权限
    private String unitname; //用来获取单位名称以便显示在修改和查看页面上
    private String creatorFlag; //用来判断当前人是否是创建人，创建人才有修改权限
    private String isReadOnlyTemplateFlag; //用来判断是否当前人可以修改模板内容
    private String reportTemplateName; //用来获取从报表模板表获取过来模板路径
    private String modifyFlag; //用来标志是否显示修改按扭
    private String isCancel; //用来标志取消审核和取消审批
    private String targetTypeName; //目标类型名称
    private String targetType;     //目标类型
    private String typeFlag;       //类型标志
    private String typeFlagName;   //类型标志名字
    private String saveExitFlag;   // 保存并退出标志

    public TargetForm() {
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

    public String getCellcontent() {
        return cellcontent;
    }

    public String getCellname() {
        return cellname;
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

    public String getCreatorFlag() {
        return creatorFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getDeptname() {
        return deptname;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public String getIsReadOnlyTemplateFlag() {
        return isReadOnlyTemplateFlag;
    }

    public String getModifyFlag() {
        return modifyFlag;
    }

    public String getReportTemplateName() {
        return reportTemplateName;
    }

    public String getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public void setReportTemplateName(String reportTemplateName) {
        this.reportTemplateName = reportTemplateName;
    }

    public String getUnitname() {
        return unitname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public void setModifyFlag(String modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    public void setIsReadOnlyTemplateFlag(String isReadOnlyTemplateFlag) {
        this.isReadOnlyTemplateFlag = isReadOnlyTemplateFlag;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setCreatorFlag(String creatorFlag) {
        this.creatorFlag = creatorFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public void setCheckername(String checkername) {
        this.checkername = checkername;
    }

    public void setCheckerid(String checkerid) {
        this.checkerid = checkerid;
    }

    public void setCellname(String cellname) {
        this.cellname = cellname;
    }

    public void setCellcontent(String cellcontent) {
        this.cellcontent = cellcontent;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public void setAuditorname(String auditorname) {
        this.auditorname = auditorname;
    }

    public void setAuditorid(String auditorid) {
        this.auditorid = auditorid;
    }

    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }
    public String getTargetTypeName() {
        return targetTypeName;
    }
    public void setTargetTypeName(String targetType) {
        this.targetTypeName = targetType;
    }
    public String getTargetType() {
        return targetType;
    }
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
    public String getSearchWeek() {
        return searchWeek;
    }
    public String getSearchMonth() {
        return searchMonth;
    }
    public void setSearchWeek(String searchWeek) {
        this.searchWeek = searchWeek;
    }
    public void setSearchMonth(String searchMonth) {
        this.searchMonth = searchMonth;
    }
    public String getTypeFlag() {
        return typeFlag;
    }
    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }
    public String getSaveExitFlag() {
        return saveExitFlag;
    }
    public void setSaveExitFlag(String saveExitFlag) {
        this.saveExitFlag = saveExitFlag;
    }
    public String getTypeFlagName() {
        return typeFlagName;
    }
    public void setTypeFlagName(String typeFlagName) {
        this.typeFlagName = typeFlagName;
    }

}

