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
    <logic:equal name="DoImpowerForm" property="actionType" value = "add">
//	CellWeb1.OpenFile( getUrl() + "/cell/template/impower.cll","");	
	CellWeb1.OpenFile( getUrl() + "<bean:write  name="DoImpowerForm" property="templatepath" filter="true"/>","");	
	</logic:equal>
	<logic:equal name="DoImpowerForm" property="actionType" value = "modify">
    CellWeb1.OpenFile( getUrl() + "<bean:write  name="DoImpowerForm" property="cellName" filter="true"/>","");
	</logic:equal>
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = 10;

	CellWeb1.style.top = idTRTop.style.top + idTRTop.offsetHeight;
	var lWidth =idTRTop.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth-70;

	var lHeight = document.body.offsetHeight - 70 - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;

	idTBBottom.style.top = lHeight + idTRTop.offsetHeight + 5 ;

	divTips.style.display="none";
	CellWeb1.style.display="";
	<logic:equal name="DoImpowerForm" property="actionType" value = "add"> 
	tform.elements("vo.create_time").value = getToday();
	tform.elements("vo.accepter").value = "<bean:write name="DoImpowerForm" property="userId"  filter="true"/>";
	tform.elements("vo.acceptername").value = "<bean:write name="DoImpowerForm" property="userName"  filter="true"/>";
	</logic:equal>
	document.forms(0).elements("cellHtml").value = "";
	document.forms(0).elements("sendflag").value = "0";
	changeToTimestamp();
}

function changeToTimestamp(){
	var begin_time=document.forms(0).elements("vo.begin_time").value;
	var end_time=document.forms(0).elements("vo.end_time").value;
	if(begin_time!=""){
		document.forms(0).elements("vo.begin_time").value=begin_time.substring(0,16);
	}
	if(end_time!=""){
		document.forms(0).elements("vo.end_time").value=end_time.substring(0,16);
	}
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
    var second = now.getSeconds();
	var sDate = year + "-"
		+ addZero(month+1) + "-"
		+ addZero(day) + " "
		+ addZero(hour) + ":"
		+ addZero(minute) + ":"
		+ addZero(second);
	return sDate;
}
function savePlanSend(){
	document.forms(0).elements("vo.send_flag").value = "1";
	document.forms(0).elements("sendflag").value = "1";
	document.forms(0).elements("cellHtml").value = getAllContentInCell();
	savePlanExit();
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
		strMsg +=  ("授权开始时间不允许空!\n");
	}
	if (sForm.elements["vo.end_time"].value == "" ){
		bRet = false;
		strMsg +=  ("授权结束时间不允许空!\n");
	}
	if (lbDate > leDate){
		bRet = false;
		strMsg +=  ("授权时间不允许小于开始时间!\n");
	} 

	if (!bRet) alert(strMsg);
	else{
	if(bDate.length==16) sForm.elements["vo.begin_time"].value += ":00";
	if(eDate.length==16) sForm.elements["vo.end_time"].value += ":00";
	}
	return bRet;
	//var curlngDate = date2Long(eDate);

}

