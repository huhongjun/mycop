package com.zhjedu.exam.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.dfcw.zjproject.zj.dao.ScoreDAO;
import com.dfcw.zjproject.zj.dao.ScoreDAOImpl;
import com.dfcw.zjproject.zj.model.ExamCourseModel;
import com.zhjedu.exam.service.IJoinExamService;
import com.zhjedu.exam.manager.IJoinExamManager;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuestionMatchingOption;
import com.zhjedu.exam.domain.ZjQuiz;
import com.zhjedu.exam.domain.ZjQuizAnswers;
import com.zhjedu.exam.domain.ZjQuizExam;
import com.zhjedu.exam.domain.ZjQuizMidEc;
import com.zhjedu.exam.domain.ZjQuizQuestion;
import com.zhjedu.util.Constants;
import com.zhjedu.util.StringUtil;
import com.zhjedu.util.UserSessionInfo;


public class joinExamService implements IJoinExamService {
	private IJoinExamManager joinExamManager;
	
	public ZjQuiz getQuizbyId(String quizId){
		return this.getJoinExamManager().getQuizbyId(quizId);		
	}
	
	public Hashtable getQuestionbyQuizid(ZjQuiz quiz, boolean isPaper){

			List list = new Vector();
			if(isPaper){ //判断是否是固定试卷
				//取得固定试卷1级题
				list = this.getJoinExamManager().getQuestionList(quiz.getId(), quiz.getPaper());
			}else{
				Random random = new Random();
				int rand = random.nextInt(10);
				list = this.getJoinExamManager().getQuestionList(quiz.getId(), rand);
			}
			Hashtable rd = this.getQuestionbyType(list, null);
			ZjQuizExam quizExam = new ZjQuizExam();
			rd.put("quizExam", quizExam);
			return rd;
	}
	
	
	public Hashtable getQuestionbyQuizid(String userId, String quizId, boolean isPaper){
		//取得未完成作业情况
		ZjQuizExam quizExam = this.getJoinExamManager().getQuizExam(userId, quizId);
		if(quizExam != null){
			List list = new Vector();
			if(isPaper){ //判断是否是固定试卷
				//取得固定试卷1级题
				list = this.getJoinExamManager().getQuestionList(quizExam.getZjQuiz().getId(), quizExam.getPaper());
			}else{
				list = this.getJoinExamManager().getQuestionList(quizExam.getZjQuiz().getId(), Integer.parseInt(quizExam.getPaper()));
			}
			Hashtable rd = this.getQuestionbyType(list, quizExam.getId());
			rd.put("quizExam", quizExam);
			this.getJoinExamManager().updateQuizExamStatus(quizExam.getId(), "1", true);
			return rd;
		}else{
			return null;
		}
	}
	
	public Hashtable getQuestionbyQuizid2(String userId, String quizId, boolean isPaper){
		//取得未完成作业情况
		ZjQuizExam quizExam = this.getJoinExamManager().getQuizExam(userId, quizId);
		if(quizExam != null){
			List list = new Vector();
			if(isPaper){ //判断是否是固定试卷
				//取得固定试卷1级题
				list = this.getJoinExamManager().getQuestionList(quizExam.getZjQuiz().getId(), quizExam.getPaper());
			}else{
				list = this.getJoinExamManager().getQuestionList(quizExam.getZjQuiz().getId(), Integer.parseInt(quizExam.getPaper()));
			}
			Hashtable rd = this.getQuestionbyType(list, quizExam.getId());
			rd.put("quizExam", quizExam);
			return rd;
		}else{
			return null;
		}
	}
	
