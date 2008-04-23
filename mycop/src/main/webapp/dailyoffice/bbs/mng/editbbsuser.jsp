<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>

<body class="bodybg">
<html:form action="/dailyoffice/bbs/mng/editbbsuser" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">用户信息</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="BBSUserForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr> 
				  <td>
				  <table width="300" height="36" border="0" cellpadding="2" cellspacing="1">
                  <tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">昵&nbsp;&nbsp;&nbsp;&nbsp;称：</td>
					    <td width="383" class='f12' height="23"><goa:write name="BBSUserForm" property="vo.nickname" /></td>
					</tr>
			       <td>&nbsp;</td>
					<tr class="listcellrow">
					     
                    <td width="86"  nowrap class="InputLabelCell">用户状态：</td>
					     <td width="383" >
						 <html:select property="vo.user_state"  styleClass="f12">
						 <html:option value='0'>允许发言</html:option>
						 <html:option value='1'>禁止发言</html:option>
						 </html:select>
						 </td>
					</tr>
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
		
		<goa:button property="save" value="保存" styleClass="button"  onclick="return doSaveExit(BBSUserForm)" operation="LYWF.OPT"/>
          <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="LYWF.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>