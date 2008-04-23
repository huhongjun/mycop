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
  <html:form action="/dailyoffice/targetmng/listfiveyearlayout" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="TableTitleText">五年规划</td>
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
		  <% String temp="doQuery('"+context+"/dailyoffice/targetmng/listfiveyearlayout.do',true)";%>
	  <goa:button operation="WRJF.VIEW" styleClass="button"  property="query" onclick="<%=temp%>" value="查询"/>
          </td> 
          <td><goa:button operation="WRJF.OPT" styleClass="button"  property="add" onclick="doAdd()" value="新增"/></td>          
          <td>
          <% String temp1="toUrl('"+context+"/dailyoffice/targetmng/editfiveyearlayout.do?actionType=modify&flag=modify',true)";%>
	  <goa:button operation="WRJF.OPT" styleClass="button"  property="modify" onclick="<%=temp1%>" value="修改"/>
          </td> 
		  
          <td> 
		  <goa:button operation="WRJF.OPT"  styleClass="button" property="delete" onclick="doDelete()"  value="删除"/>
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
			<td class="Listtitle" nowrap width="30%" id=".create_date" onclick="onchangeOrderBy(this)"><div align="left">编制日期</div></td>
			<td class="Listtitle" nowrap width="20%" id=".user_code" onclick="onchangeOrderBy(this)"><div align="left">编制人</div></td>	
			<td class="Listtitle" nowrap width="20%" id=".approve" onclick="onchangeOrderBy(this)"><div align="left">审批人</div></td>
			<td class="Listtitle" nowrap width="20%"><div align="left">审批状态</div></td>
		</tr>
    <logic:iterate id="ps" name="FiveYearLayoutForm" property="pageControl.data" type="com.gever.goa.dailyoffice.targetmng.vo.FiveYearLayoutVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/targetmng/viewfiveyearlayout.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="left" class="listcellrow"><logic:equal name="ps" property="edit_delete_flag" value = "1"><input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' ></logic:equal><logic:equal name="ps" property="edit_delete_flag" value = "0">
        <input type='checkbox' disabled></logic:equal>&nbsp</td>			
		<td class="listcellrow"><bean:write name="ps" property="create_date" filter="true"/>&nbsp;</td>
		<td class="listcellrow"><bean:write name="ps" property="user_name" filter="true"/>&nbsp;</td>
		<td class="listcellrow"><bean:write name="ps" property="approver_name" filter="true"/>&nbsp;</td>
		<td class="listcellrow4"><bean:write name="ps" property="approve_name" filter="true"/>&nbsp;</td>		
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
        <html:hidden name="FiveYearLayoutForm" property="orderType"/>
        <html:hidden name="FiveYearLayoutForm" property="orderFld"/>
		<goa:pagelink name="FiveYearLayoutForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
