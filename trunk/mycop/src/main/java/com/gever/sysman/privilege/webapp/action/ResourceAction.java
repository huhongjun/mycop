package com.gever.sysman.privilege.webapp.action;

import java.util.Collection;

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
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.ResourceDAO;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.tree.ResourceTreeNode;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;
import com.gever.util.tree.GeverTreeNode;


/**
资源
@author
 */
public class ResourceAction extends BaseAction {
    /**
     * 资源列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    String MODULE_NAME = "资源管理";
    String[] CREATE = { "create", "创建资源" };
    String[] DELETE = { "delete", "删除资源" };
    String[] UPDATE = { "update", "修改资源" };

    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PageControl pageControl;

        try {
            pageControl = new AbstractPageControlHelper() {
                        PrivilegeFactory factory = PrivilegeFactory.getInstance();
                        ResourceDAO resourceDAO = factory.getResourceDAO();

                        public Collection getPagerData(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response, long start, long count)
                            throws Exception {
                            //                            start = setStart(form, start, count);
                            return resourceDAO.getResources((int) start,
                                (int) count);
                        }

                        //                        private long setStart(ActionForm form, long start,
                        //                            long count) throws NumberFormatException {
                        //                            DynaActionForm dForm = (DynaActionForm) form;
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
                            long resourceCount = resourceDAO.getResourceCount();

                            return resourceCount;
                        }
                    }.newPageControl(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_RES_007", DefaultException.ERROR, ex);
        }

        request.setAttribute("pageControl", pageControl);

        return mapping.findForward("list");
    }

    /**
     @param mapping
     @param form
     @param request
     @param response
     @return org.apache.struts.action.ActionForward
     @throws java.lang.Exception
     @roseuid 40BAC96700CC
      */
    public ActionForward update(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        ResourceDAO resourceDAO = factory.getResourceDAO();
        DynaActionForm f = (DynaActionForm) form;
        String page = (String) f.get("page");

        long id = Long.parseLong((String) f.get("id"));
        String name = (String) f.get("name");
        String description = (String) f.get("description");
        long parentid = Long.parseLong((String) f.get("parentid"));
        String resourceType = (String) f.get("resourceType");
        String link = (String) f.get("link");
        String targetStr = (String) f.get("target");
        String code = (String) f.get("code");

        char target = ' ';

        if ((targetStr != null) && (!"".equals(targetStr))) {
            target = targetStr.charAt(0);
        }

        Resource resource = factory.createResource();
        resource.setId(id);
        resource.setName(name);
        resource.setDescription(description);
        resource.setParentID(parentid);
        resource.setResType(resourceType);
        resource.setLink(link);
        resource.setTarget(target);
        resource.setCode(code);

        try {
            resourceDAO.updateResource(resource);
        } catch (DAOException ex) {
            throw new DAOException("PRV_RES_009", DAOException.ERROR, ex);
        }

        super.addLog(request, MODULE_NAME, this.UPDATE[0], this.UPDATE[1]);

        return getListChild(mapping, form, request, response);
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC96701C5
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        try {
            String[] ids = request.getParameterValues("id");
            ResourceDAO resourceDAO = PrivilegeFactory.getInstance()
                                                      .getResourceDAO();

            //DynaActionForm f = (DynaActionForm)form;
            if (ids != null) {
                for (int i = 0; i < ids.length; i++) {
                    Resource resource = resourceDAO.findResourceByID(Long.parseLong(
                                ids[i]));

                    if (resource.getId() == 0) {
                        continue;
                    }

                    resourceDAO.deleteResource(resource);
                }
            }

            super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);

            return getListChild(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_RES_003", DefaultException.ERROR, ex);
        }
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC96702AF
     */
    public ActionForward addResource(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        ResourceDAO resourceDAO = PrivilegeFactory.getInstance().getResourceDAO();
        DynaActionForm f = (DynaActionForm) form;

        String name = (String) f.get("name");
        String description = (String) f.get("description");
        String parentId = (String) f.get("parentid");
        String resourceType = (String) f.get("resourceType");
        String link = (String) f.get("link");
        String target = (String) f.get("target");
        String code = (String) f.get("code");

        Resource parentResource = null;

        if ((parentId != null) && !"".equals(parentId)) {
            try {
                parentResource = resourceDAO.findResourceByID(Long.parseLong(
                            parentId));
            } catch (DAOException ex) {
                throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
            }
        }

        Resource resource = null;

        try {
            resource = PrivilegeFactory.getInstance().createResource();
            resource.setName(name);
            resource.setDescription(description);
            resource.setParentID(Long.parseLong(parentId));
            resource.setResType(resourceType);
            resource.setLink(link);
            resource.setCode(code);

            if ((target != null) && (!"".equals(target))) {
                resource.setTarget(target.charAt(0));
            }

            resource.setParent(parentResource);
            resourceDAO.createResource(resource);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_RES_015", DefaultException.ERROR, ex);
        }

        super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);

        // modify by eddy on 20041213----\\\
        // 防止新建后刷新出错而做的转向
        return new RedirectingActionForward(
            "resourceAction.do?action=getListChild&resid=" + parentId);

        //        return mapping.findForward("toListChild");//getListChild(mapping, form, request, response);
        // modify by eddy on 20041213----///
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC96703A9
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        try {
            long id = Long.parseLong(request.getParameter("id"));

            if (id == -1) {
                throw new DefaultException("PRV_RES_009", DefaultException.ERROR);
            }

            ResourceDAO resourceDAO = PrivilegeFactory.getInstance()
                                                      .getResourceDAO();
            DynaActionForm f = (DynaActionForm) form;
            Resource resource = resourceDAO.findResourceByID(id);

            if (resource == null) {
                throw new DefaultException("PRV_RES_009", DefaultException.ERROR);
            }

            Resource parent = resourceDAO.findResourceByID(resource.getParentID());
            resource.setParent(parent);
            f.set("id", Long.toString(id));
            f.set("name", resource.getName());
            f.set("description", resource.getDescription());
            f.set("parentid", String.valueOf(resource.getParentID()));
            f.set("resourceType", resource.getResType());
            f.set("link", resource.getLink());
            f.set("target", String.valueOf(resource.getTarget()));
            f.set("code", resource.getCode());

            String page = (String) f.get("page");
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_RES_009", DefaultException.ERROR, ex);
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
    @roseuid 40BAC96800BB
     */
    public ActionForward create(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        return mapping.findForward("create");
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC96802AF
     */
    public ActionForward deleteResourceOperation(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        ResourceDAO resourceDAO = PrivilegeFactory.getInstance().getResourceDAO();
        DynaActionForm f = (DynaActionForm) form;

        return mapping.findForward("create");
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC96803B9
     */
    public ActionForward addResourceOperation(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        ResourceDAO resourceDAO = PrivilegeFactory.getInstance().getResourceDAO();
        DynaActionForm f = (DynaActionForm) form;

        String name = (String) f.get("name");
        String description = (String) f.get("description");
        String parentIdStr = (String) f.get("parentResourceID");
        String resourceTypeStr = (String) f.get("resourceType");
        String code = (String) f.get("code");

        if ((name == null) || "".equals(name)) {
            throw new DefaultException("DB_DAO_001", DefaultException.ERROR);
        }

        if (resourceDAO.findResourceByName(name) != null) {
            throw new DefaultException("PRV_RES_004", DefaultException.ERROR);
        }

        Resource parentResource = null;

        if ((parentIdStr != null) && !"".equals(parentIdStr)) {
            try {
                parentResource = resourceDAO.findResourceByID(Long.parseLong(
                            parentIdStr));
            } catch (DAOException ex) {
                throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
            }
        }

        Resource resource = null;

        try {
            resource = PrivilegeFactory.getInstance().createResource();
            resource.setName(name);
            resource.setDescription(description);
            resource.setParent(parentResource);
            resource.setCode(code);
            resourceDAO.createResource(resource);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_RES_017", DefaultException.ERROR, ex);
        }

        f.set("id", Long.toString(resource.getId()));

        if (parentResource != null) {
            f.set("parentResourceID", Long.toString(parentResource.getId()));
            f.set("parentResourceName", parentResource.getName());
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
    @roseuid 40BAC96900DA
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        return edit(mapping, form, request, response);
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC96A000F
     */
    public ActionForward listResourceOperations(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        return null;
    }

    /**
    @param mapping
    @param form
    @param request
    @param response
    @return org.apache.struts.action.ActionForward
    @throws java.lang.Exception
    @roseuid 40BAC96A0119
     */
    public ActionForward saveResourceOperation(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        return null;
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
    public ActionForward newSubResource(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        long parentID = Long.parseLong(request.getParameter("parent"));

        try {
            ResourceDAO resourceDAO = PrivilegeFactory.getInstance()
                                                      .getResourceDAO();

            if (parentID != -1) {
                Resource resource = resourceDAO.findResourceByID(parentID);

                if (resource == null) {
                    throw new DefaultException("PRV_RES_001",
                        DefaultException.ERROR);
                }

                DynaActionForm f = (DynaActionForm) form;
                f.set("parent", Long.toString(parentID));
            }
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return mapping.findForward("newResource");
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
    public ActionForward tree(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;
        Resource root = null;
        Resource resource = null;

        long id;

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {
            //e.printStackTrace();
            id = 1;
        }

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        ResourceDAO resourceDAO = factory.getResourceDAO();

        try {
            root = (Resource) resourceDAO.getRootResource();
        } catch (DAOException ex) {
            throw new DAOException("PRV_RES_007", DAOException.ERROR, ex);
        }

        if (root == null) {
            throw new DefaultException("PRV_RES_007", DefaultException.ERROR);
        }

        try {
            resource = resourceDAO.findResourceByID(id);
        } catch (DAOException ex) {
            throw new DefaultException("PRV_RES_001", DefaultException.ERROR, ex);
        }

        if (resource == null) {
            throw new DefaultException("PRV_RES_001", DefaultException.ERROR);
        }

        ResourceTreeNode rootNode = new ResourceTreeNode(root);
        GeverTreeNode.explore(rootNode, new ResourceTreeNode(resource));
        request.setAttribute("exploreAction", "resourceAction.do?action=tree");
        request.setAttribute("viewAction", "resourceAction.do?action=view");
        request.setAttribute("rootNode", rootNode);

        return mapping.findForward("tree");
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
    public ActionForward smallTree(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;
        Resource root = null;
        Resource resource = null;

        long id = 1;

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {
            //e.printStackTrace();
            id = 1;
        }

        String formElement = request.getParameter("formElement");

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        ResourceDAO resourceDAO = factory.getResourceDAO();

        try {
            root = (Resource) resourceDAO.getRootResource();
        } catch (DAOException ex) {
            throw new DAOException("PRV_RES_007", DAOException.ERROR, ex);
        }

        if (root == null) {
            throw new DefaultException("PRV_RES_007", DefaultException.ERROR);
        }

        try {
            resource = resourceDAO.findResourceByID(id);
        } catch (DAOException e) {
            resource = root;
        }

        if (resource == null) {
            throw new DefaultException("PRV_RES_007", DefaultException.ERROR);
        }

        ResourceTreeNode rootNode = new ResourceTreeNode(root);
        GeverTreeNode.explore(rootNode, new ResourceTreeNode(resource));
        request.setAttribute("exploreAction",
            "/privilege/resourceAction.do?action=smallTree&&formElement=" +
            formElement);
        request.setAttribute("viewAction",
            "/privilege/resourceAction.do?action=view");
        request.setAttribute("rootNode", rootNode);
        request.setAttribute("formElement", formElement);

        return mapping.findForward("smallTree");
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
    public ActionForward getListChild(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PageControl pageControl;

        try {
            DynaActionForm dForm = (DynaActionForm) form;
            String page = (String) dForm.get("page");
            request.setAttribute("page", page);

            pageControl = new AbstractPageControlHelper() {
                        PrivilegeFactory factory = PrivilegeFactory.getInstance();
                        ResourceDAO resourceDAO = factory.getResourceDAO();

                        public Collection getPagerData(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response, long start, long count)
                            throws Exception {
                            String resid = request.getParameter("resid");

                            //                            start = setStart(form, start, count);
                            //==========================================================================
                            //胡勇添加，增加JSP视图列表排序功能
                            OrderList _order = OrderList.getInstance();
                            String orderby = request.getParameter(OrderList.res_key);
                            String desc = request.getParameter(OrderList.desc);

                            if (!_order.isNull(orderby) &&
                                    !_order.isNull(desc)) {
                                String[] ss = { orderby, desc };
                                request.getSession(true).setAttribute(OrderList.res_key,
                                    ss);
                                resourceDAO.setOrderby(ss);
                            } else {
                                String[] ss = (String[]) request.getSession(true)
                                                                .getAttribute(OrderList.res_key);

                                if (ss != null) {
                                    resourceDAO.setOrderby(ss);
                                }
                            }

                            //==========================================================================
                            return resourceDAO.getChileResources(resid,
                                (int) start, (int) count);
                        }

                        //                        private long setStart(ActionForm form, long start,
                        //                            long count) throws NumberFormatException {
                        //                            DynaActionForm dForm = (DynaActionForm) form;
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
                            String resid = request.getParameter("resid");
                            long resourceCount = resourceDAO.getChildResourceCount(resid);

                            return resourceCount;
                        }
                    }.newPageControl(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_RES_012", DefaultException.ERROR, ex);
        }

        request.setAttribute("pageControl", pageControl);

        return mapping.findForward("getListChild");
    }
}
