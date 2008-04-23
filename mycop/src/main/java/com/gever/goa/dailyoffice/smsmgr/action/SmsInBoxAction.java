package com.gever.goa.dailyoffice.smsmgr.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.message.vo.MessageVO;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsFactory;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsInBoxDAO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.StringUtils;

/**
 * <p>Title:�����ռ��� </p>
 * <p>Description: �����ռ��������</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsInBoxAction extends BaseAction {
    private SmsInBoxDAO mDao;
    public SmsInBoxAction() {
    }

    /**
     * ��ʼ��ҳ������
     * @param cfg ��ǰ��vo����
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) {

        SmsInBoxForm myForm = (SmsInBoxForm) cfg.getBaseForm(); //�õ�form����
        mDao = SmsFactory.getInstance().createSmsInBoxDAO(super.dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) mDao); //���ø���dao

        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null)
            myForm.setVo(new MessageVO());
        return;
    }

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        String subWhere = cfg.getBaseForm().getSqlWhere();
        String defaultWhere = "  and msg_type='2' and receiver ='" +
                                cfg.getBaseForm().getUserId() + "'";

        if (StringUtils.isNull(subWhere)) {
            cfg.getBaseForm().setSqlWhere(defaultWhere);

        } else {
          int pos = (subWhere.indexOf("msg_type"));
          if (pos <=0)
               cfg.getBaseForm().setSqlWhere(defaultWhere);
        }

        super.toList(cfg);
        return this.FORWORD_LIST_PAGE;
    }

    protected String toView(GoaActionConfig cfg) throws DefaultException,
         Exception {
       String forwardpath = super.toView(cfg);
       SmsInBoxForm messageForm = (SmsInBoxForm) cfg.getBaseForm();
       MessageVO vo = (MessageVO) cfg.getBaseForm().getVo();
       mDao.updateReadFlag(vo); //����dao��update�������޸��Ķ���־���Ķ�ʱ��
       return forwardpath;
     }

}