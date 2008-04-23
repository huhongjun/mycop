<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<head>
</head>
 <SCRIPT LANGUAGE="JavaScript">
 <!--
	//提交前置检查
	function checkInput(){		
		var strMems = "";
		var frmObj = document.forms[0];
		var iCount =0;
		for(var i =0; i<frmObj.elements.length; i++){
			elObj = frmObj.elements[i];
			strObjName = elObj.id;
			//alert ("name=" +strObjName);
			intlocal= strObjName.indexOf("UserID");
			if (intlocal>=0){
				if (elObj.checked == true){
					++iCount;
					strMems =strMems+ elObj.value +","
					//strMems = "," + strMems + elObj.value;
				}
			}
		
		}
		if(iCount<=0){
			alert("请选择一个人员");
			return false;
		}
		frmObj.elements["vo.team_member"].value=strMems;	
		frmObj.elements["vo.team_type"].value=document.forms[0].teamtype.value;
        frmObj.elements["teamtype"].value=document.forms[0].teamtype.value;
		frmObj.action = addPara(frmObj.action,"action=toSave");		
		frmObj.submit();
	}
	//因结构性扩展或收缩及细项资料
	function subItems(strDept,isOpen){
		var elObj;
		var lenth= document.all.length;
		if (document.all( strDept) == null ){
				return;
		}
		var lenth= document.all.length;
		for(var i=1; i<lenth;i++){
			elObj = document.all[i];
			if (elObj.id=="" || elObj==null || elObj=="undefined" || (elObj.id).length<4)
				continue;
			
			if (elObj.pid == strDept ){
				elObj.style.display=isOpen;
				if (document.getElementById(replace(elObj.id,"trBM","img")) !=null){
					if (isOpen=="none"){	
						document.getElementById(replace(elObj.id,"trBM","img")).src=	"<%=context%>/images/menu_close.gif";
					}	else {
						document.getElementById(replace(elObj.id,"trBM","img")).src=
						"<%=context%>/images/menu_open.gif";
					}
				}
				var strRY = replace(elObj.id,"trBM","trRY");
				try{
					if (document.getElementById(strRY) !=null) {
						document.getElementById(strRY).style.display=isOpen;
					}
				} catch (e){}
				subItems(elObj.id,isOpen);
			}
		}
	}

	//因结构性扩展或收缩及细项资料
	function openItem(strDept){
		var frmObj = document.forms[0];
		if (document.getElementById("trBM" + strDept) ==null) {
			return false;
		}
		var elObj;
		var strMode;
		var strImg =document.getElementById("img" + strDept).src;
		if (strImg.indexOf("close.gif")>=0){
			strMode = "block";
			document.getElementById("img" + strDept).src="<%=context%>/images/menu_open.gif";
		}
		else{
			strMode="none";
			document.getElementById("img" + strDept).src="<%=context%>/images/menu_close.gif";
		}
		if(document.getElementById("trRY"+strDept)!=null){
			document.getElementById("trRY"+strDept).style.display=strMode;
		}
		subItems("trBM"+strDept,strMode);

	}

 //-->
 </SCRIPT>
<body class="bodybg">
<html:form  action="/dailyoffice/worklog/setteamworklog"  >
<html:hidden property="vo.team_member" />
<html:hidden property="vo.team_type" />
<html:hidden name="TeamWorkLogSetForm" property="teamtype" />
<table width="98%" class="tableframe"  align="center" cellpadding="0" cellspacing="0">	
  	<tr>
    	  <td class="t12" colspan="5"><br></td>
    </tr>
	<tr align="center">
          <td width="62%" class="TableTitleText" colspan="5">
		  <logic:equal name="TeamWorkLogSetForm" property="teamtype" value="0">
		  设置领导团队
		  </logic:equal>
		  <logic:equal name="TeamWorkLogSetForm" property="teamtype" value="1">
		  设置关注团队
		  </logic:equal>
		  <logic:equal name="TeamWorkLogSetForm" property="teamtype" value="2">
		  设置我的团队
		  </logic:equal>
		  </td>
  	</tr>
  	<tr>
           <td class="search" colspan="8" align="right">
           <html:button property="set" value="保存" styleClass="button" onclick="checkInput()"/>
		   
		   <html:button property="back" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/>     
		   
		   <!--
		   <html:button property="back" value="返回" styleClass="button" onclick="javascript:history.back(-1)"/>
		   -->
		   </td>
	</tr>
	<tr>
    	  <td class="t12" colspan="5"><br></td>
    </tr>
<tr>
   <td colspan="8">
   <table width="100%" border="0" cellspacing="0" cellpadding="2">
	<tr align="center" class="listtitle">
		<td class="listcellTitle" align="left"><nobr>部门名称</nobr></td>
		<!--
		<td class="listcellTitle" width="80"><nobr>本部门人数</nobr></td>
		<td class="listcellTitle" width="80"><nobr>下级部门数</nobr></td>
		<td class="listcellTitle" width="80"><nobr>下级部门人数</nobr></td>
		<td class="listcellTitle" width="80"><nobr>部门主管</nobr></td>
		-->
	</tr>

	      <bean:write name="TeamWorkLogSetForm" property="outPutHtml" filter="false"/>
        <tr>
            <td height=10 class="listtail" colspan=5>         
            </td>
        </tr>
    </table>
  </td>
</tr>
</table>
</html:form>

</body>
</html>
