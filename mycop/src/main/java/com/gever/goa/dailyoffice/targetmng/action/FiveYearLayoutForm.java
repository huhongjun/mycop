package com.gever.goa.dailyoffice.targetmng.action;

import com.gever.struts.form.BaseForm;

/**
 * Title:����滮Form��
 * Description: ����滮Form��
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class FiveYearLayoutForm extends BaseForm {
    private String cellcontent; //��������
    private String cellname; //��ʱ����·����
    private String searchYear; //��ѯ��
    private String username; //�û�������
    private String approveid; //��׼��ID
    private String approvename; //��׼��������
    private String approveFlag; //���������Ƿ�����׼Ȩ��
    private String editFlag; //�����ж��Ƿ����޸�Ȩ��
    private String delFlag; //�����ж��Ƿ���ɾ��Ȩ��
    private String unitname; //������ȡ��λ�����Ա���ʾ���޸ĺͲ鿴ҳ����
    private String creatorFlag; //�����жϵ�ǰ���Ƿ��Ǵ����ˣ������˲����޸�Ȩ��
    private String isReadOnlyTemplateFlag; //�����ж��Ƿ�ǰ�˿����޸�ģ������
    private String reportTemplateName; //������ȡ�ӱ���ģ����ȡ����ģ��·��
    private String modifyFlag;  //������־�Ƿ���ʾ�޸İ�Ť
    private String isCancel;  //������־ȡ����˺�ȡ������
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
