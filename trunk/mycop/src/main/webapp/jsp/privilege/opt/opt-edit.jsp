<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<script src="<gdp:context/>/js/Validator.js"></script>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�޸�</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script>
function doBack(){
    if(navigator.appName == "Microsoft Internet Explorer"){
        history.back();
        return;
    }
    var moduleform = document.forms[0];
    var action="";
    action += "<gdp:context/>/privilege/operationAction.do?action=list";

    moduleform.action = action;
    moduleform.submit();
}
</script>
</head>

<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<html:form method="post" action="/operationAction.do">
<table width="430" align="center">
  <tr> 
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="4" align="center" class="TableTitleText">�޸���Դ����</td>
  </tr>
  <tr> 
    <td height="20" colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >��Դ����:</td>
    <td > <html:text property="code" validatetype="notempty" msg="�������벻��Ϊ��" styleClass="input2" size="30" maxlength="50" /><font color="#FF0000">*</font></td> 
  </tr>  
  <tr> 
    <td nowrap class="InputLabelCell" >��������:</td>
    <td > <html:text property="name" validatetype="notempty" msg="�������Ʋ���Ϊ��" styleClass="input2" size="30" maxlength="25" /><font color="#FF0000">*</font></td>
  </tr>  
  <tr> 
    <td nowrap class="InputLabelCell" >��������:</td>
    <td colspan="3" >
    <html:textarea  styleClass="input2" property="description" rows="5" cols="30" maxlength="50"></html:textarea>
    </td>
  </tr>
  <html:hidden property="Resource_ID" />
  <!--
   <tr> 
    <td nowrap class="InputLabelCell" >��Դ:</td>
    <td colspan="2" ><html:text styleClass="input2" property="Resource_ID" />
    </td>	
  </tr>
  -->
   <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > 

	<html:submit styleClass="button" property="Submit"  value="ȷ��" onclick="return(validater(OperationForm))" />
	  <input name="Submit22" type="reset" class="button" value="����"> 
	  <input name="Submit23" type="button" class="button" onclick="javascript:doBack()" value="����"> 
	  <html:hidden property="id" />
	  <input name="resid" type="hidden" value="<%=request.getParameter("resid")%>"> 
	  <input name="action" type="hidden" value="update">
    </td>
  </tr>
</table>
</html:form>
</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>

