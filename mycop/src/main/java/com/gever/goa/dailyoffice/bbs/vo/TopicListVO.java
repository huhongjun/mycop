package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: TopicList中的vo对象</p>
 * <p>Description: 是DO_BBS_TopicList的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class TopicListVO extends BaseVO implements VOInterface {
    public TopicListVO(){
    }
    private String topic_num = ""; // TOPIC_NUM
    private String serial_num = ""; // SERIAL_NUM
    private String bbs_user_code = ""; // BBS_USER_CODE
    private String content = ""; // CONTENT
    private String ip_address = ""; // IP_ADDRESS
    private String file_path = ""; // FILE_PATH
    private String file_name = ""; // FILE_NAME
    private String reply_date = ""; // REPLY_DATE
    private String awoke_flag = "0"; // AWOKE_FLAG
    private String is_show="0";//
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int topic_num_col = 1; // TOPIC_NUM相对应的列数
    private static final int bbs_user_code_col = 2; // BBS_USER_CODE相对应的列数
    private static final int content_col = 3; // CONTENT相对应的列数
    private static final int ip_address_col = 4; // IP_ADDRESS相对应的列数
    private static final int file_path_col = 5; // FILE_PATH相对应的列数
    private static final int file_name_col = 6; // FILE_NAME相对应的列数
    private static final int reply_date_col = 7; // REPLY_DATE相对应的列数
    private static final int awoke_flag_col = 8; // AWOKE_FLAG相对应的列数
    private static final int is_show_col=9;
    public String getTopic_num() {
        return this.topic_num;
    }
    public void setTopic_num(String topic_num) {
        this.topic_num = topic_num;
    }
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
        return this.reply_date;
    }
    public void setReply_date(String reply_date) {
        this.reply_date = reply_date;
    }
    public String getAwoke_flag() {
        return this.awoke_flag;
    }
    public void setAwoke_flag(String awoke_flag) {
        this.awoke_flag = awoke_flag;
    }
    public String getValue(String name) {
        if ("topic_num".equalsIgnoreCase(name) == true) {
             return  this.topic_num;
        } else if ("serial_num".equalsIgnoreCase(name) == true) {
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
        } else if ("awoke_flag".equalsIgnoreCase(name) == true) {
             return  this.awoke_flag;
        }else if("is_show".equalsIgnoreCase(name)){
            return this.is_show;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("topic_num".equalsIgnoreCase(name) == true) {
             this.topic_num = value ;
        } else if ("serial_num".equalsIgnoreCase(name) == true) {
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
        } else if ("awoke_flag".equalsIgnoreCase(name) == true) {
             this.awoke_flag = value ;
        }else if("is_show".equalsIgnoreCase(name)){
            this.is_show=value;
        } else {
             return ;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("reply_date".equalsIgnoreCase(name) == true) {
             colType = "timestamp";
        } else if ("awoke_flag".equalsIgnoreCase(name) == true) {
             colType = "int";
        }else if("is_show".equalsIgnoreCase(name)){
            colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "DO_BBS_TopicList";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "topic_num,bbs_user_code,content,ip_address,file_path,file_name,reply_date,awoke_flag,is_show,";
    }
    public String getAllFields() {
        return "serial_num,topic_num,bbs_user_code,content,ip_address,file_path,file_name,reply_date,awoke_flag,is_show,";
    }
    public void setValues(String[] values) {
        this.topic_num = values[topic_num_col];
        this.serial_num = values[serial_num_col];
        this.bbs_user_code = values[bbs_user_code_col];
        this.content = values[content_col];
        this.ip_address = values[ip_address_col];
        this.file_path = values[file_path_col];
        this.file_name = values[file_name_col];
        this.reply_date = values[reply_date_col];
        this.awoke_flag = values[awoke_flag_col];
        this.is_show = values[is_show_col];
    }
    public void setOtherProperty(String[] values) {
    }
    public String getIs_show() {
        return is_show;
    }
    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }
}
