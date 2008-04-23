<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>
<SCRIPT LANGUAGE="JavaScript">
var remaidflag;
var calendarcontent;
var begintime;
var awoketime;
<!--
function window.onload() {
	var sForm = document.forms[0];
	//��ʼ������������Ƿ�Ҫ������
	remaidflag="<bean:write name="CalendarArrangeForm" property="vo.remind_flag"  filter="true"/>";
	remaidflag= (remaidflag=="1") ? "1" : "0";
	calendarcontent="<bean:write name="CalendarArrangeForm" property="vo.task_sum"  filter="true"/>";
    begintime="<bean:write name="CalendarArrangeForm" property="vo.begin_time"  filter="true"/>";
	awoketime="<bean:write name="CalendarArrangeForm" property="vo.awoke_time"  filter="true"/>";
	sForm.elements["vo.arrange"].value = "<bean:write name="CalendarArrangeForm" property="userId"  filter="true"/>";
	sForm.elements["vo.arrangename"].value = "<bean:write name="CalendarArrangeForm" property="userName"  filter="true"/>";

	<logic:equal name="CalendarArrangeForm" property="actionType" value = "add">
	sForm.elements["vo.calendar"].value = "<bean:write name="CalendarArrangeForm" property="listDate"  filter="true"/>";
	sForm.elements["vo.begin_time"].value = getToday().substring(11,16);
	sForm.elements["vo.end_time"].value = getToday().substring(11,16);
	sForm.elements["vo.user_code"].value = "<bean:write name="CalendarArrangeForm" property="calendarUserVO.userCode"  filter="true"/>";
	sForm.elements["vo.user_name"].value = "<bean:write name="CalendarArrangeForm" property="calendarUserVO.userName"  filter="true"/>";
	</logic:equal>
	<logic:equal name="CalendarArrangeForm" property="vo.remind_flag"  value = "1">
		document.all.awokeTimeTitle.style.visibility = 'visible';
	</logic:equal>
	<logic:notEqual name="CalendarArrangeForm" property="vo.remind_flag"  value = "1">
		document.all.awokeTimeTitle.style.visibility = 'hidden';
	</logic:notEqual>

}

function getNewDateAddMin(otimestr,min){
	if(otimestr.length==5) otimestr += ":00";
	var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth();
    var day = now.getDate();
	var ntime = Date.parse(month+"/"+day+"/"+year + " " +otimestr);
	var MinMilli = 1000 * 60 * min;
	var ndate = new Date();
	ndate.setTime(ntime + MinMilli);
    var hour = ndate.getHours();
    var minute = ndate.getMinutes();
	var sDate = year + "-"
		+ addZero(month+1) + "-"
		+ addZero(day) + " "
		+ addZero(hour) + ":"
		+ addZero(minute);
	return sDate;
}
/*
* ��С��10������ǰ��0
*/
function addZero(val){
    if(parseInt(val)<10) val="0"+val;
    return val;
}

/*
* ��ȡ��ǰʱ�� 2000-01-01 01:01:01
*/
function getToday() {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth();
    var day = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
	var sDate = year + "-"
		+ addZero(month+1) + "-"
		+ addZero(day) + " "
		+ addZero(hour) + ":"
		+ addZero(minute) + ":"
		+ addZero(second);
	return sDate;
}
function displayDiv(obj){
	var isSelect = obj.checked;
	if(isSelect){
		document.all.awokeTimeTitle.style.visibility = 'visible';
		if(document.forms[0].elements["vo.awoke_time"].value==""){
			document.forms[0].elements["vo.awoke_time"].value = 5 ;
			document.forms[0].elements["vo.remind_flag"].value = "1";
		}
	}
	else{
		document.all.awokeTimeTitle.style.visibility = 'hidden';
		document.forms[0].elements["vo.remind_flag"].value = "0";
	}
}


