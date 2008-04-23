<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page import="com.gever.sysman.util.OrderList" %>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>

<html>
<head>
<title></title>
<jsp:include page="/jsp/jsp_css.jsp" />
<style type="text/css">
<!--
-->
</style>
<script language="javascript">
function add()
{
  window.location="<gdp:context/>/jsp/privilege/role/role-new.jsp";
}
function edit()
{
  window.location="edit.htm";
}
function query()
{
  window.location="query.htm";
}
function del(obj)
{
  var res=confirm("是否真的要删除选择的记录？");
  if(res==true) {
   obj.form.action="<gdp:context/>/privilege/roleAction.do?action=delete";   	
   obj.form.submit();
   return true;
  }
  return false;
}
</script>
<script langauge="javascript">
function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#cccccc';
}

    //==============================================================================
    // 胡勇添加，增加列表排序功能
    var ns = navigator.appName == "Netscape";
<%  String key_value = OrderList.getInstance().role_key;
    String uri = "/privilege/roleAction.do";
%>  <%@ include file="../order_inc.inc" %>
    //==============================================================================
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
<form name="roleForm" METHOD=POST ACTION="">
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="TableTitleText">角色列表</td>
  </tr>
  <tr align="right"> 
    <td colspan="2">
	 <perm:button property="addbt" type="button" styleClass="button" onclick="add()" value="新增" rescode="GDP-JSGL" optcode="ALL"/>
	  <perm:button property="delbt" styleClass="button" onclick="return toAction(this,'delete','请问确定要删除所选记录吗？')" value="删除" rescode="GDP-YHGL" optcode="ALL"/>
	  	<!--<perm:button property="searbt" styleClass="button" onclick="query()" value="查询" rescode="GDP-YHGL" optcode="VIEW"/>-->

      <!--<input name="delbt" type="submit" class="button" onclick="return del(this)" value="删除">       
      <input name="searbt" type="button" class="button"  onclick="query()"value="查询"> -->
      </td>      
  </tr>
  <tr> 
    <td colspan="2"> <table width="100%" border="0" cellpadding="2" cellspacing="0" >
        <tr> 
          <td width="24"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
         <!-- <td width="15%" class="Listtitle">编号</td>-->
          <td width="25%" id="<%=mapList[0]%>" class="Listtitle" onclick="order('<%=mapList[0]%>',event)">名称</td>
          <td width="40%" id="<%=mapList[1]%>" class="Listtitle" onclick="order('<%=mapList[1]%>',event)">描述</td>
		  <td width="10%" class="Listtitle">权限</td>
		  <td width="10%" class="Listtitle">修改</td>
        </tr>		
       <logic:iterate id="role" name="pageControl" property="data" type="com.gever.sysman.privilege.model.Role">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);"> 
          <td  class="listcellrow2"> <input type="checkbox" name="id" value="<bean:write name="role" property="id"/>"></td>
         <!-- <td class="listcellrow2"><bean:write name="role" property="id"/></td>-->
		  <td class="listcellrow2"> <a href="<gdp:context/>/privilege/userAction.do?action=listRoleUsers&rid=<bean:write name="role" property="id"/>"><bean:write name="role" property="name"/></a></td>
		  <td class="listcellrow2"><bean:write name="role" property="description"/>&nbsp;</td>
          <td class="listcellrow2">
		  <%
		  String href3="/privilege/permissionAction.do?action=role_perm_table"+"&roleid="+role.getId();
		  %>
		  <perm:image href="<%=href3%>" src="/images/edit.gif"  rescode="GDP-YHGL" optcode="ALL" />
		 
		 <!-- <a href="<%=request.getContextPath()%>/privilege/permissionAction.do?action=role_perm_table&roleid=<bean:write name="role" property="id"/>"/><img src="<%=request.getContextPath()%>/images/edit.gif" width="16" height="16" border="0"></a>-->&nbsp;</td>
		  <td class="listcellrow2">
		  <%
		  String href="/privilege/roleAction.do?action=edit"+"&id="+role.getId();
		  %>
		  <perm:image href="<%=href%>" src="/images/properties.gif"  rescode="GDP-YHGL" optcode="ALL" />

		  <!--<html:link action="/roleAction.do?action=edit" paramId="id" paramName="role" paramProperty="id" ><img src="<%=request.getContextPath()%>/images/properties.gif" width="16" height="16" border="0"></html:link>-->&nbsp;</td>
        </tr>
  	  </logic:iterate>		  
      </table>
	  </form>
	  </td>
  </tr>
  <tr> 
  <td  colspan="2" align="right" class="f12"> 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr align="center"> 
	<td class="f12">
	<gmenu:allpager name="pageControl" page="/privilege/roleAction.do?action=list"  usepic="true"/>
    </td>
	</tr>
  </table>
  </td>
  </tr>
  <tr> 
    <td colspan="2" class="f12">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="f12">&nbsp;</td>
  </tr>
  <tr align="center"> 
    <td height="19" colspan="2" class="f12">&nbsp;</td>
  </tr>
</table>
<script>
//==============================================================================
// 胡勇添加，切换图片显示
if((event_id != null) && (event_id != "null")){
    swapImages();
} else {
    if(orderby != "null"){
        event_id = orderby;
        swapImages();
    }
}
//==============================================================================
</script>
</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>




