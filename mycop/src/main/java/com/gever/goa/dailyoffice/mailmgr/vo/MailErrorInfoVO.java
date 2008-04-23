package com.gever.goa.dailyoffice.mailmgr.vo;

/**
 * <p>Title: MailErrorInfoVO�ʼ�����ѶϢ��ʾ</p>
 * <p>Description:�ʼ�����ѶϢ��ʾ </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailErrorInfoVO {
    private String errorCode = ""; //����code
    private String errorMsg = "";  //������Ϣ
    private String userName = "";  //�ü�����
    private String memo = "";      //��ע
    private int id = 0;            //���к�
    private String title = "";     //�ʼ�����

    public MailErrorInfoVO() {
    }
    public String getErrorCode() {
        return errorCode;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public String getMemo() {
        return memo;
    }
    public int getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}