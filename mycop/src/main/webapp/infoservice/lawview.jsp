<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%@ page import="com.gever.goa.infoservice.action.IsInfoManageForm"%>
<%String context =request.getContextPath();%>

<html>
<body class="bodybg">
<html:form action="/infoservice/lawview" method="post" enctype="multipart/form-data">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">���߷���༭</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsInfoManageForm2" property="actionType"/></td>
    </tr>
</table>

<table  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="95%" align="center">
	<tr class="listcellrow">
		<td width="15%">&nbsp;</td>		
		<td width="30%">&nbsp;</td>
		<td width="5%">&nbsp;</td>
		<td width="15%">&nbsp;</td>
		<td width="35%">&nbsp;</td>
	</tr>
    <html:hidden property="vo.info_flag" value="1"/>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
		<td>
		<goa:write maxlen="0"  name="IsInfoManageForm2" property="vo.title" filter="true"/>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
		<td>
		<goa:write maxlen="0"  name="IsInfoManageForm2" property="vo.info_type" filter="true"/>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>�� �� �ˣ�</td>
		<td>
		<goa:write maxlen="0"  name="IsInfoManageForm2" property="vo.user_name" filter="true"/>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell" nowrap>�������ڣ�</td>
		<td>
		<goa:write maxlen="0"  name="IsInfoManageForm2" property="vo.create_date" filter="true"/>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>�������أ�</td>
		<td colspan="4">
		<% 
		 IsInfoManageForm dForm = (IsInfoManageForm)session.getAttribute("IsInfoManageForm2");
		 String bfilepath=dForm.getVo().getValue("file_path"); 
		 String bfilename=dForm.getVo().getValue("file_name");
		 String strUrl = "";	
               String strHref = context+"/util/downloadfile.jsp?filepath=" 
                   + bfilepath + "&filename=" + bfilename;
   
                 String downloadMethod = "download2('" + bfilepath + "','" + bfilename +"')";
		
		 %>
		  <goa:link style="cursor:hand" href="<%=strHref%>" onclick ="<%=downloadMethod%>" title="���ظ���" ><%=bfilename%>
          </goa:link>&nbsp;
		</td>
	</tr>	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;�ݣ�</td>
		<td colspan="4">
		<html:textarea styleClass="input2" property="vo.content" rows="15" cols="60%"  readonly="true"/>
		</td>
	</tr>
	<tr>
		<td colspan="5">&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>

<table width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="center"> 
		<html:hidden name="IsInfoManageForm2" property="fid" />
		<%String clk="toUrl('"+context+"/infoservice/law.do?actionType=modify',false)";%>
		<goa:button property="save" value="�޸�" styleClass="button"  onclick="<%=clk%>" operation="ZCFG.OPT"/>
        <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="ZCFG.VIEW"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>