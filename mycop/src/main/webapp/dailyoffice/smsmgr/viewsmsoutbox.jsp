<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<html:form action="/dailyoffice/smsmgr/viewsmsoutbox" method="post">
<div align="center">
  <center>
   <br>
	<table  align="center" width="600"  border="0" cellspacing="0" cellpadding="0">
	<tr> 
      <td align="center" colspan="2"><span class="TableTitleText">阅读手机短信</span></td>
      </tr>
	<tr>
	<td align="center">
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
				
			  <tr class="listcellrow" height="23" >
					<td width="86" nowrap class="InputLabelCell">接收人：</td>
					<td  class="f12"><goa:write  name="SmsMgrForm" property="vo.username" filter="true"/></td>
			</tr>
		<tr height="23" >
				<td width="86" height="23" nowrap class="InputLabelCell">手机号码：</td>
				<td   class="f12"><goa:write   name="SmsMgrForm" property="vo.mbno" filter="true"/>
				</td>
			</tr>

		
	 <tr  height="23" >
				<td width="86" height="23" nowrap class="InputLabelCell">发送时间：</td>
				<td   class="f12"> <goa:write name='SmsMgrForm' property='vo.senddate' filter='true' />&nbsp;<goa:write name='SmsMgrForm' property='vo.sendtime' filter='true' /> </td>
			</tr>
	 <tr height="23" >
				<td width="86" height="23" nowrap class="InputLabelCell">状态：</td>
				<td   class="f12"> <logic:equal name="SmsMgrForm" property="vo.type" value="1"> 
		  已发送 </logic:equal> <logic:equal name="SmsMgrForm" property="vo.type" value="2"> 
		  正在发送 </logic:equal> <logic:equal name="SmsMgrForm" property="vo.type" value="3"> 
		  发送失败 </logic:equal> <logic:equal name="SmsMgrForm" property="vo.type" value="4"> 
		  定时发送 </logic:equal> </td>
			</tr>
	
	 <tr height="23" >
				 
			<td width="86"  height="23"  nowrap class="InputLabelCell">消息内容：</td>
				 <td class="f12" ><goa:write  name="SmsMgrForm" property="vo.msg" filter="false"/></td>
			</tr>
				  </table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		<html:hidden name="SmsMgrForm" property="fid" />
	
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="SJDX.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

