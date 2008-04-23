<%@ page language="java" %>
<%@ page import="com.gever.web.menusetup.form.*,java.util.*,com.gever.web.homepage.vo.*,com.gever.util.*;"%>
<%@ page contentType="text/html; charset=GB2312"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%
	MenuSetupForm form = (MenuSetupForm)request.getAttribute("MenuSetupForm");
	List sMenus =  form.getSelMenu();
	List hMenus = form.getNoSelMenu();
	UserMenuVO vo = new UserMenuVO();
	String context =request.getContextPath();

%>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>

<html>
<jsp:include page="/jsp/jsp_css.jsp" />
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr>
  <gmenu:getpathtag/>
  </tr>
</table>


<script language="javascript">
var context = "<%=context%>";
	
	function onChangeDir(nodeid){
		
		var mForm = document.forms[0];
		//mForm.elements["nodeid"].value = nodeid;
		//alert(mForm.elements["nodeid"].value);
		var url =context +"/menusetup/hidedata.do?action=toHideData&nodeid=" +nodeid + "&pnodeid=" + mForm.elements["vo.nodeid"].value;

			//	alert(url);
        //*****************************************************************
        if(navigator.appName == "Netscape") {
            document.all("hiddendata").contentDocument.location.href =url;
        } else {
		    document.frames["hiddendata"].location.href =url;
		}
        //*****************************************************************
        //window.open(url);

	}
	function reLoadData(ary){
		var mForm = document.forms[0];
		var sourceObj = mForm.elements["addLeftListBox"];
		//ɾ��Դ���е�������
		for (var idx= sourceObj.options.length-1 ; idx>=0 ; idx--){
            //*****************************************************************
            if(navigator.appName == "Netscape") {
                sourceObj.options[idx] = null;
            } else {
			    sourceObj.options.remove(idx);
            }
            //*****************************************************************
		}
		//�����µ�
		for (var idx = 0; idx < ary.length; idx++){
			var e = document.createElement("OPTION");
			e.value=ary[idx][0];
			e.text=ary[idx][1];
			sourceObj.options[sourceObj.options.length] = e;
		}	
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
		//sourceSelect.focus();
		sourceSelect.selectedIndex = sel_id -1;
	  }
	}

	//����
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
		//sourceSelect.focus();
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
	//�ƶ��б�������
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
		var strOlds ="";
		for ( var idx= 0; idx<aryMenus.length; idx++){
			strOlds += aryMenus[idx][0] + ",";

		}
		var aryNews = new Array();
		var strNews = new Array();
		var value = "";
		var isFind = false;		
		for (var i=0; i<moduleForm.addRightListBox.options.length; i++){
			value = moduleForm.addRightListBox.options[i].value;
			isFind = false;
			for (var idx = 0; idx<aryMenus.length; idx++){
				if (value == aryMenus[idx][0]){
					isFind =true;
					break;
				}
			}
			if (isFind == false){
				strNews += moduleForm.addRightListBox.options[i].value  + ",";
			}
		}
//		alert(strNews);
		moduleForm.elements["preNodes"].value  =strOlds;
		moduleForm.elements["newNodes"].value  =strNews;
		if (checkForm() == false){
			return false;
		}
		selectAllRightList();
		selectAllLeftList();
		
		var action = moduleForm.action;
		action +="?action=doMenuSetup";
		moduleForm.action= action;
		moduleForm.submit();
	}
	var maxCount = <bean:write name='MenuSetupForm' property='maxMenus'/>;
	function checkForm(){
		var moduleForm = document.forms[0];

		if (moduleForm.addRightListBox.length > maxCount){
			alert("��ʾ��Ŀ�������ܴ���"+maxCount+"����");
			return false;
		}
	}
	function doAction(url,what,whatAction){
		var mForm = document.forms[0];
		var action = "";
		action =context +url+"?action=" + what + "&actionFlag="+whatAction;
		mForm.action= action;
		alert(action);
		mForm.submit();
}
	function doBack(){
		var moduleForm = document.forms[0];

		var action = "";
		action +=context+"/menusetup/right.do?action=toHome&nodeid=-1";;
		moduleForm.action= action;
		
		moduleForm.submit();
	}
