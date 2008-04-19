package com.zhjedu.exam.service;

import java.util.List;
import java.util.Map;

import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.domain.ZjQuizExam;
import com.zhjedu.exam.domain.ZjQuizMidEc;
import com.zhjedu.util.PageObject;

public interface IQuizService {
	/**
	 * 插入或更新考试信息
	 * @param quiz
	 * @return
	 */
	public String saveQuiz(ZjQuiz quiz);
	
	/**
	 * 获取满足条件的考试列表
	 * @param map　封装查询条件
	 * @param currentPage
	 * @return
	 */
	public PageObject getQuizList(Map map,String currentPage);
	
	/**
	 * 根据主键获取考试信息
	 * @param quizid
	 * @return
	 */
	public ZjQuiz getZjQuiz(String quizid);
	
	/**
	 * 批量删除考试信息
	 * @param quizid
	 * @return
	 */
	public String removeQuiz(String[] quizid);
	public String removeQuizQuestion(String[] questions);
	
	public PageObject getQuizList(Map map,String searchkey,String timeopen, String currentPage);
	
	public String saveQuizUsers(String[] users, String quiz);
	
	public String saveQuizECs(String[] ecs, String quiz);
	
	public List getCourseList();
	
	public List getAllUsers();
	
	public List getQuizQuestionList(String quiz);
	
	public String saveQuizQuestions(String[] questions, String quiz);
	
	
	public PageObject getQuizExamList(String courseid,String quizid,String quizType,String status, String currentPage);
	
	public int getQuizExamCount(String courseid,String quizType,String status);
	
	public ZjQuizExam getQuizExam(String id);
	
	public int getStudentsFactNumber(String quiz);
	
	public ZjQuizMidEc getZjQuizMidEc(String quiz,String ec);
	
	public List getZjQuizMidEcList(String quizid);
	
	
}
