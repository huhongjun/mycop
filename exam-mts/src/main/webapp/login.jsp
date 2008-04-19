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
<TITLE>系统登陆</TITLE>
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
<html:form method="post" action="/Login.do" target="_parent">
	<html:hidden property="method" value="studentLogin" />
<TABLE height="100%" width="101%">
<TR>
    <TD vAlign="middle" align="center" class="login-bg"><table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="<%=request.getContextPath()%>/image/login-title-pic1.jpg" width="300" height="60" border="0"></td>
      </tr>
      <tr>
        <td><table width="600" height="400" border="0" align="center" cellpadding="0" cellspacing="0" background="<%=request.getContextPath()%>/image/login_bg_01.jpg">
          <tr>
            <td width="280" rowspan="2" align="center">&nbsp;</td>
            <td width="320" align="left" valign="middle"><table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="200" align="center" valign="middle" background="<%=request.getContextPath()%>/image/form_bg_01.gif"><table width="260" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="10" align="center"></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td width="29%" align="center" class="login-text">用户名：</td>
                    <td width="71%"><span class="wenzi">
                      <input class="cm-input-bg" name="userName" type="text" size="14" maxlength="50" />
                    </span></td>
                  </tr>
                  <tr>
                    <td align="center" class="login-text">验证码：</td>
                    <td><span class="wenzi">
                      <input class="cm-input-bg" type="text" name="passcord"  size="4" maxlength="4"/> <img height=20 width=60 src="<%=request.getContextPath()%>/passcode.jsp"  name="card" >
                    </span></td>
                  </tr>
                  <tr>
                    <td class="login-text" colspan="2" align="center"><%if(request.getAttribute("info") != null){out.print("<font color=\"red\">" + request.getAttribute("info") + "</font>");} %></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td valign="middle" class="wenzi"><input class="cm-button-bg" type="button" name="Submit52" value="登陆" onClick="userlogin();"/>
                        <input class="cm-button-bg" type="reset" name="Submit22" value="取消" />
                    </td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td valign="bottom">&nbsp;</td>
          </tr>
        </table></td> 
      </tr>
    </table></TD>
</TR>
</TABLE>
</html:form>
</BODY>
</HTML>
