<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>

<script language="javascript">

    <logic:present name="ErrorMsg" scope="request">
    alert('<bean:write name="ErrorMsg" scope="request" />');
    </logic:present>

</script>

<%
	String operate = (String)session.getAttribute("operate");
	session.removeAttribute("operate");

	String nodeid=(String)session.getAttribute("nodeid");
	if(nodeid==null){
	nodeid="";
}
	session.removeAttribute("nodeid");

%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function refreshTree(){
    var operate='<%=operate%>';
	var nodeid='<%=nodeid%>';
    if(operate=="insert"){
		window.parent.frames["left"].reload(nodeid);
    }else if(operate=="update"){
		window.parent.frames["left"].reload(nodeid);
    }else {
    }
}
//-->
</SCRIPT>

<html>
<body class="bodybg" onload="refreshTree()">
<html:form action="/infoservice/lawdotype" method="post">
<span class="TableTitleText"><br></span>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="center"><span class="TableTitleText">���༭</span></td>
    </tr>
    <tr>
        <td>&nbsp;<html:hidden name="IsDoTypesForm" property="actionType"/></td>
    </tr>
</table>

<table  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="70%" align="center">
	<tr class="listcellrow">
		<td width="15%">&nbsp;</td>
		<td width="65%">&nbsp;</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">������ƣ�</td>
		<td>
		<logic:equal name="IsDoTypesForm" property="actionType" value="add">
		<html:text styleClass="input2" property="vo.info_type" size="22"  validatetype="notempty" msg="������Ʋ���Ϊ�գ�"  maxlength="10"/><font color="red">&nbsp;*</font>
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="actionType" value="modify">
		<html:text styleClass="input2" property="vo.info_type" size="22"  validatetype="notempty" msg="������Ʋ���Ϊ�գ�" readonly="true"  maxlength="10"/><font color="red">&nbsp;*</font>
		</logic:equal>

		</td>
	</tr>
<!--	<tr class="listcellrow">
	    <td align="left" class="InputLabelCell">Ŀ¼����</td>
		<td>
		<html:text styleClass="input2" property="vo.typelevel" size="22"
		maxlength="10"/>
		-->
	<tr class="listcellrow">
	<logic:notEqual name="IsDoTypesForm" property="vo.parent_type" value="">
		<td align="left" class="InputLabelCell">�ϼ�Ŀ¼��</td>
		<td>
		<html:text styleClass="input2" property="vo.parent_type" size="22"
		maxlength="10" readonly="true" />
		</td>
		</logic:notEqual>
	</tr>
	<tr class="listcellrow">
	    <td align="left" class="InputLabelCell">����ģ�飺</td>
		<td><html:select property="vo.moduleflag" disabled="true"  size="1" >
		<html:option value="0" >��Դ����</html:option>
		<html:option value="1">���߷���</html:option>
		<html:option value="2" >��˾��̬</html:option>
	<!--	<html:option value="3">������Ϣ</html:option> -->
	<!--	<html:option value="4">�������</html:option> -->
		<html:option value="5">��ҵ����</html:option>
		<html:option value="6">�����ƶ�</html:option>
		<html:option value="7">�ĵ�����</html:option>
		</html:select>
		</td>
	</tr>

	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" valign="top">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
		<td>
		<html:textarea property="vo.remark" rows="4" cols="60%" styleClass="input2" maxlength="15"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td align="center">
		<goa:button property="save" value="����" styleClass="button"  onclick="return doSave(IsDoTypesForm)" operation="LSSZ.OPT"/>
		<goa:button property="save" value="���沢����" styleClass="button"  onclick="return doSaveExit(IsDoTypesForm)" operation="LSSZ.OPT"/>
        <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="LSSZ.OPT"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>
