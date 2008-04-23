<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>

<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<body class="bodybg">
<html:form action="/dailyoffice/tools/editCardcase" method="post">
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
				  <table width="500" height="36" border="0" cellpadding="2" cellspacing="1">
				  <tr>
				  <td width="15%"></td>
				  <td width="33%"></td>
				  <td width="4%"></td>
				  <td width="15%"></td>
				  <td width="33%"></td>
				  </tr>
                             <tr class="listcellrow">
				   	<td height="23" nowrap class="InputLabelCell">
                                             ��&nbsp;&nbsp;&nbsp;&nbsp;��:
				   	</td>
				   	<td  height="23" class="f12">
					<logic:equal value="-1" name="CardcaseForm" property="cardType">
						<bean:write name="CardcaseForm" property="nodename" />
					</logic:equal>
					<logic:notEqual value="-1" name="CardcaseForm" property="cardType">
                                            <html:select property="cardType">
                                               <html:options collection="cardcase_types" property="type_code"  labelProperty="type_name" styleClass="input"/>
                                             </html:select>
					</logic:notEqual>
                                        </td>
				   	<td></td>
				   	<td></td>
				   </tr>
           			<tr class="listcellrow">
					    <td  height="23" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.customer" size="24" validatetype="notempty" msg="��������Ϊ�գ�"  maxlength="10"/>
						</td><td><span class="style1">*</span></td>
						 <td height="23" nowrap class="InputLabelCell" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.nickname" size="22"  maxlength="10"/></td>
					</tr>
					<tr class="listcellrow">
					    <td  height="23" nowrap class="InputLabelCell">��˾����:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.company" size="22"  maxlength="50"/></td>
						<td></td>
						<td  height="23" nowrap class="InputLabelCell" align="right">��˾��ַ:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.address" size="22"  maxlength="30"/></td>
					</tr>
					<tr class="listcellrow">
						<td  height="23" nowrap class="InputLabelCell">ְ&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.duty" size="22"  maxlength="10"/></td>
						<td></td>
					    <td  height="23" nowrap class="InputLabelCell" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.post_code" size="22"  maxlength="20"/></td>
					</tr>
					<tr class="listcellrow">
					    <td  height="23" nowrap class="InputLabelCell">E_MAIL&nbsp;:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.e_mail" size="22" maxlength="30"/></td>

						<td></td>
						<td  height="23" nowrap class="InputLabelCell" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.phone" size="22" maxlength="30"
						onlytype="tel"/></td>
					</tr>
					<tr class="listcellrow">
					    <td  height="23" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td  height="23"><html:text styleClass="input" property="vo.fax" size="22"  maxlength="30" onlytype="tel" /></td>
						<td></td>
						<td  height="23" nowrap class="InputLabelCell" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;��:</td>
					    <td height="23"><html:text styleClass="input" property="vo.mobile" size="22"  maxlength="15" onlytype="int" /></td>
					</tr>
					<tr class="listcellrow">
					    <td  height="23" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;ҳ:</td>
					    <td colspan="4" height="23"><html:text styleClass="input" property="vo.home_page" size="22" validatetype="url" msg="��ַ��ʽ����,��ȷ��ʽ:'http://---.---��ftp://---.---'"  maxlength="50"/></td>
					</tr>
					<tr class="listcellrow">
					<td  nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;ע:</td>
					     <td nowrap colspan="4"><html:textarea property="vo.remark"  cols="37" rows="3" styleClass="input" maxlength="100"/></td>
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
		<goa:button property="save" value="����" operation="MBJ.OPT" styleClass="button"  onclick="return doSave(CardcaseForm)"/>
		<goa:button property="save" value="���沢����" operation="MBJ.OPT" styleClass="button"  onclick="return doSaveExit(CardcaseForm)"/>
          <goa:button property="exit" value="����" operation="MBJ.VIEW" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>
