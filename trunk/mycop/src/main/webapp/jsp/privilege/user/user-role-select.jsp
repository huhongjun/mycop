<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>人员角色选择</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js">
</script>

</head>

<body class="bodybg" style="overflow-x:hidden;overflow-y:hidden;border: none">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<html:form  action="/userAction">
<table align="center">
  <tr> 
    <td >&nbsp;</td>
  </tr>
  <tr> 
    <td ><table border="0" cellpadding="2" cellspacing="1">
        <tr> 
          <td colspan="2"><table border="0" cellpadding="2" cellspacing="1">
              <tr class="InputLabelCell"> 
                <td>可选角色列表:</td>
                <td valign="middle">&nbsp;</td>
                <td>已选角色列表:</td>
              </tr>
              <tr> 
                <td align="center" valign="top"> 
				<select id="addLeftListBox" name="addLeftListBox" multiple class="input2" style="width:120px; height:200px;"
				ondblclick="doSelOne('addLeftListBox','addRightListBox')" >
				  <logic:iterate id="element" name="roles_selected">
				  <option value="<bean:write name="element" property="id"/>">
				   <bean:write name="element" property="name"/>
				  </option>
				 </logic:iterate>
                  </select></td>
                <td align="center" valign="middle"> <table border="0" cellpadding="1" cellspacing="1">
                    <tr>
                      <td align="center"> <input type="button" class="button" style="width:30px;" value="&gt;" onclick="doSelOne('addLeftListBox','addRightListBox')"></td>
                    </tr>
                    <tr>
                      <td align="center"> <input name="Submit232" type="button" class="button" style="width:30px;" value="&gt&gt;" onclick="allToRight()"></td>
                    </tr>
				<tr align="center">
                      <td valign="middle"> </td>
                    </tr>
					<tr align="center">
                      <td valign="middle"> </td>
                    </tr>
                    <tr>
                      <td align="center"> <input name="Submit2232" type="button" class="button" style="width:30px;" value="&lt&lt;" onclick="allToLeft()"></td>
                    </tr>
                    <tr>
                      <td align="center"> <input type="button" class="button" style="width:30px;" value="&lt;" onclick="doSelOne('addRightListBox','addLeftListBox')"></td>
                    </tr>
                  </table></td>
                <td align="center" valign="top"> 
				<select id="addRightListBox"  name="addRightListBox" multiple class="input2" style="width:120px; height:200px;" ondblclick="doSelOne('addRightListBox','addLeftListBox')">
				 <logic:iterate id="element" name="roles_noSelect">
				  <option value="<bean:write name="element" property="id"/>">
				   <bean:write name="element" property="name"/>
				  </option>
				 </logic:iterate>
                  </select></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr> 
    <td align="center" >  <html:submit property="create" styleClass="button" value="确定" onclick="toSelectAction(this,'addRightListBox','rolesId','saveUserRole')"/>
    <html:submit  styleClass="button" property="list" value="返回" onclick="toAction(this,'listUserRoles')"/>
	<input type="hidden" id="rolesId" name="rolesId">
	<input name="uid" type="hidden"  value="<%=request.getParameter("uid")%>">
  </tr>
  <tr>
    <td align="center" >&nbsp;</td>
  </tr>
</table>
</html:form>
</body>
<%
if (true){
%>
<script language="javascript">
	clearMenu();
	function selectAllLeftList(){
		var moduleForm = document.forms[0];
	  for (i=0; i<moduleForm.addLeftListBox.options.length; i++){
		moduleForm.addLeftListBox.options[i].selected = true;
	  }
	}

	function selectAllRightList(){
		var moduleForm = document.forms[0];
	  for (i=0; i<moduleForm.addRightListBox.options.length; i++){
		moduleForm.addRightListBox.options[i].selected = true;
	  }
	}
	//移动列表中所有
    function allToRight() {
		selectAllLeftList();
		doSelAll('addLeftListBox','addRightListBox');
	}
	function allToLeft() {
		selectAllRightList();
		doSelAll('addRightListBox','addLeftListBox');
	}

</script>
<%
}
%>
