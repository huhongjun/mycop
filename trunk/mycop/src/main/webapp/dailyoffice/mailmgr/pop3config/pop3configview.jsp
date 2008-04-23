<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>

<%String context =request.getContextPath();%>
<body class="bodybg">
<html:form action="/dailyoffice/mailmgr/pop3config/pop3configview" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">邮件帐号设置</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="Pop3ConfigForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
				  <fieldset style="width:527 ">
				  <legend class="f12">接收端信息(pop3)</legend>
				  <table  width="501" border="0" cellpadding="2" cellspacing="1">
				  <tr class="listcellrow">
					    <td height="23"  width="119" class="InputLabelCell">
							帐号名称：  
						</td>
					    <td height="23" align="left">
							<goa:write name="Pop3ConfigForm" name="Pop3ConfigForm" name="Pop3ConfigForm" property="vo.pop3_name" filter="true"/>
						</td>
				 </tr>
                  <tr class="listcellrow">
					    <td height="23" width="119"  class="InputLabelCell">
							服务器地址、端口：
						</td>
					    <td height="23">
							<goa:write name="Pop3ConfigForm" name="Pop3ConfigForm" property="vo.pop3_address" filter="true"/>
						</td>
						<td >
							<goa:write name="Pop3ConfigForm" name="Pop3ConfigForm" property="vo.pop3_port" filter="true"/>
						</td>
					</tr>
					<tr class="listcellrow">
					    <td height="23" width="119" class="InputLabelCell">
							登录账号：
						</td>
					    <td height="23">
							<goa:write name="Pop3ConfigForm" name="Pop3ConfigForm" property="vo.pop3_account" filter="true"/>
						</td>
					</tr>

					<tr class="listcellrow">
					    <td height="23" width="119"  class="InputLabelCell">
							登录密码：
						</td>
					    <td height="23">
							<goa:write name="Pop3ConfigForm" name="Pop3ConfigForm" property="vo.pop3_pwd"/>
						</td>
					</tr>
					<tr class="listcellrow">
					    <td height="23" width="119" class="InputLabelCell">
							删除远程邮件：
						</td>
					    <td height="23">
							<goa:write name="Pop3ConfigForm" property="vo.del_flag"/>
						</td>				
					</tr>
					<tr class="listcellrow">
					    <td  width="119" class="InputLabelCell">
						接收邮件夹：
						</td>
					    <td >
							<goa:write name="Pop3ConfigForm"  property="vo.incept_mail_dir"/>
						</td>
					</tr>
				  </table>
		  </fieldset>
					</td>
					</tr>
					<tr>
					<td align="center">
					<fieldset style="width:527 ">
					<legend class="f12">发送端信息(smtp)</legend>
					<table width="501" >
					<tr class="listcellrow">
					    <td width="119" height="23" class="InputLabelCell">
						服务器地址、端口：</td>
					    <td height="23" width="150">
							<goa:write name="Pop3ConfigForm" property="vo.smtp_server"/>
						</td>
						<td >
						<goa:write name="Pop3ConfigForm" property="vo.smtp_port"/>
						</td>
					</tr>

					<tr class="listcellrow">
						<td width="119" height="23" class="InputLabelCell">
							显示姓名：
						</td>
						<td width="150" height="23">
							<goa:write name="Pop3ConfigForm" property="vo.show_name"/>
						</td>
					</tr>

					<tr class="listcellrow">
						<td width="119" height="23"  class="InputLabelCell">
							发信地址：
						</td>
						<td width="150" height="23">
							<goa:write name="Pop3ConfigForm" property="vo.show_address"/>
					  </td>
					</tr>
					<tr class="listcellrow">
						<td width="119" height="23" class="InputLabelCell">
							账号状态：
						</td>
						<td width="150" height="23">
							 <goa:write name="Pop3ConfigForm" property="vo.use_flag"/>
					  </td>	 
					</tr>
					<tr class="listcellrow">
						<td width="119" height="23" class="InputLabelCell">
							需要验证：
						</td>
						<td width="150" height="23">
							 <goa:write name="Pop3ConfigForm" property="vo.smtp_auth"/>
						</td>
					</tr>
					<tbody id="needAuth" name="needAuth" style="display:none">
						<tr class="listcellrow">
							<td width="119" height="23"  class="InputLabelCell">
								验证账号：
							</td>
							<td width="150" height="23">
								<goa:write name="Pop3ConfigForm" property="vo.smtp_name"/>
							</td>
						</tr>

						<tr class="listcellrow">
							<td width="119" height="23"  class="InputLabelCell">
								验证密码：
							</td>
							<td width="150" height="23">
								<goa:write name="Pop3ConfigForm" property="vo.smtp_pwd"/>
							</td>
						</tr>
					</tbody>
				  </table>
				  </fieldset>
				  </td>
				</tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center">
		<html:hidden name="Pop3ConfigForm" property="fid" />
<%
String temp = "toUrl('"+context+"/dailyoffice/mailmgr/pop3config/pop3configedit.do?actionType=modify')";
%>
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=temp%>" operation="YJGL.OPT"/>
          <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

