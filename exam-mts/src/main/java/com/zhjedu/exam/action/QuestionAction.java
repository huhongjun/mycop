package com.zhjedu.exam.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dfcw.zjproject.zj.model.TeacherModel;
import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuestionCategory;
import com.zhjedu.exam.domain.ZjQuestionMatchingAnswer;
import com.zhjedu.exam.domain.ZjQuestionMatchingOption;
import com.zhjedu.exam.domain.ZjQuestionOption;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.util.PageObject;
import com.zhjedu.util.StringUtil;
import com.zhjedu.util.UserSession;

public class QuestionAction extends BaseAction {
	public ActionForward list(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("delflag", "0");
		map.put("parent", "0");
		
		String course = request.getParameter("course");
		
		String parent = request.getParameter("parent");
		
		
		String qtype= request.getParameter("qtype");
		String category = request.getParameter("category");
		
		String catName = " ";
		String isleaf = "0";
		
		
		if(category!=null&&!category.equals("")){
			String nodeid = category;
			if(request.getParameter("nodeid")!=null&&!request.getParameter("nodeid").equals("")){
				nodeid = request.getParameter("nodeid");
			}
			ZjQuestionCategory tmp = this.getQuestionService().getZjQuestionCategory(nodeid);
			
			if(tmp!=null){
				catName = tmp.getName();
				isleaf = tmp.getIsleaf();
			}
		}
		
		
		if(parent!=null&&!parent.equals("")){
			map.put("parent", parent);
			request.setAttribute("parent", "0");
		}
		
		
		request.setAttribute("catName",catName);
		request.setAttribute("isleaf", isleaf);
		if(qtype!=null&&!qtype.equals("")){
			map.put("qtype", qtype);
			request.setAttribute("qtype", qtype);
		}
		if(category!=null&&!category.equals("")){
			map.put("category", category);
			request.setAttribute("category", category);
		}
		
		PageObject po = this.getQuestionService().getQuestionObject(map,page);
		
		request.setAttribute("po", po);
		return mapping.findForward("list");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		
		String key = request.getParameter("key");
		String page = request.getParameter("page");
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("delflag", "0");
		map.put("parent", "0");
		
		String course = request.getParameter("course");
		
		
		String parent = request.getParameter("parent");
		String chapter = request.getParameter("chapter");
		String section = request.getParameter("section");
		
		String qtype= request.getParameter("qtype");
		String category = request.getParameter("category");
		String subject = request.getParameter("subject");
		String catName = " ";
		if(course!=null&&!course.equals("")){
			String nodeid = course;
			if(request.getParameter("nodeid")!=null&&!request.getParameter("nodeid").equals("")){
				nodeid = request.getParameter("nodeid");
			}
			ZjCourse tmp = this.getQuestionService().getZjCourse(nodeid);
			if(tmp!=null){
				catName = tmp.getName();
			}
		}
		
		
		if(category!=null&&!category.equals("")){
			String nodeid = category;
			if(request.getParameter("nodeid")!=null&&!request.getParameter("nodeid").equals("")){
				nodeid = request.getParameter("nodeid");
			}
			ZjQuestionCategory tmp = this.getQuestionService().getZjQuestionCategory(nodeid);
			
			if(tmp!=null){
				catName = tmp.getName();
			}
		}
		
		if(course!=null&&!course.equals("")){
			
			map.put("course", course);
			request.setAttribute("course", course);
		}
		if(parent!=null&&!parent.equals("")){
			map.put("parent", parent);
			request.setAttribute("parent", parent);
		}
		if(chapter!=null&&!chapter.equals("")){
			map.put("chapter", chapter);
			request.setAttribute("chapter", chapter);
		}
		if(section!=null&&!section.equals("")){
			map.put("section", section);
			request.setAttribute("section", section);
		}
		
		request.setAttribute("catName",catName);

		if(qtype!=null&&!qtype.equals("")){
			map.put("qtype", qtype);
			request.setAttribute("qtype", qtype);
		}
		if(category!=null&&!category.equals("")){
			map.put("category", category);
			request.setAttribute("category", category);
		}
		if(subject!=null&&!subject.equals("")){
			map.put("subject", subject);
			request.setAttribute("subject", subject);
		}
		PageObject po = this.getQuestionService().getQuestionObject(map,key,page);
		//PageObject po = this.getQuestionService().getQuestionObject(key,qtype,page);
		request.setAttribute("qtype", qtype);
		request.setAttribute("key", key);
		request.setAttribute("po", po);
		return mapping.findForward("list");
	}
	
