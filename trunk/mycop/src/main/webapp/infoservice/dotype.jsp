<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>


<html>
<body class="bodybg">
<html:form action="/infoservice/dotype" method="post">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">信息类型编辑</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="SysInfoTypeForm" property="actionType"/></td>
    </tr>
</table>

<table align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td>
<table width="70%" align="center">
	<tr class="listcellrow">
		<td width="40%">&nbsp;</td>		
		<td width="60%">&nbsp;</td>
	</tr>
	<logic:empty name="SysInfoTypeForm" property="vo.type_code">
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类别代码：</td>
		<td nowrap>
		<html:text styleClass="input2" property="vo.type_code" size="22" onlytype="int" validatetype="notempty" msg="类别代码不能为空！"  maxlength="2"/><font color="red">&nbsp;*</font>
		</td>
	</tr>	
	</logic:empty>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类别名称：</td>
		<td nowrap>
		<html:text styleClass="input2" property="vo.type_name" size="22"  validatetype="notempty" msg="类别名称不能为空！"  maxlength="10"/><font color="red">&nbsp;*</font>
		</td>
	</tr>	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp;&nbsp;&nbsp;注：	</td>
		<td>
		<html:textarea property="vo.remark" rows="3" cols="40%" styleClass="input2" maxlength="30"/>
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
		<html:button property="save" value="保存" styleClass="button"  onclick="return doSave(SysInfoTypeForm)"/>
		<html:button property="save" value="保存并返回" styleClass="button"  onclick="return doSaveExit(SysInfoTypeForm)"/>
        <html:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>