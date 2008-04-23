<%@ page contentType="text/xml; charset=GBK" %><%response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0);%><%@ page import="java.util.*,com.gever.sysman.organization.model.*"%><%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %><?xml version="1.0" encoding="GBK" ?>
<%//2004-11-19//libiao增加
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>
<tree>
<logic:present name="childs">
<%
	String context= request.getContextPath();
	Collection c=(Collection)request.getAttribute("childs");
	StringBuffer sb=new StringBuffer();
           Department dept=null;
           Iterator it=c.iterator();
           while (it.hasNext()){
             dept=(Department)it.next();
                if (dept.getChildNum()>0)
                  sb.append("<tree nodeid =\""+dept.getId()+"\" text=\""+dept.getName()+"\" src=\""+context+"/organization/DepartmentAction.do?action=getTreeChild&#38;departid="+dept.getId()+"\" Script=\"selectItem('"+dept.getId()+"')\"  isFolder=\"true\" />");
                else
                  sb.append("<tree  nodeid =\""+dept.getId()+"\" text=\""+dept.getName()+"\" src=\""+context+"/organization/DepartmentAction.do?action=getTreeChild&#38;departid="+dept.getId()+"\" Script=\"selectItem('"+dept.getId()+"','"+dept.getName()+"')\"  isFolder=\"true\" />");
           }
		   out.print(sb.toString());
%>
</logic:present>
</tree>