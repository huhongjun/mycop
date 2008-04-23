<%@ page contentType="text/html; charset=GB2312"%>
<%@ page import="com.gever.struts.Constant"%>
<%@ taglib uri="/WEB-INF/taglib/struts-tiles.tld" prefix="tiles" %>
<html>
<%String context =request.getContextPath();%>

<%//ÔøÏéÓ¾¼Ó ÅÐ¶ÏÊÇ·ñÈÔ´¦ÓÚµÇÂ½×´Ì¬
	if(!com.gever.goa.web.util.RequestUtils.isLogon(pageContext)){
		response.sendRedirect(""+context+"/jsp/error/reLogon.jsp");
	}
%>

<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" id="goastylecss" rel="stylesheet" type="text/css">
<title><tiles:getAsString name="title"/></title>
<SCRIPT LANGUAGE="JavaScript">
<!--
	var nodeValue = "ÎÒµÄ×ÀÃæ";
	var isFrame = false;
	var isOpen = false;

	
	init_FormData();

	try{
		getNodePathName(nodeValue,"<%=context%>");
	} catch (e) {
	}
	
//-->
</SCRIPT>

<body class="bodybg">
<tiles:insert attribute="body"/>

<SCRIPT LANGUAGE="JavaScript">

<!--
	try {
		initOrderBy("<%=context%>");

	} catch(e) {}
	hideMenus(isOpen,isFrame);
	setInitData(isOpen,isFrame);
//-->
</SCRIPT>
</html>