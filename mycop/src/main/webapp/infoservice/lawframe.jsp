<%@ page contentType="text/html; charset=GB2312"%>
<%String context = request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ޱ����ĵ�</title>
</head>


  <frameset cols="180,*" id="main" >
  <frame name="left" src="<%=context%>/infoservice/lawtree.do?action=toTree"  scrolling="auto"  target="rtop" id="left">
 
    <frame src="<%=context%>/infoservice/lawlist.do?paraFlag=1" name="middle"   id="middle">

  
  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</html>
