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
	<logic:equal name="PersonWeekTargetForm" property="actionType" value = "add">
	CellWeb1.OpenFile(getUrl() + "<bean:write  name="PersonWeekTargetForm" property="reportTemplateName" filter="true"/>","");
	</logic:equal>
	<logic:equal name="PersonWeekTargetForm" property="actionType" value = "modify">
	CellWeb1.OpenFile( getUrl() + "<bean:write  name="PersonWeekTargetForm" property="cellname" filter="true"/>","");
	</logic:equal>
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = idTRTop.offsetLeft;
	//��������趨ΪTable��ƫ����
	CellWeb1.style.top = 110;
	//idTRTop.style.top + idTRTop.offsetHeight + 1;
	//�������趨ΪTable�Ķ�ƫ����
	var lWidth = idTRTop.offsetWidth-30;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;
	//������ʽ�Ŀ��
	var lHeight = document.body.offsetHeight - 50 - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;
	//������ʽ�ĸ߶�
	idTBBottom.style.top = lHeight + idTRTop.offsetHeight;
	divTips.style.display="none";
	<logic:equal name="PersonWeekTargetForm" property="creatorFlag" value = "0">
	CellWeb1.WorkbookReadonly = true;
    </logic:equal>
	//CellWeb1.ShowHScroll(0,0);
	//CellWeb1.Readonly = true;
	CellWeb1.style.display="";
}
function check(isExit){//У��ҳ��--ͨ�����ύ
	var tform = document.forms(0);	
	//alert(isExit);
	if(tform.elements("vo.current_work").value==""){
		alert("����д�ܣ�");
		tform.elements("vo.current_work").focus();
		return false;
	}	
	if(tform.elements("vo.current_work").value>5 || tform.elements("vo.current_work").value<1){
		
		alert("�뱣֤������1-5֮�䣡");
		tform.elements("vo.current_work").focus();
		return false;
	}
	if(tform.elements("auditorname").value==""){
		alert("�����������");
		return false;
	}
	tform.elements("saveAndExit").disabled = true;
	if(isExit==true){
	    savePlanExit();
	}else{
		savePlan();
	}
	return true;
}
function pageUndo() {	    
	CellWeb1.Undo();
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
<html:form action="/dailyoffice/worklog/editpersonweektarget" method="post">
<TABLE id="idTRTop" cellpadding='0' cellspacing='0' width="100%">
  <html:hidden property="cellcontent"/>
  <html:hidden name="PersonWeekTargetForm" property="actionType"/>  
  <html:hidden name="PersonWeekTargetForm" property="auditorid"/> 
  <html:hidden name="PersonWeekTargetForm" property="vo.user_code"/>  
<TR>
    <logic:equal name="PersonWeekTargetForm" property="actionType" value = "add">
	<TD colspan="10" align="center" class="TableTitleText">�����ܻ㱨1</TD>
	</logic:equal>
	<logic:equal name="PersonWeekTargetForm" property="actionType" value = "modify">
	<TD colspan="10" align="center" class="TableTitleText">�����ܻ㱨2</TD>
	</logic:equal>
</TR>
<TR class="listcellrow">
	<TD colspan="10" align="left">&nbsp;</TD>	
</TR>
<TR class="listcellrow">
    <TD width="1%" align="left">&nbsp;</TD>
	<TD width="25%">��    �ţ�<bean:write  name="PersonWeekTargetForm" property="deptname" filter="true"/></TD>
	<TD width="25%">��    λ��<bean:write  name="PersonWeekTargetForm" property="postname" filter="true"/></TD>
	<TD width="25%">��    ����<bean:write  name="PersonWeekTargetForm" property="username" filter="true"/></TD>	
	<TD width="20%" align="left">
	<html:text styleClass="input0" readonly="true" property="vo.current_year" size="4"  maxlength="4"/>
	��
	<html:text styleClass="input0" readonly="true" property="vo.current_month" size="2"  maxlength="2"/>	
	��
	<html:text styleClass="input0" onlytype="int" property="vo.current_work" size="1"  maxlength="1"/>	
	��
	</td>
</TR>
</TABLE>
<div id="divTips" style="LEFT: 0px; POSITION: relative">����װ�ػ�����ģ��......</div>

<TABLE id="idTBBottom" style="POSITION:absolute; left: 5px; top: 199px;" cellpadding='0' cellspacing='0' width="100%">
     <tr>
        <td align="center" colspan="10">&nbsp;</td>
     </tr>
     <TR>
	<TD colspan="10" align="left">&nbsp;</TD>	
     </TR>
	 <TR>
	<TD colspan="10" align="left">&nbsp;</TD>	
     </TR>
	 <tr class="listcellrow">
	 <TD colspan="8" align="left">
	 <logic:equal name="PersonWeekTargetForm" property="auditFlag" value = "1">
	 ��������<html:text styleClass="input2" property="vo.audit_opinion" size="93" maxlength="93"/>
		  <input type="radio" name="vo.audit_flag" value="1" width="15%" checked>ͨ��
		  <input type="radio" name="vo.audit_flag" value="0" width="15%" >��ͨ��	 
	</logic:equal>
	 </TD>	
	 </tr>
     <TR class="listcellrow" align="left">
	<TD width="30%" align="left">���ƣ�<bean:write  name="PersonWeekTargetForm" property="username" filter="true"/></TD>	
	<TD width="30%" align="left">�������ڣ�<bean:write  name="PersonWeekTargetForm" property="vo.create_date" filter="true"/></TD>	
	<TD width="40%" >  
		  �󡡡��ˣ�
          <logic:equal name="PersonWeekTargetForm" property="creatorFlag" value = "1">
		  <html:text property="auditorname" size="10" readonly="true"  styleClass="input2"/>	
		  </logic:equal>
		  <logic:equal name="PersonWeekTargetForm" property="creatorFlag" value = "0">
		  <html:hidden  name="PersonWeekTargetForm" property="auditorname" />
		  <bean:write  name="PersonWeekTargetForm" property="auditorname" filter="true"/>
          </logic:equal>
		  &nbsp;
		  <logic:equal name="PersonWeekTargetForm" property="creatorFlag" value = "1">
		  <input type="button" class="button" onclick="javascript:openSelectWindow('manselect','auditorid','auditorname');" value="ѡ��">
		  </logic:equal>
		</TD>
     </TR>
     <tr>
        <td align="center" colspan="10">
		<goa:button operation="YDZJ.OPT" property="save" value="����" styleClass="button"  onclick="check(false)"/>	
	  <goa:button operation="LZMB.OPT" property="saveAndExit" value="���沢����" styleClass="button"  onclick="check(true)"/>	  	 
	  <goa:button operation="LZMB.OPT" property="reset" value="ȡ��" styleClass="button"  onclick="pageUndo()"/>	  
      <goa:button operation="LZMB.VIEW" property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
     </tr>
</TABLE>
</html:form>
</BODY>
</html:html>
