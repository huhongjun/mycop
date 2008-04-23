//Source file: E:\\lichm\\MyWorks\\GDP\\工作区\\GDP工程\\Sysman\\src\\com\\gever\\sysman\\organization\\webapp\\action\\UserAction.java
package com.gever.sysman.organization.webapp.action;


import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;

import com.gever.struts.pager.AbstractPageControlHelper;
import com.gever.struts.pager.PageControl;

import com.gever.sysman.organization.dao.*;
import com.gever.sysman.organization.model.*;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.validator.DynaValidatorForm;

import java.util.*;

//屈波
import java.util.Collection;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 用户处理
 @author Hu.Walker
 */
public class UserAction extends BaseAction {
    String MODULE_NAME = "人员管理";
    String[] CREATE = { "create", "创建人员" };
    String[] DELETE = { "delete", "删除人员" };
    String[] SET_DEPARTMENT_USER = { "setDepartmentuser", "给部门分配用户" };
    String[] SET_JOB_USER = { "setJobUser", "给用户分配岗位" };
    String[] UPDATE = { "update", "修改岗位资料" };

    public ActionForward toCreate(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        DynaActionForm daf = (DynaActionForm) form;
        daf.set("id", "");
        saveToken(request);

        return mapping.findForward("create");
    }

    public ActionForward create(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            User user = OrganizationFactory.getInstance().createUser();
            user.setUserName(request.getParameter("userName"));
            user.setPassword(request.getParameter("password"));
            user.setUserType(request.getParameter("userType"));
            user.setLevel(request.getParameter("level"));
            user.setStatus(request.getParameter("status"));
            user.setValidDate(request.getParameter("validDate"));
            user.setName(request.getParameter("name"));
            user.setCode(request.getParameter("code"));
            user.setGender(request.getParameter("gender"));

            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            userDAO.createUser(user);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return list(mapping, form, request, response);
    }

