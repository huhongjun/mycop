<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>
<html>
<body class="bodybg">
<html:form action="/dailyoffice/impowermgr/listimpower" method="post">
<html:hidden name="DoImpowerForm" property="orderType"/>
<html:hidden name="DoImpowerForm" property="orderFld"/>
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr> 
	<td colspan="6">&nbsp;</td>
	</tr>
	<tr> 
	<td colspan="6" align="center" class="TableTitleText">授权书</td>
	</tr>
	<tr> 
	<td colspan="6" align="right">&nbsp;</td>
	</tr>
	<tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
	<tr>
	<td>
	<goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增"  operation="SQGL.OPT"/>
	</td>
	<td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()"  operation="SQGL.OPT"/></td>
	<% String temp="toUrl('"+context+"/dailyoffice/impowermgr/viewimpower.do',true)";%>
	<td> <goa:button  styleClass="button" property="view" value="查看"  onclick="<%=temp%>"  operation="SQGL.VIEW"/> </td>
	<td>
	<% String temp1="toUrl('"+context+"/dailyoffice/impowermgr/editimpower.do?actionType=modify',true)";%>
	<goa:button styleClass="button"  property="copy" onclick="<%=temp1%>" value="修改"  operation="SQGL.OPT"/>
	</td>
	</tr>
	</table>
	</td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%"border="0" cellspacing="0" cellpadding="2">
        <tr height="20">
            <td width="32" class="Listtitle" align="center" ><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
			<td width="17%" class="Listtitle" id=".payer" onclick="onchangeOrderBy(this)"><div align="v">被授权人</div></td>
            <td width="40%" class="Listtitle" id=".begin_time" onclick="onchangeOrderBy(this)"><div align="left">开始时间</div></td>
            <td width="40%" class="Listtitle" id=".end_time" onclick="onchangeOrderBy(this)"><div align="left">结束时间</div></td>
    </tr>
    <logic:iterate id="ps" name="DoImpowerForm" property="pageControl.data" type="com.gever.goa.dailyoffice.impowermgr.vo.DoImpowerVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/impowermgr/viewimpower.do?fid=" +ps.getSerial_num()+"',false)"%>">
	<td align="center" class="listcellrow">
	<input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >    		  
	</td>
	<td class="listcellrow"><bean:write name="ps" property="payername" filter="true"/>&nbsp;</td>
	<td class="listcellrow"><bean:write name="ps" property="begin_time" filter="true"/>&nbsp;</td>
	<td class="listcellrow"><bean:write name="ps" property="end_time" filter="true"/>&nbsp;</td>
	</tr>
	</logic:iterate>
</table>
</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="DoImpowerForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
