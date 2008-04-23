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
        <td align="center"><span class="TableTitleText">公共信息浏览</span></td>
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
		<td align="left" class="InputLabelCell" nowrap>主&nbsp;&nbsp;&nbsp;&nbsp;题：</td>
		<td colspan="4" >
		<goa:write  name="IsInfoManageForm" property="vo.title" filter="true"/>
		</td>
		</tr>
	<tr class="listcellrow">
	  <td align="left" class="InputLabelCell" nowrap>类&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
	  <td><goa:write  name="IsInfoManageForm" property="vo.info_type" filter="true"/></td>
	  <td>&nbsp;</td>
	  <td align="right" class="InputLabelCell" nowrap>&nbsp;</td>
	  <td>&nbsp;</td>
	  </tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>创 建 人：</td>
		<td>
		<goa:write  name="IsInfoManageForm" property="vo.user_name" filter="true"/>
		</td>
		<td>&nbsp;</td>
		<td align="right" class="InputLabelCell" nowrap>创建日期：</td>
		<td>
		<goa:write  name="IsInfoManageForm" property="vo.create_date" filter="true"/>
		</td>
	</tr>
	<tr class="listcellrow">
	   <td align="left" class="InputLabelCell" nowrap>网&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
	   <td>
	   <a href="<goa:write name="IsInfoManageForm" property="vo.url" filter="true" />" target="_blank">
	   <goa:write name="IsInfoManageForm" property="vo.url" filter="true"/>
	   </td></a>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell" nowrap>附件下载：</td>
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
		  <goa:link style="cursor:hand" href="<%=strHref%>" onclick ="<%=downloadMethod%>" title="下载附件" ><%=bfilename%>
          </goa:link>&nbsp;
		</td>
	</tr>	
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
		<td colspan="4">
		<html:textarea styleClass="input2" property="vo.content" rows="15" cols="60%"  readonly="true"/>
		</td>
	</tr>
	<tr class="listcellrow">
		<td align="left" class="InputLabelCell">级&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
		<td colspan="4">
		<logic:equal name="IsInfoManageForm" property="vo.info_levle" value="0">
		公司级
		</logic:equal>
		<logic:equal name="IsInfoManageForm" property="vo.info_levle" value="1">
		个人级
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
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=clk1%>" operation="GGXX.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="GGXX.VIEW"/>
		</td>
    </tr>
</table>

</html:form>
</body>
</html>