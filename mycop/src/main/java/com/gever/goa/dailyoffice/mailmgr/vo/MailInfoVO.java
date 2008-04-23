package com.gever.goa.dailyoffice.mailmgr.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: MailInfo中的vo对象</p>
 * <p>Description: 是MailInfo的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class MailInfoVO extends BaseVO implements VOInterface {
    public MailInfoVO(){
    }
    private String pagenumber = ""; // PAGENUMBER
    private String user_code = ""; // USER_CODE
    private String lable_name = ""; // LABLE_NAME
    private String post_flag = ""; // POST_FLAG
    private String sign_flag = ""; // SIGN_FLAG
    private String return_flag = ""; // RETURN_FLAG
    private String show_flag = ""; // SHOW_FLAG
    private String mail_level = ""; // MAIL_LEVEL
    private static final int pagenumber_col = 0; // PAGENUMBER相对应的列数
    private static final int user_code_col = 1; // USER_CODE相对应的列数
    private static final int lable_name_col = 2; // LABLE_NAME相对应的列数
    private static final int post_flag_col = 3; // POST_FLAG相对应的列数
    private static final int sign_flag_col = 4; // SIGN_FLAG相对应的列数
    private static final int return_flag_col = 5; // RETURN_FLAG相对应的列数
    private static final int show_flag_col = 6; // SHOW_FLAG相对应的列数
    private static final int mail_level_col = 7; // MAIL_LEVEL相对应的列数
    public String getPagenumber() {
        return this.pagenumber;
    }
    public void setPagenumber(String pagenumber) {
        this.pagenumber = pagenumber;
    }
    public String getUser_code() {
        return this.user_code;
    }
    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }
    public String getLable_name() {
        return this.lable_name;
    }
    public void setLable_name(String lable_name) {
        this.lable_name = lable_name;
    }
    public String getPost_flag() {
        return this.post_flag;
    }
    public void setPost_flag(String post_flag) {
        this.post_flag = post_flag;
    }
    public String getSign_flag() {
        return this.sign_flag;
    }
    public void setSign_flag(String sign_flag) {
        this.sign_flag = sign_flag;
    }
    public String getReturn_flag() {
        return this.return_flag;
    }
    public void setReturn_flag(String return_flag) {
        this.return_flag = return_flag;
    }
    public String getShow_flag() {
        return this.show_flag;
    }
    public void setShow_flag(String show_flag) {
        this.show_flag = show_flag;
    }
    public String getMail_level() {
        return this.mail_level;
    }
    public void setMail_level(String mail_level) {
        this.mail_level = mail_level;
    }
    public String getValue(String name) {
        if ("pagenumber".equalsIgnoreCase(name) == true) {
             return  this.pagenumber;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             return  this.user_code;
        } else if ("lable_name".equalsIgnoreCase(name) == true) {
             return  this.lable_name;
        } else if ("post_flag".equalsIgnoreCase(name) == true) {
             return  this.post_flag;
        } else if ("sign_flag".equalsIgnoreCase(name) == true) {
             return  this.sign_flag;
        } else if ("return_flag".equalsIgnoreCase(name) == true) {
             return  this.return_flag;
        } else if ("show_flag".equalsIgnoreCase(name) == true) {
             return  this.show_flag;
        } else if ("mail_level".equalsIgnoreCase(name) == true) {
             return  this.mail_level;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("pagenumber".equalsIgnoreCase(name) == true) {
             this.pagenumber = value ;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             this.user_code = value ;
        } else if ("lable_name".equalsIgnoreCase(name) == true) {
             this.lable_name = value ;
        } else if ("post_flag".equalsIgnoreCase(name) == true) {
             this.post_flag = value ;
        } else if ("sign_flag".equalsIgnoreCase(name) == true) {
             this.sign_flag = value ;
        } else if ("return_flag".equalsIgnoreCase(name) == true) {
             this.return_flag = value ;
        } else if ("show_flag".equalsIgnoreCase(name) == true) {
             this.show_flag = value ;
        } else if ("mail_level".equalsIgnoreCase(name) == true) {
             this.mail_level = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("pagenumber".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("lable_name".equalsIgnoreCase(name) == true) {
             colType = "string";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "mailinfo";
    }
    public String getPkFields() {
        return "user_code,";
    }
    public String getModifyFields() {
        return "pagenumber,lable_name,post_flag,sign_flag,return_flag,show_flag,mail_level,";
    }
    public String getAllFields() {
        return "pagenumber,user_code,lable_name,post_flag,sign_flag,return_flag,show_flag,mail_level,";
    }
    public void setValues(String[] values) {
        this.pagenumber = values[pagenumber_col];
        this.user_code = values[user_code_col];
        this.lable_name = values[lable_name_col];
        this.post_flag = values[post_flag_col];
        this.sign_flag = values[sign_flag_col];
        this.return_flag = values[return_flag_col];
        this.show_flag = values[show_flag_col];
        this.mail_level = values[mail_level_col];
    }
    public void setOtherProperty(String[] values) {
    }
}

