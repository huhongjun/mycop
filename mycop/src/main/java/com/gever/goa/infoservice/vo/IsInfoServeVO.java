package com.gever.goa.infoservice.vo;

import com.gever.util.Codes;
import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 组织信息VO类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsInfoServeVO extends BaseVO implements VOInterface {
    private static final int serial_num_col = 0; // SERIAL_NUM相对应的列数
    private static final int title_col = 1; // TITLE相对应的列数
    private static final int info_type_col = 2; // INFO_TYPE相对应的列数
    private static final int dept_col = 3; // DEPT相对应的列数
    private static final int user_code_col = 4; // USER_CODE相对应的列数
    private static final int create_date_col = 5; // CREATE_DATE相对应的列数
    private static final int file_path_col = 6; // FILE_PATH相对应的列数
    private static final int file_name_col = 7; // FILE_NAME相对应的列数
    private static final int word_content_col = 8; // WORD_CONTENT相对应的列数
    private static final int content_col = 9; // CONTENT相对应的列数
    private static final int hint_flag_col = 10; // HINT_FLAG相对应的列数
    private static final int send_flag_col = 11; // SEND_FLAG相对应的列数
    private static final int editor_type_col = 12; // EDITOR_TYPE相对应的列数
    private static final int info_flag_col = 13; // INFO_FLAG相对应的列数
    private static final int type_name_col = 14;
    private static final int user_name_col = 15;
    private static final int show_flag_col = 16; // show_flag对应的列数
    private String serial_num = ""; // SERIAL_NUM
    private String title = ""; // TITLE
    private String info_type = ""; // INFO_TYPE
    private String dept = ""; // DEPT
    private String user_code = ""; // USER_CODE
    private String create_date = ""; // CREATE_DATE
    private String file_path = ""; // FILE_PATH
    private String file_name = ""; // FILE_NAME
    private String word_content = ""; // WORD_CONTENT
    private byte[] content; // CONTENT
    private String hint_flag = ""; // HINT_FLAG
    private String send_flag = ""; // SEND_FLAG
    private String editor_type = ""; // EDITOR_TYPE
    private String info_flag = ""; // INFO_FLAG
    private String type_name = "";
    private String user_name = "";
    private String show_flag = ""; //show_falg

    public IsInfoServeVO() {
    }

    public String getSerial_num() {
        return this.serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo_type() {
        return this.info_type;
    }

    public void setInfo_type(String info_type) {
        this.info_type = info_type;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getUser_code() {
        return this.user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getCreate_date() {
        return this.create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getFile_path() {
        return this.file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_name() {
        return this.file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getWord_content() {
        return this.word_content;
    }

    public void setWord_content(String word_content) {
        this.word_content = word_content;
    }

    /**
     * @return Returns the content.
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * @param content The content to set.
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getHint_flag() {
        return this.hint_flag;
    }

    public void setHint_flag(String hint_flag) {
        this.hint_flag = hint_flag;
    }

    public String getSend_flag() {
        return this.send_flag;
    }

    public void setSend_flag(String send_flag) {
        this.send_flag = send_flag;
    }

    public String getEditor_type() {
        return this.editor_type;
    }

    public void setEditor_type(String editor_type) {
        this.editor_type = editor_type;
    }

    public String getInfo_flag() {
        return this.info_flag;
    }

    public void setInfo_flag(String info_flag) {
        this.info_flag = info_flag;
    }

    public String getValue(String name) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            return this.serial_num;
        } else if ("title".equalsIgnoreCase(name) == true) {
            return this.title;
        } else if ("info_type".equalsIgnoreCase(name) == true) {
            return this.info_type;
        } else if ("dept".equalsIgnoreCase(name) == true) {
            return this.dept;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            return this.create_date;
        } else if ("file_path".equalsIgnoreCase(name) == true) {
            return this.file_path;
        } else if ("file_name".equalsIgnoreCase(name) == true) {
            return this.file_name;
        } else if ("word_content".equalsIgnoreCase(name) == true) {
            return this.word_content;
        } else if ("content".equalsIgnoreCase(name) == true) {
            return "";
        } else if ("hint_flag".equalsIgnoreCase(name) == true) {
            return this.hint_flag;
        } else if ("send_flag".equalsIgnoreCase(name) == true) {
            return this.send_flag;
        } else if ("editor_type".equalsIgnoreCase(name) == true) {
            return this.editor_type;
        } else if ("info_flag".equalsIgnoreCase(name) == true) {
            return this.info_flag;
        } else if ("type_name".equalsIgnoreCase(name) == true) {
            return this.type_name;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            return this.user_name;
        } else if ("show_flag".equalsIgnoreCase(name) == true) {
            return this.show_flag;

            /*${cursor}*/
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("serial_num".equalsIgnoreCase(name) == true) {
            this.serial_num = value;
        } else if ("title".equalsIgnoreCase(name) == true) {
            this.title = value;
        } else if ("info_type".equalsIgnoreCase(name) == true) {
            this.info_type = value;
        } else if ("dept".equalsIgnoreCase(name) == true) {
            this.dept = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            this.create_date = value;
        } else if ("file_path".equalsIgnoreCase(name) == true) {
            this.file_path = value;
        } else if ("file_name".equalsIgnoreCase(name) == true) {
            this.file_name = value;
        } else if ("word_content".equalsIgnoreCase(name) == true) {
            this.word_content = value;
        } else if ("content".equalsIgnoreCase(name) == true) {
            this.content = Codes.decode(value.toCharArray());
        } else if ("hint_flag".equalsIgnoreCase(name) == true) {
            this.hint_flag = value;
        } else if ("send_flag".equalsIgnoreCase(name) == true) {
            this.send_flag = value;
        } else if ("editor_type".equalsIgnoreCase(name) == true) {
            this.editor_type = value;
        } else if ("info_flag".equalsIgnoreCase(name) == true) {
            this.info_flag = value;
        } else if ("type_name".equalsIgnoreCase(name) == true) {
            this.type_name = value;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            this.user_name = value;
        } else if ("show_flag".equalsIgnoreCase(name) == true) {
            this.show_flag = value;

            /*${cursor}*/
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();

        if ("user_code".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("content".equalsIgnoreCase(name) == true) {
            colType = "bytes";
        } else if ("hint_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("send_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("editor_type".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("info_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("show_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("content".equalsIgnoreCase(name) == true) {
            colType = "bytes";
        } else {
            colType = "String";
        }

        return colType;
    }

    public String getTableName() {
        return "Is_Info_serve";
    }

    public String getPkFields() {
        return "serial_num,";
    }

    public String getModifyFields() {
        return "title,info_type,dept,user_code,create_date,file_path,file_name,word_content,hint_flag,send_flag,editor_type,info_flag,show_flag,content,";
    }

    public String getAllFields() {
        return "serial_num,title,info_type,dept,user_code,create_date,file_path,file_name,word_content,content,hint_flag,send_flag,editor_type,info_flag,show_flag,";
    }

    public void setValues(String[] values) {
        this.serial_num = values[serial_num_col];
        this.title = values[title_col];
        this.info_type = values[info_type_col];
        this.dept = values[dept_col];
        this.user_code = values[user_code_col];
        this.create_date = values[create_date_col];
        this.file_path = values[file_path_col];
        this.file_name = values[file_name_col];
        this.word_content = values[word_content_col];
        this.content = Codes.decode(values[content_col].toCharArray());
        this.hint_flag = values[hint_flag_col];
        this.send_flag = values[send_flag_col];
        this.editor_type = values[editor_type_col];
        this.info_flag = values[info_flag_col];
        this.show_flag = values[show_flag_col];
    }

    public void setOtherProperty(String[] values) {
        //截取时间格式长度
        if (!StringUtils.isNull(this.create_date) &&
                (this.create_date.length() > 10)) {
            this.create_date = this.create_date.substring(0, 10);
        }
    }

    /**
     * @return 返回 show_flag。
     */
    public String getShow_flag() {
        return show_flag;
    }

    /**
     * @param show_flag 要设置的 show_flag。
     */
    public void setShow_flag(String show_flag) {
        this.show_flag = show_flag;
    }

    /**
     * @return Returns the type_name.
     */
    public String getType_name() {
        return type_name;
    }

    /**
     * @param type_name The type_name to set.
     */
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    /**
     * @return Returns the user_name.
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name The user_name to set.
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
