<%@ page contentType="text/html; charset=UTF-8" %>

<%
	if(request.getAttribute("info") == null){
%>
<script>
	alert("保存成功！");
</script>

<%
	}else if("error".equals((String)request.getAttribute("info"))){
%>
<script>
	alert("验证密码输入有误，请重新输入！");
	history.go(-1);
	//parent.window.close();
</script>

<%
	}else{
%>
<script>
	alert("试卷批改完成，页面将关闭！");
	parent.window.close();
</script>

<%
	}
%>