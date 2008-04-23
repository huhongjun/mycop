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
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailInfoAction extends BaseAction {
    MailInfoDAO mailInfoDao = null;
    public MailInfoAction() {
    }

    /**
     * 初始化页面数据
     * @param actionform 当前的vo对象
     * @return 当前所用的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MailInfoForm myForm = (MailInfoForm) cfg.getBaseForm(); //得到form变量
        mailInfoDao = MailMgrFactory.getInstance().creatMailInfo(super.dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) mailInfoDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

        //容错处理,防止vo对象为null
        if (myForm.getVo() == null) {
            myForm.setVo(new MailInfoVO());
        }
    }

    /**
     * 邮件参数更新方法
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String toEdit(GoaActionConfig cfg) throws DefaultException,
        Exception {
        //System.out.println("toEdit开始");
        VOInterface vo = cfg.getBaseForm().getVo();
        String userId = this.getUserID(cfg); //得到用户ID
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
        //System.out.println("行数为--->" + count);
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
     * 插入邮件参数设置
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
        vo.setValue("lable_name", strContent.replaceAll("\n","<br>"));//把\n替换成<br>存入数据库
        String forward=super.doUpdate(cfg, isBack);
        strContent = vo.getValue("lable_name");//把<br>替换成<br>显示出来
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
