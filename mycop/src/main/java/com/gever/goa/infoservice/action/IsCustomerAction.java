package com.gever.goa.infoservice.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsCustomerDao;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.vo.IsCustomerVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.util.StringUtils;
import com.gever.vo.BaseTreeVO;


/**
 * <p>Title: 客户管理Action</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: gever</p>
 * @author Hu.Walker
 * @version 1.0
 */
public class IsCustomerAction extends BaseAction {
    private IsCustomerDao isCustomerDao = null;

    public IsCustomerAction() {
    }

    protected void initData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        IsCustomerForm myForm = (IsCustomerForm) cfg.getBaseForm();
        isCustomerDao = IsCustomerFactory.getInstance().createIsCustomer(super.dbData);
        cfg.setBaseDao((BaseDAO) isCustomerDao);

        if (myForm.getVo() == null) {
            myForm.setVo(new IsCustomerVO());
        }
    }

    protected String toList(GoaActionConfig cfg)
        throws DefaultException, Exception {
        //ArcParamForm arcForm=new ArcParamForm();
        IsCustomerForm form = (IsCustomerForm) cfg.getBaseForm();
        String sqlWhere = "";
        String value = StringUtils.nullToString(form.getSelectQuery());
        String paraFlag = ((IsCustomerForm) cfg.getBaseForm()).getParaFlag();

        String paraSimpleQuery = ((IsCustomerForm) cfg.getBaseForm()).getParaSimpleQuery();
        paraSimpleQuery = StringUtils.replaceText(paraSimpleQuery);

        if (!"".equals(value)) {
            sqlWhere += ("AND IS_CUSTOMER.CUSTOMER_NAME LIKE '%" + value +
            "%'");
        }

        log.showLog("**************search value******" + sqlWhere);

        String status = StringUtils.nullToString(cfg.getRequest().getParameter("status"));
        form.setParaSimpleQuery("");

        if (!"".equals(status)) {
            sqlWhere += (" AND IS_CUSTOMER.Flag = " + status);
            form.setSqlWhere(sqlWhere);
            form.setSelectQuery("");
        } else {
            String type = StringUtils.nullToString(cfg.getRequest()
                                                      .getParameter("type"));

            if (!"".equals(type)) {
                sqlWhere += ("AND IS_CUSTOMER.TYPE_CODE =" + type);
                form.setSqlWhere(sqlWhere);
                form.setSelectQuery("");
            } else {
                if (paraSimpleQuery == null) {
                    paraSimpleQuery = "";
                }

                form.setSqlWhere(" AND IS_CUSTOMER.CUSTOMER_NAME LIKE '%" +
                    paraSimpleQuery + "%'");
            }
        }

        return super.toList(cfg);
    }

    //    public String myQuery(GoaActionConfig cfg)
    //        throws DefaultException, Exception {
    //        IsCustomerForm form = (IsCustomerForm) cfg.getBaseForm();
    //        String sqlWhere = "";
    //        String status =
    //            StringUtils.nullToString(cfg.getRequest().getParameter("status"));
    //        String type =
    //            StringUtils.nullToString(cfg.getRequest().getParameter("type"));
    //        String paraSimpleQuery =
    //            ( (IsCustomerForm) cfg.getBaseForm()).getParaSimpleQuery();
    //        if (paraSimpleQuery != null && !paraSimpleQuery.equals("")) {
    //            sqlWhere += " AND IS_CUSTOMER.CUSTOMER_NAME LIKE '%"
    //                + paraSimpleQuery
    //                + "%'";
    //            status = form.getStatus();
    //            type = form.getType();
    //            if (!"".equals(status)) {
    //                sqlWhere += " AND IS_CUSTOMER.FLAG=" + status;
    //              form.setSqlWhere(sqlWhere);
    //            } else if (!"".equals(type)) {
    //                sqlWhere += " AND IS_CUSTOMER.TYPE_CODE=" + type;
    //               form.setSqlWhere(sqlWhere);
    //            }
    //        }
    //        return super.toList(cfg);
    //    }
    //	protected String toList(GoaActionConfig cfg)
    //		throws DefaultException, Exception {
    //
    //		String paraFlag = ((IsCustomerForm) cfg.getBaseForm()).getParaFlag();
    //		String paraSimpleQuery =
    //			((IsCustomerForm) cfg.getBaseForm()).getParaSimpleQuery();
    //		if (paraSimpleQuery != null && !paraSimpleQuery.equals("")) {
    //			cfg.getBaseForm().setSqlWhere(
    //				" AND IS_CUSTOMER.FLAG="
    //					+ paraFlag
    //					+ " AND IS_CUSTOMER.CUSTOMER_NAME LIKE '%"
    //					+ paraSimpleQuery
    //					+ "%'");
    //
    //		}
    //		else {
    //			cfg.getBaseForm().setSqlWhere("");
    //		}
    //		return super.toList(cfg);
    //	}
    public String doRootTreeData(GoaActionConfig cfg) {
        BaseTreeVO vo1 = new BaseTreeVO();
        vo1.setNodeid("1");
        vo1.setNodename("客户状态");
        vo1.setAction("/infoservice/customerlist.do");
        vo1.setIsfolder("1");
        vo1.setSrc(
            "/infoservice/customertree.do?action=doStatusTreeData&#38;nodeid=1");

        BaseTreeVO vo2 = new BaseTreeVO();
        vo2.setNodeid("2");
        vo2.setAction("/infoservice/customerlist.do");
        vo2.setNodename("客户类别");
        vo2.setIsfolder("1");
        vo2.setSrc(
            "/infoservice/customertree.do?action=doTypeTreeData&#38;nodeid=2");

        List treeData = new ArrayList(2);
        treeData.add(vo1);
        treeData.add(vo2);
        cfg.getRequest().setAttribute("treeData", treeData);

        return TREE_PAGE;
    }

    public String doStatusTreeData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        System.out.println(
            "-------------------------doTreeData in Action------------------------");
        cfg.getRequest().setAttribute("treeData",
            isCustomerDao.getStatusTreeData());

        return super.TREE_PAGE;
    }

    public String doTypeTreeData(GoaActionConfig cfg)
        throws DefaultException, Exception {
        System.out.println(
            "-------------------------doTreeData in Action------------------------");
        cfg.getRequest().setAttribute("treeData",
            isCustomerDao.getTypeTreeData());

        return super.TREE_PAGE;
    }

    //	/**
    //		 * 获取tree的数据
    //		 * @param cfg 当前Action相关配置类
    //		 * @return forward地址
    //		 * @throws DefaultException
    //		 * @throws Exception
    //		 */
    //	public String doTreeData(GoaActionConfig cfg)
    //		throws DefaultException, Exception {
    //		System.out.println(
    //			"-------------------------doTreeData in Action------------------------");
    //		cfg.getRequest().setAttribute(
    //			"treeData",
    //			isCustomerDao.getStatusTreeData());
    //		return TREE_PAGE;
    //	}
    protected String toEdit(GoaActionConfig cfg)
        throws DefaultException, Exception {
        String foward = super.toEdit(cfg);
        IsCustomerForm form = (IsCustomerForm) cfg.getBaseForm();
        IsCustomerVO vo = (IsCustomerVO) form.getVo();
        String status = vo.getFlag();
        String type = vo.getType_code();

        if ("".equals(status)) {
            vo.setFlag(form.getStatus());
        }

        if ("".equals(type)) {
            vo.setType_code(form.getType());
        }

        return foward;
    }

    
    	protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
    			DefaultException,
    			Exception {
    
    		String forword = null;
            HttpServletRequest request = cfg.getRequest();
//    			this.initEditPage(cfg,actionType);
//    			return actionType;
            try {
                forword = super.doInsert(cfg, isBack);
            } catch (DefaultException e) {
                if (e.getMessage().equals("PK repeat!")) {
                    request.setAttribute("ErrorMsg", "客户代码重复,请重新输入！");

                    return FORWORD_EDIT_PAGE;
                } else {
                    throw e;
                }
            }

            return forword;
    
    		}
    //
    //	protected String doUpdate(GoaActionConfig cfg, boolean isBack) throws
    //				DefaultException,
    //				Exception {
    //
    //				String actionType=super.doUpdate(cfg,isBack);
    //				this.initEditPage(cfg,actionType);
    //				return actionType;
    //
    //			}
    //
    //	private GoaActionConfig initEditPage(
    //			 GoaActionConfig cfg,
    //			 String actionType)
    //			 throws DefaultException, Exception {
    //
    //					return cfg;
    //		}
}
