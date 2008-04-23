<%@ page language="java" %>

<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<script type="text/javascript" src="/<gdp:context/>/js/gdp.js"></script>
<jsp:include page="/jsp/jsp_css.jsp" />
<%@ page import="com.gever.web.menusetup.form.*,java.util.*,com.gever.web.homepage.vo.*;"%>

<%
	MenuSetupForm form = (MenuSetupForm)request.getAttribute("MenuSetupForm");
	List sMenus =  form.getShowMenu();
	List hMenus = form.getHideMenu();
	UserMenuVO vo = new UserMenuVO();
%>

<html>
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<script language="javascript">
 
 
function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#cccccc';
}
	function addOptionMoveUp(sourceSelect)
	{  
		var sel_id;
	  sel_id = sourceSelect.selectedIndex;
	  if (sourceSelect.length > 1 && sel_id > 0) //got to have at least 2 items and not the first one is selected
	  {  var prevOption = sourceSelect.options[sel_id-1];
		var newOption = new Option(prevOption.text, prevOption.value);
		var selectedOption = sourceSelect.options[sel_id];
		sourceSelect.options[sel_id-1] = new Option(selectedOption.text, selectedOption.value);
		sourceSelect.options[sel_id] = newOption;
		sourceSelect.focus();
		sourceSelect.selectedIndex = sel_id -1;
	  }
	}

	//下移
	function addOptionMoveDown(sourceSelect)
	{
		var moduleForm = document.forms[0];
		var sel_id;
	  sel_id = sourceSelect.selectedIndex;
	  if (sourceSelect.length > 1 && sel_id < sourceSelect.length -1 && sel_id > -1) //got to have at least 2 items and not the last one is selected
	  {  var nextOption = sourceSelect.options[sel_id+1];
		var newOption = new Option(nextOption.text, nextOption.value);
		var selectedOption = sourceSelect.options[sel_id];
		sourceSelect.options[sel_id+1] = new Option(selectedOption.text, selectedOption.value);
		sourceSelect.options[sel_id] = newOption;
		sourceSelect.focus();
		sourceSelect.selectedIndex = sel_id + 1;
	  }
	} 


	function selectAllLeftList(){
		var moduleForm = document.forms[0];
	  for (i=0; i<moduleForm.addLeftListBox.options.length; i++){
		moduleForm.addLeftListBox.options[i].selected = true;
	  }
	}

	function selectAllRightList(){
		var moduleForm = document.forms[0];
	  for (i=0; i<moduleForm.addRightListBox.options.length; i++){
		moduleForm.addRightListBox.options[i].selected = true;
	  }
	}

	// update menus
	function updateMenus(){
		var moduleForm = document.forms[0];
		var bYesNo = confirm("是否真的删除?");
		
		//if (checkForm() == false){
		//	return false;
		//}
		if (bYesNo == false)
			return false;
		//selectAllRightList();
		//selectAllLeftList();

		var action = moduleForm.action;
		action +="?action=doDelMenu";
		moduleForm.action= action;
		moduleForm.submit();
	}

	function checkForm(){
		var moduleForm = document.forms[0];

		if (moduleForm.addLeftListBox.length > 12){
			alert("显示栏目个数不能大于12个！");
			return false;
		}
	}
	var context ="<gdp:context/>";
	function doBack(){
		var moduleForm = document.forms[0];
		var action = "";
		
		action +=context+"/menusetup/right.do?action=toHome&nodeid=-1";
		moduleForm.action= action;
		
		moduleForm.submit();
	}
</script>
 <html:form action="/menusetup/del" onsubmit="return false">

<p align="right">

<table width="100%" align="center" class="InputFrameMain">
<tr>
    <td >&nbsp;</td>
  </tr>
  <tr> 
    <td  align="center" class="TableTitleText">删除菜单</td>
  </tr>
  <tr>
      <td align="center"> 
        <table width="95%">
		<tr> 
    <td >&nbsp;</td>
  </tr>
</table>
    <table width="95%" border="0" cellpadding="2" cellspacing="0"   align="center">
      <tr > 
          <td height="20" width="62" class="Listtitle">
<div >选择</div></td>
          <td width="627"  class="Listtitle">
<div align="left">菜单</div></td>
        </tr>
       
<logic:iterate id="element" name="MenuSetupForm" property="showMenu" type="com.gever.web.homepage.vo.UserMenuVO" indexId="index"> 
  <tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"> 
          
        <td width="62" height="20" class="listcellrow2"> 
          <input type="checkbox" name ="addRightListBox" value='<bean:write name="element" property="nodeid"/>'> 
		  </td>

          <td height="11" width="627" class="listcellrow2"><bean:write name="element" property="pathname"/></td>
          
        </tr>
	

</logic:iterate> 
</table>
<table>
          <tr> 
            <td height="22" colspan="3" align="center" valign="top" class='InputAreaCell'> 
              <p align="center"> 
                <!--<input type="button" value="提交" onclick="javascript:updateMenus();">-->
				  <html:button styleClass="button"  property="del" onclick="javascript:updateMenus();" value="确定"/>
              <html:button styleClass="button"  property="back" onclick="javascript:doBack()" value="返回"/>
              </p></td>
          </tr>
          <tr>
            <td height="10" colspan="3" align="center" valign="top" class='InputAreaCell'>&nbsp;			  <html:hidden name="MenuSetupForm" property="newNodes"/></td>
          </tr>
        </table>
  
</html:form>
<SCRIPT LANGUAGE="JavaScript">
<!--
var aryMenus = new Array();
   <% 
	String isLoad =   (String) request.getAttribute("isLoad");
	boolean bRet = false;
	if ("true".equals(isLoad)){
		bRet = true;
	}%>
   var isLoad = <%=bRet%>;
   if (isLoad == true){
		var newNodes = document.forms[0].elements["newNodes"].value;

		var aNodes = newNodes.split(",");
		for (var idx= 0 ; idx<aNodes.length; idx++){
            //******************************************************************
            if(navigator.appName == "Netscape"){
                window.parent.document.getElementById("left").contentDocument.location.reload();
                //window.parent.document.getElementById("left").delNode(aNodes[idx]);
            } else {
			    window.parent.frames["left"].delNode(aNodes[idx]);
            }
            //******************************************************************
		}
	   //window.parent
   }
 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
//-->
</SCRIPT>
</body>
</html>
