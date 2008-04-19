<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.dfcw.zjproject.zj.model.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>无标题文档</title>
		<script src="<%=request.getContextPath() %>/js/dtree.js"></script>
	</head>
	<script language="javascript">
	function search(){
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
				<td valign="top" bgcolor="#7FBEE6">
					<div align="left">
						<html:form action="Quiz.do" target="rightFrame">
						<html:hidden property="method" value="selectUserScopeForQuiz" />
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top">
									<div align="left">
										
										<p class="hcy2"> 
											学习层次：<br><select size="1" name="studykind">
											<option value="0"></option>
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
										
										<p class="hcy2">
											专业方向：<br><select size="1" name="subject">
											<option value="0"></option>
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
							<tr>
								<td valign="top">
									<div align="left">
										
										<p class="hcy2">
											培训机构：<br><select size="1" name="institution">
											<option value="0"></option>
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
										
										<p class="hcy2">
											学习中心：<br><select size="1" name="learncenter">
											<option value="0"></option>
											<%
											for(int i=0;i<learncenterList.size();i++){
												LearnCenterModel lcm = (LearnCenterModel)learncenterList.get(i);
											 %>
											<option value="<%=lcm.getId()%>"><%=lcm.getName()%></option>
											<%} %>
											</select>
										</p>
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top">
									<div align="left">
										
										<p class="hcy2">
											学习批次：<br><select size="1" name="recruitbatch">
											<option value="0"></option>
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
						</table>
						
						<input name="Submit" type="button" onClick="search();"  value="提交"/>
						</html:form>
					</div>
					<p>
					</p>
				</td>
			</tr>
		</table>
	</body>
</html>
