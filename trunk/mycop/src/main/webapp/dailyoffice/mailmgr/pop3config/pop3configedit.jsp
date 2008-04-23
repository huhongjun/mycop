<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>

<SCRIPT LANGUAGE="JavaScript">
<!--
function changeAuth(){
		var sFlag;
		sFlag = document.forms[0].elements["vo.smtp_auth"].value ;
		if (sFlag == 1){
			document.getElementById("needAuth").style.display = "block";
		}else if (sFlag == 0){
			document.getElementById("needAuth").style.display = "none";
		}
	}
									
//-->
</SCRIPT>
<html:errors />
<%String context =request.getContextPath();%>
<body class="bodybg" onload="changeAuth()">
<html:form action="/dailyoffice/mailmgr/pop3config/pop3configedit" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">邮件帐号设置</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="Pop3ConfigForm" property="actionType"/></td>
      </tr>
      <tr> <td  align="center">
   
				  <fieldset style="width:529 ">
				  <legend class="f12">接收端信息(pop3)</legend>
				  <table width="501"  border="0" cellpadding="2" cellspacing="1">
                    <tr class="listcellrow">
                      <td width="128"  class="InputLabelCell"> 帐号名称： </td>
                      <td width="154" ><html:text styleClass="input" property="vo.pop3_name" size="10"  validatetype="notempty" 
							msg="帐号名称不能为空" maxlength="30"/> </td>
                      <td width="13"  style="color:red " >＊</td>
                    </tr>
                    <tr class="listcellrow">
                      <td width="128"  class="InputLabelCell"> 服务器地址、端口：</td>
                      <td width="154"><html:text styleClass="input" property="vo.pop3_address"  validatetype="notempty" msg="接收服务器地址、端口(POP3)不能为空" maxlength="30"/> </td>
                      <td width="20" ><html:text property="vo.pop3_port" styleClass="input" style="align:right" onlytype="int" size="3" maxlength="4" /> </td>
                      <td  style="color:red " >＊</td>
                      <td  width="155"> :(一般情况下为110) </td>
                    </tr>
                    <tr class="listcellrow">
                      <td width="128"  class="InputLabelCell"> 登录账号： </td>
                      <td width="154"><html:text styleClass="input" property="vo.pop3_account" size="22" 
							 validatetype="notempty" 
							msg="POP3登录帐号不能为空" maxlength="30"/> </td>
                      <td  style="color:red " >＊</td>
                    </tr>
                    <tr class="listcellrow">
                      <td width="128"  class="InputLabelCell"> 登录密码： </td>
                      <td width="154"><html:password styleClass="input" property="vo.pop3_pwd" size="22" maxlength="255"/> </td>
                    </tr>
                    <tr class="listcellrow">
                      <td width="128"  class="InputLabelCell"> 删除远程邮件： </td>
                      <td width="154"><html:select property="vo.del_flag"> <html:option value="0">是</html:option> <html:option value="1">否</html:option> </html:select> </td>
                    </tr>
                    <tr class="listcellrow">
                      <td width="128" class="InputLabelCell"> 接收邮件夹： </td>
                      <td width="154"><html:select property="vo.incept_mail_dir"> <html:options collection="dir_name" property="serial_num" labelProperty="mail_dir_name"/> </html:select> </td>
                    </tr>
                  </table>
				  </fieldset>
					</td>
					</tr>
					<tr><td align="center">
					<fieldset style="width:527"><legend class="f12">发送端信息(smtp)</legend>
					<table  width="507" border="0" cellpadding="2" cellspacing="1">
                   
                      <tr class="listcellrow">
                        <td width="128"  class="InputLabelCell"> 服务器地址、端口：</td>
                        <td  width="154"><html:text styleClass="input" property="vo.smtp_server" size="22" maxlength="30"/> </td>
                        <td  width="20" ><html:text property="vo.smtp_port" styleClass="input" onlytype="int" size="3" maxlength="4"/> </td>
                        <td width="161"> :(一般情况下为25) </td>
						<td width="18"></td>
                      </tr>
                      <tr class="listcellrow">
                        <td width="128" class="InputLabelCell"> 显示姓名： </td>
                        <td  width="154" ><html:text  property="vo.show_name" styleClass="input" size="20"  maxlength="10"/> </td>
                      </tr>
                      <tr class="listcellrow">
                        <td width="128"  class="InputLabelCell"> 发信地址： </td>
                        <td  width="154">
						<html:text property="vo.show_address" styleClass="input" maxlength="30" validatetype="notempty" msg="发信使用地址不能为空"/> </td>
						<td style="color:red ">＊</td>
                        <td > (通常为您POP3的邮件地址) </td>
                      </tr>
					 
					<tr class="listcellrow">
						<td width="128"  class="InputLabelCell">
							账号状态：
						</td>
						<td width="154" >
							 <html:select property="vo.use_flag">
							 <html:option value="0">正常</html:option>
							 <html:option value="1">停用</html:option>
							 <html:option value="2">帐号</html:option>
							 </html:select>
					  </td>	 
					</tr>
					 <tr class="listcellrow">
                        <td width="128"  class="InputLabelCell"> 发送验证： </td>
                        <td  width="154"><html:select  property="vo.smtp_auth" onchange="javascript:changeAuth()"> <html:option value="0">不需要</html:option> <html:option value="1">需要</html:option> </html:select> </td>
                      </tr>
                     <tbody id="needAuth" name="needAuth" style="display:none">
                        <tr class="listcellrow">
                          <td width="128" class="InputLabelCell"> 验证账号： </td>
                          <td  width="154"><html:text property="vo.smtp_name" styleClass="input" size="30"/> </td>
                        </tr>
                        <tr class="listcellrow">
                          <td width="128" class="InputLabelCell"> 验证密码： </td>
                          <td  ><html:password styleClass="input" property="vo.smtp_pwd" size="22" maxlength="255"/> </td>
                        </tr>
                      </tbody>
                    </table>
					</fieldset>
					</td>
					</tr>
      <tr> 
        <td align="center"> 
		<goa:button property="save" value="保存" styleClass="button"  onclick="return doSave(Pop3ConfigForm)" operation="YJGL.OPT"/>
		<goa:button property="saveandexit" value="保存并返回" styleClass="button"  onclick="return doSaveExit(Pop3ConfigForm)" operation="YJGL.OPT"/>
          <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
  </center>
</div>

</html:form>
</body>

