<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ޱ����ĵ�</title>
<jsp:include page="/jsp/jsp_css.jsp" />
</head>
<FRAMESET  COLS="22%,78%"  style="overflow-x:hidden;overflow-y:hidden;border: none">
	<FRAME src="<gdp:context/>/organization/DepartmentAction.do?action=getTreeRoot&src=DepartmentAction" NAME="tree" id="tree">
	<FRAME src="<gdp:context/>/organization/DepartmentAction.do?action=list&departid=0" NAME="content" id="content">
</FRAMESET>
<body class="bodybg" style="overflow-x:hidden;overflow-y:hidden;border: none">
 </body>
</html>
