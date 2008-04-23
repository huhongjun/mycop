<%@ page language="java" %>

<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<jsp:include page="/jsp/jsp_css.jsp" />
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function checkInput(){
		var mForm = document.forms[0];

		if (mForm.elements["vo.nodename"].value == ""){
			alert("请输入栏目名称！");
			return false;
        }
		var action = mForm.action;
		action +="?action=doEditMenu";
		mForm.action= action;
		mForm.submit();
		return true;
		
	}
	var context = "<gdp:context/>";
	function doBack(){
		var moduleForm = document.forms[0];

		var action = "";
		action +=context+"/menusetup/right.do?action=toHome&nodeid=-1";;
		moduleForm.action= action;
		
		moduleForm.submit();
	}
//-->
</SCRIPT>
 <html:form action="/menusetup/edit" onsubmit="return false">
</p>
<table align="center" width="80%">
<tr>
      <td align="center"> 
        <table width="95%">
		<tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">编辑菜单</td>
  </tr>
          <tr> 
            <td class="InputLabelCell"> <html:hidden name="MenuSetupForm" property="nodeid"/>
		  <html:hidden name="MenuSetupForm" property="nodename"/>
			<html:hidden  name="MenuSetupForm" property="isFolder"/>&nbsp;</td>
            <td class="f12b">&nbsp;</td>
          </tr>
          <tr> 
            <td class="InputLabelCell">上级栏目：</td>
            <td class="f12"><bean:write name="MenuSetupForm" property="pvo.nodename"/><html:hidden name="MenuSetupForm" property="pvo.nodename"/><html:hidden name="MenuSetupForm" property="pvo.nodeid"/><html:hidden property="actionFlag"/></td>
          </tr>
          <tr> 
            <td class="InputLabelCell">栏目名称：</td>
            <td class="f12"><html:text property="vo.nodename" styleClass="input2" size='35' maxlength="8" /><font color="#FF0000">*</font> 
			<br><font class="f12">（建议不要超过4个汉字）</font></td>
          </tr>
          <tr> 
            <td> </td>
            <td height="22"> <p> <html:button styleClass="button"  property="delete" onclick="checkInput()" value="确定"/> 
			   <html:button styleClass="button"  property="back" onclick="javascript:doBack();" value="返回"/>
              </p></td>
          </tr>
        </table>
      </td>
</tr>
</table>
</html:form>
<SCRIPT LANGUAGE="JavaScript">
<!--
 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
//-->
</SCRIPT>