package com.gever.goa.infoservice.vo;

import com.gever.util.StringUtils;
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

public class IsStandardModelVO extends BaseVO implements VOInterface {
	public IsStandardModelVO() {
	}

	private String model_code = ""; // MODEL_CODE
	private String model_type = ""; // MODEL_TYPE
	private String create_date = ""; // CREATE_DATE
	private String issue_flag = ""; // ISSUE_FLAG
	private String model_name = ""; // MODEL_NAME
	private String user_code = ""; // USER_CODE
	private String file_path = ""; // FILE_PATH
	private String file_name = ""; // FILE_NAME
	private String word_content = ""; // WORD_CONTENT
	private String content = ""; // CONTENT
	private String editor_type = ""; // EDITOR_TYPE
	private String info_type = ""; // INFO_TYPE
	private String user_name = "";
	private static final int model_code_col = 0; // MODEL_CODE相对应的列数
	private static final int model_type_col = 1; // MODEL_TYPE相对应的列数
	private static final int create_date_col = 2; // CREATE_DATE相对应的列数
	private static final int issue_flag_col = 3; // ISSUE_FLAG相对应的列数
	private static final int model_name_col = 4; // MODEL_NAME相对应的列数
	private static final int user_code_col = 5; // USER_CODE相对应的列数
	private static final int file_path_col = 6; // FILE_PATH相对应的列数
	private static final int file_name_col = 7; // FILE_NAME相对应的列数
	private static final int word_content_col = 8; // WORD_CONTENT相对应的列数
	private static final int content_col = 9; // CONTENT相对应的列数
	private static final int editor_type_col = 10; // EDITOR_TYPE相对应的列数
	private static final int info_type_col = 11; // INFO_TYPE相对应的列数
	private static final int user_name_col = 12;

	public String getModel_code() {
		return this.model_code;
	}

	public void setModel_code(String model_code) {
		this.model_code = model_code;
	}

	public String getModel_type() {
		return this.model_type;
	}

	public void setModel_type(String model_type) {
		this.model_type = model_type;
	}

	public String getCreate_date() {
		return this.create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getIssue_flag() {
		return this.issue_flag;
	}

	public void setIssue_flag(String issue_flag) {
		this.issue_flag = issue_flag;
	}

	public String getModel_name() {
		return this.model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getUser_code() {
		return this.user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEditor_type() {
		return this.editor_type;
	}

	public void setEditor_type(String editor_type) {
		this.editor_type = editor_type;
	}

	public String getInfo_type() {
		return this.info_type;
	}

	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}

	public String getValue(String name) {
		if ("model_code".equalsIgnoreCase(name) == true) {
			return this.model_code;
		} else if ("model_type".equalsIgnoreCase(name) == true) {
			return this.model_type;
		} else if ("create_date".equalsIgnoreCase(name) == true) {
			return this.create_date;
		} else if ("issue_flag".equalsIgnoreCase(name) == true) {
			return this.issue_flag;
		} else if ("model_name".equalsIgnoreCase(name) == true) {
			return this.model_name;
		} else if ("user_code".equalsIgnoreCase(name) == true) {
			return this.user_code;
		} else if ("file_path".equalsIgnoreCase(name) == true) {
			return this.file_path;
		} else if ("file_name".equalsIgnoreCase(name) == true) {
			return this.file_name;
		} else if ("word_content".equalsIgnoreCase(name) == true) {
			return this.word_content;
		} else if ("content".equalsIgnoreCase(name) == true) {
			return this.content;
		} else if ("editor_type".equalsIgnoreCase(name) == true) {
			return this.editor_type;
		} else if ("info_type".equalsIgnoreCase(name) == true) {
			return this.info_type;
		} else {
			return "";
		}
	}

	public void setValue(String name, String value) {
		if ("model_code".equalsIgnoreCase(name) == true) {
			this.model_code = value;
		} else if ("model_type".equalsIgnoreCase(name) == true) {
			this.model_type = value;
		} else if ("create_date".equalsIgnoreCase(name) == true) {
			this.create_date = value;
		} else if ("issue_flag".equalsIgnoreCase(name) == true) {
			this.issue_flag = value;
		} else if ("model_name".equalsIgnoreCase(name) == true) {
			this.model_name = value;
		} else if ("user_code".equalsIgnoreCase(name) == true) {
			this.user_code = value;
		} else if ("file_path".equalsIgnoreCase(name) == true) {
			this.file_path = value;
		} else if ("file_name".equalsIgnoreCase(name) == true) {
			this.file_name = value;
		} else if ("word_content".equalsIgnoreCase(name) == true) {
			this.word_content = value;
		} else if ("content".equalsIgnoreCase(name) == true) {
			this.content = value;
		} else if ("editor_type".equalsIgnoreCase(name) == true) {
			this.editor_type = value;
		} else if ("info_type".equalsIgnoreCase(name) == true) {
			this.info_type = value;
		} else if ("user_name".equalsIgnoreCase(name) == true) {
			this.user_name = value;
		} else {
			return;
		}
	}

	public String getColType(String name) {
		String colType = new String();
		if ("model_type".equalsIgnoreCase(name) == true) {
			colType = "int";
		} else if ("create_date".equalsIgnoreCase(name) == true) {
			colType = "date";
		} else if ("issue_flag".equalsIgnoreCase(name) == true) {
			colType = "int";
		} else if ("user_code".equalsIgnoreCase(name) == true) {
			colType = "int";
		} else if ("word_content".equalsIgnoreCase(name) == true) {
			colType = "string";
		} else if ("content".equalsIgnoreCase(name) == true) {
			colType = "string";
		} else if ("editor_type".equalsIgnoreCase(name) == true) {
			colType = "int";
		} else if ("user_name".equalsIgnoreCase(name) == true) {
			colType = "int";
		} else {
			colType = "String";
		}
		return colType;
	}

	public String getTableName() {
		return "IS_STANDARD_MODEL";
	}

	public String getPkFields() {
		return "model_code,";
	}

	public String getModifyFields() {
		return "model_type,create_date,issue_flag,model_name,user_code,file_path,file_name,word_content,content,editor_type,info_type,";
	}

	public String getAllFields() {
		return "model_code,model_type,create_date,issue_flag,model_name,user_code,file_path,file_name,word_content,content,editor_type,info_type,";
	}

	public void setValues(String[] values) {
		this.model_code = values[model_code_col];
		this.model_type = values[model_type_col];
		this.create_date = values[create_date_col];
		this.issue_flag = values[issue_flag_col];
		this.model_name = values[model_name_col];
		this.user_code = values[user_code_col];
		this.file_path = values[file_path_col];
		this.file_name = values[file_name_col];
		this.word_content = values[word_content_col];
		this.content = values[content_col];
		this.editor_type = values[editor_type_col];
		this.info_type = values[info_type_col];
	}

	public void setOtherProperty(String[] values) {
		//截取时间格式长度
		if (!StringUtils.isNull(this.create_date)
						&& this.create_date.length() > 10) {
			this.create_date = this.create_date.substring(0, 10);

		}
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}