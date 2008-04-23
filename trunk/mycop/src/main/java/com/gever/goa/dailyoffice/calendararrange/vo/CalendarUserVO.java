package com.gever.goa.dailyoffice.calendararrange.vo;

import java.io.Serializable;

/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class CalendarUserVO
    implements Serializable {
  public CalendarUserVO() {
  }

  private String userCode="";
  private String userName = "";
  private String rightType = "";

  public String getRightType() {
    return rightType;
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public void setRightType(String rightType) {
    this.rightType = rightType;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}
