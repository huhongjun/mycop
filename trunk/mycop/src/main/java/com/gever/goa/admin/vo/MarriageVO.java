package com.gever.goa.admin.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 婚姻Marriage中的vo对象</p>
 * <p>Description: 是sys_marriage的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class MarriageVO extends BaseVO implements VOInterface {
    public MarriageVO(){
    }
    private String marriage_code = ""; // MARRIAGE_CODE
    private String marriage = ""; // MARRIAGE
    private String remarks = ""; // REMARKS
    private String marriage_code_bak="";
    private static final int marriage_code_col = 0; // MARRIAGE_CODE相对应的列数
    private static final int marriage_col = 1; // MARRIAGE相对应的列数
    private static final int remarks_col = 2; // REMARKS相对应的列数
    public String getMarriage_code() {
        return this.marriage_code;
    }
    public void setMarriage_code(String marriage_code) {
        this.marriage_code = marriage_code;
    }
    public String getMarriage() {
        return this.marriage;
    }
    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }
    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getValue(String name) {
        if ("marriage_code".equalsIgnoreCase(name) == true) {
             return  this.marriage_code;
        } else if ("marriage".equalsIgnoreCase(name) == true) {
             return  this.marriage;
        } else if ("remarks".equalsIgnoreCase(name) == true) {
             return  this.remarks;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("marriage_code".equalsIgnoreCase(name) == true) {
             this.marriage_code = value ;
        } else if ("marriage".equalsIgnoreCase(name) == true) {
             this.marriage = value ;
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
        return "sys_marriage";
    }
    public String getPkFields() {
        return "marriage_code,";
    }
    public String getModifyFields() {
        return "marriage,remarks,";
    }
    public String getAllFields() {
        return "marriage_code,marriage,remarks,";
    }
    public void setValues(String[] values) {
        this.marriage_code = values[marriage_code_col];
        this.marriage = values[marriage_col];
        this.remarks = values[remarks_col];
    }
    public void setOtherProperty(String[] values) {
      marriage_code_bak=marriage_code;
    }
    public void setMarriage_code_bak(String marriage_code_bak){
      this.marriage_code_bak=marriage_code_bak;
    }
    public String getMarriage_code_bak(){
      return marriage_code_bak;
    }
}
