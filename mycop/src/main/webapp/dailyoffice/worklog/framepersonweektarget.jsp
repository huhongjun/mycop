<%@ page contentType="text/html; charset=GB2312"%>
<%
String context = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ޱ����ĵ�</title>
</head>


  <frameset cols="180,*" id="main" >
  <frame name="left" src="<%=context%>/dailyoffice/worklog/treepersonweektarget.do?action=toTree"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="left"> 
  <frame src="<%=context%>/dailyoffice/worklog/listpersonweektarget.do" name="middle"   id="middle">
  </frameset>
  <noframes>
  <body>
  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>
  </body>
  </noframes>
</html>
