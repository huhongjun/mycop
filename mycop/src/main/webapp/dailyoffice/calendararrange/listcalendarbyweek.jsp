
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
//Calendar calendar=Calendar.getInstance();
int listindex=0;
%>
<html>
<STYLE TYPE="text/css">
td.Listtitle  { 
	width:16%;
	height:4%;
	text-align: center;
	}

.contentcss {
	.listcellrow;
	font-size: 14px;
}
</STYLE>
<HEAD>
 <SCRIPT LANGUAGE="JavaScript">
var cellcontent = new Array();
var serialnum = new Array();
var cellids = new Array();
var weekcell = new Array();

<%
    Calendar weekcalendar = (Calendar)calendar.clone();
    weekcalendar.set(Calendar.DAY_OF_WEEK, 2);
for (int i=0;i<7 ;i++ ) {	
  java.sql.Date weekdate = new java.sql.Date(weekcalendar.getTime().getTime()); 
  out.println("weekcell[" + i + "] = '" + weekdate + "';");
  weekcalendar.add(Calendar.DAY_OF_WEEK, 1);
}

int weeklen = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
System.out.println("weeklen = "+weeklen);
out.print("var weeks = new Array(1");
for (int i=1;i<weeklen ; i++) {
  out.print("," + (i+1));
}
out.print(");");



