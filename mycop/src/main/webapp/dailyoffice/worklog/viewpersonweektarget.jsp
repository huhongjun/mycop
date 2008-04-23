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

<SCRIPT LANGUAGE=javascript>
<!--
function window.onload() {
	//注册
	var tform = document.forms(0);
	CellWeb1.Login("广东金宇恒科技有限公司","","13040330","6040-0435-0204-6005");
	CellWeb1.OpenFile(getUrl() + "<bean:write  name="PersonWeekTargetForm" property="cellname" filter="true"/>","");
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = idTRTop.offsetLeft;
	//华表左边设定为Table左偏移量
	CellWeb1.style.top = 110;
	//idTRTop.style.top + idTRTop.offsetHeight + 1;
	//华表顶部设定为Table的顶偏移量
	var lWidth = idTRTop.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;
	//华表样式的宽度
	var lHeight = document.body.offsetHeight - 75 - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;
	//华表样式的高度
	idTBBottom.style.top = lHeight + idTRTop.offsetHeight;
	divTips.style.display="none";
	//alert(CellWeb1.GetCurSheet());
	CellWeb1.WorkbookReadonly = true;
	CellWeb1.style.display="";
	//CellWeb1.ShowGridLine(0,CellWeb1.GetCurSheet());
}

//取消审核
function doCancel(){
//	alert("hello");
	var tform = document.forms(0);
	tform.elements["actionType"].value="modify";	
    savePlanExit();
	return true;
}

//-->
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
<html:form action="/dailyoffice/worklog/viewpersonweektarget" method="post">
<TABLE id="idTRTop" cellpadding='0' cellspacing='0' width="100%">
  <html:hidden property="cellcontent"/>
  <html:hidden name="PersonWeekTargetForm" property="actionType"/>  
<TR>
	<TD colspan="10" align="center" class="TableTitleText">个人周汇报</TD>
</TR>
<TR class="listcellrow">
	<TD colspan="10" align="left">&nbsp;</TD>	
</TR>
<TR class="listcellrow">
	<TD width="5%" align="left">&nbsp;</TD>
	<TD width="20%">部    门：<bean:write  name="PersonWeekTargetForm" property="deptname" filter="true"/></TD>
	<TD width="20%">岗    位：<bean:write  name="PersonWeekTargetForm" property="postname" filter="true"/></TD>
	<TD width="25%">姓    名：<bean:write  name="PersonWeekTargetForm" property="username" filter="true"/></TD>	
	<TD width="30%" align="left">
	<bean:write  name="PersonWeekTargetForm" property="vo.current_year" filter="true"/>
	年
	<bean:write  name="PersonWeekTargetForm" property="vo.current_month" filter="true"/>	
	月
	<bean:write  name="PersonWeekTargetForm" property="vo.current_work" filter="true"/>	
	周
	</td>	
</TR>
</TABLE>
<div id="divTips" style="LEFT: 0px; POSITION: relative">正在装载华表插件模块......</div>

<TABLE id="idTBBottom"  style="POSITION:absolute; left: 0px; top: 250px;" cellpadding='0' cellspacing='0' width="100%">
     <tr>
        <td align="center" colspan="10">&nbsp;</td>
     </tr>
     <tr>
        <td align="center" colspan="10">&nbsp;</td>
     </tr>
	 <tr>
        <td align="center" colspan="10">&nbsp;</td>
     </tr>

     <TR class="listcellrow" >
	<TD  align="left">编制：<bean:write  name="PersonWeekTargetForm" property="username" filter="true"/></TD>
	<TD width="30%">
	  审　　核：<bean:write  name="PersonWeekTargetForm" property="auditorname" filter="true"/>	  
	</TD>
	<TD width="30%" align="right">编制日期：<bean:write  name="PersonWeekTargetForm" property="vo.create_date" filter="true"/></TD>	
	<TD width="20%">&nbsp;</TD>	
     </TR>
     <tr>
        <td align="center" colspan="10">
<% 
String 
temp1="toUrl('"+context+"/dailyoffice/worklog/editpersonweektarget.do?actionType=modify',false)";
%>
<% 
String 
temp2="toUrl('"+context+"/dailyoffice/worklog/editpersonweektarget.do?actionType=modify&modifyflag=true',false)";
%>
		  <logic:equal name="PersonWeekTargetForm" property="isCancel" value = "1">
		 <goa:button operation="LZMB.OPT" property="save" value="取消审核" styleClass="button"  onclick="doCancel()"/>	
		 </logic:equal>
		<logic:equal name="PersonWeekTargetForm" property="auditFlag" value = "1">   
		 <goa:button operation="LZMB.OPT" property="save" value="审核" styleClass="button"  onclick="<%=temp1%>"/>	
		 </logic:equal>
         <logic:equal name="PersonWeekTargetForm" property="creatorFlag" value = "1">
         <goa:button operation="LZMB.OPT" property="save" value="修改" styleClass="button"  onclick="<%=temp2%>"/>
		 </logic:equal>
		 <goa:button operation="LZMB.VIEW" property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/>
		 <goa:button operation="LZMB.VIEW" property="printpre" value="打印预览" styleClass="button" onclick="mnuFilePrintPreview_click()"/>
		 <goa:button operation="LZMB.VIEW" property="export" value="导出到Excel文件" styleClass="button" onclick="mnuFileExportExcel_click()"/>
        </td>
     </tr>
</TABLE>
</html:form>
</BODY>
</html:html>
