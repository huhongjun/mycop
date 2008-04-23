//Source file: E:\\lichm\\MyWorks\\GDP\\工作区\\GDP工程\\Sysman\\src\\com\\gever\\sysman\\organization\\webapp\\action\\JobAction.java

package com.gever.sysman.organization.webapp.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//屈波
import java.util.Collection;
import com.gever.sysman.organization.model.*;
import com.gever.sysman.organization.dao.*;

import com.gever.struts.pager.AbstractPageControlHelper;
import com.gever.struts.pager.PageControl;
import com.gever.sysman.util.BaseAction;

/**
 职位处理
 @author Hu.Walker
 */
public class JobDepartmentAction
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

        Job job = OrganizationFactory.getInstance().createJob();
        job.setId(Integer.parseInt(request.getParameter("id")));
        job.setName(request.getParameter("name"));
        job.setDescription(request.getParameter("description"));
        job.setDepartment(Integer.parseInt(request.getParameter("department")));

        JobDAO jobDAO = OrganizationFactory.getInstance().
            getJobDAO();
        jobDAO.createJob(job);

        return list(mapping, form, request, response);
    }

    public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
        Exception {
        String[] id = request.getParameterValues("id");
        JobDAO jobDAO = OrganizationFactory.getInstance().
            getJobDAO();
        Job job = jobDAO.findJobByID(Integer.parseInt(id[0]));
        DynaActionForm daf = (DynaActionForm) form;
        daf.set("id", String.valueOf(job.getId()));
        daf.set("name", job.getName());
        daf.set("description", job.getDescription());
        daf.set("department", String.valueOf(job.getDepartment()));
        daf.set("departmentName", String.valueOf(job.getDepartmentName()));
        return mapping.findForward("update");
    }
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws
        Exception {

        Job job = OrganizationFactory.getInstance().createJob();
        job.setId(Integer.parseInt(request.getParameter("id")));
        job.setName(request.getParameter("name"));
        job.setDescription(request.getParameter("description"));
        job.setDepartment(Integer.parseInt(request.getParameter("department")));

        JobDAO jobDAO = OrganizationFactory.getInstance().
            getJobDAO();
        jobDAO.updateJob(job);
        return list(mapping, form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws
        Exception {
        String[] id = request.getParameterValues("id");
        JobDAO jobDAO = OrganizationFactory.getInstance().
            getJobDAO();
        jobDAO.deleteJob(id);
        return list(mapping, form, request, response);
    }
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws
        Exception {
        PageControl pageControl = new AbstractPageControlHelper() {
            JobDAO jobDAO = OrganizationFactory.getInstance().getJobDAO();
            public Collection getPagerData(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response, long start,
                                           long count) throws Exception {
                return jobDAO.getJobs(Integer.parseInt(String.valueOf(
                    start)),
                    Integer.parseInt(String.valueOf(count)));
            }
            public long getRowCount(ActionMapping mapping,
                                    ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
                return jobDAO.getJobCount();
            }

        }
        .newPageControl(mapping, form, request, response);
        request.setAttribute("pageControl", pageControl);
        return mapping.findForward("list");
    }
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws
        Exception {
        String[] id = request.getParameterValues("id");
        JobDAO jobDAO = OrganizationFactory.getInstance().
            getJobDAO();
        Job job = jobDAO.findJobByID(Integer.parseInt(id[0]));
        DynaActionForm daf = (DynaActionForm) form;
        daf.set("id", String.valueOf(job.getId()));
        daf.set("name", job.getName());
        daf.set("description", job.getDescription());
        daf.set("department", String.valueOf(job.getDepartment()));
        daf.set("departmentName", String.valueOf(job.getDepartmentName()));
        return mapping.findForward("view");
    }

}
