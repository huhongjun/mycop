<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.gever.sysman.privilege.webapp.action.LoginAction"%>
<%
	String forward = (String) request.getAttribute(LoginAction.FORWARD_URL);
%>

<script>
//	alert("��½�ɹ�!");
	document.location = "<%=forward%>";
</script>