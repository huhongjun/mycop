package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: �ͻ�����Form</p>
 * <p>Description: KOBE OFFICE ��Ȩ���У�Υ�߱ؾ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsCustomerForm
    extends BaseForm {
    private String selectQuery;//�򵥲�ѯ����
    private String paraFlag;
	private String paraSimpleQuery=null;
    private String status;
    private String type;
    public IsCustomerForm() {
    }

    public String getSelectQuery() {
        return selectQuery;
    }
    public void setSelectQuery(String selectQuery) {
        this.selectQuery = selectQuery;
    }

    /**
     * @return Returns the paraFlag.
     */
    public String getParaFlag() {
        return paraFlag;
    }
    /**
     * @param paraFlag The paraFlag to set.
     */
    public void setParaFlag(String paraFlag) {
        this.paraFlag = paraFlag;
    }
	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param string
	 */
	public void setStatus(String string) {
		status = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

	/**
	 * @return
	 */
	public String getParaSimpleQuery() {
		return paraSimpleQuery;
	}

	/**
	 * @param string
	 */
	public void setParaSimpleQuery(String string) {
		paraSimpleQuery = string;
	}

}