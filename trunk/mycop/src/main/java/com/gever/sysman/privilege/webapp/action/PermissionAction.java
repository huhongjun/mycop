/*
 * 创建日期 2004-6-20
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.gever.sysman.privilege.webapp.action;


import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;

import com.gever.sysman.privilege.dao.*;
import com.gever.sysman.privilege.model.*;
import com.gever.sysman.util.BaseAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Hu.Walker
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class PermissionAction extends BaseAction {
    int level = 0;

    /**
    *
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
    public ActionForward permTree(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;
        Resource root = null;
        Resource resource = null;
        Collection childs = null;
        PrivilegeFactory factory = null;
        ResourceDAO resourceDAO = null;

        long id;

        try {
            id = Long.parseLong(request.getParameter("resid"));
        } catch (Exception e) {
            //e.printStackTrace();
            id = 1;
        }

        factory = PrivilegeFactory.getInstance();
        resourceDAO = factory.getResourceDAO();

        try {
            root = (Resource) resourceDAO.getRootResource();
        } catch (DAOException ex) {
            throw new DAOException("PRV_ROLE_033", DAOException.ERROR, ex);
        }

        if (root == null) {
            throw new DefaultException("PRV_ROLE_033", DefaultException.ERROR);
        }

        try {
            resource = resourceDAO.findResourceByID(id);
            childs = resourceDAO.getChilds(String.valueOf(id));
        } catch (DAOException e) {
            System.err.println(e.getMessage());
            resource = root;
        }

        if (resource == null) {
            throw new DefaultException("PRV_ROLE_033", DefaultException.ERROR);
        }

        request.setAttribute("rootNode", root);
        request.setAttribute("curRes", resource);
        request.setAttribute("childs", childs);
        request.setAttribute("xmlstr", toXML(childs));

        return mapping.findForward("permTree");
    }

    private String toXML(Collection c) {
        StringBuffer sb = new StringBuffer();
        Resource res = null;
        Iterator it = c.iterator();

        while (it.hasNext()) {
            res = (Resource) it.next();

            if (res.getChildNum() > 0) {
                sb.append("<tree nodeid =\"" + res.getId() + "\" text=\"" +
                    res.getName() +
                    "\" src=\"/gdp/permissionAction.do?action=xtree&#38;resid=" +
                    res.getId() + "\" />");
            } else {
                sb.append("<tree  nodeid =\"" + res.getId() + "\" text=\"" +
                    res.getName() +
                    "\" action=\"/gdp/permissionAction.do?action=\" />");
            }
        }

        return sb.toString();
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
    public ActionForward xtree(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;
        Resource root = null;
        Resource resource = null;
        Collection childs = null;

        long id;

        try {
            id = Long.parseLong(request.getParameter("resid"));
        } catch (Exception e) {
            //e.printStackTrace();
            id = 1;
        }

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        ResourceDAO resourceDAO = factory.getResourceDAO();

        try {
            root = (Resource) resourceDAO.getRootResource();
        } catch (DAOException ex) {
            throw new DAOException("PRV_ROLE_033", DAOException.ERROR, ex);
        }

        if (root == null) {
            throw new DefaultException("PRV_ROLE_033", DefaultException.ERROR);
        }

        try {
            resource = resourceDAO.findResourceByID(id);
            childs = resourceDAO.getChilds(String.valueOf(id));
        } catch (DAOException e) {
            System.err.println(e.getMessage());
            resource = root;
        }

        if (resource == null) {
            throw new DefaultException("PRV_ROLE_033", DefaultException.ERROR);
        }

        request.setAttribute("rootNode", root);
        request.setAttribute("curRes", resource);
        request.setAttribute("childs", childs);
        request.setAttribute("xmlstr", toXML(childs));

        return mapping.findForward("xtree");
    }

    /**
     * 生成用户权限列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward user_perm_table(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;
//        System.out.println("page: " + request.getParameter("page"));
        f.set("page", request.getParameter("page"));

        Collection opt_collect = null;
        Collection res_collect = null;
        Collection user_res_opt_collect = null;
        Collection user_role_perm_collect = null;
        String tree_table_str;
        long id;

        try {
            id = Long.parseLong(request.getParameter("userid"));
        } catch (Exception e) {
            //e.printStackTrace();
            id = 1;
        }

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        OperationDAO optDAO = factory.getOperationDAO();
        ResourceDAO resDAO = factory.getResourceDAO();
        PermissionDAO pmDAO = factory.getPermissionDAO();

        try {
            //获得所有的资源集合
            res_collect = resDAO.getResourcesTree();

            //获得所有的操作集合
            opt_collect = optDAO.getOperations();

            //根据用户id获得所有的资源操作集合（即用户权限）
            user_res_opt_collect = optDAO.getOptByUserid(id);

            //根据用户id获得用户所有角色权限集合
            user_role_perm_collect = optDAO.getUserRolePerm(String.valueOf(id));

            //将用户本身的权限与用户角色权限合并，得到最终的用户权限
            user_res_opt_collect = pmDAO.getUserAndRolePerm(id,
                    user_res_opt_collect, user_role_perm_collect);

            Collection user_res_opt_collect2 = pmDAO.getUserAndRolePerm(id,
                    user_res_opt_collect, user_role_perm_collect);
            Collection opt_isOpt_collect = pmDAO.returnUserOpts(opt_collect,
                    user_res_opt_collect);
            Collection ress = pmDAO.returnRess(res_collect, opt_isOpt_collect);

            //根据以上条件得到一个可选择的树型列表
            //tree_table_str = pmDAO.user_process(ress);
            if (user_res_opt_collect != null) {
                // 获取用户拥有的资源
                user_res_opt_collect = pmDAO.getResCollect(user_res_opt_collect);

                // 获取所有资源（包括父资源）
                user_res_opt_collect = pmDAO.getAllResources(user_res_opt_collect);
            }

            // 设置资源和操作
            Collection ressAndOprs = resDAO.getResourcesAndOperations();
            request.setAttribute("user_role_perm_collect",
                user_role_perm_collect);
            request.setAttribute("ress", user_res_opt_collect);
            request.setAttribute("user_res_opt_collect", user_res_opt_collect2);
            request.setAttribute("ressAndOprs", ressAndOprs);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        //request.setAttribute("tree_user_table_str", tree_table_str);
        return mapping.findForward("user_perm_table");
    }

    /**
     * 生成角色权限列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward role_perm_table(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;
        Collection opt_collect = null;
        Collection res_collect = null;
        Collection role_res_opt_collect = null;
        String tree_table_str;
        long id;

        try {
            id = Long.parseLong(request.getParameter("roleid"));
        } catch (Exception e) {
            //e.printStackTrace();
            id = 1;
        }

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        OperationDAO optDAO = factory.getOperationDAO();
        ResourceDAO resDAO = factory.getResourceDAO();
        PermissionDAO pmDAO = factory.getPermissionDAO();

        try {
            //获得所有的资源集合
            res_collect = resDAO.getResourcesTree();

            //获得所有的操作集合
            opt_collect = optDAO.getOperations();

            //根据角色id获得所有的资源操作集合（即角色权限）
            role_res_opt_collect = optDAO.getOptByRoleid(id);

            Collection role_res_opt_collect2 = optDAO.getOptByRoleid(id);

            Collection opt_isOpt_collect = pmDAO.returnOpts(opt_collect,
                    role_res_opt_collect);

            Collection ress = pmDAO.returnRess(res_collect, opt_isOpt_collect);

            //根据以上条件得到一个可选择的树型列表
            tree_table_str = pmDAO.process(ress);

            if (role_res_opt_collect != null) {
                // 获取用户拥有的资源
                role_res_opt_collect = pmDAO.getResCollectByRole(role_res_opt_collect);

                // 获取所有资源（包括父资源）
                role_res_opt_collect = pmDAO.getAllResources(role_res_opt_collect);
            }

            // 设置资源和操作
            Collection ressAndOprs = resDAO.getResourcesAndOperations();
            request.setAttribute("ress", role_res_opt_collect);
            request.setAttribute("user_res_opt_collect", role_res_opt_collect2);
            request.setAttribute("ressAndOprs", ressAndOprs);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        request.setAttribute("tree_table_str", tree_table_str);

        return mapping.findForward("role_perm_table");
    }

    /**
     * 存储用户权限
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward savaUserPerm(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        DynaActionForm f = (DynaActionForm) form;
        Object page = f.get("page");
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        String userid = request.getParameter("userid");

        //用户权限
        String[] res_opt_ids = request.getParameterValues("opt");

        //用户角色权限
        String[] res_opt_ids_role = request.getParameterValues("r_opt");
        PermissionDAO permDAO = PrivilegeFactory.getInstance().getPermissionDAO();

        try {
            if ((userid == null) || ("".equals(userid))) {
                throw new DAOException();
            }

            Collection perm_collect = permDAO.getUserPerm(userid, res_opt_ids);

            permDAO.savaUserPerm(userid, perm_collect);

            permDAO.resetMenuRes(userid, perm_collect);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_ROLE_034", DefaultException.ERROR,
                ex);
        }

        //  return mapping.findForward("savaRolePerm");
        PrivilegeUserAction userAction = new PrivilegeUserAction();
        userAction.list(mapping, form, request, response);
        f.set("page", page);

        return mapping.findForward("userlist");
    }

    /**
     * 存储角色权限
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward savaRolePerm(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        DynaActionForm f = (DynaActionForm) form;

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        String roleid = request.getParameter("roleid");
        String[] res_opt_ids = request.getParameterValues("opt");
        PermissionDAO permDAO = PrivilegeFactory.getInstance().getPermissionDAO();

        try {
            if ((roleid == null) || ("".equals(roleid))) {
                throw new DefaultException("PRV_ROLE_035");
            }

            Collection perm_collect = permDAO.getRolePerm(roleid, res_opt_ids);

            //保存角色权限
            permDAO.savaRolePerm(roleid, perm_collect);
/*
             杨帆 2004-11-26修改
             *********************************************************
             * 由于每次更新角色权限都要更新所有用户权限，用户较多时将影响    *
             * 系统效率，因此将此处更新用户权限去掉，在每次用户登录时更新    *
             * 权限，另外，在用户定制菜单时也可实时更新用户权限。           *
             * 在“角色添加用户”模块中存在同样问题，但考虑所操作用户数量    *
             * 较小，暂不做处理。                                       *
             *********************************************************

            // 处理菜单定制模块级联
            Role role = PrivilegeFactory.getInstance().createRole();

            role.setId(Long.parseLong(roleid));

            RoleDAO roleDAO = PrivilegeFactory.getInstance().getRoleDAO();
            Collection users = roleDAO.getUsers(role);

            Iterator iUsers = users.iterator();

            while (iUsers.hasNext()) {
                I_User user = (I_User) iUsers.next();
                String[] oprAndRes = permDAO.getUserPerm(user.getId());
                Collection perm_collect2 = permDAO.getUserPerm(Long.toString(
                            user.getId()), oprAndRes);

                permDAO.savaUserPerm(Long.toString(user.getId()), perm_collect2);

                permDAO.resetMenuRes(Long.toString(user.getId()), perm_collect2);
            }
*/
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (Exception ex) {
            throw new DefaultException("PRV_ROLE_035", DefaultException.ERROR,
                ex);
        }

        //  return mapping.findForward("savaRolePerm");
        RoleAction roleAction = new RoleAction();

        return roleAction.list(mapping, form, request, response);
    }
}
