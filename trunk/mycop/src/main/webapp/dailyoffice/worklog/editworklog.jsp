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
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/control/function.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>
<META name=VI60_defaultClientScript content=VBScript>
<SCRIPT LANGUAGE=javascript>
<!--
function window_onload() {
	CellWeb1.Login("�㶫�����Ƽ����޹�˾","","13040330","6040-0435-0204-6005");
	<logic:equal name="WorkLogForm" property="actionType" value = "add">
	CellWeb1.OpenFile(getUrl() + "<bean:write  name="WorkLogForm" property="reportTemplateName" filter="true"/>","");
	</logic:equal>
	<logic:equal name="WorkLogForm" property="actionType" value = "modify">
	CellWeb1.OpenFile( getUrl() + "<bean:write  name="WorkLogForm" property="cellname" filter="true"/>","");
	</logic:equal>
	CellWeb1.EnableUndo(1);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.display="";
}
function window.onload() {
	//ע��
	var tform = document.forms(0);
	idTBBottom.style.top = 0+ idTRTop.offsetHeight;
	divTips.style.display="none";
	window_onload();
}
function pageUndo() {	
	CellWeb1.SaveEdit();
	CellWeb1.EnableUndo(1);
	CellWeb1.Undo();

}
function getContent(cursheet){		
		var rowcount = CellWeb1.GetRows(cursheet);//��ȡ���������
		var colcount = CellWeb1.GetCols(cursheet);//��ȡ���������
		var conArr = new Array(rowcount,colcount);
		var log    = new Array(7);
		log[1] = CellWeb1.GetCellString(2,2, cursheet);
		log[2] = CellWeb1.GetCellString(2,3, cursheet);
		log[3] = CellWeb1.GetCellString(2,4, cursheet);
		log[4] = CellWeb1.GetCellString(2,5, cursheet);
		log[5] = CellWeb1.GetCellString(2,6, cursheet);
		var logTotalStr = "";        		
		for(var i=1;i<6;i++){
            logTotalStr = logTotalStr + log[i] + "||";
		}
        return logTotalStr;
	}
function getGrjb(cursheet){		
		var grjb = CellWeb1.GetCellString(2,10, cursheet);		
        return grjb;
	}
function check(isexit){//У��ҳ��
	//�жϳ����Ƿ񳬳�12
	
    var tform = document.forms(0);
	CellWeb1.SaveEdit();
	var curSheet=CellWeb1.getCurSheet();
	CellWeb1.setCurSheet(curSheet);
	var msg="";
	var content="";
	for(var idx=2;idx<10;idx++){
		content=CellWeb1.getCellString(2,idx,curSheet);
		//alert(content.length);
		if(content.length>12){
			var counter=idx-1;
			msg=msg+"��"+counter+"�����ȳ�����׼��Χ����׼����Ϊ12.\n";
		}
	}
	if(msg!=""){
		alert(msg);
		return false;
	}
	var cursheet = CellWeb1.GetCurSheet();
	CellWeb1.setCurSheet(cursheet);
	tform.logTotalStr.value = getContent(cursheet);
	tform.logGrjb.value = getGrjb(cursheet);
	if(isexit){
    savePlanExit();
	}
	else{
	savePlan();
	}
	refreshLeftWorklog();
	return true;
}
function goBack(){
	 var isback = confirm("�Ƿ񱣴沢�˳���");
	 if (isback == false) {
		 doAction('goUrl(index)');
	 }
	 else{
		 check(true);
	 }
	}
