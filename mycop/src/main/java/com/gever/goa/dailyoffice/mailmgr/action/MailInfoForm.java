package com.gever.goa.dailyoffice.mailmgr.action;

import com.gever.goa.dailyoffice.mailmgr.vo.MailInfoVO;
import com.gever.struts.form.BaseForm;

public class MailInfoForm extends BaseForm {
    public MailInfoForm() {
    }
    private MailInfoVO infoVo = new MailInfoVO();

    public void setInfoVo(MailInfoVO vo) {
        this.infoVo = vo;
    }

    public MailInfoVO getInfoVo() {
        return this.infoVo;
    }
}
