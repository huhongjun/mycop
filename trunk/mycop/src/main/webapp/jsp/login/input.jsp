<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.gever.sysman.privilege.webapp.action.LoginAction" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>

<%
	String requestURL = request.getRequestURL().toString();
	String context = request.getContextPath();
	String normalPort = (String) request.getAttribute(LoginAction.NORMAL_PORT);
	String sslPort = (String) request.getAttribute(LoginAction.SSL_PORT);
	String remote = LoginAction.getRequestAddress(requestURL);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0"> 
</HEAD>
<SCRIPT LANGUAGE="JavaScript">
<!--
var ns = navigator.appName == "Netscape";
if(ns) document.all = document.getElementById;

function verys(){
    var ret;
    var pin;
    pin=document.forms[0].capassword.value;

    if ((pin==null) || (pin=="")) {
          alert("你没有输入用户PIN码,请检查");
          cancel_qr();
          return false;
       }

    systemstatus.innerText="正在验证，请稍候...";

    ret=document.all("PkiCom3").OpkiInit();
    if (ret!=0){
           alert("客户端初使化失败:"+ret);
           systemstatus.innerText="";
           return false;
        }

    ret=document.all("PkiCom3").OpkiLogin(1,pin);
    if (ret!=0) {
           alert("你输入的PIN码不正确，请检查:"+ret);
           systemstatus.innerText="";
           ret=document.all("PkiCom3").OpkiLogout();
           ret=document.all("PkiCom3").OpkiEnd();
           cancel_qr();
           return false;
         }

     ret=document.all("PkiCom3").OpkiReadLabel("LAB_DISAID",3);
   //ret=document.all("PkiCom3").OpkiReadLabel0("LAB_DISAID",3);
     if(ret!=0){
             alert("取用户DISAID失败，请检查!");
             systemstatus.innerText="";
             ret=document.all("PkiCom3").OpkiLogout();
             ret=document.all("PkiCom3").OpkiEnd();
             return false;
        }
    document.forms[0].disakeyid.value=document.all("PkiCom3").outData;
    document.all("PkiCom3").CleanOutData();

    ret=document.all("PkiCom3").OpkiReadLabel("LAB_USERCERT",1);
    if(ret!=0){
	     alert("取用户证书失败，请检查!");
             systemstatus.innerText="";
	     ret=document.all("PkiCom3").OpkiLogout();
	     ret=document.all("PkiCom3").OpkiEnd();
	     return false;
	  }
    var tData=document.all("PkiCom3").outData;
    var tDataLen=document.all("PkiCom3").outDataLen;

    document.forms[0].user_cert.value = tData;
    document.forms[0].user_pin.value=pin;
    ret=document.all("PkiCom3").OpkiLogout();
    if (ret!=0) {
        alert("pki disa logout error:"+ret);
           systemstatus.innerText="";
	      return false;
	  }
    ret=document.all("PkiCom3").OpkiEnd();
    if (ret!=0){
	    alert("pki end error:"+ret);
           systemstatus.innerText="";
	    return false;
	  }

    return true;
}

//-->
</SCRIPT>
<!--OBJECT classid="CLSID:EE762E71-C7D7-4761-9134-1316AC18E531"
   id=PkiCom3 codeBase="<%=request.getContextPath()%>/jsp/login/clientcontrolpro.CAB#version=1,0,0,1"
   style="LEFT: 0px; TOP: 0px;display:none" VIEWASTEXT>
</OBJECT-->
<!--OBJECT classid=CLSID:0DC9CA0C-ED42-4C87-A335-1B0BD043FC70 id=PkiCom3></OBJECT-->
<BODY onload="JavaScript:document.forms[0].name.focus();changeAction()">
<html:form method="post" action="/Login" onsubmit="return checkForm(this);">
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td align="center"> <table border="0" cellpadding="3" cellspacing="0">
                    <!--tr> 
                      <td align="right" nowrap class="InputLabelCell"><bean:message  key="gever.login.lable.pin"/>： </td>
                      <td><html:password  property="capassword" /></td>
                    </tr-->                
                    <tr>
                      <td align="right" nowrap class="InputLabelCell">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr> 
                      <td align="right" nowrap class="InputLabelCell"><bean:message key="gever.login.label.user"/>：</td>
                      <td><html:text property="name" />
                      </td>
                    </tr>
                    <tr> 
                      <td align="right" nowrap class="InputLabelCell"><bean:message  key="gever.login.lable.password"/>： </td>
                      <td><html:password  property="password" /></td>
                    </tr>
                    <tr> 
                      <td align="right">&nbsp;</td>
                      <td align="left" nowrap class="InputLabelCell"><input type="checkbox" name="isSSL" onClick="changeAction();"/>                        <bean:message  key="gever.login.lable.ssl"/></td>
                    </tr>
                    <tr>
                      <td class="InputLabelCell">&nbsp;</td>
                      <td valign="top">&nbsp;</td>
                    </tr>
                    <tr>
                      <td class="InputLabelCell">&nbsp;</td>
                      <td valign="top">&nbsp;</td>
                    </tr>
                    <tr> 
                      <td class="InputLabelCell">&nbsp;</td>
                      <td valign="top">
					    <table border="0" align="right" cellpadding="1" cellspacing="1">
                          <tr align="right"> 
                            <td><div align="right">
                              <input name="Submit" type="submit" class="button" onClick = "return checkAccount()" value=<bean:message key="gever.login.button.submit" />
</div></td>
                            <td><div align="right">
                              <input name="reset" type="reset" class="button" value=<bean:message key="gever.login.button.reset" />
						    </div></td>
                          </tr>
                        </table>
					  </td>
                    </tr>
                  </table></td>
              </tr>
     </table>

	<input type="hidden" name="user_pin" value="" maxlength="15" size="20">
	<input type="hidden" name="user_cert" value="" size="20">
	 
</html:form> 
</BODY>
</HTML>
<script language="javascript">
function checkForm(obj){
/******************************
  if (obj.capassword.value != ""){
    try{
      if (verys() == false){
        return false;
      }
    } catch (e){
      alert("登陆失败！");
      obj.capassword.value = "";
      return false; 
    }
    document.all["action"].value = "caLogin";
  }
*******************************/
  return true;
}
//Modified by eddy 20041118 --------------\\\
function checkAccount() {
   var curForm = document.forms[0];
   if (curForm.name.value.length < 1) {
       alert("请输入帐号！");
       curForm.name.focus();
       return false;
   } else {
       return true;
   }
}

function changeAction() {
	if (document.forms[0].isSSL.checked) {
		document.forms[0].action="https://<%=remote%>:<%=sslPort%><%=context%>/Login.do?action=login";
	} else {
		document.forms[0].action="http://<%=remote%>:<%=normalPort%><%=context%>/Login.do?action=login";
	}
}
//Modified by eddy 20041118 --------------///
</script>