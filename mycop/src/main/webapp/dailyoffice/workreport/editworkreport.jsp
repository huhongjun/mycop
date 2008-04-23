<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>

<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/control/function.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>
<META name=VI60_defaultClientScript content=VBScript>
<SCRIPT LANGUAGE=javascript>
<!--
function window.onload() {
	//注册
	var tform = document.forms(0);
	CellWeb1.Login("广东金宇恒科技有限公司","","13040330","6040-0435-0204-6005");
    <logic:equal name="DoWorkReportForm" property="actionType" value = "add">
	//CellWeb1.OpenFile( getUrl() + "/cell/template/workreport.cll","");	
	CellWeb1.OpenFile( getUrl() + "<bean:write  name="DoWorkReportForm" property="templatepath" filter="true"/>","");	
	</logic:equal>
	<logic:equal name="DoWorkReportForm" property="actionType" value = "modify">
    CellWeb1.OpenFile( getUrl() + "<bean:write  name="DoWorkReportForm" property="cellName" filter="true"/>","");
	</logic:equal>
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = 0 ;

	CellWeb1.style.top = idTRTop.style.top + idTRTop.offsetHeight;
	var lWidth = idTRTop.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;

	var lHeight = document.body.offsetHeight -70- parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;

	idTBBottom.style.top = lHeight + idTRTop.offsetHeight + 3;

	divTips.style.display="none";
	CellWeb1.style.display="";
	<logic:equal name="DoWorkReportForm" property="actionType" value = "add">
	tform.elements("vo.user_code").value = "<bean:write name="DoWorkReportForm" property="userId"  filter="true"/>";
	tform.elements("vo.user_name").value = "<bean:write name="DoWorkReportForm" property="userName"  filter="true"/>";
	</logic:equal>
	}
//-->
function savePlanSend(){
	document.forms(0).elements("vo.send_flag").value = "1";
    savePlanExit();
}

/*
* 在小于10的数字前加0
*/
function addZero(val){
    if(parseInt(val)<10) val="0"+val;
    return val;
}

/*
* 获取当前时间 2000-01-01 01:01:01
*/
function getToday() {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth();
    var day = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
	var sDate = year + "-"
		+ addZero(month+1) + "-"
		+ addZero(day) + " "
		+ addZero(hour) + ":"
		+ addZero(minute) ;
	return sDate;
}
function checkInput(){
	var sForm = document.forms[0];
	var curDate = new Date();

	var cDate= date2datetime(curDate);
	var bDate = sForm.elements["vo.begin_time"].value;
	var eDate = sForm.elements["vo.end_time"].value;
	
	var lcDate = getDateNum(cDate.substring(0,16),2);
	var lbDate = getDateNum(bDate.substring(0,16),2);
	var leDate = getDateNum(eDate.substring(0,16),2);
	var bRet = true;
	//alert(lbDate + " = " +leDate + " = " +lcDate);
	var strMsg = "";
	if (sForm.elements["vo.begin_time"].value == "" ){
		bRet = false;
		strMsg +=  ("工作汇报开始时间不允许空!\n");
	}
	if (sForm.elements["vo.end_time"].value == "" ){
		bRet = false;
		strMsg +=  ("工作汇报结束时间不允许空!\n");
	}
	if (lbDate > leDate){
		bRet = false;
		strMsg +=  ("工作汇报时间不允许小于开始时间!\n");
	} 

	if (!bRet) alert(strMsg);
	else{
	if(bDate.length==16) sForm.elements["vo.begin_time"].value += ":00";
	if(eDate.length==16) sForm.elements["vo.end_time"].value += ":00";
	}
	return bRet;
}
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
<OBJECT id="CellWeb1" codebase="<%=request.getContextPath()%>/cell/cellweb5.cab#Version=1,0,3,0" style="display: none; POSITION:absolute"
 classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
 <PARAM NAME="_Version" VALUE="65536">
 <PARAM NAME="_ExtentX" VALUE="9710">
 <PARAM NAME="_ExtentY" VALUE="4842">
 <PARAM NAME="_StockProps" VALUE="0">
</OBJECT>

