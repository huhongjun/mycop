/*
 * Created on 2006-2-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.zj.model;

import java.io.Serializable;

/**
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CourseTeacherModel implements Serializable{
	private int ID;
    private int version;	
    private int courseID;
    private int teacherID;
         
    private int candtID;
    
    
	public CourseTeacherModel() {
		super();
	}

	public CourseTeacherModel(int id, int version, int courseID, int teacherID, int candtID) {
		super();
		ID = id;
		this.version = version;
		this.courseID = courseID;
		this.teacherID = teacherID;
		this.candtID = candtID;
		
	}

	public int getCandtID() {
		return candtID;
	}

	public void setCandtID(int candtID) {
		this.candtID = candtID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	

}
