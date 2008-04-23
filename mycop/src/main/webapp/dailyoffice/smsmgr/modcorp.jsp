<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>设置短信标语</title>
</head>
<script language="javascript">
var isClose = false;
<% String flag = (String)request.getAttribute("saveflag");
	if ("true".equals(flag)){%>
		isClose = true;
<%	}%>
	if (isClose== true){
		window.close();
}
//保存
function doSave(mform){
	if (validater(mform) == false){
		return false;
	}
	var sForm = document.forms[0];
	var sAction = sForm.action;
	var bRet = true;
	try{
		bRet = checkInput() ;
		if (bRet ==false){
			return false;
		}
	}catch(e){
	}

	if (sForm.elements["actionType"].value=="modify"){
		sAction = addPara(sAction, "action=update");
	} else {
		sAction = addPara(sAction, "action=insert");
	}
	sForm.action = sAction;
	disButton();
	sForm.submit();

}

</script>
<body class="bodybg">

<table width="430" align="center">
  <html:form action="/dailyoffice/smsmgr/modcorp" >
<table width="480" border="0" align="center" cellpadding="2" cellspacing="1">
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4">&nbsp;</td>
  </tr>
  <tr align="center">
    <td colspan="4" class="TableTitleText">更改手机短信标语</td>
  </tr>
  <tr>
    <td width="25%">&nbsp;</td>
	<td colspan="3" width="75%">&nbsp;</td>
  </tr>

  <tr>
    <td nowrap class="InputLabelCell" align="right" >标语:</td>
    <td colspan="3" ><html:text styleClass="input2" property="vo.bz" size="40"  maxlength="10"/><font color="red">*</td>
    </td>
  </tr>
  
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center">
	<goa:button property="save" value="保存" styleClass="button"  onclick="return doAction('doModCorp')" operation="DWXX.OPT"/>&nbsp;<html:reset styleClass="button" property="reset" value="重置" />&nbsp;<html:button property="close" value="关闭" styleClass="button" onclick="javascript:window.close();" />
    </td>
  </tr>
</table>
</html:form>
</table>

</body>
</html>
