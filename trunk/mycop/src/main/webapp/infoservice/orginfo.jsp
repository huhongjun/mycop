<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<script language="javascript">
function selectOnChange(obj){
	if (obj.value=='1')
		rad.style.display="inline";
	else
		rad.style.display="none";
}

</script>
 
<!--������Դ-start-->
<script type="text/javascript" 
src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" 
src="<%=context%>/jscript/pub.js"></script>
<script  LANGUAGE=VBSCRIPT 
src="<%=context%>/cell/control/function.vbs"></script>
<script  LANGUAGE=JAVASCRIPT 
src="<%=context%>/cell/control/buttons.js"></script>
<script  LANGUAGE=JAVASCRIPT 
src="<%=context%>/cell/goascript/public.js"></script>
<script  LANGUAGE=VBSCRIPT 
src="<%=context%>/cell/goascript/public.vbs"></script>
<script  LANGUAGE=VBSCRIPT 
src="<%=context%>/cell/goascript/code.vbs"></script>
<!--������Դ-end-->

<!--HTMLģ����Դ-start-->
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/toolbar.css">
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/editor.css">
<script src="<%=context%>/common/htmleditor/js/toolbar.js"></script>
<script src="<%=context%>/common/htmleditor/js/editor.js"></script>
<script src="<%=context%>/common/htmleditor/js/colorpicker.js"></script>
<!--HTMLģ����Դ-end-->

<!--��������-->
<logic:equal name="IsInfoServeForm" property="vo.editor_type" value="0">

<!--BUTTON-->

<script FOR="cbButton" EVENT="onmousedown()"	LANGUAGE="JavaScript" >
	return onCbMouseDown(this);
</script>

<script FOR="cbButton" EVENT="onclick()"		LANGUAGE="JavaScript" >
	return onCbClickEvent(this);
</script>

<script FOR="cbButton" EVENT="oncontextmenu()"	LANGUAGE="JavaScript" >
	return(event.ctrlKey);
</script>

<script LANGUAGE=javascript>

function window.onload() {
	//ע��
	var tform = document.forms(0);
	CellWeb1.Login("�㶫�����Ƽ����޹�˾","","13040330","6040-0435-0204-6005");
	<logic:equal name="IsInfoServeForm" property="actionType" value = "add">
	CellWeb1.OpenFile(getUrl() + "<bean:write  name="IsInfoServeForm" property="templatePath" filter="true"/>","");
	</logic:equal>
	<logic:equal name="IsInfoServeForm" property="actionType" value = "modify">
	CellWeb1.OpenFile( getUrl() + "<bean:write  name="IsInfoServeForm" property="cellname" filter="true"/>","");
	</logic:equal>

	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;

	CellWeb1.style.left = idTBTop.offsetLeft;

	CellWeb1.style.top = idSpanTop.offsetHeight + idTBTittle.offsetHeight + idTBTop.offsetHeight+40;

	CellWeb1.style.width = idTBTop.offsetWidth;

	CellWeb1.style.height =320;

	CellWeb1.WorkbookReadonly = false;
	CellWeb1.style.display="";

}
</script>

<object id="CellWeb1" codebase="cellweb5.cab" style="display: none; POSITION:absolute"
 classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
 <param NAME="_Version" VALUE="65536">
 <param NAME="_ExtentX" VALUE="9710">
 <param NAME="_ExtentY" VALUE="4842">
 <param NAME="_StockProps" VALUE="0">
</object>
</logic:equal>

<logic:notEqual name="IsInfoServeForm" property="vo.editor_type" value="0">
<SCRIPT LANGUAGE="JavaScript">

function setTempleteContent(templeteContent){
	document.getElementsByName("vo.word_content")[0].value=templeteContent;
	}
function checkSubmit(pageForm,operate){
	var flag=true;
	var errorInfo="";

	var wordlength = e1.getValue();
	if(wordlength.length>700){
		errorInfo="Word�༭���ֶγ��ȳ�����Χ";
		flag=false;
	}

	if(flag && operate=="save"){
		setTempleteContent(e1.getValue());doSave(pageForm);
	}else if(flag && operate=="saveExit"){
		setTempleteContent(e1.getValue());doSaveExit(pageForm);
	}

	if(!flag)
		alert(errorInfo);

}

</SCRIPT>

</logic:notEqual>

<!--�ж��Ƿ�Ϊ�޸�ҳ��-->
<logic:equal name="IsInfoServeForm" property="actionType" value="modify">
<SCRIPT LANGUAGE="JavaScript">
<!--
function checksend(){
	rad.style.display="inline";
}
//-->
</SCRIPT>
<body class="bodybg" onload="checksend()" >
</logic:equal>

<logic:notEqual name="IsInfoServeForm" property="actionType" value="modify">
<body class="bodybg">
</logic:notEqual>

<html:form action="/infoservice/orginfo" method="post" enctype="multipart/form-data">
<span id="idSpanTop" class="TableTitleText"><br></span> 

<table id="idTBTittle" width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText" nowrap>��˾��̬�༭</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsInfoServeForm" property="actionType"/></td>
    </tr>
</table>

