<%@ page contentType="text/html; charset=UTF-8" import="com.zhjedu.exam.domain.*,java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<%@ page  import="com.fredck.FCKeditor.*" %>

<html>
<head>
<%
			ZjQuestion question = (ZjQuestion)request.getAttribute("question");
			List optionList = new ArrayList();
			if(question!=null){
				optionList = question.getOptionList();	
			}		
			StringBuffer js = new StringBuffer();
			js.append("<script language=\"javascript\" type=\"text/javascript\">\n");
			js.append("function setData() {\n");
			
			if(optionList!=null&&!optionList.isEmpty()){
				ZjQuestionOption option1 = (ZjQuestionOption) optionList.get(0);
				ZjQuestionOption option2 = (ZjQuestionOption) optionList.get(1);
				js.append("setOption(" +"\""+ option1.getId()+"\"" + ","+"\""+option1.getOptioncontext()+"\""+","+"\""+question.getAnswers()+"\""+");\n");
				js.append("setOption(" +"\""+ option2.getId()+"\"" + ","+"\""+option2.getOptioncontext()+"\""+","+"\""+question.getAnswers()+"\""+");\n");
				for(int i = 2;i<optionList.size();i++) {
					ZjQuestionOption option = (ZjQuestionOption) optionList.get(i);			
					js.append("setOption(" +"\""+ option.getId()+"\"" + ","+"\""+option.getOptioncontext()+"\""+","+"\""+question.getAnswers()+"\""+");\n");
						
					
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
		var optionContent = QuestionForm.optionContent;
		var answer = QuestionForm.answer;
		var t = false;
		var rtflag = true;
		
		if(content== ""||content==null){
			alert("请输入题目内容!");
			rtflag=false;
			return false;
		}	
			
		if(optionContent!=null){
			for(var i = 0; i < optionContent.length; i ++){
				if(optionContent[i].value == "" || optionContent[i].value == null){
					alert("请填写完整选项信息!");
					optionContent[i].focus();
					rtflag=false;
					return false;
				}
				if(answer[i].checked == true){
					t = true;
				}
			}
		}
		
		if(!t){
			alert("请至少选择一个答案!");
			rtflag=false;
			return false;
		}
		
	    return rtflag;
	}
	
	
	function init(){
		if(document.getElementById("options_a").innerHTML == ""){
			document.getElementById("options_a").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"A\"/>&nbsp;A.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}
		if(document.getElementById("options_b").innerHTML == ""){
			document.getElementById("options_b").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"B\"/>&nbsp;B.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}
		if(document.getElementById("options_c").innerHTML == ""){
			document.getElementById("options_c").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"C\"/>&nbsp;C.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}
		if(document.getElementById("options_d").innerHTML == ""){
			document.getElementById("options_d").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"D\"/>&nbsp;D.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}
		
	}
	
	function setOption(optionid,optioncontext,answer){
		if(document.getElementById("options_a").innerHTML == ""){
			document.getElementById("options_a").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"A\"/>&nbsp;A.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;	
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}		
		}else if(document.getElementById("options_b").innerHTML == ""){
			document.getElementById("options_b").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"B\"/>&nbsp;B.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}else if(document.getElementById("options_c").innerHTML == ""){
			document.getElementById("options_c").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"C\"/>&nbsp;C.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}else if(document.getElementById("options_d").innerHTML == ""){
			document.getElementById("options_d").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"D\"/>&nbsp;D.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}else if(document.getElementById("options_e").innerHTML == ""){
			document.getElementById("options_e").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"E\"/>&nbsp;E.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}else if(document.getElementById("options_f").innerHTML == ""){
			document.getElementById("options_f").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"F\"/>&nbsp;F.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}else if(document.getElementById("options_g").innerHTML == ""){
			document.getElementById("options_g").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"G\"/>&nbsp;G.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}else if(document.getElementById("options_h").innerHTML == ""){
			document.getElementById("options_h").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"H\"/>&nbsp;H.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}else if(document.getElementById("options_i").innerHTML == ""){
			document.getElementById("options_i").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"I\"/>&nbsp;I.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}else if(document.getElementById("options_j").innerHTML == ""){
			document.getElementById("options_j").innerHTML = "<input type=\"hidden\" name=\"optionid\"/><input type=\"radio\" name=\"answer\" value=\"J\"/>&nbsp;J.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
			var tmp=document.getElementsByName('optionContent');
			tmp[tmp.length-1].value=optioncontext;
			
			var tmp1=document.getElementsByName('optionid');
			tmp1[tmp1.length-1].value=optionid;
			
			var tmp2=document.getElementsByName('answer');
			if(answer.indexOf(tmp2[tmp2.length-1].value)==-1){
			}
			else{
				tmp2[tmp2.length-1].checked=true;
			}	
		}
		else{}
	}
	function addOption(){
		if(document.getElementById("options_e").innerHTML == ""){
			document.getElementById("options_e").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"E\"/>&nbsp;E.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}else if(document.getElementById("options_f").innerHTML == ""){
			document.getElementById("options_f").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"F\"/>&nbsp;F.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}else if(document.getElementById("options_g").innerHTML == ""){
			document.getElementById("options_g").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"G\"/>&nbsp;G.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}else if(document.getElementById("options_h").innerHTML == ""){
			document.getElementById("options_h").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"H\"/>&nbsp;H.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}else if(document.getElementById("options_i").innerHTML == ""){
			document.getElementById("options_i").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"I\"/>&nbsp;I.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}else if(document.getElementById("options_j").innerHTML == ""){
			document.getElementById("options_j").innerHTML = "<input type=\"radio\" name=\"answer\" value=\"J\"/>&nbsp;J.&nbsp;<input type=\"text\" name=\"optionContent\" size=\"100\"  />&nbsp;<br />";
		}
		else{
			alert("最多只能有10个选项!");
			return;
		}
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
    		<input type="button" class="xbutton" name="addsingle" onClick="addOption();" value="添加选项">
    		<input type="button" class="xbutton" name="getsingle" onClick="removeOption();" value="删除选项">
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
                  <input name="updatasubmit1" type="button" class="xbutton" onClick="javascript:history.go(-1)" value="返回">                                 
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