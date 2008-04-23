package com.gever.goa.dailyoffice.smsmgr.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;
/**
 * <p>Title: SmsUser中的vo对象</p>
 * <p>Description: 是的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class SmsUserVO extends BaseVO implements VOInterface {
    public SmsUserVO(){
    }
    private String id = ""; // ID
    private String name = ""; // NAME
    private String mobile = ""; // MOBILE
    private String depts = ""; // DEPTS
    private static final int id_col = 0; // ID相对应的列数
    private static final int name_col = 1; // NAME相对应的列数
    private static final int mobile_col = 2; // MOBILE相对应的列数
    private static final int depts_col = 3; // DEPTS相对应的列数
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getDepts() {
        return this.depts;
    }
    public void setDepts(String depts) {
        this.depts = depts;
    }
    public String getValue(String name) {
        if ("id".equalsIgnoreCase(name) == true) {
             return  this.id;
        } else if ("name".equalsIgnoreCase(name) == true) {
             return  this.name;
        } else if ("mobile".equalsIgnoreCase(name) == true) {
             return  this.mobile;
        } else if ("depts".equalsIgnoreCase(name) == true) {
             return  this.depts;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("id".equalsIgnoreCase(name) == true) {
             this.id = value ;
        } else if ("name".equalsIgnoreCase(name) == true) {
             this.name = value ;
        } else if ("mobile".equalsIgnoreCase(name) == true) {
             this.mobile = value ;
        } else if ("depts".equalsIgnoreCase(name) == true) {
             this.depts = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("id".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "";
    }
    public String getPkFields() {
        return "";
    }
    public String getModifyFields() {
        return "id,name,mobile,depts,";
    }
    public String getAllFields() {
        return "id,name,mobile,depts,";
    }
    public void setValues(String[] values) {
        this.id = values[id_col];
        this.name = values[name_col];
        this.mobile = values[mobile_col];
        this.depts = values[depts_col];
    }
    public void setOtherProperty(String[] values) {
    }
}
