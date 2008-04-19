<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ page import="com.zhjedu.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<head>
<title>课程列表</title>
<link href="<%=request.getContextPath()%>/css/ksmain.css" rel="stylesheet" type="text/css" />
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
</head>
<script language=javascript>
function quizlist(){
	document.QuizForm.method.value="main";
	document.QuizForm.target="_top";
	document.QuizForm.submit();
}
</script>
<body topmargin="0" leftmargin="0">
<html:form action="/Quiz.do" method="post" >
<input type=hidden name="method" value="listCourse"/>
<input type=hidden name="quizType" value="2"/>
<table width="97%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" class="table-pub">
  
  <tr>
    <td align="center" bgcolor="#FFFFFF"><table border="0" width="100%" id="table3" cellpadding="0" style="border-collapse: collapse" cellspacing="0">
      
      
      <tr>
        <td height="71" colspan="2" align="left" valign="top" bgcolor="#FFFFFF"><table width="100%" height="60" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" id="table12" style="border-collapse: collapse">
            <tr>
              <td align="center" bgcolor="#FFFFFF" class="wenzi"><table width="100%" height="53" border="0" cellpadding="0" id="table12" style="border-collapse: collapse">
                 
                  <tr>
          <TD COLSPAN=3> <IMG SRC="<%=request.getContextPath() %>/image/top.gif" WIDTH=1002 HEIGHT=87 ALT=""></TD>
      </tr>
              </table></td>
            </tr>
        </table></td>
      </tr>
      
      
      
      <tr>
      	<td width="20%" valign="top">
      	<table width="95%"  border="0" cellpadding="0" cellspacing="0">
      	<tr>
            <td valign="bottom"><img src="image/2_13%5B1%5D.jpg" width="225" height="34" /></td>
        </tr>
        <tr>
      	<td height="80" valign="middle" background="image/2_36[1].jpg"><table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="20" colspan="2"><span class="style1">欢迎您，登陆在线考试系统！</span></td>
              </tr>
              <tr>
                <td width="30%" height="20"><span class="style1">姓名：</span></td>
                <td width="53%" align="left" height="20"><span class="style1"><%=UserSessionInfo.getTeacherRealName(request) %></span></td>
              </tr>
              <tr>
                <td height="20">　</td>
                <td height="20">　</td>
              </tr>
            </table>
        </td></tr>
        <tr>
            <td height="18" valign="top"><img src="image/2_39%5B1%5D.jpg" width="225" height="17" /></td>
          </tr>
          <tr>
        <td height="141" valign="top"><table width="95%"  border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="bottom"><img src="image/x-015.gif" width="223" height="250" /></td>
          </tr>
        </table></td>
      </tr>
        </table>
      	</td>
      	<td width="75%" valign="top">
      	  <table width="80%"  border="0" cellpadding="0" cellspacing="0">
      	  	<tr>
      	  		<td align="left">
      	  		<span class="style1"><font color="#FF0000">下面是您可以管理的课程列表，请选择相应的操作进入。</font></span>
      	  		</td>
      	  	</tr>
      	  </table>
      	  <table border="1" width="100%" id="table11" cellspacing="1" bordercolorlight="#DDDDFF" bordercolordark="#FFFFFF">
			  <logic:iterate indexId="ind" id="element" name="list"> 
              <tr <%if(ind % 2 != 0){%>bgcolor="#FFFFCC"<%} %>>             
                <td align="center" valign="top" class="wenzi"><img src="image/tb16_dept.gif" width="16" height="16" /> <span class="style1"><bean:write name="element" property="name"/></span></td>
                <td align="center" valign="top" class="wenzi"><span class="style1">
                 <input class="btn_cm" type="button" value="题库管理" name="B" onclick="javascript:window.location.href='<%=request.getContextPath()%>/Question.do?method=main&course=<bean:write name="element" property="id"/>&flag=0'"/> 
				 <input class="btn_cm" type="button" value="考试管理" name="B" onclick="javascript:window.location.href='<%=request.getContextPath()%>/Quiz.do?method=main&quizType=1&course=<bean:write name="element" property="id"/>&flag=0'"/> 
				 <input class="btn_cm" type="button" value="章节维护" name="B"/> 
  	    		 </span>
                </td>                
              </tr>
              </logic:iterate>
          </table>
      	</td>
      </tr>
      
      <tr>
        <td colspan="2" align="left" valign="top" bgcolor="#FFFFFF">
        <jsp:include page="../bottom.html"/>
        </td>
      </tr>
      
      
      
    </table></td>
  </tr>
</table>
</html:form>
</body>
</html>