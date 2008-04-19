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
import com.dfcw.zjproject.zj.model.CourseModel;
import com.dfcw.zjproject.zj.model.ExamCourseModel;
import com.dfcw.zjproject.zj.model.TeacherModel;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CourseDAOImpl implements CourseDAO {

	public ArrayList getCoursees() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_COURSE order by ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CourseModel courseModel = new CourseModel();
				
				courseModel.setID(rs.getInt("ID"));
				courseModel.setCourseID(rs.getInt("COURSE_ID"));
				courseModel.setCourseCode(rs.getString("COURSE_CODE"));
				courseModel.setCourseName(rs.getString("COURSE_NAME"));
				courseModel.setInstitutionID(rs.getInt("COURSE_ID"));
				courseModel.setRecruitbatchID(rs.getInt("MID_INSTITUTION_ID"));
				courseModel.setStudyKindID(rs.getInt("MID_RECRUITBATCH_ID"));
				courseModel.setSubjectID(rs.getInt("MID_STUDYKIND_ID"));
				courseModel.setVersion(rs.getInt("VERSION"));
				
				list.add(courseModel);
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

	public CourseModel getCourse(int id) throws SQLException {
		CourseModel courseModel = new CourseModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_COURSE where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				courseModel.setID(rs.getInt("ID"));
				courseModel.setCourseID(rs.getInt("COURSE_ID"));
				courseModel.setCourseCode(rs.getString("COURSE_CODE"));
				courseModel.setCourseName(rs.getString("COURSE_NAME"));
				courseModel.setInstitutionID(rs.getInt("COURSE_ID"));
				courseModel.setRecruitbatchID(rs.getInt("MID_INSTITUTION_ID"));
				courseModel.setStudyKindID(rs.getInt("MID_RECRUITBATCH_ID"));
				courseModel.setSubjectID(rs.getInt("MID_STUDYKIND_ID"));
				courseModel.setVersion(rs.getInt("VERSION"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return courseModel;
	}
	
	
	public ArrayList getExamCoursees() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select me.*, " + 
				"(select NAME from MID_INSTITUTION where ID=me.MID_INSTITUTION_ID)  MID_INSTITUTION_NAME," + 
				"(select NAME from MID_SUBJECT where ID=me.MID_STUDYKIND_ID)  MID_SUBJECT_NAME," +
				"(select NAME from MID_STUDYKIND where ID=me.MID_SUBJECT_ID)  MID_STUDYKIND_NAME," +
				"(select NAME from MID_RECRUITBATCH where ID=me.MID_RECRUITBATCH_ID)  MID_RECRUITBATCH_NAME" +
				" from MID_EXAMCOURSE  me order by MID_INSTITUTION_ID,MID_SUBJECT_ID,MID_STUDYKIND_ID,ID";
		
		//String sql = "select * from MID_EXAMCOURSE  me order by ID";		
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ExamCourseModel examCourseModel = new ExamCourseModel();
				examCourseModel.setID(rs.getInt("ID"));
				examCourseModel.setVersion(rs.getInt("VERSION"));
				examCourseModel.setEcId(rs.getInt("EC_ID"));
				examCourseModel.setEcName(rs.getString("EC_NAME"));
				examCourseModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				examCourseModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				examCourseModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				examCourseModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));
				examCourseModel.setMidInstitutionName(rs.getString("MID_INSTITUTION_NAME"));
				examCourseModel.setMidSubjectName(rs.getString("MID_SUBJECT_NAME"));
				examCourseModel.setMidStudykindName(rs.getString("MID_STUDYKIND_NAME"));
				examCourseModel.setMidRecruitbatchName(rs.getString("MID_RECRUITBATCH_NAME"));
				
				System.out.println("ec name ===="   + examCourseModel.getEcName());									
				System.out.println("instituion name ===="   + examCourseModel.getMidInstitutionName());
				System.out.println("Subject name ===="   + examCourseModel.getMidSubjectName());				
				list.add(examCourseModel);
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

	public ArrayList getExamCoursees(int studykind,int subject,int institution,int learncenter,int recruitbatch) throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select me.*, " + 
				"(select NAME from MID_INSTITUTION where ID=me.MID_INSTITUTION_ID)  MID_INSTITUTION_NAME," + 
				"(select NAME from MID_SUBJECT where ID=me.MID_STUDYKIND_ID)  MID_SUBJECT_NAME," +
				"(select NAME from MID_STUDYKIND where ID=me.MID_SUBJECT_ID)  MID_STUDYKIND_NAME," +
				"(select NAME from MID_RECRUITBATCH where ID=me.MID_RECRUITBATCH_ID)  MID_RECRUITBATCH_NAME" +
				" from MID_EXAMCOURSE  me where 1>0";
		/**
		 * 在MID_EXAMCOURSE表中MID_STUDYKIND_ID和MID_SUBJECT_ID数据有问题，两个字段中的数据应该调换一下．
		 */
//		if(studykind!=0){
//			sql = sql + " and MID_STUDYKIND_ID="+studykind;
//		}
//		if(subject!=0){
//			sql = sql + " and MID_SUBJECT_ID="+subject;
//		}
		
		if(subject!=0){
			sql = sql + " and MID_STUDYKIND_ID="+subject;
		}
		if(studykind!=0){
			sql = sql + " and MID_SUBJECT_ID="+studykind;
		}
		if(institution!=0){
			sql = sql + " and MID_INSTITUTION_ID="+institution;
		}
//		if(learncenter!=0){
//			sql = sql + " and MID_LEARNCENTER_ID="+learncenter;
//		}
		if(recruitbatch!=0){
			sql = sql + " and MID_RECRUITBATCH_ID="+recruitbatch;
		}
		sql = sql + " order by MID_INSTITUTION_ID,MID_SUBJECT_ID,MID_STUDYKIND_ID,ID";		
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ExamCourseModel examCourseModel = new ExamCourseModel();
				examCourseModel.setID(rs.getInt("ID"));
				examCourseModel.setVersion(rs.getInt("VERSION"));
				examCourseModel.setEcId(rs.getInt("EC_ID"));
				examCourseModel.setEcName(rs.getString("EC_NAME"));
				examCourseModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				examCourseModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				examCourseModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				examCourseModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));
				examCourseModel.setMidInstitutionName(rs.getString("MID_INSTITUTION_NAME"));
				examCourseModel.setMidSubjectName(rs.getString("MID_SUBJECT_NAME"));
				examCourseModel.setMidStudykindName(rs.getString("MID_STUDYKIND_NAME"));
				examCourseModel.setMidRecruitbatchName(rs.getString("MID_RECRUITBATCH_NAME"));
				
				System.out.println("ec name ===="   + examCourseModel.getEcName());									
				System.out.println("instituion name ===="   + examCourseModel.getMidInstitutionName());
				System.out.println("Subject name ===="   + examCourseModel.getMidSubjectName());				
				list.add(examCourseModel);
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
	
	public ExamCourseModel getExamCourse(int id) throws SQLException {
		ExamCourseModel examCourseModel = new ExamCourseModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_COURSE where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				examCourseModel.setID(rs.getInt("ID"));
				examCourseModel.setVersion(rs.getInt("VERSION"));
				examCourseModel.setEcId(rs.getInt("EC_ID"));
				examCourseModel.setEcName(rs.getString("EC_NAME"));
				examCourseModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				examCourseModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				examCourseModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				examCourseModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return examCourseModel;
	}

	public ArrayList getExamCourse(int MID_INSTITUTION_ID, int MID_RECRUITBATCH_ID, int MID_STUDYKIND_ID, int MID_SUBJECT_ID) throws SQLException {
		ArrayList list = new ArrayList();
		

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		/**
		 * 在MID_EXAMCOURSE表中MID_STUDYKIND_ID和MID_SUBJECT_ID数据有问题，两个字段中的数据应该调换一下．
		 */
//		" and MID_STUDYKIND_ID=" + MID_STUDYKIND_ID +
//		" and MID_SUBJECT_ID=" + MID_SUBJECT_ID;
		String sql = "select * from MID_EXAMCOURSE where MID_INSTITUTION_ID=" + MID_INSTITUTION_ID +
				" and MID_RECRUITBATCH_ID=" + MID_RECRUITBATCH_ID +
				" and MID_STUDYKIND_ID=" + MID_SUBJECT_ID +
				" and MID_SUBJECT_ID=" + MID_STUDYKIND_ID;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ExamCourseModel examCourseModel = new ExamCourseModel();
				examCourseModel.setID(rs.getInt("ID"));
				examCourseModel.setVersion(rs.getInt("VERSION"));
				examCourseModel.setEcId(rs.getInt("EC_ID"));
				examCourseModel.setEcName(rs.getString("EC_NAME"));
				examCourseModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				examCourseModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				examCourseModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				examCourseModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));
				list.add(examCourseModel);
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
		CourseDAOImpl i = new CourseDAOImpl();
		try {
//			ArrayList list = i.getExamCourse(1,2,3,4);
			ArrayList list = i.getExamCourse(10278,34529,17972,17979);
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			CourseModel obj = i.getCourse(5885);
			System.out.println("id=" + obj.getID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}