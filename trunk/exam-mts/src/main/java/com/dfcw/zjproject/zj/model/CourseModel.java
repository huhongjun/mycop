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
public class CourseModel implements Serializable{
	private int ID;
    private int version;	
    private int courseID;
    private String courseCode;	
    private String courseName;	 
    private int institutionID;  
    private int recruitbatchID;  
    private int studyKindID;      
    private int subjectID;
    
    
	public CourseModel() {
		super();
	}

	public CourseModel(int id, int version, int courseID, String courseCode, String courseName, int institutionID, int recruitbatchID, int studyKindID, int subjectID) {
		super();
		ID = id;
		this.version = version;
		this.courseID = courseID;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.institutionID = institutionID;
		this.recruitbatchID = recruitbatchID;
		this.studyKindID = studyKindID;
		this.subjectID = subjectID;
	}

	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public int getInstitutionID() {
		return institutionID;
	}
	public void setInstitutionID(int institutionID) {
		this.institutionID = institutionID;
	}
	public int getRecruitbatchID() {
		return recruitbatchID;
	}
	public void setRecruitbatchID(int recruitbatchID) {
		this.recruitbatchID = recruitbatchID;
	}
	public int getStudyKindID() {
		return studyKindID;
	}
	public void setStudyKindID(int studyKindID) {
		this.studyKindID = studyKindID;
	}
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
