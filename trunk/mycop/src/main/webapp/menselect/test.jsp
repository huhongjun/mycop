<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>
test
</title>

<jsp:include flush="true" page="menselect/inituserinfo.do"/>
</head>
<body bgcolor="#ffffff">
<form action="" method="POST">
<input type="text" name="namfield" onkeydown="javascipt:getUser('namfield','idfiled');" onblur="javascript:checkValue('namfield','idfiled');"/>
<input type="hidden" name="idfiled"/>
<input type="button" onclick="javascript:openSelectWindow('menselect','idfiled','namfield');" value="ѡ����Ա">
<br>
<input type="text" name="deptnamfield"/>
<input type="hidden" name="deptidfiled"/>
<input type="button" onclick="javascript:openSelectWindow('deptselect','deptidfiled','deptnamfield');" value="ѡ����">
<br />
<input type="text" name="mannamfield"/>
<input type="hidden" name="manidfiled"/>
<input type="button" onclick="javascript:openSelectWindow('manselect','manidfiled','mannamfield');" value="ѡ�񵥸���Ա">
</form>
</body>
</html>
