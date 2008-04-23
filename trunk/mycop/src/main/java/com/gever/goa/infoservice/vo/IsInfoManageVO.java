package com.gever.goa.infoservice.vo;

import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;


/**
 * <p>Title: 公共信息VO类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsInfoManageVO extends BaseVO implements VOInterface {
    private static final int title_col = 0; // TITLE相对应的列数
    private static final int info_type_col = 1; // INFO_TYPE相对应的列数
    private static final int user_code_col = 2; // USER_CODE相对应的列数
    private static final int create_date_col = 3; // CREATE_DATE相对应的列数
    private static final int file_path_col = 4; // FILE_PATH相对应的列数
    private static final int file_name_col = 5; // FILE_NAME相对应的列数
    private static final int url_col = 6; // URL相对应的列数
    private static final int content_col = 7; // CONTENT相对应的列数
    private static final int info_levle_col = 8; // INFO_LEVLE相对应的列数
    private static final int info_flag_col = 9; // INFO_FLAG相对应的列数
    private static final int parent_type_col = 10;
    private static final int user_name_col = 11;
    private static final int old_title_col = 12;
    private static final int old_info_type_col = 13;
    private String title = ""; // TITLE
    private String info_type = ""; // INFO_TYPE
    private String old_title = "";
    private String old_info_type = "";
    private String user_code = ""; // USER_CODE
    private String create_date = ""; // CREATE_DATE
    private String file_path = ""; // FILE_PATH
    private String file_name = ""; // FILE_NAME
    private String url = ""; // URL
    private String content = ""; // CONTENT
    private String info_levle = ""; // INFO_LEVLE
    private String info_flag = ""; // INFO_FLAG
    private String parent_type = "";
    private String user_name = "";

    public IsInfoManageVO() {
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfo_levle() {
        return this.info_levle;
    }

    public void setInfo_levle(String info_levle) {
        this.info_levle = info_levle;
    }

    public String getInfo_flag() {
        return this.info_flag;
    }

    public void setInfo_flag(String info_flag) {
        this.info_flag = info_flag;
    }

    public String getValue(String name) {
        if ("title".equalsIgnoreCase(name) == true) {
            return this.title;
        } else if ("info_type".equalsIgnoreCase(name) == true) {
            return this.info_type;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            return this.user_code;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            return this.create_date;
        } else if ("file_path".equalsIgnoreCase(name) == true) {
            return this.file_path;
        } else if ("file_name".equalsIgnoreCase(name) == true) {
            return this.file_name;
        } else if ("url".equalsIgnoreCase(name) == true) {
            return this.url;
        } else if ("content".equalsIgnoreCase(name) == true) {
            return this.content;
        } else if ("info_levle".equalsIgnoreCase(name) == true) {
            return this.info_levle;
        } else if ("info_flag".equalsIgnoreCase(name) == true) {
            return this.info_flag;
        } else if ("parent_type".equalsIgnoreCase(name) == true) {
            return this.parent_type;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            return this.user_name;
        } else {
            return "";
        }
    }

    public void setValue(String name, String value) {
        if ("title".equalsIgnoreCase(name) == true) {
            this.title = value;
        } else if ("info_type".equalsIgnoreCase(name) == true) {
            this.info_type = value;
        } else if ("user_code".equalsIgnoreCase(name) == true) {
            this.user_code = value;
        } else if ("create_date".equalsIgnoreCase(name) == true) {
            this.create_date = value;
        } else if ("file_path".equalsIgnoreCase(name) == true) {
            this.file_path = value;
        } else if ("file_name".equalsIgnoreCase(name) == true) {
            this.file_name = value;
        } else if ("url".equalsIgnoreCase(name) == true) {
            this.url = value;
        } else if ("content".equalsIgnoreCase(name) == true) {
            this.content = value;
        } else if ("info_levle".equalsIgnoreCase(name) == true) {
            this.info_levle = value;
        } else if ("info_flag".equalsIgnoreCase(name) == true) {
            this.info_flag = value;
        } else if ("parent_type".equalsIgnoreCase(name) == true) {
            this.parent_type = value;
        } else if ("user_name".equalsIgnoreCase(name) == true) {
            this.user_name = value;
        } else {
            return;
        }
    }

    public String getColType(String name) {
        String colType = new String();

        if ("create_date".equalsIgnoreCase(name) == true) {
            colType = "date";
        } else if ("info_levle".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("info_flag".equalsIgnoreCase(name) == true) {
            colType = "int";
        } else if ("content".equalsIgnoreCase(name) == true) {
            colType = "clob";
        } else {
            colType = "String";
        }

        return colType;
    }

    public String getTableName() {
        return "IS_INFO_MANAGE";
    }

    public String getPkFields() {
        return "title,info_type,";
    }

    public String getModifyFields() {
        return "user_code,create_date,file_path,file_name,url,info_levle,info_flag,content,";
    }

    public String getAllFields() {
        return "title,info_type,user_code,create_date,file_path,file_name,url,content,info_levle,info_flag,";
    }

    public void setValues(String[] values) {
        this.title = values[title_col];
        this.info_type = values[info_type_col];
        this.user_code = values[user_code_col];
        this.create_date = values[create_date_col];
        this.file_path = values[file_path_col];
        this.file_name = values[file_name_col];
        this.url = values[url_col];
        this.content = values[content_col];
        this.info_levle = values[info_levle_col];
        this.info_flag = values[info_flag_col];
    }

    public void setOtherProperty(String[] values) {
        this.old_title = this.title;
        this.old_info_type = this.info_type;

        //截取时间格式长度
        if (!StringUtils.isNull(this.create_date) &&
                (this.create_date.length() > 10)) {
            this.create_date = this.create_date.substring(0, 10);
        }
    }

    public String getParent_type() {
        return parent_type;
    }

    public void setParent_type(String parent_type) {
        this.parent_type = parent_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOld_title() {
        return old_title;
    }

    public void setOld_title(String old_title) {
        this.old_title = old_title;
    }

    public String getOld_info_type() {
        return old_info_type;
    }

    public void setOld_info_type(String old_info_type) {
        this.old_info_type = old_info_type;
    }
}
