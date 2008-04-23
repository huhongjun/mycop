package com.gever.goa.dailyoffice.worklog.action;

import com.gever.struts.form.BaseForm;

/**
 * Title:����Ŀ��Form��
 * Description: ����Ŀ��Form��
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class PersonMonthTargetForm extends BaseForm {
    private String username; //�û�������
    private String cellcontent; //��������
    private String cellname; //��ʱ����·����
    private String searchYear; //��ѯ��
    private String searchMonth; //��ѯ��
    private String searchUserName; //��ѯ�û�����
    private String deptnodeid; //������ȡ�����Ǳߴ������Ĳ���ID
    private String deptnodename; //������ȡ�����Ǳߴ������Ĳ���ID��Ӧ�Ĳ���������
    private String deptname; //������ʾ����������
    private String postname; //������ʾ��λ������
    private String creatorFlag;
    private String isCancel ;  //������ʶȡ�����
    private String auditFlag;
    private String auditorid;
    private String auditorname;
    private String viewFlag;


    //�����ж��Ƿ��Ǵ�����-������ӵ���޸ĺ�ɾ��Ȩ��
    private String reportTemplateName; //������ȡ�ӱ���ģ����ȡ����ģ��·��
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
