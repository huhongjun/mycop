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
	initForm();
	var tform = document.forms(0);
	CellWeb1.Login("广东金宇恒科技有限公司","","13040330","6040-0435-0204-6005");
	<logic:equal name="TargetForm" property="actionType" value = "add">
		CellWeb1.OpenFile(getUrl() + "<bean:write  name="TargetForm" property="reportTemplateName" filter="true"/>","");
	</logic:equal>
	<logic:equal name="TargetForm" property="actionType" value = "modify">
		CellWeb1.OpenFile( getUrl() + "<bean:write  name="TargetForm" property="cellname" filter="true"/>","");
	</logic:equal>
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = idTRTop.offsetLeft;
	//华表左边设定为Table左偏移量1
	CellWeb1.style.top = 105
	//idTRTop.style.top + idTRTop.offsetHeight + 1;
	//华表顶部设定为Table的顶偏移量
	var lWidth = idTRTop.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;
	//华表样式的宽度
	var lHeight = document.body.offsetHeight - 100 - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;
	//华表样式的高度
	idTBBottom.style.top = lHeight + idTRTop.offsetHeight;
	divTips.style.display="none";
	<logic:equal name="TargetForm" property="isReadOnlyTemplateFlag" value = "0">
	CellWeb1.WorkbookReadonly = true;
    </logic:equal>
	//CellWeb1.ShowHScroll(0,0);
	//CellWeb1.Readonly = true;
	CellWeb1.style.display="";
}
function initForm(){//初始化页面
	var tform = document.forms(0);
	if(tform.actionType.value == "add"){//如果当前action为新增时-将审核审批人input置空
		//tform.mbn.readonly = true;
		tform.auditorname.value="";
        tform.checkername.value="";
	}		
}

