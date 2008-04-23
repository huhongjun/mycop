<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page import="com.gever.sysman.level.dao.impl.DefaultLevelDAO"%>
<%@ page import="com.gever.sysman.level.dao.LevelDAO"%>
<%@ page import="com.gever.sysman.level.model.*"%>
<%@ page import="java.util.*"%>
<script src="<gdp:context/>/js/Validator.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户-新增</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<script language="javascript" src="<gdp:context/>/js/pub.js"></script>
</head>
<OBJECT classid="CLSID:EE762E71-C7D7-4761-9134-1316AC18E531"
   id=PkiCom3 codeBase="<gdp:context/>/jsp/login/clientcontrolpro.CAB#version=1,0,0,1"
   style="LEFT: 0px; TOP: 0px;display:none" VIEWASTEXT>
</OBJECT>
<OBJECT classid=CLSID:0DC9CA0C-ED42-4C87-A335-1B0BD043FC70 id=PSIAtv style="display:none"></OBJECT>
<SCRIPT LANGUAGE="JavaScript">
<!--
var context='<%=request.getContextPath()%>';
function selectOnChange(obj){
    if(obj.value=='1'){
    	pass.style.display='';
    	ca.style.display='none';

    }
    else {
       pass.style.display='none';
       ca.style.display='';

    }
}


function isValid(){
  if(document.forms[0].password.value!=document.forms[0].repassword.value){
        alert("两次输入的密码不相同!");
	return false;
    }

}

function ok_click(){
    if(document.forms[0].userType.value=="2"){
        document.forms[0].password.value=document.forms[0].keynet.value;
    }else{
        if(document.forms[0].password.value!=document.forms[0].repassword.value){
             alert("密码和确认密码不一样，请重新输入！");
             document.forms[0].password.focus();
             return false;
        }
    }
}

function _endAtv(){
	var ret;
	ret = PSIAtv.Logout();
	 if (!ret) {
             alert("logout error:"+ret);
             return false;
         }
	 ret = PSIAtv.AtvEnd();
     if (ret != 0) {
             alert("end error:"+ret);
             return false;
	}
	return true;
}

function getDisaKeyID(){
     var pin=document.forms[0].pin.value;
     if ((pin==null) || (pin=="")){
          alert("你没有输入用户PIN码,请检查");
          return false;
     }

     var PSIAtv = document.all("PSIAtv");
	 ret = PSIAtv.AtvInit();
	 if(ret != 0){
		alert("客户端控件初始化失败！error:"+ret);
		return false;
	 }
	 
	ret = PSIAtv.Login(1,pin);
    if (!ret){
	    alert("客户端登陆失败！ error:"+ret);
		_endAtv();
	    return false;
	}

	var user_cert="";
	var certType = 2;//签名证书
	user_cert = PSIAtv.GetCert(certType);
	if(user_cert =="")
    {
        alert("证书信息读取失败，请插入硬件介质！");
		_endAtv();
        return false;
	}
	var user_cert_info = PSIAtv.GetCertInfo(user_cert);
	alert(user_cert_info);
	if(user_cert_info==""){
		alert("解析证书信息失败！");
		_endAtv();
		return false;
	}

	var indx1 = user_cert_info.indexOf("<serialnum>");
    var indx2 = user_cert_info.indexOf("</serialnum>");
    var Certsn = user_cert_info.substring(indx1 + 11, indx2);
	alert(Certsn);
    document.forms[0].keynet.value=Certsn;

     return true;

}
//******************************************************************
function doBack(){
    if(navigator.appName == "Microsoft Internet Explorer"){
        history.back();
        return;
    }
    var moduleform = document.forms[0];
    var action="";
    action += "<gdp:context/>/privilege/userAction.do?action=list";

    moduleform.action = action;
    moduleform.submit();
}
//******************************************************************
//-->

</SCRIPT>


<body class="bodybg" onload="JavaScript:document.forms[0].user.focus();">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<logic:messagesPresent>
   <bean:message key="errors.header"/>
   <ul>
   <html:messages id="error">
      <li><bean:write name="error"/></li>
   </html:messages>
   </ul><hr>
