package com.gever.goa.dailyoffice.targetmng.action;

import com.gever.struts.form.BaseForm;

/**
 * Title:�¶��ܽ�Form��
 * Description: �¶��ܽ�Form��
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class MonthSumForm extends BaseForm {
    private String username; //�û�������
    private String cellcontent; //��������
    private String cellname; //��ʱ����·����
    private String searchYear; //��ѯ��
    private String searchMonth; //��ѯ��
    private String auditorname; //������ȡ�����������
    private String auditorid; //������ȡ�����ID
    private String checkername; //������ȡ������������
    private String checkerid; //������ȡ������ID
    private String auditFlag; //���������Ƿ������Ȩ��
    private String checkFlag; //���������Ƿ�������Ȩ��
    private String editFlag; //�����ж��Ƿ����޸�Ȩ��
    private String delFlag; //�����ж��Ƿ���ɾ��Ȩ��
    private String deptnodeid; //������ȡ�����Ǳߴ������Ĳ���ID
    private String deptnodename; //������ȡ�����Ǳߴ������Ĳ���ID��Ӧ�Ĳ���������
    private String deptname; //������ʾ����������
    private String unitname; //������ȡ��λ�����Ա���ʾ���޸ĺͲ鿴ҳ����
    private String creatorFlag; //�����жϵ�ǰ���Ƿ��Ǵ����ˣ������˲����޸�Ȩ��
    private String isReadOnlyTemplateFlag; //�����ж��Ƿ�ǰ�˿����޸�ģ������
    private String reportTemplateName; //������ȡ�ӱ���ģ����ȡ����ģ��·��
    private String modifyFlag;  //������־�Ƿ���ʾ�޸İ�Ť
    private String isCancel;  //������־ȡ����˺�ȡ������
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
