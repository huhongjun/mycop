<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<script src="<gdp:context/>/js/Validator.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户-修改</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="javascript" src="<gdp:context/>/js/gdp.js"></script>
<script language="javascript" src="<gdp:context/>/js/pub.js"></script>
</head>
<OBJECT classid="CLSID:EE762E71-C7D7-4761-9134-1316AC18E531"
   id=PkiCom3 codeBase="<gdp:context/>/jsp/login/clientcontrolpro.CAB#version=1,0,0,1"
   style="LEFT: 0px; TOP: 0px;display:none" VIEWASTEXT>
</OBJECT>
<SCRIPT LANGUAGE="JavaScript">
<!--
var context='<%=request.getContextPath()%>';
var keynet;
function selectOnChange(obj){
    if(obj.value=='1'){
        if (document.forms[0].usertype_temp.value != 1){
    	  
    	}
        document.all("pass").style.display='';
    	document.all("ca").style.display='none';
    }
    else {
       document.all("pass").style.display='none';
       document.all("ca").style.display='';
    }
}

function ok_click(){
    if(document.forms[0].usertype.value=="2"){
        document.forms[0].password.value=document.forms[0].keynet.value;
    }else{
        if(document.forms[0].password.value!=document.forms[0].repassword.value){
             alert("密码和确认密码不一样，请重新输入！");
             document.forms[0].password.focus();
             return false;
        }
    }
    return true;
}

function getDisaKeyID(){
     var pin=document.forms[0].pin.value;
     if ((pin==null) || (pin=="")){
          alert("你没有输入用户PIN码,请检查");
          return false;
     }

     ret=document.all("PkiCom3").OpkiInit();
     if (ret!=0) {
            alert("客户端初使化失败:"+ret);
            return false;
         }

     ret=document.all("PkiCom3").OpkiLogin(1,pin);
     if (ret!=0) {
               alert("你输入的PIN码不正确，请检查:"+ret);
               ret=document.all("PkiCom3").OpkiLogout();
               ret=document.all("PkiCom3").OpkiEnd();
               cancel_qr();
               return;
	 }
     ret=document.all("PkiCom3").OpkiReadLabel0("LAB_DISAID",3);
     if(ret!=0){
             alert("取用户DISAID失败，请检查!");
             ret=document.all("PkiCom3").OpkiEnd();
             return;
	}
     document.forms[0].password.value=document.all("PkiCom3").outData;

     ret=document.all("PkiCom3").OpkiLogout();
     if (ret!=0) {
             alert("pki disa logout error:"+ret);
             return;
         }
     ret=document.all("PkiCom3").OpkiEnd();
     if (ret!=0) {
             alert("pki end error:"+ret);
             return;
	}
     return true;
}

//-->
</SCRIPT>
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>

  <html:form action="/userAction.do">
<table width="480" border="0" align="center" cellpadding="2" cellspacing="1">
  <tr align="center">
    <td colspan="4" class="TableTitleText">修改用户</td>
  </tr>
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
   <tr>
   <html:hidden property="code" />
   <!-- <td nowrap class="InputLabelCell" >用户编码:</td>
    <td colspan="3" ><html:text property="code" size="30" styleClass="input2" maxlength="15" validatetype="notempty" msg="编码不能为空"/><font color="#FF0000">*</font></td>
    </td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >用户ID:</td>
    <td colspan="3" ><html:text property="id" size="30" styleClass="input2" validatetype="notempty" msg="用户ID为空" maxlength="15" readonly="true" /><font color="#FF0000">*</font></td>
  </tr>-->
  <html:hidden property="id" />
   <tr>
    <td nowrap class="InputLabelCell" >帐号:</td>
    <td colspan="3" ><html:text styleClass="input2" property="user" size="30" maxlength="15" validatetype="notempty" msg="帐号名不能为空" /><font color="#FF0000">*</font></td>
  </tr>
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

  <logic:notEqual name="UserForm" property="usertype" value="0">
   <tr>
    <td nowrap class="InputLabelCell" >类型:</td>
    <td colspan="3" >
	<html:select property="usertype"  onchange="selectOnChange(this)">
	  <html:option  value="1" >普通用户</html:option>
	  <html:option  value="2" >CA用户</html:option>
	</html:select></td>
    </td>
  </tr>
  </logic:notEqual>
	  <tbody id="pass" style="
	<logic:notEqual name="UserForm" property="usertype" value="1">display:none</logic:notEqual>
      ">
		 <tr>
		<td nowrap class="InputLabelCell" >密码:</td>
			<td colspan="3" ><html:password property="password" value="" size="30" maxlength="15" /></td>
		</tr>
		<tr>
		<td nowrap class="InputLabelCell" >确认密码:</td>
			<td colspan="3" ><html:password property="repassword" size="30" maxlength="15"  /></td>
		</tr>
  </tbody>

		<tbody id="ca" style="
	<logic:notEqual name="UserForm" property="usertype" value="2"> display:none</logic:notEqual>
        ">
		  <tr>
			<td nowrap class="InputLabelCell" >PIN:</td>
			<td colspan="3" ><input type="text" class="input2" name="pin" size="30" maxlength="15" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
  			<td  align="left">
  				<input type="button" class="button" onClick="getDisaKeyID()"  value="获得KEYNET">
  			</td>
			</tr>
		<tr>
			<td nowrap class="InputLabelCell" >KEYNET:</td>
			<td colspan="3" ><html:text property="keynet" size="30" maxlength="15" styleClass="input2" value=""/></td>
		</tr>
		</tbody>


  <logic:equal name="UserForm" property="usertype" value="1">
  <input type="hidden" name="usertype_temp" value="1"/>
  </logic:equal>
  <logic:equal name="UserForm" property="usertype" value="2">
  <input type="hidden" name="usertype_temp" value="2"/>
  </logic:equal>

 <logic:notEqual name="UserForm" property="usertype" value="0">
   <tr>
    <td nowrap class="InputLabelCell" >激活状态:</td>
    <td colspan="3" >
	<html:select property="status" >
	  <html:option  value="1">激活</html:option>
	  <html:option  value="0">不激活</html:option>
	</html:select>
	</td>
  </tr>
   <tr>
    <td nowrap class="InputLabelCell" >有效期:</td>
    <td colspan="3" ><html:text property="validdate" size="30" maxlength="15" readonly="true" styleClass="input2" onclick="getdate(this,context+'/js/',event)"/></td>
    </td>
  </tr>
 </logic:notEqual>
<html:hidden property="gender" />
  <!--<tr>
    <td nowrap class="InputLabelCell" >用户性别:</td>
    <td colspan="3" >
	<html:select property="gender" >
	  <html:option  value="1">男</html:option>
	  <html:option  value="2">女</html:option>
	</html:select>
	</td>
    </td>
  </tr>-->
  <tr>
    <td colspan="4" >&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;
	   <logic:equal name="UserForm" property="usertype" value="0">
			<html:hidden property="usertype" value="0" />
		</logic:equal>
	</td>
    <td colspan="3" >
      <html:button property="create" styleClass="button" value="确定"
	  onclick="return (validater(UserForm) && ok_click() && toAction(this,'update'))"/>&nbsp;<html:reset styleClass="button" property="reset" value="重置" />&nbsp;<html:submit styleClass="button" property="list" value="返回" onclick="toAction(this,'list')"/>
    </td>
  </tr>
</table>
</html:form>


</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
