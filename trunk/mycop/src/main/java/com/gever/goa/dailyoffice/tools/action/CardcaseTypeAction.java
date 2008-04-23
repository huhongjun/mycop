package com.gever.goa.dailyoffice.tools.action;

/**
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
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
     * ��ʼ��ҳ������
     * @param actionform ��ǰ��vo����
     * @return ��ǰ���õ�vo����
     * @throws DefaultException
     */
    protected void initData(GoaActionConfig cfg) {
        CardcaseTypeForm myForm = (CardcaseTypeForm) cfg.getBaseForm(); //�õ�form����
        CardcaseTypeDao dao = ToolsFactory.getInstance().createCardcaseTypeDao(super.dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) dao); //���ø���dao

        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null)
            myForm.setVo(new CardcaseTypeVO());
        return;
    }

    /**
     * ��ȡtree������
     * @param cfg ��ǰAction���������
     * @return forward��ַ
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
     * ���Ǹ��෽�� ��request������update_result��־��
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
     * ���Ǹ��෽�� ��request������insert_result��־��
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
     * ���Ǹ��෽������request������delete_resultֵ
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
