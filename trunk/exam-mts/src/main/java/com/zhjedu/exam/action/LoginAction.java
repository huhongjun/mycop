package com.zhjedu.exam.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dfcw.zjproject.zj.dao.CourseDAO;
import com.dfcw.zjproject.zj.dao.CourseDAOImpl;
import com.dfcw.zjproject.zj.dao.StudentDAO;
import com.dfcw.zjproject.zj.dao.StudentDAOImpl;
import com.dfcw.zjproject.zj.dao.TeacherDAO;
import com.dfcw.zjproject.zj.dao.TeacherDAOImpl;
import com.dfcw.zjproject.zj.model.ExamCourseModel;
import com.dfcw.zjproject.zj.model.StudentModel;
import com.dfcw.zjproject.zj.model.TeacherModel;
import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.util.UserSession;

public class LoginAction extends BaseAction {
public ActionForward login(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response){
		
		String userName = "";
		String password = "";
		String userType = "0";//0为学生,1为教师
		TeacherDAO tdao = new TeacherDAOImpl();
		StudentDAO sdao = new StudentDAOImpl();
		if(request.getParameter("userName")!=null){
			userName = request.getParameter("userName");
		}
		if(request.getParameter("password")!=null){
			password = request.getParameter("password");
		}
		if(request.getParameter("userType")!=null){
			userType = request.getParameter("userType");
		}
		if(!password.equals("12345")){
			return mapping.findForward("login");
		}
		if(!userName.equals("")){
			if(userType.equals("1")){
				try {
					TeacherModel teacher = tdao.getTeacher(userName);
				    if(teacher.getUserName()!=null){
					request.getSession().setAttribute(UserSession.TEACHER_SESSION, teacher);
				    }
				    else{
				    	return mapping.findForward("login");
				    }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				try {
					StudentModel student = sdao.getStudent(userName);
					if(student.getUserName()!=null){
						request.getSession().setAttribute(UserSession.STUDENT_SESSION, student);
						return new ActionForward("/assignment.do?method=getStatus&status=1");
					}
					else{
						return mapping.findForward("login");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return new ActionForward("/Quiz.do?method=main&quizType=2&flag=0");
	}

	public ActionForward studentLogin(ActionMapping mapping, ActionForm actionForm,HttpServletRequest request,HttpServletResponse response){
		StudentDAO sdao = new StudentDAOImpl();
		CourseDAO cdao = new CourseDAOImpl();
		String userName = request.getParameter("userName");
		String passcord = request.getParameter("passcord");
		
		if(request.getSession().getAttribute("rand") != null){
			String sRand = (String)request.getSession().getAttribute("rand"); //取得随机验证码
			request.getSession().removeAttribute("rand");
			if(passcord.equals(sRand)){
				try {
					StudentModel student = sdao.getStudent(userName);
					if(student != null && student.getUserName() != null){
						/*
						 * 在这里进行该学生的考试信息同步操作 start
						 */
						ArrayList list = cdao.getExamCourse(student.getMidInstitutionId(), student.getMidRecruitbatchId(), student.getMidStudykindId(), student.getMidSubjectId());
						this.getJoinExamService().saveQuizExamList(student.getStudentId() + "", list);
						/*
						 * 在这里进行该学生的考试信息同步操作 end
						 */
						request.getSession().setAttribute(UserSession.STUDENT_SESSION, student);
						return new ActionForward("/assignment_s/success.jsp");
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("info", "用户名或验证码错误！");
		return new ActionForward("/login.jsp");
	}


}