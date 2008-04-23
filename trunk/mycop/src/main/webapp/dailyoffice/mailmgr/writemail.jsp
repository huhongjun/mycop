<%@ page contentType="text/html; charset=gb2312" %>
<%@ page language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.vo.Pop3ConfigVO"%>
<%@ page import="com.gever.goa.dailyoffice.mailmgr.dao.MailConstant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html>
<title>写邮件</title>
<%
	String context =request.getContextPath();
	int SAVE_POST_MAIL = MailConstant.SAVE_POST_MAIL;
	int RETO_ME_MAIL = MailConstant.RETO_ME_MAIL;
	int SAVE_ONLY_MAIL = MailConstant.SAVE_ONLY_MAIL;
	int ADD_SIGN = MailConstant.ADD_SIGN;
        String draftFolder = MailConstant.DIR_DRAFT_FOLDER;
        String recievingFolder = MailConstant.DIR_RECIEVE_FOLDER;
        String downLoadTool = "/util/downloadfile.jsp";
        String downLoadPathParmName = "filepath=";
        String downLoadFileParmName = "filename=";
%>

<!--HTML模板资源-->
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/toolbar.css">
<link rel="stylesheet" href="<%=context%>/common/htmleditor/css/editor.css">
<script src="<%=context%>/common/htmleditor/js/toolbar.js"></script>
<script src="<%=context%>/common/htmleditor/js/editor.js"></script>
<script src="<%=context%>/common/htmleditor/js/colorpicker.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<logic:notEqual name="MailMgrForm" property="curerentMailType" value="exterioMail">
<jsp:include page="/menselect/inituserinfo.do"/>
</logic:notEqual>

