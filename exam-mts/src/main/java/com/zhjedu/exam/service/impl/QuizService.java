package com.zhjedu.exam.service.impl;

import java.util.List;
import java.util.Map;

import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.domain.ZjQuizExam;
import com.zhjedu.exam.domain.ZjQuizMidEc;
import com.zhjedu.exam.manager.IQuizManager;
import com.zhjedu.exam.service.IQuizService;
import com.zhjedu.util.PageObject;

public class QuizService implements IQuizService {

	public ZjQuizMidEc getZjQuizMidEc(String quiz, String ec) {
		// TODO Auto-generated method stub
		return quizManager.getZjQuizMidEc(quiz, ec);
	}
	public List getZjQuizMidEcList(String quizid) {
		// TODO Auto-generated method stub
		return quizManager.getZjQuizMidEcList(quizid);
	}
	public int getQuizExamCount(String courseid,String quizType, String status) {
		// TODO Auto-generated method stub
		return quizManager.getQuizExamCount(courseid,quizType, status);
	}
	public PageObject getQuizExamList(String courseid,String quizid, String quizType,String status, String currentPage) {
		// TODO Auto-generated method stub
		return quizManager.getQuizExamList(courseid,quizid,quizType, status, currentPage);
	}
	public String removeQuizQuestion(String[] questions){
		return quizManager.removeQuizQuestion( questions);
	}
	public String saveQuizQuestions(String[] questions, String quiz){
		return quizManager.saveQuizQuestions(questions, quiz);
	}
	public List getQuizQuestionList(String quiz){
		return quizManager.getQuizQuestionList(quiz);
	}
	public List getAllUsers() {
		// TODO Auto-generated method stub
		return quizManager.getAllUsers();
	}

	public String saveQuizUsers(String[] users, String quiz) {
		// TODO Auto-generated method stub
		return quizManager.saveQuizUsers(users, quiz);
	}
	
	public String saveQuizECs(String[] ecs, String quiz) {
		// TODO Auto-generated method stub
		return quizManager.saveQuizECs(ecs, quiz);
	}

	public List getCourseList() {
		// TODO Auto-generated method stub
		return quizManager.getCourseList();
	}

	private IQuizManager quizManager;
	public PageObject getQuizList(Map map, String currentPage) {
		// TODO Auto-generated method stub
		return quizManager.getQuizList(map, currentPage);
	}

	public ZjQuiz getZjQuiz(String quizid) {
		// TODO Auto-generated method stub
		return quizManager.getZjQuiz(quizid);
	}

	public String removeQuiz(String[] quizid) {
		// TODO Auto-generated method stub
		return quizManager.removeQuiz(quizid);
	}

	public String saveQuiz(ZjQuiz quiz) {
		// TODO Auto-generated method stub
		return quizManager.saveQuiz(quiz);
	}
	
	public PageObject getQuizList(Map map,String searchkey,String timeopen, String currentPage){
		return quizManager.getQuizList(map, searchkey,timeopen, currentPage);
	}

	public IQuizManager getQuizManager() {
		return quizManager;
	}

	public void setQuizManager(IQuizManager quizManager) {
		this.quizManager = quizManager;
	}
	public ZjQuizExam getQuizExam(String id) {
		// TODO Auto-generated method stub
		return quizManager.getQuizExam(id);
	}
	public int getStudentsFactNumber(String quiz){
		return quizManager.getStudentsFactNumber(quiz);
	}

}
