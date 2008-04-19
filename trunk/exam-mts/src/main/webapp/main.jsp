<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>知金教育－－在线考试系统</title>
</head>
<%
	String leftAction = (String)request.getAttribute("leftAction");
	String  rightAction = "/Question.do?method=list";
	if(request.getAttribute("rightAction")!=null){
		rightAction = (String)request.getAttribute("rightAction");
	}	 
%>
<frameset rows="85,*,70" frameborder="no" border="0" framespacing="0">
  <frame src="<%=request.getContextPath() %>/top.html" name="topFrame" scrolling="no" noresize id="topFrame" />
  <frameset cols="180,*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=leftAction %>" name="leftFrame" scrolling="yes" noresize="noresize" id="leftFrame" />
    <frame src="<%=rightAction %>" name="mainFrame" id="mainFrame" />
  </frameset>
  <frame src="<%=request.getContextPath() %>/bottom.html" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>
