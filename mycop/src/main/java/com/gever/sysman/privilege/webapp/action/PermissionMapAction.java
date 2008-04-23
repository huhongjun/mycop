/*
 * 功能描述 维护权限与操作代码的对应关系
 * 创建日期 2004-11-25 10:07:54
 */
package com.gever.sysman.privilege.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gever.sysman.privilege.dao.PermissionMapDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.model.PermissionMap;
import com.gever.sysman.util.BaseAction;


/**
 * @author Hu.Walker
 */
public class PermissionMapAction extends BaseAction {
    String MODULE_NAME = "权限操作与资源映射";
    String[] CREATE = { "create", "创建资源映射" };
    String[] DELETE = { "delete", "删除资源映射" };
    String[] UPDATE = { "update", "更新资源映射" };

    /**
     * 添加方法与操作的对应关系
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PermissionMap perMap = PrivilegeFactory.getInstance()
                                               .createPermissionMap();
        String strID = (String) request.getParameter("id");

        if (strID != null) {
            long id = Long.parseLong(strID);
            perMap.setId(id);
        } else {
            perMap.setId(0);
        }

        perMap.setActionPath(request.getParameter("actionpath"));
        perMap.setMethodName(request.getParameter("methodname"));
        perMap.setResCode(request.getParameter("rescode"));
        perMap.setResOpCode(request.getParameter("resoptcode"));

        PermissionMapDAO perMapDAO = PrivilegeFactory.getInstance()
                                                     .getPerMissionMapDAO();

        // 存在则更新，否则创建
        if (perMapDAO.haveActionMethod(perMap)) {
            perMapDAO.updatePermissionMap(perMap);
            super.addLog(request, MODULE_NAME, this.UPDATE[0], this.UPDATE[1]);
        } else {
            perMapDAO.createPermissionMap(perMap);
            super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);
        }

        return mapping.findForward("toCreate");
    }

    /**
     * 当前映射列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        return mapping.findForward("list");
    }

    /**
     * 更新页面
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toCreate(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        return mapping.findForward("toCreate");
    }

    /**
     * 根据传入的ActionPath取得其所有方法
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward toMethodList(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String actionPath = (String) request.getParameter("actionPath");

        if ((actionPath == null)) {
            return mapping.findForward("toMethodList");
        }

        PermissionMapDAO perMapDAO = PrivilegeFactory.getInstance()
                                                     .getPerMissionMapDAO();

        List methodList = perMapDAO.findPermissionMapByPath(actionPath);

        if (methodList != null) {
            request.setAttribute("methodList", methodList);
        }

        return mapping.findForward("toMethodList");
    }

    /**
     * 导出指定DDL
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward export(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PermissionMapDAO perMapDAO = PrivilegeFactory.getInstance()
                                                     .getPerMissionMapDAO();
        String actionPath = (String) request.getParameter("exportddl");

        String ddl = perMapDAO.exportDDL(actionPath);
        request.setAttribute("ddl", ddl);

        return mapping.findForward("openddl");
    }
}
