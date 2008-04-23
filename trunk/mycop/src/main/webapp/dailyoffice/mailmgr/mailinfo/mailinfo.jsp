<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<SCRIPT LANGUAGE="JavaScript">
<!--
function changeValue(checkId){
	var checkbox=document.all[checkId];
	var hiddenValue = document.all["vo." + checkId];
	if(checkbox.checked){
		hiddenValue.value="1";
	}else{
		hiddenValue.value="0";
	}
}
function getValue(){
	var hidden1=document.all["vo.post_flag"];
	var checkbox1=document.all["post_flag"];
	var hidden2=document.all["vo.sign_flag"];
	var checkbox2=document.all["sign_flag"];
	var hidden3=document.all["vo.return_flag"];
	var checkbox3=document.all["return_flag"];
	var hidden4=document.all["vo.show_flag"];
	var checkbox4=document.all["show_flag"];
	if(hidden1.value=="1"){
		checkbox1.checked=true;
	}else{
		checkbox1.checked=false;
	}
	if(hidden2.value=="1"){
		checkbox2.checked=true;
	}else{
		checkbox2.checked=false;
	}
	if(hidden3.value=="1"){
		checkbox3.checked=true;
	}else{
		checkbox3.checked=false;
	}
	if(hidden4.value=="1"){
		checkbox4.checked=true;
	}else{
		checkbox4.checked=false;
	}

}
function cancel(){
	var sForm=document.forms[0];
	sForm.submit();
}
//判断签名是否大于2000
function checkLableName(){
	var sForm=document.forms[0];
	var content=sForm.elements("vo.lable_name").value;
	//alert(content.length);
	if(content.length>1000){
		alert("签名内容过长");
		return false;
	}else{
		doSave(MailInfoForm);
	}
}


//-->
</SCRIPT>
<HEAD>
</HEAD>
<BODY class="bgcolor" onload="getValue()" >
<html:form action="/dailyoffice/mailmgr/mailinfo/mailinfo" method="post">
 <table width="100%"  border="0"  >
      <tr>
        <td width="100%" align="center"><span class="TableTitleText">参数设置</span></td>
      </tr>
      <tr>
        <td>&nbsp;<html:hidden name="MailInfoForm" property="actionType"/></td>
      </tr>
	  <tr>
	  <td >
	  <center>
<fieldset  style="height:150;width:200" >
<legend class="f12"> 个人参数</legend>
<table width="301" border="0" >
              <tr>
                <td width="71" class="InputLabelCell">分页显示：</td>
                <td width="200" align="left">
				<html:select property="vo.pagenumber">
					<html:option  value="10"></html:option>
					<html:option value="20"></html:option>
					<html:option value="30"></html:option>
					<html:option value="40"></html:option>
					<html:option value="50"></html:option>				</html:select></td>
              </tr>
              <tr>
                <td colspan="2" class="InputLabelCell">签名：</td>
              </tr>
              <tr>
                <td colspan="2" ><html:textarea property="vo.lable_name" cols="40"   rows="5" styleClass="input2"/></td>
              </tr>
      </table>
</fieldset>
</center>
</td>
</tr>
<tr >
<td>
<center>
<fieldset style="height:150;width:200">
<legend class="f12"> 发送参数 </legend>
<table  border="0" width="301" >

              <tr align="left">
                <td  height="30" class="f12"  ><label for="post_flag"><input type="checkbox" id="post_flag" onclick="changeValue('post_flag')"  />保存到寄件夹</label></td>
				<html:hidden property="vo.post_flag"/>
                <td height="30" class="f12" ><label for="sign_flag"><input type="checkbox" id="sign_flag" onclick="changeValue('sign_flag')"/>添加个人签名</label></td>
				<html:hidden property="vo.sign_flag"/>

              </tr>
			  <tr>
                 <td height="30" class="f12"><label for="return_flag"><input type="checkbox" id="return_flag" onclick="changeValue('return_flag')"/>要求回执</label></td>
				 <html:hidden property="vo.return_flag"/>
                <td height="30" class="f12"><label for="show_flag"><input type="checkbox" id="show_flag" onclick="changeValue('show_flag')"/>显示邮件表头</label></td>
				 <html:hidden property="vo.show_flag"/>
              </tr>
			   <tr>
                 <td height="30" class="InputLabelCell">默认邮件级别：</td>
                <td height="30"><html:select property="vo.mail_level">
					<html:option  value="0">普通邮件</html:option>
					<html:option value="1">重要邮件</html:option>
					<html:option value="2">最普通邮件</html:option>		</html:select>
				</td>
              </tr>
	  </table>
</fieldset>
</center>
</td>
</tr>
<tr>
        <td align="center">
		<goa:button property="save" operation="YJGL.OPT" value="保存" onclick="return checkLableName()" styleClass="button"/>
          <goa:button property="reset" value="重置" styleClass="button" onclick="cancel()"/></td>
   </tr>
</table>
</html:form>
</BODY>
</HTML>
