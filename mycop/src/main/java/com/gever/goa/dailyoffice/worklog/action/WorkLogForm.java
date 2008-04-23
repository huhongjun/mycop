package com.gever.goa.dailyoffice.worklog.action;

import java.util.ArrayList;
import java.util.List;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title:������־Form��</p>
 * <p>Description: ������־Form��</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class WorkLogForm extends BaseForm {
    private String username; //�û�������
    private String deptnames; //��ǰ�û����ڲ���������
    private String cellcontent; //��������
    private String cellname; //��ʱ����·����
    private String searchBeginTime; //��ѯ��ʼʱ��
    private String searchEndTime; //��ѯ����ʱ��
    private String isInsertFlag = "";

    //�ж��Ƿ���������ı�־-�������־û��д�������
    //�ж��Ƿ��Ѿ�д���������־--1--��д��--0--��û��д
    private String isTodayWorkLogFlag = "";

    //�����ж��Ƿ��ǽ������־-�鿴ʱ�����޸İ�ť��
    //��Ϊ��־�б�����ʾ�����ǵ�ǰ��һ���˵���־�����Բ������ж��Ƿ�ʱ��ǰ��д����־
    //����������־��ѯ�п���ֱ�Ӳ鿴�û���־�����Ի�����Ҫ���뵱ǰ�˵��ж�����
    private String reportTemplateName; //������ȡ�ӱ���ģ����ȡ����ģ��·��
    private String logTotalStr; //������ȡ��־���ַ���
    private String logGrjb; //������ȡ��־�еĸ��˽���
    private List listLog = new ArrayList();
    private List adviceList = new ArrayList();//��־�����б�
    private String adviceWords;//��־������Ϣ
    private String editAdviceFlag = "0";//�Ƿ�༭��־��Ϣ,0Ϊ���༭��1Ϊ�༭

    public WorkLogForm() {
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

    public String getSearchBeginTime() {
        return searchBeginTime;
    }

    public String getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchBeginTime(String searchBeginTime) {
        this.searchBeginTime = searchBeginTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public String getIsInsertFlag() {
        return isInsertFlag;
    }

    public void setIsInsertFlag(String isInsertFlag) {
        this.isInsertFlag = isInsertFlag;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptnames() {
        return deptnames;
    }

    public void setDeptnames(String deptnames) {
        this.deptnames = deptnames;
    }

    public List getListLog() {
        return listLog;
    }

    public void setListLog(List listLog) {
        this.listLog = listLog;
    }

    public String getIsTodayWorkLogFlag() {
        return isTodayWorkLogFlag;
    }

    public void setIsTodayWorkLogFlag(String isTodayWorkLogFlag) {
        this.isTodayWorkLogFlag = isTodayWorkLogFlag;
    }

    public String getReportTemplateName() {
        return reportTemplateName;
    }

    public void setReportTemplateName(String reportTemplateName) {
        this.reportTemplateName = reportTemplateName;
    }

    public String getLogTotalStr() {
        return logTotalStr;
    }

    public void setLogTotalStr(String logTotalStr) {
        this.logTotalStr = logTotalStr;
    }

    public String getLogGrjb() {
        return logGrjb;
    }

    public void setLogGrjb(String logGrjb) {
        this.logGrjb = logGrjb;
    }
    public List getAdviceList() {
        return adviceList;
    }
    public void setAdviceList(List adviceList) {
        this.adviceList = adviceList;
    }
    public String getAdviceWords() {
        return adviceWords;
    }
    public void setAdviceWords(String adviceWords) {
        this.adviceWords = adviceWords;
    }
    public String getEditAdviceFlag() {
        return editAdviceFlag;
    }
    public void setEditAdviceFlag(String editAdviceFlag) {
        this.editAdviceFlag = editAdviceFlag;
    }
}
