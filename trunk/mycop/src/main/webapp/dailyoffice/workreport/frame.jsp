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
<title>�ޱ����ĵ�</title>
</head>


  <frameset cols="150,*" id="main" >
  <frame name="left" src="<%=context%>/dailyoffice/workreport/treeworkreport.do?action=toTree"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="left">
 
    <frame src="<%=rightUrl%>" name="middle"   id="middle">

  
  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</html>
