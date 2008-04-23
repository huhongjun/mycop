<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<script src="<gdp:context/>/js/Validator.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>新增</title>
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
    <td colspan="4" align="center" class="TableTitleText">增加资源操作</td>
  </tr>
  <tr> 
    <td height="20" colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >操作编码:</td>
    <td > <input name="code" type="text" ValidateType="notempty" Msg="操作编码不能为空" maxlength="50" size="30" class="input2"><font color="#FF0000">*</font></td>
  </tr>  
  <tr> 
    <td nowrap class="InputLabelCell" >操作名称:</td>
    <td > <input name="name" type="text" ValidateType="notempty" Msg="操作名称不能为空" maxlength="25" size="30" class="input2"><font color="#FF0000">*</font> </td>
  </tr>  
  <tr> 
    <td nowrap class="InputLabelCell" >操作描述:</td>
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
    <td colspan="3" > <input name="Submit" type="submit" onclick="return(validater(OperationForm))" class="button" value="确定"> 
      <input name="Submit22" type="reset" class="button" value="重置"> 
	  <input name="Submit23" type="button" class="button" onclick="javascript:doBack()" value="返回"> 
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