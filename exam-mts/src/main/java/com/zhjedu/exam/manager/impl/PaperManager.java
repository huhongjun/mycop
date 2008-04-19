package com.zhjedu.exam.manager.impl;

import java.util.List;
import java.util.Vector;

import com.zhjedu.exam.domain.ZjPaper;
import com.zhjedu.exam.manager.IPaperManager;
import com.zhjedu.util.BaseManager;
import com.zhjedu.util.Constants;
import com.zhjedu.util.PageObject;
import com.zhjedu.util.StringUtil;

public class PaperManager extends BaseManager implements
		IPaperManager {

	public PageObject getPaperList(String courseId, String name, int page){
		String counthql = "select count(id) from ZjPaper where course='" + courseId + "' and delflag='0'";
		String hql = "from ZjPaper where course='" + courseId + "' and delflag='0'";
		if(name != null && !"".equals(name)){
			counthql += " and name like '%" + name + "%'";
			hql += " and name like '%" + name + "%'";
		}
		hql += " order by id DESC";
		return this.executePage(counthql, hql, page, Constants.pageSize);
	}
	
	public void removePaper(String[] paperId){
		String hql = "update ZjPaper set delflag='1' where id in ('" + StringUtil.combineStringArray(paperId, ",").replaceAll(",", "','") + "')";
		this.execute(hql);
	}
	
	public ZjPaper getPaper(String paperId){
		ZjPaper paper = null;
		String hql = "from ZjPaper where id='" + paperId + "' and delflag='0'";
		List list = this.findObject(hql);
		if(list != null && list.size() > 0){
			paper = (ZjPaper)list.get(0);
		}
		return paper;
	}
	
	public List getPaperQuestion(String paperId){
		String hql = "from ZjQuizQuestion where paper='" + paperId + "'";
		return this.findObject(hql);
	}
	
	public void removeQuestion(String paperId, String[] questionId){
		String hql = "delete from ZjQuizQuestion where paper='" + paperId + "' and question.id in ('" + StringUtil.combineStringArray(questionId, ",").replaceAll(",", "','") + "')";
		this.execute(hql);
	}
	
	public List getQuestion(String[] questionId){
		String hql = "from ZjQuestion where id in ('" + StringUtil.combineStringArray(questionId, ",").replaceAll(",", "','") + "')";
		return this.findObject(hql);
	}
	
	public void savePaperQuestion(Vector paperQuestionList){
		this.executeBatch(paperQuestionList);
	}
	
	public void savePaper(ZjPaper paper){
		this.saveOrUpdateObject(paper);
	}
	
	public void savePaperStatus(String paperId, String status){
		String hql = "update ZjPaper set status='" + status + "' where id='" + paperId + "'";
		this.execute(hql);
	}
}
