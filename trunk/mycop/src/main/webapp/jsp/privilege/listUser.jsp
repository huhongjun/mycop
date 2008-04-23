<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<%@ page import="java.util.*,com.gever.struts.pager.PageControl,com.gever.sysman.util.OrderList"%>
<%Map map=(Map)session.getAttribute("map");%>
<html>
<head>
<title></title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
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

    //==============================================================================
    // 胡勇添加，增加列表排序功能
    var ns = navigator.appName == "Netscape";
<%  String key_value = OrderList.getInstance().user_key;
    String uri = "/privilege/userAction.do";
%>  <%@ include file="../order_inc.inc" %>
    //==============================================================================
</script>
</head>
<body class="bodybg" onload="document.forms[0].keyword.focus();">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
    <!--<td>系统管理/用户列表</td>-->
  </tr>
</table>
<table width="95%" align="center">
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" class="TableTitleText">用户列表</td>
  </tr>
  <html:form method="post" action="/userAction.do" onsubmit="return handleAction(this);">
  <tr align="right">
    <td colspan="2" class="listcellrow2">帐号或姓名： 
        <html:text property="keyword" size="10" styleClass="input2"/>
	<perm:button property="querybt" styleClass="button" 
    onclick="if(document.forms[0].keyword.value==''){ alert('关键字不能为空！'); return false; } else { toAction(this,'search'); }" 
    value="搜索" rescode="GDP-YHGL" optcode="VIEW" />
	<perm:button property="addbt" styleClass="button" onclick="toAction(this,'toCreate')" value="新增" rescode="GDP-YHGL" optcode="ALL" />
	<perm:button property="delbt" styleClass="button" onclick="return toAction(this,'delete','请问确定要删除所选记录吗？')" value="删除" rescode="GDP-YHGL" optcode="ALL"/>
	<perm:button property="searbt" styleClass="button" onclick="toAction(this, 'toQuery')" value="查询" rescode="GDP-YHGL" optcode="VIEW"/>
   </td>
  </tr>
  <tr>
    <td colspan="2">
	<table width="100%" border="0" cellpadding="2" cellspacing="0" >
        <tr>
          <td width="5%" class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
          <!--<td width="10%" class="Listtitle">编码</td>-->
          <td width="10%" id="<%=mapList[0]%>" class="Listtitle" onclick="order('<%=mapList[0]%>',event)">帐号</td>
          <td width="10%" id="<%=mapList[1]%>" class="Listtitle" onclick="order('<%=mapList[1]%>',event)">姓名</td>
          <!--<td width="5%" class="Listtitle">性别</td>-->
		  <td width="10%" id="<%=mapList[2]%>" class="Listtitle" onclick="order('<%=mapList[2]%>',event)">用户类型</td>
          <td width="10%" id="<%=mapList[3]%>" class="Listtitle" onclick="order('<%=mapList[3]%>',event)">行政级别</td>
		  <td width="10%" id="<%=mapList[4]%>" class="Listtitle" onclick="order('<%=mapList[4]%>',event)">激活状态</td>
		  <td width="10%" id="<%=mapList[5]%>" class="Listtitle" onclick="order('<%=mapList[5]%>',event)">有效期</td>
		  <td width="10%" class="Listtitle">用户权限</td>
		  <td width="10%" class="Listtitle">修改</td>
		  <td width="10%" class="Listtitle">初始化密码</td>
        </tr>
       <logic:iterate id="user" name="pageControl" property="data" type="com.gever.sysman.privilege.model.I_User">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
         <td class="listcellrow2"> <input type="checkbox" name="id" value="<bean:write name="user" property="id"/>"></td>
		  <!--<td class="listcellrow2"><bean:write name="user" property="code"/>&nbsp;</td>-->
          <td class="listcellrow2"><bean:write name="user" property="userName"/></td>
		  <td class="listcellrow2"><a href="<%=request.getContextPath()%>/privilege/userAction.do?action=listUserRoles&uid=<bean:write name="user" property="id"/>"><bean:write name="user" property="name"/></a></td>
		  <!--<td class="listcellrow2"><logic:equal name="user" property="gender" value="1">男</logic:equal><logic:equal name="user" property="gender" value="2">女</logic:equal>&nbsp;</td>-->
		  <td class="listcellrow2"><logic:equal name="user" property="userType" value="0">管理员</logic:equal><logic:equal name="user" property="userType" value="1">普通用户</logic:equal><logic:equal name="user" property="userType" value="2">CA用户</logic:equal><logic:equal name="user" property="userType" value="CA用户">CA用户</logic:equal><logic:equal name="user" property="userType" value="普通用户">普通用户</logic:equal>&nbsp;</td>
          <td class="listcellrow2"><%if((user.getLevel()!=null) && !user.getLevel().equals("")) out.print(map.get(new Long(user.getLevel())));%>&nbsp;</td>
		   <td class="listcellrow2"><logic:equal name="user" property="status" value="0">不激活</logic:equal><logic:equal name="user" property="status" value="1">已激活</logic:equal><logic:equal name="user" property="status" value="激活">已激活</logic:equal><logic:equal name="user" property="status" value="不激活">不激活</logic:equal>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="user" property="validDate"/>&nbsp;</td>
		  <td class="listcellrow2">
		  <%
		  String href1="/privilege/permissionAction.do?action=user_perm_table&page="+String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage())+"&userid="+user.getId();
		  %>
		  <perm:image href="<%=href1%>" src="/images/edit.gif"  rescode="GDP-YHGL" optcode="ALL" />


		 <!-- <a href="<%=request.getContextPath()%>/privilege/permissionAction.do?action=user_perm_table&userid=<bean:write name="user" property="id"/>"><img src="<%=request.getContextPath()%>/images/edit.gif" width="16" height="16" border="0"></a>-->&nbsp;</td>
		  <td  class="listcellrow2">
		  <%
		  String href2="/privilege/userAction.do?action=toUpdate&page="+String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage())+"&id="+user.getId();
		  %>
		  <perm:image href="<%=href2%>" src="/images/properties.gif"  rescode="GDP-YHGL" optcode="ALL" />

		  <!--<html:link action="/userAction.do?action=toUpdate" paramId="id" paramName="user" paramProperty="id" ><img src="<%=request.getContextPath()%>/images/properties.gif" width="16" height="16" border="0"></html:link>-->&nbsp;</td>
		  <td class="listcellrow2">
		  <%
		  String href3="/privilege/userAction.do?action=toPassword"+"&id="+user.getId();
		  %>
		  <logic:equal name="user" property="userType" value="1">
		  <perm:image href="<%=href3%>" src="/images/properties.gif"  rescode="GDP-YHGL" optcode="ALL" />
		  </logic:equal>
		 <!--<html:link action="/userAction.do?action=toPassword" paramId="id" paramName="user" paramProperty="id"  ><img src="<%=request.getContextPath()%>/images/properties.gif" width="16" height="16" border="0"></html:link>-->&nbsp;</td>
	     </tr>
  	  </logic:iterate>
      </table>
	  </td>
  </tr>
</html:form>
  <tr>
  <td  colspan="2"  class="f12">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr align="center">
	<td class="f12">
	<gmenu:allpager name="pageControl" page="/privilege/userAction.do?action=list"  usepic="true"/>
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
  <tr >
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
<script language="javascript">
function handleAction(obj){
  temp = obj.action;
  position = temp.indexOf("?action");
  if (position == -1){
    obj.action = temp + "?action=search";
  }
  return true;
}
</script>
