package com.gever.goa.dailyoffice.tools.action;

/**
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao;
import com.gever.goa.dailyoffice.tools.dao.ToolsFactory;
import com.gever.goa.dailyoffice.tools.vo.CardcaseTypeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

public class CardcaseTypeAction extends BaseAction {
   // private CardcaseTypeDao dao;

    /**
     * 初始化页面数据
     * @param actionform 当前的vo对象
     * @return 当前所用的vo对象
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) {
        CardcaseTypeForm myForm = (CardcaseTypeForm) cfg.getBaseForm(); //得到form变量
        CardcaseTypeDao dao = ToolsFactory.getInstance().createCardcaseTypeDao(super.dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) dao); //设置父类dao

        //容错处理,防止vo对象为null
        if (myForm.getVo() == null)
            myForm.setVo(new CardcaseTypeVO());
        return;
    }

    /**
     * 获取tree的数据
     * @param cfg 当前Action相关配置类
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    public String doTreeData(GoaActionConfig cfg) throws DefaultException,
            Exception {
        CardcaseTypeDao dao=(CardcaseTypeDao)cfg.getBaseDao();
        cfg.getRequest().setAttribute("treeData", dao.getTreeData());
        log.showLog(dao.getTreeData());
        return TREE_PAGE;
    }

    /**
     * 覆盖父类方法 在request中设置update_result标志；
     * @param cfg GoaActionConfig
     * @param isBack boolean
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    public String doUpdate(GoaActionConfig cfg, boolean isBack) throws
            DefaultException, Exception {
        String forword = null;
        forword = super.doUpdate(cfg, isBack);
        cfg.getSession().setAttribute("operate", "update");
        return forword;
    }

    /**
     * 覆盖父类方法 在request中设置insert_result标志；
     * @param cfg GoaActionConfig
     * @param isBack boolean
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    public String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException, Exception {
        CardcaseTypeForm form = (CardcaseTypeForm) cfg.getBaseForm();
        CardcaseTypeVO typeVO = (CardcaseTypeVO) form.getVo();
        typeVO.setUser_code(form.getUserId());

        String forword = null;
        forword = super.doInsert(cfg, isBack);
        cfg.getSession().setAttribute("operate", "insert");
        return forword;
    }

    /**
     * 覆盖父类方法，在request中设置delete_result值
     * @param cfg GoaActionConfig
     * @throws DefaultException
     * @throws Exception
     * @return String
     */
    public String doDelete(GoaActionConfig cfg) throws DefaultException,
            Exception {
        String forword = null;
        //cfg.getRequest().setAttribute("deleteIDs",cfg.getRequest().getParameterValues("fid"));
        forword = super.doDelete(cfg);
        cfg.getSession().setAttribute("operate", "delete");
        cfg.getSession().setAttribute("operateID",
                                      cfg.getRequest().getParameterValues("fid"));
        return forword;
    }

    protected String toList(GoaActionConfig cfg) throws DefaultException,
            Exception {
        CardcaseTypeForm form = (CardcaseTypeForm) cfg.getBaseForm();
        String sqlWhere = " and user_code=" + form.getUserId();
        form.setSqlWhere(sqlWhere);
        return super.toList(cfg);
    }
}
