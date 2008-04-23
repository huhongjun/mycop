<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %>
<%@ taglib uri="/WEB-INF/taglib/gever-menu" prefix="gmenu" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<%@ page import="java.util.*" %>



<html>
<head>
<script type="text/javascript" src="<gdp:context/>/common/xtreeEx/xtree.js"></script>
<script type="text/javascript" src="<gdp:context/>/common/xtreeEx/xmlextras.js"></script>
<script type="text/javascript" src="<gdp:context/>/common/xtreeEx/xloadtree.js"></script>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<jsp:include page="/jsp/jsp_css.jsp" />
<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT">

<title></title>
<!-- The xtree script file -->
<div style="position: absolute; width: 100%; top: 0px; left: 0px; height: 100%; padding: 0px; " id="divEl">




<script >
/// XP Look
webFXTreeConfig.rootIcon		= "<gdp:context/>/common/xtreeEx/images/xp/folder.png";
webFXTreeConfig.openRootIcon	= "<gdp:context/>/common/xtreeEx/images/xp/openfolder.png";
webFXTreeConfig.folderIcon		= "<gdp:context/>/common/xtreeEx/images/xp/folder.png";
webFXTreeConfig.openFolderIcon	= "<gdp:context/>/common/xtreeEx/images/xp/openfolder.png";
webFXTreeConfig.fileIcon		= "<gdp:context/>/common/xtreeEx/images/xp/file.png";
webFXTreeConfig.lMinusIcon		= "<gdp:context/>/common/xtreeEx/images/xp/Lminus.png";
webFXTreeConfig.lPlusIcon		= "<gdp:context/>/common/xtreeEx/images/xp/Lplus.png";
webFXTreeConfig.tMinusIcon		= "<gdp:context/>/common/xtreeEx/images/xp/Tminus.png";
webFXTreeConfig.tPlusIcon		= "<gdp:context/>/common/xtreeEx/images/xp/Tplus.png";
webFXTreeConfig.iIcon			= "<gdp:context/>/common/xtreeEx/images/xp/I.png";
webFXTreeConfig.lIcon			= "<gdp:context/>/common/xtreeEx/images/xp/L.png";
webFXTreeConfig.tIcon			= "<gdp:context/>/common/xtreeEx/images/xp/T.png";
webFXTreeConfig.blankIcon       = "<gdp:context/>/common/xtreeEx/images/xp/blank.png"


//if (document.getElementById) {
	var tree = new WebFXTree('Struts配置根目录', null,"javascript:exportDDL('/');", null, null, null);
	
   
	//tree.setBehavior('classic');
   //tree.add(new WebFXTree('配置根目录'));
  <%
Collection configs = (ArrayList)request.getAttribute("configs");

Object[] arrConfigs= configs.toArray();

 for(int i=0;i<arrConfigs.length;i++){
   String nodeName=(String)arrConfigs[i];//"config/privilege";
   String subPath=nodeName.substring("config/".length());
  
  String src=request.getContextPath()+"/"+subPath+"/getConfigAction.do?action=getConfigNode&uuid="+nodeName;
  String action="javascript:void(0)";
  String Script="javascript:exportDDL('" + nodeName + "')";
  String nodeid="0";

   %>
treeObj=new WebFXLoadTreeItem('<%=nodeName%>', "<%=src%>","<%=action%>","<%=Script%>", null, null, null,'<%=nodeid%>');
  treeObj.isFolder=true;
  tree.add(treeObj);

	<%
  }
  %>


//}
document.write(tree);

</script>
</div>
</head>
<body class="bodybg"><SCRIPT LANGUAGE="JavaScript">
<!--
var ns = navigator.appName == "Netscape";
function selectItem(strutActionPath,methodName){
//覆盖默认方法
    if(ns) var pForm = window.parent.centerFrame.document.forms[0];
    else   var pForm = parent.centerFrame.permissionMapForm;
	pForm.actionpath.value = strutActionPath;
	pForm.methodname.value = methodName;
}

function actionPathOnClick(actionPath){
    if(ns){
        window.parent.centerFrame.document.forms[0].exportddl.value = actionPath;
        window.parent.bottomFrame.location="<gdp:context/>/privilege/permissionMapAction.do?action=toMethodList&actionPath="+actionPath;
	} else {
        parent.centerFrame.permissionMapForm.exportddl.value=actionPath;
	    parent.bottomFrame.location="<gdp:context/>/privilege/permissionMapAction.do?action=toMethodList&actionPath="+actionPath;
    }
}

function exportDDL(actionPath){
    if(ns) window.parent.centerFrame.document.forms[0].exportddl.value = actionPath;
	else parent.centerFrame.permissionMapForm.exportddl.value = actionPath;
}
//-->
</SCRIPT>

</body>
</html>