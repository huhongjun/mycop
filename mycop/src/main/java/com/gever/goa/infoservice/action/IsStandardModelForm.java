package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: 标准模板Form</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsStandardModelForm extends BaseForm {
    private String paraSimpleQuery = ""; //简单查询参数

    private String paraFlag;

    private String paraInsert; //新增参数

    private String nodeID; //树结点ID
    
    private String infotype;

    public IsStandardModelForm() {
    }

    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }

    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

    public String getParaInsert() {
        return paraInsert;
    }

    public void setParaInsert(String paraInsert) {
        this.paraInsert = paraInsert;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
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
     * @return Returns the infotype.
     */
    public String getInfotype() {
        return infotype;
    }
    /**
     * @param infotype The infotype to set.
     */
    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }
}