function getAllContentInCell(){
	var tabhead = "<table width='100%' border='0' cellspacing='0' cellpadding='0'>";
	var tabbutoom = "</table>";
	var tabstr=tabhead;
	var cursheet = CellWeb1.GetCurSheet();
	CellWeb1.setCurSheet(cursheet);
	var rowcount = CellWeb1.GetRows(cursheet);
	var colcount = CellWeb1.GetCols(cursheet);
	var conArr = new Array(rowcount,colcount);
	for(var rindex=1;rindex<rowcount;rindex++){
		tabstr += "<tr>";
		for(var cindex=1;cindex<colcount;cindex++){
			var cellstr = CellWeb1.GetCellString(cindex,rindex, cursheet);
			tabstr += "<td style='font-size: 12px;'>" + cellstr + "</td>";  
		}
		tabstr+= "</tr>";
	}
	tabstr+=tabbutoom;
	return tabstr;
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

<OBJECT id="CellWeb1" codebase="<%=request.getContextPath()%>/cell/cellweb5.cab#Version=1,0,3,0" style="display: none; POSITION:absolute"
 classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
 <PARAM NAME="_Version" VALUE="65536">
 <PARAM NAME="_ExtentX" VALUE="9710">
 <PARAM NAME="_ExtentY" VALUE="4842">
 <PARAM NAME="_StockProps" VALUE="0">
</OBJECT>

<TABLE  cellpadding='0' cellspacing='0' width="100%">
<html:form action="/dailyoffice/impowermgr/editimpower" method="post">
<html:hidden name="DoImpowerForm" property="cellcontent"/>
<html:hidden name="DoImpowerForm" property="cellHtml"/>
<html:hidden name="DoImpowerForm" property="actionType"/>
<html:hidden name="DoImpowerForm" property="vo.send_flag"/>
<html:hidden name="DoImpowerForm" property="sendflag"/>
<html:hidden name="DoImpowerForm" property="vo.serial_num"/>
<html:hidden name="DoImpowerForm" property="vo.create_time"/>
<html:hidden name="DoImpowerForm" property="vo.accepter"/>
<html:hidden name="DoImpowerForm" property="vo.acceptername"/>
<TR>
	<TD>
	<div align="center">
  <center>
          <table  id="idTRTop" align="center" width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr> 
              <td align="center" colspan="2"><span class="TableTitleText">
			<logic:equal name="DoImpowerForm" property="actionType" value = "add">
			新增授权书
			</logic:equal>
			  <logic:equal name="DoImpowerForm" property="actionType" value = "modify">
			  修改授权书
			  </logic:equal>
			</span></td>
               </tr>
            <tr> 
			<td>
			<table width="98%" border="0" cellspacing="0" cellpadding="0">
                  <tr> 
                    <td align="center"> 
					<table  width="98%"  id="idTBTop" border="0" cellpadding="0" cellspacing="4">
                        <tr height="23" > 
                          <td colspan="2"></td>
                        </tr>
                        <tr class="listcellrow" height="30" > 
                          <td colspan="2"  width="15%" nowrap><font class="InputLabelCell"> 
                            授 权 人：</font> <logic:equal name="DoImpowerForm" property="actionType" value = "add"> 
                            <bean:write name="DoImpowerForm" property="userName"  filter="true"/> 
                            </logic:equal> <logic:equal name="DoImpowerForm" property="actionType" value = "modify"> 
                            <bean:write name="DoImpowerForm" property="vo.acceptername"  filter="true"/> 
                            </logic:equal></td>
                          <td   width="27.5%" colspan="2" align="center" nowrap> 
                            <font class="InputLabelCell">被授权人：</font> <html:hidden name="DoImpowerForm" property="vo.payer"/> 
                            <html:text styleClass="input0" property="vo.payername" size="15" validatetype="notempty" msg="被授权人不能为空！" readonly="true" maxlength="10"/><font color="red">*</font><html:button property="selectpayername" value="选择人员" styleClass="button" onclick="javascript:openSelectWindow('manselect','vo.payer','vo.payername');"/> 
                          </td>
                          <td colspan="2" width="19%"  nowrap> <font class="InputLabelCell">授权期间：</font> 
                            <html:text styleClass="input2" property="vo.begin_time" size="23" validatetype="time3"  msg="授权期间时间不对,正确格式:'2004-07-13 16:31'"  onlytype="time3" maxlength="20"/> 
                          </td>
                          <td nowrap> <font class="InputLabelCell">至 </font> <html:text styleClass="input2" property="vo.end_time" size="21" validatetype="time3"   msg="结束时间不对,正确格式:'2004-07-13 16:31'" onlytype="time3" maxlength="20"/><font color="red">*</font> 
                          </td>
                        </tr>
                        <tr class="listcellrow" height="23" > 
                          <td  width="5%"  valign="top"  class="InputLabelCell" nowrap>通知人员： 
                          </td>
                          <td colspan="2" width="30%"  valign="middle"><html:hidden name="DoImpowerForm" property="vo.notice"/><html:textarea styleClass="input2" property="vo.noticename" cols="50" rows="4"  readonly="true" maxlength="1000" onkeydown="javascipt:getUser('vo.noticename','vo.notice');" onblur="javascript:checkValue('vo.noticename','vo.notice');"/><font color="red">*</font>
                          </td>
                          <td width="9%" ><html:button property="selectnotice" value="选择人员" styleClass="button"  onclick="javascript:openSelectWindow('menselect','vo.notice','vo.noticename');"/> </td>
                          <td width="5%" valign="top"  class="InputLabelCell" nowrap>授权说明： 
                          </td>
                          <td colspan="2"><html:textarea property="vo.comments" cols="50" rows="4" styleClass="input2" maxlength="30"/></td>
                        </tr>
                        <tr class="listcellrow" height="1" > 
                          <td colspan="2" ></td>
                          <td colspan="6"></td>
                        </tr>
                      </table>
                      <div id="divTips" style="LEFT: 109px; POSITION: relative; top: 179px;">正在加载华表模板..........</div>
                      <table id="idTBBottom" style="POSITION:absolute; left: 0px; top: 199px;" cellpadding='0' cellspacing='0' width="98%">
                        <tr> 
                          <td align="center" height="40" >
						    <goa:button property="save" value="保存" styleClass="button"  onclick="savePlan()"  operation="SQGL.OPT"/> 
                            <goa:button property="save" value="保存并返回" styleClass="button"  onclick="savePlanExit()"  operation="SQGL.OPT"/> 
                            <goa:button property="save" value="邮件通知" styleClass="button"  onclick="savePlanSend()"  operation="SQGL.OPT"/> 
                            <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"  operation="SQGL.VIEW"/></td>
                        </tr>
                      </table></td>
                  </tr>
                </table></td>
            </tr>
            <tr> 
              <td> </td>
            </tr>
          </table>
        </center>
</div>
	</TD>
</TR>
</html:form>
</TABLE>

