package com.zhjedu.exam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhjedu.exam.domain.ZjPaper;

public class PaperAction extends BaseAction {
	//固定试卷列表
	public ActionForward listPaper(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String courseId = request.getParameter("courseId");		//课程ID
		String name = request.getParameter("tname");
		if(name == null){
			name = "";
		}
		String currentPage = request.getParameter("page");
		int page = 1;
		if(currentPage != null && !"".equals(currentPage))
			page = Integer.parseInt(currentPage);
		request.setAttribute("po", this.getPaperService().getPaperList(courseId, name, page));
		request.setAttribute("courseId", courseId);
		request.setAttribute("name", name);
		return mapping.findForward("listPaper");
	}
	//移除固定试卷
	public ActionForward removePaper(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String[] paperId = request.getParameterValues("id");
		this.getPaperService().removePaper(paperId);
		return listPaper(mapping, actionForm, request, response);
	}
	//添加固定试卷页面
	public ActionForward addPaper(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String courseId = request.getParameter("courseId");		//课程ID
		request.setAttribute("courseId", courseId);
		return mapping.findForward("addPaper");
	}
	//编辑固定试卷页面
	public ActionForward editPaper(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String paperId = request.getParameter("id");
		ZjPaper paper = this.getPaperService().getPaper(paperId);
		if(paper == null){
			return mapping.findForward("error");	//返回错误
		}else{
			String courseId = request.getParameter("courseId");		//课程ID
			request.setAttribute("courseId", courseId);
			request.setAttribute("paper", paper);
			return mapping.findForward("editPaper");
		}
	}
	//保存添加的固定试卷
	public ActionForward savePaper(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		this.getPaperService().savePaper(request);		
		return listPaper(mapping, actionForm, request, response);
	}
	//更新固定试卷
	public ActionForward updatePaper(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		this.getPaperService().updatePaper(request);
		return listPaper(mapping, actionForm, request, response);
	}
	//停用固定试卷
	public ActionForward stopPaper(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String paperId = request.getParameter("id");
		this.getPaperService().stopPaper(paperId);
		return listPaper(mapping, actionForm, request, response);
	}
	//发布固定试卷
	public ActionForward pubPaper(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String paperId = request.getParameter("id");
		boolean t = this.getPaperService().pubPaper(paperId);
		if(t == true){
			return listPaper(mapping, actionForm, request, response);
		}else{
			request.setAttribute("info", "pubError");
			request.setAttribute("courseId", request.getParameter("courseId"));
			return mapping.findForward("error");
		}
		
	}
	//固定试卷下试题列表
	public ActionForward listQuestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String paperId = request.getParameter("id");
		request.setAttribute("quizQuestion", this.getPaperService().getPaperQuestion(paperId));
		return mapping.findForward("listQuestion");
	}
	//移除固定试卷下试题
	public ActionForward removeQuestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String paperId = request.getParameter("id");
		String[] questionId = request.getParameterValues("questionId");
		this.getPaperService().removeQuestion(paperId, questionId);
		return listQuestion(mapping, actionForm, request, response);
	}
	//保存试卷和试题关系(即为试卷添加试题)
	public ActionForward savePaperQuestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String paperId = request.getParameter("id");					//试卷ID
		String[] questionId = request.getParameterValues("questionId");	//试题ID数组
		String _score = request.getParameter("score");					//每题分值(为空则为0分)
		int score = 0;
		if(_score != null && !"".equals(_score)){
			score = Integer.parseInt(_score);
		}
		this.getPaperService().savePaperQuestion(paperId, questionId, score);
		return listQuestion(mapping, actionForm, request, response);
	}
}