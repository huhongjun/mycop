<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page import="java.util.*,java.util.Calendar,com.gever.goa.dailyoffice.calendararrange.vo.CalendarArrangeVO,com.gever.goa.dailyoffice.calendararrange.action.CalendarArrangeForm"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>

<%
String context =request.getContextPath();
CalendarArrangeForm calendarform = (CalendarArrangeForm)session.getAttribute("CalendarArrangeForm");
List monthList = calendarform.getCalendarList();
Calendar calendar = calendarform.getCalendar();
System.out.println("calendar.get(Calendar.MONTH)"+calendar.get(Calendar.MONTH));
System.out.println("calendarform.getCalendarMonth()"+calendarform.getCalendarMonth());
int listindex=0;
%>
<html>
<STYLE TYPE="text/css">
td.Listtitle  { 
	width:13.5%;
	height:3%;
	text-align: center;
	}
</STYLE>
<HEAD>
 <SCRIPT LANGUAGE="JavaScript">
var cellcontent = new Array();
<%
for (Iterator monthiter = monthList.iterator(); monthiter.hasNext(); ) {
  List dayList= (List)monthiter.next();
  out.print("cellcontent[" + listindex++ + "] = \"\" + ");
  int itemcount=0;
  for (Iterator dayiter = dayList.iterator(); dayiter.hasNext(); ) {
    CalendarArrangeVO vo = (CalendarArrangeVO)dayiter.next();
    out.print("\"<br>" + "<a href='"+context+"/dailyoffice/calendararrange/listcalendar.do?action=toView&fid=" +vo.getSerial_num() + "'>" + vo.getBegin_time()+ " " + vo.getTask_sum() + "</a></br>\" + ");
	if(itemcount<1)	{
		itemcount++;
	}
	else {
    out.print("\"．．．．．．\" + ");
	break;
  }
  }
  out.println("\"\";");
}
%>
 var months = new Array("1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12");
 var daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31,30, 31, 30, 31);
 var days = new Array("日", "一", "二","三", "四", "五", "六");
 function getDays(month, year) {
	 if (1 == month)
		 return ((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400) ? 29 : 28;
	 else
	 return daysInMonth[month];
 }
 function getToday() {
	 this.now = new Date(<%=calendar.get(Calendar.YEAR)%>,<%=calendar.get(Calendar.MONTH)%>,<%=calendar.get(Calendar.DATE)%>,<%=calendar.get(Calendar.HOUR)%>,<%=calendar.get(Calendar.MINUTE)%>,<%=calendar.get(Calendar.SECOND)%>);
	 this.year = this.now.getFullYear();
	 this.month = this.now.getMonth();
	 this.day = this.now.getDate();
	 this.hour = this.now.getHours();
	 this.minute = this.now.getMinutes();
	 this.second = this.now.getSeconds();
}

today = new getToday();
function newCalendar() {
	var datestr = new Date();
	todaydate = datestr.getFullYear() + "-" + addZero(datestr.getMonth()+1) + "-" + addZero(datestr.getDate());
	var yearselect = document.forms[0].elements("calendarYear");
	var parseYear = parseInt(yearselect[yearselect.selectedIndex].text);
	var newCal = new Date(parseYear,document.forms[0].elements("calendarMonth").selectedIndex, 1);
	var day = -1;
	var startDay = newCal.getDay();
	var daily = 0;
	if ((today.year == newCal.getFullYear()) && (today.month == newCal.getMonth()))
		day = today.day;
	var tableCal = document.all.calendar.tBodies.dayList;
	var intDaysInMonth = getDays(newCal.getMonth(), newCal.getFullYear());
	for (var intWeek = 0; intWeek < tableCal.rows.length;intWeek++){
		for (var intDay = 0;intDay < tableCal.rows[intWeek].cells.length;intDay++) {
		var cell = tableCal.rows[intWeek].cells[intDay];
		if ((intDay == startDay) && (0 == daily))
			daily = 1;
		nowdate = today.year + "-" + addZero(today.month+1) + "-" + addZero(daily);
		var divbstr = (todaydate == nowdate) ? "<font color='red' size='5px'><strong>" : "<font color='#339933' size='5px'><strong>";
		var divestr = (todaydate == nowdate) ? "</strong></font>" : "</strong></font>";
		if ((daily > 0) && (daily <= intDaysInMonth)){
			cell.id = today.year + "-" + addZero(today.month+1) + "-" + addZero(daily);
			cell.innerText = daily;
			cell.innerHTML =
			"<table width='100%' height='100%'  border='0' cellspacing='0' cellpadding='0'>" +
			"<tr class='listcellrow' >"+
			"<td height='10%' valign='top' align='center' >" +
			"<a href=\"javascript:getDate2('"+ cell.id +"')\">" +
			divbstr + cell.innerText + divestr +
			"</a>" +
			"</td>"+
			"<tr class='listcellrow' >"+
			"<td  valign='top'  align='left' height='90%' style='word-break: break-all'>"+
			cellcontent[daily-1] +
			"</td>" +
			"</tr>" +
			"</table>";
			daily++;
		}
		else
			cell.innerText = " ";
	}
	}
}
function addZero(val){
	if(parseInt(val)<10) val="0"+val;
	return val;
}
function getDate(cell) {
	var sDate;
	var celltext="";
	try{
		celltext = cell.innerText;
	} catch (e){return;}

	if ("" != celltext.replace(/(^\s*)|(\s*$)/g, "")){
		sDate = cell.id;
		document.all.ret.value = sDate;
		var form = document.forms[1];
		form.elements("listDate").value = sDate;
		form.submit();
		}
		else document.all.ret.value='';
}
function getDate2(str) {
	var sDate;
	sDate = str;
	document.all.ret.value = sDate;
	var form = document.forms[1];
	form.elements("listDate").value = sDate;
	form.submit();
	}
function changeCalendar(){
		var form = document.forms[0];
		form.submit();
}
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function changeArranger(){
	var form1 = document.forms[1];
	var selectuser = document.forms[0].elements("rightUsers");
	form1.action="listcalendarbymonth.do";
	var calendarUserVO = selectuser.value;
	var userarr = getValueOfArranger(calendarUserVO,"|");
	form1.elements("calendarUserVO.userCode").value = userarr[0];
	form1.elements("calendarUserVO.userName").value = userarr[1];
	form1.elements("calendarUserVO.rightType").value = userarr[2];
	form1.submit();
}

function getValueOfArranger(objvalue,str){
	var userarr = objvalue.split(str);
	return userarr;
}
//-->
</SCRIPT>
</HEAD>
<BODY ONLOAD="newCalendar();" OnUnload="window.returnValue = document.all.ret.value;" class="bodybg">
<html:form action="/dailyoffice/calendararrange/listcalendarbymonth" method="post">
<html:hidden name="CalendarArrangeForm" property="listDate"/>
  <input type="hidden" name="ret">
  <TABLE   width='100%' height='100%' border="1" cellpadding="0" cellspacing="0" ID="calendar">
    <THEAD>
    <TR>
      <TD COLSPAN=7 height="30" class="InputLabelCell">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td  ALIGN=left class="InputLabelCell">
  选择人员:
    <select name="rightUsers" styleClass="input0" onchange="changeArranger();" >
      <%
      String userid = calendarform.getCalendarUserVO().getUserCode();
      if(calendarform.getCalendarUserVO().getUserCode().equals(calendarform.getUserId())){
      %>
	<option value='<bean:write name="CalendarArrangeForm" property="userId"/>|<bean:write name="CalendarArrangeForm" property="userName"/>|1' selected="selected">
      <%}else{ %>
	<option value='<bean:write name="CalendarArrangeForm" property="userId"/>|<bean:write name="CalendarArrangeForm" property="userName"/>|1'>
      <%}%>
	  <bean:write name="CalendarArrangeForm" property="userName"/>
	  </option>
	<logic:iterate id="cuvo" name="CalendarArrangeForm" property="calendarUserList" type="com.gever.goa.dailyoffice.calendararrange.vo.CalendarUserVO" indexId="index">
      <%
      if(calendarform.getCalendarUserVO().getUserCode().equals(cuvo.getUserCode())){
      %>
	<option value='<bean:write name="cuvo" property="userCode"/>|<bean:write name="cuvo" property="userName"/>|<bean:write name="cuvo" property="rightType"/>' selected="selected">
      <%}else{ %>
	<option value='<bean:write name="cuvo" property="userCode"/>|<bean:write name="cuvo" property="userName"/>|<bean:write name="cuvo" property="rightType"/>'>
      <%}%>
	<bean:write name="cuvo" property="userName"/>
	</option>
	</logic:iterate>
	</select>
	</td>
    <td align="right" class="InputLabelCell">
	     <!-- Year combo box -->
        <html:select property="calendarYear" styleClass="input0">
        <SCRIPT LANGUAGE="JavaScript">
          // Output years into the document.
          // Select current year.
          for (var intLoop = today.year-50; intLoop < (today.year + 10); intLoop++)
          document.write("<OPTION VALUE= " + intLoop + " " +(today.year == intLoop ?"selected" : "") + ">" +  intLoop);
          </SCRIPT>
        </html:select>
		年
        <!-- Month combo box -->
        <html:select property="calendarMonth" styleClass="input0">
        <SCRIPT LANGUAGE="JavaScript">
        for (var intLoop = 0; intLoop < months.length; intLoop++){
        document.write("<OPTION VALUE= " + (intLoop + 1) + " " + (today.month == intLoop ? "selected" : "") + ">" + months[intLoop]);
        }
        </SCRIPT>
        </html:select>
		月
		  <goa:button  styleClass="button" property="delete" value="浏览"  onclick="document.forms[0].submit()"  operation="LTAP.VIEW"/>
		  <goa:button  styleClass="button" property="delete" value="按月"  onclick="location.href='listcalendarbymonth.do'"  operation="LTAP.VIEW"/>
		  <goa:button  styleClass="button" property="delete" value="按周"  onclick="location.href='listcalendarbyweek.do'"  operation="LTAP.VIEW"/>
		  <goa:button  styleClass="button" property="delete" value="设置"  onclick="location.href='framecalendar.jsp'"  operation="LTAP.VIEW"/>
</td>
  </tr>
</table>
		</TD>

      </TD>
    </TR>
    <TR align='center'>
      <!-- Generate column for each day. -->
      <SCRIPT LANGUAGE="JavaScript">
       // Output days.
       for (var intLoop = 0; intLoop < days.length;intLoop++)
         document.write("<TD class='Listtitle'>" + days[intLoop] + "</TD>");
    </SCRIPT>
    </TR>
    </THEAD>
	<TBODY ID="dayList" ALIGN=CENTER>
    <!-- Generate grid for individual days. -->
    <SCRIPT LANGUAGE="JavaScript">
		for (var intWeeks = 0; intWeeks < 6; intWeeks++) {
			document.write("<TR>");
			for (var intDays = 0; intDays < days.length;intDays++){
				document.write("<TD width='14%' height='16%' align='center'></TD>");
			}
			document.write("</TR>");
		}
	</SCRIPT>
    </TBODY>
  </TABLE>
</html:form>
<html:form action="/dailyoffice/calendararrange/listcalendar" method="post">
<html:hidden name="CalendarArrangeForm" property="listDate"/>
<html:hidden name="CalendarArrangeForm" property="calendarUserVO.userCode"/>
<html:hidden name="CalendarArrangeForm" property="calendarUserVO.userName"/>
<html:hidden name="CalendarArrangeForm" property="calendarUserVO.rightType"/>
</html:form>
</BODY>
</HTML>


