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
		strMsg +=  ("�뱣֤���������ڿ�ʼ����֮��!\n");
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
    <td colspan="8" align="center" class="TableTitleText">�ҵ���־</td>
  </tr>
  <tr> 
    <td colspan="8" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		   <td class="InputLabelCell">
		   <!--��Ҫ��һ��ͨ�õ�ѡ������µĿ�ͺ�-->
		   ��ʼ���ڣ�
             <html:text styleClass="input2" property="searchBeginTime" readonly="true" size="10" maxlength="10" validatetype="date"  msg="���ڲ���,��ȷ��ʽ:'2000-01-02'"  onlytype="date" />
		   �������ڣ�
             <html:text styleClass="input2" property="searchEndTime" readonly="true" size="10" maxlength="10" validatetype="date"  msg="���ڲ���,��ȷ��ʽ:'2000-01-02'"  onlytype="date" />
          </td>
	      <td>
	  <goa:button styleClass="button"  property="query" onclick="checkInputAndSearch()" value="��ѯ" operation="WDLZ.VIEW"/>
          </td> 
		  <logic:equal name="WorkLogForm" property="isInsertFlag" value="0">
          <td><goa:button operation="WDLZ.OPT" styleClass="button"  property="add" onclick="doAdd()" value="����"/></td>
		  </logic:equal>
		  <logic:equal name="WorkLogForm" property="isInsertFlag" value="1">
          <!--<td>&nbsp;</td>-->
		  </logic:equal>                                
	  <td>
<% 
String 
temp="toUrl('"+context+"/dailyoffice/worklog/editworklog.do?actionType=modify',true)";
%>
	  <goa:button operation="WDLZ.OPT" styleClass="button"  property="modify" onclick="<%=temp%>" value="�޸�"/>
          </td>  
		  <!--
          <td>
          <html:button  styleClass="button" property="view" value="�鿴"  onclick="toUrl('/goa/dailyoffice/worklog/viewworklog.do',true)"/>
		  </td>
		  -->
		  <!--
          <td>
		  <html:button  styleClass="button" property="delete" onclick="doDelete()"  value="ɾ��"/>
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
              <input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)">
            </td>
			<td class="Listtitle" nowrap width="30%"><div align="left">����</div></td>
			<td class="Listtitle" nowrap width="30%"><div align="left">����</div></td>
			<td class="Listtitle" nowrap width="30%"><div align="left">����ʱ��</div></td>		  
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
