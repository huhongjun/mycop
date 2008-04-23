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
<title>修改</title>
<tr> 
  <gmenu:getpathtag/>
  </tr>
<jsp:include page="/jsp/jsp_css.jsp" />
<script>
function doBack(){
    if(navigator.appName != "Netscape"){
        history.back();
        return;
    }
    document.location = "<gdp:context/>/privilege/levelAction.do?action=list";
}
</script>
</head>

<body class="bodybg">

<html:form method="post" action="/levelAction.do">
<table width="430" align="center">

  <tr> 
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="4" align="center" class="TableTitleText">修改层级</td>
  </tr>
  <tr> 
    <td height="20" colspan="4" >&nbsp;</td>
  </tr>
    <tr> 
    <td nowrap class="InputLabelCell" >层级编码:</td>
    <td > <html:text property="code" styleClass="input2" validatetype="notempty"  size="30" maxlength="10" /><font color="#FF0000">*</font></td>
  </tr> 
  <tr> 
    <td nowrap class="InputLabelCell" >层级名称:</td>
    <td > <html:text property="name" styleClass="input2" validatetype="notempty" msg="层级名称不能为空" size="30" maxlength="10" /><font color="#FF0000">*</font></td>
  </tr>  
  <tr> 
    <td nowrap class="InputLabelCell" >层级描述:</td>
    <td colspan="3" ><html:textarea  styleClass="input2" property="description" rows="4" cols="30" maxlength="50"></html:textarea> 
    </td>
  </tr>
   <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > <input name="Submit" type="submit" class="button" value="确定" onclick="return(validater(levelForm))"> 
      <input name="Submit22" type="reset" class="button" value="重置"> 
	  <input name="Submit23" type="button" class="button" onclick="javascirpt:doBack()" value="返回"> 
	  <html:hidden property="id" />
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
