<%@ page contentType="text/html; charset=GB2312"%>
<%	String context =request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��Ƭ��</title>
</head>
  <frameset cols="180,*" id="main" >
  <frame name="lefttree" src="<%=context%>/dailyoffice/tools/treeCardType.do"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="lefttree">
     <frame src="<%=context%>/dailyoffice/tools/listCardcaseType.do" name="cardmiddle"   id="cardmiddle">
  <noframes>
  <body>
  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>
  </body>
  </noframes>
  </frameset>
</html>
