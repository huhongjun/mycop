<%@ page contentType="text/html; charset=UTF-8" import="com.zhjedu.exam.domain.*,java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<%@ page  import="com.fredck.FCKeditor.*" %> 
<html>
<head>
<%
			ZjQuestion question = (ZjQuestion)request.getAttribute("question");
			List matchingOptionsList = new ArrayList();
			List matchingAnswersList = new ArrayList();
			if(question!=null){
				matchingOptionsList = question.getMatchingOptionList();	
				matchingAnswersList = question.getMatchingAnswerList();
			}		
			StringBuffer js = new StringBuffer();
			js.append("<script language=\"javascript\" type=\"text/javascript\">\n");
			js.append("function setData() {\n");
			
			if(matchingOptionsList!=null&&!matchingOptionsList.isEmpty()){
				int length = 2;
				length = matchingOptionsList.size();
				if(matchingOptionsList.size()>matchingAnswersList.size()){
					length = matchingAnswersList.size();
				}
				ZjQuestionMatchingOption option1 = (ZjQuestionMatchingOption) matchingOptionsList.get(0);
				ZjQuestionMatchingOption option2 = (ZjQuestionMatchingOption) matchingOptionsList.get(1);
				ZjQuestionMatchingAnswer answer1 = (ZjQuestionMatchingAnswer) matchingAnswersList.get(0);
				ZjQuestionMatchingAnswer answer2 = (ZjQuestionMatchingAnswer) matchingAnswersList.get(1);
				js.append("setOption(" +"\""+ option1.getId()+"\"" + ","+"\""+option1.getOptioncontent()+"\""+","+"\""+answer1.getId()+"\""+","+"\""+answer1.getAnswercontext()+"\""+","+"\""+question.getAnswers()+"\""+");\n");
				js.append("setOption(" +"\""+ option2.getId()+"\"" + ","+"\""+option2.getOptioncontent()+"\""+","+"\""+answer2.getId()+"\""+","+"\""+answer2.getAnswercontext()+"\""+","+"\""+question.getAnswers()+"\""+");\n");
				for(int i = 2;i<length;i++) {
					ZjQuestionMatchingOption option = (ZjQuestionMatchingOption) matchingOptionsList.get(i);
					ZjQuestionMatchingAnswer answer = (ZjQuestionMatchingAnswer) matchingAnswersList.get(i);		
					js.append("setOption(" +"\""+ option.getId()+"\"" + ","+"\""+option.getOptioncontent()+"\""+","+"\""+answer.getId()+"\""+","+"\""+answer.getAnswercontext()+"\""+","+"\""+question.getAnswers()+"\""+");\n");
						
					
				}
				for(int j=0;j<length;j++){
					ZjQuestionMatchingOption option = (ZjQuestionMatchingOption) matchingOptionsList.get(j);
					js.append("document.QuestionForm.optionanswer["+j+"].value="+"\""+option.getAnswer()+"\""+";\n");
				}
			}
			else{
				js.append("init();");
			}
			js.append("}\n");
			js.append("</script>\n");
			//System.out.println(js);
			out.println(js);
			
			
