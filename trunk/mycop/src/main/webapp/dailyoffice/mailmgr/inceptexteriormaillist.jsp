<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.dao.MailConstant"%>
<%@ page import="com.gever.util.StringUtils"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>

<TITLE>POP3��������</TITLE>
<SCRIPT LANGUAGE="JavaScript">
<!--

function doInceptAction(actionType){
	var frmObj = document.forms[0];
	var bCheck;
	bCheck = checkSelect();
	var action = frmObj.action;
	if (bCheck <=0 && actionType !="add") {
		alert('����û��ѡ���κ�pop3�ʼ��ʺţ�');
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
                  	<td class="f14">�ⲿ�ʼ��˺�����</td>
                  </tr>
                  <tr>
          		<td><hr size="1"></td>
                  </tr>
                  <TR >
          		<TD height=42 valign="middle" align="left" width="100%" colspan="5" class="f12">
            			<p align="left">����������<strong> <goa:write name="Pop3ConfigForm" property="numberOfPop3Acount" filter="ture"/> </strong>���ʼ��˺�
         		</TD>
                  </TR>
		  <TR >
                        <TD height=23 valign="middle" align="left" width="100%" colspan="5">
    				<p align="right">| <input type="button" onclick="javascript:doInceptAction('count')" class="button" value="ͳ��pop3�ʼ���"> | <input type="button" onclick="javascript:doInceptAction('incept')" class="button" value="����pop3�ʼ�" > | <input type="button" onclick="javascript:doInceptAction('delete')" class="button" value="ɾ��pop3�ʼ�" > |  <!--input type="button" onclick="javascript:doInceptAction('add')" class="button" value="�����ʼ��˺�"-->
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
				<TD height=20 nowrap class="mailtdbg" id=".pop3_name"  onclick="onchangeOrderBy(this)"><div align="left">�˺�����</div></TD>
          			<TD height="20" nowrap class="mailtdbg" id=".pop3_address"  onclick="onchangeOrderBy(this)"><div align="left">POP3���������˿�</div></TD>
				<TD height="20" nowrap class="mailtdbg" id=".incept_mail_dir"  onclick="onchangeOrderBy(this)"><div align="left">�����ʼ���</div></TD>
                                <TD height="20" nowrap class="mailtdbg" id=".del_flag"  onclick="onchangeOrderBy(this)"><div align="left">ɾ��Զ��</div></TD>
				<TD height="20" nowrap class="mailtdbg">Զ���ʼ���</TD>
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
                                                ��
					</logic:equal>
					<logic:notEqual name="ps" property="del_flag" value="1">
                                            ��
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
	<tr><td align="center" bgcolor="#F7F7F7">�ʼ�����ͳ��, ���Ժ�...</td></tr>
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
	<tr><td align="center" bgcolor="#F7F7F7">�ʼ�����ɾ��, ���Ժ�...</td></tr>
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
	<tr><td align="center" bgcolor="#F7F7F7">�ʼ����ڽ���, ���Ժ�...</td></tr>
	</table>
	</td>
	<td width="30%"></td>
	</tr>
	</table>
</div>
</logic:equal>
<logic:notEqual name="Pop3ConfigForm" property="inceptType" value="false">
<div align=left class="f12">
	<font color=blue><p align="center">�ʼ�����������£� </p></font>

	<BR>
	<BR>
	<bean:write name='Pop3ConfigForm' property='inceptResult' filter='false' />
<BR>


	  <a href="inceptexteriormaillist.do">����</a></div>
</logic:notEqual>
