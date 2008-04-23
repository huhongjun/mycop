package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: 信息类型设置Form</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsDoTypesForm extends BaseForm {
    private String paraFlag=""; //导航参数

    private String paraSimpleQuery="";//简单查询参数

    private String nodeid="";

    private String fmoduleflag;

    /**
     * @return Returns the fmodulefalg.
     */
    public String getFmoduleflag() {
        return fmoduleflag;
    }

    /**
     * @param fmodulefalg The fmodulefalg to set.
     */
    public void setFmoduleflag(String fmoduleflag) {
        this.fmoduleflag = fmoduleflag;
    }

    /**
     * @return Returns the nodeid.
     */
    public String getNodeid() {
        return nodeid;
    }

    /**
     * @param nodeid The nodeid to set.
     */
    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public IsDoTypesForm() {
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

    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }

    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

}