/**
 * EquipmentForm.java.
 * User: Fengch  Date: 2007-1-4 0:19:29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.dfcw.zjproject.zj.form;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

import javax.servlet.http.HttpServletRequest;

public class CourseForm extends ValidatorActionForm
{
	private int ID;
    private String version;	
    private int courseID;
    private String courseCode;	
    private String courseName;	 
    private int institutionID;  
    private int recruitbatchID;  
    private int studyKindID;      
    private int subjectID;
    
    
	public CourseForm() {
		super();
	}
	public CourseForm(int id, String version, int courseID, String courseCode, String courseName, int institutionID, int recruitbatchID, int studyKindID, int subjectID) {
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}
