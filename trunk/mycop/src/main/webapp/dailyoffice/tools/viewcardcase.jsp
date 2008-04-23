<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<body class="bodybg">
<html:form action="/dailyoffice/tools/viewCardcase" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center"><span class="TableTitleText">��Ƭ����</span></td>
      </tr>
      <tr>
        <td>&nbsp;<html:hidden name="CardcaseForm" property="actionType"/></td>
      </tr>
      <tr>
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr>
				  <td>
				  <table width="500" height="36" border="0" cellpadding="5" cellspacing="0">
                                    <tr class="listcellrow">
				   	<td width="15��" height="23" nowrap class="InputLabelCell">
                                             ��&nbsp;&nbsp;&nbsp;&nbsp;��:
				   	</td>
				   	<td width="35��" height="23">
                                            <bean:write  name="CardTypeVO" property="type_name" filter="true"/>
                                        </td>
				   	<td></td>
				   	<td></td>
                                    <tr class="listcellrow">
					    <td width="15%" height="23" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td width="35%" height="23" class="f12"><bean:write  name="CardcaseForm" property="vo.customer" filter="true"/></td>
						<!--<td width="5%"></td-->
						 <td width="15%" height="23" nowrap class="InputLabelCell" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td width="35%" height="23" class="f12"><bean:write   name="CardcaseForm" property="vo.nickname" filter="true"/>
						</td>
					</tr>

					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">��˾����:</td>
					    <td width="383" height="23" class="f12"><bean:write   name="CardcaseForm" property="vo.company" filter="true"/>
						</td>

						<td width="86" height="23" nowrap class="InputLabelCell" align="right">��˾��ַ:</td>
					    <td width="383" height="23" class="f12"><bean:write   name="CardcaseForm" property="vo.address" filter="true"/>
						</td>
					</tr>

					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">ְ&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
						<td width="383" height="23" class="f12"><bean:write   name="CardcaseForm" property="vo.duty" filter="true"/>
						</td>

						<td width="86" height="23" nowrap class="InputLabelCell" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
						 <td width="383" height="23" class="f12"><bean:write   name="CardcaseForm" property="vo.post_code" filter="true"/>
						</td>
					</tr>

					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">E_MAIL&nbsp;:</td>
						<td width="383" height="23" class="f12"><a href=mailto:<bean:write name="CardcaseForm" property="vo.e_mail" filter="true"/>><bean:write  name="CardcaseForm" property="vo.e_mail" filter="true"/></a>
						</td>

						<td width="86" height="23" nowrap class="InputLabelCell" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td width="383" height="23" class="f12"><bean:write   name="CardcaseForm" property="vo.phone" filter="true"/>
						</td>
					</tr>
				<!--	<tr class="listcellrow">

					</tr>-->
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td width="383" height="23" class="f12"><bean:write   name="CardcaseForm" property="vo.fax" filter="true"/>
						</td>

						<td width="86" height="23" nowrap class="InputLabelCell" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td width="383" height="23" class="f12"><bean:write   name="CardcaseForm" property="vo.mobile" filter="true"/>
						</td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;ҳ:</td>
					    <td width="383" height="23" colspan="4" class="f12"><a href="<bean:write  name="CardcaseForm" property="vo.home_page" filter="true"/>" target="window_name"><bean:write  name="CardcaseForm" property="vo.home_page" filter="true"/></a>
						</td>
					</tr>
					<tr class="listcellrow">
				     <td width="86" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;ע:</td>
					     <td width="383" colspan="3" class="f12"><bean:write  name="CardcaseForm" property="vo.remark" filter="true"/></td>
					</tr>
				  </table>
		  			</td>
				</tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="center">
		<html:hidden name="CardcaseForm" property="fid" />
		<%String modifylink="toUrl('"+context+"/dailyoffice/tools/editCardcase.do?actionType=modify',false)";%>
		<goa:button property="save" value="�޸�" styleClass="button" operation="MBJ.OPT" onclick="<%=modifylink%>"/>
        	<goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="MBJ.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

