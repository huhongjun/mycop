<%@ page contentType="text/html; charset=GB2312"%>
<%String context=request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>备忘录</title>
</head>
  <frameset rows="*" cols="245,*" id="main" >
  <br>
<br>
<br>
<br>
<br>
  <frame name="left" src="<%=context%>/dailyoffice/tools/ticklerdate.jsp"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="left">
     <frame src="<%=context%>/dailyoffice/tools/listTickler.do" name="middle"   id="middle">
  <noframes>
  <body>
  <p>此网页使用了框架，但您的浏览器不支持框架。</p>
  </body>
  </noframes>
  </frameset>
</html>
