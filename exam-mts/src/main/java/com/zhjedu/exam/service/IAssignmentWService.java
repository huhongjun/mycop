package com.zhjedu.exam.service;

import java.util.ArrayList;

import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.util.PageObject;

public interface IAssignmentWService {

	public boolean saveAssignment(ZjQuiz quiz);
	
	public ArrayList getAssignment(String sql) ;
	
	public String removeQuestion(String[] questionid);
	
	public ZjQuiz getQuiz(String questionid);
	
	public PageObject getAssignmentObject(String userid, String status, String currentPage);
}
