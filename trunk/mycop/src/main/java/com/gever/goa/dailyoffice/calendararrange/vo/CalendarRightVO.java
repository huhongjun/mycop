package com.gever.goa.dailyoffice.calendararrange.vo;

import com.gever.exception.DefaultException;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: CalendarRight中的vo对象</p>
 * <p>Description: 是DO_VIEW_RIGHT和DO_ARRANGER的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class CalendarRightVO
    extends BaseVO
    implements VOInterface {
  public CalendarRightVO() {
  }

  private String user_code = ""; // USER_CODE
  private String member = ""; // MEMBER
  private String user_name = ""; // USER_NAME
  private String righttype = "";
  private static final int user_code_col = 0; // USER_CODE相对应的列数
  private static final int member_col = 1; // MEMBER相对应的列数
  private static final int user_name_col = 2; // USER_NAME相对应的列数
  private static final int righttype_col = 3;
  public String getUser_code() {
    return this.user_code;
  }

  public void setUser_code(String user_code) {
    this.user_code = user_code;
  }

  public String getMember() {
    return this.member;
  }

  public void setMember(String member) {
    this.member = member;
  }

  public String getUser_name() {
    return this.user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getRighttype() {
    return righttype;
  }

  public void setRighttype(String righttype) {
    this.righttype = righttype;
  }

  public String getValue(String name) {
    if ("user_code".equalsIgnoreCase(name) == true) {
      return this.user_code;
    }
    else if ("member".equalsIgnoreCase(name) == true) {
      return this.member;
    }
    else if ("user_name".equalsIgnoreCase(name) == true) {
      return this.user_name;
    }
    else if ("righttype".equalsIgnoreCase(name) == true) {
      return this.righttype;
    }
    else {
      return "";
    }
  }

  public void setValue(String name, String value) {
    if ("user_code".equalsIgnoreCase(name) == true) {
      this.user_code = value;
    }
    else if ("member".equalsIgnoreCase(name) == true) {
      this.member = value;
    }
    else if ("user_name".equalsIgnoreCase(name) == true) {
      this.user_name = value;
    }
    else if ("righttype".equalsIgnoreCase(name) == true) {
      this.righttype = value;
    }
    else {
      return;
    }
  }

  public String getColType(String name) {
    String colType = new String();
    if ("user_code".equalsIgnoreCase(name) == true) {
      colType = "int";
    }
    else {
      colType = "String";
    }
    return colType;
  }

  public String getTableName() {
    if (this.righttype.equals("1")) {
      return "DO_ARRANGER";
    }
    else {
      return "DO_VIEW_RIGHT";
    }
  }

  public String getPkFields() {
    return "user_code,";
  }

  public String getModifyFields() {
      return "member,";
   }

  public String getAllFields() {
     return "user_code,member,righttype,";
  }

  public void setValues(String[] values) {
    this.user_code = values[user_code_col];
    this.member = values[member_col];
    this.user_name = values[user_name_col];
    this.righttype = values[righttype_col];
  }

  public void setOtherProperty(String[] values) {
    if (! (this.member == null || "".equals(this.member) ||
       "null".equals(this.member))) {
  try {
    this.user_name = (new com.gever.sysman.api.OrganizationUtil()).
        getUserNamesByUserdIDs(this.member);
  }
  catch (DefaultException ex) {
    ex.printStackTrace();
  }
}

  }

}
