<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.dao.MailConstant"%>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.action.MailMgrForm"%>
<%@ page import="com.gever.util.StringUtils"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
	String garbageFolderValue = MailConstant.DIR_GARBAGE_FOLDER;
        String recieveFolderValue = MailConstant.DIR_RECIEVE_FOLDER;
        String draftFolderValue = MailConstant.DIR_DRAFT_FOLDER;
        String sendingFolderValue = MailConstant.DIR_SENDING_FOLDER;
        int replyWithOriginalFlag = MailConstant.RE_MAIL_WITH_ORIGINAL_MAIL;
        int replyToAllFlag = MailConstant.RE_MAIL_TO_ALL;
        String urgenceMail = MailConstant.URNGENCE_MAIL;
        String context = request.getContextPath();
        MailMgrForm form = (MailMgrForm)session.getAttribute("MailMgrForm");
        String imgName = "ico_sjx.gif";
        if(recieveFolderValue.equals(form.getMailDirId())) {
            imgName = "ico_sjx.gif";
        } else if(garbageFolderValue.equals(form.getMailDirId())) {
            imgName = "ico_ljt.gif";
        } else if(draftFolderValue.equals(form.getMailDirId())) {
            imgName = "ico_cgx.gif";
        } else if(sendingFolderValue.equals(form.getMailDirId())) {
            imgName = "ico_fjx.gif";
        }
%>
<title>邮件列表</title>

<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>

var replyWithOriginalFlag = <%=replyWithOriginalFlag%>;
var replyToAllFlag = <%=replyToAllFlag%>;
var writeMailUrl = '<%=context%>' + "/dailyoffice/mailmgr/writemail.do";

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
		tmpObj=obj.parentElement;
		obj=tmpObj;
	//}
        if(document.all) menu.style.width = obj.offsetWidth+'px';
        menu.style.top = ylib_getPageY(obj)+obj.offsetHeight+'px';
        menu.style.left = ylib_getPageX(obj)+'px';
        //menu.style.visibility = 'visible';
	//var scrollHeight = document.body.scrollTop;
	//var scrollLeft = document.body.scrollLeft;
	menu.style.cursor="default";

	if(menu.target==null){
		menu.target=obj;
	}
	if (obj ==null){
		alert("is null");
	}

	//var left =scrollLeft+event.clientX-event.offsetX ;
	//var top =scrollHeight+event.clientY-event.offsetY ;//evt.offsetY+evt.y + scrollHeight;
	//top = top - 86;
	//left = left - 5;
	//menu.style.pixelLeft=left;
	//menu.style.pixelTop=top;

	//alert("menu" + menu.style.display);
	if(menu.style.display=="none") {
		menu.style.display="block";
	} else if(menu.style.display=="block") {
		menu.style.display="none";
	}
	event.cancelBubble=true;
}

