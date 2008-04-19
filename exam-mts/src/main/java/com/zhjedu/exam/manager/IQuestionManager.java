package com.zhjedu.exam.manager;

import java.util.List;
import java.util.Map;

import com.zhjedu.exam.domain.ZjCourse;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuestionCategory;
import com.zhjedu.exam.domain.ZjQuestionMatchingAnswer;
import com.zhjedu.exam.domain.ZjQuestionMatchingOption;
import com.zhjedu.exam.domain.ZjQuestionOption;
import com.zhjedu.util.PageObject;

public interface IQuestionManager {
	
	/** ������޸�**/
	
	/**
	 * ������޸���Ŀ��Ϣ
	 * @param question
	 * @return �����id
	 */
	public String saveQuestion(ZjQuestion question);
	
	
	/**
	 * ������޸�ƥ����ƥ������Ϣ���������Ȳ�����Ŀ��Ϣ������ȡ����Ŀ��Ϣ�����
	 * @param questionMatching
	 * @return
	 */
	public String saveQuestionMatchingOption(ZjQuestionMatchingOption questionMatchingOption);
	
	/**
	 * ������޸�ƥ����Ĵ���Ϣ���������Ȳ�����Ŀ��Ϣ������ȡ��ƥ������Ϣ�����
	 * @param questionMatchingSub
	 * @return
	 */
	public String saveQuestionMatchingAnswer(ZjQuestionMatchingAnswer questionMatchingAnswer);
	
	/**
	 * ������޸�ѡ����Ϣ���������Ȳ����Ӧ������Ŀ����Ϣ������ȡ����Ŀ��Ϣ�����
	 * @param questionOption
	 * @return
	 */
	public String saveQuestionOption(ZjQuestionOption questionOption);
	
	
	
	/**
	 * ɾ������questionid������Ŀ��Ϣ��ֻ���߼�ɾ��
	 * @param questionid
	 * @return
	 */
	public String removeQuestion(String[] questionid);
	
	/**��ѯ**/
	
	/**
	 * ���map�����¼�Ĳ�ѯ�����ȡ�����������Ŀ
	 * @param map��ѯ����ļ�ֵ��
	 * @param currentPage ��ǰҳ
	 * @return����ҳ����PageObject
	 */
	public PageObject getQuestionObject(Map map, String currentPage);
	public PageObject getQuestionObject(Map map,String key, String currentPage);
	public PageObject getQuestionObject(String key,String qtype,String currentPage);
	public ZjQuestion getQuestion(String questionid);
	
	public List getMenuList(String courseid);
	
	public ZjCourse getZjCourse(String id);
	public ZjCourse getZjCourseByOId(String oid);
	public ZjQuestionCategory getZjQuestionCategory(String id);
	public List getAllCategory(String parent);
	public ZjQuestionCategory getRootCategory();
	public List getCourseItemList(String course);
	
	
	public String saveQuestionCategory(ZjQuestionCategory category);
	public String removeQuestionCategory(String category);
	public PageObject getCategoryObject(Map map, String currentPage);
	
	public String removeQuestionOption( String question);
	public String removeMatchingOption( String question);
	public String removeMatchingAnswer( String question);
	
	public List getQuestionListByCategory(String category,List list);
	
	public List getQuestionListByCategory(String category) ;
	
	public List getZjCategoryList(String parent);
}
