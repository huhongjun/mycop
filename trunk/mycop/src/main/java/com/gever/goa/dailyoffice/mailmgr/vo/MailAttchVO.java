package com.gever.goa.dailyoffice.mailmgr.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: MailAttch中的vo对象</p>
 * <p>Description: 是MailAttch的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class MailAttchVO extends BaseVO implements VOInterface {
    public MailAttchVO(){
    }
    private String serial_num = ""; // SERIAL_NUM
    private String mail_id = ""; // MAIL_ID
    private String attch_name = ""; // ATTCH_NAME
    private String file_path = ""; // FILE_PATH
    private String memo = ""; // MEMO
    private long fileSize = 0l;
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int mail_id_col = 1; // MAIL_ID相对应的列数
    private static final int attch_name_col = 2; // ATTCH_NAME相对应的列数
    private static final int file_path_col = 3; // FILE_PATH相对应的列数
    private static final int memo_col = 4; // MEMO相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getMail_id() {
        return this.mail_id;
    }
    public void setMail_id(String mail_id) {
        this.mail_id = mail_id;
    }
    public String getAttch_name() {
        return this.attch_name;
    }
    public void setAttch_name(String attch_name) {
        this.attch_name = attch_name;
    }
    public String getFile_path() {
        return this.file_path;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        } else if ("mail_id".equalsIgnoreCase(name) == true) {
             return  this.mail_id;
        } else if ("attch_name".equalsIgnoreCase(name) == true) {
             return  this.attch_name;
        } else if ("file_path".equalsIgnoreCase(name) == true) {
             return  this.file_path;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             return  this.memo;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        } else if ("mail_id".equalsIgnoreCase(name) == true) {
             this.mail_id = value ;
        } else if ("attch_name".equalsIgnoreCase(name) == true) {
             this.attch_name = value ;
        } else if ("file_path".equalsIgnoreCase(name) == true) {
             this.file_path = value ;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             this.memo = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();

        return  "String";
    }
    public String getTableName() {
        return "MailAttch";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "mail_id,attch_name,file_path,memo,";
    }
    public String getAllFields() {
        return "serial_num,mail_id,attch_name,file_path,memo,";
    }
    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.mail_id = values[mail_id_col];
        this.attch_name = values[attch_name_col];
        this.file_path = values[file_path_col];
        this.memo = values[memo_col];
    }
    public void setOtherProperty(String[] values) {
    }
  public long getFileSize() {
    return fileSize;
  }
  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }
}
