<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<HTML>
<HEAD>
<TITLE>选择一个组织 </TITLE>
<!-- The xtree script file -->
<script src="<gdp:context/>/common/xtreeEx/xtree.js"></script>
<SCRIPT LANGUAGE="JavaScript">
<!--
function select(){
  window.opener.thisForm.fdeparment.value='总公司';
}
//-->
</SCRIPT>

<!-- Modify this file to change the way the tree looks -->
<jsp:include page="/jsp/jsp_css.jsp" />

<style>
	body { background: white; color: black; }
	input { width: 120px; }
</style>

<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>

<BODY>
<SCRIPT LANGUAGE="JavaScript">
<!--
<%
 int i,j,k;
 out.println("if (document.getElementById) {");
 out.println("var tree = new WebFXTree('Root')");
 out.println("tree.setBehavior('classic')");
 out.println("var a;");
for(i=0;i<5;i++){
 out.println("var a"+i+" = new WebFXTreeItem('"+i+"')");
 out.println("tree.add(a"+i+");");
 for(j=0;j<3;j++){
   out.println("var b"+i+""+j+" = new WebFXTreeItem('"+i+"."+j+"')");
   out.println("a"+i+".add(b"+i+""+j+");");
   for(k=0;k<2;k++){
	out.println("var c"+i+""+j+""+k+" = new WebFXTreeItem('"+i+"."+j+"."+k+"'),select()");
	out.println("b"+i+""+j+".add(c"+i+""+j+""+k+");");
   }
 }
}
out.println("}");
out.println("document.write(tree)");
%>
//-->
</SCRIPT>
</BODY>
</HTML>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