	public ActionForward listChild(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		
		String page = request.getParameter("page");
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("delflag", "0");
		
		
		String parent = (String)request.getAttribute("parent");

		if(parent!=null&&!parent.equals("")){
			map.put("parent", parent);
		}
		ZjQuestion tmp = this.getQuestionService().getQuestion(parent);
		PageObject po = this.getQuestionService().getQuestionObject(map,page);
		request.setAttribute("question", tmp);
		request.setAttribute("content", tmp.getQuestioncontext());
		request.setAttribute("parent", parent);
		request.setAttribute("po", po);
		return mapping.findForward("listchild");
	}
	
	public ActionForward addsubquestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String parent = request.getParameter("parent");
		request.setAttribute("parent", parent);
		return mapping.findForward("addsubquestion");
	}
	public ActionForward add(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		ZjQuestion question = (ZjQuestion)actionForm;
		String course = request.getParameter("course");
		
		String chapter = request.getParameter("chapter");
		String section = request.getParameter("section");
		String qtype = request.getParameter("qtype");
		if(qtype.equals("1")){
			request.setAttribute("qname", "单选题");
			
		}
		else if(qtype.equals("2")){
			request.setAttribute("qname", "多选题");
		
		}
		
		else if(qtype.equals("3")){
			request.setAttribute("qname","判断题");
			
		}
		else if(qtype.equals("4")){
			request.setAttribute("qname", "匹配题");
			
		}
		else if(qtype.equals("5")){
			request.setAttribute("qname","选择填空题");
			
		}
		else if(qtype.equals("6")){
			request.setAttribute("qname","填空题");
			
		}
		else if(qtype.equals("7")){
			request.setAttribute("qname", "简答题");
			
		}
		else if(qtype.equals("9")){
			request.setAttribute("qname","综合题");
			
		}
		else{
			
		}
		request.setAttribute("course", course);
		request.setAttribute("chapter", chapter);
		request.setAttribute("section", section);
		request.setAttribute("qtype", qtype);
		request.setAttribute("question", question);
		return mapping.findForward("add");
	}
	public ActionForward addQuestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		ZjQuestion question = (ZjQuestion)actionForm;
		String questionid = request.getParameter("id");
		
		if(questionid!=null&&!questionid.equals("")){
			question = this.getQuestionService().getQuestion(questionid);
			request.setAttribute("questionid", questionid);
		}
		String qtype = question.getQtype();
		request.setAttribute("question", question);
		if(qtype.equals("1")){
			request.setAttribute("qname", "单选题");
			return mapping.findForward("addsinglechoice");
		}
		else if(qtype.equals("2")){
			request.setAttribute("qname","多选题");
			return mapping.findForward("addmultichoice");
		}
		
		else if(qtype.equals("3")){
			request.setAttribute("qname","判断题");
			return mapping.findForward("addjudge");
		}
		else if(qtype.equals("4")){
			request.setAttribute("qname","匹配题");
			return mapping.findForward("addmatching");
		}
		else if(qtype.equals("5")){
			request.setAttribute("qname","选择填空题");
			return mapping.findForward("addchoicefill");
		}
		else if(qtype.equals("6")){
			request.setAttribute("qname","填空题");
			return mapping.findForward("addinputfill");
		}
		else if(qtype.equals("7")){
			request.setAttribute("qname","简答题");
			return mapping.findForward("addanswer");
		}
		else if(qtype.equals("9")){
			request.setAttribute("qname","综合题");
			String page = request.getParameter("page");
			Map<String, Serializable> map = new HashMap<String, Serializable>();
			map.put("delflag", "0");
			
			
			
			map.put("parent", questionid);
			
			ZjQuestion tmp = this.getQuestionService().getQuestion(questionid);
			PageObject po = this.getQuestionService().getQuestionObject(map,page);
			request.setAttribute("content", tmp.getQuestioncontext());
			request.setAttribute("parent", questionid);
			request.setAttribute("po", po);
			
			return mapping.findForward("addintegrate");
		}
		else{
			return mapping.findForward("integrate");
		}
		
		
	}
	
	public ActionForward addSubQuestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		ZjQuestion question = (ZjQuestion)actionForm;
		String questionid = request.getParameter("questionid");
		
		if(questionid!=null&&!questionid.equals("")){
			question = this.getQuestionService().getQuestion(questionid);
			request.setAttribute("questionid", questionid);
		}
		String qtype = request.getParameter("qtype");
		request.setAttribute("question", question);
		if(qtype.equals("1")){
			request.setAttribute("qname", "单选题");
			return mapping.findForward("addsubsinglechoice");
		}
		else if(qtype.equals("2")){
			request.setAttribute("qname","多选题");
			return mapping.findForward("addsubmultichoice");
		}
		
		else if(qtype.equals("3")){
			request.setAttribute("qname","判断题");
			return mapping.findForward("addsubjudge");
		}
		else if(qtype.equals("4")){
			request.setAttribute("qname","匹配题");
			return mapping.findForward("addsubmatching");
		}
		else if(qtype.equals("5")){
			request.setAttribute("qname","选择填空题");
			return mapping.findForward("addsubchoicefill");
		}
		else if(qtype.equals("6")){
			request.setAttribute("qname","填空题");
			return mapping.findForward("addsubinputfill");
		}
		else if(qtype.equals("7")){
			request.setAttribute("qname","简答题");
			return mapping.findForward("addsubanswer");
		}
		
		else{
			return mapping.findForward("");
		}
		
		
	}
	
	
	public ActionForward saveIntegrate(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		ZjQuestion question = (ZjQuestion)actionForm;
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String questionId = "0";
		if(request.getParameter("questionId")!=null&&!request.getParameter("questionId").equals("")){
			questionId = request.getParameter("questionId");
		}
		question.setParent(questionId);
		if(question.getId()==null){
			question.setCreatedate(System.currentTimeMillis());
		}
		String questioncontext = request.getParameter("questioncontext");
		if(questioncontext==null){
			questioncontext = "";
		}
		question.setQuestioncontext(questioncontext);
		question.setLasteditdate(System.currentTimeMillis());
		question.setCreator(userid);
		question.setDelflag("0");
		question.setLasteditor(userid);
		question.setLastusedate(new Long(0));
		
		question.setShuffleoptions("0");
		question.setStatus("0");
		question.setRightRate(0.00);
		question.setUsetime(new Long(0));
		
		String questionid = this.getQuestionService().saveQuestion(question);
		String actiontype = "";
		if(request.getParameter("actiontype")!=null){
			actiontype = request.getParameter("actiontype");
		}
		request.setAttribute("question", question);
		request.setAttribute("content",questioncontext );
		request.setAttribute("parent", questionid);
		if(actiontype.equals("1")){
			return mapping.findForward("addchild");
			}
		if(actiontype.equals("2")){
			return listChild(mapping, actionForm, request, response);
		}
		return null;
		
	}
	public ActionForward save(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		ZjQuestion question = (ZjQuestion)actionForm;
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String parent = "0";
		if(request.getParameter("parent")!=null&&!request.getParameter("parent").equals("")){
			parent = request.getParameter("parent");
		}
		question.setParent(parent);
		if(question.getId()==null){
			question.setCreatedate(System.currentTimeMillis());
		}
		String questioncontext = request.getParameter("questioncontext");
		if(questioncontext==null){
			questioncontext = "";
		}
		question.setQuestioncontext(questioncontext);
		question.setLasteditdate(System.currentTimeMillis());
		question.setCreator(userid);
		question.setDelflag("0");
		question.setLasteditor(userid);
		question.setLastusedate(new Long(0));
		
		question.setShuffleoptions("0");
		question.setStatus("0");
		question.setRightRate(0.00);
		question.setUsetime(new Long(0));
		String[] answer = request.getParameterValues("answer");
		if(answer!=null&&answer.length>0){
			question.setAnswers(StringUtil.combineStringArray(answer, ","));
		}
		String[] optionContent = request.getParameterValues("optionContent");
		String[] optionid = request.getParameterValues("optionid");
		if(optionid==null){
			optionid = new String[] {};
		}
		String[] moptionid = request.getParameterValues("moptionid");
		if(moptionid==null){
			moptionid = new String[] {};
		}
		String[] manswerid = request.getParameterValues("manswerid");
		if(manswerid==null){
			manswerid = new String[] {};
		}
		String[] matchingOptions = request.getParameterValues("matchingOptions");
		String[] matchingAnswers = request.getParameterValues("matchingAnswers");
		String[] QuestionFlag = {"A", "B", "C", "D", "E", "F","G","H","I","J"};
		String[] optionanswer = request.getParameterValues("optionanswer");
	    if(optionContent != null && optionContent.length > 0){
	    	ArrayList optionList = new ArrayList();	
	    	if(question.getId()!=null){
	    		this.getQuestionService().removeQuestionOption(question.getId());
			}
	    	
	    	
	    	for(int i = 0; i < optionContent.length; i ++){
	    		ZjQuestionOption option = new ZjQuestionOption();
	    		option.setOptioncontext(optionContent[i]);
	    		option.setViewflag(QuestionFlag[i]);	    		
	    		optionList.add(option);
	    	}
	    	question.setOptionList(optionList);
	    }
	    if(matchingOptions != null && matchingOptions.length > 0){
	    	ArrayList matchingOptionList = new ArrayList();	 
	    	String questionanswer = "";
	    	if(question.getId()!=null){
	    		this.getQuestionService().removeMatchingOption(question.getId());
			}
	    	
	    	for(int i = 0; i < matchingOptions.length; i ++){
	    		ZjQuestionMatchingOption matchingOption = new ZjQuestionMatchingOption();
	    		matchingOption.setOptioncontent(matchingOptions[i]);
	    		matchingOption.setAnswer(optionanswer[i]);
	    		if(questionanswer.equals("")){
	    			questionanswer = optionanswer[i];
	    		}
	    		else{
	    			questionanswer = questionanswer + "," + optionanswer[i];
	    		}
	    		matchingOptionList.add(matchingOption);
	    	}
	    	question.setMatchingOptionList(matchingOptionList);
	    	question.setAnswers(questionanswer);
	    }
	    if(matchingAnswers != null && matchingAnswers.length > 0){
	    	ArrayList matchingAnswerList = new ArrayList();	    	
	    	if(question.getId()!=null){
	    		this.getQuestionService().removeMatchingAnswer(question.getId());
			}
	    	
	    	for(int i = 0; i < matchingAnswers.length; i ++){
	    		ZjQuestionMatchingAnswer matchingAnswer = new ZjQuestionMatchingAnswer();
	    		matchingAnswer.setAnswercontext(matchingAnswers[i]);	
	    		matchingAnswer.setViewflag(QuestionFlag[i]);
	    		matchingAnswerList.add(matchingAnswer);
	    		
	    	}
	    	question.setMatchingAnswerList(matchingAnswerList);
	    }
		String questionid = this.getQuestionService().saveQuestion(question);
		
		return list(mapping, actionForm, request, response);
	}
	public ActionForward edit(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String questionid=request.getParameter("questionid");
		ZjQuestion question = this.getQuestionService().getQuestion(questionid);
		request.setAttribute("course", question.getCourse());
		request.setAttribute("chapter", question.getChapter());
		request.setAttribute("section", question.getSection());
		request.setAttribute("category", question.getCategory());
		String qtype = question.getQtype();
		request.setAttribute("qtype", qtype);
		request.setAttribute("question", question);
		if(qtype.equals("1")){
			request.setAttribute("qname","单选题");
			
		}
		else if(qtype.equals("2")){
			request.setAttribute("qname","多选题");
		
		}
		
		else if(qtype.equals("3")){
			request.setAttribute("qname","判断题");
			
		}
		else if(qtype.equals("4")){
			request.setAttribute("qname","匹配题");
			
		}
		else if(qtype.equals("5")){
			request.setAttribute("qname","选择填空题");
			
		}
		else if(qtype.equals("6")){
			request.setAttribute("qname","填空题");
			
		}
		else if(qtype.equals("7")){
			request.setAttribute("qname","简答题");
			
		}
		else if(qtype.equals("9")){
			request.setAttribute("qname","综合题");
			
		}
		else{
			
		}

		return mapping.findForward("add");
		
	}
	public ActionForward savesubquestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		ZjQuestion question = (ZjQuestion)actionForm;
		String userid = "";
		System.out.println(">>>>>>>>>>>>>"+question.getQuestionNum());
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String parent = "0";
		if(request.getParameter("parent")!=null&&!request.getParameter("parent").equals("")){
			parent = request.getParameter("parent");
		}
		if(request.getAttribute("parent")!=null){
			parent = (String)request.getAttribute("parent");
		}
		ZjQuestion tmp = this.getQuestionService().getQuestion(parent);
		question.setParent(parent);
		if(question.getId()==null){
			question.setCreatedate(System.currentTimeMillis());
		}
		question.setLasteditdate(System.currentTimeMillis());
		question.setCreator(userid);
		question.setDelflag("0");
		question.setLasteditor(userid);
		question.setLastusedate(new Long(0));
		
		question.setShuffleoptions("0");
		question.setStatus("0");
		question.setRightRate(0.00);
		question.setUsetime(new Long(0));
		question.setAbility(tmp.getAbility());
		question.setCategory(tmp.getCategory());
		question.setChapter(tmp.getChapter());
		question.setSection(tmp.getSection());
		question.setCourse(tmp.getCourse());
		question.setDifficulty(tmp.getDifficulty());
		question.setDistinguish(tmp.getDistinguish());
		question.setKnowledge(tmp.getKnowledge());
		question.setSource(tmp.getSource());
		String questioncontext = request.getParameter("questioncontext");
		if(questioncontext==null){
			questioncontext = "";
		}
		question.setQuestioncontext(questioncontext);
		String[] answer = request.getParameterValues("answer");
		if(answer!=null&&answer.length>0){
			question.setAnswers(StringUtil.combineStringArray(answer, ","));
		}
		String[] optionid = request.getParameterValues("optionid");
		if(optionid==null){
			optionid = new String[] {};
		}
		String[] moptionid = request.getParameterValues("moptionid");
		if(moptionid==null){
			moptionid = new String[] {};
		}
		String[] manswerid = request.getParameterValues("manswerid");
		if(manswerid==null){
			manswerid = new String[] {};
		}
		String[] optionContent = request.getParameterValues("optionContent");
		String[] matchingOptions = request.getParameterValues("matchingOptions");
		String[] matchingAnswers = request.getParameterValues("matchingAnswers");
		String[] QuestionFlag = {"A", "B", "C", "D", "E", "F","G","H","I","J"};
		String[] optionanswer = request.getParameterValues("optionanswer");
		if(optionContent != null && optionContent.length > 0){
	    	ArrayList optionList = new ArrayList();	    	
	    	if(question.getId()!=null){
	    		this.getQuestionService().removeQuestionOption(question.getId());
			}
	    	
	    	for(int i = 0; i < optionContent.length; i ++){
	    		ZjQuestionOption option = new ZjQuestionOption();
	    		option.setOptioncontext(optionContent[i]);
	    		option.setViewflag(QuestionFlag[i]);	    		
	    		optionList.add(option);
	    	}
	    	question.setOptionList(optionList);
	    }
	    if(matchingOptions != null && matchingOptions.length > 0){
	    	ArrayList matchingOptionList = new ArrayList();	 
	    	String questionanswer = "";
	    	if(question.getId()!=null){
	    		this.getQuestionService().removeMatchingOption(question.getId());
			}
	    	
	    	for(int i = 0; i < matchingOptions.length; i ++){
	    		ZjQuestionMatchingOption matchingOption = new ZjQuestionMatchingOption();
	    		matchingOption.setOptioncontent(matchingOptions[i]);
	    		matchingOption.setAnswer(optionanswer[i]);
	    		if(questionanswer.equals("")){
	    			questionanswer = optionanswer[i];
	    		}
	    		else{
	    			questionanswer = questionanswer + "," + optionanswer[i];
	    		}
	    		matchingOptionList.add(matchingOption);
	    	}
	    	question.setMatchingOptionList(matchingOptionList);
	    	question.setAnswers(questionanswer);
	    }
	    if(matchingAnswers != null && matchingAnswers.length > 0){
	    	ArrayList matchingAnswerList = new ArrayList();	    	
	    	if(question.getId()!=null){
	    		this.getQuestionService().removeMatchingAnswer(question.getId());
			}
	    	
	    	for(int i = 0; i < matchingAnswers.length; i ++){
	    		ZjQuestionMatchingAnswer matchingAnswer = new ZjQuestionMatchingAnswer();
	    		matchingAnswer.setAnswercontext(matchingAnswers[i]);	
	    		matchingAnswer.setViewflag(QuestionFlag[i]);
	    		matchingAnswerList.add(matchingAnswer);
	    		
	    	}
	    	question.setMatchingAnswerList(matchingAnswerList);
	    }
		String questionid = this.getQuestionService().saveQuestion(question);
		request.setAttribute("parent", parent);
		
		return listChild(mapping, actionForm, request, response);
	}
	
	public ActionForward leftTree(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String course = request.getParameter("course");
		String treeStr = this.getMb().generateCourseTree("mainFrame", request.getContextPath()+"/Question.do?method=list", course,request.getContextPath());
		request.setAttribute("treeStr", treeStr);
		request.setAttribute("course",course);
		request.setAttribute("title","课考目录");
		request.setAttribute("category", "");
		return mapping.findForward("leftTree");
	}
	
	public ActionForward leftCatTree(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String category = request.getParameter("category");
		String treeStr = this.getMb().generateCategoryTree("mainFrame", request.getContextPath()+"/Question.do?method=list", category,request.getContextPath());
		request.setAttribute("treeStr", treeStr);
		request.setAttribute("category",category);
		request.setAttribute("course", "");
		request.setAttribute("title","机考目录");
		return mapping.findForward("leftTree");
	}
	
	public ActionForward main(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String course = "";
		if(request.getParameter("course")!=null){
			course = request.getParameter("course");
		}
		String courseid = "";
		String rightAction = "";
		ZjCourse tmp = new ZjCourse();
		if(course!=null&&!course.equals("")){
			 tmp = this.getQuestionService().getZjCourseByOId(course);
			 if(tmp==null){
				 tmp = this.getQuestionService().getZjCourse(course);
			 }
			 courseid = tmp.getId();
			 rightAction = request.getContextPath()+"/Question.do?method=list&course="+courseid;
			 
		}
		else{
			rightAction = request.getContextPath()+"/Quiz.do?method=listCourse";
		}
		
		String leftAction = request.getContextPath()+"/Question.do?method=leftTree&course="+courseid;
		
		request.setAttribute("leftAction", leftAction);
		request.setAttribute("rightAction",rightAction);
		request.setAttribute("category", "");
		return mapping.findForward("main");
	}
	
	public ActionForward main_category(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		ZjQuestionCategory root = this.getQuestionService().getRootCategory();
		String category = "";
		if(root!=null){
			category = root.getId();
		}
		String leftAction = request.getContextPath()+"/Question.do?method=leftCatTree&category="+category+"&course=";
		String rightAction = request.getContextPath()+"/Question.do?method=list&category="+category+"&course=";
		request.setAttribute("leftAction", leftAction);
		request.setAttribute("rightAction",rightAction);
		return mapping.findForward("main");
	}
	public ActionForward remove(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String[] questionid = request.getParameterValues("questionid");
		String info = this.getQuestionService().removeQuestion(questionid);
		if(info != null){
			request.setAttribute("info", info);
			return mapping.findForward("Exception");
		}
		
		return list(mapping, actionForm, request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ActionForward listassignemnt(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		//String[] questionid = request.getParameterValues("questionid");
		//String info = this.getQuestionService().removeQuestion(questionid);
		//		分页
		
		//String name=request.getParameter("name");
		
		/*String userid="8";
		String status="0";
		String page="8";
		PageObject po = this.getAss().getAssignmentObject(userid,status, page);
		request.setAttribute("po", po);*/
		
	    String sql="from ZjQuiz where delflag='0'";
	    ArrayList list;
	  
	    try
	    {
	    	list=this.getAss().getAssignment(sql);
	    	
			if(list.size()>-1){
				request.setAttribute("list", list);
					
				return mapping.findForward("assignment_list");
			}else
				return null;
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
		
		
		
		/*else
		{
			String sql="from ZjQuiz where name like'"+name+"%' and delflag='0'";
			
			ArrayList list=this.getAss().getAssignment(sql);
			if(list.size()>-1){
				request.setAttribute("list", list);
				
				return mapping.findForward("assignment_list");
			}else
				return null;
		}*/
		
	}
	
	public ActionForward addassignemnt(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		//
		//ZjQuiz question = (ZjQuiz)actionForm;
		//IAssignmentManager ia=new AssignmentManager();
		
		ZjQuiz quiz = new ZjQuiz();
		String T10= request.getParameter("T10");
		String T11= request.getParameter("T11");
		String D1= request.getParameter("D1");
		String T13= request.getParameter("T13");
		String T1= request.getParameter("T1");
		String T9= request.getParameter("T9");
		String T7= request.getParameter("T7");
		String T8= request.getParameter("T8");
		String S2= request.getParameter("S2");
		//quiz.setId(T10);
		quiz.setCourse("1");
		quiz.setName(T11);
		quiz.setDescription(D1);
		quiz.setTimeopen(System.currentTimeMillis());
		quiz.setTimeclose(System.currentTimeMillis());
		quiz.setTimelimit(System.currentTimeMillis());
		quiz.setQuestionsperpage(Long.parseLong("10"));
		quiz.setRandomquestions("5");
		quiz.setAttemptonlast("1");
		quiz.setGrademethod("1");
		quiz.setDecimalpoints(Long.parseLong("2"));
		quiz.setPassword("as");
		quiz.setIsScore("y");
		quiz.setQuizType("1");
		quiz.setStatus("0");
		quiz.setPaperLocation("as");
		quiz.setIsInvigilate("1");
		
		quiz.setDelflag("0");
		quiz.setLasteditdate(System.currentTimeMillis());
		quiz.setCreatedate(System.currentTimeMillis());
		quiz.setMaxExamNum(Long.parseLong("100"));
		quiz.setPaper("as");
		quiz.setPassscore(12.09);
		
		boolean bo =this.getAss().saveAssignment(quiz);
		//ia.saveAssignment(quiz);
		//System.out.print(bo);
		if(bo)
		{
			this.listassignemnt(mapping, actionForm, request, response);
			return mapping.findForward("assignment_list");
		}else
		{
			return mapping.findForward("assignment");
		}
		
		//quiz.setId(id);
		/*
		 * id:主键
course:对应课程信息表主键
name:试卷名称
description:试卷描述信息
timeopen:测试开放日期
timeclose:测试关闭日期
timelimit:时间限制
questionsperpage:每页显示的题目数

password:考试密码
visible:是否可见
is_score:是否计入平时成绩
quiz_type:作业（离线作业、在线作业）\考试\测试
status:1停用、0可用
paper_location:试卷url
is_invigilate:是否监考(0:否,1:是)

randomquestions:随机排列题目
attempts:允许试答次数
attemptonlast:在上次答卷的基础上答卷
grademethod :评分办法
penaltyscheme:使用罚分
decimalpoints:成绩中保留小数点位数
password:考试密码
visible:是否可见
is_score:是否计入平时成绩
quiz_type:作业（离线作业、在线作业）\考试\测试
status:1停用、0可用
paper_location:试卷url
is_invigilate:是否监考(0:否,1:是)*/
		//this.getQuestionService().saveQuestion(quiz);
		
	}
	
	public ActionForward removeAssignment(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		
		String[] questionid = request.getParameterValues("quizid");
		String info = this.getAss().removeQuestion(questionid);
		System.out.print("remove==============="+info);
		if(info == null){
			request.setAttribute("info", info);
			this.listassignemnt(mapping, actionForm, request, response);
			return mapping.findForward("assignment_list");
		}
		//System.out.print("aaaaaaa");
		//return null;
		return null;
	}
	
	
	public ActionForward editAssignment(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String questionid=request.getParameter("quizid");
		ZjQuiz question = this.getAss().getQuiz(questionid);
		//id
		request.setAttribute("id",question.getId());
		
		
		request.setAttribute("name", question.getName());
		//System.out.print("name====="+question.getName()+"id======"+question.getId());
		return mapping.findForward("update_assignment");
		
	}
	
	
	public ActionForward updateassignemnt(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String quizId=request.getParameter("id");
		//System.out.print("quizid=="+quizId);
		
		String name= request.getParameter("T11");
		String D1= request.getParameter("D1");
		
		ZjQuiz quiz=this.getAss().getQuiz(quizId);
		
		quiz.setCourse("1");
		quiz.setName(name);
		quiz.setDescription(D1);
		quiz.setTimeopen(System.currentTimeMillis());
		quiz.setTimeclose(System.currentTimeMillis());
		quiz.setTimelimit(System.currentTimeMillis());
		quiz.setQuestionsperpage(Long.parseLong("10"));
		quiz.setRandomquestions("5");
		quiz.setAttemptonlast("1");
		quiz.setGrademethod("1");
		quiz.setDecimalpoints(Long.parseLong("2"));
		quiz.setPassword("as");
		quiz.setIsScore("y");
		quiz.setQuizType("1");
		quiz.setStatus("0");
		quiz.setPaperLocation("as");
		quiz.setIsInvigilate("1");
		
		quiz.setDelflag("0");
		quiz.setLasteditdate(System.currentTimeMillis());
		quiz.setCreatedate(System.currentTimeMillis());
		quiz.setMaxExamNum(Long.parseLong("100"));
		quiz.setPaper("as");
		quiz.setPassscore(12.09);
		
		boolean bo =this.getAss().saveAssignment(quiz);
		//ia.saveAssignment(quiz);
		//System.out.print(bo);
		if(bo)
		{
			this.listassignemnt(mapping, actionForm, request, response);
			return mapping.findForward("assignment_list");
		}else
		{
			return mapping.findForward("assignment");
		}
		
	}
	

	public ActionForward searchAssignment(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		
		String status="0";
		String page="8";
		PageObject po = this.getAss().getAssignmentObject(userid,status, page);
		request.setAttribute("po", po);
		
		String name=request.getParameter("name");
		String sql="from ZjQuiz where name like'"+name+"%' and delflag='0'";
		
		ArrayList list=this.getAss().getAssignment(sql);
		if(list.size()>-1){
			request.setAttribute("list", list);
			
			return mapping.findForward("assignment_list");
		}else
			return null;
	}
	
	public ActionForward mainCategory(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String parent = "";
		if(request.getAttribute("parent")!=null){
			parent = (String)request.getAttribute("parent");
		}
		String leftAction = request.getContextPath()+"/Category.do?method=leftCategoryTree&parent="+parent;
		String rightAction = request.getContextPath()+"/Category.do?method=listCategory&parent="+parent;
		request.setAttribute("leftAction", leftAction);
		request.setAttribute("rightAction",rightAction);
		return mapping.findForward("main");
	}
}