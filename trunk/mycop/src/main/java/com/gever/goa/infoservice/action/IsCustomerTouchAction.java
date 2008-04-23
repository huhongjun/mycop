package com.gever.goa.infoservice.action;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsCustomerDao;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.IsCustomerTouchDao;
import com.gever.goa.infoservice.vo.IsCustomerTouchVO;
import com.gever.goa.infoservice.vo.IsCustomerVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.StringUtils;


/**
 * <p>Title: 客户联系人Action</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company:</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsCustomerTouchAction extends BaseAction {
    private IsCustomerTouchDao isCustomerTouchDao = null;

    public IsCustomerTouchAction() {
    }

    protected void initData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsCustomerTouchForm myForm = (IsCustomerTouchForm) cfg.getBaseForm();
        isCustomerTouchDao = IsCustomerFactory.getInstance()
                                              .createIsCustomerTouch(super.dbData);
        cfg.setBaseDao((BaseDAO) isCustomerTouchDao);

        if (myForm.getVo() == null) {
            myForm.setVo(new IsCustomerTouchVO());
        }

        this.setVoSql(false);
    }

    protected String toList(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsCustomerTouchForm form = (IsCustomerTouchForm) cfg.getBaseForm();
        String cuscode = StringUtils.nullToString(cfg.getRequest().getParameter("customer_code"));
        String sqlWhere = "";

        if (!"".equals(cuscode)) {
            sqlWhere = " AND IS_CUSTOMER_TOUCH.CUSTOMER_CODE ='" + cuscode +
                "'";
        }

        String paraSimpleQuery = ((IsCustomerTouchForm) cfg.getBaseForm()).getParaSimpleQuery();

        if (paraSimpleQuery == null) {
            paraSimpleQuery = "";
        }

        sqlWhere += (" AND IS_CUSTOMER_TOUCH.CUSTOMER LIKE '%" +
        paraSimpleQuery + "%'");

        cuscode = form.getCustomer_code();

        if (!"".equals(cuscode)) {
            sqlWhere += ("AND IS_CUSTOMER_TOUCH.CUSTOMER_CODE='" + cuscode +
            "'");
            form.setSqlWhere(sqlWhere);
        } else {
        }

        cfg.getBaseForm().setSqlWhere(sqlWhere);

        form = (IsCustomerTouchForm) cfg.getBaseForm();

        String customer_code = form.getCustomer_code();
        IsCustomerVO vo = new IsCustomerVO();
        vo.setCustomer_code(customer_code);

        IsCustomerDao dao = IsCustomerFactory.getInstance().createIsCustomer(super.dbData);
        form.setCustomerVO((IsCustomerVO) dao.queryByPK(vo));

        return super.toList(cfg);
    }

    protected String toEdit(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String actionType = super.toEdit(cfg);

        if (!MODIFY_ACTION.equals(actionType)) {
            IsCustomerTouchVO ictVO = (IsCustomerTouchVO) cfg.getBaseForm()
                                                             .getVo();
            String sexcode = ictVO.getSex_code();

            if ("".equals(sexcode)) {
                ictVO.setSex_code("男");
            } else {
            }

            cfg.getBaseForm().setVo(ictVO);
        }

        IsCustomerDao icDao = IsCustomerFactory.getInstance().createIsCustomer(super.dbData);
        IsCustomerVO icVO = new IsCustomerVO();
        icDao.setSqlWhere(
            " AND IS_CUSTOMER.FLAG=0 AND IS_CUSTOMER.CUSTOMER_CODE NOT IN (SELECT CUSTOMER_CODE FROM IS_CUSTOMER_TOUCH) ");

        //		Collection CustomerList = icDao.queryAll(icVO);
        //		cfg.getRequest().setAttribute("CustomerList", CustomerList);
        return actionType;
    }

    /**
     * 翻页动作(继续返回到本页面新增)
     * @param cfg 当前Action相关配置类
     * @param pageType 页面类型
     * @return forward地址
     * @throws DefaultException
     * @throws Exception
     */

    //	public Object queryBypk(){
    //			return super.queryBypk(vo);
    //
    //		}
    protected String doPage(GoaActionConfig cfg, String pageType)
        throws DefaultException, Exception {
        return super.doPage(cfg, pageType);
    }

    protected String doInsert(GoaActionConfig cfg, boolean isBack)
        throws DefaultException, Exception {
        IsCustomerTouchForm form = (IsCustomerTouchForm) cfg.getBaseForm();
        IsCustomerTouchVO vo = (IsCustomerTouchVO) form.getVo();
        String forword = null;
        HttpServletRequest request = cfg.getRequest();
        vo.setCustomer_code(form.getCustomer_code());
        cfg.getBaseDao().setIsIdMng(false);

        //        return super.doInsert(cfg, isBack);
        try {
            forword = super.doInsert(cfg, isBack);
        } catch (DefaultException e) {
            if (e.getMessage().equals("PK repeat!")) {
                request.setAttribute("ErrorMsg", "存在相同姓名，请重新输入！");

                return FORWORD_EDIT_PAGE;
            } else {
                throw e;
            }
        }

        return forword;
    }

    protected String doUpdate(GoaActionConfig cfg, boolean isBack)
        throws DefaultException, Exception {
        IsCustomerTouchForm form = (IsCustomerTouchForm) cfg.getBaseForm();
        IsCustomerTouchVO vo = (IsCustomerTouchVO) form.getVo();
        vo.setCustomer_code(form.getCustomer_code());

        return super.doUpdate(cfg, isBack);
    }
}
