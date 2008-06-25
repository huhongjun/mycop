package com.zhjedu.exam.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.zhjedu.exam.domain.ZjPaper;
import com.zhjedu.util.PageObject;

public interface IPaperService {
	/**
	 * 获取固定试卷分页列表
	 * @param courseId	课程ID
	 * @param name		查询条件:试卷名称
	 * @param page		当前页
	 * @return			装载了试卷列表信息的分页对象
	 */
	public PageObject getPaperList(String courseId, String name, int page);
	
	/**
	 * 移除固定试卷信息(非物理删除)
	 * @param paperId	试卷ID数组
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
	 * 保存试卷试题关系
	 * @param paperId		试卷ID
	 * @param questionId	试题ID数组
	 * @param score			每题分值
	 */
	public void savePaperQuestion(String paperId, String[] questionId, int score);
	
	/**
	 * 保存试卷信息
	 * @param request	传递参数对象
	 */
	public void savePaper(HttpServletRequest request);
	
	/**
	 * 更新试卷信息
	 * @param request	传递参数对象
	 */
	public void updatePaper(HttpServletRequest request);
	
	/**
	 * 停用试卷
	 * @param paperId	试卷ID
	 */
	public void stopPaper(String paperId);
	
	/**
	 * 发布试卷
	 * @param paperId	试卷ID
	 * @return
	 */
	public boolean pubPaper(String paperId);
}