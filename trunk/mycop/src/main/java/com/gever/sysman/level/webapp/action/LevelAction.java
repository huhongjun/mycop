package com.gever.sysman.level.webapp.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.RedirectingActionForward;

import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.struts.pager.AbstractPageControlHelper;
import com.gever.struts.pager.PageControl;
import com.gever.sysman.level.dao.LevelDAO;
import com.gever.sysman.level.dao.impl.DefaultLevelDAO;
import com.gever.sysman.level.model.Level;
import com.gever.sysman.level.model.impl.DefaultLevel;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;


public class LevelAction extends BaseAction {
    String MODULE_NAME = "层级管理";
    String[] CREATE = { "create", "创建层级" };
    String[] DELETE = { "delete", "删除层级" };
    String[] UPDATE = { "update", "修改层级资料" };

    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PageControl pageControl = new AbstractPageControlHelper() {
                    LevelDAO levelDAO = new DefaultLevelDAO();

                    public Collection getPagerData(ActionMapping mapping,
                        ActionForm form, HttpServletRequest request,
                        HttpServletResponse response, long start, long count)
                        throws Exception {
                        //                start = setStart(form, start, count);
                        //==========================================================================
                        //胡勇添加，增加JSP视图列表排序功能
                        OrderList _order = OrderList.getInstance();
                        String orderby = request.getParameter(OrderList.level_key);
                        String desc = request.getParameter(OrderList.desc);

                        if (!_order.isNull(orderby) && !_order.isNull(desc)) {
                            String[] ss = { orderby, desc };
                            request.getSession(true).setAttribute(OrderList.level_key,
                                ss);
                            levelDAO.setOrderby(ss);
                        } else {
                            String[] ss = (String[]) request.getSession(true)
                                                            .getAttribute(OrderList.level_key);

                            if (ss != null) {
                                levelDAO.setOrderby(ss);
                            }
                        }

                        //==========================================================================
                        return levelDAO.getLevels(start, count);
                    }

                    //            private long setStart(ActionForm form, long start, long count)
                    //                    throws NumberFormatException {
                    //                DynaActionForm dForm = (DynaActionForm) form;
                    //                String page = (String) dForm.get("page");
                    //                page = page.equals("") ? "0" : page;
                    //                long lPage = Long.parseLong(page);
                    //                lPage = lPage > 0 ? lPage - 1 : 0;
                    //                start = lPage * count;
                    //                return start;
                    //            }
                    public long getRowCount(ActionMapping mapping,
                        ActionForm form, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
                        long count = levelDAO.getLevelCount();

                        return count;
                    }
                }.newPageControl(mapping, form, request, response);

        request.setAttribute("pageControl", pageControl);

        return mapping.findForward("list");
    }

    public ActionForward create(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        return mapping.findForward("create");
    }

    public ActionForward addLevel(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        DynaActionForm daForm = (DynaActionForm) form;
        Level level = new DefaultLevel();
        level.setCode((String) daForm.get("code"));
        level.setName((String) daForm.get("name"));
        level.setDescription((String) daForm.get("description"));

        LevelDAO levelDAO = new DefaultLevelDAO();

        try {
            levelDAO.createLevel(level);
        } catch (DAOException e) {
            throw new DefaultException("PRV_ROLE_001", DefaultException.ERROR);
        }

        super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);

        // modify by eddy on 20041213----\\\
        // 防止新建后刷新出错而做的转向
        return new RedirectingActionForward("levelAction.do?action=list");

        // return list(mapping, form, request, response);
        // modify by eddy on 20041213----///
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String[] ids = request.getParameterValues("id");
        LevelDAO levelDAO = new DefaultLevelDAO();

        try {
            levelDAO.deleteLevelByIds(ids);
        } catch (DAOException e) {
            e.printStackTrace();

            return (mapping.findForward("failed"));
        }

        super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);

        return list(mapping, form, request, response);
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        LevelDAO levelDAO = new DefaultLevelDAO();

        long id = Integer.parseInt(request.getParameter("id"));
        DynaActionForm df = (DynaActionForm) form;
        Level level = null;

        try {
            level = levelDAO.findLevelByID(id);
        } catch (DAOException e) {
            e.printStackTrace();

            return (mapping.findForward("failed"));
        }

        if (level == null) {
            return (mapping.findForward("failed"));
        }

        df.set("id", Long.toString(id));
        df.set("code", level.getCode());
        df.set("name", level.getName());
        df.set("description", level.getDescription());
        request.setAttribute("levelForm", df);

        return mapping.findForward("edit");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        LevelDAO levelDAO = new DefaultLevelDAO();

        DynaActionForm df = (DynaActionForm) form;

        long id = Long.parseLong((String) df.get("id"));
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Level level = new DefaultLevel();
        level.setId(id);
        level.setCode(code);
        level.setName(name);
        level.setDescription(description);

        try {
            levelDAO.updateLevel(level);
        } catch (DAOException e) {
            throw new DefaultException("PRV_ROLE_015", DefaultException.ERROR);
        }

        super.addLog(request, MODULE_NAME, this.UPDATE[0], this.UPDATE[1]);

        return list(mapping, form, request, response);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;

        long id;

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {
            e.printStackTrace();

            return (mapping.findForward("failed"));
        }

        LevelDAO levelDAO = new DefaultLevelDAO();

        Level level = null;

        try {
            level = levelDAO.findLevelByID(id);
        } catch (DAOException e) {
            System.err.println(e.getMessage());

            return (mapping.findForward("failed"));
        }

        if (level == null) {
            return (mapping.findForward("failed"));
        }

        f.set("id", Long.toString(level.getId()));
        f.set("code", level.getCode());
        f.set("name", level.getName());
        f.set("description", level.getDescription());

        //Role parentRole = role.getParentRole();
        //		if (parentRole != null) {
        //			f.set("parentRoleID", Long.toString(parentRole.getId()));
        //			f.set("parentRoleName", parentRole.getName());
        //		}
        return mapping.findForward("view");
    }
}
