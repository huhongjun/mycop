<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>参加考试</title>
</head>
<%
	String quizId = request.getParameter("quizId");
%>
<frameset rows="60,*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=request.getContextPath()%>/joinExam.do?method=quizInfo&quizId=<%=quizId %>" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" style='BORDER-BOTTOM:#000000 1px solid;'/>
  <frame src="<%=request.getContextPath()%>/joinExam.do?method=quizQuestionInfo&quizId=<%=quizId %>" name="mainFrame" id="mainFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>