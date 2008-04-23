<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>


<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/control/function.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/control/buttons.js"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>

<!--HTML模板资源-->
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/toolbar.css">
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/editor.css">
<script src="<%=context%>/common/htmleditor/js/toolbar.js"></script>
<script src="<%=context%>/common/htmleditor/js/editor.js"></script>
<script src="<%=context%>/common/htmleditor/js/colorpicker.js"></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function setTempleteContent(templeteContent){
	document.getElementsByName("vo.content")[0].value=templeteContent;
}

function doUrlCenter(url){

	var sOpen = "width="+screen.availWidth +", height="+screen.availHeight +",top=0, left=0, status=no";
	var passform = window.open(url, '',sOpen)
	passform.focus();
	
}
//-->
</SCRIPT>
<html>
<body class="bodybg">
<html:form action="/infoservice/template" method="post" enctype="multipart/form-data">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">
		<logic:equal name="IsStandardModelForm" property="nodeID" value="1">工作日志模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="2">周汇报模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="3">月汇报模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="4">年汇报模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="5">每周目标模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="6">月度目标模板编辑</logic:equal>
        <logic:equal name="IsStandardModelForm" property="nodeID" value="7">月度总结模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="8">年度目标模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="9">年度总结模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="10">五年规划模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="11">工作汇报模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="12">授权书模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="13">组织信息模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="14">部门信息模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="15">报告管理模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="16">行业新闻模板编辑</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="17">管理制度模板编辑</logic:equal>
		</span>
		</td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsStandardModelForm" property="actionType"/></td>
    </tr>
</table>

<table  align="center" width="95%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="95%" align="center">
	<tr class="listcellrow">
		<td width="15%">&nbsp;</td>		
		<td width="30%">&nbsp;</td>
		<td width="5%">&nbsp;</td>
		<td width="15%">&nbsp;</td>
		<td width="35%">&nbsp;</td>
	</tr>
	<html:hidden property="vo.content"/>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>模板名称：</td>
		<td nowrap>
		<html:text styleClass="input2" property="vo.model_name" size="22"  validatetype="notempty" msg="模板名称不能为空！"  maxlength="10"/><font color="red">&nbsp;*</font>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell" nowrap>模板类型：</td>
		<td nowrap>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="1">工作日志</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="2">周汇报</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="3">月汇报</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="4">年汇报</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="5">每周目标</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="6">月度目标</logic:equal>
        <logic:equal name="IsStandardModelForm" property="nodeID" value="7">月度总结</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="8">年度目标</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="9">年度总结</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="10">五年规划</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="11">工作汇报</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="12">授权书</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="13">公司动态</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="14">部门信息</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="15">报告管理</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="16">行业新闻</logic:equal>
		<logic:equal name="IsStandardModelForm" property="nodeID" value="17">管理制度</logic:equal>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>是否发布：</td>
		<td>
		<html:radio property="vo.issue_flag" value="1"/>是
		<html:radio property="vo.issue_flag" value="0"/>否
		</td>
	    <td>&nbsp;</td>
		
	    <td>&nbsp;</td>

	</tr>
	<logic:equal name="IsStandardModelForm" property="paraInsert" value="0">
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>上传模板：</td>
		<td colspan="4">
		<input type="file" name ="theFile" class="input" value=""/>
		</td>
	</tr>	
	</logic:equal>
	<!--<logic:equal name="IsStandardModelForm" property="vo.editor_type" value="0">
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>上传模板：</td>
		<td colspan="4">
		<input type="file" name ="theFile" class="input" value=""/>
		</td>
	</tr>	
	</logic:equal>-->
	<tr>
		<td colspan="5">&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>

<logic:empty name="IsStandardModelForm" property="vo.model_name" >
<logic:equal name="IsStandardModelForm" property="paraInsert" value="0">
<table class="tablebk" align="center" width="85%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">
			<input type="button" name="designTemplate" value="模板设计" onclick="doUrlCenter('<%=context%>/cell/eReport.jsp')">
			<!--jsp:include page="../cell/eReport.htm" /-->
		</td>
	</tr>
</table>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<goa:button property="save" value="保存" styleClass="button"  onclick="return doSave(IsStandardModelForm)" operation="BZMB.OPT"/>
		<goa:button property="save" value="保存并返回" styleClass="button"  onclick="return doSaveExit(IsStandardModelForm)" operation="BZMB.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="BZMB.VIEW"/>
		</td>
    </tr>
</table>
</logic:equal>

<logic:equal name="IsStandardModelForm" property="paraInsert" value="1">
<table class="tablebk" align="center" width="85%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">		
			<SCRIPT LANGUAGE="JavaScript">
			<!--
				var editor=new Editor("e1",500,300);
			//-->
			</SCRIPT>		
		</td>
	</tr>
</table>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<goa:button property="save" value="保存" styleClass="button"  onclick="setTempleteContent(e1.getValue());return doSave(IsStandardModelForm)" operation="BZMB.OPT"/>
		<goa:button property="save" value="保存并返回" styleClass="button"  onclick="setTempleteContent(e1.getValue());return doSaveExit(IsStandardModelForm)" operation="BZMB.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="BZMB.VIEW"/>
		</td>
    </tr>
</table>
</logic:equal>
</logic:empty>

<logic:notEmpty name="IsStandardModelForm" property="vo.model_name" >
<logic:equal name="IsStandardModelForm" property="vo.editor_type" value="0">
<table class="tablebk" align="center" width="85%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">
			<input type="button" name="designTemplate" value="模板设计" onclick="doUrlCenter('<%=context%>/cell/eReport.jsp')">
			<!--jsp:include page="../cell/eReport.htm" /-->
		</td>
	</tr>
</table>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<goa:button property="save" value="保存" styleClass="button"  onclick="return doSave(IsStandardModelForm)" operation="BZMB.OPT"/>
		<goa:button property="save" value="保存并返回" styleClass="button"  onclick="return doSaveExit(IsStandardModelForm)" operation="BZMB.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="BZMB.VIEW"/>
		</td>
    </tr>
</table>
</logic:equal>

<logic:equal name="IsStandardModelForm" property="vo.editor_type" value="1">
<table class="tablebk" align="center" width="85%" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">		
			<SCRIPT LANGUAGE="JavaScript">
			<!--
				var editor=new Editor("e1",500,300);
			//-->
			</SCRIPT>		
		</td>
	</tr>
</table>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<goa:button property="save" value="保存" styleClass="button"  onclick="setTempleteContent(e1.getValue());return doSave(IsStandardModelForm)" operation="BZMB.OPT"/>
		<goa:button property="save" value="保存并返回" styleClass="button"  onclick="setTempleteContent(e1.getValue());return doSaveExit(IsStandardModelForm)" operation="BZMB.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="BZMB.VIEW"/>
		</td>
    </tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
e1.setValue(document.getElementsByName("vo.content")[0].value);
//-->
</SCRIPT>
</logic:equal>
</logic:notEmpty>

</html:form>
</body>
</html>