for (Iterator monthiter = monthList.iterator(); monthiter.hasNext(); ) {
  List dayList= (List)monthiter.next();
  out.print("cellcontent[" + listindex++ + "] = \"\" + ");
  int itemcount=0;
  for (Iterator dayiter = dayList.iterator(); dayiter.hasNext(); ) {
    CalendarArrangeVO vo = (CalendarArrangeVO)dayiter.next();
    out.print("\"<br><a href='"+context+"/dailyoffice/calendararrange/listcalendar.do?action=toView&fid="+vo.getSerial_num()+"'>" + vo.getBegin_time()+ " " + vo.getTask_sum() + "</a></br>\" + ");
	if(itemcount<5)	{
		itemcount++;
	}
	else {
    out.print("\"........\" + ");
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
	 // Test for leap year when February is selected.
	 if (1 == month)
		 return ((0 == year % 4) && (0 != (year % 100))) || (0 == year % 400) ? 29 : 28;
	 else
	 return daysInMonth[month];
 }
 function getToday() {
	 // Generate today's date.
	 this.now = new Date(<%=calendar.get(Calendar.YEAR)%>,<%=calendar.get(Calendar.MONTH)%>,<%=calendar.get(Calendar.DATE)%>,<%=calendar.get(Calendar.HOUR)%>,<%=calendar.get(Calendar.MINUTE)%>,<%=calendar.get(Calendar.SECOND)%>);
	 this.year = this.now.getFullYear();
	 this.month = this.now.getMonth();
	 this.day = this.now.getDate();
     this.week = <%=calendar.get(Calendar.WEEK_OF_MONTH)%>;
	 this.hour = this.now.getHours();
	 this.minute = this.now.getMinutes();
	 this.second = this.now.getSeconds();
}
// Start with a calendar for today.
today = new getToday();

function newCalendar() {
	toLastCell1();
    toLastCell2();
	var datestr = new Date();
	todaydate = datestr.getFullYear() + "-" + addZero(datestr.getMonth()+1) + "-" + addZero(datestr.getDate());
	var tableCal1 = document.all.calendar.tBodies.dayList1;
	var tableCal2 = document.all.calendar.tBodies.dayList2;
	var dayindex=0;
	for (var intWeek = 0; intWeek < tableCal1.rows.length;intWeek++)
		for (var intDay = 0;intDay < tableCal1.rows[intWeek].cells.length-1;intDay++) {
		var cell = tableCal1.rows[intWeek].cells[intDay];
		var divbstr = (weekcell[dayindex] == todaydate) ? "<font color='red' size='5px'><strong>" : "<font color='#339933' size='5px'><strong>";
		var divestr = (weekcell[dayindex+1] == todaydate) ? "</strong></font>" : "</strong></font>";
		cell.id = weekcell[dayindex];
		cell.innerText = cell.id.substring(5);
		cell.innerHTML =
			"<table width='100%' height='100%'  border='0' cellspacing='0' cellpadding='0'>" +
			"<tr class='listcellrow' >"+
			"<td  height='10%' valign='top' align='center' >" +
			"<a href=\"javascript:getDate2('"+ cell.id +"')\">" +
			divbstr + cell.innerText + divestr +
			"</a>" +
			"</td>"+
			"</tr>" +
			"<tr class='contentcss' >"+
			"<td  valign='top'  align='left' height='90%' style='word-break: break-all'>"+
		    cellcontent[dayindex]  +
			"</td>" +
			"</tr>" +
			"</table>";
			//alert(cellcontent[dayindex]);
		
		dayindex++;
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


function toLastCell1(){
	var datestr = new Date();
	var todaydate = datestr.getFullYear() + "-" + addZero(datestr.getMonth()+1) + "-" + addZero(datestr.getDate());
	var divbstr = (weekcell[5] == todaydate) ? "<font color='red' size='5px'><strong>" : "<font color='#339933' size='5px'><strong>";
		var divestr = (weekcell[5] == todaydate) ? "</strong></font>" : "</strong></font>";
	var mytable=document.all.namedItem("tab3");
	var myrow=mytable.rows;
	var cell=myrow[0].cells;
	var mycell=cell[0];
  mycell.id=weekcell[5];
 
  mycell.innerText=  mycell.id.substring(5);
  
  mycell.innerHTML =
			"<table width='100%' height='100%'  border='0' cellspacing='0' cellpadding='0'>" +
			"<tr class='listcellrow' >"+ 
			"<td  height='10%' valign='top' align='center' >" +
			"<a href=\"javascript:getDate2('"+ mycell.id +"')\">" + divbstr+
			mycell.innerText + divestr+
			"</a>" +
			"</td>"+
			"</tr>" +
			"<tr>" + "<td height='10%'>" + "</td></tr>" +
			"<tr class='contentcss' >"+
			"<td  valign='top'  align='left' height='80%' style='word-break: break-all'>"+
		    cellcontent[5]  +
			"</td>" +
			"</tr>" +
			"</table>";
			
}
function toLastCell2(){
	
	var datestr = new Date();
	var todaydate = datestr.getFullYear() + "-" + addZero(datestr.getMonth()+1) + "-" + addZero(datestr.getDate());
	var divbstr = (weekcell[6] == todaydate) ? "<font color='red' size='5px'><strong>" : "<font color='#339933' size='5px'><strong>";
		var divestr = (weekcell[6] == todaydate) ? "</strong></font>" : "</strong></font>";
	var mytable=document.all.namedItem("tab3");
	var myrow=mytable.rows;
	var cell=myrow[2].cells;
	var mycell=cell[0];
  mycell.id=weekcell[6];
  
  mycell.innerText=  mycell.id.substring(5);
  
  mycell.innerHTML =
			"<table width='100%' height='100%'  border='0' cellspacing='0' cellpadding='0'>" +
			"<tr class='listcellrow' >"+
			"<td  height='10%' valign='top' align='center' >" +
			"<a href=\"javascript:getDate2('"+ mycell.id +"')\">" + divbstr+
			mycell.innerText + divestr+
			"</a>" +
			"</td>"+
			"<tr class='contentcss' >"+
			"<td  valign='top'  align='left' height='90%' style='word-break: break-all'>"+
		    cellcontent[6]  +
			"</td>" +
			"</tr>" +
			"</tr>" +
			"</table>";
			
}
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function changeArranger(){
	var form1 = document.forms[1];
	var selectuser = document.forms[0].elements("rightUsers");
	form1.action="listcalendarbyweek.do";
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
<html:form action="/dailyoffice/calendararrange/listcalendarbyweek" method="post">
<html:hidden name="CalendarArrangeForm" property="listDate"/>
  <input type="hidden" name="ret">
  
 	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td  ALIGN=left class="InputLabelCell">选择人员:
    <select name="rightUsers"  onchange="changeArranger();" styleClass="input0">
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
          for (var intLoop = today.year-50; intLoop < (today.year + 10); intLoop++)
          document.write("<OPTION VALUE= " + intLoop + " " +(today.year == intLoop ?"selected" : "") + ">" +  intLoop);
          </SCRIPT>
          </html:select>
		  年
        <html:select property="calendarMonth" styleClass="input0">
        <SCRIPT LANGUAGE="JavaScript">
        // Output months into the document.
        // Select current month.
        for (var intLoop = 0; intLoop < months.length; intLoop++){
        document.write("<OPTION VALUE= " + (intLoop + 1) + " " + (today.month == intLoop ? "selected" : "") + ">" + months[intLoop]);
        }
        </SCRIPT>
        </html:select>
		月
		<!-- Week combo box -->
        <html:select property="calendarWeek" styleClass="input0">
        <SCRIPT LANGUAGE="JavaScript">
        // Output months into the document.
        // Select current month.
        for (var intLoop = 0; intLoop < weeks.length; intLoop++){
        document.write("<OPTION VALUE= " + (intLoop + 1) + " " + (today.week == (intLoop+1) ? "selected" : "") + ">" + weeks[intLoop]);
        }
        </SCRIPT>
        </html:select>
		周
		  <goa:button  styleClass="button" property="delete" value="浏览"  onclick="document.forms[0].submit()"  operation="LTAP.VIEW"/>
		  <goa:button  styleClass="button" property="delete" value="按月"  onclick="location.href='listcalendarbymonth.do'"  operation="LTAP.VIEW"/>
		  <goa:button  styleClass="button" property="delete" value="按周"  onclick="location.href='listcalendarbyweek.do'"  operation="LTAP.VIEW"/>
		  <goa:button  styleClass="button" property="delete" value="设置"  onclick="location.href='framecalendar.jsp'"  operation="LTAP.VIEW"/>
		  </td>
  </tr>
</table>
<br>
<TABLE  width='100%' height='86%'   border="1"  cellspacing="0" cellpadding="0" ID="calendar">
    <THEAD>
    <TR align='center'>
      <!-- Generate column for each day. -->
      <SCRIPT LANGUAGE="JavaScript">
       // Output days.
       for (var intLoop = 0; intLoop < 5;intLoop++)
         document.write("<TD class='Listtitle' width='15%' nowrap>" + days[intLoop+1] + "</TD>");
		 document.write("<TD class='Listtitle' >" + days[6] + "</TD>");
    </SCRIPT>
    </TR>
	</THEAD>
	<TBODY ID="dayList1" ALIGN=CENTER>
    <!-- Generate grid for individual days. -->
    <SCRIPT LANGUAGE="JavaScript">
		for (var intWeeks = 0; intWeeks < 1; intWeeks++) {
			document.write("<TR>");
			for (var intDays = 0; intDays <6;intDays++){
				if(intDays==5)
				{
				document.write("<td>");
				document.write("<table id='tab3' width='100%' height='100%' border='0' cellpadding='0' cellspacing='0'>");
				document.write("<tr>");
				document.write("<td ></td>");
				document.write("</tr>");
				document.write("<tr>");
				document.write("<td align='center' class='Listtitle'>" + days[0] + "</td>");
				document.write("</tr>");
				document.write("<tr>");
				document.write("<td height='47%'></td>");
				document.write("</tr>");
				document.write("</table>");
				document.write("</td>");
				}
				
				else
				document.write("<TD  height='97%' width='17%'></TD>");
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


