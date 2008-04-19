package com.zhjedu.util;


import javax.servlet.http.HttpServletRequest;

import com.dfcw.zjproject.zj.model.StudentModel;
import com.dfcw.zjproject.zj.model.TeacherModel;


public class UserSessionInfo {
	
	public static boolean studentIsLogin(HttpServletRequest request){
		StudentModel student = (StudentModel)request.getSession().getAttribute(UserSession.STUDENT_SESSION);
		if(student != null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean teacherIsLogin(HttpServletRequest request){
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		if(teacher != null){
			return true;
		}else{
			return false;
		}
	}

	public static int getStudentId(HttpServletRequest request) {
		StudentModel student = (StudentModel)request.getSession().getAttribute(UserSession.STUDENT_SESSION);
		return student.getStudentId();
	}
	
	public static String getStudentName(HttpServletRequest request) {
		StudentModel student = (StudentModel)request.getSession().getAttribute(UserSession.STUDENT_SESSION);
		return student.getUserName();
	}
	
	public static String getStudentRealName(HttpServletRequest request) {
		StudentModel student = (StudentModel)request.getSession().getAttribute(UserSession.STUDENT_SESSION);
		return student.getRealName();
	}
	
	public static int getTeacherId(HttpServletRequest request) {
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		return teacher.getTeacherId();
	}
	
	public static String getTeacherName(HttpServletRequest request) {
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		return teacher.getUserName();
	}
	
	public static String getTeacherRealName(HttpServletRequest request) {
		TeacherModel teacher = (TeacherModel)request.getSession().getAttribute(UserSession.TEACHER_SESSION);
		return teacher.getRealName();
	}
	public static String getScore(float totalScore, int questionNum, int currentQuestionNo){
		String returnScore = "0";
		int tempScore = 0;
		for(int i = 0; i < questionNum; i ++){
			int x = Math.round((totalScore - tempScore) / (questionNum - i));
			tempScore += x;
			if(i == currentQuestionNo){
				returnScore = x + "";
				break;
			}
		}
		return returnScore;
	}
	
	public static String getQuestionNo(int i){
		String[] x = new String[20];
		x[0] = "一";
		x[1] = "二";
		x[2] = "三";
		x[3] = "四";
		x[4] = "五";
		x[5] = "六";
		x[6] = "七";
		x[7] = "八";
		x[8] = "九";
		x[9] = "十";
		x[10] = "十一";
		x[11] = "十二";
		x[12] = "十三";
		x[13] = "十四";
		x[14] = "十五";
		x[15] = "十六";
		x[16] = "十七";
		x[17] = "十八";
		x[18] = "十九";
		x[19] = "二十";
		if(i >= x.length)
			i = 0;
		return x[i];
	}
}
