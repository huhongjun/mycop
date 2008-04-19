<%@ page contentType="text/html; charset=UTF-8"%>

<script language="javascript">
	alert("连接时间过长或登录已失效，请重新登录！");
	top.location.href="<%=request.getContextPath()%>/login.jsp";
</script>