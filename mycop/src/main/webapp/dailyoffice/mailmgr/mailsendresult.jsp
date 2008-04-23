<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.action.MailMgrForm"%>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.dao.MailConstant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
	String context =request.getContextPath();
%>
<%
	MailMgrForm mailForm = (MailMgrForm)session.getAttribute("MailMgrForm");
        //System.out.println("----------- error list size is " + mailForm.getErrorList().size());
        String strAction = "发送";
        if(mailForm.isSaveOnly()) {
            strAction = "草稿保存";
        }else if(mailForm.isSave()) {
            strAction += "并保存";
	}
%>
<html>
<%
    if("".equals(mailForm.getErrorInfo().getErrorMsg()) == false) {
%>
<div align="center">
	  <font color=red><br>
	    <br>
      发送邮件时碰到错误，您的邮件可能没有成功发送。<br>
      <br>
      <br>
      <br>
	  </font>
          <b><a href="maillist.do?mailDirId=0"> 回到收件夹</a><br>
	  </b>
	  <br>
	  <br>
	  <br>
          <br>
          <b><a href="javascript:history.back();"> 发送回到邮件</a><br>
	  </b>
	  <!--a href="javascript:window.opener.reload();">是</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.back();">否</a-->

</div>
<%
} else if(mailForm.getErrorList().size() > 0) {
%>
<table width="100%" border="0" cellpadding="1" cellspacing="0">
   	   <tr>
	   		<td colspan="4" align="center">
	  <font color=red><br>
	    <br>
      发送邮件时碰到错误，您的邮件可能没有成功发送。<br>
      <br>
      <br>
      <br>
          </font>
		</td>
	</tr>
	<tr>
		<td colspan="4">
          <table border="0" align="center" cellpadding="1" cellspacing="1">
          <tr class="Listtitle">
            <td width="50" nowrap>用户姓名</td>
          <td width="100">邮件主题</td>
          <td width="100">错误代码</td>
          <td width="250">出错信息</td>
          </tr>
	<logic:iterate id="ps" name="MailMgrForm" property="errorList" type="com.gever.goa.dailyoffice.mailmgr.vo.MailErrorInfoVO" indexId="index">
		<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
                	<td class="listcellrow">
                            <goa:write name="ps" property="userName" filter="true"/>&nbsp;
                	</td>
                	<td class="listcellrow">
                            <goa:write name="ps" property="title" filter="true"/>&nbsp;
                	</td>
                	<td class="listcellrow">
                            <goa:write name="ps" property="errorCode"/>&nbsp;
                	</td>
                	<td class="listcellrow4">
                            <goa:write name="ps" property="errorMsg" filter="false"/>&nbsp;
                	</td>
                </tr>
        </logic:iterate>
                <tr>
                    <td colspan="4">
                        <br>
                    </td>
                </tr>
				<tr>
					<td colspan="3" align="center">
          				<input type="button" class="button" onclick="javascript:window.location = 'maillist.do?mailDirId=0'" value="回到收件夹" >
					</td>
					<td colspan="1" align="center">
         				<input type="button" class="button" onclick="javascript:window.location='/goa/dailyoffice/mailmgr/writemail.do?resend=true'" value="回到发送邮件">
					</td>
				</tr>
            </table>
		</td>
	</tr>
</table>
	  <!--a href="javascript:window.opener.reload();">是</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.back();">否</a-->

</div>
<%
	} else if(mailForm.getPop3MailResult() != null && "".equals(mailForm.getPop3MailResult()) == false) {
%>
<div align="center">
	  <font color=red><br>
	    <br>
      发送邮件时碰到错误，您的邮件可能没有成功发送。<br>
          <goa:write name="MailMgrForm" property="pop3MailResult" filter="false"/>
      <br>
      <br>
      <br>
	  </font>
          <b><a href="maillist.do?mailDirId=0"> 回到收件夹</a><br>
	  </b>
	  <br>
	  <br>
	  <br>
          <br>
          <b><a href="javascript:history.back();"> 发送回到邮件</a><br>
	  </b>
	  <!--a href="javascript:window.opener.reload();">是</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.back();">否</a-->

</div>
<%
	}else {
%>
      <div align="center">
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	  <br>
	<table width="60%" border="0" align="center" height="20%" cellspacing="1" bgcolor="#333333">
      <tr>
        <td bgcolor="#FFFFFF">
	  <table border="0" width="60%" height="100%" bgcolor="white">
	  <tr >
	  <td bgcolor="white" valign="middle" align="center"><img src="<%=context%>/dailyoffice/images/ok.gif" border="0"></td>

	<td class="f14" valign="middle" align="center">
	邮件<%=strAction%>成功！
	</td>
	</tr>
	</table>
	</td>
	</tr>
	</table>
	      <a href="maillist.do?mailDirId=0"><br>
	      <br>
	      <br>
	    返回收件夹</a>

	      </div>
            </div>
<%}%>
</body>

</html>
