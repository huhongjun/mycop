package com.gever.sysman.privilege.dao.impl;


import com.gever.config.Constants;

import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.ConnectionProvider;
import com.gever.jdbc.connection.ConnectionProviderFactory;
import com.gever.jdbc.connection.ConnectionUtil;
import com.gever.jdbc.database.dialect.Global;

import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.SQLFactory;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.sysman.privilege.model.I_User;
import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;
import com.gever.sysman.privilege.model.Role;
import com.gever.sysman.privilege.model.UserPermission;
import com.gever.sysman.util.OrderList;
import com.gever.util.crypto.Digest;
import com.gever.util.crypto.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @version 1.0
 */
public class DefaultUserDAO extends
		com.gever.sysman.organization.dao.impl.DefaultUserDAO implements
		UserDAO {
	private static String GET_RoleUserByID = "SELECT ID,USERNAME,PASSWORD,USERTYPE,LEVEL,STATUS,VALIDDATE,NAME,GENDER,CODE FROM T_USER WHERE 1=2 ";

	private static String DEL_ALL_User_ROLES = "DELETE FROM T_USER_ROLE WHERE ROLE_ID=?  ";

	//    private static String CHECKLOGIN = "SELECT COUNT(ID) FROM T_USER WHERE
	// USERNAME=? AND PASSWORD=?";
	//    private static String ADD_User_Permission = "INSERT INTO
	// T_USER_PERMISSION (USER_ID,RESOURCE_ID,RESOPERATION_ID) VALUES(?,?,?)";
	//    private static String GET_User_Permission = "SELECT
	// USER_ID,RESOURCE_ID,RESOPERATION_ID FROM T_USER_PERMISSION WHERE
	// USER_ID=?";
	//    private static String DEL_User_Permission = "DELETE FROM
	// T_USER_PERMISSION WHERE USER_ID=? AND RESOURCE_ID=? AND
	// RESOPERATION_ID=?";
	//    private static String DEL_UserRoel = "DELETE FROM T_USER_ROLE WHERE
	// USER_ID=? AND ROLE_ID=?";
	//    private static String GET_USERS = "SELECT
	// ID,USERNAME,PASSWORD,USERTYPE,LEVEL,STATUS,VALIDDATE,NAME,GENDER,CODE
	// FROM T_USER order by USERNAME";
	//    private static String GET_Role_USERS =
	//        "SELECT ID,USERNAME,PASSWORD,USERTYPE,LEVEL,STATUS,VALIDDATE,NAME,GENDER
	// FROM T_USER WHERE ID IN " +
	//        "(SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)";
	//    private static String GET_RoleUser_Count = "SELECT COUNT(USER_ID) FROM
	// T_USER_ROLE WHERE ROLE_ID=?";
	//    private static String GET_USER_ByNAME="SELECT * FROM T_USER WHERE
	// USERNAME=?";
	//   private String select_man = "SELECT NAME FROM T_USER WHERE LEVEL=?";
	//    private String select_all_man = "SELECT * FROM T_USER";
	private static SQLFactory sqlFactory = PrivilegeFactory.getInstance()
			.getSQLFactory();

	public DefaultUserDAO() {
	}

	public Collection getResources(User user) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserPermission up = null;
		Collection result = new ArrayList();

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn
					.prepareStatement(sqlFactory.get("GET_User_Permission"));
			pstmt.setString(1, String.valueOf(user.getId()));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				up = PrivilegeFactory.getInstance().createUserPermission();
				up.setUser_id(rs.getLong("USER_ID"));
				up.setResource_id(rs.getLong("RESOURCE_ID"));
				up.setResop_id(rs.getLong("RESOPERATION_ID"));
				result.add(up);
			}

			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_USER_001", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public void removeResource(User iuser, Resource resouve)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Iterator iopr = resouve.getOperations().iterator();

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn
					.prepareStatement(sqlFactory.get("DEL_User_Permission"));
			pstmt.setString(1, String.valueOf(iuser.getId()));
			pstmt.setString(2, String.valueOf(resouve.getId()));

			while (iopr.hasNext()) {
				Operation opt = (Operation) iopr.next();
				pstmt.setString(3, String.valueOf(opt.getId()));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_002", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public void addResources(User iuser, Collection res) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Iterator ires = res.iterator();
		Iterator iopr = null;
		Resource resource = null;

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn
					.prepareStatement(sqlFactory.get("ADD_User_Permission"));

			while (ires.hasNext()) {
				resource = (Resource) ires.next();
				iopr = resource.getOperations().iterator();
				pstmt.setString(1, String.valueOf(iuser.getId()));

				while (iopr.hasNext()) {
					Operation option = (Operation) iopr.next();
					pstmt.setString(2, String.valueOf(option.getId()));
					pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_003", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	//if sucess then reutrn username else return ""
	public String checkLoginForCA(String caDisa) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String userName = "";
		String sql = "SELECT username FROM T_USER WHERE usertype='2' AND PASSWORD='"
				+ caDisa + "'";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				userName = rs.getString("username");
			}

			return userName;
		} catch (Exception e) {
			throw new DAOException("PRV_USER_004", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				throw new DAOException("PRV_USER_004", e);
			}
		}
	}

	public boolean checkLogin(String name, String password) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			byte[] pass_bytes = Digest.getMessageDigest(password, Digest.MD5);
			pstmt = conn.prepareStatement(sqlFactory.get("CHECKLOGIN"));
			pstmt.setString(1, name);
			pstmt.setString(2, Util.byte2hex(pass_bytes));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					result = true;
				} else {
					result = false;
				}
			}

			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_USER_004", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/*
	 * @see com.gever.sysman.privilege.dao.UserDAO#removeRole(com.gever.sysman.privilege.model.I_User,
	 *      com.gever.sysman.privilege.model.Role)
	 */
	public void removeRole(User user, Role role) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sqlFactory.get("DEL_UserRoel"));
			pstmt.setString(1, String.valueOf(user.getId()));
			pstmt.setString(2, String.valueOf(role.getId()));
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("PRV_USER_005", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	//==========================================================
	//胡勇添加，增加JSP视图列表排序功能
	private String[] orderby;

	public void setOrderby(String[] s) {
		this.orderby = s;
	}

	//==========================================================
	public Collection getUsers(int start, int count) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;
		String sql = Global.getDialect().getLimitString(
				sqlFactory.get("GET_USERS"));

		//==========================================================
		//胡勇添加，增加JSP视图列表排序功能
		sql = OrderList.getInstance().formatSQL(orderby, sql);
		//==========================================================
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start,
					2, start + count);

			//            pstmt.setLong(1, start);
			//            pstmt.setLong(2, start + count);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

	public Collection getUsersForSearch(String strSQL, int start, int count)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;
		String sql = Global.getDialect().getLimitString(strSQL);

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start,
					2, start + count);

			//            pstmt.setLong(1, start);
			//            pstmt.setLong(2, start + count);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			//throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

	public Collection getUsers(String inputSQL, int start, int count)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;
		String sql = Global.getDialect().getLimitString(inputSQL);

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);

			//            pstmt.setLong(1, start);
			//            pstmt.setLong(2, start + count);
			pstmt = Global.getDialect().setStatementPageValue(pstmt, 1, start,
					2, start + count);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString(5));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString(7));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

	/**
	 * 获取角色用户
	 */
	public Collection getRoleUsers(String rid, int start, int count)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;
		String sql = Global.getDialect().getLimitString(
				sqlFactory.get("GET_Role_USERS"));

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rid);
			pstmt = Global.getDialect().setStatementPageValue(pstmt, 2,
					(int) start, 3, (int) (start + count));

			//            pstmt.setLong(2, start);
			//            pstmt.setLong(3, start + count);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_007", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

	/**
	 * 获取角色用户
	 */
	public Collection getRoleUsers(String rid) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sqlFactory.get("GET_Role_USERS"));
			pstmt.setString(1, rid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_007", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

	public int getRoleUserCount(String rid) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		int role_usercount = 0;

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sqlFactory.get("GET_RoleUser_Count"));
			pstmt.setString(1, rid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				role_usercount = rs.getInt(1);
			}

			return role_usercount;
		} catch (Exception e) {
			throw new DAOException("PRV_USER_008", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public boolean haveUserName(String username) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		int role_usercount = 0;

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sqlFactory.get("GET_USER_ByNAME"));
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_009", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public User findUserByUserName(String username) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		int role_usercount = 0;

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sqlFactory.get("GET_USER_ByNAME"));
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				User user = OrganizationFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setCode(rs.getString("CODE"));
				user.setGender(rs.getString("GENDER"));

				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_010", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中
	 * 
	 * @完成时间：2004年11月17日
	 * @作者：方晓
	 */
	public void addUserToRole(String[] userId, String rolesId)
			throws DAOException, SQLException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO T_USER_ROLE(USER_ID,ROLE_ID)VALUES(?,?)";

		//    "DELETE FROM T_USER WHERE USERTYPE<>'0' and ID=" + userId[0];
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);

			for (int i = 0; i < userId.length; i++) {
				if (userId[i] != null) {
					pstmt.setString(1, userId[i]);
					pstmt.setString(2, rolesId);

					pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			throw new DAOException("ORG_User_003", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_User_003", e);
			}
		}
	}

	/**
	 * 扩展方法：得出列表，并具有有过滤功能 修改时间：2004年11月18日 修改作者：方晓
	 */
	public Collection getUsers(int start, int count, String rid)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;

		String sql1 = "SELECT ID,USERNAME,PASSWORD,USERTYPE,Level,Status,Validdate,Name,Code,Gender"
				+ " FROM T_USER WHERE ID NOT IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)";
		String sql = Global.getDialect().getLimitString(sql1);

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rid);

			pstmt = Global.getDialect().setStatementPageValue(pstmt, 2,
					(int) start, 3, (int) (start + count));

			//pstmt.setLong(1, start);
			//   pstmt.setLong(2, start + count);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

	/**
	 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中。
	 * 
	 * @完成时间：2004年11月17日
	 * @作者：方晓
	 * @扩展功能：新加了一个参数roleId
	 * @修改时间：2004年11月24日
	 * @修改人：方晓（杨帆协助）
	 */
	public void deleteUserFromRole(String[] userId, String roleId)
			throws DAOException, SQLException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM T_USER_ROLE WHERE ROLE_ID=? and USER_ID="
				+ userId[0];

		for (int i = 1; i < userId.length; i++) {
			sql += (" OR USER_ID=" + userId[i]);
		}

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roleId);
			pstmt.executeUpdate();
