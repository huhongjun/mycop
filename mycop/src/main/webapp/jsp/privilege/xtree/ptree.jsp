<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*,com.gever.sysman.privilege.dao.*,com.gever.sysman.privilege.model.*,com.gever.sysman.privilege.model.impl.*"%>

<%
  Resource root=(Resource)request.getAttribute("rootNode");
  Resource curRes=(Resource)request.getAttribute("curRes");
  Collection childs=(Collection)request.getAttribute("childs");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
<title>XLoadTree Demo (WebFX)</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/common/xtree/xtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/xtree/xmlextras.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/xtree/xloadtree.js"></script>
<jsp:include page="/jsp/jsp_css.jsp" />

<style type="text/css">

body {
	background:	white;
	color:		black;
}

</style>
</head>
<body>

<script type="text/javascript">

/// XP Look
webFXTreeConfig.rootIcon		= "<%=request.getContextPath()%>/common/xtree/images/xp/folder.png";
webFXTreeConfig.openRootIcon	= "<%=request.getContextPath()%>/common/xtree/images/xp/openfolder.png";
webFXTreeConfig.folderIcon		= "<%=request.getContextPath()%>/common/xtree/images/xp/folder.png";
webFXTreeConfig.openFolderIcon	= "<%=request.getContextPath()%>/common/xtree/images/xp/openfolder.png";
webFXTreeConfig.fileIcon		= "<%=request.getContextPath()%>/common/xtree/images/xp/file.png";
webFXTreeConfig.lMinusIcon		= "<%=request.getContextPath()%>/common/xtree/images/xp/Lminus.png";
webFXTreeConfig.lPlusIcon		= "<%=request.getContextPath()%>/common/xtree/images/xp/Lplus.png";
webFXTreeConfig.tMinusIcon		= "<%=request.getContextPath()%>/common/xtree/images/xp/Tminus.png";
webFXTreeConfig.tPlusIcon		= "<%=request.getContextPath()%>/common/xtree/images/xp/Tplus.png";
webFXTreeConfig.iIcon			= "<%=request.getContextPath()%>/common/xtree/images/xp/I.png";
webFXTreeConfig.lIcon			= "<%=request.getContextPath()%>/common/xtree/images/xp/L.png";
webFXTreeConfig.tIcon			= "<%=request.getContextPath()%>/common/xtree/images/xp/T.png";

//var tree = new WebFXLoadTree("WebFXLoadTree", "tree1.xml");
//tree.setBehavior("classic");

//var rti;
var tree = new WebFXTree("<%=root.getName()%>");
<%
 Iterator it =childs.iterator();
 while (it.hasNext()) {
	 Resource res=(Resource)it.next();
 %>
tree.add(new WebFXLoadTreeItem("<%=res.getName()%>", "<%=request.getContextPath()%>/permissionAction.do?action=xtree&resid=<%=res.getId()%>",null, null, null, null,<%=res.getId()%>));

<%
 }
%>

document.write(tree);
function toAction(nodeid){
var url="api.html";
self.location=url+"?action="+nodeid;
}
</script>

</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
