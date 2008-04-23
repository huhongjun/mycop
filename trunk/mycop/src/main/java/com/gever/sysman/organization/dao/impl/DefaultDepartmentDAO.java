package com.gever.sysman.organization.dao.impl;

import com.gever.sysman.db.SequenceMan;
import com.gever.sysman.organization.dao.*;
import com.gever.sysman.organization.model.*;
import com.gever.config.Constants;
import com.gever.exception.db.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import com.gever.jdbc.connection.*;
import com.gever.jdbc.database.dialect.Global;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.util.OrderList;

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

public class DefaultDepartmentDAO implements DepartmentDAO {

	private static SQLFactory sqlFactory = OrganizationFactory.getInstance()
			.getSQLFactory();

	public void insertDepartment(Department department) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =
		//	"insert into
		// T_DEPARTMENT(ID,Code,Name,Description,DepartmentType,Parent_ID)
		// values(?,?,?,?,?,?)";
		"insert into T_DEPARTMENT(ID,Code,Name,Description,DepartmentType,Parent_ID,Functionary) values(?,?,?,?,?,?,?)";//libiao修改2004-11-25
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);

			int i = 1;
			pstmt.setLong(i++, SequenceMan.nextID());
			pstmt.setString(i++, department.getCode());
			pstmt.setString(i++, department.getName());
			pstmt.setString(i++, department.getDescription());
			pstmt.setString(i++, department.getDepartmentType());
			pstmt.setInt(i++, department.getParentDepartment());
			pstmt.setString(i++, department.getFunctionary());//libiao add at
															  // 2004-11-25

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("ORG_Depart_001", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_Depart_001", e);
			}
		}
	}

	public void updateDepartment(Department department) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =
		//	"UPDATE T_DEPARTMENT SET
		// ID=?,CODE=?,NAME=?,DESCRIPTION=?,DEPARTMENTTYPE=?,PARENT_ID=? WHERE
		// ID=?";
		"UPDATE T_DEPARTMENT SET ID=?,CODE=?,NAME=?,DESCRIPTION=?,DEPARTMENTTYPE=?,PARENT_ID=?,FUNCTIONARY=? WHERE ID=?";//libiao
																														 // modify
																														 // at
																														 // 2004-11-25
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);

			int i = 1;
			pstmt.setInt(i++, department.getId());
			pstmt.setString(i++, department.getCode());
			pstmt.setString(i++, department.getName());
			pstmt.setString(i++, department.getDescription());
			pstmt.setString(i++, department.getDepartmentType());
			pstmt.setInt(i++, department.getParentDepartment());
			pstmt.setString(i++, department.getFunctionary());//libiao add at
															  // 2004-11-25
			pstmt.setInt(i++, department.getId());

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("ORG_Depart_002", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt);
			} catch (Exception e) {
				throw new DAOException("ORG_Depart_002", e);
			}
		}
	}

	public void deleteDepartment(String[] departmentId) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//===========================================================================================
		// 胡勇添加，删除关联数据
		if (departmentId.length != 0) {
			ArrayList depar_id = new ArrayList();
			String csql = "SELECT ID FROM T_DEPARTMENT WHERE PARENT_ID="
					+ departmentId[0];
			for (int i = 0; i < departmentId.length; i++) {
				csql += " OR PARENT_ID=" + departmentId[i];
			}
			try {
				cp = ConnectionProviderFactory
						.getConnectionProvider(Constants.DATA_SOURCE);
				conn = cp.getConnection();
				pstmt = conn.prepareStatement(csql);

//				System.out.println(csql);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					depar_id.add(rs.getInt(1) + "");
				}
			} catch (Exception e) {
				throw new DAOException("ORG_Depart_003", e);
			} finally {
				try {
					ConnectionUtil.close(conn, pstmt);
				} catch (Exception e) {
					throw new DAOException("ORG_Depart_003", e);
				}
			}
			//===========================================================================================
			
			//####################libiao add begin at 2004/12/22
			if (departmentId.length > 0) {
				try {
					JobDAO jobDAO = OrganizationFactory.getInstance()
							.getJobDAO();
//					System.out.println(departmentId.length + "");
					jobDAO.deleteJobDepartment(departmentId[0]);
					UserJobDAO userjobDAO = OrganizationFactory.getInstance()
							.getUserJobDAO();

					userjobDAO.deleteUserJobByJob(jobDAO
							.getJobId(departmentId[0]));
				} catch (Exception e) {
					throw new DAOException("ORG_User_003_LinkDate", e);
				}
			}
			//####################libiao add end at 2004/12/22
			String sql = "DELETE FROM T_DEPARTMENT WHERE ID=" + departmentId[0]
					+ " OR PARENT_ID=" + departmentId[0];//LIBIAO ADD DELETE
														 // SUN
			for (int i = 1; i < departmentId.length; i++) {
				sql += " OR ID=" + departmentId[i];
			}
			try {
				cp = ConnectionProviderFactory
						.getConnectionProvider(Constants.DATA_SOURCE);
				conn = cp.getConnection();
				pstmt = conn.prepareStatement(sql);

				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new DAOException("ORG_Depart_003", e);
			} finally {
				try {
					ConnectionUtil.close(conn, pstmt);
				} catch (Exception e) {
					throw new DAOException("ORG_Depart_003", e);
				}
			}

			//===========================================================================================
			// 胡勇添加，删除关联数据
			String[] ss = new String[depar_id.size()];
			depar_id.toArray(ss);
			deleteDepartment(ss);
			//===========================================================================================
		}
	}

	public Collection findDepartment(String searchQuery, long start, long count)
			throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		Department dept = null;

		StringBuffer sql = new StringBuffer();
		sql.append(sqlFactory.get("FIND_DEPARTMENT"));
		sql.append(" ");

		//	    sql.append(" SELECT ");
		//	    sql.append(" A.ID AS ID, ");
		//	    sql.append(" A.CODE AS CODE, ");
		//	    sql.append(" A.NAME AS NAME, ");
		//	    sql.append(" A.DESCRIPTION AS DESCRIPTION, ");
		//	    sql.append(" A.DEPARTMENTTYPE AS DEPARTMENTTYPE, ");
		//	    sql.append(" A.PARENT_ID AS PARENT_ID, ");
		//	    sql.append(" B.NAME AS PARENT_NAME, ");
		//	    sql.append(" C.CHILD AS CHILDNUM ");
		//	    sql.append(" FROM ");
		//	    sql.append(" T_DEPARTMENT A ");
		//	    sql.append(" LEFT JOIN T_DEPARTMENT B ON(A.PARENT_ID=B.ID) ");
		//	    sql.append(" LEFT JOIN (select parent_id,count(*) as child from
		// T_DEPARTMENT group by parent_id )");
		//	    sql.append(" AS C on A.ID=C.parent_id ");
		//	    sql.append(" WHERE ");

		sql.append(searchQuery);
		sql
				.append(" ORDER BY A.ORDERID ASC, A.CODE ASC, A.NAME ASC, B.NAME ASC ");//黎彪增加根据ORDERID来排序2004-11-24；

		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			for (int i = 1; rs.next(); i++) {
				if (start != 0 && i < start) {
					continue;
				}
				if (count != 0 && i >= count) {
					break;
				}

				dept = OrganizationFactory.getInstance().createDepartment();
				dept.setId(rs.getInt("ID"));
				dept.setCode(rs.getString("CODE"));
				dept.setName(rs.getString("NAME"));
				dept.setDescription(rs.getString("DESCRIPTION"));
				dept.setDepartmentType(rs.getString("DEPARTMENTTYPE"));
				dept.setParentDepartment(rs.getInt("PARENT_ID"));
				dept.setFunctioary(rs.getString("FUNCTIONARY"));//libiao add at
																// 2004-11-25
				dept.setParentDepartmentName(rs.getString("PARENT_NAME"));
				dept.setChildNum(rs.getInt("CHILDNUM"));

				list.add(dept);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_Depart_004", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_Depart_004", e);
			}
		}
		return list;
	}

	public Collection findDepartmentsForPage(String searchQuery, long start,
			long count) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		Department dept = null;

		StringBuffer sql = new StringBuffer();
		sql.append(sqlFactory.get("FIND_DEPARTMENT_FOR_PAGE"));
		sql.append(" ");
		//    sql.append(" SELECT ");
		//    sql.append(" A.ID AS ID, ");
		//    sql.append(" A.CODE AS CODE, ");
		//    sql.append(" A.NAME AS NAME, ");
		//    sql.append(" A.DESCRIPTION AS DESCRIPTION, ");
		//    sql.append(" A.DEPARTMENTTYPE AS DEPARTMENTTYPE, ");
		//    sql.append(" A.PARENT_ID AS PARENT_ID, ");
		//    sql.append(" B.NAME AS PARENT_NAME ");
		//    sql.append(" FROM ");
		//    sql.append(" T_DEPARTMENT A ");
		//    sql.append(" LEFT JOIN T_DEPARTMENT B ON(A.PARENT_ID=B.ID) ");
		//    sql.append(" WHERE ");
		sql.append(searchQuery);
		sql
				.append(" ORDER BY A.ORDERID ASC, A.CODE ASC, A.NAME ASC, B.NAME ASC ");
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();

			String strSQL = Global.getDialect().getLimitString(sql.toString());
			//==========================================================
			//胡勇添加，增加JSP视图列表排序功能
			strSQL = OrderList.getInstance().formatSQL(orderby, strSQL);
			//==========================================================
			pstmt = conn.prepareStatement(strSQL);
			pstmt = Global.getDialect().setStatementPageValue(pstmt, 1,
					(int) start, 2, (int) (start + count));
			//      pstmt.setLong(1, start);
			//      pstmt.setLong(2, start + count);

			rs = pstmt.executeQuery();

			for (int i = 1; rs.next(); i++) {
				dept = OrganizationFactory.getInstance().createDepartment();
				dept.setId(rs.getInt("ID"));
				dept.setCode(rs.getString("CODE"));
				dept.setName(rs.getString("NAME"));
				dept.setDescription(rs.getString("DESCRIPTION"));
				dept.setDepartmentType(rs.getString("DEPARTMENTTYPE"));
				dept.setParentDepartment(rs.getInt("PARENT_ID"));
				dept.setParentDepartmentName(rs.getString("PARENT_NAME"));

				list.add(dept);
			}
		} catch (Exception e) {
			throw new DAOException("ORG_Depart_022", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				throw new DAOException("ORG_Depart_022", e);
			}
		}
		return list;
	}

	public Department findDepartmentByID(long id) throws DAOException {
		try {
			Collection collection = this.findDepartment(" A.ID=" + id, 0, 0);
			return (Department) collection.iterator().next();
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_005", e);
		}
	}

	public Collection findDepartmentByCode(String code) throws DAOException {
		try {
			return this.findDepartment("A.Code='" + code + "' ", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_006", e);
		}

	}

	public Department findDepartmentNameByid(String id) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		Statement stmt = null;
		Department dept = null;

		String sql = "select * from T_DEPARTMENT where id = " + id;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				dept = OrganizationFactory.getInstance().createDepartment();
				dept.setName(rs.getString("name"));
			}
			return dept;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("ORG_Depart_001", e);
		} finally {
			try {
				ConnectionUtil.close(conn, stmt);
			} catch (Exception e) {
				throw new DAOException("ORG_Depart_001", e);
			}
		}

	}

	public Collection findDepartmentByName(String name) throws DAOException {
		try {
			return this.findDepartment(" A.NAME='" + name + "' ", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_006", e);
		}
	}

	public Collection getDepartments() throws DAOException {
		try {
			return this.findDepartment(" 0=0 ", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_007", e);
		}

	}

	public Collection getDepartments(long start, long count)
			throws DAOException {
		try {
			return this.findDepartment(" 0=0 ", start, count);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_008", e);
		}

	}

	public Collection getDepartmentsForPage(long id, long start, long count)
			throws DAOException {
		try {
			return this.findDepartmentsForPage(" A.PARENT_ID = " + id, start,
					count);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_022", e);
		}

	}

	public Collection getDepartmentsForPage(long start, long count)
			throws DAOException {
		try {
			return this.findDepartmentsForPage(" 0=0 ", start, count);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_022", e);
		}

	}

	public Collection getRootDepartment() throws DAOException {
		try {
			return this.findDepartment(" A.PARENT_ID=0", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_009", e);
		}

	}

	public Collection getSubDepartments(Department department)
			throws DAOException {
		try {
			return this.findDepartment(" A.PARENT_ID=" + department.getId(), 0,
					0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_010", e);
		}

	}

	public Collection getSubDepartments(Department department, long start,
			long count) throws DAOException {
		try {
			return this.findDepartment(" A.PARENT_ID=" + department.getId(),
					start, count);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_010", e);
		}

	}

	public Department getParentDepartment(Department department)
			throws DAOException {
		try {
			Collection collection = this.findDepartment(" A.ID="
					+ department.getId(), 0, 0);
			return (Department) collection.iterator().next();
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_011", e);
		}

	}

	public Collection getJobs(Department department) throws DAOException {
		try {
			String cdt = "";
			cdt = " T_JOB.DEPARTMENT_ID=" + department.getId();

			return OrganizationFactory.getInstance().getJobDAO().findJob(cdt,
					0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_012", e);
		}

	}

	public Collection getJobs(Department department, long start, long count)
			throws DAOException {
		try {
			return OrganizationFactory.getInstance().getJobDAO().findJob(
					" T_JOB.DEPARTMENT_ID=" + department.getId(), start, count);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_012", e);
		}

	}

	//==========================================================
	//胡勇添加，增加JSP视图列表排序功能
	private String[] orderby;

	public void setOrderby(String[] s) {
		this.orderby = s;
	}

	//==========================================================
	public Collection getJobsForPage(Department department, long start,
			long count) throws DAOException {
		try {
			String cdt = " T_JOB.DEPARTMENT_ID=" + department.getId();
			//==========================================================
			//胡勇添加，增加JSP视图列表排序功能
			JobDAO jobDao = OrganizationFactory.getInstance().getJobDAO();
			jobDao.setOrderby(orderby);
			return jobDao.findJobsForPage(cdt, start, count);
			//==========================================================
			/*******************************************************************
			 * return OrganizationFactory .getInstance() .getJobDAO()
			 * .findJobsForPage( cdt, start, count);
			 ******************************************************************/
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_012", e);
		}

	}

	public Collection getUnDistributeJobs() throws DAOException {
		try {
			return OrganizationFactory.getInstance().getJobDAO().findJob(
					" T_JOB.DEPARTMENT_ID=0", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_013", e);
		}

	}

	public Collection getUsers(Department department) throws DAOException {
		try {
			return OrganizationFactory.getInstance().getUserDAO().findUser(
					" ID IN (SELECT ID FROM T_DEPARTMENT_PERSON WHERE DEPARTMENT_ID="
							+ department.getId() + ")", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_014", e);
		}

	}

	public Collection getUnDistributeUsers(Department department)
			throws DAOException {
		try {
			return OrganizationFactory.getInstance().getUserDAO().findUser(
					" ID NOT IN (SELECT ID FROM T_DEPARTMENT_PERSON WHERE DEPARTMENT_ID="
							+ department.getId() + ")", 0, 0);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_015", e);
		}

	}

	public Collection getUsers(Department department, long start, long count)
			throws DAOException {
		try {
			return OrganizationFactory.getInstance().getUserDAO().findUser(
					" ID IN (SELECT ID FROM T_DEPARTMENT_PERSON WHERE DEPARTMENT_ID="
							+ department.getId() + ")", start, count);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_016", e);
		}

	}

	public Collection getUsersForPage(Department department, long start,
			long count) throws DAOException {
		try {
			//==========================================================
			//胡勇添加，增加JSP视图列表排序功能
			UserDAO userDao = OrganizationFactory.getInstance().getUserDAO();
			userDao.setOrderby(orderby);
			return userDao.findUsersForPage(
					" ID IN (SELECT ID FROM T_DEPARTMENT_PERSON WHERE DEPARTMENT_ID="
							+ department.getId() + ")", start, count);
			//==========================================================
			/*******************************************************************
			 * return OrganizationFactory .getInstance() .getUserDAO()
			 * .findUsersForPage( " ID IN (SELECT ID FROM T_DEPARTMENT_PERSON
			 * WHERE DEPARTMENT_ID=" + department.getId() + ")", start, count);
			 ******************************************************************/
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_016", e);
		}

	}

	private int getDepartmentCount(String searchQuery) throws DAOException {
		ConnectionProvider cp = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		String sql = "Select count(id) as count from T_DEPARTMENT where "
				+ searchQuery;
		try {
			cp = ConnectionProviderFactory
					.getConnectionProvider(Constants.DATA_SOURCE);
			conn = cp.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("ORG_Depart_017", e);
		} finally {
			try {
				ConnectionUtil.close(conn, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("ORG_Depart_017", e);
			}
		}
		return count;
	}

	public int getDepartmentCount(long id) throws DAOException {
		try {
			return this.getDepartmentCount(" PARENT_ID = " + id);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_018", e);
		}

	}

	public int getDepartmentCount() throws DAOException {
		try {
			return this.getDepartmentCount(" 0=0 ");
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_018", e);
		}

	}

	public int getSubDepartmentCount(Department department) throws DAOException {
		try {
			return this.getDepartmentCount(" PARENT_ID=" + department.getId());
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_019", e);
		}
	}

	public int getUserCount(Department department) throws DAOException {
		try {
			return OrganizationFactory.getInstance().getUserDepartmentDAO()
					.getUserDepartmentCount(String.valueOf(department.getId()));
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_020", e);
		}

	}

	public void removeUser(Department department, User user)
			throws DAOException {
		try {
			int[] departmentId = new int[1];
			departmentId[0] = department.getId();
			int[] userId = new int[1];
			userId[0] = user.getId();

			OrganizationFactory.getInstance().getUserDepartmentDAO()
					.deleteUserDepartment(departmentId, userId);
		} catch (DAOException e) {
			throw new DAOException("ORG_Depart_021", e);
		}

	}

}