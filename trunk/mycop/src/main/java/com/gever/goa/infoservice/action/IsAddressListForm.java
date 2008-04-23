package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;


/**
 * <p>Title: 群组设置Form</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsAddressListForm extends BaseForm {
    private String paraSimpleQuery; //简单查询参数
    private String paraFlag; //判断是否为公共群组，1为公共群组，0为私有群组

    public IsAddressListForm() {
    }

    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }

    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

	/**
	 * @return 返回 paraFlag。
	 */
	public String getParaFlag() {
		return paraFlag;
	}
	/**
	 * @param paraFlag 要设置的 paraFlag。
	 */
	public void setParaFlag(String paraFlag) {
		this.paraFlag = paraFlag;
	}
}
