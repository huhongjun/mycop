package com.gever.goa.infoservice.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: ������ϢForm</p>
 * <p>Description: KOBE OFFICE ��Ȩ���У�Υ�߱ؾ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class IsInfoManageForm
    extends BaseForm {
    private String paraFlag; //��������
    private String paraSimpleQuery; //�򵥲�ѯ����
    private String nodeID; //�����ID
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