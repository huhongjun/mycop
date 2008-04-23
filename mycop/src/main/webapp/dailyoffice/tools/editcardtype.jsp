<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>
<%
	String operate = (String)session.getAttribute("operate");
	session.removeAttribute("operate");
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
function refreshTree(){
    var operate='<%=operate%>';
    if(operate=="insert"){
		window.parent.frames["lefttree"].reload('0');
    }else if(operate=="update"){
		window.parent.frames["lefttree"].reload('0');
    }else {
    }
}
//-->
</SCRIPT>

<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<body onload="refreshTree()" class="bodybg">
<html:form action="/dailyoffice/tools/editCardcaseType" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center"><span class="TableTitleText">名片类别</span></td>
      </tr>
      <tr>
        <td>&nbsp;<html:hidden name="CardcaseTypeForm" property="actionType"/></td>
      </tr>
      <tr>
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr>
				  <td>
				  <table width="500" height="36" border="0" cellpadding="2" cellspacing="1">
				  <tr>
				  <td width="20%"></td><td width="75%"></td><td width="5%"></td>
				  </tr>
           			<tr class="listcellrow">
					    <td  height="23" nowrap class="InputLabelCell">类别名称：</td>
					    <td height="23"><html:text styleClass="input" property="vo.type_name" size="22"  validatetype="notempty" msg="类别名称不能为空！" maxlength="10"/></td>

					    <td ><span class="style1">*</span></td>
           			</tr>
					<tr class="listcellrow" >
					    <td  height="23" nowrap class="InputLabelCell">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					    <td height="23" colspan="2"><html:textarea property="vo.remark"  cols="37" rows="3" styleClass="input" maxlength="30"/></td>

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
		<goa:button property="save" value="保存" styleClass="button" operation="MBJ.OPT" onclick="return doSave(CardcaseTypeForm)"/>
		<goa:button property="save" value="保存并返回" styleClass="button" operation="MBJ.OPT" onclick="return doSaveExit(CardcaseTypeForm)"/>
          <goa:button property="exit" value="返回" styleClass="button" operation="MBJ.VIEW" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
  </center>
</div>

</html:form>
</body>
