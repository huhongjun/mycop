package com.zhjedu.exam.manager.impl;

import java.util.List;

import com.zhjedu.exam.domain.ZjManeuverList;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.manager.IManeuverManager;
import com.zhjedu.util.BaseManager;

public class ManeuverManager extends BaseManager implements
		IManeuverManager {

	public List getManeuver(){
		String hql = "from ZjManeuver where status='0' order by status ASC";
		List list = this.findObject(hql);
		return list;
	}
	
	public int getQuestionCountbyCourse(String courseId, String scope, String questionType){
		String hql = "select count(*) from ZjQuestion where parent='0' and status='0' and delflag='0'";
		if(questionType != null && !"0".equals(questionType)){
			hql += " and qtype='" + questionType + "'";
		}
		if(scope != null && !"".equals(scope)){
			hql += " and category in ('" + scope.replaceAll(",", "','") + "')";
		}
		List list = this.findObject(hql);
		if(list != null && list.size() > 0){
			Object obj = (Object)list.get(0);
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	public List getQuestionListbyCourse(String courseId, String scope, String questionType){
		String hql = "from ZjQuestion where parent='0' and status='0' and delflag='0'";
		if(questionType != null && !"0".equals(questionType)){
			hql += " and qtype='" + questionType + "'";
		}
		if(scope != null && !"".equals(scope)){
			hql += " and category in ('" + scope.replaceAll(",", "','") + "')";
		}
		List list = this.findObject(hql);
		return list;
	}
	
	public void saveManeuverList(List list){
		this.saveOrUpdateBatch(list);
	}
	
	public List getManeuverListbyQuiz(String quizId){
		String hql = "from ZjManeuverList where status='0' and quizid='" + quizId + "'";
		List list = this.findObject(hql);
		return list;
	}
	
	public int getQuestionCount(String scope, String questionType, double difficult_s, double difficult_e){
		String hql = "select count(*) from ZjQuestion where parent='0' and status='0' and delflag='0' and qtype='" + questionType + "' and category='" + scope + "'";
		if(difficult_s > 0){
			hql += " and difficulty>='" + difficult_s + "'";
		}
		if(difficult_e > 0){
			hql += " and difficulty<='" + difficult_e + "'";
		}
		List list = this.findObject(hql);
		if(list != null && list.size() > 0){
			Object obj = (Object)list.get(0);
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	public List getQuestion(String scope, String questionType, double difficult_s, double difficult_e){
		String hql = "from ZjQuestion where parent='0' and status='0' and delflag='0' and qtype='" + questionType + "' and category='" + scope + "'";
		if(difficult_s > 0){
			hql += " and difficulty>='" + difficult_s + "'";
		}
		if(difficult_e > 0){
			hql += " and difficulty<='" + difficult_e + "'";
		}
		List list = this.findObject(hql);
		return list;
	}
	
	public void saveQuizQuestionList(List list){
		this.saveOrUpdateBatch(list);
	}
	
	public List getChapterbyCourse(String courseId){
		String hql = "from ZjQuestionCategory where delflag='0' and isleaf='1'";
		List list = this.findObject(hql);
		return list;
	}
	
	public List getManeuverList(String quizId){
		String hql = "select" +
				" zml.id," +
				" zml.quizid," +
				" zml.maneuverCode," +
				" zml.scope," +
				" (select name from ZjQuestionCategory where delflag='0' and id=zml.scope) as chapterName," +
				" zml.questionType," +
				" zml.hard1Num," +
				" (select count(id) from ZjQuestion where parent='0' and status='0' and delflag='0' and qtype=zml.questionType and category=zml.scope and difficulty>='0.1' and difficulty<='0.2') as hard1TotalNum," +
				" zml.hard2Num," +
				" (select count(id) from ZjQuestion where parent='0' and status='0' and delflag='0' and qtype=zml.questionType and category=zml.scope and difficulty>='0.3' and difficulty<='0.4') as hard2TotalNum," +
				" zml.hard3Num," +
				" (select count(id) from ZjQuestion where parent='0' and status='0' and delflag='0' and qtype=zml.questionType and category=zml.scope and difficulty>='0.5' and difficulty<='0.6') as hard3TotalNum," +
				" zml.hard4Num," +
				" (select count(id) from ZjQuestion where parent='0' and status='0' and delflag='0' and qtype=zml.questionType and category=zml.scope and difficulty>='0.7' and difficulty<='0.8') as hard4TotalNum," +
				" zml.hard5Num," +
				" (select count(id) from ZjQuestion where parent='0' and status='0' and delflag='0' and qtype=zml.questionType and category=zml.scope and difficulty>='0.9' and difficulty<='1.0') as hard5TotalNum," +
				" zml.nohardNum," +
				" (select count(id) from ZjQuestion where parent='0' and status='0' and delflag='0' and qtype=zml.questionType and category=zml.scope) as nohardTotalNum," +
				" zml.score," +
				" zml.status" +
				" from ZjManeuverList as zml where zml.status='0' and zml.quizid='" + quizId + "'";
		List list = this.findObject(hql);
		return list;
	}
	
	public void removeManeuverbyId(String maneuverId){
		this.execute("delete from ZjManeuverList where id='" + maneuverId + "'");
	}
	
	public void saveManeuverList(ZjManeuverList maneuver){
		this.saveOrUpdateObject(maneuver);
	}
	
	public List getManeuverListbyScopeAndQuestiontype(String quizId, String scope, String questionType){
		String hql = "from ZjManeuverList where quizid='" + quizId + "' and scope='" + scope + "' and questionType='" + questionType + "'";
		List list = this.findObject(hql);
		return list;
	}
	
	public ZjManeuverList getManeuverbyId(String maneuverId){
		List list = this.findObject("from ZjManeuverList where id='" + maneuverId + "'");
		if(list != null && list.size() > 0){
			return (ZjManeuverList)list.get(0);
		}else{
			return null;
		}
	}
	
	public void removeManeuverList(String quizId){
		String hql = "delete from ZjManeuverList where quizid='" + quizId + "'";
		this.execute(hql);
	}
	
	public ZjQuiz getQuizbyId(String quizId){
		String hql = "from ZjQuiz where id='" + quizId + "'";
		List list = this.findObject(hql);
		if(list != null && list.size() > 0){
			return (ZjQuiz)list.get(0);
		}else{
			return null;
		}		
	}
	
	public void removeQuizQuestion(String quizId){
		this.execute("delete from ZjQuizQuestion where quiz='" + quizId + "'");
	}
}
