<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<body class="bodybg">
<html:form action="/dailyoffice/message/viewmessage" method="post">
<script language=javascript>
function closeWin(){
    window.opener.location.reload();
   window.opener.location="/adminbsnmng/message/messageindex.jsp"
    window.close();
}
function doReply(){
	toUrl("<%=context%>/dailyoffice/message/editmessage.do?action=doReply&id_replyto=<bean:write name="MessageForm" property="vo.user_code" filter="true" />&name_replyto=<bean:write name="MessageForm" property="vo.user_name" filter="true" />",false);
}
</script>
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">����Ϣ</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="MessageForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			
				
				  <table width="400" height="36" border="0" align="center"  cellpadding="2" cellspacing="1">
                  			<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">��&nbsp;��&nbsp;�ˣ�</td>
					    <td width="383" height="23"><bean:write  name="MessageForm" property="vo.user_name" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">����ʱ�䣺</td>
					    <td width="383" height="23"><bean:write   name="MessageForm" property="vo.send_time" filter="true"/>
						</td>
					</tr>
					
					<tr class="listcellrow">						     
                    			<td width="86" height="23" nowrap class="InputLabelCell">��Ϣ���ݣ�</td>
					    <td><textarea name="textarea" rows="7" class="input2" style="width:300px;" readonly="true"><bean:write name="MessageForm" property="vo.content" filter="true" /></textarea>
					</tr>
						     
				  </table>
		  			</td>
				</tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		<html:hidden name="MessageForm" property="fid" />
		<goa:button property="save" value="�ظ�" styleClass="button"  onclick="doReply()" operation="RBDX.OPT"/>
        	<input type='button' class='button' value='����' onclick="doAction('goUrl(index)')"/>        	
      </tr>
    </table>
    </center>
</div>
</html:form>
</body>

