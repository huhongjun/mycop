package com.gever.goa.admin.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: sex中的vo对象</p>
 * <p>Description: 是sys_sex的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class SexVO extends BaseVO implements VOInterface {
    public SexVO(){
    }
    private String sex_code = ""; // SEX_CODE
    private String sex_name = ""; // SEX_NAME
    private String remarks = ""; // REMARKS
    private String sex_code_bak="";
    private static final int sex_code_col = 0; // SEX_CODE相对应的列数
    private static final int sex_name_col = 1; // SEX_NAME相对应的列数
    private static final int remarks_col = 2; // REMARKS相对应的列数
    public String getSex_code() {
        return this.sex_code;
    }
    public void setSex_code(String sex_code) {
        this.sex_code = sex_code;
    }
    public String getSex_name() {
        return this.sex_name;
    }
    public void setSex_name(String sex_name) {
        this.sex_name = sex_name;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getValue(String name) {
        if ("sex_code".equalsIgnoreCase(name) == true) {
             return  this.sex_code;
        } else if ("sex_name".equalsIgnoreCase(name) == true) {
             return  this.sex_name;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             return  this.remarks;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("sex_code".equalsIgnoreCase(name) == true) {
             this.sex_code = value ;
        } else if ("sex_name".equalsIgnoreCase(name) == true) {
             this.sex_name = value ;
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
        return "sys_sex";
    }
    public String getPkFields() {
        return "sex_code,";
    }
    public String getModifyFields() {
        return "sex_name,remarks,";
    }
    public String getAllFields() {
        return "sex_code,sex_name,remarks,";
    }
    public void setValues(String[] values) {
        this.sex_code = values[sex_code_col];
        this.sex_name = values[sex_name_col];
        this.remarks = values[remarks_col];
    }

    public void setOtherProperty(String[] values) {
      sex_code_bak = sex_code;
    }

    public void setSex_code_bak(String sex_code_bak) {
      this.sex_code_bak = sex_code_bak;
    }

    public String getSex_code_bak() {
      return sex_code_bak;
}

}
