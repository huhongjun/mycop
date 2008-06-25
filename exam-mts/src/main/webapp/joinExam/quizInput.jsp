<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<%@ page import="com.zhjedu.util.*" %>
<%@ page import="com.zhjedu.util.Constants" %>
<%@ page import="com.zhjedu.exam.domain.*" %>
<%
response.setHeader("content-disposition","attachment;filename=exam.doc"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷导出</title>
</head>
<body>
<form name="examForm" action="<%=request.getContextPath()%>/joinExam.do?method=sbtExam" method="post">
<P align="center"><bean:write name="quiz" property="name"/></P>

<%
	double _totalScore = 0;
	int questionNo = 0;
	int questionNo2 = 0;
	ArrayList questionId = new ArrayList();
	StringBuffer _questionId = new StringBuffer("");
	String op = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Hashtable questionInfo = (Hashtable)request.getAttribute("quizQuestion");
	if(questionInfo.containsKey(Constants.QUESTION_SINGLECHOICE)){
		List questionList = (List)questionInfo.get(Constants.QUESTION_SINGLECHOICE);
		if(questionList != null && questionList.size() > 0){
			questionNo = questionNo + 1;
%>
	<%=UserSessionInfo.getQuestionNo(questionNo - 1) %>、单选题
    <table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				questionNo2 = questionNo2 + 1;
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
				_totalScore += quizQuestion.getGrade();
%>
      <tr>
            <td width="52" valign="top">第<%=questionNo2 %>题 </td>
            <td height="25" colspan="2" align="left"><%=question.getQuestioncontext() %> <span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
        </tr>
<%
				List optionList = question.getOptionList();
				if(optionList != null && optionList.size() > 0){
					
					for(int j = 0; j < optionList.size(); j ++){
						ZjQuestionOption option = (ZjQuestionOption)optionList.get(j);
%>
          <tr>
            <td  height="25" align="right"></td>
            <td width="22"><%=op.substring(j, j + 1) %> </td>
            <td width="858" align="left"><%=option.getOptioncontext() %> </td>
          </tr>
<%
					}
				}
			}
%>
        </table>
<%
		}
	}
%>

<%
	if(questionInfo.containsKey(Constants.QUESTION_MULTICHOICE)){
		List questionList = (List)questionInfo.get(Constants.QUESTION_MULTICHOICE);
		if(questionList != null && questionList.size() > 0){
			questionNo = questionNo + 1;
%>
    <%=UserSessionInfo.getQuestionNo(questionNo - 1) %>、多选题
<table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				questionNo2 = questionNo2 + 1;
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
				_totalScore += quizQuestion.getGrade();
%>
		<tr>
              <td width="52" valign="top">第<%=questionNo2 %>题</td>
              <td height="25" colspan="2" align="left"> <%=question.getQuestioncontext() %> （<%=quizQuestion.getGrade() %>分）</td>
        </tr>
<%
				List optionList = question.getOptionList();
				if(optionList != null && optionList.size() > 0){
					
					for(int j = 0; j < optionList.size(); j ++){
						ZjQuestionOption option = (ZjQuestionOption)optionList.get(j);
%>
            <tr>
              <td  height="25" align="right"></td>
              <td width="22"><%=op.substring(j, j + 1) %> </td>
              <td width="858" align="left"> <%=option.getOptioncontext() %> </td>
      </tr>
<%
					}
				}
			}
%>
    </table>
<%
		}
	}
%>

<%
	if(questionInfo.containsKey(Constants.QUESTION_JUDGE)){
		List questionList = (List)questionInfo.get(Constants.QUESTION_JUDGE);
		if(questionList != null && questionList.size() > 0){
			questionNo = questionNo + 1;
%>
    <%=UserSessionInfo.getQuestionNo(questionNo - 1) %>、判断题
<table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				questionNo2 = questionNo2 + 1;
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
				_totalScore += quizQuestion.getGrade();
%>
            <tr>
              <td width="50" valign="top">第<%=questionNo2 %>题</td>
              <td width="886" height="25" align="left"><%=question.getQuestioncontext() %> <span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
            </tr>
<%
			}
%>
    </table>
<%
		}
	}
%>

