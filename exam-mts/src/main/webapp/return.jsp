<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String info = (String)request.getAttribute("info");
	if(info == null || "".equals(info)){
		info = "连接时间过长或登录已失效，请重新登录！";
	}
%>
<script language="javascript">
	alert("<%=info %>");
	top.location.href="<%=request.getContextPath()%>/index.jsp";
</script>