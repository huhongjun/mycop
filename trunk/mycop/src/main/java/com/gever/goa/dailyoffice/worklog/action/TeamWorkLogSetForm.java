package com.gever.goa.dailyoffice.worklog.action;

import java.util.ArrayList;

import com.gever.struts.form.BaseForm;

/**
 * Title:�Ŷ���־Form��
 * Description: �Ŷ���־Form��
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class TeamWorkLogSetForm extends BaseForm {
    private String outPutHtml = ""; //��Ҫ�Ǵ洢�����������־��ȫ����־�Ĳ�ѯ��html
    private String listOutPutHtml = "";//�Ŷ����ú��Ŷ���־�б�ҳ���html
    private String searchBeginTime=""; //��ѯ��ʼʱ��
    private String searchEndTime=""; //��ѯ����ʱ��
    private String searchUserName="";
    private String searchUserCode="";
    private String searchWriteLogDate="";//��ѯ��־��д����
    private String teamtype=""; //�Ŷ�����
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
