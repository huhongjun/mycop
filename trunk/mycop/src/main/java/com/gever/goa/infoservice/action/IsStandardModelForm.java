package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: ��׼ģ��Form</p>
 * <p>Description: KOBE OFFICE ��Ȩ���У�Υ�߱ؾ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsStandardModelForm extends BaseForm {
    private String paraSimpleQuery = ""; //�򵥲�ѯ����

    private String paraFlag;

    private String paraInsert; //��������

    private String nodeID; //�����ID
    
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