</logic:messagesPresent>
<html:form action="/userAction.do?action=create" onsubmit="return ok_click();">
<table width="480" border="0" align="center" cellpadding="2" cellspacing="1">
  <tr align="center">
    <td colspan="4" class="TableTitleText">新增用户</td>
  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >帐号:</td>
    <td colspan="3" ><html:text styleClass="input2" property="user" size="30" maxlength="15" validatetype="notempty" msg="帐号名不能为空" /><font color="#FF0000">*</font></td>
  </tr>
 <!-- <tr>
    <td nowrap class="InputLabelCell" >用户编码:</td>
    <td colspan="3" ><html:text property="code" size="30" styleClass="input2" maxlength="15" validatetype="notempty" msg="编码不能为空"/><font color="#FF0000">*</font></td>
    </td>
  </tr>-->
   <tr>
    <td nowrap class="InputLabelCell" >姓名:</td>
    <td colspan="3" ><html:text property="name" size="30" maxlength="15" styleClass="input2" validatetype="notempty" msg="姓名不能为空"/><font color="#FF0000">*</font></td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >用户行政级别:</td>
    <td colspan="3" ><html:select property="level" style="width:150px;" >
	                 <html:options collection="options" property="value" labelProperty="label"/>
					  </html:select></td>
  </tr>
   <!--<tr>
    <td nowrap class="InputLabelCell" >性别:</td>
    <td colspan="3" >
	<html:select property="gender" value="0">
	  <html:option  value="1">男</html:option>
	  <html:option  value="2">女</html:option>
	</html:select>
	</td>
    </td>
  </tr>-->
  <tr>
    <td nowrap class="InputLabelCell" >类型:</td>
    <td colspan="3" >
	<html:select property="userType" value="0" onchange="selectOnChange(this)">
	  <html:option  value="1" >普通用户</html:option>
	  <html:option  value="2" >CA用户</html:option>
	</html:select></td>
    </td>
  </tr>
  </tr>
   <tbody id="pass" style="display:''">
   <tr>
    <td nowrap class="InputLabelCell" >密码:</td>
    <td colspan="3" ><html:password property="password" styleClass="input2" size="30" maxlength="15" /></td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >确认密码:</td>
    <td colspan="3" ><html:password property="repassword"  styleClass="input2" size="30" maxlength="15"/></td>
  </tr>
  </tbody>
  <tbody id="ca" style="display:none">
   <tr>
    <td nowrap class="InputLabelCell" >PIN:</td>
    <td colspan="3" ><input type="text" name="pin" class="input2" size="30" maxlength="15" /></td>
  </tr>
  <tr>
   <td>&nbsp;</td>
  	<td  align="left">
  	  <input type="button" class="button" onClick="getDisaKeyID()"  value="获得KEYNET">
  	</td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >KEYNET:</td>
    <td colspan="3" ><input type="text" name="keynet" class="input2" size="30" maxlength="15" /></td>
  </tr>
  </tbody>
  <tr>
    <td nowrap class="InputLabelCell" >激活状态:</td>
    <td colspan="3" >
	<html:select property="status" value="0" >
	  <html:option  value="1">激活</html:option>
	  <html:option  value="0">不激活</html:option>
	</html:select>
	</td>
    </td>
  </tr>
  <tr>
    <td nowrap class="InputLabelCell" >有效期:</td>
    <td colspan="3" ><html:text property="validdate" styleClass="input2"  size="30" maxlength="15" readonly="true" onclick="getdate(this,context+'/js/',event)"/></td>
    </td>
  </tr>

  <tr>
    <td colspan="4" >&nbsp;
	<html:hidden property="level" />
  </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3" >
      <html:submit property="create" value="确定" styleClass="button" onclick="return (validater(UserForm))"/>&nbsp;<html:reset property="reset" styleClass="button" value="重置" />&nbsp;<html:button property="list" styleClass="button" value="返回" onclick="javascript:doBack();"/>
    </td>
  </tr>
</table>
</html:form>
<html:javascript  formName="userForm" dynamicJavascript="true" staticJavascript="true" />
</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
