<%@ page contentType="text/html; charset=gb2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-tiles.tld" prefix="tiles" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>天行协同信息平台</title>
<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style3 {color: #0000FF}
-->
</style>
</head>
<body class="bodybg">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center"><table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tablebk">
        <tr> 
          <td width="241"><a href="<%=request.getContextPath()%>/util/selenium/TestRunner.html?test=./copTest/TestSuite.html"><img src="<%=request.getContextPath()%>/images/cop-login.jpg" border="0"></a></td>
          <td> <div align="center"><span class="logo">登  录</span><br>
              <br> 
            </div>
            <span class="style3"><tiles:insert attribute="input"/>
            </span>
            <p>&nbsp;</p>
	        <span class="style3"><tiles:insert attribute="message"/>
            </span>
	        <p align="center" class="f12 style3"></p>
          <p align="center" class="f12 style3">&nbsp;</p></td>
        </tr>
      </table>
      </td>
  </tr>
</table>
</body>
</html>
