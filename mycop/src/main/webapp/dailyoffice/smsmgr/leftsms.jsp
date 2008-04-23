<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.struts.Constant"%>
<html>


<%
String context =request.getContextPath();
%>

<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" id="goastylecss" rel="stylesheet" type="text/css">

<TITLE>左帧页面</TITLE>


</HEAD>
<body class="mailbody" topmargin=0 leftmargin=0 marginwidth=0 marginheight=0   >
<SCRIPT LANGUAGE="JavaScript">
<!--
	function exitSystem(){
		var bYesNo = confirm("是否退出短信服务系统?");

		if (bYesNo == true ){
			window.parent.close();
		}

	}
//-->
</SCRIPT>
<body class="mailbody2">
<table border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="<%=context%>/images/duxin.gif" width="130" height="89"></td>
  </tr>
  <tr>
    <td height="31" class="f14" align="center" nowrap="nowrap"><font color="#FFFFFF"><strong>欢迎您：<%=(String) session.getAttribute(Constant.NAME)%></strong></font></td>
  </tr>
  <tr>
    <td align="center">
<table border="0" cellpadding="0" cellspacing="0">

        <tr>
          <td><img src="<%=context%>/images/tree4.gif" width="12" height="20"></td>
          <td ><img src="<%=context%>/images/ico_mail_fyj.gif" width="16" height="16" align="absmiddle"><a href="<%=context%>/dailyoffice/smsmgr/writesms.do" target="middle" class="menu2">发短信</a></td>
        </tr>
        <tr>
          <td><img src="<%=context%>/images/tree4.gif" width="12" height="20"></td>
          <td class="t12bai"><img src="<%=context%>/images/ico_mail_sjx.gif" width="16" height="16" align="absmiddle"><a href="<%=context%>/dailyoffice/smsmgr/listsmsinbox.do" target="middle" class="menu2">收件箱</a></td>
        </tr>
        <tr>
          <td><img src="<%=context%>/images/tree4.gif" width="12" height="20"></td>
          <td class="t12bai"><img src="<%=context%>/images/ico_mail_fjx.gif" width="16" height="16" align="absmiddle"><a href="<%=context%>/dailyoffice/smsmgr/listsmsoutbox.do"  target="middle" class="menu2">发件箱</a></td>
        </tr>

        <tr>
          <td><img src="<%=context%>/images/tree4.gif" width="12" height="20"></td>
          <td class="t12bai"><img src="<%=context%>/images/ico_mail_dzb.gif" width="16" height="16" align="absmiddle"><a href ="<%=context%>/infoservice/communication.do?paraFlag=1" target="middle" class="menu2">通讯簿</span></td>
        </tr>
        <tr>
          <td><img src="<%=context%>/images/tree4-2.gif" width="12" height="20"></td>
          <td class="t12bai"><img src="<%=context%>/images/ico_mpj.gif" width="16" height="16" align="absmiddle"><a href="<%=context%>/dailyoffice/tools/cardframe.jsp"  target="middle" class="menu2">名片夹</span></td>
        </tr>
        

        <tr>
          <td>&nbsp;</td>
          <td class="t12">&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
