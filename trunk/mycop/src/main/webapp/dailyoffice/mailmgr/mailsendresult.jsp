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
        String strAction = "����";
        if(mailForm.isSaveOnly()) {
            strAction = "�ݸ屣��";
        }else if(mailForm.isSave()) {
            strAction += "������";
	}
%>
<html>
<%
    if("".equals(mailForm.getErrorInfo().getErrorMsg()) == false) {
%>
<div align="center">
	  <font color=red><br>
	    <br>
      �����ʼ�ʱ�������������ʼ�����û�гɹ����͡�<br>
      <br>
      <br>
      <br>
	  </font>
          <b><a href="maillist.do?mailDirId=0"> �ص��ռ���</a><br>
	  </b>
	  <br>
	  <br>
	  <br>
          <br>
          <b><a href="javascript:history.back();"> ���ͻص��ʼ�</a><br>
	  </b>
	  <!--a href="javascript:window.opener.reload();">��</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.back();">��</a-->

</div>
<%
} else if(mailForm.getErrorList().size() > 0) {
%>
<table width="100%" border="0" cellpadding="1" cellspacing="0">
   	   <tr>
	   		<td colspan="4" align="center">
	  <font color=red><br>
	    <br>
      �����ʼ�ʱ�������������ʼ�����û�гɹ����͡�<br>
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
            <td width="50" nowrap>�û�����</td>
          <td width="100">�ʼ�����</td>
          <td width="100">�������</td>
          <td width="250">������Ϣ</td>
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
          				<input type="button" class="button" onclick="javascript:window.location = 'maillist.do?mailDirId=0'" value="�ص��ռ���" >
					</td>
					<td colspan="1" align="center">
         				<input type="button" class="button" onclick="javascript:window.location='/goa/dailyoffice/mailmgr/writemail.do?resend=true'" value="�ص������ʼ�">
					</td>
				</tr>
            </table>
		</td>
	</tr>
</table>
	  <!--a href="javascript:window.opener.reload();">��</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.back();">��</a-->

</div>
<%
	} else if(mailForm.getPop3MailResult() != null && "".equals(mailForm.getPop3MailResult()) == false) {
%>
<div align="center">
	  <font color=red><br>
	    <br>
      �����ʼ�ʱ�������������ʼ�����û�гɹ����͡�<br>
          <goa:write name="MailMgrForm" property="pop3MailResult" filter="false"/>
      <br>
      <br>
      <br>
	  </font>
          <b><a href="maillist.do?mailDirId=0"> �ص��ռ���</a><br>
	  </b>
	  <br>
	  <br>
	  <br>
          <br>
          <b><a href="javascript:history.back();"> ���ͻص��ʼ�</a><br>
	  </b>
	  <!--a href="javascript:window.opener.reload();">��</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.back();">��</a-->

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
	�ʼ�<%=strAction%>�ɹ���
	</td>
	</tr>
	</table>
	</td>
	</tr>
	</table>
	      <a href="maillist.do?mailDirId=0"><br>
	      <br>
	      <br>
	    �����ռ���</a>

	      </div>
            </div>
<%}%>
</body>

</html>
