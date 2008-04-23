<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>


<html>
<body class="bodybg">
<html:form action="/infoservice/dotypeview" method="post">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">信息类型编辑</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="SysInfoTypeForm" property="actionType"/></td>
    </tr>
</table>

<table  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="95%" align="center">
	<tr class="listcellrow">
		<td width="15%">&nbsp;</td>		
		<td width="80%">&nbsp;</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">类别代码：</td>
		<td>
		<goa:write maxlen="0"  name="SysInfoTypeForm" property="vo.type_code" filter="true"/>
		</td>
	</tr>	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">类别名称：</td>
		<td>
		<goa:write maxlen="0"  name="SysInfoTypeForm" property="vo.type_name" filter="true"/>
		</td>
	</tr>	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
		<td>
		<goa:write maxlen="0"  name="SysInfoTypeForm" property="vo.remark" filter="true"/>
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
		<html:hidden name="SysInfoTypeForm" property="fid" />
		<html:button property="save" value="修改" styleClass="button"  onclick=" toUrl('<%=context%>/infoservice/dotype.do?actionType=modify',false)"/>
        <html:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>