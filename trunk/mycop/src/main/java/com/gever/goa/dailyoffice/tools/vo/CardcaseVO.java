package com.gever.goa.dailyoffice.tools.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: cardcase中的vo对象</p>
 * <p>Description: 是DO_CARDCASE的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class CardcaseVO extends BaseVO
    implements VOInterface {
    public CardcaseVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    //private String user_code = ""; // USER_CODE
    private String type_code = ""; //type_code
    private String customer = ""; // CUSTOMER
    private String nickname = ""; // NICKNAME
    private String duty = ""; // DUTY
    private String company = ""; // COMPANY
    private String address = ""; // ADDRESS
    private String post_code = ""; // POST_CODE
    private String e_mail = ""; // E_MAIL
    private String phone = ""; // PHONE
    private String fax = ""; // FAX
    private String mobile = ""; // MOBILE
    private String home_page = ""; // HOME_PAGE
    private String remark = ""; // REMARK
    private String user_code = "";
    private String user_name = "";
    private String file_path = ""; //用来存入导入名片夹文件的路径
    private String file_name=""; //用来存入导入名片夹的文件名
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int customer_col = 1; // CUSTOMER相对应的列数
    private static final int type_code_col = 2; //type_code相对应的列数
    private static final int nickname_col = 3; // NICKNAME相对应的列数
    private static final int duty_col = 4; // DUTY相对应的列数
    private static final int company_col = 5; // COMPANY相对应的列数
    private static final int address_col = 6; // ADDRESS相对应的列数
    private static final int post_code_col = 7; // POST_CODE相对应的列数
    private static final int e_mail_col = 8; // E_MAIL相对应的列数
    private static final int phone_col = 9; // PHONE相对应的列数
    private static final int fax_col = 10; // FAX相对应的列数
    private static final int mobile_col = 11; // MOBILE相对应的列数
    private static final int home_page_col = 12; // HOME_PAGE相对应的列数
    private static final int remark_col = 13; // REMARK相对应的列数
    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getType_code() {
        return this.type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDuty() {
        return this.duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost_code() {
        return this.post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getE_mail() {
        return this.e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome_page() {
        return this.home_page;
    }

    public void setHome_page(String home_page) {
        this.home_page = home_page;
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
        }
        else if ("type_code".equalsIgnoreCase(name) == true) {
            return this.type_code;
        }
        else if ("customer".equalsIgnoreCase(name) == true) {
            return this.customer;
        }
        else if ("nickname".equalsIgnoreCase(name) == true) {
            return this.nickname;
        }
        else if ("duty".equalsIgnoreCase(name) == true) {
            return this.duty;
        }
        else if ("company".equalsIgnoreCase(name) == true) {
            return this.company;
        }
        else if ("address".equalsIgnoreCase(name) == true) {
            return this.address;
        }
        else if ("post_code".equalsIgnoreCase(name) == true) {
            return this.post_code;
        }
        else if ("e_mail".equalsIgnoreCase(name) == true) {
            return this.e_mail;
        }
        else if ("phone".equalsIgnoreCase(name) == true) {
            return this.phone;
        }
        else if ("fax".equalsIgnoreCase(name) == true) {
            return this.fax;
        }
        else if ("mobile".equalsIgnoreCase(name) == true) {
            return this.mobile;
        }
        else if ("home_page".equalsIgnoreCase(name) == true) {
            return this.home_page;
        }
        else if ("remark".equalsIgnoreCase(name) == true) {
            return this.remark;
        }
        else if ("user_name".equalsIgnoreCase(name) == true) {
            return this.user_name;
        }
        else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        }
        else if ("file_path".equalsIgnoreCase(name) == true) {
            return this.file_path;
        }
        else if ("file_name".equalsIgnoreCase(name)==true){
            return this.file_name;
        }
        else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        }
        else if ("type_code".equalsIgnoreCase(name) == true) {
            this.type_code = value;
        }
        else if ("customer".equalsIgnoreCase(name) == true) {
            this.customer = value;
        }
        else if ("nickname".equalsIgnoreCase(name) == true) {
            this.nickname = value;
        }
        else if ("duty".equalsIgnoreCase(name) == true) {
            this.duty = value;
        }
        else if ("company".equalsIgnoreCase(name) == true) {
            this.company = value;
        }
        else if ("address".equalsIgnoreCase(name) == true) {
            this.address = value;
        }
        else if ("post_code".equalsIgnoreCase(name) == true) {
            this.post_code = value;
        }
        else if ("e_mail".equalsIgnoreCase(name) == true) {
            this.e_mail = value;
        }
        else if ("phone".equalsIgnoreCase(name) == true) {
            this.phone = value;
        }
        else if ("fax".equalsIgnoreCase(name) == true) {
            this.fax = value;
        }
        else if ("mobile".equalsIgnoreCase(name) == true) {
            this.mobile = value;
        }
        else if ("home_page".equalsIgnoreCase(name) == true) {
            this.home_page = value;
        }
        else if ("remark".equalsIgnoreCase(name) == true) {
            this.remark = value;
        }
        else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        }
        else if ("user_name".equalsIgnoreCase(name) == true) {
            this.user_name = value;
        }
        else if ("file_path".equalsIgnoreCase(name) == true) {
            this.file_path=value;
        }
        else if("file_name".equalsIgnoreCase(name)==true){
            this.file_name=value;
        }
        else {
            return;
        }
    }

    public String getColType(String name) {
        //String colType = new String();

        return "String";
    }

    public String getTableName() {
        return "DO_CARDCASE";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "type_code,customer,nickname,duty,company,address,post_code,e_mail,phone,fax,mobile,home_page,remark,";
    }

    public String getAllFields() {
        return "serial_num,type_code,customer,nickname,duty,company,address,post_code,e_mail,phone,fax,mobile,home_page,remark,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.type_code = values[type_code_col];
        this.customer = values[customer_col];
        this.nickname = values[nickname_col];
        this.duty = values[duty_col];
        this.company = values[company_col];
        this.address = values[address_col];
        this.post_code = values[post_code_col];
        this.e_mail = values[e_mail_col];
        this.phone = values[phone_col];
        this.fax = values[fax_col];
        this.mobile = values[mobile_col];
        this.home_page = values[home_page_col];
        this.remark = values[remark_col];
    }

    public void setOtherProperty(String[] values) {
        this.file_path = this.getFile_path();
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    public String getFile_name() {
        return file_name;
    }
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}