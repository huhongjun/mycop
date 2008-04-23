<%@ page language="java" %>

<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page import="com.gever.web.menusetup.form.*,java.util.*,com.gever.web.homepage.vo.*;"%>

<%
	MenuSetupForm form = (MenuSetupForm)request.getAttribute("MenuSetupForm");
	List sMenus =  form.getShowMenu();
	List hMenus = form.getHideMenu();
	UserMenuVO vo = new UserMenuVO();
	String context =request.getContextPath();
%>
<html>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<jsp:include page="/jsp/jsp_css.jsp" />
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
<script language="javascript">
 
 
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
	//移动列表中所有
    function allToRight() {
		selectAllLeftList();
		doSelAll('addLeftListBox','addRightListBox');
	}
	function allToLeft() {
		selectAllRightList();
		doSelAll('addRightListBox','addLeftListBox');
	}
	// update menus
	function updateMenus(){
		var moduleForm = document.forms[0];
		if (checkForm() == false){
			return false;
		}
		selectAllRightList();
		selectAllLeftList();

		var action = moduleForm.action;
		if (confirm("注意：你所隐藏的模块可能无法恢复显现。确定吗？")){
		  action +="?action=doHideMenu";
		  moduleForm.action= action;
		  moduleForm.submit();
		}
	}
	var maxCount = <bean:write name='MenuSetupForm' property='maxMenus'/>;
	function checkForm(){
		var moduleForm = document.forms[0];
	
		if (moduleForm.addRightListBox.length > maxCount){
			alert("显示栏目个数不能大于"+maxCount+"个！");
			return false;
		}
	}

	var context ="<%=context%>";
	function doBack(){

		var moduleForm = document.forms[0];
		var action = "";
		
		if (moduleForm.elements["nodeid"].value==0)
			action +=context+"/menusetup/right.do?action=toHome&nodeid=-1";
		else
			action +=context+"/menusetup/menusetup.do?action=toMenuSetup";
		moduleForm.action= action;
		
		moduleForm.submit();
	}
</script>
 <html:form action="/menusetup/hide" onsubmit="return false">

<p align="right"><br>

<table width="90%" align="center" class="InputFrameMain">
<tr>
      <td align="center"> 
        <table width="95%">
		<tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">隐藏菜单</td>
  </tr>
<tr>
<td>
<table align="center">
          <tr> 
            <td colspan="3" class="InputLabelCell"> <html:hidden name="MenuSetupForm" property="nodeid"/>
		  <html:hidden name="MenuSetupForm" property="nodename"/>
			<html:hidden  name="MenuSetupForm" property="isFolder"/>&nbsp;</td>
          </tr>
          <tr> 
            <td class="InputLabelCell" colspan="3">本级栏目：<bean:write name="MenuSetupForm" property="vo.nodename"/>
            <html:hidden name="MenuSetupForm" property="vo.nodename"/></td>
          </tr>
          <tr> 
            <td class="InputLabelCell">功能隐藏区</td>
            <td valign="middle" class="InputLabelCell">&nbsp;</td>
            <td class="InputLabelCell">功能显示区</td>
          </tr>
          <tr> 
            <td align="center" valign="top" class='InputAreaCell'> 
            <select name="addLeftListBox" id="addLeftListBox" multiple class="t12" style="width:120px; height:200px;" ondblclick="doSelOne('addLeftListBox','addRightListBox')">
<% 
    for (int i=0; i<sMenus.size(); i++){
		 vo = new UserMenuVO();
		 vo = (UserMenuVO)sMenus.get(i);
    %>
         <option value="<%=vo.getNodeid()%>"><%=vo.getNodename()%></option>
   <% }     %>
              </select> </td>
            <td align="center" valign="middle" class='InputAreaCell'> <table border="0" cellpadding="1" cellspacing="1">
                    <tr>
                      <td align="center"> <input type="button" class="button" style="width:30px;" value="&gt;" onclick="doSelOne('addLeftListBox','addRightListBox')"></td>
                    </tr>
                    <tr>
                      <td align="center"> <input name="Submit232" type="button" class="button" style="width:30px;" value="&gt&gt;" onclick="allToRight()"></td>
                    </tr>
				<tr align="center">
                      <td valign="middle"> </td>
                    </tr>
					<tr align="center">
                      <td valign="middle"> </td>
                    </tr>
                    <tr>
                      <td align="center"> <input name="Submit2232" type="button" class="button" style="width:30px;" value="&lt&lt;" onclick="allToLeft()"></td>
                    </tr>
                    <tr>
                      <td align="center"> <input type="button" class="button" style="width:30px;" value="&lt;" onclick="doSelOne('addRightListBox','addLeftListBox')"></td>
                    </tr>
                  </table></td>
            <td align="center" valign="top" class='InputAreaCell'>
            <select name="addRightListBox" id="addRightListBox" multiple class="t12" style="width:120px; height:200px;" ondblclick="doSelOne('addRightListBox','addLeftListBox')">
   <% 
    for (int i=0; i<hMenus.size(); i++){
		 vo = new UserMenuVO();
		 vo = (UserMenuVO)hMenus.get(i);
    %>
         <option value="<%=vo.getNodeid()%>"><%=vo.getNodename()%></option>
   <% }     %>
              </select> </td>
			  <td class='InputAreaCell'> 
              <table border="0" cellpadding="1" cellspacing="1"> 
               <tr>
		           <td align="center" valign="top" class="InputLabelCell">上移</td>
		       </tr>
	           <tr>
                  <td valign="top"> <img src="<gdp:context/>/jsp/homepage/images/but_cy_1.gif" width="22" height="17" onclick="addOptionMoveUp(document.forms[0].addRightListBox)"> 
                  </td>
              </tr>
				<tr>
                  <td valign="bottom"> <img src="<gdp:context/>/jsp/homepage/images/but_cy_2.gif" width="22" height="17" onclick="addOptionMoveDown(document.all('addRightListBox'))"> 
                  </td>
                </tr>
				<tr>
		           <td align="center" valign="top" class="InputLabelCell">下移</td>
		       </tr>
				</table>
				</td>
          </tr>
          <tr> 
            <td height="22" colspan="3" align="center" valign="top" class='InputAreaCell'> 
              <p align="center"> 
                <!--<input type="button" value="提交" onclick="javascript:updateMenus();">-->
				  <html:button styleClass="button"  property="del" onclick="javascript:updateMenus();" value="确定"/>
              <html:button styleClass="button"  property="back" onclick="javascript:doBack()" value="返回"/>
              </p></td>
          </tr>
          <tr>
            <td height="10" colspan="3" align="center" valign="top" class='InputAreaCell'>&nbsp;</td>
          </tr>
        </table>
  </td></tr></table>
</html:form>
<SCRIPT LANGUAGE="JavaScript">
<!--
var aryMenus = new Array();
   <% 
	String isLoad =   (String) request.getAttribute("isLoad");
	boolean bRet = false;
	if ("true".equals(isLoad)){
		bRet = true;
	}
 %>
   var isLoad = <%=bRet%>;
   if (isLoad == true){
        //*****************************************************************
        if(navigator.appName == "Microsoft Internet Explorer"){
            window.parent.frames["left"].reload(document.forms[0].elements["nodeid"].value);
		} else {
		    var leftframe = window.parent.document.getElementById("left");
        //leftframe.src="<gdp:context/>/jsp/menusetup/lefttree.jsp?action=toRoot&nodeid=" + document.forms[0].elements["nodeid"].value;
            leftframe.contentDocument.location.reload(document.forms[0].elements["nodeid"].value);
        }
        //*****************************************************************
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
