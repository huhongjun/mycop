<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<html:form action="/User/viewUser" method="post">
<div align="center">
  <center>
   <br>
	<table  align="center" width="600"  border="0" cellspacing="0" cellpadding="0">
	<tr> 
      <td align="center" colspan="2"><span class="TableTitleText">?? �༭</span></td>
      </tr>
	<tr>
	<td align="center">
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
				
							  <tr class="listcellrow" height="23" >
					    <td width="86" nowrap class="InputLabelCell">??��</td>
					    <td class="f12"><goa:write  name="UserForm" property="vo.id" filter="true"/></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					    <td width="86" nowrap class="InputLabelCell">??��</td>
					    <td class="f12"><goa:write  name="UserForm" property="vo.name" filter="true"/></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					    <td width="86" nowrap class="InputLabelCell">??��</td>
					    <td class="f12"><goa:write  name="UserForm" property="vo.email" filter="true"/></td>
			  </tr>
			</table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		        <html:hidden name="UserForm" property="fid" />
		        <goa:button property="save" value="�޸�" styleClass="button"  onclick=" toUrl('../qiqu/User/editUser.do?actionType=modify',false)" operation="TSGL.OPT"/>
        <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="TSGL.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

