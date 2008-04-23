<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>


<html>
<body class="bodybg">
<html:form action="/infoservice/lawdotypeview" method="post">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">类别浏览</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsDoTypesForm" property="actionType"/></td>
    </tr>
</table>

<table  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="80%" align="center">
	<tr class="listcellrow">
		<td width="20%">&nbsp;</td>		
		<td width="60%">&nbsp;</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">类别名称：</td>
		<td>
		<goa:write maxlen="0"  name="IsDoTypesForm" property="vo.info_type" filter="true"/>
		</td>
	</tr>	
	<tr class="listcellrow">
	<logic:notEqual name="IsDoTypesForm" property="vo.parent_type" value="">
		<td align="left" class="InputLabelCell">父类名称：</td>
		<td>
		<goa:write maxlen="0"  name="IsDoTypesForm" property="vo.parent_type" filter="true"/>
		</td>
		</logic:notEqual>
	</tr>	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">类别级别：</td>
		<td>
		<goa:write name="IsDoTypesForm" property="vo.typelevel" filter="true"/>
		</td>		
	</tr>
	<tr class="listcellrow">
	    <td align="left" class="InputLabelCell">所属模块：</td>
		<td>
		<logic:equal name="IsDoTypesForm" property="vo.moduleflag" value="0">
		资源共享
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="vo.moduleflag" value="1">
		政策法规
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="vo.moduleflag" value="2">
		公司动态
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="vo.moduleflag" value="3">
		部门信息
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="vo.moduleflag" value="4">
		报告管理
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="vo.moduleflag" value="5">
		行业新闻
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="vo.moduleflag" value="6">
		管理制度
		</logic:equal>
		<logic:equal name="IsDoTypesForm" property="vo.moduleflag" value="7">
		文档资料
		</logic:equal>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
		<td>
		<goa:write maxlen="0"  name="IsDoTypesForm" property="vo.remark" filter="true"/>
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
		<html:hidden name="IsDoTypesForm" property="fid" />
		<%String clk="toUrl('"+context+"/infoservice/lawdotype.do?actionType=modify',false)";%>
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=clk%>" operation="LSSZ.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="LSSZ.VIEW"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>