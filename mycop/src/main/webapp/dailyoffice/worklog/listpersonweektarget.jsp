<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page import="java.util.*,com.gever.util.StringUtils,java.util.Calendar,com.gever.goa.dailyoffice.worklog.action.PersonWeekTargetForm;"%>

<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
Calendar calendar = Calendar.getInstance();
PersonWeekTargetForm personWeekTargetForm = (PersonWeekTargetForm)session.getAttribute("PersonWeekTargetForm");
String searchYear = personWeekTargetForm.getSearchYear();
String searchMonth = personWeekTargetForm.getSearchMonth();
String searchWeek = personWeekTargetForm.getSearchWeek();
if(!StringUtils.isNull(searchYear)){
 calendar.set(Calendar.YEAR, Integer.parseInt(searchYear));
}
if(!StringUtils.isNull(searchMonth)){
 calendar.set(Calendar.MONTH, Integer.parseInt(searchMonth)-1);
}
if(!StringUtils.isNull(searchWeek)){
 calendar.set(Calendar.WEEK_OF_MONTH, Integer.parseInt(searchWeek));
}
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
function doSearch(){//校验页面--通过后提交
	var tform = document.forms(0);
    doQuery('<%=context%>/dailyoffice/worklog/listpersonweektarget.do',true);
	return true;
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
today = new getToday();
 var months = new Array("1", "2", "3", "4", "5", "6", "7", "8", "9","10", "11", "12");

<%
int weeklen = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
System.out.println("weeklen = "+weeklen);
out.print("var weeks = new Array(1");
for (int i=1;i<weeklen ; i++) {
  out.print("," + (i+1));
}
out.print(");");
%>

//-->
</SCRIPT>
<html>
<body class="bodybg">
  <html:form action="/dailyoffice/worklog/listpersonweektarget" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="10">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="10" align="center" class="TableTitleText">
	<logic:equal name="PersonWeekTargetForm" property="viewFlag" value="1"><bean:write  name="PersonWeekTargetForm" property="deptnodename" filter="true"/></logic:equal>个人周汇报</td>
  </tr>
  <tr>
    <td colspan="10" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="10" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
           <td class="InputLabelCell">
		   <!--需要做一个通用的选择年和月的框就好-->
        <html:select property="searchYear" styleClass="input0">
        <SCRIPT LANGUAGE="JavaScript">
          for (var intLoop = today.year-50; intLoop < (today.year + 10); intLoop++)
          document.write("<OPTION VALUE= " + intLoop + " " +(today.year == intLoop ?"selected" : "") + ">" +  intLoop);
          </SCRIPT>
          </html:select>
		  年
			<html:select property="searchMonth" styleClass="input0">
        <SCRIPT LANGUAGE="JavaScript">
        // Output months into the document.
        // Select current month.
        for (var intLoop = 0; intLoop < months.length; intLoop++){
        document.write("<OPTION VALUE= " + (intLoop + 1) + " " + (today.month == intLoop ? "selected" : "") + ">" + months[intLoop]);
        }
        </SCRIPT>
        </html:select>
		月
		 <html:select property="searchWeek" styleClass="input0">
        <SCRIPT LANGUAGE="JavaScript">
        // Output months into the document.
        // Select current month.
        for (var intLoop = 0; intLoop < weeks.length; intLoop++){
        document.write("<OPTION VALUE= " + (intLoop + 1) + " " + (today.week == (intLoop+1) ? "selected" : "") + ">" + weeks[intLoop]);
        }

		
        </SCRIPT>
        </html:select>
		周
			 <html:text styleClass="input2" property="searchUserName" size="10" maxlength="10"/>&nbsp;用户

	  <goa:button operation="LZMB.VIEW" styleClass="button"  property="query" onclick="doSearch()" value="查询"/>

<logic:notEqual name="PersonWeekTargetForm"  property="deptnodeid" value="">
          <goa:button operation="LZMB.OPT" styleClass="button"  property="add" onclick="doAdd()" value="新增"/>
</logic:notEqual>

<% 
String 
temp1="toUrl('"+context+"/dailyoffice/worklog/editpersonweektarget.do?actionType=modify',true)";
%>
<% 
String 
temp2="toUrl('"+context+"/dailyoffice/worklog/viewpersonweektarget.do',true)";
%>
	  <goa:button operation="LZMB.OPT" styleClass="button"  property="modify" onclick="<%=temp1%>" value="修改"/>
          <goa:button operation="LZMB.VIEW"  styleClass="button" property="view" value="查看"  onclick="<%=temp2%>"/>
		  <goa:button operation="LZMB.OPT"  styleClass="button" property="delete" onclick="doDelete()"  value="删除"/>
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
			<td class="Listtitle" nowrap width="20%" id=".current_year" onclick="onchangeOrderBy(this)"><div align="left">年</div></td>
			<td class="Listtitle" nowrap width="20%" id=".current_month" onclick="onchangeOrderBy(this)"><div align="left">月</div></td>
			<td class="Listtitle" nowrap width="30%" id=".current_work" onclick="onchangeOrderBy(this)"><div align="left">周</div></td>
			<td class="Listtitle" nowrap width="30%" id=".user_code" onclick="onchangeOrderBy(this)"><div align="left">姓名</div></td>
		</tr>
    <logic:iterate id="ps" name="PersonWeekTargetForm" property="pageControl.data" type="com.gever.goa.dailyoffice.worklog.vo.PersonTargetVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/worklog/viewpersonweektarget.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="left" class="listcellrow">
		<logic:equal name="ps" property="edit_delete_flag" value = "1">
		<input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >
		</logic:equal>
		<logic:equal name="ps" property="edit_delete_flag" value = "0">
		<input type='checkbox' disabled>
		</logic:equal>
		</td>
		
		<td class="listcellrow"><bean:write name="ps" property="current_year" filter="true" /></td>
		<td class="listcellrow"><bean:write name="ps" property="current_month" filter="true"/></td>
		<td class="listcellrow">
		<a href="<%=context%>/dailyoffice/worklog/viewpersonweektarget.do?fid=<bean:write name='ps' property='serial_num' filter='true'/>">
		<bean:write name="ps" property="current_work" filter="true"/>
		</a>
		</td>
		<td class="listcellrow4"><bean:write name="ps" property="user_name" filter="true"/></td>
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr>
    <td colspan="8" align="center" class="f12">
	    <html:hidden name="PersonWeekTargetForm" property="orderType"/>
        <html:hidden name="PersonWeekTargetForm" property="orderFld"/>
		<goa:pagelink name="PersonWeekTargetForm" property="pageControl" />
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
