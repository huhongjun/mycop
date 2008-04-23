<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>




<html>
<head>
<title></title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<style type="text/css">
<!--
-->
</style>
<script langauge="javascript">
function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#cccccc';
}
</script>
</head>
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr> 
<gmenu:getpathtag/>
    <!--<td>系统管理/角色列表</td>-->
  </tr>
</table>
<table width="95%" align="center">
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="TableTitleText">用户角色列表(当前用户--<bean:write name="user" property="userName"/>)</td>
  </tr>
  <html:form method="post" action="/userAction.do">
  <tr align="right"> 
    <td colspan="2"> 
	<input property="list" type="button" class="button" value="返回" onclick="toAction(this,'list')"/>
	<perm:button property="addbt" type="button" styleClass="button" onclick="toAction(this,'selectRoles')" value="选择角色" rescode="GDP-YHGL" optcode="ALL"/>

	<!--<input name="addbt" type="button" class="button" onclick="toAction(this,'selectRoles')" value="选择角色">-->

	<input name="uid" type="hidden"  value="<%=request.getParameter("uid")%>">
     </td>
  </tr>
  <tr> 
    <td colspan="2"> 

	<table width="100%" border="0" cellpadding="2" cellspacing="0">
        <tr> 
          <!--<td width="24"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
          <td class="Listtitle">编号</td>-->
          <td class="Listtitle">角色名称</td>
          <td class="Listtitle">描述</td>
		  <!-- <td class="Listtitle">角色类型</td>-->
        </tr>		
       <logic:iterate id="user_roles" name="pageControl" property="data" type="com.gever.sysman.privilege.model.Role">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);"> 
          <!--<td  class="listcellrow2"> <input type="checkbox" name="id" value="<bean:write name="user_roles" property="id"/>"></td>
		  <td class="listcellrow2"><bean:write name="user_roles" property="id"/>&nbsp;</td>-->
          <td class="listcellrow2"><bean:write name="user_roles" property="name"/>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="user_roles" property="description"/>&nbsp;</td>
		  <!--<td class="listcellrow2"><bean:write name="user_roles" property="roleType"/>&nbsp;</td>-->	
		</tr>
  	  </logic:iterate>	   
      </table>	 
	  </td>
  </tr>

  <tr> 
    <td  colspan="2" class="f12"></td>
  </tr>
  </html:form>
  <tr> 
  <td  colspan="2" class="f12"> 
  <%
  String strPage = "/privilege/userAction.do?action=listUserRoles&uid=" + request.getParameter("uid");
  %>
  <gmenu:allpager name="pageControl" page="<%=strPage%>"  usepic="true"/>
  </td>
  </tr>
  <tr> 
    <td colspan="2" class="f12">&nbsp;</td>
  </tr>
  <tr > 
    <td height="19" colspan="2" class="f12">&nbsp;</td>
  </tr>
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
