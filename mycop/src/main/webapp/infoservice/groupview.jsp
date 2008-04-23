<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>


<html>
<body class="bodybg">
<html:form action="/infoservice/groupview" method="post">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">群组设置编辑</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsAddressListForm" property="actionType"/></td>
    </tr>
</table>

<table align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="68%" height="53" align="center">
	<tr class="listcellrow">
		<td width="13%">&nbsp;</td>		
		<td width="87%">&nbsp;</td>

	</tr>
    <html:hidden property="vo.flag" value="0"/>
	<tr class="listcellrow">
		<td width="13%" nowrap align="left" class="InputLabelCell">群组名称：</td>
		<td class="f12">
		<goa:write maxlen="0"  name="IsAddressListForm" property="vo.group_name" filter="true"/>
		</td>
	</tr>
	<tr></tr>
	<tr></tr>
	<tr class="listcellrow">
		<td width="13%" nowrap align="left" class="InputLabelCell">成&nbsp;&nbsp;&nbsp;&nbsp;员：</td>
		<td class="f12">
		<goa:write maxlen="0"  name="IsAddressListForm" property="vo.memberNames" filter="true"/>
		</td>
	</tr>
</table>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
	<tr></tr>
    <tr> 
        <td align="center"> 
		<html:hidden name="IsAddressListForm" property="fid" />
		<logic:equal name="IsAddressListForm" property="paraFlag" value="1">
		<goa:button property="save" value="修改" styleClass="button" operation="ZQSZ.PUBOPT"  onclick=" toUrl('<%=context%>/infoservice/group.do?actionType=modify',false)"/>
		</logic:equal>
		<logic:equal name="IsAddressListForm" property="paraFlag" value="0">
		<goa:button property="save" value="修改" styleClass="button" operation="ZQSZ.OPT"  onclick=" toUrl('../infoservice/group.do?actionType=modify',false)"/>
		</logic:equal>
        <goa:button property="exit" value="返回" styleClass="button" operation="ZQSZ.VIEW" onclick="doAction('goUrl(index)')"/>
		</td>
    </tr>
	<tr>
		<td colspan="5">&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>

</html:form>
</body>
</html>