    public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            String[] id = request.getParameterValues("id");
            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            User user = userDAO.findUserByID(Integer.parseInt(id[0]));
            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", String.valueOf(user.getId()));
            daf.set("userName", user.getUserName());
            daf.set("password", user.getPassword());
            daf.set("userType", user.getUserType());
            daf.set("level", user.getLevel());
            daf.set("status", user.getStatus());
            daf.set("validDate", user.getValidDate());
            daf.set("name", user.getName());
            daf.set("code", user.getCode());
            daf.set("gender", user.getGender());
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return mapping.findForward("update");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            User user = OrganizationFactory.getInstance().createUser();
            user.setId(Integer.parseInt(request.getParameter("id")));
            user.setUserName(request.getParameter("userName"));
            user.setPassword(request.getParameter("password"));
            user.setUserType(request.getParameter("userType"));
            user.setLevel(request.getParameter("level"));
            user.setStatus(request.getParameter("status"));
            user.setValidDate(request.getParameter("validDate"));
            user.setName(request.getParameter("name"));
            user.setCode(request.getParameter("code"));
            user.setGender(request.getParameter("gender"));

            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            userDAO.updateUser(user);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return list(mapping, form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            String[] id = request.getParameterValues("id");
            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            userDAO.deleteUser(id);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            DynaValidatorForm dForm = (DynaValidatorForm) form;
            String page = (String) dForm.get("page");
            request.setAttribute("page", page);

            PageControl pageControl = new AbstractPageControlHelper() {
                        UserDAO userDAO = OrganizationFactory.getInstance()
                                                             .getUserDAO();
                        DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                                         .getDepartmentDAO();

                        public Collection getPagerData(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response, long start, long count)
                            throws Exception {
//                            start = setStart(form, start, count);

                            if (request.getParameter("departid") != null) {
                                Department department = OrganizationFactory.getInstance()
                                                                           .createDepartment();
                                department.setId(Integer.parseInt(
                                        String.valueOf(request.getParameter(
                                                "departid"))));
                                //==========================================================================
                                //胡勇添加，增加JSP视图列表排序功能
                                OrderList _order = OrderList.getInstance();
                                String orderby = request.getParameter(OrderList.employee_key);
                                String desc = request.getParameter(OrderList.desc);
                            
                                if (!_order.isNull(orderby) && !_order.isNull(desc)) {
                                    String[] ss = {orderby, desc};
                                    request.getSession(true).setAttribute(OrderList.employee_key, ss);
                                    departmentDAO.setOrderby(ss);
                                } else {
                                    String[] ss = (String[]) request.getSession(true).getAttribute(OrderList.employee_key);
                                    if (ss != null) {
                                        departmentDAO.setOrderby(ss);
                                    }
                                }
                                //==========================================================================
                                Collection col = departmentDAO.getUsersForPage(department,
                                        Integer.parseInt(String.valueOf(start)),
                                        Integer.parseInt(String.valueOf(count)));

                                return col;
                            } else {
                                return userDAO.getUsersForPage(Integer.parseInt(
                                        String.valueOf(start)),
                                    Integer.parseInt(String.valueOf(count)));
                            }
                        }

//                        private long setStart(ActionForm form, long start,
//                            long count) throws NumberFormatException {
//                            DynaValidatorForm dForm = (DynaValidatorForm) form;
//                            String page = (String) dForm.get("page");
//                            long lPage = Long.parseLong(page);
//                            lPage = (lPage > 0) ? (lPage - 1) : 0;
//                            start = lPage * count;
//
//                            return start;
//                        }

                        public long getRowCount(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response)
                            throws Exception {
                            if (request.getParameter("departid") != null) {
                                Department department = OrganizationFactory.getInstance()
                                                                           .createDepartment();
                                department.setId(Integer.parseInt(
                                        String.valueOf(request.getParameter(
                                                "departid"))));

                                return departmentDAO.getUsers(department).size();
                            } else {
                                return userDAO.getUserCount();
                            }
                        }
                    }.newPageControl(mapping, form, request, response);
            request.setAttribute("pageControl", pageControl);

            return mapping.findForward("list");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DefaultException(ex);
        }
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            String[] id = request.getParameterValues("id");
            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            User user = userDAO.findUserByID(Integer.parseInt(id[0]));
            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", String.valueOf(user.getId()));
            daf.set("userName", user.getUserName());
            daf.set("password", user.getPassword());
            daf.set("userType", user.getUserType());
            daf.set("level", user.getLevel());
            daf.set("status", user.getStatus());
            daf.set("validDate", user.getValidDate());
            daf.set("name", user.getName());
            daf.set("code", user.getCode());
            daf.set("gender", user.getGender());

            Collection userDepartments = userDAO.getDepartments(user);
            request.setAttribute("userDepartments", userDepartments);