function validateContent(){
	var curSheet=CellWeb1.getCurSheet();
	CellWeb1.setCurSheet(curSheet);
	var msg="";
	var content="";
	for(var idx=2;idx<10;idx++){
		content=CellWeb1.getCellString(2,idx,curSheet);
		//alert(content.length);
		if(content.length>12){
			msg=msg+"��"+idx+"�г��ȳ�����׼��Χ����׼����Ϊ12.";
		}
	}
	if(msg!=""){
		alert(msg);
		return false;
	}
}
//ˢ����߹������Ĺ�����־��
function refreshLeftWorklog(){
	setTimeout(_refreshLeftWorklog(),1000);
}
function _refreshLeftWorklog(){
	window.top.left.f_load();
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

<TABLE id="idTRTop" cellpadding='0' cellspacing='0' width="100%" height="80%" >

<TR>
    <logic:equal name="WorkLogForm" property="actionType" value = "add">
	<TD colspan="6" align="center" class="TableTitleText">������־</TD>
	</logic:equal>
	<logic:equal name="WorkLogForm" property="actionType" value = "modify">
    <TD colspan="6" align="center" class="TableTitleText">������־</TD>
	</logic:equal>
</TR>
<TR class="listcellrow">
    <TD width="5%">&nbsp;</TD>
	<TD colspan="6" align="left"><b>��־���ԣ�</b></TD>
</TR>
<TR class="listcellrow">
    <TD width="1%">&nbsp;</TD>
	<TD width="30%">��    �ţ�<bean:write  name="WorkLogForm" property="vo.serial_num" filter="true"/></TD>
	<TD width="10%">&nbsp;</TD>
	<TD width="30%">����ʱ�䣺<bean:write  name="WorkLogForm" property="vo.create_time" filter="true"/></TD>
	<TD width="10%" align="left">&nbsp;</TD>
	<TD width="10%" align="left">&nbsp;</TD>    
</TR>
<TR class="listcellrow">
	<TD colspan="8" align="left">&nbsp;</TD>	
</TR>
<TR class="listcellrow">
    <TD width="5%">&nbsp;</TD>
	<TD colspan="6" align="left"><b>��־��Ϣ��</b></TD>	
</TR>
<TR class="listcellrow">
    <TD width="1%">&nbsp;</TD>
	<TD width="30%">��    �ţ�<bean:write  name="WorkLogForm" property="deptnames" filter="true"/></TD>
	<TD width="10%">&nbsp;</TD>
	<TD width="30%">��&nbsp;&nbsp;&nbsp;&nbsp;����<bean:write  name="WorkLogForm" property="username" filter="true"/></TD>
	<TD width="20%">��д���ڣ�<bean:write  name="WorkLogForm" property="vo.fill_date" filter="true"/></TD>	
</TR>
  <TR class="listcellrow"> 
  <TD width="5%">&nbsp;</TD>
    <TD colspan="4" height="80%" width="95%">
	<OBJECT id="CellWeb1" height="100%" width="100%" codebase="<%=request.getContextPath()%>/cell/cellweb5.cab" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
 <PARAM NAME="_Version" VALUE="65536">
 <PARAM NAME="_ExtentX" VALUE="9710">
 <PARAM NAME="_ExtentY" VALUE="4842">
 <PARAM NAME="_StockProps" VALUE="0">
</OBJECT>
	</TD>
  </TR>
</TABLE>

<div id="divTips" style="LEFT: 0px; POSITION: relative;">����װ�ػ�����ģ��......</div>

<html:form action="/dailyoffice/worklog/editworklog" method="post">
  <html:hidden name="WorkLogForm" property="cellcontent"/>
  <html:hidden name="WorkLogForm" property="actionType"/>  
  <html:hidden name="WorkLogForm" property="logTotalStr"/>  
  <html:hidden name="WorkLogForm" property="logGrjb"/>  
  <html:hidden name="WorkLogForm" property="vo.user_code"/>
<TABLE id="idTBBottom" style="POSITION:absolute; left: 44px; top: 10px;" cellpadding='0' cellspacing='0' width="90%" border="0">
     <tr>
        <td align="center" colspan="4">&nbsp;</td>
     </tr>
     <tr>
        <td align="center" colspan="4">&nbsp;</td>
     </tr>
	 <tr>
        <td align="center" colspan="4">
			<div id="advicelist" width="100%">
			<fieldset><legend  class="InputLabelCell">�������</legend>
			<table width="100%" cellpadding='0' cellspacing='0'>
			<logic:iterate id="ps" name="WorkLogForm" property="adviceList" type="com.gever.goa.dailyoffice.worklog.vo.WorkLogAdviceVO" indexId="index">
				<tr>
					<td class="InputLabelCell" nowrap width="20%">
						<bean:write name="ps" property="username"/>
						[<bean:write name="ps" property="op_date"/>]��
					</td>
					<td class="InputLabelCell">						
						<bean:write name="ps" property="advice"/>
					</td>
				</tr>
			</logic:iterate>
			</table>
			</fieldset>
			</div>
		</td>
     </tr>
     <tr>
        <td align="center" colspan="4">
	  <goa:button operation="WDLZ.OPT" property="save" value="����" styleClass="button"  onclick="check(false)"/>
	  <goa:button operation="WDLZ.OPT" property="saveAndExit" value="���沢����" styleClass="button"  onclick="check(true)"/>	 
	  <goa:button operation="WDLZ.OPT" property="reset" value="ȡ��" styleClass="button"  onclick="pageUndo()"/>	  
      <goa:button operation="WDLZ.VIEW" property="exit" value="����" styleClass="button" onclick="goBack()"/>
      </td>
	</tr>
</TABLE>
</html:form>
</BODY>
</html:html>