<table id="idTBTop"  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="95%" align="center">
	<tr class="listcellrow">
		<td width="18%">&nbsp;</td>		
		<td width="30%">&nbsp;</td>
		<td width="2%">&nbsp;</td>
		<td width="15%">&nbsp;</td>
		<td width="35%">&nbsp;</td>
	</tr>
    <html:hidden property="vo.info_flag" value="2"/>	
	<html:hidden property="cellcontent"/>	
	<html:hidden property="vo.word_content"/>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
		<td nowrap>
		<html:text styleClass="input2" property="vo.title" size="22"  validatetype="notempty" msg="���ⲻ��Ϊ�գ�"  maxlength="30"/><font color="red">&nbsp;*</font>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
		<td nowrap>
		<html:select property="vo.info_type">
			<html:options collection="IsDoTypeList" property="info_type" labelProperty="info_type"/>
		</html:select>
		</td>
	</tr>
	<tr class="listcellrow">
	<td align="left" class="InputLabelCell">�Ƿ���ʾ��</td>
	<td colspan="4">
		<html:radio property="vo.show_flag" value="1" />��
		<html:radio property="vo.show_flag" value="0" />��
	</td>
	</tr>
	<logic:notEqual name="IsInfoServeForm" property="vo.file_name" value="">
	<logic:equal name="IsInfoServeForm" property="actionType" value="modify">
	<tr class="listcellrow">
	<td align="left" class="InputLabelCell">�������ƣ�</td>
	<td colspan="4">
		<html:text styleClass="input2" property="vo.file_name" size="22" readonly="true" readonly="true"/>
	</td>
	</tr>
	</logic:equal>
	</logic:notEqual>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">����������</td>
		<td colspan="4">
		<input type="file" name ="theFile" class="input2" size="52" value=""/>
		</td>
	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" valign="top">�������ݣ�</td>
		<td colspan="4">			
		<!--html:textarea property="vo.content" rows="3" cols="72%" styleClass="input2"/-->
		</td>
	</tr>
</table>
</td>
</tr>
</table>
<!--HTMLģ��-->
<logic:notEqual name="IsInfoServeForm" property="vo.editor_type" value="0">
<table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>
	<script LANGUAGE="JavaScript">
				<!--
					//Editor.context="<%=context%>";
					var editor=new Editor("e1",520,320);
				//-->
				</script>	
</td>
  </tr>
</table>
</logic:notEqual>

<table id="idTBMiddle"  align="center" width="85%" cellspacing="0" cellpadding="0" style="POSITION:absolute; left: 81px; top: 560px; width: 305px; height: 60px;">
  <tr>
  <td >
  <table id="idTBMiddle" width="88%" align="center">
	  <tr class="listcellrow">
		  <td width="15%">&nbsp;</td>		
		  <td width="30%">&nbsp;</td>
		  <td width="5%">&nbsp;</td>
	    </tr>
	  <tr class="listcellrow">
		  <td align="left" class="InputLabelCell">ֱ�ӷ�����</td>
		  <td>
		  <html:radio property="vo.send_flag" value="1" onclick="selectOnChange(this)"/>��
		  <html:radio property="vo.send_flag" value="0" onclick="selectOnChange(this)"/>��
		  </td>
		  <td>&nbsp;</td>
	    </tr>
	  <tr>
		  <td colspan="3">&nbsp;</td>
	  </tr>
  </table>
  </td>
  </tr>
</table>
<table id="idTBMiddle"  align="center" width="85%" cellspacing="0" cellpadding="0" style="POSITION:absolute; left: 371px; top: 560px; width: 305px; height: 60px;">
  <tr>
    <td ><table id="idTBMiddle" width="88%" align="center">
        <tr class="listcellrow">
          <td width="15%">&nbsp;</td>
          <td width="30%">&nbsp;</td>
          <td width="5%">&nbsp;</td>
        </tr>
		<tbody id="rad" style="display:none">
        <tr class="listcellrow">
          <td align="left" class="InputLabelCell">���Ͷ��ţ�</td>
          <td>
		  <html:radio property="vo.hint_flag" value="1"/>��
		  <html:radio property="vo.hint_flag" value="0"/>��
		  </td>
          <td>&nbsp;</td>
        </tr>
		</tbody>
		
        <tr>
          <td colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
<!--������-->
<logic:equal name="IsInfoServeForm" property="vo.editor_type" value="0">
<table id="idTBBottom" width="95%" border="0" cellspacing="0" cellpadding="0" style="POSITION:absolute; left: 0px; top: 600px;">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<goa:button property="save" value="����" styleClass="button"  onclick="return savePlan()" operation="ZZXX.OPT"/>
		<goa:button property="save" value="���沢����" styleClass="button"  onclick="return savePlanExit()" operation="ZZXX.OPT"/>
        <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="ZZXX.VIEW"/>
		</td>
    </tr>
</table>
</logic:equal>

<logic:notEqual name="IsInfoServeForm" property="vo.editor_type" value="0">
<table id="idTBBottom" width="95%" border="0" cellspacing="0" cellpadding="0" style="POSITION:absolute; left: 0px; top: 600px;">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<goa:submit property="save" value="����" styleClass="button"  onclick="checkSubmit(IsInfoServeForm,'save')" operation="ZZXX.OPT"/>
		<goa:button property="save" value="���沢����" styleClass="button"  onclick="checkSubmit(IsInfoServeForm,'saveExit')" operation="ZZXX.OPT"/>
        <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="ZZXX.VIEW"/>
		</td>
    </tr>
</table>
</logic:notEqual>

</html:form>
<logic:notEqual name="IsInfoServeForm" property="vo.editor_type" value="0">
<SCRIPT LANGUAGE="JavaScript">
<!--
e1.setValue(document.getElementsByName("vo.word_content")[0].value);
//-->
</SCRIPT>
</logic:notEqual>


</body>
