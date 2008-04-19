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

<BODY class="body-css">
<form name="myform" method="post" action="<%=request.getContextPath()%>/joinExam.do?method=check&quizId=<%=request.getParameter("quizId") %>">
<TABLE height="100%" width="101%">
<TR>
    <TD vAlign="middle" align="center" class="login-bg"><table width="600" border="0" cellspacing="0" cellpadding="0">
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
                    <td align="center" class="login-text" colspan="2"><font color="#FF0000">请输入考试密码参加考试！</font></td>
                  </tr>
                  <tr>
                    <td align="center" class="login-text">考试密码：</td>
                    <td><span class="wenzi">
                      <input class="cm-input-bg" type="text" name="password"  size="4"/>
                    </span></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td valign="middle" class="wenzi"><input class="cm-button-bg" type="submit" name="Submit52" value="进入"/>
                        <input class="cm-button-bg" type="reset" name="Submit22" value="关闭" onclick="javascript:window.close()"/>
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
</form>
</BODY>
</HTML>
