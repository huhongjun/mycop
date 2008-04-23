package com.gever.sysman.privilege.webapp.action;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.action.RedirectingActionForward;

import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.database.dialect.Dialect;
import com.gever.jdbc.database.dialect.Global;
import com.gever.jdbc.database.dialect.OracleDialect;
import com.gever.struts.pager.AbstractPageControlHelper;
import com.gever.struts.pager.PageControl;
import com.gever.sysman.level.dao.LevelDAO;
import com.gever.sysman.level.dao.impl.DefaultLevelDAO;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.dao.*;
import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Role;
import com.gever.sysman.privilege.util.UniQueryConfig;
import com.gever.sysman.util.BaseAction;
import com.gever.sysman.util.OrderList;
import com.gever.sysman.level.model.Level;
import com.gever.sysman.level.model.impl.DefaultLevel;
import com.gever.web.homepage.dao.UserMenuDAOFactory;
import com.gever.web.homepage.dao.UserMenuDao;


/**
 * �û����Action
 */
public class PrivilegeUserAction extends BaseAction {
	public final static String COMMON_QUERY_SQL = "com.gever.sysman.privilege.USER_QUERY_SQL";

	String MODULE_NAME = "�û�����";

	String[] CREATE = { "create", "�����û�" };

	String[] DELETE = { "delete", "ɾ���û�" };

	String[] UPDATE = { "update", "�����û�" };

	/**
	 * ����"��ָ����ɫ���û��Ľ�ɫ������ɾ��"����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throws java.lang.Exception
	 * @roseuid 40BAC96001F4
	 */
	public ActionForward deleteUserRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException {
		DynaActionForm f = (DynaActionForm) form;

		long userid = Long.parseLong(request.getParameter("userid"));
		long roleid = Long.parseLong(request.getParameter("roleid"));

		if (userid == -1) {
			throw new DefaultException("PRV_ROLE_013", DefaultException.ERROR);
		}

		if (roleid == -1) {
			throw new DefaultException("PRV_ROLE_013", DefaultException.ERROR);
		}

		PrivilegeFactory factory = PrivilegeFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		I_User user = (I_User) userDAO.findUserByID(userid);

		if (user == null) {
			throw new DefaultException("PRV_ROLE_013", DefaultException.ERROR);
		}

		RoleDAO roleDAO = PrivilegeFactory.getInstance().getRoleDAO();
		Role role = roleDAO.findRoleByID(roleid);

		if (role == null) {
			throw new DefaultException("PRV_ROLE_013", DefaultException.ERROR);
		}

		try {
			userDAO.removeRole(user, role);
		} catch (DAOException e) {
			throw new DefaultException("PRV_ROLE_013", DefaultException.ERROR);
		}

		Collection roles = roleDAO.getRoles(user);
		f.set("id", Long.toString(user.getId()));
		f.set("name", user.getName());

		request.setAttribute("roles", roles);

		return mapping.findForward("listUserRoles");
	}

