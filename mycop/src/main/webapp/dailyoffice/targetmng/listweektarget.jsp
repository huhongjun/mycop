<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page import="java.util.*,java.util.Calendar"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
Calendar calendar =Calendar.getInstance();
%>
<SCRIPT LANGUAGE="JavaScript">
var cellcontent = new Array();
var cellids = new Array();
var weekcell = new Array();

<%
    Calendar weekcalendar = (Calendar)calendar.clone();
    weekcalendar.set(Calendar.DAY_OF_WEEK, 1);
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
%>
 var months = new Array("1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12");
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
</script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function doSearch(){//校验页面--通过后提交
	var tform = document.forms(0);	
	if(tform.elements("searchMonth").value != null && tform.elements("searchMonth").value != ""){
	if(tform.elements("searchMonth").value>12 || tform.elements("searchMonth").value<1){
		
		alert("请保证月度数在1-12之间！");
		tform.elements("searchMonth").focus();
		return false;
	}	
	}
	if(tform.elements("searchWeek").value != null && tform.elements("searchWeek").value != ""){
	if(tform.elements("searchWeek").value>5 || tform.elements("searchWeek").value<1){
		
		alert("请保证周数在1-5之间！");
		tform.elements("searchWeek").focus();
		return false;
	}
	}
    doQuery('<%=context%>/dailyoffice/targetmng/listweektarget.do',true)
	return true;
}
//-->
</SCRIPT>
<html>
<body class="bodybg">
  <html:form action="/dailyoffice/targetmng/listweektarget" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="TableTitleText"><bean:write  name="WeekTargetForm" property="deptnodename" filter="true"/>周目标</td>
  </tr>
  <tr> 
    <td colspan="8" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="8" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
           <td class="t12">
		   <!--需要做一个通用的选择年和月的框就好-->
     	  <!-- Year combo box -->
        <html:select property="searchYear">
        <SCRIPT LANGUAGE="JavaScript">
          for (var intLoop = today.year-50; intLoop < (today.year + 10); intLoop++)
          document.write("<OPTION VALUE= " + intLoop + " " +(today.year == intLoop ?"selected" : "") + ">" +  intLoop);
          </SCRIPT>
          </html:select>
		  <span class="InputLabelCell">年</span>
        <html:select property="searchMonth">
        <SCRIPT LANGUAGE="JavaScript">
        // Output months into the document.
        // Select current month.
        for (var intLoop = 0; intLoop < months.length; intLoop++){
        document.write("<OPTION VALUE= " + (intLoop + 1) + " " + (today.month == intLoop ? "selected" : "") + ">" + months[intLoop]);
        }
        </SCRIPT>
        </html:select>
		<span class="InputLabelCell">月</span>
		<!-- Week combo box -->
        <html:select property="searchWeek">
        <SCRIPT LANGUAGE="JavaScript">
        // Output months into the document.
        // Select current month.
        for (var intLoop = 0; intLoop < weeks.length; intLoop++){
        document.write("<OPTION VALUE= " + (intLoop + 1) + " " + (today.week == (intLoop+1) ? "selected" : "") + ">" + weeks[intLoop]);
        }
        </SCRIPT>
        </html:select>
		<span class="InputLabelCell">周</span>
          </td>
	      <td>
	  <goa:button operation="MZMB.VIEW" styleClass="button"  property="query" onclick="doSearch()" value="查询"/>
          </td> 
          <td><goa:button operation="MZMB.OPT" styleClass="button"  property="add" onclick="doAdd()" value="新增"/></td>          
          <td>
           <% String temp="toUrl('"+context+"/dailyoffice/targetmng/editweektarget.do?actionType=modify&flag=modify',true)";%>
	  <goa:button operation="MZMB.OPT" styleClass="button"  property="modify" onclick="<%=temp%>" value="修改"/>
          </td>
		  <!--
          <td>
          <goa:button operation="MZMB.VIEW"  styleClass="button" property="view" value="查看"  onclick="toUrl('/goa/dailyoffice/targetmng/viewweektarget.do',true)"/>
		  </td>
		  -->
          <td> 
		  <goa:button operation="MZMB.OPT"  styleClass="button" property="delete" onclick="doDelete()"  value="删除"/>
          </td>		  
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="10">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr> 
            <td valign="top" width="3%" class="Listtitle">              
              <input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)">
            </td>
			<td class="Listtitle" nowrap width="25%" id=".current_year" onclick="onchangeOrderBy(this)"><div align="left">年</div></td>
			<td class="Listtitle" nowrap width="25%" id=".current_month" onclick="onchangeOrderBy(this)"><div align="left">月</div></td>
			<td class="Listtitle" nowrap width="25%" id=".current_work" onclick="onchangeOrderBy(this)"><div align="left">周</div></td>
			<td class="Listtitle" nowrap width="25%"><div align="left">审核审批状况</div></td>
		</tr>
    <logic:iterate id="ps" name="WeekTargetForm" property="pageControl.data" type="com.gever.goa.dailyoffice.targetmng.vo.TargetVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/targetmng/viewweektarget.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="left" class="listcellrow">
		<logic:equal name="ps" property="edit_delete_flag" value = "1">
		<input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >	  
		</logic:equal>
		<logic:equal name="ps" property="edit_delete_flag" value = "0">
        <input type='checkbox' disabled> 
		</logic:equal>
		</td>		
		<td class="listcellrow"><bean:write name="ps" property="current_year" filter="true"/>&nbsp;</td>
		<td class="listcellrow"><bean:write name="ps" property="current_month" filter="true"/>&nbsp;</td>
		<td class="listcellrow">
		<% String link_url = ""+context+"/dailyoffice/targetmng/viewweektarget.do?fid="+ ps.getSerial_num();%>
		<goa:link href="<%=link_url%>"><bean:write name="ps" property="current_work" filter="true"/></goa:link>&nbsp;</td>	
		<td class="listcellrow4"><bean:write name="ps" property="audit_check_name" filter="true"/>&nbsp;</td>
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
	    <html:hidden name="WeekTargetForm" property="orderType"/>
        <html:hidden name="WeekTargetForm" property="orderFld"/>
		<goa:pagelink name="WeekTargetForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
