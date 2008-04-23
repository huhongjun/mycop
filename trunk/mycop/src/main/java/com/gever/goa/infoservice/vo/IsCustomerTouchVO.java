package com.gever.goa.infoservice.vo;

import com.gever.util.StringUtils;
import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 客户联系人VO类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsCustomerTouchVO extends BaseVO implements VOInterface {
	public IsCustomerTouchVO() {
	}

	private String customer = ""; // CUSTOMER
	private String customerbak = ""; // 不在表中显示的数据，做备份用
	private String customer_code = ""; // CUSTOMER_CODE
	private String sex_code = ""; // SEX_CODE
	private String job = ""; // JOB
	private String love = ""; // LOVE
	private String work_phone = ""; // WORK_PHONE
	private String home_phone = ""; // HOME_PHONE
	private String mobile = ""; // MOBILE
	private String e_mail = ""; // E_MAIL
	private String address = ""; // ADDRESS
	private String remark = ""; // REMARK
	private String birthday = ""; // BIRTHDAY
	private static final int customer_col = 0; // CUSTOMER相对应的列数
	private static final int customer_code_col = 1; // CUSTOMER_CODE相对应的列数
	private static final int sex_code_col = 2; // SEX_CODE相对应的列数
	private static final int job_col = 3; // JOB相对应的列数
	private static final int love_col = 4; // LOVE相对应的列数
	private static final int work_phone_col = 5; // WORK_PHONE相对应的列数
	private static final int home_phone_col = 6; // HOME_PHONE相对应的列数
	private static final int mobile_col = 7; // MOBILE相对应的列数
	private static final int e_mail_col = 8; // E_MAIL相对应的列数
	private static final int address_col = 9; // ADDRESS相对应的列数
	private static final int remark_col = 10; // REMARK相对应的列数
	private static final int birthday_col = 11; // BIRTHDAY相对应的列数

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomer_code() {
		return this.customer_code;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public String getSex_code() {
		return this.sex_code;
	}

	public void setSex_code(String sex_code) {
		this.sex_code = sex_code;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getLove() {
		return this.love;
	}

	public void setLove(String love) {
		this.love = love;
	}

	public String getWork_phone() {
		return this.work_phone;
	}

	public void setWork_phone(String work_phone) {
		this.work_phone = work_phone;
	}

	public String getHome_phone() {
		return this.home_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getE_mail() {
		return this.e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getValue(String name) {
		if ("customer".equalsIgnoreCase(name) == true) {
			return this.customer;
		} else if ("customer_code".equalsIgnoreCase(name) == true) {
			return this.customer_code;
		} else if ("sex_code".equalsIgnoreCase(name) == true) {
			return this.sex_code;
		} else if ("job".equalsIgnoreCase(name) == true) {
			return this.job;
		} else if ("love".equalsIgnoreCase(name) == true) {
			return this.love;
		} else if ("work_phone".equalsIgnoreCase(name) == true) {
			return this.work_phone;
		} else if ("home_phone".equalsIgnoreCase(name) == true) {
			return this.home_phone;
		} else if ("mobile".equalsIgnoreCase(name) == true) {
			return this.mobile;
		} else if ("e_mail".equalsIgnoreCase(name) == true) {
			return this.e_mail;
		} else if ("address".equalsIgnoreCase(name) == true) {
			return this.address;
		} else if ("remark".equalsIgnoreCase(name) == true) {
			return this.remark;
		} else if ("birthday".equalsIgnoreCase(name) == true) {
			return this.birthday;
		} else {
			return "";
		}
	}

	public void setValue(String name, String value) {
		if ("customer".equalsIgnoreCase(name) == true) {
			this.customer = value;
		} else if ("customer_code".equalsIgnoreCase(name) == true) {
			this.customer_code = value;
		} else if ("sex_code".equalsIgnoreCase(name) == true) {
			this.sex_code = value;
		} else if ("job".equalsIgnoreCase(name) == true) {
			this.job = value;
		} else if ("love".equalsIgnoreCase(name) == true) {
			this.love = value;
		} else if ("work_phone".equalsIgnoreCase(name) == true) {
			this.work_phone = value;
		} else if ("home_phone".equalsIgnoreCase(name) == true) {
			this.home_phone = value;
		} else if ("mobile".equalsIgnoreCase(name) == true) {
			this.mobile = value;
		} else if ("e_mail".equalsIgnoreCase(name) == true) {
			this.e_mail = value;
		} else if ("address".equalsIgnoreCase(name) == true) {
			this.address = value;
		} else if ("remark".equalsIgnoreCase(name) == true) {
			this.remark = value;
		} else if ("birthday".equalsIgnoreCase(name) == true) {
			this.birthday = value;
		} else {
			return;
		}
	}

	public String getColType(String name) {
		String colType = new String();
		if ("birthday".equalsIgnoreCase(name) == true) {
			colType = "date";
		} else {
			colType = "String";
		}
		return colType;
	}

	public String getTableName() {
		return "IS_CUSTOMER_TOUCH";
	}

	public String getPkFields() {
		return "customer,";
	}

	public String getModifyFields() {
		return "customer_code,sex_code,job,love,work_phone,home_phone,mobile,e_mail,address,remark,birthday,";
	}

	public String getAllFields() {
		return "customer,customer_code,sex_code,job,love,work_phone,home_phone,mobile,e_mail,address,remark,birthday,";
	}

	public void setValues(String[] values) {
		this.customer = values[customer_col];
		this.customer_code = values[customer_code_col];
		this.sex_code = values[sex_code_col];
		this.job = values[job_col];
		this.love = values[love_col];
		this.work_phone = values[work_phone_col];
		this.home_phone = values[home_phone_col];
		this.mobile = values[mobile_col];
		this.e_mail = values[e_mail_col];
		this.address = values[address_col];
		this.remark = values[remark_col];
		this.birthday = values[birthday_col];
	}

	public void setOtherProperty(String[] values) {
		this.setCustomerbak(this.customer);
		//截取时间格式长度
		if (!StringUtils.isNull(this.birthday) && this.birthday.length() > 10) {
			this.birthday = this.birthday.substring(0, 10);
		}
	}

	/**
	 * @return
	 */
	public String getCustomerbak() {
		return customerbak;
	}

	/**
	 * @param string
	 */
	public void setCustomerbak(String string) {
		customerbak = string;
	}

}