<script language="JavaScript" type="text/JavaScript">
var rowsCount = 0;//3;
var actionType = 0;
var saveToDraft = <%=SAVE_ONLY_MAIL%>;
var contentLength = 250000;
var delfiles = new Array();
var editorWidth = 670;
var editorHeight = 330;
	if ( (screen.width == 800) && (screen.height == 600) ) {
		editorWidth = 550 ;
		editorHeight = 250;
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
		tmpObj=obj.parentElement;
		obj=tmpObj;
	//}
        var body = document.body;
        var top = ylib_getPageY(obj)+obj.offsetHeight;
        if(top - body.scrollTop + 80 > body.clientHeight ) {
            top = top - 108;
     	<logic:equal name="MailMgrForm" property="curerentMailType" value="exterioMail">
		top = top + 20;
     	</logic:equal>
        }
        if(document.all) menu.style.width = obj.offsetWidth+'px';
        menu.style.top = top +'px';
        menu.style.left = ylib_getPageX(obj)+'px';
        var menuHeight = menu.clientHeight;
        /*alert("menu top : " + menu.style.top
             + "\n menu left : " + menu.style.left
             + "\n menu height : " + menuHeight
             + "\n menu offsetTop : " + menu.offsetTop
             + "\n body height : " + body.clientHeight
             + "\n body scrollTop : " + body.scrollTop
             + "\n menu current top : " + (top - body.scrollTop));*/
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
	hideMenu();
}
function hideMenu(){
	var menu=document.all.menu;
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
function setcheckbox(name) {
	var checked = true;
	//alert('name is ' + name);
	if(document.all[name].checked == true) {
		checked = false;
	}
	//alert('checked : ' + checked);
		document.all[name].checked = checked;
	setActionType(name);
}
function setActionType(name) {
	if(document.all[name].checked == false) {
		actionType = actionType - (document.all[name].value - 0);
	} if(document.all[name].checked == true) {
		actionType = actionType + (document.all[name].value - 0);
	}
		//alert('check value is ' + document.all[name].value + '\n actionType is ' + actionType);
		document.all.mailActionType.value = actionType;

}
var oTable;
var oRow;
var oCell;
function addRow(tableName) {
	oTable = document.all[tableName];
	rowsCount++;
	if(rowsCount < 20) {
		oRow = oTable.insertRow();
		oRow.id = tableName + rowsCount;
		oCell = oRow.insertCell(0);
		oCell.innerHTML = '&nbsp;';
		oCell = oRow.insertCell(1);
		oCell.innerHTML = '<span class="InputLabelCell">附件：</span>';
		oCell = oRow.insertCell(2);
		oCell.innerHTML = getCellInnerHTML(tableName);
	}
}
function getCellInnerHTML(tableName){
 	var innerHTML = "<input type='file' class='input2' name='theFile"+ rowsCount +"'  style='width:400'  property='attach'><img src='../../images/delete.gif' alt='删除该附件' onclick=\"deleteRow('"+ tableName + "'," + rowsCount +")\">";
	//alert('innerHTML is \n' + innerHTML);
	return innerHTML;
}
function deleteRow(tableName,index) {
	var id = tableName + index;
	oTable = document.all[tableName];
	//alert('id is ' + id);
	oTable.deleteRow(document.all[id].rowIndex);
	rowsCount--;
}
function setContent(value) {
	document.all['mailVo.content'].value = value;
}
function setFileElements(typeName) {
	var elements = document.forms[0].elements;
	var tempStr = "";
	//alert('elements length is ' + elements.length);
	for(var i = 0 ; i < elements.length ; i ++) {
		//alert('element type is ' + elements[i].type);
		if(elements[i].type == typeName) {
			//alert('element name is ' + element.name);
			tempStr += elements[i].name + ","
		}
	}
	//alert('fileElements is ' + tempStr);

	document.all['fileElements'].value = tempStr.substr(0,tempStr.length-1);
}
function chekReciever() {
    if(document.all['mailVo.receive_address'].value.length > 0) {
        return true;
    } else if(document.all['mailVo.copy_send'].value.length > 0) {
        return true;
    } else if(document.all['mailVo.dense_send'].value.length > 0) {
        return true;
    } else {
        alert('请选择收件人');
        return false;
    }
}
function checkContentLength() {
    var content = e1.getValue();
    if(content.length > contentLength) {
        alert('邮件正文内容超过最大长度限定！');
        content = content.substr(0,contentLength);
        e1.setValue(content);
    }
    return content;
}
var processType = "sending";
function setSaveToDraft(mform) {
    processType = "saving";
    actionType += saveToDraft;
    document.all.mailActionType.value = actionType;
    setContent(checkContentLength());
    setFileElements('file');
    if (validater(mform) == false){
    	return false;
    }
    //alert("priority value : " + document.all['mailVo.priority'].value);
    document.all['mailDirId'].value = <%=draftFolder%>;
    var sForm = document.forms[0];
    var sAction = sForm.action;
    sForm.elements["deleteFiles"].value = delfiles.join(",");
    if (sForm.elements["actionType"].value=="modify"){
    	sAction = addPara(sAction, "action=modifyDraft");
    } else {
    	sAction = addPara(sAction, "action=sendMail");
    }
    sAction = addPara(sAction, "saveToDraft=true");
    //alert('action is ' + sAction);
    //alert('next dir id  is ' + document.all['mailDirId'].value);
    sForm.action = sAction;
    //sForm.submit();
    doSubmit(sForm);
}
function doSubmit(form){
    //alert('doSubmit');
    if(processType == 'saving') {
        document.all['processingStr'].innerHTML = "&nbsp;&nbsp;邮件正在保存, 请稍候...&nbsp;&nbsp;";
    }
    processing.style.visibility="visible";
    //var url = "processingdialog.jsp";
    //var vReturnValue = window.showModelessDialog(url,"","dialogHeight: 320px; dialogWidth: 450px; center: Yes; help: no; resizable: no; status: no;");
    disableBtn();
    form.submit();
}
function disableBtn(){
    var elements = document.forms[0].elements;
    for(var idx = 0 ; idx < elements.length ; idx++) {
        if(elements[idx].type == "button") {
            elements[idx].disabled = true;
        }
    }
}
function doAction(mform){
	setContent(checkContentLength());
	setFileElements('file');
	var sForm = document.forms[0];
     <logic:equal name="MailMgrForm" property="curerentMailType" value="exterioMail">
        if ((sForm.elements["smtpState"].value == "") || (sForm.elements["smtpState"].value=="noserver")){
            alert("没有选择smtp帐号,不允许发外面的邮件");
            return ;
	}
    </logic:equal>
        if(chekReciever() == false) {
            return false;
        }
	if (validater(mform) == false){
		return false;
	}
    document.all['mailDirId'].value = <%=recievingFolder%>;
	var sAction = sForm.action;
	var bRet = true;
	sForm.elements["deleteFiles"].value = delfiles.join(",");
	if (sForm.elements["actionType"].value=="modify"){
		sAction = addPara(sAction, "action=modifyDraft");
	} else {
		sAction = addPara(sAction, "action=sendMail");
	}
        //alert('action is ' + sAction);
	sForm.action = sAction;
	if(!isEmptyTitle()){
		return ;
	}
	//sForm.submit();
    doSubmit(sForm);

}

//add by lihadong

function isEmptyTitle(){
	var sform=document.forms[0];
	if(sform.elements("mailVo.title").value==""){
		var retVal=confirm("您将要发送一封没有主题邮件，确定？");
		if(retVal){
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
}

function setE1() {
	var value = document.all['mailVo.content'].value;
	e1.setValue(value);
}
function setE2() {
	var value = document.all['mailVo.originalContent'].value;
	e2.setValue(value);
}
function resetForm() {
    e1.setValue('');
}
 var mailType = 0 ;
     <logic:equal name="MailMgrForm" property="curerentMailType" value="exterioMail">
 mailType = 1;
    </logic:equal>
function showOriginalContentArea() {
    	var flag =false;
	flag = document.getElementById("showOriginalContent").checked
	if (flag==true) {
	    document.getElementById("originalContentArea").style.display = "none"
	} else {
	    document.getElementById("originalContentArea").style.display = "block"
	}
}
function initSendMailSet() {
    /*(" post_flag is " + document.all['mailInfo.post_flag'].value
          + "\n sign_flag is " + document.all['mailInfo.sign_flag'].value
          + "\n return_flag is " + document.all['mailInfo.return_flag'].value)*/
    if(document.all['mailInfo.post_flag'].value == '1' ) {
        document.all['post_flag'].checked = true;
	setActionType('post_flag');
    } if(document.all['mailInfo.sign_flag'].value == '1' ) {
        document.all['sign_flag'].checked = true;
	setActionType('sign_flag');
    } if(document.all['mailInfo.mail_level'].value != '') {
	document.all['mailVo.priority'].value = document.all['mailInfo.mail_level'].value;
    }
<logic:notEqual name="MailMgrForm" property="curerentMailType" value="exterioMail">
    if(document.all['mailInfo.return_flag'].value == '1' ) {
        document.all['return_flag'].checked = true;
	setActionType('return_flag');
    }
</logic:notEqual>
    //alert(" init action type is " + document.all.mailActionType.value)
}
function disableInputReceivers() {
    var frmObj = document.forms[0];
    for(var idx = 0 ; idx < frmObj.elements.length ; idx ++) {
        var elObj = frmObj.elements[idx];
        if(elObj.type == "text" && elObj.alt == "selectMan" ) {
            frmObj.elements[idx].disabled = true;
        }
    }
}
function init() {
	setE1();
        for(var idx = 1 ; idx < 4 ; idx ++) {
            addRow('fileUploadTable');
        }
<logic:notEmpty name="MailMgrForm" property="mailVo.originalContent">
        setE2()
	e2.setReadOnly();
</logic:notEmpty>
<logic:equal name="MailMgrForm" property="curerentMailType" value="exterioMail">
        disableInputReceivers();
</logic:equal>
        initSendMailSet();
}
	function getOptText(name){
		var obj = document.all("down");
		var value = obj.value;
		for (var idx= obj.options.length-1 ; idx>=0 ; idx--){
			if (value == obj.options(idx).value){;
				return obj.options(idx).text;
			}
		}
		return "";
	}
	function showAttch(name){
		var filename = getOptText(name);
		//alert(filename);
		var filePath = document.all("down").value; //得到附件路径
		var pos = filePath.indexOf("filepath=");
		if(pos<=0){
			return false;
		}
		var npos = filePath.indexOf("&sid=");
		 filePath = filePath.substring(pos+"filepath=".length,npos);
		// alert(filePath);

		//alert(filePath+" filename=" + filename);
		if (isShow(filePath) ){
			download3(filePath,filename);
		} else {
			//alert(filePath);
			document.frames["attch"].location.href ="<%=context%>/util/downloadfile.jsp?filepath="+filePath + "&filename=" +filename;
		}
	}
	function delAttch(name){
		var sourceObj = document.all(name);
		var value = document.all(name).value;
		for (j=sourceObj.options.length-1 ; j >=0 ; j--){
			if (sourceObj.options[j].value==value){
				var npos = value.indexOf("&sid=") + 5;
				delfiles[delfiles.length]=value.substring(npos);
				sourceObj.remove(j);
			}
		}
	}
	//黎海冬加
	var comma = ","
function checkValue(name,id) {
    var names = new Array();
    var namestr = document.all[name].value;
    //alert('+++++ ' + namestr);
    var namelen = document.all[name].value.length;
    var infoArray = new Array();
    var j = 0;
    var lastCommaPos = namestr.lastIndexOf(comma);
    while(lastCommaPos == namestr.length) {
    	namestr = namestr.substring(0,lastCommaPos);
   	lastCommaPos = namestr.lastIndexOf(comma);
   }
    //alert('--- namestr -- ' + namestr);
    names = namestr.split(',');
    for(user in userArray) {
		for(var i = 0 ; i < names.length ; i++) {
			if(userArray[user].isMatch('name_equal',names[i])) {
				infoArray[j] = userArray[user];
				j++;
				break;
			}
		}
	}
	var nameary = new Array();
	var idary = new Array();
	for(info in infoArray) {
		nameary[info] = infoArray[info].name;
		idary[info] = infoArray[info].id;
	}
	//alert('infoArray.length is ' + infoArray.length);
    var idstr = document.all[id].value;
    namestr = nameary.join(comma);
    idstr = idary.join(comma);
    if(namestr.length > 0) {
        namestr = namestr + comma
    }
    document.all[name].value = namestr;
    document.all[id].value = idstr;
    //alert('name is ' + namestr + '\n id is ' + idstr);
}
</script>

</head>

<body class="bodybg" onLoad="javascript:init();">
 <html:form action="/dailyoffice/mailmgr/writemail" method="post" enctype="multipart/form-data">
<table width="92%" border="0" align="center" cellpadding="2" cellspacing="1">
						<html:hidden property="actionType" />
		  				<html:hidden property="mailActionType" />
		  				<html:hidden property="fileElements" />
                        <html:hidden property="mailDirId"/>
                        <html:hidden property="deleteFiles" />
	
	<tr>
		  <td width="41"><span class="f14">
		  				
						 </span></td>
		  <td class="f14" nowrap>发送<logic:notEqual name="MailMgrForm" property="curerentMailType" value="exterioMail">内
</logic:notEqual><logic:equal name="MailMgrForm" property="curerentMailType" value="exterioMail">外</logic:equal>部邮件</td>
                  <td colspan="2" class="f14">
			 <table width="100" height="22" border="0" cellpadding="1" cellspacing="1">
				<tr align="center">
				  <td width="43">
                                      <goa:button styleClass="button" operation="YJGL.OPT" property="send" value="发送" onclick="javascript:doAction(MailMgrForm);"/></td>
				  <td><goa:button styleClass="button" operation="YJGL.OPT" property="cmdAddAttc" value="增加附件" onclick="javascript:addRow('fileUploadTable');"/></td>
				  <td><goa:button property="save" operation="YJGL.OPT" value="保存草稿" onclick="javascript:setSaveToDraft(MailMgrForm);"/></td>
				  <td>
					<goa:button operation="YJGL.OPT" styleClass="button" property="mailset"   value="邮件设置" onclick="javascript:showMenu('menu');"/>
                                  </td>
				  <td>
					<goa:button property="reset" styleClass="button" value="重写邮件" onclick="javascript:resetForm();" operation="YJGL.OPT"/>
                                  </td>
                                 <td>
					<goa:button styleClass="button" property="back"   value="返回" onclick="javascript:history.back();"/>
                                  </td>
				</tr>
			  </table>
                  </td>
    </tr>
    <!--tr>
		  <td>&nbsp;</td>
		  <td colspan="3" class="f12"><hr size="1"></td>
    </tr-->
<logic:notEqual name="MailMgrForm" property="curerentMailType" value="exterioMail">
    <tr>
		  <td>&nbsp;</td>
		<td width="60" nowrap class="InputLabelCell">
                    <input name="receiver" type="button" class="button" onClick="javascript:selectMailMan(mailType);" value="收件人:" elname="mailVo.receive_address,mailVo.receive_address_name,mailVo.copy_send,mailVo.copy_send_name,mailVo.dense_send,mailVo.dense_send_name">
                    </td>
		<td nowrap width="385">
                    <html:text styleClass="input2" property="mailVo.receive_address_name" size="80" alt="selectMan" onkeydown="javascipt:getUser('mailVo.receive_address_name','mailVo.receive_address');" onblur="javascript:checkValue('mailVo.receive_address_name','mailVo.receive_address');"/><html:hidden  property="mailVo.receive_address"/>
                    </td>
                <td width="168" align="left">
                &nbsp;
		</td>
    </tr>
    <tr>
		  <td>&nbsp;</td>
		<td nowrap class="InputLabelCell">
                    <input name="copysend" type="button" class="button" onClick="javascript:selectMailMan(mailType);" value="抄送人:" elname="mailVo.receive_address,mailVo.receive_address_name,mailVo.copy_send,mailVo.copy_send_name,mailVo.dense_send,mailVo.dense_send_name">
                    </td>
		<td nowrap ><html:text styleClass="input2" property="mailVo.copy_send_name" size="80" alt="selectMan" onkeydown="javascipt:getUser('mailVo.copy_send_name','mailVo.copy_send');" onblur="javascript:checkValue('mailVo.copy_send_name','mailVo.copy_send');"/>
					<html:hidden property="mailVo.copy_send"/>
                </td>
                <td>
                &nbsp;
    	</td>
    </tr>
    <tr>
		  <td>&nbsp;</td>
		<td nowrap class="InputLabelCell">
                    <input name="whisper" type="button" class="button" onClick="javascript:selectMailMan(mailType);" value="密送人:" elname="mailVo.receive_address,mailVo.receive_address_name,mailVo.copy_send,mailVo.copy_send_name,mailVo.dense_send,mailVo.dense_send_name">
                    </td>
		<td nowrap ><html:text styleClass="input2" property="mailVo.dense_send_name"  size="80" alt="selectMan" onkeydown="javascipt:getUser('mailVo.dense_send_name','mailVo.dense_send');" onblur="javascript:checkValue('mailVo.dense_send_name','mailVo.dense_send');"/>
					<html:hidden property="mailVo.dense_send"/>
                </td>
                <td>
			&nbsp;
		  </td>
    </tr>
</logic:notEqual>

<logic:equal name="MailMgrForm" property="curerentMailType" value="exterioMail">
<!--外部邮件-->
    <tr>
		  <td>&nbsp;</td>
		<td width="60" nowrap class="InputLabelCell">
                    <input name="receiver" type="button" class="button" onClick="javascript:selectMailMan(mailType);" value="收件人:" elname="mailVo.receive_address,mailVo.receive_address_name,mailVo.copy_send,mailVo.copy_send_name,mailVo.dense_send,mailVo.dense_send_name">
                    </td>
		<td nowrap width="385">
                    <html:text styleClass="input2" property="mailVo.receive_address_name" size="80" alt="selectMan" onkeydown="javascipt:getOutUser('mailVo.receive_address_name','mailVo.receive_address');" onblur="javascript:checkOutValue('mailVo.receive_address_name','mailVo.receive_address');"/><html:hidden  property="mailVo.receive_address"/>
                    </td>
                <td width="168" align="left">
                &nbsp;
		</td>
    </tr>
    <tr>
		  <td>&nbsp;</td>
		<td nowrap class="InputLabelCell">
                    <input name="copysend" type="button" class="button" onClick="javascript:selectMailMan(mailType);" value="抄送人:" elname="mailVo.receive_address,mailVo.receive_address_name,mailVo.copy_send,mailVo.copy_send_name,mailVo.dense_send,mailVo.dense_send_name">
                    </td>
		<td nowrap ><html:text styleClass="input2" property="mailVo.copy_send_name" size="80" alt="selectMan" onkeydown="javascipt:getOutUser('mailVo.copy_send_name','mailVo.copy_send');" onblur="javascript:checkOutValue('mailVo.copy_send_name','mailVo.copy_send');"/>
					<html:hidden property="mailVo.copy_send"/>
                </td>
                <td>
                &nbsp;
    	</td>
    </tr>
    <tr>
		  <td>&nbsp;</td>
		<td nowrap class="InputLabelCell">
                    <input name="whisper" type="button" class="button" onClick="javascript:selectMailMan(mailType);" value="密送人:" elname="mailVo.receive_address,mailVo.receive_address_name,mailVo.copy_send,mailVo.copy_send_name,mailVo.dense_send,mailVo.dense_send_name">
                    </td>
		<td nowrap ><html:text styleClass="input2" property="mailVo.dense_send_name"  size="80" alt="selectMan" onkeydown="javascipt:getOutUser('mailVo.dense_send_name','mailVo.dense_send');" onblur="javascript:checkOutValue('mailVo.dense_send_name','mailVo.dense_send');"/>
					<html:hidden property="mailVo.dense_send"/>
                </td>
                <td>
			&nbsp;
		  </td>
    </tr>

	<tr>
                  <td>&nbsp;</td>
                <td nowrap class="InputLabelCell">发送服务器:</td>
                <td nowrap >
                    <html:select property="smtpState" disabled="false" style="width=20%" styleClass="input2" onchange="return onChangeSmtp()" size="1">
				<html:option value="noserver">请选择</html:option>
				<html:options collection="pop3ConfigList" property="serial_num" labelProperty="pop3_name"/>
                    </html:select>
                    <input type ="text" name ="email" class="input2" readonly size ="40%" value ="">
                </td>
      		<td nowrap >&nbsp;</td>
    </tr>
    </logic:equal>

    <tr>
		  <td>&nbsp;</td>
		<td nowrap class="InputLabelCell">&nbsp;主&nbsp;题:</td>
		<td nowrap class="f12" ><html:text styleClass="input2" property="mailVo.title"   size="80" maxlength="250" /></td>
                <td nowrap class="f12" >&nbsp;</td>
    </tr>
    <tr>
		  <td>&nbsp;</td>
		<td nowrap colspan="3"><!--html:textarea property="mailVo.content"  maxlength="50000" cols="70" rows="10" style="bk" /-->
				<SCRIPT LANGUAGE="JavaScript">
				<!--
					var editor=new Editor("e1",editorWidth,editorHeight);
				//-->
				</SCRIPT>
				<html:hidden property="mailVo.content" />
				</td>
    </tr>
    <logic:notEmpty name="MailMgrForm" property="mailVo.originalContent">
        <tr>
		  <td>&nbsp;</td>
           <td width="100%" class="InputLabelCell" colspan="3">
               <label for="showOriginalContent"><input name="showOriginalContent" id="showOriginalContent" type="checkbox" onclick="javascript:showOriginalContentArea()">隐藏正文内容: </label>
            </td>
        </tr>
	<tr heigth="300" id="originalContentArea">
		  <td>&nbsp;</td>
	    <td colspan="3">
		<SCRIPT LANGUAGE="JavaScript">
		<!--
                	var editor=new Editor("e2",editorWidth,editorHeight);
		//-->
		</SCRIPT>
		<html:hidden property="mailVo.originalContent" />
		<html:hidden property="mailVo.attachInfo" />
            </td>
	</tr>
    </logic:notEmpty>
	<logic:empty name="MailMgrForm" property="mailVo.originalContent">
	<!-- add by zxy-->
	<!-- 防止另一窗口转发内容覆盖-->
	<html:hidden property="mailVo.originalContent" />
	<html:hidden property="mailVo.attachInfo" />
	</logic:empty>
				<!--tr>
				  <td colspan="4"><hr size="1"></td>
			   </tr-->
		  <tr>
		   <td colspan="4">
		   <table width="100%" id="fileUploadTable">
 <logic:greaterThan name="MailMgrForm" property="mailVo.attachcount" value="0">
    <tr class="f12">
		  <td width="41">&nbsp;</td>
          <td class="InputLabelCell">附件：</td>
          <td>
			  <SELECT NAME="down" style="width:200">

                <logic:iterate id="ps" name="MailMgrForm" property="mailVo.attachList" type="com.gever.goa.dailyoffice.mailmgr.vo.MailAttchVO" indexId="index">
                <%
				String strUrl = "";
				String strHref = context + downLoadTool + "?" + downLoadPathParmName
                   + ps.getFile_path() + "&sid=" + ps.getSerial_num();
				   strUrl = ps.getAttch_name();

				  %>
				  
				  <option value="<%=strHref%>"><%=ps.getAttch_name()%></option>
				  </logic:iterate>
				  </SELECT>
				  <html:button styleClass="button"  property="查看" onclick="showAttch('down')" value="查看"/>
				  <html:button styleClass="button"  property="删除" onclick="delAttch('down')" value="删除"/>
				  </td>
			  </tr>
</logic:greaterThan>
		   <!--tr>
		  <td width="41">&nbsp;</td>
			<td width="60" nowrap class="InputLabelCell">
			  附件：
			  </td>
				  <td valign="middle">
					<input class="input2" type="file" name="theFile1"  style="width:400" property="attach">
				  </td>
			   </tr>

			<tr>
		  <td>&nbsp;</td>
			<td nowrap class="InputLabelCell">
			  附件：
			  </td>
				  <td valign="middle">
					<input class="input2" type="file" name="theFile2"  style="width:400"  property="attach">
				  </td>
			   </tr>

			<tr>
		     <td>&nbsp;</td>
			<td nowrap class="InputLabelCell" >
			  附件：
			  </td>
				  <td valign="middle">
					<input class="input2" type="file" name="theFile3"  style="width:400"  property="attach">
				  </td>
			   </tr-->
			 </table>
			</td>
		  </tr>

				<!--tr>
				  <td colspan="4"><hr size="1"></td>
			   </tr-->
    <tr>
    	<td class="f12">&nbsp;</td>
    	<td class="f12">&nbsp;</td>
		<td colspan="2">
			 <table width="100" height="22" border="0" cellpadding="1" cellspacing="1">
			<tr align="center">
				  <td width="43">
                                      <goa:button styleClass="button" operation="YJGL.OPT" property="send" value="发送" onclick="javascript:doAction(MailMgrForm);"/></td>
				  <td><goa:button styleClass="button" operation="YJGL.OPT" property="cmdAddAttc" value="增加附件" onclick="javascript:addRow('fileUploadTable');"/></td>
				  <td><goa:button property="save" operation="YJGL.OPT" value="保存草稿" onclick="javascript:setSaveToDraft(MailMgrForm);"/></td>
				  <td>
					<goa:button operation="YJGL.OPT" styleClass="button" property="mailset"   value="邮件设置" onclick="javascript:showMenu('menu');"/>
                                  </td>
				  <td>
					<goa:button property="reset" styleClass="button" value="重写邮件" onclick="javascript:resetForm();" operation="YJGL.OPT"/>
                                  </td>
                                 <td>
					<goa:button styleClass="button" property="back"   value="返回" onclick="javascript:history.back();"/>
                                  </td>
				</tr>
			  </table>
		 </td>
    </tr>
	  <!--tr>
		<td>&nbsp;</td>
		<td colspan="3" class="f12">&nbsp;</td>
	  </tr-->
   </table>
<div  id="menu" style="position:absolute;left:400;top:900;display:none;width: 0; height: 0;background-color: #D5E7FA; border-style: ridge; border-width: 1" >
   <div>
	  <table height="" cellspacing="0" cellpadding="0">
     <logic:notEqual name="MailMgrForm" property="curerentMailType" value="exterioMail">
			<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td nowrap class="f12" onclick="javacript:setcheckbox('return_flag');event.cancelBubble = true;">
			<input type=checkbox name="return_flag" value="<%=RETO_ME_MAIL%>"onclick="javascript:setActionType('return_flag');event.cancelBubble = true;">
			<span>是否回执</span>
			</td>
			</tr>
      </logic:notEqual>
		<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td height="18" nowrap class="f12" onclick="javacript:setcheckbox('post_flag');event.cancelBubble = true;">
			<input type=checkbox name="post_flag" value="<%=SAVE_POST_MAIL%>" onclick="javascript:setActionType('post_flag');event.cancelBubble = true;">
                            <html:hidden property="mailInfo.post_flag"/>
			<span>保存到寄件夹</span></td></tr>
			<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td nowrap class="f12" onclick="javacript:setcheckbox('sign_flag');event.cancelBubble = true;">
			<input type=checkbox name="sign_flag" value="<%=ADD_SIGN%>"onclick="javascript:setActionType('sign_flag');event.cancelBubble = true;">
                            <html:hidden property="mailInfo.sign_flag"/>
			<span>加上个人签名</span>
			</td></tr>
                            <html:hidden property="mailInfo.return_flag"/>
			<tr   onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">
			<td nowrap class="f12" onclick="javacript:event.cancelBubble = true;">
			选择邮件级别:
			<!--select name="PRIORITYSelect" size=1  onChange="changePriority()" class="f12">
				<option value = 0>普通邮件</option>
				<option value =1>重要邮件</option>
				<option value =2>最普通邮件</option>
			</select-->
			   <html:hidden property="mailInfo.mail_level"/>
                        <html:select name="MailMgrForm" property="mailVo.priority" size="1" styleClass="f12">
                            <html:option value="0">
                                普通邮件
                            </html:option>
                            <html:option value="1">
                                重要邮件
                            </html:option>
                            <html:option value="2">
                                最普通邮件
                            </html:option>
                        </html:select>
			</td>
			</tr>
	  </table>
   </div>
</div>
<div id="processing" style="position:absolute; top:400; left:20; z-index:10; visibility:hidden">
	<table WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0">
	<tr>
	<td width="30%"></td>
	<td bgcolor="#0">
	<table WIDTH="100%" height="70" BORDER="0" CELLSPACING="2" CELLPADDING="0">
	<tr><td bgcolor="#eeeeee" align="center" class="f14" id="processingStr">&nbsp;&nbsp;邮件正在发送, 请稍候...&nbsp;&nbsp;</td></tr>
	</table>
	</td>
	<td width="30%"></td>
	</tr>
	</table>
</div>

   </html:form>
</html>
<iframe src="" name="attch" id="attch" style="display:none"></iframe>
<logic:equal name="MailMgrForm" property="curerentMailType" value="exterioMail">
<SCRIPT LANGUAGE="JavaScript">
<!--//zxy add 外部邮件
function getOutUser(name,id){
	return ;
}
function checkOutValue(name,id) {
	var comma = ","
    var names = new Array();
    var namestr = document.all[name].value;
    //alert('+++++ ' + namestr);
    var namelen = document.all[name].value.length;
    var j = 0;
    var lastCommaPos = namestr.lastIndexOf(comma);
    while(lastCommaPos == namestr.length-1) {
		if(lastCommaPos==-1){
			break;
		}
    	namestr = namestr.substring(0,lastCommaPos);
   		lastCommaPos = namestr.lastIndexOf(comma);
   }
    //alert('--- namestr -- ' + namestr);
    names = namestr.split(comma);
	var nameary = new Array();
	var idary = new Array();
	for(var i=0;i<names.length;i++){
		var user = _findUserByName(names[i]);
		if(user == null){
			nameary[i] = names[i];
			idary[i] = names[i];
		}else{
			nameary[i] = user.name;
			idary[i] = user.id;
		}
	}
   
    var idstr = document.all[id].value;
	//alert(idstr);
    namestr = nameary.join(comma);
    idstr = idary.join(comma);
	//alert(idstr);
    if(namestr.length > 0) {
        namestr = namestr + comma;
    }
    document.all[name].value = namestr;
    document.all[id].value = idstr;
    //alert('name is ' + namestr + '\n id is ' + idstr);
}
//
function _findUserByName(name){
	return null;
	/*
	 for(user in userArray) {
		for(var i = 0 ; i < names.length ; i++) {
			if(userArray[user].isMatch('name_equal',names[i])) {
				infoArray[j] = userArray[user];
				j++;
				break;
			}
		}
	}*/
}
//-->
</SCRIPT>


<SCRIPT LANGUAGE="JavaScript">
<!--
	function resetall(){
		document.all("smtpState").value="noserver";
		document.forms[0].elements["mailVo.priority"].value = "1";
	}
	function onChangeSmtp(){
		var smtpValue =  document.all("smtpState").value;
		var emailObj =  document.all("email");
		if (document.all("smtpState").value == "noserver"){
			emailObj.value=""
			return true
		}

		var iLength = 0;

		for (var idx =0; idx<ary.length; idx++){
			if (smtpValue == ary[idx][0]) {
				//alert("smtp email--> " +ary[idx][1]);
				emailObj.value = ary[idx][1];
			}
		}
	}
var ary=new Array();
	<%  //读出userIDList的资料,然后赋给javaScript中的一个数组变量当中
	    ArrayList aryList = (ArrayList)request.getAttribute("pop3ConfigList");
            System.out.println("----------- in page aryList size is " + aryList.size());
        %>
	    var lngLength =<%= aryList.size() %> ; //得到总记录数
	<%

		for (int idx=0; idx<aryList.size(); idx++){
			Pop3ConfigVO view= (Pop3ConfigVO)aryList.get(idx);
			out.println ("      ary[" + idx + "] = new Array();"); // 重新定义数组")

			out.println ("      ary[" + idx + "][0] = \"" + view.getSerial_num() +"\";"); //得到ID
			out.println ("      ary[" + idx + "][1] = \"" + view.getShow_address() +"\";"); //得到smtp
			view = null; //回收垃圾箱
		}

	%>

	resetall();

//-->
</SCRIPT>
</logic:equal>


