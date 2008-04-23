<%@ page contentType="text/html;charset=gb2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%
String basedata = getServletContext().getRealPath("/WEB-INF/basedata-config.xml");
String database = getServletContext().getRealPath("/WEB-INF/database-config.xml");
String dbname = (String) request.getAttribute("dbname");
String tablenames = (String) request.getAttribute("tablenames");
String filename = dbname + ".ddl";;
String exportFilePath = (String) request.getAttribute("exportFilePath");
%>
<script language="javascript">
function setType(type) {
	var baseDataForm = document.forms[0];
	var exportAllDB = "";
	var exportStructure = "";
	switch(type) {
		case '1' :
			exportAllDB = "true";
		    exportStructure = "true";
			break;
		case '2' :
			exportAllDB = "true";
		    exportStructure = "false";
			break;
		case '3' :
			exportAllDB = "false";
		   exportStructure = "true";
			break;
		case '4' :
			exportAllDB = "false";
		    exportStructure = "false";
			break;
		default :
		    break;
	}
	baseDataForm.exportAllDB.value = exportAllDB;
	baseDataForm.exportStructure.value = exportStructure;
}
function doExport() {
	var baseDataForm = document.forms[0];
	var action = baseDataForm.action;
	action +="?action=doExport";
	baseDataForm.action= action;		
	baseDataForm.submit();
}
function doBack(){
    if(navigator.appName == "Netscape"){
        window.parent.location = "/gdp/homepage/maintop.do?action=homepage";
        return;
    }
    history.back();
    return;
}
/**
function doImport() {
	var baseDataForm = document.forms[0];
	var action = baseDataForm.action;
	action +="?action=doImport";
	baseDataForm.action = action;
	baseDataForm.submit();
}
*/
</script>
<html>
<head>
<title>ϵͳ���ݵ��뵼��</title>
</head>
<jsp:include page="/jsp/jsp_css.jsp" />
<body class="bodybg">
<gmenu:getpathtag/>
<form method="post" action="BaseDataAction.do">
<input type="hidden" name="basedata" value="<%=basedata%>"/>
<input type="hidden" name="database" value="<%=database%>"/>
<input type="hidden" name="exportAllDB" value="true"/>
<input type="hidden" name="exportStructure" value="true"/>
<input type="hidden" name="filename" value="<%=filename%>"/>
<input type="hidden" name="exportFilePath" value="<%=exportFilePath%>"/>
<table width="100%" align="center" class="EditTable">
  <tr><td>&nbsp;</td>
  </tr>
  <tr> 
    <td class="EditTitle">ϵͳ���ݵ���</td>
  </tr>
  <tr> 
    <td align="center"> 
      <table width="50%" class="EditBody">
	    <tr><td>&nbsp;</td>
		</tr>
        <tr> 
          <td width="100%" align="center" class="InputLabelCell">��ǰ���ݿ�Ϊ<%=dbname%>,�ɵ��뵼�����±����ݣ�(<%=tablenames%>),���赼�����������ݣ����Ƚ������á�</td>
        </tr>
		<tr>
		</tr>
		<tr> 
          <td width="100%" align="left" class="InputLabelCell">
		  <input type="radio" name="exportType" value="1" checked onclick="if(this.checked) {setType('1')}"/>�����������ݿ⼰�ṹ</td>
        </tr>
		<tr> 
          <td width="100%" align="left" class="InputLabelCell">
		  <input type="radio" name="exportType" value="2" onclick="if(this.checked) {setType('2')}"/>�����������ݿ����ݣ��������ṹ��</td>
        </tr>
		<tr> 
          <td width="100%" align="left" class="InputLabelCell">
		  <input type="radio" name="exportType" value="3" onclick="if(this.checked) {setType('3')}"/>�����������ݿ����г����ṹ</td>
        </tr>
		<tr> 
          <td width="100%" align="left" class="InputLabelCell">
		  <input type="radio" name="exportType" value="4" onclick="if(this.checked) {setType('4')}"/>�����������ݿ����г������ݣ��������ṹ��</td>
        </tr>
		<tr>
		</tr>
        <tr>
          <td align="center">
            <perm:button styleClass="button" property="export" value="����" onclick="doExport()" rescode="GDP-JCSJ" optcode="ALL"/>
			<!--
			<html:submit styleClass="button" property="import" value="����" onclick="doImport()"/>
			-->
			<html:button styleClass="button" property="back" value="����" onclick="doBack()"/>
			
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</body>
</html>