function check(){
	var tform = document.forms(0);
	//周报告时才判断
	<logic:equal name="TargetForm" property="typeFlag" value="1">
	if(tform.elements("vo.current_work").value==""){
		alert("请填写周！");
		tform.elements("vo.current_work").focus();
		return false;
	}
	<logic:equal name="TargetForm" property="actionType" value="add">
	if(tform.elements("vo.current_month").value>12||
	   tform.elements("vo.current_month").value<-1){
		alert("请保证月份在1－12之间");
		tform.elements("vo.current_month").focus();
		return false;
	}
	</logic:equal>
	if(tform.elements("vo.current_work").value>5 || tform.elements("vo.current_work").value<1){
		
		alert("请保证周数在1-5之间！");
		tform.elements("vo.current_work").focus();
		return false;
	}
	</logic:equal>
	//月报告时才判断
	<logic:equal name="TargetForm" property="typeFlag" value="2">
	if(tform.elements("vo.current_month").value>12){
		alert("请保证月份在1－12之间");
		tform.elements("vo.current_month").focus();
		return false;
	}
	</logic:equal>
	if(tform.elements("auditorname").value==""){
		alert("请输入审核人");
		return false;
	}
	if(tform.elements("checkername").value==""){
		alert("请输入审批人");
		return false;
	}
}
//保存并返回
function saveExit(){//校验页面--通过后提交
	if(check()==false){
		return false;
	}
    savePlanExit();
	return true;
}
function pageUndo() {	      
	CellWeb1.Undo();
}
//保存动作
function toSave(){
	if(check()==false){
		return false;
	}
	savePlan();
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
<html:form action="/dailyoffice/reportmgr/edittarget" method="post">
<TABLE id="idTRTop" cellpadding='0' cellspacing='0' width="100%">
  <html:hidden property="cellcontent"/>
  <html:hidden name="TargetForm" property="actionType"/> 
  <html:hidden name="TargetForm" property="auditorid"/> 
  <html:hidden name="TargetForm" property="checkerid"/>   
  <html:hidden name="TargetForm" property="vo.user_code"/>
<TR>   
	<!--新增-->
    <logic:equal name="TargetForm" property="actionType" value = "add">
		<TD colspan="5" align="center" class="TableTitleText">
		<logic:notEqual name="TargetForm" property="typeFlag" value="3">
			<bean:write  name="TargetForm" property="vo.current_year" filter="true"/>年</logic:notEqual><logic:equal name="TargetForm" property="typeFlag" value="3"><html:text styleClass="input9" name="TargetForm"  property="vo.current_year" onlytype="int" size="4" maxlength="4"/></logic:equal><logic:lessEqual name="TargetForm" property="typeFlag" value="2"><html:text  styleClass="input9" property="vo.current_month"  onlytype="int" size="2" maxlength="2"/>月</logic:lessEqual><logic:equal name="TargetForm" property="typeFlag" value="1"><html:text styleClass="input9" property="vo.current_work" onlytype="int" size="2" maxlength="1"/>周</logic:equal><bean:write name="TargetForm" property="targetTypeName" filter="true"/></TD>
	</logic:equal>
	<!--修改-->
	<logic:equal name="TargetForm" property="actionType" value = "modify">
		<TD colspan="8" align="center" class="TableTitleText">
		<bean:write  name="TargetForm" property="vo.current_year" filter="true"/><logic:notEqual name="TargetForm" property="typeFlag" value="3">年</logic:notEqual><logic:equal name="TargetForm" property="typeFlag" value="2"><html:text styleClass="input9" name="TargetForm" property="vo.current_month" size="2" maxlength="2" onlytype="int"/>月</logic:equal><logic:notEqual name="TargetForm" property="typeFlag" value="2"><logic:notEqual name="TargetForm" property="typeFlag" value="3"><bean:write  name="TargetForm" property="vo.current_month" filter="true"/>月</logic:notEqual></logic:notEqual><logic:equal name="TargetForm" property="typeFlag" value="1"><html:text styleClass="input9"  property="vo.current_work" onlytype="int" size="2" maxlength="1"/>周</logic:equal><bean:write name="TargetForm" property="targetTypeName" filter="true"/>
		</TD>
	</logic:equal>
</TR>
<TR class="listcellrow">
	<TD colspan="8" align="left">&nbsp;</TD>	
</TR>
<TR class="listcellrow">
    <TD width="1%">&nbsp;</TD>
    <TD width="29%"><bean:write  name="TargetForm" property="unitname" filter="true"/>&nbsp;</TD>
	<TD width="40%">&nbsp;</TD>
	<TD width="30%" align="left"></TD>
</TR>	
</TABLE>
<div id="divTips" style="LEFT: 0px; POSITION: relative">正在装载华表插件模块......</div>

<TABLE id="idTBBottom" style="POSITION:absolute; left: 5px; top: 199px;" cellpadding='0' cellspacing='0' width="100%">
     <tr>
        <td align="center" colspan="8">&nbsp;</td>
     </tr>
	 <tr>
        <td align="center" colspan="8">&nbsp;</td>
     </tr>
     <TR>
	<TD colspan="8" align="left">&nbsp;</TD>	
     </TR>
     <logic:equal name="TargetForm" property="auditFlag" value = "1">
     <TR class="listcellrow">
	 <TD colspan="8" align="left">
	 审核意见：<html:text styleClass="input2" property="vo.audit_opinion" size="100" maxlength="100"/>
	 <logic:equal name="TargetForm" property="auditFlag" value = "1">
		  <input type="radio" name="vo.audit_flag" value="1" width="15%" checked>通过
		  </logic:equal>
		  <logic:equal name="TargetForm" property="checkFlag" value = "1">
		  <input type="radio" name="vo.check_flag" value="1"  width="15%" checked>通过
		  </logic:equal>&nbsp;
		  <logic:equal name="TargetForm" property="auditFlag" value = "1">
		  <input type="radio" name="vo.audit_flag" value="0" width="15%" >不通过	 
		  </logic:equal>
		  <logic:equal name="TargetForm" property="checkFlag" value = "1">
		  <input type="radio" name="vo.check_flag" value="0" width="15%" >不通过	 
		  </logic:equal>&nbsp;
	 </TD>	
     </TR>
     </logic:equal>
	 <logic:equal name="TargetForm" property="checkFlag" value = "1">
	 <TR class="listcellrow">
	 <TD colspan="8" align="left">审批意见：<html:text styleClass="input2" property="vo.check_opinion" size="99" maxlength="100"/>
	 <logic:equal name="TargetForm" property="auditFlag" value = "1">
		  <input type="radio" name="vo.audit_flag" value="1" checked>通过
		  </logic:equal>
		  <logic:equal name="TargetForm" property="checkFlag" value = "1">
		  <input type="radio" name="vo.check_flag" value="1" checked>通过
		  </logic:equal>&nbsp;
		  <logic:equal name="TargetForm" property="auditFlag" value = "1">
		  <input type="radio" name="vo.audit_flag" value="0" >不通过	 
		  </logic:equal>
		  <logic:equal name="TargetForm" property="checkFlag" value = "1">
		  <input type="radio" name="vo.check_flag" value="0" >不通过	 
		  </logic:equal>&nbsp;
	 </TD>	
     </TR>
	 </logic:equal>     
     <TR class="listcellrow" align="left">
		<TD width="30%">
		编　　制：<bean:write  name="TargetForm" property="username" filter="true"/>	  
		</TD>
		<TD width="40%" >  
		  审　　核：
          <logic:equal name="TargetForm" property="editFlag" value = "1">
		  <html:text property="auditorname" size="10" readonly="true"  styleClass="input2"/>	</logic:equal>
		  <logic:equal name="TargetForm" property="editFlag" value = "0">
		  <html:hidden  name="TargetForm" property="auditorname" />
		  <bean:write  name="TargetForm" property="auditorname" filter="true"/>
          </logic:equal>
		  &nbsp;
		  <logic:equal name="TargetForm" property="editFlag" value = "1">
		  <input type="button" class="button" onclick="javascript:openSelectWindow('manselect','auditorid','auditorname');" value="选择">
		  </logic:equal>
		</TD>
		<TD width="30%" align="left">
		  审　　批：
		  <logic:equal name="TargetForm" property="editFlag" value = "1">
		  <html:text property="checkername" size="10" readonly="true"  styleClass="input2"/> 
		  </logic:equal>
		  <logic:equal name="TargetForm" property="editFlag" value = "0">
		  <html:hidden  name="TargetForm" property="checkername" />
		  <bean:write  name="TargetForm" property="checkername" filter="true"/>
		  </logic:equal>
		  &nbsp;
		  <logic:equal name="TargetForm" property="editFlag" value = "1">
		  <input type="button" class="button" onclick="javascript:openSelectWindow('manselect','checkerid','checkername');" value="选择">
		  </logic:equal>
		</TD>
		
     </TR>
     <TR>
     </TR>
	 <TR class="listcellrow" align="left">
		<TD width="20%">
		编制日期：<bean:write  name="TargetForm" property="vo.create_date" filter="true"/>
		</TD>
		<TD width="30%" >
		审核日期：<bean:write  name="TargetForm" property="vo.audit_date" filter="true"/>
		</TD>
		<TD width="40%" align="left">
		审批日期：<bean:write  name="TargetForm" property="vo.check_date" filter="true"/>
		</TD>
		
     </TR>
     <tr>
        <td align="center" colspan="8">
	  <goa:button operation="MZMB.OPT" property="save" value="保存" styleClass="button"  onclick="toSave()"/>	
	  <goa:button operation="MZMB.OPT" property="saveAndExit" value="保存并返回" styleClass="button"  onclick="saveExit()"/>	  
	  <goa:button operation="MZMB.OPT" property="reset" value="取消" styleClass="button"  onclick="pageUndo()"/>
          <goa:button operation="MZMB.VIEW" property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
     </tr>
</TABLE>
</html:form>
</BODY>
</html:html>
