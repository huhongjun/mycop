package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 标准模板VO类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsAddressListVO
    extends BaseVO
    implements VOInterface {
    public IsAddressListVO() {
    }
    private String serial_num="";
    private String type_flag = ""; // TYPE_FLAG
    private String group_name = ""; // GROUP_NAME
    private String user_code = ""; // USER_CODE
    private String member = ""; // member
    private String group_code = ""; // GROUP_CODE
    private String table_name = ""; // TABLE_NAME
    private String memberNames;
    private static final int serial_num_col=0;
    private static final int type_flag_col = 1; // TYPE_FLAG相对应的列数
    private static final int group_name_col = 2; // GROUP_NAME相对应的列数
    private static final int user_code_col = 3; // USER_CODE相对应的列数
    private static final int member_col = 4; // member相对应的列数
    private static final int group_code_col = 5; // GROUP_CODE相对应的列数
    private static final int table_name_col = 6; // TABLE_NAME相对应的列数
    private static final int memberNames_col = 7;
    public String getType_flag() {
        return this.type_flag;
    }

    public void setType_flag(String type_flag) {
        this.type_flag = type_flag;
    }

    public String getGroup_name() {
        return this.group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getGroup_code() {
        return this.group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public String getTable_name() {
        return this.table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getValue(String name) {
        if ("type_flag".equalsIgnoreCase(name) == true) {
            return this.type_flag;
        } else if ("group_name".equalsIgnoreCase(name) == true) {
            return this.group_name;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("member".equalsIgnoreCase(name) == true) {
            return this.member;
        } else if ("group_code".equalsIgnoreCase(name) == true) {
            return this.group_code;
        } else if ("table_name".equalsIgnoreCase(name) == true) {
            return this.table_name;
        } else if ("memberNames".equalsIgnoreCase(name) == true) {
            return this.memberNames;
        }else if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        }

        else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("type_flag".equalsIgnoreCase(name) == true) {
            this.type_flag = value;
        } else if ("group_name".equalsIgnoreCase(name) == true) {
            this.group_name = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("member".equalsIgnoreCase(name) == true) {
            this.member = value;
        } else if ("group_code".equalsIgnoreCase(name) == true) {
            this.group_code = value;
        } else if ("table_name".equalsIgnoreCase(name) == true) {
            this.table_name = value;
        } else if ("memberNames".equalsIgnoreCase(name) == true) {
            this.memberNames = value;
        } else if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num=value;
        }

        else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("type_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "IS_ADDRESS_LIST";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "type_flag,group_name,user_code,member,group_code,table_name,";
    }

    public String getAllFields() {
        return "serial_num,type_flag,group_name,user_code,member,group_code,table_name,";
    }

    public void setValues(String[] values) {
        this.serial_num=values[serial_num_col];
        this.type_flag = values[type_flag_col];
        this.group_name = values[group_name_col];
        this.user_code = values[user_code_col];
        this.member = values[member_col];
        this.group_code = values[group_code_col];
        this.table_name = values[table_name_col];
    }

    public void setOtherProperty(String[] values) {
    }

    public String getMemberNames() {
        return memberNames;
    }

    public void setMemberNames(String memberNames) {
        this.memberNames = memberNames;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
    public String getSerial_num() {
        return serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
}
