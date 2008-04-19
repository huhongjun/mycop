<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<%@ page import="com.zhjedu.util.*" %>
<%@ page import="com.zhjedu.util.Constants" %>
<%@ page import="com.zhjedu.exam.domain.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参加考试</title>
<link href="<%=request.getContextPath()%>/css/shiti.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="examForm" action="<%=request.getContextPath()%>/joinExam.do?method=sbtExam" method="post">
<div align="center" class="bgall">
	<div class="toptitle"> 试 题 列 表</div>
       <div class="borderv">
    <div class="ms">
<%
	int questionNo = 0;
	ArrayList questionId = new ArrayList();
	StringBuffer _questionId = new StringBuffer("");
	String op = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Hashtable questionInfo = (Hashtable)request.getAttribute("quizQuestion");
	if(questionInfo.containsKey(Constants.QUESTION_SINGLECHOICE)){
		List questionList = (List)questionInfo.get(Constants.QUESTION_SINGLECHOICE);
		if(questionList != null && questionList.size() > 0){
			questionNo = questionNo + 1;
%>
	<div class="tt"><%=questionNo %>)、单选题</div>
    <div class="line-h">&nbsp;</div>
    <table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
%>
      <tr>
            <td width="52" >第<%=i + 1 %>题 </td>
            <td height="25" colspan="2" align="left"><%=question.getQuestioncontext() %> <span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
        </tr>
<%
				List optionList = question.getOptionList();
				if(optionList != null && optionList.size() > 0){
					
					for(int j = 0; j < optionList.size(); j ++){
						ZjQuestionOption option = (ZjQuestionOption)optionList.get(j);
%>
          <tr>
            <td  height="25" align="right"><input type="radio" name="<%=question.getId() %>" value="<%=op.substring(j, j + 1) %>"></td>
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
    <div class="tt"><%=questionNo %>)、多选题</div>
        <div class="line-h">&nbsp;</div>
<table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
%>
		<tr>
              <td width="52" >第<%=i + 1 %>题</td>
              <td height="25" colspan="2" align="left"> <%=question.getQuestioncontext() %> （<%=quizQuestion.getGrade() %>分）</td>
        </tr>
<%
				List optionList = question.getOptionList();
				if(optionList != null && optionList.size() > 0){
					
					for(int j = 0; j < optionList.size(); j ++){
						ZjQuestionOption option = (ZjQuestionOption)optionList.get(j);
%>
            <tr>
              <td  height="25" align="right"><input type="checkbox" name="<%=question.getId() %>" value="<%=op.substring(j, j + 1) %>"></td>
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
    <div class="tt"><%=questionNo %>)、判断题</div>
        <div class="line-h">&nbsp;</div>
<table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
%>
            <tr>
              <td width="50" >第<%=i + 1 %>题</td>
              <td width="886" height="25" align="left"><%=question.getQuestioncontext() %> <span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
            </tr>

            <tr>
              <td  height="25" align="right"><input type="radio" name="<%=question.getId() %>" value="1"></td>
              <td align="left">正确</td>
            </tr>
            <tr>
              <td height="25" align="right" ><input type="radio" name="<%=question.getId() %>" value="0"></td>
              <td align="left">错误</td>
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
    <div class="tt"><%=questionNo %>)、填空题</div>
        <div class="line-h">&nbsp;</div>
<table class="tb-form">
<%
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				long num = question.getQuestionNum();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
%>
		<tr>
              <td width="52"><span >第<%=i + 1 %>题 </span></td>
              <td height="22" colspan="2" align="left"><%=question.getQuestioncontext() %><span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
        </tr>
<%
				for(int j = 0; j < num; j ++){
%>
      <tr>
              <td align="right"><%=j + 1 %>、</td>
              <td width="673" align="left"><input type="text" name="<%=question.getId() %>" size="20"></td>
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
    <div class="tt"><%=questionNo %>)、简答题</div>
 	<div class="line-h">&nbsp;</div>
<table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				questionId.add(question.getId());
				_questionId.append("," + question.getId());
%>
		<tr>
              <td width="52"><span >第<%=i + 1 %>题 </span></td>
              <td height="22" colspan="2" align="left"><%=question.getQuestioncontext() %><span class="tx">（<%=quizQuestion.getGrade() %>分）</span></td>
        </tr>
		<tr>
              <td width="52"></td>
              <td height="22" colspan="2" align="left"><textarea rows="10" name="<%=question.getId() %>" cols="60" ></textarea></td>
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
        <div class="tt"><%=questionNo %>)、匹配题</div>
        <div class="line-h">&nbsp;</div>
        <table>
<%
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				List matchingOptionList = question.getMatchingOptionList();
				List matchingAnswerList = question.getMatchingAnswerList();
%>
		<tr>
              <td width="52"><span >第<%=i + 1 %>题 </span></td>
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
            <select size="1" name="<%=matchingOption.getId() %>">
<%
						for(int k = 0; k < matchingOptionList.size(); k ++){
%>
	            <option value="<%=op.substring(k, k + 1) %>"><%=op.substring(k, k + 1) %></option>
<%
						}
%>
            </select>
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
        <div class="tt"><%=questionNo %>)、<%=title %></div>
        <div class="line-h">&nbsp;</div>
      <table>

