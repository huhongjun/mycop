<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context = request.getContextPath();
%>
</SCRIPT>
<html>
<body class="bodybg">
  <html:form action="/dailyoffice/mailmgr/pop3config/pop3configlist" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText">外部邮件帐号设置</td></tr>
  <tr>
   <td>&nbsp;<html:hidden name="Pop3ConfigForm" property="actionType"/></td>
      </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		 
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="新建" operation="YJGL.OPT"/>
          </td>
		   <td>
<% 
String temp1="toUrl('"+context+"/dailyoffice/mailmgr/pop3config/pop3configedit.do?actionType=modify',true)";
%>		
		   <goa:button styleClass="button"  property="copy" onclick="<%=temp1%>" value="修改" operation="YJGL.OPT"/>
          </td>

		   <td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="YJGL.OPT"/>
<% 
String temp2="toUrl('"+context+"/dailyoffice/mailmgr/pop3config/pop3configview.do',true)";
%>	
		    <td> <goa:button  styleClass="button" property="delete" value="查看"  onclick="<%=temp2%>" operation="YJGL.VIEW"/>
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  >
        <tr height="20">
            <td width="15" class="Listtitle" align="right" ><input alt="选择" type="checkbox" name="selectall"  onclick="selectAllChk(this)"></td>
      		<td width="20%" class="Listtitle" id=".pop3_address" onclick="onchangeOrderBy(this)"> <div align="left">POP3服务器</div></td>
      		<td width="20%" class="Listtitle" id=".incept_mail_dir"  onclick="onchangeOrderBy(this)"> <div align="left">接收邮件夹</div></td>
            <td width="20%" class="Listtitle" id=".smtp_server"  onclick="onchangeOrderBy(this)"> <div align="left">SMTP服务器</div></td>
            <td width="20%" class="Listtitle" id=".pop3_name"  onclick="onchangeOrderBy(this)"> <div align="left">帐号名称</div></td>
			<td width="20%" class="Listtitle" id=".use_flag"  onclick="onchangeOrderBy(this)"> <div align="left"> 帐号状态</div></td>
    </tr>
    <logic:iterate id="ps" name="Pop3ConfigForm" property="pageControl.data" type="com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/mailmgr/pop3config/pop3configview.do?fid=" +ps.getSerial_num()+ "',false)"%>">
		<td class="listcellrow">
			<input type='checkbox' name='fid' value='<goa:write name="ps" property="serial_num" filter="true"/>'>   		  
			</td>
		<td class="listcellrow"  align="left"><goa:write name="ps" property="pop3_address" filter="true"/>&nbsp;</td>
		<td class="listcellrow" align="left"><goa:write name="ps" property="mail_dir_name" filter="true"/>&nbsp;</td>
<td class="listcellrow"><goa:write name="ps" property="smtp_server" filter="true"/>&nbsp;</td>
<td class="listcellrow"><goa:write name="ps" property="pop3_name" filter="true"/>&nbsp;</td>
<td class="listcellrow4"><goa:write name="ps" property="use_flag" filter="true"/>&nbsp;</td>	
	</tr>
	</logic:iterate>
</table>
</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<html:hidden name="Pop3ConfigForm" property="orderType"/>
		<html:hidden name="Pop3ConfigForm" property="orderFld"/>
		<goa:pagelink name="Pop3ConfigForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>

</body>
</html>
