<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.dfcw.zjproject.zj.model.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>无标题文档</title>
		<script src="<%=request.getContextPath() %>/js/dtree.js"></script>
	</head>
	<script language="javascript">
		function search(){
			if(document.QuizForm.institution.value == "0"){
				alert("请选择培训机构！");
				document.QuizForm.institution.focus();
				return;
			}
			if(document.QuizForm.recruitbatch.value == "0"){
				alert("请选择学习批次！");
				document.QuizForm.recruitbatch.focus();
				return;
			}
			if(document.QuizForm.studykind.value == "0"){
				alert("请选择学习层次！");
				document.QuizForm.studykind.focus();
				return;
			}
			if(document.QuizForm.subject.value == "0"){
				alert("请选择专业方向！");
				document.QuizForm.subject.focus();
				return;
			}
			document.QuizForm.target="rightFrame";
			document.QuizForm.submit();
		}
	</script>
    <%
    List studykindList = (List)request.getAttribute("studykinds");
    List subjectList = (List)request.getAttribute("subjects");
    List institutionList = (List)request.getAttribute("institutions");
    List learncenterList = (List)request.getAttribute("learncenters");
    List recruitbatchList = (List)request.getAttribute("recruitbatchs");
     %>
	<body topmargin="0" leftmargin="0">
		<table width="100%" height="540" border="0" cellpadding="0"
			cellspacing="0" bgcolor="#CAE4FF" id="table2">
			<tr>
				<td width="5%" bgcolor="#7FBEE6">&nbsp;</td>
				<td valign="top" bgcolor="#7FBEE6">
					<div align="left">
						<html:form action="Quiz.do" target="rightFrame">
						<html:hidden property="method" value="selectUserScopeForQuiz" />
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top">
									<div align="left">
										<p class="style1">
											培训机构：<br><select size="1" name="institution">
											<option value="0">请选择..</option>
											<%
											for(int i=0;i<institutionList.size();i++){
												InstitutionModel im = (InstitutionModel)institutionList.get(i);
											 %>
											<option value="<%=im.getId()%>"><%=im.getName()%></option>
											<%} %>
											</select>
										</p>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<div align="left">
										
										<p class="style1">
											学习批次：<br><select size="1" name="recruitbatch">
											<option value="0">请选择..</option>
											<%
											for(int i=0;i<recruitbatchList.size();i++){
												RecruitbatcModel rm = (RecruitbatcModel)recruitbatchList.get(i);
											 %>
											<option value="<%=rm.getId()%>"><%=rm.getName()%></option>
											<%} %>
											</select>
										</p>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<div align="left">
										
										<p class="style1"> 
											学习层次：<br><select size="1" name="studykind">
											<option value="0">请选择..</option>
											<%
											for(int i=0;i<studykindList.size();i++){
												StudyKindModel skm = (StudyKindModel)studykindList.get(i);
											 %>
											<option value="<%=skm.getId()%>"><%=skm.getName()%></option>
											<%} %>
											</select>
										</p>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<div align="left">
										<p class="style1">
											专业方向：<br><select size="1" name="subject">
											<option value="0">请选择..</option>
											<%
											for(int i=0;i<subjectList.size();i++){
												SubJectModel sjm = (SubJectModel)subjectList.get(i);
											 %>
											<option value="<%=sjm.getId()%>"><%=sjm.getName()%></option>
											<%} %>
											</select>
										</p>
									</div>
								</td>
							</tr>
						</table>
						<br />
						<p align="center"><input name="Submit"  class="btn_cm_small" type="button" onClick="search();"  value="查询"/></p>
						</html:form>
					</div>
					<p class="style1">
					<font color="#FF0000">请从右侧查询出来的科目列表中选择要发布的科目，点击选择按钮。请注意：同一培训机构、学习批次、学习层次、专业方向下只允许选择一个科目。</font>
					</p>
				</td>
			</tr>
		</table>
	</body>
</html>
