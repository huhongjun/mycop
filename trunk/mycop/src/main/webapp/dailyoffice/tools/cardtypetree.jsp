<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>
<%	String context =request.getContextPath();%>

<script type="text/javascript">

	var context = "<%=context%>";
	//var rti;
	var tree = new WebFXLoadTree("我的名片夹",  context+"/dailyoffice/tools/treeCardType.do?action=doTreeData","javascript:toCardRight('/dailyoffice/tools/listCardcaseType.do')",null, null, null, null,'0');
	try{
		tree.expand();
	} catch(e) {
	}

	document.write(tree);
	/*try{
		document.body.onmousedown=function(){window.parent.window.parent.hideAllMenu();	}
	} catch(e) {
	}*/
</script>
<SCRIPT LANGUAGE="JavaScript">
<!--
//根目录需要到右边的节点
function toCardRight(url){
	var date = new Date();
	var rnd="rnd="+date.getTime();
	url = addPara(url,rnd);
	window.parent.frames["cardmiddle"].location.href=context+url;
}
//-->
</SCRIPT>
</body>
</html>
