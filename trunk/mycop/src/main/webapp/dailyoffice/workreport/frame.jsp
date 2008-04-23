<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.gever.util.StringUtils"%>
<%
String context = request.getContextPath();
%>
<%
	String rightUrl = ""+context+"/dailyoffice/workreport/listworkreport.do";
	String workReportId = request.getParameter("workReportId");
	String listType = request.getParameter("listType");
	if (!StringUtils.isNull(workReportId)){
		rightUrl = ""+context+"/dailyoffice/workreport/viewworkreport.do?listType="+listType+"&fid="+workReportId;
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
</head>


  <frameset cols="150,*" id="main" >
  <frame name="left" src="<%=context%>/dailyoffice/workreport/treeworkreport.do?action=toTree"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="left">
 
    <frame src="<%=rightUrl%>" name="middle"   id="middle">

  
  <noframes>
  <body>

  <p>此网页使用了框架，但您的浏览器不支持框架。</p>

  </body>
  </noframes>
</html>
