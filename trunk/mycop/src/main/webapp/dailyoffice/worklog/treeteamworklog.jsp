<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
%>
<%	String context =request.getContextPath();%>

<script >
if (document.getElementById) {
	var tree = new WebFXTree('��־��ѯ');
	tree.setBehavior('classic');
	var a = new WebFXTreeItem('�쵼�Ŷ�',"javascript:toRight('/dailyoffice/worklog/listteamworklog.do?action=toList&nodeid=0')");
    tree.add(a);

	a = new WebFXTreeItem('��ע�Ŷ�',"javascript:toRight('/dailyoffice/worklog/listteamworklog.do?action=toList&nodeid=1')");
    tree.add(a);

	a = new WebFXTreeItem('�ҵ��Ŷ�',"javascript:toRight('/dailyoffice/worklog/listteamworklog.do?action=toList&nodeid=2')");
    tree.add(a);
	
	
	document.write(tree);
}
</script>
</body>
</html>
