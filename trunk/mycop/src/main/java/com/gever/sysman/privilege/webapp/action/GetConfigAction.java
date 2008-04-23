package com.gever.sysman.privilege.webapp.action;

import com.gever.sysman.privilege.dao.PermissionMapDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.impl.DefaultPermissionMapDAO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import java.util.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetConfigAction extends DispatchAction {
    public ActionForward showRoot(ActionMapping mapping, ActionForm actionform,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PermissionMapDAO perMapDAO = PrivilegeFactory.getInstance()
                                                     .getPerMissionMapDAO();
        List lst = new ArrayList();
        org.apache.struts.action.ActionServlet as = (org.apache.struts.action.ActionServlet) this.servlet.getServletContext()
                                                                                                         .getAttribute(org.apache.struts.Globals.ACTION_SERVLET_KEY);
        Enumeration names = as.getServletConfig().getInitParameterNames();
        lst.add("config/");

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();

            if (!name.startsWith("config/")) {
                continue;
            }

            lst.add(name);
        }

        request.setAttribute("configs", lst);

        // clean database
        perMapDAO.clearDataBase(DefaultPermissionMapDAO.ROOT_NODE, "", lst);

        return mapping.findForward("showRoot");
    }

    public ActionForward getConfigNode(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        PermissionMapDAO perMapDAO = PrivilegeFactory.getInstance()
                                                     .getPerMissionMapDAO();
        List lstActionPaths = new ArrayList();
        List lstClassTypes = new ArrayList();

        String uuid = (String) request.getParameter("uuid");

        request.setAttribute("uuid", uuid);

        for (int i = 0;
                i < mapping.getModuleConfig().findActionConfigs().length;
                i++) {
            lstActionPaths.add(mapping.getModuleConfig().findActionConfigs()[i].getPath());
            lstClassTypes.add(mapping.getModuleConfig().findActionConfigs()[i].getType());
        }

        request.setAttribute("lstActionPaths", lstActionPaths);
        request.setAttribute("lstClassTypes", lstClassTypes);

        // clean database
        perMapDAO.clearDataBase(DefaultPermissionMapDAO.CONFIG_NODE, uuid,
            lstActionPaths);

        return mapping.findForward("showconfig");
    }

    public ActionForward getClassTree(ActionMapping mapping,
        ActionForm actionform, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        PermissionMapDAO perMapDAO = PrivilegeFactory.getInstance()
                                                     .getPerMissionMapDAO();
        Collection listActionMethod = new ArrayList();
        String type = (String) request.getParameter("classname");
        String uuid = (String) request.getParameter("uuid");
        request.setAttribute("uuid", uuid);

        Object obj = Class.forName(type).newInstance();

        java.lang.reflect.Method[] arrMethod = obj.getClass()
                                                  .getDeclaredMethods();

        for (int i = 0; i < arrMethod.length; i++) {
            //过滤内部类
            if (arrMethod[i].getName().endsWith("$")) {
                continue;
            }

            //判断返回类型是否 为ActionForward
            if (!arrMethod[i].getReturnType().equals(Class.forName(
                            "org.apache.struts.action.ActionForward"))) {
                continue;
            }

            //判断参数
            Class[] actionParams = {
                Class.forName("org.apache.struts.action.ActionMapping"),
                Class.forName("org.apache.struts.action.ActionForm"),
                Class.forName("javax.servlet.http.HttpServletRequest"),
                Class.forName("javax.servlet.http.HttpServletResponse")
            };
            Class[] testParams = arrMethod[i].getParameterTypes();

            for (int j = 0; j < testParams.length; j++) {
                if (!testParams[j].equals(actionParams[j])) {
                    continue;
                }
            }

            listActionMethod.add(arrMethod[i].getName());
        }

        request.setAttribute("listActionMethod", listActionMethod);

        // clean database
        String actionPath = request.getParameter("uuid");
        perMapDAO.clearDataBase(DefaultPermissionMapDAO.ACTION_PATH,
            actionPath, listActionMethod);

        return mapping.findForward("showmethod");
    }
}
