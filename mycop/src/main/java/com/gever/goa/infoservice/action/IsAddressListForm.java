package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;


/**
 * <p>Title: Ⱥ������Form</p>
 * <p>Description: KOBE OFFICE ��Ȩ���У�Υ�߱ؾ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsAddressListForm extends BaseForm {
    private String paraSimpleQuery; //�򵥲�ѯ����
    private String paraFlag; //�ж��Ƿ�Ϊ����Ⱥ�飬1Ϊ����Ⱥ�飬0Ϊ˽��Ⱥ��

    public IsAddressListForm() {
    }

    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }

    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

	/**
	 * @return ���� paraFlag��
	 */
	public String getParaFlag() {
		return paraFlag;
	}
	/**
	 * @param paraFlag Ҫ���õ� paraFlag��
	 */
	public void setParaFlag(String paraFlag) {
		this.paraFlag = paraFlag;
	}
}