%>
<script language="javascript">

	function sbt(){
		
		var editor=FCKeditorAPI.GetInstance("questioncontext");   
		
		var content=FCKeditorAPI.GetInstance("questioncontext").GetXHTML(true);//editor.EditorDocument.body.innerHTML;
		
		var matchingOptions = QuestionForm.matchingOptions;
		var matchingAnswers = QuestionForm.matchingAnswers;
		
		var rtflag = true;
		
		if(content== ""||content==null){
			alert("请输入题目内容!");
			rtflag=false;
			return false;
		}	
		
		
		if(matchingOptions!=null){
			for(var j = 0; j < matchingOptions.length; j ++){
				if(matchingOptions[j].value == "" || matchingOptions[j].value == null){
					alert("请填写完整匹配选项信息!");
					matchingOptions[j].focus();
					rtflag=false;
					return false;
				}
			}
		}
		
		if(matchingAnswers!=null){
			for(var k = 0; k < matchingAnswers.length; k ++){
				if(matchingAnswers[k].value == "" || matchingAnswers[k].value == null){
					alert("请填写完整匹配答案信息!");
					matchingAnswers[k].focus();
					rtflag=false;
					return false;
				}
			}
		}
		
	    return rtflag;
	}
	
	function init(){
		if(document.getElementById("options_a").innerHTML == ""){
			document.getElementById("options_a").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"A\"/>&nbsp;1.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;A.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}
		if(document.getElementById("options_b").innerHTML == ""){
			document.getElementById("options_b").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"B\"/>&nbsp;2.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;B.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}
	}
	
	function setOption(optionid,optioncontext,answerid,answercontext,answer){
		if(document.getElementById("options_a").innerHTML == ""){
			document.getElementById("options_a").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;1.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;A.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;	
				
		}else if(document.getElementById("options_b").innerHTML == ""){
			document.getElementById("options_b").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;2.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;B.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;
		}else if(document.getElementById("options_c").innerHTML == ""){
			document.getElementById("options_c").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;3.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;C.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;
		}else if(document.getElementById("options_d").innerHTML == ""){
			document.getElementById("options_d").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;4.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;D.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;
		}else if(document.getElementById("options_e").innerHTML == ""){
			document.getElementById("options_e").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;5.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;E.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;
		}else if(document.getElementById("options_f").innerHTML == ""){
			document.getElementById("options_f").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;6.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;F.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;
		}else if(document.getElementById("options_g").innerHTML == ""){
			document.getElementById("options_g").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;7.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;G.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;	
		}else if(document.getElementById("options_h").innerHTML == ""){
			document.getElementById("options_h").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;8.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;H.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;	
		}else if(document.getElementById("options_i").innerHTML == ""){
			document.getElementById("options_i").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;9.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;I.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');
			tmp11[tmp11.length-1].value=answerid;
			
			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;			
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;	
		}else if(document.getElementById("options_j").innerHTML == ""){
			document.getElementById("options_j").innerHTML = "<input type=\"hidden\" name=\"moptionid\"/><input type=\"hidden\" name=\"manswerid\"/>&nbsp;10.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;J.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" />&nbsp;&nbsp;&nbsp;&nbsp;<select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
			var tmp10=document.getElementsByName('matchingOptions');
			tmp10[tmp10.length-1].value=optioncontext;			
			var tmp11=document.getElementsByName('moptionid');

			var tmp20=document.getElementsByName('matchingAnswers');
			tmp20[tmp20.length-1].value=answercontext;		
			var tmp21=document.getElementsByName('manswerid');
			tmp21[tmp21.length-1].value=answerid;	
		}
		else{}
	}
	function addOption(){
		if(document.getElementById("options_c").innerHTML == ""){
			document.getElementById("options_c").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"C\"/>&nbsp;3.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;C.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" /><select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}else if(document.getElementById("options_d").innerHTML == ""){
			document.getElementById("options_d").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"D\"/>&nbsp;4.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;D.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" /><select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}else if(document.getElementById("options_e").innerHTML == ""){
			document.getElementById("options_e").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"E\"/>&nbsp;5.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;E.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" /><select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}else if(document.getElementById("options_f").innerHTML == ""){
			document.getElementById("options_f").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"F\"/>&nbsp;6.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;F.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" /><select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}else if(document.getElementById("options_g").innerHTML == ""){
			document.getElementById("options_g").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"G\"/>&nbsp;7.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;G.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" /><select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}else if(document.getElementById("options_h").innerHTML == ""){
			document.getElementById("options_h").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"H\"/>&nbsp;8.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;H.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" /><select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}else if(document.getElementById("options_i").innerHTML == ""){
			document.getElementById("options_i").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"viewFlag\" value=\"I\"/>&nbsp;9.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;I.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" /><select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}else if(document.getElementById("options_j").innerHTML == ""){
			document.getElementById("options_j").innerHTML = "<input type=\"radio\" style=\"display:none;\" name=\"answer\" value=\"J\"/>&nbsp;10.&nbsp;<input type=\"text\" name=\"matchingOptions\" size=\"20\" maxlength=\"30\" />&nbsp;J.<input type=\"text\" name=\"matchingAnswers\" size=\"20\" maxlength=\"30\" /><select name=\"optionanswer\"><option value=\"A\">A</option><option value=\"B\">B</option><option value=\"C\">C</option><option value=\"D\">D</option><option value=\"E\">E</option><option value=\"F\">F</option><option value=\"G\">G</option><option value=\"H\">H</option><option value=\"I\">I</option><option value=\"J\">J</option></select><br />";
		}
		else{
			alert("最多只能有10个选项!");
			return;
		}
	}
	function generateOptionAnswer(){
		
	}
	function removeOption(){
		if(document.getElementById("options_j").innerHTML != ""){
			document.getElementById("options_j").innerHTML = "";
		}else if(document.getElementById("options_i").innerHTML != ""){
			document.getElementById("options_i").innerHTML = "";
		}else if(document.getElementById("options_h").innerHTML != ""){
			document.getElementById("options_h").innerHTML = "";
		}else if(document.getElementById("options_g").innerHTML != ""){
			document.getElementById("options_g").innerHTML = "";
		}else if(document.getElementById("options_f").innerHTML != ""){
			document.getElementById("options_f").innerHTML = "";
		}else if(document.getElementById("options_e").innerHTML != ""){
			document.getElementById("options_e").innerHTML = "";
		}else if(document.getElementById("options_d").innerHTML != ""){
			document.getElementById("options_d").innerHTML = "";
		}else if(document.getElementById("options_c").innerHTML != ""){
			document.getElementById("options_c").innerHTML = "";
		}
		else{
			alert("至少必须有2个选项!");
			return;
		}
	}
</script>

</head>
<body>
<html:form method="post" action="/Question.do" onsubmit="if(!sbt()){return false;}">
	<html:hidden property="method" value="save"/>
	<html:hidden property="id" value="<%=request.getParameter("questionid") %>"/>
	<html:hidden property="parent" value="<%=request.getParameter("parent") %>"/>
	<html:hidden property="course" value="<%=request.getParameter("course") %>"/>
	<html:hidden property="chapter" value="<%=request.getParameter("chapter") %>"/>
	<html:hidden property="section" value="<%=request.getParameter("section") %>"/>
	<html:hidden property="category" value="<%=request.getParameter("category") %>"/>
	<html:hidden property="qtype" value="<%=request.getParameter("qtype") %>" />
	<html:hidden property="source" value="<%=(String)request.getAttribute("source") %>" />
	<html:hidden property="difficulty" value="<%=(String)request.getAttribute("difficulty") %>" />
	<html:hidden property="distinguish" value="<%=(String)request.getAttribute("distinguish") %>" />
	<html:hidden property="purpose" value="<%=(String)request.getAttribute("purpose") %>" />
	<html:hidden property="ability" value="<%=(String)request.getAttribute("ability") %>" />
	<html:hidden property="defaultgrade" value="<%=(String)request.getAttribute("defaultgrade") %>" />
	<html:hidden property="status" value="<%=(String)request.getAttribute("status") %>" />
	<html:hidden property="questionKey" value="<%=(String)request.getAttribute("questionKey") %>" />
	<html:hidden property="memo" value="<%=(String)request.getAttribute("memo") %>" />
	<html:hidden property="createdate" name="question" />
	
	<div align="center">
	
    	<table width="100%"  cellpadding="2" cellspacing="0" class="tbsort">
          <tbody>
          	<tr><td align="center" nowrap class="style1" width="100%"><%=(String)request.getAttribute("qname") %>-试题内容</td></tr>
          	<tr width=100% >
          		<td colspan=3 bgcolor="menu">
          		
				<logic:equal name = 'question' property='id' value=''>
		      		<input type='hidden' name='questioncontext'>
				</logic:equal>        
				<logic:notEqual name = 'question' property='id' value=''>
		      		<input type='hidden'  name='questioncontext' value='<bean:write name='question' property='questioncontext'/>'>
				</logic:notEqual> 
				<%
					FCKeditor oFCKeditor ;
					oFCKeditor = new FCKeditor( request, "questioncontext" ) ;
					oFCKeditor.setHeight("250");
					oFCKeditor.setBasePath(request.getContextPath()+"/FCKeditor/" ) ;
					oFCKeditor.setValue( "" );
					out.println( oFCKeditor.create() ) ;
				%>
          		
          		</td>
          	</tr>
         </table>
       </div>
       <div class="block5_2">
			<span id="options_a"></span>
    		<span id="options_b"></span>
    		<span id="options_c"></span>
    		<span id="options_d"></span>
    		<span id="options_e"></span>    
    		<span id="options_f"></span>
    		<span id="options_g"></span>
    		<span id="options_h"></span>
    		<span id="options_i"></span>
    		<span id="options_j"></span>
    		<span id="optionbutton">	 
    		<input type="button" class="xbutton" name="addsingle" onClick="addOption();" value="添加匹配项">
    		<input type="button" class="xbutton" name="getsingle" onClick="removeOption();" value="删除匹配项">
    		<span>
    	    
    	    
		</div>
    			
  			
    <div align="center">
		<table width="79%" border="0" cellpadding="2" cellspacing="0" class="tbsort">
		  <tbody>
            <iframe  height="0"  id="reviewframe" src="/resource/question/review.jsp"></iframe>
              <tr class="main5" align="center">
                <td align="center" nowrap></td>
                <td align="center" nowrap>               
                  &nbsp;<input name="Submit" type="submit" class="xbutton" value="确定">
                  <input name="updatasubmit1" type="button" class="xbutton" onClick="javascript:history.go(-1)" value="取消">                                 
                </td>
                <td align="center" nowrap>　</td>
              </tr>
              
          </tbody>
        </table>
	</div>
</html:form>
</body>
<script type="text/javascript" language="javascript">
	
	setData();
</script>
</html>