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
		out.println ("      ary[" + idx + "] = new Array();"); // ���¶�������")
		out.println ("      ary[" + idx + "][0] = \"" + vo.getNodeid() +"\";"); //�õ��û�ID
		out.println ("      ary[" + idx + "][1] = \"" + vo.getNodename() +"\";"); //�õ��û�����
		vo = null; //����������
}
%>

window.parent.reLoadData(ary);
</SCRIPT>