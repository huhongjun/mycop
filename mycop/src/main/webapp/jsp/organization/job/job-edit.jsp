<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>岗位管理-修改</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<script src="<gdp:context/>/js/Validator.js"></script>

</head>

<body class="bodybg">
<script>
var ns = navigator.appName == "Netscape";
	try{
		document.body.onmousedown=function(){
            if(ns) top.getElementById("right").hideAllMenu();
            else top.frames["right"].hideAllMenu();
        }	
	}catch(e){
	//
	}
</script>
<table width="430" align="center">
 <tr> 
  <gmenu:getpathtag/>
  </tr>
 <html:form action="/JobAction">
 <html:hidden property="id"/>
<table width="430" align="center">
  <tr> 
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="4" align="center" class="TableTitleText">岗位修改</td>
  </tr>
  <tr> 
    <td colspan="4" >&nbsp;</td>
  </tr>
  <!--
  <tr> 
    <td nowrap class="InputLabelCell" >岗位ID:</td>
    <td colspan="3" ><html:hidden property="id"/></td>
  </tr>
  -->
  </tr>
   <tr> 
    <td nowrap class="InputLabelCell" >岗位名称:</td>
    <td colspan="3" ><html:text  styleClass="input2"  validatetype="notempty" msg="岗位名称不能为空"  property="name" size="30" maxlength="25" /><font color="#FF0000">*</font></td>
  </tr>
  <tr> 
    <td nowrap class="InputLabelCell" >简介:</td>
	 <td colspan="3" ><html:textarea  styleClass="input2" property="description" rows="5" cols="30" maxlength="50"></html:textarea>
  </tr>
 <tr> 
    <td >&nbsp;</td>
    <td colspan="3" > 
	  <html:button styleClass="button" property="create" value="确定" onclick="return (validater() && toAction(this,'update'))"/>&nbsp;
      <html:reset styleClass="button"  property="reset" value="重置" />&nbsp;
	  <html:button styleClass="button"  property="list" value="返回" onclick="toAction(this,'list')"/>
	  <input type="hidden" id="departid" name="departid" value="<%=request.getParameter("departid")%>">
	  <input type="hidden" name="page" value="<%=request.getParameter("page")%>">
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