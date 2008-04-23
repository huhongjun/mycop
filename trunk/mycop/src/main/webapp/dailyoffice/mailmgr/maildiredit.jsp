<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.dao.MailConstant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<title>邮件夹信息</title>
<%
	String context =request.getContextPath();
%>
<SCRIPT LANGUAGE=javascript>
function init() {
	try{
    if(document.all['vo.flag'].value == '0' || document.all['vo.flag'].value == '') {
        document.all['candelete'].checked = false;
    } else {
        document.all['candelete'].checked = true;
    }
    if(document.all['candelete'].checked == false && document.all['vo.flag'].value == "") {
        document.all['vo.flag'].value == '0';
    }
	}catch(e){
	}
}
</SCRIPT>
</head>

<body class="bodybg" onload="javascript:init()">
<html:form action="/dailyoffice/mailmgr/maildiredit">
<table border="0" cellpadding="0" cellspacing="0" width="90%" align="center">
  <tr>
    <td height="30"></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="15"><img src="../../images/ico_bjwjj.gif" width="32" height="32"></td>
    <td><p align="left" class="f14">编辑邮件夹</p></td>
  </tr>
  <tr>
    <td height="8">&nbsp;</td>
    <td><hr size="1"></td>
  </tr>
  <tr>
    <td height="7">&nbsp;<html:hidden name="MailDirectoryForm" property="actionType"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="40">&nbsp;</td>
    <td align="center">
      <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0>
        <TR>
          <TD class="InputLabelCell">邮件夹名称:</TD>
          <TD valign="top"><html:text property="vo.mail_dir_name"  validatetype="notempty" msg="请输入邮件夹名称" size="25" maxlength="15" style="width:145" styleClass="input2"/> <html:hidden property="vo.flag" value="0"/></TD>
        </TR>
    
        <tr>
            <td colspan="2">
                &nbsp;
            </td>
        </tr>
        <TR>
          <TD height="25" align="center" colspan="2">
		<goa:button property="save" value="保存" styleClass="button" operation="YJGL.OPT" onclick="return doSave(MailDirectoryForm)"/>
		<goa:button property="saveAndReturn" value="保存并返回" styleClass="button" operation="YJGL.OPT"  onclick="return doSaveExit(MailDirectoryForm)"/>
          	<goa:button property="exit" value="返回" styleClass="button" operation="YJGL.OPT" onclick="doAction('goUrl(index)')"/></td>
          </TD>
        </TR>
      </TABLE>
    </td>
  </tr>
</table>
</html:form>
</body>

</html>
