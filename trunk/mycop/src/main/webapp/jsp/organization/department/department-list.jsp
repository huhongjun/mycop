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
<script language="javascript" src="<gdp:context/>/js/gdp.js">
</script>
<script langauge="javascript">
function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#ffffff';
}
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
var ns = navigator.appName == "Netscape";
<%
//judge want to load the tree
if(request.getParameter("action")!=null&&("create".equals(request.getParameter("action"))||"update".equals(request.getParameter("action"))||"delete".equals(request.getParameter("action")) )){
    if(param.get("departid")!=null){
	%>
    // =========================================================================================
    reload();
<%  }} %>
    reload();
function reload(){
    reloaded = true;
    if(ns){
        window.parent.document.getElementById("tree").contentDocument.location.reload('<%=param.get("departid")%>');
    } else {
        window.parent.frames["tree"].reload('<%=param.get("departid")%>');
    }
}
    // =========================================================================================
//-->
</SCRIPT>
<script>
    //==============================================================================
    // ������ӣ������б�������
    var ns = navigator.appName == "Netscape";
<%  String key_value = OrderList.getInstance().department_key;
    String uri = "/organization/DepartmentAction.do";
%>  <%@ include file="../../order_inc.inc" %>
    //==============================================================================
</script>
<script langauge="javascript">
//###############������ӿ�ʼ2004-11-24
function ord()
{
  parent.location="<gdp:context/>/jsp/organization/department/order.jsp";
}
//###############������ӽ���2004-11-24
function orderdepartment()
{
 window.location="<gdp:context/>/jsp/organization/department/department_order.jsp?resid=<%=request.getParameter("departid")%>";
}//##########################libiao add 2004-12-3
</script>
</head>
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
   <!-- <td>ϵͳ����/��֯����</td>-->
  </tr>
</table>
<table width="95%" align="center">
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>

