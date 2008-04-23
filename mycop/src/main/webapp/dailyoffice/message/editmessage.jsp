<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>

<%String context =request.getContextPath();%>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function chekReciever() {
    if(document.all['vo.receiver'].value.length > 0) {
        return true;
    } else {
        alert('请选择收件人!');
        return false;
    }  
}
function chekContent() {
     var content=document.all['vo.content'].value.trim();
  
    if(content.length > 0) {
        return true;
    } else {
        alert('请填写短消息内容!');
        return false;
    }  
}
//保存
function doSaveExit(mform){
	if (validater(mform) == false){
		return false;
	}
	var sForm = document.forms[0];
	var sAction = sForm.action;
	var bRet = true;
	try{
		bRet = checkInput() ;
		if (bRet ==false){
			return false;
		}
	}catch(e){
	}

	if (sForm.elements["actionType"].value=="modify"){
		sAction = addPara(sAction, "action=modify");
	} else {
		sAction = addPara(sAction, "action=add");
	}
	if(chekReciever() == false) {
            return false;
        }
        if(chekContent() == false) {
            return false;
        }
	sForm.action = sAction;
	sForm.submit();

}


//-->
</SCRIPT>
<body class="bodybg">
<html:form action="/dailyoffice/message/editmessage" method="post" enctype="multipart/form-data">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">短信息-新增</span></td>
      </tr>
      <tr> <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td></tr>
      <tr> <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td>
            <td>&nbsp;&nbsp;&nbsp;</td></tr>
      <tr></tr>
      <tr></tr>
      <tr> 
        <td>&nbsp;<html:hidden name="MessageForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
				</tr> 
                  			  <tr class="listcellrow">	
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                  			  <td>&nbsp;&nbsp;&nbsp;</td>
                                            <td nowrap class="InputLabelCell">人员选择：</td>
                                              <td width="86" colspan="1">
						  <textarea class="input2" name="vo.receivername" cols="45" rows="2" ><bean:write   name="MessageForm" property="vo.receivername" filter="true"/>
						  </textarea>
						  <html:hidden name="MessageForm" property="vo.receiver" /></td>
						<td><input name="button" class="button" type="button" onClick="javascript:openSelectWindow('menselect','vo.receiver','vo.receivername');" value="选择" ><font color="red">&nbsp;*</font>
                                        </td>
	                                </tr>
					<tr class="listcellrow">
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>&nbsp;&nbsp;&nbsp;</td>
                  			<td>&nbsp;&nbsp;&nbsp;</td>
                  			<td>&nbsp;&nbsp;&nbsp;</td>
                  			<td>&nbsp;&nbsp;&nbsp;</td>
					    <td width="86" align="left" height="100"  nowrap class="InputLabelCell">信息内容：</td>
					   <td height="100"width="250" colspan="3"><html:textarea property="vo.content"  cols="50" rows="6" styleClass="input2" maxlength="125"/></td>
					</tr>
				  </table>
		  		</td>
				</tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		<goa:button property="save" value="发送" styleClass="button"  onclick="return doSaveExit(MessageForm)" operation="RBDX.OPT" />
          	<goa:button property="exit" value="返回" styleClass="button" onclick="javascript: history.back()" operation="RBDX.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>
</html:form>
</body>

