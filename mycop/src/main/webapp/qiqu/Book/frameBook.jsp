<%@ page contentType="text/html; charset=GB2312"%>
<% String context = request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>ģ��[??]���ҳ��</title>
</head>
  <frameset cols="180,*" id="main" >
  
  <frame name="left" 
  	src="<%=context%>/qiqu/Book/treeBook.do?action=toTree"  
  	onclick="javascript:alert()" 	scrolling="auto" 	target="rtop" 	id="left"
  	>
 
  <frame name="middle" id="middle" 
  src="<%=context%>/qiqu/Book/listBook.do"
  >

  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</html>
