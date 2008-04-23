<%@ page language="java" %>
<%@ page contentType="text/html; charset=GB2312"%>
<%
	String context =request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<%
	String middleSrc = ""+context+"/dailyoffice/mailmgr/maildirectorylist.do";
        if(request.getParameter("openReceiveFolder") != null) {
            middleSrc = ""+context+"/dailyoffice/mailmgr/maillist.do?mailDirId=0";
        }
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>邮件服务</title>
</head>
<frameset cols="150,*" id="main" framespacing="0" frameborder="yes" border="1" bordercolor="#006600">
  <frame name="left" src="<%=context%>/dailyoffice/mailmgr/mailleft.jsp"    target="rtop" id="left" scrolling="no" noresize >
  <frame src="<%=middleSrc%>" name="middle"   id="middle" frameborder="no" noresize id="middle" >
  <noframes>
  <body>

  <p>此网页使用了框架，但您的浏览器不支持框架。</p>

  </body>
  </noframes>


</html>
