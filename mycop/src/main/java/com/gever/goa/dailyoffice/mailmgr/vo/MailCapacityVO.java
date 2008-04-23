package com.gever.goa.dailyoffice.mailmgr.vo;


import com.gever.util.SumUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: MailCapacity中的vo对象</p>
 * <p>Description: 是邮件表的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class MailCapacityVO extends BaseVO implements VOInterface {
    public MailCapacityVO(){
    }
    private String user_code = ""; // USER_CODE
    private String mail_capacity = ""; // MAIL_CAPACITY
    private String capacity_flag = ""; // CAPACITY_FLAG
    private String limit_flag = ""; // LIMIT_FLAG
    private String memo = ""; // MEMO
    private String usedmailsize = ""; // USEDMAILSIZE
    private String name = ""; // NAME
    private String cur_capacity="";
    private static final int user_code_col = 0; // USER_CODE相对应的列数
    private static final int mail_capacity_col = 1; // MAIL_CAPACITY相对应的列数
    private static final int capacity_flag_col = 2; // CAPACITY_FLAG相对应的列数
    private static final int limit_flag_col = 3; // LIMIT_FLAG相对应的列数
    private static final int memo_col = 4; // MEMO相对应的列数
    private static final int usedmailsize_col = 5; // USEDMAILSIZE相对应的列数
    private static final int name_col = 6; // NAME相对应的列数
    public String getUser_code() {
        return this.user_code;
    }
    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }
    public String getMail_capacity() {
        return this.mail_capacity.equals("")?  "0" :this.mail_capacity ;
    }
    public void setMail_capacity(String mail_capacity) {
        this.mail_capacity = mail_capacity;
    }
    public String getCapacity_flag() {
        return this.capacity_flag;
    }
    public void setCapacity_flag(String capacity_flag) {
        this.capacity_flag = capacity_flag;
    }
    public String getLimit_flag() {
        return this.limit_flag;
    }
    public void setLimit_flag(String limit_flag) {
        this.limit_flag = limit_flag;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getUsedmailsize() {
        return this.usedmailsize.equals("") ? "0": this.usedmailsize;
    }
    public void setUsedmailsize(String usedmailsize) {
        this.usedmailsize = usedmailsize;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue(String name) {
        if ("user_code".equalsIgnoreCase(name) == true) {
             return  this.user_code;
        } else if ("mail_capacity".equalsIgnoreCase(name) == true) {
             return  this.mail_capacity;
        } else if ("capacity_flag".equalsIgnoreCase(name) == true) {
             return  this.capacity_flag;
        } else if ("limit_flag".equalsIgnoreCase(name) == true) {
             return  this.limit_flag;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             return  this.memo;
        } else if ("usedmailsize".equalsIgnoreCase(name) == true) {
             return  this.usedmailsize;
        } else if ("name".equalsIgnoreCase(name) == true) {
             return  this.name;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("user_code".equalsIgnoreCase(name) == true) {
             this.user_code = value ;
        } else if ("mail_capacity".equalsIgnoreCase(name) == true) {
             this.mail_capacity = value ;
        } else if ("capacity_flag".equalsIgnoreCase(name) == true) {
             this.capacity_flag = value ;
        } else if ("limit_flag".equalsIgnoreCase(name) == true) {
             this.limit_flag = value ;
        } else if ("memo".equalsIgnoreCase(name) == true) {
             this.memo = value ;
        } else if ("usedmailsize".equalsIgnoreCase(name) == true) {
             this.usedmailsize = value ;
        } else if ("name".equalsIgnoreCase(name) == true) {
             this.name = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("user_code".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("mail_capacity".equalsIgnoreCase(name) == true) {
             colType = "Decimal";
        } else if ("usedmailsize".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "MailCapacity";
    }
    public String getPkFields() {
        return "user_code,";
    }
    public String getModifyFields() {
        return "mail_capacity,capacity_flag,limit_flag,memo,";
    }
    public String getAllFields() {
        return "user_code,mail_capacity,capacity_flag,limit_flag,memo,usedmailsize,name,";
    }
    public void setValues(String[] values) {
        this.user_code = values[user_code_col];
        this.mail_capacity = values[mail_capacity_col];
        this.capacity_flag = values[capacity_flag_col];
        this.limit_flag = values[limit_flag_col];
        this.memo = values[memo_col];
        this.usedmailsize = values[usedmailsize_col];
        this.name = values[name_col];
    }
    public void setOtherProperty(String[] values) {
        String usedMailSize = SumUtils.format(SumUtils.sum(this.getUsedmailsize() + "/(1024*1024)"),"0.00");
        this.setUsedmailsize(usedMailSize);
        this.setCur_capacity(String.valueOf(SumUtils.sub(this.mail_capacity,this.usedmailsize)));

    }
    public String getCur_capacity() {
        return cur_capacity;
    }
    public void setCur_capacity(String cur_capacity) {
        this.cur_capacity = cur_capacity;
    }
}
