<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%String context=request.getContextPath();%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

<title>��֯����-�鿴</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script src="<gdp:context/>/js/gdp.js"></script>
<!-- The xtree script file -->
<script src="<gdp:context/>/common/xtreeEx/xtree.js"></script>
<!-- Modify this file to change the way the tree looks -->
</head>

<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">

  <tr>
  <gmenu:getpathtag/>
  </tr>

</table>
<table width="75%" align="center" cellpadding="10" cellspacing="1">
  <tr align="center">
    <td bgcolor="#FFFFFF" class="TableTitleText">��֯��ϸ����</td>
  </tr>
  <tr align="center">
    <td bgcolor="#FFFFFF" >
      <table align="center" class="f12">
          <!--<tr>
			<td nowrap class="InputLabelCell" >��֯ID:</td>
			<td><bean:write name="DepartmentForm" property="id"/></td>
		</tr>-->
		<tr>
		<td nowrap class="InputLabelCell" >��֯����:</td>
		<td class="f12"><bean:write name="DepartmentForm" property="code"/></td>
        </tr>
		 <tr>
			<td nowrap class="InputLabelCell" >��֯����:</td>
			<td><bean:write name="DepartmentForm" property="name"/></td>
		</tr>

        <tr>
		<td nowrap class="InputLabelCell" >�ϼ���֯:</td>
		<td>
		    <logic:equal name="DepartmentForm" property="parentDepartmentName" value="null">
		    ��˾
	            </logic:equal>
		    <logic:notEqual name="DepartmentForm" property="parentDepartmentName" value="null">
		    <bean:write name="DepartmentForm" property="parentDepartmentName"/>
	            </logic:notEqual>

		</td>
        </tr>

		<tr>
			<td nowrap class="InputLabelCell" >���:</td>
			<td colspan="3" ><bean:write name="DepartmentForm" property="description"/>
		</tr>
      </table>
	  </td>
  </tr>
  <tr>
   <td>

<td>
  </tr>
  <tr>

 </td>

  <tr align="center">
    <td >
	<html:form action="/DepartmentAction" >
     <html:submit  styleClass="button"  property="list" value="����" onclick="toAction(this,'list')"/>
     <input type="hidden" name="departid" value="<%=request.getParameter("departid")%>">
	  <input type="hidden" name="page" value="<%=request.getParameter("page")%>">
    </html:form>
	</td>
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