</script>
 <html:form action="/menusetup/menusetup" onsubmit="return false">

<p align="right">
 <table width=100% border="0" cellspacing="1" cellpadding="2">
 <tr>
 <td >
      &nbsp;</td>
  </tr>
  <tr> 
    <td   align="center" class="TableTitleText">�˵�����</td>
  </tr> <tr>
 <td >
      &nbsp;</td>
  </tr>

  <table  border="0" cellspacing="1" cellpadding="2">
        <tr>
      <td><logic:equal name="MenuSetupForm" property="hasInsert" value="true">
			  <html:button styleClass="button"  property="add" onclick="doAction('/menusetup/edit.do','toEditMenu','add')" value="�����˵�"/>
			</logic:equal>
          </td>
          
          <td><html:button styleClass="button"  property="hide" onclick="doAction('/menusetup/hide.do','toHideMenu','')" value="�˵�����"/>
		  <html:button styleClass="button"  property="del" onclick="doAction('/menusetup/del.do','toDelMenu','')" value="ɾ���˵�"/>
		  <html:hidden name="MenuSetupForm" property="nodeid"/>
		  <html:hidden name="MenuSetupForm" property="nodename"/>
			<html:hidden  name="MenuSetupForm" property="isFolder"/>
          </td>

        </tr>
      </table>
<table width="90%" align="center" class="InputFrameMain">
<tr>
<td>
<table align="center">
          <tr> 
            <td colspan="3" class="InputLabelCell"> <html:hidden name="MenuSetupForm" property="nodeid"/>
		  <html:hidden name="MenuSetupForm" property="nodename"/>
		   <html:hidden name="MenuSetupForm" property="vo.nodeid"/>
			<html:hidden  name="MenuSetupForm" property="isFolder"/>&nbsp;</td>

          </tr>
          <tr> 
            <td class="InputLabelCell" colspan="3">������Ŀ�� 
            <logic:notEqual name="MenuSetupForm" property="nodeid" value="0">
			<%
			 //���ι��޸�
			 //2004-10-18
			 //���GDP1.0��linux�������ϴ˴�������ҵ�bug
			 //����Ϊԭ���Ĵ���
			 //**************************************
			// <html:text styleClass="input" name="MenuSetupForm" style="width:200" property="vo.nodename"/>
			//***************************************
			 String voNodeName=form.getVo().getNodename();
             /*******************************************************
			 try{
                   voNodeName=new String(voNodeName.getBytes("ISO8859-1"),"GB2312");
			 }catch(Exception e){

			 }
             *******************************************************/
			 %>
			<input type="text" name="vo.nodename" value="<%=voNodeName%>" style="width:200" class="input">
			</logic:notEqual>
			<logic:equal name="MenuSetupForm" property="nodeid" value="0">
			��Ŀ¼
			</logic:equal></td>
          </tr>
		   <tr> 
            <td class="InputLabelCell" colspan="3">�˵���Ŀ��<bean:write name="MenuSetupForm" property="outOption" filter="false"/></td>
          </tr>
          <tr> 
            <td class="InputLabelCell">��ѡ�˵�</td>
            <td valign="middle" class="InputLabelCell">&nbsp;</td>
            <td class="InputLabelCell">��ѡ�˵�</td>
          </tr>
          <tr> 
            <td align="center" valign="top" class='InputAreaCell'> 
			 <INPUT TYPE="hidden" name = "preNodes" value ="">
			  <html:hidden name="MenuSetupForm" property="newNodes"/>
            <select name="addLeftListBox" id="addLeftListBox" multiple class="t12" style="width:120px; height:200px;" ondblclick="doSelOne('addLeftListBox','addRightListBox')">
