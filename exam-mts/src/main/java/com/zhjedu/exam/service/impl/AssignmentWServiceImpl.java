package com.zhjedu.exam.service.impl;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.manager.IAssignmentManager;
import com.zhjedu.exam.manager.IAssignmentWManager;
import com.zhjedu.exam.manager.impl.AssignmentWManager;
import com.zhjedu.exam.service.IAssignmentWService;
import com.zhjedu.util.PageObject;

public class AssignmentWServiceImpl implements
		IAssignmentWService {
	
	private static final Log log = LogFactory.getLog(AssignmentServiceImpl.class);

	private IAssignmentWManager assignmentWManager;
	
	

	public IAssignmentWManager getAssignmentWManager() {
		return assignmentWManager;
	}

	public void setAssignmentWManager(IAssignmentWManager assignmentWManager) {
		this.assignmentWManager = assignmentWManager;
	}

	public boolean saveAssignment(ZjQuiz quiz){
		assignmentWManager.saveAssignment(quiz);
		return true;
	}
	
	public ArrayList getAssignment(String sql) {
		return assignmentWManager.getAssignment(sql);
		
	}

	public PageObject getAssignmentObject(String userid, String status, String currentPage) {
		// TODO Auto-generated method stub
		return assignmentWManager.getAssignmentObject(userid, status, currentPage);
	}

	public String removeQuestion(String[] questionid) {
		// TODO Auto-generated method stub
		return assignmentWManager.removeQuestion(questionid);
	}

	public ZjQuiz getQuiz(String questionid) {
		// TODO Auto-generated method stub
		return assignmentWManager.getQuiz(questionid);
		
	}

}
