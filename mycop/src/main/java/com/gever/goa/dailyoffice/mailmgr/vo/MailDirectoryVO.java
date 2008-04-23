package com.gever.goa.dailyoffice.mailmgr.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: MailDirectory中的vo对象</p>
 * <p>Description: 是MailDirectory的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class MailDirectoryVO extends BaseVO implements VOInterface {
    public MailDirectoryVO(){
    }
    private String serial_num = ""; // SERIAL_NUM
    private String user_code = ""; // USER_CODE
    private String mail_dir_name = ""; // MAIL_DIR_NAME
    private String flag = ""; // FLAG
    private String memo = ""; // MEMO
    private String totalmailsize = ""; // TOTALMAILSIZE
    private String totalmailnum = ""; // TOTALMAILNUM
    private String unreadmailnum = ""; // UNREADMAILNUM
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int user_code_col = 1; // USER_CODE相对应的列数
    private static final int mail_dir_name_col = 2; // MAIL_DIR_NAME相对应的列数
    private static final int flag_col = 3; // FLAG相对应的列数
    private static final int memo_col = 4; // MEMO相对应的列数
    private static final int totalmailsize_col = 5; // TOTALMAILSIZE相对应的列数
    private static final int totalmailnum_col = 6; // TOTALMAILNUM相对应的列数
    private static final int unreadmailnum_col = 7; // UNREADMAILNUM相对应的列数
    private String oldDirectoryName = "";//修改前名称
    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getUser_code() {
        return this.user_code;
    }
    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }
    public String getMail_dir_name() {
        return this.mail_dir_name;
    }
    public void setMail_dir_name(String mail_dir_name) {
        this.mail_dir_name = mail_dir_name;
    }
    public String getFlag() {
        return this.flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getTotalmailsize() {
        return this.totalmailsize;
    }
    public void setTotalmailsize(String totalmailsize) {
        this.totalmailsize = totalmailsize;
    }
    public String getTotalmailnum() {
        return this.totalmailnum;
    }
    public void setTotalmailnum(String totalmailnum) {
        this.totalmailnum = totalmailnum;
    }
    public String getUnreadmailnum() {
        return this.unreadmailnum;
    }
    public void setUnreadmailnum(String unreadmailnum) {
        this.unreadmailnum = unreadmailnum;
    }
    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             return  this.user_code;
        } else if ("mail_dir_name".equalsIgnoreCase(name) == true) {
             return  this.mail_dir_name;
        } else if ("flag".equalsIgnoreCase(name) == true) {
             return  this.flag;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             return  this.memo;
        } else if ("totalmailsize".equalsIgnoreCase(name) == true) {
             return  this.totalmailsize;
        } else if ("totalmailnum".equalsIgnoreCase(name) == true) {
             return  this.totalmailnum;
        } else if ("unreadmailnum".equalsIgnoreCase(name) == true) {
             return  this.unreadmailnum;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             this.user_code = value ;
        } else if ("mail_dir_name".equalsIgnoreCase(name) == true) {
             this.mail_dir_name = value ;
        } else if ("flag".equalsIgnoreCase(name) == true) {
             this.flag = value ;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             this.memo = value ;
        } else if ("totalmailsize".equalsIgnoreCase(name) == true) {
             this.totalmailsize = value ;
        } else if ("totalmailnum".equalsIgnoreCase(name) == true) {
             this.totalmailnum = value ;
        } else if ("unreadmailnum".equalsIgnoreCase(name) == true) {
             this.unreadmailnum = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("totalmailsize".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else if ("totalmailnum".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("unreadmailnum".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "MailDirectory";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "user_code,mail_dir_name,flag,memo,";
    }
    public String getAllFields() {
        return "serial_num,user_code,mail_dir_name,flag,memo,totalmailsize,totalmailnum,unreadmailnum,";
    }
    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.user_code = values[user_code_col];
        this.mail_dir_name = values[mail_dir_name_col];
        this.flag = values[flag_col];
        this.memo = values[memo_col];
        this.totalmailsize = values[totalmailsize_col];
        this.totalmailnum = values[totalmailnum_col];
        this.unreadmailnum = values[unreadmailnum_col];
    }
    public void setOtherProperty(String[] values) {
        this.setOldDirectoryName(this.getMail_dir_name());
    }
    public String getOldDirectoryName() {
        return oldDirectoryName;
    }
    public void setOldDirectoryName(String oldDirectoryName) {
        this.oldDirectoryName = oldDirectoryName;
    }
}
