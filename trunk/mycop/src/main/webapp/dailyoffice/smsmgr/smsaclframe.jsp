<%@ page language="java" %>
<%@ page contentType="text/html; charset=GB2312"%>
<%
String context =request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>���ŷ���</title>
</head>
<frameset cols="180,*" id="main" >
  <frame name="left" src="<%=context%>/dailyoffice/smsmgr/treeleftacl.do?action=toTree"  onclick="javascript:alert()" scrolling="auto"  target="rtop" id="left">
 
    <frame src="<%=context%>/dailyoffice/smsmgr/listsmsacl.do" name="middle"   id="middle">

  
  <noframes>
  <body>

  <p>����ҳʹ���˿�ܣ��������������֧�ֿ�ܡ�</p>

  </body>
  </noframes>


</html>
