package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: ViewTopiclist中的vo对象</p>
 * <p>Description: 是view_bbs_topiclist的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class ViewTopiclistVO extends BaseVO implements VOInterface {
    public ViewTopiclistVO(){
    }
    private String serial_num = ""; // SERIAL_NUM
    private String bbs_user_code = ""; // BBS_USER_CODE
    private String content = ""; // CONTENT
    private String ip_address = ""; // IP_ADDRESS
    private String file_path = ""; // FILE_PATH
    private String file_name = ""; // FILE_NAME
    private String reply_date = ""; // REPLY_DATE
    private String nickname = ""; // NICKNAME
    private String user_icon = ""; // USER_ICON
    private String last_log_time = ""; // LAST_LOG_TIME
    private String user_code = ""; // USER_CODE
    private String user_state = ""; // USER_STATE
    private String sex_code = ""; // SEX_CODE
    private String ismaster = ""; // ISMASTER
    private String topic_num="";
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int bbs_user_code_col = 1; // BBS_USER_CODE相对应的列数
    private static final int content_col = 2; // CONTENT相对应的列数
    private static final int ip_address_col = 3; // IP_ADDRESS相对应的列数
    private static final int file_path_col = 4; // FILE_PATH相对应的列数
    private static final int file_name_col = 5; // FILE_NAME相对应的列数
    private static final int reply_date_col = 6; // REPLY_DATE相对应的列数
    private static final int nickname_col = 7; // NICKNAME相对应的列数
    private static final int user_icon_col = 8; // USER_ICON相对应的列数
    private static final int last_log_time_col = 9; // LAST_LOG_TIME相对应的列数
    private static final int user_code_col = 10; // USER_CODE相对应的列数
    private static final int user_state_col = 11; // USER_STATE相对应的列数
    private static final int sex_code_col = 12; // SEX_CODE相对应的列数
    private static final int topic_num_col=13;
    private static final int ismaster_col = 14; // ISMASTER相对应的列数

    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getBbs_user_code() {
        return this.bbs_user_code;
    }
    public void setBbs_user_code(String bbs_user_code) {
        this.bbs_user_code = bbs_user_code;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getIp_address() {
        return this.ip_address;
    }
    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
    public String getFile_path() {
        return this.file_path;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    public String getFile_name() {
        return this.file_name;
    }
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    public String getReply_date() {
        //显示时间截取到分
       String tmp=this.reply_date;
       int index=tmp.lastIndexOf(":");
       if(index!=-1){
           tmp=tmp.substring(0,index);
       }
       return tmp;

    }
    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
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
        //显示时间截取到分
        String tmp=this.last_log_time;
        int index=tmp.lastIndexOf(":");
        if(index!=-1){
            tmp=tmp.substring(0,index);
        }
        return tmp;

    }
    public void setLast_log_time(String last_log_time) {
        this.last_log_time = last_log_time;
    }
    public String getUser_code() {
        return this.user_code;
    }
    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }
    public String getUser_state() {
        return this.user_state;
    }
    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }
    public String getSex_code() {
        return this.sex_code;
    }
    public void setSex_code(String sex_code) {
        this.sex_code = sex_code;
    }
    public String getIsmaster() {
        return this.ismaster;
    }
    public void setIsmaster(String ismaster) {
        this.ismaster = ismaster;
    }
    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        } else if ("bbs_user_code".equalsIgnoreCase(name) == true) {
             return  this.bbs_user_code;
        } else if ("content".equalsIgnoreCase(name) == true) {
             return  this.content;
        } else if ("ip_address".equalsIgnoreCase(name) == true) {
             return  this.ip_address;
        } else if ("file_path".equalsIgnoreCase(name) == true) {
             return  this.file_path;
        } else if ("file_name".equalsIgnoreCase(name) == true) {
             return  this.file_name;
        } else if ("reply_date".equalsIgnoreCase(name) == true) {
             return  this.reply_date;
        } else if ("nickname".equalsIgnoreCase(name) == true) {
             return  this.nickname;
        } else if ("user_icon".equalsIgnoreCase(name) == true) {
             return  this.user_icon;
        } else if ("last_log_time".equalsIgnoreCase(name) == true) {
             return  this.last_log_time;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             return  this.user_code;
        } else if ("user_state".equalsIgnoreCase(name) == true) {
             return  this.user_state;
        } else if ("sex_code".equalsIgnoreCase(name) == true) {
             return  this.sex_code;
        } else if ("ismaster".equalsIgnoreCase(name) == true) {
             return  this.ismaster;
        }else if("topic_num".equalsIgnoreCase(name)){
            return this.topic_num;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        } else if ("bbs_user_code".equalsIgnoreCase(name) == true) {
             this.bbs_user_code = value ;
        } else if ("content".equalsIgnoreCase(name) == true) {
             this.content = value ;
        } else if ("ip_address".equalsIgnoreCase(name) == true) {
             this.ip_address = value ;
        } else if ("file_path".equalsIgnoreCase(name) == true) {
             this.file_path = value ;
        } else if ("file_name".equalsIgnoreCase(name) == true) {
             this.file_name = value ;
        } else if ("reply_date".equalsIgnoreCase(name) == true) {
             this.reply_date = value ;
        } else if ("nickname".equalsIgnoreCase(name) == true) {
             this.nickname = value ;
        } else if ("user_icon".equalsIgnoreCase(name) == true) {
             this.user_icon = value ;
        } else if ("last_log_time".equalsIgnoreCase(name) == true) {
             this.last_log_time = value ;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             this.user_code = value ;
        } else if ("user_state".equalsIgnoreCase(name) == true) {
             this.user_state = value ;
        } else if ("sex_code".equalsIgnoreCase(name) == true) {
             this.sex_code = value ;
        } else if ("ismaster".equalsIgnoreCase(name) == true) {
             this.ismaster = value ;
        }else if("topic_num".equalsIgnoreCase(name)){
            this.topic_num=value;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("reply_date".equalsIgnoreCase(name) == true) {
             colType = "timestamp";
        } else if ("last_log_time".equalsIgnoreCase(name) == true) {
             colType = "timestamp";
        } else if ("user_code".equalsIgnoreCase(name) == true) {
             colType = "int";
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
        return "view_bbs_topiclist";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "bbs_user_code,content,ip_address,file_path,file_name,reply_date,nickname,user_icon,last_log_time,user_code,user_state,sex_code,topic_num,";
    }
    public String getAllFields() {
        return "serial_num,bbs_user_code,content,ip_address,file_path,file_name,reply_date,nickname,user_icon,last_log_time,user_code,user_state,sex_code,topic_num,";
    }
    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.bbs_user_code = values[bbs_user_code_col];
        this.content = values[content_col];
        this.ip_address = values[ip_address_col];
        this.file_path = values[file_path_col];
        this.file_name = values[file_name_col];
        this.reply_date = values[reply_date_col];
        this.nickname = values[nickname_col];
        this.user_icon = values[user_icon_col];
        this.last_log_time = values[last_log_time_col];
        this.user_code = values[user_code_col];
        this.user_state = values[user_state_col];
        this.sex_code = values[sex_code_col];
        this.ismaster = values[ismaster_col];
        this.topic_num=values[topic_num_col];
    }
    public void setOtherProperty(String[] values) {
    }
    public String getTopic_num() {
        return topic_num;
    }
    public void setTopic_num(String topic_num) {
        this.topic_num = topic_num;
    }
}
