<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"
	language="java"%>
<%
   String userid = request.getParameter("userid");
   if(userid == null){
       userid = "333333333333333333333333333333";
    //String userid = "555555555555555555555555555555";
    //String userid = "444444444444444444444444444444";
	//String userid = "666666666666666666666666666666";
   }
	

	
	if (userid != null) {
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	
	<body>
		<form name="assignmentForm" action="<%=request.getContextPath()%>/assignment.do?method=queryList"	method="post">
			<input type="hidden" name="userid" value="<%=userid%>" />
		</form>
	</body>
	
</html:html>
<script>

	 document.assignmentForm.submit();

</script>
	<%
			} else {
			out.println("对不起，请求不成功！");
		}
	%>
