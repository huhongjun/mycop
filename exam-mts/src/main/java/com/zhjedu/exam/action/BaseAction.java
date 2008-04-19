package com.zhjedu.exam.action;
import org.apache.struts.actions.DispatchAction;

import com.zhjedu.exam.domain.ZjUser;
import com.zhjedu.exam.service.IAssignmentService;
import com.zhjedu.exam.service.IAssignmentWService;
import com.zhjedu.exam.service.IJoinExamService;
import com.zhjedu.exam.service.IManeuverService;
import com.zhjedu.exam.service.IPaperService;
import com.zhjedu.exam.service.IQuestionService;
import com.zhjedu.exam.service.IQuizService;
import com.zhjedu.exam.service.impl.MenuBuilder;
import com.zhjedu.util.ApplictionContext;


public class BaseAction extends DispatchAction {
	protected IQuestionService getQuestionService() {
		return (IQuestionService) ApplictionContext.getInstance().getApplictionContext().getBean("questionFacade");
	}

	protected IQuizService getQuizService() {
		return (IQuizService) ApplictionContext.getInstance().getApplictionContext().getBean("quizFacade");
	}
	
	protected IManeuverService getManeuverService() {
		return (IManeuverService) ApplictionContext.getInstance().getApplictionContext().getBean("maneuverFacade");
	}
	
	protected IJoinExamService getJoinExamService() {
		return (IJoinExamService) ApplictionContext.getInstance().getApplictionContext().getBean("joinExamFacade");
	}
	
	protected IAssignmentService getAssignmentService() {
		return (IAssignmentService) ApplictionContext.getInstance().getApplictionContext().getBean("iassignmentService");
	}
	
	protected MenuBuilder getMb() {
		return (MenuBuilder) ApplictionContext.getInstance().getApplictionContext().getBean("mb");
	}
	
	protected IPaperService getPaperService() {
		return (IPaperService) ApplictionContext.getInstance().getApplictionContext().getBean("paperFacade");
	}
	
	protected IAssignmentWService getAss() {
		return (IAssignmentWService) ApplictionContext.getInstance().getApplictionContext().getBean("assignmentWFacade");
	}
	
}
