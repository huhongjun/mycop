package com.gever.goa.dailyoffice.impowermgr.vo;

import com.gever.exception.DefaultException;
import com.gever.util.Codes;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: DoImpower中的vo对象</p>
 * <p>Description: 是DO_IMPOWER的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class DoImpowerVO
    extends BaseVO
    implements VOInterface {
  public DoImpowerVO() {
  }

  private String serial_num = ""; // SERIAL_NUM
  private String create_time = ""; // CREATE_TIME
  private String accepter = ""; // ACCEPTER
  private String payer = ""; // PAYER
  private String begin_time = ""; // BEGIN_TIME
  private String end_time = ""; // END_TIME
  private String comments = ""; // COMMENTS
  private String notice = ""; // NOTICE
  private byte[] content; // CONTENT
  private String send_flag = "0"; // SEND_FLAG
  private String acceptername = "";
  private String payername = "";
  private String noticename = "";
  private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
  private static final int create_time_col = 1; // CREATE_TIME相对应的列数
  private static final int accepter_col = 2; // ACCEPTER相对应的列数
  private static final int payer_col = 3; // PAYER相对应的列数
  private static final int begin_time_col = 4; // BEGIN_TIME相对应的列数
  private static final int end_time_col = 5; // END_TIME相对应的列数
  private static final int comments_col = 6; // COMMENT相对应的列数
  private static final int notice_col = 7; // NOTICE相对应的列数
  private static final int content_col = 8; // CONTENT相对应的列数
  private static final int send_flag_col = 9; // SEND_FLAG相对应的列数
  private static final int acceptername_col = 10; // SEND_FLAG相对应的列数
  private static final int payername_col = 11; // SEND_FLAG相对应的列数
  private static final int noticename_col = 12; // SEND_FLAG相对应的列数
  public String getSerial_num() {
    return this.serial_num;
  }

  public void setSerial_num(String serial_num) {
    this.serial_num = serial_num;
  }

  public String getCreate_time() {
    return this.create_time;
  }

  public void setCreate_time(String create_time) {
    this.create_time = create_time;
  }

  public String getAccepter() {
    return this.accepter;
  }

  public void setAccepter(String accepter) {
    this.accepter = accepter;
  }

  public String getPayer() {
    return this.payer;
  }

  public void setPayer(String payer) {
    this.payer = payer;
  }

  public String getBegin_time() {
    return this.begin_time;
  }

  public void setBegin_time(String begin_time) {
    this.begin_time = begin_time;
  }

  public String getEnd_time() {
    return this.end_time;
  }

  public void setEnd_time(String end_time) {
    this.end_time = end_time;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getNotice() {
    return this.notice;
  }

  public void setNotice(String notice) {
    this.notice = notice;
  }

  public byte[] getContent() {
    return this.content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public String getSend_flag() {
    return this.send_flag;
  }

  public void setSend_flag(String send_flag) {
    this.send_flag = send_flag;
  }

  public String getValue(String name) {
    if ("serial_num".equalsIgnoreCase(name) == true) {
      return this.serial_num;
    }
    else if ("create_time".equalsIgnoreCase(name) == true) {
      return this.create_time;
    }
    else if ("accepter".equalsIgnoreCase(name) == true) {
      return this.accepter;
    }
    else if ("payer".equalsIgnoreCase(name) == true) {
      return this.payer;
    }
    else if ("begin_time".equalsIgnoreCase(name) == true) {
      return this.begin_time;
    }
    else if ("end_time".equalsIgnoreCase(name) == true) {
      return this.end_time;
    }
    else if ("comments".equalsIgnoreCase(name) == true) {
      return this.comments;
    }
    else if ("notice".equalsIgnoreCase(name) == true) {
      return this.notice;
    }
    else if ("acceptername".equalsIgnoreCase(name) == true) {
      return this.acceptername;
    }

    else if ("noticename".equalsIgnoreCase(name) == true) {
      return this.noticename;
    }
    else if ("payername".equalsIgnoreCase(name) == true) {
      return this.payername;
    }
    else if ("content".equalsIgnoreCase(name) == true) {
      return "";
      //return  this.content;
    }
    else if ("send_flag".equalsIgnoreCase(name) == true) {
      return this.send_flag;
    }
    else {
      return "";
    }
  }

  public void setValue(String name, String value) {
    if ("serial_num".equalsIgnoreCase(name) == true) {
      this.serial_num = value;
    }
    else if ("create_time".equalsIgnoreCase(name) == true) {
      this.create_time = value;
    }
    else if ("accepter".equalsIgnoreCase(name) == true) {
      this.accepter = value;
    }
    else if ("payer".equalsIgnoreCase(name) == true) {
      this.payer = value;
    }
    else if ("begin_time".equalsIgnoreCase(name) == true) {
      this.begin_time = value;
    }
    else if ("end_time".equalsIgnoreCase(name) == true) {
      this.end_time = value;
    }
    else if ("comments".equalsIgnoreCase(name) == true) {
      this.comments = value;
    }
    else if ("notice".equalsIgnoreCase(name) == true) {
      this.notice = value;
    }
    else if ("acceptername".equalsIgnoreCase(name) == true) {
      this.acceptername = value;
    }
    else if ("noticename".equalsIgnoreCase(name) == true) {
      this.noticename = value;
    }
    else if ("payername".equalsIgnoreCase(name) == true) {
      this.payername = value;
    }

    else if ("content".equalsIgnoreCase(name) == true) {
      this.content = Codes.decode(value.toCharArray());
    }
    else if ("send_flag".equalsIgnoreCase(name) == true) {
      this.send_flag = value;
    }
    else {
      return;
    }
  }

  public String getColType(String name) {
    String colType = new String();
    if ("create_time".equalsIgnoreCase(name) == true) {
      colType = "timestamp";
    }
    else if ("begin_time".equalsIgnoreCase(name) == true) {
      colType = "timestamp";
    }
    else if ("end_time".equalsIgnoreCase(name) == true) {
      colType = "timestamp";
    }
    else if ("content".equalsIgnoreCase(name) == true) {
      colType = "bytes";
    }
    else if ("send_flag".equalsIgnoreCase(name) == true) {
      colType = "int";
    }
    else {
      colType = "String";
    }
    return colType;
  }

  public String getTableName() {
    return "DO_IMPOWER";
  }

  public String getPkFields() {
    return "serial_num,";
  }

  public String getModifyFields() {
    return "create_time,accepter,payer,begin_time,end_time,comments,notice,send_flag,content,";
  }

  public String getAllFields() {
    return "serial_num,create_time,accepter,payer,begin_time,end_time,comments,notice,send_flag,acceptername,payername,noticename,content,";
  }

  public void setValues(String[] values) {
    this.serial_num = values[serial_num_col];
    this.create_time = values[create_time_col];
    this.accepter = values[accepter_col];
    this.payer = values[payer_col];
    this.begin_time = values[begin_time_col];
    this.end_time = values[end_time_col];
    this.comments = values[comments_col];
    this.notice = values[notice_col];
    this.content = Codes.decode(values[content_col].toCharArray());
    this.send_flag = values[send_flag_col];
    this.acceptername = values[acceptername_col];
    this.payername = values[payername_col];
    this.noticename = values[noticename_col];
  }

  public void setOtherProperty(String[] values) {
    if (! (this.create_time == null || "".equals(this.create_time) ||
           "null".equals(this.create_time))) {
      if (this.create_time.length() > 19) {
        this.create_time = this.create_time.substring(0, 19);
      }
    }

    if (! (this.begin_time == null || "".equals(this.begin_time) ||
           "null".equals(this.begin_time))) {
      if (this.begin_time.length() > 16) {
        this.begin_time = this.begin_time.substring(0, 16);
      }
    }

    if (! (this.end_time == null || "".equals(this.end_time) ||
           "null".equals(this.end_time))) {
      if (this.end_time.length() > 16) {
        this.end_time = this.end_time.substring(0, 16);
      }
    }

    if (! (this.notice == null || "".equals(this.notice) ||
           "null".equals(this.notice))) {
      try {
        this.noticename = (new com.gever.sysman.api.OrganizationUtil()).
            getUserNamesByUserdIDs(this.notice);
      }
      catch (DefaultException ex) {
        ex.printStackTrace();
      }
    }

  }
  public String getAcceptername() {
    return acceptername;
  }
  public void setAcceptername(String acceptername) {
    this.acceptername = acceptername;
  }
  public String getNoticename() {
    return noticename;
  }
  public void setNoticename(String noticename) {
    this.noticename = noticename;
  }
  public String getPayername() {
    return payername;
  }
  public void setPayername(String payername) {
    this.payername = payername;
  }

}
