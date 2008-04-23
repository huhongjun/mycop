package com.gever.sysman.organization.webapp.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Çü²¨
import java.util.*;
import com.gever.sysman.organization.model.*;
import com.gever.sysman.organization.dao.*;

import com.gever.struts.pager.AbstractPageControlHelper;
import com.gever.struts.pager.PageControl;
import com.gever.sysman.util.BaseAction;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE¡¡OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class UserDepartmentAction
    extends BaseAction {

    public ActionForward toCreate(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
        Exception {
        return mapping.findForward("create");
    }

    public ActionForward create(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws
        Exception {

        UserDepartment userDepartment = OrganizationFactory.getInstance().
            createUserDepartment();
        userDepartment.setUser(Integer.parseInt(request.getParameter("user")));
        userDepartment.setDepartment(Integer.parseInt(request.getParameter("department")));

        UserDepartmentDAO userDepartmentDAO = OrganizationFactory.getInstance().
            getUserDepartmentDAO();
        userDepartmentDAO.createUserDepartment(userDepartment);

        return list(mapping, form, request, response);
    }

    public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
        Exception {
        String[] id = request.getParameterValues("id");
        int[] user = new int[id.length];
        int[] department = new int[id.length];
        StringTokenizer st = null;

        for (int i = 0; i < id.length; i++) {
            st = new StringTokenizer(id[i], ",");
            user[i] = Integer.parseInt(st.nextToken());
            department[i] = Integer.parseInt(st.nextToken());
        }

        UserDepartmentDAO userDepartmentDAO = OrganizationFactory.getInstance().
            getUserDepartmentDAO();
        UserDepartment userDepartment = userDepartmentDAO.
            findUserDepartmentByUserByDepartment(user[0], department[0]);

        DynaActionForm daf = (DynaActionForm) form;
        daf.set("user", String.valueOf(userDepartment.getUser()));
        daf.set("department", String.valueOf(userDepartment.getDepartment()));
        daf.set("userName", String.valueOf(userDepartment.getUserName()));
        daf.set("departmentName", String.valueOf(userDepartment.getDepartmentName()));
        daf.set("userUpdate", String.valueOf(userDepartment.getUser()));
        daf.set("departmentUpdate", String.valueOf(userDepartment.getDepartment()));

        return mapping.findForward("update");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws
        Exception {

        DynaActionForm daf = (DynaActionForm) form;
        UserDepartment userDepartment = OrganizationFactory.getInstance().
            createUserDepartment();
        userDepartment.setUser(Integer.parseInt(request.getParameter("user")));
        userDepartment.setDepartment(Integer.parseInt(request.getParameter("department")));

        UserDepartmentDAO userDepartmentDAO = OrganizationFactory.getInstance().
            getUserDepartmentDAO();
        userDepartmentDAO.updateUserDepartment(userDepartment,
                                               Integer.parseInt( (String)
            daf.get("userUpdate")), Integer.parseInt( (String) daf.get("departmentUpdate")));

        return list(mapping, form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws
        Exception {
        String[] id = request.getParameterValues("id");
        int[] user = new int[id.length];
        int[] department = new int[id.length];
        StringTokenizer st = null;

        for (int i = 0; i < id.length; i++) {
            st = new StringTokenizer(id[i], ",");
            user[i] = Integer.parseInt(st.nextToken());
            department[i] = Integer.parseInt(st.nextToken());
        }

        UserDepartmentDAO userDepartmentDAO = OrganizationFactory.getInstance().
            getUserDepartmentDAO();
        userDepartmentDAO.deleteUserDepartment(user, department);

        return list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws
        Exception {

        PageControl pageControl = new AbstractPageControlHelper() {
            UserDepartmentDAO userDepartmentDAO = OrganizationFactory.getInstance().
                getUserDepartmentDAO();

            public Collection getPagerData(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response, long start,
                                           long count) throws Exception {
//              start = setStart(form, start, count);
              return userDepartmentDAO.findUserDepartmentsForPage(Integer.parseInt(
                  String.valueOf(start)), Integer.parseInt(String.valueOf(count)));
            }

//            private long setStart(ActionForm form, long start, long count) throws
//                NumberFormatException {
//              DynaValidatorForm dForm = (DynaValidatorForm)form;
//              String page = (String)dForm.get("page");
//              long lPage = Long.parseLong(page);
//              lPage = lPage > 0 ? lPage-1 : 0;
//              start = lPage * count;
//
//              return start;
//            }

            public long getRowCount(ActionMapping mapping,
                                    ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
                return userDepartmentDAO.getUserDepartmentCount();
            }

        }

        .newPageControl(mapping, form, request, response);

        request.setAttribute("pageControl", pageControl);

        return mapping.findForward("list");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws
        Exception {

        String[] user = request.getParameterValues("user");
        String[] department = request.getParameterValues("department");

        UserDepartmentDAO userDepartmentDAO = OrganizationFactory.getInstance().
            getUserDepartmentDAO();
        UserDepartment userDepartment = userDepartmentDAO.
            findUserDepartmentByUserByDepartment(Integer.parseInt(user[0]),
                                                 Integer.parseInt(department[0]));

        DynaActionForm daf = (DynaActionForm) form;
        daf.set("user", String.valueOf(userDepartment.getUser()));
        daf.set("department", String.valueOf(userDepartment.getDepartment()));
        daf.set("userName", String.valueOf(userDepartment.getUserName()));
        daf.set("departmentName", String.valueOf(userDepartment.getDepartmentName()));

        return mapping.findForward("view");
    }

}
