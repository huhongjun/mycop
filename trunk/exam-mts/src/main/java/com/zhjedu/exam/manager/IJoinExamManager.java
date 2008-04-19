package com.zhjedu.exam.manager;

import java.util.List;

import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.domain.ZjQuizAnswers;
import com.zhjedu.exam.domain.ZjQuizExam;

public interface IJoinExamManager {
	
	/**
	 * 根据ID取得作业信息
	 * @param quizId	作业ID
	 * @return			作业对象
	 */
	public ZjQuiz getQuizbyId(String quizId);
	
	/**
	 * 根据用户ID,作业ID取得未完成的作业情况
	 * @param userId	用户ID
	 * @param quizId	作业ID
	 * @return			未完成作业情况(未参加或已参加未完成)
	 */
	public ZjQuizExam getQuizExam(String userId, String quizId);
	
	/**
	 * 取得一份试卷的所有一级题(固定试卷)
	 * @param quizId	作业ID
	 * @param paperId	试卷ID
	 * @return			一级题LIST
	 */
	public List getQuestionList(String quizId, String paperId);
	
	/**
	 * 取得一份试卷的所有一级题(随机出题)
	 * @param quizId	作业ID
	 * @param belongto	第几套题
	 * @return			一级题LIST
	 */
	public List getQuestionList(String quizId, int belongto);
	
	/**
	 * 获得一道题(单/多选题)的选项List
	 * @param questionId	题ID
	 * @return				选项List
	 */
	public List getQuestionOptionList(String questionId);
	
	/**
	 * 获得一道题(匹配题)的匹配选项List
	 * @param questionId	题ID
	 * @return				匹配选项List
	 */
	public List getQuestionMatchingOptionList(String questionId);

	/**
	 * 获得一道题(匹配题)的匹配答案List
	 * @param questionId	题ID
	 * @return				匹配答案List
	 */
	public List getQuestionMatchingAnswerList(String questionId);
	
	/**
	 * 获得一道题(综合题)的子题List
	 * @param questionId	题ID
	 * @return				子题List
	 */
	public List getSonQuestionList(String questionId);
	
	/**
	 * 保存用户答题信息
	 * @param quizAnswerList	答题信息LIST
	 */
	public void saveQuizAnswerList(List quizAnswerList);
	
	/**
	 * 移除学生答卷信息
	 * @param quizExamId	用户考试信息ID
	 */
	public void removeQuizAnswer(String quizExamId);
	
	/**
	 * 更新学生考试信息状态
	 * @param quizExamId	考试信息ID
	 * @param status		状态位
	 * @param  isStart		是否开始作业（true-是 false-否）
	 */
	public void updateQuizExamStatus(String quizExamId, String status, boolean isStart);
	
	/**
	 * 更新学生考试信息状态
	 * @param quizExamId	考试信息ID
	 * @param status		状态位
	 */
	public void updateQuizExamStatus(String quizExamId, String status);
	
	/**
	 * 更新学生考试信息分数
	 * @param quizExamId	考试信息ID
	 */
	public void updateQuizExamScore(String quizExamId);
	
	/**
	 * 取得一道题的用户答案
	 * @param quizExamId	用户考试信息ID
	 * @param questionId	题目ID
	 * @return				用户考试答案
	 */
	public ZjQuizAnswers getQuestionAnswer(String quizExamId, String questionId);
	
	/**
	 * 更新一道题的分值
	 * @param quizAnswerId	用户答案ID
	 * @param score			用户答案得分
	 */
	public void updateScore(String quizAnswerId, String score);
	
	/**
	 * 更新用户考试的批注
	 * @param quizExamId	用户考试ID
	 * @param postil		批注内容
	 */
	public void updatePostil(String quizExamId, String postil);
	
	/**
	 * 取得考试信息列表
	 * @param quizId	考试ID
	 * @param password	考试密码
	 * @return			考试信息列表
	 */
	public List getQuizList(String quizId, String password);
	
	
	//****************************************  */
	//以下是同步学生参加考试数据的一些方法  2008-04-05
	//***************************************** */
	
	/**
	 * 根据中间库入学科目ID查询分配给的考试信息
	 * @param examCourseId	入学科目ID
	 * @return				科目和考试关联对象List
	 */
	public List getQuizMidECList(int examCourseId);
	
	/**
	 * 判断用户考试信息是否存在
	 * @param userId	用户ID
	 * @param quizId	考试ID
	 * @return			true - 不存在  false - 存在
	 */
	public boolean isQuizExamHave(String userId, String quizId);
	
	/**
	 * 保存用户考试信息
	 * @param quizExamList	用户考试信息列表
	 */
	public void saveQuizExam(List quizExamList);
}
