package com.zhjedu.exam.manager.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.Vector;

import org.hibernate.HibernateException;

import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuestionCategory;
import com.zhjedu.exam.domain.ZjQuestionMatchingAnswer;
import com.zhjedu.exam.domain.ZjQuestionMatchingOption;
import com.zhjedu.exam.domain.ZjQuestionOption;
import com.zhjedu.exam.domain.ZjQuizQuestion;

import com.zhjedu.exam.manager.IQuestionManager;
import com.zhjedu.util.BaseManager;
import com.zhjedu.util.Constants;
import com.zhjedu.util.PageObject;
import com.zhjedu.util.StringUtil;

public class QuestionManager extends BaseManager implements
		IQuestionManager {
	public PageObject getCategoryObject(Map map, String currentPage) {
		// TODO Auto-generated method stub
		PageObject po = new PageObject();
		String counthql = "select count(id) from ZjQuestionCategory where 1>0";
		String hql = "from ZjQuestionCategory where 1>0";
		Set keySet = map.keySet();
        Iterator it = keySet.iterator();
        while(it.hasNext())
        {
        	String key = (String)it.next();
        	if(map.get(key) != null)
        	{
        		if(map.get(key).equals("")){
        			counthql = counthql + " and "+key+" is null";
            		hql = hql + " and "+key+" is null";
        		}
        		else{
        			counthql = counthql + " and "+key+"='"+map.get(key)+"'";
        			hql = hql + " and "+key+"='"+map.get(key)+"'";
        		}
        	}
        }
        hql = hql +" order by lasteditdate desc,parent asc";
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

	public String removeQuestionCategory(String category) {
		// TODO Auto-generated method stub
		String info = null;
		
		Vector ve = new Vector();
		ve.add("update ZjQuestionCategory set delflag='1' where id='" + category + "'");
		
		try {
			this.executeBatch(ve);
		} catch (RuntimeException e) {
			
			info = "删除目录失败:" + e.getMessage();
			throw e;
		}
		return info;
	}

	public String saveQuestionCategory(ZjQuestionCategory category) {
		// TODO Auto-generated method stub
		String id = "";
		if(category.getId()==null||category.getId().equals("")){
			id=this.getHibernateTemplate().save(category).toString();
		}
		else{
			id = category.getId();
			this.getHibernateTemplate().update(category);			
		}
		return id;
	}
	public ZjQuestionCategory getZjQuestionCategory(String id) {
		// TODO Auto-generated method stub
		ZjQuestionCategory category = new ZjQuestionCategory();
		String hql = "from ZjQuestionCategory where id='"+id+"'";
		List list = this.getHibernateTemplate().find(hql);
		if(list!=null&&!list.isEmpty()){
			category = (ZjQuestionCategory)list.get(0);
		}
		return category;
	}
	
	public List getZjCategoryList(String parent) {
		// TODO Auto-generated method stub
		
		String hql = "from ZjQuestionCategory where parent='"+parent+"'";
		List list = this.getHibernateTemplate().find(hql);
		
		return list;
	}
	public List getQuestionListByCategory(String category,List list) {
		// TODO Auto-generated method stub
		List tmpQList = new ArrayList();
		String hql = "from ZjQuestion where category='"+category+"' and delflag='0'";
		
		tmpQList = this.getHibernateTemplate().find(hql);
		if(tmpQList!=null&&!tmpQList.isEmpty()){
			for(int i=0;i<tmpQList.size();i++){
				ZjQuestion tmpq = (ZjQuestion)tmpQList.get(i);
				list.add(tmpq);
			}
		}
		String hql1 = "from ZjQuestionCategory where parent='" + category + "' and delflag='0'";
		List tmpCategoryList = this.getHibernateTemplate().find(hql1);
		if(tmpCategoryList!=null&&!tmpCategoryList.isEmpty()){
			for(int j=0;j<tmpCategoryList.size();j++){
				ZjQuestionCategory tmp = (ZjQuestionCategory)tmpCategoryList.get(j);
				list = getQuestionListByCategory(tmp.getId(),list);
			}
		}
		return list;
	}
	
	public List getQuestionListByCategory(String category) {
		// TODO Auto-generated method stub
		
		String hql = "from ZjQuestion where category='"+category+"' and delflag='0'";
		List list = this.getHibernateTemplate().find(hql);
		
		
		return list;
	}
	public ZjQuestionCategory getRootCategory() {
		// TODO Auto-generated method stub
		ZjQuestionCategory category = null;
		String hql = "from ZjQuestionCategory where isleaf='1' and delflag='0'";
		List list = this.getHibernateTemplate().find(hql);
		if(list!=null&&!list.isEmpty()){
			category = (ZjQuestionCategory)list.get(0);
		}
		return category;
	}

	public List getMenuList(String courseid) {
		// TODO Auto-generated method stub
		List menulist = new ArrayList();
		String hql = "from ZjCourse where id='"+courseid+"'";
		List tmp = this.getHibernateTemplate().find(hql);
		ZjCourse course = new ZjCourse();
		if(tmp!=null&&!tmp.isEmpty()){
			course = (ZjCourse)tmp.get(0);
			menulist.add(course);
		}
		menulist = this.getMenuList(menulist, course.getId());
		return menulist;
	}
	
	public List getMenuList(List menulist,String courseid) {
		String hql = "from ZjCourse where parent='"+courseid+"'";
		List tmplist = this.getHibernateTemplate().find(hql);
		if(tmplist!=null&&!tmplist.isEmpty()){
			for(int i=0;i<tmplist.size();i++){
				ZjCourse course = (ZjCourse)tmplist.get(i);
				menulist.add(course);
				menulist = this.getMenuList(menulist, course.getId());
			}
			
		}
		return menulist;
	}
	
	public List getAllCategory(String parent){
		List list = null;
		String hql = "from ZjQuestionCategory where delflag='0'";
		if(parent!=null&&!parent.equals("")){
			hql = hql + " and parent='"+parent+"'";
		}
		list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	public List getCourseItemList(String course){
		List list = null;
		String hql = "from ZjCourse where delflag='0'";
		if(course!=null&&!course.equals("")){
			hql = hql + " and parent='"+course+"'";
		}
		list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	public ArrayList loadQuestionData(List list){
		
		ArrayList tmpList = new ArrayList();
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				ZjQuestion question = (ZjQuestion)list.get(i);
				String hql1 = "from ZjQuestionOption where question='"+question.getId()+"'";
				String hql2 = "from ZjQuestionMatchingOption where question='"+question.getId()+"'";
				String hql3 = "from ZjQuestionMatchingAnswer where question='"+question.getId()+"'";
				question.setOptionList(this.getHibernateTemplate().find(hql1));
				question.setMatchingOptionList(this.getHibernateTemplate().find(hql2));
				question.setMatchingAnswerList(this.getHibernateTemplate().find(hql3));
				tmpList.add(question);
			}
		}		
		return tmpList;
	}
	public ZjQuestion getQuestion(String questionid){
		ZjQuestion question = new ZjQuestion();
		String hql = "from ZjQuestion where id='"+questionid+"'";
		List list = this.getHibernateTemplate().find(hql);
		list = this.loadQuestionData(list);
		if(list!=null&&!list.isEmpty()){
			question = (ZjQuestion)list.get(0);
		}
		return question;
	}
	public PageObject getQuestionObject(Map map, String currentPage) {
		// TODO Auto-generated method stub
		PageObject po = new PageObject();
		String counthql = "select count(id) from ZjQuestion where 1>0";
		String hql = "from ZjQuestion where 1>0";
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
        hql = hql +" order by lasteditdate desc,qtype asc";
		int page = 1;
		if(currentPage != null && !"".equals(currentPage)){
			page = Integer.parseInt(currentPage);
		}
		try {
			po = this.executePage(counthql, hql, page, Constants.pageSize);
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		ArrayList list = this.loadQuestionData(po.getDatas());
		po.setDatas(list);
		return po;
	}
	
	public PageObject getQuestionObject(Map map,String searchkey, String currentPage) {
		// TODO Auto-generated method stub
		PageObject po = new PageObject();
		String counthql = "select count(id) from ZjQuestion where 1>0";
		String hql = "from ZjQuestion where 1>0";
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
        if(searchkey!=null&&!searchkey.equals("")){
        	counthql = counthql + " and (questionKey like '%"+searchkey+"%' or title like '%"+searchkey+"%' or questioncontext like '%"+searchkey+"%' or memo like '%"+searchkey+"%')";
        	hql = hql + " and (questionKey like '%"+searchkey+"%' or title like '%"+searchkey+"%' or questioncontext like '%"+searchkey+"%' or memo like '%"+searchkey+"%')";
        }
        hql = hql +" order by qtype asc,lasteditdate desc";
		int page = 1;
		if(currentPage != null && !"".equals(currentPage)){
			page = Integer.parseInt(currentPage);
		}
		try {
			po = this.executePage(counthql, hql, page, Constants.pageSize);
		} catch (HibernateException e) {
			e.printStackTrace();
		}		
		ArrayList list = this.loadQuestionData(po.getDatas());
		po.setDatas(list);
		return po;
	}
	
	
	public PageObject getQuestionObject(String key,String qtype, String currentPage) {
		// TODO Auto-generated method stub
		PageObject po = new PageObject();
		String counthql = "select count(id) from ZjQuestion where 1>0";
		String hql = "from ZjQuestion where 1>0";
		if(qtype!=null&&!qtype.equals("")){
			counthql = counthql + " and qtype='"+qtype+"'";
			hql = hql + " and qtype='"+qtype+"'";
		}
        
        if(key!=null&&!key.equals("")){
        	counthql = counthql + " and (questionKey like '%"+key+"%' or title like '%"+key+"%' or questioncontext like '%"+key+"%' or memo like '%"+key+"%')";
        	hql = hql + " and (questionKey like '%"+key+"%' or title like '%"+key+"%' or questioncontext like '%"+key+"%' or memo like '%"+key+"%')";
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
		ArrayList list = this.loadQuestionData(po.getDatas());
		po.setDatas(list);
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
		ve.add("update ZjQuestion set delflag='1' where id in ('" + cd + "')");
		
		try {
			this.executeBatch(ve);
		} catch (RuntimeException e) {
			
			info = "删除失败:" + e.getMessage();
			throw e;
		}
		return info;
	}

	public String saveQuestion(ZjQuestion question) {
		// TODO Auto-generated method stub
		String id = "";
		if(question.getId()==null||question.getId().equals("")){
			id=this.getHibernateTemplate().save(question).toString();
		}
		else{
			id = question.getId();
			this.getHibernateTemplate().update(question);			
		}
		return id;
	}

	public String saveQuestionMatchingOption(ZjQuestionMatchingOption questionMatchingOption) {
		// TODO Auto-generated method stub
		String id = "";
		if(questionMatchingOption.getId()==null||questionMatchingOption.getId().equals("")){
			id=this.getHibernateTemplate().save(questionMatchingOption).toString();
		}
		else{
			id = questionMatchingOption.getId();
			this.getHibernateTemplate().update(questionMatchingOption);			
		}
		return id;
	}

	public String saveQuestionMatchingAnswer(ZjQuestionMatchingAnswer questionMatchingAnswer) {
		// TODO Auto-generated method stub
		String id = "";
		if(questionMatchingAnswer.getId()==null||questionMatchingAnswer.getId().equals("")){
			id=this.getHibernateTemplate().save(questionMatchingAnswer).toString();
		}
		else{
			id = questionMatchingAnswer.getId();
			this.getHibernateTemplate().update(questionMatchingAnswer);			
		}
		return id;
	}

	public String saveQuestionOption(ZjQuestionOption questionOption) {
		// TODO Auto-generated method stub
		String id = "";
		if(questionOption.getId()==null||questionOption.getId().equals("")){
			id=this.getHibernateTemplate().save(questionOption).toString();
		}
		else{
			id = questionOption.getId();
			this.getHibernateTemplate().update(questionOption);			
		}
		return id;
	}
	public ZjCourse getZjCourse(String id){
		ZjCourse course = new ZjCourse();
		String hql = "from ZjCourse where id='"+id+"'";
		List list = this.getHibernateTemplate().find(hql);
		if(list!=null&&!list.isEmpty()){
			course = (ZjCourse)list.get(0);
		}
		return course;
	}
	public ZjCourse getZjCourseByOId(String oid){
		ZjCourse course = null;
		String hql = "from ZjCourse where originId='"+oid+"'";
		List list = this.getHibernateTemplate().find(hql);
		if(list!=null&&!list.isEmpty()){
			course = (ZjCourse)list.get(0);
		}
		return course;
	}
	
	public static void main(String[] args) {
		int r=(int)(Math.random()*10);
		System.out.println(r);
	}
	
	public String removeQuestionOption( String question) {
		// TODO Auto-generated method stub
		String info = null;
		String hql = " from ZjQuestionOption where question='"+question+"'";
		try {
			this.getHibernateTemplate().deleteAll(this.getHibernateTemplate().find(hql));
			
		} catch (RuntimeException e) {
			
			info = "删除题目失败:" + e.getMessage();
			throw e;
		}
		return info;
	}
	public String removeMatchingOption( String question) {
		// TODO Auto-generated method stub
		String info = null;
		String hql = " from ZjQuestionMatchingOption where question='"+question+"'";
		try {
			this.getHibernateTemplate().deleteAll(this.getHibernateTemplate().find(hql));
			
		} catch (RuntimeException e) {
			
			info = "删除题目失败:" + e.getMessage();
			throw e;
		}
		return info;
	}
	public String removeMatchingAnswer( String question) {
		// TODO Auto-generated method stub
		String info = null;
		String hql = " from ZjQuestionMatchingAnswer where question='"+question+"'";
		try {
			this.getHibernateTemplate().deleteAll(this.getHibernateTemplate().find(hql));
			
		} catch (RuntimeException e) {
			
			info = "删除题目失败:" + e.getMessage();
			throw e;
		}
		return info;
	}
}