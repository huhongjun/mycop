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
public class CourseScoreModel implements Serializable{
	private int ID;
    private int version;		
    private int studentID;	 
    private int courseID;	    
    private int jobState;
    private int timeState;    
    private int jobScore;   
    private int examScore;       
    private int examState; 
    private int examKind;
    
    
	public CourseScoreModel() {
		super();
	}
	public CourseScoreModel(int id, int version, int studentID, int courseID, int jobState, int timeState, int jobScore, int examScore, int examState, int examKind) {
		super();
		ID = id;
		this.version = version;
		this.studentID = studentID;
		this.courseID = courseID;
		this.jobState = jobState;
		this.timeState = timeState;
		this.jobScore = jobScore;
		this.examScore = examScore;
		this.examState = examState;
		this.examKind = examKind;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getExamKind() {
		return examKind;
	}
	public void setExamKind(int examKind) {
		this.examKind = examKind;
	}
	public int getExamScore() {
		return examScore;
	}
	public void setExamScore(int examScore) {
		this.examScore = examScore;
	}
	public int getExamState() {
		return examState;
	}
	public void setExamState(int examState) {
		this.examState = examState;
	}
	public int getID() {
		return ID;
	}
	public void setID(int id) {
		ID = id;
	}
	public int getJobScore() {
		return jobScore;
	}
	public void setJobScore(int jobScore) {
		this.jobScore = jobScore;
	}
	public int getJobState() {
		return jobState;
	}
	public void setJobState(int jobState) {
		this.jobState = jobState;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getTimeState() {
		return timeState;
	}
	public void setTimeState(int timeState) {
		this.timeState = timeState;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}     

}
