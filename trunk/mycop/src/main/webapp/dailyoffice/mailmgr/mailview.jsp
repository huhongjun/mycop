<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.dao.MailConstant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
        String context = request.getContextPath();
        String downLoadTool = "/util/downloadfile.jsp";
        String downLoadPathParmName = "filepath=";
        String downLoadFileParmName = "filename=";
        int replyWithOriginalFlag = MailConstant.RE_MAIL_WITH_ORIGINAL_MAIL;
        int replyToAllFlag = MailConstant.RE_MAIL_TO_ALL;
        String garbageFolderValue = MailConstant.DIR_GARBAGE_FOLDER;
%>
<head>
<!--HTML模板资源-->
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/toolbar.css">
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/editor.css">
<script src="<%=context%>/common/htmleditor/js/toolbar.js"></script>
<script src="<%=context%>/common/htmleditor/js/editor.js"></script>
<script src="<%=context%>/common/htmleditor/js/colorpicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>查看邮件</title>
</head>

<script language="javascript">
var replyWithOriginalFlag = <%=replyWithOriginalFlag%>;
var replyToAllFlag = <%=replyToAllFlag%>;
var writeMailUrl = '<%=context%>' + "/dailyoffice/mailmgr/writemail.do";
var mailId = '<goa:write name="MailMgrForm" property="mailVo.serial_num" filter="true"/>';
var editorWidth = 670;
var editorHeight = 300;
if ( (screen.width == 800) && (screen.height == 600) ) {
	editorWidth = 550 ;
	editorHeight = 200;
}
function ylib_Browser()
{
	d=document;
	this.agt=navigator.userAgent.toLowerCase();
	this.major = parseInt(navigator.appVersion);
	this.dom=(d.getElementById)?1:0;
	this.ns=(d.layers);
	this.ns4up=(this.ns && this.major >=4);
	this.ns6=(this.dom&&navigator.appName=="Netscape");
	this.op=(window.opera? 1:0);
	this.ie=(d.all);
	this.ie4=(d.all&&!this.dom)?1:0;
	this.ie4up=(this.ie && this.major >= 4);
	this.ie5=(d.all&&this.dom);
	this.win=((this.agt.indexOf("win")!=-1) || (this.agt.indexOf("16bit")!=-1));
	this.mac=(this.agt.indexOf("mac")!=-1);
};

var oBw = new ylib_Browser();
function ylib_getPageX(o) { var x=0; if(oBw.ns) x=o.pageX; else { while(eval(o)) { x+=o.offsetLeft; o=o.offsetParent; } } return x; };
function ylib_getPageY(o) { var y=0; if(oBw.ns) y=o.pageY; else { while(eval(o)) { y+=o.offsetTop; o=o.offsetParent; } } return y; };
function showMenu(menuName){
	var evt=event;
	var obj=evt.srcElement;
	var tmpObj=null;
	//while(obj.tagName!="SPAN"){
	var menu=document.all[menuName];
        var body = document.body;
        var top = ylib_getPageY(obj)+obj.offsetHeight;
        if(top - body.scrollTop + 80 > body.clientHeight ) {
            top = top - 90;
        }
	//}
        //alert('obj width : ' + obj.offsetWidth);
        if(document.all) menu.style.width = obj.offsetWidth+'px';
        //menu.style.height = 300;
        menu.style.top = top+'px';
        menu.style.left = ylib_getPageX(obj)+'px';
	menu.style.cursor="default";

	if(menu.target==null){
		menu.target=obj;
	}
	if (obj ==null){
		alert("is null");
	}

	if(menu.style.display=="none") {
		menu.style.display="block";
	} else if(menu.style.display=="block") {
		menu.style.display="none";
	}
	event.cancelBubble=true;
}
document.onclick=function(){
	hideMenu('replyMenu');
}
function hideMenu(menuName){
	var menu=document.all[menuName];
    //alert("!!!")

	var evt=event;
	var target=menu.target;
	if(target!=null){
	    if(evt.srcElement!=target){
	        menu.style.display="none";
	    }
	}else   if ( menu.style.display == "block"){
    menu.style.display = "none" }
}
function replyMailByType(type){
	var bCheck;
	var frmObj = document.forms[0];
	frmObj.submit();
}
	function DoClick(){
		if (event.button == 1){
			alert("注意！请点击鼠标右键，选择“目标另存为...”来下载邮件至本地！请保存为 .eml 文件，以便可以使用OUTLOOK直接阅读。");
		}
	}

	function changeMailH(){
		var flag =false;
                var display = document.getElementById("MailHeader").style.display;
		//flag = document.getElementById("showMailHeader").checked
		//if (flag==true) {
                if(display == "none") {
			display = "block";
		} else if(display == "block") {
                       display = "none";
		}
		document.getElementById("MailHeader").style.display = display;
	}
