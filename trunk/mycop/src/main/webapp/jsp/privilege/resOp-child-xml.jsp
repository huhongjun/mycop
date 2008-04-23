<%@ page contentType="text/xml; charset=GBK" %><%@ page import="java.util.*,com.gever.sysman.privilege.model.*"%><%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %><?xml version="1.0" encoding="GBK" ?>

<%//2004-11-19//libiao增加
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>

<tree>
<logic:present name="opts">
<%
	String context= request.getContextPath();
	Collection c=(Collection)request.getAttribute("opts");
	String resCode=(String)request.getAttribute("resCode");

	StringBuffer sb=new StringBuffer();
           Operation opt=null;
           Iterator it=c.iterator();
		   //System.out.println("eee");
           while (it.hasNext()){
             opt=(Operation)it.next();
               
                  sb.append("<tree nodeid =\""+opt.getCode()+"\" text=\""+opt.getName()+"("+opt.getCode()+")"+"\"  Script=\"selectItem('"+resCode+"','"+opt.getCode()+"')\" isFolder=\"false\"/>");
              
		   }
		   out.print(sb.toString());
%>
</logic:present>
</tree>