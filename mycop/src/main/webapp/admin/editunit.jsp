<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>

<%String context =request.getContextPath();%>


<html:form action="/admin/editunit" method="post">
<div align="center">
  <center>
    <br>  
	<table  align="center" width="500"  border="0" cellspacing="0" cellpadding="0">
	<tr> 
      <td align="center" colspan="2"><span class="TableTitleText">��λ��Ϣ</span></td>
      </tr>
	<tr>
	<td align="center">
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
		  <tr> 
			<td>&nbsp;<html:hidden name="UnitForm" property="actionType"/></td>
		  </tr>
	   
		  <tr class="listcellrow" height="23" >
				<td width="86" nowrap class="InputLabelCell">��λ��ƣ�</td>
				<td ><html:text styleClass="input2" property="vo.unit_code" size="40" validatetype="notempty" msg="��λ��Ʋ���Ϊ�գ�" maxlength="10"/><font color="red">*</font></td>
			</tr>
			<tr class="listcellrow"  height="23" >
				<td width="86" nowrap class="InputLabelCell">��λ���ƣ�</td>
				<td ><html:text styleClass="input2" property="vo.unit_name" size="40" validatetype="notempty" msg="��λ���Ʋ���Ϊ�գ�"  maxlength="30"/><font color="red">*</font></td>
			</tr>

			<tr class="listcellrow" height="23">
				<td width="86" nowrap class="InputLabelCell">��ϵ�绰��</td>
				<td ><html:text styleClass="input2" property="vo.phone" size="40" validatetype="phone" msg="�绰��ʽ����,��ȷ��ʽ:'xxx-xxxxxx'"maxlength="30"/></td>
			</tr>
			<tr class="listcellrow" height="23">
				<td width="86" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;�棺</td>
				<td height="23"><html:text styleClass="input2" property="vo.fax" validatetype="phone"size="40" msg="�����ʽ����,��ȷ��ʽ:'xxx-xxxxxx' " maxlength="30"/></td>
			</tr>
			<tr class="listcellrow" height="23" >
				<td width="86" nowrap class="InputLabelCell">�������䣺</td>
				<td ><html:text styleClass="input2" property="vo.e_mail" size="40"  validatetype="email" msg="email��ʽ����,��ȷ��ʽ:'xxx@xxx.xxx'"   maxlength="30"/></td>
			</tr>
			<tr class="listcellrow" height="23">
				<td width="86"  nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;վ��</td>
				<td ><html:text styleClass="input2" property="vo.home_page" size="40" validatetype="url" msg="��ַ��ʽ����,��ȷ��ʽ:'http://---.---��ftp://---.---"   maxlength="100"/></td>
			</tr>
			<tr class="listcellrow" height="23">
				<td width="86"  nowrap class="InputLabelCell">��λ���ͣ�</td>
				
				 <td width="250" class="InputLabelCell"><html:select styleClass="input" property="vo.unit_type" >
									<html:option value="0">��ҵ</html:option>
									<html:option value="1">����</html:option></html:select></td>
			</tr>
			<tr class="listcellrow">
			<td width="86"  nowrap valign="top"  class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
				 <td ><html:textarea property="vo.remark" cols="45" rows="4" styleClass="input2" maxlength="200"/></td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
		  </tr>
		  
		</table>
	</td>
	</tr><tr> 
        <td align="center" colspan="2"> 
		<goa:button property="save" value="����" styleClass="button"  onclick="return doSave(UnitForm)" operation="DWXX.OPT"/>
		<goa:button property="save" value="���沢����" styleClass="button"  onclick="return doSaveExit(UnitForm)" operation="DWXX.OPT"/>
                <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="DWXX.VIEW"/></td>
      </tr></table>
    </center>
</div>

</html:form>
</body>

