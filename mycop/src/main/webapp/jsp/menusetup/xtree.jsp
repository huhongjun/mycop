<%@ page contentType="text/xml; charset=GBK" %><?xml version="1.0" encoding="GBK" ?><%
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%><%@ page import="java.util.*,com.gever.web.homepage.vo.UserMenuVO,com.gever.web.menusetup.form.MenuSetupForm"%>
<tree>
 <%String context =request.getContextPath();
%>
<%
	MenuSetupForm mForm = (MenuSetupForm) request.getAttribute("MenuSetupForm");
	Collection c=(Collection)mForm.getCurNodeList();;
	StringBuffer sb = new StringBuffer();
        UserMenuVO vo = null;
        Iterator it = c.iterator();
        while (it.hasNext()) {
            vo = new UserMenuVO();
            vo = (UserMenuVO) it.next();
            vo.setNodeid(vo.getNodeid().trim());
			if ("1".equals(vo.getIshided()))
				continue;
            if ("1".equals(vo.getIsfolder())) {
                sb.append("<tree nodeid =\"").append(vo.getNodeid());
                sb.append("\" text=\"").append(vo.getNodename());
                sb.append("\" src=\"").append(context);
                sb.append("/menusetup/left.do?action=toXTree&#38;isFolder=1&#38;nodeid=");
                sb.append(vo.getNodeid()).append("\"");
                sb.append(" action=\"").append(context);
                sb.append("/menusetup/menusetup.do?action=toMenuSetup&#38;nodeid=");
                sb.append(vo.getNodeid()).append("&#38;nodename=");
                sb.append(vo.getNodename()).append("&#38;isFolder=1");
                sb.append("\" isFolder=\"true\"  target=\"middle\" />");

            } else {
                sb.append("<tree  nodeid =\"").append(vo.getNodeid());
                sb.append("\" text=\"").append(vo.getNodename());
				sb.append("\" src=\"").append(context);
                sb.append("/menusetup/left.do?action=toXTree&#38;isFolder=1&#38;nodeid=");
                sb.append(vo.getNodeid());
                sb.append("\"  action = \"").append(context);
                sb.append("/menusetup/edit.do?action=toEditMenu");
                sb.append("&#38;actionFlag=modify&#38;nodeid=");
                sb.append(vo.getNodeid()).append("&#38;nodename=");
                sb.append(vo.getNodename()).append("&#38;isFolder=0");
                sb.append("\" target=\"middle\" isFolder=\"false\" />");

            }
        }
		out.print(sb.toString());
%>
</tree>