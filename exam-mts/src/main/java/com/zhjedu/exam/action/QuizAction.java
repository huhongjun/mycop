package com.zhjedu.exam.action;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dfcw.zjproject.zj.dao.CourseDAO;
import com.dfcw.zjproject.zj.dao.CourseDAOImpl;
import com.dfcw.zjproject.zj.dao.OtherDAO;
import com.dfcw.zjproject.zj.dao.OtherDAOImpl;
import com.dfcw.zjproject.zj.dao.StudentDAO;
import com.dfcw.zjproject.zj.dao.StudentDAOImpl;
import com.dfcw.zjproject.zj.dao.TeacherDAO;
import com.dfcw.zjproject.zj.dao.TeacherDAOImpl;
import com.dfcw.zjproject.zj.model.CourseTeacherModel;
import com.dfcw.zjproject.zj.model.TeacherModel;
import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuestionCategory;
import com.zhjedu.exam.domain.ZjQuestionMatchingAnswer;
import com.zhjedu.exam.domain.ZjQuestionMatchingOption;
import com.zhjedu.exam.domain.ZjQuestionOption;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.domain.ZjQuizExam;
import com.zhjedu.exam.domain.ZjQuizMidEc;
import com.zhjedu.util.DateTimeUtil;
import com.zhjedu.util.PageObject;
import com.zhjedu.util.StringUtil;
import com.zhjedu.util.UserSession;

public class QuizAction extends BaseAction {
	public ActionForward list(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("delflag", "0");
		String quizType = "2";
		if(request.getParameter("quizType")!=null){
			quizType = request.getParameter("quizType");
		}
		
		if(quizType!=null&&!quizType.equals("")){
			map.put("quizType", quizType);
			request.setAttribute("quizType", quizType);
		}
		String course = "";
		if(request.getParameter("course")!=null&&!(request.getParameter("course").trim()).equals("")){
			course = request.getParameter("course");
		}
		if(request.getParameter("nodeid")!=null&&!(request.getParameter("nodeid").trim()).equals("")){
			course = request.getParameter("nodeid");
		}
		if(!course.equals("")){
			map.put("course", course);
		}
		PageObject po = this.getQuizService().getQuizList(map,page);
		
		request.setAttribute("po", po);
		if(quizType.equals("1")){
			return mapping.findForward("listassignment");
		}
		return mapping.findForward("list");
	}
	
	public ActionForward selectCatScope(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("delflag", "0");
		
		String category = "";
		if(request.getParameter("category")!=null&&!(request.getParameter("category").trim()).equals("")){
			category = request.getParameter("category");
		}
		
		List list = this.getQuestionService().getAllCategory(category);
		
		request.setAttribute("list", list);
		return mapping.findForward("listScope");
	}
	
