package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title:�ͻ���������VO </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0  ��������:2004-8-11
 */
public class IsCustomerTypeVO extends BaseVO implements VOInterface {
	public IsCustomerTypeVO() {
	}
	private String type_code = ""; // TYPE_CODE �ͻ����ʹ���
	private String type_name = ""; // TYPE_NAME �ͻ���������
	private String remark = ""; // REMARK
	private static final int type_code_col = 0; // TYPE_CODE���Ӧ������
	private static final int type_name_col = 1; // TYPE_NAME���Ӧ������
	private static final int remark_col = 2; // REMARK���Ӧ������
	public String getType_code() {
		return this.type_code;
	}
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	public String getType_name() {
		return this.type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getValue(String name) {
		if ("type_code".equalsIgnoreCase(name) == true) {
			return this.type_code;
		}
		else if ("type_name".equalsIgnoreCase(name) == true) {
			return this.type_name;
		}
		else if ("remark".equalsIgnoreCase(name) == true) {
			return this.remark;
		}
		else {
			return "";
		}
	}
	public void setValue(String name, String value) {
		if ("type_code".equalsIgnoreCase(name) == true) {
			this.type_code = value;
		}
		else if ("type_name".equalsIgnoreCase(name) == true) {
			this.type_name = value;
		}
		else if ("remark".equalsIgnoreCase(name) == true) {
			this.remark = value;
		}
		else {
			return;
		}
	}
	public String getColType(String name) {
		String colType = new String();
		if ("type_code".equalsIgnoreCase(name) == true) {
			colType = "int";
		}
		else {
			colType = "String";
		}
		return colType;
	}
	public String getTableName() {
		return "IS_CUSTOMER_TYPE";
	}
	public String getPkFields() {
		return "type_code,";
	}
	public String getModifyFields() {
		return "type_name,remark,";
	}
	public String getAllFields() {
		return "type_code,type_name,remark,";
	}
	public void setValues(String[] values) {
		this.type_code = values[type_code_col];
		this.type_name = values[type_name_col];
		this.remark = values[remark_col];
	}
	public void setOtherProperty(String[] values) {
	}
}