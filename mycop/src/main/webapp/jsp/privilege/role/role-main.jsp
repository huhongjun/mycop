<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>无标题文档</title>
<jsp:include page="/jsp/jsp_css.jsp" />
</head>
<script type="text/javascript" src="<gdp:context/>/js/gdp.js"></script>
<body class="bodybg" style="overflow-x:hidden;overflow-y:hidden;border: none">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="200" valign="top"> 
      <IFRAME border="0" framespacing="0" frameborder="NO" height=100% width=100% name=main src="../roleTree.jsp"></IFRAME></td>
    <td><IFRAME border="0" framespacing="0" frameborder="NO" height=100% width=100% name=main src="<gdp:context/>/privilege/roleAction.do?action=list"></IFRAME></td>
  </tr>
</table>
</body>
</html>
<%
if (true){
%>
<script language="javascript">clearMenu();</script>
<%
}
%>