	/**
	 * ����"��ָ����ɫ��ӵ��û���ӵ�еĽ�ɫ������"����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throws java.lang.Exception
	 * @roseuid 40BAC96200DA
	 */
	public ActionForward addUserRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException {
		DynaActionForm f = (DynaActionForm) form;

		long id;

		try {
			id = Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
			throw new DefaultException("PRV_ROLE_002", DefaultException.ERROR);
		}

		PrivilegeFactory factory = PrivilegeFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();

		I_User user = null;

		try {
			user = (I_User) userDAO.findUserByID(id);
		} catch (DAOException e) {
			throw new DefaultException("PRV_ROLE_002", DefaultException.ERROR);
		}

		if (user == null) {
			throw new DefaultException("PRV_ROLE_002", DefaultException.ERROR);
		}

		f.set("id", Long.toString(user.getId()));
		f.set("name", user.getName());

		return mapping.findForward("addUserRoles");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			DynaActionForm dForm = (DynaActionForm) form;
			String page = (String) dForm.get("page");
			request.setAttribute("page", page);

			// �ж��Ƿ��ͨ�ò�ѯ����
			if ((request.getParameter("page") != null)
					&& (request.getSession().getAttribute(COMMON_QUERY_SQL) != null)) {
				return this.query(mapping, form, request, response);
			} else if ((request.getParameter("page") == null)
					&& (request.getSession().getAttribute(COMMON_QUERY_SQL) != null)) {
				request.getSession().removeAttribute(COMMON_QUERY_SQL);
			} else {
			}

			PageControl pageControl = new AbstractPageControlHelper() {
				PrivilegeFactory factory = PrivilegeFactory.getInstance();

				UserDAO userDAO = factory.getUserDAO();

				LevelDAO levelDAO = new DefaultLevelDAO();

				public Collection getPagerData(ActionMapping mapping,
						ActionForm form, HttpServletRequest request,
						HttpServletResponse response, long start, long count)
						throws Exception {
					//                            start = setStart(form, start, count);
					//==========================================================================
					//������ӣ�����JSP��ͼ�б�������
					OrderList _order = OrderList.getInstance();
					String orderby = request.getParameter(OrderList.user_key);
					String desc = request.getParameter(OrderList.desc);

					if (!_order.isNull(orderby) && !_order.isNull(desc)) {
						String[] ss = { orderby, desc };
						request.getSession(true).setAttribute(
								OrderList.user_key, ss);
						userDAO.setOrderby(ss);
					} else {
						String[] ss = (String[]) request.getSession(true)
								.getAttribute(OrderList.user_key);

						if (ss != null) {
							userDAO.setOrderby(ss);
						}
					}

					//==========================================================================
					return userDAO.getUsers((int) start, (int) count);
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
				public long getRowCount(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
						throws Exception {
					long count = userDAO.getUserCount();

					return count;
				}
			}

			.newPageControl(mapping, form, request, response);

			request.setAttribute("pageControl", pageControl);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}
		LevelDAO levelDAO = new DefaultLevelDAO();
		Collection levelList = levelDAO.getLevel();
		Map map = new HashMap();
		Iterator i = levelList.iterator();
		while (i.hasNext()) {
			Level level = new DefaultLevel();
			level = (Level) i.next();
			map.put(new Long(level.getId()), level.getName());
		}
		request.getSession().setAttribute("map", map);

		return mapping.findForward("list");
	}

	public ActionForward listRoleUsers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String rid = request.getParameter("rid");
		// String userids = request.getParameter("userId");
		//  System.out.println(userids + "----------userids--------");
		// String[] userid = userids.split( ",");
		// String ids=request.getParameterValues("ids")
		// String[] id = request.getParameterValues("id");
		//System.out.println(id + "----------id--------");
		try {
			PageControl pageControl = new AbstractPageControlHelper() {
				PrivilegeFactory factory = PrivilegeFactory.getInstance();

				UserDAO userDAO = factory.getUserDAO();

				public Collection getPagerData(ActionMapping mapping,
						ActionForm form, HttpServletRequest request,
						HttpServletResponse response, long start, long count)
						throws Exception {
					String rid = request.getParameter("rid");

					return userDAO.getRoleUsers(rid, (int) start, (int) count);
				}

				public long getRowCount(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
						throws Exception {
					String rid = request.getParameter("rid");
					long count = userDAO.getRoleUserCount(rid);

					RoleDAO roleDAO = PrivilegeFactory.getInstance()
							.getRoleDAO();
					Role role = roleDAO.findRoleByID(Long.parseLong(rid));
					request.setAttribute("role", role);

					return count;
				}
			}

			.newPageControl(mapping, form, request, response);

			request.setAttribute("pageControl", pageControl);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}
		PrivilegeFactory factory = PrivilegeFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		userDAO.getRoleUsers(rid);
		Collection users_noSelect = userDAO.getSelectUsersInRoles(rid);
		request.setAttribute("users_noSelect", users_noSelect);
		//    System.out.println("--------"+ userDAO.getUsersByID(id));
		//userDAO.getUsersByID(userid);

		// Collection roles_selected = roleDAO.getOtherRolesByID(id);

		// request.setAttribute("users_noSelect", users_noSelect);

		return mapping.findForward("listRoleUsers");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException {
		try {
			String[] id = request.getParameterValues("id");

			PrivilegeFactory factory = PrivilegeFactory.getInstance();
			UserDAO userDAO = factory.getUserDAO();
			User user = userDAO.findUserByID(Integer.parseInt(id[0]));

			DynaActionForm daf = (DynaActionForm) form;
			daf.set("id", String.valueOf(user.getId()));
			daf.set("user", user.getUserName());
			daf.set("password", user.getPassword());
			daf.set("repassword", user.getPassword());
			daf.set("pin", user.getPassword());
			daf.set("usertype", user.getUserType());
			daf.set("level", user.getLevel());
			daf.set("status", user.getStatus());
			daf.set("validdate", user.getValidDate());
			daf.set("name", user.getName());
			daf.set("code", user.getCode());
			daf.set("gender", user.getGender());
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return mapping.findForward("view");
	}

	public ActionForward toCreate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException {
		DynaActionForm daf = (DynaActionForm) form;
		daf.set("id", "");
		daf.set("user", "");
		daf.set("password", "");
		daf.set("repassword", "");
		daf.set("pin", "");
		daf.set("name", "");
		daf.set("code", "");
		daf.set("sex", "");
		daf.set("usertype", "");
		daf.set("status", "");
		daf.set("validdate", "");
		daf.set("level", "");
		daf.set("gender", "");
		daf.set("keyword", "");
		saveToken(request);

		LevelDAO levelDAO = new DefaultLevelDAO();
		request.setAttribute("options", levelDAO.findLevelByName());

		return mapping.findForward("toCreate");
	}

	public ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException, Exception {
		try {
			//if (this.isTokenValid(request)) {
			User user = OrganizationFactory.getInstance().createUser();

			//user.setId(Integer.parseInt(request.getParameter("id")));
			user.setUserName(request.getParameter("user"));
			user.setPassword(request.getParameter("password"));
			user.setUserType(request.getParameter("userType"));
			user.setLevel(request.getParameter("level"));
			user.setStatus(request.getParameter("status"));
			user.setValidDate(request.getParameter("validdate"));
			user.setName(request.getParameter("name"));
			user.setCode(request.getParameter("code"));
			user.setGender(request.getParameter("gender"));

			com.gever.sysman.organization.dao.UserDAO userDAO = OrganizationFactory
					.getInstance().getUserDAO();
			UserDAO i_userDAO = PrivilegeFactory.getInstance().getUserDAO();

			if (i_userDAO.haveUserName(user.getUserName())) {
				throw new DefaultException("ORG_User_018",
						DefaultException.ERROR);
			}

			//У��licence

			userDAO.createUser(user);

			super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		// modify by eddy on 20041213----\\\
		// ��ֹ�½���ˢ�³��������ת��
		return new RedirectingActionForward("userAction.do?action=list");

		// return list(mapping, form, request, response);
		// modify by eddy on 20041213----///
	}

	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException {
		try {
			String[] id = request.getParameterValues("id");

			UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
			User user = userDAO.findUserByID(Integer.parseInt(id[0]));

			DynaActionForm daf = (DynaActionForm) form;
			daf.set("id", String.valueOf(user.getId()));
			daf.set("user", user.getUserName());
			daf.set("password", user.getPassword());
			daf.set("usertype", user.getUserType());
			daf.set("level", user.getLevel());
			daf.set("status", user.getStatus());
			daf.set("validdate", user.getValidDate());
			daf.set("name", user.getName());
			daf.set("code", user.getCode());
			daf.set("gender", user.getGender());

			String page = (String) daf.get("page");
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		LevelDAO levelDAO = new DefaultLevelDAO();
		request.setAttribute("options", levelDAO.findLevelByName());

		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			User user = PrivilegeFactory.getInstance().createUser();
			user.setId(Integer.parseInt(request.getParameter("id")));
			user.setUserName(request.getParameter("user"));
			user.setPassword(request.getParameter("password"));
			user.setUserType(request.getParameter("usertype"));
			user.setLevel(request.getParameter("level"));
			user.setStatus(request.getParameter("status"));
			user.setValidDate(request.getParameter("validdate"));
			user.setName(request.getParameter("name"));
			user.setCode(request.getParameter("code"));
			user.setGender(request.getParameter("gender"));

			UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
			User t_user = userDAO.findUserByUserName(user.getUserName());

			if ((t_user != null) && (t_user.getId() != user.getId())) {
				throw new DefaultException("ORG_User_019",
						DefaultException.ERROR);
			}

			// У��licence

			userDAO.updateUser(user);

			//super.addLog(request, MODULE_NAME, this.UPDATE[0],
			// this.UPDATE[1]);
			DynaActionForm dForm = (DynaActionForm) form;
			String page = (String) dForm.get("page");

			return list(mapping, form, request, response);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		} catch (DefaultException e) {
			throw e;
		} catch (Exception e) {
			throw new DefaultException("ORG_User_002", DefaultException.ERROR);
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String[] id = request.getParameterValues("id");

			UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();

			if (id != null) {
				userDAO.deleteUser(id);
			}

			super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return list(mapping, form, request, response);
	}

	public ActionForward toPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException {
		try {
			UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
			User user = userDAO.findUserByID(Integer.parseInt(request
					.getParameter("id")));

			DynaActionForm daf = (DynaActionForm) form;
			daf.set("id", String.valueOf(user.getId()));
			daf.set("user", user.getUserName());
			daf.set("password", user.getPassword());
			daf.set("usertype", user.getUserType());
			daf.set("level", user.getLevel());
			daf.set("status", user.getStatus());
			daf.set("validdate", user.getValidDate());
			daf.set("name", user.getName());
			daf.set("code", user.getCode());
			daf.set("gender", user.getGender());
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return mapping.findForward("password");
	}

	public ActionForward toPassword2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException {
		try {
			UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
			User user = userDAO.findUserByID(Integer.parseInt(request
					.getParameter("id")));

			DynaActionForm daf = (DynaActionForm) form;
			daf.set("id", String.valueOf(user.getId()));
			daf.set("user", user.getUserName());
			daf.set("password", user.getPassword());
			daf.set("usertype", user.getUserType());
			daf.set("level", user.getLevel());
			daf.set("status", user.getStatus());
			daf.set("validdate", user.getValidDate());
			daf.set("name", user.getName());
			daf.set("code", user.getCode());
			daf.set("gender", user.getGender());
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return mapping.findForward("password2");
	}

	public ActionForward setPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
			User user = userDAO.findUserByID(Integer.parseInt(request
					.getParameter("id")));

			user.setPassword(request.getParameter("pass"));

			userDAO.updateUserPassword(user);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return list(mapping, form, request, response);
	}

	public ActionForward setPassword2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
			User user = userDAO.findUserByID(Integer.parseInt(request
					.getParameter("id")));
			user.setPassword(request.getParameter("pass"));
			userDAO.updateUserPassword(user);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return mapping.findForward("success");
	}

	/**
	 * ѡ���û��Ľ�ɫ���г���ѡ�ĺ�δѡ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws java.lang.Exception
	 */
	public ActionForward selectRoles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			PrivilegeFactory factory = PrivilegeFactory.getInstance();
			RoleDAO roleDAO = factory.getRoleDAO();
			String id = request.getParameter("uid");
			Collection roles_selected = roleDAO.getOtherRolesByID(id);
			Collection roles_noSelect = roleDAO.getRolesByID(id);

			request.setAttribute("roles_selected", roles_selected);
			request.setAttribute("roles_noSelect", roles_noSelect);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return mapping.findForward("selectRoleByUser");
	}

	/**
	 * ����"����ָ���û���ӵ�еĽ�ɫ�趨"����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throws java.lang.Exception
	 * @roseuid 40BAC96100DA
	 */
	public ActionForward saveUserRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		long id = Long.parseLong(request.getParameter("uid"));
		String roleids = request.getParameter("rolesId");
		String[] rolesId = StringUtils.split(roleids, ",");

		// String[] rolesId = request.getParameterValues("rolesId");
		PrivilegeFactory factory = PrivilegeFactory.getInstance();
		RoleDAO roleDAO = factory.getRoleDAO();
		UserDAO userDAO = factory.getUserDAO();

		User user = null;

		try {
			user = (User) userDAO.findUserByID(id);
		} catch (DAOException ex) {
			throw new DAOException("PRV_ROLE_015", DAOException.ERROR, ex);
		}

		if (user == null) {
			throw new DefaultException("PRV_ROLE_015", DefaultException.ERROR);
		}

		try {
			roleDAO.deleteUserRolesByIds(String.valueOf(user.getId()));
			roleDAO.addRoles(user, roleDAO.getRolesByID(rolesId));

			// �������²˵�����ģ��
			OperationDAO optDAO = factory.getOperationDAO();
			ResourceDAO resDAO = factory.getResourceDAO();
			PermissionDAO pmDAO = factory.getPermissionDAO();

			Collection opt_collect = null;
			Collection res_collect = null;
			Collection user_res_opt_collect = null;
			Collection user_role_perm_collect = null;

			//�����û�id������е���Դ�������ϣ����û�Ȩ�ޣ�
			user_res_opt_collect = optDAO.getOptByUserid(user.getId());

			//�����û�id����û����н�ɫȨ�޼���
			user_role_perm_collect = optDAO.getUserRolePerm(Integer
					.toString(user.getId()));

			//���û������Ȩ�����û���ɫȨ�޺ϲ����õ����յ��û�Ȩ��
			user_res_opt_collect = pmDAO.getUserAndRolePerm(user.getId(),
					user_res_opt_collect, user_role_perm_collect);

			if (user_res_opt_collect != null) {
				// ��ȡ�û�ӵ�е���Դ
				user_res_opt_collect = pmDAO
						.getResCollect(user_res_opt_collect);

				// ��ȡ������Դ����������Դ��
				user_res_opt_collect = pmDAO
						.getAllResources(user_res_opt_collect);
			}

			//Collection opt_isOpt_collect =
			// pmDAO.returnUserOpts(opt_collect,user_res_opt_collect);
			//Collection ress = pmDAO.returnRess(res_collect,
			// opt_isOpt_collect);
			//ȡ���������ò˵�
			UserMenuDao dao = UserMenuDAOFactory.getInstance().getUserMenuDao();
			dao.resetUserMenus(Integer.toString(user.getId()),
					user_res_opt_collect);
		} catch (DAOException ex) {
			throw new DAOException("PRV_ROLE_015", DAOException.ERROR, ex);
		}

		return listUserRoles(mapping, form, request, response);
	}

	/**
	 * �г���ǰ�û������н�ɫ
	 * 
	 * @param mapping
	 * @param forms
	 * @param request
	 * @param response
	 * @return org.apache.struts.action.ActionForward
	 * @throws java.lang.Exception
	 * @roseuid 40BAC96101D4
	 */
	public ActionForward listUserRoles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			PageControl pageControl = new AbstractPageControlHelper() {
				PrivilegeFactory factory = PrivilegeFactory.getInstance();

				RoleDAO roleDAO = factory.getRoleDAO();

				public Collection getPagerData(ActionMapping mapping,
						ActionForm form, HttpServletRequest request,
						HttpServletResponse response, long start, long count)
						throws Exception {
					String id = request.getParameter("uid");

					if (id == null) {
						throw new DefaultException("PRV_ROLE_016",
								DefaultException.ERROR);
					}

					return roleDAO.getRolesByID(id, start, count);
				}

				public long getRowCount(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
						throws Exception {
					String id = request.getParameter("uid");
					long count = roleDAO.getUserRoleCount(id);

					return count;
				}
			}

			.newPageControl(mapping, form, request, response);
			UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
			long id = Long.parseLong(request.getParameter("uid"));
			User user = userDAO.findUserByID(id);

			request.setAttribute("user", user);

			request.setAttribute("pageControl", pageControl);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return mapping.findForward("listUserRoles");
	}

	public ActionForward toQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws DefaultException {
		//DynaActionForm daf = (DynaActionForm) form;
		//daf.set("id", "");
		//saveToken(request);
		return mapping.findForward("toQuery");
	}

	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String strSQL = (String) request
					.getAttribute(UniQueryConfig.DEFAULT_SQL_NAME);

			if (strSQL == null) {
				strSQL = (String) request.getSession().getAttribute(
						COMMON_QUERY_SQL);
			} else {
				request.getSession().setAttribute(COMMON_QUERY_SQL, strSQL);
			}

			PageControl pageControl = new AbstractPageControlHelper() {
				PrivilegeFactory factory = PrivilegeFactory.getInstance();

				UserDAO userDAO = factory.getUserDAO();

				public Collection getPagerData(ActionMapping mapping,
						ActionForm form, HttpServletRequest request,
						HttpServletResponse response, long start, long count)
						throws Exception {
					String strSQL = (String) request
							.getAttribute(UniQueryConfig.DEFAULT_SQL_NAME);

					if (strSQL == null) {
						strSQL = (String) request.getSession().getAttribute(
								COMMON_QUERY_SQL);
					}

					return userDAO.getUsers(strSQL, (int) start, (int) count);
				}

				public long getRowCount(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
						throws Exception {
					String strSQL = (String) request
							.getAttribute(UniQueryConfig.DEFAULT_SQL_NAME);

					if (strSQL == null) {
						strSQL = (String) request.getSession().getAttribute(
								COMMON_QUERY_SQL);
					}

					long count = userDAO.getUserCountBySQL(strSQL);

					return count;
				}
			}

			.newPageControl(mapping, form, request, response);

			request.setAttribute("pageControl", pageControl);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return mapping.findForward("list");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String keyword = request.getParameter("keyword");
			String strSQL = constructSQLForSearch(keyword.trim());
			request.getSession().setAttribute(COMMON_QUERY_SQL, strSQL);

			PageControl pageControl = new AbstractPageControlHelper() {
				PrivilegeFactory factory = PrivilegeFactory.getInstance();

				UserDAO userDAO = factory.getUserDAO();

				public Collection getPagerData(ActionMapping mapping,
						ActionForm form, HttpServletRequest request,
						HttpServletResponse response, long start, long count)
						throws Exception {
					String strSQL = (String) request
							.getAttribute(UniQueryConfig.DEFAULT_SQL_NAME);

					if (strSQL == null) {
						strSQL = (String) request.getSession().getAttribute(
								COMMON_QUERY_SQL);
					}

					return userDAO.getUsersForSearch(strSQL, (int) start,
							(int) count);
				}

				public long getRowCount(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response)
						throws Exception {
					String strSQL = (String) request
							.getAttribute(UniQueryConfig.DEFAULT_SQL_NAME);

					if (strSQL == null) {
						strSQL = (String) request.getSession().getAttribute(
								COMMON_QUERY_SQL);
					}

					long count = userDAO.getUserCountBySQL(strSQL);

					return count;
				}
			}

			.newPageControl(mapping, form, request, response);

			request.setAttribute("pageControl", pageControl);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		return mapping.findForward("list");
	}

	private String constructSQLForSearch(String keyword) {
		Dialect dialect = Global.getDialect();
		String strSQL = null;

		if (dialect instanceof OracleDialect) {
			strSQL = "SELECT ID,USERNAME,PASSWORD,USERTYPE,LEV,STATUS,VALIDDATE,NAME,GENDER,CODE FROM T_USER";
		} else {
			strSQL = "SELECT ID,USERNAME,PASSWORD,USERTYPE,LEVEL,STATUS,VALIDDATE,NAME,GENDER,CODE FROM T_USER";
		}

		if ((keyword != null) && !"".equals(keyword.trim())) {
			strSQL = strSQL + " where CODE like '%" + keyword
					+ "%' or USERNAME like '%" + keyword + "%' or NAME like '%"
					+ keyword + "%'";
		}

		strSQL = strSQL + " order by USERNAME";

		return strSQL;
	}

	/*
	 * ��չ���������û��б�ѡ��һ����¼�������¼��ӵ���ɫ�û��ı��� �޸�ʱ�䣺2004��11��17�� �޸����ߣ�����
	 * ��չ�������ܣ�����ӵ��û�Ӧ�ñ����������ɫ��Ȩ�ޣ����Ҹ��û���Ӧ�����������Ȩ�� ��������ɫ�����Ȩ�ޣ�����Ȩ�����⼸��Ȩ�޵Ĳ�����
	 * �޸�ʱ�䣺2004��11��24�� �޸��� ���������Э����
	 */
	public ActionForward saveRoleUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionErrors errors = new ActionErrors();
		String rolesId = request.getParameter("rolesId");

		String[] id = request.getParameterValues("id");

		UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
		PrivilegeFactory factory = PrivilegeFactory.getInstance();
		RoleDAO roleDAO = factory.getRoleDAO();

		// UserDAO userDAO = factory.getUserDAO();
		//Role role=null;
		User user = null;

		if (id != null) {
			userDAO.addUserToRole(id, rolesId);

			// �������²˵�����ģ��
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
				user_res_opt_collect = optDAO.getOptByUserid(Long
						.parseLong(id[i]));

				//�����û�id����û����н�ɫȨ�޼���
				user_role1_perm_collect = optDAO.getUserRolePerm(id[i]);

				//���ݽ�ɫid������е��û������ҽ�Ȩ�޶���������û�
				//���û������Ȩ�����û���ɫȨ�޺ϲ����õ����յ��û�Ȩ��
				user_res_opt_collect = pmDAO.getUserAndRolePerm(Long
						.parseLong(id[i]), user_res_opt_collect,
						user_role1_perm_collect);

				if (user_res_opt_collect != null) {
					// ��ȡ�û�ӵ�е���Դ
					user_res_opt_collect = pmDAO
							.getResCollect(user_res_opt_collect);

					// ��ȡ������Դ����������Դ��
					user_res_opt_collect = pmDAO
							.getAllResources(user_res_opt_collect);
				}

				//Collection opt_isOpt_collect =
				// pmDAO.returnUserOpts(opt_collect,user_res_opt_collect);
				//Collection ress = pmDAO.returnRess(res_collect,
				// opt_isOpt_collect);
				//ȡ���������ò˵�
				UserMenuDao dao = UserMenuDAOFactory.getInstance()
						.getUserMenuDao();
				dao.resetUserMenus(id[i], user_res_opt_collect);
			}
		}

		super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);

		return listRoleUsers(mapping, form, request, response);
	}

