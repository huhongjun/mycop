package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: ��������Form</p>
 * <p>Description: KOBE OFFICE ��Ȩ���У�Υ�߱ؾ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SysInfoTypeForm
    extends BaseForm {
    private String paraSimpleQuery;//�򵥲�ѯ����
    public SysInfoTypeForm() {
    }
    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }
    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

}