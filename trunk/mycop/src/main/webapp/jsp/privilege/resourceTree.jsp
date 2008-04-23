<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title></title>
<!-- The xtree script file -->
<script src="<gdp:context/>/js/xtree_privilege.js"></script>

<!-- Modify this file to change the way the tree looks -->
<jsp:include page="/jsp/jsp_css.jsp" />

<style>
	body { background: white; color: black; }
	input { width: 120px; }
</style>

</head>
<body background="#EEEEEE">


<SCRIPT LANGUAGE="JavaScript">
<!--
function toAction(obj){
 parent.main.location=obj;
}
<%
 out.println("if (document.getElementById) {");
 out.println("var tree = new WebFXTree('资源管理')");
 out.println("tree.setBehavior('classic')");
 out.println("var a;");
 out.println("var list='"+request.getContextPath()+"/privilege/resourceAction.do?action=list';");
 out.println("var a= new WebFXTreeItem('资源列表','javascript:toAction(list)')");
 out.println("tree.add(a);");
 out.println("var b;");
 out.println("var restree='"+request.getContextPath()+"/privilege/resourceAction.do?action=tree';");
 out.println("var b= new WebFXTreeItem('资源树','javascript:toAction(restree)')");
 out.println("tree.add(b);");
 out.println("}");
out.println("document.write(tree)");
%>
//-->
</SCRIPT>

</body>


<script>
function addNode() {
	if (tree.getSelected()) {
		tree.getSelected().add(new WebFXTreeItem('New'));
	}
}

function addNodes() {
	if (tree.getSelected()) {
		var foo = tree.getSelected().add(new WebFXTreeItem('New'));
		var bar = foo.add(new WebFXTreeItem('Sub 1'));
		var fbr = foo.add(new WebFXTreeItem('Sub 2'));
	}
}

function delNode() {
	if (tree.getSelected()) {
		tree.getSelected().remove();
	}
}
</script>
</html>
