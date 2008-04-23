package com.gever.sysman.organization.dao.impl;

import com.gever.config.Constants;
import com.gever.exception.db.DAOException;
import com.gever.jdbc.connection.*;
import com.gever.jdbc.database.dialect.Global;

import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.dao.SQLFactory;
import com.gever.sysman.organization.dao.UserDepartmentDAO;
import com.gever.sysman.organization.model.UserDepartment;
import com.gever.sysman.organization.model.impl.DefaultUserDepartment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;

/**
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
public class DefaultUserDepartmentDAO implements UserDepartmentDAO {
	protected SQLFactory sqlFactory = OrganizationFactory.getInstance()
			.getSQLFactory();

	public DefaultUserDepartmentDAO() {
	}

	public void createUserDepartment(UserDepartment userDepartment)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO T_DEPARTMENT_PERSON(ID, DEPARTMENT_ID) VALUES (?,?)";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);

			int i = 1;
			pstmt.setInt(i++, userDepartment.getUser());
			pstmt.setInt(i++, userDepartment.getDepartment());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_001", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_001", e);
			}
		}
	}

	public void createUserDepartment(int[] userId, int[] departmentId)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		Statement stmt = null;
		String sql = "";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			stmt = conn.createStatement();

			for (int i = 0; i < userId.length; i++) {
				sql = "INSERT INTO T_DEPARTMENT_PERSON(ID, DEPARTMENT_ID) VALUES ("
						+ userId[i] + "," + departmentId[i] + ")";
				stmt.addBatch(sql);
			}

			stmt.executeBatch();
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_002", e);
		} finally {
			try {
				ConnectionUtil.close(conn, stmt);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_002", e);
			}
		}
	}

	public void updateUserDepartment(UserDepartment userDepartment,
			int userUpdate, int departmentUpdate) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE T_DEPARTMENT_PERSON SET ID=?, DEPARTMENT_ID=? WHERE ID=? AND DEPARTMENT_ID=?";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);

			int i = 1;
			pstmt.setInt(i++, userDepartment.getUser());
			pstmt.setInt(i++, userDepartment.getDepartment());
			pstmt.setInt(i++, userUpdate);
			pstmt.setInt(i++, departmentUpdate);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_003", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_003", e);
			}
		}
	}

	public void deleteUserDepartment(int[] userId, int[] departmentId)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM T_DEPARTMENT_PERSON WHERE (ID=" + userId[0]
				+ " AND DEPARTMENT_ID=" + departmentId[0] + ")";

		for (int i = 1; i < userId.length; i++) {
			sql += (" OR (ID=" + userId[i] + " AND DEPARTMENT_ID="
					+ departmentId[i] + ")");
		}

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_004", e);
			}
		}
	}

	public void deleteUserDepartmentByDepartment(int departmentId)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM T_DEPARTMENT_PERSON WHERE DEPARTMENT_ID="
				+ departmentId + "";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_004", e);
			}
		}
	}

	public void deleteUserDepartmentByUser(int userId) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM T_DEPARTMENT_PERSON WHERE ID=" + userId + "";

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_004", e);
			}
		}
	}

	// 批量删除接口
	public void deleteUserDepartmentByUser(String[] userId) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM T_DEPARTMENT_PERSON WHERE ID=" + Integer.parseInt(userId[0]);
		for(int i=1;i<userId.length;i++){
        	sql += " OR ID=" + Integer.parseInt(userId[i]);
        }
		
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_004", e);
			}
		}
	}

	public Collection findUserDepartment(String searchQuery, long start,
			long count) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		UserDepartment userDepartment = null;

		StringBuffer sql = new StringBuffer();

		//    sql.append(" SELECT ");
		//    sql.append(" T_DEPARTMENT_PERSON.ID AS ID, ");
		//    sql.append(" T_DEPARTMENT_PERSON.DEPARTMENT_ID AS DEPARTMENT_ID, ");
		//    sql.append(" T_USER.USERNAME AS USER_NAME, ");
		//    sql.append(" T_DEPARTMENT.NAME AS DEPARTMENT_NAME ");
		//    sql.append(" FROM ");
		//    sql.append(" T_DEPARTMENT_PERSON ");
		//    sql.append(" LEFT JOIN T_USER ON(T_DEPARTMENT_PERSON.ID=T_USER.ID)
		// ");
		//    sql.append(
		//        " LEFT JOIN T_DEPARTMENT
		// ON(T_DEPARTMENT_PERSON.DEPARTMENT_ID=T_DEPARTMENT.ID) ");
		//    sql.append(" WHERE ");
		sql.append(sqlFactory.get("FIND_USER_DEPARTMENT"));
		sql.append(" " + searchQuery);
		sql.append(" ORDER BY USER_NAME ASC, DEPARTMENT_NAME ASC ");

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			for (int i = 1; rs.next(); i++) {
				if ((start != 0) && (i < start)) {
					continue;
				}

				if ((count != 0) && (i >= count)) {
					break;
				}

				userDepartment = OrganizationFactory.getInstance()
						.createUserDepartment();
				userDepartment.setUser(rs.getInt("ID"));
				userDepartment.setDepartment(rs.getInt("DEPARTMENT_ID"));
				userDepartment.setUserName(rs.getString("USER_NAME"));
				userDepartment.setDepartmentName(rs
						.getString("DEPARTMENT_NAME"));

				list.add(userDepartment);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_005", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_005", e);
			}
		}

		return list;
	}

	public Collection findUserDepartmentsForPage(String searchQuery,
			long start, long count) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		UserDepartment userDepartment = null;

		StringBuffer sql = new StringBuffer();

		//    sql.append(" SELECT ");
		//    sql.append(" T_DEPARTMENT_PERSON.ID AS ID, ");
		//    sql.append(" T_DEPARTMENT_PERSON.DEPARTMENT_ID AS DEPARTMENT_ID, ");
		//    sql.append(" T_USER.USERNAME AS USER_NAME, ");
		//    sql.append(" T_DEPARTMENT.NAME AS DEPARTMENT_NAME ");
		//    sql.append(" FROM ");
		//    sql.append(" T_DEPARTMENT_PERSON ");
		//    sql.append(" LEFT JOIN T_USER ON(T_DEPARTMENT_PERSON.ID=T_USER.ID)
		// ");
		//    sql.append(
		//        " LEFT JOIN T_DEPARTMENT
		// ON(T_DEPARTMENT_PERSON.DEPARTMENT_ID=T_DEPARTMENT.ID) ");
		//    sql.append(" WHERE ");
		sql.append(sqlFactory.get("FIND_USER_DEPARTMENTS_FOR_PAGE"));
		sql.append(" " + searchQuery);
		sql.append(" ORDER BY USER_NAME ASC, DEPARTMENT_NAME ASC ");

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			String strSQL = Global.getDialect().getLimitString(sql.toString());
			pstmt = conn.prepareStatement(strSQL);
			pstmt = Global.getDialect().setStatementPageValue(pstmt, 1,
					(int) start, 2, (int) (start + count));

			//      pstmt.setLong(1, start);
			//      pstmt.setLong(2, start + count);
			rs = pstmt.executeQuery();

			for (int i = 1; rs.next(); i++) {
				userDepartment = OrganizationFactory.getInstance()
						.createUserDepartment();
				userDepartment.setUser(rs.getInt("ID"));
				userDepartment.setDepartment(rs.getInt("DEPARTMENT_ID"));
				userDepartment.setUserName(rs.getString("USER_NAME"));
				userDepartment.setDepartmentName(rs
						.getString("DEPARTMENT_NAME"));

				list.add(userDepartment);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_005", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_005", e);
			}
		}

		return list;
	}

	public Collection findUserDepartments() throws DAOException {
		try {
			return this.findUserDepartment(" 0=0 ", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_UserDepart_006", e);
		}
	}

	public Collection findUserDepartments(long start, long count)
			throws DAOException {
		try {
			return this.findUserDepartment(" 0=0 ", start, count);
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_007", e);
		}
	}

	public Collection findUserDepartmentsForPage(long start, long count)
			throws DAOException {
		try {
			return this.findUserDepartmentsForPage(" 0=0 ", start, count);
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_007", e);
		}
	}

	public Collection findUserDepartmentByUser(int userId) throws DAOException {
		try {
			return this.findUserDepartment(" T_DEPARTMENT_PERSON.ID=" + userId,
					0, 0);
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_008", e);
		}
	}

	public Collection findUserDepartmentByDepartment(int departmentId)
			throws DAOException {
		try {
			return this.findUserDepartment(
					" T_DEPARTMENT_PERSON.DEPARTMENT_ID=" + departmentId, 0, 0);
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_009", e);
		}
	}

	public UserDepartment findUserDepartmentByUserByDepartment(int userId,
			int departmentId) throws DAOException {
		try {
			Collection collection = this.findUserDepartment(
					" T_DEPARTMENT_PERSON.ID=" + userId
							+ " AND T_DEPARTMENT_PERSON.DEPARTMENT_ID="
							+ departmentId, 0, 0);

			if (!collection.isEmpty())
				return (UserDepartment) collection.iterator().next();
			else
				return new DefaultUserDepartment();
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_010", e);
		}
	}

	public int getUserDepartmentCount(String searchQuery) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		String sql = "SELECT COUNT(ID) AS COUNT FROM T_DEPARTMENT_PERSON WHERE "
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
			throw new DAOException("ORG_UserDepart_011", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_UserDepart_011", e);
			}
		}

		return count;
	}

	public long getUserDepartmentCount() throws DAOException {
		try {
			return this.getUserDepartmentCount(" 0=0 ");
		} catch (Exception e) {
			throw new DAOException("ORG_UserDepart_012", e);
		}
	}
}