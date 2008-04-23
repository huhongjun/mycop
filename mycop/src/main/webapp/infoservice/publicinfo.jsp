<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<script language="javascript">
     
    <logic:present name="ErrorMsg" scope="request">
    alert('<bean:write name="ErrorMsg" scope="request" />');
    </logic:present>
	
</script>

<html>
<body class="bodybg">
<html:form action="/infoservice/publicinfo" method="post" enctype="multipart/form-data">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">公共信息编辑</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsInfoManageForm" property="actionType"/></td>
    </tr>
</table>

<table  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="95%" align="center">
	<tr class="listcellrow">
		<td width="15%">&nbsp;</td>		
		<td width="30%">&nbsp;</td>
		<td width="5%">&nbsp;</td>
		<td width="15%">&nbsp;</td>
		<td width="35%">&nbsp;</td>
	</tr>
    <html:hidden property="vo.info_flag" value="0"/>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>主&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
		<td nowrap>
		<html:text styleClass="input2" property="vo.title" size="22"  validatetype="notempty" msg="主题不能为空！"  maxlength="25"/><font color="red">&nbsp;*</font>
		</td>
		<td>&nbsp;</td>
		
		<td nowrap>
		
		</td>
	</tr>
	<tr class="listcellrow">
	  <td align="left" class="InputLabelCell" nowrap>类&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
	  <td colspan="4">
	  <html:select property="vo.info_type">
			<html:options collection="IsDoTypeList" property="info_type" labelProperty="info_type"/>
		</html:select>&nbsp;</td>
	  </tr>
	<tr class="listcellrow">
	    <td align="left" class="InputLabelCell" nowrap>网&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
		<td colspan="4">
		<html:text styleClass="input2" property="vo.url" size="22" maxlength="100"
		validatetype="url" msg="输入的网址格式不正确！正确格式为  http://---.---"/>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>附&nbsp;&nbsp;&nbsp;&nbsp;件：</td>
		<td colspan="4">        
		<input type="file" name ="theFile" class="input2" size="66" />
		</td>
	</tr>	
	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap valign="top">内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
		<td colspan="4">
		<html:textarea property="vo.content" rows="12" cols="75%" styleClass="input2"/>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" >级&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
		<td colspan="4">
		<html:radio property="vo.info_levle" value="0" />公司级
		<html:radio property="vo.info_levle" value="1" />个人级
		</td>
	</tr>
	<tr>
		<td colspan="5">&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<goa:button property="save" value="保存" styleClass="button"  onclick="return doSave(IsInfoManageForm)" operation="GGXX.OPT"/>
		<goa:button property="save" value="保存并返回" styleClass="button"  onclick="return doSaveExit(IsInfoManageForm)" operation="GGXX.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="GGXX.VIEW"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>