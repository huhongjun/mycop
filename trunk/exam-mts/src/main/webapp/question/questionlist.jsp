<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<%@ page import="com.zhjedu.util.Constants" %>
<%@ page import="com.zhjedu.util.DateTimeUtil" %>
<script src="../js/edit.js" type="text/javascript"></script>
<script language=javascript src="../js/question.js"></script>
<link href="<%=request.getContextPath() %>/css/wxj.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/ksmain.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=request.getContextPath()%>/js/meizzDate.js"></script>
<style type="text/css">
<!--
.sec1 {cursor: hand;
font-size:12px;
color: #000000;
width:107;
height:25;
background-image=url(image/x-00100.gif);
}
.style1 {font-size: 12px}
.style11 {color: #164f8e;
	font-weight: bold;
}
.style11 {color: #164f8e; font-weight: bold; font-size: 14px; }
.style12 {color: #0000FF}
.style13 {color: #3b6ba0}
.style14 {	font-size: 36px;
	color: #3B6BA0;
}
.style8 {color: #0000FF; font-size: 12px; }
.style9 {font-size: 14px}
-->
</style>
<script language=javascript>
function check(){
	flag=confirm("你确定要删除此项吗？");
	return flag;
}
function addquestion(catName,isleaf){
	if(isleaf=='0'){
		alert(catName+" 不是叶节点,不能在此目录下添加试题!请选择合适的目录!");
	}
	else{
		document.QuestionForm.action="question/addquestion.jsp";
		document.QuestionForm.submit();
	}
}
function search(){
	QuestionForm.target = "_self";
	QuestionForm.method.value = "search";
	QuestionForm.submit();
	return true;
}
function editquestion(){
	var j = 0;
	for(var i=0;i<QuestionForm.questionid.length;i++){
		if(QuestionForm.questionid[i].checked==true){
			j=j+1;
		}
	}
	
	if(j==0){
		alert("请选择要编辑的题目！");
		return;
	}
	else if(j>1){
		alert("一次只允许编辑一个题目！");
		return;
	}
	else{
		QuestionForm.target = "_self";
		QuestionForm.method.value = "edit";
		QuestionForm.submit();
		return true;
	}
	
}
function removequestion(){
if(check()){
	var j = 0;
	for(var i=0;i<QuestionForm.questionid.length;i++){
		if(QuestionForm.questionid[i].checked==true){
			j=j+1;
		}
	}
	
	if(j==0){
		confirm("请选择要删除的题目！");
		return;
	}
	
	else{
		
		QuestionForm.target = "_self";
		QuestionForm.method.value = "remove";
		QuestionForm.submit();
		return true;
	}
	}
	
}
function cat_question(){
	document.QuestionForm.method.value="main_category";
	document.QuestionForm.target="_parent";
	document.QuestionForm.submit();
}
function listcategory(){  
	document.QuestionForm.method.value="mainCategory";
	document.QuestionForm.target="_parent";
	document.QuestionForm.submit();
}
</script>

<html:form action="Question.do">
<html:hidden property="method" value="search"/>
<%
	String category="";
	String isleaf="0";
	if(request.getParameter("category")!=null&&!request.getParameter("category").equals("")){
		category = request.getParameter("category");
	}
	if(request.getParameter("isleaf")!=null&&!request.getParameter("isleaf").equals("")){
		isleaf = request.getParameter("isleaf");
	}
	if(request.getAttribute("category")!=null&&!((String)request.getAttribute("category")).equals("")){
		category = (String)request.getAttribute("category");
	}
	if(request.getAttribute("isleaf")!=null&&!((String)request.getAttribute("isleaf")).equals("")){
		isleaf = (String)request.getAttribute("isleaf");
	}
	%>
	
	<input type=hidden name="category" value="<%=category%>"/>
	<input type=hidden name="isleaf" value="<%=isleaf%>"/>
<table border="0" width="100%" id="table4">
  <tr>
    <td height="36" colspan="9"><img border="0" src="<%=request.getContextPath() %>/image/tb16_dept.gif" width="16" height="16" /><b><span class="style3"><span class="style4"> 试题列表(所属目录：</span><font color="#FFFFFF"><font color="#ff0000"><%=request.getAttribute("catName") %> </font> </font><span class="style4">) </span></span></b></td>
  </tr>
  <tr>
    <td colspan="10" height="25">
    <!-- 
    <a href="#" onClick="addquestion('<%=request.getAttribute("catName") %>','<%=isleaf %>');">
    <img src="<%=request.getContextPath() %>/image/x-001.gif" width="109" height="21" border="0" /></a> 
    <a href="javascript:editquestion()"><img src="<%=request.getContextPath() %>/image/x-002.gif" width="109" height="21" border="0" /></a>
    <a onClick="return check();" href="javascript:removequestion()"><img src="<%=request.getContextPath() %>/image/x-003.gif" width="109" height="21" border="0" /></a> 
     -->
    <input type="button" onClick="addquestion('<%=request.getAttribute("catName") %>','<%=isleaf %>');"  class="btn_cm" value="增加试题" name="B43">
    <input type="button" onClick="editquestion()"  class="btn_cm" value="编辑试题" name="B43">
    <input type="button" onClick="removequestion()"  class="btn_cm" value="删除试题" name="B43">
    <input type="button" onClick="listcategory();"  class="btn_cm" value="目录维护" name="B43">
    <input type="button" onClick="cat_question();"  class="btn_cm" value="题目维护" name="B43">
    
  </tr>
  <tr>
    <td colspan="10" height="25">
    
    <span class="style1">试题类型</span>
        <html:select size="1" property="qtype">
          <html:option value="">所有题型</html:option>
          <html:option value="<%=Constants.QUESTION_SINGLECHOICE %>">单选题</html:option>
          <html:option value="<%=Constants.QUESTION_MULTICHOICE %>">多选题</html:option>
          <html:option value="<%=Constants.QUESTION_JUDGE %>">判断题</html:option>
          <html:option value="<%=Constants.QUESTION_MATCHING %>">匹配题</html:option>
          <html:option value="<%=Constants.QUESTION_INTEGRATE %>">综合题</html:option>
        </html:select>
        <font size="2" class="style1">关键字</font>
        <html:text property="key" size="14" value="" />
        <input type="button" onclick="search();" value="查询"  class="btn_cm_small" name="B6" /></td>
  </tr>
  <tr>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center">
      <input type="checkbox" name="questionid" disabled />
    </p></td>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center" class="style1"><b>序号</b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="35%"><p align="center"><b><font class="style1">&nbsp;题目描述</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center" class="style1"><b><font class="style1">出题次数</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="15%"><p align="center" class="style1"><b><font class="style1">关键字</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center" class="style1"><b><font class="style1">分值</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="6%"><p align="center" class="style1"><b><font class="style1">题型</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center" class="style1"><b><font class="style1">难度系数</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="10%"><p align="center" class="style1"><b><font class="style1">修改日期</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="14%"><p align="center" class="style1"><b><font class="style1">操作</font></b></p></td>
  </tr>
  <%com.zhjedu.util.PageObject po=(com.zhjedu.util.PageObject)request.getAttribute("po");%>   
  <bean:define id="list" name="po" property="datas"/>
  <logic:iterate indexId="ind" id="element" name="list">
  <tr bgcolor="#EEF7FF"  class="style1">
    <td><p align="center">
      <input type="checkbox" name="questionid" value="<bean:write name="element" property="id"/>" />
    </p></td>
    <td align="center" bgcolor="#EEF7FF"><font class="style1" color="#0000FF"><%=ind+1 %></font></td>
    <td><font size="2" class="wenzi">
    <span style="width:220px;height:15px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap"> 
		<bean:write name="element" property="questioncontext" filter="false"/> 
	</span> </font>
	</td>
    <td align="center"><font class="style1"  color="#0000FF"><bean:write name="element" property="usetime"/></font></td>
    <td><font size="2" class="wenzi"><bean:write name="element" property="questionKey"/></font></td>
    <td align="center"><font size="2" class="style1" color="#0000FF"><bean:write name="element" property="defaultgrade"/></font></td>
    <td align="center" bgcolor="#EEF7FF"><div align="center"><font class="style1" ><font size="2" class="wenzi">
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_SINGLECHOICE %>">单选题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_MULTICHOICE %>">多选题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_JUDGE %>">判断题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_MATCHING %>">匹配题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_INPUTFILL %>">填空题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_ANSWER %>">问答题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_INTEGRATE %>">综合题</logic:equal>
    </font></font></div></td>
    
    <td align="center"><font size="2" class="style1" color="#0000FF"><bean:write name="element" property="difficulty"/></font></td>
    <bean:define id="tmp" name="element" property="lasteditdate"/>
    <td align="center"><font size="2" class="style1"> <%=DateTimeUtil.getTime((Long)tmp,2) %></font></td>
    <td align="center"><font size="2" class="style1"> <a href="<%=request.getContextPath() %>/Question.do?method=edit&questionid=<bean:write name="element" property="id"/>&category=<%=category %>">编辑</a> <a href=javascript:if(confirm("确定删除该题目吗？")){window.location="<%=request.getContextPath() %>/Question.do?method=remove&questionid=<bean:write name="element" property="id"/>&category=<%=category %>";}>删除</a></font></td>
  </tr>
  </logic:iterate>
  </table>
  <table border="0" width="100%" id="table4">
  <tr>
    <td align="center" colspan="9"> 　</td>
  </tr>
  <tr>
		<td align="center" colspan="9"><span class="style1">
		<page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="QuestionForm" pageVar="page" jsName="changePage" url="Question.do?method=list"/>
		</span></td>
  </tr>
</table>
</html:form>