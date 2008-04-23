<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%
	String context = request.getContextPath();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>系统提示</title>
<jsp:include page="/jsp/jsp_css.jsp" />

</head>
<SCRIPT LANGUAGE="JavaScript">
<!--
function reErrorPage(){

	if (opener != null) {
		if (opener.parent != null) {
			opener.parent.top.location = "<%=context%>";
			window.close();
		}
	}
	if(parent != null){
		window.parent.top.location = "<%=context%>";
		return;
	}

	document.location = "<%=context%>";

}
//-->
</SCRIPT>
<body class="bodybg">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="f12">
  <tr> 
    <td>&nbsp;</td>
  </tr>
</table>
<table width="75%" align="center" cellpadding="10" cellspacing="0" class="tablebk">
  <tr align="center"> 
    <td height="265" bgcolor="#FFFFFF"><table width="100%" height="240" border="0" cellpadding="2" cellspacing="1">
        <tr> 
          <td width="41%" align="center"><img src="<%=context%>/images/error-1.gif" width="67" height="67"> 
          </td>
          <td width="59%"><table width="100%" border="0" cellspacing="1" cellpadding="2">
              <tr> 
                <td height="35" class="f14">错误!</td>
              </tr> 
              <tr> 
                <td height="67" class="f12">系统超时，请重新登陆！<br>
				</td>
              </tr>
            </table>
            <br> </td>
        </tr>
      </table></td>
  </tr>
</table>

<div align="center"><br>
  <input name="timeoutb" type="submit" class="button" onclick="reErrorPage()" value="返回">
</div>
</body>
</html>