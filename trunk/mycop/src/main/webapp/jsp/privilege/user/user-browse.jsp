<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>组织资料-查看</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script src="<gdp:context/>/js/gdp.js"></script>
<!-- The xtree script file -->
<script src="<gdp:context/>/common/xtreeEx/xtree.js"></script>
<!-- Modify this file to change the way the tree looks -->
</head>

<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
    <td>帐号管理</td>
  </tr>
</table>
<table width="75%" align="center" cellpadding="10" cellspacing="1">
  <tr align="center">
    <td bgcolor="#FFFFFF" class="TableTitleText">帐号详细资料</td>
  </tr>
  <tr align="center">
    <td bgcolor="#FFFFFF" >
      <table align="center">
          <tr>
			<td nowrap class="InputLabelCell" >用户ID:</td>
			<td><bean:write name="UserForm" property="id"/></td>
		</tr>
		 <tr>
			<td nowrap class="InputLabelCell" >用户名称:</td>
			<td><bean:write name="UserForm" property="name"/></td>
		</tr>
		 <tr>
			<td nowrap class="InputLabelCell" >用户名:</td>
			<td><bean:write name="UserForm" property="userName"/></td>
		</tr>
		<tr>
		<td nowrap class="InputLabelCell" >用户性别:</td>
		<td><logic:equal name="UserForm" property="gender" value="1" >男</logic:equal>
		  <logic:equal name="UserForm" property="gender" value="2" >女</logic:equal>
		  <logic:equal name="UserForm" property="gender" value="0" >未知</logic:equal>
		 </td>
        </tr>
		<tr>
		<td nowrap class="InputLabelCell" >用户编码:</td>
		<td><bean:write name="UserForm" property="code"/></td>
        </tr>
		<tr>
		<td nowrap class="InputLabelCell" >用户类型:</td>
		<td><bean:write name="UserForm" property="userType"/></td>
        </tr>
        <tr>
		<td nowrap class="InputLabelCell" >用户状态:</td>
		<td>
         <logic:equal name="UserForm" property="status" value="1" >激活</logic:equal>
		  <logic:equal name="UserForm" property="status" value="0" >未激活</logic:equal>
		</td>
        </tr>

		<tr>
			<td nowrap class="InputLabelCell" >有效期:</td>
			<td  ><bean:write name="UserForm" property="validDate"/>
			</td>
		</tr>
		<tr>
			<td nowrap class="InputLabelCell" >所属组织:</td>
			<td >
			 <logic:iterate id="element" name="userDepartments">
				<bean:write name="element" property="name"/>,
              </logic:iterate>
			</td>
		</tr>
		<tr>
			<td nowrap class="InputLabelCell" >所属组织:</td>
			<td >
              <logic:iterate id="element" name="userJobs">
				<bean:write name="element" property="name"/>,
			</logic:iterate>
			</td>
		</tr>


      </table>
	  </td>
  </tr>
  <tr>
   <td>

<td>
  </tr>

  <tr align="center">
    <td >
	<html:form action="/userAction.do" >
     <html:submit property="list" value="返回" onclick="toAction(this,'list')"/>
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
