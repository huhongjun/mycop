<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%String context =request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style>
	body { background: white; color: black; }
	input { width: 120px; }
</style>
<script >
if (document.getElementById) {
	var tree = new WebFXTree('标准模板1');
	tree.setBehavior('classic');
	var a = new WebFXTreeItem('工作日志',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=1')");
    tree.add(a);

	a = new WebFXTreeItem('周汇报',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=2')");
    tree.add(a);

	a = new WebFXTreeItem('月汇报',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=3')");
    tree.add(a);

	a = new WebFXTreeItem('年汇报',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=4')");
    tree.add(a);

	a = new WebFXTreeItem('每周目标',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=5')");
    tree.add(a);

	a = new WebFXTreeItem('月度目标',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=6')");
    tree.add(a);

	a = new WebFXTreeItem('月度总结',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=7')");
    tree.add(a);

	a = new WebFXTreeItem('年度目标',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=8')");
    tree.add(a);

	a = new WebFXTreeItem('年度总结',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=9')");
    tree.add(a);

	a = new WebFXTreeItem('五年规划',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=10')");
    tree.add(a);

	a = new WebFXTreeItem('工作汇报',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=11')");
    tree.add(a);

	a = new WebFXTreeItem('授权书',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=12')");
    tree.add(a);
	document.write(tree);
}
var context = "<%=context%>";
	//var rti;
	var tree = new WebFXLoadTree("标准模版2",  context+"/infoservice/templatetree.do?action=doStaticTreeData","",null, null, null, null,'0');
	try{
		tree.expand();
	} catch(e) {
	}

	document.write(tree);
</script>
</head>
<body>
</body>
</html>