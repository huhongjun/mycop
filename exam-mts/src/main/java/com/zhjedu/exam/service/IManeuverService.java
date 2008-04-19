package com.zhjedu.exam.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhjedu.exam.domain.ZjManeuverList;

public interface IManeuverService {
	
	/**
	 * 保存详细策略
	 * @param courseId 课程ID
	 * @param quizId   考试或试卷ID
	 * @return         true-成功 false-失败
	 */
	public boolean saveManeuverList(String courseId, String quizId);
	
	/**
	 * 根据作业策略保存作业与试题的关系
	 * @param quizId 作业ID
	 * @return		 true-成功 false-失败
	 */
	public boolean saveQuizQuestion(String quizId);
	
	/**
	 * 根据课程ID取得该课程下章信息下拉列表的HTML代码
	 * @param courseId	课程ID
	 * @return			HTML代码
	 */
	public String getChapterSelectOptionsHtml(String courseId);
	
	/**
	 * 取得题型信息下拉列表的HTML代码
	 * @return	HTML代码
	 */
	public String getQuestionSelectOptionsHtml();
	
	/**
	 * 根据考试ID/作业ID获得策略详细信息
	 * @param quizId	考试ID/作业ID
	 * @return			装载了一个Object[]对象的List
	 */
	public List getManeuverList(String quizId);
	
	/**
	 * 根据策略ID删除策略详细信息
	 * @param maneuverId	策略ID
	 */
	public void removeManeuverbyId(String maneuverId);
	
	/**
	 * 保存单条策略详细信息
	 * @param maneuver	策略详细信息
	 * @return			true-成功，false-库中已存在相同的策略信息
	 */
	public boolean saveManeuver(ZjManeuverList maneuver);
	
	/**
	 * 更新策略详细信息
	 * @param maneuverId	策略ID
	 * @param request		request对象，用来传参
	 * @return				true-成功，false-根据ID未找到该策略
	 */
	public boolean updateManeuver(String maneuverId, HttpServletRequest request);
	
	/**
	 * 测试级联查询(测试用)
	 * @return
	 */
	public boolean test();
}
