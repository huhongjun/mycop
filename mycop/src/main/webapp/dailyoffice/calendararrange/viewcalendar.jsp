<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>


<html:form action="/dailyoffice/calendararrange/viewcalendar" method="post">
<html:hidden name="CalendarArrangeForm" property="actionType"/>
<html:hidden name="CalendarArrangeForm" property="vo.user_code"/>
<html:hidden name="CalendarArrangeForm" property="vo.serial_num"/>
<html:hidden name="CalendarArrangeForm" property="listDate"/>
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
	<table  align="center" width="600"  border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center" colspan="2"><span class="TableTitleText"><bean:write name="CalendarArrangeForm" property="vo.user_name"/>日程安排</span></td>
      </tr>
      <tr> 
	<td align="center">
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="3">
		<tr height="23"> 
			<td></td>
		  </tr>
		<tr class="listcellrow" height="23" >  
		<td width="86" height="23" nowrap class="InputLabelCell">日　　期：</td>
		<td><goa:write  name="CalendarArrangeForm" property="vo.calendar" filter="true"/></td>
		<td width="86" class="InputLabelCell" >安 排 人：</td>
		<td ><goa:write  name="CalendarArrangeForm" property="vo.arrangename" filter="true"/>
		</tr>
        <tr class="listcellrow" height="23" >
        <td width="86" nowrap class="InputLabelCell">事务类型：</td>
        <td>
		<logic:equal name="CalendarArrangeForm" property="vo.task_type" value = "0">工作事务</logic:equal>
		<logic:equal name="CalendarArrangeForm" property="vo.task_type" value = "1">个人事务</logic:equal>
		</td>
		<td width="20%" nowrap class="InputLabelCell" >日程主人：</td>
		  <td>
		  <bean:write name="CalendarArrangeForm" property="vo.user_name" filter="true"/>
		  </td>
         </tr>
         <tr class="listcellrow" height="23" >
		<td  width="86" height="23" nowrap class="InputLabelCell">开始时间：</td>
		<td ><goa:write   name="CalendarArrangeForm" property="vo.begin_time" filter="true"/>
		<td  width="86" class="InputLabelCell" >结束时间：</td>
		<td ><goa:write   name="CalendarArrangeForm" property="vo.end_time" filter="true"/>
		</td>
		</tr>
         <tr class="listcellrow" height="23" >
                    <td width="86" nowrap class="InputLabelCell">事务概要：</td>
                    <td colspan="3"  style="word-break:break-all"><goa:write  name="CalendarArrangeForm" property="vo.task_sum" filter="true"/></td>
                  </tr>
        <tr class="listcellrow" height="23" > 
                    <td width="86"  nowrap class="InputLabelCell">事务详细：</td>
                    <td colspan="3"  style="word-break:break-all"><goa:write   name="CalendarArrangeForm" property="vo.task_content" filter="true"/></td>
                  </tr>
        <tr class="listcellrow" height="23" >
                    <td width="86" nowrap class="InputLabelCell">提　　醒：</td>
                    <td >
					<logic:equal name="CalendarArrangeForm" property="vo.remind_flag" value = "0">
					不需要
					</logic:equal>
					<logic:equal name="CalendarArrangeForm" property="vo.remind_flag" value = "1">
					需要
					</logic:equal>
					<td class="InputLabelCell"></td>
                    <td ></td>
				  </tr>
					<logic:equal name="CalendarArrangeForm" property="vo.remind_flag" value = "1">
				  <tr id='awokeTimeTD' class="listcellrow" height="23"> 
                    <td  width="86" nowrap class="InputLabelCell">提醒时间：</td>
                    <td ><goa:write   name="CalendarArrangeForm" property="vo.awoke_time" filter="true"/> 分钟</td>
					<td  class="InputLabelCell"></td>
                    <td ></td>
				  </tr>
					</logic:equal>
          </table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		<html:hidden name="CalendarArrangeForm" property="fid" />
		<logic:equal name="CalendarArrangeForm" property="calendarUserVO.rightType" value="1">
		<% String temp="toUrl('"+context+"/dailyoffice/calendararrange/editcalendar.do?actionType=modify',false)";%>
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=temp%>" operation="LTAP.OPT"/>
        </logic:equal>
		<goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="LTAP.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>
</html:form>


