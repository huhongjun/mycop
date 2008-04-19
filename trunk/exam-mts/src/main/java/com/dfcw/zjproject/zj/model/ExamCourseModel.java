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
public class ExamCourseModel implements Serializable{

	private int ID;
    private int version;	
    private int ecId;
    private String ecName;	 
    private String allName;	     
    private int midInstitutionId;  
    private int midRecruitbatchId;  
    private int midStudykindId;
    private int midSubjectId;
    
    private String midInstitutionName;	 
    private String midRecruitbatchName;	     
    private String midStudykindName;	     
    private String midSubjectName;	     
    
	public ExamCourseModel() {
		super();
	}

	public ExamCourseModel(int id, int version, int ecId, String ecName, String allName, int midInstitutionId, int midRecruitbatchId, int midStudykindId, int midSubjectId, String midInstitutionName, String midRecruitbatchName, String midStudykindName, String midSubjectName) {
		super();
		ID = id;
		this.version = version;
		this.ecId = ecId;
		this.ecName = ecName;
		this.allName = allName;
		this.midInstitutionId = midInstitutionId;
		this.midRecruitbatchId = midRecruitbatchId;
		this.midStudykindId = midStudykindId;
		this.midSubjectId = midSubjectId;
		this.midInstitutionName = midInstitutionName;
		this.midRecruitbatchName = midRecruitbatchName;
		this.midStudykindName = midStudykindName;
		this.midSubjectName = midSubjectName;
	}






	public int getEcId() {
		return ecId;
	}

	public void setEcId(int ecId) {
		this.ecId = ecId;
	}

	public String getEcName() {
		return ecName;
	}

	public void setEcName(String ecName) {
		this.ecName = ecName;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	public int getMidInstitutionId() {
		return midInstitutionId;
	}

	public void setMidInstitutionId(int midInstitutionId) {
		this.midInstitutionId = midInstitutionId;
	}

	public int getMidRecruitbatchId() {
		return midRecruitbatchId;
	}

	public void setMidRecruitbatchId(int midRecruitbatchId) {
		this.midRecruitbatchId = midRecruitbatchId;
	}

	public int getMidStudykindId() {
		return midStudykindId;
	}

	public void setMidStudykindId(int midStudykindId) {
		this.midStudykindId = midStudykindId;
	}

	public int getMidSubjectId() {
		return midSubjectId;
	}

	public void setMidSubjectId(int midSubjectId) {
		this.midSubjectId = midSubjectId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getMidInstitutionName() {
		return midInstitutionName;
	}

	public void setMidInstitutionName(String midInstitutionName) {
		this.midInstitutionName = midInstitutionName;
	}

	public String getMidRecruitbatchName() {
		return midRecruitbatchName;
	}

	public void setMidRecruitbatchName(String midRecruitbatchName) {
		this.midRecruitbatchName = midRecruitbatchName;
	}

	public String getMidStudykindName() {
		return midStudykindName;
	}

	public void setMidStudykindName(String midStudykindName) {
		this.midStudykindName = midStudykindName;
	}

	public String getMidSubjectName() {
		return midSubjectName;
	}

	public void setMidSubjectName(String midSubjectName) {
		this.midSubjectName = midSubjectName;
	}



	public String getAllName() {
		return allName;
	}



	public void setAllName(String allName) {
		this.allName = allName;
	}

}
