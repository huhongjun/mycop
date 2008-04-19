package com.zhjedu.exam.service.impl;

import java.util.List;
import java.util.Map;

import com.zhjedu.exam.domain.ZjQuestion;

import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.exam.domain.ZjQuestionCategory;
import com.zhjedu.exam.domain.ZjQuestionMatchingAnswer;
import com.zhjedu.exam.domain.ZjQuestionMatchingOption;
import com.zhjedu.exam.domain.ZjQuestionOption;
import com.zhjedu.exam.manager.IQuestionManager;
import com.zhjedu.exam.service.IQuestionService;
import com.zhjedu.util.PageObject;

public class QuestionService implements IQuestionService {
	
	public List getZjCategoryList(String parent) {
		// TODO Auto-generated method stub
		return questionManager.getZjCategoryList(parent);
	}

	public List getQuestionListByCategory(String category) {
		// TODO Auto-generated method stub
		return questionManager.getQuestionListByCategory(category);
	}

	public List getQuestionListByCategory(String category, List list) {
		// TODO Auto-generated method stub
		return questionManager.getQuestionListByCategory(category, list);
	}

	public String removeMatchingAnswer(String question) {
		// TODO Auto-generated method stub
		return questionManager.removeMatchingAnswer(question);
	}

	public String removeMatchingOption(String question) {
		// TODO Auto-generated method stub
		return questionManager.removeMatchingOption(question);
	}

	public String removeQuestionOption(String question) {
		// TODO Auto-generated method stub
		return questionManager.removeQuestionOption(question);
	}

	public PageObject getCategoryObject(Map map, String currentPage) {
		// TODO Auto-generated method stub
		return questionManager.getCategoryObject(map, currentPage);
	}

	public String removeQuestionCategory(String category) {
		// TODO Auto-generated method stub
		return questionManager.removeQuestionCategory(category);
	}

	public String saveQuestionCategory(ZjQuestionCategory category) {
		// TODO Auto-generated method stub
		return questionManager.saveQuestionCategory(category);
	}
	public List getCourseItemList(String course) {
		// TODO Auto-generated method stub
		return questionManager.getCourseItemList(course);
	}

	public List getAllCategory(String parent) {
		// TODO Auto-generated method stub
		return questionManager.getAllCategory(parent);
	}

	public ZjQuestionCategory getZjQuestionCategory(String id) {
		// TODO Auto-generated method stub
		return questionManager.getZjQuestionCategory(id);
	}

	private IQuestionManager questionManager;
	
	public ZjCourse getZjCourse(String id) {
		// TODO Auto-generated method stub
		return questionManager.getZjCourse(id);
	}

	public PageObject getQuestionObject(Map map, String currentPage) {
		// TODO Auto-generated method stub
		return questionManager.getQuestionObject(map, currentPage);
	}
	public PageObject getQuestionObject(Map map,String key, String currentPage) {
		// TODO Auto-generated method stub
		return questionManager.getQuestionObject(map,key, currentPage);
	}
	public PageObject getQuestionObject(String key,String qtype,String currentPage){
		return questionManager.getQuestionObject(key,qtype, currentPage);
	}
	public String removeQuestion(String[] questionid) {
		// TODO Auto-generated method stub
		return questionManager.removeQuestion(questionid);
	}

	public String saveQuestion(ZjQuestion question) {
		// TODO Auto-generated method stub
		String questionid = questionManager.saveQuestion(question);
		List optionList = question.getOptionList();
		List matchingOptionList = question.getMatchingOptionList();
		List matchingAnswerList = question.getMatchingAnswerList();
		if(optionList!=null&&!optionList.isEmpty()){
			for(int i=0;i<optionList.size();i++){
				ZjQuestionOption option = (ZjQuestionOption)optionList.get(i);
				option.setQuestion(questionid);
				questionManager.saveQuestionOption(option);
			}
		}
		if(matchingOptionList!=null&&!matchingOptionList.isEmpty()){
			for(int i=0;i<matchingOptionList.size();i++){
				ZjQuestionMatchingOption matchingOption = (ZjQuestionMatchingOption)matchingOptionList.get(i);
				matchingOption.setQuestion(questionid);
				questionManager.saveQuestionMatchingOption(matchingOption);
			}
		}
		if(matchingAnswerList!=null&&!matchingAnswerList.isEmpty()){
			for(int i=0;i<matchingAnswerList.size();i++){
				ZjQuestionMatchingAnswer matchingAnswer = (ZjQuestionMatchingAnswer)matchingAnswerList.get(i);
				matchingAnswer.setQuestion(questionid);
				questionManager.saveQuestionMatchingAnswer(matchingAnswer);
			}
		}
		return questionid;
	}
	
	public ZjQuestion getQuestion(String questionid) {
		// TODO Auto-generated method stub
		return questionManager.getQuestion(questionid);
	}

	public IQuestionManager getQuestionManager() {
		return questionManager;
	}

	public void setQuestionManager(IQuestionManager questionManager) {
		this.questionManager = questionManager;
	}

	public ZjCourse getZjCourseByOId(String oid) {
		// TODO Auto-generated method stub
		return questionManager.getZjCourseByOId(oid);
	}

	public ZjQuestionCategory getRootCategory() {
		// TODO Auto-generated method stub
		return questionManager.getRootCategory();
	}

}
