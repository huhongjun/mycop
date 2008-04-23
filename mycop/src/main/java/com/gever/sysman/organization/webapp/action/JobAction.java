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

import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.struts.pager.AbstractPageControlHelper;
import com.gever.struts.pager.PageControl;
import com.gever.sysman.organization.dao.*;
import com.gever.sysman.organization.model.*;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;


/**
 职位处理
 @author Hu.Walker
 */
public class JobAction extends BaseAction {
    String MODULE_NAME = "岗位管理";
    String[] CREATE = { "create", "创建岗位" };
    String[] DELETE = { "delete", "删除岗位" };
    String[] SET_DEPARTMENT_JOB = { "setJobUser", "分配岗位用户" };
    String[] UPDATE = { "update", "修改岗位资料" };

    public ActionForward toCreate(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            DynaActionForm daf = (DynaActionForm) form;

            daf.set("id", "");

            daf.set("name", "");

            daf.set("description", "");

            daf.set("department", "");

            daf.set("departmentName", "");

            saveToken(request);

            return mapping.findForward("create");
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }

    public ActionForward create(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            Job job = OrganizationFactory.getInstance().createJob();
            job.setName(request.getParameter("name"));
            job.setDescription(request.getParameter("description"));
            job.setDepartment(Integer.parseInt(request.getParameter("departid")));
          int departid=Integer.parseInt(request.getParameter("departid"));

            JobDAO jobDAO = OrganizationFactory.getInstance().getJobDAO();

            /*
            if (jobDAO.findJobByName(job.getName()).size() != 0){
                throw new DefaultException("ORG_Job_001", DefaultException.ERROR);
            }
            */
            jobDAO.createJob(job);
            super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);

            // modify by eddy on 20041213----\\\
            // 防止新建后刷新出错而做的转向
            return new RedirectingActionForward("JobAction.do?action=list&departid="+departid);