function gotoWriteMail(type) {
    var actionType = document.all['mailActionType'].value;
    var locationUrl = "";
    var rnd = new Date().getTime();
    //alert(rnd);
    switch(type) {
        case 'fw':
        	locationUrl = writeMailUrl + "?" + "fw=true&mailSelect=" + mailId;
                break;
        default:
        	locationUrl = writeMailUrl + "?" + "replyActionType=" + actionType
                + "&mailSelect=" + mailId;
    }
    locationUrl = locationUrl + "&" +rnd;
    //alert("locationUrl : " + locationUrl);
    var frmObj = document.forms[0];
    frmObj.action = locationUrl;
    frmObj.submit();
    //window.location = locationUrl;
}
function doFW() {
    gotoWriteMail('fw');
}
function replyMail() {
    var actionType = 0;
    document.all['mailActionType'].value = actionType;
    //alert('In replyMail,actionType : ' + document.all['mailActionType'].value);
}
function replyMailWithOriginal() {
    var actionType = 0;
    actionType += replyWithOriginalFlag;
    document.all['mailActionType'].value = actionType;
    //alert('replyMailWithOriginal,actionType : ' + document.all['mailActionType'].value);
}
function replyMailtoAll() {
    var actionType = 0;
    actionType += replyToAllFlag;
    document.all['mailActionType'].value = actionType;
    //alert('replyMailtoAll,actionType : ' + document.all['mailActionType'].value);
}
function replyToAllWithOriginal() {
    var actionType = 0;
    actionType += replyWithOriginalFlag;
    actionType += replyToAllFlag;
    document.all['mailActionType'].value = actionType;
    //alert('replyToAllWithOriginal,actionType : ' + document.all['mailActionType'].value);
}
function deletMail() {
	//alert("mailId : " + mailId);
        var anser = confirm("当前邮件将被删除到垃圾箱，是否确定？");
        if (anser == false) {
        	return false;
        }
	var locationUrl = '<%=context%>' + "/dailyoffice/mailmgr/maillist.do" + "?moveMailToGarbage=true&deleteMailId=" + mailId ;
	window.location = locationUrl;
}
function delMailEver() {
	//alert("mailId : " + mailId);
        var anser = confirm("当前邮件将被永久删除，是否确定？");
        if (anser == false) {
        	return false;
        }
	var locationUrl = '<%=context%>' + "/dailyoffice/mailmgr/maillist.do" + "?deleteMailEver=true&deleteMailId=" + mailId ;
	window.location = locationUrl;
}
function doReturn() {
	var frmObj = document.forms[0];
	var strAction = frmObj.action ;
	frmObj.action =strAction+"?back=true";
	frmObj.submit();
}
function setE1() {
	var value = document.all['mailVo.content'].value;
	e1.setValue(value);
	e1.setReadOnly();
}
function init() {
	setE1();
        var check = false
        if(document.all['mailInfo.show_flag'].value == 1) {
    		document.getElementById("MailHeader").style.display = "block";
                document.all['showMailHeaderLable'].innerHTML = "隐藏邮件头";
                //check = false;
        } else {
            document.getElementById("MailHeader").style.display = "none";
            document.all['showMailHeaderLable'].innerHTML = "显示邮件头";
            //check = false;
        }
        document.all['showMailHeader'].checked = check;
}
</script>
<body class="bodybg" onload="javascript:init();">
<html:form action="/dailyoffice/mailmgr/mailview">
<table border="0" cellpadding="0" cellspacing="1" width="92%" height="60%" align="center" valign="middle">
  <tr>
    <td valign="top" width="100%">
