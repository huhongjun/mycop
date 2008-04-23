package com.gever.sysman.organization.webapp.action;


//屈波
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.RedirectingActionForward;
import org.apache.struts.validator.DynaValidatorForm;

import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.struts.pager.AbstractPageControlHelper;
import com.gever.struts.pager.PageControl;
import com.gever.sysman.organization.dao.*;
import com.gever.sysman.organization.model.*;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;


/**
 部门处理
 @author Hu.Walker
 */
public class DepartmentAction extends BaseAction {
    String MODULE_NAME = "部门管理";
    String[] CREATE = { "create", "创建部门" };
    String[] DELETE = { "delete", "删除部门" };
    String[] SET_DEPARTMENT_JOB = { "setDepartmentJob", "分配部门岗位" };
    String[] SET_DEPARTMENT_USER = { "setDepartmentUser", "分配部门用户" };
    String[] UPDATE = { "update", "修改部门资料" };

    public ActionForward toCreate(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            DynaActionForm daf = (DynaActionForm) form;
            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();

            if (Long.parseLong(request.getParameter("departid")) != 0) {
                Department dept = departmentDAO.findDepartmentByID(Long.parseLong(
                            request.getParameter("departid")));
                daf.set("id", "");
                daf.set("parentDepartment", String.valueOf(dept.getId()));
                daf.set("parentDepartmentName", String.valueOf(dept.getName()));
            } else {
                daf.set("parentDepartment", "0");
                daf.set("parentDepartmentName", "公司");
            }

            saveToken(request);

            return mapping.findForward("create");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward create(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            DynaActionForm daf = (DynaActionForm) form;
            String pageNumber = (String) request.getSession().getAttribute("page");
            daf.set("page", pageNumber);

            Department department = OrganizationFactory.getInstance()
                                                       .createDepartment();
             long  departid=Long.parseLong(request.getParameter("departid"));
            department.setCode(request.getParameter("code"));
            department.setName(request.getParameter("name"));
            department.setDescription(request.getParameter("description"));
            department.setDepartmentType(request.getParameter("departmentType")); //libiao在2004-11-26发现这个字段没有使用
            department.setFunctioary(request.getParameter("functionary"));
            department.setParentDepartment(Integer.parseInt(
                    request.getParameter("parentDepartment")));

            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();

            if (departmentDAO.findDepartmentByCode(department.getCode()).size() != 0) {
                throw new DefaultException("ORG_Depart_022",
                    DefaultException.ERROR);
            }

            departmentDAO.insertDepartment(department);
            super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);

            // modify by eddy on 20041213----\\\
            // 防止新建后刷新出错而做的转向
            return new RedirectingActionForward(
                "DepartmentAction.do?action=list&departid="+departid);

            // return list(mapping, form, request, response);
            // modify by eddy on 20041213----///
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (DefaultException ex) {
            throw new DefaultException("ORG_Depart_022",
                DefaultException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_Depart_001",
                DefaultException.ERROR, ex);
        }
    }

    public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            String[] id = request.getParameterValues("id");

            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();
            Department department = departmentDAO.findDepartmentByID(Integer.parseInt(
                        id[0]));

            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", String.valueOf(department.getId()));
            daf.set("code", department.getCode());
            daf.set("name", department.getName());
            daf.set("description", department.getDescription());
            daf.set("departmentType", department.getDepartmentType());
            daf.set("functionary", department.getFunctionary()); //libiao add at 2004-11-25
            daf.set("parentDepartment",
                String.valueOf(department.getParentDepartment()));
            daf.set("parentDepartmentName",
                String.valueOf(department.getParentDepartmentName()));

            return mapping.findForward("update");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            Department department = OrganizationFactory.getInstance()
                                                       .createDepartment();
            DynaValidatorForm dForm = (DynaValidatorForm) form;

            department.setId(Integer.parseInt(request.getParameter("id")));
            department.setCode(request.getParameter("code"));
            department.setName(request.getParameter("name"));
            department.setDescription(request.getParameter("description"));
            department.setDepartmentType(request.getParameter("departmentType"));
            department.setFunctioary(request.getParameter("functionary")); //libiao add at 2004-11-25
            department.setParentDepartment(Integer.parseInt(
                    request.getParameter("parentDepartment")));

            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();

            Collection departments = departmentDAO.findDepartmentByCode(department.getCode());
            Iterator iDepartments = departments.iterator();

            if (((departmentDAO.findDepartmentByName(department.getName()).size() != 0) ||
                    (departmentDAO.findDepartmentByCode(department.getCode())
                                      .size() != 0)) &&
                    (department.getId() != department.getId())) {
                throw new DefaultException("ORG_Depart_023",
                    DefaultException.ERROR);
            }

            departmentDAO.updateDepartment(department);
            super.addLog(request, MODULE_NAME, this.UPDATE[0], this.UPDATE[1]);

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (DefaultException ex) {
            throw new DefaultException("ORG_Depart_023",
                DefaultException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_Depart_002",
                DefaultException.ERROR, ex);
        }
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            if (request.getParameterValues("id") != null) {
                String[] id = request.getParameterValues("id");
                DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                                 .getDepartmentDAO();
                departmentDAO.deleteDepartment(id);
            }

