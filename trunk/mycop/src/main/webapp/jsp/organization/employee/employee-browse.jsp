<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
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
  </tr>
</table>
<table width="75%" align="center" cellpadding="10" cellspacing="1">
  <tr align="center">
    <td bgcolor="#FFFFFF" class="TableTitleText">�ʻ���ϸ����</td>
  </tr>
  <tr align="center">
    <td bgcolor="#FFFFFF" >
      <table align="center" class="f12">
          <!--<tr>
			<td nowrap class="InputLabelCell" >�û�ID:</td>
			<td><bean:write name="OUserForm" property="id"/></td>
		</tr>-->
		<tr>
			<td nowrap class="InputLabelCell" >�ʻ�:</td>
			<td><bean:write name="OUserForm" property="userName"/></td>
		</tr>
		 <tr>
			<td nowrap class="InputLabelCell" >����:</td>
			<td><bean:write name="OUserForm" property="name"/></td>
		</tr>

		<!--<tr>
		<td nowrap class="InputLabelCell" >�û��Ա�:</td>
		<td><logic:equal name="OUserForm" property="gender" value="1" >��</logic:equal>
		  <logic:equal name="OUserForm" property="gender" value="2" >Ů</logic:equal>
		  <logic:equal name="OUserForm" property="gender" value="0" >δ֪</logic:equal>
		 </td>
        </tr>-->
		<!--<tr>
		<td nowrap class="InputLabelCell" >�û�����:</td>
		<td><bean:write name="OUserForm" property="code"/></td>
        </tr>-->
		<tr>
		<td nowrap class="InputLabelCell" >�û�����:</td>
		<td><bean:write name="OUserForm" property="userType"/></td>
        </tr>
        <tr>
		<td nowrap class="InputLabelCell" >�û�״̬:</td>
		<td>
         <logic:equal name="OUserForm" property="status" value="1" >����</logic:equal>
		  <logic:equal name="OUserForm" property="status" value="0" >δ����</logic:equal>
		</td>
        </tr>

		<tr>
			<td nowrap class="InputLabelCell" >��Ч��:</td>
			<td  ><bean:write name="OUserForm" property="validDate"/>
			</td>
		</tr>
		<!--<tr>
			<td nowrap class="InputLabelCell" >������֯:</td>
			<td >
			 <logic:iterate id="element" name="userDepartments">
				<bean:write name="element" property="name"/>,
              </logic:iterate>
			</td>
		</tr>
		<tr>
			<td nowrap class="InputLabelCell" >������֯:</td>
			<td >
              <logic:iterate id="element" name="userJobs">
				<bean:write name="element" property="name"/>,
			</logic:iterate>
			</td>
		</tr>-->


      </table>
	  </td>
  </tr>
  <tr>
   <td>

<td>
  </tr>

  <tr align="center">
    <td >
	<html:form action="/EmployeeAction" >
     <html:submit property="list" value="����" styleClass="button" onclick="toAction(this,'list')"/>
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
