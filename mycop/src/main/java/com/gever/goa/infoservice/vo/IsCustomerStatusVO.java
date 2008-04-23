package com.gever.goa.infoservice.vo;

import com.gever.vo.BaseVO;
import com.gever.vo.VOInterface;

/**
 * <p>Title:�ͻ�״̬VO </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0  ��������:2004-8-11
 */
public class IsCustomerStatusVO extends BaseVO implements VOInterface {
	public IsCustomerStatusVO() {
	}
	private String flag = ""; // FLAG �ͻ�״̬
	private String status = ""; // STATUS ״̬����
	private String remark = ""; // REMARK ��ע
	private static final int flag_col = 0; // FLAG���Ӧ������
	private static final int status_col = 1; // STATUS���Ӧ������
	private static final int remark_col = 2; // REMARK���Ӧ������
	public String getFlag() {
		return this.flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getValue(String name) {
		if ("flag".equalsIgnoreCase(name) == true) {
			return this.flag;
		}
		else if ("status".equalsIgnoreCase(name) == true) {
			return this.status;
		}
		else if ("remark".equalsIgnoreCase(name) == true) {
			return this.remark;
		}
		else {
			return "";
		}
	}
	public void setValue(String name, String value) {
		if ("flag".equalsIgnoreCase(name) == true) {
			this.flag = value;
		}
		else if ("status".equalsIgnoreCase(name) == true) {
			this.status = value;
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
		if ("flag".equalsIgnoreCase(name) == true) {
			colType = "int";
		}
		else {
			colType = "String";
		}
		return colType;
	}
	public String getTableName() {
		return "IS_CUSTOMER_STATUS";
	}
	public String getPkFields() {
		return "flag,";
	}
	public String getModifyFields() {
		return "status,remark,";
	}
	public String getAllFields() {
		return "flag,status,remark,";
	}
	public void setValues(String[] values) {
		this.flag = values[flag_col];
		this.status = values[status_col];
		this.remark = values[remark_col];
	}
	public void setOtherProperty(String[] values) {
	}
}