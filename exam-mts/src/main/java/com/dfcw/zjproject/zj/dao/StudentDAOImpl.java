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
import com.dfcw.zjproject.zj.model.ExamCourseModel;
import com.dfcw.zjproject.zj.model.StudentModel;
import com.dfcw.zjproject.zj.model.TeacherModel;
import com.zhjedu.util.StringUtil;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class StudentDAOImpl implements StudentDAO {

	public ArrayList getStudents() throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_STUDENT order by ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StudentModel studentModel = new StudentModel();
				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));

				list.add(studentModel);
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
	
	public ArrayList getStudents(String course) throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select s.* from MID_STUDENT s,MID_CHOICE_COURSE c where c.MID_COURSE_COURSE_ID='"+course+"' and s.STUDENT_ID=c.MID_STUDENT_STUDENT_ID order by s.ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StudentModel studentModel = new StudentModel();
				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));

				list.add(studentModel);
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
	
	

	public StudentModel getStudent(int id) throws SQLException {
		StudentModel studentModel = new StudentModel();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_STUDENT where STUDENT_ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return studentModel;
	}
	
	public StudentModel getStudentBySID(int stuid) throws SQLException {
		StudentModel studentModel = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_STUDENT where STUDENT_ID="+ stuid ;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				studentModel = new StudentModel();
				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return studentModel;
	}
	
	
	public StudentModel getStudent(String USER_NAME) throws SQLException {
		StudentModel studentModel = null;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_STUDENT where USER_NAME='" + USER_NAME + "'";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				studentModel = new StudentModel();
				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return studentModel;
	}
	
	
	/**
	 * 0表示不存在,1表示存在
	 */
	public int checkStudent(int id) throws SQLException{
		int i = 0;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_STUDENT where ID=" + id;
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				i = 1;
			}

		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}
		
		return i;
	}
	
	public ArrayList getEntraceExamStudent(int id) throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "select stu.* from MID_STUDENT stu, MID_EXAMCOURSE exam " +
				"where stu.MID_INSTITUTION_ID=exam.MID_INSTITUTION_ID " +
				"and stu.MID_RECRUITBATCH_ID=exam.MID_RECRUITBATCH_ID " +
				"and stu.MID_STUDYKIND_ID=exam.MID_STUDYKIND_ID " +
				"and stu.MID_SUBJECT_ID=exam.MID_SUBJECT_ID " +
				"and exam.ID=" + id + " and stu.STATE=2" + 
				" order by stu.ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StudentModel studentModel = new StudentModel();
				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));

				list.add(studentModel);
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
	
	public ArrayList getCourseExamStudent(int id) throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select stu.* from MID_STUDENT stu, MID_EXAMCOURSE exam " +
		"where stu.MID_INSTITUTION_ID=exam.MID_INSTITUTION_ID " +
		"and stu.MID_RECRUITBATCH_ID=exam.MID_RECRUITBATCH_ID " +
		"and stu.MID_STUDYKIND_ID=exam.MID_SUBJECT_ID " +
		"and stu.MID_SUBJECT_ID=exam.MID_STUDYKIND_ID " +
		"and exam.ID=" + id + " and stu.STATE=2" + 
		" order by stu.ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StudentModel studentModel = new StudentModel();
				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));

				list.add(studentModel);
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
		StudentDAOImpl i = new StudentDAOImpl();
		try {
			ArrayList list = i.getStudents();
//			StudentModel obj = (StudentModel)list.get(0);
//			System.out.println(obj.getId());
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			StudentModel obj = i.getStudent(8087);
			System.out.println("id=" + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			int x = i.checkStudent(8087);
			if(x == 0){
				System.out.println("No Body");
			}else if(x == 1){
				System.out.println("Have Body");
			}else{
				System.out.println("Check Student Error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList list = i.getEntraceExamStudent(8087);
			System.out.println("list.size()=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ArrayList list = i.getCourseExamStudent(10220);
			System.out.println("list.size()111=" + list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList getStudentsByCourse(String course) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select s.* from MID_STUDENT s,MID_CHOICE_COURSE c where c.MID_COURSE_COURSE_ID='"+course+"' and s.STUDENT_ID=c.MID_STUDENT_STUDENT_ID order by s.ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StudentModel studentModel = new StudentModel();
				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));

				list.add(studentModel);
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
	
	public ArrayList getStudents(int studykind,int subject,int institution,int learncenter,int recruitbatch ) throws SQLException {
		ArrayList list = new ArrayList();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select * from MID_STUDENT where 1>0";
		if(studykind!=0){
			sql = sql + " and MID_STUDYKIND_ID="+studykind;
		}
		if(subject!=0){
			sql = sql + " and MID_SUBJECT_ID="+subject;
		}
		if(institution!=0){
			sql = sql + " and MID_INSTITUTION_ID="+institution;
		}
		if(learncenter!=0){
			sql = sql + " and MID_LEARNCENTER_ID="+learncenter;
		}
		if(recruitbatch!=0){
			sql = sql + " and MID_RECRUITBATCH_ID="+recruitbatch;
		}
		sql = sql + " order by ID";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				StudentModel studentModel = new StudentModel();
				studentModel.setId(rs.getInt("ID"));
				studentModel.setVersion(rs.getInt("VERSION"));
				studentModel.setStudentId(rs.getInt("STUDENT_ID"));
				studentModel.setUserName(rs.getString("USER_NAME"));
				studentModel.setRealName(rs.getString("REAL_NAME"));
				studentModel.setSex(rs.getString("SEX"));
				studentModel.setCertNo(rs.getString("CERT_NO"));
				studentModel.setExamNo(rs.getString("EXAM_NO"));
				studentModel.setEntryNo(rs.getString("ENTRY_NO"));
				studentModel.setState(rs.getInt("STATE"));
				studentModel.setExambatchId(rs.getInt("EXAMBATCH_ID"));
				studentModel.setMidInstitutionId(rs.getInt("MID_INSTITUTION_ID"));
				studentModel.setMidLearncenterId(rs.getInt("MID_LEARNCENTER_ID"));
				studentModel.setMidRecruitbatchId(rs.getInt("MID_RECRUITBATCH_ID"));
				studentModel.setMidStudykindId(rs.getInt("MID_STUDYKIND_ID"));
				studentModel.setMidSubjectId(rs.getInt("MID_SUBJECT_ID"));

				list.add(studentModel);
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
	public int getStudentsChooseCount(String course) throws SQLException {
		int count = 0;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "select s.* from MID_STUDENT s,MID_CHOICE_COURSE c where c.MID_COURSE_COURSE_ID='"+course+"' and s.STUDENT_ID=c.MID_STUDENT_STUDENT_ID order by s.ID";
		
		System.out.println("sql=" + sql);

		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = count + 1;
			}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	public int getStudentsOughtCount(String quizid,String strValue) throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int count = 0;
		String[] ecs = null;
		CourseDAO cdao =  new CourseDAOImpl();
		if(strValue!=null){
			ecs = StringUtil.split(strValue, ",");
		}
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			
			for(int i=0;i<ecs.length;i++){
				ExamCourseModel tmp = cdao.getExamCourse(Integer.parseInt(ecs[i]));
				String sql = "select count(*) from MID_STUDENT s where MID_STUDYKIND_ID=" + tmp.getMidStudykindId() + " and MID_SUBJECT_ID="+tmp.getMidSubjectId()+" and MID_INSTITUTION_ID="+tmp.getMidInstitutionId()+" and MID_RECRUITBATCH_ID="+tmp.getMidRecruitbatchId();
				rs = stmt.executeQuery(sql);
				rs.next();
				count = count + rs.getInt(1);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}