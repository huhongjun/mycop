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
 * <p>Title:短信收件箱 </p>
 * <p>Description: 短信收件箱控制器</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsInBoxAction extends BaseAction {
    private SmsInBoxDAO mDao;
    public SmsInBoxAction() {
    }

    /**
     * 初始化页面数据
     * @param cfg 当前的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) {

        SmsInBoxForm myForm = (SmsInBoxForm) cfg.getBaseForm(); //得到form变量
        mDao = SmsFactory.getInstance().createSmsInBoxDAO(super.dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) mDao); //设置父类dao

        //容错处理,防止vo对象为null
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
       mDao.updateReadFlag(vo); //调用dao中update方法来修改阅读标志和阅读时间
       return forwardpath;
     }

}