package com.gever.goa.dailyoffice.workreport.vo;

import com.gever.util.Codes;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: DoWorkReport中的vo对象</p>
 * <p>Description: 是DO_WORK_REPORT的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class DoWorkReportVO extends BaseVO implements VOInterface {
    public DoWorkReportVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    private String create_date = ""; // CREATE_DATE
    private String user_code = ""; // USER_CODE
    private String begin_time = ""; // BEGIN_TIME
    private String end_time = ""; // END_TIME
    private String render = ""; // RENDER
    private String checker = ""; // CHECKER
    private String check_date = ""; // CHECK_DATE// CONTENT
    private String send_flag = "0"; // SEND_FLAG
    private String user_name = "";
    private String render_name = "";
    private String checker_name = "";
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int create_date_col = 1; // CREATE_DATE相对应的列数
    private static final int user_code_col = 2; // USER_CODE相对应的列数
    private static final int begin_time_col = 3; // BEGIN_TIME相对应的列数
    private static final int end_time_col = 4; // END_TIME相对应的列数
    private static final int render_col = 5; // RENDER相对应的列数
    private static final int checker_col = 6; // CHECKER相对应的列数
    private static final int check_date_col = 7; // CHECK_DATE相对应的列数
    private static final int content_col = 8; // CONTENT相对应的列数
    private static final int send_flag_col = 9; // SEND_FLAG
    private static final int user_name_col = 10;
    private static final int render_name_col = 11;
    private static final int checker_name_col = 12;
    private byte[] content; // SEND_FLAG相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getCreate_date() {
        return this.create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
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

    public String getRender() {
        return this.render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getChecker() {
        return this.checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheck_date() {
        return this.check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getSend_flag() {
        return this.send_flag;
    }

    public void setSend_flag(String send_flag) {
        this.send_flag = send_flag;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            return this.create_date;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("begin_time".equalsIgnoreCase(name) == true) {
            return this.begin_time;
        } else if ("end_time".equalsIgnoreCase(name) == true) {
            return this.end_time;
        } else if ("render".equalsIgnoreCase(name) == true) {
            return this.render;
        } else if ("checker".equalsIgnoreCase(name) == true) {
            return this.checker;
        } else if ("render_name".equalsIgnoreCase(name) == true) {
            return this.render_name;
        } else if ("checker_name".equalsIgnoreCase(name) == true) {
            return this.checker_name;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            return this.user_name;
        } else if ("check_date".equalsIgnoreCase(name) == true) {
            return this.check_date;
        } else if ("content".equalsIgnoreCase(name) == true) {
            return "";
            //String.copyValueOf(Codes.encode(this.content));
        } else if ("send_flag".equalsIgnoreCase(name) == true) {
            return this.send_flag;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            this.create_date = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("begin_time".equalsIgnoreCase(name) == true) {
            this.begin_time = value;
        } else if ("end_time".equalsIgnoreCase(name) == true) {
            this.end_time = value;
        } else if ("render".equalsIgnoreCase(name) == true) {
            this.render = value;
        } else if ("checker".equalsIgnoreCase(name) == true) {
            this.checker = value;
        } else if ("render_name".equalsIgnoreCase(name) == true) {
            this.render_name = value;
        } else if ("checker_name".equalsIgnoreCase(name) == true) {
            this.checker_name = value;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            this.user_name = value;
        } else if ("check_date".equalsIgnoreCase(name) == true) {
            this.check_date = value;
        } else if ("content".equalsIgnoreCase(name) == true) {
            this.content = Codes.decode(value.toCharArray());
        } else if ("send_flag".equalsIgnoreCase(name) == true) {
            this.send_flag = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("create_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("begin_time".equalsIgnoreCase(name) == true) {
            colType = "timestamp";
        } else if ("end_time".equalsIgnoreCase(name) == true) {
            colType = "timestamp";
        } else if ("check_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("content".equalsIgnoreCase(name) == true) {
            colType = "bytes";
        } else if ("send_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "DO_WORK_REPORT";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "create_date,user_code,begin_time,end_time,render,checker,check_date,send_flag,content,";
    }

    public String getAllFields() {
        return "serial_num,create_date,user_code,begin_time,end_time,render,checker,check_date,send_flag,user_name,render_name,checker_name,content,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.create_date = values[create_date_col];
        this.user_code = values[user_code_col];
        this.begin_time = values[begin_time_col];
        this.end_time = values[end_time_col];
        this.render = values[render_col];
        this.checker = values[checker_col];
        this.check_date = values[check_date_col];
        this.content = Codes.decode(values[content_col].toCharArray());
        this.send_flag = values[send_flag_col];
        this.render_name = values[render_name_col];
        this.checker_name = values[checker_name_col];
        this.user_name = values[user_name_col];
    }

    public void setOtherProperty(String[] values) {
        if (! (this.begin_time == null || "".equals(this.begin_time) ||
               "null".equals(this.begin_time))) {
            if (this.begin_time.length() > 16) {
                this.begin_time = this.begin_time.substring(0, 16);
            }
        }
        if (! (this.end_time == null || "".equals(this.end_time) ||
               "null".equals(this.end_time))) {
            if (this.end_time.length() > 16) {
                this.end_time = this.end_time.substring(0, 16);
            }
        }
        if (!StringUtils.isNull(this.check_date) &&
            this.check_date.length() > 10) {
            this.check_date = this.check_date.substring(0, 10);
        }
        if (!StringUtils.isNull(this.create_date) &&
            this.create_date.length() > 10) {
            this.create_date = this.create_date.substring(0, 10);
        }
    }

    public String getChecker_name() {
        return checker_name;
    }

    public void setChecker_name(String checker_name) {
        this.checker_name = checker_name;
    }

    public String getRender_name() {
        return render_name;
    }

    public void setRender_name(String render_name) {
        this.render_name = render_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
