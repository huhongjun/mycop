/*
 * Created on 2006-2-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.dfcw.zjproject.zj.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.dfcw.zjproject.zj.model.InstitutionModel;
import com.dfcw.zjproject.zj.model.LearnCenterModel;
import com.dfcw.zjproject.zj.model.RecruitbatcModel;
import com.dfcw.zjproject.zj.model.StudyKindModel;
import com.dfcw.zjproject.zj.model.SubJectModel;

/**
 * @author MI_Viewer
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public interface OtherDAO {


	public ArrayList getSubjects() throws SQLException;
	

	public SubJectModel getSubject(int id) throws SQLException;	
	

	public ArrayList getStudyKinds() throws SQLException;
	

	public StudyKindModel getStudyKind(int id) throws SQLException;	
	
	
	public ArrayList getRecruitbatcs() throws SQLException;
	

	public RecruitbatcModel getRecruitbatc(int id) throws SQLException;		
	
	
	public ArrayList getLearnCenters() throws SQLException;
	

	public LearnCenterModel getLearnCenter(int id) throws SQLException;		
	
	
	public ArrayList getInstitutions() throws SQLException;
	

	public InstitutionModel getInstitution(int id) throws SQLException;		
	
}
