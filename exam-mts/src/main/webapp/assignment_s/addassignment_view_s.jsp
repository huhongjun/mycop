<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zhjedu.exam.domain.ZjQuizExam" %>
<%@ page import="com.zhjedu.exam.domain.ZjQuiz" %>
<%@ page import="com.zhjedu.exam.domain.ZjCourse" %>
<%@ page import="com.zhjedu.util.DateTimeUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object[] list = (Object[])request.getAttribute("qq");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <style type="text/css">
<!--
.sec1 {cursor: hand;
font-size:12px;
color: #000000;
width:107;
height:25;
background-image=url(image/x-00100.gif);
}
.style1 {font-size: 12px}
.style11 {color: #164f8e;
	font-weight: bold;
}
.style11 {color: #164f8e; font-weight: bold; font-size: 14px; }
.style12 {color: #0000FF}
.style13 {color: #3b6ba0}
.style14 {	font-size: 36px;
	color: #3B6BA0;
}
.style8 {color: #0000FF; font-size: 12px; }
.style9 {font-size: 14px}
-->
</style>
    <base href="<%=basePath%>">
    <title>AssignmentView</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%            
			ZjQuizExam ze = (ZjQuizExam)list[0];
			ZjQuiz zq = (ZjQuiz)list[1];
   %>
  <body>
    <table border="0" width="100%" id="table4">
  <tr bgcolor="#FFFFFF">
    <td height="30" colspan="4"><span class="style16"><img src="assignment_s/image/x-006.gif" width="300" height="70" /></span></td>
  </tr>
  <tr bgcolor="#7FBEE6">
    <td width="96%" height="30" colspan="4"><p align="center" class="style1"><strong> 查看考试</strong></p></td>
  </tr>
  <tr bgcolor="#F7FBFF">
    
    <td align="center" bgcolor="#EEF7FF"><p align="left" class="style1">考试名称:</p></td>
    <td align="center"><p align="left" class="style1">
      <%=zq.getName() %>
    </p></td>
    <td width="25%" align="left" bgcolor="#F7FBFF" class="style1"> 考试方式:</td>
    <%			String style = zq.getQuizStyle();
				if("0".equals(style))
			    {
			      style = "在线";
			    }if("1".equals(style))
			    {
			      style = "离线";
			     } %>
    <td align="left"><p align="left" class="style1">
		<%=style %>
	</p></td>
  </tr>
  <tr bgcolor="#EEF7FF">
    <td width="15%" align="left" bgcolor="#EEF7FF" class="style1"> 考试状态:</td>
    <td width="24%" align="left"><p align="left" class="style1">
    <%			String status = ze.getStatus();
				if("0".equals(status))
			    {
			      status = "未开始";
			    }
			    if("1".equals(status))
			    {
			      status = "未完成";
			     }
			     if("2".equals(status))
			    {
			      status = "已完成";
			     }
			     if("3".equals(status))
			    {
			      status = "已批阅";
			     }
			      %>
		<%=status %></p>
	</td>
    <td width="25%" align="left" bgcolor="#EEF7FF" class="style1"> 成&nbsp;&nbsp;&nbsp; 绩</td>
    <td align="left"><p align="left" class="style1"><%=ze.getGrade().toString() %></p></td>
  </tr>
  <tr bgcolor="#F7FBFF">
    <td width="15%" align="center" bgcolor="#F7FBFF"><p align="left" class="style1">创 建 人</p></td>
    <td width="24%" align="center"><p align="left" class="style1">
     <%=zq.getCreator() %>
    </p></td>
    <td width="25%" align="center" bgcolor="#F7FBFF"><p align="left" class="style1">创建时间</p></td>
   
    <td align="center"><p align="left" class="style1">
      <%=DateTimeUtil.getTime((Long)zq.getCreatedate(),2) %>
    </p></td>
  </tr>
  <tr bgcolor="#EEF7FF">
    <td align="center"><p align="left" class="style1">允许参加次数</p></td>
    <td><p align="left" class="style1">1</p><%//=ze.getSequence().toString() %></td>
    <td align="center"><p align="left" class="style1">计分方式</p></td>
    <%
	String grademethod = zq.getGrademethod();
	if("1".equals(grademethod)) {
		grademethod ="最高成绩";
	}if("3".equals(grademethod)){
		grademethod ="最后成绩";
	}if("2".equals(grademethod)){
		grademethod ="平均成绩";
	}
		%>
    <td><p align="left" class="style1"><%=grademethod%></p></td>
  </tr>
  <tr bgcolor="#F7FBFF">
    <td width="15%" align="center"><p align="left" class="style1">有 效 期</p></td>
    <td colspan="3" align="center"><p align="left" class="style1">
          <%=DateTimeUtil.getTime((Long)zq.getTimeopen(),2) %>
          &nbsp; <span class="style1">至</span>
         <%=DateTimeUtil.getTime((Long)zq.getTimeclose(),2) %>
      </p></td>
  </tr>
  <tr bgcolor="#EEF7FF">
    <td width="15%" height="50" align="center"><p align="left" class="style1">备&nbsp;&nbsp;&nbsp; 注</p></td>
    <td height="50" colspan="3" align="center"><p align="left" class="style1">
      <%=zq.getDescription()%>
    </p></td>
  </tr>
  <tr>
    <td align="center" bgcolor="#FFFFFF" colspan="4"> 　</td>
  </tr>
  <tr>
    <td colspan="4" align="left"><p align="center">
    <%
    if(!ze.getStatus().equals("2") && !ze.getStatus().equals("3")){
    %>
      <!-- 
      <input type="submit" value="下载考试" name="B7" src="" />
      &nbsp;
      <input type="submit" value="上传考试" name="B1" src="" />
      
       -->
      <input type="button" value="在线考试" name="B12" src="" onclick="window.open('<%=request.getContextPath()%>/joinExam/check.jsp?quizId=<%=zq.getId() %>')"/>
      &nbsp;
    <%} %>
      <input type="button" value="返回" name="B13" onclick="javascript:history.go(-1);"/>
    </p></td>
  </tr>
</table>
  </body>
</html>
