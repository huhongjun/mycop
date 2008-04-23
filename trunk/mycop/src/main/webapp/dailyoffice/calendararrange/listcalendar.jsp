<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>

<html>
<body class="bodybg">
 <html:form action="/dailyoffice/calendararrange/listcalendar" method="post">
<html:hidden name="CalendarArrangeForm" property="orderType"/>
<html:hidden name="CalendarArrangeForm" property="orderFld"/>
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText"><bean:write name="CalendarArrangeForm" property="calendarUserVO.userName"/>日程安排</td>
  </tr>
  <tr>
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		<logic:equal name="CalendarArrangeForm" property="calendarUserVO.rightType" value="1">
		<td>
		  <goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增"  operation="LTAP.OPT"/>
		</td>
		<td>
		 <% String temp="toUrl('"+context+"/dailyoffice/calendararrange/editcalendar.do?actionType=modify',true)";%>
		<goa:button styleClass="button"  property="copy" onclick="<%=temp%>" value="修改"  operation="LTAP.OPT"/>
		</td>
		<td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()"  operation="LTAP.OPT"/> </td>
		</logic:equal>
		<td>
		<% String temp1="toUrl('"+context+"/dailyoffice/calendararrange/viewcalendar.do',true)";%>
		<goa:button styleClass="button"  property="view" onclick="<%=temp1%>" value="查看"  operation="LTAP.VIEW"/>
		</td>
		<logic:equal name="CalendarArrangeForm" property="listType" value="toListMonth">
		<td><goa:button styleClass="button"  property="goback"  onclick="doAction('goUrl(monthindex)')" value="返回"  operation="LTAP.VIEW"/> </td>   
		</logic:equal>    
		<logic:notEqual name="CalendarArrangeForm" property="listType" value="toListMonth">
		<td><goa:button styleClass="button"  property="goback"  onclick="doAction('goUrl(weekindex)')" value="返回"  operation="LTAP.VIEW"/> </td>  
		</logic:notEqual> 
		</tr>
      </table>
	  </td>
  </tr>
  <tr>
    <td colspan="6">
	<table width="100%"border="0" cellspacing="0" cellpadding="2">
        <tr height="20">
            <td width="2" class="Listtitle" align="center" ><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
      		<td width="10%" class="Listtitle" id=".calendar" onclick="onchangeOrderBy(this)"><div align="left">日期</div></td>
      		<td width="10%" class="Listtitle" id=".arrange" onclick="onchangeOrderBy(this)"><div align="v">创建人</div></td>
            <td width="10%" class="Listtitle" id=".task_type" onclick="onchangeOrderBy(this)"><div align="v">事务类型</div></td>
            <td width="40%"  class="Listtitle" id=".task_sum" onclick="onchangeOrderBy(this)"><div align="v">事务概要</div></td>
			<td width="15%" class="Listtitle" id=".begin_time" onclick="onchangeOrderBy(this)"><div align="left">开始时间</div></td>
			<td width="15%" class="Listtitle" id=".end_time" onclick="onchangeOrderBy(this)"><div align="left">结束时间</div></td>
    </tr>
    <logic:iterate id="ps" name="CalendarArrangeForm" property="pageControl.data" type="com.gever.goa.dailyoffice.calendararrange.vo.CalendarArrangeVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/calendararrange/viewcalendar.do?fid=" +ps.getSerial_num()+ "',false)"%>">
		<td class="listcellrow">
		<input type='checkbox' name='fid' value='<goa:write name="ps" property="serial_num" filter="true"/>'>
		</td>
		<td class="listcellrow"><goa:write name="ps" property="calendar" filter="true"/>&nbsp;</td>
		<td class="listcellrow"><goa:write name="ps" property="arrangename" filter="true"/></td>
		<td class="listcellrow">
		<logic:equal name="ps" property="task_type" value = "0">
		工作事务
		</logic:equal>
		<logic:equal name="ps" property="task_type" value = "1">
		个人事务
		</logic:equal>
		&nbsp;</td>
		<td class="listcellrow" style="word-break:break-all"> <goa:write name="ps" property="task_sum" filter="true"/>&nbsp; </td>
		<td class="listcellrow"><goa:write name="ps" property="begin_time" filter="true"/>&nbsp;</td>
		<td class="listcellrow"><goa:write name="ps" property="end_time" filter="true"/>&nbsp;</td>
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr>
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="CalendarArrangeForm" property="pageControl" />
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
