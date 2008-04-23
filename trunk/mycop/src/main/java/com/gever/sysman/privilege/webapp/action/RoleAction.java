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
import com.gever.sysman.level.dao.LevelDAO;
import com.gever.sysman.level.dao.impl.DefaultLevelDAO;
import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.PermissionDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.ResourceDAO;
import com.gever.sysman.privilege.dao.RoleDAO;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.sysman.privilege.model.Role;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;
import com.gever.web.homepage.dao.UserMenuDAOFactory;
import com.gever.web.homepage.dao.UserMenuDao;


/**
 * ��ɫ���Action
 */
public class RoleAction extends BaseAction {
    String MODULE_NAME = "��ɫ����";
    String[] CREATE = { "create", "������ɫ" };
    String[] DELETE = { "delete", "ɾ����ɫ" };
    String[] UPDATE = { "update", "�޸Ľ�ɫ����" };

    public ActionForward addUserRole(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        DynaActionForm df = (DynaActionForm) form;
        Role role = factory.createRole();
        role.setName((String) df.get("name"));
        role.setDescription((String) df.get("description"));

        RoleDAO roleDAO = factory.getRoleDAO();

        if (roleDAO.haveRoleName(role.getName())) {
            throw new DefaultException("PRV_ROLE_031", DefaultException.ERROR);
        }

        try {
            roleDAO.createRole(role);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);

        // modify by eddy on 20041213----\\\
        // ��ֹ�½���ˢ�³��������ת��
        return new RedirectingActionForward(
            "roleAction.do?action=list");

        // return list(mapping, form, request, response);
        // modify by eddy on 20041213----///
    }

