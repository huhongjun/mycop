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
        <td align="center"><span class="TableTitleText">�ʼ��ʺ�����</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="Pop3ConfigForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
				  <fieldset style="width:527 ">
				  <legend class="f12">���ն���Ϣ(pop3)</legend>
				  <table  width="501" border="0" cellpadding="2" cellspacing="1">
				  <tr class="listcellrow">
					    <td height="23"  width="119" class="InputLabelCell">
							�ʺ����ƣ�  
						</td>
					    <td height="23" align="left">
							<goa:write name="Pop3ConfigForm" name="Pop3ConfigForm" name="Pop3ConfigForm" property="vo.pop3_name" filter="true"/>
						</td>
				 </tr>
                  <tr class="listcellrow">
					    <td height="23" width="119"  class="InputLabelCell">
							��������ַ���˿ڣ�
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
							��¼�˺ţ�
						</td>
					    <td height="23">
							<goa:write name="Pop3ConfigForm" name="Pop3ConfigForm" property="vo.pop3_account" filter="true"/>
						</td>
					</tr>

					<tr class="listcellrow">
					    <td height="23" width="119"  class="InputLabelCell">
							��¼���룺
						</td>
					    <td height="23">
							<goa:write name="Pop3ConfigForm" name="Pop3ConfigForm" property="vo.pop3_pwd"/>
						</td>
					</tr>
					<tr class="listcellrow">
					    <td height="23" width="119" class="InputLabelCell">
							ɾ��Զ���ʼ���
						</td>
					    <td height="23">
							<goa:write name="Pop3ConfigForm" property="vo.del_flag"/>
						</td>				
					</tr>
					<tr class="listcellrow">
					    <td  width="119" class="InputLabelCell">
						�����ʼ��У�
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
					<legend class="f12">���Ͷ���Ϣ(smtp)</legend>
					<table width="501" >
					<tr class="listcellrow">
					    <td width="119" height="23" class="InputLabelCell">
						��������ַ���˿ڣ�</td>
					    <td height="23" width="150">
							<goa:write name="Pop3ConfigForm" property="vo.smtp_server"/>
						</td>
						<td >
						<goa:write name="Pop3ConfigForm" property="vo.smtp_port"/>
						</td>
					</tr>

					<tr class="listcellrow">
						<td width="119" height="23" class="InputLabelCell">
							��ʾ������
						</td>
						<td width="150" height="23">
							<goa:write name="Pop3ConfigForm" property="vo.show_name"/>
						</td>
					</tr>

					<tr class="listcellrow">
						<td width="119" height="23"  class="InputLabelCell">
							���ŵ�ַ��
						</td>
						<td width="150" height="23">
							<goa:write name="Pop3ConfigForm" property="vo.show_address"/>
					  </td>
					</tr>
					<tr class="listcellrow">
						<td width="119" height="23" class="InputLabelCell">
							�˺�״̬��
						</td>
						<td width="150" height="23">
							 <goa:write name="Pop3ConfigForm" property="vo.use_flag"/>
					  </td>	 
					</tr>
					<tr class="listcellrow">
						<td width="119" height="23" class="InputLabelCell">
							��Ҫ��֤��
						</td>
						<td width="150" height="23">
							 <goa:write name="Pop3ConfigForm" property="vo.smtp_auth"/>
						</td>
					</tr>
					<tbody id="needAuth" name="needAuth" style="display:none">
						<tr class="listcellrow">
							<td width="119" height="23"  class="InputLabelCell">
								��֤�˺ţ�
							</td>
							<td width="150" height="23">
								<goa:write name="Pop3ConfigForm" property="vo.smtp_name"/>
							</td>
						</tr>

						<tr class="listcellrow">
							<td width="119" height="23"  class="InputLabelCell">
								��֤���룺
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
		<goa:button property="save" value="�޸�" styleClass="button"  onclick="<%=temp%>" operation="YJGL.OPT"/>
          <goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

