<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�û�-����</title>
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
    <td colspan="4" class="TableTitleText">�����û�</td>
  </tr>
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >�û�����:</td>
    <td colspan="3" ><html:text property="name" size="50" maxlength="25" /></td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >�û��ʻ���:</td>
    <td colspan="3" ><html:text property="userName" size="30" maxlength="30" /></td>
  </tr>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >�û�����:</td>
    <td colspan="3" ><html:password property="password" size="30" maxlength="15" /></td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >�û�ȷ������:</td>
    <td colspan="3" ><html:password property="repassword" size="30" maxlength="15" /></td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û�����:</td>
    <td colspan="3" ><html:text property="code" size="30" maxlength="15" /></td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û�����:</td>
    <td colspan="3" >
	<html:select property="userType" value="1">
	  <html:option  value="0">����Ա</html:option>
	  <html:option  value="1">��ͨ�û�</html:option>
	</html:select></td>
    </td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >�û��Ա�:</td>
    <td colspan="3" >
	<html:select property="gender" value="0">
	  <html:option  value="0">δ֪</html:option>
	  <html:option  value="1">��</html:option>
	  <html:option  value="2">Ů</html:option>
	</html:select>
	</td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û�����״̬:</td>
    <td colspan="3" >
	<html:select property="status" value="0" >
	  <html:option  value="1">����</html:option>
	  <html:option  value="0">������</html:option>
	</html:select>
	</td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û���Ч��:</td>
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
    <td colspan="3" > <html:button property="create" value="ȷ��" onclick="toAction(this,'create')"/>&nbsp;
      <html:reset property="reset" value="����" />&nbsp;
	  <html:button property="list" value="����" onclick="toAction(this,'list')"/>
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
