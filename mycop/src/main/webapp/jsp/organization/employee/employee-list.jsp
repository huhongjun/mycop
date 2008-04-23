<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ page import="java.util.*,com.gever.struts.pager.PageControl,com.gever.sysman.util.OrderList"%>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
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
<%  String key_value = OrderList.getInstance().employee_key;
    String uri = "/organization/EmployeeAction.do";
%>  <%@ include file="../../order_inc.inc" %>
    //==============================================================================
</script>
<%
//set link parameter value
Map param=new java.util.HashMap();
param.put("departid",request.getParameter("departid"));
param.put("page",String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage()));
pageContext.setAttribute("param", param);
Map param2=new java.util.HashMap();
param2.put("departid",request.getParameter("departid"));
pageContext.setAttribute("param2", param2);
%>
</head>
<body class="bodybg">
<script>
	try{
		document.body.onmousedown=function(){top.frames["right"].hideAllMenu();}	
	}catch(e){
	//
	}
</script>
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
   <!-- <td>系统管理/人员管理</td>-->
  </tr>
</table>
<table width="95%" align="center">
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="TableTitleText">人员管理</td>
  </tr>
  <html:form action="/EmployeeAction" >
  <tr align="right"> 
     <td colspan="2">
	   <%
	   	  /* <INPUT TYPE="text" NAME="search" >
	   	  <SELECT NAME="condition" class="button">
	   		<option value="name">人员名称</option>
	   		<option value="username">人员帐户</option>
	   		<option value="code">人员编码</option>
	   	  </SELECT>
	   	  <input name="sea" type="submit" class="button"  onclick="toAction(this,'tosearch')" value="查询"> */
	   %>
	  &nbsp;&nbsp;&nbsp;&nbsp;
	  <!--<perm:button property="addbt" styleClass="button" onclick="add()" value="新建" rescode="GDP-YHGL" optcode="ALL" />-->
	  <perm:button property="create" styleClass="button" onclick="toAction(this,'selectDepartmentUser')" value="选择人员" rescode="GDP-RYGL" optcode="ALL"/>
      <!--input name="del" type="submit" class="button" onclick="toAction(this,'delete')" value="删除"--> 
	  <input type="hidden" name="departid" value="<%=request.getParameter("departid")%>">
	  </td>
  </tr>
  <tr> 
    <td colspan="2"> 
	<table width="100%" border="0" cellspacing="0" cellpadding="2">
         <tr> 
          <td width="5%"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
           <!--<td width="15%" class="Listtitle">人员编码</td>-->
          <td width="12%" id="<%=mapList[0]%>" class="Listtitle" onclick="order('<%=mapList[0]%>',event)">帐户</td>
          <td width="18%" id="<%=mapList[1]%>" class="Listtitle" onclick="order('<%=mapList[1]%>',event)">姓名</td>
		  <td width="15%" id="<%=mapList[2]%>" class="Listtitle" onclick="order('<%=mapList[2]%>',event)">用户类型</td>
		  <td width="15%" id="<%=mapList[3]%>" class="Listtitle" onclick="order('<%=mapList[3]%>',event)">用户状态</td>
		   <td width="15%" id="<%=mapList[4]%>" class="Listtitle" onclick="order('<%=mapList[4]%>',event)">有效期</td>
          <!--<td width="10%" class="Listtitle">人员性别</td>-->
          <td width="5%"  class="Listtitle">岗位</td>

        </tr>
      <logic:iterate id="element" name="pageControl" property="data" type="com.gever.sysman.organization.model.User">
		<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);"> 
         
		 
		  <td class="listcellrow2"><INPUT TYPE="checkbox" NAME="id" value='<bean:write name="element" property="id"/>'>&nbsp;</td>
		   <!--<td class="listcellrow2"><bean:write name="element" property="code"/>&nbsp;</td>-->
          <td class="listcellrow2"><html:link action="/EmployeeAction?action=view" paramId="id" paramName="element" paramProperty="id" name="param"><bean:write name="element" property="userName"/></html:link>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="element" property="name"/>&nbsp;</td>
         <td class="listcellrow2"><logic:equal name="element" property="userType" value="0">管理员</logic:equal><logic:equal name="element" property="userType" value="1">普通用户</logic:equal><logic:equal name="element" property="userType" value="2">CA用户</logic:equal><logic:equal name="element" property="userType" value="CA用户">CA用户</logic:equal><logic:equal name="element" property="userType" value="普通用户">普通用户</logic:equal>&nbsp;</td>
		   <td class="listcellrow2"><logic:equal name="element" property="status" value="0">不激活</logic:equal><logic:equal name="element" property="status" value="1">已激活</logic:equal><logic:equal name="element" property="status" value="激活">已激活</logic:equal><logic:equal name="element" property="status" value="不激活">不激活</logic:equal>&nbsp;</td>
		    <td class="listcellrow2"><bean:write name="element" property="validDate"/>&nbsp;</td>
          <!--<td class="listcellrow2">
		  <logic:equal name="element" property="gender" value="1" >男</logic:equal>
		  <logic:equal name="element" property="gender" value="2" >女</logic:equal>
		  <logic:equal name="element" property="gender" value="0" >未知</logic:equal>
		  &nbsp;</td>-->
		  <td  class="listcellrow2">
		  <%
		  String href="/organization/EmployeeAction.do?action=selectJobUser&page="+String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage())+"&departid="+request.getParameter("departid")+"&id="+element.getId();
		  %>
		  <perm:image href="<%=href%>" src="/images//job-sel.gif"  rescode="GDP-RYGL" optcode="ALL" />
		  <!--<html:link action="/EmployeeAction.do?action=selectJobUser" paramId="id" paramName="element" paramProperty="id"  name="param"><img src="<gdp:context/>/images/job-sel.gif" width="16" height="16" border="0"></html:link>-->&nbsp;</td>
		  
        </tr>
        </logic:iterate>
	  </table>
	  
	  
	  </td>
  </tr>
  </html:form>
  <tr> 
    <td class="f12"></td>
     <td class="f12">
	   
	  <!--<smile:pager name="pageControl" page="/organization/EmployeeActionAction.do?action=list" >
	   <table width="60%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
			<td class="f12">
			共<bean:write name="pageControl" property="maxRowCount"/>条记录&nbsp;<bean:write name="pageControl" property="rowsPerPage"/>条记录/页&nbsp;
			<span class="f12">直接查看<smile:list/>页&nbsp;</span>
				
			</td>
			<td align="right" class="f12"> 
				<table width="150" border="0" cellspacing="0" cellpadding="0">
					<tr align="center"> 
						<td><smile:first><img src="<gdp:context/>/images/First.gif" alt="第一页" width="16" height="13" border="0"></smile:first>&nbsp;</td>
						<td><smile:prev><img src="<gdp:context/>/images/Previous.gif" alt="上一页" width="16" height="13"></smile:prev>&nbsp;</td>
						<td><smile:next><img src="<gdp:context/>/images/Next.gif" alt="下一页" width="16" height="13"></smile:next>&nbsp;</td>
						<td><smile:last><img src="<gdp:context/>/images/Last.gif" alt="最后一页" width="16" height="13"></smile:last>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	  </table>
       </smile:pager>-->
	    <gmenu:allpager name="pageControl" page="/organization/EmployeeAction.do?action=list"  usepic="true" param="param2"/>
      
	  
	  </td>
  </tr>
  <tr> 
    <td colspan="2" class="f12">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="f12">&nbsp;</td>
  </tr>
  <tr > 
    <td height="19" colspan="2" class="f12">&nbsp;
	
	</td>
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