    /**
     * �г���ǰ�û���ʣ�µĿɹ�ѡ��Ľ�ɫ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward listRoleByUser(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PageControl pageControl;

        try {
            pageControl = new AbstractPageControlHelper() {
                        PrivilegeFactory factory = PrivilegeFactory.getInstance();
                        RoleDAO roleDAO = factory.getRoleDAO();

                        public Collection getPagerData(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response, long start, long count)
                            throws Exception {
                            String id = request.getParameter("uid");

                            return roleDAO.getOtherRolesByID(id, start, count);
                        }

                        public long getRowCount(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response)
                            throws Exception {
                            String id = request.getParameter("uid");
                            long count = roleDAO.getOtherRoleCount(id);

                            return count;
                        }
                    }.newPageControl(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (DefaultException ex) {
            throw new DefaultException("PRV_ROLE_005", DefaultException.ERROR,
                ex);
        }

        request.setAttribute("pageControl", pageControl);

        return mapping.findForward("listRoleByUser");
    }

    /**
     * �г���ǰ�û��Ѿ�ӵ�еĽ�ɫ�ͻ�ʣ�µĿɹ�ѡ��Ľ�ɫ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward listRoles(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        Collection curr_user_roles = null;
        Collection other_user_roles = null;

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        RoleDAO roleDAO = factory.getRoleDAO();
        String id = request.getParameter("uid");

        //��ǰ�û��ĵ����н�ɫ
        try {
            curr_user_roles = roleDAO.getRolesByID(id);

            //��ǰ�û���ʣ�µĹ�ѡ��Ľ�ɫ
            other_user_roles = roleDAO.getOtherRolesByID(id);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        request.setAttribute("curr_user_roles", curr_user_roles);
        request.setAttribute("other_user_roles", other_user_roles);

        return mapping.findForward("listRoles");
    }

    public ActionForward permission(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        long rid = Long.parseLong(request.getParameter("rid"));

        try {
            PrivilegeFactory factory = PrivilegeFactory.getInstance();
            Role role = factory.createRole();
            RoleDAO roleDAO = factory.getRoleDAO();
            role = roleDAO.findRoleByID(rid);

            Collection res = roleDAO.getResources(role);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        }

        return mapping.findForward("permission");
    }

    /**
     * ��ɫ�б�
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PageControl pageControl;

        try {
            pageControl = new AbstractPageControlHelper() {
                        PrivilegeFactory factory = PrivilegeFactory.getInstance();
                        RoleDAO roleDAO = factory.getRoleDAO();

                        public Collection getPagerData(ActionMapping mapping,
                            ActionForm form, HttpServletRequest request,
                            HttpServletResponse response, long start, long count)
                            throws Exception {
                            //                            start = setStart(form, start, count);
                            //==========================================================================
                            //������ӣ�����JSP��ͼ�б�������
                            OrderList _order = OrderList.getInstance();
                            String orderby = request.getParameter(OrderList.role_key);
                            String desc = request.getParameter(OrderList.desc);

                            if (!_order.isNull(orderby) &&
                                    !_order.isNull(desc)) {
                                String[] ss = { orderby, desc };
                                request.getSession(true).setAttribute(OrderList.role_key,
                                    ss);
                                roleDAO.setOrderby(ss);
                            } else {
                                String[] ss = (String[]) request.getSession(true)
                                                                .getAttribute(OrderList.role_key);

                                if (ss != null) {
                                    roleDAO.setOrderby(ss);
                                }
                            }

                            //==========================================================================
                            return roleDAO.getRoles(start, count);
                        }

                        //                        private long setStart(ActionForm form, long start,
                        //                            long count) throws NumberFormatException {
                        //                            DynaActionForm dForm = (DynaActionForm) form;
                        //                            String page = (String) dForm.get("page");
                        //                            page = page.equals("") ? "0" : page;
                        //
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
                            long count = roleDAO.getRoleCount();

                            return count;
                        }
                    }.newPageControl(mapping, form, request, response);
        } catch (DAOException ex) {
            throw new DAOException("DB_DAO_001", DAOException.ERROR, ex);
        } catch (DefaultException ex) {
            throw new DefaultException("PRV_ROLE_019", DefaultException.ERROR,
                ex);
        }

        request.setAttribute("pageControl", pageControl);

        return mapping.findForward("list");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        RoleDAO roleDAO = factory.getRoleDAO();

        DynaActionForm df = (DynaActionForm) form;

        long id = Long.parseLong((String) df.get("id"));

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Role role = factory.createRole();
        role.setId(id);
        role.setName(name);
        role.setDescription(description);

        /*
            Role roleByName=roleDAO.findRoleByName(role.getName());
                    if ((roleByName!=null)&&(roleByName.getId()!=role.getId())){

                            errors.add("exist_role",new ActionError("exist.role"));
                            saveErrors(request, errors);
                            return mapping.findForward("failed");
                    }
         */
        /*
                                             if (roleDAO.haveRoleName(role.getName())){
                                                 throw new DefaultException("PRV_ROLE_001", DefaultException.ERROR);
                                            }
        */
        try {
            roleDAO.updateRole(role);
        } catch (DAOException ex) {
            throw new DAOException("PRV_ROLE_032", DAOException.ERROR, ex);
        }

        try {
            super.addLog(request, MODULE_NAME, this.UPDATE[0], this.UPDATE[1]);

            return list(mapping, form, request, response);

            //   return mapping.findForward("view");
        } catch (Exception ex) {
            throw new DefaultException("PRV_ROLE_015", DefaultException.ERROR,
                ex);
        }
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        String[] ids = request.getParameterValues("id");

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        RoleDAO roleDAO = factory.getRoleDAO();

        try {
            roleDAO.deleteRolesByIds(ids);
        } catch (DAOException ex) {
            throw new DAOException("PRV_ROLE_014", DAOException.ERROR, ex);
        }

