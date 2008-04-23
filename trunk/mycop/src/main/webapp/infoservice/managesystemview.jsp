<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.goa.infoservice.action.IsInfoServeForm"%>
<%String context =request.getContextPath();%>

<!--华表资源-start-->
<script type="text/javascript" 
src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" 
src="<%=context%>/jscript/pub.js"></script>
<SCRIPT  LANGUAGE=VBSCRIPT 
src="<%=context%>/cell/control/function.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT 
src="<%=context%>/cell/control/buttons.js"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT 
src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT 
src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT 
src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>
<!--华表资源－end-->

<!--HTML编辑器资源-start-->
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/toolbar.css">
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/editor.css">
<script src="<%=context%>/common/htmleditor/js/toolbar.js"></script>
<script src="<%=context%>/common/htmleditor/js/editor.js"></script>
<script src="<%=context%>/common/htmleditor/js/colorpicker.js"></script>
<!--HTML编辑器资源-end-->

<!--华表内容-->
<logic:equal name="IsInfoServeForm4" property="vo.editor_type" value="0">
<SCRIPT LANGUAGE=javascript>
<!--
function window.onload() {
	//注册
	var tform = document.forms(0);
	CellWeb1.Login("广东金宇恒科技有限公司","","13040330","6040-0435-0204-6005");

	CellWeb1.OpenFile(getUrl() + "<bean:write  name="IsInfoServeForm4" property="cellname" filter="true"/>","");

	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;

	CellWeb1.style.left = idTBTop.offsetLeft;

	CellWeb1.style.top = idSpanTop.offsetHeight + idTBTittle.offsetHeight + idTBTop.offsetHeight+70;

	CellWeb1.style.width = idTBTop.offsetWidth;

	CellWeb1.style.height =350;

	CellWeb1.WorkbookReadonly = true;
	CellWeb1.style.display="";
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

<OBJECT id="CellWeb1" codebase="cellweb5.cab" style="display: none; POSITION:absolute"
 classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
 <PARAM NAME="_Version" VALUE="65536">
 <PARAM NAME="_ExtentX" VALUE="9710">
 <PARAM NAME="_ExtentY" VALUE="4842">
 <PARAM NAME="_StockProps" VALUE="0">
</OBJECT>
</logic:equal>

<logic:notEqual name="IsInfoServeForm4" property="vo.editor_type" value="0">
<SCRIPT LANGUAGE="JavaScript">

function setTempleteContent(templeteContent){
	document.getElementsByName("vo.word_content")[0].value=templeteContent;
	//alert(document.getElementsByName("vo.word_content")[0].value);
}
</SCRIPT>
</logic:notEqual>

<html>
<body class="bodybg" >

<html:form action="/infoservice/managesystemview" method="post" enctype="multipart/form-data">
<span id="idSpanTop" class="TableTitleText"><br></span> 

<table id="idTBTittle" width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">管理制度浏览</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsInfoServeForm4" property="actionType"/></td>
    </tr>
</table>

<table id="idTBTop"  align="center" width="85%" cellspacing="0" cellpadding="0">
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
    <html:hidden property="vo.info_flag" value="1"/>
	<html:hidden property="cellcontent"/>
	<html:hidden property="vo.word_content"/>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">主&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
		<td>
		<goa:write  name="IsInfoServeForm4" property="vo.title" filter="true"/>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell">类&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
		<td>
		<goa:write  name="IsInfoServeForm4" property="vo.info_type" filter="true"/>
		</td>
	</tr>	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">发 布 人：</td>
		<td>
		<goa:write  name="IsInfoServeForm4" property="vo.user_name" filter="true"/>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell">发布时间：</td>
		<td>
		<goa:write  name="IsInfoServeForm4" property="vo.create_date" filter="true"/>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">附件下载：</td>
		<td colspan="4">
		<% 
		 IsInfoServeForm dForm = (IsInfoServeForm)session.getAttribute("IsInfoServeForm4");
		 String bfilepath=dForm.getVo().getValue("file_path"); 
		 String bfilename=dForm.getVo().getValue("file_name");
		 String strUrl = "";	
               String strHref = context+"/downloadfile.jsp?filepath=" 
                   + bfilepath + "&filename=" + bfilename;
   
                 String downloadMethod = "download2('" + bfilepath + "','" + bfilename +"')";
		
		 %>
		  <goa:link style="cursor:hand" href="<%=strHref%>" onclick ="<%=downloadMethod%>" title="下载附件" ><%=bfilename%>
          </goa:link>&nbsp;
		</td>
	</tr>

	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">发布内容：</td>
		<td colspan="4">
		<!--goa:write  name="IsInfoServeForm4" property="vo.content" filter="true"/-->
		</td>
	</tr>
</table>
</td>
</tr>
</table>
<!--HTML模板-->
<logic:notEqual name="IsInfoServeForm4" property="vo.editor_type" value="0">
<table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	<script LANGUAGE="JavaScript">
				<!--
					//Editor.context="<%=context%>";
					var editor=new Editor("e1",530,340);
				//-->
				</script>	
</td>
  </tr>
</table>
</logic:notEqual>

<table id="idTBMiddle" align="center" width="85%" cellspacing="0" cellpadding="0" style="POSITION:absolute; left: 58px; top: 540px;">
<tr>
<td>
<table id="idTBMiddle" width="95%" align="center">
	<tr class="listcellrow">
		<td width="15%">&nbsp;</td>		
		<td width="30%">&nbsp;</td>
		<td width="5%">&nbsp;</td>
		<td width="15%">&nbsp;</td>
		<td width="35%">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="5">&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>

<table id="idTBBottom" width="95%" border="0" cellspacing="0" cellpadding="0" style="POSITION:absolute; left: 0px; top: 610px;">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<html:hidden name="IsInfoServeForm4" property="fid" />
		<%String clk1="toUrl('"+context+"/infoservice/managesystemedit.do?actionType=modify',false)";%>
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=clk1%>" operation="HYXW.OPT" />
		
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="HYXW.VIEW"/>
		<logic:equal name="IsInfoServeForm4" property="vo.editor_type" value="0">
		<input type="button" class="button" name="print" value="打印预览" onclick="mnuFilePrintPreview_click()">
		<input type="button" class="button" name="print" value="输出为Excel文件" onclick="mnuFileExportExcel_click()">
		</logic:equal>

		</td>
    </tr>
</table>

</html:form>

<logic:notEqual name="IsInfoServeForm4" property="vo.editor_type" value="0">
<SCRIPT LANGUAGE="JavaScript">
<!--
e1.setValue(document.getElementsByName("vo.word_content")[0].value);
e1.setReadOnly();
//-->
</SCRIPT>
</logic:notEqual>
<IFRAME frameBorder=1 id="attch" src="" style="display:none" ></IFRAME>
</body>
</html>