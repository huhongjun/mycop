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
<input type="button" onclick="javascript:openSelectWindow('menselect','idfiled','namfield');" value="选择人员">
<br>
<input type="text" name="deptnamfield"/>
<input type="hidden" name="deptidfiled"/>
<input type="button" onclick="javascript:openSelectWindow('deptselect','deptidfiled','deptnamfield');" value="选择部门">
<br />
<input type="text" name="mannamfield"/>
<input type="hidden" name="manidfiled"/>
<input type="button" onclick="javascript:openSelectWindow('manselect','manidfiled','mannamfield');" value="选择单个人员">
</form>
</body>
</html>
