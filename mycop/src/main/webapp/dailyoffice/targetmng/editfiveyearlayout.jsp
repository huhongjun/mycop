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
	initForm();
	var tform = document.forms(0);
	CellWeb1.Login("�㶫�����Ƽ����޹�˾","","13040330","6040-0435-0204-6005");
	<logic:equal name="FiveYearLayoutForm" property="actionType" value = "add">
	CellWeb1.OpenFile(getUrl() + "<bean:write  name="FiveYearLayoutForm" property="reportTemplateName" filter="true"/>","");
	</logic:equal>
	<logic:equal name="FiveYearLayoutForm" property="actionType" value = "modify">
	CellWeb1.OpenFile( getUrl() + "<bean:write  name="FiveYearLayoutForm" property="cellname" filter="true"/>","");
	</logic:equal>
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = idTRTop.offsetLeft;
	//��������趨ΪTable��ƫ����
	CellWeb1.style.top = 90;
	//idTRTop.style.top + idTRTop.offsetHeight + 1;
	//�������趨ΪTable�Ķ�ƫ����
	var lWidth = idTRTop.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;
	//������ʽ�Ŀ��
	var lHeight = document.body.offsetHeight - 110 - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;
	//������ʽ�ĸ߶�
	idTBBottom.style.top = lHeight + idTRTop.offsetHeight;
	divTips.style.display="none";
	<logic:equal name="FiveYearLayoutForm" property="isReadOnlyTemplateFlag" value = "0">
	CellWeb1.WorkbookReadonly = true;
    </logic:equal>
	//CellWeb1.ShowHScroll(0,0);
	//CellWeb1.Readonly = true;
	CellWeb1.style.display="";
}
function initForm(){//��ʼ��ҳ��
	var tform = document.forms(0);
	if(tform.actionType.value == "add"){//�����ǰactionΪ����ʱ-�����������input�ÿ�
		//tform.mbn.readonly = true;
		tform.approvename.value="";
	}		
}
function check(){
	var tform = document.forms(0);
	
	<logic:equal name="FiveYearLayoutForm" property="editFlag" value="1">
	if(tform.elements("approvename").value!="" ){
		var isSendMsg=confirm("�����������˷��Ͷ��ţ�ȷ����");
		//alert(isSendMsg);
		tform.elements("isSendMsg").value=isSendMsg;
		return true;
	}
	</logic:equal>
}
//���沢����
function saveExit(){//У��ҳ��--ͨ�����ύ
	if(check()==false){
		return false;
	}
    savePlanExit();
	return true;
}

function pageUndo() {	      
	CellWeb1.Undo();
}
//���涯��
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
<html:form action="/dailyoffice/targetmng/editfiveyearlayout" method="post">
<TABLE id="idTRTop" cellpadding='0' cellspacing='0' width="100%">
  <html:hidden property="cellcontent"/>
  <html:hidden name="FiveYearLayoutForm" property="actionType"/>
  <html:hidden name="FiveYearLayoutForm" property="approveid"/>   
  <html:hidden name="FiveYearLayoutForm" property="vo.user_code"/>
  <html:hidden name="FiveYearLayoutForm" property="isSendMsg"/>
<TR>
	<logic:equal name="FiveYearLayoutForm" property="actionType" value = "add">
	<TD colspan="8" align="center" class="TableTitleText"><bean:write  name="FiveYearLayoutForm" property="vo.current_year" filter="true"/>������滮</TD>
	</logic:equal>
	<logic:equal name="FiveYearLayoutForm" property="actionType" value = "modify">
	<TD colspan="8" align="center" class="TableTitleText"><bean:write  name="FiveYearLayoutForm" property="vo.current_year" filter="true"/>������滮</TD>
	</logic:equal>
</TR>
</TR>
<TR class="listcellrow">
	<TD colspan="8" align="left">&nbsp;</TD>	
</TR>
</TABLE>
<div id="divTips" style="LEFT: 0px; POSITION: relative">����װ�ػ�����ģ��......</div>

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
     <TR class="listcellrow" align="left">
	<TD width="13%">
	  �ࡡ���ƣ�<bean:write  name="FiveYearLayoutForm" property="username" filter="true"/>	  
	</TD>	
	<TD width="17%">
	  �������ڣ�<bean:write  name="FiveYearLayoutForm" property="vo.create_date" filter="true"/>	  
	</TD>
	<TD width="20%" >  
		  ��   ׼��
          <logic:equal name="FiveYearLayoutForm" property="editFlag" value = "1">
		  <html:text property="approvename" size="10" readonly="true"  styleClass="input2"/>	
		  </logic:equal>
		  <logic:equal name="FiveYearLayoutForm" property="editFlag" value = "0">
		  <bean:write  name="FiveYearLayoutForm" property="approvename" filter="true"/>
		  <html:hidden name="FiveYearLayoutForm" property="approvename"/>
          </logic:equal>
		  &nbsp;
		  <logic:equal name="FiveYearLayoutForm" property="editFlag" value = "1">
		  <input type="button" class="button" onclick="javascript:openSelectWindow('manselect','approveid','approvename');" value="ѡ��">
		  </logic:equal>
		</TD>    	
	<TD width="17%">
	  ��׼���ڣ�	  
	  <bean:write  name="FiveYearLayoutForm" property="vo.approve_date" filter="true"/>
	</TD>
    <logic:equal name="FiveYearLayoutForm" property="approveFlag" value = "1">
	<TD width="30%">		  
		  <input type="radio" name="vo.approve_flag" value="1" checked>ͨ��		 
		  <input type="radio" name="vo.approve_flag" value="0" >��ͨ��	 		  
	</TD>
	</logic:equal>&nbsp;
	<logic:equal name="FiveYearLayoutForm" property="approveFlag" value = "0">
	<TD width="10%">		  
		  	 	&nbsp;	  
	</TD>
	</logic:equal>
     </TR>
     <TR>
	<TD colspan="8" align="left">&nbsp;</TD>	
     </TR>
     <tr>
        <td align="center" colspan="8">
	  <goa:button operation="WRJF.OPT" property="save" value="����" styleClass="button"  onclick="toSave()"/>	
	  <goa:button operation="WRJF.OPT" property="saveAndExit" value="���沢����" styleClass="button"  onclick="saveExit()"/>	  
	  <goa:button operation="WRJF.OPT" property="reset" value="ȡ��" styleClass="button"  onclick="pageUndo()"/>
          <goa:button operation="WRJF.VIEW" property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
     </tr>
</TABLE>
</html:form>
</BODY>
</html:html>
