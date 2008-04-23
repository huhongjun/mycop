package com.gever.goa.dailyoffice.calendararrange.vo;

import com.gever.exception.DefaultException;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: CalendarArrange中的vo对象</p>
 * <p>Description: 是DO_CALENDAR_ARRANGE的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class CalendarArrangeVO extends BaseVO implements VOInterface {
    public CalendarArrangeVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    private String calendar = ""; // CALENDAR
    private String user_code = ""; // USER_CODE
    private String user_name = "";
    private String begin_time = ""; // BEGIN_TIME
    private String end_time = ""; // END_TIME
    private String task_type = ""; // TASK_TYPE
    private String task_sum = ""; // TASK_SUM
    private String task_content = ""; // TASK_CONTENT
    private String remind_flag = "0"; // REMIND_FLAG
    private String awoke_time = ""; // AWOKE_TIME
    private String arrange = ""; // ARRANGE
    private String arrangename = "";
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int calendar_col = 1; // CALENDAR相对应的列数
    private static final int user_code_col = 2; // USER_CODE相对应的列数
    private static final int begin_time_col = 3; // BEGIN_TIME相对应的列数
    private static final int end_time_col = 4; // END_TIME相对应的列数
    private static final int task_type_col = 5; // TASK_TYPE相对应的列数
    private static final int task_sum_col = 6; // TASK_SUM相对应的列数
    private static final int task_content_col = 7; // TASK_CONTENT相对应的列数
    private static final int remind_flag_col = 8; // REMIND_FLAG相对应的列数
    private static final int awoke_time_col = 9; // AWOKE_TIME相对应的列数
    private static final int arrange_col = 10; // ARRANGE相对应的列数
    private static final int user_name_col = 11; // ARRANGE相对应的列数
    private static final int arrangename_col = 12; // ARRANGE相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getCalendar() {
        return this.calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getBegin_time() {
        return this.begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTask_type() {
        return this.task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public String getTask_sum() {
        return this.task_sum;
    }

    public void setTask_sum(String task_sum) {
        this.task_sum = task_sum;
    }

    public String getTask_content() {
        return this.task_content;
    }

    public void setTask_content(String task_content) {
        this.task_content = task_content;
    }

    public String getRemind_flag() {
        return this.remind_flag;
    }

    public void setRemind_flag(String remind_flag) {
        this.remind_flag = remind_flag;
    }

    public String getAwoke_time() {
        return this.awoke_time;
    }

    public void setAwoke_time(String awoke_time) {
        this.awoke_time = awoke_time;
    }

    public String getArrange() {
        return this.arrange;
    }

    public void setArrange(String arrange) {
        this.arrange = arrange;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("calendar".equalsIgnoreCase(name) == true) {
            return this.calendar;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            return this.user_name;
        }

        else if ("begin_time".equalsIgnoreCase(name) == true) {
            return this.begin_time;
        } else if ("end_time".equalsIgnoreCase(name) == true) {
            return this.end_time;
        } else if ("task_type".equalsIgnoreCase(name) == true) {
            return this.task_type;
        } else if ("task_sum".equalsIgnoreCase(name) == true) {
            return this.task_sum;
        } else if ("task_content".equalsIgnoreCase(name) == true) {
            return this.task_content;
        } else if ("remind_flag".equalsIgnoreCase(name) == true) {
            return this.remind_flag;
        } else if ("awoke_time".equalsIgnoreCase(name) == true) {
            return this.awoke_time;
        } else if ("arrange".equalsIgnoreCase(name) == true) {
            return this.arrange;
        } else if ("arrangename".equalsIgnoreCase(name) == true) {
            return this.arrangename;
        }

        else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("calendar".equalsIgnoreCase(name) == true) {
            this.calendar = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            this.user_name = value;
        } else if ("begin_time".equalsIgnoreCase(name) == true) {
            this.begin_time = value;
        } else if ("end_time".equalsIgnoreCase(name) == true) {
            this.end_time = value;
        } else if ("task_type".equalsIgnoreCase(name) == true) {
            this.task_type = value;
        } else if ("task_sum".equalsIgnoreCase(name) == true) {
            this.task_sum = value;
        } else if ("task_content".equalsIgnoreCase(name) == true) {
            this.task_content = value;
        } else if ("remind_flag".equalsIgnoreCase(name) == true) {
            this.remind_flag = value;
        } else if ("awoke_time".equalsIgnoreCase(name) == true) {
            this.awoke_time = value;
        } else if ("arrange".equalsIgnoreCase(name) == true) {
            this.arrange = value;
        } else if ("arrangename".equalsIgnoreCase(name) == true) {
            this.arrangename = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("calendar".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("begin_time".equalsIgnoreCase(name) == true) {
            colType = "time";
        } else if ("end_time".equalsIgnoreCase(name) == true) {
            colType = "time";
        } else if ("task_type".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("remind_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("awoke_time".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "DO_CALENDAR_ARRANGE";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "calendar,user_code,begin_time,end_time,task_type,task_sum,task_content,remind_flag,awoke_time,arrange,";
    }

    public String getAllFields() {
        return "serial_num,calendar,user_code,begin_time,end_time,task_type,task_sum,task_content,remind_flag,awoke_time,arrange,user_name,arrangename,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.calendar = values[calendar_col];
        this.user_code = values[user_code_col];
        this.begin_time = values[begin_time_col];
        this.end_time = values[end_time_col];
        this.task_type = values[task_type_col];
        this.task_sum = values[task_sum_col];
        this.task_content = values[task_content_col];
        this.remind_flag = values[remind_flag_col];
        this.awoke_time = values[awoke_time_col];
        this.arrange = values[arrange_col];
        this.user_name = values[user_name_col];
        this.arrangename = values[arrangename_col];
    }

    public void setOtherProperty(String[] values) {
        if (!StringUtils.isNull(this.begin_time) &&
   (begin_time.lastIndexOf("."))!=-1 ){
           // this.begin_time.length() > 5) {
            //this.begin_time = this.begin_time.substring(0, 5);

            //db2和oracle下的类型不同，db2下是time(12:00:00.000000),
            //oracle下是date(2004-10-29 12:00:00.000000);
            int docindex = begin_time.lastIndexOf(".");
            begin_time=begin_time.substring(docindex-8,docindex-3);

        }
        if (!StringUtils.isNull(this.calendar) && this.calendar.length() > 10) {
            this.calendar = this.calendar.substring(0, 10);
        }
        if (!StringUtils.isNull(this.end_time) &&
   end_time.lastIndexOf(".") !=-1){
           // this.end_time.length() > 5) {
            //this.end_time = this.end_time.substring(0, 5);
            //db2和oracle下的类型不同，db2下是time(12:00:00.000000),
            //oracle下是date(2004-10-29 12:00:00.000000);
            int docindex = end_time.lastIndexOf(".");
            this.end_time=this.end_time.substring(docindex-8,docindex-3);

        }
        if (!StringUtils.isNull(this.user_code)) {
            try {
                this.user_name = (new com.gever.sysman.api.OrganizationUtil()).
                    getUserNamesByUserdIDs(this.user_code);
            } catch (DefaultException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getArrangename() {
        return arrangename;
    }

    public void setArrangename(String arrangename) {
        this.arrangename = arrangename;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
