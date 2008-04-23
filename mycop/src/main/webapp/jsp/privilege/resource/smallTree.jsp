<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="java.util.*,com.gever.sysman.privilege.dao.*,com.gever.sysman.privilege.tree.*,com.gever.util.tree.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%String context =request.getContextPath();%>
<script type="text/javascript" src="<%=context%>/js/gdp.js"></script>

<%
    String exploreAction = (String)request.getAttribute("exploreAction");
    String viewAction = (String)request.getAttribute("viewAction");
    String formElement = (String)request.getAttribute("formElement");
    GeverTreeNode root = (GeverTreeNode)request.getAttribute("rootNode");
%>
<%!
	private void outputHTML(StringBuffer result, GeverTreeNode node, String exploreAction) {
		NodeObject nodeObject = node.getNodeObject();
		long id = nodeObject.getId();
		String name = nodeObject.getName();
		int level = node.getLevel();

		int vert = level - 1;
		vert = vert < 0 ? 0 : vert;

		if (level == 0) { //根目录
			result.append("<tr><td valign=top align=left>");
			result.append("<a href=\"javascript:selectNode('" + id + "','" + name + "');\">");
			result.append("<IMG border=0  src=\"images/ic_tree_start.gif\" vspace=0 width=16 height=16 align=bottom></a>");

			result.append("<a href=\"javascript:selectNode('" + id + "','" + name + "');\" class=\"treefolder\">(" + name + ")</a>");
			result.append("</td></tr>");

		} else { //非根目录
			result.append("<tr><td valign=bottom align=left nowrap>");

			result.append("<img src=\"images/empty.gif\" border=0 vspace=0 hspace=0 width=1 height=16 align=left>");

            if (node.getNextSibling() != null) {
                //竖线
                for (int i = 0; i < vert; i++) {
                    result.append("<img src=\"images/ic_tree_vert.gif\" border=0 vspace=0 hspace=0 width=16 height=16 align=left>");
                }
            } else {
                for (int i = 0; i < vert; i++) {
                    result.append("<img src=\"images/ic_tree_empty.gif\" border=0 vspace=0 hspace=0 width=16 height=16 align=left>");
                }
            }

			if (!node.isExplored()) { //未展开
				result.append("<a href=\"" + exploreAction + "&id=" + id + "\">");
				if (node.getNextSibling() == null) { //无后续节点
					result.append("<img src=\"images/ic_tree_pend.gif\" border=0 vspace=0 hspace=0 width=16 height=16 align=left>");
				} else {
					result.append("<img src=\"images/ic_tree_pkreuz.gif\" border=0 vspace=0 hspace=0 width=16 height=16 align=left>");
				}
			} else { //已展开
                long parentId = id;
                if (node.getParent() != null) {
                    parentId = ((GeverTreeNode)node.getParent()).getNodeObject().getId();
                }
				result.append("<a href=\"" + exploreAction + "&id=" + parentId + "\">");
				if (node.getNextSibling() == null) { //无后续节点
					result.append("<img src=\"images/ic_tree_mend.gif\" border=0 vspace=0 hspace=0 width=16 height=16 align=left>");
				} else {
					result.append("<img src=\"images/ic_tree_mkreuz.gif\" border=0 vspace=0 hspace=0 width=16 height=16 align=left>");
				}
			}
			result.append("</a>");

			result.append("<a href=\"javascript:selectNode('" + id + "','" + name + "');\">");
			result.append("<img src=\"images/ic_tree_folder.gif\" border=0 vspace=0 hspace=0 width=16 height=16 align=left>");
			result.append("</a>");

			result.append("&nbsp;<a href=\"javascript:selectNode('" + id + "','" + name + "');\" class=\"treefolder\">");
			result.append(name);
			result.append("</a>");
			result.append("</td></tr>");
		}

		Enumeration enum = node.children();
		while (enum.hasMoreElements()) {
			GeverTreeNode temp = (GeverTreeNode)enum.nextElement();
			outputHTML(result, temp, exploreAction);
		}
	}
%>
<%
    StringBuffer result = new StringBuffer();
    outputHTML(result,root,exploreAction);
%>
<html>

<head><META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=GB2312">

<link rel=stylesheet type="text/css" href="images/format.css">
<script language=JavaScript>
<!--
function selectNode(id,name){
    window.opener.self.document.all('tempValue').value = id;
    window.opener.self.document.all('tempDescription').value = name;
    window.opener.copySelection("<%= formElement%>");
}
//-->
</script>
</head>

<body bgcolor="#ffffff">

<!-- begin of folder tree -->
<table cellspacing=0 cellpadding=0 border=0>
<%= result.toString()%>
</table>
<!-- end of folder tree -->
</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>