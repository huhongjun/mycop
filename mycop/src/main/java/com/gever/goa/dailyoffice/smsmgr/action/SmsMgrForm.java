package com.gever.goa.dailyoffice.smsmgr.action;

import com.gever.goa.dailyoffice.smsmgr.vo.SMSCapacityVO;
import com.gever.struts.form.BaseForm;

/**
 * <p>Title:手机短信 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsMgrForm extends BaseForm {
    private String keyMobile; //手动key的手机号码
    private String selectMobile; //手动key的手机号码
    private String msg; //手动key的短信息内容
    private SMSCapacityVO capVO = new SMSCapacityVO();
    private String sendYear = new String(""); //定时发送时所需:年
    private String sendMonth = new String(""); //定时发送时所需:月
    private String sendDay = new String(""); //定时发送时所需:日
    private String sendHour = new String(""); //定时发送时所需:小时
    private String sendMinute = new String(""); //定时发送时所需:分钟
    private String startDate = new String("");  //查询开始日期
    private String endDate = new String("");   //查询迄止日期

    private String searchUser = new String("");  //培训人员
    private String searchMsg = new String("");  //短信内容
    private String type = new String("0");   //查寻类别
    private String isSendBox = new String("true");   //查寻类别
    private String sumCount = new String("0");   //总共发送数量
    private String whereCount = new String("0");   //今天已发送数量
    private String otherCount = new String("0");  //剩下数量

    public SmsMgrForm() {
    }
    public String getEndDate() {
        return endDate;
    }
    public String getIsSendBox() {
        return isSendBox;
    }
    public String getKeyMobile() {
        return keyMobile;
    }
    public String getMsg() {
        return msg;
    }
    public String getOtherCount() {
        return otherCount;
    }
    public String getSearchMsg() {
        return searchMsg;
    }
    public String getSearchUser() {
        return searchUser;
    }
    public String getSelectMobile() {
        return selectMobile;
    }
    public String getSendDay() {
        return sendDay;
    }
    public String getSendHour() {
        return sendHour;
    }
    public String getSendMinute() {
        return sendMinute;
    }
    public String getSendMonth() {
        return sendMonth;
    }
    public String getSendYear() {
        return sendYear;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getSumCount() {
        return sumCount;
    }
    public String getType() {
        return type;
    }
    public String getWhereCount() {
        return whereCount;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setIsSendBox(String isSendBox) {
        this.isSendBox = isSendBox;
    }
    public void setKeyMobile(String keyMobile) {
        this.keyMobile = keyMobile;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setOtherCount(String otherCount) {
        this.otherCount = otherCount;
    }
    public void setSearchMsg(String searchMsg) {
        this.searchMsg = searchMsg;
    }
    public void setSearchUser(String searchUser) {
        this.searchUser = searchUser;
    }
    public void setSelectMobile(String selectMobile) {
        this.selectMobile = selectMobile;
    }
    public void setSendDay(String sendDay) {
        this.sendDay = sendDay;
    }
    public void setSendHour(String sendHour) {
        this.sendHour = sendHour;
    }
    public void setSendMinute(String sendMinute) {
        this.sendMinute = sendMinute;
    }
    public void setSendMonth(String sendMonth) {
        this.sendMonth = sendMonth;
    }
    public void setSendYear(String sendYear) {
        this.sendYear = sendYear;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setSumCount(String sumCount) {
        this.sumCount = sumCount;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setWhereCount(String whereCount) {
        this.whereCount = whereCount;
    }
    public SMSCapacityVO getCapVO() {
        return capVO;
    }
    public void setCapVO(SMSCapacityVO capVO) {
        this.capVO = capVO;
    }

}