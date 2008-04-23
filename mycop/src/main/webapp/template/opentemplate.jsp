<%@ page contentType="text/html; charset=GB2312"%>

<%@ taglib uri="/WEB-INF/taglib/struts-tiles.tld" prefix="tiles" %>
<html>
<%String context =request.getContextPath();%>

<%//����Ӿ�� �ж��Ƿ��Դ��ڵ�½״̬
	if(!com.gever.goa.web.util.RequestUtils.isLogon(pageContext)){
		response.sendRedirect(""+context+"/jsp/error/reLogon.jsp");
	}
%>

<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<link href="<%=com.gever.goa.web.util.RequestUtils.getCSSPath(pageContext)%>" id="goastylecss" rel="stylesheet" type="text/css">
<title><tiles:getAsString name="title"/></title>

<body class="bodybg">

<SCRIPT LANGUAGE="JavaScript">
<!--
	var nodeValue = "�ҵ�����";
	var isFrame = false;
	var isOpen = false;
	init_FormData();
	
//-->
</SCRIPT>

<body class="bodybg">
<tiles:insert attribute="body"/>

<SCRIPT LANGUAGE="JavaScript">

<!--
	try {
		initOrderBy("<%=context%>");

	} catch(e) {}
	setInitData(isOpen,isFrame);
//-->
</SCRIPT>
</html>