package com.gever.goa.admin.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 民族National中的vo对象</p>
 * <p>Description: 是sys_national的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class NationVO extends BaseVO implements VOInterface {
    public NationVO(){
    }
    private String national_code = ""; // NATIONAL_CODE
    private String national_name = ""; // NATIONAL_NAME
    private String remarks = ""; // REMARKS
    private String national_code_bak="";
    private static final int national_code_col = 0; // NATIONAL_CODE相对应的列数
    private static final int national_name_col = 1; // NATIONAL_NAME相对应的列数
    private static final int remarks_col = 2; // REMARKS相对应的列数
    private static final int national_code_bak_col = 2; // REMARKS相对应的列数
    public String getNational_code() {
        return this.national_code;
    }
    public void setNational_code(String national_code) {
        this.national_code = national_code;
    }
    public String getNational_name() {
        return this.national_name;
    }
    public void setNational_name(String national_name) {
        this.national_name = national_name;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getValue(String name) {
        if ("national_code".equalsIgnoreCase(name) == true) {
             return  this.national_code;
        } else if ("national_name".equalsIgnoreCase(name) == true) {
             return  this.national_name;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             return  this.remarks;
           } else if ("national_code_bak".equalsIgnoreCase(name) == true) {
             return  this.national_code_bak;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("national_code".equalsIgnoreCase(name) == true) {
             this.national_code = value ;
        } else if ("national_name".equalsIgnoreCase(name) == true) {
             this.national_name = value ;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             this.remarks = value ;
           } else if ("national_code_bak".equalsIgnoreCase(name) == true) {
             this.national_code_bak = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();

        return  "String";
    }
    public String getTableName() {
        return "sys_national";
    }
    public String getPkFields() {
        return "national_code,";
    }
    public String getModifyFields() {
        return "national_name,remarks,";
    }
    public String getAllFields() {
        return "national_code,national_name,remarks,";
    }
    public void setValues(String[] values) {
        this.national_code = values[national_code_col];
        this.national_name = values[national_name_col];
        this.remarks = values[remarks_col];
        this.national_code_bak = values[national_code_bak_col];
    }
    public void setOtherProperty(String[] values) {
      national_code_bak=national_code;
    }
    public String getNational_code_bak() {
       return national_code_bak;
     }
     public void setNational_code_bak(String national_code_bak) {
       this.national_code_bak = national_code_bak;
     }
   }
