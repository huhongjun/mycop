<%@ page language="java" %>
<%@ page contentType="text/html; charset=GB2312"%>
<%
String context =request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>���ŷ���</title>
</head>
<frameset cols="150,*" id="main" framespacing="0" frameborder="yes" border="1" bordercolor="#006600">
  <frame name="left" src="<%=context%>/dailyoffice/smsmgr/leftsms.jsp"   scrolling="no" noresize target="rtop">

    <frame src="<%=context%>/dailyoffice/smsmgr/writesms.do" frameborder="no" noresize id="middle" name="middle"  >


  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>


</html>
