<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="java.util.*,com.gever.web.homepage.vo.UserMenuVO"%>
<%
        response.setHeader("pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);%>
<SCRIPT LANGUAGE="JavaScript">
<!--
	var ary=new Array();
	//alert();
//-->
<%	List curMenus = (ArrayList)request.getAttribute("curMenus");%>
<%
	for (int idx = 0 ; idx < curMenus.size(); idx++){
		UserMenuVO vo = new UserMenuVO();
		vo = (UserMenuVO)curMenus.get(idx);
		out.println ("      ary[" + idx + "] = new Array();"); // 重新定义数组")
		out.println ("      ary[" + idx + "][0] = \"" + vo.getNodeid() +"\";"); //得到用户ID
		out.println ("      ary[" + idx + "][1] = \"" + vo.getNodename() +"\";"); //得到用户名称
		vo = null; //回收垃圾箱
}
%>

window.parent.reLoadData(ary);
</SCRIPT>