<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<link href="<%=request.getContextPath()%>/css/ksmain.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.sec1 {cursor: hand;
font-size:12px;
color: #000000;
width:107;
height:25;
background-image=url(image/x-00100.gif);
}
.style1 {font-size: 12px}
.style11 {color: #164f8e;
	font-weight: bold;
}
.style11 {color: #164f8e; font-weight: bold; font-size: 14px; }
.style12 {color: #0000FF}
.style13 {color: #3b6ba0}
.style14 {	font-size: 36px;
	color: #3B6BA0;
}
.style8 {color: #0000FF; font-size: 12px; }
.style9 {font-size: 14px}
-->
</style>
<html>
<head>
<title>添加目录</title>
</head>
<script language="javascript">
function validate(){
	if(this.document.CategoryForm.name.value==""){
		alert("目录名称不能为空");
		return false;
	}
	
	return true;
}
</script>
<body>
<html:form method="post" action="/Category.do?method=saveCategory" target="_parent" onsubmit="if(!validate()){return false;}" >
<%
String parent = "";
if(request.getParameter("parent")!=null){
	parent = request.getParameter("parent");
}
 %>
<html:hidden property="parent" name="category" />
<html:hidden property="id" name="category" />
	<div align="center">
		<table border="0" width="100%" id="table4">
			<tr>
				<td width="100%" height="30">
					<p class="style1">
						您现在的位置是：考试中心 / 考试管理 / 添加/修改目录 /
					</p>
				</td>
			</tr>
			<tr>
				<td height="30" align="left" valign="middle" bgcolor="#7FBEE6">
					<div align="center">
						<span class="style3">添加/修改目录</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" border="0" id="table4">
						<tr bgcolor="#EEF7FF">
							<td width="11%" height="21">
								<span class="style1"> 目录名称</span>
							</td>
							<td width="90%" height="21">
								<html:text property="name" name="category" />
							</td>
						</tr>
						<tr bgcolor="#F7FBFF">
							<td width="11%" height="21">
								<span class="style1"> 目录简介</span>
							</td>
							<td width="90%" height="21">
								<html:textarea property="info" name="category" cols="29" rows="3" />
							</td>
						</tr>
						<tr>
					<td align="left" colspan="4">
					<p align="center">
						<input type="submit" class="btn_cm_small" value="保存" name="B7" src="" />
						<input type="button" onClick="history.go(-1)"  class="btn_cm_small" value="返回" name="B1" />
									&nbsp;
					</p>
					</td>
			</tr>
					</table>
				</td>
			</tr>
			
		</table>
		
		</div>

</html:form>
</body>
</html>
