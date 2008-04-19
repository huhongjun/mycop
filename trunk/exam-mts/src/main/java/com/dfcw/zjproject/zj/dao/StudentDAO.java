/*
 * Created on 2006-2-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.zj.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dfcw.zjproject.zj.model.StudentModel;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface StudentDAO {

    //获取所有学生列表
	public ArrayList getStudents() throws SQLException;
	
//	获取学习某课程的所有学生列表
	public ArrayList getStudentsByCourse(String course) throws SQLException;
	
    //获取单个学生详细信息
	public StudentModel getStudent(int id) throws SQLException;	
	
//	获取单个学生详细信息
	public StudentModel getStudent(String USER_NAME) throws SQLException;	
	
	//判断学生是否存在
	public int checkStudent(int id) throws SQLException;
	
	//获取参加某入学科目考试的考生名单
	//入参为入学考试科目的ID
	public ArrayList getEntraceExamStudent(int id) throws SQLException;		
	
	//获取参加某课程考试的学生名单
	//入参为课程的ID	
	public ArrayList getCourseExamStudent(int id) throws SQLException;		
	
	public StudentModel getStudentBySID(int stuid) throws SQLException ;
	
	public ArrayList getStudents(String course) throws SQLException;
	
	public int getStudentsChooseCount(String course) throws SQLException;
	
	public ArrayList getStudents(int studykind,int subject,int institution,int learncenter,int recruitbatch ) throws SQLException;
	
	public int getStudentsOughtCount(String quizid,String ecs) throws SQLException;
}