function gotoWriteMail(type) {
    var actionType = document.all['mailActionType'].value;
    var locationUrl = "";
    var mailSelect = document.all['fid'];
    var selectMailId = "";
    for(var i = 0 ; i < mailSelect.length ; i ++) {
        if(mailSelect[i].checked) {
            selectMailId = mailSelect[i].value;
        }
    }
    switch(type) {
        case 'fw':
        	locationUrl = writeMailUrl + "?" + "fw=true";
                break;
        default:
        	locationUrl = writeMailUrl + "?"
                            + "replyActionType=" + actionType
                            + "&mailSelect=" + selectMailId;
    }
    //alert("locationUrl : " + locationUrl);
    window.location = locationUrl;
}
function replyCheck() {
    	var returnvalue = true;
        var bCheck;
        bCheck = checkSelect();
        if (bCheck==0) {
                alert('您没有选择任何邮件！');
                returnvalue = false;
        } else if(bCheck > 1) {
            alert('您一次只能回复一封邮件！');
            returnvalue = false;
	}
        return returnvalue;
}
function replyMailAction() {
    if(replyMail()) {
        gotoWriteMail();
    }
}
function replyMailWithOriginalAction () {
    if(replyMailWithOriginal()) {
        gotoWriteMail();
    }
}
function replyMailtoAllAction () {
    if(replyMailtoAll()) {
        gotoWriteMail();
    }
}
function replyToAllWithOriginalAction () {
    if(replyToAllWithOriginal()) {
        gotoWriteMail();
    }
}
function replyMail() {

    var actionType = 0;
    if(replyCheck() == false) {
        return false;
    }
    document.all['mailActionType'].value = actionType;
    //alert('In replyMail,actionType : ' + document.all['mailActionType'].value);
    return true;
}
function replyMailWithOriginal() {
    var actionType = 0;
    if(replyCheck() == false) {
        return false;
    }
    actionType += replyWithOriginalFlag;
    document.all['mailActionType'].value = actionType;
    //alert('replyMailWithOriginal,actionType : ' + document.all['mailActionType'].value);
    return true;
}
function replyMailtoAll() {
    var actionType = 0;
    if(replyCheck() == false) {
        return false;
    }
    actionType += replyToAllFlag;
    document.all['mailActionType'].value = actionType;
    //alert('replyMailtoAll,actionType : ' + document.all['mailActionType'].value);
    return true;
}
function replyToAllWithOriginal() {
    var actionType = 0;
    if(replyCheck() == false) {
        return false;
    }
    actionType += replyWithOriginalFlag;
    actionType += replyToAllFlag;
    document.all['mailActionType'].value = actionType;
    //alert('replyToAllWithOriginal,actionType : ' + document.all['mailActionType'].value);
    return true;
}
document.onclick=function(){
	hideMenu('moveDirMenu');
        hideMenu('garbageMenu');
	hideMenu('replyMenu');
	hideMenu('queryMenu');
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
function gotoMailDir() {
	var frmObj = document.forms[0];
	strValue=frmObj.elements["gotoMailDirId"].value;

	if ( frmObj.elements["gotoMailDirId"].value=="none")
		return false;
	strAction = frmObj.action;
    	strAction += "?gotoMailDir=true";

 	frmObj.action = strAction;

	frmObj.submit();
}
function moveMail(strMailDirId){
	var bCheck;
	var frmObj = document.forms[0];
	bCheck = checkSelect();
        var e = event.srcElement;
	//alert(window.zyyjjmc.value);
	//var  = e.value
	if (bCheck==0) {
		alert('您没有选择任何邮件！');
		return;
	}
	frmObj.action = frmObj.action + "?moveToDirId=" + strMailDirId;
	//alert('action is ' + frmObj.action);
	frmObj.submit();
}
function delMail(){
	var bCheck;
	var frmObj = document.forms[0];
	bCheck = checkSelect();
	if (bCheck==0) {
		alert('您没有选择任何邮件！');
		return;
	}
        frmObj.action = frmObj.action + "?moveMailToGarbage=true" ;

	frmObj.submit();
}
function delMailEver() {
        var bCheck;
        var frmObj = document.forms[0];
        bCheck = checkSelect();
        if (bCheck==0) {
                alert('您没有选择任何邮件！');
                return;
        }
        anser = confirm("当前邮件将被删除，是否确定？"+ "有"+bCheck+ "条记录删除!");
        if (anser ==true)
                frmObj.action = frmObj.action + "?deleteMailEver=true" ;
        else
                return ;
	frmObj.submit();
}
function revertMail(){
	var bCheck;
	var frmObj = document.forms[0];
	bCheck = checkSelect();
	if (bCheck==0) {
		alert('您没有选择任何邮件！');
		return;
	}
	anser = confirm("当前邮件将被还原，是否确定？"+ "有"+bCheck+ "条记录还原!");
	if (anser ==true)
		frmObj.action = frmObj.action + "?revertMailFromGarbage=true" ;
	else
		return ;
	frmObj.submit();
}
function revertAllMail(){
	var bCheck;
	var frmObj = document.forms[0];
	anser = confirm("所有邮件将被还原，是否确定？");
	if (anser ==true)
		frmObj.action = frmObj.action + "?revertAllMailFromGarbage=true" ;
	else
		return ;
	frmObj.submit();
}
function removeAllGarbageMail(){
	var bCheck;
	var frmObj = document.forms[0];
	anser = confirm("所有邮件将被清空，是否确定？");
	if (anser ==true)
		frmObj.action = frmObj.action + "?removeAllMailFromGarbage=true" ;
	else
		return ;
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
                    if(elObj.name == "selectall") {
                        continue;
                    }
				++iCount;
		}
	}
	return (iCount)

}

