/*
 * Created on 2006-2-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.zj.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dfcw.zjproject.zj.model.TeacherModel;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface TeacherDAO {


	public ArrayList getTeachers() throws SQLException;

	public TeacherModel getTeacher(int id) throws SQLException;	
	
	public ArrayList getTeacherCourses(String teacherid) throws SQLException;	
	
	public TeacherModel getTeacher(String name) throws SQLException ;

}
