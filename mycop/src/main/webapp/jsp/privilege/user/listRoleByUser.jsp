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
<style type="text/css">
<!--
-->
</style>
<script language="javascript">
function save()
{
  roleForm.submit();
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
  var res=confirm("�Ƿ����Ҫɾ��ѡ��ļ�¼��");
  if(res==true) {
   obj.form.action="<gdp:context/>/privilege/roleAction.do?action=delete";   	
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
</script>
</head>
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr> 
  <gmenu:getpathtag/>
    <!--<td>ϵͳ����/��ɫ�б�</td>-->
  </tr>
</table>
<table width="95%" align="center">
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2" class="TableTitleText">�û���ɫ�б�</td>
  </tr>
  <html:form method="post" action="/roleAction.do">
  <tr align="right"> 
    <td colspan="2"> <input name="addbt" type="button" class="button" onclick="add()" value="�½�">
      <input name="delbt" type="submit" class="button" onclick="del(this)" value="ɾ��"> 
      <input name="searbt" type="button" class="button"  onclick="query()"value="��ѯ"> </td>
  </tr>
  <tr> 
    <td colspan="2"> 

	<table width="100%" border="0" cellspacing="0" cellpadding="2">
        <tr> 
          <td width="24"  class="Listtitle"><input type="checkbox" name="checkbox5" value="checkbox"  onclick="checkAll(this,'id')"></td>
          <td class="Listtitle">���</td>
          <td class="Listtitle">��ɫ����</td>
          <td class="Listtitle">����</td>
		   <td class="Listtitle">��ɫ����</td>
        </tr>		
       <logic:iterate id="user_roles" name="pageControl" property="data" type="com.gever.sysman.privilege.model.Role">
	    <tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);"> 
          <td  class="listcellrow2"> <input type="checkbox" name="rolesId" value="<bean:write name="user_roles" property="id"/>"></td>
		  <td class="listcellrow2"><bean:write name="user_roles" property="id"/></td>
          <td class="listcellrow2"><bean:write name="user_roles" property="name"/></td>
		  <td class="listcellrow2"><bean:write name="user_roles" property="description"/></td>
		  <td class="listcellrow2"><bean:write name="user_roles" property="roleType"/></td>	
		</tr>
  	  </logic:iterate>	   
      </table>	 
	  </td>
  </tr>
  <html:hidden property="uid"  value="<%=request.getParameter("uid")%>" />
  <html:hidden property="action"  value="saveUserRole" />
</html:form>
  <tr> 
  <td  colspan="2"  class="f12"> 
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr > 
	<td class="f12">
	<smile:pager name="pageControl" page="/privilege/roleAction.do?action=listUserRoles">
	<smile:first><img src="<gdp:context/>/images/First.gif" alt="��һҳ" width="18" height="13" border="0"></smile:first>&nbsp;
	<smile:prev><img src="<gdp:context/>/images/Previous.gif" alt="��һҳ" width="14" height="13"></smile:prev>&nbsp;
	<smile:next><img src="<gdp:context/>/images/Next.gif" alt="��һҳ" width="14" height="13"></smile:next>&nbsp;
	<smile:last><img src="<gdp:context/>/images/Last.gif" alt="���һҳ" width="18" height="13"></smile:last>&nbsp;
	ֱ�Ӳ鿴<smile:list/>ҳ&nbsp;
	<bean:write name="pageControl" property="rowsPerPage"/>����¼/ҳ&nbsp;��<bean:write name="pageControl" property="maxRowCount"/>����¼
    </smile:pager>
    </td>
	</tr>
  </table>
  </td>
  </tr>
  <tr> 
    <td class="f12"><input type="button" name="savebt" value="����" onclick="save()"></td>
	<td  class="f12"><input type="button" name="back" value="����" onclick="history.back()"></td>
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
