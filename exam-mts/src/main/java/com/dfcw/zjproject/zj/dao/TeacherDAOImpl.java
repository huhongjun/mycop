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
import com.dfcw.zjproject.zj.model.CourseTeacherModel;
import com.dfcw.zjproject.zj.model.TeacherModel;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TeacherDAOImpl implements TeacherDAO {

	public ArrayList getTeacherCourses(String teacherid) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_COURSE_TEACHER where MID_TEACHER_TEACHER_ID='"+teacherid+"' order by ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CourseTeacherModel ctModel = new CourseTeacherModel();
				
				ctModel.setID(rs.getInt("ID"));
				ctModel.setVersion(rs.getInt("VERSION"));
				ctModel.setTeacherID(rs.getInt("MID_TEACHER_TEACHER_ID"));
				ctModel.setCourseID(rs.getInt("MID_COURSE_COURSE_ID"));
				ctModel.setCandtID(rs.getInt("ZHJ_CANDT_ID"));

				list.add(ctModel);
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

	public ArrayList getTeachers() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_TEACHER order by ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				TeacherModel teacherModel = new TeacherModel();
				
				teacherModel.setId(rs.getInt("ID"));
				teacherModel.setVersion(rs.getInt("VERSION"));
				teacherModel.setTeacherId(rs.getInt("TEACHER_ID"));
				teacherModel.setUserName(rs.getString("USER_NAME"));
				teacherModel.setRealName(rs.getString("REAL_NAME"));

				list.add(teacherModel);
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

	public TeacherModel getTeacher(int id) throws SQLException {
		TeacherModel teacherModel = new TeacherModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_TEACHER where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				teacherModel.setId(rs.getInt("ID"));
				teacherModel.setVersion(rs.getInt("VERSION"));
				teacherModel.setTeacherId(rs.getInt("TEACHER_ID"));
				teacherModel.setUserName(rs.getString("USER_NAME"));
				teacherModel.setRealName(rs.getString("REAL_NAME"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return teacherModel;
	}
	
	public TeacherModel getTeacher(String name) throws SQLException {
		TeacherModel teacherModel = new TeacherModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_TEACHER where USER_NAME='" + name + "'";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				teacherModel.setId(rs.getInt("ID"));
				teacherModel.setVersion(rs.getInt("VERSION"));
				teacherModel.setTeacherId(rs.getInt("TEACHER_ID"));
				teacherModel.setUserName(rs.getString("USER_NAME"));
				teacherModel.setRealName(rs.getString("REAL_NAME"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return teacherModel;
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
		TeacherDAOImpl i = new TeacherDAOImpl();
		try {
			ArrayList list = i.getTeachers();
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			TeacherModel obj = i.getTeacher(10011);
			
			System.out.println("id=" + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}