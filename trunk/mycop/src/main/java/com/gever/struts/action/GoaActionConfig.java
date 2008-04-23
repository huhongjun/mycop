package com.gever.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import com.gever.jdbc.BaseDAO;
import com.gever.struts.form.BaseForm;
import com.gever.struts.pager.PageHelper;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class GoaActionConfig {
    public GoaActionConfig() {
    }

    private ActionMapping mapping;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private PageHelper pageHelper;
    private HttpSession session;
    private BaseForm baseForm;
    private BaseDAO baseDao ;
    public ActionMapping getMapping() {
        return mapping;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setMapping(ActionMapping mapping) {
        this.mapping = mapping;
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper = pageHelper;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public BaseForm getBaseForm() {
        return baseForm;
    }

    public void setBaseForm(BaseForm baseForm) {
        this.baseForm = baseForm;
    }
    public BaseDAO getBaseDao() {
        return baseDao;
    }
    public void setBaseDao(BaseDAO baseDao) {
        this.baseDao = baseDao;
    }

}