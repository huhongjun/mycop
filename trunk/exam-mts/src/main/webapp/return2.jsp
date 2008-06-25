<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String info = (String)request.getAttribute("info");
%>
<script language="javascript">
<%
	if(info != null && "student".equals(info)){
%>
top.location.href="<%=request.getContextPath()%>/login.jsp";
<%	
	}else if(info != null && "teacher".equals(info)){
%>
top.location.href="<%=request.getContextPath()%>/index.jsp";
<%
	}
%>
	
</script>