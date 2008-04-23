package com.gever.sysman.privilege.webapp.action;


import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;

import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.ResourceDAO;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *资源操作相关Action
 */
public class ResourceOperationAction extends BaseAction {
    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC95C03C9
     */
    String MODULE_NAME = "操作管理";
    String[] CREATE = { "create", "创建操作" };
    String[] DELETE = { "delete", "删除操作" };
    String[] UPDATE = { "update", "修改操作" };

    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        OperationDAO operationDAO = factory.getOperationDAO();
        Collection opts = null;
        String id = request.getParameter("resid");
        //==========================================================================
        //胡勇添加，增加JSP视图列表排序功能
        OrderList _order = OrderList.getInstance();
        String orderby = request.getParameter(OrderList.opt_key);
        String desc = request.getParameter(OrderList.desc);
                            
        if (!_order.isNull(orderby) && !_order.isNull(desc)) {
            String[] ss = {orderby, desc};
            request.getSession(true).setAttribute(OrderList.opt_key, ss);
            operationDAO.setOrderby(ss);
        } else {
            String[] ss = (String[]) request.getSession(true).getAttribute(OrderList.opt_key);
            if (ss != null) {
                operationDAO.setOrderby(ss);
            }
        }
        //==========================================================================
        try {
            if ((id != null) && (!"".equals(id)) &&
                    !"null".equalsIgnoreCase(id)) {
                opts = operationDAO.getOperationsByResid(id);
            } else {
                opts = operationDAO.getOperations();
            }
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        request.setAttribute("pageControl", opts);

        return mapping.findForward("list");
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC95D00CC
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            PrivilegeFactory factory = PrivilegeFactory.getInstance();
            OperationDAO operationDAO = factory.getOperationDAO();
            DynaActionForm f = (DynaActionForm) form;

            long id = Long.parseLong((String) f.get("id"));
            String name = (String) f.get("name");
            String description = (String) f.get("description");
            long resourceid = Long.parseLong((String) f.get("Resource_ID"));
            String code = (String) f.get("code");
            Operation operation = factory.createOperation();
            operation.setId(id);
            operation.setName(name);
            operation.setResourceID(resourceid);
            operation.setDescription(description);
            operation.setCode(code);

            //	resource.setTables(tables);
            operationDAO.updateOperation(operation);

            super.addLog(request, MODULE_NAME, this.UPDATE[0], this.UPDATE[1]);

            return list(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_OPERATION_001",
                DefaultException.ERROR, ex);
        }
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC95D01C5
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        String[] ids = request.getParameterValues("id");

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        OperationDAO operationDAO = factory.getOperationDAO();

        try {
            operationDAO.deleteOperation(ids);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        try {
            super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);

            return list(mapping, form, request, response);
        } catch (Exception ex) {
            throw new DefaultException("ORG_OPERATION_002",
                DefaultException.ERROR, ex);
        }
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC95D02BF
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            OperationDAO operationDAO = PrivilegeFactory.getInstance()
                                                        .getOperationDAO();
            DynaActionForm f = (DynaActionForm) form;

            String name = (String) f.get("name");
            String description = (String) f.get("description");
            String code = (String) f.get("code");

            if ((name == null) || "".equals(name)) {
                throw new DefaultException("ORG_OPERATION_003",
                    DefaultException.ERROR);
            }

            Operation operation = null;

            operation = PrivilegeFactory.getInstance().createOperation();
            operation.setName(name);
            operation.setDescription(description);
            operationDAO.createOperation(operation);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_OPERATION_003",
                DefaultException.ERROR, ex);
        }

        return mapping.findForward("view");
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC95D03B9
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            long id = Long.parseLong(request.getParameter("id"));

            if (id == -1) {
                throw new DefaultException("ORG_OPERATION_004",
                    DefaultException.ERROR);
            }

            OperationDAO operationDAO = PrivilegeFactory.getInstance()
                                                        .getOperationDAO();
            DynaActionForm f = (DynaActionForm) form;
            Operation operation = operationDAO.findOperationByID(id);

            if (operation == null) {
                throw new DefaultException("ORG_OPERATION_004",
                    DefaultException.ERROR);
            }

            f.set("name", operation.getName());
            f.set("description", operation.getDescription());
            f.set("Resource_ID", String.valueOf(operation.getResourceID()));
            f.set("code", operation.getCode());
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_OPERATION_004",
                DefaultException.ERROR, ex);
        }

        return mapping.findForward("edit");
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC95E00CB
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        OperationDAO operationDAO = factory.getOperationDAO();
        DynaActionForm df = (DynaActionForm) form;

        String name = (String) df.get("name");
        String description = (String) df.get("description");
        long resource_id = Long.parseLong((String) df.get("Resource_ID"));
        String code = (String) df.get("code");
        Operation operation = null;

        try {
            operation = factory.createOperation();
            operation.setName(name);
            operation.setDescription(description);
            operation.setResourceID(resource_id);
            operation.setCode(code);
            operationDAO.createOperation(operation);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_OPERATION_004",
                DefaultException.ERROR, ex);
        }

        super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);

        return list(mapping, form, request, response);
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC95E01C5
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;

        long id;

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception ex) {
            throw new DefaultException("PRV_RES_001", DefaultException.ERROR, ex);
        }

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        OperationDAO operationDAO = factory.getOperationDAO();

        Operation operation = null;

        try {
            operation = operationDAO.findOperationByID(id);
        } catch (DAOException ex) {
            throw new DAOException("PRV_RES_001", DAOException.ERROR, ex);
        }

        if (operation == null) {
            throw new DefaultException("PRV_RES_001", DefaultException.ERROR);
        }

        f.set("id", Long.toString(operation.getId()));
        f.set("name", operation.getName());
        f.set("description", operation.getDescription());

        return mapping.findForward("view");
    }

    public ActionForward getTreeRoot(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            ResourceDAO resDAO = PrivilegeFactory.getInstance().getResourceDAO();
            Resource root = (Resource) resDAO.getRootResource();
            request.setAttribute("rootNode", root);

            return mapping.findForward("getTreeRoot");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("ORG_OPERATION_005",
                DefaultException.ERROR, ex);
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

        Collection resources;

        try {
            resources = resourceDAO.getChilds(String.valueOf(id));
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
        }

        request.setAttribute("childs", resources);

        return mapping.findForward("getTreeChild");
    }
}
