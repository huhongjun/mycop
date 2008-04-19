package com.zhjedu.exam.service;

import java.util.List;
import java.util.Map;

import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuestionCategory;
import com.zhjedu.util.PageObject;

public interface IQuestionService {

	/**
	 * ������޸���Ŀ��Ϣ
	 * @param question
	 * @return �����id
	 */
	public String saveQuestion(ZjQuestion question);
	
	
	/**
	 * ɾ������questionid������Ŀ��Ϣ��ֻ���߼�ɾ��
	 * @param questionid
	 * @return
	 */
	public String removeQuestion(String[] questionid);
	
	/**��ѯ**/
	
	/**
	 * 
	 * @param map ��ѯ����ļ�ֵ��
	 * @param currentPage ��ǰҳ
	 * @return����ҳ����PageObject
	 */
	public PageObject getQuestionObject(Map map,String currentPage);
	
	public ZjQuestion getQuestion(String questionid);
	
	public PageObject getQuestionObject(String key,String qtype,String currentPage);
	public PageObject getQuestionObject(Map map,String key,String currentPage);
	
	public ZjCourse getZjCourse(String id);
	public ZjQuestionCategory getZjQuestionCategory(String id);
	public ZjCourse getZjCourseByOId(String oid);
	public List getAllCategory(String parent);
	public ZjQuestionCategory getRootCategory();
	public List getCourseItemList(String course);
	
	public String saveQuestionCategory(ZjQuestionCategory category);
	public String removeQuestionCategory(String category);
	public PageObject getCategoryObject(Map map, String currentPage);
	
	
	public String removeQuestionOption( String question);
	public String removeMatchingOption( String question);
	public String removeMatchingAnswer( String question);
	
	public List getQuestionListByCategory(String category) ;
	public List getQuestionListByCategory(String category,List list);
	public List getZjCategoryList(String parent);
}