<table width=100% border=0 cellspacing=0 cellpadding=0>
	<tr>
            <td>
                <html:hidden name="MailMgrForm" property="mailActionType"/>
                <html:hidden name="MailMgrForm" property="fid"/>
                <html:hidden name="MailMgrForm" property="mailInfo.show_flag"/>
            </td>
	</tr>
</table>
      <table border="0" cellPadding="0" cellSpacing="0" width="100%">
        <tr>
          <td height="5" colspan="2" align="left" nowrap class="f14">看邮件</td>
        </tr>
        <tr>
          <td height="5" colspan="2" align="left" nowrap class="f14"><hr size="1"></td>
        </tr>
        <tr class="listcellrow">
          <td height="14" align="left" nowrap></td>
          <td align="right" height="23" class="f12"><label for="showMailHeader"> &nbsp; <span id="showMailHeaderLable">隐藏邮件头</span><input name="showMailHeader" id="showMailHeader" type="checkbox" onclick="javascript:changeMailH()"></label>
              <logic:notEqual name="MailMgrForm" property="mailDirId" value="<%=garbageFolderValue%>">
            | <goa:button property="YJGL.OPT" onclick='javascript:deletMail();' value="删除" styleClass="button"/>
              </logic:notEqual>
            | <goa:button property="YJGL.OPT" onclick='javascript:delMailEver();' value="永久删除" styleClass="button"/> | <goa:button property="YJGL.OPT" value="回复" onclick="javascript:showMenu('replyMenu')" styleClass="button"/> | <goa:button property="YJGL.OPT" onclick="javascript:doFW();" value="转发" styleClass="button"/>
            | <input type="button" onClick="javascript:doReturn();" value="返回" class="button"> |
		  </td>
        </tr>
      </table>
<hr size="1">
    <table width="100%" border="0" cellpadding="0" cellspacing="1" valign="top">
        <tbody id="MailHeader" style="display:none" class="listcellrow">
          <tr>
            <td width="10%" align="left" nowrap class="InputLabelCell">发件人：</td>
            <td width="40%" height="18" class ="bgbk2">
                <goa:write name="MailMgrForm" property="mailVo.post_username" filter="true"/>
            </td>
            <td width="10%" height="18"  nowrap class="InputLabelCell">收&nbsp;件&nbsp;人：</td>
            <td width="40%" height="18" class ="bgbk2">
                <goa:write name="MailMgrForm" property="mailVo.receive_address_name" filter="true"/>
            </td>
  	  </tr>
          <tr>
            <td width="10%" nowrap class="InputLabelCell" >抄&nbsp;&nbsp;送：</td>
            <td height="18"  class ="bgbk2"><goa:write name="MailMgrForm" property="mailVo.copy_send_name" filter="true"/></td>
            <td width="10%" height="18"  nowrap class="InputLabelCell">发送时间：</td>
            <td height="18" class ="bgbk2"><nobr><goa:write name="MailMgrForm" property="mailVo.send_date" filter="true"/></nobr></td>
         </tr>
      </tbody>
        <tr>
          <td width="10%" align="left" nowrap class="InputLabelCell">主&nbsp;&nbsp;题：</td>
          <td height="18" colspan="3" align="left" class="f12">
              <goa:write name="MailMgrForm" property="mailVo.title" filter="true"/>
          </td>
        </tr>
</table>

