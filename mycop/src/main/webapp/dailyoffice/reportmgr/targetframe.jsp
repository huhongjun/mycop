<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java"%>
<%@ page import="com.gever.goa.dailyoffice.targetmng.util.TargetConstant"%>
<%
	//ȡ��Ŀ������
	String targetType=request.getParameter("targetType");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�������</title>
</head>
  <frameset cols="180,*" id="main" >
  <frame name="left" src="/goa/dailyoffice/reportmgr/treetarget.do?action=toTree"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="left"> 
  <frame src="/goa/dailyoffice/reportmgr/listtarget.do?targetType=<%=targetType%>" name="middle"   id="middle">
  </frameset>
  <noframes>
  <body>
  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>
  </body>
  </noframes>
</html>
