package com.zhjedu.exam.manager.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.hibernate.HibernateException;

import com.dfcw.zjproject.zj.dao.StudentDAO;
import com.dfcw.zjproject.zj.dao.StudentDAOImpl;
import com.dfcw.zjproject.zj.model.StudentModel;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.domain.ZjQuizExam;
import com.zhjedu.exam.domain.ZjQuizMidEc;
import com.zhjedu.exam.domain.ZjQuizQuestion;
import com.zhjedu.exam.manager.IQuizManager;
import com.zhjedu.util.BaseManager;
import com.zhjedu.util.Constants;
import com.zhjedu.util.DateTimeUtil;
import com.zhjedu.util.PageObject;
import com.zhjedu.util.StringUtil;

public class QuizManager extends BaseManager implements IQuizManager {

	public String removeQuizQuestion( String[] questions) {
		// TODO Auto-generated method stub
		String info = null;
		try {
			for(int i=0;i<questions.length;i++){
				String hql = "from ZjQuizQuestion where id='"+questions[i]+"'";
				List list = this.getHibernateTemplate().find(hql);
				if(list!=null&&!list.isEmpty()){
					this.getHibernateTemplate().delete((ZjQuizQuestion)list.get(0));
				}
			}
			
		} catch (RuntimeException e) {
			
			info = "删除题目失败:" + e.getMessage();
			throw e;
		}
		return info;
	}
	public List getQuizQuestionList(String quiz){
		List list = null;
		String hql = "from ZjQuizQuestion where quiz='"+quiz+"'";
		list = this.getHibernateTemplate().find(hql);
		return list;
	}
	public ZjQuizQuestion getQuizQuestion(String quiz,String question){
		List list = null;
		ZjQuizQuestion qq = null;
		String hql = "from ZjQuizQuestion where quiz='"+quiz+"' and question.id='"+question+"'";
		list = this.getHibernateTemplate().find(hql);
		if(list!=null&&!list.isEmpty()){
			qq = (ZjQuizQuestion)list.get(0);
		}
		return qq;
	}
	
	public List getAllUsers() {
		// TODO Auto-generated method stub
		String hql = "from ZjUser";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}

	public String saveQuizUsers(String[] users, String quiz) {
		// TODO Auto-generated method stub

		if(users != null && users.length > 0){
			for(int i=0;i<users.length;i++){
				ZjQuizExam quizExam = new ZjQuizExam();
				quizExam.setUserid(users[i]);
				System.out.println(users[i]);
				ZjQuiz tmp = this.getZjQuiz(quiz);
				quizExam.setZjQuiz(tmp);
				if(this.getZjQuizExam(quiz, users[i])==null){
					quizExam.setPaper(String.valueOf((int)(Math.random()*10)));
					this.getHibernateTemplate().save(quizExam);
				}
			}
		}
		
		return "";
	}
	
	public String saveQuizECs(String[] ecs, String quiz) {
		// TODO Auto-generated method stub

		if(ecs != null && ecs.length > 0){
			for(int i=0;i<ecs.length;i++){
				ZjQuizMidEc quizMidEc = new ZjQuizMidEc();
				quizMidEc.setMidExamId(ecs[i]);
				ZjQuiz tmp = this.getZjQuiz(quiz);
				quizMidEc.setZjQuiz(tmp);
				if(this.getZjQuizMidEc(quiz, ecs[i])==null){
					this.getHibernateTemplate().save(quizMidEc);
				}
			}
		}
		
		return "";
	}
	
	public String saveQuizQuestions(String[] questions, String quiz) {
		// TODO Auto-generated method stub

		if(questions != null && questions.length > 0){
			for(int i=0;i<questions.length;i++){
				ZjQuizQuestion quizQuestion = new ZjQuizQuestion();
				
				String hql = "from ZjQuestion where id='"+questions[i]+"'";
				List list = this.getHibernateTemplate().find(hql);
				quizQuestion.setQuestion((ZjQuestion)list.get(0));
				quizQuestion.setQuiz(quiz);
				if(this.getQuizQuestion(quiz, questions[i])==null){
					this.getHibernateTemplate().save(quizQuestion);
				}
			}
		}
		
		return "";
	}