	/*
	 * ��չ���������û��б�ѡ��һ����¼�������¼��ӵ���ɫ�û��ı��� �޸�ʱ�䣺2004��11��17�� �޸����ߣ�����
	 * ��չ�������ܣ�����ӵ��û�Ӧ�ñ����������ɫ��Ȩ�ޣ����Ҹ��û���Ӧ�����������Ȩ�� ��������ɫ�����Ȩ�ޣ�����Ȩ�����⼸��Ȩ�޵Ĳ�����
	 * �޸�ʱ�䣺2004��11��24�� �޸��� ���������Э����
	 */
	/*
	 * public ActionForward saveRoleUser(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { ActionErrors errors = new ActionErrors(); String
	 * rolesId=request.getParameter("rolesId");
	 * 
	 * String[] id = request.getParameterValues("id");
	 * System.out.println("---------"+id[0]+"=====================");
	 * System.out.println("---roleid------"+rolesId);
	 * 
	 * 
	 * 
	 * UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
	 * PrivilegeFactory factory = PrivilegeFactory.getInstance(); RoleDAO
	 * roleDAO = factory.getRoleDAO(); // UserDAO userDAO =
	 * factory.getUserDAO(); //Role role=null; User user = null;
	 * 
	 * 
	 * if (id != null) { userDAO.addUserToRole(id,rolesId);
	 * 
	 *  // �������²˵�����ģ�� OperationDAO optDAO = factory.getOperationDAO();
	 * ResourceDAO resDAO = factory.getResourceDAO(); PermissionDAO pmDAO =
	 * factory.getPermissionDAO();
	 * 
	 * Collection opt_collect = null; Collection res_collect = null; Collection
	 * user_res_opt_collect = null; Collection user_role_perm_collect = null;
	 * Collection user_role1_perm_collect=null;
	 * //user_role_perm_collect=optDAO.getOptByRoleid(Long.parseLong(rolesId));
	 * //�����û�id������е���Դ�������ϣ����û�Ȩ�ޣ� for(int i=0;i <id.length;i++){
	 * System.out.println(id[i]+"========before for=============");
	 * user_res_opt_collect = optDAO.getOptByUserid(Long.parseLong(id[i]));
	 * System.out.println(id[i]+"========== after for===========");
	 * //�����û�id����û����н�ɫȨ�޼��� user_role1_perm_collect =
	 * optDAO.getUserRolePerm(id[i]); //���ݽ�ɫid������е��û������ҽ�Ȩ�޶���������û�
	 * 
	 * //���û������Ȩ�����û���ɫȨ�޺ϲ����õ����յ��û�Ȩ�� user_res_opt_collect =
	 * pmDAO.getUserAndRolePerm(Long.parseLong(id[i]), user_res_opt_collect,
	 * user_role1_perm_collect); System.out.println(id[i]+"========== after
	 * for==========="); if (user_res_opt_collect != null) { // ��ȡ�û�ӵ�е���Դ
	 * user_res_opt_collect = pmDAO.getResCollect(user_res_opt_collect); //
	 * ��ȡ������Դ����������Դ�� user_res_opt_collect = pmDAO.getAllResources(
	 * user_res_opt_collect); }
	 * 
	 * //Collection opt_isOpt_collect =
	 * pmDAO.returnUserOpts(opt_collect,user_res_opt_collect); //Collection ress =
	 * pmDAO.returnRess(res_collect, opt_isOpt_collect); UserMenuDao dao =
	 * UserMenuDAOFactory.getInstance().getUserMenuDao();
	 * System.out.println(id[i]+"========== 3===========");
	 * dao.resetUserMenus(id[i], user_res_opt_collect);
	 * System.out.println(id[i]+"========== 4==========="); } }
	 * System.out.println("-------12345-----"); super.addLog(request,
	 * MODULE_NAME, this.DELETE[0], this.DELETE[1]); return
	 * listRoleUsers(mapping, form, request, response);
	 *  }
	 */
	/*
	 * ��չ���������û��б�ѡ��һ����¼�������¼��ӵ���ɫ�û��ı��к����ṩ�������ܡ� �޸�ʱ�䣺2004��11��17�� �޸����ߣ�����
	 */
	public ActionForward userInRoleSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String keyword = request.getParameter("keyword");
		String strSQL = constructSQLForSearch(keyword);
		request.getSession().setAttribute(COMMON_QUERY_SQL, strSQL);

