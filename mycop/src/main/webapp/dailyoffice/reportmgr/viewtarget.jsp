<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%
String context =request.getContextPath();
%>
<html:html>
<head>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/control/function.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/control/buttons.js"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>

<SCRIPT LANGUAGE=javascript>
<!--
function window.onload() {
	//ע��
	var tform = document.forms(0);
	CellWeb1.Login("�㶫�����Ƽ����޹�˾","","13040330","6040-0435-0204-6005");
	CellWeb1.OpenFile(getUrl() + "<bean:write  name="TargetForm" property="cellname" filter="true"/>","");
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = idTRTop.offsetLeft;
	//��������趨ΪTable��ƫ����
	CellWeb1.style.top = 105;
	//idTRTop.style.top + idTRTop.offsetHeight + 1;
	//�������趨ΪTable�Ķ�ƫ����
	var lWidth = idTRTop.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;
	//������ʽ�Ŀ��
	var lHeight = document.body.offsetHeight - 100 - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;
	//������ʽ�ĸ߶�
	idTBBottom.style.top = lHeight + idTRTop.offsetHeight;
	divTips.style.display="none";
	//alert(CellWeb1.GetCurSheet());
	CellWeb1.WorkbookReadonly = true;
	CellWeb1.style.display="";
	//CellWeb1.ShowGridLine(0,CellWeb1.GetCurSheet());
}
//ȡ����˻�����
function doCancel(){
//	alert("hello");
	var tform = document.forms(0);
	tform.elements["actionType"].value="modify";	
    savePlanExit();
	return true;
}

//-->
</SCRIPT>	

<!--BUTTON-->
<SCRIPT FOR="cbButton" EVENT="onmousedown()"	LANGUAGE="JavaScript" >
	return onCbMouseDown(this);
</SCRIPT>

<SCRIPT FOR="cbButton" EVENT="onclick()"		LANGUAGE="JavaScript" >
	return onCbClickEvent(this);
</SCRIPT>

<SCRIPT FOR="cbButton" EVENT="oncontextmenu()"	LANGUAGE="JavaScript" >
	return(event.ctrlKey);
</SCRIPT>


</HEAD>
<BODY id="mainbody" class="bodybg">
<br>
<OBJECT id="CellWeb1" codebase="<%=request.getContextPath()%>/cell/cellweb5.cab" style="display: none; POSITION:absolute"
 classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
 <PARAM NAME="_Version" VALUE="65536">
 <PARAM NAME="_ExtentX" VALUE="9710">
 <PARAM NAME="_ExtentY" VALUE="4842">
 <PARAM NAME="_StockProps" VALUE="0">
</OBJECT>
<html:form action="/dailyoffice/reportmgr/viewtarget" method="post">
<TABLE id="idTRTop" cellpadding='0' cellspacing='0' width="100%">
  <html:hidden property="cellcontent"/>
  <html:hidden name="TargetForm" property="actionType"/>  
<TR>
    <TD colspan="8" align="center" class="TableTitleText">
	<bean:write  name="TargetForm" property="vo.current_year" filter="true"/><logic:notEqual name="TargetForm" property="typeFlag" value="3">��</logic:notEqual><logic:lessThan name="TargetForm" property="typeFlag" value="3"><bean:write  name="TargetForm" property="vo.current_month" filter="true"/>��<logic:equal name="TargetForm" property="typeFlag" value="1"><bean:write  name="TargetForm" property="vo.current_work" filter="true"/>��</logic:equal></logic:lessThan><bean:write name="TargetForm" property="targetTypeName" filter="true"/>
	</TD>	
</TR>
<TR class="listcellrow">
	<TD colspan="8" align="left">&nbsp;</TD>	
</TR>
<TR class="listcellrow">
    <TD width="1%">&nbsp;</TD>
    <TD width="29%"><bean:write  name="TargetForm" property="unitname" filter="true"/>&nbsp;</TD>
	<TD width="40%">&nbsp;</TD>
	<TD width="30%"></TD>
</TR>	
</TABLE>
<div id="divTips" style="LEFT: 0px; POSITION: relative">����װ�ػ�����ģ��......</div>

<TABLE id="idTBBottom" style="POSITION:absolute; left: 5px; top: 199px;" cellpadding='0' cellspacing='0' width="100%">
     <tr>
        <td align="center" colspan="4">&nbsp;</td>
     </tr>
     <tr>
        <td align="center" colspan="4">&nbsp;</td>
     </tr>
	 <tr>
        <td align="center" colspan="4">&nbsp;</td>
     </tr>
     <TR class="listcellrow" align="left">
	<TD width="20%">
	  �ࡡ���ƣ�<bean:write  name="TargetForm" property="username" filter="true"/>
	</TD>
	<TD width="30%">
	  �󡡡��ˣ�<bean:write  name="TargetForm" property="auditorname" filter="true"/>	  
	</TD>
	<TD width="30%">
	  �󡡡�����<bean:write  name="TargetForm" property="checkername" filter="true"/>	  
	</TD>
     </TR>
	 <TR class="listcellrow" align="left">
		<TD width="30%">
		�������ڣ�<bean:write  name="TargetForm" property="vo.create_date" filter="true"/>
		</TD>
		<TD width="40%" >
		������ڣ�<bean:write  name="TargetForm" property="vo.audit_date" filter="true"/>
		</TD>
		<TD width="30%">
		�������ڣ�<bean:write  name="TargetForm" property="vo.check_date" filter="true"/>
		</TD>
     </TR>
     <tr>
        <td align="center" colspan="8">	  
		  <logic:equal name="TargetForm" property="isCancel" value = "1">
		 <goa:button operation="MZMB.OPT" property="save" value="ȡ�����" styleClass="button"  onclick="doCancel()"/>	
		 </logic:equal>
		   <logic:equal name="TargetForm" property="isCancel" value = "2">
		 <goa:button operation="MZMB.OPT" property="save" value="ȡ������" styleClass="button"  onclick="doCancel()"/>	
		 </logic:equal>
        <logic:equal name="TargetForm" property="modifyFlag" value = "1">
		 <goa:button operation="MZMB.OPT" property="save" value="�޸�" styleClass="button"  onclick=" toUrl('/goa/dailyoffice/reportmgr/edittarget.do?actionType=modify&flag=modify',false)"/>	
		 </logic:equal>
		 <logic:equal name="TargetForm" property="creatorFlag" value = "2">   
		 <goa:button operation="MZMB.OPT" property="save" value="���" styleClass="button"  onclick=" toUrl('/goa/dailyoffice/reportmgr/edittarget.do?actionType=modify',false)"/>	</logic:equal>
		 <logic:equal name="TargetForm" property="creatorFlag" value = "3">   
		 <goa:button operation="MZMB.OPT" property="save" value="����" styleClass="button"  onclick=" toUrl('/goa/dailyoffice/reportmgr/edittarget.do?actionType=modify',false)"/>	</logic:equal>
         <goa:button operation="MZMB.VIEW" property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')"/>
		 <goa:button operation="MZMB.VIEW" property="exit" value="��ӡԤ��" styleClass="button" onclick="mnuFilePrintPreview_click()"/>
		 <goa:button operation="MZMB.VIEW" property="exit" value="������Excel�ļ�" styleClass="button" onclick="mnuFileExportExcel_click()"/>
        </td>
     </tr>
</TABLE>
</html:form>
</BODY>
</html:html>
