package com.gever.goa.dailyoffice.smsmgr.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsInBoxForm extends BaseForm{
    private String startDate = new String("");  //��ѯ��ʼ����
    private String endDate = new String("");   //��ѯ��ֹ����
    private String searchUser = new String("");  //��ѵ��Ա
    private String searchMsg = new String("");  //��������
    public SmsInBoxForm() {
    }
    public String getEndDate() {
        return endDate;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getSearchMsg() {
        return searchMsg;
    }
    public String getSearchUser() {
        return searchUser;
    }
    public void setSearchUser(String searchUser) {
        this.searchUser = searchUser;
    }
    public void setSearchMsg(String searchMsg) {
        this.searchMsg = searchMsg;
    }

}