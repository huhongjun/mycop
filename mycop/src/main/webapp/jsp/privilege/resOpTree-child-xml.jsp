<%@ page contentType="text/xml; charset=GBK" %><%@ page import="java.util.*,com.gever.sysman.privilege.model.*"%><%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %><?xml version="1.0" encoding="GBK" ?>

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
           Resource res=null;
           Iterator it=c.iterator();
           while (it.hasNext()){
             res=(Resource)it.next();
                if (res.getChildNum()>0)
                  sb.append("<tree nodeid =\""+res.getId()+"\" text=\""+res.getName()+"\" src=\""+context+"/privilege/getResOpTreeAction.do?action=getTreeChild&#38;resid="+res.getId()+"\"  isFolder=\"true\"/>");
                else{

                  sb.append("<tree  nodeid =\""+res.getId()+"\" text=\""+res.getName()+"("+res.getCode()+")"+"\" src=\""+context+"/privilege/getResOpTreeAction.do?action=getResOpByResID&#38;resid="+res.getId()+"\"    isFolder=\"true\" />");
                }
		   }
		   out.print(sb.toString());
%>
</logic:present>
</tree>