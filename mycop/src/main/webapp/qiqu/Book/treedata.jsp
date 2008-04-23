<%@ page contentType="text/xml; charset=GBK" %>
<?xml version="1.0" encoding="GBK" ?>
<%
        response.setHeader("pragma", "no-cache");
			        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>
<%@ page import="java.util.*,com.gever.vo.BaseTreeVO"%>
	<tree>
<%	
	    List treeData = (ArrayList)request.getAttribute("treeData");
    String context =request.getContextPath();
    StringBuffer sb = new StringBuffer();
            for (int idx = 0; idx < treeData.size(); idx++) {
                BaseTreeVO vo = new BaseTreeVO();
                vo = (BaseTreeVO) treeData.get(idx);
                if ("1".equals(vo.getIsfolder())) {
                    sb.append("<tree nodeid =\"").append(vo.getNodeid());
                    sb.append("\" text=\"").append(vo.getNodename());
                    sb.append("\" src=\"").append(context);
                    sb.append(vo.getSrc()).append("\"");
                    sb.append(" action=\"").append(context);
                    sb.append(vo.getAction());
                    sb.append("\" isFolder=\"true\"  target=\"middle\" />");
                } else {
                    sb.append("<tree  nodeid =\"").append(vo.getNodeid());
                    sb.append("\" text=\"").append(vo.getNodename());
                    sb.append("\" src=\"").append(context);
                    sb.append(vo.getSrc()).append("\"");
                    sb.append(" action = \"").append(context);
                    sb.append(vo.getAction());
                    sb.append("\" target=\"middle\" isFolder=\"false\" />");

                }
            }out.print(sb.toString());%>
</tree>