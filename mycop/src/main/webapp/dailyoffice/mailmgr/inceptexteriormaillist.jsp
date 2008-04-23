<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.dao.MailConstant"%>
<%@ page import="com.gever.util.StringUtils"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>

<TITLE>POP3邮箱设置</TITLE>
<SCRIPT LANGUAGE="JavaScript">
<!--

function doInceptAction(actionType){
	var frmObj = document.forms[0];
	var bCheck;
	bCheck = checkSelect();
	var action = frmObj.action;
	if (bCheck <=0 && actionType !="add") {
		alert('您还没有选择任何pop3邮件帐号！');
		return false;
	}
	if (actionType == "count"){
		counting.style.visibility="visible";
	} else if (actionType == "delete"){
		deleteing.style.visibility="visible";
	} else if(actionType == "incept"){
		incepting.style.visibility="visible";
	}

	frmObj.action =  action + "?" + actionType + "=true";
	//alert("form action is " + frmObj.action);
	frmObj.submit();

}
function checkSelect(){
	var frmObj = document.forms[0];
	var elObj,intlocal;
	var iCount = 0;
	var strSelectd ="";
	for(var i=0;i<frmObj.elements.length;i++){
		elObj = frmObj.elements[i];
		strObjName = elObj.name;
		if (elObj.type == "checkbox" && elObj.checked == true){
				++iCount;
		}
	}
	return (iCount)

}
//-->
</SCRIPT>

<script langauge="javascript">
function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#eeeeee';
}
</script>
<body class="bodybg">
<p>&nbsp;</p>
<table width="98%" border=0 cellspacing=1 cellpadding=2 height="23" align="center">
    <tr>
    	<td width="36" valign="top">
                <img src="../../images/ico_js.gif" width="32" height="32">
        </td>
	<td>
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                  	<td class="f14">外部邮件账号设置</td>
                  </tr>
                  <tr>
          		<td><hr size="1"></td>
                  </tr>
                  <TR >
          		<TD height=42 valign="middle" align="left" width="100%" colspan="5" class="f12">
            			<p align="left">您共设置了<strong> <goa:write name="Pop3ConfigForm" property="numberOfPop3Acount" filter="ture"/> </strong>个邮件账号
         		</TD>
                  </TR>
		  <TR >
                        <TD height=23 valign="middle" align="left" width="100%" colspan="5">
    				<p align="right">| <input type="button" onclick="javascript:doInceptAction('count')" class="button" value="统计pop3邮件数"> | <input type="button" onclick="javascript:doInceptAction('incept')" class="button" value="接收pop3邮件" > | <input type="button" onclick="javascript:doInceptAction('delete')" class="button" value="删除pop3邮件" > |  <!--input type="button" onclick="javascript:doInceptAction('add')" class="button" value="新增邮件账号"-->
     				</p>
  			</TD>
		  </TR>
      		</table>
        </td>
    </TR>
    <tr>
	<td>&nbsp;</td>
	<td>
        	<logic:equal name="Pop3ConfigForm" property="inceptType" value="false">

		<html:form action="/dailyoffice/mailmgr/inceptexteriormaillist">
                    <html:hidden name="Pop3ConfigForm" property="orderType"/>
                    <html:hidden name="Pop3ConfigForm" property="orderFld"/>
                <TABLE width="100%" BORDER=0 CELLSPACING=0 CELLPADDING=1 >
                	<TR class="Listtitle">
				<TD height=20 nowrap class="mailtdbg" id=".pop3_name"  onclick="onchangeOrderBy(this)"><div align="left">账号名称</div></TD>
          			<TD height="20" nowrap class="mailtdbg" id=".pop3_address"  onclick="onchangeOrderBy(this)"><div align="left">POP3服务器：端口</div></TD>
				<TD height="20" nowrap class="mailtdbg" id=".incept_mail_dir"  onclick="onchangeOrderBy(this)"><div align="left">接收邮件夹</div></TD>
                                <TD height="20" nowrap class="mailtdbg" id=".del_flag"  onclick="onchangeOrderBy(this)"><div align="left">删除远程</div></TD>
				<TD height="20" nowrap class="mailtdbg">远程邮件数</TD>
			</TR>
		<logic:iterate id="ps" name="Pop3ConfigForm" property="pageControl.data" type="com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO" indexId="index">
			<TR onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
				<TD height=20  class="listcellrow">
					<input type="checkbox" name="pop3ConfigeSelect" value='<goa:write name="ps" property="serial_num"/>' indexes="indexes"/>
					<!--a href="..\pop3configedit.do?ID=<%=ps.getSerial_num()%>&type=true"--><%=ps.getPop3_name()%><!--/a--></TD>
				<TD height="20" class="listcellrow">&nbsp;<%=ps.getPop3_address()%></TD>
				<TD height="20" class="listcellrow">&nbsp;<goa:write name='ps' property='incept_mail_dir' filter='true' /></TD>
				<TD height="20"  class="listcellrow">&nbsp;
                                    	<logic:equal name="ps" property="del_flag" value="1">
                                                否
					</logic:equal>
					<logic:notEqual name="ps" property="del_flag" value="1">
                                            是
					</logic:notEqual>
                             	</TD>
				<TD height="20"  class="listcellrow4">&nbsp;
					<goa:write name='ps' property='exteriorMailCount' filter='true' />
				</TD>
                        </TR>

                </logic:iterate>
  			<TR >
                              	<TD class="splitpage" align="right" colspan="5" >
				   <goa:pagelink name="Pop3ConfigForm" property="pageControl" />
				</TD>
                        </TR>
		</TABLE>
		</html:form>
        </td>
    </tr>
