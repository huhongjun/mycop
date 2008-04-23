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

        BaseForm myForm = cfg.getBaseForm(); //得到form变量
        BBSMngDAO bbsdao = BBSFactory.getInstance().createMngDAO(super.dbData); //得到相对应dao的实例
        bbsdao.setQuest_Sql(BBSMngDAO.TOPBOARD_QUERY_SQL);
        cfg.setBaseDao( (BaseDAO) bbsdao);

        //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao

        //容错处理,防止vo对象为null
        if (myForm.getVo() == null)
            myForm.setVo(new TopBoardVO());
    }

    /**
     * 得到所有的一级数据
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
        long lngCnt = dao.queryByCount(vo); //统计
        // long lngCnt = cfg.getBaseDao().getCount();
        PageControl pc = pHelper.pagination(bForm.getCurPage(), lngCnt,
                                            "");

        //得到所有数据
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

