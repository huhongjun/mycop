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
  window.location="<gdp:context/>/jsp/privilege/level/level-new.jsp";
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
   obj.form.action="<gdp:context/>/privilege/levelAction.do?action=delete";   	
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
<%  String key_value = OrderList.getInstance().level_key;
    String uri = "/privilege/levelAction.do";
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
<form name="levelForm" METHOD=POST ACTION="levelAction.do">
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="TableTitleText">行政级别列表</td>
  </tr>
  <tr align="right"> 
    <td colspan="2">
	 <perm:button property="addbt" type="button" styleClass="button" onclick="add()" value="新增" rescode="GDP-XZJB" optcode="ALL"/>
	  <perm:button property="delbt" styleClass="button" onclick="return del(this,'delete','请问确定要删除所选记录吗？')" value="删除" rescode="GDP-XZJB" optcode="ALL"/>
	  	
      </td>      
  </tr>
  <tr> 
    <td colspan="2"> <table width="100%" border="0" cellpadding="2" cellspacing="0" >
        <tr> 
          <td width="24"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
         <!-- <td width="15%" class="Listtitle">编号</td>-->
         
		  <td width="10%" class="Listtitle" id="<%=mapList[0]%>" onclick="order('<%=mapList[0]%>',event)">编号</td>
		  <td width="25%" class="Listtitle" id="<%=mapList[1]%>" onclick="order('<%=mapList[1]%>',event)">名称</td>
          <td width="40%" class="Listtitle" id="<%=mapList[2]%>" onclick="order('<%=mapList[2]%>',event)">描述</td>
		  <td width="10%" class="Listtitle">修改</td>
        </tr>		
       <logic:iterate id="level" name="pageControl" property="data" type="com.gever.sysman.level.model.Level">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);"> 
          <td  class="listcellrow2"> <input type="checkbox" name="id" value="<bean:write name="level" property="id"/>"
          <% if(level.getId()<10) out.print("disabled"); %>></td>
        
		  <td class="listcellrow2"><bean:write name="level" property="code"/></td>
		  <td class="listcellrow2"><bean:write name="level" property="name"/></td>
		  <td class="listcellrow2"><bean:write name="level" property="description"/>&nbsp;</td>
           <td class="listcellrow2">
		  <%
		  String href="/privilege/levelAction.do?action=edit"+"&id="+level.getId();
		  %>
		  <perm:image href="<%=href%>" src="/images/properties.gif"  rescode="GDP-XZJB" optcode="ALL" />

		 &nbsp;</td>
	   
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
	<gmenu:allpager name="pageControl" page="/privilege/levelAction.do?action=list"  usepic="true"/>
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




