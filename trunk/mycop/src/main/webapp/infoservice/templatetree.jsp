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
	var tree = new WebFXTree('��׼ģ��1');
	tree.setBehavior('classic');
	var a = new WebFXTreeItem('������־',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=1')");
    tree.add(a);

	a = new WebFXTreeItem('�ܻ㱨',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=2')");
    tree.add(a);

	a = new WebFXTreeItem('�»㱨',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=3')");
    tree.add(a);

	a = new WebFXTreeItem('��㱨',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=4')");
    tree.add(a);

	a = new WebFXTreeItem('ÿ��Ŀ��',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=5')");
    tree.add(a);

	a = new WebFXTreeItem('�¶�Ŀ��',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=6')");
    tree.add(a);

	a = new WebFXTreeItem('�¶��ܽ�',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=7')");
    tree.add(a);

	a = new WebFXTreeItem('���Ŀ��',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=8')");
    tree.add(a);

	a = new WebFXTreeItem('����ܽ�',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=9')");
    tree.add(a);

	a = new WebFXTreeItem('����滮',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=10')");
    tree.add(a);

	a = new WebFXTreeItem('�����㱨',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=11')");
    tree.add(a);

	a = new WebFXTreeItem('��Ȩ��',"javascript:toRight('/infoservice/template.do?action=toList&nodeid=12')");
    tree.add(a);
	document.write(tree);
}
var context = "<%=context%>";
	//var rti;
	var tree = new WebFXLoadTree("��׼ģ��2",  context+"/infoservice/templatetree.do?action=doStaticTreeData","",null, null, null, null,'0');
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