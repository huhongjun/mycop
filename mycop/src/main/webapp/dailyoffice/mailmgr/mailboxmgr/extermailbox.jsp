<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function setMember(){
	document.getElementsByName("vo.advance_mems")[0].value=document.getElementsByName("idfiled")[0].value;
}
//-->
</SCRIPT>

<html>
<body class="bodybg">
<html:form action="/innermgr/hr/birthdayawokeedit" method="post">
  <table width="95%" height="84" border="0" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td height="37" class="TableTitleText"><div align="center">生日提醒设置</div></td>
    </tr>
	<tr>
	<td><html:hidden name="BirthdayAwokeForm" property="actionType"/></td>
	</tr>
  </table>
  <table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td><table width="56%" border="0" align="center" cellpadding="2" cellspacing="0">
        <tr>
          <td width="27%" nowrap class="InputLabelCell"><div align="right">提前提醒天数：</div></td>
          <td width="36%">
		  <html:text styleClass="input2" property="vo.advance_days" validatetype="int" msg="只可为整数"  maxlength="10"/>
		  </td>
          <td width="37%"><div align="center">
                <html:button property="sel" styleClass="button"  onclick="javascript:openSelectWindow('menselect','idfiled','namfield');" value="选择人员"/>
          </div></td>
        </tr>
        <tr>
          <td nowrap class="InputLabelCell"><div align="right">提醒人员选择：</div></td>
		  
          <td colspan="2">
		  <html:hidden property="vo.advance_mems"/>
          <textarea  name="namfield" class="input2" rows="6" cols="45%" readonly><bean:write name="BirthdayAwokeForm" property="vo.memberNames" filter="true"/></textarea>
          <input type="hidden" name="idfiled" value="<bean:write name="BirthdayAwokeForm" property="vo.advance_mems" filter="true"/>">
		  </td>
          </tr>
      </table>      </td>
    </tr>
  </table>
  <table width="95%" height="28" border="0" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <td align="center">
	  		<goa:button property="save" value="保存" styleClass="button" onclick="setMember();return doSave(BirthdayAwokeForm)"/>
		<goa:button property="save" value="保存并返回" styleClass="button" onclick="setMember();return doSaveExit(BirthdayAwokeForm)"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/>
	  </td>
    </tr>
  </table>
  </html:form>
</body>
</html>