package com.zhjedu.exam.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;

import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.manager.IAssignmentWManager;
import com.zhjedu.util.BaseManager;
import com.zhjedu.util.Constants;
import com.zhjedu.util.PageObject;
import com.zhjedu.util.StringUtil;


public class AssignmentWManager extends BaseManager implements IAssignmentWManager {

	private static final Log log = LogFactory.getLog(AssignmentManager.class);
	public boolean saveAssignment(ZjQuiz quiz) {
		try {
			
			super.saveOrUpdateObject(quiz);
			
			return true;
		} catch (HibernateException he) { 
			he.printStackTrace();
			log.error("hibernate 异常：", he);
			throw he;
		}
	}
	public ArrayList getAssignment(String sql) {
		try {
			
			ArrayList list=(ArrayList) super.findObject(sql);
			return list;
		} catch (HibernateException he) { 
			he.printStackTrace();
			log.error("hibernate 异常：", he);
			throw he;
		}
	}
	public PageObject getAssignmentObject(String userid, String status, String currentPage) {
		PageObject po = new PageObject();
		String counthql = "select count(*) from ZjQuizExam as t "
				+ "where t.userid= '" + userid + "' and t.status='" + status
				+ "'";

		String hql = "from ZjQuizExam as t ,ZjQuiz as q,ZjCourse as c "
				+ "where t.zjQuiz=q.id and q.zjCourse=c.id and t.userid='"
				+ userid + "' and t.status='" + status + "'";

		int page = 1;
		if (currentPage != null && !"".equals(currentPage)) {
			page = Integer.parseInt(currentPage);
		}
		System.out.println(">>" + counthql);
		System.out.println(">>" + hql);
		try {
			po = this.executePage(counthql, hql, page, Constants.pageSize);
		} catch (HibernateException e) {
			e.printStackTrace();
		}

		return po;
	}
	public String removeQuestion(String[] questionid) {
		// TODO Auto-generated method stub
		String cd = "";
		if(questionid != null && questionid.length > 0){
			cd = StringUtil.combineStringArray(questionid, "','");
		}
		String info = null;
		Vector ve = new Vector();
		ve.add("update ZjQuiz set delflag='1' where id in ('" + cd + "')");
		
		try {
			this.executeBatch(ve);
		} catch (RuntimeException e) {
			
			info = "ɾ����Ŀʧ��,�����쳣:" + e.getMessage();
			throw e;
		}
		return info;
	}
	public ZjQuiz getQuiz(String questionid) {
		ZjQuiz quiz = new ZjQuiz();
		String hql = "from ZjQuiz where id='"+questionid+"'";
		List list = this.getHibernateTemplate().find(hql);
		//list = this.loadQuestionData(list);
		//if(list!=null&&!list.isEmpty()){
			quiz = (ZjQuiz)list.get(0);
		//}
		return quiz;
	}

}
