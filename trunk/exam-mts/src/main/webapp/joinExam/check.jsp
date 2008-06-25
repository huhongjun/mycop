<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page"%>
<HTML>
<HEAD>
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<TITLE>参加考试——请输入考试密码</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/login-css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function userlogin()
{
	if(QuizForm.userName.value == null || QuizForm.userName.value == ""){
		alert("请输入用户名！");
		QuizForm.userName.focus();
		return;
	}
	if(QuizForm.passcord.value == null || QuizForm.passcord.value == ""){
		alert("请输入验证码！");
		QuizForm.passcord.focus();
		return;
	}
	document.QuizForm.submit();
}
</script>
<BODY class="body-css">
<form name="myform" method="post" action="<%=request.getContextPath()%>/joinExam.do?method=check&quizId=<%=request.getParameter("quizId") %>">
<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="40"><table width="510"  border="0" align="center" cellpadding="0" cellspacing="0" background="../image/login-exam.jpg">
      <tr>
        <td height="278" valign="top"><table width="50%"  border="0" align="center" cellpadding="0" cellspacing="0" class="13">
          <tr>
            <td width="24%" height="110">&nbsp;</td>
            <td width="76%">&nbsp;</td>
            </tr>
          <tr>
            <td height="40" class="13" nowrap><font style="font-size:12px"><strong>考试密码：</strong></font></td>
            <td><input type="password" name="password" style="width:150px;"></td>
            </tr>          
          <tr>
            <td class="login-text" colspan="2" align="center"><%if(request.getAttribute("info") != null){out.print("<font color=\"red\">" + request.getAttribute("info") + "</font>");} %></td>
          </tr>
          
          <tr>
            <td colspan="2" height="40" align="center"><div id="submitForm"><span class="button"><input type="submit" value=" 进入 "/></span>　<span class="button"><input type="reset" value=" 重置 "/></span>　<span class="button"><input type="button" value=" 退出 " onclick="javascript:window.close()"/></span></div></td>
          </tr>
         
        </table></td>
        </tr>
    </table></td>
  </tr>
</table>
</form>
</BODY>
</HTML>
