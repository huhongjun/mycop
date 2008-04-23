<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户-新增</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<script language="javascript" src="<gdp:context/>/js/pub.js"></script>
</head>

<body class="bodybg" onload="JavaScript:document.forms[0].userName.focus();">
<script>
	try{
		document.body.onmousedown=function(){top.frames["right"].hideAllMenu();}	
	}catch(e){
	//
	}
</script>
<html:form action="/EmployeeAction">
<table width="480" border="0" align="center" cellpadding="2" cellspacing="1">
  <tr align="center"> 
    <td colspan="4" class="TableTitleText">新增用户</td>
  </tr>
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >用户名称:</td>
    <td colspan="3" ><html:text property="name" size="50" maxlength="25" /></td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >用户帐户名:</td>
    <td colspan="3" ><html:text property="userName" size="30" maxlength="30" /></td>
  </tr>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >用户密码:</td>
    <td colspan="3" ><html:password property="password" size="30" maxlength="15" /></td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >用户确认密码:</td>
    <td colspan="3" ><html:password property="repassword" size="30" maxlength="15" /></td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户编码:</td>
    <td colspan="3" ><html:text property="code" size="30" maxlength="15" /></td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户类型:</td>
    <td colspan="3" >
	<html:select property="userType" value="1">
	  <html:option  value="0">管理员</html:option>
	  <html:option  value="1">普通用户</html:option>
	</html:select></td>
    </td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >用户性别:</td>
    <td colspan="3" >
	<html:select property="gender" value="0">
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
	<html:select property="status" value="0" >
	  <html:option  value="1">激活</html:option>
	  <html:option  value="0">不激活</html:option>
	</html:select>
	</td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >用户有效期:</td>
    <td colspan="3" ><html:text property="validDate" size="30" maxlength="15" readonly="true" onclick="getdate(this,'gdp/js/')"/></td>
    </td>
  </tr>

  <tr> 
    <td colspan="4" >&nbsp;
	<html:hidden property="level" />
	</td>
  </tr>
  <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > <html:button property="create" value="确定" onclick="toAction(this,'create')"/>&nbsp;
      <html:reset property="reset" value="重置" />&nbsp;
	  <html:button property="list" value="返回" onclick="toAction(this,'list')"/>
    </td>
  </tr>
</table>
</html:form>
</body>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
