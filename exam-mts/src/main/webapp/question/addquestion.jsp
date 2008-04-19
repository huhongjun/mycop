<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<style type="text/css">
<!--
.style1 {font-size: 12px}
-->
</style>
<script language=javascript>
function addquestion(qtype){
	QuestionForm.target = "_self";
	QuestionForm.qtype.value = qtype;
	QuestionForm.submit();
}
</script>
<html:form action="Question.do" method="post">
<html:hidden property="method" value="add"/>
	<%
	String course="";
	String chapter="";
	String section="";
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
	if(request.getParameter("category")!=null&&!request.getParameter("category").equals("")){
		category = request.getParameter("category");
	}
	%>
	<input type=hidden name="course" value="<%=course %>"/>
	<input type=hidden name="chapter" value="<%=chapter %>"/>
	<input type=hidden name="section" value="<%=section %>"/>
	<input type=hidden name="qtype" value=""/>
	<input type=hidden name="category" value="<%=category%>"/>
</html:form>
<table border="0" width="100%" id="table4" height="286">
  <tr>
    <td height="30" class="style1"><img src="<%=request.getContextPath() %>/image/zhengjiashitileixing.gif" width="300" height="70" /></td>
  </tr>
  <tr>
    <td height="28" bgcolor="#7FBEE6"><p align="center" class="wenzi"><strong>试题类型</strong></p></td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF"> 　</td>
  </tr>
  <tr>
    <td bgcolor="#EEF7FF"><p align="center"> 
    <a href="#" onClick="addquestion(1)"><img src="<%=request.getContextPath() %>/image/j-001.gif" width="113" height="51" border="0" /></a>　
    <a href="#" onClick="addquestion(2)"><img src="<%=request.getContextPath() %>/image/j-002.gif" width="113" height="51" border="0" /></a>　　</p></td>
  </tr> 
  <tr> 
    <td align="center" bgcolor="#F7FBFF">&nbsp;</td>
  </tr>
  <tr>
    <td align="center" bgcolor="#EEF7FF">
	<a href="#" onClick="addquestion(3)"><img src="<%=request.getContextPath() %>/image/j-003.gif" width="113" height="51" border="0" /></a>　
    <a href="#" onClick="addquestion(4)"><img src="<%=request.getContextPath() %>/image/j-006.gif" width="113" height="51" border="0" /></a>　
    <a href="#" onClick="addquestion(9)"><img src="<%=request.getContextPath() %>/image/j-007.gif" width="113" height="51" border="0" /></a>
    </td>
  </tr>
  <tr>
    <td align="center" bgcolor="#EEF7FF">&nbsp;</td>
  </tr>
</table>
                <br>
                <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="24"><img src="<%=request.getContextPath() %>/image/left.gif" width="24" height="20" ></td>
                    <td class="newwindow-tit" bgcolor="#CAE4FF"> <font color="#FF0000" class="style1">　*请选择所要添加的相应试题类型</font></td>
                    <td width="24"><img src="<%=request.getContextPath() %>/image/right.gif" width="24" height="20" ></td>
                  </tr>
</table>
