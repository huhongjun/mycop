package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: 类型设置Form</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SysInfoTypeForm
    extends BaseForm {
    private String paraSimpleQuery;//简单查询参数
    public SysInfoTypeForm() {
    }
    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }
    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

}