package com.gever.goa.dailyoffice.smsmgr.action;

import com.gever.goa.dailyoffice.smsmgr.vo.SMSCapacityVO;
import com.gever.struts.form.BaseForm;

/**
 * <p>Title:�ֻ����� </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsMgrForm extends BaseForm {
    private String keyMobile; //�ֶ�key���ֻ�����
    private String selectMobile; //�ֶ�key���ֻ�����
    private String msg; //�ֶ�key�Ķ���Ϣ����
    private SMSCapacityVO capVO = new SMSCapacityVO();
    private String sendYear = new String(""); //��ʱ����ʱ����:��
    private String sendMonth = new String(""); //��ʱ����ʱ����:��
    private String sendDay = new String(""); //��ʱ����ʱ����:��
    private String sendHour = new String(""); //��ʱ����ʱ����:Сʱ
    private String sendMinute = new String(""); //��ʱ����ʱ����:����
    private String startDate = new String("");  //��ѯ��ʼ����
    private String endDate = new String("");   //��ѯ��ֹ����

    private String searchUser = new String("");  //��ѵ��Ա
    private String searchMsg = new String("");  //��������
    private String type = new String("0");   //��Ѱ���
    private String isSendBox = new String("true");   //��Ѱ���
    private String sumCount = new String("0");   //�ܹ���������
    private String whereCount = new String("0");   //�����ѷ�������
    private String otherCount = new String("0");  //ʣ������

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