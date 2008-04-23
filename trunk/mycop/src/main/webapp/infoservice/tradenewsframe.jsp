<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.gever.util.StringUtils"%>
<%
String context = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
</head>


  <frameset cols="180,*" id="main" >
  <frame name="left" src="<%=context%>/infoservice/tradenewstree.do?action=toTree" scrolling="auto"  target="rtop" id="left">
 
    <frame src="<%=context%>/infoservice/tradenewslist.do?paraFlag=5" name="middle"   id="middle">

  
  <noframes>
  <body>

  <p>此网页使用了框架，但您的浏览器不支持框架。</p>

  </body>
  </noframes>
</html>