            // return list(mapping, form, request, response);
            // modify by eddy on 20041213----///
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }
    }

    public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            String[] id = request.getParameterValues("id");

            JobDAO jobDAO = OrganizationFactory.getInstance().getJobDAO();
            Job job = jobDAO.findJobByID(Integer.parseInt(id[0]));

            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", String.valueOf(job.getId()));
            daf.set("name", job.getName());
            daf.set("description", job.getDescription());
            daf.set("department", String.valueOf(job.getDepartment()));
            daf.set("departmentName", String.valueOf(job.getDepartmentName()));

            return mapping.findForward("update");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_Job_002", DefaultException.ERROR, ex);
        }
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            Job job = OrganizationFactory.getInstance().createJob();
            job.setId(Integer.parseInt(request.getParameter("id")));
            job.setDepartment(Integer.parseInt(request.getParameter("departid")));
            job.setName(request.getParameter("name"));
            job.setDescription(request.getParameter("description"));

            //job.setDepartment(Integer.parseInt(request.getParameter("department")));
            JobDAO jobDAO = OrganizationFactory.getInstance().getJobDAO();

            /*
            if (jobDAO.findJobByName(job.getName()).size() != 0){
                  throw new DefaultException("ORG_Job_002", DefaultException.ERROR);
              }
            */
            jobDAO.updateJob(job);
            super.addLog(request, MODULE_NAME, this.UPDATE[0], this.UPDATE[1]);

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DefaultException("ORG_Job_002", DefaultException.ERROR, ex);
        }
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            String[] id = request.getParameterValues("id");
            JobDAO jobDAO = OrganizationFactory.getInstance().getJobDAO();

            if (id != null) {
                jobDAO.deleteJob(id);
                super.addLog(request, MODULE_NAME, this.DELETE[0],
                    this.DELETE[1]);
            }

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DefaultException(ex);
        }
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            final String departmentId = request.getParameter("departid");
            PageControl pageControl = new AbstractPageControlHelper() {
                        DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                                         .getDepartmentDAO();
                        JobDAO jobDAO = OrganizationFactory.getInstance()
                                                           .getJobDAO();

                        public Collection getPagerData(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response, long start, long count)
                            throws Exception {
                            //                            start = setStart(form, start, count);
                            //==========================================================================
                            //胡勇添加，增加JSP视图列表排序功能
                            OrderList _order = OrderList.getInstance();
                            String orderby = request.getParameter(OrderList.job_key);
                            String desc = request.getParameter(OrderList.desc);

                            if (!_order.isNull(orderby) &&
                                    !_order.isNull(desc)) {
                                String[] ss = { orderby, desc };
                                request.getSession(true).setAttribute(OrderList.job_key,
                                    ss);
                                jobDAO.setOrderby(ss);
                            } else {
                                String[] ss = (String[]) request.getSession(true)
                                                                .getAttribute(OrderList.job_key);

                                if (ss != null) {
                                    jobDAO.setOrderby(ss);
                                }
                            }

                            //==========================================================================
                            if ((departmentId != null) &&
                                    !"0".equals(departmentId) &&
                                    !"".equals(departmentId)) {
                                Department department = OrganizationFactory.getInstance()
                                                                           .createDepartment();
                                department.setId(Integer.parseInt(
                                        String.valueOf(departmentId)));

                                Collection col = departmentDAO.getJobsForPage(department,
                                        Integer.parseInt(String.valueOf(start)),
                                        Integer.parseInt(String.valueOf(count)));

                                return col;
                            } else {
                                return jobDAO.findJobsForPage(" 0=0 ",
                                    Integer.parseInt(String.valueOf(start)),
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
                            if ((departmentId != null) &&
                                    !"0".equals(departmentId) &&
                                    !"".equals(departmentId)) {
                                Department department = OrganizationFactory.getInstance()
                                                                           .createDepartment();
                                department.setId(Integer.parseInt(
                                        String.valueOf(departmentId)));

                                return departmentDAO.getJobs(department).size();
                            } else {
                                return jobDAO.getJobCount();
                            }
                        }
                    }.newPageControl(mapping, form, request, response);

            request.setAttribute("pageControl", pageControl);

            DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();
            String strDepartment = "公司";

            if ((departmentId != null) && !"0".equals(departmentId) &&
                    !"".equals(departmentId)) {
                Department department = departmentDAO.findDepartmentNameByid(departmentId);

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
            JobDAO jobDAO = OrganizationFactory.getInstance().getJobDAO();
            Job job = jobDAO.findJobByID(Integer.parseInt(id[0]));
            DynaActionForm daf = (DynaActionForm) form;
            daf.set("id", String.valueOf(job.getId()));
            daf.set("name", job.getName());
            daf.set("description", job.getDescription());
            daf.set("department", String.valueOf(job.getDepartment()));
            daf.set("departmentName", String.valueOf(job.getDepartmentName()));

            return mapping.findForward("view");
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
            JobDAO jobDAO = OrganizationFactory.getInstance().getJobDAO();
            Job job = OrganizationFactory.getInstance().createJob();
            job.setId(Integer.parseInt(request.getParameter("id")));
            job.setDepartment(Integer.parseInt(request.getParameter("departid")));

            Collection jobUsers = jobDAO.getUsersByDept(job);
            request.setAttribute("jobUsers", jobUsers);

            Collection unJobUsers = jobDAO.getUnDistributeUsersByDept(job);
            request.setAttribute("unJobUsers", unJobUsers);

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
            int iJobId = Integer.parseInt(request.getParameter("id"));
            String strUserId = request.getParameter("userIds");
            StringTokenizer st = new StringTokenizer(strUserId, ",");
            int[] userId = new int[st.countTokens()];
            int[] jobId = new int[st.countTokens()];

            for (int i = 0; st.hasMoreTokens(); i++) {
                userId[i] = Integer.parseInt(st.nextToken());
                jobId[i] = iJobId;
            }

            UserJobDAO userJobDAO = OrganizationFactory.getInstance()
                                                       .getUserJobDAO();
            userJobDAO.deleteUserJobByJob(iJobId);

            if (userId.length > 0) {
                userJobDAO.createUserJob(userId, jobId);
            }

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException(ex);
        }
    }
}
