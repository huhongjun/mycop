<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page contentType="text/html; charset=GB2312" %>
<%	String context =request.getContextPath();%>
<jsp:include page="/jsp/jsp_css.jsp" />
<script type="text/javascript" src="<%=context%>/js/gdp.js"></script>
<%@ page import="java.util.*,com.gever.sysman.privilege.model.*"%>
<%
  Resource root=(Resource)request.getAttribute("rootNode");
  if (root == null){
    return;
  }
  //tree root key
  String rootName=root.getName();
  String src=request.getContextPath()+"/privilege/operationAction.do?action=getTreeChild&resid=0";
  String action="javascript:void(0)";
  String Script="selectRoot()";
  String nodeid="0";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
<title>XLoadTree Demo (WebFX)</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xtree.js"></script>
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xmlextras.js"></script>
<script type="text/javascript" src="<%=context%>/common/xtreeEx/xloadtree.js"></script>

<style type="text/css">

body {
	background:	white;
	color:		black;
}

</style>
</head>
<body>
<!--p><button onclick="modify('411','yes')">Reload Item</button></p-->
<script type="text/javascript">

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


var tree = new WebFXLoadTree("<%=rootName%>", "<%=src%>","<%=action%>","<%=Script%>", null, null, null,'<%=nodeid%>');
try{
document.write(tree);
tree.expand();
jnodes[jnodes.length].node=tree;
jnodes[jnodes.length].id='<%=nodeid%>';
} catch(e) {
}

function reload(nodeid){
  try{
  var size=jnodes.length;
    for(i=0;i<size;i++){
	  if(jnodes[i].id==nodeid){
		jnodes[i].node.reload();
		jnodes[i].node.expand();
		}
	}
  } catch(e){
  }
}
function selectItem(item){
  document.all('resid').value=item;
  document.forms[0].submit();
}
function selectRoot() {
 document.all('resid').value="";
  document.forms[0].submit();
}
</script>
 <form action="<%=context%>/privilege/<%=request.getParameter("src")%>.do" target="main">
   <input type="hidden" name="action" value="list">
   <input type="hidden" id="resid" name="resid" value="0">
 </form >
 <SCRIPT LANGUAGE="JavaScript">
<!--
 try{
document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	
} }catch(e){
}
//-->
</SCRIPT> 
</body>
</html>
