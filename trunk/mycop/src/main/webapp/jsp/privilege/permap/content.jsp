<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<script src="<gdp:context/>/js/Validator.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��ϵӳ��</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<script language="javascript" src="<gdp:context/>/js/pub.js"></script>
</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
var context='<%=request.getContextPath()%>';


function ok_click(){
	if (parent.bottomFrame == null) {
		parent.bottomFrame.location="<gdp:context/>/privilege/permissionMapAction.do?action=toMethodList";
	}
    parent.bottomFrame.location.reload();
}

function add(){
	document.forms[0].action="<gdp:context/>/privilege/permissionMapAction.do?action=create";
	document.forms[0].target="_self";
	if(validater(document.forms[0])){
		return true;
	}
	return false;
}

function exportDDL(){
	parForm = document.forms[0];

	if (parForm.exportddl.value==null ||parForm.exportddl.value=="" ) {
		alert("��ѡ��������Ľڵ���е���������");
		return;
	}
	
	document.forms[0].action="<gdp:context/>/privilege/permissionMapAction.do?action=export";
	document.forms[0].target="bottomFrame";
	document.forms[0].submit();

}
//******************************************************************
function doBack(){
    if(navigator.appName == "Microsoft Internet Explorer"){
        history.back();
        return;
    }
    var permissionMapForm = document.forms[0];
    var action="";
    action += "<gdp:context/>/privilege/permissionMapAction.do?action=list";

    permissionMapForm.action = action;
    permissionMapForm.submit();
}
//******************************************************************
//-->

</SCRIPT>


<body class="bodybg" onload="javascript:ok_click();">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>

<html:form action="/permissionMapAction.do?action=create">

<center>
<table width="100%" border="0"  cellpadding="2" cellspacing="1">
  <tr align="center">
    <td colspan="4" class="TableTitleText">��ϵӳ��ά��</td>
  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr>
    <td width="38%" align="right" nowrap  class="InputLabelCell">����·��:</td>
    <td width="62%" colspan="3" ><html:text property="actionpath" size="30" styleClass="input2" maxlength="100" validatetype="notempty" msg="����·������Ϊ��" readonly="true"/></td>
  </tr>
  <tr>
    <td nowrap  class="InputLabelCell"  align="right">��������:</td>
    <td colspan="3" ><html:text property="methodname" size="30" maxlength="50" styleClass="input2" validatetype="notempty" msg="�������Ʋ���Ϊ��" readonly="true"/></td>
  </tr>
   <tr>
    <td nowrap  class="InputLabelCell" align="right" >��Դ����:</td>
    <td colspan="3" ><html:text property="rescode" size="30" styleClass="input2" maxlength="30" validatetype="notempty" msg="��Դ���벻��Ϊ��" readonly="true"/></td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" align="right" >��������:</td>
    <td colspan="3" ><html:text property="resoptcode" styleClass="input2"  readonly="true" size="30" maxlength="30" validatetype="notempty" msg="�������벻��Ϊ��"/></td>
  </tr>
  <tr>
	<td>&nbsp;</td>
    <td colspan="3" align="left">
      <html:submit property="create" value="ȷ��" styleClass="button" onclick="return (add())"/>
      <html:reset property="reset" styleClass="button" value="����" />
      <html:button property="list" styleClass="button" value="����" onclick="javascript:doBack();"/>
    </td>
  </tr>
  <tr>
	<td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" align="right" >�����ڵ�:</td>
    <td colspan="3" ><html:text property="exportddl" styleClass="input2" readonly="true"  size="30" maxlength="30"/>
		<html:button property="exp" value="����" styleClass="button" onclick="javascript:exportDDL()"/>
	</td>
  </tr>
</table>
</center>
</html:form>

</body>
</html>

<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
