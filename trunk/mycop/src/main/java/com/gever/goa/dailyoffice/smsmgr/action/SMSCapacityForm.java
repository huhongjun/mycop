package com.gever.goa.dailyoffice.smsmgr.action;

import com.gever.struts.form.BaseForm;

/**
 * <p>Title: ���Ź���</p>
 * <p>Description:���Ź��� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SMSCapacityForm extends BaseForm {
    private String defaultSize = "";  //Ĭ���ֽ�
    private String nodeid="";
    private String findName = "";
    public SMSCapacityForm() {
    }
    public String getDefaultSize() {
        return defaultSize;
    }
    public void setDefaultSize(String defaultSize) {
        this.defaultSize = defaultSize;
    }
    public String getNodeid() {
        return nodeid;
    }
    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }
    public String getFindName() {
        return findName;
    }
    public void setFindName(String findName) {
        this.findName = findName;
    }

}