<% 
    for (int i=0; i<hMenus.size(); i++){
		 vo = new UserMenuVO();
		 vo = (UserMenuVO)hMenus.get(i);
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
    for (int i=0; i<sMenus.size(); i++){
		 vo = new UserMenuVO();
		 vo = (UserMenuVO)sMenus.get(i);
    %>
         <option value="<%=vo.getNodeid()%>"><%=vo.getNodename()%></option>
   <% }     %>
              </select> </td>
			  <td class='InputAreaCell'> 
              <table border="0" cellpadding="1" cellspacing="1">
		       <tr>
		           <td align="center" valign="top" class="InputLabelCell">����</td>
		       </tr>
               <tr>
                  <td valign="top"> <img src="<%=context%>/jsp/homepage/images/but_cy_1.gif" width="22" height="17" onclick="addOptionMoveUp(document.forms[0].addRightListBox)"> 
                  </td>
              </tr>
				<tr>
                  <td valign="bottom"> <img src="<%=context%>/jsp/homepage/images/but_cy_2.gif" width="22" height="17" onclick="addOptionMoveDown(document.all('addRightListBox'))"> 
                  </td>
                </tr>
				<tr>
		           <td align="center" valign="top" class="InputLabelCell">����</td>
		       </tr>
				</table>
				</td>
          </tr>
          <tr> 
            <td height="22" colspan="3" align="center" valign="top" class='InputAreaCell'> 
              <p align="center"> 
                <!--<input type="button" value="�ύ" onclick="javascript:updateMenus();">-->
                <html:button styleClass="button"  property="del" onclick="javascript:updateMenus();" value="ȷ��"/>
              <html:button styleClass="button"  property="back" onclick="javascript:doBack();" value="����"/>
              </p></td>
          </tr>
          <tr>
            <td height="10" colspan="3" align="center" valign="top" class='InputAreaCell'>&nbsp;</td>
          </tr>
        </table>
  </td></tr></table> </td></tr></table>
</html:form>

<SCRIPT LANGUAGE="JavaScript">
<!--
function toRefreshLeft(){
    //******************************************************************
    if(navigator.appName == "Netscape"){
        window.parent.document.getElementById("left").contentDocument.reload(document.forms[0].elements["vo.nodeid"].value);
    } else {
	    window.parent.frames["left"].reload(document.forms[0].elements["vo.nodeid"].value);
    }
    //******************************************************************
}
function toRefreshModify(){
    //******************************************************************
    if(navigator.appName == "Netscape"){
        window.parent.document.getElementById("left").contentDocument.reload(document.forms[0].elements["vo.nodeid"].value);
	} else {
	    window.parent.frames["left"].modifyName(document.forms[0].elements["vo.nodeid"].value);
    }
    //******************************************************************
}
var aryMenus = new Array();
   <% 
	String isLoad =   (String) request.getAttribute("isLoad");
	boolean bRet = false;
	if ("true".equals(isLoad)){
		bRet = true;
	}
    for (int i=0; i<sMenus.size(); i++){
		 vo = new UserMenuVO();
		 vo = (UserMenuVO)sMenus.get(i);
		 out.println ("   aryMenus[" + i + "] = new Array();"); // ���¶�������")
		out.println ("    aryMenus[" + i + "][0] = \"" + vo.getNodeid() +"\";"); //�õ��û�ID
		out.println ("    aryMenus[" + i + "][1] = \"" + vo.getNodename() +"\";"); //�õ��û�����
   }%>
   var isLoad = <%=bRet%>;
   if (isLoad == true){


		var newNodes = document.forms[0].elements["newNodes"].value;

		var aNodes = newNodes.split(",");
		for (var idx= 0 ; idx<aNodes.length; idx++){
			//window.parent.frames["left"].delNode(aNodes[idx]);
		}
		setTimeout("toRefreshModify()",0);
		//setTimeout("toRefreshLeft()",2000);
		
	//window.parent.frames["left"].reload(document.forms[0].elements["vo.nodeid"].value);
	
	   //window.parent
   }
 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
//-->
</SCRIPT>


<IFRAME frameBorder=0 id=hiddendata src="" style="display:none"  >
</IFRAME>

</body>

</html>
