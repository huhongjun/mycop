<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.gever.util.StringUtils"%>
<%
String context = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ޱ����ĵ�</title>
</head>


  <frameset cols="180,*" id="main" >
  <frame name="left" src="<%=context%>/infoservice/tradenewstree.do?action=toTree" scrolling="auto"  target="rtop" id="left">
 
    <frame src="<%=context%>/infoservice/tradenewslist.do?paraFlag=5" name="middle"   id="middle">

  
  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</html>