            Collection userJobs = userDAO.getJobs(user);
            request.setAttribute("userJobs", userJobs);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return mapping.findForward("view");
    }

    public ActionForward userdepartment(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", request.getParameter("id"));
            daf.set("userName", request.getParameter("name"));

            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            User user = OrganizationFactory.getInstance().createUser();
            user.setId(Integer.parseInt(request.getParameter("id")));

            Collection userDepartments = userDAO.getDepartments(user);
            request.setAttribute("userDepartments", userDepartments);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return mapping.findForward("userdepartment");
    }

    public ActionForward userjob(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", request.getParameter("id"));
            daf.set("name", request.getParameter("name"));

            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            User user = OrganizationFactory.getInstance().createUser();
            user.setId(Integer.parseInt(request.getParameter("id")));

            Collection userJobs = userDAO.getJobs(user);
            request.setAttribute("userJobs", userJobs);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return mapping.findForward("userjob");
    }

    public ActionForward toPassword(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            User user = userDAO.findUserByID(Integer.parseInt(
                        request.getParameter("id")));

            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", String.valueOf(user.getId()));
            daf.set("userName", user.getUserName());
            daf.set("password", user.getPassword());
            daf.set("userType", user.getUserType());
            daf.set("level", user.getLevel());
            daf.set("status", user.getStatus());
            daf.set("validDate", user.getValidDate());
            daf.set("name", user.getName());
            daf.set("code", user.getCode());
            daf.set("gender", user.getGender());
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return mapping.findForward("password");
    }

    public ActionForward setPassword(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            User user = userDAO.findUserByID(Integer.parseInt(
                        request.getParameter("id")));

            user.setPassword(request.getParameter("password"));

            userDAO.updateUser(user);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return list(mapping, form, request, response);
    }

    public ActionForward selectDepartmentUser(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        try {
            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();
            Department department = OrganizationFactory.getInstance()
                                                       .createDepartment();
            department.setId(Integer.parseInt(request.getParameter("departid")));

            Collection departmentUsers = departmentDAO.getUsers(department);
            request.setAttribute("departmentUsers", departmentUsers);

            Collection unDepartmentUsers = departmentDAO.getUnDistributeUsers(department);
            request.setAttribute("unDepartmentUsers", unDepartmentUsers);

            return mapping.findForward("seldptuser");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward selectJobUser(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            UserDAO userDAO = OrganizationFactory.getInstance().getUserDAO();
            Department dept = OrganizationFactory.getInstance()
                                                 .createDepartment();
            User user = OrganizationFactory.getInstance().createUser();
            dept.setId(Integer.parseInt(request.getParameter("departid")));
            user.setId(Integer.parseInt(request.getParameter("id")));

            Collection jobUsers = userDAO.getJobs(user, dept);
            request.setAttribute("jobUsers", jobUsers);

            Collection unJobUsers = userDAO.getUnDistributeJobs(user, dept);
            request.setAttribute("unJobUsers", unJobUsers);

            request.setAttribute("userID", request.getParameter("id"));

            return mapping.findForward("seljobuser");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward setJobUser(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            int iUserId = Integer.parseInt(request.getParameter("userID"));
            String strJobId = request.getParameter("jobId");
            StringTokenizer st = new StringTokenizer(strJobId, ",");
            int[] jobId = new int[st.countTokens()];
            int[] userId = new int[st.countTokens()];
//            System.err.println(st.countTokens());

            for (int i = 0; st.hasMoreTokens(); i++) {
                jobId[i] = Integer.parseInt(st.nextToken());
                userId[i] = iUserId;
            }

            UserJobDAO userJobDAO = OrganizationFactory.getInstance()
                                                       .getUserJobDAO();
            userJobDAO.deleteUserJobByUser(iUserId);

            if (jobId.length > 0) {
                userJobDAO.createUserJob(userId, jobId);
            }

            super.addLog(request, MODULE_NAME, this.SET_JOB_USER[0],
                this.SET_JOB_USER[1]);

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DefaultException(ex);
        }
    }

    public ActionForward setDepartmentUser(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        try {
            int iDepartmentId = Integer.parseInt(request.getParameter(
                        "departid"));
            String strUserId = request.getParameter("userId");
            StringTokenizer st = new StringTokenizer(strUserId, ",");
            int[] userId = new int[st.countTokens()];
            int[] departmentId = new int[st.countTokens()];

            for (int i = 0; st.hasMoreTokens(); i++) {
                userId[i] = Integer.parseInt(st.nextToken());
                departmentId[i] = iDepartmentId;
            }

            UserDepartmentDAO userDepartmentDAO = OrganizationFactory.getInstance()
                                                                     .getUserDepartmentDAO();
            userDepartmentDAO.deleteUserDepartmentByDepartment(iDepartmentId);

            if (userId.length > 0) {
                userDepartmentDAO.createUserDepartment(userId, departmentId);
            }

            super.addLog(request, MODULE_NAME, this.SET_DEPARTMENT_USER[0],
                this.SET_DEPARTMENT_USER[1]);

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DefaultException(ex);
        }
    }

    public void getUsersByDepartment(Department department, java.util.Set users)
        throws Exception {
        try {
            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();

            Collection col = departmentDAO.getUsersForPage(department, 0,
                    Integer.MAX_VALUE);
            users.addAll(col);

            Collection subDepartments = departmentDAO.getSubDepartments(department);
            Iterator iterator = subDepartments.iterator();

            while (iterator.hasNext()) {
                Department department2 = (Department) iterator.next();
                getUsersByDepartment(department2, users);
            }
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DefaultException(e);
        }
    }
}