	public Hashtable getQuestionAnswerbyQuizid(String userId, String quizId, boolean isPaper){
		//取得未完成作业情况
		ZjQuizExam quizExam = this.getJoinExamManager().getQuizExam(userId, quizId);
		if(quizExam != null){
			List list = new Vector();
			if(isPaper){ //判断是否是固定试卷
				//取得固定试卷1级题
				list = this.getJoinExamManager().getQuestionList(quizExam.getZjQuiz().getId(), quizExam.getPaper());
			}else{
				list = this.getJoinExamManager().getQuestionList(quizExam.getZjQuiz().getId(), Integer.parseInt(quizExam.getPaper()));
			}
			Hashtable rd = this.getQuestionbyType(list, quizExam.getId());
			rd.put("quizExam", quizExam);
			return rd;
		}else{
			return null;
		}
	}
	
public void saveExamScore2(HttpServletRequest request, String userId, String quizId, boolean isPaper, String totalhour){
		
		//将提交的答案保存进数据库
		Hashtable questionInfo = this.getQuestionbyQuizid(userId, quizId, isPaper);
		ZjQuizExam quizExam = (ZjQuizExam)questionInfo.get("quizExam");
		quizExam.setTotalhour(Long.parseLong(totalhour));
		List saveList = new Vector();
		saveList.add(quizExam);
		this.getJoinExamManager().saveQuizExam(saveList);
//		首先移除所有题
		String quizExamId = quizExam.getId();
		this.getJoinExamManager().removeQuizAnswer(quizExamId);
		
		List quizAnswer = new Vector();
		if(questionInfo.containsKey(Constants.QUESTION_SINGLECHOICE)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_SINGLECHOICE);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
				}
			}
		}
		
		
		if(questionInfo.containsKey(Constants.QUESTION_MULTICHOICE)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_MULTICHOICE);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
				}
			}
		}
		
		
		if(questionInfo.containsKey(Constants.QUESTION_JUDGE)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_JUDGE);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
				}
			}
		}
		
		
		if(questionInfo.containsKey(Constants.QUESTION_MATCHING)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_MATCHING);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					List matchingOptionList = question.getMatchingOptionList();
					double score = quizQuestion.getGrade();
					int rightNum = 0;
					String __answer = "";
					if(matchingOptionList != null && matchingOptionList.size() > 0){
						for(int j = 0; j < matchingOptionList.size(); j ++){
							ZjQuestionMatchingOption matchingOption = (ZjQuestionMatchingOption)matchingOptionList.get(j);
							
							
							String[] answer = request.getParameterValues(matchingOption.getId());
							String _answer = "";
							
							if(answer != null)
								_answer = StringUtil.combineStringArray(answer, ",");
							
							if(_answer.equals(matchingOption.getAnswer())){
								rightNum = rightNum + 1;
							}
							__answer += "," + _answer;
						}
					}
					if(__answer != null && __answer.length() > 0)
						__answer = __answer.substring(1);
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(__answer);
					_quizAnswer.setGrade(0.0);
//					_quizAnswer.setGrade(Math.ceil(score * ((float)rightNum / (float)matchingOptionList.size())));
					//System.out.println(score + "*(" + rightNum + "/" + matchingOptionList.size() + ")=" + score * ((float)rightNum / (float)matchingOptionList.size()) + ">>" + Math.ceil(score * ((float)rightNum / (float)matchingOptionList.size())));
					quizAnswer.add(_quizAnswer);
					
				}
			}
		}
		
		
		if(questionInfo.containsKey(Constants.QUESTION_INPUTFILL)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_INPUTFILL);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
//					if(_answer.equals(question.getAnswers())){
//						score = quizQuestion.getGrade();
//					}
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
				}
			}
		}
		
		if(questionInfo.containsKey(Constants.QUESTION_ANSWER)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_ANSWER);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