</TABLE>


<div id="counting" style="position:absolute; top:400; left:20; z-index:10; visibility:hidden">
	<table WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
	<tr>
	<td width="30%"></td>
	<td bgcolor="#009900">
	<table WIDTH="100%" height="70" BORDER="0" CELLSPACING="2" CELLPADDING="0">
	<tr><td align="center" bgcolor="#F7F7F7">邮件正在统计, 请稍候...</td></tr>
	</table>
	</td>
	<td width="30%"></td>
	</tr>
	</table>
</div>
<!--<div id="saving" style="position:relative; top:150; left:20; z-index:10; visibility:hidden">-->
<div id="deleteing" style="position:absolute; top:400; left:20; z-index:10; visibility:hidden">
	<table WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
	<tr>
	<td width="30%"></td>
	<td bgcolor="#009900">
	<table WIDTH="100%" height="70" BORDER="0" CELLSPACING="2" CELLPADDING="0">
	<tr><td align="center" bgcolor="#F7F7F7">邮件正在删除, 请稍候...</td></tr>
	</table>
	</td>
	<td width="30%"></td>
	</tr>
	</table>
</div>
<div id="incepting" style="position:absolute; top:400; left:20; z-index:10; visibility:hidden">
	<table WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
	<tr>
	<td width="30%"></td>
	<td bgcolor="#009900">
	<table WIDTH="100%" height="70" BORDER="0" CELLSPACING="2" CELLPADDING="0">
	<tr><td align="center" bgcolor="#F7F7F7">邮件正在接收, 请稍候...</td></tr>
	</table>
	</td>
	<td width="30%"></td>
	</tr>
	</table>
</div>
</logic:equal>
<logic:notEqual name="Pop3ConfigForm" property="inceptType" value="false">
<div align=left class="f12">
	<font color=blue><p align="center">邮件接收情况如下！ </p></font>

	<BR>
	<BR>
	<bean:write name='Pop3ConfigForm' property='inceptResult' filter='false' />
<BR>


	  <a href="inceptexteriormaillist.do">返回</a></div>
</logic:notEqual>
