<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%
	String errorMsg = (String)session.getAttribute("errorMsg");
	if (errorMsg == null)
		errorMsg = "δ��ԭ�����,����ϵϵͳ����Ա.";
	String errorDesr =(String)session.getAttribute("errorDesr");
	if (errorDesr == null)
		errorDesr = "δδ��ԭ�����,����ϵϵͳ����Ա.";
	String errorCode =(String)session.getAttribute("errorCode");
	if (errorCode == null)
		errorCode = "δ֪";
	String context = request.getContextPath();
	//session.removeAttribute("errorMsg");
	//session.removeAttribute("errorDesr");
	//session.removeAttribute("errorCode");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>ϵͳ��ʾ</title>
<jsp:include page="/jsp/jsp_css.jsp" />
<script>
function doBack(){
    if(navigator.appName != "Netscape"){
        history.back();
        return;
    }
    var loc = window.parent.document.location + "";
    if(loc.indexOf("info_error.jsp") > 0){
        window.parent.document.location = "/gdp/";
        return;
    }
    window.parent.document.location.reload();
}
</script>
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
          <td width="41%" align="center"><img src="<%=context%>/images/error-1.gif" width="67" height="67"> 
          </td>
          <td width="59%"><table width="100%" border="0" cellspacing="1" cellpadding="2">
              <tr> 
                <td height="35" class="f14">����!</td>
              </tr>
              <tr> 
                <td height="67" class="f12">����ID��:<%=errorCode%><br>
				������Ϣ:<%=errorMsg%></td>
              </tr>
            </table>
            <br> </td>
        </tr>
      </table></td>
  </tr>
</table>

<div align="center"><br>
  <input name="Submit" type="submit" class="button" onclick="javascript:doBack()" value="����">
</div>
</body>
</html>
