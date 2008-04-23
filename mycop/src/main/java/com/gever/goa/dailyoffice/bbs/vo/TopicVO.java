package com.gever.goa.dailyoffice.bbs.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: Topic中的vo对象</p>
 * <p>Description: 是DO_BBS_TOPIC的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class TopicVO extends BaseVO implements VOInterface {
    public TopicVO(){
    }
    private String serial_num = ""; // SERIAL_NUM
    private String sboard_serial = ""; // SBOARD_SERIAL
    private String topic = ""; // TOPIC
    private String bbs_user_code = ""; // bbs_user_code
    private String topic_type = ""; // TOPIC_TYPE
    private String topic_user = ""; // TOPIC_USER
    private String appear_date = ""; // APPEAR_DATE
    private String topic_order = ""; // TOPIC_ORDER
    private String hit_times = ""; // HIT_TIMES
    private String isblock = ""; // ISBLOCK
    private String is_show="";//
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int sboard_serial_col = 1; // SBOARD_SERIAL相对应的列数
    private static final int topic_col = 2; // TOPIC相对应的列数
    private static final int bbs_user_code_col = 3; // bbs_user_code相对应的列数
    private static final int topic_type_col = 4; // TOPIC_TYPE相对应的列数
    private static final int topic_user_col = 5; // TOPIC_USER相对应的列数
    private static final int appear_date_col = 6; // APPEAR_DATE相对应的列数
    private static final int topic_order_col = 7; // TOPIC_ORDER相对应的列数
    private static final int hit_times_col = 8; // HIT_TIMES相对应的列数
    private static final int isblock_col = 9; // ISBLOCK相对应的列数
    private static final int is_show_col=10;
    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getSboard_serial() {
        return this.sboard_serial;
    }
    public void setSboard_serial(String sboard_serial) {
        this.sboard_serial = sboard_serial;
    }
    public String getTopic() {
        return this.topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getBbs_user_code() {
        return this.bbs_user_code;
    }
    public void setBbs_user_code(String bbs_user_code) {
        this.bbs_user_code = bbs_user_code;
    }
    public String getTopic_type() {
        return this.topic_type;
    }
    public void setTopic_type(String topic_type) {
        this.topic_type = topic_type;
    }
    public String getTopic_user() {
        return this.topic_user;
    }
    public void setTopic_user(String topic_user) {
        this.topic_user = topic_user;
    }
    public String getAppear_date() {
        return this.appear_date;
    }
    public void setAppear_date(String appear_date) {
        this.appear_date = appear_date;
    }
    public String getTopic_order() {
        return this.topic_order;
    }
    public void setTopic_order(String topic_order) {
        this.topic_order = topic_order;
    }
    public String getHit_times() {
        return this.hit_times;
    }
    public void setHit_times(String hit_times) {
        this.hit_times = hit_times;
    }
    public String getIsblock() {
        return this.isblock;
    }
    public void setIsblock(String isblock) {
        this.isblock = isblock;
    }
    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        } else if ("sboard_serial".equalsIgnoreCase(name) == true) {
             return  this.sboard_serial;
        } else if ("topic".equalsIgnoreCase(name) == true) {
             return  this.topic;
        } else if ("bbs_user_code".equalsIgnoreCase(name) == true) {
             return  this.bbs_user_code;
        } else if ("topic_type".equalsIgnoreCase(name) == true) {
             return  this.topic_type;
        } else if ("topic_user".equalsIgnoreCase(name) == true) {
             return  this.topic_user;
        } else if ("appear_date".equalsIgnoreCase(name) == true) {
             return  this.appear_date;
        } else if ("topic_order".equalsIgnoreCase(name) == true) {
             return  this.topic_order;
        } else if ("hit_times".equalsIgnoreCase(name) == true) {
             return  this.hit_times;
        } else if ("isblock".equalsIgnoreCase(name) == true) {
             return  this.isblock;
         }else if("is_show".equalsIgnoreCase(name)){
            return this.is_show;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        } else if ("sboard_serial".equalsIgnoreCase(name) == true) {
             this.sboard_serial = value ;
        } else if ("topic".equalsIgnoreCase(name) == true) {
             this.topic = value ;
        } else if ("bbs_user_code".equalsIgnoreCase(name) == true) {
             this.bbs_user_code = value ;
        } else if ("topic_type".equalsIgnoreCase(name) == true) {
             this.topic_type = value ;
        } else if ("topic_user".equalsIgnoreCase(name) == true) {
             this.topic_user = value ;
        } else if ("appear_date".equalsIgnoreCase(name) == true) {
             this.appear_date = value ;
        } else if ("topic_order".equalsIgnoreCase(name) == true) {
             this.topic_order = value ;
        } else if ("hit_times".equalsIgnoreCase(name) == true) {
             this.hit_times = value ;
        } else if ("isblock".equalsIgnoreCase(name) == true) {
             this.isblock = value ;
         }else if("is_show".equalsIgnoreCase(name)){
            this.is_show=value;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("topic_type".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("appear_date".equalsIgnoreCase(name) == true) {
             colType = "timestamp";
        } else if ("topic_order".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("hit_times".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("isblock".equalsIgnoreCase(name) == true) {
             colType = "int";
         }else if("is_show".equalsIgnoreCase(name)){
            colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "DO_BBS_TOPIC";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "sboard_serial,topic,bbs_user_code,topic_type,topic_user,appear_date,topic_order,hit_times,isblock,is_show,";
    }
    public String getAllFields() {
        return "serial_num,sboard_serial,topic,bbs_user_code,topic_type,topic_user,appear_date,topic_order,hit_times,isblock,is_show,";
    }
    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.sboard_serial = values[sboard_serial_col];
        this.topic = values[topic_col];
        this.bbs_user_code = values[bbs_user_code_col];
        this.topic_type = values[topic_type_col];
        this.topic_user = values[topic_user_col];
        this.appear_date = values[appear_date_col];
        this.topic_order = values[topic_order_col];
        this.hit_times = values[hit_times_col];
        this.isblock = values[isblock_col];
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
