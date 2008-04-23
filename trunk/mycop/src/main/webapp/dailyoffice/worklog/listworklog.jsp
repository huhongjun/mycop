<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
function checkInputAndSearch(){	
	var sForm = document.forms[0];	
	var bDate = sForm.elements["searchBeginTime"].value;
	var eDate = sForm.elements["searchEndTime"].value;			
	var bRet = true;	
	var strMsg = "";	
	if((bDate!="" && bDate!=null) && (eDate!="" && eDate!=null)){
	var lbDate = getDateNum(bDate,0);
	var leDate = getDateNum(eDate,0);
	if (lbDate > leDate){
		bRet = false;
		strMsg +=  ("请保证结束日期在开始日期之后!\n");
	} 
	}	
	if (!bRet){
		alert(strMsg);
	}else{
		doQuery('<%=context%>/dailyoffice/worklog/listworklog.do',true);
	}
	return bRet;
}
//-->
</SCRIPT>
<html>
<body class="bodybg">
  <html:form action="/dailyoffice/worklog/listworklog" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="TableTitleText">我的日志</td>
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
		   开始日期：
             <html:text styleClass="input2" property="searchBeginTime" readonly="true" size="10" maxlength="10" validatetype="date"  msg="日期不对,正确格式:'2000-01-02'"  onlytype="date" />
		   结束日期：
             <html:text styleClass="input2" property="searchEndTime" readonly="true" size="10" maxlength="10" validatetype="date"  msg="日期不对,正确格式:'2000-01-02'"  onlytype="date" />
          </td>
	      <td>
	  <goa:button styleClass="button"  property="query" onclick="checkInputAndSearch()" value="查询" operation="WDLZ.VIEW"/>
          </td> 
		  <logic:equal name="WorkLogForm" property="isInsertFlag" value="0">
          <td><goa:button operation="WDLZ.OPT" styleClass="button"  property="add" onclick="doAdd()" value="新增"/></td>
		  </logic:equal>
		  <logic:equal name="WorkLogForm" property="isInsertFlag" value="1">
          <!--<td>&nbsp;</td>-->
		  </logic:equal>                                
	  <td>
<% 
String 
temp="toUrl('"+context+"/dailyoffice/worklog/editworklog.do?actionType=modify',true)";
%>
	  <goa:button operation="WDLZ.OPT" styleClass="button"  property="modify" onclick="<%=temp%>" value="修改"/>
          </td>  
		  <!--
          <td>
          <html:button  styleClass="button" property="view" value="查看"  onclick="toUrl('/goa/dailyoffice/worklog/viewworklog.do',true)"/>
		  </td>
		  -->
		  <!--
          <td>
		  <html:button  styleClass="button" property="delete" onclick="doDelete()"  value="删除"/>
          </td>
		  -->
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
			<td class="Listtitle" nowrap width="30%"><div align="left">日期</div></td>
			<td class="Listtitle" nowrap width="30%"><div align="left">星期</div></td>
			<td class="Listtitle" nowrap width="30%"><div align="left">创建时间</div></td>		  
		</tr>
    <logic:iterate id="ps" name="WorkLogForm" property="pageControl.data" type="com.gever.goa.dailyoffice.worklog.vo.WorkLogVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/worklog/viewworklog.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="left" class="listcellrow">
		<logic:equal name="ps" property="isTodayWorkLog" value = "1">
		<input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >
		</logic:equal>
		<logic:equal name="ps" property="isTodayWorkLog" value = "0">
		<input type='checkbox' disabled> 
		</logic:equal>
		</td>		
		<td class="listcellrow"><bean:write name="ps" property="fill_date" filter="true"/></td>
		<td class="listcellrow"><bean:write name="ps" property="cur_week" filter="true"/></td>
		<td class="listcellrow4"><bean:write name="ps" property="create_time" filter="true"/></td>
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
	    <html:hidden name="WorkLogForm" property="orderType"/>
        <html:hidden name="WorkLogForm" property="orderFld"/>
		<goa:pagelink name="WorkLogForm" property="pageControl" /> 		
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
