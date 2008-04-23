<%@ page contentType="text/xml; charset=GBK" %><%@ page import="java.util.*,com.gever.sysman.privilege.model.*"%><%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %><?xml version="1.0" encoding="GBK" ?>
<%//2004-11-19//libiao增加
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>

<tree>
<logic:present name="listActionMethod">
<%
	String context= request.getContextPath();
	Collection c=(Collection)request.getAttribute("listActionMethod");
	StringBuffer sb=new StringBuffer();
	String uuid=(String)request.getAttribute("uuid");

	Iterator it=c.iterator();
	while (it.hasNext()){
		String methodName=(String)it.next();
		String action="javascript:void(0)";   
		sb.append("<tree  nodeid =\""+methodName+"\" text=\""+methodName+"\"   action=\""+action+"\" Script=\"selectItem('"+uuid+"','"+methodName+"')\"  isFolder=\"false\" />");
	}
	out.print(sb.toString());
%>
</logic:present>
</tree>