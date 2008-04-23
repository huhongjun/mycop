<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>
<SCRIPT  LANGUAGE=JAVASCRIPT src="<%=context%>/cell/goascript/public.js"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/public.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT src="<%=context%>/cell/goascript/code.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=VBSCRIPT 
src="<%=context%>/cell/control/function.vbs"></SCRIPT>
<SCRIPT  LANGUAGE=JAVASCRIPT 

<SCRIPT LANGUAGE=javascript>
<!--
function window.onload() {
	//注册
	var tform = document.forms(0);
	CellWeb1.Login("广东金宇恒科技有限公司","","13040330","6040-0435-0204-6005") ;
    CellWeb1.OpenFile( getUrl() + "<bean:write  name="DoWorkReportForm" property="cellName" filter="true"/>","");
	CellWeb1.EnableUndo(true);
	CellWeb1.Mergecell = true;
	CellWeb1.WorkbookReadonly = true;
	CellWeb1.border = 0;
	CellWeb1.style.left = 0 ;

	CellWeb1.style.top = idTRTop.style.top + idTRTop.offsetHeight;
	var lWidth = idTRTop.offsetWidth;
	if( lWidth <= 0) lWidth = 1;
	CellWeb1.style.width = lWidth;

	var lHeight = document.body.offsetHeight - 70 - parseInt(CellWeb1.style.top);
	if( lHeight <= 0 ) lHeight = 1;
	CellWeb1.style.height = lHeight;

	idTBBottom.style.top = lHeight + idTRTop.offsetHeight + 3;


	divTips.style.display="none";
	CellWeb1.style.display="";
}


function checkReport(mform){
	var sForm = document.forms[0];
	if(sForm.elements["vo.begin_time"].value.length==16) sForm.elements["vo.begin_time"].value += ":00";
	if(sForm.elements["vo.end_time"].value.length==16) sForm.elements["vo.end_time"].value += ":00";
	sForm.elements("vo.checker").value="<bean:write name="DoWorkReportForm" property="userId"  filter="true"/>";
	sForm.elements("vo.checker_name").value="<bean:write name="DoWorkReportForm" property="userName"  filter="true"/>";
	sForm.elements("vo.check_date").value = getToday().substring(0,9);
	sForm.action = "editworkreport.do";
	var sAction = sForm.action;
	sForm.elements["actionType"].value="modify";
	sAction = addPara(sAction, "action=modify");
	sForm.action = sAction;
	CellWeb1.SaveEdit();
	if(saveCell()){
	sForm.submit();
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

//-->

</SCRIPT>
<OBJECT id="CellWeb1" codebase="<%=request.getContextPath()%>/cell/cellweb5.cab#Version=1,0,3,0" style="display: none; POSITION:absolute"
 classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A>
 <PARAM NAME="_Version" VALUE="65536">
 <PARAM NAME="_ExtentX" VALUE="9710">
 <PARAM NAME="_ExtentY" VALUE="4842">
 <PARAM NAME="_StockProps" VALUE="0">
</OBJECT>

<TABLE  cellpadding='0' cellspacing='0' width="100%">
<html:form action="/dailyoffice/workreport/viewworkreport" method="post">
<html:hidden name="DoWorkReportForm" property="cellcontent"/>
<html:hidden name="DoWorkReportForm" property="actionType"/>
<html:hidden name="DoWorkReportForm" property="vo.send_flag"/>
<html:hidden name="DoWorkReportForm" property="vo.checker"/>
<html:hidden name="DoWorkReportForm" property="vo.checker_name"/>
<html:hidden name="DoWorkReportForm" property="vo.check_date"/>
<html:hidden name="DoWorkReportForm" property="vo.begin_time"/>
<html:hidden name="DoWorkReportForm" property="vo.end_time"/>
<TR>
	<TD>
	<div align="center">
  <center>
    <table  id="idTRTop"  align="center" width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center" colspan="2"><span class="TableTitleText">工作汇报</span></td>
      </tr>
      <tr>
	<td align="center">
			<table width="98%" border="0" cellspacing="0" cellpadding="0">
                  <tr> 
                    <td align="center"> 
					<table  width="100%"  id="idTBTop" border="0" cellpadding="0" cellspacing="2">
					<tr height="23" ><td></td></tr>
					<tr class="listcellrow"  height="23" >
						  <td width="30%"   nowrap><font class="InputLabelCell">被汇报人：</font><bean:write  name="DoWorkReportForm" property="vo.render_name" filter="true"/>
						  </td>
                          <td width="30%"  nowrap><font class="InputLabelCell">开始时间：</font><bean:write  name="DoWorkReportForm" property="vo.begin_time" filter="true"/></td>
                          <td nowrap ><font class="InputLabelCell">结束时间：</font><bean:write  name="DoWorkReportForm" property="vo.end_time" filter="true"/></td>
                        </tr>
						<tr class="listcellrow">
                          <td colspan="3"></td>
                        </tr>
						<tr class="listcellrow">
                          <td colspan="3"></td>
                        </tr>                      </table>
                      <div id="divTips" style="LEFT: 109px; POSITION: relative; top: 179px;">正在加载华表模板..........</div>
                      <table align="center" id="idTBBottom" style="POSITION:absolute; left: 5px; top: 199px;" cellpadding='0' cellspacing='2' width="100%">
                        <tr class="listcellrow"  height="23" > 
                          <td width="200"  nowrap>
						  <font class="InputLabelCell">编 制 人：</font><bean:write  name="DoWorkReportForm" property="vo.user_name" filter="true"/>
						  </td>
					   <td width="200"  nowrap>
						  <font class="InputLabelCell">编制日期：</font><bean:write  name="DoWorkReportForm" property="vo.create_date" filter="true"/>					  
						  </td>
                          <td width="200"  nowrap><font class="InputLabelCell">审 批 人：</font><bean:write  name="DoWorkReportForm" property="vo.checker_name" filter="true"/></td>
						  <td width="200"  nowrap ><font class="InputLabelCell">审批日期：</font><bean:write  name="DoWorkReportForm" property="vo.check_date" filter="true"/></td>
						  </tr>
						  <tr height="10" ><td></td></tr>
		<tr>
        <td align="center" colspan="4">
<% 
String 
temp1="toUrl('"+context+"/dailyoffice/workreport/editworkreport.do?actionType=modify',false)";
String 
temp2="toUrl('"+context+"/dailyoffice/workreport/editworkreport.do?actionType=modify',false)";
%>
		<html:hidden name="DoWorkReportForm" property="fid" /> 
		<logic:equal name="DoWorkReportForm" property="listType" value = "0">
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=temp1%>" operation="GZHB.OPT"/> 
		</logic:equal>
		<logic:equal name="DoWorkReportForm" property="listType" value = "1">
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=temp2%>" operation="GZHB.OPT"/> 
		</logic:equal>
		<logic:equal name="DoWorkReportForm" property="listType" value = "3">
		<logic:equal name="DoWorkReportForm" property="vo.checker_name" value = "">
		<goa:button property="check" value="审批" styleClass="button"  onclick="checkReport(DoWorkReportForm)" operation="GZHB.OPT"/> 
		</logic:equal>
		</logic:equal>
		<goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="GZHB.VIEW"/>
		<input type="button" class="button" name="print" value="打印预览" onclick="mnuFilePrintPreview_click()">
		<input type="button" class="button" name="output" value="输出为Excel文件" onclick="mnuFileExportExcel_click()">
		</td>
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


