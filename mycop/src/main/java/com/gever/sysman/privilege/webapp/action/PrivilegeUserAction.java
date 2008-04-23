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
 * 用户相关Action
 */
public class PrivilegeUserAction extends BaseAction {
	public final static String COMMON_QUERY_SQL = "com.gever.sysman.privilege.USER_QUERY_SQL";

	String MODULE_NAME = "用户管理";

	String[] CREATE = { "create", "创建用户" };

	String[] DELETE = { "delete", "删除用户" };

	String[] UPDATE = { "update", "更新用户" };

	/**
	 * 处理"将指定角色从用户的角色集合中删除"请求
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
	 * 处理"将指定角色添加到用户所拥有的角色集合中"请求
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

			// 判断是否从通用查询进入
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
					//胡勇添加，增加JSP视图列表排序功能
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

			//校验licence

			userDAO.createUser(user);

			super.addLog(request, MODULE_NAME, this.CREATE[0], this.CREATE[1]);
		} catch (DAOException ex) {
			throw new DAOException("DB_DAO_001", DefaultException.ERROR, ex);
		}

		// modify by eddy on 20041213----\\\
		// 防止新建后刷新出错而做的转向
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

			// 校验licence

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
	 * 选择用户的角色，列出已选的和未选的
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
	 * 处理"保存指定用户所拥有的角色设定"请求
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

			// 级联更新菜单定制模块
			OperationDAO optDAO = factory.getOperationDAO();
			ResourceDAO resDAO = factory.getResourceDAO();
			PermissionDAO pmDAO = factory.getPermissionDAO();

			Collection opt_collect = null;
			Collection res_collect = null;
			Collection user_res_opt_collect = null;
			Collection user_role_perm_collect = null;

			//根据用户id获得所有的资源操作集合（即用户权限）
			user_res_opt_collect = optDAO.getOptByUserid(user.getId());

			//根据用户id获得用户所有角色权限集合
			user_role_perm_collect = optDAO.getUserRolePerm(Integer
					.toString(user.getId()));

			//将用户本身的权限与用户角色权限合并，得到最终的用户权限
			user_res_opt_collect = pmDAO.getUserAndRolePerm(user.getId(),
					user_res_opt_collect, user_role_perm_collect);

			if (user_res_opt_collect != null) {
				// 获取用户拥有的资源
				user_res_opt_collect = pmDAO
						.getResCollect(user_res_opt_collect);

				// 获取所有资源（包括父资源）
				user_res_opt_collect = pmDAO
						.getAllResources(user_res_opt_collect);
			}

			//Collection opt_isOpt_collect =
			// pmDAO.returnUserOpts(opt_collect,user_res_opt_collect);
			//Collection ress = pmDAO.returnRess(res_collect,
			// opt_isOpt_collect);
			//取消重新设置菜单
			UserMenuDao dao = UserMenuDAOFactory.getInstance().getUserMenuDao();
			dao.resetUserMenus(Integer.toString(user.getId()),
					user_res_opt_collect);
		} catch (DAOException ex) {
			throw new DAOException("PRV_ROLE_015", DAOException.ERROR, ex);
		}

		return listUserRoles(mapping, form, request, response);
	}

	/**
	 * 列出当前用户的所有角色
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
	 * 扩展方法：在用户列表选中一条记录或多条记录添加到角色用户的表中 修改时间：2004年11月17日 修改作者：方晓
	 * 扩展方法功能：所添加的用户应该被赋给这个角色的权限，并且该用户还应该有它自身的权限 和其他角色给与的权限，它的权限是这几个权限的并集。
	 * 修改时间：2004年11月24日 修改人 ：方晓（杨帆协助）
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

			// 级联更新菜单定制模块
			OperationDAO optDAO = factory.getOperationDAO();
			ResourceDAO resDAO = factory.getResourceDAO();
			PermissionDAO pmDAO = factory.getPermissionDAO();

			Collection opt_collect = null;
			Collection res_collect = null;
			Collection user_res_opt_collect = null;
			Collection user_role_perm_collect = null;
			Collection user_role1_perm_collect = null;

			//user_role_perm_collect=optDAO.getOptByRoleid(Long.parseLong(rolesId));
			//根据用户id获得所有的资源操作集合（即用户权限）
			for (int i = 0; i < id.length; i++) {
				user_res_opt_collect = optDAO.getOptByUserid(Long
						.parseLong(id[i]));

				//根据用户id获得用户所有角色权限集合
				user_role1_perm_collect = optDAO.getUserRolePerm(id[i]);

				//根据角色id查出所有的用户，并且将权限都给所查的用户
				//将用户本身的权限与用户角色权限合并，得到最终的用户权限
				user_res_opt_collect = pmDAO.getUserAndRolePerm(Long
						.parseLong(id[i]), user_res_opt_collect,
						user_role1_perm_collect);

				if (user_res_opt_collect != null) {
					// 获取用户拥有的资源
					user_res_opt_collect = pmDAO
							.getResCollect(user_res_opt_collect);

					// 获取所有资源（包括父资源）
					user_res_opt_collect = pmDAO
							.getAllResources(user_res_opt_collect);
				}

				//Collection opt_isOpt_collect =
				// pmDAO.returnUserOpts(opt_collect,user_res_opt_collect);
				//Collection ress = pmDAO.returnRess(res_collect,
				// opt_isOpt_collect);
				//取消重新设置菜单
				UserMenuDao dao = UserMenuDAOFactory.getInstance()
						.getUserMenuDao();
				dao.resetUserMenus(id[i], user_res_opt_collect);
			}
		}

		super.addLog(request, MODULE_NAME, this.DELETE[0], this.DELETE[1]);

		return listRoleUsers(mapping, form, request, response);
	}

	/*
	 * 扩展方法：在用户列表选中一条记录或多条记录添加到角色用户的表中 修改时间：2004年11月17日 修改作者：方晓
	 * 扩展方法功能：所添加的用户应该被赋给这个角色的权限，并且该用户还应该有它自身的权限 和其他角色给与的权限，它的权限是这几个权限的并集。
	 * 修改时间：2004年11月24日 修改人 ：方晓（杨帆协助）
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
	 *  // 级联更新菜单定制模块 OperationDAO optDAO = factory.getOperationDAO();
	 * ResourceDAO resDAO = factory.getResourceDAO(); PermissionDAO pmDAO =
	 * factory.getPermissionDAO();
	 * 
	 * Collection opt_collect = null; Collection res_collect = null; Collection
	 * user_res_opt_collect = null; Collection user_role_perm_collect = null;
	 * Collection user_role1_perm_collect=null;
	 * //user_role_perm_collect=optDAO.getOptByRoleid(Long.parseLong(rolesId));
	 * //根据用户id获得所有的资源操作集合（即用户权限） for(int i=0;i <id.length;i++){
	 * System.out.println(id[i]+"========before for=============");
	 * user_res_opt_collect = optDAO.getOptByUserid(Long.parseLong(id[i]));
	 * System.out.println(id[i]+"========== after for===========");
	 * //根据用户id获得用户所有角色权限集合 user_role1_perm_collect =
	 * optDAO.getUserRolePerm(id[i]); //根据角色id查出所有的用户，并且将权限都给所查的用户
	 * 
	 * //将用户本身的权限与用户角色权限合并，得到最终的用户权限 user_res_opt_collect =
	 * pmDAO.getUserAndRolePerm(Long.parseLong(id[i]), user_res_opt_collect,
	 * user_role1_perm_collect); System.out.println(id[i]+"========== after
	 * for==========="); if (user_res_opt_collect != null) { // 获取用户拥有的资源
	 * user_res_opt_collect = pmDAO.getResCollect(user_res_opt_collect); //
	 * 获取所有资源（包括父资源） user_res_opt_collect = pmDAO.getAllResources(
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
	 * 扩展方法：在用户列表选中一条记录或多条记录添加到角色用户的表中后，再提供搜索功能。 修改时间：2004年11月17日 修改作者：方晓
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
	 * 扩展方法：提交通过行政级别来过滤角色分配用户列表而得到新的列表。 修改时间：2004年12月1日 修改作者：方晓
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
				//做判断
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
	 * 正在建设中^^^^^^^^1^^^^^^^^^^^^^
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
		//做判断
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
	 * *正在建设中^^^^^^^^^^^^^^^^2^^^^^^^^^^^^^^^^
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
			 * 激活用户权限，添加的用户就具有这个角色的权限，那么删除这个用户的后， 这个用户的所在这个角色的权限也会相应的被去掉
			 * 但是考虑到在添加用户和删除用户就激活权限的时候就会影响弹出客户端窗口的
			 * 性能，故此在这里就不处理权限的问题，可以等到用户在登录的时候再激活权限
			 * 了。故在这里将激活权限的代码注释掉。特此在这里注释！！！！！！
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
			 * //根据用户id获得所有的资源操作集合（即用户权限） for(int i=0;i <userid.length;i++){
			 * System.out.println(userid[i]+"========before for=============");
			 * user_res_opt_collect =
			 * optDAO.getOptByUserid(Long.parseLong(userid[i]));
			 * System.out.println(userid[i]+"========== after for===========");
			 * //根据用户id获得用户所有角色权限集合 user_role1_perm_collect =
			 * optDAO.getUserRolePerm(userid[i]); //根据角色id查出所有的用户，并且将权限都给所查的用户
			 * 
			 * //将用户本身的权限与用户角色权限合并，得到最终的用户权限 user_res_opt_collect =
			 * pmDAO.getUserAndRolePerm(Long.parseLong(userid[i]),
			 * user_res_opt_collect, user_role1_perm_collect);
			 * System.out.println(userid[i]+"========== after for===========");
			 * if (user_res_opt_collect != null) { // 获取用户拥有的资源
			 * user_res_opt_collect = pmDAO.getResCollect(user_res_opt_collect); //
			 * 获取所有资源（包括父资源） user_res_opt_collect = pmDAO.getAllResources(
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