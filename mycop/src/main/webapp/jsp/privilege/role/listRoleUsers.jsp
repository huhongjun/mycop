<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm.tld" prefix="perm" %>
<%@ page import="java.util.*"%>

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
function selectUser(){
	window.open("<gdp:context/>/privilege/roleAction.do?action=selectUser&rid=<%=request.getParameter("rid")%>","人员选择","width=310,height=350,status=no,resizable=no")
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
<input name="rid" type="hidden"  value="<%=request.getParameter("rid")%>">

  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" class="TableTitleText">角色分配用户列表(<bean:write name="role" property="name"/>)</td>
  </tr>
   <script>
       cur_roleid="<bean:write name="role" property="id"/>";
  </script>
  <html:form method="post" action="/roleAction.do">
  <tr align="right">
    <td colspan="2">
	<perm:button property="addbt" type="button" styleClass="button" onclick="selectUser()" value="选择用户" rescode="GDP-YHGL" optcode="ALL"/>
	<perm:button property="delbt" styleClass="button" onclick="return toAction(this,'deleteUserFromRole','请问确定要删除所选记录吗？','&roleId='+cur_roleid)" value="删除用户" rescode="GDP-YHGL" optcode="ALL"/>
	<input type="button"  class="button" name="back" value="返回" onclick="toAction(this,'list')">
<input name="rid" type="hidden"  value="<%=request.getParameter("rid")%>">
	 </td>
  </tr>
  <tr>
    <td colspan="2">

	<table width="100%" border="0" cellpadding="2" cellspacing="0" >
        <tr>
		  <td width="5%" class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
          <!--<td width="24"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox"></td>-->
          <!--<td class="Listtitle">编号</td>-->
          <td class="Listtitle">帐号</td>
          <td class="Listtitle">姓名</td>
          <!--<td class="Listtitle">性别</td>-->
		  <td class="Listtitle">用户类型</td>
		  <td class="Listtitle">激活状态</td>
		  <td class="Listtitle">有效期</td>
		  <!--td class="Listtitle">用户角色</td>
		  <td class="Listtitle">用户权限</td-->
        </tr>
       <logic:iterate id="user" name="pageControl" property="data" type="com.gever.sysman.privilege.model.I_User">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
	<td class="listcellrow2"> <input type="checkbox" name="id" value="<bean:write name="user" property="id"/>"></td>
          <!--<td  class="listcellrow2"> <input type="checkbox" name="id" value="<bean:write name="user" property="id"/>"&nbsp;</td>-->
		  <!--<td class="listcellrow2"><bean:write name="user" property="id"/>&nbsp;</td>-->
          <td class="listcellrow2"><bean:write name="user" property="userName"/>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="user" property="name"/>&nbsp;</td>
		  <!--<td class="listcellrow2"><bean:write name="user" property="status"/>&nbsp;</td>-->
		  <td class="listcellrow2"><logic:equal name="user" property="userType" value="0">管理员</logic:equal><logic:equal name="user" property="userType" value="1">普通用户</logic:equal><logic:equal name="user" property="userType" value="2">CA用户</logic:equal>&nbsp;</td>
		  <td class="listcellrow2"><logic:equal name="user" property="status" value="0">不激活</logic:equal><logic:equal name="user" property="status" value="1">已激活</logic:equal>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="user" property="validDate"/>&nbsp;</td>
		 
		  <!--td class="listcellrow2"><a href="<gdp:context/>/privilege/roleAction.do?action=listUserRoles&uid=<bean:write name="user" property="id"/>">用户角色</a></td>
		  <td class="listcellrow2"><a href="#">用户权限</a></td-->
	     </tr>
  	  </logic:iterate>
      </table>
	  </td>
  </tr>
  
</html:form>
  <tr>
  <td  colspan="2"  class="f12">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr >
	<td class="f12">
	<%
	String strPage = "/privilege/userAction.do?action=listRoleUsers&rid=" + request.getParameter("rid");
	%>
	<gmenu:allpager name="pageControl" page="<%=strPage%>"  usepic="true"/>
    </td>
	</tr>
  </table>
  </td>
  </tr>
  <tr>
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
<script language="javascript">//clearMenu();</script>
<%
}
%>