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
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>
<META name=VI60_defaultClientScript content=VBScript>

<SCRIPT LANGUAGE=javascript>
<!--
function window_onload() {
	CellWeb1.Login("广东金宇恒科技有限公司","","13040330","6040-0435-0204-6005");
	CellWeb1.OpenFile(getUrl() + "<bean:write  name="WorkLogForm" property="cellname" filter="true"/>","");
	CellWeb1.EnableUndo(1);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.WorkbookReadonly = true;
	CellWeb1.style.display="";

}
function window.onload() {
	//注册
	var tform = document.forms(0);
	idTBBottom.style.top = idTRTop.offsetHeight;
	divTips.style.display="none";
	window_onload();

}

//-->
</SCRIPT>	

<!--BUTTON-->
</HEAD>
<BODY id="mainbody" class="bodybg">
<TABLE id="idTRTop" cellpadding='0' cellspacing='0' width="100%" height="80%" border="0">
  <TR> 
    <TD colspan="6" align="center" class="TableTitleText">工作日志</TD>
  </TR>
  <TR class="listcellrow"> 
    <TD width="10%">&nbsp;</TD>
    <TD colspan="6" align="left"><b>日志属性：</b></TD>
  </TR>
  <TR class="listcellrow"> 
    <TD width="10%">&nbsp;</TD>
    <TD width="30%" >编 号：<bean:write  name="WorkLogForm" property="vo.serial_num" filter="true"/></TD>
    <TD width="5%">&nbsp;</TD>
    <TD width="30%" >创建时间：<bean:write  name="WorkLogForm" property="vo.create_time" filter="true"/></TD>
    <TD width="30%">&nbsp;</TD>
    <TD width="10%">&nbsp;</TD>
  </TR>
  <TR class="listcellrow"> 
    <TD width="10%">&nbsp;</TD>
    <TD colspan="6" align="left"><b>日志信息：</b></TD>
  </TR>
  <TR class="listcellrow"> 
    <TD width="10%">&nbsp;</TD>
    <TD width="30%" >部 门：<bean:write  name="WorkLogForm" property="deptnames" filter="true"/></TD>
    <TD width="5%">&nbsp;</TD>
    <TD width="30%" >姓&nbsp;&nbsp;&nbsp;&nbsp;名：<bean:write  name="WorkLogForm" property="username" filter="true"/></TD>
    <TD width="30%" >填写日期：<bean:write  name="WorkLogForm" property="vo.fill_date" filter="true"/></TD>
  </TR>
  <TR class="listcellrow"> 
  <TD width="10%">&nbsp;</TD>
    <TD colspan="4" height="80%" width="90%">
	<OBJECT id="CellWeb1" height="100%" width="100%" codebase="<%=request.getContextPath()%>/cell/cellweb5.cab" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
	 <PARAM NAME="_Version" VALUE="65536">
	<PARAM NAME="_ExtentX" VALUE="9710">
	<PARAM NAME="_ExtentY" VALUE="4842">
	<PARAM NAME="_StockProps" VALUE="0">
    </OBJECT>
	</TD>
  </TR>
</TABLE>
<div id="divTips" style="LEFT: 0px; POSITION: relative">正在装载华表插件模块......</div>
<html:form action="/dailyoffice/worklog/viewworklog" method="post">
  <html:hidden name="WorkLogForm" property="cellcontent"/> 
  <html:hidden name="WorkLogForm" property="actionType"/> 

<TABLE id="idTBBottom"  cellpadding='0' cellspacing='0' width="100%" border="0">
     <tr>
    <TD width="10%">&nbsp;</TD>
        <td align="center" colspan="4">&nbsp;</td>
     </tr>
    <TD width="10%">&nbsp;</TD>
        <td align="center" colspan="4">
<logic:equal name="WorkLogForm" property="editAdviceFlag" value = "1">
		<div id="advicelist" width="100%">
			<fieldset><legend  class="InputLabelCell">反馈意见</legend>
				<table width="100%" cellpadding='0' cellspacing='0' ><tr> 
					  <td class="bodybg" align=right colspan="2">
						<span><strong>普通文本留言</strong>(留言不要超过500个字)剩余字数: <input name=remLen value=500 readonly type=text size=3 maxlength=3 class="bodybg" style="border: 0; color: red"></span>&nbsp;
					  </td>
					</tr>
					<tr>
						<td class="InputLabelCell">
							<textarea style="width:100%" name=words class="input2" onPropertyChange="textCounter('words', 500)"><bean:write name="WorkLogForm" property="adviceWords"/></textarea>
						</td>
					</tr>
				</table>
			</fieldset>
			</div>
