package com.gever.goa.dailyoffice.mailmgr.action;

import com.gever.goa.dailyoffice.tools.vo.CardcaseVO;
import com.gever.struts.form.BaseForm;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: </p>
 * <p>Description:��Ƭ�е��룬����ActionForm�� </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class MailboxForm extends BaseForm {
    private CardcaseVO vo= new CardcaseVO();

    public MailboxForm() {
    }

    public VOInterface getVo(){
        return (CardcaseVO) this.vo;
    }
    public void setVo(VOInterface vo){
        this.vo=(CardcaseVO) vo;
    }

}