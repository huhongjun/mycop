<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<script src="<gdp:context/>/js/Validator.js"></script>
<script>
function doBack(){
    if(navigator.appName == "Microsoft Internet Explorer"){
        history.back();
        return;
    }
    var moduleform = document.forms[0];
    var action="";
    action += "<gdp:context/>/privilege/roleAction.do?action=list";

    moduleform.action = action;
    moduleform.submit();
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>����</title>
<jsp:include page="/jsp/jsp_css.jsp" />
</head>

<body class="bodybg" onload="JavaScript:document.forms[0].name.focus();">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<form method="post" name="roleForm" action="<gdp:context/>/privilege/roleAction.do">
<table width="430" align="center">
  <tr> 
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="4" align="center" class="TableTitleText">�����û���ɫ</td>
  </tr>
  <tr> 
    <td height="20" colspan="4" >&nbsp;</td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >����:</td>
    <td > <input name="name" type="text" size="30" maxlength="10" class="input2"  ValidateType="notempty" Msg="��ɫ���Ʋ���Ϊ��"><font color="#FF0000">*</font> </td>
  </tr>  
  <tr> 
    <td nowrap class="InputLabelCell" >����:</td>
    <td colspan="3" >
    <textarea name="description" rows="4" cols="30" class="input2"  maxlength="50" onfocus="javascript:setMaxLen(event)"></textarea> 
    </td>
  </tr>
   <tr> 
    <td colspan="4" >&nbsp;</td> 
  </tr>
  <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > <input name="Submit" type="submit" class="button" value="ȷ��" onclick="return(validater(roleForm))"> 
      <input name="Submit22" type="reset" class="button" value="����"> 
	  <input name="Submit23" type="button" class="button" onclick="javascript:doBack()" value="����"> 
	  <input name="action" type="hidden" value="addUserRole">
    </td>
  </tr>
</table>
</form>
</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
