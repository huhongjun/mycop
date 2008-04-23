package com.gever.goa.dailyoffice.bbs.action;

import org.apache.struts.action.ActionMapping;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.bbs.dao.BBSFactory;
import com.gever.goa.dailyoffice.bbs.dao.BBSMngDAO;
import com.gever.goa.dailyoffice.bbs.vo.SBoardVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.struts.form.BaseForm;
import com.gever.struts.pager.PageControl;
import com.gever.struts.pager.PageHelper;
import com.gever.util.StringUtils;
import com.gever.util.SumUtils;
import com.gever.vo.VOInterface;

public class SBoardAction extends BaseAction {
    protected void initData(GoaActionConfig cfg) throws DefaultException,
            Exception {
        BaseForm myForm = cfg.getBaseForm(); //得到form变量
        BBSMngDAO bbsdao = BBSFactory.getInstance().createMngDAO(super.dbData); //得到相对应dao的实例
        bbsdao.setQuest_Sql(BBSMngDAO.SBOARD_QUERY_SQL);
        String id = cfg.getRequest().getParameter("topid");
        if (id != null && !"null".equals(id)) {
            myForm.setSqlWhere(" and tboard_serial= '" + id + "' ");
            ( (SBoardForm) myForm).setTopid(id);
        }

        cfg.setBaseDao( (BaseDAO) bbsdao);
        //容错处理,防止vo对象为null
        if (myForm.getVo() == null)
            myForm.setVo(new SBoardVO());

    }

    public String toEdit(GoaActionConfig cfg) throws DefaultException,
            Exception {
        String forward = super.toEdit(cfg);
        String actionType = SumUtils.nullToString(cfg.getRequest().getParameter(
                "actionType"));
        if (!this.MODIFY_ACTION.equals(actionType)) {
            SBoardVO vo = new SBoardVO();
            vo.setValue("tboard_serial",
                        ( (SBoardForm) cfg.getBaseForm()).getTopid());
            cfg.getBaseForm().setVo(vo);
        }
        return forward;
    }

    /**
     * 得到所有的二级数据
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
        sqlWhere += " and sboard_state=1 ";
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

        //bForm.setPageControl(pc);
        bForm.setPageControl(pc);

        return this.FORWORD_LIST_PAGE;
    }

    protected String toList(GoaActionConfig cfg) throws
            DefaultException,
            Exception {

        String sqlWhere = "";
        SBoardForm form = (SBoardForm) cfg.getBaseForm();
        if (this.needClearSearchValue(cfg)) {
            form.setSearchValue("");
        }

        String SBoard_Name = form.getSearchValue();
        if (!SBoard_Name.equals("")) {
            SBoard_Name = StringUtils.replaceText(SBoard_Name);
            sqlWhere += " and SBoard_Name like '%" + SBoard_Name + "%'";
        }
        String topid = form.getTopid();
        if (!topid.equals("")) {
            sqlWhere += "and TBoard_Serial = '" + topid + "'";
        }
        form.setSqlWhere(sqlWhere);
        String forword = super.toList(cfg);
        return forword;
    }

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
            DefaultException, Exception {
        SBoardForm form = (SBoardForm) cfg.getBaseForm();
        String forword = super.doInsert(cfg, isBack);
        form.setSearchValue("");
        return forword;
    }

    private boolean needClearSearchValue(GoaActionConfig cfg) {
        boolean ret = true;
        String lastPath = (String) cfg.getSession().getAttribute(
                "currentaction");
        String lastAction="";

        if (lastPath != null) {
            ActionMapping lastMapping = this.getServlet().findMapping(lastPath);
            if (lastMapping != null) {
               lastAction = lastMapping.getType();
            }
            log.showLog("1111111111111111111lastAction" + lastAction);
            String currAction = cfg.getMapping().getType();
            log.showLog("1111111111111111111currAction" + currAction);
            if (currAction.equals(lastAction)) {
                return false;
            }

        }

        return ret;
    }
}