	public ActionForward selectCourseScope(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("delflag", "0");
		
		String course = "";
		if(request.getParameter("course")!=null&&!(request.getParameter("course").trim()).equals("")){
			course = request.getParameter("course");
		}
		if(request.getParameter("nodeid")!=null&&!(request.getParameter("nodeid").trim()).equals("")){
			course = request.getParameter("nodeid");
		}
		
		List list = this.getQuestionService().getCourseItemList(course);
		
		request.setAttribute("list", list);
		return mapping.findForward("listScope");
	}
	
	
	public ActionForward selectUserScope(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		String course = request.getParameter("course");
		ZjCourse tmp = this.getQuestionService().getZjCourse(course);
		StudentDAO sdao = new StudentDAOImpl();
		List list = new ArrayList();
		try {
			list = sdao.getStudentsByCourse(tmp.getOriginId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		return mapping.findForward("listUserScope");
	}
	
	public ActionForward selectUserScopeForQuiz(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		int studykind = 0;
		if(request.getParameter("studykind")!=null){
		    studykind = Integer.parseInt(request.getParameter("studykind"));
		}
		int subject = 0;
		if(request.getParameter("subject")!=null){
			subject = Integer.parseInt(request.getParameter("subject"));
		}
		int institution = 0;
		if(request.getParameter("institution")!=null){
			institution = Integer.parseInt(request.getParameter("institution"));
		}
		int learncenter = 0;
		if(request.getParameter("learncenter")!=null){
			learncenter = Integer.parseInt(request.getParameter("learncenter"));
		}
		int recruitbatch = 0;
		if(request.getParameter("recruitbatch")!=null){
			recruitbatch = Integer.parseInt(request.getParameter("recruitbatch"));
		}
		
		
		CourseDAO cdao = new CourseDAOImpl();
		List list = new ArrayList();
		try {
			list = cdao.getExamCoursees(studykind, subject, institution, learncenter, recruitbatch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		return mapping.findForward("listUserScopeForQuiz");
	}
	
	public ActionForward saveAssignment(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		ZjQuiz quiz = (ZjQuiz)actionForm;
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String timeopen = "";
		String timeclose = "";
		if(request.getParameter("timeopen")!=null){
			timeopen = request.getParameter("timeopen");
		}
		if(request.getParameter("timeclose")!=null){
			timeclose = request.getParameter("timeclose");
		}
		try {
			if(!timeopen.equals("")){
				quiz.setTimeopen(DateTimeUtil.getTimeStamp(timeopen,2));
			}
			if(!timeclose.equals("")){
				quiz.setTimeclose(DateTimeUtil.getTimeStamp(timeclose,2));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(request.getParameter("timeLateFlag")!=null&&request.getParameter("timeLateFlag").equals("0")){
			quiz.setTimelate(new Long(0));
		}
		if(request.getParameter("course")!=null){
			
				 ZjCourse tmp = this.getQuestionService().getZjCourseByOId(request.getParameter("course"));
				 if(tmp==null){
					 tmp = this.getQuestionService().getZjCourse(request.getParameter("course"));
				 }
				 if(tmp!=null){
					 quiz.setZjCourse(tmp);
			}
			
		}
		String scopestr = request.getParameter("scopestr");
		quiz.setScope(scopestr);
		quiz.setCreatedate(System.currentTimeMillis());
		quiz.setLasteditdate(System.currentTimeMillis());
		quiz.setCreator(userid);
		quiz.setLasteditor(userid);
	    
		String quizid = this.getQuizService().saveQuiz(quiz);
		if(request.getParameter("flag")!=null&&request.getParameter("flag").equals("0")){
			return new ActionForward("/Maneuver.do?method=createManeuver&quizId="+quizid);
		}
		else{
			return new ActionForward("/Maneuver.do?method=listManeuver&quizId="+quizid);
		}
	}
	
	public ActionForward saveQuiz(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		ZjQuiz quiz = (ZjQuiz)actionForm;
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String timeopen = "";
		String timeclose = "";
		if(request.getParameter("timeopen")!=null){
			timeopen = request.getParameter("timeopen");
		}
		if(request.getParameter("timeclose")!=null){
			timeclose = request.getParameter("timeclose");
		}
		try {
			if(!timeopen.equals("")){
				quiz.setTimeopen(DateTimeUtil.getTimeStamp(timeopen,2));
			}
			if(!timeclose.equals("")){
				quiz.setTimeclose(DateTimeUtil.getTimeStamp(timeclose,2));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(request.getParameter("timeLateFlag")!=null&&request.getParameter("timeLateFlag").equals("0")){
			quiz.setTimelate(new Long(0));
		}
		if(request.getParameter("course")!=null){
			
				 ZjCourse tmp = this.getQuestionService().getZjCourseByOId(request.getParameter("course"));
				 if(tmp==null){
					 tmp = this.getQuestionService().getZjCourse(request.getParameter("course"));
				 }
				 if(tmp!=null){
					 quiz.setZjCourse(tmp);
			}
			
		}
		String scopestr = request.getParameter("scopestr");
		quiz.setScope(scopestr);
		quiz.setCreatedate(System.currentTimeMillis());
		quiz.setLasteditdate(System.currentTimeMillis());
		quiz.setCreator(userid);
		quiz.setLasteditor(userid);
	    
		String quizid = this.getQuizService().saveQuiz(quiz);
		if(request.getParameter("flag")!=null&&request.getParameter("flag").equals("0")){
			return new ActionForward("/Maneuver.do?method=createManeuver&quizId="+quizid);
		}
		else{
			return new ActionForward("/Maneuver.do?method=listManeuver&quizId="+quizid);
		}
	}
	
	public ActionForward addQuiz(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		ZjQuiz quiz = (ZjQuiz)actionForm;
		List scopeList = new ArrayList();
		String quizid = request.getParameter("id");
		
		if(quizid!=null&&!quizid.equals("")){
			quiz = this.getQuizService().getZjQuiz(quizid);
			String scopes = quiz.getScope();
			if(scopes!=null&&!scopes.equals("")){
				String[] scopeArrays = StringUtil.split(scopes);
				for(int i=0;i<scopeArrays.length;i++){
					String scope = scopeArrays[i];
					ZjQuestionCategory category = this.getQuestionService().getZjQuestionCategory(scope);
					if(category!=null){
						scopeList.add(category);
					}
				}
			}
			request.setAttribute("quizid", quizid);
		}		
		request.setAttribute("scopeList", scopeList);
		request.setAttribute("quiz", quiz);		
		return mapping.findForward("add");
		
		
	}
	
	public ActionForward addAssignment(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		ZjQuiz quiz = (ZjQuiz)actionForm;
		List scopeList = new ArrayList();
		String quizid = request.getParameter("id");
		String course = request.getParameter("course");
		if(quizid!=null&&!quizid.equals("")){
			quiz = this.getQuizService().getZjQuiz(quizid);
			String scopes = quiz.getScope();
			if(scopes!=null&&!scopes.equals("")){
				String[] scopeArrays = StringUtil.split(scopes);
				for(int i=0;i<scopeArrays.length;i++){
					String courseid = scopeArrays[i];
					ZjCourse tmp = this.getQuestionService().getZjCourse(courseid);
					if(tmp!=null){
						scopeList.add(tmp);
					}
				}
			}
			request.setAttribute("quizid", quizid);
		}	
		request.setAttribute("course", course);
		request.setAttribute("scopeList", scopeList);
		request.setAttribute("quiz", quiz);		
		return mapping.findForward("addassignment");
		
		
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
		String quizid=request.getParameter("quizid");
		String quizType=request.getParameter("quizType");
		List scopeList = new ArrayList();
		ZjQuiz quiz = this.getQuizService().getZjQuiz(quizid);
		if(quizid!=null&&!quizid.equals("")){
			quiz = this.getQuizService().getZjQuiz(quizid);
			String scopes = quiz.getScope();
			if(scopes!=null&&!scopes.equals("")){
				String[] scopeArrays = StringUtil.split(scopes);
				for(int i=0;i<scopeArrays.length;i++){
					String scope = scopeArrays[i];
					ZjQuestionCategory category = this.getQuestionService().getZjQuestionCategory(scope);
					if(category!=null){
						scopeList.add(category);
					}
				}
			}
			request.setAttribute("quizid", quizid);
		}
		request.setAttribute("scopeList", scopeList);
		request.setAttribute("quiz", quiz);
		if(quizType!=null&&quizType.equals("1")){
			return mapping.findForward("addassignment");
		}

		return mapping.findForward("add");
		
	}
	
	public ActionForward editassignment(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String quizid=request.getParameter("quizid");		
		List scopeList = new ArrayList();
		ZjQuiz quiz = this.getQuizService().getZjQuiz(quizid);
		if(quizid!=null&&!quizid.equals("")){
			quiz = this.getQuizService().getZjQuiz(quizid);
			String scopes = quiz.getScope();
			if(scopes!=null&&!scopes.equals("")){
				String[] scopeArrays = StringUtil.split(scopes);
				for(int i=0;i<scopeArrays.length;i++){
					String scope = scopeArrays[i];
					ZjCourse course = this.getQuestionService().getZjCourse(scope);
					if(course!=null){
						scopeList.add(course);
					}
				}
			}
			request.setAttribute("quizid", quizid);
		}
		String course = request.getParameter("course");
		request.setAttribute("course", course);
		request.setAttribute("scopeList", scopeList);
		request.setAttribute("quiz", quiz);
		

		return mapping.findForward("addassignment");
		
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
		String[] quizid = request.getParameterValues("quizid");
		String info = this.getQuizService().removeQuiz(quizid);
		if(info != null){
			request.setAttribute("info", info);
			return mapping.findForward("Exception");
		}
		return list(mapping, actionForm, request, response);
	}
	
	public ActionForward restart(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String quizid = request.getParameter("quiz");
		ZjQuiz quiz = this.getQuizService().getZjQuiz(quizid);
		quiz.setStatus("0");
		this.getQuizService().saveQuiz(quiz);
		return list(mapping, actionForm, request, response);
	}
	
	public ActionForward stop(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String quizid = request.getParameter("quiz");
		ZjQuiz quiz = this.getQuizService().getZjQuiz(quizid);
		quiz.setStatus("1");
		this.getQuizService().saveQuiz(quiz);
		
		return list(mapping, actionForm, request, response);
	}
	
	public ActionForward removeQuizQuestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String[] questions = request.getParameterValues("qqid");
		String quiz = request.getParameter("quiz");
		String info = this.getQuizService().removeQuizQuestion(questions);
		if(info != null){
			request.setAttribute("info", info);
			return mapping.findForward("Exception");
		}
		return listQuizQuestions(mapping, actionForm, request, response);
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
		String quizType = "2";
		String flag = "1";
		
		if(request.getParameter("quizType")!=null){
			quizType= request.getParameter("quizType");
		}
		if(request.getParameter("flag")!=null){
			flag= request.getParameter("flag");
		}
		
		String leftAction = request.getContextPath()+"/Quiz.do?method=leftTree&quizType="+quizType;
		String rightAction = request.getContextPath()+"/Quiz.do?method=getAssignmentinfo&quizType="+quizType;
		if(flag.equals("1")){
			rightAction = request.getContextPath()+"/Quiz.do?method=list&quizType="+quizType;
		}
		
		request.setAttribute("leftAction", leftAction);
		request.setAttribute("rightAction",rightAction);
		return mapping.findForward("main");
	}
	
	public ActionForward leftTree(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String category = request.getParameter("category");
		String course = request.getParameter("course");
		String quizType = request.getParameter("quizType");
		String treeStr = "";
//		if(course!=null&&!course.equals("")&&(quizType.equals("1"))){
//			treeStr = this.getMb().generateCourseTree("mainFrame", request.getContextPath()+"/Quiz.do?method=list&quizType="+quizType, course,request.getContextPath());
//		}
//		else{
//			treeStr = this.getMb().generateCategoryTree("mainFrame", request.getContextPath()+"/Quiz.do?method=list", category,request.getContextPath());
//		}
		request.setAttribute("treeStr", treeStr);
		request.setAttribute("category",category);
		return mapping.findForward("leftTree");
	}
	
	public ActionForward leftCategoryTree(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String category = request.getParameter("category");
			
		String treeStr = "";
		
		treeStr = this.getMb().generateCategoryTree("mainFrame", request.getContextPath()+"/Category.do?method=listCategory", category,request.getContextPath());
			
		request.setAttribute("treeStr", treeStr);
		request.setAttribute("category",category);
		return mapping.findForward("leftTree");
	}
	
	public ActionForward leftCatTree(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String category = request.getParameter("category");
		String treeStr = this.getMb().generateCategoryTreeForChoose("rightFrame", request.getContextPath()+"/Quiz.do?method=selectCatScope", category,request.getContextPath());
		request.setAttribute("treeStr", treeStr);
		request.setAttribute("category",category);
		return mapping.findForward("leftScopeTree");
	}
	
	public ActionForward leftCourseTree(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String course = request.getParameter("course");
		String treeStr = this.getMb().generateCourseTree("rightFrame", request.getContextPath()+"/Quiz.do?method=selectCourseScope", course,request.getContextPath());
		request.setAttribute("treeStr", treeStr);
		request.setAttribute("course",course);
		return mapping.findForward("leftScopeTree");
	}
	
	public ActionForward leftQuestionTree(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String course = request.getParameter("course");
		String treeStr = this.getMb().generateCourseTree("rightFrame", request.getContextPath()+"/Question.do?method=listQuestion", course,request.getContextPath());
		request.setAttribute("treeStr", treeStr);
		request.setAttribute("course",course);
		return mapping.findForward("leftScopeTree");
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("delflag", "0");
		String key = "";
		if(request.getParameter("key")!=null){
			key = request.getParameter("key").trim();
		}
		String quizType = "";
		if(request.getParameter("quizType")!=null){
			quizType = request.getParameter("quizType").trim();
		}
		String timeopen = "";
		if(request.getParameter("timeopen")!=null){
			timeopen = request.getParameter("timeopen").trim();
		}
		if(!quizType.equals("")){
			map.put("quizType", quizType);
		}

		PageObject po = this.getQuizService().getQuizList(map,key,timeopen,page);
		//PageObject po = this.getQuestionService().getQuestionObject(key,qtype,page);
		
		request.setAttribute("key", key);
		request.setAttribute("timeopen", timeopen);
		request.setAttribute("po", po);
		if(quizType.equals("1")){
			return mapping.findForward("listassignment");
		}
		return mapping.findForward("list");
	}
	
	
	public ActionForward listCategory(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		Map<String, Serializable> map = new HashMap<String, Serializable>();
		map.put("delflag", "0");
		map.put("parent", "");
		
		String info = "";
		if(request.getParameter("info")!=null){
			info = (String)request.getParameter("info");
		}
		if(request.getAttribute("info")!=null){
			info = (String)request.getAttribute("info");
		}
		request.setAttribute("info", info);
		String category = request.getParameter("category");
		if(category==null||category.equals("")){
			category = (String)request.getAttribute("category");
		}
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
		
				
		request.setAttribute("catName",catName);
		request.setAttribute("isleaf", isleaf);
		if(category!=null&&!category.equals("")){
			map.put("parent", category);
			request.setAttribute("category", category);
		}
		
		PageObject po = this.getQuestionService().getCategoryObject(map,page);
		
		request.setAttribute("po", po);
		return mapping.findForward("listcategory");
	}
	
	public ActionForward saveCategory(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		ZjQuestionCategory category = (ZjQuestionCategory)actionForm;
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		
		category.setCreatedate(System.currentTimeMillis());
		category.setLasteditdate(System.currentTimeMillis());
		category.setCreator(userid);
		category.setDelflag("0");
		category.setLasteditor(userid);
		
		String categoryid = this.getQuestionService().saveQuestionCategory(category);
		if(category.getParent()!=null&&!category.getParent().equals("")){
			ZjQuestionCategory tmp = this.getQuestionService().getZjQuestionCategory(category.getParent());
			if(tmp!=null){
				tmp.setIsleaf("0");
				if(tmp.getParent()==null){
					tmp.setParent("");
				}
				this.getQuestionService().saveQuestionCategory(tmp);
			}
		}
		request.setAttribute("parent", category.getParent());
		return mainCategory(mapping, actionForm, request, response);
	}
	
	public ActionForward addCategory(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		ZjQuestionCategory category = (ZjQuestionCategory)actionForm;
		String userid = "";
		String info = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String categoryid = request.getParameter("parent");
		System.out.println("parent:"+categoryid);
		if(categoryid!=null&&!categoryid.equals("")){
			List list = this.getQuestionService().getQuestionListByCategory(categoryid);
			if(list!=null&&!list.isEmpty()&&list.size()>0){
				info = "该目录下有题目,不能添加子目录,若要在该目录下添加子目录,请先将该目录下的题目全部删除!";
				request.setAttribute("info", info);
				request.setAttribute("category", categoryid);
				return listCategory(mapping, actionForm, request, response);
			}
		}
		request.setAttribute("category", category);
		return mapping.findForward("addcategory");
	}
	
	public ActionForward editCategory(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String categoryid=request.getParameter("categoryid");
		String parent = "";
		if(request.getParameter("parent")!=null){
			parent = request.getParameter("parent");
		}
		ZjQuestionCategory category = this.getQuestionService().getZjQuestionCategory(categoryid);
		
		request.setAttribute("category", category);

		return mapping.findForward("addcategory");
		
	}
	
	public ActionForward removeCategory(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		String info = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String parent = "";
		if(request.getParameter("parent")!=null){
			parent = request.getParameter("parent");
		}
		String category = request.getParameter("category");
		if(category!=null&&!category.equals("")){
			List list = this.getQuestionService().getQuestionListByCategory(category, new ArrayList());
			if(list!=null&&!list.isEmpty()&&list.size()>0){
				info = "该目录或它的子目录下有题目,暂时不能删除该目录,要想删除该目录,请先将该目录下的题目全部删除!";
				request.setAttribute("info", info);
				request.setAttribute("parent", parent);
				return mainCategory(mapping, actionForm, request, response);
			}
		}
		
		 info = this.getQuestionService().removeQuestionCategory(category);
		if(info != null){
			request.setAttribute("info", info);
			return mapping.findForward("Exception");
		}
		
		if(category!=null&&!category.equals("")){
			List tmpList = this.getQuestionService().getZjCategoryList(category);
			if(tmpList==null||tmpList.isEmpty()){
				ZjQuestionCategory tmp = this.getQuestionService().getZjQuestionCategory(parent);
				tmp.setIsleaf("1");
				this.getQuestionService().saveQuestionCategory(tmp);
			}
		}
		request.setAttribute("info", info);
		request.setAttribute("parent", parent);
		return mainCategory(mapping, actionForm, request, response);
	}
	
	public ActionForward mainCategory(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String parent = "";
		if(request.getAttribute("parent")!=null){
			parent = (String)request.getAttribute("parent");
		}
		String info = "";
		if(request.getAttribute("info")!=null){
			info = (String)request.getAttribute("info");
		}
		String leftAction = request.getContextPath()+"/Category.do?method=leftCategoryTree&category="+parent;
		String rightAction = request.getContextPath()+"/Category.do?method=listCategory&category="+parent+"&info="+info;
		request.setAttribute("leftAction", leftAction);
		request.setAttribute("rightAction",rightAction);
		return mapping.findForward("main");
	}
	
	public ActionForward listCourse(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String page = request.getParameter("page");
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		TeacherDAO teacherDAO = new TeacherDAOImpl();
		List ctList = new ArrayList();
		try {
			 ctList = teacherDAO.getTeacherCourses(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List list = new ArrayList();
		for(int i=0;i<ctList.size();i++){
			CourseTeacherModel ctm = (CourseTeacherModel)ctList.get(i);
			ZjCourse course = this.getQuestionService().getZjCourseByOId(String.valueOf(ctm.getCourseID()));
			if(course!=null){
				list.add(course);
			}
		}
		
		
		request.setAttribute("list", list);
		return mapping.findForward("listcourse");
	}
	
	
	public ActionForward addQuizToMidEC(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String strValue = request.getParameter("ec");
		System.out.println(strValue);
		String quiz = "";
		if(request.getParameter("quiz")!=null){
			quiz = request.getParameter("quiz");
		}
		String[] ecs = null;
		if(strValue!=null){
			ecs = StringUtil.split(strValue, ","); 
		}
		for(int i=0;i<ecs.length;i++){
			System.out.println(ecs[i]+",");
		}
		if(ecs!=null&&ecs.length>0){
			this.getQuizService().saveQuizECs(ecs, quiz);
		}
		
		if(ecs.length>0){
			ZjQuiz tmp = this.getQuizService().getZjQuiz(quiz);
			tmp.setInvigilator1("1");
			this.getQuizService().saveQuiz(tmp);
		}
		return list(mapping, actionForm, request, response);
	}
	
	
	public ActionForward addQuestionsToQuiz(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String userid = "";
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher!=null){
			userid = String.valueOf(teacher.getTeacherId());
		}
		if(userid.equals("")){
			return mapping.findForward("login");
		}
		String strValue = request.getParameter("questions");
		String quiz = "";
		
		if(request.getParameter("quiz")!=null){
			quiz = request.getParameter("quiz");
		}
		
		String[] questions = null;
		if(strValue!=null){
			questions = StringUtil.split(strValue, ",");
		}
		if(questions!=null&&questions.length>0){
			this.getQuizService().saveQuizQuestions(questions, quiz);
		}

		return listQuizQuestions(mapping, actionForm, request, response);
	}
	
	public ActionForward listQuizQuestions(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
		String quiz = request.getParameter("quiz");
		String course = request.getParameter("course");
		ZjQuiz tmp = this.getQuizService().getZjQuiz(quiz);
		List list = this.getQuizService().getQuizQuestionList(quiz);
		request.setAttribute("quizName", tmp.getName());
		request.setAttribute("list", list);
		return mapping.findForward("listquizquestions");
	}
	
	public ActionForward listQuestion(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
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
			request.setAttribute("parent", "0");
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
		PageObject po = this.getQuestionService().getQuestionObject(map,page);
		
		request.setAttribute("po", po);
		return mapping.findForward("listquestion");
	}
	
	public ActionForward main_category(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) {
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
	
	
	public ActionForward getAssignmentinfo(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response){
		
		String courseid = "";
		String courseName = "";
		if(request.getParameter("course")!=null){
			courseid = request.getParameter("course");
			ZjCourse course = this.getQuestionService().getZjCourse(courseid);
			courseName = course.getName();
		}
		int assnum = 0;
		int cenum = 0;
		int quiznum = 0;
		
		assnum = this.getQuizService().getQuizExamCount(courseid,"1", "2");
		cenum = this.getQuizService().getQuizExamCount(courseid,"3", "2");
		quiznum = this.getQuizService().getQuizExamCount(courseid,"2", "2");
		request.setAttribute("quiznum", String.valueOf(quiznum));
		request.setAttribute("assnum", String.valueOf(assnum));
		request.setAttribute("cenum", String.valueOf(cenum));
		request.setAttribute("courseName", courseName);
		request.setAttribute("courseid", courseid);
		return mapping.findForward("quizmessage");
	}
	
	public ActionForward getStudentQuizList(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response){
		String page = request.getParameter("page");
		String quizType = "2";
		String status = "2";
		String courseid = "";
		String quizid = "";
//		if(request.getParameter("quizType")!=null){
//			quizType = request.getParameter("quizType");
//		}
		if(request.getParameter("status")!=null){
			status = request.getParameter("status");
		}
		if(request.getParameter("course")!=null){
			courseid = request.getParameter("course");
		}
		if(request.getParameter("quiz")!=null){
			quizid = request.getParameter("quiz");
		}
		PageObject po = this.getQuizService().getQuizExamList(courseid,quizid, quizType, status, page);
		request.setAttribute("po", po);
		request.setAttribute("courseid", courseid);
		request.setAttribute("quizid", quizid);
		request.setAttribute("quizType", quizType);
		request.setAttribute("status", status);
		return mapping.findForward("list_s_quiz");
	}
	
	public ActionForward viewQuizExam(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response){
		
		String quiz = "";
		if(request.getParameter("quiz")!=null){
			quiz = request.getParameter("quiz");
		}
		ZjQuizExam qe = this.getQuizService().getQuizExam(quiz);
		
		request.setAttribute("qe", qe);
		
		return mapping.findForward("view_quiz_exam");
	}
	public ActionForward viewQuiz(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response){
		
		String quizid = "";
		int oughtnumber = 0;
		int factnumber = 0;
		if(request.getParameter("quiz")!=null){
			quizid = request.getParameter("quiz");
		}
		String course = request.getParameter("course");
		
		ZjQuiz quiz = this.getQuizService().getZjQuiz(quizid);
		StudentDAO sdao = new StudentDAOImpl();
		try {
			String ecs = "";
			List list = this.getQuizService().getZjQuizMidEcList(quizid);
			if(list!=null&&!list.isEmpty()){
				for(int i=0;i<list.size();i++){
					ZjQuizMidEc tmp = (ZjQuizMidEc)list.get(i);
					ecs = ecs + "," + String.valueOf(tmp.getMidExamId());
				}
				
			}
			oughtnumber = sdao.getStudentsOughtCount(quizid,ecs);
			
			factnumber = this.getQuizService().getStudentsFactNumber(quizid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("oughtnumber", String.valueOf(oughtnumber));
		request.setAttribute("factnumber", String.valueOf(factnumber));
		request.setAttribute("quiz", quiz);
		request.setAttribute("course", course);
		return mapping.findForward("view_quiz");
	}
	
	public ActionForward leftForQuiz(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response){
		OtherDAO odao = new OtherDAOImpl();
		try {
			List studykinds = odao.getStudyKinds();
			List subjects = odao.getSubjects();
			List institutions = odao.getInstitutions();
			List learncenters = odao.getLearnCenters();
			List recruitbatchs = odao.getRecruitbatcs();
			request.setAttribute("studykinds", studykinds);
			request.setAttribute("subjects", subjects);
			request.setAttribute("institutions", institutions);
			request.setAttribute("learncenters", learncenters);
			request.setAttribute("recruitbatchs", recruitbatchs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mapping.findForward("leftForQuiz");
	}
}