package com.zhjedu.exam.manager;

import java.util.ArrayList;

import com.zhjedu.util.PageObject;
import com.zhjedu.exam.domain.ZjQuiz;

public interface IAssignmentManager {
	
	/** 分页查询 */
	public PageObject getAssignmentObject(String userid, String status, String courseId, String currentPage);
	
	/** 统计新作业的数目 */
	public int getList(String userid, String courseId) throws Exception;
	
	/** 通过userid 和 status 查询数据 */
	public ArrayList getQCbyExam(String userid, String status) ;
	
	/** 通过userid，status和assignmentID查询单条记录明细	 */
	public Object[] getAssignment(String userid, String status, String assignment) ;
	
	public boolean saveAssignment(ZjQuiz quiz);

}
