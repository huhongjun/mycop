<%@ page contentType="text/html; charset=GB2312"%>
<%String context = request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>短消息服务</title>
</head>


  <frameset cols="130,*" id="main" >
  <frame name="left" src="<%=context%>/dailyoffice/message/treemessage.do?action=toTree"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="middle"> 
  <frame src="<%=context%>/dailyoffice/message/listmessage.do" name="middle"   id="middle">
  </frameset>
  <noframes>
  <body>
  <p>此网页使用了框架，但您的浏览器不支持框架。</p>
  </body>
  </noframes>
</html>