<hr size="1">
<table border="0" cellpadding="0" cellspacing="0" width="100%" bordercolorlight="#C0C0C0" bordercolordark="#FFFFFF" valign="top">
        <tr>
           <td width="100%" class="InputLabelCell">正文内容:</td>
        </tr>
	<tr heigth="300">
		<td nowrap colspan="3"><!--html:textarea property="mailVo.content"  maxlength="50000" cols="70" rows="10" style="bk" /-->
				<SCRIPT LANGUAGE="JavaScript">
				<!--
					var editor=new Editor("e1",editorWidth,editorHeight);
				//-->
				</SCRIPT>
				<html:hidden property="mailVo.content" />
				</td>
    </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%" valign="top">
 <logic:greaterThan name="MailMgrForm" property="mailVo.attachcount" value="0">
  <logic:iterate id="ps" name="MailMgrForm" property="mailVo.attachList" type="com.gever.goa.dailyoffice.mailmgr.vo.MailAttchVO" indexId="index">

    <tr class="f12">
          <td class="InputLabelCell">附件：
             <% String strUrl = "";
              /* String strHref = context + downLoadTool + "?" + downLoadPathParmName
                   + ps.getFile_path() + "&" + downLoadFileParmName + ps.getAttch_name();
		 strUrl = ps.getAttch_name();*/
		 String strHref = context + ps.getFile_path();
		 strUrl = ps.getAttch_name();
                 String downloadMethod = "download2('" + ps.getFile_path() + "','"+ps.getAttch_name()+"')";
		%>
              <goa:link style="cursor:hand" href="<%=strHref%>" onclick ="<%=downloadMethod%>" title="下载附件" ><%=ps.getAttch_name()%>
              </goa:link>&nbsp;

          </td>
    </tr>
  </logic:iterate>
</logic:greaterThan>
<logic:lessEqual name="MailMgrForm" property="mailVo.attachcount" value="0">
    <tr>
      <td valign="top">
     </td>
    <tr >
      <td width="100%" align="left" class="InputAreaCell" >
		  附件: 无
	    </td>
	</tr>
</logic:lessEqual>
<tr>
    <td>
        <hr size="1">
    </td>
</tr>
        <tr>

          <td align="left" height="23" class="f12">&nbsp;
              <logic:notEqual name="MailMgrForm" property="mailDirId" value="<%=garbageFolderValue%>">
            | <goa:button property="YJGL.OPT" onclick='javascript:deletMail();' value="删除" styleClass="button"/>
              </logic:notEqual>
            | <goa:button property="YJGL.OPT" onclick='javascript:delMailEver();' value="永久删除" styleClass="button"/> | <goa:button property="YJGL.OPT" value="回复" onclick="javascript:showMenu('replyMenu')" styleClass="button"/> | <goa:button property="YJGL.OPT" onclick="javascript:doFW();" value="转发" styleClass="button"/>
            | <input type="button" onClick="javascript:doReturn();" value="返回" class="button"> |
		  </td>
        </tr>
<tr>
    <td>
        <hr size="1">
    </td>
</tr>
</table>
</html:form>
<IFRAME frameBorder=1 id="attch" src="" style="display:none" ></IFRAME>
</body>
</html>

<div  id="replyMenu" style="position:absolute;left:400;top:900;display:none;width: 0; height: 0;background-color: #D5E7FA; border-style: ridge; border-width: 1" >
   <div>
	  <table cellspacing="0" cellpadding="0">
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
                           <td height="18" nowrap class="f12" onclick="javacript:replyMail();gotoWriteMail();event.cancelBubble = true;">

				<span>
                       		  回信
                        	</span>
                            </td>
			</tr>
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:replyMailWithOriginal();gotoWriteMail();event.cancelBubble = true;">

				<span>
                    		    回信（附带原文）
                     		</span>
                       	    </td>
			</tr>
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:replyMailtoAll();gotoWriteMail();event.cancelBubble = true;">

				<span>
                    		    给所有人回信
                      		  </span>
                           </td>
			</tr>
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:replyToAllWithOriginal();gotoWriteMail();event.cancelBubble = true;">

				<span>
                    		    给所有人回信（附带原文）
                      		  </span>
                           </td>
			</tr>
	  </table>
   </div>
</div>

