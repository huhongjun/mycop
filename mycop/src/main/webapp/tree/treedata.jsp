<%@ page contentType="text/xml; charset=GBK" %>
<?xml version="1.0" encoding="GBK" ?>
<%
		response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
%>
<%@ page import="java.util.*,com.gever.vo.BaseTreeVO,com.gever.util.*"%>	
<tree>
<%
List treeData = (ArrayList)request.getAttribute("treeData");
String context =request.getContextPath();
StringBuffer sb = new StringBuffer();
String traget = "";
String action = "";
            for (int idx = 0; idx < treeData.size(); idx++) {
                BaseTreeVO vo = new BaseTreeVO();
                vo = (BaseTreeVO) treeData.get(idx);
		traget = vo.getTarget();
		action=vo.getAction();
                if ("1".equals(vo.getIsfolder())) {
                    sb.append("<tree nodeid =\"").append(vo.getNodeid());
                    sb.append("\" text=\"").append(vo.getNodename());
                    sb.append("\" src=\"").append(context);
                    sb.append(vo.getSrc()).append("\"");
			
			if(!StringUtils.isNull(action)){
		 sb.append(" action=\"").append(context);
		 sb.append(action).append("\"");
		 
		 if (StringUtils.isNull(vo.getTarget())){
			traget = "middle";
		}
		sb.append(" target=\"").append(traget).append("\" ");

			}else{
		 sb.append(" action=\"#\"" );	
			}

                    sb.append(" isFolder=\"true\" />");
                } else {
                    sb.append("<tree  nodeid =\"").append(vo.getNodeid());
                    sb.append("\" text=\"").append(vo.getNodename());
                    sb.append("\" src=\"").append(context);
                    sb.append(vo.getSrc()).append("\"");
			
			if(!StringUtils.isNull(action)){
		 sb.append(" action=\"").append(context);
		 sb.append(action).append("\"");

		if (StringUtils.isNull(vo.getTarget())){
			traget = "middle";
		}
		sb.append(" target=\"").append(traget).append("\" ");

			}else{
		 sb.append(" action=\"#\"" );	
			}
			
			
                    sb.append(" isFolder=\"false\" />");

                }
            }out.print(sb.toString());
%></tree>