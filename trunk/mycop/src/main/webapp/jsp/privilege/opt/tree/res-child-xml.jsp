<%@ page contentType="text/xml; charset=GBK" %><%@ page import="java.util.*,com.gever.sysman.privilege.model.*"%><%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %><?xml version="1.0" encoding="GBK" ?>
<tree>
<logic:present name="childs">
<%
	String context= request.getContextPath();
	Collection c=(Collection)request.getAttribute("childs");
	StringBuffer sb=new StringBuffer();
           Resource res=null;
           Iterator it=c.iterator();
           while (it.hasNext()){
             res=(Resource)it.next();
                if (res.getChildNum()>0)
                  sb.append("<tree nodeid =\""+res.getId()+"\" text=\""+res.getName()+"\" src=\""+context+"/privilege/operationAction.do?action=getTreeChild&#38;resid="+res.getId()+"\" Script=\"null\" isFolder=\"true\"/>");
                else
                  sb.append("<tree  nodeid =\""+res.getId()+"\" text=\""+res.getName()+"\" src=\""+context+"/privilege/operationAction.do?action=list&#38;resid="+res.getId()+"\"  Script=\"selectItem('"+res.getId()+"')\"  isFolder=\"true\" />");
           }
		   out.print(sb.toString());
%>
</logic:present>
</tree>