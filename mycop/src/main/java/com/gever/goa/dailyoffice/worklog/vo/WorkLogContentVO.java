package com.gever.goa.dailyoffice.worklog.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 工作日志内容中的vo对象</p>
 * <p>Description: 是DO_LOG_CONTENT的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0  创建日期:2004-09-07
 */
public class WorkLogContentVO extends BaseVO implements VOInterface {
    public WorkLogContentVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    private String tserial_num = ""; // TSERIAL_NUM
    private String log_content = ""; // LOG_CONTENT
    private String remark = ""; // REMARK
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int tserial_num_col = 1; // TSERIAL_NUM相对应的列数
    private static final int log_content_col = 2; // LOG_CONTENT相对应的列数
    private static final int remark_col = 3; // REMARK相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getTserial_num() {
        return this.tserial_num;
    }

    public void setTserial_num(String tserial_num) {
        this.tserial_num = tserial_num;
    }

    public String getLog_content() {
        return this.log_content;
    }

    public void setLog_content(String log_content) {
        this.log_content = log_content;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("tserial_num".equalsIgnoreCase(name) == true) {
            return this.tserial_num;
        } else if ("log_content".equalsIgnoreCase(name) == true) {
            return this.log_content;
        } else if ("remark".equalsIgnoreCase(name) == true) {
            return this.remark;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("tserial_num".equalsIgnoreCase(name) == true) {
            this.tserial_num = value;
        } else if ("log_content".equalsIgnoreCase(name) == true) {
            this.log_content = value;
        } else if ("remark".equalsIgnoreCase(name) == true) {
            this.remark = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        return "String";
    }

    public String getTableName() {
        return "DO_LOG_CONTENT";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "tserial_num,remark,log_content,";
    }

    public String getAllFields() {
        return "serial_num,tserial_num,remark,log_content,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.tserial_num = values[tserial_num_col];
        this.log_content = values[log_content_col];
        this.remark = values[remark_col];
    }

    public void setOtherProperty(String[] values) {
    }
}