</logic:equal>
<logic:equal name="WorkLogForm" property="editAdviceFlag" value = "0">
			<div id="advicelist" width="100%">
			<fieldset><legend  class="InputLabelCell">反馈意见</legend>
			<table width="100%" cellpadding='0' cellspacing='0'>
			<logic:iterate id="ps" name="WorkLogForm" property="adviceList" type="com.gever.goa.dailyoffice.worklog.vo.WorkLogAdviceVO" indexId="index">
				<tr>
					<td class="InputLabelCell" nowrap width="20%">
						<bean:write name="ps" property="username"/>
						[<bean:write name="ps" property="op_date"/>]：
					</td>
					<td class="InputLabelCell">						
						<bean:write name="ps" property="advice"/>
					</td>
				</tr>
			</logic:iterate>
			</table>
			</fieldset>
			</div>
</logic:equal>
		</td>
     </tr>
     <tr>
    <TD width="10%">&nbsp;</TD>
        <td align="center" colspan="4">
		<logic:equal name="WorkLogForm" property="isTodayWorkLogFlag" value = "1">
<% 
String 
temp="toUrl('"+context+"/dailyoffice/worklog/editworklog.do?actionType=modify',false)";
%>
		 <goa:button operation="WDLZ.OPT" property="save" value="修改" styleClass="button"  onclick="<%=temp%>"/>
		 </logic:equal>
		 <!--
         <goa:button operation="WDLZ.OPT" property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/>
		 -->
<logic:equal name="WorkLogForm" property="editAdviceFlag" value = "1">
		 <goa:button operation="WDLZ.VIEW" property="submit" value="提交意见" styleClass="button" onclick="sumbitAdvice()"/>
</logic:equal>
        <goa:button operation="WDLZ.VIEW" property="exit" value="返回" styleClass="button" onclick="javascript:history.back(-1)"/>
        </td>
     </tr>
</TABLE>
</html:form>
<form id="adviceForm" name="adviceForm" action="/gdca/dailyoffice/worklog/saveworklogadvice.do">
<input type="hidden" name="adviceWords" id="adviceWords">
<input type="hidden" name="type" id="type">
</form>
<logic:equal name="WorkLogForm" property="editAdviceFlag" value = "1">
<SCRIPT LANGUAGE="JavaScript">
<!--
String.prototype.trim = function()
{
    // 用正则表达式将前后空格用空字符串替代。
    return this.replace( /(^\s*)|(\s*$)/g, "" );
}

function textCounter(fieldname, maxlimit) { 
	var field = document.getElementById(fieldname);
	//alert(fieldname + ": " + field);
	var remLen = document.getElementById("remLen");
	var submitBtn = document.getElementById("submit");
	if(field.value.trim().length > 0) {
		submitBtn.disabled = false;
	} else {
		submitBtn.disabled = true;
	}
	if (field.value.length > maxlimit) 
		field.value = field.value.substring(0, maxlimit); 
	else 
		remLen.value = maxlimit - field.value.length;
}
function sumbitAdvice() {
	var adviceForm = document.forms['adviceForm'];
	var action = adviceForm.action;
	var advice = document.getElementById("adviceWords");
	var words = document.getElementById("words");
	var type = document.getElementById("type");
	advice.value = words.value
	alert('adviceForm.action: ' + action 
		+ '\n advice: ' + advice.value
		+ '\n type: ' + type.value);
	adviceForm.submit();
}
function init() {
	var adviceForm = document.forms['adviceForm'];
	var action = adviceForm.action;
	var type = document.getElementById("type");
	var submitBtn = document.getElementById("submit");
		submitBtn.disabled = true;
	var words = document.getElementById("words");
	if(words.value.length > 0) {
		type.value = "modify";
	} else {		
		type.value = "new";
	}
	adviceForm.action = action;
}
init();
//-->
</SCRIPT>
</logic:equal>
</BODY>
</html:html>
