<%@ page contentType="text/html; charset=GB2312" %>


<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="java.util.*,com.gever.web.homepage.vo.UserMenuVO"%>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%@ taglib uri="/WEB-INF/taglib/gever-perm" prefix="perm" %>
<%
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>
<%	String context =request.getContextPath();%>
<jsp:include page="/jsp/jsp_css.jsp" />
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
<title>菜单定制</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<gdp:context/>/common/xtreeEx/xtree.js"></script>
<script type="text/javascript" src="<gdp:context/>/common/xtreeEx/xmlextras.js"></script>
<script type="text/javascript" src="<gdp:context/>/common/xtreeEx/xloadtree.js"></script>

<jsp:include page="/jsp/jsp_css.jsp" />
</head>
<body >

<script type="text/javascript">

/// XP Look

/// XP Look
webFXTreeConfig.rootIcon		= "<%=context%>/common/xtreeEx/images/xp/folder.png";
webFXTreeConfig.openRootIcon	= "<%=context%>/common/xtreeEx/images/xp/openfolder.png";
webFXTreeConfig.folderIcon		= "<%=context%>/common/xtreeEx/images/xp/folder.png";
webFXTreeConfig.openFolderIcon	= "<%=context%>/common/xtreeEx/images/xp/openfolder.png";
webFXTreeConfig.fileIcon		= "<%=context%>/common/xtreeEx/images/xp/file.png";
webFXTreeConfig.lMinusIcon		= "<%=context%>/common/xtreeEx/images/xp/Lminus.png";
webFXTreeConfig.lPlusIcon		= "<%=context%>/common/xtreeEx/images/xp/Lplus.png";
webFXTreeConfig.tMinusIcon		= "<%=context%>/common/xtreeEx/images/xp/Tminus.png";
webFXTreeConfig.tPlusIcon		= "<%=context%>/common/xtreeEx/images/xp/Tplus.png";
webFXTreeConfig.iIcon			= "<%=context%>/common/xtreeEx/images/xp/I.png";
webFXTreeConfig.lIcon			= "<%=context%>/common/xtreeEx/images/xp/L.png";
webFXTreeConfig.tIcon			= "<%=context%>/common/xtreeEx/images/xp/T.png";
webFXTreeConfig.blankIcon       = "<%=context%>/common/xtreeEx/images/xp/blank.png"

//var tree = new WebFXLoadTree("WebFXLoadTree", "tree1.xml");
//tree.setBehavior("classic");
var root = "0";
var context = "<%=context%>";
//var rti;
var tree = new WebFXLoadTree("用户菜单",  context+"/menusetup/left.do?action=toXTree&isFolder=1&nodeid=0","javascript:toRight()",null, null, null, null,'0');

//定制系统默认菜单--杨帆--2004-11-19 添加
<perm:defaultmenu rescode="GDP-CDDZ" optcode="MRCD">
var tree1 = new WebFXLoadTree("系统默认菜单",  context+"/menusetup/leftDefault.do?action=toXTree&isFolder=1&nodeid=0","javascript:toRightDefault()",null, null, null, null,'0');
</perm:defaultmenu>
//定制系统默认菜单--杨帆--2004-11-19 添加结束

try{
	tree.expand();
	<perm:defaultmenu rescode="GDP-CDDZ" optcode="MRCD">
	  tree1.expand(); //系统默认菜单树
	</perm:defaultmenu>

}catch(e){}
function reload(nodeid){
/*
  var size=jnodes.length;
	if (nodeid == root){
		tree.reload();
		<perm:defaultmenu rescode="GDP-MRCD" optcode="ALL">
		  tree1.reload(); //系统默认菜单树
		</perm:defaultmenu>
		return;
	}
	for(i=0;i<size;i++){
	  if(jnodes[i].id==nodeid){
		 
		jnodes[i].node.reload();
		jnodes[i].node.expand();
	  }
	}
*/
    tree.reload();
   <perm:defaultmenu rescode="GDP-CDDZ" optcode="MRCD">
	  tree1.reload(); //系统默认菜单树
   </perm:defaultmenu>
}

function modifyName(nodeid){
   var size=jnodes.length;
   	if (nodeid == root){
		tree.reload();
		return;
	}
   for(i=0;i<size;i++){
	  if(jnodes[i].id==nodeid){
		  try{
		      jnodes[i].node.parentNode.reload();
			//jnodes[i].node.reload();
			//jnodes[i].node.expand();
		  }catch(e){}
		
        
		//}
	  }
	}
}

function delNode(nodeid){
	var size=jnodes.length;
	for(i=0;i<size;i++){
		if(jnodes[i].id==nodeid){
			
			jnodes[i].node.remove();
		}
	}
}
//定制系统默认菜单--杨帆--2004-11-19 添加
function toRightDefault(){
	if(navigator.appName == "Netscape"){
        window.parent.document.getElementById("middle").contentDocument.location.href = context+"/menusetup/rightDefault.do?action=toHome&nodeid=-1";
	} else {
		window.parent.frames["middle"].location.href=context+"/menusetup/rightDefault.do?action=toHome&nodeid=-1";
	}
}
<perm:defaultmenu rescode="GDP-CDDZ" optcode="MRCD">
  document.write(tree1);
</perm:defaultmenu>
//定制系统默认菜单--杨帆--2004-11-19 添加结束

function toRight(){
    //******************************************************************
    if(navigator.appName == "Netscape"){
        window.parent.document.getElementById("middle").contentDocument.location.href = context+"/menusetup/right.do?action=toHome&nodeid=-1";
	} else {
        window.parent.frames["middle"].location.href=context+"/menusetup/right.do?action=toHome&nodeid=-1";
    }
    //******************************************************************
}
document.write(tree);


 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
</script>
 
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
//-->
</SCRIPT>
