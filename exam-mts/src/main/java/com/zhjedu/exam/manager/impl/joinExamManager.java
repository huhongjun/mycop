package com.zhjedu.exam.manager.impl;

import java.util.List;

import com.zhjedu.exam.domain.ZjManeuverList;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.domain.ZjQuizAnswers;
import com.zhjedu.exam.domain.ZjQuizExam;
import com.zhjedu.exam.manager.IJoinExamManager;
import com.zhjedu.exam.manager.IManeuverManager;
import com.zhjedu.util.BaseManager;

public class joinExamManager extends BaseManager implements
		IJoinExamManager {

	public ZjQuiz getQuizbyId(String quizId){
		String hql = "from ZjQuiz where id='" + quizId + "'";
		List list = this.findObject(hql);
		if(list != null && list.size() > 0){
			return (ZjQuiz)list.get(0);
		}else{
			return null;
		}
	}
	
	public ZjQuizExam getQuizExam(String userId, String quizId){
		//(status='0' or status='1')   0-未参加考试 1-已参加未考完
		String hql = "from ZjQuizExam where userid='" + userId + "' and quiz='" + quizId + "'";
		List list = this.findObject(hql);
		if(list != null && list.size() > 0){
			return (ZjQuizExam)list.get(0);
		}else{
			return null;
		}
	}
	
	public List getQuestionList(String quizId, String paperId){
		//parent='0' 一级题
		String hql = "from ZjQuizQuestion where quiz='" + quizId + "' and paper='" + paperId + "' and question.parent='0'";
		return this.findObject(hql);
	}
	
	public List getQuestionList(String quizId, int belongto){
		String hql = "from ZjQuizQuestion where quiz='" + quizId + "' and belongto=" + belongto + " and question.parent='0'";
		return this.findObject(hql);
	}
	
	public List getQuestionOptionList(String questionId){
		String hql = "from ZjQuestionOption where question='" + questionId + "'";
		return this.findObject(hql);
	}
	
	public List getQuestionMatchingOptionList(String questionId){
		String hql = "from ZjQuestionMatchingOption where question='" + questionId + "'";
		return this.findObject(hql);
	}
	
	public List getQuestionMatchingAnswerList(String questionId){
		String hql = "from ZjQuestionMatchingAnswer where question='" + questionId + "'";
		return this.findObject(hql);
	}
	
	public List getSonQuestionList(String questionId){
		String hql = "from ZjQuestion where parent='" + questionId + "'";
		return this.findObject(hql);
	}
	
	public void saveQuizAnswerList(List quizAnswerList){
		this.saveOrUpdateBatch(quizAnswerList);
	}
	
	public void removeQuizAnswer(String quizExamId){
		String hql = "delete from ZjQuizAnswers where exam='" + quizExamId + "'";
		this.execute(hql);
	}
	
	public void updateQuizExamStatus(String quizExamId, String status, boolean isStart){
		String hql = "update ZjQuizExam set status='" + status + "'";
		if(isStart){
			hql += ", timestart=" + System.currentTimeMillis();
		}else{
			hql += ", timefinish=" + System.currentTimeMillis();
		}
		hql += " where id='" + quizExamId + "'";
		this.execute(hql);
	}
	
	public void updateQuizExamStatus(String quizExamId, String status){
		String hql = "update ZjQuizExam set status='" + status + "'";
		hql += " where id='" + quizExamId + "'";
		this.execute(hql);
	}
	
	public void updateQuizExamScore(String quizExamId){
		String hql = "update ZjQuizExam set grade=(select sum(grade) from ZjQuizAnswers where exam='" + quizExamId + "')";
		hql += " where id='" + quizExamId + "'";
		this.execute(hql);
	}
	
	public ZjQuizAnswers getQuestionAnswer(String quizExamId, String questionId){
		String hql = "from ZjQuizAnswers where exam='" + quizExamId + "' and question='" + questionId + "'";
		List list = this.findObject(hql);
		if(list != null && list.size() > 0){
			return (ZjQuizAnswers)list.get(0);
		}else{
			return null;
		}
	}
	
	public void updateScore(String quizAnswerId, String score){
		String hql = "update ZjQuizAnswers set grade=" + score + " where id='" + quizAnswerId + "'";
		this.execute(hql);
	}
	
	public void updatePostil(String quizExamId, String postil){
		String hql = "update ZjQuizExam set postil='" + postil + "' where id='" + quizExamId + "'";
		this.execute(hql);
	}
	
	public List getQuizList(String quizId, String password){
		String hql = "from ZjQuiz where id='" + quizId + "' and password='" + password + "'";
		return this.findObject(hql);
	}
	
	
	//****************************************  */
	//以下是同步学生参加考试数据的一些方法  2008-04-05
	//***************************************** */
	public List getQuizMidECList(int examCourseId){
		String hql = "from ZjQuizMidEc where midExamId=" + examCourseId;
		return this.findObject(hql);
	}
	
	public boolean isQuizExamHave(String userId, String quizId){
		String hql = "from ZjQuizExam where quiz='" + quizId + "' and userid='" + userId + "'";
		List list = this.findObject(hql);
		if(list != null && list.size() > 0){
			return false;
		}else{
			return true;
		}
	}
	
	public void saveQuizExam(List quizExamList){
		this.saveOrUpdateBatch(quizExamList);
	}
}