//			System.out.println(sql);
		} catch (Exception e) {
			throw new DAOException("ORG_User_003", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_User_003", e);
			}
		}
	}

	/**
	 * libiao 增加通过level来找用户名
	 * 
	 * @param level
	 *            String
	 * @throws DAOException
	 * @return String[]
	 */

	// public String[] findFunctionaryMan(String level) throws DAOException{
	//        ConnectionProvider cp = null;
	//        Connection conn = null;
	//        ResultSet rs = null;
	//        PreparedStatement pstmt = null;
	//        String[] listname=null;
	//        try {
	//          cp =
	// ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
	//          conn = cp.getConnection();
	//          pstmt = conn.prepareStatement(select_man);
	//          pstmt.setString(1, String.valueOf(level));
	//          rs = pstmt.executeQuery();
	//          int i=0;
	//        while(rs.next())
	//        {
	//          listname[i]=rs.getString("NAME");
	//          i++;
	//        }
	//     } catch (Exception e) {
	//                                 throw new DAOException("PRV_RES_001", e);
	//     } finally {
	//         try {
	//             if (rs != null) {
	//                 rs.close();
	//             }
	//
	//             rs = null;
	//
	//             if (conn != null) {
	//                 conn.close();
	//             }
	//
	//             if (pstmt != null) {
	//                 pstmt.close();
	//             }
	//         } catch (Exception e) {
	//         }
	//     }
	//     return listname;
	// }
	// public String findAllMan() throws DAOException{
	//        ConnectionProvider cp = null;
	//        Connection conn = null;
	//        ResultSet rs = null;
	//        PreparedStatement pstmt = null;
	//       // I_User user = null;
	//      // Collection listuser=new ArrayList();
	//      String user_level;
	//        try {
	//          cp =
	// ConnectionProviderFactory.getConnectionProvider(Constants.DATA_SOURCE);
	//          conn = cp.getConnection();
	//          pstmt = conn.prepareStatement(select_all_man);
	//          rs = pstmt.executeQuery();
	//          int i=0;
	//          rs.next();
	//          user_level=rs.getString("LEVEL");
	//
	//       // while(rs.next())
	//      // {
	//         // user = PrivilegeFactory.getInstance().createUser();
	//        // user.setLevel(rs.getString("LEVEL"));
	//        // listuser.add(user);
	//       // }
	//     } catch (Exception e) {
	//                                 throw new DAOException("PRV_RES_001", e);
	//     } finally {
	//         try {
	//             if (rs != null) {
	//                 rs.close();
	//             }
	//
	//             rs = null;
	//
	//             if (conn != null) {
	//                 conn.close();
	//             }
	//
	//             if (pstmt != null) {
	//                 pstmt.close();
	//             }
	//         } catch (Exception e) {
	//         }
	//     }
	//     return user_level;
	// }
	/**
	 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中后的分页的表示
	 * 
	 * @完成时间：2004年11月30日
	 * @作者：方晓
	 */

	public int getRoleToUserCount(String rid) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		String sql = "SELECT COUNT(ID) AS COUNT"
				+ " FROM T_USER WHERE ID NOT IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)";
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (Exception e) {
			throw new DAOException("ORG_User_012", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_User_012", e);
			}
		}
		return count;

	}

	/**
	 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中
	 * 
	 * @完成时间：2004年11月17日
	 * @作者：方晓
	 */

	/*
	 * public void addUserToRole(String[] userId, String rolesId) throws
	 * DAOException, SQLException { ConnectionProvider cp = null; Connection
	 * conn = null; PreparedStatement pstmt = null; String sql = "INSERT INTO
	 * T_USER_ROLE(USER_ID,ROLE_ID)VALUES(?,?)"; // "DELETE FROM T_USER WHERE
	 * USERTYPE <>'0' and ID=" + userId[0];
	 * 
	 * try { cp = ConnectionProviderFactory.getConnectionProvider(Constants.
	 * DATA_SOURCE); conn = cp.getConnection(); pstmt =
	 * conn.prepareStatement(sql); for (int i = 0; i < userId.length; i++) { if
	 * (userId[i] != null) { pstmt.setString(1, userId[i]); pstmt.setString(2,
	 * rolesId); System.out.println("-----rolesid-------" + rolesId);
	 * pstmt.executeUpdate(); } } } catch (Exception e) { throw new
	 * DAOException("ORG_User_003", e); } finally { try {
	 * ConnectionUtil.close(conn, pstmt); } catch (Exception e) { throw new
	 * DAOException("ORG_User_003", e); } }
	 *  }
	 */
	/**
	 * 扩展方法：得出列表，并具有有过滤功能 修改时间：2004年11月18日 修改作者：方晓
	 */
	/*
	 * public Collection getUsers(int start, int count, String rid) throws
	 * DAOException { ConnectionProvider cp = null; Connection conn = null;
	 * PreparedStatement pstmt = null; ResultSet rs = null; ArrayList list = new
	 * ArrayList(); I_User user = null;
	 * 
	 * String sql1 = "SELECT
	 * ID,USERNAME,PASSWORD,USERTYPE,Level,Status,Validdate,Name,Code,Gender" + "
	 * FROM T_USER WHERE ID NOT IN (SELECT USER_ID FROM T_USER_ROLE WHERE
	 * ROLE_ID=?)"; String sql = Global.getDialect().getLimitString(sql1);
	 * 
	 * try { cp = ConnectionProviderFactory.getConnectionProvider(Constants.
	 * DATA_SOURCE); conn = cp.getConnection(); pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, rid);
	 * System.out.println(rid + "================="); pstmt =
	 * Global.getDialect().setStatementPageValue(pstmt, 2, (int) start, 3, (int)
	 * (start + count)); //pstmt.setLong(1, start); // pstmt.setLong(2, start +
	 * count);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { user = PrivilegeFactory.getInstance().createUser();
	 * user.setId(rs.getInt("ID")); user.setUserName(rs.getString("USERNAME"));
	 * user.setPassword(rs.getString("PASSWORD"));
	 * user.setUserType(rs.getString("USERTYPE"));
	 * user.setLevel(rs.getString("LEVEL"));
	 * user.setStatus(rs.getString("STATUS"));
	 * user.setValidDate(rs.getString("VALIDDATE"));
	 * user.setName(rs.getString("NAME"));
	 * user.setGender(rs.getString("GENDER"));
	 * user.setCode(rs.getString("CODE"));
	 * 
	 * list.add(user); } } catch (Exception e) { throw new
	 * DAOException("PRV_USER_006", e); } finally { try { if (conn != null) {
	 * conn.close(); }
	 * 
	 * if (pstmt != null) { pstmt.close(); } } catch (Exception e) { } }
	 * 
	 * return list; }
	 */
	/**
	 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中。
	 * 
	 * @完成时间：2004年11月17日
	 * @作者：方晓
	 * @扩展功能：新加了一个参数roleId
	 * @修改时间：2004年11月24日
	 * @修改人：方晓（杨帆协助）
	 */

	/*
	 * public void deleteUserFromRole(String[] userId, String roleId) throws
	 * DAOException, SQLException { ConnectionProvider cp = null; Connection
	 * conn = null; PreparedStatement pstmt = null; String sql = "DELETE FROM
	 * T_USER_ROLE WHERE ROLE_ID=? and USER_ID=" + userId[0]; for (int i = 1; i <
	 * userId.length; i++) { sql += " OR USER_ID=" + userId[i]; } try { cp =
	 * ConnectionProviderFactory.getConnectionProvider( Constants.DATA_SOURCE);
	 * conn = cp.getConnection(); pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, roleId); pstmt.executeUpdate();
	 * System.out.println(sql); } catch (Exception e) { throw new
	 * DAOException("ORG_User_003", e); } finally { try {
	 * ConnectionUtil.close(conn, pstmt); } catch (Exception e) { throw new
	 * DAOException("ORG_User_003", e); } }
	 *  }
	 */
	/*
	 * 扩展方法：提交通过行政级别来过滤角色分配用户列表而得到新的列表。 修改时间：2004年12月1日 修改作者：方晓
	 */
	public Collection findUserInRolesByLevel(int start, int count, String rid,
			String level) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;
		String sql1 = "SELECT ID,USERNAME,PASSWORD,USERTYPE,Level,Status,Validdate,Name,Code,Gender"
				+ " FROM T_USER WHERE ID NOT IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)"
				+ "and level=?";
		String sql = Global.getDialect().getLimitString(sql1);
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rid);
			pstmt.setString(2, level);
			//
			pstmt = Global.getDialect().setStatementPageValue(pstmt, 3,
					(int) start, 4, (int) (start + count));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;

	}

	/**
	 * 扩展方法：通过将用户列表中的id添加一条记录或多个记录到角色分配用户表中后并且通过level来选择的分页的表示
	 * 
	 * @完成时间：2004年11月30日
	 * @作者：方晓
	 */

	public int getRoleToUserCountByLevel(String rid, String level)
			throws DAOException {

		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		String sql = "SELECT COUNT(ID) AS COUNT"
				+ " FROM T_USER WHERE ID NOT IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)"
				+ "and level=?";
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rid);
			pstmt.setString(2, level);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (Exception e) {
			throw new DAOException("ORG_User_012", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_User_012", e);
			}
		}
		return count;

	}

	/**
	 * 正在改进中………………………………
	 * 
	 * 
	 *  
	 */

	public Collection getUsersInRoles(String rid) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;

		String sql1 = "SELECT ID,USERNAME,PASSWORD,USERTYPE,Level,Status,Validdate,Name,Code,Gender"
				+ " FROM T_USER WHERE ID NOT IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, rid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

	public Collection getSelectUsersInRoles(String rid) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;

		String sql1 = "SELECT ID,USERNAME,PASSWORD,USERTYPE,Level,Status,Validdate,Name,Code,Gender"
				+ " FROM T_USER WHERE ID  IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, rid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;
	}

	/*
	 * 
	 * 正在建设中^^^^^^^^^^^^^^^^^^
	 *  
	 */
	public Collection findUserInRolesByLevel(String rid, String level)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;
		String sql1 = "SELECT ID,USERNAME,PASSWORD,USERTYPE,Level,Status,Validdate,Name,Code,Gender"
				+ " FROM T_USER WHERE ID NOT IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)"
				+ "and level=?";
		// String sql = Global.getDialect().getLimitString(sql1);
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, rid);
			pstmt.setString(2, level);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;

	}

	/*
	 * 
	 * 正在建设中^^^^^^^^^^^^^^^^^^
	 *  
	 */
	public Collection findSelectUserInRolesByLevel(String rid, String level)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		I_User user = null;
		String sql1 = "SELECT ID,USERNAME,PASSWORD,USERTYPE,Level,Status,Validdate,Name,Code,Gender"
				+ " FROM T_USER WHERE ID IN (SELECT USER_ID FROM T_USER_ROLE WHERE ROLE_ID=?)"
				+ "and level=?";
		// String sql = Global.getDialect().getLimitString(sql1);
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, rid);
			pstmt.setString(2, level);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("PRV_USER_006", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

		return list;

	}

	/*
	 */
	public void addUsers(Role role, Collection users) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Iterator iroles = users.iterator();

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			String sql = "INSERT INTO T_USER_ROLE(USER_ID,ROLE_ID)VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			//      System.out.println(sql+"===============add");
			while (iroles.hasNext()) {
				User user = (User) iroles.next();
				pstmt.setString(1, String.valueOf(role.getId()));
				pstmt.setString(2, String.valueOf(user.getId()));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_024", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public Collection getUsersByID(String[] userids) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection result = new ArrayList();
		User user = null;

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			StringBuffer sbsql = new StringBuffer(GET_RoleUserByID);
			for (int i = 0; i < userids.length; i++) {
				sbsql.append(" or ID=");
				sbsql.append(userids[i]);
			}
			pstmt = conn.prepareStatement(sbsql.toString());
//			System.out.println(sbsql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = PrivilegeFactory.getInstance().createUser();
				user.setId(rs.getInt("ID"));
				user.setUserName(rs.getString("USERNAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setUserType(rs.getString("USERTYPE"));
				user.setLevel(rs.getString("LEVEL"));
				user.setStatus(rs.getString("STATUS"));
				user.setValidDate(rs.getString("VALIDDATE"));
				user.setName(rs.getString("NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setCode(rs.getString("CODE"));

				result.add(user);
			}

			return result;
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_028", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}

	}

	public void deleteRoleUserByIds(String rid) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer delsql = new StringBuffer(DEL_ALL_User_ROLES);

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(delsql.toString());
			pstmt.setString(1, rid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("PRV_ROLE_013", e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
			}
		}
	}

}