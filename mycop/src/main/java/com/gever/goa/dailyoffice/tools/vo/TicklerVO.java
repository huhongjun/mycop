package com.gever.goa.dailyoffice.tools.vo;

import java.util.Date;

import com.gever.util.DateTimeUtils;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;


/**
 * <p>Title: dailyoffice.tools中的vo对象</p>
 * <p>Description: 是DO_TICKLER的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class TicklerVO extends BaseVO implements VOInterface {
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int user_code_col = 1; // USER_CODE相对应的列数
    private static final int content_col = 2; // CONTENT相对应的列数
    private static final int create_date_col = 3; // CREATE_DATE相对应的列数
    private static final int remind_flag_col = 4; // REMIND_FLAG相对应的列数
    private static final int awoke_time_col = 5; // AWOKE_TIME相对应的列数
    private static final int finish_flag_col = 6; // FINISH_FLAG相对应的列数

    //xyz add begin
    private String awokeDate = initCreateDate();
    private String awokeTime = DateTimeUtils.getCurrentTime();
    private String awoke_time_bak;
    private String remind_flag_bak = "";
    private String content_bak = "";

    //xyz add end
    private String serial_num = ""; // SERIAL_NUM
    private String user_code = ""; // USER_CODE
    private String content = ""; // CONTENT
    private String create_date = awokeDate; // CREATE_DATE
    private String remind_flag = ""; // REMIND_FLAG

    // private String awoke_time = ""; // AWOKE_TIME
    private String finish_flag = ""; // FINISH_FLAG

    public TicklerVO() {
    }

    //xyz add begin
    private String initCreateDate() {
        String date = new Date().toLocaleString();
        date = date.substring(0, date.indexOf(" "));

        return date;
    }

    public void setAwokeDate(String awokeDate) {
        this.awokeDate = awokeDate;
    }

    public String getAwokeDate() {
        return this.awokeDate;
    }

    public void setAwokeTime(String awokeTime) {
        this.awokeTime = awokeTime;
    }

    public String getAwokeTime() {
        return this.awokeTime;
    }

    // public static void main(String[] args){
    // String str=null;
    //   System.out.println(str.equals(""));
    //System.out.println(new TicklerVO().initCreateDate());
    //System.out.println(new java.util.Date().toLocaleString());
    // }
    //xyz add end;
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

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_date() {
        return this.create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getRemind_flag() {
        return this.remind_flag;
    }

    public void setRemind_flag(String remind_flag) {
        this.remind_flag = remind_flag;
    }

    public String getAwoke_time() {
        return awokeDate + " " + awokeTime; //xyz add
    }

    public void setAwoke_time(String awoke_time) {
        if (awoke_time != null) {
            int index;

            if ((index = awoke_time.indexOf(".")) != -1) {
                awoke_time = awoke_time.substring(0, index);
            }

            String[] array = awoke_time.split(" ");

            if ((array != null) && (array.length == 2)) {
                this.setAwokeDate(array[0]);
                this.setAwokeTime(array[1]);
            }
        }

        // this.awoke_time = awoke_time;
    }

    public String getFinish_flag() {
        return this.finish_flag;
    }

    public void setFinish_flag(String finish_flag) {
        this.finish_flag = finish_flag;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("content".equalsIgnoreCase(name) == true) {
            return this.content;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            return this.create_date;
        } else if ("remind_flag".equalsIgnoreCase(name) == true) {
            return this.remind_flag;
        } else if ("awoke_time".equalsIgnoreCase(name) == true) {
            return this.getAwoke_time();
        } else if ("finish_flag".equalsIgnoreCase(name) == true) {
            return this.finish_flag;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("content".equalsIgnoreCase(name) == true) {
            this.content = value;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            this.create_date = value;
        } else if ("remind_flag".equalsIgnoreCase(name) == true) {
            this.remind_flag = value;
        } else if ("awoke_time".equalsIgnoreCase(name) == true) {
            this.setAwoke_time(value);
        } else if ("finish_flag".equalsIgnoreCase(name) == true) {
            this.finish_flag = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();

        if ("create_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("awoke_time".equalsIgnoreCase(name) == true) {
            colType = "timestamp";
        } else if ("finish_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else {
            colType = "String";
        }

        return colType;
    }

    public String getTableName() {
        return "do_tickler";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "user_code,content,create_date,remind_flag,awoke_time,finish_flag,";
    }

    public String getAllFields() {
        return "serial_num,user_code,content,create_date,remind_flag,awoke_time,finish_flag,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.user_code = values[user_code_col];
        this.content = values[content_col];
        this.create_date = values[create_date_col];
        this.remind_flag = values[remind_flag_col];
        this.setAwoke_time(values[awoke_time_col]);
        this.finish_flag = values[finish_flag_col];
    }

    public void setOtherProperty(String[] values) {
        this.setAwoke_time_bak(this.getAwoke_time());
        this.setContent_bak(this.getContent());
        this.setRemind_flag_bak(this.getRemind_flag());

        //gw add
        if (!StringUtils.isNull(this.create_date) &&
                (this.create_date.length() > 10)) {
            this.create_date = this.create_date.substring(0, 10);
        }
    }

    public String getAwoke_time_bak() {
        return awoke_time_bak;
    }

    public void setAwoke_time_bak(String awoke_time_bak) {
        this.awoke_time_bak = awoke_time_bak;
    }

    public String getRemind_flag_bak() {
        return remind_flag_bak;
    }

    public void setRemind_flag_bak(String remind_flag_bak) {
        this.remind_flag_bak = remind_flag_bak;
    }

    public String getContent_bak() {
        return content_bak;
    }

    public void setContent_bak(String content_bak) {
        this.content_bak = content_bak;
    }
}