//ȡ�ò������ŵĶ�������
function checkMessageAction(){
	var sForm = document.forms[0];
	var remaidflag1=sForm.elements["remindbox"].checked;
	var calendarcontent1=sForm.elements["vo.task_sum"].value;
	var begintime1=sForm.elements["vo.begin_time"].value;
	var awoketime1=sForm.elements["vo.awoke_time"].value;
	sForm.elements["messageAction"].value = "";
	if (sForm.elements["actionType"].value=="add"){
		if(sForm.elements["remindbox"].checked){
			sForm.elements["messageAction"].value = "toInsert";
		}
	}
	else if(sForm.elements["actionType"].value=="modify"){
		if(remaidflag=="0" && sForm.elements["remindbox"].checked) sForm.elements["messageAction"].value = "toInsert";
		if(remaidflag=="1" && !sForm.elements["remindbox"].checked) sForm.elements["messageAction"].value = "toDelete";
		if(remaidflag=="1" && sForm.elements["remindbox"].checked) {
			if(calendarcontent!=calendarcontent1 || begintime!=begintime1 || awoketime!=awoketime1)
				sForm.elements["messageAction"].value = "toUpdate";
		}
	}
}
function doSave(mform){
	if (validater(mform) == false){
		return false;
	}
	if(checkTime()== false){
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
		checkMessageAction();
	}catch(e){
	}
	if (sForm.elements["actionType"].value=="modify"){
		sAction = addPara(sAction, "action=update");
	} else {
		sAction = addPara(sAction, "action=insert");
	}

	sForm.action = sAction;
	sForm.submit();

}
//
function checkTime()
{
	var sForm = document.forms[0];
	var bDate = sForm.elements["vo.begin_time"].value;
	var eDate = sForm.elements["vo.end_time"].value;
	var strMsg="";
	var bRet = true;
	if( !isTimeStr(bDate)){
		bRet = false;
		strMsg +=  ("��ʼʱ�䲻��,��ȷ��ʽ:'10:29'!\n");
	}
	if(!isTimeStr(eDate)){
		bRet = false;
		strMsg +=  ("����ʱ�䲻��,��ȷ��ʽ:'10:29'!\n");
	}
	if(!bRet){
		alert(strMsg);
		return false;
	}
	return true;
}

//����
function doSaveExit(mform){
	if (validater(mform) == false){
		return false;
	}
	if(checkTime()== false){
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
		checkMessageAction();
	}catch(e){
	}

	if (sForm.elements["actionType"].value=="modify"){
		sAction = addPara(sAction, "action=modify");
	} else {
		sAction = addPara(sAction, "action=add");
	}

	sForm.action = sAction;
	sForm.submit();

}

