<%@ page contentType="text/xml; charset=GB2312" %>
<?xml version="1.0" encoding="GB2312" ?>
<%
		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
%>
<%@ page import="java.util.*,com.gever.vo.BaseTreeVO"%>	<tree>
<%
List treeData = (ArrayList)request.getAttribute("treeData");//获取树型菜单中各项的列表
String context =request.getContextPath();//获取上下文路径，这里是goa
StringBuffer sb = new StringBuffer();
            for (int idx = 0; idx < treeData.size(); idx++) {//逐条循环并且生成树型结构
                BaseTreeVO vo = new BaseTreeVO();
                vo = (BaseTreeVO) treeData.get(idx);
                if ("1".equals(vo.getIsfolder())) {//如果是目录
                    sb.append("<tree nodeid =\"").append(vo.getNodeid());
                    sb.append("\" text=\"").append(vo.getNodename());
                    sb.append("\" src=\"").append(context);
                    sb.append(vo.getSrc()).append("\"");
                    sb.append(" action=\"").append(context);
                    sb.append(vo.getAction());
                    sb.append("\" isFolder=\"true\"  target=\"middle\" />");
                } else {//如果是叶子节点
                    sb.append("<tree  nodeid =\"").append(vo.getNodeid());
                    sb.append("\" text=\"").append(vo.getNodename());
                    sb.append("\" src=\"").append(context);
                    sb.append(vo.getSrc()).append("\"");
                    sb.append(" action = \"").append(context);
                    sb.append(vo.getAction());
                    sb.append("\" target=\"middle\" isFolder=\"false\" />");

                }
            }out.print(sb.toString());
%></tree>