	public ZjQuizExam getZjQuizExam(String quizid,String userid) {
		// TODO Auto-generated method stub
		ZjQuizExam quizExam = null;
		String hql = "from ZjQuizExam where userid='"+userid+"' and quiz='"+quizid+"'";
		List list = this.getHibernateTemplate().find(hql);
		
		if(list!=null&&list.size()>0){
			quizExam = (ZjQuizExam)list.get(0);
		}
		return quizExam;
	}
	
	public ZjQuizMidEc getZjQuizMidEc(String quizid,String ec) {
		// TODO Auto-generated method stub
		ZjQuizMidEc tmp = null;
		String hql = "from ZjQuizMidEc where midExamId='" +ec+ "' and quiz='"+quizid+"'";
		List list = this.getHibernateTemplate().find(hql);
		
		if(list!=null&&list.size()>0){
			tmp = (ZjQuizMidEc)list.get(0);
		}
		return tmp;
	}
	
	public List getZjQuizMidEcList(String quizid) {
		// TODO Auto-generated method stub
		
		String hql = "from ZjQuizMidEc where quiz='"+quizid+"'";
		List list = this.getHibernateTemplate().find(hql);
		
		
		return list;
	}
	
	public List getCourseList() {
		// TODO Auto-generated method stub
		String hql = "from ZjCourse where itemType='0'";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}
	public PageObject getQuizList(Map map, String currentPage) {
		// TODO Auto-generated method stub
		PageObject po = new PageObject();
		String counthql = "select count(id) from ZjQuiz where 1>0";
		String hql = "from ZjQuiz where 1>0";
		Set keySet = map.keySet();
        Iterator it = keySet.iterator();
        while(it.hasNext())
        {
        	String key = (String)it.next();
        	if(map.get(key) != null)
        	{
        		counthql = counthql + " and "+key+"='"+map.get(key)+"'";
        		hql = hql + " and "+key+"='"+map.get(key)+"'";
        	}
        }
        hql = hql +" order by lasteditdate desc,quizType asc";
		int page = 1;
		if(currentPage != null && !"".equals(currentPage)){
			page = Integer.parseInt(currentPage);
		}
		try {
			po = this.executePage(counthql, hql, page, Constants.pageSize);
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		
		return po;
	}
	
	public PageObject getQuizExamList(String courseid,String quizid,String quizType,String status, String currentPage) {
		// TODO Auto-generated method stub
		PageObject po = new PageObject();
		String counthql = "select count(A.id) from ZjQuizExam A,ZjQuiz B where A.zjQuiz.id=B.id and B.quizType='"+quizType+"' and A.status='"+status+"'";
		String hql = "select A from ZjQuizExam A,ZjQuiz B where A.zjQuiz.id=B.id and B.quizType='"+quizType+"' and A.status='"+status+"'";
		if(courseid!=null&&!courseid.equals("")){
			counthql = counthql + " and B.zjCourse.id='"+courseid+"'";
			hql = hql + " and B.zjCourse.id='"+courseid+"'";
		}
        if(quizid!=null&&!quizid.equals("")){
        	counthql = counthql + " and A.quiz='"+quizid+"'";
			hql = hql + " and A.quiz='"+quizid+"'";
        }
		int page = 1;
		if(currentPage != null && !"".equals(currentPage)){
			page = Integer.parseInt(currentPage);
		}
		try {
			po = this.executePage(counthql, hql, page, Constants.pageSize);
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		ArrayList list = this.loadUserData(po.getDatas());
		po.setDatas(list);
		return po;
	}
	
	public ZjQuizExam getQuizExam(String id){
		ZjQuizExam qe = null;
		String hql = "from ZjQuizExam where id='"+id+"'";
		List list = this.getHibernateTemplate().find(hql);
		if(list!=null&&!list.isEmpty()){
			qe = (ZjQuizExam)list.get(0);
		}
		StudentDAO sdao = new StudentDAOImpl();
		if(qe!=null){
			
				
			try {
				StudentModel student = sdao.getStudentBySID(Integer.parseInt(qe.getUserid()));
				ZjQuiz quiz = this.getZjQuiz(qe.getQuiz());
				qe.setStudent(student);
				qe.setZjQuiz(quiz);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		return qe;
	}
	
	public ArrayList loadUserData(List list){
		
		ArrayList tmpList = new ArrayList();
		StudentDAO sdao = new StudentDAOImpl();
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				ZjQuizExam qe = (ZjQuizExam)list.get(i);
				System.out.println(qe.getQuiz());
				try {
					StudentModel student = sdao.getStudentBySID(Integer.parseInt(qe.getUserid()));
					ZjQuiz quiz = this.getZjQuiz(qe.getQuiz());
					qe.setStudent(student);
					qe.setZjQuiz(quiz);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tmpList.add(qe);
			}
		}		
		return tmpList;
	}
	
	public int getQuizExamCount(String courseid,String quizType,String status){
		int num = 0;
		String counthql = "select count(A.id) from ZjQuizExam A,ZjQuiz B where A.zjQuiz.id=B.id and A.status='"+status+"' and B.quizType='"+quizType+"' and B.zjCourse.id='"+courseid+"'";
		List list = this.getHibernateTemplate().find(counthql);
		if(list!=null&&!list.isEmpty()){
			num = ((Long)list.get(0)).intValue();
		}
		return num;
	}
	
	public PageObject getQuizList(Map map,String searchkey,String timeopen, String currentPage) {
		// TODO Auto-generated method stub
		PageObject po = new PageObject();
		String counthql = "select count(id) from ZjQuiz where 1>0";
		String hql = "from ZjQuiz where 1>0";
		Set keySet = map.keySet();
        Iterator it = keySet.iterator();
        while(it.hasNext())
        {
        	String key = (String)it.next();
        	if(map.get(key) != null)
        	{
        		counthql = counthql + " and "+key+"='"+map.get(key)+"'";
        		hql = hql + " and "+key+"='"+map.get(key)+"'";
        	}
        }
        try {
			if(timeopen!=null&&!timeopen.equals("")){
				counthql = counthql + " and timeopen>"+DateTimeUtil.getTimeStamp(timeopen, 2);
				hql = hql + " and timeopen>"+DateTimeUtil.getTimeStamp(timeopen, 2);
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if(searchkey!=null&&!searchkey.equals("")){
        	counthql = counthql + " and (name like '%"+searchkey+"%' or description like '%"+searchkey+"%')";
        	hql = hql + " and (name like '%"+searchkey+"%' or description like '%"+searchkey+"%')";
        }
        hql = hql +" order by lasteditdate desc";
		int page = 1;
		if(currentPage != null && !"".equals(currentPage)){
			page = Integer.parseInt(currentPage);
		}
		try {
			po = this.executePage(counthql, hql, page, Constants.pageSize);
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		
		return po;
	}
	public ZjQuiz getZjQuiz(String quizid) {
		// TODO Auto-generated method stub
		ZjQuiz quiz = new ZjQuiz();
		String hql = "from ZjQuiz where id='"+quizid+"'";
		List list = this.getHibernateTemplate().find(hql);
		
		if(list!=null&&!list.isEmpty()){
			quiz = (ZjQuiz)list.get(0);
		}
		return quiz;
	}

	public String saveQuiz(ZjQuiz quiz) {
		// TODO Auto-generated method stub
		String id = "";
		if(quiz.getId()==null||quiz.getId().equals("")){
			id=this.getHibernateTemplate().save(quiz).toString();
		}
		else{
			id = quiz.getId();
			this.getHibernateTemplate().update(quiz);			
		}
		return id;
	}

	public String removeQuiz(String[] quizid) {
		// TODO Auto-generated method stub
		String cd = "";
		if(quizid != null && quizid.length > 0){
			cd = StringUtil.combineStringArray(quizid, "','");
		}
		String info = null;
		Vector ve = new Vector();
		ve.add("update ZjQuiz set delflag='1' where id in ('" + cd + "')");
		
		try {
			this.executeBatch(ve);
		} catch (RuntimeException e) {
			
			info = "删除考试失败:" + e.getMessage();
			throw e;
		}
		return info;
	}
	public int getStudentsFactNumber(String quiz){
		int num = 0;
		String hql = "select count(*) from ZjQuizExam where quiz='"+quiz+"'";
		List list = this.getHibernateTemplate().find(hql);
		if(list!=null&&!list.isEmpty()){
			num = ((Long)list.get(0)).intValue();
		}
		return num;
	}
}
