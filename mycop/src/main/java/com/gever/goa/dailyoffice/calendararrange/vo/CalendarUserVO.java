package com.gever.goa.dailyoffice.calendararrange.vo;

import java.io.Serializable;

/**
 * <p>Title: ���а칫�Զ���ƽ̨</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
