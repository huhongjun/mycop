/*
 * Created on 2006-2-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.zj.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dfcw.zjproject.util.DBUtil;
import com.dfcw.zjproject.zj.model.InstitutionModel;
import com.dfcw.zjproject.zj.model.LearnCenterModel;
import com.dfcw.zjproject.zj.model.RecruitbatcModel;
import com.dfcw.zjproject.zj.model.StudyKindModel;
import com.dfcw.zjproject.zj.model.SubJectModel;
import com.dfcw.zjproject.zj.model.TeacherModel;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class OtherDAOImpl implements OtherDAO {

	public ArrayList getSubjects() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_SUBJECT order by name";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				SubJectModel subjectModel = new SubJectModel();
				
				subjectModel.setId(rs.getInt("ID"));
				subjectModel.setName(rs.getString("NAME"));

				list.add(subjectModel);
			}
		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return list;
	}

	public SubJectModel getSubject(int id) throws SQLException {
		SubJectModel subjectModel = new SubJectModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_SUBJECT where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				subjectModel.setId(rs.getInt("ID"));
				subjectModel.setName(rs.getString("NAME"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return subjectModel;
	}
	
	
	
	public ArrayList getStudyKinds() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_STUDYKIND order by ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StudyKindModel studykindModel = new StudyKindModel();
				
				studykindModel.setId(rs.getInt("ID"));
				studykindModel.setName(rs.getString("NAME"));

				list.add(studykindModel);
			}
		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return list;
	}

	public StudyKindModel getStudyKind(int id) throws SQLException {
		StudyKindModel studykindModel = new StudyKindModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_STUDYKIND where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				studykindModel.setId(rs.getInt("ID"));
				studykindModel.setName(rs.getString("NAME"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return studykindModel;
	}

	
	public ArrayList getRecruitbatcs() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_RECRUITBATCH order by ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				RecruitbatcModel recruitbatcModel = new RecruitbatcModel();
				
				recruitbatcModel.setId(rs.getInt("ID"));
				recruitbatcModel.setName(rs.getString("NAME"));

				list.add(recruitbatcModel);
			}
		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return list;
	}

	public RecruitbatcModel getRecruitbatc(int id) throws SQLException {
		RecruitbatcModel recruitbatcModel = new RecruitbatcModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_RECRUITBATCH where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				recruitbatcModel.setId(rs.getInt("ID"));
				recruitbatcModel.setName(rs.getString("NAME"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return recruitbatcModel;
	}
	
	
	public ArrayList getLearnCenters() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_LEARNCENTER order by ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				LearnCenterModel learncenterModel = new LearnCenterModel();
				
				learncenterModel.setId(rs.getInt("ID"));
				learncenterModel.setName(rs.getString("NAME"));

				list.add(learncenterModel);
			}
		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return list;
	}

	public LearnCenterModel getLearnCenter(int id) throws SQLException {
		LearnCenterModel learncenterModel = new LearnCenterModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_LEARNCENTER where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				learncenterModel.setId(rs.getInt("ID"));
				learncenterModel.setName(rs.getString("NAME"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return learncenterModel;
	}
	
	public ArrayList getInstitutions() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_INSTITUTION order by name";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				InstitutionModel institutionModel = new InstitutionModel();
				
				institutionModel.setId(rs.getInt("ID"));
				institutionModel.setName(rs.getString("NAME"));

				list.add(institutionModel);
			}
		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return list;
	}

	public InstitutionModel getInstitution(int id) throws SQLException {
		InstitutionModel institutionModel = new InstitutionModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_INSTITUTION where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				institutionModel.setId(rs.getInt("ID"));
				institutionModel.setName(rs.getString("NAME"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return institutionModel;
	}

	private static void closeConnection(Connection conn) throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException se) {

			throw se;
		}
	}

	private static void closeResultSet(ResultSet rs) throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException se) {
			throw se;
		}
	}

	private static void closeStatement(Statement stmt) throws SQLException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException se) {
			throw se;
		}
	}
	
	public static void main(String[] args) {
		OtherDAOImpl i = new OtherDAOImpl();
		try {
			ArrayList list = i.getSubjects();
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			SubJectModel obj = i.getSubject(10011);
			System.out.println("id=" + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList list = i.getStudyKinds();
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			StudyKindModel obj = i.getStudyKind(10011);
			System.out.println("id=" + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList list = i.getRecruitbatcs();
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			RecruitbatcModel obj = i.getRecruitbatc(10011);
			System.out.println("id=" + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList list = i.getLearnCenters();
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			LearnCenterModel obj = i.getLearnCenter(10011);
			System.out.println("id=" + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList list = i.getInstitutions();
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			InstitutionModel obj = i.getInstitution(10011);
			System.out.println("id=" + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}