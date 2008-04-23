package com.gever.goa.dailyoffice.smsmgr.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsInBoxForm extends BaseForm{
    private String startDate = new String("");  //查询开始日期
    private String endDate = new String("");   //查询迄止日期
    private String searchUser = new String("");  //培训人员
    private String searchMsg = new String("");  //短信内容
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