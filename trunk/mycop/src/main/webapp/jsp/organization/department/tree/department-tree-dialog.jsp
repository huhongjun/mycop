<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*,com.gever.sysman.organization.model.*"%>
<%String context=request.getContextPath();%>
<%
  Department root=(Department)request.getAttribute("rootNode");
  //tree root key
  String rootName=root.getName();
  String src=request.getContextPath()+"/organization/DepartmentAction.do?action=getTreeChild&departid=0";
  String action="javascript:empty()";
  String Script="selectItem('0')";
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
<jsp:include page="/jsp/jsp_css.jsp" />

<style type="text/css">

body {
	background:	white;
	color:		black;
}

</style>
</head>
<body OnUnload="window.returnValue = document.all.departid.value;window.close();">
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

try{
var tree = new WebFXLoadTree("<%=rootName%>","<%=src%>","<%=action%>","<%=Script%>", null, null, null,'<%=nodeid%>');
document.write(tree);
tree.expand();
}catch(e){}
function selectItem(item){
  document.all('departid').value=item;
  window.close();
}

</script>
<form  target="_self">
   <input type="hidden" id="departid" name="departid" value="<%=request.getParameter("value")%>">
</form >
</body>
</html>
