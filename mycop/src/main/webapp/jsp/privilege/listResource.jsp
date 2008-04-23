<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page import="com.gever.sysman.util.OrderList" %>

<html>
<head>
<title></title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<style type="text/css">
<!--
-->
</style>
<script language="javascript">
//##############������Ӵ��뿪ʼ2004-11-18################
function order()
{
	//���Ľṹ����������
  parent.location="<gdp:context/>/jsp/privilege/order.jsp";
}
function orderResource()
{
//  parent.location="<gdp:context/>/jsp/privilege/orderResource.jsp";
  window.location="<gdp:context/>/jsp/privilege/orderResource.jsp?resid=<%=request.getParameter("resid")%>"
}


function openddl()
{
  parent.location="<gdp:context/>/privilege/openddlAction.do?action=exportDDL";
}
//##############������Ӵ������2004-11-18################
function add()
{
  window.location="<gdp:context/>/privilege/resourceAction.do?action=create&resid=<%=request.getParameter("resid")%>";
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

  var strDel='0';

	for(var i = 0; i < obj.form.elements.length; i++) {
		if (obj.form.elements[i].type == 'checkbox') {
		//'checkbox5' ��ѡ��ǰ������checkbox�ĸ�ѡ������֡��޳�
             if(obj.form.elements[i].checked && obj.form.elements[i].name != 'checkbox5') {
			  strDel = '1';
		  }
		}
	}
	if(strDel == '0') {
         alert('��û��ѡ���κμ�¼!');
		 return false;
	}
  var res=confirm("�Ƿ����Ҫɾ��ѡ��ļ�¼��");
  if(res==true) {
   obj.form.action="<gdp:context/>/privilege/resourceAction.do?action=delete&resid=<%=request.getParameter("resid")%>";
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
	obj.style.backgroundColor='#ffffff';
}
<% if(request.getParameter("action")!=null&&!"getListChild".equals(request.getParameter("action"))){
  if(request.getParameter("resid")!=null){
%>
    // =========================================================================================
    reload();
<%  }}  %>
    reload();
var ns = navigator.appName == "Netscape";
function reload(){
    reloaded = true;
    if(ns){
        window.parent.document.getElementById("left_tree").contentDocument.location.reload('<%=request.getParameter("resid")%>');
    } else {
        window.parent.frames["left_tree"].reload('<%=request.getParameter("resid")%>');
    }
}
    // =========================================================================================

    //==============================================================================
    // ������ӣ������б�������
    var ns = navigator.appName == "Netscape";
<%  String key_value = OrderList.getInstance().res_key;
    String uri = "/privilege/resourceAction.do";
%>  <%@ include file="../order_inc.inc" %>
    //==============================================================================
</script>
</head>
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
    <!--<td>ϵͳ����/��Դ�б�</td>-->
  </tr>
</table>
<table width="95%" align="center">
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" class="TableTitleText">��Դ�б�</td>
  </tr>
  <html:form method="post" action="/resourceAction.do">
  <tr align="right">
    <td colspan="2">
	<!--������Ӵ��뿪ʼ2004-11-18-->
 <perm:button  property="addbt" type="button" styleClass="button" onclick="openddl()" value="����DDL" rescode="GDP-ZYGL" optcode="ALL" />
 <!--
	 <perm:button  property="addbt" type="button" styleClass="button" onclick="order()" value="˳�����" rescode="GDP-ZYGL" optcode="ALL" />
	 -->
	  <perm:button  property="addbt" type="button" styleClass="button" onclick="orderResource()" value="˳�����" rescode="GDP-ZYGL"  optcode="ZYPX" />
<!--������Ӵ������2004-11-18-->
     <perm:button  property="addbt" type="button" styleClass="button" onclick="add()" value="����" rescode="GDP-ZYGL" optcode="ALL" />
	 <perm:button property="delbt" styleClass="button" onclick="return del(this,'delete','����ȷ��Ҫɾ����ѡ��¼��')" value="ɾ��" rescode="GDP-ZYGL" optcode="ALL"/>
	 <!--<perm:button property="searbt" styleClass="button" onclick="query()" value="��ѯ" rescode="GDP-ZYGL" optcode="VIEW"/> </td>-->
  </tr>
  <tr>
    <td colspan="2">

	<table width="100%" border="0" cellspacing="0" cellpadding="2">
        <tr>
          <td width="24" class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox" onclick="checkAll(this,'id')"></td>
          <!--<td class="Listtitle">���</td>-->
		  <td id="<%=mapList[0]%>" class="Listtitle" onclick="order('<%=mapList[0]%>',event)">��Դ����</td>
          <td id="<%=mapList[1]%>" class="Listtitle" onclick="order('<%=mapList[1]%>',event)">��Դ����</td>
          <td id="<%=mapList[2]%>" class="Listtitle" onclick="order('<%=mapList[2]%>',event)">��Դ����</td>
          <td id="<%=mapList[3]%>" class="Listtitle" onclick="order('<%=mapList[3]%>',event)">������Դ</td>
          <td class="Listtitle">�޸�</td>
        </tr>
       <logic:iterate id="resource" name="pageControl" property="data" type="com.gever.sysman.privilege.model.Resource">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td  class="listcellrow2"> <input type="checkbox" name="id" value="<bean:write name="resource" property="id"/>" 
            <% if(resource.getId()<35) out.print("disabled"); %>>&nbsp;</td>
		  <!--<td class="listcellrow2"><bean:write name="resource" property="id"/></td>-->
		  <td class="listcellrow2"><bean:write name="resource" property="code"/>&nbsp;</td>
          <td class="listcellrow2"><bean:write name="resource" property="name"/>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="resource" property="description"/>&nbsp;</td>
		  <td class="listcellrow2"><bean:write name="resource" property="parentName"/>&nbsp;</td>
		  <td class="listcellrow2">
		  <%
		  String href3="/privilege/resourceAction.do?action=edit"+"&id="+resource.getId()+"&resid="+
		  resource.getParentID();
		  %>
		  <perm:image href="<%=href3%>" src="/images/properties.gif"  rescode="GDP-ZYGL" optcode="ALL" />


		 <!-- <a href="<%=request.getContextPath()%>/privilege/resourceAction.do?action=edit&id=<bean:write name="resource" property="id"/>&resid=<bean:write name="resource" property="parentID"/>"><img src="<%=request.getContextPath()%>/images/properties.gif" width="16" height="16" border="0"></a>-->&nbsp;</td>
	     </tr>
  	  </logic:iterate>
      </table>
	  </td>
  </tr>
  <!-- <input type="hidden" name="resid" value="<%=request.getParameter("resid")%>">-->
</html:form>
  <tr>
  <td  colspan="2"  class="f12">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr >
	<td class="f12">
	<%
	String resid = request.getParameter("resid");
    String temp = "/privilege/resourceAction.do?action=getListChild&resid=" + resid;
    %>
	<gmenu:allpager name="pageControl" page="<%=temp%>"  usepic="true"/>
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
<SCRIPT LANGUAGE="JavaScript">
<!--
 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();
} }catch(e){
}
//-->
</SCRIPT>
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