function selectAll(obj){
	var frmObj = document.forms[0];
	var elObj,intlocal;
	var iCount = 0;

	for(var i=0;i<frmObj.elements.length;i++){
		elObj = frmObj.elements[i];
		strObjName = elObj.name;
		intlocal= strObjName.indexOf(".select[");
		if (intlocal>=0){
			elObj.checked= obj.checked;
		}
	}

}
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

}
function gotoView(mailId) {
    var frmObj = document.forms[0];
    var newLocation = "<%=context%>" + "/dailyoffice/mailmgr/mailview.do" + "?fid=" + mailId;
    window.location = newLocation ;
}
function gotoEditDraft(mailId) {
    var frmObj = document.forms[0];
    var newLocation = "<%=context%>" + "/dailyoffice/mailmgr/writemail.do" + "?editDraft=true" + "&mailId=" + mailId;
    window.location = newLocation ;
}
function GotoOtherPage() {
    this.gotoView = gotoView;
    this.gotoEditDraft = gotoEditDraft;
}
var goto = new GotoOtherPage();
var methodName;
<logic:notEqual name="MailMgrForm" property="mailDirId" value="<%=draftFolderValue%>">
   methodName = 'gotoView';
</logic:notEqual>
<logic:equal name="MailMgrForm" property="mailDirId" value="<%=draftFolderValue%>">
   methodName = 'gotoEditDraft';
