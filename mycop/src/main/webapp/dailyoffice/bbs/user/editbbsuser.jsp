<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<body class="bodybg">
<html:form action="/dailyoffice/bbs/user" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">�û���Ϣ</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="BBSUserForm" property="actionType"/>
		<html:hidden property="sqlWhere"/></td>
      </tr>
      <tr> 
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr> 
				  <td>
				  <table width="400" height="36" border="0" cellpadding="2" cellspacing="1">
                  <tr class="listcellrow">
				  <td width='15%'>&nbsp;</td>
					    <td width="86" height="23" nowrap class="InputLabelCell">�ǳƣ�</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.nickname" size="22" validatetype="notempty" msg="���Ʋ���Ϊ�գ�" maxlength="10"/></td><td width='15%'><font color="red">*</font></td>
					</tr>
					<tr class="listcellrow">
					<td width='15%'>&nbsp;</td>
					    <td width="86" height="23" nowrap class="InputLabelCell">�Ա�</td>
					    <td width="383" height="23"><html:select property="vo.sex_code"  styleClass="f12">
						 <html:option value='1'>��</html:option>
						 <html:option value='0'>Ů</html:option>
						 <html:option value='2'>����</html:option>
						 </html:select>
						 </td><td width='15%'>&nbsp;</td>
					</tr>
					<tr class="listcellrow">
					<td width='15%'>&nbsp;</td>
					    <td width="86" height="23" nowrap class="InputLabelCell">E_MAIL��</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.e_mail" size="22" maxlength="20" validatetype="email" msg="email��ʽ����,��ȷ��ʽ:'xxx@xxx.xxx'"/></td><td width='15%'>&nbsp;</td>
					</tr>
					<tr class="listcellrow">
					    <td width='15%'>&nbsp;</td>
					    <td width="86" height="23" nowrap class="InputLabelCell">��ҳ��</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.home_page" size="22" maxlength="50" validatetype="url" msg="��ַ��ʽ����,��ȷ��ʽ:'http://---.---��ftp://---.---'"/></td><td width='15%'>&nbsp;</td>
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
		<html:button property="save" value="ȷ&nbsp;&nbsp;��" styleClass="button"  onclick="return doSaveExit(BBSUserForm)"/>
		<!--html:button property="exit" value="����" styleClass="button" onclick="javascript:history.back()"/></td -->
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>