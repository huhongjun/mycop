<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%
	String errorMsg = (String)session.getAttribute("errorMsg");
	if (errorMsg == null)
		errorMsg = "未名原因错误,请联系系统管理员.";
	String errorDesr =(String)session.getAttribute("errorDesr");
	if (errorDesr == null)
		errorDesr = "未名原因错误,请联系系统管理员.";
	String errorCode =(String)session.getAttribute("errorCode");
	if (errorCode == null)
		errorCode = "未知";
	String context = request.getContextPath();
	session.removeAttribute("errorMsg");
	session.removeAttribute("errorDesr");
	session.removeAttribute("errorCode");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>系统提示</title>
<jsp:include page="/jsp/jsp_css.jsp" />
</head>


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
          <td width="41%" align="center"><img src="<%=context%>/images/info_ok.gif" width="63" height="64">
          </td>
          <td width="59%"><table width="100%" border="0" cellspacing="1" cellpadding="2">
              <tr>
                <td height="35" class="f14">完成</td>
              </tr>
              <tr>
                <td height="67" class="f12">未名原因错误,请联系系统管理员。</td>
              </tr>
            </table>
            <br> </td>
        </tr>
      </table></td>
  </tr>
</table>

<div align="center"><br>
  <input name="Submit" type="submit" class="button" onclick="history.back()" value="确定">
</div>
</body>
</html>
