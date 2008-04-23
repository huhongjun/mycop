<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page import="java.util.*,com.gever.goa.dailyoffice.calendararrange.vo.*,com.gever.goa.dailyoffice.calendararrange.action.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
CalendarArrangeForm myForm = (CalendarArrangeForm)session.getAttribute("CalendarArrangeForm");
List deptCalendarUserList = myForm.getDeptCalendarUserList();
CalendarRightVO calendarRightVO = myForm.getCalendarRightVO();
%>
<html>
<SCRIPT LANGUAGE="JavaScript">
var calendarRightmember = "<%=calendarRightVO.getMember()%>".split(",");
var addRightmember = new Array();
var deleteRightmember = new Array();
var newRightmember = "<%=calendarRightVO.getMember()%>".split(",");
//alert(calendarRightmember);
//alert(newRightmember);
<!--
function doAction(whatAction){
	//汤启礼改
	var mForm=null;
	if(event!=null){
		var btn=event.srcElement;
		if(btn.tagName=="INPUT"){
			mForm=btn.form;
		}else{
			mForm = document.forms[0];
		}
	}else{
		mForm = document.forms[0];
	}
	//汤启礼改
	var action =  mForm.action ;

	action =  addPara(action,"action=" + whatAction);
	mForm.target = "_parent";
	mForm.action = action
	mForm.submit();

}

function changeRight(){
var form = document.forms[0];
form.submit();
}

function selectAllChk(obj){
	var isSelect = obj.checked
	var length  = 0;
	try{
		length = document.forms[0].usercode.length;
	} catch (e){return false;}

	if (isNaN(length))	{
		try{
		document.forms[0].usercode.checked=isSelect;
		}catch(e){}
	}
	if (length ==0)	{
		return false;
	}

	if (length == 1)	{
		document.forms[0].usercode.checked=isSelect;
	} else {
		for(var i=0;i<document.forms[0].usercode.length;i++){
			document.forms[0].usercode[i].checked=isSelect;
		}
	}
}

//更新人员数组
function clickCheckedItems(obj){
	var membervalur = obj.value;
	var isSelect = obj.checked;
	var existflag = false;
	var index = -1;
	//判断选择的人员在calendarRightmember是否存在,存在的话把位置号赋给index
	for(var i=0;i<calendarRightmember.length;i++){
		if(calendarRightmember[i]==membervalur){
			existflag = true;
			index = i;
			break;
		}
	}
	//如果选中
	if(isSelect){
		if(!existflag){
			addArrayItem(newRightmember,membervalur);
		}
		else if(existflag && newRightmember[index]==""){
			newRightmember[index] = calendarRightmember[index];
		}
	}
	//如果取消选择
	else if(!isSelect){
		deleteArrayItem(newRightmember,membervalur);
	}

}

//添加元素到数组中,去除重复
function addArrayItem(arr,value){
	var existflag = false;
	for(var i=0;i<arr.length;i++){
		if(arr[i]==value){
			existflag = true;
			break;
		}
	}
	if(!existflag){
		arr.push(value);
	}
}
//把数组中value所在的位置值设为""
function deleteArrayItem(arr,value){
	for(var i=0;i<arr.length;i++){
		if(arr[i]==value){
			arr[i] = "";
		}
	}
}


function updateRightmember(){
	var clength =  newRightmember.length;
	var newarr = new Array();
	for(var i=0;i<clength;i++){
		if(newRightmember[i]!="")
			newarr.push(newRightmember[i]);
	}
	return newarr;
}
//保存
function doSave(){
	var sForm = document.forms[0];
	sForm.action = "updatecalendarright.do";
	sForm.elements("calendarRightVO.user_code").value = "<bean:write name="CalendarArrangeForm" property="userId"  filter="true"/>";
	var result = updateRightmember();
	sForm.elements("calendarRightVO.member").value = result.join(",");
	sForm.elements("calendarRightVO.righttype").value = sForm.elements("rightType").value;
	sForm.elements["actionType"].value="toEditRight";
	sForm.submit();
}

