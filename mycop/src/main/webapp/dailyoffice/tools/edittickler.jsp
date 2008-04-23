<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>


<SCRIPT LANGUAGE="JavaScript">
<!--
var awokeDateValue ='<bean:write name="TicklerForm" property="vo.awokeDate" />';
var awokeTimeValue ='<bean:write name="TicklerForm" property="vo.awokeTime" />';

function isRemind(){
	var mform = document.forms[0];
	var box=mform.elements["vo.remind_flag"];
	var awokeDate=mform.elements["vo.awokeDate"];
	var awokeTime=mform.elements["vo.awokeTime"];
	if(box.checked){
		awokeDate.disabled=false;
		awokeTime.disabled=false;
		//alert("checked");
	}else{
		awokeDate.disabled=true;
		awokeDate.value=awokeDateValue;
		awokeTime.disabled=true;
		awokeTime.value=awokeTimeValue;
		//alert("uncheked");
	}
}
//-->
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
function checkInput(){
	var form= document.forms[0];
	var value=form.elements["vo.content"].value;
	if(isEmpty(value)){
		alert("备忘录内容不能为空！");
		return false;
	}else{
		return true;
	}
}
//-->
</SCRIPT>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<body class="bodybg">
<html:form action="/dailyoffice/tools/editTickler" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center"><span class="TableTitleText">备忘录编辑</span></td>
      </tr>
      <tr>
        <td>&nbsp;<html:hidden name="TicklerForm" property="actionType"/></td>
      </tr>
      <tr>
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr>
				  <td>
				  <table width="500" height="36" border="0" cellpadding="2" cellspacing="1">
           			<tr class="listcellrow">
					<tr>
					<td width="15%"></td>
					<td width="78%"></td>
					<td width="7%"></td>
					</tr>
					    <td  height="23" nowrap class="InputLabelCell">编制日期:</td>
					    <td  height="23" class='f12'><bean:write  property="vo.create_date" name="TicklerForm"/></td>
						<td width="7%"></td>
					</tr>
					<tr class="listcellrow">
					    <td nowrap class="InputLabelCell">内&nbsp;&nbsp;&nbsp;&nbsp;容:</td>
					    <td style="word-break:break-all" ><html:textarea property="vo.content"   cols="37" rows="4" styleClass="input"
						maxlength="150" />
		</td>
					    <td><span class="style1">*</span></td>
					</tr>
       				  </table>
                                  <table width="500" height="36" border="0" cellpadding="2" cellspacing="1">
                                  <tr class="listcellrow">
                                  	<td width="20%" class="InputLabelCell">提&nbsp;&nbsp;&nbsp;&nbsp;醒:</td>
                                    <logic:notEqual value="1" property="vo.remind_flag" name="TicklerForm">
                                        <td width="2%">
                                              <html:checkbox property="vo.remind_flag" onclick="isRemind()" value="1" />
                                        </td>
                                        <td width="20%" align="right" class="InputLabelCell">提醒时间:</td>
                                  	<td>
                                            <html:text property="vo.awokeDate" disabled="true" styleClass="input" maxlength="10" onlytype="date" validatetype="date"  msg="日期不对,正确格式:'2000-01-02 '"></html:text>
                                    </td>
                                        <td>
                                          <html:text property="vo.awokeTime" disabled="true"  styleClass="input" maxlength="8" validatetype="timestr"  msg="时间不对,正确格式:'00:00:00'">
                                          </html:text>
                                        </td>
                                    </logic:notEqual>
                                    <logic:equal value="1" property="vo.remind_flag" name="TicklerForm">
                                          <td width="2%">
                                        	<input type='checkbox' name="vo.remind_flag" onclick="isRemind()" checked value="1" />
                                         </td>
                                         <td width="20%" align="right" class="InputLabelCell">提醒时间:</td>
                                  	<td>
                                            <html:text property="vo.awokeDate" styleClass="input" maxlength="10" onlytype="date" validatetype="date"  msg="日期不对,正确格式:'2000-01-02'" ></html:text>
                                    </td>
                                        <td>
                                          <html:text property="vo.awokeTime" styleClass="input" maxlength="8"  validatetype="timestr"  msg="时间不对,正确格式:'00:00:00'">
                                          </html:text>
                                        </td>
                                    </logic:equal>
                                      	<td align="right" width="10%"class="InputLabelCell">完成:</td>
                                  	<td width="3%">
                                            <logic:equal value="1" property="vo.finish_flag" name="TicklerForm">
                                              <input type='checkbox' name="vo.finish_flag" checked  value="1" />
                                            </logic:equal>
                                            <logic:notEqual value="1" property="vo.finish_flag" name="TicklerForm">
                                              <input type='checkbox' name="vo.finish_flag"   value="1" />
                                            </logic:notEqual>
                                    </td>
									<td width="20"></td>
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
		<goa:button property="save" value="保存" operation="BWL.OPT" styleClass="button"  onclick="return doSave(TicklerForm)"/>
		<goa:button property="save" value="保存并返回" operation="BWL.OPT" styleClass="button"  onclick="return doSaveExit(TicklerForm)"/>
          <goa:button property="exit" value="返回" operation="BWL.VIEW" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
  </center>
</div>

</html:form>
</body>
