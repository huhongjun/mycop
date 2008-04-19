/*
 * Created on 2006-2-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.zj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dfcw.zjproject.util.DBUtil;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScoreDAOImpl implements ScoreDAO {

	public void addEntraceScore(int courseid,int studentID,float score) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "insert into MID_ENTRANCE_SCORE(ID, VERSION, MID_STUDENT_ID, MID_EXAMCOURSE_ID, SCORE, STATE) values(midseq.nextval, 1, " + studentID + ", " + courseid + ", " + score + ", 1)";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.execute();
		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
	}

	public void addCourseScore(int courseid,int studentID,float score) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "insert into MID_JOB_SCORE(ID, VERSION, MID_STUDENT_ID, MID_COURSE_ID, EXAM_SCORE) values(midseq.nextval, 1, " + studentID + ", " + courseid + ", " + score + ")";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.execute();
		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
	}
	
	public void add() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		ID              NUMBER(19) not null,
//		  VERSION         NUMBER(19) not null,
//		  STUDENT_ID      NUMBER(19) not null,知金库学生ID
//		  USER_NAME       VARCHAR2(50) not null,用户名
//		  REAL_NAME       VARCHAR2(50) not null,姓名
//		  SEX             VARCHAR2(4) not null,性别
//		  CERT_NO         VARCHAR2(40) not null,证件号
//		  EXAM_NO         VARCHAR2(40) not null,准考证号
//		  ENTRY_NO        VARCHAR2(40) not null,报名号
//		  STATE           NUMBER(2) not null,状态
//											2-----可以参加入学考试
//											3、4-----可以参加课程学习
//		  EXAMBATCH_ID    NUMBER(19) not null,知金库入学考试批次ID
//		  MID_INSTITUTION_ID  NUMBER(19) not null,网院ID
//		  MID_LEARNCENTER_ID  NUMBER(19) not null,学习中心ID
//		  MID_RECRUITBATCH_ID NUMBER(19) not null,入学批次ID
//		  MID_STUDYKIND_ID    NUMBER(19) not null,培养层次ID
//		  MID_SUBJECT_ID      NUMBER(19) not null专业ID

		String sql = "insert into MID_STUDENT(ID, VERSION, STUDENT_ID, USER_NAME, REAL_NAME, sex, CERT_NO, EXAM_NO, ENTRY_NO, " +
				"STATE, EXAMBATCH_ID,MID_INSTITUTION_ID,MID_LEARNCENTER_ID,MID_RECRUITBATCH_ID,MID_STUDYKIND_ID,MID_SUBJECT_ID) values(midseq.nextval," +
				"1,198777,'test','testname','1','123456','123456','123456',2,1,10278,10309,17969,17975,17972)";
		System.out.println("sql=" + sql);
		try {
			conn = DBUtil.getConnection();
		    ps = conn.prepareStatement(sql);
		    ps.execute();
		} catch (SQLException se) {
			throw se;
		}

		finally {
			closeResultSet(rs);
			closeStatement(ps);
			closeConnection(conn);
		}
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
	
	private static void closeStatement(PreparedStatement ps) throws SQLException {
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException se) {
			throw se;
		}
	}
	
	public static void main(String[] args) {
		ScoreDAOImpl i = new ScoreDAOImpl();
//		try {
//			i.add();
//			System.out.println("Insert Success");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			i.addCourseScore(4, 5, 6);
//			System.out.println("Insert Success");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
}