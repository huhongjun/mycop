<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<script src="<gdp:context/>/js/Validator.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>����</title>
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

<body class="bodybg" onload="JavaScript:document.forms[0].code.focus();">
<gmenu:getpathtag/>

<form method="post" name="OperationForm" action="<gdp:context/>/privilege/operationAction.do">
<table width="430" align="center">
  <tr> 
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="4" align="center" class="TableTitleText">������Դ����</td>
  </tr>
  <tr> 
    <td height="20" colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >��������:</td>
    <td > <input name="code" type="text" ValidateType="notempty" Msg="�������벻��Ϊ��" maxlength="50" size="30" class="input2"><font color="#FF0000">*</font></td>
  </tr>  
  <tr> 
    <td nowrap class="InputLabelCell" >��������:</td>
    <td > <input name="name" type="text" ValidateType="notempty" Msg="�������Ʋ���Ϊ��" maxlength="25" size="30" class="input2"><font color="#FF0000">*</font> </td>
  </tr>  
  <tr> 
    <td nowrap class="InputLabelCell" >��������:</td>
    <td colspan="3" >
    <textarea name="description" rows="4" cols="30" class="input2"  maxlength="50" onfocus="javascript:setMaxLen(event)"></textarea> 
    </td>
  </tr>
   <input name="Resource_ID" type="hidden" value="<%=request.getParameter("resid")%>">  
   <input name="resid" type="hidden" value="<%=request.getParameter("resid")%>"> 
   <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > <input name="Submit" type="submit" onclick="return(validater(OperationForm))" class="button" value="ȷ��"> 
      <input name="Submit22" type="reset" class="button" value="����"> 
	  <input name="Submit23" type="button" class="button" onclick="javascript:doBack()" value="����"> 
	  <input name="action" type="hidden" value="create">
    </td>
  </tr>
</table>
</form>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
//-->
</SCRIPT>