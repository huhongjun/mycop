<%@ page contentType="text/html; charset=GB2312"%>
<%
String context = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��Դ����</title>
</head>


  <frameset cols="180,*" id="main" >
  <frame name="left" src="<%=context%>/infoservice/publicinfotree.do?action=toTree"  scrolling="auto"  target="rtop" id="left">
 
    <frame src="<%=context%>/infoservice/publicinfolist.do?paraFlag=0" name="middle"   id="middle">

  
  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</html>
