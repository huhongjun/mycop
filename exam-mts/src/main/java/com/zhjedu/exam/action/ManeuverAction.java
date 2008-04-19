package com.zhjedu.exam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhjedu.exam.domain.ZjManeuverList;

public class ManeuverAction extends BaseAction {
	/*
	 * 显示策略详细列表
	 */
	public ActionForward listManeuver(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String quizId = request.getParameter("quizId");		//考试/作业ID
		String courseId = ""; //课程ID 机考无效
		//生成了的章下拉列表HTML代码
		String chapterSelectOptionsHtml = this.getManeuverService().getChapterSelectOptionsHtml(courseId);
		//生成了的题型下拉列表HTML代码
		String questionSelectOptionsHtml = this.getManeuverService().getQuestionSelectOptionsHtml();
		List list = this.getManeuverService().getManeuverList(quizId);
//		this.getManeuverService().test();  //测试
		request.setAttribute("quizId", quizId);
		request.setAttribute("courseId", courseId);
		request.setAttribute("chapterSelectOptionsHtml", chapterSelectOptionsHtml);
		request.setAttribute("questionSelectOptionsHtml", questionSelectOptionsHtml);
		request.setAttribute("list", list);
		return mapping.findForward("listManeuver");
	}
	
	/*
	 * 保存策略详细
	 */
	public ActionForward saveManeuver(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String quizId = request.getParameter("quizId");		//考试/作业ID
		String scope = request.getParameter("scope");
		String questionType = request.getParameter("questionType");
		ZjManeuverList maneuver = new ZjManeuverList();
		maneuver.setQuizid(quizId);
		maneuver.setQuestionType(questionType);
		maneuver.setScope(scope);
		maneuver.setHard1Num(0L);
		maneuver.setHard2Num(0L);
		maneuver.setHard3Num(0L);
		maneuver.setHard4Num(0L);
		maneuver.setHard5Num(0L);
		maneuver.setNohardNum(0L);
		maneuver.setScore("0");
		maneuver.setStatus("0");
		if(this.getManeuverService().saveManeuver(maneuver)){
			return this.listManeuver(mapping, actionForm, request, response);
		}else{
			request.setAttribute("info", "加入策略时发生错误，可能是因为你所加入的策略信息已经存在。");
			return mapping.findForward("err_page");
		}
	}
	
	/*
	 * 更新策略详细
	 */
	public ActionForward updateManeuver(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String maneuverId = request.getParameter("maneuverId");
		if(this.getManeuverService().updateManeuver(maneuverId, request)){
			return this.listManeuver(mapping, actionForm, request, response);
		}else{
			request.setAttribute("info", "修改策略时发生错误，可能是该策略信息已经不存在。");
			return mapping.findForward("err_page");
		}
	}
	
	/*
	 * 完成策略详细
	 */
	public ActionForward finishManeuver(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String quizId = request.getParameter("quizId");
		if(this.getManeuverService().saveQuizQuestion(quizId)){
			return new ActionForward("/Quiz.do?method=list&quizType=2");
//			return this.listManeuver(mapping, actionForm, request, response);
		}else{
			request.setAttribute("info", "生成作业试题时发生错误，可能是由于题库题量不够，请检查题库和策略信息。");
			return mapping.findForward("err_page");
		}
	}
	
	/*
	 * 移除策略详细
	 */
	public ActionForward removeManeuver(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String maneuverId = request.getParameter("maneuverId");
		this.getManeuverService().removeManeuverbyId(maneuverId);
		return this.listManeuver(mapping, actionForm, request, response);
	}
	
	/*
	 * 根据默认策略生成详细策略
	 */
	public ActionForward createManeuver(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String quizId = request.getParameter("quizId");		//考试/作业ID
		String courseId = ""; //课程ID 机考无效
		this.getManeuverService().saveManeuverList(courseId, quizId);
		return this.listManeuver(mapping, actionForm, request, response);
	}
	
}