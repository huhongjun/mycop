<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户-修改</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<script language="javascript" src="<gdp:context/>/js/pub.js"></script>
</head>

<body class="bodybg">
<script>
	try{
		document.body.onmousedown=function(){top.frames["right"].hideAllMenu();}	
	}catch(e){
	//
	}
</script>
<table width="430" align="center">
  <html:form action="/EmployeeAction">
<table width="480" border="0" align="center" cellpadding="2" cellspacing="1">
  <tr align="center"> 
    <td colspan="4" class="TableTitleText">修改用户</td>
  </tr>
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >用户ID:</td>
    <td colspan="3" ><html:text property="id" size="50" maxlength="25" readonly="true" /></td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >用户名称:</td>
    <td colspan="3" ><html:text property="name" size="50" maxlength="25" /></td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户帐户名:</td>
    <td colspan="3" ><html:text property="userName" size="30" maxlength="30" /></td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户编码:</td>
    <td colspan="3" ><html:text property="code" size="30" maxlength="15" /></td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户类型:</td>
    <td colspan="3" >
      <html:select property="userType" >
	  <html:option  value="0">管理员</html:option>
	  <html:option  value="1">普通用户</html:option>
	   </html:select></td>
	</td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户性别:</td>
    <td colspan="3" >
	<html:select property="gender" >
	  <html:option  value="0">未知</html:option>
	  <html:option  value="1">男</html:option>
	  <html:option  value="2">女</html:option>
	</html:select>
	</td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户激活状态:</td>
    <td colspan="3" >
	<html:select property="status" >
	  <html:option  value="1">激活</html:option>
	  <html:option  value="0">不激活</html:option>
	</html:select>
	</td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户有效期:</td>
    <td colspan="3" ><html:text property="validDate" size="30" maxlength="15" readonly="true" onclick="getdate(this,'<gdp:context/>/js')"/></td>
    </td>
  </tr>

  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > <html:button property="create" value="确定" onclick="toAction(this,'update')"/>&nbsp;
      <html:reset property="reset" value="重置" />&nbsp;
	  <html:button property="list" value="返回" onclick="toAction(this,'list')"/>
    </td>
  </tr>
</table>
</html:form>
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
