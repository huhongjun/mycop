<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page import="com.gever.sysman.util.OrderList"%>
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
  window.location="<gdp:context/>/jsp/privilege/opt/opt-new.jsp?resid=<%=request.getParameter("resid")%>";
}
//#################黎彪增加代码开始2004-11-19##################
//增加对资源操作排序的功能2004-11-19
function operationorder()
{
window.location="<gdp:context/>/jsp/privilege/opt/opt_order.jsp?resid=<%=request.getParameter("resid")%>"}
//#################黎彪增加代码结束2004-11-19##################
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
   obj.form.action="<gdp:context/>/privilege/operationAction.do?action=delete";
   obj.form.submit();
  }
}
</script>
<script langauge="javascript">
function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#ffffff';
}

    //==============================================================================
    // 胡勇添加，增加列表排序功能
    var ns = navigator.appName == "Netscape";
<%  String key_value = OrderList.getInstance().opt_key;
    String uri = "/privilege/operationAction.do";
%>  <%@ include file="../order_inc.inc" %>
    //==============================================================================
</script>
</head>
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
    <!--<td>系统管理/操作列表</td>-->
  </tr>
</table>
<table width="95%" >
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" class="TableTitleText">操作列表</td>
  </tr>
  <html:form method="post" action="/operationAction.do">
  <tr align="right">
    <td colspan="2">
      <% if (request.getParameter("resid")!=null && !"".equals(request.getParameter("resid")) && !"null".equals(request.getParameter("resid"))){ %>
	   <!--黎彪增加代码开始2004-11-19
           没有被调用
	  <perm:button property="addbt" styleClass="button"  type="button" onclick="operationorder()" value="操作排序" rescode="GDP-CZGL" optcode="ALL" />
      黎彪增加代码结束2004-11-19-->
	  <perm:button property="addbt" styleClass="button"  type="button" onclick="add()" value="新增" rescode="GDP-CZGL" optcode="ALL" />
      <% } %>
      <perm:button property="delbt" styleClass="button" onclick="return toAction(this,'delete','请问确定要删除所选记录吗？')" value="删除" rescode="GDP-CZGL" optcode="ALL"/>
	  	<!--<perm:button property="searbt" styleClass="button" onclick="query()" value="查询" rescode="GDP-CZGL" optcode="VIEW"/>-->
	 <!-- <input name="delbt" type="button" class="button" onclick="del(this)" value="删除">
      <input name="searbt" type="button" class="button"  onclick="query()"value="查询">--> </td>
  </tr>
  <tr>
    <td colspan="2">

	<table width="100%" border="0" cellspacing="0" cellpadding="2">
        <tr>
          <td class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
          <td class="Listtitle" id="<%=mapList[0]%>" onclick="order('<%=mapList[0]%>',event)">编号</td>
          <td class="Listtitle" id="<%=mapList[1]%>" onclick="order('<%=mapList[1]%>',event)">操作编码</td>
          <td class="Listtitle" id="<%=mapList[2]%>" onclick="order('<%=mapList[2]%>',event)">操作名称</td>
          <td class="Listtitle" id="<%=mapList[3]%>" onclick="order('<%=mapList[3]%>',event)">操作描述</td>
          <td class="Listtitle" id="<%=mapList[4]%>" onclick="order('<%=mapList[4]%>',event)">所属资源</td>
          <td class="Listtitle">修改</td>
        </tr>
       <logic:iterate id="operation" name="pageControl" type="com.gever.sysman.privilege.model.Operation">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td  class="listcellrow2"> <input type="checkbox" name="id" value="<bean:write name="operation" property="id"/>" 
            <% if(operation.getId()<35) out.print("disabled"); %>>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="operation" property="id"/>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="operation" property="code"/>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="operation" property="name"/>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="operation" property="description"/>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="operation" property="resourceName"/>&nbsp;</td>
		  <td class="listcellrow2">
		  <%
		  String href3="/privilege/operationAction.do?action=edit"+"&id="+operation.getId()+"&resid="+
		  request.getParameter("resid");
		  %>
		  <perm:image href="<%=href3%>" src="/images/properties.gif"  rescode="GDP-CZGL" optcode="ALL" />

		  <!--<a href="<gdp:context/>/privilege/operationAction.do?action=edit&id=<bean:write name="operation" property="id"/>&resid=<%=request.getParameter("resid")%>"><img src="<gdp:context/>/images/properties.gif" width="16" height="16" border="0"></a>-->&nbsp;</td>
	     </tr>
  	  </logic:iterate>
      </table>
	  </td>
  </tr>
  <input type="hidden" name="resid" value="<%=request.getParameter("resid")%>">
</html:form>
  <tr>
  <td  colspan="2" class="f12">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr >
	<td class="f12">
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
