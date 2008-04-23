package com.gever.goa.dailyoffice.mailmgr.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.MailInfoDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.vo.MailInfoVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.form.BaseForm;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailInfoAction extends BaseAction {
    MailInfoDAO mailInfoDao = null;
    public MailInfoAction() {
    }

    /**
     * ��ʼ��ҳ������
     * @param actionform ��ǰ��vo����
     * @return ��ǰ���õ�vo����
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MailInfoForm myForm = (MailInfoForm) cfg.getBaseForm(); //�õ�form����
        mailInfoDao = MailMgrFactory.getInstance().creatMailInfo(super.dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) mailInfoDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null) {
            myForm.setVo(new MailInfoVO());
        }
    }

    /**
     * �ʼ��������·���
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
        Exception {
        //System.out.println("toEdit��ʼ");
        VOInterface vo = cfg.getBaseForm().getVo();
        String userId = this.getUserID(cfg); //�õ��û�ID
        vo.setValue("user_code", userId);
        mailInfoDao = (MailInfoDAO) cfg.getBaseDao();
        StringBuffer sb = new StringBuffer();
        sb.append(" and user_code=");
        sb.append(userId);
        cfg.getBaseDao().setSqlWhere(sb.toString());
        VOInterface voCur = (MailInfoVO) mailInfoDao.queryByPK(vo);
        String content=voCur.getValue("lable_name");
        voCur.setValue("lable_name",content.replaceAll("<br>",""));
        long count = mailInfoDao.queryByCount(vo);
        //System.out.println("����Ϊ--->" + count);
        if (count > 0) {
            cfg.getBaseForm().setActionType("modify");
        }
        else {
            cfg.getBaseForm().setActionType("insert");
        }
        MailInfoForm mailInfoForm = (MailInfoForm) cfg.getBaseForm();
        mailInfoForm.setVo(voCur);
        return this.FORWORD_EDIT_PAGE;
    }

    /**
     * �����ʼ���������
     * @param cfg
     * @param isBack
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        VOInterface vo = cfg.getBaseForm().getVo();
        BaseForm bForm = cfg.getBaseForm();
        String strContent = vo.getValue("lable_name"); ;
        vo.setValue("lable_name", strContent.replaceAll("\n","<br>"));
        vo.setValue("user_code", bForm.getUserId());
        cfg.getBaseDao().insert(vo);
        return this.FORWORD_EDIT_PAGE;
    }
/*
    private String doReplace(String str) {
        String content=null;
        System.out.println("The str is:"+str);
         content=str.replaceAll("\n","<br>");
         System.out.println("the content is:"+content);
         return content;

    }
*/
    protected String doUpdate(GoaActionConfig cfg,boolean isBack) throws DefaultException,
        Exception {
        VOInterface vo = cfg.getBaseForm().getVo();
        String strContent = vo.getValue("lable_name"); ;
        vo.setValue("lable_name", strContent.replaceAll("\n","<br>"));//��\n�滻��<br>�������ݿ�
        String forward=super.doUpdate(cfg, isBack);
        strContent = vo.getValue("lable_name");//��<br>�滻��<br>��ʾ����
        vo.setValue("lable_name", strContent.replaceAll("<br>",""));
        return forward;


    }

    private String getUserID(GoaActionConfig cfg) {
        BaseForm form = cfg.getBaseForm();
        String userId = form.getUserId();
        //System.out.println("User id is :"+userId);
        return userId;
    }

}
