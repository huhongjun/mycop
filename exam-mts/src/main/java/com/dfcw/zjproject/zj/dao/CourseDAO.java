/*
 * Created on 2006-2-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.zj.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dfcw.zjproject.zj.model.CourseModel;
import com.dfcw.zjproject.zj.model.ExamCourseModel;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface CourseDAO {

    //取出所有课程信息
	public ArrayList getCoursees() throws SQLException;
	
	//依据课程ID获取课程详细信息
	public CourseModel getCourse(int id) throws SQLException;
	
	//根据网院ID、入学批次ID、培养层次ID、专业ID获取入学科目列表
	public ArrayList getExamCourse(int MID_INSTITUTION_ID, int MID_RECRUITBATCH_ID, int MID_STUDYKIND_ID, int MID_SUBJECT_ID) throws SQLException;

    //取出所有入学课目信息
	public ArrayList getExamCoursees() throws SQLException;
	
	//依据课程ID获取入学科目详细信息
	public ExamCourseModel getExamCourse(int id) throws SQLException;

	public ArrayList getExamCoursees(int studykind,int subject,int institution,int learncenter,int recruitbatch) throws SQLException ;
}