//					if(_answer.equals(question.getAnswers())){
//						score = quizQuestion.getGrade();
//					}
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
				}
			}
		}
		
		for(int a = 0; a < 3; a ++){
			if(questionInfo.containsKey(Constants.QUESTION_INTEGRATE + a)){
				List questionList = (List)questionInfo.get(Constants.QUESTION_INTEGRATE + a);
				if(questionList != null && questionList.size() > 0){
					for(int i = 0; i < questionList.size(); i ++){
						ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
						ZjQuestion question = quizQuestion.getQuestion();
						List sonQuestionList = question.getSonQuestionList();
						
						ZjQuizAnswers __quizAnswer = new ZjQuizAnswers();
						__quizAnswer.setUserid(userId);
						__quizAnswer.setExam(quizExamId);
						__quizAnswer.setQuestion(question.getId());
						__quizAnswer.setAnswer("");
						__quizAnswer.setGrade(0.0);
						quizAnswer.add(__quizAnswer);
						
						
						if(sonQuestionList != null && sonQuestionList.size() > 0){
							for(int j = 0; j < sonQuestionList.size(); j ++){
								ZjQuestion sonQuestion = (ZjQuestion)sonQuestionList.get(j);
								String[] answer = request.getParameterValues(sonQuestion.getId());
								String _answer = "";
								double score = 0;
								if(answer != null)
									_answer = StringUtil.combineStringArray(answer, ",");
//								if(sonQuestion.getQtype().equals(Constants.QUESTION_SINGLECHOICE) || sonQuestion.getQtype().equals(Constants.QUESTION_MULTICHOICE) || sonQuestion.getQtype().equals(Constants.QUESTION_JUDGE)){
//									if(_answer.equals(sonQuestion.getAnswers())){
//										String score1 = UserSessionInfo.getScore(Float.parseFloat(quizQuestion.getGrade().toString()), sonQuestionList.size(), j);
//										score = Double.parseDouble(score1);
//									}
//								}
								
								ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
								_quizAnswer.setUserid(userId);
								_quizAnswer.setExam(quizExamId);
								_quizAnswer.setQuestion(sonQuestion.getId());
								_quizAnswer.setAnswer(_answer);
								_quizAnswer.setGrade(score);
								quizAnswer.add(_quizAnswer);
							}
						}
					}
				}
			}
		}
		
		this.getJoinExamManager().saveQuizAnswerList(quizAnswer);
//		将状态置为已批阅和总分
//		this.getJoinExamManager().updateQuizExamScore(quizExamId);
//		this.getJoinExamManager().updateQuizExamStatus(quizExamId, "3", false);
	}
	
	
	public void saveExamScore(HttpServletRequest request, String userId, String quizId, boolean isPaper){
		
		//将提交的答案保存进数据库
		Hashtable questionInfo = this.getQuestionbyQuizid(userId, quizId, isPaper);
		ZjQuizExam quizExam = (ZjQuizExam)questionInfo.get("quizExam");
		double totalscore = 0.0;
//		首先移除所有题
		String quizExamId = quizExam.getId();
		this.getJoinExamManager().removeQuizAnswer(quizExamId);
		
		List quizAnswer = new Vector();
		if(questionInfo.containsKey(Constants.QUESTION_SINGLECHOICE)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_SINGLECHOICE);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
					if(_answer.equals(question.getAnswers())){
						score = quizQuestion.getGrade();
					}
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
					totalscore += score;
				}
			}
		}
		
		
		if(questionInfo.containsKey(Constants.QUESTION_MULTICHOICE)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_MULTICHOICE);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
					if(_answer.equals(question.getAnswers())){
						score = quizQuestion.getGrade();
					}
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
					totalscore += score;
				}
			}
		}
		
		
		if(questionInfo.containsKey(Constants.QUESTION_JUDGE)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_JUDGE);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
					if(_answer.equals(question.getAnswers())){
						score = quizQuestion.getGrade();
					}
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
					totalscore += score;
				}
			}
		}
		
		
		if(questionInfo.containsKey(Constants.QUESTION_MATCHING)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_MATCHING);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					List matchingOptionList = question.getMatchingOptionList();
					double score = quizQuestion.getGrade();
					int rightNum = 0;
					String __answer = "";
					if(matchingOptionList != null && matchingOptionList.size() > 0){
						for(int j = 0; j < matchingOptionList.size(); j ++){
							ZjQuestionMatchingOption matchingOption = (ZjQuestionMatchingOption)matchingOptionList.get(j);
							
							
							String[] answer = request.getParameterValues(matchingOption.getId());
							String _answer = "";
							
							if(answer != null)
								_answer = StringUtil.combineStringArray(answer, ",");
							
							if(_answer.equals(matchingOption.getAnswer())){
								rightNum = rightNum + 1;
							}
							__answer += "," + _answer;
						}
					}
					if(__answer != null && __answer.length() > 0)
						__answer = __answer.substring(1);
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(__answer);
					_quizAnswer.setGrade(Math.ceil(score * ((float)rightNum / (float)matchingOptionList.size())));
					//System.out.println(score + "*(" + rightNum + "/" + matchingOptionList.size() + ")=" + score * ((float)rightNum / (float)matchingOptionList.size()) + ">>" + Math.ceil(score * ((float)rightNum / (float)matchingOptionList.size())));
					quizAnswer.add(_quizAnswer);
					totalscore += _quizAnswer.getGrade();
					
				}
			}
		}
		
		
		if(questionInfo.containsKey(Constants.QUESTION_INPUTFILL)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_INPUTFILL);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
