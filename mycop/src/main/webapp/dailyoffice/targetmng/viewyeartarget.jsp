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
	//注册
	var tform = document.forms(0);
	CellWeb1.Login("广东金宇恒科技有限公司","","13040330","6040-0435-0204-6005");
	CellWeb1.OpenFile(getUrl() + "<bean:write  name="YearTargetForm" property="cellname" filter="true"/>","");
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


//取消审核或审批
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
<html:form action="/dailyoffice/targetmng/viewyeartarget" method="post">
<TABLE id="idTRTop" cellpadding='0' cellspacing='0' width="100%">
  <html:hidden property="cellcontent"/>
  <html:hidden name="YearTargetForm" property="actionType"/>  
<TR>
	<TD colspan="8" align="center" class="TableTitleText"><bean:write  name="YearTargetForm" property="vo.current_year" filter="true"/>年度目标</TD>
</TR>
<TR class="listcellrow">
	<TD colspan="8" align="left">&nbsp;</TD>	
</TR>
<TR class="listcellrow">
<TD width="2%">&nbsp;</TD>
        <TD width="29%"><bean:write  name="YearTargetForm" property="unitname" filter="true"/>&nbsp;</TD>
	<TD width="40%">&nbsp;</TD>
	<TD width="30%">部门：<bean:write  name="YearTargetForm" property="deptname" filter="true"/></TD>
</TR>	
</TABLE>
<div id="divTips" style="LEFT: 0px; POSITION: relative">正在装载华表插件模块......</div>

<TABLE id="idTBBottom" style="POSITION:absolute; left: 8px; top: 199px;" cellpadding='0' cellspacing='0' width="100%">
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
	  编　　制：<bean:write  name="YearTargetForm" property="username" filter="true"/>
	</TD>
	<TD width="30%">
	  审　　核：<bean:write  name="YearTargetForm" property="auditorname" filter="true"/>	  
	</TD>
	<TD width="30%">
	  审　　批：<bean:write  name="YearTargetForm" property="checkername" filter="true"/>	  
	</TD>
     </TR>
	 <TR class="listcellrow" align="left">
	<TD width="20%">
	  编制日期：<bean:write  name="YearTargetForm" property="vo.create_date" filter="true"/>	  
	</TD>
	<TD width="30%" >
	  审核日期：	  
	  <bean:write  name="YearTargetForm" property="vo.audit_date" filter="true"/>
	</TD>
	<TD width="30%">
	  审批日期：	  
	  <bean:write  name="YearTargetForm" property="vo.check_date" filter="true"/>
	</TD>
     </TR>
     <tr>
        <td align="center" colspan="8">	
		<logic:equal name="YearTargetForm" property="isCancel" value = "1">
		 <goa:button operation="MZMB.OPT" property="cancelAudit" value="取消审核" styleClass="button"  onclick="doCancel()"/>	
		 </logic:equal>
		   <logic:equal name="YearTargetForm" property="isCancel" value = "2">
		 <goa:button operation="MZMB.OPT" property="cancelCheck" value="取消审批" styleClass="button"  onclick="doCancel()"/>	
		 </logic:equal>
         <logic:equal name="YearTargetForm" property="modifyFlag" value = "1">
          <% String temp="toUrl('"+context+"/dailyoffice/targetmng/edityeartarget.do?actionType=modify&flag=modify',false)";%>
         <goa:button operation="NDMB.OPT" property="modify" value="修改" styleClass="button"  onclick="<%=temp%>"/>
         </logic:equal>
		 <logic:equal name="YearTargetForm" property="creatorFlag" value = "2">
		 <% String temp1="toUrl('"+context+"/dailyoffice/targetmng/edityeartarget.do?actionType=modify',false)";%>
         <goa:button operation="NDMB.OPT" property="audit" value="审核" styleClass="button"  onclick="<%=temp1%>"/>
         </logic:equal>
		 <logic:equal name="YearTargetForm" property="creatorFlag" value = "3">
		 <% String temp2="toUrl('"+context+"/dailyoffice/targetmng/edityeartarget.do?actionType=modify',false)";%>
         <goa:button operation="NDMB.OPT" property="check" value="审批" styleClass="button"  onclick="<%=temp2%>"/>
         </logic:equal>
		 <goa:button operation="NDMB.VIEW" property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/>
		 <goa:button operation="NDMB.VIEW" property="exit" value="打印预览" styleClass="button" onclick="mnuFilePrintPreview_click()"/>
		 <goa:button operation="NDMB.VIEW" property="exit" value="导出到Excel文件" styleClass="button" onclick="mnuFileExportExcel_click()"/>
        </td>
        </td>
     </tr>
</TABLE>
</html:form>
</BODY>
</html:html>
