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
<html:form action="/infoservice/publicview" method="post" enctype="multipart/form-data">
<span class="TableTitleText"><br></span> 

<table width="95%" border="0" cellspacing="0" cellpadding="0">
    <tr> 
        <td align="center"><span class="TableTitleText">������Ϣ���</span></td>
    </tr>
    <tr> 
        <td>&nbsp;<html:hidden name="IsInfoManageForm" property="actionType"/></td>
    </tr>
</table>

<table  align="center" width="85%" cellspacing="0" cellpadding="0">
<tr>
<td >
<table width="80%" align="center">
	<tr class="listcellrow">
		<td width="15%">&nbsp;</td>		
		<td width="20%">&nbsp;</td>
		<td width="5%">&nbsp;</td>
		<td width="15%">&nbsp;</td>
		<td width="30%">&nbsp;</td>
	</tr>
    <html:hidden property="vo.info_flag" value="0"/>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>��&nbsp;&nbsp;&nbsp;&nbsp;�⣺</td>
		<td colspan="4" >
		<goa:write  name="IsInfoManageForm" property="vo.title" filter="true"/>
		</td>
		</tr>
	<tr class="listcellrow">
	  <td align="left" class="InputLabelCell" nowrap>��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
	  <td><goa:write  name="IsInfoManageForm" property="vo.info_type" filter="true"/></td>
	  <td>&nbsp;</td>
	  <td align="right" class="InputLabelCell" nowrap>&nbsp;</td>
	  <td>&nbsp;</td>
	  </tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>�� �� �ˣ�</td>
		<td>
		<goa:write  name="IsInfoManageForm" property="vo.user_name" filter="true"/>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell" nowrap>�������ڣ�</td>
		<td>
		<goa:write  name="IsInfoManageForm" property="vo.create_date" filter="true"/>
		</td>
	</tr>
	<tr class="listcellrow">
	   <td align="left" class="InputLabelCell" nowrap>��&nbsp;&nbsp;&nbsp;&nbsp;ַ��</td>
	   <td>
	   <a href="<goa:write name="IsInfoManageForm" property="vo.url" filter="true" />" target="_blank">
	   <goa:write name="IsInfoManageForm" property="vo.url" filter="true"/>
	   </td></a>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>�������أ�</td>
		<td colspan="4">
	<% 
		 IsInfoManageForm dForm = (IsInfoManageForm)session.getAttribute("IsInfoManageForm");
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
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;��</td>
		<td colspan="4">
		<logic:equal name="IsInfoManageForm" property="vo.info_levle" value="0">
		��˾��
		</logic:equal>
		<logic:equal name="IsInfoManageForm" property="vo.info_levle" value="1">
		���˼�
		</logic:equal>
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
		<html:hidden name="IsInfoManageForm" property="fid" />
		<%String clk1="toUrl('"+context+"/infoservice/publicinfo.do?actionType=modify',false)";%>
		<goa:button property="save" value="�޸�" styleClass="button"  onclick="<%=clk1%>" operation="GGXX.OPT"/>
        <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="GGXX.VIEW"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>