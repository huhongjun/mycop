<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function setMember(){
	document.getElementsByName("vo.member")[0].value=document.getElementsByName("idfiled")[0].value;
}
//-->
</SCRIPT>

<html>
<body class="bodybg">
<html:form action="/infoservice/group" method="post">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">群组设置编辑</span></td>
    </tr>
    <tr> 
        <td>&nbsp;
		<html:hidden name="IsAddressListForm" property="actionType"/>
		</td>
    </tr>
</table>

<table  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="80%" align="center">
	<tr class="listcellrow">
		<td width="10%">&nbsp;</td>	
		<td width="10%">&nbsp;</td>
		<td width="50%">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
    <!--html:hidden property="vo.flag" value="0"/-->
	<tr class="listcellrow">
	    <td width="10%">&nbsp;</td>
		<td align="left" class="InputLabelCell">群组名称：</td>
		<td nowrap styleClass="input2" >
		<html:text styleClass="input2" property="vo.group_name"   validatetype="notempty" msg="群组名称不能为空！"  maxlength="10"/><font color="red">&nbsp;*</font>
		</td>
	</tr>
	<tr class="listcellrow">
	    <td width="10%">&nbsp;</td>
		<td align="left" class="InputLabelCell">成&nbsp;&nbsp;&nbsp;&nbsp;员：</td>
		<td>
		<html:hidden property="vo.member"/>		
		<textarea  name="namfield" class="input2" rows="6" cols="50%" readonly><bean:write name="IsAddressListForm" property="vo.memberNames" filter="true"/></textarea>
		<input type="hidden" name="idfiled" value="<bean:write name="IsAddressListForm" property="vo.member" filter="true"/>">
		</td><td valign="bottom">
		<html:button property="sel" styleClass="button"  onclick="javascript:openSelectWindow('menselect','idfiled','namfield');" value="选择人员"/>
		</td>
	</tr>
	<tr>
		<td colspan="5">&nbsp;</td>
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
		<goa:button property="save" value="保存" styleClass="button" operation="ZQSZ.OPT"  onclick="setMember();return doSave(IsAddressListForm)"/>
		<goa:button property="save" value="保存并返回" styleClass="button" operation="ZQSZ.OPT"  onclick="setMember();return doSaveExit(IsAddressListForm)"/>
        <goa:button property="exit" value="返回" styleClass="button" operation="ZQSZ.VIEW" onclick="doAction('goUrl(index)')"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>