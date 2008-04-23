package com.gever.goa.dailyoffice.bbs.action;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.BBSFactory;
import com.gever.goa.dailyoffice.bbs.dao.BBSMngDAO;
import com.gever.goa.dailyoffice.bbs.vo.TopBoardVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.form.BaseForm;
import com.gever.struts.pager.PageControl;
import com.gever.struts.pager.PageHelper;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

public class TopBoardAction extends BaseAction {

    protected void initData(GoaActionConfig cfg) throws DefaultException,
            Exception {

        BaseForm myForm = cfg.getBaseForm(); //�õ�form����
        BBSMngDAO bbsdao = BBSFactory.getInstance().createMngDAO(super.dbData); //�õ����Ӧdao��ʵ��
        bbsdao.setQuest_Sql(BBSMngDAO.TOPBOARD_QUERY_SQL);
        cfg.setBaseDao( (BaseDAO) bbsdao);

        //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao

        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null)
            myForm.setVo(new TopBoardVO());
    }

    /**
     * �õ����е�һ������
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    public String toAll(GoaActionConfig cfg) throws
            DefaultException,
            Exception {
        BaseForm bForm = cfg.getBaseForm();
        BaseDAO dao = cfg.getBaseDao();
        VOInterface vo = bForm.getVo();
        String sqlWhere = bForm.getSqlWhere();
        sqlWhere+=" and tboard_state=1 ";
        bForm.setSqlWhere(sqlWhere);
        PageHelper pHelper = cfg.getPageHelper();
        dao.setOrderColumn(bForm.getOrderFld());
        dao.setOrderType(bForm.getOrderType());
        dao.setSqlWhere(bForm.getSqlWhere());
        long lngCnt = dao.queryByCount(vo); //ͳ��
        // long lngCnt = cfg.getBaseDao().getCount();
        PageControl pc = pHelper.pagination(bForm.getCurPage(), lngCnt,
                                            "");

        //�õ���������
        pc.setData(dao.queryByPage(vo, 0,
                                                lngCnt));

        //cfg.getBaseForm().setPageControl(pc);
        bForm.setPageControl(pc);

        return this.FORWORD_LIST_PAGE;
    }

    protected String toList(GoaActionConfig cfg) throws
            DefaultException,
            Exception {
        String sqlWhere = "";
        TopBoardForm form = (TopBoardForm) cfg.getBaseForm();
        String TBoard_Name = form.getSearchValue();
        if (!TBoard_Name.equals("")) {
            TBoard_Name=StringUtils.replaceText(TBoard_Name);
            sqlWhere += " and TBoard_Name like '%" + TBoard_Name + "%'";
        }
        form.setSqlWhere(sqlWhere);
        String forword = super.toList(cfg);
        return forword;
    }

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException, Exception {
        TopBoardForm form = (TopBoardForm) cfg.getBaseForm();
        String forword = super.doInsert(cfg, isBack);
        form.setSearchValue("");
        return forword;
    }

}

