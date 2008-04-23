<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<%@ page import="java.util.*,com.gever.struts.pager.PageControl"%>
<html>
<head>
<title></title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<style type="text/css">
<!--
-->
</style>
<script langauge="javascript">
function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#cccccc';
}

var ns = navigator.appName == "Netscape";
function display(action,method,res,opt) {
    if(ns) var pForm = window.parent.centerFrame.document.forms[0];
	else var pForm = parent.centerFrame.permissionMapForm;
	pForm.actionpath.value = action;
	pForm.methodname.value = method;
	pForm.rescode.value = res;
	pForm.resoptcode.value = opt;
}
</script>
</head>
<body class="bodybg">

<table width="95%" align="center">
  <tr>
    <td colspan="2" class="listcellrow2" align="center">操作映射列表</td>
  </tr>
  <tr>
    <td colspan="2">
	<table width="100%" border="0" cellpadding="2" cellspacing="0" >
        <tr>
          <td width="10%" class="Listtitle">方法名称</td>
		  <td width="10%" class="Listtitle">资源代码</td>
		  <td width="10%" class="Listtitle">资源操作代码</td>
        </tr>
		<logic:present name="methodList">
			<logic:iterate id="perMap" name="methodList" type="com.gever.sysman.privilege.model.PermissionMap">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);" onclick="javascript:display('<bean:write name="perMap" property="actionPath"/>','<bean:write name="perMap" property="methodName"/>','<bean:write name="perMap" property="resCode"/>','<bean:write name="perMap" property="resOpCode"/>')">
		  <td class="listcellrow2">
		  <bean:write name="perMap" property="methodName"/>
		  </td>
		  <td  class="listcellrow2">
		  <bean:write name="perMap" property="resCode"/>
		  </td>
		  <td class="listcellrow2">
		  <bean:write name="perMap" property="resOpCode"/>
		  </td>
	    </tr>
			</logic:iterate>
		</logic:present>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
<script language="javascript">
function handleAction(obj){
  temp = obj.action;
  position = temp.indexOf("?action");
  if (position == -1){
    obj.action = temp + "?action=search";
  }
  return true;
}
</script>
