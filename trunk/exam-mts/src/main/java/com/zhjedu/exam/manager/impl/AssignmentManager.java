package com.zhjedu.exam.manager.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.manager.IAssignmentManager;
import com.zhjedu.util.BaseManager;
import com.zhjedu.util.Constants;
import com.zhjedu.util.DateTimeUtil;
import com.zhjedu.util.PageObject;

public class AssignmentManager extends BaseManager implements
		IAssignmentManager {

	private static final Log log = LogFactory.getLog(AssignmentManager.class);

	/**
	 * 分页查询
	 */
	public PageObject getAssignmentObject(String userid, String status, String courseId, String currentPage) {

		PageObject po = new PageObject();
		String counthql = "select count(*) from ZjQuizExam as t,ZjQuiz as q "
				+ "where t.zjQuiz=q.id and q.delflag='0' and q.status='0' and t.userid= '" + userid + "'";
		
		String hql = "from ZjQuizExam as t ,ZjQuiz as q "
				+ "where t.zjQuiz=q.id and q.delflag='0' and q.status='0' and t.userid='" + userid + "'";
//		if(courseId != null && !courseId.equals("")){
//			counthql = counthql + " and q.zjCourse='" + courseId + "'";
//			hql = hql + " and q.zjCourse='" + courseId + "'";
//		}
		if(status != null && !status.equals("")){
			counthql = counthql + " and t.status='" + status + "'";
			hql = hql + " and t.status='" + status + "'";
		}
		try {
			if("0".equals(status) || "1".equals(status)){ //当试卷为未交卷状态时进行时间控制  DateTimeUtil.getTime(System.currentTimeMillis(),2)
				counthql = counthql + " and t.zjQuiz.timeopen<=" + System.currentTimeMillis() + " and t.zjQuiz.timeclose>=" + DateTimeUtil.getTimeStamp(DateTimeUtil.getTime(System.currentTimeMillis(),2), 2);
				hql = hql + " and t.zjQuiz.timeopen<=" + System.currentTimeMillis() + " and t.zjQuiz.timeclose>=" + DateTimeUtil.getTimeStamp(DateTimeUtil.getTime(System.currentTimeMillis(),2), 2);
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int page = 1;
		if (currentPage != null && !"".equals(currentPage)) {
			page = Integer.parseInt(currentPage);
		}
		try {
			po = this.executePage(counthql, hql, page, Constants.pageSize);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return po;
	}

	/**
	 * 统计新作业的数目
	 */
	public int getList(String userid, String courseId) throws Exception {
		try {
//			String hql = "select count(*) from ZjQuizExam as t where t.userid= :userid and t.zjQuiz.zjCourse= :courseid and t.status='0'";
			String hql = "select count(*) from ZjQuizExam as t,ZjQuiz as q "
				+ "where t.zjQuiz=q.id and t.userid= :userid and q.delflag='0' and q.status='0' and t.status='0'";
			hql = hql + " and t.zjQuiz.timeopen<=" + System.currentTimeMillis() + " and t.zjQuiz.timeclose>=" + DateTimeUtil.getTimeStamp(DateTimeUtil.getTime(System.currentTimeMillis(),2), 2);
//			String hql = "select count(*) from ZjQuizExam as t where t.userid= :userid and t.status='0'";
			Query query = this.getSession().createQuery(hql);
			query.setString("userid", userid);
//			query.setString("courseid", courseId);
			return ((Long) query.uniqueResult()).intValue();

		} catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		}
	}

	/**
	 * 通过userid 和 status 查询数据
	 */
	public ArrayList getQCbyExam(String userid, String status) {

		StringBuffer sb = new StringBuffer();
		sb.append("from ZjQuizExam as t ,ZjQuiz as q,ZjCourse as c ");
		sb.append("where t.zjQuiz=q.id and q.zjCourse=c.id ");
		sb
				.append("and t.userid='" + userid + "' and t.status='" + status
						+ "'");
		try {
			String hql = sb.toString();
			List list = this.findObject(hql);

			return (ArrayList) list;
		} catch (HibernateException he) {
			he.printStackTrace();
			log.error("hibernate 异常：", he);
			throw he;
		}
	}

	/**
	 * 通过userid，status和assignmentID查询单条记录明细
	 */
	public Object[] getAssignment(String userid, String status,
			String assignmentID) {

		StringBuffer sb = new StringBuffer();
		sb.append("from ZjQuizExam as t ,ZjQuiz as q ");
		sb.append("where t.zjQuiz=q.id ");
		sb.append("and t.userid='" + userid + "' and t.status='" + status + "'"
				+ " and q.id='" + assignmentID + "'");
		try {
			String hql = sb.toString();

			Object[] q = (Object[]) this.getSession().createQuery(hql)
					.uniqueResult();

			return q;
		} catch (HibernateException he) {
			he.printStackTrace();
			log.error("hibernate 异常：", he);
			throw he;
		}

	}

	public boolean saveAssignment(ZjQuiz quiz) {
		
		try {
			//ZjQuiz q=new ZjQuiz();
			//q.setId(quiz.getId());
			
			
			super.saveOrUpdateObject(quiz);
			
			return true;
		} catch (HibernateException he) { 
			he.printStackTrace();
			log.error("hibernate 异常：", he);
			throw he;
		}
		
	}

}