        super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);

        return list(mapping, form, request, response);
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        RoleDAO roleDAO = factory.getRoleDAO();

        long id = Integer.parseInt(request.getParameter("id"));

        DynaActionForm df = (DynaActionForm) form;

        Role role = null;

        try {
            role = roleDAO.findRoleByID(id);
        } catch (DAOException ex) {
            throw new DAOException("PRV_ROLE_028", DAOException.ERROR, ex);
        }

        if (role == null) {
            throw new DefaultException("PRV_ROLE_028", DefaultException.ERROR);
        }

        df.set("id", Long.toString(id));
        df.set("name", role.getName());
        df.set("description", role.getDescription());
        request.setAttribute("roleForm", df);

        return mapping.findForward("edit");
    }

    public ActionForward create(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        return mapping.findForward("create");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws DefaultException {
        DynaActionForm f = (DynaActionForm) form;

        long id;

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {
            throw new DefaultException("PRV_ROLE_016", DefaultException.ERROR);
        }

        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        RoleDAO roleDAO = factory.getRoleDAO();

        Role role = null;

        try {
            role = roleDAO.findRoleByID(id);
        } catch (DAOException ex) {
            throw new DAOException("PRV_ROLE_028", DAOException.ERROR, ex);
        }

        if (role == null) {
            throw new DefaultException("PRV_ROLE_016", DefaultException.ERROR);
        }

        f.set("id", Long.toString(role.getId()));
        f.set("name", role.getName());
        f.set("description", role.getDescription());

        //Role parentRole = role.getParentRole();
        //		if (parentRole != null) {
        //			f.set("parentRoleID", Long.toString(parentRole.getId()));
        //			f.set("parentRoleName", parentRole.getName());
        //		}
        return mapping.findForward("view");
    }

    /**
           * ��ӷ������ݣ��ڽ�ɫ�û�������䡰�û��б�
           * ���ʱ�䣺2004��11��17��
           * ��ӷ������ݣ��ڷ�ҳ��ʱ��ȡ�ü�¼������ʱ��������UserDAO��һ���·���
                         getRoleToUser(String string);����ʵ�ֵľ��ǹ��˺��
                         ��¼������
           * ���ʱ�䣺2004��11��38��
           * ������ߣ�����
       **/
    public ActionForward userList(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        DynaActionForm dForm = (DynaActionForm) form;
        String page = (String) dForm.get("page");
        request.setAttribute("page", page);

        String rid = request.getParameter("rid");
        LevelDAO levelDAO = new DefaultLevelDAO();

        PageControl pageControl = new AbstractPageControlHelper() {
                    PrivilegeFactory factory = PrivilegeFactory.getInstance();
                    UserDAO userDAO = factory.getUserDAO();

                    public Collection getPagerData(ActionMapping mapping,
                        ActionForm form, HttpServletRequest request,
                        HttpServletResponse response, long start, long count)
                        throws Exception {
                        String rid = request.getParameter("rid");

                        //String username=request.getParameter("username");
                        //                   start = setStart(form, start, count);
                        //���ж�
                        return userDAO.getUsers((int) start, (int) count, rid);
                    }

                    //               private long setStart(ActionForm form, long start, long count) throws
                    //                   NumberFormatException {
                    //                 DynaActionForm dForm = (DynaActionForm)form;
                    //                 String page = (String)dForm.get("page");
                    //                 page = page.equals("") ? "0" : page;
                    //                 long lPage = Long.parseLong(page);
                    //                 lPage = lPage > 0 ? lPage-1 : 0;
                    //                 start = lPage * count;
                    //                 return start;
                    //               }
                    public long getRowCount(ActionMapping mapping,
                        ActionForm form, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
                        String rid = request.getParameter("rid");
                        long count = userDAO.getRoleToUserCount(rid);

                        return count;
                    }
                }.newPageControl(mapping, form, request, response);
        request.setAttribute("options", levelDAO.findLevelByName());
        request.setAttribute("pageControl", pageControl);
        request.setAttribute("rolesId", rid);

        return mapping.findForward("userlist");
    }

    /*
         *��չ�������ڽ�ɫ�����û���ʱ�����ɾ�����ܡ�
         *�޸�ʱ�䣺2004��11��17��
         *�޸����ߣ�����
         *��չ�˷����Ĺ��ܣ���ɾ��һ���û�����ô����û���ӵ�������ɫ��Ȩ��
         Ҳ��ɾ����������û�����ӵ�������Ȩ�޺�������ɫ��������Ȩ�ޡ�
         *�޸�ʱ�䣺2004��11��24��
         *�޸����ߣ��������ָ����
        */
    public ActionForward deleteUserFromRole(ActionMapping mapping,
        ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        String[] id = request.getParameterValues("id");
        String roleId = request.getParameter("roleId");

        PrivilegeFactory factory = PrivilegeFactory.getInstance();

        UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();

        if (id != null) {
            userDAO.deleteUserFromRole(id, roleId);

            OperationDAO optDAO = factory.getOperationDAO();
            ResourceDAO resDAO = factory.getResourceDAO();
            PermissionDAO pmDAO = factory.getPermissionDAO();

            Collection opt_collect = null;
            Collection res_collect = null;
            Collection user_res_opt_collect = null;
            Collection user_role_perm_collect = null;
            Collection user_role1_perm_collect = null;

            //user_role_perm_collect=optDAO.getOptByRoleid(Long.parseLong(rolesId));
            //�����û�id������е���Դ�������ϣ����û�Ȩ�ޣ�
            for (int i = 0; i < id.length; i++) {
                user_res_opt_collect = optDAO.getOptByUserid(Long.parseLong(
                            id[i]));

                //�����û�id����û����н�ɫȨ�޼���
                user_role1_perm_collect = optDAO.getUserRolePerm(id[i]);

                //���ݽ�ɫid������е��û������ҽ�Ȩ�޶���������û�
                //���û������Ȩ�����û���ɫȨ�޺ϲ����õ����յ��û�Ȩ��
                user_res_opt_collect = pmDAO.getUserAndRolePerm(Long.parseLong(
                            id[i]), user_res_opt_collect,
                        user_role1_perm_collect);

                if (user_res_opt_collect != null) {
                    // ��ȡ�û�ӵ�е���Դ
                    user_res_opt_collect = pmDAO.getResCollect(user_res_opt_collect);

                    // ��ȡ������Դ����������Դ��
                    user_res_opt_collect = pmDAO.getAllResources(user_res_opt_collect);
                }

                //Collection opt_isOpt_collect = pmDAO.returnUserOpts(opt_collect,user_res_opt_collect);
                //Collection ress = pmDAO.returnRess(res_collect, opt_isOpt_collect);
                UserMenuDao dao = UserMenuDAOFactory.getInstance()
                                                    .getUserMenuDao();

                dao.resetUserMenus(id[i], user_res_opt_collect);
            }
        }

        super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);

        return mapping.findForward("deleteList");
    }

    /**
           * ��ӷ������ݣ��ڽ�ɫ�û����桰ѡ���û���
           * ���ʱ�䣺2004��11��17��
           * ��ӷ������ݣ��ڷ�ҳ��ʱ��ȡ�ü�¼������ʱ��������UserDAO��һ���·���
                         getRoleToUser(String string);����ʵ�ֵľ��ǹ��˺��
                         ��¼������
           * ���ʱ�䣺2004��11��28��
           * ������ߣ�����
       **/
    public ActionForward selectUser(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PrivilegeFactory factory = PrivilegeFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        LevelDAO levelDAO = new DefaultLevelDAO();

        String rid = request.getParameter("rid");

        Collection users_selected = userDAO.getUsersInRoles(rid);

        // Collection roles_selected = roleDAO.getOtherRolesByID(id);
        Collection users_noSelect = userDAO.getSelectUsersInRoles(rid);
        request.setAttribute("users_selected", users_selected);
        request.setAttribute("users_noSelected", users_noSelect);

        // request.setAttribute("users_noSelect", users_noSelect);
        request.setAttribute("options", levelDAO.findLevelByName());

        return mapping.findForward("selectman");
    }
}