<%
			for(int i = 0; i < questionList.size(); i ++){
				ZjQuizQuestion quizQuestion = (ZjQuizQuestion)questionList.get(i);
				ZjQuestion question = quizQuestion.getQuestion();
				List sonQuestionList = question.getSonQuestionList();
%>
  <tr>
            <td width="52">第<%=i + 1 %>题</td>
            <td height="22" colspan="2" align="left"><%=question.getQuestioncontext() %>（<%=quizQuestion.getGrade() %>分）</td>
        </tr>
<%
				if(sonQuestionList != null && sonQuestionList.size() > 0){
					for(int j = 0; j < sonQuestionList.size(); j ++){
						String score = UserSessionInfo.getScore(Float.parseFloat(quizQuestion.getGrade().toString()), sonQuestionList.size(), j);
						ZjQuestion sonQuestion = (ZjQuestion)sonQuestionList.get(j);
						_questionId.append("," + sonQuestion.getId());
						questionId.add(sonQuestion.getId());
						if(Constants.QUESTION_SINGLECHOICE.equals(sonQuestion.getQtype())){	//子题为单选题
							List optionList = sonQuestion.getOptionList();
							if(sonQuestion.getQuestioncontext() != null && !"".equals(sonQuestion.getQuestioncontext())){
%>
          <tr>
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125"></td>
          </tr>
<%
								if(optionList != null && optionList.size() > 0){
%>

          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
<%
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <input type="radio" name="<%=sonQuestion.getId() %>" value="<%=op.substring(k, k + 1) %>">
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %>
<%
									}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
								}
							}else{
								if(optionList != null && optionList.size() > 0){
%>
          <tr>
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left">
<%
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <input type="radio" name="<%=sonQuestion.getId() %>" value="<%=op.substring(k, k + 1) %>">
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %>
<%
									}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
								}
							}
						}else if(Constants.QUESTION_MULTICHOICE.equals(sonQuestion.getQtype())){	//子题为多选题
							List optionList = sonQuestion.getOptionList();
							if(sonQuestion.getQuestioncontext() != null && !"".equals(sonQuestion.getQuestioncontext())){
%>
          <tr>
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125"></td>
          </tr>
<%
								if(optionList != null && optionList.size() > 0){
%>

          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
<%
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <input type="checkbox" name="<%=sonQuestion.getId() %>" value="<%=op.substring(k, k + 1) %>">
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %>
<%
									}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
								}
							}else{
								if(optionList != null && optionList.size() > 0){
%>
          <tr>
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left">
<%
									for(int k = 0; k < optionList.size(); k ++){
										ZjQuestionOption option = (ZjQuestionOption)optionList.get(k);
%>
            <input type="checkbox" name="<%=sonQuestion.getId() %>" value="<%=op.substring(k, k + 1) %>">
            <%=op.substring(k, k + 1) %> <%=option.getOptioncontext() %>
<%
									}
%>
            </td>
            <td width="125"></td>
          </tr>
<%
								}
							}
						}else if(Constants.QUESTION_JUDGE.equals(sonQuestion.getQtype())){	//子题为判断题
							List optionList = sonQuestion.getOptionList();
							if(sonQuestion.getQuestioncontext() != null && !"".equals(sonQuestion.getQuestioncontext())){
%>
          <tr>
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125"></td>
          </tr>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
			<input type="radio" name="<%=sonQuestion.getId() %>" value="1"> 正确
			<input type="radio" name="<%=sonQuestion.getId() %>" value="2"> 错误
            </td>
            <td width="125"></td>
          </tr>
<%
							}else{
%>
          <tr>
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left">
			<input type="radio" name="<%=sonQuestion.getId() %>" value="1"> 正确
			<input type="radio" name="<%=sonQuestion.getId() %>" value="2"> 错误
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
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125"></td>
          </tr>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left">
<%
								for(int k = 0; k < num; k ++){
%>
				<%=k + 1 %> <input type="text" name="<%=sonQuestion.getId() %>" size="20"> 
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
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left">
<%
								for(int k = 0; k < num; k ++){
%>
				<%=k + 1 %> <input type="text" name="<%=sonQuestion.getId() %>" size="20"> 
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
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left"><%=sonQuestion.getQuestioncontext() %> （<%=score %>分）</td>
            <td width="125"></td>
          </tr>
          <tr>
            <td height="25" align="right"></td>
            <td width="755" align="left"><textarea rows="10" name="<%=sonQuestion.getId() %>" cols="60" ></textarea></td>
            <td width="125"></td>
          </tr>
<%
							}else{
%>
          <tr>
            <td height="25" align="right"><span ><%=j + 1 %></span>、</td>
            <td width="755" align="left"><textarea rows="10" name="<%=sonQuestion.getId() %>" cols="60" ></textarea></td>
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
	}//xxxx

	}
%>
 
</div>
       </div>
 </div>
<%
String id = _questionId.toString();
if(id != null && id.length() > 0){
	id = id.substring(1);
}
%>
<input type="hidden" name="questionId" value="<%=id %>">
<input type="hidden" name="quizId" value="<%=request.getParameter("quizId") %>">
</form>
</body>
<script type="text/javascript">
	function checkFinish(){
<%
	if(questionId != null && questionId.size() > 0){
		for(int i = 0; i < questionId.size(); i ++){
%>
		if(!check(document.getElementsByName("<%=(String)questionId.get(i)%>"))){
			return;
		}
<%
		}
	}
%>
		alert("全部完成!");
	}
	
	function check(s){
		var k = 0;
		if(s.length){
			for(var i = 0; i < s.length; i ++){
				if(s[i].type == "radio" || s[i].type == "checkbox"){
					if(s[i].checked == false){
						k ++;
					}
				}else{
					if(s[i].value == null || s[i].value == ""){
						alert("有题目未完成!");
						s[i].focus();
						return false;
					}
				}
			}
			if(k == s.length){
				alert("有题目未完成!");
				s[0].focus();
				return false;
			}
		}else{
			if(s.type == "radio" || s.type == "checkbox"){
				if(s.checked == false){
					alert("有题目未完成!");
					s.focus();
					return false;
				}
			}else{
				if(s.value == null || s.value == ""){
					alert("有题目未完成!");
					s.focus();
					return false;
				}
			}
		}
		return true;
	}
	
	function sbt(){
<%
	if(questionId != null && questionId.size() > 0){
		for(int i = 0; i < questionId.size(); i ++){
%>
		nullToString(document.getElementsByName("<%=(String)questionId.get(i)%>"));
<%
		}
	}
%>
		examForm.submit();
	}
	
	function nullToString(s){
		if(s.length){
			for(var i = 0; i < s.length; i ++){
				if(s[i].type == "text" || s[i].type == "textarea"){
					if(s[i].value == null || s[i].value == ""){
						s[i].value = " ";
					}
				}
			}
		}else{
			if(s.type == "text" || s.type == "textarea"){
				if(s.value == null || s.value == ""){
					s.value = " ";
				}
			}
		}
	}
</script>
</html>