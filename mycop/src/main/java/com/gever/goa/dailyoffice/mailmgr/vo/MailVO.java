package com.gever.goa.dailyoffice.mailmgr.vo;


import java.util.ArrayList;
import java.util.List;

import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: Mail中的vo对象</p>
 * <p>Description: 是Mail的映射,包含表中的所有属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5  创建日期:
 */
public class MailVO extends BaseVO implements VOInterface {
    public MailVO(){
    }
    private String user_code = ""; // USER_CODE
    private String serial_num = ""; // SERIAL_NUM
    private String post_username = ""; // POST_USERNAME
    private String post_address = ""; // POST_ADDRESS
    private String receive_address = ""; // RECEIVE_ADDRESS
    private String copy_send = ""; // COPY_SEND
    private String dense_send = ""; // DENSE_SEND
    private String send_date = ""; // SEND_DATE
    private String title = ""; // TITLE
    private String old_maildir_id = ""; // OLD_MAILDIR_ID
    private String mail_dir_id = ""; // MAIL_DIR_ID
    private String re_flag = ""; // RE_FLAG
    private String read_flag = ""; // READ_FLAG
    private String content = ""; // CONTENT
    private String mail_type = ""; // MAIL_TYPE
    private String priority = ""; // PRIORITY
    private String mail_size = ""; // MAIL_SIZE
    private String attachcount = "0"; // ATTACHCOUNT
    private static final int user_code_col = 0; // USER_CODE相对应的列数
    private static final int serial_num_col = 1; // SERIAL_NUM相对应的列数
    private static final int post_username_col = 2; // POST_USERNAME相对应的列数
    private static final int post_address_col = 3; // POST_ADDRESS相对应的列数
    private static final int receive_address_col = 4; // RECEIVE_ADDRESS相对应的列数
    private static final int copy_send_col = 5; // COPY_SEND相对应的列数
    private static final int dense_send_col = 6; // DENSE_SEND相对应的列数
    private static final int send_date_col = 7; // SEND_DATE相对应的列数
    private static final int title_col = 8; // TITLE相对应的列数
    private static final int old_maildir_id_col = 9; // OLD_MAILDIR_ID相对应的列数
    private static final int mail_dir_id_col = 10; // MAIL_DIR_ID相对应的列数
    private static final int re_flag_col = 11; // RE_FLAG相对应的列数
    private static final int read_flag_col = 12; // READ_FLAG相对应的列数
    private static final int content_col = 13; // CONTENT相对应的列数
    private static final int mail_type_col = 14; // MAIL_TYPE相对应的列数
    private static final int priority_col = 15; // PRIORITY相对应的列数
    private static final int mail_size_col = 16; // MAIL_SIZE相对应的列数
    private static final int attachcount_col = 17; // ATTACHCOUNT相对应的列数
    private List attachList = new ArrayList(); //附件列表
    private String attachInfo = "";//附件信息
    private String copy_send_name = ""; // COPY_SEND_NAME
    private String dense_send_name = ""; // DENSE_SEND_NAME
    private String receive_address_name = ""; //收件人名列表
    private String unReadMailStyleBegin = ""; //未读邮件风格开始
    private String unReadMailStyleEnd = "";//未读邮件风格结束
    private String originalContent = "";//原件内容

