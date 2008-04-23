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
<title>新增</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="Javascript" src="<gdp:context/>/js/privilege.js"></script>
<script language="Javascript">
function copy(target)
{
    text = document.all('tempDescription').value;
    value = document.all('tempValue').value;
     document.all(target).value=value;
}
function selectAll()
{
    var obj = document.all("parentid");
    for (i = 0; i < obj.length; i++)
    { 
        obj.options[i].selected = true;
    }
}
function doBack(){
    if(navigator.appName == "Microsoft Internet Explorer"){
        history.back();
        return;
    }
    var moduleform = document.forms[0];
    var action="<gdp:context/>/privilege/resourceAction.do?action=getListChild&resid=1";

    moduleform.action = action;
    moduleform.submit();
}
</script>
</head>

<body class="bodybg" onload="JavaScript:document.forms[0].code.focus();">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<html:form method="post" action="/resourceAction.do" onsubmit="selectAll();">
<input type="hidden" name="tempValue" id="tempValue" >
<input type="hidden" name="tempDescription" id="tempDescription" >
<table width="430" align="center">
  <tr> 
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="4" align="center" class="TableTitleText">增加资源</td>
  </tr>
  <tr> 
    <td height="20" colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
      <td nowrap class="InputLabelCell" >资源编码:</td>
    <td > 
	<html:text property="code"  size="30" validatetype="notempty" msg="资源编码不能为空" styleClass="input2"    maxlength="50"  value="" /><font color="#FF0000">*</font>
	 </td>
  </tr>
  <tr> 
      <td nowrap class="InputLabelCell" >资源名称:</td>
    <td >
	<html:text property="name"  size="30" validatetype="notempty" msg="资源名称不能为空" styleClass="input2"  maxlength="25" value=""  /><font color="#FF0000">*</font>
	 </td>
  </tr>  
   <tr> 
      <td nowrap class="InputLabelCell" >资源连接:</td>
    <td > 
	<html:text property="link" size="30" maxlength="200" styleClass="input2" value=""/> </td>
  </tr>  
  <tr> 
     <td nowrap class="InputLabelCell" >资源描述:</td>
    <td colspan="3" >
	<html:textarea property="description" rows="4" cols="30" maxlength="50" styleClass="input2"/>
    </td>
  </tr> 
    <tr> 
      <td nowrap class="InputLabelCell" >资源类型:</td>
    <td ><select name="resourceType" id="resourceType">
          <option value="0" selected>资源</option>
          <option value="1">资源包</option>
        </select></td>
  </tr> 
    <tr> 
      <td nowrap class="InputLabelCell" >显示方式:</td>
    <td><select name="target" id="target">
          <option value="0" selected>blank</option>
          <option value="1">self</option>
          <option value="2">parent</option>
          <option value="3">main</option>
        </select></td>
  </tr>  
   <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > 
	<html:submit styleClass="button" property="Submit"  value="确定" onclick="return(validater(resourceForm))" />
      <input name="Submit22" type="reset" class="button" value="重置">
	  <input name="Submit23" type="button" class="button" onclick="javascript:doBack()" value="返回"> 
	  <input type="hidden"  id="resid" name="resid" value="<%=request.getParameter("resid")%>">
	  <input name="parentid" id="parentid" type="hidden" value="<%=request.getParameter("resid")%>">
	  <input name="action" type="hidden" value="addResource">
    </td>
  </tr>
</table>
</html:form>
<SCRIPT LANGUAGE="JavaScript">
<!--
 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
//-->
</SCRIPT>
</body>
</html>
