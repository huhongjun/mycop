<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>

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

    function secBoard(status,quiz){
    	flag = status;
       if(status!=null)
       {
       window.location.href="Quiz.do?method=getStudentQuizList&status="+status+"&quizType=2&quiz="+quiz;
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
<form action="Quiz.do?method=getStudentQuizList" method="post" name="quizForm">
  <%
  String status = request.getAttribute("status").toString();
  String quizType = request.getAttribute("quizType").toString();  
  String quizid="";
  
  if(request.getAttribute("quizid")!=null){
  	quizid = (String)request.getAttribute("quizid");
  }
 
   %>
   <input type=hidden name="quizid" value="<%=quizid %>"/>

  <div align="center">
  <div align="left"> <img src="<%=request.getContextPath() %>/image/x-10105.gif" width="300" height="70" />
      <table border="0" cellspacing="1" cellpadding="1" id="secTable">
        <tr align="center">
        <td id="id5" valign="middle" class="sec1">已完成 </td>
        </tr>
      </table>
    <table border="0" width="100%" id="table4">
        <tr bgcolor="#7FBEE6">
          <td width="5%" height="21"><p align="center" class="style1"><strong>序号</strong></p></td>
          <td width="18%" height="21"><p align="center" class="style1"><strong>考试名称</strong></p></td>
         
          
         
          <td bgcolor="#7FBEE6" width="9%"><p align="center" class="style1"><strong>学生姓名</strong></p></td>
          <td width="5%" height="21"><p align="center" class="style1"><strong>成绩</strong></p></td>
          <td width="13%" height="21" bgcolor="#7FBEE6"><div align="center"><span class="style3"> 操作</span></div></td>
        </tr>
        <%com.zhjedu.util.PageObject po=(com.zhjedu.util.PageObject)request.getAttribute("po");%> 
        <bean:define id="list" name="po" property="datas"/>
  		<logic:iterate indexId="ind" id="element" name="list">
  		<tr <%if(ind%2==0){%>bgcolor="#F7FBFF"<%}else{%>bgcolor="#EEF7FF"<%}%>>
          <td width="5%" height="21"><p align="center" class="style1"><%=ind+1 %></p></td>
          <td width="18%" height="21"><p align="center" class="style1"><bean:write name="element" property="zjQuiz.name"/></p></td>
         
          
         
          <td width="9%"><p align="center" class="style1"><strong><bean:write name="element" property="student.realName"/></strong></p></td>
          <td width="5%" height="21"><p align="center" class="style1"><strong><bean:write name="element" property="grade"/></strong></p></td>
          <td width="13%" height="21"><div align="center"><span class="style3">
          <%if(status.equals("2")){%>
          <input class="btn_cm" type="button" value="批阅试卷" name="B" onclick="javascript:window.open('<%=request.getContextPath() %>/joinExam.do?method=quizQuestionAnswerInfo&userId=<bean:write name="element" property="student.studentId"/>&quizId=<bean:write name="element" property="zjQuiz.id"/>')"/>
          <%}else if(status.equals("3")){%>
          <input class="btn_cm" type="button" value="查看详细" name="B" onclick="javascript:window.open('<%=request.getContextPath() %>/joinExam.do?method=quizQuestionAnswer&userId=<bean:write name="element" property="student.studentId"/>&quizId=<bean:write name="element" property="zjQuiz.id"/>')"/>
          <%} %>
          </span></div></td>
        </tr>
        
  		</logic:iterate>
<tr>
	<td align="center" colspan="9">
		<page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="quizForm" pageVar="page" jsName="changePage" url="Quiz.do?method=getStudentQuizList"/>
	</td>
</tr>
      </table>
    <p>&nbsp;</p>
  </div>
</div>

<input type="hidden" name="quizType" value="<%=quizType %>">
</form>
  </body>
</html>
