<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page import="java.util.*,com.gever.util.StringUtils,java.util.Calendar,com.gever.goa.dailyoffice.worklog.action.PersonYearTargetForm;"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
Calendar calendar = Calendar.getInstance();
PersonYearTargetForm personYearTargetForm = (PersonYearTargetForm)session.getAttribute("PersonYearTargetForm");
String searchYear = personYearTargetForm.getSearchYear();
if(!StringUtils.isNull(searchYear)){
 calendar.set(Calendar.YEAR, Integer.parseInt(searchYear));
}
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
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
//-->
</SCRIPT>
<html>
<body class="bodybg">
  <html:form action="/dailyoffice/worklog/listpersonyeartarget" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="8" align="center" class="TableTitleText">
	<logic:equal name="PersonYearTargetForm" property="viewFlag" value="1">
	<bean:write  name="PersonYearTargetForm" property="deptnodename" filter="true"/></logic:equal>个人年度汇报</td>
  </tr>
  <tr>
    <td colspan="8" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
           <td class="InputLabelCell">
		   <!--需要做一个通用的选择年和月的框就好-->
		 <!-- Year combo box -->
        <html:select property="searchYear" styleClass="input0">
        <SCRIPT LANGUAGE="JavaScript">
          for (var intLoop = today.year-50; intLoop < (today.year + 10); intLoop++)
          document.write("<OPTION VALUE= " + intLoop + " " +(today.year == intLoop ?"selected" : "") + ">" +  intLoop);
          </SCRIPT>
          </html:select>
		  年
		 <html:text styleClass="input2" property="searchUserName" size="10" maxlength="10"/>&nbsp;用户
          </td>
		  <td>
<% 
String 
temp1="doQuery('"+context+"/dailyoffice/worklog/listpersonyeartarget.do',true)";
%>
	  <goa:button operation="LNMB.VIEW" styleClass="button"  property="query" onclick="<%=temp1%>" value="查询"/>
          </td>
		  <logic:notEqual name="PersonYearTargetForm" property="deptnodeid" value="">
          <td><goa:button operation="LNMB.OPT" styleClass="button"  property="add" onclick="doAdd()" value="新增"/></td>
		  </logic:notEqual>
          <td>
<% 
String 
temp2="toUrl('"+context+"/dailyoffice/worklog/editpersonyeartarget.do?actionType=modify',true)";
%>
<% 
String 
temp3="toUrl('"+context+"/dailyoffice/worklog/viewpersonyeartarget.do',true)";
%>
	  <goa:button operation="LNMB.OPT" styleClass="button"  property="modify" onclick="<%=temp2%>" value="修改"/>
          </td>
          <td>
          <goa:button operation="LNMB.VIEW"  styleClass="button" property="view" value="查看"  onclick="<%=temp3%>"/>
          <td>
		  <goa:button operation="LNMB.OPT"  styleClass="button" property="delete" onclick="doDelete()"  value="删除"/>
          </td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr>
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr>
            <td valign="top" width="3%" class="Listtitle">
              <input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)">
            </td>
			<td class="Listtitle" nowrap width="50%" id=".current_year" onclick="onchangeOrderBy(this)"><div align="left">年度</div></td>
			<td class="Listtitle" nowrap width="50%" id=".user_code" onclick="onchangeOrderBy(this)"><div align="left">姓名</div></td>
		</tr>
    <logic:iterate id="ps" name="PersonYearTargetForm" property="pageControl.data" type="com.gever.goa.dailyoffice.worklog.vo.PersonTargetVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/worklog/viewpersonyeartarget.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="left" class="listcellrow">
		<logic:equal name="ps" property="edit_delete_flag" value = "1">
		<input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >
		</logic:equal>
		<logic:equal name="ps" property="edit_delete_flag" value = "0">
		<input type='checkbox' disabled>
		</logic:equal>
		</td>
		<td class="listcellrow">
		<a href="<%=context%>/dailyoffice/worklog/viewpersonyeartarget.do?fid=<bean:write name='ps' property='serial_num' filter='true'/>">
		<bean:write name="ps" property="current_year" filter="true"/>
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
	    <html:hidden name="PersonYearTargetForm" property="orderType"/>
        <html:hidden name="PersonYearTargetForm" property="orderFld"/>
		<goa:pagelink name="PersonYearTargetForm" property="pageControl" />
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
