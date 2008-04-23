<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.gever.sysman.privilege.webapp.action.LoginAction"%>
<%
	String forward = (String) request.getAttribute(LoginAction.FORWARD_URL);
%>

<script>
//	alert("µÇÂ½³É¹¦!");
	document.location = "<%=forward%>";
</script>