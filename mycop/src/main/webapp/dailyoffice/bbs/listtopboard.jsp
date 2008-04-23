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
  <html:form action="/dailyoffice/bbs/topboard" method="post">
    <table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
	<TR>
    	<TD>
		
		
		</TD>
    </TR>
	<logic:iterate id="ps" name="TopBoardForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.TopBoardVO" indexId="index">
    <TR>
    	<TD>

		<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        
    
	<tr bgcolor="#9B9B9B" onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
	<td width="30%" class="listcellrow2">À¸Ä¿Ãû³Æ111:<a href="javaScript:<%="toUrl('sboard.do?topid=" +ps.getSerial_num()+"',false)"%>">&nbsp;<bean:write name="ps" property="tboard_name" filter="true"/>&nbsp;</a></td></tr>
	<tr>
	<td class="listcellrow2">¼ò½é£º<bean:write name="ps" property="tboard_note" filter="true"/>&nbsp;</td>
	</tr>
	<tr><td>&nbsp;</td>
	</tr>

	
</table>
		</TD>
    </TR>


    </logic:iterate>
	</TABLE>
	

</html:form>
</body>
</html>
