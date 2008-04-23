package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: User中的vo对象</p>
 * <p>Description: 是DO_BBS_user的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class UserVO extends BaseVO implements VOInterface {
    public UserVO(){
    }
    private String bbs_user_code = ""; // BBS_USER_CODE
    private String nickname = ""; // NICKNAME
    private String user_icon = ""; // USER_ICON
    private String last_log_time = ""; // LAST_LOG_TIME
    private String user_state = ""; // USER_STATE
    private String user_code = ""; // USER_CODE
    private String sex_code = ""; // SEX_CODE
    private String e_mail = ""; // E_MAIL
    private String home_page = ""; // HOME_PAGE
    private String ismaster = ""; // ISMASTER
    private static final int bbs_user_code_col = 0; // BBS_USER_CODE相对应的列数
    private static final int nickname_col = 1; // NICKNAME相对应的列数
    private static final int user_icon_col = 2; // USER_ICON相对应的列数
    private static final int last_log_time_col = 3; // LAST_LOG_TIME相对应的列数
    private static final int user_state_col = 4; // USER_STATE相对应的列数
    private static final int user_code_col = 4; // USER_CODE相对应的列数
    private static final int sex_code_col = 5; // SEX_CODE相对应的列数
    private static final int e_mail_col = 6; // E_MAIL相对应的列数
    private static final int home_page_col = 7; // HOME_PAGE相对应的列数
    private static final int ismaster_col = 9; // ISMASTER相对应的列数
    public String getBbs_user_code() {
        return this.bbs_user_code;
    }
    public void setBbs_user_code(String bbs_user_code) {
        this.bbs_user_code = bbs_user_code;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUser_icon() {
        return this.user_icon;
    }
    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }
    public String getLast_log_time() {
        return this.last_log_time;
    }
    public void setLast_log_time(String last_log_time) {
        this.last_log_time = last_log_time;
    }
    public String getUser_state() {
        return this.user_state;
    }
    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }
    public String getUser_code() {
        return this.user_code;
    }
    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }
    public String getSex_code() {
        return this.sex_code;
    }
    public void setSex_code(String sex_code) {
        this.sex_code = sex_code;
    }
    public String getE_mail() {
        return this.e_mail;
    }
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
    public String getHome_page() {
        return this.home_page;
    }
    public void setHome_page(String home_page) {
        this.home_page = home_page;
    }
    public String getIsmaster() {
        return this.ismaster;
    }
    public void setIsmaster(String ismaster) {
        this.ismaster = ismaster;
    }
    public String getValue(String name) {
        if ("bbs_user_code".equalsIgnoreCase(name) == true) {
             return  this.bbs_user_code;
        } else if ("nickname".equalsIgnoreCase(name) == true) {
             return  this.nickname;
        } else if ("user_icon".equalsIgnoreCase(name) == true) {
             return  this.user_icon;
        } else if ("last_log_time".equalsIgnoreCase(name) == true) {
             return  this.last_log_time;
        } else if ("user_state".equalsIgnoreCase(name) == true) {
             return  this.user_state;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             return  this.user_code;
        } else if ("sex_code".equalsIgnoreCase(name) == true) {
             return  this.sex_code;
        } else if ("e_mail".equalsIgnoreCase(name) == true) {
             return  this.e_mail;
        } else if ("home_page".equalsIgnoreCase(name) == true) {
             return  this.home_page;
        } else if ("ismaster".equalsIgnoreCase(name) == true) {
             return  this.ismaster;
       } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("bbs_user_code".equalsIgnoreCase(name) == true) {
             this.bbs_user_code = value ;
        } else if ("nickname".equalsIgnoreCase(name) == true) {
             this.nickname = value ;
        } else if ("user_icon".equalsIgnoreCase(name) == true) {
             this.user_icon = value ;
        } else if ("last_log_time".equalsIgnoreCase(name) == true) {
             this.last_log_time = value ;
        } else if ("user_state".equalsIgnoreCase(name) == true) {
             this.user_state = value ;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             this.user_code = value ;
        } else if ("sex_code".equalsIgnoreCase(name) == true) {
             this.sex_code = value ;
        } else if ("e_mail".equalsIgnoreCase(name) == true) {
             this.e_mail = value ;
        } else if ("home_page".equalsIgnoreCase(name) == true) {
             this.home_page = value ;
        } else if ("ismaster".equalsIgnoreCase(name) == true) {
             this.ismaster = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("last_log_time".equalsIgnoreCase(name) == true) {
             colType = "datetime";
        } else if ("user_state".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("ismaster".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "DO_BBS_user";
    }
    public String getPkFields() {
        return "bbs_user_code,";
    }
    public String getModifyFields() {
        return "nickname,user_icon,user_code,user_state,sex_code,e_mail,home_page,";
    }
    public String getAllFields() {
        return "bbs_user_code,nickname,user_icon,last_log_time,user_code,user_state,sex_code,e_mail,home_page,";
    }
    public void setValues(String[] values) {
        this.bbs_user_code = values[bbs_user_code_col];
        this.nickname = values[nickname_col];
        this.user_icon = values[user_icon_col];
        this.last_log_time = values[last_log_time_col];
        this.user_state = values[user_state_col];
        this.user_code = values[user_code_col];
        this.sex_code = values[sex_code_col];
        this.e_mail = values[e_mail_col];
        this.home_page = values[home_page_col];
        this.ismaster = values[ismaster_col];
    }
    public void setOtherProperty(String[] values) {
    }
}
