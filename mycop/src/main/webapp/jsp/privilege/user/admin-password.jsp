<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�û�-�޸�</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
</head>

<body class="bodybg">

<table width="430" align="center">
  <html:form action="/userAction.do?action=setPassword2" onsubmit="return equals('pass','repass','password','�������벻��ͬ������������')">
<table width="480" border="0" align="center" cellpadding="2" cellspacing="1">
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
    <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr align="center"> 
    <td colspan="4" class="TableTitleText">���������޸�</td>
  </tr>
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
 
  <tr > 
    <td nowrap class="InputLabelCell" >������:</td>
    <td colspan="3" ><input type="password" name="pass" class="input2" size="30" maxlength="15" ></td>
    </td>
  </tr>
  <tr > 
    <td nowrap class="InputLabelCell" >ȷ��������:</td>
    <td colspan="3" ><input type="password" name="repass" class="input2"  size="30" maxlength="15" ></td>
    </td>
 
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr > 
    <td >&nbsp;</td>
    <td colspan="3" >
      <html:submit  property="update" value="ȷ��" styleClass="button"/>
	  <html:button  property="close" value="�ر�" styleClass="button" onclick="window.parent.close()"/>
	  <html:hidden name="UserForm" property="id"/>
	  <html:hidden property="password" />
    </td>
  </tr>
</table>
</html:form>
</table>

</body>
</html>

