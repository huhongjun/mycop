<%@ page contentType="text/html; charset=GB2312"%>
<%String context = request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>����Ϣ����</title>
</head>


  <frameset cols="130,*" id="main" >
  <frame name="left" src="<%=context%>/dailyoffice/message/treemessage.do?action=toTree"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="middle"> 
  <frame src="<%=context%>/dailyoffice/message/listmessage.do" name="middle"   id="middle">
  </frameset>
  <noframes>
  <body>
  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>
  </body>
  </noframes>
</html>
