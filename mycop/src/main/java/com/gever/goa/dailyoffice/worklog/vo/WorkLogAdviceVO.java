package com.gever.goa.dailyoffice.worklog.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: </p>
 * <p>Description: 工作日志建议VO 与DO_WORk_LOG_ADVICE对应</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: GEVER</p>
 * @author Hu.Walker
 * @version 0.9
 */

public class WorkLogAdviceVO
    extends BaseVO
    implements VOInterface {
    public WorkLogAdviceVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    private String op_date = ""; // OP_DATE
    private String adviser = ""; // ADVISER
    private String advice = ""; // ADVICE
    private String remark = ""; // REMARK
    private String username = ""; // USERNAME
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int op_date_col = 1; // OP_DATE相对应的列数
    private static final int adviser_col = 2; // ADVISER相对应的列数
    private static final int advice_col = 3; // ADVICE相对应的列数
    private static final int remark_col = 4; // REMARK相对应的列数
    private static final int username_col = 5; // USERNAME相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getOp_date() {
        return this.op_date;
    }

    public void setOp_date(String op_date) {
        this.op_date = op_date;
    }

    public String getAdviser() {
        return this.adviser;
    }

    public void setAdviser(String adviser) {
        this.adviser = adviser;
    }

    public String getAdvice() {
        return this.advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        }
        else if ("op_date".equalsIgnoreCase(name) == true) {
            return this.op_date;
        }
        else if ("adviser".equalsIgnoreCase(name) == true) {
            return this.adviser;
        }
        else if ("advice".equalsIgnoreCase(name) == true) {
            return this.advice;
        }
        else if ("remark".equalsIgnoreCase(name) == true) {
            return this.remark;
        }
        else if ("username".equalsIgnoreCase(name) == true) {
            return this.username;
        }
        else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        }
        else if ("op_date".equalsIgnoreCase(name) == true) {
            this.op_date = value;
        }
        else if ("adviser".equalsIgnoreCase(name) == true) {
            this.adviser = value;
        }
        else if ("advice".equalsIgnoreCase(name) == true) {
            this.advice = value;
        }
        else if ("remark".equalsIgnoreCase(name) == true) {
            this.remark = value;
        }
        else if ("username".equalsIgnoreCase(name) == true) {
            this.username = value;
        }
        else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();

        return "String";
    }

    public String getTableName() {
        return "DO_WORk_LOG_ADVICE";
    }

    public String getPkFields() {
        return "serial_num,op_date,adviser,";
    }

    public String getModifyFields() {
        return "serial_num,op_date,adviser,advice,remark,";
    }

    public String getAllFields() {
        return "serial_num,op_date,adviser,advice,remark,username,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.op_date = values[op_date_col];
        this.adviser = values[adviser_col];
        this.advice = values[advice_col];
        this.remark = values[remark_col];
        this.username = values[username_col];
    }

    public void setOtherProperty(String[] values) {
    }
}