<%
	if(questionInfo.containsKey(Constants.QUESTION_INPUTFILL)){
		List questionList = (List)questionInfo.get(Constants.QUESTION_INPUTFILL);
		if(questionList != null && questionList.size() > 0){
			questionNo = questionNo + 1;
%>
    <%=UserSessionInfo.getQuestionNo(questionNo - 1) %>、填空题
<table class="tb-form">
<%
			for(int i = 0; i < questionList.size(); i ++){
				questionNo2 = questionNo2 + 1;
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				long num = question.getQuestionNum();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
				_totalScore += quizQuestion.getGrade();
%>
		<tr>
              <td width="52" valign="top"><span >第<%=questionNo2 %>题 </span></td>
              <td height="22" colspan="2" align="left"><%=question.getQuestioncontext() %><span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
        </tr>
<%
				for(int j = 0; j < num; j ++){
%>
      <tr>
              <td align="right"><%=j + 1 %>、</td>
              <td width="673" align="left">____________________</td>
              <td width="207"></td>
      </tr>
<%
				}
			}
%>
    </table>
<%
		}
	}
%>


<%
	if(questionInfo.containsKey(Constants.QUESTION_ANSWER)){
		List questionList = (List)questionInfo.get(Constants.QUESTION_ANSWER);
		if(questionList != null && questionList.size() > 0){
			questionNo = questionNo + 1;
%>
    <%=UserSessionInfo.getQuestionNo(questionNo - 1) %>、简答题
<table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				questionNo2 = questionNo2 + 1;
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
				_totalScore += quizQuestion.getGrade();
%>
		<tr>
              <td width="52" valign="top"><span >第<%=questionNo2 %>题 </span></td>
              <td height="22" width="810" align="left"><%=question.getQuestioncontext() %><span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
        </tr>
		<tr>
              <td width="52"></td>
              <td height="22" colspan="2" align="left"><br/><br/><br/><br/><br/><br/></td>
        </tr>
<%
			}
%>
      </table>
<%
		}
	}
%>


<%
	if(questionInfo.containsKey(Constants.QUESTION_MATCHING)){
		List questionList = (List)questionInfo.get(Constants.QUESTION_MATCHING);
		if(questionList != null && questionList.size() > 0){
			questionNo = questionNo + 1;
%>
        <%=UserSessionInfo.getQuestionNo(questionNo - 1) %>、匹配题
        <table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				questionNo2 = questionNo2 + 1;
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				List matchingOptionList = question.getMatchingOptionList();
				List matchingAnswerList = question.getMatchingAnswerList();
				_totalScore += quizQuestion.getGrade();
%>
		<tr>
              <td width="52" valign="top"><span >第<%=questionNo2 %>题 </span></td>
              <td height="22" colspan="2" align="left"><%=question.getQuestioncontext() %><span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
        </tr>
<%
				if(matchingOptionList != null && matchingOptionList.size() > 0){
					for(int j = 0; j < matchingOptionList.size(); j ++){
						ZjQuestionMatchingOption matchingOption = (ZjQuestionMatchingOption)matchingOptionList.get(j);
						ZjQuestionMatchingAnswer matchingAnswer = (ZjQuestionMatchingAnswer)matchingAnswerList.get(j);
						questionId.add(matchingOption.getId());
						_questionId.append("," + matchingOption.getId());
%>
          <tr>
            <td width="52"><%=j + 1 %>. <%=matchingOption.getOptioncontent() %></td>
            <td width="72"><%=op.substring(j, j + 1) %>. <%=matchingAnswer.getAnswercontext() %></td>
            <td width="738" align="left">
            </td>
          </tr>
<%
					}
				}
			}
%>
        </table> 
<%
		}
	}
%>


