package com.zhjedu.exam.service.impl;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhjedu.exam.manager.IAssignmentManager;
import com.zhjedu.exam.manager.impl.AssignmentManager;
import com.zhjedu.exam.service.IAssignmentService;
import com.zhjedu.util.PageObject;

public class AssignmentServiceImpl extends AssignmentManager implements IAssignmentService{

	private static final Log log = LogFactory.getLog(AssignmentServiceImpl.class);

	private IAssignmentManager assignmentManager;

	public IAssignmentManager getAssignmentManager() {
		return assignmentManager;
	}

	public void setAssignmentManager(IAssignmentManager assignmentManager) {
		this.assignmentManager = assignmentManager;
	}
	
	/** 分页查询 */
	public PageObject getAssignmentObject(String userid, String status, String courseId, String currentPage){
		return assignmentManager.getAssignmentObject(userid, status, courseId, currentPage);
	}

	/** 统计新作业的数目 */
	public int getList(String userid, String courseId) throws Exception {
		return assignmentManager.getList(userid, courseId);
	}
	
	/** 通过userid 和 status 查询数据 */
	public ArrayList getQCbyExam(String userid, String status) {
		return assignmentManager.getQCbyExam(userid, status);
	}

	/** 通过userid，status和assignmentID查询单条记录明细	 */
	public Object[] getAssignment(String userid, String status,
			String assignment) {
		return assignmentManager.getAssignment(userid, status, assignment);
	}

}