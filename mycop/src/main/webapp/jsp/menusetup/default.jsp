<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ page import="java.util.*,com.gever.web.homepage.vo.UserMenuVO"%>

<html:errors />
<jsp:include page="/jsp/jsp_css.jsp" />
<script language="JavaScript" type="text/JavaScript">


function changeBgColorOnMouseOut(obj){
	obj.style.backgroundColor='';
}
function changeBgColorOnMouseOver(obj){
	obj.style.backgroundColor='#cccccc';
}

var context ="<gdp:context/>";
function doAction(url,what,whatAction){
	var mForm = document.forms[0];
	var action = "";
	
	action =context +url+"?action=" + what + "&actionFlag="+whatAction;
	mForm.action= action;
	//alert(action);
	mForm.submit();
}

//默认值
function doDefault(){
	var bYesNo = confirm("此操作将删除用户自定义菜单，是否将菜单设置恢复到默认状态?");
	if (bYesNo == false)
		return false;
	var mForm = document.forms[0];
	var action = mForm.action
	action =context+ "/menusetup/right.do?action=toHome&actionFlag=default" ;
	mForm.action= action;
	mForm.submit();
	return false;
}
</script>

<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>
  <html:form action="/menusetup/right" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">菜单列表</td>
  </tr>
  <tr> 
    <td colspan="6" align="right">&nbsp;	
	</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
      <td>
			<logic:equal name="MenuSetupForm" property="hasInsert" value="true">
			 <perm:button styleClass="button"  property="add" onclick="doAction('/menusetup/edit.do','toEditMenu','add')" value="新增菜单"rescode="GDP-CDDZ" optcode="ALL"/>
			</logic:equal>
          </td><%/*
          <td> <html:button styleClass="button"  property="setup" onclick="doAction('/menusetup/menusetup.do','toMenuSetup','')"value="菜单分配"/>
          </td>*/
		   %>
          <td><perm:button styleClass="button"  property="hide" onclick="doAction('/menusetup/hide.do','toHideMenu','')" value="菜单设置" rescode="GDP-CDDZ" optcode="ALL"/>
		  <perm:button styleClass="button"  property="del" onclick="doAction('/menusetup/del.do','toDelMenu','')" value="删除菜单"rescode="GDP-CDDZ" optcode="ALL"/>
		  <html:hidden name="MenuSetupForm" property="nodeid"/>
		  <html:hidden name="MenuSetupForm" property="parentid"/>
		  <html:hidden name="MenuSetupForm" property="nodename"/>
			<html:hidden  name="MenuSetupForm" property="isFolder"/>
          </td>
		  <td> <perm:button type="button" styleClass="button"  property="default" onclick="doDefault()" value="恢复默认" rescode="GDP-CDDZ" optcode="ALL"/>
          </td>
        </tr>
      </table>
	  </td>
  </tr>
  </table>
	
<table width="95%" height="20" border="0"  align="center" cellpadding="2" cellspacing="0" >
  <tr align="left" height="20"  > 
    <td width="20%" class="Listtitle"> 菜单名称</td>
          
    <td width="20%" height="20" class="Listtitle"> 
      下级菜单</td>
          
    <td width="20%" height="20" class="Listtitle"> 所有下级菜单</td>
          
    <td width="20%" height="20" class="Listtitle"> 所有隐藏菜单</td>

        </tr>
		 <logic:iterate id="ms" name="MenuSetupForm" property="homeData" type="com.gever.web.homepage.vo.UserMenuVO" indexId="index"> 
			<tr  height="20" onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);">  
    <td  class="listcellrow2"><bean:write name="ms" property="nodename"/></td>
			<td  height="20" class="listcellrow2"><bean:write name="ms" property="nextnodenum"/></td>
			<td  height="20" class="listcellrow2"><bean:write name="ms" property="sumnum"/></td>
			<td  height="20" class="listcellrow2"><bean:write name="ms" property="hidenum"/></td>
			</tr>
		 </logic:iterate>
		<tr> 
          
    <td  align="left" class="Listtitle">总计</td>
          <td  class="Listtitle"><bean:write name="MenuSetupForm" property="sum"/></td>
          <td  align="left" class="Listtitle"><bean:write name="MenuSetupForm" property="levelnums"/> </td>
		
		    <td  class="Listtitle"><bean:write name="MenuSetupForm" property="hides"/></td>
        </tr>
	</table>
	</html:form>
	<SCRIPT LANGUAGE="JavaScript">
	<%
	String isLoad =   (String) request.getAttribute("isLoad");
	String actionType =   (String) request.getAttribute("actionType");
	boolean bRet = false;
	boolean bAdd = true;
	UserMenuVO vo = (UserMenuVO)session.getAttribute("menuvo");
	String nodeid="";
	if ("true".equals(isLoad) || vo !=null){
		if ((vo == null )||(vo.getParentid() == null))
			nodeid ="0";
		else
			nodeid =vo.getParentid().trim();
		//nodeid=vo.getNodeid();
		session.removeAttribute("menuvo");
		if("0".equals(nodeid)){
		//nodeid="-1";
		}
		bRet = true;
	}if ("add".equals(actionType) || vo !=null){
		bAdd = true;

	} else {
		bAdd = false;
	}%>

	
	var isLoad = <%=bRet%>;
	var bAdd = <%=bAdd%>
   if (isLoad == true ){
        //******************************************************************
        // 胡勇添加，Netscape 不支持frames对象
        if(navigator.appName == "Netscape"){
            window.parent.document.getElementById("left").contentDocument.location.reload("<%=nodeid%>");
        } else {
		    window.parent.frames["left"].reload("<%=nodeid%>");
		}
        //******************************************************************
	   //window.parent
   } 
    try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
	</SCRIPT>
</body>
</html>
