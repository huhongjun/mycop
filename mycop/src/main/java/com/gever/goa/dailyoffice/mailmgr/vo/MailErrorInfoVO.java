package com.gever.goa.dailyoffice.mailmgr.vo;

/**
 * <p>Title: MailErrorInfoVO邮件错误讯息提示</p>
 * <p>Description:邮件错误讯息提示 </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailErrorInfoVO {
    private String errorCode = ""; //错误code
    private String errorMsg = "";  //错误信息
    private String userName = "";  //用肩名称
    private String memo = "";      //备注
    private int id = 0;            //序列号
    private String title = "";     //邮件主题

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