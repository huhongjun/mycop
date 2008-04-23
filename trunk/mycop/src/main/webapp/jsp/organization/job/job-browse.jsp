<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>工作日志-新增</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
</head>

<body class="bodybg">
<script>
	try{
		document.body.onmousedown=function(){top.frames["right"].hideAllMenu();}
	}catch(e){
	//
	}
</script>
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<table width="75%" align="center" cellpadding="10" cellspacing="1">
  <tr align="center">
    <td bgcolor="#FFFFFF" class="TableTitleText">岗位详细资料</td>
  </tr>

  <tr align="center">
    <td bgcolor="#FFFFFF" >
      <table align="center" class="f12">
          <!--<tr>
			<td nowrap class="InputLabelCell" >岗位ID:</td>
			<td><bean:write name="JobForm" property="id"/></td>
		</tr>-->
		 <tr>
			<td nowrap class="InputLabelCell" >岗位名称:</td>
			<td><bean:write name="JobForm" property="name"/></td>
		</tr>
		<tr>
			<td nowrap class="InputLabelCell" >简介:</td>
			<td colspan="3" ><bean:write name="JobForm" property="description"/>
		</tr>
		<!--<tr>
			<td nowrap class="InputLabelCell" >所属组织ID:</td>
			<td colspan="3" ><bean:write name="JobForm" property="department"/>
		</tr>-->
		<tr>
			<td nowrap class="InputLabelCell" >所属组织名称:</td>
			<td colspan="3" >
		    <logic:equal name="JobForm" property="departmentName" value="null">
		    公司
	            </logic:equal>
		    <logic:notEqual name="JobForm" property="departmentName" value="null">
		    <bean:write name="JobForm" property="departmentName"/>
	            </logic:notEqual>

		</tr>
      </table>
	  </td>
  </tr>

  <tr align="center">
    <td >
	<html:form action="/JobAction" >
     <html:submit property="list" styleClass="button" value="返回" onclick="toAction(this,'list')"/>
	 <input type="hidden" name="departid" value="<%=request.getParameter("departid")%>">
	<input type="hidden" name="page" value="<%=request.getParameter("page")%>">
    </html:form>
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