//					if(_answer.equals(question.getAnswers())){
//						score = quizQuestion.getGrade();
//					}
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
					totalscore += score;
					
				}
			}
		}
		
		if(questionInfo.containsKey(Constants.QUESTION_ANSWER)){
			List questionList = (List)questionInfo.get(Constants.QUESTION_ANSWER);
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
					ZjQuestion question = quizQuestion.getQuestion();
					String[] answer = request.getParameterValues(question.getId());
					String _answer = "";
					double score = 0;
					if(answer != null)
						_answer = StringUtil.combineStringArray(answer, ",");
//					if(_answer.equals(question.getAnswers())){
//						score = quizQuestion.getGrade();
//					}
					ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
					_quizAnswer.setUserid(userId);
					_quizAnswer.setExam(quizExamId);
					_quizAnswer.setQuestion(question.getId());
					_quizAnswer.setAnswer(_answer);
					_quizAnswer.setGrade(score);
					quizAnswer.add(_quizAnswer);
					totalscore += score;
				}
			}
		}
		
		for(int a = 0; a < 3; a ++){
			if(questionInfo.containsKey(Constants.QUESTION_INTEGRATE + a)){
				List questionList = (List)questionInfo.get(Constants.QUESTION_INTEGRATE + a);
				if(questionList != null && questionList.size() > 0){
					for(int i = 0; i < questionList.size(); i ++){
						ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
						ZjQuestion question = quizQuestion.getQuestion();
						List sonQuestionList = question.getSonQuestionList();
						
						ZjQuizAnswers __quizAnswer = new ZjQuizAnswers();
						__quizAnswer.setUserid(userId);
						__quizAnswer.setExam(quizExamId);
						__quizAnswer.setQuestion(question.getId());
						__quizAnswer.setAnswer("");
						__quizAnswer.setGrade(0.0);
						quizAnswer.add(__quizAnswer);
						
						
						if(sonQuestionList != null && sonQuestionList.size() > 0){
							for(int j = 0; j < sonQuestionList.size(); j ++){
								ZjQuestion sonQuestion = (ZjQuestion)sonQuestionList.get(j);
								String[] answer = request.getParameterValues(sonQuestion.getId());
								String _answer = "";
								double score = 0;
								if(answer != null)
									_answer = StringUtil.combineStringArray(answer, ",");
								if(sonQuestion.getQtype().equals(Constants.QUESTION_SINGLECHOICE) || sonQuestion.getQtype().equals(Constants.QUESTION_MULTICHOICE) || sonQuestion.getQtype().equals(Constants.QUESTION_JUDGE)){
									if(_answer.equals(sonQuestion.getAnswers())){
										String score1 = UserSessionInfo.getScore(Float.parseFloat(quizQuestion.getGrade().toString()), sonQuestionList.size(), j);
										score = Double.parseDouble(score1);
									}
								}
								
								ZjQuizAnswers _quizAnswer = new ZjQuizAnswers();
								_quizAnswer.setUserid(userId);
								_quizAnswer.setExam(quizExamId);
								_quizAnswer.setQuestion(sonQuestion.getId());
								_quizAnswer.setAnswer(_answer);
								_quizAnswer.setGrade(score);
								quizAnswer.add(_quizAnswer);
								totalscore += score;
							}
						}
					}
				}
			}
		}
		
		this.getJoinExamManager().saveQuizAnswerList(quizAnswer);