<%
	for(int a = 0; a < 3; a ++){
	

	if(questionInfo.containsKey(Constants.QUESTION_INTEGRATE + a)){
		List questionList = (List)questionInfo.get(Constants.QUESTION_INTEGRATE + a);
		String title = "综合题";
		if(a == 1){
			title = "阅读理解";
		}else if(a == 2){
			title = "完型填空";
		}
		if(questionList != null && questionList.size() > 0){
			questionNo = questionNo + 1;
%>
        <%=UserSessionInfo.getQuestionNo(questionNo - 1) %>、<%=title %>
      <table>

<%
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				List sonQuestionList = question.getSonQuestionList();
				_totalScore += quizQuestion.getGrade();
%>
  <tr>
            <td width="52" valign="top">第<%=UserSessionInfo.getQuestionNo(i) %>部分</td>
            <td height="22"  width="755"  align="left"><%=question.getQuestioncontext() %>（<%=quizQuestion.getGrade() %>分）</td>
        	<td width="125">&nbsp;</td>
        </tr>
<%
				if(sonQuestionList != null && sonQuestionList.size() > 0){
					for(int j = 0; j < sonQuestionList.size(); j ++){
						questionNo2 = questionNo2 + 1;
						String score = UserSessionInfo.getScore(Float.parseFloat(quizQuestion.getGrade().toString()), sonQuestionList.size(), j);
						ZjQuestion sonQuestion = (ZjQuestion)sonQuestionList.get(j);
						_questionId.append("," + sonQuestion.getId());
						questionId.add(sonQuestion.getId());
						if(Constants.QUESTION_SINGLECHOICE.equals(sonQuestion.getQtype())){	//子题为单选题
							List optionList = sonQuestion.getOptionList();
							if(sonQuestion.getQuestioncontext() != null && !"".equals(sonQuestion.getQuestioncontext())){
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125">&nbsp;</td>
          </tr>
<%
								if(optionList != null && optionList.size() > 0){
								Set optionListOn = sonQuestion.getOptionListOn();
								if(optionListOn != null){
									Iterator it = optionListOn.iterator();
									if(it.hasNext()){
										ZjQuestionOptionListOn listOn = (ZjQuestionOptionListOn)it.next();
										long x = listOn.getId().getListOn();
										if(x == 1){	//全部横排
%>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
<%
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %>
<%
									}
%>
            </td>
            <td width="125">&nbsp;</td>
          </tr>
<%
										}else if(x == 2){	//分两列排
%>
          <tr>
          	<td height="25" align="right"></td>
            <td height="25" width="755" align="left">
            	<table style="width:700px;">
<%
											int col = optionList.size() % 2;
											if(col > 0) 
												col = optionList.size() / 2 + 1;
											else
												col = optionList.size() / 2;
											for(int k = 0; k < col; k ++){
										
%>
            		<tr>
<%
												for(int t = 0; t < 2; t ++){
													if(2 * k + t < optionList.size()){
														ZjQuestionOption option = (ZjQuestionOption)optionList.get(2 * k + t);
%>
            			<td height="25" style="width:350px;" align="left">
			            <%=op.substring(2 * k + t, 2 * k + t + 1) %> <%=option.getOptioncontext() %>
            			</td>
<%
													}else{
%>
            			<td height="25" style="width:350px;" align="left">&nbsp;</td>
<%
													}
												}
%>
            		</tr>
<%
											}
%>
            	</table>
            </td>
            <td width="125"></td>
          </tr>
<%
										}else{
%>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
<%
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %><br/>
<%
									}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
										}
									}
								}
								}
							}else{
								if(optionList != null && optionList.size() > 0){
								Set optionListOn = sonQuestion.getOptionListOn();
								if(optionListOn != null){
									Iterator it = optionListOn.iterator();
									if(it.hasNext()){
										ZjQuestionOptionListOn listOn = (ZjQuestionOptionListOn)it.next();
										long x = listOn.getId().getListOn();
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left">
<%
										if(x == 1){	//全部横排

									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %>
<%
									}
										}else if(x == 2){	//分两列排
%>
            	<table style="width:700px;">
<%
											int col = optionList.size() % 2;
											if(col > 0) 
												col = optionList.size() / 2 + 1;
											else
												col = optionList.size() / 2;
											for(int k = 0; k < col; k ++){
										
%>
            		<tr>
<%
												for(int t = 0; t < 2; t ++){
													if(2 * k + t < optionList.size()){
														ZjQuestionOption option = (ZjQuestionOption)optionList.get(2 * k + t);
%>
            			<td height="25" style="width:350px;" align="left">
			            <%=op.substring(2 * k + t, 2 * k + t + 1) %> <%=option.getOptioncontext() %>
            			</td>
<%
													}else{
%>
            			<td height="25" style="width:350px;" align="left">&nbsp;</td>
<%
													}
												}
%>
            		</tr>
<%
											}
%>
            	</table>

<%
										}else{
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %><br/>
<%
									}
										}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
								}
							}
							}}
						}else if(Constants.QUESTION_MULTICHOICE.equals(sonQuestion.getQtype())){	//子题为多选题
							List optionList = sonQuestion.getOptionList();
							if(sonQuestion.getQuestioncontext() != null && !"".equals(sonQuestion.getQuestioncontext())){
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125">&nbsp;</td>
          </tr>
<%
								if(optionList != null && optionList.size() > 0){
								Set optionListOn = sonQuestion.getOptionListOn();
								if(optionListOn != null){
									Iterator it = optionListOn.iterator();
									if(it.hasNext()){
										ZjQuestionOptionListOn listOn = (ZjQuestionOptionListOn)it.next();
										long x = listOn.getId().getListOn();
										if(x == 1){	//全部横排
%>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
<%
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %>
<%
									}
%>
            </td>
            <td width="125">&nbsp;</td>
          </tr>
<%
										}else if(x == 2){	//分两列排
%>
          <tr>
          	<td height="25" align="right"></td>
            <td height="25" width="755" align="left">
            	<table style="width:700px;">
<%
											int col = optionList.size() % 2;
											if(col > 0) 
												col = optionList.size() / 2 + 1;
											else
												col = optionList.size() / 2;
											for(int k = 0; k < col; k ++){
										
%>
            		<tr>
<%
												for(int t = 0; t < 2; t ++){
													if(2 * k + t < optionList.size()){
														ZjQuestionOption option = (ZjQuestionOption)optionList.get(2 * k + t);
%>
            			<td height="25" style="width:350px;" align="left">
			            <%=op.substring(2 * k + t, 2 * k + t + 1) %> <%=option.getOptioncontext() %>
            			</td>
<%
													}else{
%>
            			<td height="25" style="width:350px;" align="left">&nbsp;</td>
<%
													}
												}
%>
            		</tr>
<%
											}
%>
            	</table>
            </td>
            <td width="125"></td>
          </tr>
<%
										}else{
%>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
<%
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %><br/>
<%
									}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
										}
									}
								}
%>


<%
								}
							}else{
								if(optionList != null && optionList.size() > 0){
								Set optionListOn = sonQuestion.getOptionListOn();
								if(optionListOn != null){
									Iterator it = optionListOn.iterator();
									if(it.hasNext()){
										ZjQuestionOptionListOn listOn = (ZjQuestionOptionListOn)it.next();
										long x = listOn.getId().getListOn();
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left">
<%
										if(x == 1){	//全部横排

									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %>
<%
									}
										}else if(x == 2){	//分两列排
%>
            	<table style="width:700px;">
<%
											int col = optionList.size() % 2;
											if(col > 0) 
												col = optionList.size() / 2 + 1;
											else
												col = optionList.size() / 2;
											for(int k = 0; k < col; k ++){
										
%>
            		<tr>
<%
												for(int t = 0; t < 2; t ++){
													if(2 * k + t < optionList.size()){
														ZjQuestionOption option = (ZjQuestionOption)optionList.get(2 * k + t);
%>
            			<td height="25" style="width:350px;" align="left">
			            <%=op.substring(2 * k + t, 2 * k + t + 1) %> <%=option.getOptioncontext() %>
            			</td>
<%
													}else{
%>
            			<td height="25" style="width:350px;" align="left">&nbsp;</td>
<%
													}
												}
%>
            		</tr>
<%
											}
%>
            	</table>

<%
										}else{
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %><br/>
<%
									}
										}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
								}
							}
							}}
						}else if(Constants.QUESTION_JUDGE.equals(sonQuestion.getQtype())){	//子题为判断题
							List optionList = sonQuestion.getOptionList();
							if(sonQuestion.getQuestioncontext() != null && !"".equals(sonQuestion.getQuestioncontext())){
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125"></td>
          </tr>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
            </td>
            <td width="125"></td>
          </tr>
<%
							}else{
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left">
            </td>
            <td width="125"></td>
          </tr>
<%
							}
						}else if(Constants.QUESTION_INPUTFILL.equals(sonQuestion.getQtype())){	//子题为填空题
							long num = sonQuestion.getQuestionNum();
							if(sonQuestion.getQuestioncontext() != null && !"".equals(sonQuestion.getQuestioncontext())){
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125"></td>
          </tr>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
<%
								for(int k = 0; k < num; k ++){
%>
				<%=k + 1 %> ____________________ 
<%
								}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
							}else{
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left">
<%
								for(int k = 0; k < num; k ++){
%>
				<%=k + 1 %> ____________________
<%
								}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
							}
						}else if(Constants.QUESTION_ANSWER.equals(sonQuestion.getQtype())){	//子题为问答题
							if(sonQuestion.getQuestioncontext() != null && !"".equals(sonQuestion.getQuestioncontext())){
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125"></td>
          </tr>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left"><br/><br/><br/><br/><br/><br/></td>
            <td width="125"></td>
          </tr>
<%
							}else{
%>
          <tr>
            <td height="25" align="right" valign="top"><span >第<%=questionNo2 %>题</span></td>
            <td width="755" align="left"><br/><br/><br/><br/><br/><br/></td>
            <td width="125"></td>
          </tr>
<%
							}
						}
					}
				}
			}
%>
        </table>
<%
		}
	}
	}
%>
</form>
</body>

</html>