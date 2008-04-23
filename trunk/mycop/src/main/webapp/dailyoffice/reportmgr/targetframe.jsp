<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java"%>
<%@ page import="com.gever.goa.dailyoffice.targetmng.util.TargetConstant"%>
<%
	//取到目标类型
	String targetType=request.getParameter("targetType");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>报告管理</title>
</head>
  <frameset cols="180,*" id="main" >
  <frame name="left" src="/goa/dailyoffice/reportmgr/treetarget.do?action=toTree"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="left"> 
  <frame src="/goa/dailyoffice/reportmgr/listtarget.do?targetType=<%=targetType%>" name="middle"   id="middle">
  </frameset>
  <noframes>
  <body>
  <p>此网页使用了框架，但您的浏览器不支持框架。</p>
  </body>
  </noframes>
</html>
