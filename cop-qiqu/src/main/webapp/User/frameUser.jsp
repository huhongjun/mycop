<%@ page contentType="text/html; charset=GB2312"%>
<% String context = request.getContextPath(); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>ģ��[??]���ҳ��</title>
</head>
  <frameset cols="180,*" id="main" >
  <frame name="left" 
  	src="<%=context%>/User/treeUser.do?action=toTree"  
  	onclick="javascript:alert()" 
  	scrolling="auto"  
  	target="rtop" 
  	id="left">
 
  <frame src="<%=context%>/User/listUser.do" name="middle" id="middle">

  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>
</html>