</logic:equal>
function excuteMethod(parm) {
    goto[methodName](parm);
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
</head>

<body class="bodybg" onload="javascript:init();">
 <html:form action="/dailyoffice/mailmgr/maillist">
<table border="0" cellpadding="2"  cellspacing="1" width="98%">
<tr>
    <td width="36"><img src="../../images/<%=imgName%>" width="36" height="36"></td>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td class="f14">
              <html:hidden name="MailMgrForm" property="mailActionType"/>
              <html:hidden name="MailMgrForm" property="mailDirId"/>
          </td>
        </tr>
        <tr>
          <td class="bgbk1 f14"><goa:write name='MailMgrForm' property='mailDirectoryVo.mail_dir_name' filter='true' /></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="f12">您的邮箱容量为:<strong><goa:write name='MailMgrForm' property='mailCapacityVo.mail_capacity' filter='true' /></strong>&nbsp;M，目前已使用<strong><goa:write name='MailMgrForm' property='mailCapacityVo.usedmailsize' filter='true' /></strong>&nbsp;M</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td class="f12"><goa:write name='MailMgrForm' property='mailDirectoryVo.mail_dir_name' filter='true' />中有 <strong><goa:write name='MailMgrForm' property='mailDirectoryVo.totalmailnum' filter='true' /></strong> 个邮件，其中未读邮件
      <strong><goa:write name='MailMgrForm' property='mailDirectoryVo.unreadmailnum' filter='true' /></strong> 个</td>
  </tr>
  <tr>
    <td  nowrap>&nbsp;
    </td>
    <td class="f12">
        <logic:equal name="MailMgrForm" property="isPageSetuped" value="false">
            您还没有配置您的个人邮箱参数！每页显示邮件数将设置为系统默认值10行
        </logic:equal>
        <logic:equal name="MailMgrForm" property="isPageSetuped" value="true">
            每页显示邮件数：<goa:write name="MailMgrForm" property="pageNumber" filter="true"/>
        </logic:equal>
</td>
  </tr>
  <!--开始：信件条目-->
  <tr>
    <td>&nbsp;</td>
    <td><table width="100%" border="0" cellpadding="1" cellspacing="0">
        <tr align="right">
          <td colspan="7" class="mailtdbg"  >
              <table border="0" align="right" cellpadding="1" cellspacing="1">
              <tr align="right" height="20">
              	<td valign="bottom">
                    <input type="button" class="button" name="back" value="后退" onclick="javascript:history.back();"  />
              	</td>
                <td valign="bottom">
                    <html:hidden property="findNameQuery"/>
                    <html:hidden property="findMailTitleQuery"/>
                    <html:hidden property="findTimeQuery"/>
                    <html:hidden property="mailTodayQuery"/>
                    <html:hidden property="unreadMailQuery"/>
                    <html:hidden property="unergenceMailQuery"/>
                    <html:hidden property="exterioMailQuery"/>
                    <html:hidden property="orderType"/>
                    <html:hidden property="orderFld"/>

                    <html:hidden property="strWhereFromPage"/>
                    <goa:button property="search" styleClass="button" value="搜索" onclick="javascript:showMenu('queryMenu');"  operation="YJGL.VIEW" />
                </td>
                    <logic:equal name="MailMgrForm" property="mailDirId" value="<%=recieveFolderValue%>">
                        <td valign="middle">
                        <goa:button property="btnshowReceiveMenu" styleClass="button" value="回复邮件" onclick="javascript:showMenu('replyMenu');" operation="YJGL.OPT" />
                        </td>
                    </logic:equal>
                <td valign="bottom">
                    <logic:notEqual name="MailMgrForm" property="mailDirId" value="<%=garbageFolderValue%>">
                    	<goa:button property="btndelete" styleClass="button" value="删除" onclick="javascript:delMail();" operation="YJGL.OPT" />
                    </logic:notEqual>
                    <logic:equal name="MailMgrForm" property="mailDirId" value="<%=garbageFolderValue%>">
                        <goa:button property="btnshowGarbageMenu" styleClass="button" value="垃圾箱操作" onclick="javascript:showMenu('garbageMenu');" operation="YJGL.OPT" />
                    </logic:equal>
                </td>
                <td><goa:button property="btndeleteever" styleClass="button" value="永久删除" onclick="javascript:delMailEver();" operation="YJGL.OPT"/></td>
                <td><goa:button property="btndelete" styleClass="button" value="转移邮件" onclick="javascript:showMenu('moveDirMenu');" operation="YJGL.OPT"/></td>
                <td align=right nowrap valign="middle" >
                        <!--请选择要跳转的邮件夹：-->
                    	<html:select property="gotoMailDirId" disabled="false" onchange="return gotoMailDir()" size="1" styleClass="button">
                        <html:option value="-1"  >请选择跳转邮件夹</html:option>
                    	<html:options collection="otherMailDirList" property="serial_num" labelProperty="mail_dir_name"/>
                   	 </html:select>
                </td>
              </tr>
          </table>
          </td>
        </tr>
        <tr class="mailtdbg2">
          <td width="28"  ><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
          <td width="17" id=".priority"  onclick="onchangeOrderBy(this)"> <img src="<%=context%>/images/mail_priority.gif" alt="优先级别"></td>
          <td width="19" id=".attachcount"  onclick="onchangeOrderBy(this)"> <img src="<%=context%>/images/mail_atth.gif" alt="附件"></td>
          <td width="307" id=".title" onclick="onchangeOrderBy(this)"><div align="left"> 邮件主题</div></td>
          <td width="158" id=".post_username" onclick="onchangeOrderBy(this)" ><div align="left"> 发件人</div></td>
          <td width="113" id=".send_date" onclick="onchangeOrderBy(this)"><div align="left">  时间</div></td>
          <td width="95" id=".mail_size" onclick="onchangeOrderBy(this)"><div align="left"> 大小</div></td>
        </tr>
	<logic:iterate id="ps" name="MailMgrForm" property="pageControl.data" type="com.gever.goa.dailyoffice.mailmgr.vo.MailVO" indexId="index">
        <tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick='javascript:excuteMethod("<goa:write name='ps' property='serial_num'/>");'>
          <td  width="16" class="listcellrow">
              <input type="checkbox" name="fid" value="<goa:write name='ps' property='serial_num'/>"/>
          </td>
          <td   width="16" class="listcellrow">
              <logic:equal name="ps" property="priority" value="<%=urgenceMail%>">
            	<img src="<%=context%>/images/mail_hight.gif" alt="重要">
              </logic:equal>
              <logic:notEqual name="ps" property="priority" value="<%=urgenceMail%>">
                  &nbsp;
              </logic:notEqual>
          </td>
          <td width="16" class="listcellrow">
              <logic:greaterThan name="ps" property="attachcount" value="0">
           	<img src="<%=context%>/images/mail_atth.gif">
              </logic:greaterThan>
              <logic:lessEqual name="ps" property="attachcount" value="0">
                  &nbsp;
              </logic:lessEqual>
         </td>
         <td  width="290" class="listcellrow">
              <goa:write name='ps' property='unReadMailStyleBegin' filter='false' />
                  <%
                  	String title = "".equals(ps.getTitle()) ? "无标题" : ps.getTitle();
                        String method = "javascript:excuteMethod('" + ps.getSerial_num() + "');";
                  %>
          	<goa:link href='<%=method%>' title="<%=title%>" operation="YJGL.OPT">
                  <%=StringUtils.subStr(title,40) %>
          	</goa:link>
              <goa:write name='ps' property='unReadMailStyleEnd' filter='false' />
              &nbsp;
          </td>
	  <td  width="100" class="listcellrow">
              <goa:write name='ps' property='unReadMailStyleBegin' filter='false' />
		<goa:write name='ps' property='post_username' filter='true' />
              <goa:write name='ps' property='unReadMailStyleEnd' filter='false' />
              &nbsp;
         </td>

          <td  width="100" class="listcellrow">
              <nobr>
              <goa:write name='ps' property='unReadMailStyleBegin' filter='false' />
		<goa:write name='ps' property='send_date' filter='true' />
              <goa:write name='ps' property='unReadMailStyleEnd' filter='false' />
              </nobr>
              &nbsp;
          </td>
          <td width="60" class="listcellrow4">
              <nobr>
              <goa:write name='ps' property='unReadMailStyleBegin' filter='false' />
		<goa:write name='ps' property='mail_size' filter='true' />
              <goa:write name='ps' property='unReadMailStyleEnd' filter='false' />
            </nobr>
              &nbsp;
          </td>
        </tr>
	</logic:iterate>
        <tbody>
          <tr>
            <td colspan=7 class="splitpage" align="right">
		<goa:pagelink name="MailMgrForm" property="pageControl" />
            </td>
          </tr>
        </tbody>
      </table>

   </td>
  </tr>
 </table>

</html:form>

</body>
<div  id="moveDirMenu" style="position:absolute;left:400;top:900;display:none;width: 0; height: 0;background-color: #D5E7FA; border-style: ridge; border-width: 1" >
   <div>
	  <table height="" cellspacing="0" cellpadding="0">
              <logic:iterate id="element" name="otherMailDirList" type="com.gever.goa.dailyoffice.mailmgr.vo.MailDirectoryVO" indexId="index">
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:moveMail('<goa:write name="element" property="serial_num"/>');event.cancelBubble = true;">

			<span><goa:write name="element" property="mail_dir_name"/></span></td>
			</tr>
              </logic:iterate>
	  </table>
   </div>
</div>
<div  id="garbageMenu" style="position:absolute;left:400;top:900;display:none;width: 0; height: 0;background-color: #D5E7FA; border-style: ridge; border-width: 1" >
   <div>
	  <table height="" cellspacing="0" cellpadding="0">
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
                           <td height="18" nowrap class="f12" onclick="javacript:revertMail();event.cancelBubble = true;">

				<span>
                       		  还原选择邮件
                        	</span>
                            </td>
			</tr>
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:revertAllMail();event.cancelBubble = true;">

				<span>
                    		    还原所有邮件
                     		</span>
                       	    </td>
			</tr>
		<tr    onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:removeAllGarbageMail();event.cancelBubble = true;">

				<span>
                    		    清空所有邮件
                      		  </span>
                           </td>
			</tr>
	  </table>
   </div>
</div>
<div  id="replyMenu" style="position:absolute;left:400;top:900;display:none;width: 0; height: 0;background-color: #D5E7FA; border-style: ridge; border-width: 1" >
   <div>
	  <table cellspacing="0" cellpadding="0">
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
                           <td height="18" nowrap class="f12" onclick="javacript:replyMailAction();event.cancelBubble = true;">

				<span>
                       		  回信
                        	</span>
                            </td>
			</tr>
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:replyMailWithOriginalAction();event.cancelBubble = true;">

				<span>
                    		    回信（附带原文）
                     		</span>
                       	    </td>
			</tr>
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:replyMailtoAllAction();event.cancelBubble = true;">

				<span>
                    		    给所有人回信
                      		  </span>
                           </td>
			</tr>
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:replyToAllWithOriginalAction();event.cancelBubble = true;">

				<span>
                    		    给所有人回信（附带原文）
                      		  </span>
                           </td>
			</tr>
	  </table>
   </div>
</div>
<div  id="queryMenu" style="position:absolute;left:400;top:900;display:none;width: 0; height: 0;background-color: #D5E7FA; border-style: ridge; border-width: 1" >
   <div>
	  <table align="center" width="300" class="InputFrameMain"><tr><td><table border=0 align=center cellpadding=0 cellspacing=0 style="Margin:5px 5px 5px 5px">
		<tr class=InputFrameLine onclick="javacript:event.cancelBubble = true;">
			<td class="InputLabelCell" width="80"><nobr>发件人</nobr></td>
			<td class="InputAreaCell" width="200">
				<input type="text" name="findName" class="input" style="width:200" maxlength="10" onchange="javascript:findNameChange('findName');"/>
			</td>
		</tr>
		<tr class=InputFrameLine onclick="javacript:event.cancelBubble = true;">
			<td class="InputLabelCell"><nobr>邮件主题</nobr></td>
			<td class="InputAreaCell">
				<input type="text" name="findMailTitle" class="input" style="width:200" maxlength="10" onchange="javascript:findMailTitleChange('findMailTitle');"/>
			</td>
		</tr>
		<tr class=InputFrameLine onclick="javacript:event.cancelBubble = true;">
			<td class="InputLabelCell"><nobr>发送日期</nobr></td>
			<td class="InputAreaCell">
				<input type="text" name="findTime" onlytype="date" onfocus="checkTextBoxInput()"  ValidateType="date" Msg="正确格式:'2000-01-02'"  class="input" style="width:200" maxlength="10"  onPropertyChange="javascript:findTimeChange('findTime','mailToday');"/>
			</td>
		</tr>
		<tr class=InputFrameLine onclick="javacript:event.cancelBubble = true;" align="center">
				<td class="InputAreaCell" onclick="javascript:mailTodayChecked('mailToday','findTime');">
					<input type="checkbox" name="mailToday" value="1" onclick="javascript:mailTodayChecked('mailToday','findTime');">今日邮件
				</td>
				<td class="InputAreaCell" onclick="javascript:setCheckedQueryValueToOne('unreadMail');">
					<input type="checkbox" name="unreadMail" value="2" onclick="javascript:setCheckedQueryValueToOne('unreadMail');">未读邮件
				</td>
                </tr>
                <tr class=InputFrameLine onclick="javacript:event.cancelBubble = true;" align="center">
				<td class="InputAreaCell" onclick="javascript:setCheckedQueryValueToOne('unergenceMail');">
					<input type="checkbox" name="unergenceMail" value="3" onclick="javascript:setCheckedQueryValueToOne('unergenceMail');">紧急邮件
				</td>
				<td class="InputAreaCell" onclick="javascript:setCheckedQueryValueToOne('exterioMail');">
					<input type="checkbox" name="exterioMail" value="3" onclick="javascript:setCheckedQueryValueToOne('exterioMail');">外部邮件
				</td>
                </tr>
		<tr class=InputFrameButtonLine onclick="javacript:event.cancelBubble = true;">
		 	<td height="23" colspan="4" align="center">
            			<input type="button" class="button" onclick="javascript:doSearch();" value="确定" id=button3 name=button3>
				<input type="button" class="button" onclick="javascript:doReset();" value="重置" id=button3 name=button3>
				<input type="button" class="button" onclick="javascript:hideMenu('queryMenu');" value="取消" id=button3 name=button3>
          		</td>
		</tr>
        </table>
     </td>
    </tr>
</table>
   </div>
</div>
<SCRIPT LANGUAGE="JavaScript">
<!--
var strWhere = ' where 1=1 ';
function doReset() {
    document.all['findName'].value = "";
    document.all['findMailTitle'].value = "";
    document.all['findTime'].value = "";
    document.all['mailToday'].checked = false;
    document.all['unreadMail'].checked = false;
    document.all['unergenceMail'].checked = false;
}
function replaceSingleQuote(nameValue) {
    var qoute = /'/g;
    var name = nameValue.replace(qoute, "''" );
    return name;
}
function findNameChange(objName) {
    var nameValue = document.all[objName].value
    var name = replaceSingleQuote(nameValue);
    document.all[objName + 'Query'].value = name;
    //alert('findNameChange : ' + document.all[objName + 'Query'].value);
}
function findMailTitleChange(objName) {
    var nameValue = document.all[objName].value
    var name = replaceSingleQuote(nameValue);
    document.all[objName + 'Query'].value = name;
    //alert('findMailTitleChange : ' + document.all[objName + 'Query'].value);
}
function findTimeChange(objName,exceptObjName) {
    if(event.propertyName == 'value') {
   	 var today = new Date();
   	 //alert(document.all[objName].value);
    	if(isDate(document.all[objName].value) == false) {
  	      alert("日期输入错误！");
             document.all[objName].value = getDateStr(today);
   	     return false;
 	   }
 	   var selectVaue = date2Long(document.all[objName].value);
	    var todayValue = date2Long(getDateStr(today));
	    //alert("select  is " + selectVaue + "\n" + "today is " + todayValue)
	    if(document.all[objName].value.length > 0) {
	        document.all[exceptObjName].disabled = true;
	    } else {
       		  document.all[exceptObjName].disabled = false;
 	   }
   	 if(selectVaue > todayValue) {
 	       alert("请选择今天以前的日期！");
 	   	document.all[objName].value = getDateStr(today);
 	   }
    	document.all[objName + 'Query'].value = document.all[objName].value;
  	 // alert('findTimeChange : ' + document.all[objName + 'Query'].value);
    }
}
function setcheckbox(name) {
	var checked = true;
	//alert('name is ' + name);
	if(document.all[name].checked == true) {
		checked = false;
	}
	//alert('checked : ' + checked);
	document.all[name].checked = checked;
        return checked;
}
function mailTodayChecked(objName,exceptObjName) {
    var today = new Date();
    //alert(getDateStr(today));
    if(document.all[objName].disabled) {
        return false;
    }

    if(setcheckbox(objName)) {
        document.all[exceptObjName].disabled = true;
        document.all[exceptObjName].value = "";
        //document.all[exceptObjName + 'Query'].value = "";
    	//alert('mailTodayChecked\n' + 'Today is ' + getDateStr(today));
    } else {
        document.all[exceptObjName].disabled = false;
    }
    document.all[exceptObjName + 'Query'].value = getDateStr(today);
    //alert("mailTodayChecked " +exceptObjName + "Query : " + document.all[exceptObjName + 'Query'].value);
}
function setCheckedQueryValueToOne(objName) {
    if(setcheckbox(objName)) {
        document.all[objName + 'Query'].value = "1";
    }else {
        document.all[objName + 'Query'].value = "";
    }
    //var queryUnreadMail = " read_flag = '1' ";
    //document.all[objName + 'Query'].value = queryUnreadMail;
    //alert('unreadMailChecked : ' + document.all[objName + 'Query'].value);
}
function doSearch() {
    /*if(document.all['findTime'].value == ""
    && document.all['mailTodayQuery'].checked == false ) {
        document.all['findTimeQuery'].value = "";
    }*/
    /*alert('doSearch\n'
    + document.all['findNameQuery'].value + "\n -"
    + document.all['findMailTitleQuery'].value + "\n -"
    + document.all['findTimeQuery'].value + "\n -"
    + document.all['mailTodayQuery'].value + "\n -"
    + document.all['unreadMailQuery'].value + "\n -"
    + document.all['unergenceMailQuery'].value +"\n -");*/
    var frmObj = document.forms[0];
    frmObj.action = frmObj.action + "?search=true" ;
    frmObj.submit();
}
function getDateStr(objDate) {
    var year = objDate.getYear();
    var monthValue = objDate.getMonth() +1;
    var month = monthValue > 9 ? monthValue : '0' + monthValue;
    var date = objDate.getDate() > 9 ? objDate.getDate() : '0' + objDate.getDate();
    var dateStr = year + "-" + month + "-" + date
    return dateStr;
}
function init() {
    if(document.all['findNameQuery'].value != "" ) {
        document.all['findName'].value = document.all['findNameQuery'].value;
    }
    if(document.all['findMailTitleQuery'].value != "" ) {
        document.all['findMailTitle'].value = document.all['findMailTitleQuery'].value;
    }
    if(document.all['findTimeQuery'].value != "" ) {
        document.all['findTime'].value = document.all['findTimeQuery'].value;
        document.all['findTime'].disabled = false;
        document.all['mailToday'].checked = false;
    }
    if(document.all['mailTodayQuery'].value != "" ) {
        document.all['mailToday'].checked = true;
        document.all['findTime'].disabled = true;
    }
    if(document.all['unreadMailQuery'].value != "" ) {
        document.all['unreadMail'].checked = true;
    }
    if(document.all['unergenceMailQuery'].value != "" ) {
        document.all['unergenceMail'].checked = true;
    }
}
//-->
</SCRIPT>
