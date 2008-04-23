<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<jsp:include page="/jsp/jsp_css.jsp" />
</head>

<body class="bodybg">
</td></tr></table>
<table width="100%" border="0" cellpadding="5" cellspacing="0">
<tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr><td align="left" colspan="2" class="TableTitleText">失败提示！</td> </tr>
  <tr><td align="center"><html:errors /></td> </tr>  
  <tr> 
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr> 
    <td align="center">
	<input name="back" type="button"  class="button" value="返回" onclick="history.back()"></td>
  </tr>
</table>
</body>
</html>
