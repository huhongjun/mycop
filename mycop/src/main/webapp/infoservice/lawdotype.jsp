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
        <td align="center"><span class="TableTitleText">类别编辑</span></td>
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
		<td align="left" class="InputLabelCell">类别名称：</td>
		<td>
		<logic:equal name="IsDoTypesForm" property="actionType" value="add">
		<html:text styleClass="input2" property="vo.info_type" size="22"  validatetype="notempty" msg="类别名称不能为空！"  maxlength="10"/><font color="red">&nbsp;*</font>
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="actionType" value="modify">
		<html:text styleClass="input2" property="vo.info_type" size="22"  validatetype="notempty" msg="类别名称不能为空！" readonly="true"  maxlength="10"/><font color="red">&nbsp;*</font>
		</logic:equal>

		</td>
	</tr>
<!--	<tr class="listcellrow">
	    <td align="left" class="InputLabelCell">目录级别：</td>
		<td>
		<html:text styleClass="input2" property="vo.typelevel" size="22"
		maxlength="10"/>
		-->
	<tr class="listcellrow">
	<logic:notEqual name="IsDoTypesForm" property="vo.parent_type" value="">
		<td align="left" class="InputLabelCell">上级目录：</td>
		<td>
		<html:text styleClass="input2" property="vo.parent_type" size="22"
		maxlength="10" readonly="true" />
		</td>
		</logic:notEqual>
	</tr>
	<tr class="listcellrow">
	    <td align="left" class="InputLabelCell">所属模块：</td>
		<td><html:select property="vo.moduleflag" disabled="true"  size="1" >
		<html:option value="0" >资源共享</html:option>
		<html:option value="1">政策法规</html:option>
		<html:option value="2" >公司动态</html:option>
	<!--	<html:option value="3">部门信息</html:option> -->
	<!--	<html:option value="4">报告管理</html:option> -->
		<html:option value="5">行业新闻</html:option>
		<html:option value="6">管理制度</html:option>
		<html:option value="7">文档资料</html:option>
		</html:select>
		</td>
	</tr>

	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" valign="top">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
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
		<goa:button property="save" value="保存" styleClass="button"  onclick="return doSave(IsDoTypesForm)" operation="LSSZ.OPT"/>
		<goa:button property="save" value="保存并返回" styleClass="button"  onclick="return doSaveExit(IsDoTypesForm)" operation="LSSZ.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="LSSZ.OPT"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>
