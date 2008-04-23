package com.gever.web.homepage.form;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class UserMenuForm extends ActionForm{

    private String userID = "";
    private String userName = "";
    private int screenXY = 1024;
    private int deskPicWidth = 750;   //桌面图片宽度
    private int deskPicHeight = 480;  //桌面图片高度
    private String picPath = new String("images/deskpic_1.jpg");
    private String isAdmin =new String( "false");

    public UserMenuForm() {
    }
    public String getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public int getDeskPicHeight() {
        return deskPicHeight;
    }
    public int getDeskPicWidth() {
        return deskPicWidth;
    }
    public String getIsAdmin() {
        return isAdmin;
    }
    public String getPicPath() {
        return picPath;
    }
    public int getScreenXY() {
        return screenXY;
    }
    public void setScreenXY(int screenXY) {
        this.screenXY = screenXY;
    }
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }
    public void setDeskPicWidth(int deskPicWidth) {
        this.deskPicWidth = deskPicWidth;
    }
    public void setDeskPicHeight(int deskPicHeight) {
        this.deskPicHeight = deskPicHeight;
    }

}
