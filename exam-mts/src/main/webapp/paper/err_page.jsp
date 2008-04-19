<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String info = (String)request.getAttribute("info");
	if(info != null && "pubError".equals(info)){
		String courseId = (String)request.getAttribute("courseId");
%>
<script type="text/javascript">
<!--
	if(confirm("发布试卷失败，可能是因为试卷下试题分值不符合，点击确定进行该试卷试题维护页面，点击取消返回试卷列表页面")){
		window.location.href = "<%=request.getContextPath()%>/Paper.do?method=listPaper&courseId=<%=courseId%>";
	}else{
		window.location.href = "<%=request.getContextPath()%>/Paper.do?method=listPaper&courseId=<%=courseId%>";
	}
//-->
</script>
<%
	}
	
%>