            super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            DynaValidatorForm dForm = (DynaValidatorForm) form;
            String page = (String) dForm.get("page");
            request.setAttribute("page", page);
            request.getSession().setAttribute("page", page);

            PageControl pageControl = new AbstractPageControlHelper() {
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
                                String orderby = request.getParameter(OrderList.department_key);
                                String desc = request.getParameter(OrderList.desc);

                                if (!_order.isNull(orderby) &&
                                        !_order.isNull(desc)) {
                                    String[] ss = { orderby, desc };
                                    request.getSession(true).setAttribute(OrderList.department_key,
                                        ss);
                                    departmentDAO.setOrderby(ss);
                                } else {
                                    String[] ss = (String[]) request.getSession(true)
                                                                    .getAttribute(OrderList.department_key);

                                    if (ss != null) {
                                        departmentDAO.setOrderby(ss);
                                    }
                                }

                                //==========================================================================
                                return departmentDAO.getDepartmentsForPage(Long.parseLong(
                                        request.getParameter("departid")),
                                    start, count);
                            } else {
                                return departmentDAO.getDepartmentsForPage(Integer.parseInt(
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

                                return departmentDAO.getDepartmentCount(Long.parseLong(
                                        request.getParameter("departid")));
                            } else {
                                return departmentDAO.getDepartmentCount();
                            }
                        }
                    }.newPageControl(mapping, form, request, response);
            request.setAttribute("pageControl", pageControl);

            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();
            String strDepartment = "公司";

            if (request.getParameter("departid") != null) {
                Department department = departmentDAO.findDepartmentNameByid(request.getParameter(
                            "departid"));

                if (department != null) {
                    strDepartment = department.getName();
                }
            }

            request.setAttribute("department", strDepartment);

            return mapping.findForward("list");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            String[] id = request.getParameterValues("id");

            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();
            Department department = departmentDAO.findDepartmentByID(Integer.parseInt(
                        id[0]));

            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", String.valueOf(department.getId()));
            daf.set("code", department.getCode());
            daf.set("name", department.getName());
            daf.set("description", department.getDescription());
            daf.set("departmentType", department.getDepartmentType());
            daf.set("parentDepartment",
                String.valueOf(department.getParentDepartment()));
            daf.set("parentDepartmentName",
                String.valueOf(department.getParentDepartmentName()));

            return mapping.findForward("view");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward selectDepartmentJob(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        try {
            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", request.getParameter("id"));
            daf.set("name", request.getParameter("name"));

            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();
            Department department = OrganizationFactory.getInstance()
                                                       .createDepartment();
            department.setId(Integer.parseInt(request.getParameter("id")));

            Collection departmentJobs = departmentDAO.getJobs(department);
            request.setAttribute("departmentJobs", departmentJobs);

            Collection unDepartmentJobs = departmentDAO.getUnDistributeJobs();
            request.setAttribute("unDepartmentJobs", unDepartmentJobs);

            return mapping.findForward("seldptjob");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward setDepartmentJob(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        try {
            String departmentId = request.getParameter("id");
            String strJobId = request.getParameter("jobId");
            StringTokenizer st = new StringTokenizer(strJobId, ",");
            String[] jobId = new String[st.countTokens()];

            for (int i = 0; st.hasMoreTokens(); i++) {
                jobId[i] = st.nextToken();
            }

            JobDAO JobDAO = OrganizationFactory.getInstance().getJobDAO();
            JobDAO.setDepartmentJob(departmentId, jobId);
            super.addLog(request, MODULE_NAME, SET_DEPARTMENT_JOB[0],
                SET_DEPARTMENT_JOB[1]);

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward selectDepartmentUser(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        try {
            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", request.getParameter("id"));
            daf.set("name", request.getParameter("name"));

            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();
            Department department = OrganizationFactory.getInstance()
                                                       .createDepartment();
            department.setId(Integer.parseInt(request.getParameter("id")));

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

    public ActionForward setDepartmentUser(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        try {
            int iDepartmentId = Integer.parseInt(request.getParameter("id"));
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

            super.addLog(request, MODULE_NAME, SET_DEPARTMENT_USER[0],
                SET_DEPARTMENT_USER[1]);

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward getTreeRoot(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            Department root = OrganizationFactory.getInstance()
                                                 .createDepartment();
            root.setName("公司");
            request.setAttribute("rootNode", root);

            //黎彪增加代码开始 2004-11-24
            String bNoView = (String) request.getParameter("bNoView");

            if ((bNoView == null) || ("").equals(bNoView)) {
                bNoView = "0";
            }

            request.setAttribute("bNoView", bNoView); //

            //黎彪增加代码结束
            String page = "getroottree";

            return mapping.findForward(page);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward getTreeDialog(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            Department root = OrganizationFactory.getInstance()
                                                 .createDepartment();
            root.setName("公司");
            request.setAttribute("rootNode", root);

            String page = "getdialogtree";

            return mapping.findForward(page);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward getTreeChild(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        Department dept = OrganizationFactory.getInstance().createDepartment();
        String departid = request.getParameter("departid");
        dept.setId(Integer.parseInt(departid));

        DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                         .getDepartmentDAO();
        Collection departments = departmentDAO.getSubDepartments(dept);
        request.setAttribute("childs", departments);

        String page = "getchildtree";

        return mapping.findForward(page);
    }
}