function checkInput(){
	var sForm = document.forms[0];
	var curDate = new Date();

	var cDate= date2datetime(curDate);
	var bDate = sForm.elements["vo.begin_time"].value;
	var eDate = sForm.elements["vo.end_time"].value;
	var calendar_date = sForm.elements["calendar_date"].value+" ";

	//var lcDate = getDateNum(cDate.substring(0,16),1);
	//var lbDate = getDateNum(cDate.substring(0,11)+ bDate.substring(0,5),1);
	//var leDate = getDateNum(cDate.substring(0,11)+ eDate.substring(0,5),1);
	var lcDate = getDateNum(cDate.substring(0,16),2);
	var lbDate = getDateNum(calendar_date+ bDate.substring(0,5),2);
	var leDate = getDateNum(calendar_date+ eDate.substring(0,5),2);
	var bRet = true;
	var strMsg = "";
	if (sForm.elements["vo.begin_time"].value == "" ){
		bRet = false;
		strMsg +=  ("�ճ̿�ʼʱ�䲻�����!\n");
	}
	if (sForm.elements["vo.end_time"].value == "" ){
		bRet = false;
		strMsg +=  ("�ճ̽���ʱ�䲻�����!\n");
	}
	if (lbDate > leDate){
		bRet = false;
		strMsg +=  ("�ճ̽���ʱ�䲻����С�ڿ�ʼʱ��!\n");
	}
	if(sForm.elements["remindbox"].checked){
		if(isEmpty(sForm.elements["vo.awoke_time"].value)){
			bRet = false;
			strMsg +=  ("��Ҫ���ѣ�����ʱ�䲻��Ϊ��!\n");
		}
		else if(!isInt(sForm.elements["vo.awoke_time"].value)){
			bRet = false;
			strMsg +=  ("����ʱ�����������!\n");
		}
		//else {
			//var bDate2 = getNewDateAddMin(bDate,(sForm.elements["vo.awoke_time"].value*-1));
			//var lbDate2 = getDateNum(bDate2.substring(0,16),1);
			//bDate2=calendar_date+bDate2.substring(11,16);
			//alert(bDate2);
			//var lbDate2 = getDateNum(bDate2.substring(0,16),2);
			//alert(lbDate2);
			//if(lbDate2 < lcDate){
			//	bRet = false;
			//	strMsg +=  ("�������ʱ�䣨"+ bDate2.substring(11,16) +"�����ڵ�ǰʱ�䣨"+cDate.substring(11,16)+"��֮��!\n");
			//}
		//}
	}
	else {
		sForm.elements["vo.awoke_time"].value = "";
	}
	if (!bRet) alert(strMsg);
	else{
		if(bDate.length==5) sForm.elements["vo.begin_time"].value += ":00";
		if(eDate.length==5) sForm.elements["vo.end_time"].value += ":00";
	}
	return bRet;
}
//-->
</SCRIPT>
<html:form action="/dailyoffice/calendararrange/editcalendar" method="post">
<html:hidden name="CalendarArrangeForm" property="actionType"/>
<html:hidden name="CalendarArrangeForm" property="vo.user_code"/>
<html:hidden name="CalendarArrangeForm" property="vo.user_name"/>
<html:hidden name="CalendarArrangeForm" property="vo.serial_num"/>
<html:hidden name="CalendarArrangeForm" property="vo.arrange"/>
<html:hidden name="CalendarArrangeForm" property="vo.arrangename"/>
<html:hidden name="CalendarArrangeForm" property="vo.calendar"/>
<html:hidden name="CalendarArrangeForm" property="messageAction"/>
<html:hidden name="CalendarArrangeForm" property="vo.remind_flag"/>
<div align="center">
  <center>
	<table  align="center" width="500"  border="0" cellspacing="0" cellpadding="0">
      <tr>
	<logic:equal name="CalendarArrangeForm" property="actionType" value = "add">
         <td align="center" colspan="2"><span class="TableTitleText">�����ճ̰���</span></td>
	</logic:equal>
	<logic:equal name="CalendarArrangeForm" property="actionType" value = "modify">
         <td align="center" colspan="2"><span class="TableTitleText">�޸��ճ̰���</span></td>
	</logic:equal>
      </tr>
      <tr>
	<td align="center">
		<table width="100%" align="center" border="0" cellspacing="0" cellpadding="4">
		  <tr  height="30" >
			<td></td>
		  </tr>
		  <tr class="listcellrow" height="23" >
		  <td width="20%" nowrap class="InputLabelCell">�ա����ڣ�</td>
		  <td >
		  <logic:equal name="CalendarArrangeForm" property="actionType" value = "add">
		  <bean:write name="CalendarArrangeForm" property="listDate"  filter="true"/>
		  <!--zxy add 2004-10-20-->
			<input type="hidden" name="calendar_date" value="<bean:write name="CalendarArrangeForm" property="listDate"  filter="true"/>">
		  <!---->
		  </logic:equal>
		  <logic:equal name="CalendarArrangeForm" property="actionType" value = "modify">
		  <bean:write name="CalendarArrangeForm" property="vo.calendar"  filter="true"/>
		   <!--zxy add 2004-10-20-->
			<input type="hidden" name="calendar_date" value="<bean:write name="CalendarArrangeForm" property="vo.calendar"  filter="true"/>">
		  <!---->
		  </logic:equal>
		  </td>
		  <td width="20%" nowrap class="InputLabelCell" >�� �� �ˣ�</td>
		  <td>
		  <bean:write name="CalendarArrangeForm" property="userName"  filter="true"/>
		  </td>
		  </tr>
          <tr class="listcellrow" height="23" >
		  <td width="20%"  nowrap class="InputLabelCell">�������ͣ�</td>
		  <td>
		  <html:select style="width:145px" styleClass="input0" property="vo.task_type">
		  <html:option value="0">��������</html:option>
		  <html:option value="1">��������</html:option>
		  </html:select><font color="red">*</font>
		  <td width="20%" nowrap class="InputLabelCell" >�ճ����ˣ�</td>
		  <td>
		  <logic:equal name="CalendarArrangeForm" property="actionType" value = "modify">
		  <bean:write name="CalendarArrangeForm" property="vo.user_name" filter="true"/>
		  </logic:equal>
		  <logic:equal name="CalendarArrangeForm" property="actionType" value = "add">
		  <bean:write name="CalendarArrangeForm" property="calendarUserVO.userName"  filter="true"/>
		  </logic:equal>
		  </td>
        </tr>
          <tr class="listcellrow"height="23" >
		  <td width="20%" nowrap class="InputLabelCell">��ʼʱ�䣺</td>
		  <td ><html:text styleClass="input2" property="vo.begin_time" size="22" validatetype="notempty"  onlytype="timeonly" msg="��ʼʱ�䲻��Ϊ��,��ȷ��ʽ:'10:59'"  maxlength="20"/><font color="red">*</font></td>
		  <td class="InputLabelCell" nowrap>����ʱ�䣺</td>
		  <td ><html:text styleClass="input2" property="vo.end_time" size="22" validatetype="notempty"  msg="����ʱ�䲻��Ϊ��,��ȷ��ʽ:'10:59'" onlytype="timeonly" maxlength="20"/><font color="red">*</font></td>
		   </tr>
          <tr class="listcellrow"height="23" >
		  <td width="20%" nowrap class="InputLabelCell">�����Ҫ��</td>
		  <td colspan="3"><html:text styleClass="input2" property="vo.task_sum" size="80" validatetype="notempty" msg="�����Ҫ����Ϊ��"  maxlength="30"/><font color="red">*</font></td>
        </tr>
          <tr class="listcellrow"height="23" >
		  <td width="20%"  nowrap class="InputLabelCell">������ϸ��</td>
		  <td colspan="3"><html:textarea styleClass="input2" property="vo.task_content" cols="15" rows="15" styleClass="input" maxlength="100"/></td>
        </tr>
          <tr class="listcellrow"height="23" >
		  <td nowrap class="InputLabelCell">�ᡡ���ѣ�</td>
		  <td >
		  <logic:equal name="CalendarArrangeForm" property="vo.remind_flag"  value = "1">
		  <input type=checkbox name="remindbox" value="1" onclick="displayDiv(this)" checked/>
		  </logic:equal>
		  <logic:notEqual name="CalendarArrangeForm" property="vo.remind_flag"  value = "1">
		  <input type=checkbox name="remindbox" value="1" onclick="displayDiv(this)"/>
		  </logic:notEqual>
		  </td>
		  <td class="InputLabelCell" colspan="2"><div id='awokeTimeTitle'>��ǰ <html:text styleClass="input2" property="vo.awoke_time" size="12" onlytype="int"  maxlength="10"/> ��������</div></td>
		  </tr>
</table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="center">
		<goa:button property="save" value="����" styleClass="button"  onclick="return doSave(CalendarArrangeForm)" operation="LTAP.OPT"/>
		<goa:button property="save" value="���沢����" styleClass="button"  onclick="return doSaveExit(CalendarArrangeForm)" operation="LTAP.OPT"/>
		<goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="LTAP.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>
</html:form>

