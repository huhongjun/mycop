package com.gever.goa.infoservice.action;

import com.gever.goa.infoservice.vo.IsCustomerVO;
import com.gever.struts.form.BaseForm;

/**
 * <p>Title: �ͻ���ϵ��Form</p>
 * <p>Description: KOBE OFFICE ��Ȩ���У�Υ�߱ؾ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsCustomerTouchForm
    extends BaseForm {
    private String paraSimpleQuery;//�򵥲�ѯ����
    private String customer_code;
    private IsCustomerVO customerVO;
    public IsCustomerTouchForm() {
    }
    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }
    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

	/**
	 * @return
	 */
	public String getCustomer_code() {
		return customer_code;
	}

	/**
	 * @param string
	 */
	public void setCustomer_code(String string) {
		customer_code = string;
	}

	/**
	 * @return
	 */
	public IsCustomerVO getCustomerVO() {
		return customerVO;
	}

	/**
	 * @param customerVO
	 */
	public void setCustomerVO(IsCustomerVO customerVO) {
		this.customerVO = customerVO;
	}

}