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
  <html:form action="/dailyoffice/bbs/sboard" method="post">
	<table width="100%" border="0" cellpadding="4" cellspacing="0"  align="center">
        <tr><td width="40%">&nbsp;</td>
		<td width="20%">&nbsp;</td>
		<td width="20%">&nbsp;</td>
		<td width="20%">&nbsp;</td>
		</tr>
    <logic:iterate id="ps" name="SBoardForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.SBoardVO" indexId="index">
	<tr bgcolor="#FFFFCC" 
	onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
		
		<td colspan="4" class="listcellrow2">&nbsp;话题：<a href="javaScript:<%="toUrl('listTopic.do?sboardID=" +ps.getSerial_num()+"',false)"%>">&nbsp;&nbsp;<goa:write name="ps" property="sboard_name" filter="true" maxlen="70"/></a></td>
	</tr>
	<tr bgcolor="#FFFFFF" >
		
		<td width="30%" class="listcellrow2" colspan=4>
		  <table width="100%" border="0" cellpadding="4" cellspacing="0"  align="center">
		  <tr>
		  	<td class="listcellrow2">&nbsp;简介：</td>
		  </tr>
		  <tr>
		  	<TD class="listcellrow2">&nbsp;&nbsp;&nbsp;&nbsp;<goa:write name="ps" property="sboard_note" filter="true"/>&nbsp;</TD>
		  </tr>
		  </table>
		  </td>
		
	</tr>
	
	</logic:iterate>
</table>
</html:form>

 <div align="center"><logic:equal  name="SBoardForm" property="pageControl.data" value="[]">
对不起用户;论坛正在建设中,请见谅!
	   </logic:equal>
   </div>
</body>
</html>