<input name="id" type="hidden"  value="<%=request.getParameterValues("id")%>">

    <td colspan="2" class="TableTitleText">
	<%
	String departmentName = (String)request.getAttribute("department");
	%>
	<%
	out.println(""+ departmentName);
	%>�б�</td>
  </tr>
   <html:form action="/DepartmentAction" >
  <tr align="right">
    <td colspan="2">
	  <%
	  	  /*<INPUT TYPE="text" NAME="search" >
	  	  <SELECT NAME="condition" class="button">
	  		<option value="name">��֯����</option>
	  		<option value="code">��֯����</option>
	  	  </SELECT>
	  	  <input name="sea" type="submit" class="button"  onclick="toAction(this,'tosearch')" value="��ѯ"> */
	  %>
	  &nbsp;&nbsp;&nbsp;&nbsp;
	  <!--������Ӵ��뿪ʼ2004-11-24
	   <perm:button  property="addbt" type="button" styleClass="button" onclick="order()" value="�ṹ����" rescode="GDP-BMGL" optcode="ALL" />
	-->
	<!--������Ӵ��뿪ʼ2004-12-2-->
	   <perm:button  property="addbt" type="button" styleClass="button" onclick="orderdepartment()" value="��������" rescode="GDP-BMGL" optcode="BMPX" />
   <!--������Ӵ��뿪ʼ2004-12-2-->
	  <perm:button property="create"  onclick="toAction(this,'toCreate')" value="����" rescode="GDP-BMGL" optcode="ALL" />
      <perm:button property="del"  onclick="return toAction(this,'delete','��Ҫɾ����֯���ܰ�������֯��ɾ������֯�������������֯ɾ��������ȷ��Ҫɾ������֯��')" value="ɾ��" rescode="GDP-BMGL" optcode="ALL" />
	  <input type="hidden" name="departid" value="<%=param.get("departid")%>">
	  </td>
  </tr>
  <tr>
    <td colspan="2">
	   <table width="100%" border="0" cellspacing="0" cellpadding="2">
        <tr>
          <td width="24"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
          <td width="20%" id="<%=mapList[0]%>" class="Listtitle" onclick="order('<%=mapList[0]%>',event)">��֯����</td>
          <td width="35%" id="<%=mapList[1]%>" class="Listtitle" onclick="order('<%=mapList[1]%>',event)">��֯����</td>
          <td width="15%" class="Listtitle">�޸�</td>
		  <td width="15%" class="Listtitle">ѡ���λ</td>
		  <td width="15%" class="Listtitle">ѡ����Ա</td>
        </tr>
        <logic:iterate id="element" name="pageControl" property="data" type="com.gever.sysman.organization.model.Department" >
		<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td  class="listcellrow2">

		  <INPUT TYPE="checkbox" NAME="id" value='<bean:write name="element" property="id"/>'>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="element" property="code"/>&nbsp;</td>
          <td class="listcellrow2"><html:link action="/DepartmentAction?action=view" paramId="id" paramName="element" paramProperty="id" name="param"><bean:write name="element" property="name"/></html:link>&nbsp;</td>
          <td  class="listcellrow2">
		  <%
		  String href="/organization/DepartmentAction.do?action=toUpdate&page="+String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage())+"&departid="+request.getParameter("departid")+"&id="+element.getId();
		  %>
		  <perm:image href="<%=href%>" src="/images/properties.gif"  rescode="GDP-BMGL" optcode="ALL" />
		  <!--<html:link action="/DepartmentAction?action=toUpdate" paramId="id" paramName="element" paramProperty="id" name="param"><img src="<gdp:context/>" width="16" height="16" border="0"></html:link>-->&nbsp;</td>
		  <td  class="listcellrow2">
		   <%
		   String href1="/organization/DepartmentAction.do?action=selectDepartmentJob&page="+String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage())+"&departid="+request.getParameter("departid")+"&id="+element.getId();
		   %>
		  <perm:image href="<%=href1%>" src="/images/job-sel.gif"  rescode="GDP-BMGL" optcode="ALL" />&nbsp;
		  <!-- <html:link action="/DepartmentAction?action=selectDepartmentJob" paramId="id" paramName="element" paramProperty="id" name="param" ><img src="<gdp:context/>/images/job-sel.gif" width="16" height="16" border="0"></html:link>&nbsp;</td>-->
		  <td  class="listcellrow2">
		  <%
		  String href2="/organization/DepartmentAction.do?action=selectDepartmentUser&page="+String.valueOf(((PageControl)request.getAttribute("pageControl")).getCurrentPage())+"&departid="+request.getParameter("departid")+"&id="+element.getId();
		  %>
		  <perm:image href="<%=href2%>" src="/images/emp-sel.gif"  rescode="GDP-BMGL" optcode="ALL" />
		  <!-- <html:link action="/DepartmentAction?action=selectDepartmentUser" paramId="id" paramName="element" paramProperty="id" name="param" ><img src="<gdp:context/>/images/emp-sel.gif" width="16" height="16" border="0"></html:link>-->&nbsp;</td>
        </tr>
        </logic:iterate>

      </table></td>
  </tr>
  </html:form>
  <tr>
    <td class="f12"></td>
     <td  class="f12">
	  <!-- <smile:pager name="pageControl" page="">
	   <table width="60%" border="0" cellspacing="0" cellpadding="0">
          <tr>
			<td class="f12">
			��<bean:write name="pageControl" property="maxRowCount"/>����¼&nbsp;<bean:write name="pageControl" property="rowsPerPage"/>����¼/ҳ&nbsp;
			<span class="f12">ֱ�Ӳ鿴<smile:list/>ҳ&nbsp;</span>

			</td>
			<td align="right" class="f12">
				<table width="150" border="0" cellspacing="0" cellpadding="0">
					<tr align="center">
						<td><smile:first><img src="<gdp:context/>/images/First.gif" alt="��һҳ" width="16" height="13" border="0"></smile:first>&nbsp;</td>
						<td><smile:prev><img src="<gdp:context/>/images/Previous.gif" alt="��һҳ" width="16" height="13"></smile:prev>&nbsp;</td>
						<td><smile:next><img src="<gdp:context/>/images/Next.gif" alt="��һҳ" width="16" height="13"></smile:next>&nbsp;</td>
						<td><smile:last><img src="<gdp:context/>/images/Last.gif" alt="���һҳ" width="16" height="13"></smile:last>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	  </table>
       </smile:pager>-->
	   <gmenu:allpager name="pageControl" page="/organization/DepartmentAction.do?action=list"  usepic="true" param="param2"/>


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
// ������ӣ��л�ͼƬ��ʾ
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
