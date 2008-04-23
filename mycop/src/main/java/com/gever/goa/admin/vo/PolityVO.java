package com.gever.goa.admin.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 中的vo对象</p>
 * <p>Description: 是select * from sys_polity的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class PolityVO extends BaseVO implements VOInterface {
    public PolityVO(){
    }
    private String polity_code = ""; // POLITY_CODE
    private String polity_name = ""; // POLITY_NAME
    private String remarks = ""; // REMARKS
    private String polity_code_bak="";
    private static final int polity_code_col = 0; // POLITY_CODE相对应的列数
    private static final int polity_name_col = 1; // POLITY_NAME相对应的列数
    private static final int remarks_col = 2; // REMARKS相对应的列数
    public String getPolity_code() {
        return this.polity_code;
    }
    public void setPolity_code(String polity_code) {
        this.polity_code = polity_code;
    }
    public String getPolity_name() {
        return this.polity_name;
    }
    public void setPolity_name(String polity_name) {
        this.polity_name = polity_name;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getValue(String name) {
        if ("polity_code".equalsIgnoreCase(name) == true) {
             return  this.polity_code;
        } else if ("polity_name".equalsIgnoreCase(name) == true) {
             return  this.polity_name;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             return  this.remarks;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("polity_code".equalsIgnoreCase(name) == true) {
             this.polity_code = value ;
        } else if ("polity_name".equalsIgnoreCase(name) == true) {
             this.polity_name = value ;
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
        return "sys_polity";
    }
    public String getPkFields() {
        return "polity_code,";
    }
    public String getModifyFields() {
        return "polity_name,remarks,";
    }
    public String getAllFields() {
        return "polity_code,polity_name,remarks,";
    }
    public void setValues(String[] values) {
        this.polity_code = values[polity_code_col];
        this.polity_name = values[polity_name_col];
        this.remarks = values[remarks_col];
    }
    public void setOtherProperty(String[] values) {
      polity_code_bak=polity_code;
    }
    public void setPolity_code_bak(String polity_code_bak){
    this.polity_code_bak=polity_code_bak;
  }
  public String getPolity_code_bak(){
    return polity_code_bak;
  }

}
