<%@ page contentType="text/xml; charset=GBK" %><%@ page import="java.util.*,com.gever.sysman.privilege.model.*"%><%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %><?xml version="1.0" encoding="GBK" ?>
<%//2004-11-19//libiao增加
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>

<tree>
<logic:present name="lstActionPaths">
<%
	String context= request.getContextPath();
	Collection lstActionPaths = (ArrayList)request.getAttribute("lstActionPaths");
	Collection lstClassTypes = (ArrayList)request.getAttribute("lstClassTypes");
	Object[] listActionPaths= lstActionPaths.toArray();
	Object[] listClassTypes= lstClassTypes.toArray();
	StringBuffer sb=new StringBuffer();
	String uuid=(String)request.getAttribute("uuid");

	for(int i=0;i<listActionPaths.length;i++){
		String strutsActionName=(String)listActionPaths[i];
		String uuid2=uuid+strutsActionName;
		String dataSrc=request.getContextPath()+"/privilege/getConfigAction.do?action=getClassTree&amp;classname="+listClassTypes[i]+"&amp;uuid="+uuid2;
	
		String action="javascript:void(0)";   
		sb.append("<tree  src=\""+dataSrc+"\" nodeid =\""+strutsActionName+"\" text=\""+strutsActionName+"\"   action=\""+action+"\" Script=\"actionPathOnClick('"+uuid2+"')\"  isFolder=\"true\" />");
	}
	out.print(sb.toString());
%>
</logic:present>
</tree>