//保存
function doSaveExit(){
	var sForm = document.forms[0];
	sForm.action = "updatecalendarright.do";
	sForm.elements("calendarRightVO.user_code").value = "<bean:write name="CalendarArrangeForm" property="userId"  filter="true"/>";
	var result = updateRightmember();
	sForm.elements("calendarRightVO.member").value = result.join(",");
	sForm.elements("calendarRightVO.righttype").value = sForm.elements("rightType").value;
	sForm.elements["actionType"].value="toUpdateRight";
	sForm.target = "_parent";
	sForm.submit();
}

//-->
</SCRIPT>
<body class="bodybg">
  <html:form action="/dailyoffice/calendararrange/editcalendarbydept" method="post">
  <html:hidden name="CalendarArrangeForm" property="departmentId"/>
  <html:hidden name="CalendarArrangeForm" property="actionType"/>
  <html:hidden name="CalendarArrangeForm" property="listType"/>
  <html:hidden name="CalendarArrangeForm" property="calendarRightVO.user_code"/>
  <html:hidden name="CalendarArrangeForm" property="calendarRightVO.member"/>
  <html:hidden name="CalendarArrangeForm" property="calendarRightVO.user_name"/>
  <html:hidden name="CalendarArrangeForm" property="calendarRightVO.righttype"/>
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText">设置日程安排</td>
  </tr>
  <tr>
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">

	 <table border="0" cellspacing="1" cellpadding="2">
        <tr  class="listcellrow">
		<td>权限类型：</td>
	   <td>
	      <html:select name="CalendarArrangeForm" property="rightType" onchange="changeRight()" styleClass="input0">
             <html:option value="0">浏览权限</html:option>
             <html:option value="1">修改权限</html:option>
          </html:select>
          </td>
		  <td>&nbsp;
          </td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr>
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
          <td width="25%" class="Listtitle"><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)">全选</td>
          <td width="25%" class="Listtitle">&nbsp;</td>
          <td width="25%" class="Listtitle">&nbsp;</td>
          <td width="25%" class="Listtitle">&nbsp;</td>
        </tr>

		<%
		Iterator iter = deptCalendarUserList.iterator();
		while (iter.hasNext()) {
		%>
		<tr>
		<%
			for (int i = 0; i < 4; i++) {
				if(iter.hasNext()){
					CalendarUserVO item = (CalendarUserVO)iter.next();
		%>
		<td class="listcellrow">
                <%
                if(item.getRightType().equals(myForm.getRightType())){
                %>
		<input type=checkbox name="usercode" value='<%=item.getUserCode()%>' onclick='clickCheckedItems(this)' checked>
                <%}else{%>
                <input type=checkbox name="usercode" value='<%=item.getUserCode()%>' onclick='clickCheckedItems(this)'>
                <%}%><%=item.getUserName()%>
		</td>
		<%}else{%>
		<td class="listcellrow">&nbsp;</td>
		<%}}
		%>
		</tr>
		<%}%>
	</table>
</td>
  </tr>
      <tr>
        <td align="center">
		<goa:button property="save" value="保存" styleClass="button"  onclick="doSave()"  operation="LTAP.OPT"/>
		<goa:button property="save" value="保存退出" styleClass="button"  onclick="doSaveExit()"  operation="LTAP.OPT"/>
		<logic:equal name="CalendarArrangeForm" property="listType" value="toListMonth">
		<goa:button styleClass="button"  property="goback"  onclick="doAction('goUrl(monthindex)')" value="返回"  operation="LTAP.VIEW"/>
		</logic:equal>
		<logic:equal name="CalendarArrangeForm" property="listType" value="toListWeek">
		<goa:button styleClass="button"  property="goback"  onclick="doAction('goUrl(weekindex)')" value="返回"  operation="LTAP.VIEW"/>
		</logic:equal>
      </tr>
	</table>
	</html:form>
</body>
</html>