//		将状态置为已批阅和总分
		this.getJoinExamManager().updateQuizExamScore(quizExamId);
		this.getJoinExamManager().updateQuizExamStatus(quizExamId, "3", false);	
		//回写中间库
		try {
			ScoreDAO sdao = new ScoreDAOImpl();
			sdao.addEntraceScore(Integer.parseInt(quizExam.getMidExamCourseId()), Integer.parseInt(quizExam.getUserid()), Float.parseFloat(totalscore + ""));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//quizExam
	}
	
	/**
	 * 分题型装载试题的完整信息
	 * @param questionList	一级题LIST
	 * @return				分题型装载试题完整信息的Hashtable
	 */
	public Hashtable getQuestionbyType(List questionList, String quizExamId){
		Hashtable rd = new Hashtable();
		if(questionList != null && questionList.size() > 0){
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				question = this.getQuestionAnswer(question, quizExamId);
				String alias = "";
				if(question.getAlias() != null){
					alias = question.getAlias();
				}
				if(rd.containsKey(question.getQtype() + alias)){
					List tempList = (List)rd.get(question.getQtype() + alias);
					question = this.getQuestionInfo(question);
					if(Constants.QUESTION_INTEGRATE.equals(question.getQtype())){
						List sonQuestionList = question.getSonQuestionList();
						List _tempList = new Vector();
						if(sonQuestionList != null && sonQuestionList.size() > 0){
							for(int j = 0; j < sonQuestionList.size(); j ++){
								ZjQuestion sonQuestion = (ZjQuestion)sonQuestionList.get(j);
								sonQuestion = this.getQuestionAnswer(sonQuestion, quizExamId);
								_tempList.add(sonQuestion);
							}
						}
						question.setSonQuestionList(_tempList);
					}
					quizQuestion.setQuestion(question);
					
					
					
					tempList.add(quizQuestion);
					rd.put(question.getQtype() + alias, tempList);
				}else{
					List tempList = new Vector();
					question = this.getQuestionInfo(question);
					if(Constants.QUESTION_INTEGRATE.equals(question.getQtype())){
						List sonQuestionList = question.getSonQuestionList();
						List _tempList = new Vector();
						if(sonQuestionList != null && sonQuestionList.size() > 0){
							for(int j = 0; j < sonQuestionList.size(); j ++){
								ZjQuestion sonQuestion = (ZjQuestion)sonQuestionList.get(j);
								sonQuestion = this.getQuestionAnswer(sonQuestion, quizExamId);
								_tempList.add(sonQuestion);
							}
						}
						question.setSonQuestionList(_tempList);
					}
					quizQuestion.setQuestion(question);
					tempList.add(quizQuestion);
					rd.put(question.getQtype() + alias, tempList);
				}
			}
		}
		return rd;
	}
	
	/**
	 * 获取用户这道题的答案
	 * @param question		题对象
	 * @param quizExamId	用户考试信息ID
	 * @return				装载了答案的题对象
	 */
	public ZjQuestion getQuestionAnswer(ZjQuestion question, String quizExamId){
		ZjQuizAnswers quizAnswers = this.getJoinExamManager().getQuestionAnswer(quizExamId, question.getId());
		if(quizAnswers != null)
			question.setUAnswer(quizAnswers);
		return question;
	}
	
	/**
	 * 通过一道一级题,获取该题的子题/选项等所有信息(不会对判断/填空/简答题进行操作)
	 * @param question	一级题对象
	 * @return			包含所有信息的题对象
	 */
	public ZjQuestion getQuestionInfo(ZjQuestion question){
		if(Constants.QUESTION_SINGLECHOICE.equals(question.getQtype()) || Constants.QUESTION_MULTICHOICE.equals(question.getQtype())){ //单选题 or 多选题
			question.setOptionList(this.getJoinExamManager().getQuestionOptionList(question.getId()));
		}
		if(Constants.QUESTION_MATCHING.equals(question.getQtype())){ //匹配题
			question.setMatchingOptionList(this.getJoinExamManager().getQuestionMatchingOptionList(question.getId()));
			question.setMatchingAnswerList(this.getJoinExamManager().getQuestionMatchingAnswerList(question.getId()));
		}
		if(Constants.QUESTION_INTEGRATE.equals(question.getQtype())){ //综合题
			//获取子题
			List sonQuestionList = this.getJoinExamManager().getSonQuestionList(question.getId());
			List tempList = new Vector();
			if(sonQuestionList != null && sonQuestionList.size() > 0){
				for(int i = 0; i < sonQuestionList.size(); i ++){
					tempList.add(getQuestionInfo((ZjQuestion)sonQuestionList.get(i))); //递归
				}
			}
			question.setSonQuestionList(tempList);
		}
		return question;
	}
	
	public void saveScorebyquizAnswerId(String quizAnswerId, String score){
		this.getJoinExamManager().updateScore(quizAnswerId, score);
	}
	
	public void savePostilbyquizExamId(String quizExamId, String postil){
		this.getJoinExamManager().updatePostil(quizExamId, postil);
	}
	
	public void saveFinish(String quizExamId){
		this.getJoinExamManager().updateQuizExamScore(quizExamId);
		this.getJoinExamManager().updateQuizExamStatus(quizExamId, "3");
	}
	
	public boolean checkPass(String quizId, String password){
		List list = this.getJoinExamManager().getQuizList(quizId, password);
		if(list != null && list.size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	
	//****************************************  */
	//以下是同步学生参加考试数据的一些方法  2008-04-05
	//***************************************** */
	
	
	
	
	public void saveQuizExamList(String userId, ArrayList list){
		List saveList = new Vector();
		Hashtable rd = new Hashtable();
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				ExamCourseModel examCourse = (ExamCourseModel)list.get(i);
				List quizMidECList = this.getJoinExamManager().getQuizMidECList(examCourse.getID());
				if(quizMidECList != null && quizMidECList.size() > 0){
					for(int j = 0; j < quizMidECList.size(); j ++){
						ZjQuizMidEc quizMidEC = (ZjQuizMidEc)quizMidECList.get(j);
						//if(!rd.containsKey(quizMidEC.getQuiz())){
							//if(this.getJoinExamManager().isQuizExamHave(userId, quizMidEC.getQuiz())){
								ZjQuizExam quizExam = new ZjQuizExam();
								Random random = new Random();
								int rand = random.nextInt(10);
								quizExam.setZjQuiz(quizMidEC.getZjQuiz());
								quizExam.setUserid(userId);
								quizExam.setPaper(rand + "");
								quizExam.setMidExamCourseId(quizMidEC.getMidExamId());
								saveList.add(quizExam);
							//}
							//rd.put(quizMidEC.getQuiz(), "");
						//}
					}
				}
			}
		}
		this.getJoinExamManager().saveQuizExam(saveList);
	}
	
	
	public ZjQuizExam getQuizExam(String userId, String quizId){
		return this.getJoinExamManager().getQuizExam(userId, quizId);
	}
	
	
	
	
	
	public IJoinExamManager getJoinExamManager() {
		return joinExamManager;
	}

	public void setJoinExamManager(IJoinExamManager joinExamManager) {
		this.joinExamManager = joinExamManager;
	}

}