<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>知金教育－－在线考试系统</title>
</head>

<frameset rows="85,*,70" frameborder="no" border="0" framespacing="0">
  <frame src="top.html" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
  <frameset cols="180,*" frameborder="no" border="0" framespacing="0">
    <frame src="<%=request.getContextPath()%>/assignment_s/left.jsp?courseId=<%=request.getParameter("courseId") %>" name="leftFrame" scrolling="" noresize="noresize" id="leftFrame" />
    <frame src="<%=request.getContextPath()%>/assignment.do?method=queryList&courseId=<%=request.getParameter("courseId") %>" name="mainFrame" id="mainFrame" />
  </frameset>
  <frame src="bottom.html" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>
