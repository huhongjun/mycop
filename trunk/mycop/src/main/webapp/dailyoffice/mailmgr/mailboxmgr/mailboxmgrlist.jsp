<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
%>

<%
String context =request.getContextPath();
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
function checkSelect(){
	var count = 0;
	var length = 0;
	try{
		 length = document.forms[0].fid.length;
	} catch(e){
		return false;
	}
	if (isNaN(length))	{
		try{
			if (document.forms[0].fid.checked)	{
				++count;
			}
		}catch(e){}
	}

	for(var i=0;i<document.forms[0].fid.length;i++){
		if(document.forms[0].fid[i].checked)
			count++;
	}
	if(count==0){
				return false;
	} else
		return true;

}


//选择
function selectAllChk(obj){
	var count=0;
	var isSelect = obj.checked
	var length  = 0;
	try{
		length = document.forms[0].fid.length;
	} catch (e){return false;}

	if (isNaN(length))	{
		try{
		document.forms[0].fid.checked=isSelect;
		}catch(e){}
	}
	//alert(length + obj.checked);
	if (length ==0)	{
		return false;
	}

	if (length == 1)	{
		document.forms[0].fid.checked=isSelect;
	} else {
		for(var i=0;i<document.forms[0].fid.length;i++){
			document.forms[0].fid[i].checked=isSelect;
		}

	}
	checkTiming(isSelect);
}

function checkTiming(isCheck){
	var frmObj = document.forms[0];
	var elObj,intlocal;
	var iCount = 0;
	var strSelectd ="";
	for(var i=0;i<frmObj.elements.length;i++){
		elObj = frmObj.elements[i];
		strObjName = elObj.name;
		//alert(strObjName);
		intlocal= strObjName.indexOf("timing");
		if (intlocal>=0){

				elObj.disabled= !isCheck;

		}
	}
	return (iCount)

}
function checkOne(obj){

	var td=obj.parentElement;
	var tr=td.parentElement;
	//alert(tr.tagName)
	var cell=tr.cells[3];
	var chk=cell.children[0];
	chk.disabled=!obj.checked;
}

function checkInput(){
	var bRet =true;
	var frmObj = document.forms[0];
	if (frmObj.elements["vo.mail_capacity"].value==""){
		bRet = false;
	}
	if(isDecimal(frmObj.elements["vo.mail_capacity"].value)==false){
		bRet = false;
	}
	if(frmObj.elements["vo.mail_capacity"].value>10000 || frmObj.elements["vo.mail_capacity"].value<=0){
		bRet = false;
	}
	if (bRet ==false)
		alert("邮箱容量应在0 - 10000之间");
		//init();
	return bRet;
}
function doSave(){
	var bRet =false;
	if( !checkInput())
		return false;
	bCheck = checkSelect();
	if (bCheck<=false) {
		alert('您没有选择任何人员！');
		init();
		return false;
	}
	var anser = confirm("你确定要设置当前选择的邮箱容量吗？");
	if (anser == false)
		return false;
	document.forms[0].action =document.forms[0].action +"?action=doSave";
	document.forms[0].submit();

}
function addinfo()
{
  window.location="<%=context%>/dailyoffice/mailmgr/mailboxmgr.do?actionType=modify&fid=0";
}
function doDefault(){
	var bRet =false;
	if( !checkInput())
		return false;
	var anser = confirm("该操作将设置所有的邮箱容量为当前设置的大小，你确定吗？");
	if (anser == false)
		return false;
	document.forms[0].action =document.forms[0].action +"?action=doDefault";
	document.forms[0].submit();

}
function init(){
	var mailCapacity=document.forms[0].elements["vo.mail_capacity"];
	var name=document.forms[0].elements["vo.name"];
	mailCapacity.value="";
	name.focus();
}
function doQuery(){
	var sForm=document.forms[0];
	sForm.action=sForm.action+"?action=doQueryByUser";
	sForm.submit();
}

//-->
</SCRIPT>
<html>
<body class="bodybg" onload="init()">
  <html:form action="/dailyoffice/mailmgr/mailboxmgr/mailboxmgrlist" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText">邮箱管理</td></tr>
  <tr>
   <td>&nbsp;<html:hidden name="MailboxMgrForm" property="actionType"/></td>
      </tr>
  <tr> 
    <td class="f12" colspan="6">
	 <table border="0" width="700" cellspacing="1" cellpadding="2">
        <tr>	 
		<td class="f12" width="30%"><table width="100%" border="0">
		  <tr><td width="18%" align="left" class="f12">姓名：</td>
		<td width="36%"><html:text property="vo.name" size="15" maxlength="15" styleClass="input"/></td>
		<td  width="16%"><goa:button property="query" value="查询" styleClass="button" onclick="doQuery()" operation="YSGL.VIEW"/></td>
		</tr></table></td>
          <td class="f12" width="200" align="left">邮箱大小：</td>
		  <td width="155">
		   <html:text property="vo.mail_capacity" size="5" maxlength="5" styleClass="input"/>
		   </td>
 <td width="150"> <goa:button  styleClass="button" property="delete" value="设置所有人邮箱大小为该值"  onclick="doDefault()" operation="YSGL.OPT"/></td>
		   <td width="41"><goa:button styleClass="button"  property="exit"  onclick="doSave()" value="保存" operation="YSGL.OPT"/>
          </td>

		  <td width="100">
		<goa:button styleClass="button"  property="toEdit" onclick="addinfo()" value="外发邮件设置"/></td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="5">
	<table width="100%" border="0" cellpadding="2" cellspacing="0" align="center">
        <tr height="20" >
            <td width="5" class="Listtitle" ><input alt="选择" type="checkbox" name="selectall" align="left" onclick="selectAllChk(this)"></td>
      		<td width="25%" class="Listtitle" id=".name" onclick="onchangeOrderBy(this)"><div align="left">姓名</div></td>
      		<td width="25%" class="Listtitle" id=".mail_capacity"  onclick="onchangeOrderBy(this)"><div align="right">邮箱总空间(M)</div></td>
            <td width="25%" class="Listtitle" id=".usedmailsize"  onclick="onchangeOrderBy(this)" ><div align="right">已用空间(M)</div></td>
            <td width="25%" class="Listtitle" id=".mail_capacity-usedmailsize" onclick="onchangeOrderBy(this)" ><div align="right">未使用(M)</div></td>
    </tr>
    <logic:iterate id="ps" name="MailboxMgrForm" property="pageControl.data" type="com.gever.goa.dailyoffice.mailmgr.vo.MailCapacityVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
		<td  align="left" class="listcellrow">
		<input type='checkbox' name='fid' value='<goa:write name="ps" property="user_code" filter="true"/>'>   		  
		</td>
		<td class="listcellrow"><goa:write name="ps" property="name" filter="true"/>&nbsp;</td>
		<td align="right" class="listcellrow"><goa:write name="ps" property="mail_capacity" filter="true"/>&nbsp;</td>
		<td align="right" class="listcellrow"><goa:write name="ps" property="usedmailsize" filter="true"/>&nbsp;</td>
		<td align="right" class="listcellrow4"><goa:write name="ps" property="cur_capacity" filter="true"/>&nbsp;</td>
	</tr>
	</logic:iterate>
</table>
</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<html:hidden name="MailboxMgrForm" property="orderType"/>
		<html:hidden name="MailboxMgrForm" property="orderFld"/>
		<goa:pagelink name="MailboxMgrForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>

</body>
</html>
