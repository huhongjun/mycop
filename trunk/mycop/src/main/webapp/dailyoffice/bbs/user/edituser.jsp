<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<body class="bodybg">
<html:form action="/sample/editsample" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">ע��</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="SampleForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0" class="tablebk">
				<tr> 
				  <td bgcolor="#FFFFFF">
				  <table width="400" height="36" border="0" cellpadding="2" cellspacing="1">
                  <tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">����</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.name" size="22" validatetype="notempty" msg="���Ʋ���Ϊ�գ�" maxlength="100"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">�绰</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.tel" size="22"  maxlength="10"/></td>
					</tr>

					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">����</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.age" size="22" onlytype="int" maxlength="10"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">����</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.bdate" size="22" validatetype="date"  msg="���ڲ���,��ȷ��ʽ:'2000-01-02'"  onlytype="date" maxlength="10"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">����ʱ��</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.adddate" size="22" validatetype="time"  msg="���ڲ���,��ȷ��ʽ:'2000-01-02 '" onlytype="time" maxlength="17"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">���</td>
					    <td width="383" height="23"><html:text styleClass="input" property="vo.amt" size="22" validatetype="notempty" msg="����Ϊ�գ�" onlytype="float" maxlength="10"/></td>
					</tr>
					<tr class="listcellrow">
					     
                    <td width="86" height="100" nowrap class="InputLabelCell">��ע</td>
					     <td width="383" height="100"><html:textarea property="vo.memo" cols="37" rows="4" styleClass="input" maxlength="200"/></td>
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
		<html:button property="save" value="����" styleClass="button"  onclick="return doSave(SampleForm)"/>
		<html:button property="save" value="���沢����" styleClass="button"  onclick="return doSaveExit(SampleForm)"/>
          <html:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>