<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>
<html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function checkInputAndSearch(){	
	var sForm = document.forms[0];	
	var bDate = sForm.elements["searchBeginTime"].value;
	var eDate = sForm.elements["searchEndTime"].value;			
	var bRet = true;	
	var strMsg = "";	
	if (!isEmpty(bDate) && !isEmpty(eDate) && bDate > eDate){
		bRet = false;
		strMsg +=  ("�뱣֤���������ڿ�ʼ����֮��!\n");
	} 	
	if (!bRet){
		alert(strMsg);
	}else{
		toUrl('<%=context%>/dailyoffice/workreport/listworkreport.do',false);
	}
	return bRet;
}
//-->
</SCRIPT>
<body class="bodybg">
<html:form action="/dailyoffice/workreport/listworkreport" method="post">
<html:hidden name="DoWorkReportForm" property="orderType"/>
<html:hidden name="DoWorkReportForm" property="orderFld"/>
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr> 
	<td colspan="6">&nbsp;</td>
	</tr>
	<tr> 
	<td colspan="6" align="center" class="TableTitleText">�����㱨</td>
	</tr>
    <tr> 
    <td colspan="6" align="right">&nbsp;</td>
    </tr>
	<tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
	<tr  class="listcellrow">
	<td class="InputLabelCell">��ʼ���ڣ�<html:text styleClass="input2" property="searchBeginTime" readonly="true" size="10" maxlength="10" validatetype="date"  msg="���ڲ���,��ȷ��ʽ:'2000-01-02'"  onlytype="date"/></td>
	<td class="InputLabelCell">�������ڣ�<html:text styleClass="input2" property="searchEndTime" readonly="true" size="10" maxlength="10" validatetype="date"  msg="���ڲ���,��ȷ��ʽ:'2000-01-02'"  onlytype="date" /></td>
	<td><goa:button styleClass="button"  property="query" onclick="checkInputAndSearch()" value="��ѯ" operation="GZHB.VIEW"/></td>
	<td>
	<goa:button styleClass="button"  property="add" onclick="doAdd()" value="����" operation="GZHB.OPT"/>
	</td>
	<td>
	<logic:equal name="DoWorkReportForm" property="listType" value = "0">
	<goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doDelete()" operation="GZHB.OPT"/>
	</logic:equal>
	<logic:equal name="DoWorkReportForm" property="listType" value = "2">
	<goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doDelete()" operation="GZHB.OPT"/>
	</logic:equal>
	</td>

<% 
String 
temp1="toUrl('"+context+"/dailyoffice/workreport/viewworkreport.do',true)";
String 
temp2="toUrl('"+context+"/dailyoffice/workreport/editworkreport.do?actionType=modify',true)";

String 
temp3="toUrl('"+context+"/dailyoffice/workreport/editworkreport.do?actionType=modify',true)";

String 
temp4="toUrl('"+context+"/dailyoffice/workreport/viewworkreport.do',true)";
%>
	<td>
	<goa:button  styleClass="button" property="view" value="�鿴"  onclick="<%=temp1%>" operation="GZHB.VIEW"/>
	</td>
	<td>
	<logic:equal name="DoWorkReportForm" property="listType" value = "0">
	<goa:button styleClass="button"  property="copy" onclick="<%=temp2%>" value="�޸�" operation="GZHB.OPT"/>
	</logic:equal>	
	<logic:equal name="DoWorkReportForm" property="listType" value = "1">
	<goa:button styleClass="button"  property="copy" onclick="<%=temp3%>" value="�޸�" operation="GZHB.OPT"/>
	</logic:equal>	
	<logic:equal name="DoWorkReportForm" property="listType" value = "3">
	<goa:button styleClass="button"  property="copy" onclick="<%=temp4%>" value="����" operation="GZHB.OPT"/>
	</logic:equal>	
	</td>
	</tr>
	</table>
	</td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%"border="0" cellspacing="0" cellpadding="2">
        <tr height="20">
            <td width="32" class="Listtitle" align="center" ><input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
			<td width="15%" class="Listtitle" id=".create_date" onclick="onchangeOrderBy(this)"><div align="left">����</div></td>
      		<td width="15%" class="Listtitle"  id=".user_code" onclick="onchangeOrderBy(this)"><div align="v">������</div></td>
			<td width="15%" class="Listtitle" id=".render" onclick="onchangeOrderBy(this)"><div align="v">���㱨��</div></td>
            <td width="25%" class="Listtitle"  id=".begin_time" onclick="onchangeOrderBy(this)"><div align="left">��ʼʱ��</div></td>
            <td width="25%" class="Listtitle" id=".end_time" onclick="onchangeOrderBy(this)"><div align="left">����ʱ��</div></td>
    </tr>
    <logic:iterate id="ps" name="DoWorkReportForm" property="pageControl.data" type="com.gever.goa.dailyoffice.workreport.vo.DoWorkReportVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/workreport/viewworkreport.do?fid=" +ps.getSerial_num()+"',false)"%>">
	<td align="center" class="listcellrow">
	<input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >    		  
	</td>
	<td class="listcellrow"><bean:write name="ps" property="create_date" filter="true"/>&nbsp;</td>
	<td class="listcellrow"><bean:write name="ps" property="user_name" filter="true"/>&nbsp;</td>
	<td class="listcellrow"><bean:write name="ps" property="render_name" filter="true"/>&nbsp;</td>
	<td class="listcellrow"><bean:write name="ps" property="begin_time" filter="true"/>&nbsp;</td>
	<td class="listcellrow"><bean:write name="ps" property="end_time" filter="true"/>&nbsp;</td>
	</tr>
	</logic:iterate>
</table>
</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="DoWorkReportForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
