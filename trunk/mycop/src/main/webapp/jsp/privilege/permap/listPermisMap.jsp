<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page language="java" %>
<html>
<head>
<%	
 String context =request.getContextPath();
    //response.setHeader("pragma", "no-cache");response.setHeader("Cache-Control", "no-cache");
    //response.setDateHeader("Expires", 0L);
	session.setAttribute("screenXY", request.getParameter("screenXY"));
%>
<title>天行开发平台</title>
</head>
<frameset rows="*,25%" cols="*" frameborder="NO" border="0" framespacing="0">
  <frameset cols="27%,*,27%" frameborder="yes" border="1" framespacing="0">
    <frame src="<gdp:context/>/getConfigAction.do?action=showRoot" name="leftFrame" noresize>
	<frame src="<gdp:context/>/privilege/permissionMapAction.do?action=toCreate" name="centerFrame" scrolling="NO" noresize>
	<frame src="<gdp:context/>/privilege/getResOpTreeAction.do?action=getTreeRoot" name="rightFrame" noresize>
  </frameset>
  <frame src="<gdp:context/>/privilege/permissionMapAction.do?action=toMethodList" name="bottomFrame" noresize>
</frameset>
<noframes><body>
</body></noframes> 
</html>