		PageControl pageControl = new AbstractPageControlHelper() {
			PrivilegeFactory factory = PrivilegeFactory.getInstance();

			UserDAO userDAO = factory.getUserDAO();

			public Collection getPagerData(ActionMapping mapping,
					ActionForm form, HttpServletRequest request,
					HttpServletResponse response, long start, long count)
					throws Exception {
				String strSQL = (String) request
						.getAttribute(UniQueryConfig.DEFAULT_SQL_NAME);

				if (strSQL == null) {
					strSQL = (String) request.getSession().getAttribute(
							COMMON_QUERY_SQL);
				}

				return userDAO.getUsersForSearch(strSQL, (int) start,
						(int) count);
			}

			public long getRowCount(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				String strSQL = (String) request
						.getAttribute(UniQueryConfig.DEFAULT_SQL_NAME);

				if (strSQL == null) {
					strSQL = (String) request.getSession().getAttribute(
							COMMON_QUERY_SQL);
				}

				long count = userDAO.getUserCountBySQL(strSQL);

				return count;
			}
		}

		.newPageControl(mapping, form, request, response);

		request.setAttribute("pageControl", pageControl);

		return mapping.findForward("toSelectUser");
	}

	/*
	 * ��չ�������ύͨ���������������˽�ɫ�����û��б���õ��µ��б� �޸�ʱ�䣺2004��12��1�� �޸����ߣ�����
	 */
	public ActionForward listByLevel(ActionMapping mapping, ActionForm form,
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
				String level = request.getParameter("level");

				//String username=request.getParameter("username");
				//             start = setStart(form, start, count);
				//���ж�
				return userDAO.findUserInRolesByLevel((int) start, (int) count,
						rid, level);
			}

			//         private long setStart(ActionForm form, long start, long count)
			// throws
			//             NumberFormatException {
			//           DynaActionForm dForm = (DynaActionForm)form;
			//           String page = (String)dForm.get("page");
			//           page = page.equals("") ? "0" : page;
			//           long lPage = Long.parseLong(page);
			//           lPage = lPage > 0 ? lPage-1 : 0;
			//           start = lPage * count;
			//           return start;
			//         }
			public long getRowCount(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				String rid = request.getParameter("rid");
				String level = request.getParameter("level");
				long count = userDAO.getRoleToUserCountByLevel(rid, level);

				return count;
			}
		}

		.newPageControl(mapping, form, request, response);
		request.setAttribute("options", levelDAO.findLevelByName());
		request.setAttribute("pageControl", pageControl);
		request.setAttribute("rolesId", rid);

		return mapping.findForward("userlist");
	}

	/*
	 * 
	 * ���ڽ�����^^^^^^^^1^^^^^^^^^^^^^
	 *  
	 */
	public ActionForward listByLevelInRole(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dForm = (DynaActionForm) form;

		LevelDAO levelDAO = new DefaultLevelDAO();
		PrivilegeFactory factory = PrivilegeFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		String rid = request.getParameter("rid");
		String level = request.getParameter("level");

		//String username=request.getParameter("username");
		//���ж�
		Collection select = userDAO.findUserInRolesByLevel(rid, level);
		Collection selectRight = userDAO.findSelectUserInRolesByLevel(rid,
				level);

		request.setAttribute("options", levelDAO.findLevelByName());

		request.setAttribute("users_noSelected", selectRight);

		request.setAttribute("users_selected", select);

		// request.setAttribute("options",levelDAO.findLevelByName());
		// request.setAttribute("rolesId",rid);
		return mapping.findForward("selectUser");
	}

	/*
	 * *���ڽ�����^^^^^^^^^^^^^^^^2^^^^^^^^^^^^^^^^
	 */
	public ActionForward saveRoleToUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionErrors errors = new ActionErrors();

		long id = Long.parseLong(request.getParameter("rid"));
		String userids = request.getParameter("userId");
		String[] userid = StringUtils.split(userids, ",");

		PrivilegeFactory factory = PrivilegeFactory.getInstance();
		RoleDAO roleDAO = factory.getRoleDAO();
		UserDAO userDAO = factory.getUserDAO();

		Role role = null;

		try {
			role = (Role) roleDAO.findRoleByID(id);
		} catch (DAOException e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"privilege.user.not.found"));
			saveErrors(request, errors);

			return (mapping.findForward("failed"));
		}

		if (role == null) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"privilege.user.not.found"));
			saveErrors(request, errors);

			return (mapping.findForward("failed"));
		}

		try {
			userDAO.deleteRoleUserByIds(String.valueOf(role.getId()));

			if (userid.length != 0) {
				roleDAO.addUsers(role, userDAO.getUsersByID(userid));
			}

			/*
			 * �����û�Ȩ�ޣ���ӵ��û��;��������ɫ��Ȩ�ޣ���ôɾ������û��ĺ� ����û������������ɫ��Ȩ��Ҳ����Ӧ�ı�ȥ��
			 * ���ǿ��ǵ�������û���ɾ���û��ͼ���Ȩ�޵�ʱ��ͻ�Ӱ�쵯���ͻ��˴��ڵ�
			 * ���ܣ��ʴ�������Ͳ�����Ȩ�޵����⣬���Եȵ��û��ڵ�¼��ʱ���ټ���Ȩ��
			 * �ˡ��������ｫ����Ȩ�޵Ĵ���ע�͵����ش�������ע�ͣ�����������
			 * 
			 * OperationDAO optDAO = factory.getOperationDAO(); ResourceDAO
			 * resDAO = factory.getResourceDAO(); PermissionDAO pmDAO =
			 * factory.getPermissionDAO();
			 * 
			 * Collection opt_collect = null; Collection res_collect = null;
			 * Collection user_res_opt_collect = null; Collection
			 * user_role_perm_collect = null; Collection
			 * user_role1_perm_collect=null;
			 * //user_role_perm_collect=optDAO.getOptByRoleid(Long.parseLong(rolesId));
			 * //�����û�id������е���Դ�������ϣ����û�Ȩ�ޣ� for(int i=0;i <userid.length;i++){
			 * System.out.println(userid[i]+"========before for=============");
			 * user_res_opt_collect =
			 * optDAO.getOptByUserid(Long.parseLong(userid[i]));
			 * System.out.println(userid[i]+"========== after for===========");
			 * //�����û�id����û����н�ɫȨ�޼��� user_role1_perm_collect =
			 * optDAO.getUserRolePerm(userid[i]); //���ݽ�ɫid������е��û������ҽ�Ȩ�޶���������û�
			 * 
			 * //���û������Ȩ�����û���ɫȨ�޺ϲ����õ����յ��û�Ȩ�� user_res_opt_collect =
			 * pmDAO.getUserAndRolePerm(Long.parseLong(userid[i]),
			 * user_res_opt_collect, user_role1_perm_collect);
			 * System.out.println(userid[i]+"========== after for===========");
			 * if (user_res_opt_collect != null) { // ��ȡ�û�ӵ�е���Դ
			 * user_res_opt_collect = pmDAO.getResCollect(user_res_opt_collect); //
			 * ��ȡ������Դ����������Դ�� user_res_opt_collect = pmDAO.getAllResources(
			 * user_res_opt_collect); }
			 * 
			 * //Collection opt_isOpt_collect =
			 * pmDAO.returnUserOpts(opt_collect,user_res_opt_collect);
			 * //Collection ress = pmDAO.returnRess(res_collect,
			 * opt_isOpt_collect); UserMenuDao dao =
			 * UserMenuDAOFactory.getInstance().getUserMenuDao();
			 * System.out.println(userid[i]+"========== 3===========");
			 * dao.resetUserMenus(userid[i], user_res_opt_collect);
			 * System.out.println(userid[i]+"========== 4==========="); }
			 *  
			 */
		} catch (DAOException e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"privilege.user.update.failed"));
			saveErrors(request, errors);

			return (mapping.findForward("failed"));
		}

		return listRoleUsers(mapping, form, request, response);
	}
}