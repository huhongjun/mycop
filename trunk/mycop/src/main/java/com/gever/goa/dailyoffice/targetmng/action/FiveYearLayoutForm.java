package com.gever.goa.dailyoffice.targetmng.action;

import com.gever.struts.form.BaseForm;

/**
 * Title:五年规划Form类
 * Description: 五年规划Form类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */
public class FiveYearLayoutForm extends BaseForm {
    private String cellcontent; //报表内容
    private String cellname; //临时报表路径名
    private String searchYear; //查询年
    private String username; //用户中文名
    private String approveid; //批准人ID
    private String approvename; //批准人中文名
    private String approveFlag; //用来设置是否有批准权限
    private String editFlag; //用来判断是否有修改权限
    private String delFlag; //用来判断是否有删除权限
    private String unitname; //用来获取单位名称以便显示在修改和查看页面上
    private String creatorFlag; //用来判断当前人是否是创建人，创建人才有修改权限
    private String isReadOnlyTemplateFlag; //用来判断是否当前人可以修改模板内容
    private String reportTemplateName; //用来获取从报表模板表获取过来模板路径
    private String modifyFlag;  //用来标志是否显示修改按扭
    private String isCancel;  //用来标志取消审核和取消审批
    private String isSendMsg;
    public FiveYearLayoutForm() {
    }

    public String getCellcontent() {
        return cellcontent;
    }

    public String getCellname() {
        return cellname;
    }

    public String getSearchYear() {
        return searchYear;
    }

    public void setCellcontent(String cellcontent) {
        this.cellcontent = cellcontent;
    }

    public void setCellname(String cellname) {
        this.cellname = cellname;
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getApproveid() {
        return approveid;
    }

    public String getApprovename() {
        return approvename;
    }

    public void setApproveid(String approveid) {
        this.approveid = approveid;
    }

    public void setApprovename(String approvename) {
        this.approvename = approvename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
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
