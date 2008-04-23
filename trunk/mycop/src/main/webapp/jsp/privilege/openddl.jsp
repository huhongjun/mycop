<%@ page contentType="text/html; charset=GB2312" import="java.util.*"%><%
response.setContentType("application/octet-stream");
Collection ddllist=(Collection)request.getAttribute("ddllist");
String contentDisposition=null;
contentDisposition = contentDisposition != null ? contentDisposition : "attachment;";
response.setHeader("Content-Disposition", contentDisposition + " filename=DB2DDL.ddl");
//String ddl=(String)request.getAttribute("ddl");
Iterator it = ddllist.iterator();
    while(it.hasNext()) {
       out.println(it.next().toString());
    }

%>
