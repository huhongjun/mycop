package com.gever.goa.infoservice.vo;

import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 客户资料VO类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsCustomerVO extends BaseVO implements VOInterface {
	public IsCustomerVO() {
	}

	private String customer_code = ""; // CUSTOMER_CODE
	private String customer_name = ""; // CUSTOMER_NAME
	private String phone = ""; // PHONE
	private String fax = ""; // FAX
	private String e_mail = ""; // E_MAIL
	private String home_page = ""; // HOME_PAGE
	private String address = ""; // ADDRESS
	private String post_code = ""; // POST_CODE
	private String secret_level = ""; // SECRET_LEVEL
	private String remark = ""; // REMARK
	private String open_bank = ""; // OPEN_BANK
	private String account = ""; // ACCOUNT
	private String flag = ""; // FLAG
	private String type_code = ""; // TYPE_CODE
	private String memory = ""; // MEMORY
	private static final int customer_code_col = 0; // CUSTOMER_CODE相对应的列数
	private static final int customer_name_col = 1; // CUSTOMER_NAME相对应的列数
	private static final int phone_col = 2; // PHONE相对应的列数
	private static final int fax_col = 3; // FAX相对应的列数
	private static final int e_mail_col = 4; // E_MAIL相对应的列数
	private static final int home_page_col = 5; // HOME_PAGE相对应的列数
	private static final int address_col = 6; // ADDRESS相对应的列数
	private static final int post_code_col = 7; // POST_CODE相对应的列数
	private static final int secret_level_col = 8; // SECRET_LEVEL相对应的列数
	private static final int remark_col = 9; // REMARK相对应的列数
	private static final int open_bank_col = 10; // OPEN_BANK相对应的列数
	private static final int account_col = 11; // ACCOUNT相对应的列数
	private static final int flag_col = 12; // FLAG相对应的列数
	private static final int type_code_col = 13; // TYPE_CODE相对应的列数
	private static final int memory_col = 14; // MEMORY相对应的列数

	public String getCustomer_code() {
		return this.customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getCustomer_name() {
		return this.customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
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

	public String getE_mail() {
		return this.e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getHome_page() {
		return this.home_page;
	}

	public void setHome_page(String home_page) {
		this.home_page = home_page;
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

	public String getSecret_level() {
		return this.secret_level;
	}

	public void setSecret_level(String secret_level) {
		this.secret_level = secret_level;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOpen_bank() {
		return this.open_bank;
	}

	public void setOpen_bank(String open_bank) {
		this.open_bank = open_bank;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getType_code() {
		return this.type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getMemory() {
		return this.memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getValue(String name) {
		if ("customer_code".equalsIgnoreCase(name) == true) {
			return this.customer_code;
		} else if ("customer_name".equalsIgnoreCase(name) == true) {
			return this.customer_name;
		} else if ("phone".equalsIgnoreCase(name) == true) {
			return this.phone;
		} else if ("fax".equalsIgnoreCase(name) == true) {
			return this.fax;
		} else if ("e_mail".equalsIgnoreCase(name) == true) {
			return this.e_mail;
		} else if ("home_page".equalsIgnoreCase(name) == true) {
			return this.home_page;
		} else if ("address".equalsIgnoreCase(name) == true) {
			return this.address;
		} else if ("post_code".equalsIgnoreCase(name) == true) {
			return this.post_code;
		} else if ("secret_level".equalsIgnoreCase(name) == true) {
			return this.secret_level;
		} else if ("remark".equalsIgnoreCase(name) == true) {
			return this.remark;
		} else if ("open_bank".equalsIgnoreCase(name) == true) {
			return this.open_bank;
		} else if ("account".equalsIgnoreCase(name) == true) {
			return this.account;
		} else if ("flag".equalsIgnoreCase(name) == true) {
			return this.flag;
		} else if ("type_code".equalsIgnoreCase(name) == true) {
			return this.type_code;
		} else if ("memory".equalsIgnoreCase(name) == true) {
			return this.memory;
		} else {
			return "";
		}
	}

	public void setValue(String name, String value) {
		if ("customer_code".equalsIgnoreCase(name) == true) {
			this.customer_code = value;
		} else if ("customer_name".equalsIgnoreCase(name) == true) {
			this.customer_name = value;
		} else if ("phone".equalsIgnoreCase(name) == true) {
			this.phone = value;
		} else if ("fax".equalsIgnoreCase(name) == true) {
			this.fax = value;
		} else if ("e_mail".equalsIgnoreCase(name) == true) {
			this.e_mail = value;
		} else if ("home_page".equalsIgnoreCase(name) == true) {
			this.home_page = value;
		} else if ("address".equalsIgnoreCase(name) == true) {
			this.address = value;
		} else if ("post_code".equalsIgnoreCase(name) == true) {
			this.post_code = value;
		} else if ("secret_level".equalsIgnoreCase(name) == true) {
			this.secret_level = value;
		} else if ("remark".equalsIgnoreCase(name) == true) {
			this.remark = value;
		} else if ("open_bank".equalsIgnoreCase(name) == true) {
			this.open_bank = value;
		} else if ("account".equalsIgnoreCase(name) == true) {
			this.account = value;
		} else if ("flag".equalsIgnoreCase(name) == true) {
			this.flag = value;
		} else if ("type_code".equalsIgnoreCase(name) == true) {
			this.type_code = value;
		} else if ("memory".equalsIgnoreCase(name) == true) {
			this.memory = value;
		} else {
			return;
		}
	}

	public String getColType(String name) {
		String colType = new String();
		if ("secret_level".equalsIgnoreCase(name) == true) {
			colType = "int";
		} else if ("flag".equalsIgnoreCase(name) == true) {
			colType = "int";
		} else if ("type_code".equalsIgnoreCase(name) == true) {
			colType = "int";
		} else if ("memory".equalsIgnoreCase(name) == true) {
			colType = "date";
		} else {
			colType = "String";
		}
		return colType;
	}

	public String getTableName() {
		return "IS_CUSTOMER";
	}

	public String getPkFields() {
		return "customer_code,";
	}

	public String getModifyFields() {
		return "customer_name,phone,fax,e_mail,home_page,address,post_code,secret_level,remark,open_bank,account,flag,type_code,memory,";
	}

	public String getAllFields() {
		return "customer_code,customer_name,phone,fax,e_mail,home_page,address,post_code,secret_level,remark,open_bank,account,flag,type_code,memory,";
	}

	public void setValues(String[] values) {
		this.customer_code = values[customer_code_col];
		this.customer_name = values[customer_name_col];
		this.phone = values[phone_col];
		this.fax = values[fax_col];
		this.e_mail = values[e_mail_col];
		this.home_page = values[home_page_col];
		this.address = values[address_col];
		this.post_code = values[post_code_col];
		this.secret_level = values[secret_level_col];
		this.remark = values[remark_col];
		this.open_bank = values[open_bank_col];
		this.account = values[account_col];
		this.flag = values[flag_col];
		this.type_code = values[type_code_col];
		this.memory = values[memory_col];
	}

	public void setOtherProperty(String[] values) {
		//截取时间格式长度
		if (!StringUtils.isNull(this.memory) && this.memory.length() > 10) {
			this.memory = this.memory.substring(0, 10);
		}
	}
}