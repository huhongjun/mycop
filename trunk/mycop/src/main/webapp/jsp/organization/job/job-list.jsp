<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>


<%@ page import="java.util.*,com.gever.struts.pager.PageControl,com.gever.sysman.util.OrderList"%>
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
<%  String key_value = OrderList.getInstance().job_key;
    String uri = "/organization/JobAction.do";
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
		if(window.parent!=null){
			document.body.onmousedown=function(){
				window.parent.top.frames["right"].hideAllMenu();
			}
		}	
	}catch(e){
	//
	}
</script>
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr> 
  <gmenu:getpathtag/>
    <!--<td>系统管理/岗位管理</td>-->
  </tr>
</table>
<table width="95%" align="center">
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="TableTitleText">
	<%
	String departmentName = (String)request.getAttribute("department");
	%>
	<%
	out.println(""+ departmentName);
	%>岗位列表</td>
  </tr>
  <html:form action="/JobAction" >
  <tr align="right"> 
    <td colspan="2">
	  <%
	  	  /* <INPUT TYPE="text" NAME="search" >
	  	  <SELECT NAME="condition" class="button">
	  		<option value="name">岗位名称</option>
	  	  </SELECT>
	  	  <input name="sea" type="submit" class="button"  onclick="toAction(this,'tosearch')" value="查询"> */
	  %>
	  &nbsp;&nbsp;&nbsp;&nbsp;
	 
	  <perm:button property="create"  onclick="toAction(this,'toCreate')" value="新增" rescode="GDP-GWGL" optcode="ALL" />
	  <perm:button property="del"  onclick="return toAction(this,'delete','请问确定要删除所选记录吗？')" value="删除" rescode="GDP-GWGL" optcode="ALL" />
	 <!--<input name="create" type="submit" class="button" onclick="toAction(this,'toCreate')" value="新建"  >
      <input name="del" type="submit" class="button" onclick="return toAction(this,'delete','请问确定要删除所选记录吗？')" value="删除"> 
	  -->
	  
	  <input type="hidden" name="departid" value="<%=request.getParameter("departid")%>" >
	  </td>
  </tr>
  <tr> 
    <td colspan="2"> <table width="100%" border="0" cellspacing="0" cellpadding="2">
        <tr> 
          <td width="24"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox"  onclick="javascript:checkAll(this,'id');"></td>
          <td width="50%" id="<%=mapList[0]%>" class="Listtitle" onclick="order('<%=mapList[0]%>',event)">岗位名称</td>
          <td width="20%" class="Listtitle">修改</td>
		  <td width="30%" class="Listtitle">人员</td>
        </tr>
        <logic:iterate id="element" name="pageControl" property="data" type="com.gever.sysman.organization.model.Job">
		<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);"> 
          <td  class="listcellrow2"> 
		  
		  <INPUT TYPE="checkbox" NAME="id" value='<bean:write name="element" property="id"/>'></td>
          <td class="listcellrow2"><html:link action="/JobAction?action=view" paramId="id" paramName="element" paramProperty="id" name="param"><bean:write name="element" property="name"/></html:link></td>
          <td  class="listcellrow2">
		  <%
		  String href="/organization/JobAction.do?action=toUpdate&page="+String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage())+"&departid="+request.getParameter("departid")+"&id="+element.getId();
		  %>
		  <perm:image href="<%=href%>" src="/images/edit.gif"  rescode="GDP-GWGL" optcode="ALL" />&nbsp;
		  <!--<html:link action="/JobAction?action=toUpdate" paramId="id" paramName="element" paramProperty="id" name="param"><img src="<gdp:context/>/images/edit.gif" width="16" height="16" border="0"></html:link>-->
		  </td>
		  <td  class="listcellrow2">
		  <%
		  String href1="/organization/JobAction.do?action=selectJobUser&page="+String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage())+"&departid="+request.getParameter("departid")+"&id="+element.getId();
		  %>
		  <perm:image href="<%=href1%>" src="/images//job-sel.gif"  rescode="GDP-GWGL" optcode="ALL" />&nbsp;
		  <!-- <html:link action="/JobAction?action=selectJobUser" paramId="id" paramName="element" paramProperty="id" name="param"><img src="<gdp:context/>/images/emp-sel.gif" width="16" height="16" border="0"></html:link>--></td>
        </tr>
        </logic:iterate>
       
       
       
      </table></td>
  </tr>
   </html:form>
  <tr> 
    <td class="f12"></td>
     <td  class="f12">
	   
	   <!--<smile:pager name="pageControl" page="/organization/JobAction.do?action=list">
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
	    <gmenu:allpager name="pageControl" page="/organization/JobAction.do?action=list"  usepic="true" param="param2"/>
      
	  
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
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>

