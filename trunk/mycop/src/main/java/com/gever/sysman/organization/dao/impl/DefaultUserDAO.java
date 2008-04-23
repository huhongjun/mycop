package com.gever.sysman.organization.dao.impl;

import com.gever.sysman.db.SequenceMan;
import com.gever.sysman.organization.dao.*;
import com.gever.sysman.organization.model.*;
import com.gever.config.Constants;
import com.gever.exception.db.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.ArrayList;
import com.gever.jdbc.connection.*;
import com.gever.jdbc.database.dialect.Global;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.util.OrderList;
import com.gever.util.crypto.Digest;
import com.gever.util.crypto.Util;
import com.gever.web.menusetup.dao.MenuSetupFactory;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: KOBE OFFICE
 * </p>
 * 
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultUserDAO implements UserDAO {

	SQLFactory sqlFactory = OrganizationFactory.getInstance().getSQLFactory();

	public void createUser(User user) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//    String sql = "INSERT INTO T_USER(ID, USERNAME, PASSWORD, USERTYPE,
		// LEVEL, STATUS, VALIDDATE, NAME, CODE, GENDER) VALUES
		// (?,?,?,?,?,?,?,?,?,?)";
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			//检测CA是否已分配给某个用户
			if ("2".equals(user.getUserType())) {
				String tmpSql = "select username from T_USER where usertype='2' and password='"
						+ user.getPassword() + "'";
				pstmt = conn.prepareStatement(tmpSql);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					throw new Exception("该CA证书已分配给账号"
							+ rs.getString("username"));
				}
			}

			pstmt = conn.prepareStatement(sqlFactory.get("CREATE_USER"));

			int i = 1;
			pstmt.setLong(i++, SequenceMan.nextID());
			pstmt.setString(i++, user.getUserName());
			// 普通用户要加密，CA用户不用加密
			if ("2".equals(user.getUserType())) {
				pstmt.setString(i++, user.getPassword());
			} else {
				byte[] pass_bytes = Digest.getMessageDigest(user.getPassword(),
						Digest.MD5);
				pstmt.setString(i++, Util.byte2hex(pass_bytes));
			}
			pstmt.setString(i++, user.getUserType());
			pstmt.setString(i++, user.getLevel());
			pstmt.setString(i++, user.getStatus());
			pstmt.setString(i++, user.getValidDate());
			pstmt.setString(i++, user.getName());
			pstmt.setString(i++, user.getCode());
			pstmt.setString(i++, user.getGender());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_User_001", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_User_001", e);
			}
		}
	}

	public void updateUser(User user) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String oldUserType = this.findUserByID(user.getId()).getUserType();
		String sql = "";
		if ("2".equals(user.getUserType())) {
			if ("2".equals(oldUserType)) {
				sql = sqlFactory.get("UPDATE_USER22");
			} else if ("1".equals(oldUserType)) {
				sql = sqlFactory.get("UPDATE_USER21");
			}
		} else {
			if ("1".equals(oldUserType)) {
				sql = sqlFactory.get("UPDATE_USER11");
			} else if ("2".equals(oldUserType)) {
				sql = sqlFactory.get("UPDATE_USER12");
			}
		}
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			//检测CA是否已分配给某个用户
			if ("2".equals(user.getUserType())) {
				String tmpSql = "select username from T_USER where usertype='2' and password='"
						+ user.getPassword() + "' AND ID<>" + user.getId();
				pstmt = conn.prepareStatement(tmpSql);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					throw new Exception("该CA证书已分配给账号"
							+ rs.getString("username"));
				}
			}

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setInt(i++, user.getId());
			pstmt.setString(i++, user.getUserName());
			if ("2".equals(user.getUserType())) {
				pstmt.setString(i++, user.getPassword());
			} else {
				if ("2".equals(oldUserType)) {
					byte[] pass_bytes = Digest.getMessageDigest(user
							.getPassword(), Digest.MD5);
					pstmt.setString(i++, Util.byte2hex(pass_bytes));
				}
			}
			pstmt.setString(i++, user.getUserType());
			pstmt.setString(i++, user.getLevel());
			pstmt.setString(i++, user.getStatus());
			pstmt.setString(i++, user.getValidDate());
			pstmt.setString(i++, user.getName());
			pstmt.setString(i++, user.getCode());
			pstmt.setString(i++, user.getGender());
			pstmt.setInt(i++, user.getId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_User_002", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_User_002", e);
			}
		}
	}

	public void deleteUser(String[] userId) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM T_USER WHERE USERTYPE<>'0' and ID="
				+ userId[0];
		for (int i = 1; i < userId.length; i++) {
			sql += " OR ID=" + userId[i];
		}
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_User_003", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_User_003", e);
			}
		}
		//========================================================================================
		// 胡勇添加
		// 删除用户关联数据
		try {
			MenuSetupFactory.getInstance().getMenuSetupDao().deleteByUserID(userId);
			PrivilegeFactory.getInstance().getPermissionDAO().delUserPerm(userId);
			OrganizationFactory.getInstance().getUserJobDAO().deleteUserJobByUser(userId);
			OrganizationFactory.getInstance().getUserDepartmentDAO().deleteUserDepartmentByUser(userId);
		} catch (Exception e) {
			throw new DAOException("ORG_User_003_LinkDate", e);
		}
		//========================================================================================
	}

	public Collection findUser(String searchQuery, long start, long count)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		User user = null;
		String sql = sqlFactory.get("FIND_USER") + " WHERE " + searchQuery;
		sql += (" ORDER BY USERNAME ASC ");

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			for (int i = 1; rs.next(); i++) {
				if (start != 0 && i < start) {
					continue;
				}
				if (count != 0 && i >= count) {
					break;
				}

				user = OrganizationFactory.getInstance().createUser();
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

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_User_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_User_004", e);
			}
		}
		return list;
	}

	//==========================================================
	//胡勇添加，增加JSP视图列表排序功能
	private String[] orderby;

	public void setOrderby(String[] s) {
		this.orderby = s;
	}

	//==========================================================
	public Collection findUsersForPage(String searchQuery, long start,
			long count) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		User user = null;
		String sql = sqlFactory.get("FIND_USER") + " WHERE " + searchQuery;
		sql += (" ORDER BY USERNAME ASC ");

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			sql = Global.getDialect().getLimitString(sql);
			//==========================================================
			//胡勇添加，增加JSP视图列表排序功能
			sql = OrderList.getInstance().formatSQL(orderby, sql);
			//==========================================================
			pstmt = conn.prepareStatement(sql);
			pstmt = Global.getDialect().setStatementPageValue(pstmt, 1,
					(int) start, 2, (int) (start + count));
			//      pstmt.setLong(1, start);
			//      pstmt.setLong(2, start + count);

			rs = pstmt.executeQuery();

			for (int i = 1; rs.next(); i++) {
				user = OrganizationFactory.getInstance().createUser();
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

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_User_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_User_004", e);
			}
		}
		return list;
	}

	public User findUserByID(long id) throws DAOException {
		try {
			Collection collection = this.findUser(" ID=" + id, 0, 0);
			return (User) collection.iterator().next();
		} catch (DAOException e) {
			throw new DAOException("ORG_User_005", e);
		}

	}

	public User findUserByUserName(String userName) throws DAOException {
		try {
			Collection collection = this.findUser(" USERNAME='" + userName
					+ "' ", 0, 0);
			if (collection.size() > 0)
				return (User) collection.iterator().next();
			else
				return null;
		} catch (DAOException e) {
			throw new DAOException("ORG_User_006", e);
		}

	}

	public Collection findUserByPin(String pin) throws DAOException {
		try {
			return this.findUser(" password='" + pin + "' ", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_007", e);
		}

	}

	public Collection findUserByName(String name) throws DAOException {
		try {
			return this.findUser(" name='" + name + "' ", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_007", e);
		}

	}

	public Collection getUsers(long start, long count) throws DAOException {
		try {
			return this.findUser(" 0=0 ", start, count);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_008", e);
		}

	}

	public Collection getUsersForPage(long start, long count)
			throws DAOException {
		try {
			return this.findUsersForPage(" 0=0 ", start, count);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_004", e);
		}

	}

	public Collection getUsers() throws DAOException {
		try {
			return this.findUser(" 0=0 ", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_009", e);
		}

	}

	public Collection getJobs(User user) throws DAOException {
		try {
			return OrganizationFactory.getInstance().getJobDAO().findJob(
					"T_JOB.ID IN (SELECT JOB_ID AS ID FROM T_JOB_PERSON WHERE ID="
							+ user.getId() + ")", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_010", e);
		}

	}

	public Collection getJobs(User user, Department dept) throws DAOException {
		try {
			return OrganizationFactory
					.getInstance()
					.getJobDAO()
					.findJob(
							"T_JOB.ID IN (SELECT JOB_ID AS ID FROM T_JOB_PERSON WHERE ID="
									+ user.getId()
									+ ") and T_JOB.ID IN (SELECT  ID FROM T_JOB WHERE DEPARTMENT_ID="
									+ dept.getId() + ")", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_010", e);
		}

	}

	public Collection getUnDistributeJobs(User user, Department dept)
			throws DAOException {
		try {
			return OrganizationFactory
					.getInstance()
					.getJobDAO()
					.findJob(
							"T_JOB.ID NOT IN (SELECT JOB_ID AS ID FROM T_JOB_PERSON WHERE ID="
									+ user.getId()
									+ ") and T_JOB.ID IN (SELECT  ID FROM T_JOB WHERE DEPARTMENT_ID="
									+ dept.getId() + ")", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_010", e);
		}
	}

	public Collection getDepartments(User user) throws DAOException {
		try {
			return OrganizationFactory.getInstance().getDepartmentDAO()
					.findDepartment(
							"A.ID IN (SELECT DEPARTMENT_ID AS ID FROM T_DEPARTMENT_PERSON WHERE ID="
									+ user.getId() + ")", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_User_011", e);
		}

	}

	public int getUserCount(String searchQuery) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		String sql = "SELECT COUNT(ID) AS COUNT FROM T_USER WHERE "
				+ searchQuery;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
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

	public int getUserCount() throws DAOException {
		try {
			return this.getUserCount(" 0=0 ");
		} catch (DAOException e) {
			throw new DAOException("ORG_User_012", e);
		}

	}

	public int getUserCountBySQL(String inputSQL) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = this.parseSQL4RecordCount(inputSQL);
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (Exception e) {
			//throw new DAOException("ORG_User_012", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_User_012", e);
			}
		}
		return count;

	}

	public void addDepartments(User user, Collection departments)
			throws DAOException {
		try {
			UserDepartmentDAO uddao = OrganizationFactory.getInstance()
					.getUserDepartmentDAO();
			UserDepartment ud = OrganizationFactory.getInstance()
					.createUserDepartment();
			while (departments.iterator().hasNext()) {
				ud.setUser(user.getId());
				ud.setDepartment(((Department) departments.iterator().next())
						.getId());
				uddao.createUserDepartment(ud);
			}
		} catch (DAOException e) {
			throw new DAOException("ORG_User_014", e);
		}

	}

	public void addJobs(User user, Collection jobs) throws DAOException {
		try {
			UserJobDAO ujdao = OrganizationFactory.getInstance()
					.getUserJobDAO();
			UserJob uj = OrganizationFactory.getInstance().createUserJob();
			while (jobs.iterator().hasNext()) {
				uj.setUser(user.getId());
				uj.setJob(((Job) jobs.iterator().next()).getId());
				ujdao.createUserJob(uj);
			}
		} catch (DAOException e) {
			throw new DAOException("ORG_User_015", e);
		}

	}

	public void setDepartments(User user, Collection departments)
			throws DAOException {

		UserDepartmentDAO uddao = OrganizationFactory.getInstance()
				.getUserDepartmentDAO();
		UserDepartment ud = OrganizationFactory.getInstance()
				.createUserDepartment();
		int size = departments.size();
		int[] userId = new int[size];
		int[] departmentId = new int[size];
		Object[] obj = departments.toArray();
		try {
			for (int i = 0; i < size; i++) {
				userId[i] = user.getId();
				departmentId[i] = ((Department) obj[i]).getId();
			}
			uddao.deleteUserDepartment(userId, departmentId);

			while (departments.iterator().hasNext()) {
				ud.setUser(user.getId());
				ud.setDepartment(((Department) departments.iterator().next())
						.getId());
				uddao.createUserDepartment(ud);
			}
		} catch (DAOException e) {
			throw new DAOException("ORG_User_016", e);
		}

	}

	public void setJobs(User user, Collection jobs) throws DAOException {
		UserJobDAO ujdao = OrganizationFactory.getInstance().getUserJobDAO();
		UserJob uj = OrganizationFactory.getInstance().createUserJob();
		int size = jobs.size();
		int[] userId = new int[size];
		int[] jobId = new int[size];
		Object[] obj = jobs.toArray();
		try {
			for (int i = 0; i < size; i++) {
				userId[i] = user.getId();
				jobId[i] = ((Job) obj[i]).getId();
			}
			ujdao.deleteUserJob(userId, jobId);

			while (jobs.iterator().hasNext()) {
				uj.setUser(user.getId());
				uj.setJob(((Job) jobs.iterator().next()).getId());
				ujdao.createUserJob(uj);
			}

		} catch (DAOException e) {
			throw new DAOException("ORG_User_017", e);
		}
	}

	public void updateUserPassword(User user) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		sql = "UPDATE T_USER SET PASSWORD = ? WHERE ID = ?";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			byte[] pass_bytes = Digest.getMessageDigest(user.getPassword(),
					Digest.MD5);
			pstmt.setString(i++, Util.byte2hex(pass_bytes));
			pstmt.setInt(i++, user.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_User_002", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_User_002", e);
			}
		}

	}

	private String parseSQL4RecordCount(String inputSQL) throws DAOException {
		String result = null;
		int position = inputSQL.indexOf("FROM");
		if (position != -1) {
			result = "select count(*) as COUNT " + inputSQL.substring(position);
			position = result.toUpperCase().indexOf("ORDER BY");
			if (position != -1) {
				result = result.substring(0, position);
			}
		}
		return result;
	}

	/**
	 * data 2004/12/2 author libiao
	 * 
	 * @throws DAOException
	 * @return Collection
	 */
	public Collection getAllUserName() throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		User user = null;
		String sql = sqlFactory.get("FIND_USER");
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			for (int i = 1; rs.next(); i++) {
				//                                  if (start != 0 && i < start) {
				//                                          continue;
				//                                  }
				//                                  if (count != 0 && i >= count) {
				//                                          break;
				//                                  }

				user = OrganizationFactory.getInstance().createUser();
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

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_User_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_User_004", e);
			}
		}
		return list;
	}

	public Collection getAllLevel() throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		User user = null;
		String sql = "SELECT DISTINCT LEVEL FROM T_USER";
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = OrganizationFactory.getInstance().createUser();
				user.setLevel(rs.getString("LEVEL"));

				list.add(user);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_User_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_User_004", e);
			}
		}
		return list;
	}
	
	// 行政级别删除时更新用户列表
	public void updateUserLevel(String[] level_id) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE T_USER SET LEVEL='1' where LEVEL='" + level_id[0] + "'";
		for(int i=1;i<level_id.length;i++){
			sql += " OR LEVEL='" + level_id[i] + "'";
		}
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_User_005", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_User_005", e);
			}
		}
	}
}