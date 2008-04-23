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
	var tree = new WebFXTree('内部短信');
	tree.setBehavior('classic');
	var a = new WebFXTreeItem('所有消息',"javascript:toRight('/dailyoffice/message/listmessage.do?action=toList&nodeid=0')");
    tree.add(a);
    
    var a = new WebFXTreeItem('未读消息',"javascript:toRight('/dailyoffice/message/listmessage.do?action=toList&nodeid=1')");
    tree.add(a);
    
    var a = new WebFXTreeItem('已读消息',"javascript:toRight('/dailyoffice/message/listmessage.do?action=toList&nodeid=2')");
    tree.add(a);
	
	document.write(tree);
}
</script>
</head>
<body>
</body>
</html>