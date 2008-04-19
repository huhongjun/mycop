<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>

<%@ page import="com.zhjedu.exam.domain.ZjQuizExam" %>
<%@ page import="com.zhjedu.exam.domain.ZjQuiz" %>
<%@ page import="com.zhjedu.exam.domain.ZjCourse" %>
<%@ page import="com.zhjedu.util.DateTimeUtil" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
  <link href="<%=request.getContextPath()%>/css/ksmain.css" rel="stylesheet" type="text/css" />
    <title>list</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <style type="text/css">
<!--
.sec1 {/*background-color: #EEEEEE;*/
cursor: hand;
font-size:12px;
color: #000000;
width:107;
height:25;
background-image=url(assignment_s/image/x-00100.gif);
}
.style1 {font-size: 12px}
.style3 {font-size: 12px; font-weight: bold; }
-->
</style>
  <script>
    flag = 0;

    function secBoard(status){
    	flag = status;
       if(status!=null)
       {
       window.location.href="assignment.do?method=getStatus&courseId=<%=request.getAttribute("courseId") %>&status="+status;
       }
    }
    function select(){
       if(0==flag){
       
       }else{
       
       }if(1==flag){
       
       }else{
       
       }if(2==flag){
       
       }else{
       
       }if(3==flag){
       
       }else{
       
       }
    }
  </script>
  <body>
<form action="assignment.do?method=getStatus" method="post" name="assignmentForm">
  <div align="center">
  <div align="left"> <img src="assignment_s/image/x-00101.gif" width="300" height="70" />
      <table border="0" cellspacing="1" cellpadding="1" id="secTable">
        <tr align="center">
        <%
        String status = request.getAttribute("status").toString();
        String courseId = (String)request.getAttribute("courseId");
        if(status.equals("0")){%>
        <td id="id1" valign="middle" class="sec1">新考试</td>
        <%	        	
        }else{%>
        <td id="id2" valign="middle" class="sec1"><a href="javascript:secBoard(0)">新考试</a></td>
       <% }
          
                 if(status.equals("1")){%>
        <td id="id3" valign="middle" class="sec1">已参加未完成 </td>
        <%	        	
        }else{%>
        <td id="id4" valign="middle" class="sec1"><a href="javascript:secBoard(1)">已参加未完成 </a></td>
       <% }
                if(status.equals("3")){%>
        <td id="id7" valign="middle" class="sec1">已完成 </td>
        <%	        	
        }else{%>
       <td id="id8" valign="middle" class="sec1"><a href="javascript:secBoard(3)">已完成 </a></td>
       <% }
        %>
        </tr>
      </table>
    <table border="0" width="100%" id="table4">
        <tr bgcolor="#7FBEE6">
          <td width="5%" height="21"><p align="center" class="style1"><strong>序号</strong></p></td>
          <td width="18%" height="21"><p align="center" class="style1"><strong>考试名称</strong></p></td>
          <td width="15%" height="21" bgcolor="#7FBEE6"><p align="center" class="style1"><strong>有效期</strong></p></td>
          <td bgcolor="#7FBEE6" width="13%"><p align="center" class="style1"><strong>考试发布方式</strong></p></td>
          <td bgcolor="#7FBEE6" width="9%"><p align="center" class="style1"><strong>计分方式</strong></p></td>
          <td width="5%" height="21"><p align="center" class="style1"><strong>成绩</strong></p></td>
          <td width="13%" height="21" bgcolor="#7FBEE6"><div align="center"><span class="style3"> 操作</span></div></td>
        </tr>
        <%com.zhjedu.util.PageObject po=(com.zhjedu.util.PageObject)request.getAttribute("po");%> 
        
        <%
         ArrayList list = po.getDatas();
         if(list!=null)
         {  
            int i=0;
            Iterator it = list.iterator();
            while(it.hasNext()){
            Object[] zjquizexam = (Object[])it.next();
			ZjQuizExam ze = (ZjQuizExam)zjquizexam[0];
			ZjQuiz zq = (ZjQuiz)zjquizexam[1];
			
			i++;
			String assignmentId = zq.getId();
			String name = zq.getName();
			Long timeopen = zq.getTimeopen();
			Long timeclose = zq.getTimeclose();
			String style = zq.getQuizStyle();
			if("0".equals(style))
            {
              style = "在线";
            }if("1".equals(style))
            {
              style = "离线";
             }
			String grademethod = zq.getGrademethod();
			if("1".equals(grademethod)) {
				grademethod ="最高成绩";
			}if("3".equals(grademethod)){
				grademethod ="最后成绩";
			}if("2".equals(grademethod)){
				grademethod ="平均成绩";
			}
			String grade = ze.getGrade().toString();
          %>
          <tr bgcolor="#EEF7FF">
          <td align="center"><span class="style1"><%=i%></span></td>
          <td align="center"><span class="style1"><%=name%></span></td>
          <td align="center"><span class="style1"><%=DateTimeUtil.getTime((Long)timeopen,2)%>--<%=DateTimeUtil.getTime((Long)timeclose,2)%></span></td>
          <td bgcolor="#EEF7FF" align="center"><span class="style1"><%=style%></span></td>
          <td align="center"><span class="style1"><%=grademethod%></span></td>
          <td align="center"><span class="style1"><%=grade%></span></td>
          <td bgcolor="#EEF7FF"><div align="center"><input class="btn_cm_small" type="button" value="查看" name="B" onclick="javascript:window.location.href='<%=request.getContextPath() %>/assignment.do?method=getAssignment&assignmentId=<%=assignmentId%>&status=<%=status%>'"/></div></td>
        </tr>        
          <%
             }
           }
         %>
        
<tr>
	<td align="center" colspan="9">
		<span class="style1"><page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="assignmentForm" pageVar="page" jsName="changePage" url="assignment.do?method=getStatus"/></span>
	</td>
</tr>
      </table>
    <p>&nbsp;</p>
  </div>
</div>
<input type="hidden" name="courseId" value="<%=courseId %>">
<input type="hidden" name="status" value="<%=status %>">
</form>
  </body>
</html>
