<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�û�-�޸�</title>
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
    <td colspan="4" class="TableTitleText">�޸��û�</td>
  </tr>
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >�û�ID:</td>
    <td colspan="3" ><html:text property="id" size="50" maxlength="25" readonly="true" /></td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >�û�����:</td>
    <td colspan="3" ><html:text property="name" size="50" maxlength="25" /></td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û��ʻ���:</td>
    <td colspan="3" ><html:text property="userName" size="30" maxlength="30" /></td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û�����:</td>
    <td colspan="3" ><html:text property="code" size="30" maxlength="15" /></td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û�����:</td>
    <td colspan="3" >
      <html:select property="userType" >
	  <html:option  value="0">����Ա</html:option>
	  <html:option  value="1">��ͨ�û�</html:option>
	   </html:select></td>
	</td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û��Ա�:</td>
    <td colspan="3" >
	<html:select property="gender" >
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
	<html:select property="status" >
	  <html:option  value="1">����</html:option>
	  <html:option  value="0">������</html:option>
	</html:select>
	</td>
    </td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >�û���Ч��:</td>
    <td colspan="3" ><html:text property="validDate" size="30" maxlength="15" readonly="true" onclick="getdate(this,'<gdp:context/>/js')"/></td>
    </td>
  </tr>

  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > <html:button property="create" value="ȷ��" onclick="toAction(this,'update')"/>&nbsp;
      <html:reset property="reset" value="����" />&nbsp;
	  <html:button property="list" value="����" onclick="toAction(this,'list')"/>
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
