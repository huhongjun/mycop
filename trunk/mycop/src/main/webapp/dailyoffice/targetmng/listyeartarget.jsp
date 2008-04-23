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

<html>
<body class="bodybg">
  <html:form action="/dailyoffice/targetmng/listyeartarget" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="TableTitleText">年度目标</td>
  </tr>
  <tr> 
    <td colspan="8" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
           <td class="t12">
		   <!--需要做一个通用的选择年和月的框就好-->
                <html:select property="searchYear">
			<SCRIPT LANGUAGE="JavaScript">
          for (var intLoop = today.year-50; intLoop < (today.year + 10); intLoop++)
          document.write("<OPTION VALUE= " + intLoop + " " +(today.year == intLoop ?"selected" : "") + ">" +  intLoop);
          </SCRIPT>
          </html:select>
		  <span class="InputLabelCell">年</span>
          </td>
		  <td>
		   <% String temp="doQuery('"+context+"/dailyoffice/targetmng/listyeartarget.do',true)";%>
	  <goa:button operation="NDMB.VIEW" styleClass="button"  property="query" onclick="<%=temp%>" value="查询"/>
          </td> 
          <td><goa:button operation="NDMB.OPT" styleClass="button"  property="add" onclick="doAdd()" value="新增"/></td>          
          <td>
          <% String temp1="toUrl('"+context+"/dailyoffice/targetmng/edityeartarget.do?actionType=modify&flag=modify',true)";%>
	  <goa:button operation="NDMB.OPT" styleClass="button"  property="modify" onclick="<%=temp1%>"  value="修改"/>
          </td> 
		  <!--
          <td>
          <goa:button operation="NDMB.VIEW"  styleClass="button" property="view" value="查看"  onclick="toUrl('/goa/dailyoffice/targetmng/viewyeartarget.do',true)"/>
		  </td>
		  -->
          <td> 
		  <goa:button operation="NDMB.OPT"  styleClass="button" property="delete" onclick="doDelete()"  value="删除"/>
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
			<td class="Listtitle" nowrap width="30%" id=".dept_code" onclick="onchangeOrderBy(this)"><div align="left">部门</div></td>
			<td class="Listtitle" nowrap width="30%" id=".current_year" onclick="onchangeOrderBy(this)"><div align="left">年度</div></td>	
			<td class="Listtitle" nowrap width="30%"><div align="left">审核审批状况</div></td>
		</tr>
    <logic:iterate id="ps" name="YearTargetForm" property="pageControl.data" type="com.gever.goa.dailyoffice.targetmng.vo.TargetVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/targetmng/viewyeartarget.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="left" class="listcellrow">
		<logic:equal name="ps" property="edit_delete_flag" value = "1">
		<input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >	  
		</logic:equal>
		<logic:equal name="ps" property="edit_delete_flag" value = "0">
        <input type='checkbox' disabled> 
		</logic:equal>
		</td>		
		<td class="listcellrow"><bean:write name="ps" property="dept_name" filter="true"/>&nbsp;</td>
		<td class="listcellrow"><% String link_url = ""+context+"/dailyoffice/targetmng/viewyeartarget.do?fid=" +ps.getSerial_num(); %>
		<goa:link href="<%=link_url%>"><bean:write name="ps" property="current_year" filter="true"/></goa:link>&nbsp;</td>
		<td class="listcellrow4"><bean:write name="ps" property="audit_check_name" filter="true"/>&nbsp;</td>
		
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
        <html:hidden name="YearTargetForm" property="orderType"/>
        <html:hidden name="YearTargetForm" property="orderFld"/>
		<goa:pagelink name="YearTargetForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
