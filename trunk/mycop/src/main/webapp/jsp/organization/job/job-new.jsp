<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>岗位管理-新增</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script src="<gdp:context/>/js/gdp.js"></script>
<script src="<gdp:context/>/js/Validator.js"></script>

</head>

<body class="bodybg" onload="JavaScript:document.forms[0].name.focus();">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<script>
	try{
		document.body.onmousedown=function(){top.frames["right"].hideAllMenu();}	
	}catch(e){
	//
	}
</script>
<html:form action="/JobAction">
<table width="430" align="center">
  <tr> 
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr align="center"> 
    <td colspan="4" class="TableTitleText">新增岗位</td>
  </tr>
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >岗位名称:</td>
    <td colspan="3" ><html:text validatetype="notempty" msg="岗位名称不能为空" styleClass="input2"  property="name" size="30" maxlength="15" /><font color="#FF0000">*</font></td>
  </tr>

  <tr> 
    <td nowrap class="InputLabelCell" >简介:</td>
	 <td colspan="3" >
     <html:textarea  styleClass="input2" property="description" rows="5" cols="30" maxlength="50"></html:textarea>
    
  </tr>
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr> 
    <td >&nbsp;</td>
   <td colspan="3" > <html:button styleClass="button"  property="create" value="确定" onclick="return (validater() && toAction(this,'create'))"/>&nbsp;
      <html:reset styleClass="button"  property="reset" value="重置" />&nbsp;
	  <html:button styleClass="button"  property="list" value="返回" onclick="toAction(this,'list')"/>
	  <input type="hidden" name="departid" value="<%=request.getParameter("departid")%>" >
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