<TABLE  cellpadding='0' cellspacing='0' width="100%">
<html:form action="/dailyoffice/workreport/editworkreport" method="post">
<html:hidden name="DoWorkReportForm" property="cellcontent"/>
<html:hidden name="DoWorkReportForm" property="actionType"/>
<html:hidden name="DoWorkReportForm" property="vo.send_flag"/>
<html:hidden name="DoWorkReportForm" property="vo.user_code"/>
<html:hidden name="DoWorkReportForm" property="vo.user_name"/>
<html:hidden name="DoWorkReportForm" property="vo.create_date"/>
<html:hidden name="DoWorkReportForm" property="vo.serial_num"/>
<TR>
	<TD>
	<div align="center">
  <center>
    <table  id="idTRTop"  align="center" width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
	<logic:equal name="DoWorkReportForm" property="actionType" value = "add">
	      <td align="center" colspan="2"><span class="TableTitleText">工作汇报</span></td>
	</logic:equal>
	<logic:equal name="DoWorkReportForm" property="actionType" value = "modify">
	      <td align="center" colspan="2"><span class="TableTitleText">工作汇报</span></td>
	</logic:equal>
      </tr>
      <tr>
	<td align="center">
			<table width="98%" border="0" cellspacing="0" cellpadding="0">
                  <tr> 
                    <td align="center"> 
					<table  width="100%"  id="idTBTop" border="0" cellpadding="0" cellspacing="2">
					<tr height="23" ><td></td></tr>
					<tr class="listcellrow"  height="23" >
						  <td width="300"   nowrap><font class="InputLabelCell">被汇报人：</font><html:text styleClass="input0" property="vo.render_name" size="15" validatetype="notempty" msg="被汇报人不能为空！" readonly="true" maxlength="10"/><font color="red">*</font><html:button property="save" value="选择人员" styleClass="button"   onclick="javascript:openSelectWindow('manselect','vo.render','vo.render_name');"/>
						  <html:hidden name="DoWorkReportForm" property="vo.render"/>
						  </td>
                          <td width="210"  nowrap><font class="InputLabelCell">开始时间：</font><html:text styleClass="input2" property="vo.begin_time" size="20" validatetype="time3"  msg="开始时间不对,正确格式:'2004-07-13 16:31'"  onlytype="time3" maxlength="20"/><font color="red">*</font></td>
                          <td width="210"  nowrap ><font class="InputLabelCell">结束时间：</font><html:text styleClass="input2" property="vo.end_time" size="20" validatetype="time3"   msg="结束时间不对,正确格式:'2004-07-13 16:31'" onlytype="time3" maxlength="20"/><font color="red">*</font></td>
                        </tr>
						<tr class="listcellrow"  height="1" >
                          <td colspan="3"></td>
                        </tr>
                      </table>
                      <div id="divTips" style="LEFT: 109px; POSITION: relative; top: 179px;">正在加载华表模板..........</div>
                      <table align="center" id="idTBBottom" style="POSITION:absolute; left: 5px; top: 199px;" cellpadding='0' cellspacing='2' width="100%">
                        <tr class="listcellrow"  height="23" > 
                          <td width="200"  nowrap>
						  <font class="InputLabelCell">编 制 人：</font><logic:equal name="DoWorkReportForm" property="actionType" value = "add">
						  <bean:write name="DoWorkReportForm" property="userName"  filter="true"/>
						  </logic:equal>
                          <logic:equal name="DoWorkReportForm" property="actionType" value = "modify">
						  <bean:write name="DoWorkReportForm" property="vo.user_name"  filter="true"/>
						  </logic:equal>
						  </td>
					   <td width="200"  nowrap>
						  <font class="InputLabelCell">编制日期：</font><logic:equal name="DoWorkReportForm" property="actionType" value = "add">
						  <SCRIPT LANGUAGE="JavaScript">
						  <!--
						  document.write(getToday().substring(0,10));
						  document.forms[0].elements("vo.create_date").value = getToday().substring(0,10);
						  //-->
						  </SCRIPT>
						  </logic:equal>
                          <logic:equal name="DoWorkReportForm" property="actionType" value = "modify">
						  <bean:write name="DoWorkReportForm" property="vo.create_date"  filter="true"/>
						  </logic:equal>					  
						  </td>
                          <td width="200"  nowrap><font class="InputLabelCell">审 批 人：</font><bean:write  name="DoWorkReportForm" property="vo.checker_name" filter="true"/></td>
						  <td width="200"  nowrap ><font class="InputLabelCell">审批日期：</font><bean:write  name="DoWorkReportForm" property="vo.check_date" filter="true"/></td>
						  </tr>
						  <tr height="10" ><td></td></tr>
		<tr>
        <td align="center" colspan="4">
		<goa:button property="save" value="保存" styleClass="button"  onclick="savePlan()" operation="GZHB.OPT"/>
		<goa:button property="save" value="保存并返回" styleClass="button"  onclick="savePlanExit()" operation="GZHB.OPT"/>
		<goa:button property="save" value="递交审批" styleClass="button"  onclick="savePlanSend()" operation="GZHB.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="GZHB.VIEW"/></td>
      </tr>
                      </TABLE>
       </td>
				</tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>
</td>
      </tr>
    </table>
    </center>
</div>
	</TD>
</TR>
</html:form>
</TABLE>