    public String getUser_code() {
        return this.user_code;
    }
    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }
    public String getSerial_num() {
        return this.serial_num;
    }
    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }
    public String getPost_username() {
        return this.post_username;
    }
    public void setPost_username(String post_username) {
        this.post_username = post_username;
    }
    public String getPost_address() {
        return this.post_address;
    }
    public void setPost_address(String post_address) {
        this.post_address = post_address;
    }
    public String getReceive_address() {
        return this.receive_address;
    }
    public void setReceive_address(String receive_address) {
        this.receive_address = receive_address;
    }
    public String getCopy_send() {
        return this.copy_send;
    }
    public void setCopy_send(String copy_send) {
        this.copy_send = copy_send;
    }
    public String getDense_send() {
        return this.dense_send;
    }
    public void setDense_send(String dense_send) {
        this.dense_send = dense_send;
    }
    public String getSend_date() {
        return this.send_date;
    }
    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOld_maildir_id() {
        return this.old_maildir_id;
    }
    public void setOld_maildir_id(String old_maildir_id) {
        this.old_maildir_id = old_maildir_id;
    }
    public String getMail_dir_id() {
        return this.mail_dir_id;
    }
    public void setMail_dir_id(String mail_dir_id) {
        this.mail_dir_id = mail_dir_id;
    }
    public String getRe_flag() {
        return this.re_flag;
    }
    public void setRe_flag(String re_flag) {
        this.re_flag = re_flag;
    }
    public String getRead_flag() {
        return this.read_flag;
    }
    public void setRead_flag(String read_flag) {
        this.read_flag = read_flag;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getMail_type() {
        return this.mail_type;
    }
    public void setMail_type(String mail_type) {
        this.mail_type = mail_type;
    }
    public String getPriority() {
        return this.priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public String getMail_size() {
        return this.mail_size;
    }
    public void setMail_size(String mail_size) {
        this.mail_size = mail_size;
    }
    public String getAttachcount() {
        return this.attachcount;
    }
    public void setAttachcount(String attachcount) {
        this.attachcount = attachcount;
    }
    public String getValue(String name) {
        if ("user_code".equalsIgnoreCase(name) == true) {
             return  this.user_code;
        } else if ("serial_num".equalsIgnoreCase(name) == true) {
             return  this.serial_num;
        } else if ("post_username".equalsIgnoreCase(name) == true) {
             return  this.post_username;
        } else if ("post_address".equalsIgnoreCase(name) == true) {
             return  this.post_address;
        } else if ("receive_address".equalsIgnoreCase(name) == true) {
             return  this.receive_address;
        } else if ("copy_send".equalsIgnoreCase(name) == true) {
             return  this.copy_send;
        } else if ("dense_send".equalsIgnoreCase(name) == true) {
             return  this.dense_send;
        } else if ("send_date".equalsIgnoreCase(name) == true) {
             return  this.send_date;
        } else if ("title".equalsIgnoreCase(name) == true) {
             return  this.title;
        } else if ("old_maildir_id".equalsIgnoreCase(name) == true) {
             return  this.old_maildir_id;
        } else if ("mail_dir_id".equalsIgnoreCase(name) == true) {
             return  this.mail_dir_id;
        } else if ("re_flag".equalsIgnoreCase(name) == true) {
             return  this.re_flag;
        } else if ("read_flag".equalsIgnoreCase(name) == true) {
             return  this.read_flag;
        } else if ("content".equalsIgnoreCase(name) == true) {
             return  this.content;
        } else if ("mail_type".equalsIgnoreCase(name) == true) {
             return  this.mail_type;
        } else if ("priority".equalsIgnoreCase(name) == true) {
             return  this.priority;
        } else if ("mail_size".equalsIgnoreCase(name) == true) {
             return  this.mail_size;
        } else if ("attachcount".equalsIgnoreCase(name) == true) {
             return  this.attachcount;
        } else {
             return "";
        }
    }
    public void setValue(String name, String value) {
        if ("user_code".equalsIgnoreCase(name) == true) {
             this.user_code = value ;
        } else if ("serial_num".equalsIgnoreCase(name) == true) {
             this.serial_num = value ;
        } else if ("post_username".equalsIgnoreCase(name) == true) {
             this.post_username = value ;
        } else if ("post_address".equalsIgnoreCase(name) == true) {
             this.post_address = value ;
        } else if ("receive_address".equalsIgnoreCase(name) == true) {
             this.receive_address = value ;
        } else if ("copy_send".equalsIgnoreCase(name) == true) {
             this.copy_send = value ;
        } else if ("dense_send".equalsIgnoreCase(name) == true) {
             this.dense_send = value ;
        } else if ("send_date".equalsIgnoreCase(name) == true) {
             this.send_date = value ;
        } else if ("title".equalsIgnoreCase(name) == true) {
             this.title = value ;
        } else if ("old_maildir_id".equalsIgnoreCase(name) == true) {
             this.old_maildir_id = value ;
        } else if ("mail_dir_id".equalsIgnoreCase(name) == true) {
             this.mail_dir_id = value ;
        } else if ("re_flag".equalsIgnoreCase(name) == true) {
             this.re_flag = value ;
        } else if ("read_flag".equalsIgnoreCase(name) == true) {
             this.read_flag = value ;
        } else if ("content".equalsIgnoreCase(name) == true) {
             this.content = value ;
        } else if ("mail_type".equalsIgnoreCase(name) == true) {
             this.mail_type = value ;
        } else if ("priority".equalsIgnoreCase(name) == true) {
             this.priority = value ;
        } else if ("mail_size".equalsIgnoreCase(name) == true) {
             this.mail_size = value ;
        } else if ("attachcount".equalsIgnoreCase(name) == true) {
             this.attachcount = value ;
        } else {
             return ;
        }
    }
    public String getColType(String name) {
        String colType = new String();
        if ("user_code".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else if ("content".equalsIgnoreCase(name) == true) {
             colType = "clob";
        } else if ("mail_size".equalsIgnoreCase(name) == true) {
             colType = "long";
        } else if ("attachcount".equalsIgnoreCase(name) == true) {
             colType = "int";
        } else {
            colType = "String";
        }
        return colType;
    }
    public String getTableName() {
        return "Mail";
    }
    public String getPkFields() {
        return "serial_num,";
    }
    public String getModifyFields() {
        return "user_code,post_username,post_address,receive_address,copy_send,dense_send,send_date,title,old_maildir_id,mail_dir_id,re_flag,read_flag,mail_type,priority,mail_size,content,";
    }
    public String getAllFields() {
        return "user_code,serial_num,post_username,post_address,receive_address,copy_send,dense_send,send_date,title,old_maildir_id,mail_dir_id,re_flag,read_flag,mail_type,priority,mail_size,attachcount,content,";
    }
    public void setValues(String[] values) {
        this.user_code = values[user_code_col];
        this.serial_num = values[serial_num_col];
        this.post_username = values[post_username_col];
        this.post_address = values[post_address_col];
        this.receive_address = values[receive_address_col];
        this.copy_send = values[copy_send_col];
        this.dense_send = values[dense_send_col];
        this.send_date = values[send_date_col];
        this.title = values[title_col];
        this.old_maildir_id = values[old_maildir_id_col];
        this.mail_dir_id = values[mail_dir_id_col];
        this.re_flag = values[re_flag_col];
        this.read_flag = values[read_flag_col];
        this.content = values[content_col];
        this.mail_type = values[mail_type_col];
        this.priority = values[priority_col];
        this.mail_size = values[mail_size_col];
        this.attachcount = values[attachcount_col];
    }
    public void setOtherProperty(String[] values) {
        String mailSizeToKB = StringUtils.sizeToKB(this.getMail_size());
        String attachCount = this.getAttachcount().equals("")? "0" : this.getAttachcount();
        String unReadMailBegin = "1".equals(this.getRead_flag()) ? "" : "<b>";
        String unReadMailEnd = "1".equals(this.getRead_flag()) ? "" : "</b>";
        //String mailTitle = "".equals(this.getTitle().trim()) ? "没有标题" : this.getTitle();
        //this.setTitle(mailTitle);
        this.setUnReadMailStyleBegin(unReadMailBegin);
        this.setUnReadMailStyleEnd(unReadMailEnd);
        this.setAttachcount(attachCount);
        this.setMail_size(mailSizeToKB);
    }
    public List getAttachList() {
        return attachList;
    }
    public void setAttachList(List attachList) {
        this.attachList = attachList;
    }
    public String getDense_send_name() {
        return dense_send_name;
    }
    public void setDense_send_name(String dense_send_name) {
        this.dense_send_name = dense_send_name;
    }
    public String getCopy_send_name() {
        return copy_send_name;
    }
    public void setCopy_send_name(String copy_send_name) {
        this.copy_send_name = copy_send_name;
    }
    public String getReceive_address_name() {
        return receive_address_name;
    }
    public void setReceive_address_name(String receive_address_name) {
        this.receive_address_name = receive_address_name;
    }
    public String getUnReadMailStyleBegin() {
        return unReadMailStyleBegin;
    }
    public String getUnReadMailStyleEnd() {
        return unReadMailStyleEnd;
    }
    public void setUnReadMailStyleBegin(String unReadMailStyleBegin) {
        this.unReadMailStyleBegin = unReadMailStyleBegin;
    }
    public void setUnReadMailStyleEnd(String unReadMailStyleEnd) {
        this.unReadMailStyleEnd = unReadMailStyleEnd;
    }
    public String getOriginalContent() {
        return originalContent;
    }
    public void setOriginalContent(String originalContent) {
        this.originalContent = originalContent;
    }
	/**
	 * @return
	 */
	public String getAttachInfo() {
		return attachInfo;
	}

	/**
	 * @param string
	 */
	public void setAttachInfo(String string) {
		attachInfo = string;
	}

}
