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
	var tree = new WebFXTree('�ڲ�����');
	tree.setBehavior('classic');
	var a = new WebFXTreeItem('������Ϣ',"javascript:toRight('/dailyoffice/message/listmessage.do?action=toList&nodeid=0')");
    tree.add(a);
    
    var a = new WebFXTreeItem('δ����Ϣ',"javascript:toRight('/dailyoffice/message/listmessage.do?action=toList&nodeid=1')");
    tree.add(a);
    
    var a = new WebFXTreeItem('�Ѷ���Ϣ',"javascript:toRight('/dailyoffice/message/listmessage.do?action=toList&nodeid=2')");
    tree.add(a);
	
	document.write(tree);
}
</script>
</head>
<body>
</body>
</html>