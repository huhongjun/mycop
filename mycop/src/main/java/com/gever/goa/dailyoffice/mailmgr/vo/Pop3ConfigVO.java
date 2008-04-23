package com.gever.goa.dailyoffice.mailmgr.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: POP3Config中的vo对象</p>
 * <p>Description: 是POP3Config的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class Pop3ConfigVO extends BaseVO
    implements VOInterface {
    public Pop3ConfigVO() {
    }

    private String serial_num = ""; // SERIAL_NUM
    private String user_code = ""; // USER_CODE
    private String pop3_name = ""; // POP3_NAME
    private String pop3_address = ""; // POP3_ADDRESS
    private String pop3_port = ""; //pop3端口
    private String pop3_account = ""; // POP3_ACCOUNT
    private String pop3_pwd = ""; // POP3_PWD
    private String auto_flag = ""; // AUTO_FLAG
    private String del_flag = ""; // DEL_FLAG
    private String incept_mail_dir = ""; // INCEPT_MAIL_DIR
    private String show_name = ""; // SHOW_NAME
    private String show_address = ""; // SHOW_ADDRESS
    private String smtp_server = ""; // SMTP_SERVER
    private String smtp_port = ""; //smtp端口
    private String smtp_auth = ""; // SMTP_AUTH
    private String smtp_name = ""; // SMTP_NAME
    private String smtp_pwd = ""; // SMTP_PWD
    private String use_flag = ""; // USE_FLAG
    private String use_flag_name = ""; //对应use_flag的值
    private String mail_dir_name = ""; // MAIL_DIR_NAME
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int user_code_col = 1; // USER_CODE相对应的列数
    private static final int pop3_name_col = 2; // POP3_NAME相对应的列数
    private static final int pop3_address_col = 3; // POP3_ADDRESS相对应的列数
    private static final int pop3_account_col = 4; // POP3_ACCOUNT相对应的列数
    private static final int pop3_pwd_col = 5; // POP3_PWD相对应的列数
    private static final int auto_flag_col = 6; // AUTO_FLAG相对应的列数
    private static final int del_flag_col = 7; // DEL_FLAG相对应的列数
    private static final int incept_mail_dir_col = 8; // INCEPT_MAIL_DIR相对应的列数
    private static final int show_name_col = 9; // SHOW_NAME相对应的列数
    private static final int show_address_col = 10; // SHOW_ADDRESS相对应的列数
    private static final int smtp_server_col = 11; // SMTP_SERVER相对应的列数
    private static final int smtp_auth_col = 12; // SMTP_AUTH相对应的列数
    private static final int smtp_name_col = 13; // SMTP_NAME相对应的列数
    private static final int smtp_pwd_col = 14; // SMTP_PWD相对应的列数
    private static final int use_flag_col = 15; // USE_FLAG相对应的列数
    private static final int mail_dir_name_col = 16; // MAIL_DIR_NAME相对应的列数
    private String exteriorMailCount = "";
    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getPop3_name() {
        return this.pop3_name;
    }

    public void setPop3_name(String pop3_name) {
        this.pop3_name = pop3_name;
    }

    public String getPop3_address() {
        return this.pop3_address;
    }

    public void setPop3_address(String pop3_address) {
        this.pop3_address = pop3_address;
    }

    public String getPop3_account() {
        return this.pop3_account;
    }

    public void setPop3_account(String pop3_account) {
        this.pop3_account = pop3_account;
    }

    public String getPop3_pwd() {
        return this.pop3_pwd;
    }

    public void setPop3_pwd(String pop3_pwd) {
        this.pop3_pwd = pop3_pwd;
    }

    public String getAuto_flag() {
        return this.auto_flag;
    }

    public void setAuto_flag(String auto_flag) {
        this.auto_flag = auto_flag;
    }

    public String getDel_flag() {
        return this.del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    public String getIncept_mail_dir() {
        return this.incept_mail_dir;
    }

    public void setIncept_mail_dir(String incept_mail_dir) {
        this.incept_mail_dir = incept_mail_dir;
    }

    public String getShow_name() {
        return this.show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
    }

    public String getShow_address() {
        return this.show_address;
    }

    public void setShow_address(String show_address) {
        this.show_address = show_address;
    }

    public String getSmtp_server() {
        return this.smtp_server;
    }

    public void setSmtp_server(String smtp_server) {
        this.smtp_server = smtp_server;
    }

    public String getSmtp_auth() {
        return this.smtp_auth;
    }

    public void setSmtp_auth(String smtp_auth) {
        this.smtp_auth = smtp_auth;
    }

    public String getSmtp_name() {
        return this.smtp_name;
    }

    public void setSmtp_name(String smtp_name) {
        this.smtp_name = smtp_name;
    }

    public String getSmtp_pwd() {
        return this.smtp_pwd;
    }

    public void setSmtp_pwd(String smtp_pwd) {
        this.smtp_pwd = smtp_pwd;
    }

    public String getUse_flag() {
        return this.use_flag;
    }

    public void setUse_flag(String use_flag) {
        this.use_flag = use_flag;
    }

    public String getMail_dir_name() {
        return this.mail_dir_name;
    }

    public void setMail_dir_name(String mail_dir_name) {
        this.mail_dir_name = mail_dir_name;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        }
        else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        }
        else if ("pop3_name".equalsIgnoreCase(name) == true) {
            return this.pop3_name;
        }
        else if ("pop3_address".equalsIgnoreCase(name) == true) {
            return this.pop3_address;
        }
        else if ("pop3_account".equalsIgnoreCase(name) == true) {
            return this.pop3_account;
        }
        else if ("pop3_pwd".equalsIgnoreCase(name) == true) {
            return this.pop3_pwd;
        }
        else if ("auto_flag".equalsIgnoreCase(name) == true) {
            return this.auto_flag;
        }
        else if ("del_flag".equalsIgnoreCase(name) == true) {
            return this.del_flag;
        }
        else if ("incept_mail_dir".equalsIgnoreCase(name) == true) {
            return this.incept_mail_dir;
        }
        else if ("show_name".equalsIgnoreCase(name) == true) {
            return this.show_name;
        }
        else if ("show_address".equalsIgnoreCase(name) == true) {
            return this.show_address;
        }
        else if ("smtp_server".equalsIgnoreCase(name) == true) {
            return this.smtp_server;
        }
        else if ("smtp_auth".equalsIgnoreCase(name) == true) {
            return this.smtp_auth;
        }
        else if ("smtp_name".equalsIgnoreCase(name) == true) {
            return this.smtp_name;
        }
        else if ("smtp_pwd".equalsIgnoreCase(name) == true) {
            return this.smtp_pwd;
        }
        else if ("use_flag".equalsIgnoreCase(name) == true) {
            return this.use_flag;
        }
        else if ("mail_dir_name".equalsIgnoreCase(name) == true) {
            return this.mail_dir_name;
        }
        else if ("pop3_port".equalsIgnoreCase(name) == true) {
            return this.pop3_port;
        }
        else if ("smtp_port".equalsIgnoreCase(name) == true) {
            return this.smtp_port;
        }

        else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        }
        else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        }
        else if ("pop3_name".equalsIgnoreCase(name) == true) {
            this.pop3_name = value;
        }
        else if ("pop3_address".equalsIgnoreCase(name) == true) {
            this.pop3_address = value;
        }
        else if ("pop3_account".equalsIgnoreCase(name) == true) {
            this.pop3_account = value;
        }
        else if ("pop3_pwd".equalsIgnoreCase(name) == true) {
            this.pop3_pwd = value;
        }
        else if ("auto_flag".equalsIgnoreCase(name) == true) {
            this.auto_flag = value;
        }
        else if ("del_flag".equalsIgnoreCase(name) == true) {
            this.del_flag = value;
        }
        else if ("incept_mail_dir".equalsIgnoreCase(name) == true) {
            this.incept_mail_dir = value;
        }
        else if ("show_name".equalsIgnoreCase(name) == true) {
            this.show_name = value;
        }
        else if ("show_address".equalsIgnoreCase(name) == true) {
            this.show_address = value;
        }
        else if ("smtp_server".equalsIgnoreCase(name) == true) {
            this.smtp_server = value;
        }
        else if ("smtp_auth".equalsIgnoreCase(name) == true) {
            this.smtp_auth = value;
        }
        else if ("smtp_name".equalsIgnoreCase(name) == true) {
            this.smtp_name = value;
        }
        else if ("smtp_pwd".equalsIgnoreCase(name) == true) {
            this.smtp_pwd = value;
        }
        else if ("use_flag".equalsIgnoreCase(name) == true) {
            this.use_flag = value;
        }
        else if ("mail_dir_name".equalsIgnoreCase(name) == true) {
            this.mail_dir_name = value;
        }else if ("pop3_port".equalsIgnoreCase(name) == true) {
            this.pop3_port = value;
        }
        else if ("smtp_port".equalsIgnoreCase(name) == true) {
            this.smtp_port = value;
        }else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();
        if ("user_code".equalsIgnoreCase(name) == true) {
            colType = "int";
        }
        else {
            colType = "String";
        }
        return colType;
    }

    public String getTableName() {
        return "pop3mailsetup";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "user_code,pop3_name,pop3_address,pop3_account,pop3_pwd,auto_flag,del_flag,incept_mail_dir,show_name,show_address,smtp_server,smtp_auth,smtp_name,smtp_pwd,use_flag,";
    }

    public String getAllFields() {
        return "serial_num,user_code,pop3_name,pop3_address,pop3_account,pop3_pwd,auto_flag,del_flag,incept_mail_dir,show_name,show_address,smtp_server,smtp_auth,smtp_name,smtp_pwd,use_flag,mail_dir_name,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.user_code = values[user_code_col];
        this.pop3_name = values[pop3_name_col];
        this.pop3_address = values[pop3_address_col];
        this.pop3_account = values[pop3_account_col];
        this.pop3_pwd = values[pop3_pwd_col];
        this.auto_flag = values[auto_flag_col];
        this.del_flag = values[del_flag_col];
        this.incept_mail_dir = values[incept_mail_dir_col];
        this.show_name = values[show_name_col];
        this.show_address = values[show_address_col];
        this.smtp_server = values[smtp_server_col];
        this.smtp_auth = values[smtp_auth_col];
        this.smtp_name = values[smtp_name_col];
        this.smtp_pwd = values[smtp_pwd_col];
        this.use_flag = values[use_flag_col];
        this.mail_dir_name = values[mail_dir_name_col];
    }

    public void setOtherProperty(String[] values) {
        String use_flag = this.getUse_flag();
        if (use_flag.trim().equals("0")) {
            this.setUse_flag("正常");
        }
        else if (use_flag.trim().equals("1")) {
            this.setUse_flag("停用");
        }
        else if (use_flag.trim().equals("2")) {
            this.setUse_flag("默认帐号");
        }
       // this.setIncept_mail_dir(this.getMail_dir_name());

    }

    public String getExteriorMailCount() {
        return exteriorMailCount;
    }

    public void setExteriorMailCount(String exteriorMailCount) {
        this.exteriorMailCount = exteriorMailCount;
    }

    public String getSmtp_port() {
        return smtp_port;
    }

    public void setSmtp_port(String smtp_port) {
        this.smtp_port = smtp_port;
    }

    public String getPop3_port() {
        return pop3_port;
    }

    public void setPop3_port(String pop3_port) {
        this.pop3_port = pop3_port;
    }

}