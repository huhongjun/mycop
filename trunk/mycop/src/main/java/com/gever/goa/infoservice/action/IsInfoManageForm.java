package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: 公共信息Form</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsInfoManageForm
    extends BaseForm {
    private String paraFlag; //导航参数
    private String paraSimpleQuery; //简单查询参数
    private String nodeID; //树结点ID
    private String nodeRemark;
    public IsInfoManageForm() {
    }

    public String getParaFlag() {
        return paraFlag;
    }

    public void setParaFlag(String paraFlag) {
        this.paraFlag = paraFlag;
    }

    public String getParaSimpleQuery() {
        return paraSimpleQuery;
    }

    public void setParaSimpleQuery(String paraSimpleQuery) {
        this.paraSimpleQuery = paraSimpleQuery;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

	public String getNodeRemark() {
		return nodeRemark;
	}

	public void setNodeRemark(String nodeRemark) {
		this.nodeRemark = nodeRemark;
	}

}