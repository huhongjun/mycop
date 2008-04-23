package com.gever.goa.dailyoffice.mailmgr.action;

import com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO;
import com.gever.struts.form.BaseForm;
import com.gever.vo.VOInterface;
/**
 *
 * <p>Title:邮箱管理VO </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailboxMgrForm extends BaseForm {
    MailCapacityVO mailCapacityVo= new MailCapacityVO();
    private String nodeid="";
    public MailboxMgrForm() {
    }
    public void setVo(MailCapacityVO vo){
        this.mailCapacityVo=vo;
    }
    public VOInterface getVo(){
        return this.mailCapacityVo;
    }
    public String getNodeid() {
        return nodeid;
    }
    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }



}