package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: view_bbs_topic中的vo对象</p>
 * <p>Description: 是view_bbs_topic的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class ViewTopicVO extends BaseVO implements VOInterface {
    private String serial_num = ""; // SERIAL_NUM
    private String topic = ""; // TOPIC
    private String nickname = ""; // NICKNAME
    private String reply_cnt = ""; // REPLY_CNT
    private String hit_times = ""; // HIT_TIMES
    private String appear_date = ""; // APPEAR_DATE
    private String reply_time = ""; // REPLY_TIME
    private String replyer = ""; // REPLYER
    private String sboard_serial="";
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int topic_col = 1; // TOPIC相对应的列数
    private static final int nickname_col = 2; // NICKNAME相对应的列数
    private static final int reply_cnt_col = 3; // REPLY_CNT相对应的列数
    private static final int hit_times_col = 4; // HIT_TIMES相对应的列数
    private static final int appear_date_col = 5; // APPEAR_DATE相对应的列数
    private static final int reply_time_col = 6; // REPLY_TIME相对应的列数
    private static final int replyer_col = 7; // REPLYER相对应的列数
    private static final int sboard_serial_col=8;
    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getTopic() {
        return this.topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getReply_cnt() {
        return this.reply_cnt;
    }
    public void setReply_cnt(String reply_cnt) {
        this.reply_cnt = reply_cnt;
    }
    public String getHit_times() {
        return this.hit_times;
    }
    public void setHit_times(String hit_times) {
        this.hit_times = hit_times;
    }
    public String getAppear_date() {
        //显示时间截取到分
        String tmp=this.appear_date;
        int index=tmp.lastIndexOf(":");
        if(index!=-1){
            tmp=tmp.substring(0,index);
        }
        return tmp;
    }
    public void setAppear_date(String appear_date) {
        this.appear_date = appear_date;
    }
    public String getReply_time() {
        //显示时间截取到分
        String tmp=this.reply_time;
        int index=tmp.lastIndexOf(":");
        if(index!=-1){
            tmp=tmp.substring(0,index);
        }
        return tmp;

    }
    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }
    public String getReplyer() {
        return this.replyer;
    }
    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }
    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        }else if("sboard_serial".equalsIgnoreCase(name)){
            return this.sboard_serial;
        }else if ("topic".equalsIgnoreCase(name) == true) {
             return  this.topic;
        } else if ("nickname".equalsIgnoreCase(name) == true) {
             return  this.nickname;
        } else if ("reply_cnt".equalsIgnoreCase(name) == true) {
             return  this.reply_cnt;
        } else if ("hit_times".equalsIgnoreCase(name) == true) {
             return  this.hit_times;
        } else if ("appear_date".equalsIgnoreCase(name) == true) {
             return  this.appear_date;
        } else if ("reply_time".equalsIgnoreCase(name) == true) {
             return  this.reply_time;
        } else if ("replyer".equalsIgnoreCase(name) == true) {
             return  this.replyer;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        }else if("sboard_serial".equalsIgnoreCase(name)){
            this.sboard_serial=value;
        }else if ("topic".equalsIgnoreCase(name) == true) {
             this.topic = value ;
        } else if ("nickname".equalsIgnoreCase(name) == true) {
             this.nickname = value ;
        } else if ("reply_cnt".equalsIgnoreCase(name) == true) {
             this.reply_cnt = value ;
        } else if ("hit_times".equalsIgnoreCase(name) == true) {
             this.hit_times = value ;
        } else if ("appear_date".equalsIgnoreCase(name) == true) {
             this.appear_date = value ;
        } else if ("reply_time".equalsIgnoreCase(name) == true) {
             this.reply_time = value ;
        } else if ("replyer".equalsIgnoreCase(name) == true) {
             this.replyer = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("reply_cnt".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("hit_times".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("appear_date".equalsIgnoreCase(name) == true) {
             colType = "timestamp";
        } else if ("reply_time".equalsIgnoreCase(name) == true) {
             colType = "timestamp";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "view_bbs_topic";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "topic,nickname,reply_cnt,hit_times,appear_date,reply_time,replyer,sboard_serial,";
    }
    public String getAllFields() {
        return "serial_num,topic,nickname,reply_cnt,hit_times,appear_date,reply_time,replyer,sboard_serial,";
    }
    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.topic = values[topic_col];
        this.nickname = values[nickname_col];
        this.reply_cnt = values[reply_cnt_col];
        this.hit_times = values[hit_times_col];
        this.appear_date = values[appear_date_col];
        this.reply_time = values[reply_time_col];
        this.replyer = values[replyer_col];
        this.serial_num= values[sboard_serial_col];
    }
    public void setOtherProperty(String[] values) {
    }
    public String getSboard_serial() {
        return sboard_serial;
    }
    public void setSboard_serial(String sboard_serial) {
        this.sboard_serial = sboard_serial;
    }
}
