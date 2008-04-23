package com.gever.sysman.privilege.webapp.action;


import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;

import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.ResourceDAO;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.util.BaseAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetResOpTreeAction extends BaseAction {
    public GetResOpTreeAction() {
    }

    public ActionForward getResOpByResID(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        OperationDAO operationDAO = factory.getOperationDAO();
        Collection opts = null;
        String id = request.getParameter("resid");

        try {
            if ((id != null) && (!"".equals(id)) &&
                    !"null".equalsIgnoreCase(id)) {
                opts = operationDAO.getOperationsByResid(id);
            } else {
                opts = operationDAO.getOperations();
            }

            ResourceDAO resourceDAO = factory.getResourceDAO();
            Resource res = resourceDAO.findResourceByID(Integer.parseInt(id));
            String resCode = res.getCode();
            request.setAttribute("resCode", resCode);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        request.setAttribute("opts", opts);

        return mapping.findForward("getResOpByResID");
    }

    public ActionForward getTreeRoot(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            ResourceDAO resDAO = PrivilegeFactory.getInstance().getResourceDAO();
            Resource root = (Resource) resDAO.getRootResource();
            request.setAttribute("rootNode", root);

            //黎彪增加代码开始 2004-11-18
            String bNoView = (String) request.getParameter("bNoView");

            if ((bNoView == null) || ("").equals(bNoView)) {
                bNoView = "0";
            }

            request.setAttribute("bNoView", bNoView); //

            //黎彪增加代码结束
            return mapping.findForward("getTreeRoot");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_RES_007", DefaultException.ERROR, ex);
        }
    }

    /**
    *
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
    public ActionForward getTreeChild(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;
        Resource root = null;
        Resource resource = null;

        long id = 1;

        try {
            id = Long.parseLong(request.getParameter("resid"));
        } catch (Exception e) {
            //e.printStackTrace();
            id = 1;
        }

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        ResourceDAO resourceDAO = factory.getResourceDAO();

        Collection resources = resourceDAO.getChilds(String.valueOf(id));
        request.setAttribute("childs", resources);

        return mapping.findForward("getTreeChild");
    }
}
