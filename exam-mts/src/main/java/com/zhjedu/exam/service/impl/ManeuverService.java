package com.zhjedu.exam.service.impl;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.zhjedu.exam.service.IManeuverService;
import com.zhjedu.exam.manager.IManeuverManager;
import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.exam.domain.ZjManeuver;
import com.zhjedu.exam.domain.ZjManeuverItem;
import com.zhjedu.exam.domain.ZjManeuverList;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuestionCategory;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.domain.ZjQuizQuestion;
import com.zhjedu.util.Constants;


public class ManeuverService implements IManeuverService {
	private IManeuverManager maneuverManager;
	
	public boolean saveManeuverList(String courseId, String quizId){
		List list = this.getManeuverManager().getManeuver();//按优先级取得默认策略
		this.getManeuverManager().removeManeuverList(quizId);
		String scope = "";
		ZjQuiz quiz = this.getManeuverManager().getQuizbyId(quizId);
		if(quiz != null)
			scope = quiz.getScope();
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				ZjManeuver maneuver = (ZjManeuver)list.get(i);
				Iterator iterator = maneuver.getManeuverItem().iterator();
				Iterator iterator1 = maneuver.getManeuverItem().iterator();
				if(checkManeuverItem(iterator1, courseId, scope)){//判断标志位是否成功
					//加入策略的详细信息
					List maneuverList = getManeuverList(iterator, courseId, scope, quizId);
					this.getManeuverManager().saveManeuverList(maneuverList);
					break;				 //退出FOR循环
				}else{
					if(i == (list.size() - 1)){ //判断是否是最后一次循环
						return false;	 //返回,生成策略失败
					}else{
						continue;		 //继续FOR循环
					}
				}
			}
		}else{
			return false;//未取得策略,生成策略失败
		}
		return true;
	}
	
	public boolean test(){
		List list = this.getManeuverManager().getManeuver();//按优先级取得默认策略
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				ZjManeuver maneuver = (ZjManeuver)list.get(i);
				Iterator iterator = maneuver.getManeuverItem().iterator();
				while(iterator != null && iterator.hasNext()){
					ZjManeuverItem item = (ZjManeuverItem)iterator.next();//获得策略明细信息
				}
			}
		}else{
			return false;//未取得策略,生成策略失败
		}
		return true;
	}
	
	/**
	 * 检测该课程题库是否满足策略要求
	 * @param iterator 策略明细
	 * @param courseId 课程ID
	 * @param scope    范围,用逗号隔开的章节ID
	 * @return         true-满足 false-不满足
	 */
	public boolean checkManeuverItem(Iterator iterator, String courseId, String scope){
		boolean t = true; //重置标志位
		while(iterator != null && iterator.hasNext()){
			ZjManeuverItem item = (ZjManeuverItem)iterator.next();//获得策略明细信息
			int questionNum = this.getManeuverManager().getQuestionCountbyCourse(courseId, scope, item.getQuestionType());
			if(questionNum > item.getQuestionNum()){
				continue;
			}else{
				t = false;
				break;
			}
		}
		return t;
	}
	
	/**
	 * 策略生成主方法
	 * @param iterator 策略明细
	 * @param courseId 课程ID
	 * @param scope    范围,用逗号隔开的章节ID
	 * @param quizId   考试或试卷ID
	 * @return         策略详细LIST
	 */
	public List getManeuverList(Iterator iterator, String courseId, String scope, String quizId){
		List meneuverList = new Vector();       //用来装载策略详细信息
		while(iterator != null && iterator.hasNext()){
			ZjManeuverItem item = (ZjManeuverItem)iterator.next();
			List questionList = this.getManeuverManager().getQuestionListbyCourse(courseId, scope, item.getQuestionType());
			Hashtable rd = getQuestionGroupbyChapter(questionList);
			List _idList = (List)rd.get("_idList"); //取得保存了章ID信息的LIST
			
			long questionNum = item.getQuestionNum(); //要取题数量
			long totalNum = questionList.size();      //符合条件的题库数量
			long chapterNum = _idList.size();         //有题目的章数
			long num = 0;                             //已取题总量
			
			if(_idList != null && _idList.size() > 0){
				for(int i = 0; i < _idList.size(); i ++){
					String chapter = (String)_idList.get(i); //取得章ID
					List _tempList = (List)rd.get(chapter);  //取得该章下的题LIST
					long _tempNum = _tempList.size();        //该章下题目数量
					long t = (int)Math.ceil((float)_tempNum / (float)totalNum * (float)questionNum); //根据章下所含题比例决定在该章取多少道题 取最大值
					num += t;     //加入取题总量
					if(num < questionNum){
						if(t > 0){
							//加入对象
							if("0".equals(item.getQuestionType())){	//如果不限题型
								Hashtable hd = getQuestionGroupbyType(_tempList);
								for(int j = 0; j <= 9; j ++){		//循环题型 1-9是各题型的编号
									List __tempList = (List)hd.get(j + "");
									if(__tempList != null && __tempList.size() >= t){
										ZjManeuverList meneuver = new ZjManeuverList();
										meneuver.setQuizid(quizId);
										meneuver.setScope(chapter);
										meneuver.setQuestionType(j + "");
										meneuver.setHard1Num(0L);
										meneuver.setHard2Num(0L);
										meneuver.setHard3Num(0L);
										meneuver.setHard4Num(0L);
										meneuver.setHard5Num(0L);
										meneuver.setNohardNum(t);
										meneuver.setScore(item.getQuestionScore());
										meneuver.setStatus("0");
										meneuverList.add(meneuver);
										break;
									}else if(__tempList != null && __tempList.size() < t){
										ZjManeuverList meneuver = new ZjManeuverList();
										meneuver.setQuizid(quizId);
										meneuver.setScope(chapter);
										meneuver.setQuestionType(j + "");
										meneuver.setHard1Num(0L);
										meneuver.setHard2Num(0L);
										meneuver.setHard3Num(0L);
										meneuver.setHard4Num(0L);
										meneuver.setHard5Num(0L);
										meneuver.setNohardNum(Long.parseLong(__tempList.size() + ""));
										meneuver.setScore(item.getQuestionScore());
										meneuver.setStatus("0");
										meneuverList.add(meneuver);
										t = t - __tempList.size();
									}
								}
							}else{
								ZjManeuverList meneuver = new ZjManeuverList();
								meneuver.setQuizid(quizId);
								meneuver.setScope(chapter);
								meneuver.setQuestionType(item.getQuestionType());
								meneuver.setHard1Num(0L);
								meneuver.setHard2Num(0L);
								meneuver.setHard3Num(0L);
								meneuver.setHard4Num(0L);
								meneuver.setHard5Num(0L);
								meneuver.setNohardNum(t);
								meneuver.setScore(item.getQuestionScore());
								meneuver.setStatus("0");
								meneuverList.add(meneuver);
							}
						}
					}else{ //如果取题总量已经大于要取题数量时
						t = t - (num - questionNum);
						if(t > 0){
							//加入对象
							if("0".equals(item.getQuestionType())){	//如果不限题型
								Hashtable hd = getQuestionGroupbyType(_tempList);
								for(int j = 0; j <= 9; j ++){		//循环题型 1-9是各题型的编号
									List __tempList = (List)hd.get(j + "");
									if(__tempList != null && __tempList.size() >= t){
										ZjManeuverList meneuver = new ZjManeuverList();
										meneuver.setQuizid(quizId);
										meneuver.setScope(chapter);
										meneuver.setQuestionType(j + "");
										meneuver.setHard1Num(0L);
										meneuver.setHard2Num(0L);
										meneuver.setHard3Num(0L);
										meneuver.setHard4Num(0L);
										meneuver.setHard5Num(0L);
										meneuver.setNohardNum(t);
										meneuver.setScore(item.getQuestionScore());
										meneuver.setStatus("0");
										meneuverList.add(meneuver);
										break;
									}else if(__tempList != null && __tempList.size() < t){
										ZjManeuverList meneuver = new ZjManeuverList();
										meneuver.setQuizid(quizId);
										meneuver.setScope(chapter);
										meneuver.setQuestionType(j + "");
										meneuver.setHard1Num(0L);
										meneuver.setHard2Num(0L);
										meneuver.setHard3Num(0L);
										meneuver.setHard4Num(0L);
										meneuver.setHard5Num(0L);
										meneuver.setNohardNum(Long.parseLong(__tempList.size() + ""));
										meneuver.setScore(item.getQuestionScore());
										meneuver.setStatus("0");
										meneuverList.add(meneuver);
										t = t - __tempList.size();
									}
								}
							}else{
								ZjManeuverList meneuver = new ZjManeuverList();
								meneuver.setQuizid(quizId);
								meneuver.setScope(chapter);
								meneuver.setQuestionType(item.getQuestionType());
								meneuver.setHard1Num(0L);
								meneuver.setHard2Num(0L);
								meneuver.setHard3Num(0L);
								meneuver.setHard4Num(0L);
								meneuver.setHard5Num(0L);
								meneuver.setNohardNum(t);
								meneuver.setScore(item.getQuestionScore());
								meneuver.setStatus("0");
								meneuverList.add(meneuver);
							}
						}
						break;
					}
				}
			}
		}
		return meneuverList;
	}
	
	/**
	 * 对题目LIST按章节进行分组
	 * @param questionList 题目LIST
	 * @return             一个装载了按章分组题的LIST的Hashtable
	 * 			           固定的Key: _idList 一个装了章ID信息的List
	 * 			           其他的Key: 以章ID作为Key值装载了该章下的题的List
	 */
	public Hashtable getQuestionGroupbyChapter(List questionList){
		Hashtable rd = new Hashtable();
		List _idList = new Vector();
		if(questionList != null && questionList.size() > 0){
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuestion question = (ZjQuestion)questionList.get(i);
//				String chapter = "_all";
//				if(question.getChapter() == null || "".equals(question.getChapter())){ //如果章ID不为空则取得章ID
//					chapter = question.getChapter();
//				}
				String chapter = "";
				if(question.getCategory()!=null){
					chapter = question.getCategory();
				}
				if(!chapter.equals("")){
				if(rd.containsKey(chapter)){
					List qList = (List)rd.get(chapter);
					qList.add(question);
					rd.put(chapter, qList);
				}else{
					List qList = new Vector();
					qList.add(question);
					rd.put(chapter, qList);
					_idList.add(chapter);
				}
				}
			}
		}
		rd.put("_idList", _idList);
		return rd;
	}
	
	/**
	 * 对题目LIST按题型进行分组
	 * @param questionList 题目LIST
	 * @return             一个装载了按章分组题的LIST的Hashtable
	 * 			           其他的Key: 以题型代码作为Key值装载了该题型下的题的List
	 */
	public Hashtable getQuestionGroupbyType(List questionList){
		Hashtable rd = new Hashtable();
		if(questionList != null && questionList.size() > 0){
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuestion question = (ZjQuestion)questionList.get(i);
				String qType = question.getQtype();
				if(rd.containsKey(qType)){
					List qList = (List)rd.get(qType);
					qList.add(question);
					rd.put(qType, qList);
				}else{
					List qList = new Vector();
					qList.add(question);
					rd.put(qType, qList);
				}
			}
		}
		return rd;
	}
	
	public boolean saveQuizQuestion(String quizId){
		boolean t = true;
		List questionList = new Vector();
		List list = this.getManeuverManager().getManeuverListbyQuiz(quizId);//获取一门考试下的策略详细列表
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				ZjManeuverList meneuver = (ZjManeuverList)list.get(i);
				
				String scope = meneuver.getScope();               //取得章ID
				String questionType = meneuver.getQuestionType(); //取得题型
				long hard1Num = meneuver.getHard1Num();			  //取得容易题数 难度系数 0.1~0.2
				long hard2Num = meneuver.getHard2Num();			  //取得较易题数 难度系数 0.3~0.4
				long hard3Num = meneuver.getHard3Num();			  //取得中等题数 难度系数 0.5~0.6
				long hard4Num = meneuver.getHard4Num();			  //取得较难题数 难度系数 0.7~0.8
				long hard5Num = meneuver.getHard5Num();			  //取得难题数   难度系数 0.9~1.0
				long nohardNum = meneuver.getNohardNum();		  //取得所有题数 难度系数 0.1~1.0
				String score = meneuver.getScore();				  //取得每题分值
				long totalNum = hard1Num + hard2Num + hard3Num + hard4Num + hard5Num + nohardNum;
				if(checkTotalQuestion(scope, questionType, totalNum)){ //是否满足总题量
					if(hard1Num > 0){
						if(!checkTotalQuestionbyDifficuty(scope, questionType, hard1Num, 0.1, 0.2)){ //是否满足容易题量
							t = false;  //失败
							break;
						}
					}
					
					if(hard2Num > 0){
						if(!checkTotalQuestionbyDifficuty(scope, questionType, hard2Num, 0.3, 0.4)){ //是否满足较易题量
							t = false;  //失败
							break;
						}
					}
					
					if(hard3Num > 0){
						if(!checkTotalQuestionbyDifficuty(scope, questionType, hard3Num, 0.5, 0.6)){ //是否满足中等题量
							t = false;  //失败
							break;
						}
					}
					
					if(hard4Num > 0){
						if(!checkTotalQuestionbyDifficuty(scope, questionType, hard4Num, 0.7, 0.8)){ //是否满足较难题量
							t = false;  //失败
							break;
						}
					}
					
					if(hard5Num > 0){
						if(!checkTotalQuestionbyDifficuty(scope, questionType, hard5Num, 0.9, 1.0)){ //是否满足难题量
							t = false;  //失败
							break;
						}
					}
					
					if(nohardNum > 0){
						if(!checkTotalQuestionbyDifficuty(scope, questionType, totalNum, 0, 0)){ //是否满足不限题量
							t = false;  //失败
							break;
						}
					}
					questionList = loadList(meneuver, questionList, quizId);
					
				}else{
					t = false;  //失败
					break;
				}
			}
		}
		
		if(t == true){
			this.getManeuverManager().removeQuizQuestion(quizId);			//首先移除掉作业试题关系
			this.getManeuverManager().saveQuizQuestionList(questionList);
		}
		
		return t;
	}
	
	/**
	 * 检测总题数是否满足条件
	 * @param scope			章ID
	 * @param questionType	题型
	 * @param totalNum		总题量
	 * @return             	true-满足 false-不满足
	 */
	public boolean checkTotalQuestion(String scope, String questionType, long totalNum){
		int num = this.getManeuverManager().getQuestionCount(scope, questionType, 0.1, 1.0);
		if(totalNum > num){
			return false;
		}else{
			return true;
		}		
	}
	
	/**
	 * 检测按难度系数的题量是否满足条件
	 * @param scope				章ID
	 * @param questionType		题型
	 * @param num				题量
	 * @param difficult_s		开始难度系数(如不加控制,该项为0)
	 * @param difficult_e		结束难度系数(如不加控制,该项为0)
	 * @return					true-满足 false-不满足
	 */
	public boolean checkTotalQuestionbyDifficuty(String scope, String questionType, long num, double difficult_s, double difficult_e){
		int num1 = this.getManeuverManager().getQuestionCount(scope, questionType, difficult_s, difficult_e);
		if(num > num1){
			return false;
		}else{
			return true;
		}		
	}
	
	/**
	 * 组装要保存的作业和试题关系List
	 * @param meneuver		策略详细
	 * @param questionList  组装用的List对象
	 * @param quizId		作业ID
	 * @return				组装好的List
	 */
	public List loadList(ZjManeuverList meneuver, List questionList, String quizId){
		String scope = meneuver.getScope();               //取得章ID
		String questionType = meneuver.getQuestionType(); //取得题型
		long hard1Num = meneuver.getHard1Num();			  //取得容易题数 难度系数 0.1~0.2
		long hard2Num = meneuver.getHard2Num();			  //取得较易题数 难度系数 0.3~0.4
		long hard3Num = meneuver.getHard3Num();			  //取得中等题数 难度系数 0.5~0.6
		long hard4Num = meneuver.getHard4Num();			  //取得较难题数 难度系数 0.7~0.8
		long hard5Num = meneuver.getHard5Num();			  //取得难题数   难度系数 0.9~1.0
		long nohardNum = meneuver.getNohardNum();		  //取得所有题数 难度系数 0.1~1.0
		String score = meneuver.getScore();				  //取得每题分值
		long belongto = 10;								  //定义出题套数
		
		for(long i = 0; i < belongto; i ++){
			Hashtable rd = new Hashtable();
			if(hard1Num > 0){ //取容易题
				List qList = this.getManeuverManager().getQuestion(scope, questionType, 0.1, 0.2);
				for(int j = 0; j < hard1Num; j ++){
					Random random = new Random();
					int rand = random.nextInt(qList.size());
					ZjQuestion question = (ZjQuestion)qList.get(rand);
					qList.remove(rand);
					ZjQuizQuestion qQuestion = new ZjQuizQuestion();
					qQuestion.setQuiz(quizId);
					qQuestion.setQuestion(question);
					qQuestion.setGrade(Long.parseLong(score));
					qQuestion.setBelongto(i);
					questionList.add(qQuestion);
					rd.put(question.getId(), "");
				}
			}
			
			if(hard2Num > 0){  //取较易题
				List qList = this.getManeuverManager().getQuestion(scope, questionType, 0.3, 0.4);
				for(int j = 0; j < hard2Num; j ++){
					Random random = new Random();
					int rand = random.nextInt(qList.size());
					ZjQuestion question = (ZjQuestion)qList.get(rand);
					qList.remove(rand);
					ZjQuizQuestion qQuestion = new ZjQuizQuestion();
					qQuestion.setQuiz(quizId);
					qQuestion.setQuestion(question);
					qQuestion.setGrade(Long.parseLong(score));
					qQuestion.setBelongto(i);
					questionList.add(qQuestion);
					rd.put(question.getId(), "");
				}
			}
			
			if(hard3Num > 0){  //取中等题
				List qList = this.getManeuverManager().getQuestion(scope, questionType, 0.5, 0.6);
				for(int j = 0; j < hard3Num; j ++){
					Random random = new Random();
					int rand = random.nextInt(qList.size());
					ZjQuestion question = (ZjQuestion)qList.get(rand);
					qList.remove(rand);
					ZjQuizQuestion qQuestion = new ZjQuizQuestion();
					qQuestion.setQuiz(quizId);
					qQuestion.setQuestion(question);
					qQuestion.setGrade(Long.parseLong(score));
					qQuestion.setBelongto(i);
					questionList.add(qQuestion);
					rd.put(question.getId(), "");
				}
			}
			
			if(hard4Num > 0){  //取较难题
				List qList = this.getManeuverManager().getQuestion(scope, questionType, 0.7, 0.8);
				for(int j = 0; j < hard4Num; j ++){
					Random random = new Random();
					int rand = random.nextInt(qList.size());
					ZjQuestion question = (ZjQuestion)qList.get(rand);
					qList.remove(rand);
					ZjQuizQuestion qQuestion = new ZjQuizQuestion();
					qQuestion.setQuiz(quizId);
					qQuestion.setQuestion(question);
					qQuestion.setGrade(Long.parseLong(score));
					qQuestion.setBelongto(i);
					questionList.add(qQuestion);
					rd.put(question.getId(), "");
				}
			}
			
			if(hard5Num > 0){  //取难题
				List qList = this.getManeuverManager().getQuestion(scope, questionType, 0.9, 1.0);
				for(int j = 0; j < hard5Num; j ++){
					Random random = new Random();
					int rand = random.nextInt(qList.size());
					ZjQuestion question = (ZjQuestion)qList.get(rand);
					qList.remove(rand);
					ZjQuizQuestion qQuestion = new ZjQuizQuestion();
					qQuestion.setQuiz(quizId);
					qQuestion.setQuestion(question);
					qQuestion.setGrade(Long.parseLong(score));
					qQuestion.setBelongto(i);
					questionList.add(qQuestion);
					rd.put(question.getId(), "");
				}
			}
			
			if(nohardNum > 0){  //取不限题
				List qList = this.getManeuverManager().getQuestion(scope, questionType, 0.1, 1.0);
				for(int j = 0; j < nohardNum; j ++){
					Random random = new Random();
					int rand = random.nextInt(qList.size());
					ZjQuestion question = (ZjQuestion)qList.get(rand);
					qList.remove(rand);
					if(!rd.containsKey(question.getId())){ //判断该题是否已取
						ZjQuizQuestion qQuestion = new ZjQuizQuestion();
						qQuestion.setQuiz(quizId);
						qQuestion.setQuestion(question);
						qQuestion.setGrade(Long.parseLong(score));
						qQuestion.setBelongto(i);
						questionList.add(qQuestion);
					}else{
						j = j - 1;
					}
				}
			}
		}
		return questionList;
	}
	
	public String getChapterSelectOptionsHtml(String courseId){
		List list = this.maneuverManager.getChapterbyCourse(courseId);
		StringBuffer html = new StringBuffer("");
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				ZjQuestionCategory course = (ZjQuestionCategory)list.get(i);
				html.append("<option value=\"" + course.getId() + "\">" + course.getName() + "</option>/n");
			}
		}
		return html.toString();
	}
	
	public String getQuestionSelectOptionsHtml(){
		StringBuffer html = new StringBuffer("");
		html.append("<option value=\"" + Constants.QUESTION_SINGLECHOICE + "\">单选题</option>/n");
		html.append("<option value=\"" + Constants.QUESTION_MULTICHOICE + "\">多选题</option>/n");
		html.append("<option value=\"" + Constants.QUESTION_JUDGE + "\">判断题</option>/n");
		html.append("<option value=\"" + Constants.QUESTION_MATCHING + "\">匹配题</option>/n");
		html.append("<option value=\"" + Constants.QUESTION_INTEGRATE + "\">综合题</option>/n");
		return html.toString();
	}
	
	public List getManeuverList(String quizId){
		return this.getManeuverManager().getManeuverList(quizId);
	}
	
	public void removeManeuverbyId(String maneuverId){
		this.getManeuverManager().removeManeuverbyId(maneuverId);
	}
	
	public boolean saveManeuver(ZjManeuverList maneuver){
		List list = this.getManeuverManager().getManeuverListbyScopeAndQuestiontype(maneuver.getQuizid(), maneuver.getScope(), maneuver.getQuestionType());
		if(list != null && list.size() > 0){
			return false;
		}else{
			this.getManeuverManager().saveManeuverList(maneuver);
			return true;
		}
	}
	
	public boolean updateManeuver(String maneuverId, HttpServletRequest request){
		ZjManeuverList maneuver = this.getManeuverManager().getManeuverbyId(maneuverId);
		if(maneuver != null){
			maneuver.setHard1Num(Long.parseLong(request.getParameter("hard1Num")));
			maneuver.setHard2Num(Long.parseLong(request.getParameter("hard2Num")));
			maneuver.setHard3Num(Long.parseLong(request.getParameter("hard3Num")));
			maneuver.setHard4Num(Long.parseLong(request.getParameter("hard4Num")));
			maneuver.setHard5Num(Long.parseLong(request.getParameter("hard5Num")));
			maneuver.setNohardNum(Long.parseLong(request.getParameter("nohardNum")));
			maneuver.setScore(request.getParameter("score"));
			this.getManeuverManager().saveManeuverList(maneuver);
			return true;
		}else{
			return false;
		}
	}
	
	
	public IManeuverManager getManeuverManager() {
		return maneuverManager;
	}

	public void setManeuverManager(IManeuverManager maneuverManager) {
		this.maneuverManager = maneuverManager;
	}
}
