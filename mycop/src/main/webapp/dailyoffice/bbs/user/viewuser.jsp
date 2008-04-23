<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<body class="bodybg">
<html:form action="/dailyoffice/bbs/mng/viewbbsuser" method="post">
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
        <td>&nbsp;<html:hidden name="UserForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0" class="tablebk">
				<tr> 
				  <td bgcolor="#FFFFFF">
				  <table width="400" height="36" border="0" cellpadding="2" cellspacing="1">
                  <tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">NICKNAME</td>
					    <td width="383" height="23"><goa:write  name="UserForm" property="vo.NICKNAME" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">USER_ICON</td>
					    <td width="383" height="23"><goa:write   name="UserForm" property="vo.USER_ICON" filter="true"/>
						</td>
					</tr>

					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">SEX_CODE</td>
					    <td width="383" height="23"><goa:write  name="UserForm" property="vo.SEX_CODE" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">E_MAIL</td>
					    <td width="383" height="23"><goa:write  name="UserForm" property="vo.E_MAIL" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">HOME_PAGE</td>
					    <td width="383" height="23"><goa:write  name="UserForm" property="vo.HOME_PAGE" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">USER_CODE</td>
					    <td width="383" height="23"><goa:write  name="UserForm" property="vo.USER_CODE" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">USER_STATE</td>
					    <td width="383" height="23"><goa:write  name="UserForm" property="vo.USER_STATE" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">LAST_LOG_TIME</td>
					    <td width="383" height="23"><goa:write  name="UserForm" property="vo.LAST_LOG_TIME" filter="true"/></td>
					</tr>
					<tr class="listcellrow4">
					    <td width="86" height="23" nowrap class="InputLabelCell">LAST_LOG_TIME</td>
					    <td width="383" height="23"><goa:write  name="UserForm" property="vo.LAST_LOG_TIME" filter="true"/></td>
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
		<html:hidden name="UserForm" property="fid" />
<% 
String temp=" toUrl('"+context+"/dailyoffice/bbs/mng/editbbsuser.do?actionType=modify',false)";
%>
		<html:button property="save" value="修改" styleClass="button"  onclick="<%=temp%>"/>
        <html:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

