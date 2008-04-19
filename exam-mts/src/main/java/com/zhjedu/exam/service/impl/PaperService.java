package com.zhjedu.exam.service.impl;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.zhjedu.exam.service.IPaperService;
import com.zhjedu.exam.manager.IPaperManager;
import com.zhjedu.exam.domain.ZjPaper;
import com.zhjedu.exam.domain.ZjQuestion;
import com.zhjedu.exam.domain.ZjQuizQuestion;
import com.zhjedu.util.PageObject;


public class PaperService implements IPaperService {
	private IPaperManager paperManager;

	public IPaperManager getPaperManager() {
		return paperManager;
	}

	public void setPaperManager(IPaperManager paperManager) {
		this.paperManager = paperManager;
	}
	
	public PageObject getPaperList(String courseId, String name, int page){
		return paperManager.getPaperList(courseId, name, page);
	}
	
	public void removePaper(String[] paperId){
		if(paperId != null && paperId.length > 0){
			paperManager.removePaper(paperId);
		}
	}
	
	public ZjPaper getPaper(String paperId){
		return paperManager.getPaper(paperId);
	}
	
	public List getPaperQuestion(String paperId){
		return paperManager.getPaperQuestion(paperId);
	}
	
	public void removeQuestion(String paperId, String[] questionId){
		if(questionId != null && questionId.length > 0 && paperId != null && !"".equals(paperId)){
			paperManager.removeQuestion(paperId, questionId);
		}
	}
	
	public void savePaperQuestion(String paperId, String[] questionId, int score){
		if(questionId != null && questionId.length > 0 && paperId != null && !"".equals(paperId)){
			Vector paperQuestionList = new Vector();	//用来装载要添加的列表
			List questionList = paperManager.getQuestion(questionId);	//取得题目信息
			if(questionList != null && questionList.size() > 0){
				for(int i = 0; i < questionList.size(); i ++){
					ZjQuizQuestion quizQuestion = new ZjQuizQuestion();
					quizQuestion.setPaper(paperId);
					quizQuestion.setQuestion((ZjQuestion)questionList.get(i));
					quizQuestion.setBelongto(0L);
					quizQuestion.setGrade((long)score);
					paperQuestionList.add(quizQuestion);
				}
			}
			paperManager.savePaperQuestion(paperQuestionList);
		}
	}
	
	public void savePaper(HttpServletRequest request){
		ZjPaper paper = loadData(request, new ZjPaper());
		paperManager.savePaper(paper);
	}
	
	public void updatePaper(HttpServletRequest request){
		String paperId = request.getParameter("id");
		ZjPaper paper = paperManager.getPaper(paperId);
		paper = loadData(request, paper);
		paperManager.savePaper(paper);
	}
	
	public void stopPaper(String paperId){
		paperManager.savePaperStatus(paperId, "1");
	}
	
	public boolean pubPaper(String paperId){
		List list = paperManager.getPaperQuestion(paperId);
		long totalScore = 0;
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)list.get(i);
				totalScore += quizQuestion.getGrade();
			}
		}
		if(totalScore == 100){
			paperManager.savePaperStatus(paperId, "2");
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 读取试卷数据
	 * @param request	传递参数对象
	 * @param paper		试卷对象
	 * @return			要保存的试卷信息
	 */
	public ZjPaper loadData(HttpServletRequest request, ZjPaper paper){
		if(paper.getId() == null || "".equals(paper.getId())){
			String course = request.getParameter("courseId");
			if(course != null && !"".equals(course)){
				paper.setCourse(course);
			}
			paper.setQuestionsperpage(0L);
			paper.setRandomquestions(" ");
			paper.setCreatedate(System.currentTimeMillis());	
			paper.setDelflag("0");
			paper.setStatus("0");
			paper.setBuildType("1");//目前只有固定方式    0随机;1固定;
		}
		String name = request.getParameter("name");
		if(name != null && !"".equals(name)){
			paper.setName(name);
		}
		String description = request.getParameter("description");
		System.out.println(">>>" + description);
		if(description != null && !"".equals(description)){
			paper.setDescription(description);
		}
		String source = request.getParameter("source");
		if(source != null && !"".equals(source)){
			paper.setSource(source);
		}
		paper.setLasteditdate(System.currentTimeMillis());
		return paper;
	}
}