<%@ page contentType="text/html; charset=GB2312" %>
<%@ page language="java" %>
<html>
<head>
<%	
 String context =request.getContextPath();
    //response.setHeader("pragma", "no-cache");response.setHeader("Cache-Control", "no-cache");
    //response.setDateHeader("Expires", 0L);
	session.setAttribute("screenXY", request.getParameter("screenXY"));
%>


<title>天行协同办公平台</title>
</head>
<frameset id="main" cols="25,*" framespacing="0" frameborder="no" border="0">
  <frame src="<%=context%>/jsp/homepage/left.jsp" name="left" scrolling="no" id="left">
  <frame src="<%=context%>/homepage/maintop.do?action=homepage" name="right" frameborder="no" scrolling="no" noresize id="right">
  <frame name="hiddendata" id="hiddendata" src="<%=context%>/template/hiddendata.jsp">
</frameset>
<noframes></noframes>
<body >
</body>
</html>
