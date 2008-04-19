package com.zhjedu.exam.action;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dfcw.zjproject.zj.model.StudentModel;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.util.UserSession;
import com.zhjedu.util.UserSessionInfo;

public class joinExamAction extends BaseAction {
	public ActionForward check(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.studentIsLogin(request)){
			return mapping.findForward("studentLogin");
		}
		String quizId = request.getParameter("quizId");		//考试/作业ID
		String password = request.getParameter("password");
		if(this.getJoinExamService().checkPass(quizId, password)){
			return new ActionForward("/joinExam/exam.jsp?quizId=" + quizId);
		}else{
			request.setAttribute("info", "error");
			return mapping.findForward("info2");
//			return new ActionForward("/joinExam/exam.jsp?quizId=" + quizId);
		}
	}
	
	public ActionForward quizInfo(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.studentIsLogin(request)){
			return mapping.findForward("studentLogin");
		}
		String quizId = request.getParameter("quizId");		//考试/作业ID
		request.setAttribute("quiz", this.getJoinExamService().getQuizbyId(quizId));
		return mapping.findForward("quizInfo");
	}
	
	public ActionForward quizYulan(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.teacherIsLogin(request)){
			return mapping.findForward("login");
		}
		String quizId = request.getParameter("quizId");		//考试/作业ID
		ZjQuiz quiz = this.getJoinExamService().getQuizbyId(quizId);
		Hashtable rd = new Hashtable();
		if(quiz != null){
			if(quiz.getPaper() != null && !"".equals(quiz.getPaper())){
				rd = this.getJoinExamService().getQuestionbyQuizid(quiz, true);
			}else{
				rd = this.getJoinExamService().getQuestionbyQuizid(quiz, false);
			}
		}
		request.setAttribute("quizQuestion", rd);
		return mapping.findForward("quizYulan");
	}	
	
	public ActionForward quizQuestionInfo(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.studentIsLogin(request)){
			return mapping.findForward("studentLogin");
		}
		String quizId = request.getParameter("quizId");		//考试/作业ID
		String userid = "";
		StudentModel student = (StudentModel)request.getSession().getAttribute(UserSession.STUDENT_SESSION);
		if(student!=null){
			userid = String.valueOf(student.getStudentId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		ZjQuiz quiz = this.getJoinExamService().getQuizbyId(quizId);
		Hashtable rd = new Hashtable();
		if(quiz != null){
			if(quiz.getPaper() != null && !"".equals(quiz.getPaper())){
				rd = this.getJoinExamService().getQuestionbyQuizid(userid, quizId, true);
			}else{
				rd = this.getJoinExamService().getQuestionbyQuizid(userid, quizId, false);
			}
		}
		request.setAttribute("quizQuestion", rd);
		return mapping.findForward("quizQuestionInfo");
	}
	
	public ActionForward sbtExam(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.studentIsLogin(request)){
			return mapping.findForward("studentLogin");
		}
		String quizId = request.getParameter("quizId");		//考试/作业ID
		String userid = "";
		StudentModel student = (StudentModel)request.getSession().getAttribute(UserSession.STUDENT_SESSION);
		if(student!=null){
			userid = String.valueOf(student.getStudentId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		ZjQuiz quiz = this.getJoinExamService().getQuizbyId(quizId);
		if(quiz != null){
			if(quiz.getPaper() != null && !"".equals(quiz.getPaper())){
				this.getJoinExamService().saveExamScore(request, userid, quizId, true);
			}else{
				this.getJoinExamService().saveExamScore(request, userid, quizId, false);
			}
		}
		return mapping.findForward("info");
	}
	
	public ActionForward quizQuestionAnswerInfo(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.teacherIsLogin(request)){
			return mapping.findForward("login");
		}
		String quizId = request.getParameter("quizId");		//考试/作业ID
		String userId = request.getParameter("userId");
		ZjQuiz quiz = this.getJoinExamService().getQuizbyId(quizId);
		Hashtable rd = new Hashtable();
		if(quiz != null){
			if(quiz.getPaper() != null && !"".equals(quiz.getPaper())){
				rd = this.getJoinExamService().getQuestionAnswerbyQuizid(userId, quizId, true);
			}else{
				rd = this.getJoinExamService().getQuestionAnswerbyQuizid(userId, quizId, false);
			}
		}
		request.setAttribute("quizQuestion", rd);
		return mapping.findForward("quizQuestionAnswerInfo");
	}
	
	public ActionForward quizQuestionAnswer(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String quizId = request.getParameter("quizId");		//考试/作业ID
		String userId = request.getParameter("userId");
		ZjQuiz quiz = this.getJoinExamService().getQuizbyId(quizId);
		Hashtable rd = new Hashtable();
		if(quiz != null){
			if(quiz.getPaper() != null && !"".equals(quiz.getPaper())){
				rd = this.getJoinExamService().getQuestionbyQuizid2(userId, quizId, true);
			}else{
				rd = this.getJoinExamService().getQuestionbyQuizid2(userId, quizId, false);
			}
		}
		request.setAttribute("quizQuestion", rd);
		return mapping.findForward("quizQuestionAnswer");
	}
	
	public ActionForward saveScore(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.teacherIsLogin(request)){
			return mapping.findForward("login");
		}
		String quizAnswerId = request.getParameter("quizAnswerId");
		String score = request.getParameter("score");
		this.getJoinExamService().saveScorebyquizAnswerId(quizAnswerId, score);
		return mapping.findForward("info2");
	}
	
	public ActionForward savePostil(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.teacherIsLogin(request)){
			return mapping.findForward("login");
		}
		String quizExamId = request.getParameter("quizExamId");
		String postil = request.getParameter("postil");
		this.getJoinExamService().savePostilbyquizExamId(quizExamId, postil);
		return mapping.findForward("info2");
	}
	
	public ActionForward finish(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		if(!UserSessionInfo.teacherIsLogin(request)){
			return mapping.findForward("login");
		}
		String quizExamId = request.getParameter("quizExamId");
		this.getJoinExamService().saveFinish(quizExamId);
		request.setAttribute("info", "finish");
		return mapping.findForward("info2");
	}
}