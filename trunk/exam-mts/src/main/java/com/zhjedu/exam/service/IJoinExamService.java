package com.zhjedu.exam.service;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import com.zhjedu.exam.domain.ZjQuiz;

public interface IJoinExamService {
	

	/**
	 * 根据ID取得作业信息
	 * @param quizId	作业ID
	 * @return			作业对象
	 */
	public ZjQuiz getQuizbyId(String quizId);
	
	/**
	 * 取得作业需要的题信息
	 * @param quiz		作业对象
	 * @param isPaper	是否固定试卷(true-是 false-否)
	 * @return			作业需要的题信息
	 */
	public Hashtable getQuestionbyQuizid(ZjQuiz quiz, boolean isPaper);
	
	/**
	 * 取得作业需要的题信息
	 * @param userId	用户ID
	 * @param quizId	作业ID
	 * @param isPaper	是否固定试卷(true-是 false-否)
	 * @return			作业需要的题信息
	 */
	public Hashtable getQuestionbyQuizid2(String userId, String quizId, boolean isPaper);
	
	/**
	 * 取得作业需要的题信息
	 * @param userId	用户ID
	 * @param quizId	作业ID
	 * @param isPaper	是否固定试卷(true-是 false-否)
	 * @return			作业需要的题信息
	 */
	public Hashtable getQuestionbyQuizid(String userId, String quizId, boolean isPaper);
	
	/**
	 * 保存用户参加作业情况
	 * @param request	传递用户答案
	 * @param userId	用户ID
	 * @param quizId	作业ID
	 * @param isPaper	是否固定试卷(true-是 false-否)
	 */
	public void saveExamScore(HttpServletRequest request, String userId, String quizId, boolean isPaper);
	
	/**
	 * 取得作业需要的题答案信息
	 * @param userId	用户ID
	 * @param quizId	作业ID
	 * @param isPaper	是否固定试卷(true-是 false-否)
	 * @return			作业需要的题信息
	 */
	public Hashtable getQuestionAnswerbyQuizid(String userId, String quizId, boolean isPaper);
	
	/**
	 * 保存一道题的分值
	 * @param quizAnswerId	用户答案ID
	 * @param score			用户答案得分
	 */
	public void saveScorebyquizAnswerId(String quizAnswerId, String score);
	
	/**
	 * 保存教师批注
	 * @param quizExamId	用户考试ID
	 * @param postil		批注内容
	 */
	public void savePostilbyquizExamId(String quizExamId, String postil);
	
	/**
	 * 完成作业批改
	 * @param quizExamId	考试信息ID
	 */
	public void saveFinish(String quizExamId);
	
	/**
	 * 检测考试密码是否正确
	 * @param quizId	考试ID
	 * @param password	考试密码
	 * @return			结果 true-正确 false-错误
	 */
	public boolean checkPass(String quizId, String password);
	
	
	//****************************************  */
	//以下是同步学生参加考试数据的一些方法  2008-04-05
	//***************************************** */
	
	/**
	 * 保存用户考试信息
	 * @param userId	用户ID
	 * @param list		入学科目ID
	 */
	public void saveQuizExamList(String userId, ArrayList list);
}
