package com.gever.sysman.organization.webapp.action;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//屈波
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
 * <p>Company: KOBE　OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class UserJobAction
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

        UserJob userJob = OrganizationFactory.getInstance().
            createUserJob();
        userJob.setUser(Integer.parseInt(request.getParameter("user")));
        userJob.setJob(Integer.parseInt(request.getParameter("job")));

        UserJobDAO userJobDAO = OrganizationFactory.getInstance().
            getUserJobDAO();
        userJobDAO.createUserJob(userJob);
        super.addLog(request,"用户岗位","create","创建一个用户岗位关联");
        return list(mapping, form, request, response);
    }

    public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
        Exception {
        String[] id = request.getParameterValues("id");
        int[] user = new int[id.length];
        int[] job = new int[id.length];
        StringTokenizer st = null;

        for (int i = 0; i < id.length; i++) {
            st = new StringTokenizer(id[i], ",");
            user[i] = Integer.parseInt(st.nextToken());
            job[i] = Integer.parseInt(st.nextToken());
        }

        UserJobDAO userJobDAO = OrganizationFactory.getInstance().
            getUserJobDAO();

        UserJob userJob = userJobDAO.findUserJobByUserByJob(user[0], job[0]);

        DynaActionForm daf = (DynaActionForm) form;
        daf.set("user", String.valueOf(userJob.getUser()));
        daf.set("userName", String.valueOf(userJob.getUserName()));
        daf.set("job", String.valueOf(userJob.getJob()));
        daf.set("jobName", String.valueOf(userJob.getJobName()));
        daf.set("userUpdate", String.valueOf(userJob.getUser()));
        daf.set("jobUpdate", String.valueOf(userJob.getJob()));

        return mapping.findForward("update");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws
        Exception {

        UserJob userJob = OrganizationFactory.getInstance().
            createUserJob();

        DynaActionForm daf = (DynaActionForm) form;
        userJob.setUser(Integer.parseInt( (String) daf.get("user")));
        userJob.setJob(Integer.parseInt( (String) daf.get("job")));

        UserJobDAO userJobDAO = OrganizationFactory.getInstance().
            getUserJobDAO();

        userJobDAO.updateUserJob(userJob,
                                 Integer.parseInt( (String) (daf.get("userUpdate"))),
                                 Integer.parseInt( (String) (daf.get("jobUpdate"))));

        return list(mapping, form, request, response);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws
        Exception {
        String[] id = request.getParameterValues("id");
        int[] user = new int[id.length];
        int[] job = new int[id.length];
        StringTokenizer st = null;

        for (int i = 0; i < id.length; i++) {
            st = new StringTokenizer(id[i], ",");
            user[i] = Integer.parseInt(st.nextToken());
            job[i] = Integer.parseInt(st.nextToken());
        }

        UserJobDAO userJobDAO = OrganizationFactory.getInstance().
            getUserJobDAO();
        userJobDAO.deleteUserJob(user, job);

        return list(mapping, form, request, response);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws
        Exception {

        PageControl pageControl = new AbstractPageControlHelper() {
            UserJobDAO userJobDAO = OrganizationFactory.getInstance().getUserJobDAO();

            public Collection getPagerData(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response, long start,
                                           long count) throws Exception {
//              start = setStart(form, start, count);
              return userJobDAO.findUserJobsForPage(Integer.parseInt(String.valueOf(
                  start)), Integer.parseInt(String.valueOf(count)));
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
              return userJobDAO.getUserJobCount();
            }

        }

        .newPageControl(mapping, form, request, response);

        request.setAttribute("pageControl", pageControl);

        return mapping.findForward("list");
    }

    public ActionForward lists(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws
        Exception {
        UserJobDAO userJobDAO = OrganizationFactory.getInstance().
            getUserJobDAO();
        Collection userJobs = userJobDAO.findUserJobs();
        request.setAttribute("userJobs", userJobs);

        return mapping.findForward("list");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws
        Exception {

        String[] user = request.getParameterValues("user");
        String[] job = request.getParameterValues("job");

        UserJobDAO userJobDAO = OrganizationFactory.getInstance().
            getUserJobDAO();
        UserJob userJob = userJobDAO.
            findUserJobByUserByJob(Integer.parseInt(user[0]),
                                   Integer.parseInt(job[0]));

        DynaActionForm daf = (DynaActionForm) form;
        daf.set("user", String.valueOf(userJob.getUser()));
        daf.set("userName", String.valueOf(userJob.getUserName()));
        daf.set("job", String.valueOf(userJob.getJob()));
        daf.set("jobName", String.valueOf(userJob.getJobName()));

        return mapping.findForward("view");
    }

}
