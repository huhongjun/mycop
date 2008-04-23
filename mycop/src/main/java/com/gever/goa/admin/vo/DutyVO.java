package com.gever.goa.admin.vo;


import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title:职务 Duty中的vo对象</p>
 * <p>Description: 是的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class DutyVO extends BaseVO implements VOInterface {
    public DutyVO(){
    }
    private String duty_code = ""; // DUTY_CODE
    private String duty_name = ""; // DUTY_NAME
    private String remarks = ""; // REMARKS
    private String duty_code_bak="";
    private static final int duty_code_col = 0; // DUTY_CODE相对应的列数
    private static final int duty_name_col = 1; // DUTY_NAME相对应的列数
    private static final int remarks_col = 2; // REMARKS相对应的列数
    private static final int duty_code_bak_col = 2; // REMARKS相对应的列数
    public String getDuty_code() {
        return this.duty_code;
    }
    public void setDuty_code(String duty_code) {
        this.duty_code = duty_code;
    }
    public String getDuty_name() {
        return this.duty_name;
    }
    public void setDuty_name(String duty_name) {
        this.duty_name = duty_name;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getValue(String name) {
        if ("duty_code".equalsIgnoreCase(name) == true) {
             return  this.duty_code;
        } else if ("duty_name".equalsIgnoreCase(name) == true) {
             return  this.duty_name;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             return  this.remarks;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("duty_code".equalsIgnoreCase(name) == true) {
             this.duty_code = value ;
        } else if ("duty_name".equalsIgnoreCase(name) == true) {
             this.duty_name = value ;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             this.remarks = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();

        return  "String";
    }
    public String getTableName() {
        return "sys_duty";
    }
    public String getPkFields() {
        return "duty_code,";
    }
    public String getModifyFields() {
        return "duty_name,remarks,";
    }
    public String getAllFields() {
        return "duty_code,duty_name,remarks,";
    }
    public void setValues(String[] values) {
        this.duty_code = values[duty_code_col];
        this.duty_name = values[duty_name_col];
        this.remarks = values[remarks_col];
    }
    public void setOtherProperty(String[] values) {
      duty_code_bak=duty_code;

    }

    public String getDuty_code_bak() {
      return duty_code_bak;
    }

    public void setDuty_code_bak(String duty_code_bak) {
      this.duty_code_bak = duty_code_bak;
    }
    }
