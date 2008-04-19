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
public class StudentModel implements Serializable{
//	  ID              NUMBER(19) not null,
//	  VERSION         NUMBER(19) not null,
//	  STUDENT_ID      NUMBER(19) not null,知金库学生ID
//	  USER_NAME       VARCHAR2(50) not null,用户名
//	  REAL_NAME       VARCHAR2(50) not null,姓名
//	  SEX             VARCHAR2(4) not null,性别
//	  CERT_NO         VARCHAR2(40) not null,证件号
//	  EXAM_NO         VARCHAR2(40) not null,准考证号
//	  ENTRY_NO        VARCHAR2(40) not null,报名号
//	  STATE           NUMBER(2) not null,状态
//										2-----可以参加入学考试
//										3、4-----可以参加课程学习
//	  EXAMBATCH_ID    NUMBER(19) not null,知金库入学考试批次ID
//	  MID_INSTITUTION_ID  NUMBER(19) not null,网院ID
//	  MID_LEARNCENTER_ID  NUMBER(19) not null,学习中心ID
//	  MID_RECRUITBATCH_ID NUMBER(19) not null,入学批次ID
//	  MID_STUDYKIND_ID    NUMBER(19) not null,培养层次ID
//	  MID_SUBJECT_ID      NUMBER(19) not null专业ID
	private int id;
	private int version;
	private int studentId;
    private String userName;       
    private String realName;	
    private String sex;	
    private String certNo;
    private String examNo;
    private String entryNo;
    private int state;    
    private int exambatchId;
    private int midInstitutionId;    
    private int midLearncenterId; 
    private int midRecruitbatchId; 
    private int midStudykindId; 
    private int midSubjectId;
    
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getEntryNo() {
		return entryNo;
	}
	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}
	public int getExambatchId() {
		return exambatchId;
	}
	public void setExambatchId(int exambatchId) {
		this.exambatchId = exambatchId;
	}
	public String getExamNo() {
		return examNo;
	}
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMidInstitutionId() {
		return midInstitutionId;
	}
	public void setMidInstitutionId(int midInstitutionId) {
		this.midInstitutionId = midInstitutionId;
	}
	public int getMidLearncenterId() {
		return midLearncenterId;
	}
	public void setMidLearncenterId(int midLearncenterId) {
		this.midLearncenterId = midLearncenterId;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	} 

    
}
