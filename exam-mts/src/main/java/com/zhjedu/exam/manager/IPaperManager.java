package com.zhjedu.exam.manager;

import java.util.List;
import java.util.Vector;

import com.zhjedu.exam.domain.ZjPaper;
import com.zhjedu.exam.domain.ZjQuizQuestion;
import com.zhjedu.util.PageObject;

public interface IPaperManager {

	/**
	 * 获取固定试卷分页列表
	 * @param courseId	课程ID
	 * @param name		查询条件:试卷名称
	 * @param page		当前页
	 * @return			装载了试卷列表信息的分页对象
	 */
	public PageObject getPaperList(String courseId, String name, int page);
	
	/**
	 * 移除固定试卷(非物理移除)
	 * @param paperId	不为空的试卷ID数组
	 */
	public void removePaper(String[] paperId);
	
	/**
	 * 根据ID获取正常状态的固定试卷信息
	 * @param paperId	试卷ID
	 * @return			固定试卷信息
	 */
	public ZjPaper getPaper(String paperId);
	
	/**
	 * 根据固定试卷ID获取试卷的试题信息
	 * @param paperId	试卷ID
	 * @return			固定试卷试题信息
	 */
	public List getPaperQuestion(String paperId);
	
	/**
	 * 移除固定试卷下的试题信息
	 * @param paperId		试卷ID
	 * @param questionId	题目ID数组
	 */
	public void removeQuestion(String paperId, String[] questionId);
	
	/**
	 * 根据试题ID数组获取试题信息
	 * @param questionId	试题ID数组
	 * @return				装载了试题的List
	 */
	public List getQuestion(String[] questionId);
	
	/**
	 * 保存试卷试题关系
	 * @param paperQuestionList	装载有ZjQuizQuestion对象的list
	 */
	public void savePaperQuestion(Vector paperQuestionList);
	
	/**
	 * 保存试卷信息
	 * @param paper	试卷信息
	 */
	public void savePaper(ZjPaper paper);
	
	/**
	 * 保存试卷状态
	 * @param paperId	试卷ID
	 * @param status	状态
	 */
	public void savePaperStatus(String paperId, String status);
}
