package com.zhjedu.exam.manager;

import java.util.List;

import com.zhjedu.exam.domain.ZjManeuverList;
import com.zhjedu.exam.domain.ZjQuiz;

public interface IManeuverManager {
	
	/**
	 * 获取默认策略
	 * @return 默认策略信息的List
	 */
	public List getManeuver();
	
	/**
	 * 取得一门课程下一种题型的可用个数(默认条件:状态位可用,父ID为'0'即为非子题,未删除)
	 * @param courseId     课程ID必须传值且不能为空
	 * @param scope        范围,如果为空则不判断范围
	 * @param questionType 题型ID,为空或为0时为所有题型
	 * @return             题的数量
	 */
	public int getQuestionCountbyCourse(String courseId, String scope, String questionType);
	
	/**
	 * 取得一门课程下一种题型的题目List(默认状态:状态位可用,父ID为'0'即为非子题,未删除)
	 * @param courseId     课程ID必须传值且不能为空
	 * @param scope        范围,如果为空则不判断范围
	 * @param questionType 题型ID,为空或为0时为所有题型
	 * @return             题的列表
	 */
	public List getQuestionListbyCourse(String courseId, String scope, String questionType);
	
	/**
	 * 保存策略详细信息
	 * @param list 保存了策略详细信息的List
	 */
	public void saveManeuverList(List list);
	
	/**
	 * 通过考试获得策略详细列表
	 * @param quizId 考试ID
	 * @return       策略详细信息的List
	 */
	public List getManeuverListbyQuiz(String quizId);
	
	/**
	 * 取得一章下某种题型的题目数量(默认条件:状态位可用,父ID为'0'即为非子题,未删除)
	 * @param scope			章ID
	 * @param questionType	题型
	 * @param difficult_s	开始难度系数(如不加控制,该项为0)
	 * @param difficult_e	结束难度系数(如不加控制,该项为0)
	 * @return				满足参数条件的题目数量
	 */
	public int getQuestionCount(String scope, String questionType, double difficult_s, double difficult_e);
	
	/**
	 * 取得一章下某种题型的题目列表(默认条件:状态位可用,父ID为'0'即为非子题,未删除)
	 * @param scope			章ID
	 * @param questionType	题型
	 * @param difficult_s	开始难度系数(如不加控制,该项为0)
	 * @param difficult_e	结束难度系数(如不加控制,该项为0)
	 * @return				满足参数条件的题目列表
	 */
	public List getQuestion(String scope, String questionType, double difficult_s, double difficult_e);
	
	/**
	 * 保存作业试题关系
	 * @param list 保存了作业试题关系信息的List
	 */
	public void saveQuizQuestionList(List list);
	
	/**
	 * 根据课程ID取得章信息
	 * @param courseId	课程ID
	 * @return			章列表
	 */
	public List getChapterbyCourse(String courseId);
	
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
	 * 保存策略详细信息
	 * @param maneuver	策略详细信息
	 */
	public void saveManeuverList(ZjManeuverList maneuver);
	
	/**
	 * 根据作业ID,章ID和题型查找策略详细信息
	 * @param quizId		作业ID
	 * @param scope			章ID（范围）
	 * @param questionType	题型
	 * @return				策略List
	 */
	public List getManeuverListbyScopeAndQuestiontype(String quizId, String scope, String questionType);
	
	/**
	 * 根据ID查找策略详细信息
	 * @param maneuverId	策略ID
	 * @return				策略详细信息
	 */
	public ZjManeuverList getManeuverbyId(String maneuverId);
	
	/**
	 * 物理删除作业下的所有策略详细
	 * @param quizId	作业ID
	 */
	public void removeManeuverList(String quizId);
	
	/**
	 * 根据作业ID获取作业信息
	 * @param quizId	作业ID
	 * @return			作业对象
	 */
	public ZjQuiz getQuizbyId(String quizId);
	
	/**
	 * 移除作业试题关系
	 * @param quizId	作业ID
	 */
	public void removeQuizQuestion(String quizId);
}
