<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>


<html:form action="/User/editUser" method="post">
<div align="center">
  <center>
    <br>  
	<table  align="center" width="500"  border="0" cellspacing="0" cellpadding="0">
	<tr> 
      <td align="center" colspan="2"><span class="TableTitleText">??�༭</span></td>
      </tr>
	<tr>
	<td align="center">
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
		  <tr> 
			<td>&nbsp;<html:hidden name="UserForm" property="actionType"/></td>
		  </tr>
				  <tr class="listcellrow" height="23" >
					     <td width="86" nowrap class="InputLabelCell">??��</td>
				     <td ><html:text styleClass="input2" property="vo.id" size="50" validatetype="notempty" msg="���Ʋ���Ϊ�գ�" maxlength="100"/><font color="red">*</font></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					     <td width="86" nowrap class="InputLabelCell">??��</td>
				     <td ><html:text styleClass="input2" property="vo.name" size="50" validatetype="notempty" msg="���Ʋ���Ϊ�գ�" maxlength="100"/><font color="red">*</font></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					     <td width="86" nowrap class="InputLabelCell">??��</td>
				     <td ><html:text styleClass="input2" property="vo.email" size="50" validatetype="notempty" msg="���Ʋ���Ϊ�գ�" maxlength="100"/><font color="red">*</font></td>
			  </tr>
			  <tr>
			<td>&nbsp;</td>
		  </tr>
		  
		</table>
	</td>
	</tr><tr> 
        <td align="center" colspan="2"> 
			          <goa:button property="save" value="����" styleClass="button"  onclick="return doSave(UserForm)" operation="TSGL.OPT"/>
		          <goa:button property="save" value="���沢����" styleClass="button"  onclick="return doSaveExit(UserForm)" operation="TSGL.OPT"/>
          <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="TSGL.VIEW"/></td>
      </tr></table>
    </center>
</div>

</html:form>
</body>

