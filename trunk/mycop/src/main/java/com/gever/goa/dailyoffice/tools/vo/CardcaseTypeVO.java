package com.gever.goa.dailyoffice.tools.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: CardcaseType中的vo对象</p>
 * <p>Description: 是do_cardcase_type的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class CardcaseTypeVO extends BaseVO implements VOInterface {
    public CardcaseTypeVO() {
    }

    private String type_code = ""; //type_code
    private String user_code = ""; // USER_CODE
    private String type_name = ""; // TYPE_NAME
    private String remark = "";
    private String subnum = ""; //该类别名片个数

    //private String type_name_bak="";//备份主键type_name
    private static final int type_code_col = 0; // TYPE_CODE相对应的列数
    private static final int user_code_col = 1; // USER_CODE相对应的列数
    private static final int type_name_col = 2; // TYPE_NAME相对应的列数
    private static final int remark_col = 3; // REMARK相对应的列数

    /*
     public String getType_name_bak(){
       return this.type_name_bak;
     }
     public void setType_name_bak(String type_name){
       this.type_name_bak=type_name;
     }
     */
    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getType_name() {
        return this.type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getValue(String name) {
        if ("type_code".equalsIgnoreCase(name) == true) {
            return this.type_code;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("type_name".equalsIgnoreCase(name) == true) {
            return this.type_name;
            /* }else if("type_name_bak".equalsIgnoreCase(name)){
                   return this.type_name_bak;*/
        } else if ("remark".equalsIgnoreCase(name) == true) {
            return this.remark;
        } else if ("subnum".equalsIgnoreCase(name) == true) {
            return this.subnum;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("type_code".equalsIgnoreCase(name) == true) {
            this.type_code = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("type_name".equalsIgnoreCase(name) == true) {
            this.type_name = value;
            /*} else if("type_name_bak".equalsIgnoreCase(name)){
                 this.type_name_bak=value;*/
        } else if ("remark".equalsIgnoreCase(name) == true) {
            this.remark = value;
        } else if ("subnum".equalsIgnoreCase(name) == true) {
            this.subnum = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        //String colType = new String();
        if ("user_code".equalsIgnoreCase(name)) {
            return "int";
        } else if ("subnum".equalsIgnoreCase(name)) {
            return "int";
        }
        return "String";
    }

    public String getTableName() {
        return "do_cardcase_type";
    }

    public String getPkFields() {
        return "type_code,";
    }

    public String getModifyFields() {
        return "user_code,type_name,remark,";
    }

    public String getAllFields() {
        return "type_code,user_code,type_name,remark,";
    }

    public void setValues(String[] values) {
        this.type_code = values[type_code_col];
        this.user_code = values[user_code_col];
        this.type_name = values[type_name_col];
        this.remark = values[remark_col];
    }

    public void setOtherProperty(String[] values) {
        // this.type_name_bak=type_name;
    }

    public String getSubnum() {
        return subnum;
    }

    public void setSubnum(String subnum) {
        this.subnum = subnum;
    }

}
