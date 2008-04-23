<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.dao.MailConstant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<TITLE>邮箱信息显示</TITLE>
<%
	String context =request.getContextPath();
%>
<SCRIPT LANGUAGE="JavaScript">
	/*function doDelete(){

		var frmObj = document.forms[0];
		var strAction = frmObj.action ;
		frmObj.action =strAction+"?actionFlag=delete";
		//alert ("delete=" + frmObj.action);
		frmObj.submit();

	}*/
	function gotoView(mailId) {
    var frmObj = document.forms[0];
    var newLocation = "<%=context%>" + "/dailyoffice/mailmgr/mailview.do" + "?fid=" + mailId;
    window.location = newLocation ;
}
</SCRIPT>
<script langauge="javascript">
function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#eeeeee';
}
</script>

</HEAD>
<body class="bodybg">
 <html:form action="/dailyoffice/mailmgr/maildirectorylist">
<p>&nbsp;</p>
<table width="92%" border="0" align="center" cellpadding="2" cellspacing="1">
  <tr>
    <td width="36"><img src="../../images/ico_wjj.gif" width="36" height="36"></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="f14">文件夹管理</td>
        </tr>
        <tr>
          <td><hr size="1"></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="f12">当前用户:<strong><goa:write name='MailDirectoryForm' property='userName' filter='true' />&nbsp;</strong></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="f12">邮箱容量 <B><goa:write name='MailDirectoryForm' property='mailCapacityVo.mail_capacity' filter='true' />&nbsp;</B>M，
	已使用<B><bean:write name='MailDirectoryForm' property='mailCapacityVo.usedmailsize' filter='true' />&nbsp;</B>M
	</td>
  </tr>
  <logic:equal name="MailDirectoryForm" property="operate" value="true">
<TR><td>&nbsp;</td>
<TD valign="middle" align="left" width="98%" colspan="4">
    <%
    	String tempUrl = "javascript:window.location='" + context + "/dailyoffice/mailmgr/maildiredit.do'";
    %>
		<p align="right">
                    <goa:button property="ADD" operation="YJGL.OPT" styleClass="button" value="新增邮件夹" onclick="<%=tempUrl%>"  /> | <goa:button styleClass="button" property="delete" operation="YJGL.OPT" value="删除" onclick="javascript:doDelete()"  />
                  |</p>
    </TD>
  </TR>
  </logic:equal>
<tr>
    <td>&nbsp;</td>
    <td class="t12">
	<TABLE width=100% BORDER=0 CELLSPACING=0 CELLPADDING=1 >
            <TR class="Listtitle">
            	<TD width="277">邮件夹</TD>
   		<TD width="161" align="right">邮件数</TD>
		<TD width="162" align="right">新邮件数</TD>
		<TD width="162" align="right">占用空间</TD>
            </TR>
 <logic:iterate id="ps" name="MailDirectoryForm" property="userMailDirList" type="com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO" indexId="index">
 	    <%if("COUNT".equals(ps.getUser_code())) {%>
	<TR class="Listtitle">
		  <TD valign="middle" align="left" width="161"> &nbsp;<goa:write name='ps' property='mail_dir_name' filter='true' />
          </TD>
		  <TD valign="middle" align="right" width="162">&nbsp;<goa:write name='ps' property='totalmailnum' filter='true' />
          </TD>
		  <TD valign="middle" align="left" width="162">&nbsp;<goa:write name='ps' property='unreadmailnum' filter='true' />
          </TD>
		  <TD valign="middle" align="right" width="162">&nbsp;<goa:write name='ps' property='totalmailsize' filter='true' />
          </TD>

	</TR>
             <%} else {%>
	     <tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick='javascript:gotoView("<goa:write name='ps' property='serial_num'/>");'>
                 <TD width="277" class="listcellrow">
                       <logic:equal name="MailDirectoryForm" property="operate" value="true">
            	  	<input type="checkbox" name="fid" value="<%=ps.getSerial_num()%>"
                        <%
                        	if("000000".equals(ps.getUser_code()) || Integer.parseInt(ps.getTotalmailnum()) > 0) {
                        %>
                            disabled="true"
                        <%}%>
                        />&nbsp;
                       </logic:equal>
                       <%
                          String tempUrl = "javascript:window.location='" + context + "/dailyoffice/mailmgr/maillist.do?mailDirId=" + ps.getSerial_num() + "'";
                       %>
                             <goa:link href="<%=tempUrl%>" operation="YJGL.VIEW">
                                 <goa:write name='ps' property='mail_dir_name' filter='true' />
                             </goa:link>
                        <logic:notEqual name="ps" property="user_code" value="000000">
                            <logic:notEqual name="ps" property="serial_num" value="">
                                <%
                                    tempUrl = "javascript:window.location='" + context + "/dailyoffice/mailmgr/maildiredit.do?actionType=modify&fid=" + ps.getSerial_num() + "'";
                                %>
                                <goa:link  border="0" href="<%=tempUrl%>" operation="YJGL.OPT">
                                <img alt="修改" src="../../images/property.gif" border="0"/>
                                </goa:link>
                           </logic:notEqual>
                      </logic:notEqual>
                </TD>
                <TD width="161" class="listcellrow" align="right"><goa:write name='ps' property='totalmailnum' filter='true' /></TD>
                <TD width="162" class="listcellrow" align="right"><goa:write name='ps' property='unreadmailnum' filter='true' /></TD>
                <TD width="162" class="listcellrow4" align="right"><goa:write name='ps' property='totalmailsize' filter='true' /></TD>
            </TR>
            <%}%>
	</logic:iterate>
        </TABLE>
  <logic:equal name="MailDirectoryForm" property="operate" value="true">
    <TR>
    <td>&nbsp;</td>
    <TD valign="middle" align="left" width="98%" colspan="4">
		<p align="right">
                    <%
                        String tempUrl = "javascript:window.location='" + context + "/dailyoffice/mailmgr/maildiredit.do'";
                    %>
                    <goa:button property="ADD" operation="YJGL.OPT" styleClass="button" value="新增邮件夹" onclick="<%=tempUrl%>"  /> | <goa:button styleClass="button" property="delete" operation="YJGL.OPT" value="删除" onclick="javascript:doDelete()"  />
                  |</p>
    </TD>
  </TR>
  </logic:equal>
  </table>
	</td>
  </tr>
</table>


</html:form>
</BODY>
