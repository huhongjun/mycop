package com.gever.goa.dailyoffice.calendararrange.vo;

import com.gever.exception.DefaultException;

/**
 * <p>Title: 天行办公自动化平台</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public class ArrangeCalendarRightVO
    extends CalendarRightVO {
  public ArrangeCalendarRightVO() {
  }

  private String righttype = "1";
  private String arrange = ""; // MEMBER
  private static final int user_code_col = 0; // USER_CODE相对应的列数
  private static final int arrange_col = 1; // arrange相对应的列数
  private static final int user_name_col = 2; // USER_NAME相对应的列数
  private static final int righttype_col = 3;

  public String getValue(String name) {
    if ("user_code".equalsIgnoreCase(name) == true) {
      return this.getUser_code();
    }
    else if ("arrange".equalsIgnoreCase(name) == true) {
      return this.arrange;
    }
    else if ("user_name".equalsIgnoreCase(name) == true) {
      return this.getUser_name();
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
      this.setUser_code(value);
    }
    else if ("arrange".equalsIgnoreCase(name) == true) {
      this.setArrange(value);
    }
    else if ("user_name".equalsIgnoreCase(name) == true) {
      this.setUser_name(value);
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
    return "DO_ARRANGER";
  }

  public String getPkFields() {
    return "user_code,";
  }

  public String getModifyFields() {
    return "arrange,";

  }

  public String getAllFields() {
    return "user_code,arrange,righttype,";

  }

  public void setValues(String[] values) {
    this.setUser_code(values[user_code_col]);
    this.arrange = values[arrange_col];
    this.setUser_name(values[user_name_col]);
    this.setRighttype(values[righttype_col]);
  }

  public void setOtherProperty(String[] values) {
    if (! (this.arrange == null || "".equals(this.arrange) ||
           "null".equals(this.arrange))) {
      try {
        this.setUser_name( (new com.gever.sysman.api.OrganizationUtil()).
                          getUserNamesByUserdIDs(this.arrange));
      }
      catch (DefaultException ex) {
        ex.printStackTrace();
      }
    }

  }

  public String getArrange() {
    return arrange;
  }

  public void setArrange(String arrange) {
    this.arrange = arrange;
  }

}
