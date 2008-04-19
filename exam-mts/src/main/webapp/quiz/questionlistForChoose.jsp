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
<script language=javascript>
function addquestion(){
	document.QuestionForm.action="question/addquestion.jsp";
	document.QuestionForm.submit();
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
	var j = 0;
	for(var i=0;i<QuestionForm.questionid.length;i++){
		if(QuestionForm.questionid[i].checked==true){
			j=j+1;
		}
	}
	
	if(j==0){
		alert("请选择要删除的题目！");
		return;
	}
	
	else{
		QuestionForm.target = "_self";
		QuestionForm.method.value = "remove";
		QuestionForm.submit();
		return true;
	}
	
}
</script>

<html:form action="Question.do">
<html:hidden property="method" value="search"/>
<%
	String course="";
	String chapter="";
	String section="";
	String nodeid="";
	String category="";
	if(request.getParameter("course")!=null&&!request.getParameter("course").equals("")){
		course = request.getParameter("course");
	}
	if(request.getParameter("chapter")!=null&&!request.getParameter("chapter").equals("")){
		chapter = request.getParameter("chapter");
	}
	if(request.getParameter("section")!=null&&!request.getParameter("section").equals("")){
		section = request.getParameter("section");
	}
	if(request.getParameter("nodeid")!=null&&!request.getParameter("nodeid").equals("")){
		nodeid = request.getParameter("nodeid");
	}
	if(request.getParameter("category")!=null&&!request.getParameter("category").equals("")){
		category = request.getParameter("category");
	}
	%>
	<input type=hidden name="course" value="<%=course %>"/> 
	<input type=hidden name="chapter" value="<%=chapter %>"/>
	<input type=hidden name="section" value="<%=section %>"/>
	<input type=hidden name="nodeid" value="<%=nodeid%>"/>
	<input type=hidden name="category" value="<%=category%>"/>
<table border="0" width="100%" id="table4">
  <tr>
    <td height="36" colspan="9"><img border="0" src="<%=request.getContextPath() %>/image/tb16_dept.gif" width="16" height="16" /><b><span class="style3"><span class="style4"> 试题列表(所属目录：</span><font color="#FFFFFF"><font color="#ff0000"><%=request.getAttribute("catName") %> </font> </font><span class="style4">) </span></span></b></td>
  </tr>
  <tr>
    <td colspan="9" height="25"><a href="#" onClick="addquestion();">
    <img src="<%=request.getContextPath() %>/image/x-001.gif" width="109" height="21" border="0" /></a> 
    <a href="javascript:editquestion()"><img src="<%=request.getContextPath() %>/image/x-002.gif" width="109" height="21" border="0" /></a>
    <a href="javascript:removequestion()"><img src="<%=request.getContextPath() %>/image/x-003.gif" width="109" height="21" border="0" /></a> &nbsp;&nbsp; <span class="style1">试题类型</span>
        <html:select size="1" property="qtype">
          <html:option value="">请选择题型</html:option>
          <html:option value="<%=Constants.QUESTION_JUDGE %>">判断题</html:option>
          <html:option value="<%=Constants.QUESTION_SINGLECHOICE %>">单选题</html:option>
          <html:option value="<%=Constants.QUESTION_MULTICHOICE %>">多选题</html:option>
          <html:option value="<%=Constants.QUESTION_MATCHING %>">匹配题</html:option>
          <html:option value="<%=Constants.QUESTION_ANSWER %>">简答题</html:option>
          <html:option value="<%=Constants.QUESTION_INTEGRATE %>">综合题</html:option>
        </html:select>
        <font size="2" class="style1">关键字</font>
        <html:text property="key" size="14" value="" style="color: #C0C0C0" />
        <input type="button" onclick="search();" value="查询" name="B6" /></td>
  </tr>
  <tr>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center">
      
    </p></td>
    <td height="21" bgcolor="#7FBEE6" width="6%"><p align="center" class="style1"><b>序号</b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="39%"><p align="center"><b><font class="style1">&nbsp;题目描述</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="4%"><p align="center" class="style1"><b><font class="style1">出题次数</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="15%"><p align="center" class="style1"><b><font class="style1">关键字</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center" class="style1"><b><font class="style1">分值</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="10%"><p align="center" class="style1"><b><font class="style1">题型</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center" class="style1"><b><font class="style1">难度系数</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="11%"><p align="center" class="style1"><b><font class="style1">修改日期</font></b></p></td>
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
  </tr>
  </logic:iterate>
  </table>
  <table border="0" width="100%" id="table4">
  <tr>
    <td align="center" colspan="9"> 　</td>
  </tr>
  <tr>
		<td align="center" colspan="9">
		<page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="QuestionForm" pageVar="page" jsName="changePage" url="Question.do?method=list"/>
		</td>
  </tr>
</table>
</html:form>