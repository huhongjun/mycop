<%@ page contentType="text/html; charset=GB2312"%>
<%	String context =request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>名片夹</title>
</head>
  <frameset cols="180,*" id="main" >
  <frame name="lefttree" src="<%=context%>/dailyoffice/tools/treeCardType.do"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="lefttree">
     <frame src="<%=context%>/dailyoffice/tools/listCardcaseType.do" name="cardmiddle"   id="cardmiddle">
  <noframes>
  <body>
  <p>此网页使用了框架，但您的浏览器不支持框架。</p>
  </body>
  </noframes>
  </frameset>
</html>
