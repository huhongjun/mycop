<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page"%>
<%@ page import="com.zhjedu.exam.domain.ZjQuiz"%>
<%@ page import="com.zhjedu.util.DateTimeUtil"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
 <%@   page   import="java.sql.*"%>   
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
-->
</style>
<%
  ArrayList list = (ArrayList)request.getAttribute("list");
 %>
 <%
 	//com.zhjedu.util.PageObject po=(com.zhjedu.util.PageObject)request.getAttribute("po");
 %> 
<script language=javascript>
function addquestion(){
	document.QuestionForm.action="question/addquestion.jsp";
	document.QuestionForm.submit();
}
function search(){

	
	window.document.all.QuestionForm.action="<%=request.getContextPath()%>/Question.do?method=listassignemnt";
	QuestionForm.submit();
	return true;
}
function editquestion(){
	var j = 0;
	for(var i=0;i<QuestionForm.quizid.length;i++){
		if(QuestionForm.quizid[i].checked==true){
			j=j+1;
		}
	}
	
	if(j==0){
		alert("请选择要编辑的考试！");
		return;
	}
	else if(j>1){
		alert("一次只允许编辑一个考试！");
		return;
	}
	else{
		window.document.all.QuestionForm.action="<%=request.getContextPath()%>/Question.do?method=editAssignment";
		QuestionForm.submit();
		return true;
	}
	
}
function removequestion(){
	var j = 0;
	for(var i=0;i<QuestionForm.quizid.length;i++){
		if(QuestionForm.quizid[i].checked==true){
			j=j+1;
		}
	}
	
	if(j==0){
		alert("请选择要删除的考试！");
		return;
	}
	
	else{
		//QuestionForm.target = "_self";
		//QuestionForm.method.value = "removeAssignment";
		window.document.all.QuestionForm.action="<%=request.getContextPath()%>/Question.do?method=removeAssignment";
		QuestionForm.submit();
		return true;
	}
	
}
</script>
<html:form action="Question.do" method="post">

	<table border="0" width="100%" id="table4">
		<tr bgcolor="#FFFFFF">
			<td height="40" colspan="11">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="30" valign="bottom">
							<p class="style1">
								您管理的课程有：
							</p>
						</td>
					</tr>
					<tr>
						<td height="35" valign="middle" bgcolor="#E3EDF9">
							<table border="0" cellspacing="1" cellpadding="1" id="secTable">
								<tr align="center">
									<td valign="middle" class="sec1" onclick="secBoard(0)">
										大学政治
									</td>
									<td valign="middle" class="sec1" onclick="secBoard(1)">
										大学英语
									</td>
									<td valign="middle" class="sec1" onclick="secBoard(2)">
										大学英语2
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<img src="<%=request.getContextPath() %>/image/x-016.gif" width="300" height="70" />
			</td>
		</tr>
		<tr>
			<td colspan="11">
				<a href="<%=request.getContextPath() %>/assignment_T/add_assignemnt.jsp"><img
						src="<%=request.getContextPath() %>/image/x-009-002.gif"
						width="109" height="21" border="0" /> </a>
				<a href="javascript:editquestion()"><img
						src="<%=request.getContextPath() %>/image/x-009-003.gif"
						width="109" height="21" border="0" /> </a>
				 <a href="javascript:removequestion()"><img
					src="<%=request.getContextPath() %>/image/x-009-001.gif"
					width="109" height="21" border="0" /></a>
				&nbsp;&nbsp;
				<span class="style1">考试名称</span>
				<input type="text" name="name" size="14" style="color: #C0C0C0" />
				&nbsp;
				<span class="style1">创建时间</span>
				<input type="text" name="T2" size="14" style="color: #C0C0C0" />
				
				<input type="button" onclick="search()" value="查询" name="B6" />
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td height="21" colspan="11" align="left">
				<div align="left">
					<table border="0" cellspacing="1" cellpadding="1" id="secTable">
						<tr align="center">
							<td valign="middle" class="sec1" onclick="secBoard(0)">
								未发布
							</td>
							<td valign="middle" class="sec1" onclick="secBoard(1)">
								已发布
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr bgcolor="#7FBEE6">
			<td width="6%" height="21">
				<p align="center">
					<input type="checkbox" name="quizid" value="ON" />
				</p>
			</td>
			<td width="12%" height="21">
				<p align="center" class="style1">
					考试编号
				</p>
			</td>
			<td width="14%" height="21">
				<p align="center" class="style1">
					&nbsp;考试名称
				</p>
			</td>
			<td width="9%" height="21">
				<p align="center" class="style1">
					有效期
				</p>
			</td>
			<td width="7%" height="21">
				<p align="center" class="style1">
					创建时间
				</p>
			</td>
			<td width="7%" height="21">
				<p align="center" class="style1">
					创建人
				</p>
			</td>
			<td width="7%" height="21">
				<p align="center" class="style1">
					考试发布方式
				</p>
			</td>
			<td width="7%">
				<div align="center">
					<span class="style1">计分方式</span>
				</div>
			</td>
			<td width="11%">
				<div align="center">
					<span class="style1">允许参加次数</span>
				</div>
			</td>
			<td width="5%">
				<div align="center">
					<span class="style1">状态</span>
				</div>
			</td>
			<td width="15%" height="21">
				<p align="center" class="style1">
					操作
				</p>
			</td>
		</tr>

		<%
				if (list != null) {
				for(int i=0;i<list.size();i++){
				ZjQuiz zq = (ZjQuiz) list.get(i);
				//ZjCourse zc =(ZjCourse)zjquizexam[2];

				//i++;
				String assignmentId = zq.getId();
				String name = zq.getName();
				//String title = zc.getTitle();
				
				DateTimeUtil date=new DateTimeUtil();				
				
				String timeopen = date.getTime(zq.getTimeopen(),2);
				
				String timeclose = date.getTime(zq.getTimeclose(),2);
				
				String style = zq.getQuizStyle();
				String Delflag=zq.getDelflag();
				if ("0".equals(style)) {
					style = "在线";
				}
				if ("1".equals(style)) {
					style = "离线";
				}
				String grademethod = zq.getGrademethod();
				if ("0".equals(grademethod)) {
					grademethod = "最高成绩";
				}
				if ("1".equals(grademethod)) {
					grademethod = "最后成绩";
				}
				if ("2".equals(grademethod)) {
					grademethod = "平均成绩";
				}
				//String grade = ze.getGrade().toString();
		%>
		<tr bgcolor="#EEF7FF">
			<td>
				<p align="center">
					<input type="checkbox" name="quizid" value="<%=assignmentId %>" />
				</p>
			</td>
			<td align="center">
				<span class="style1"><%=i+1%>
				</span>
			</td>
			<td>
				<div align="center">
					<span class="style1"><a href="<%=request.getContextPath()%>/Question.do?method=editAssignment"><%=name%>
					</a>
					</span>
				</div>
			</td>
			<td>
				<%=timeopen%>--<%=timeclose%>
			</td>
			<td>
				<%=timeopen%>
			</td>
			<td>
				aaa
			</td>
			<td>
			</td>
			<td>
				<%=grademethod%>
			</td>
			<td>
				3
			</td>
			<td>
				<%=Delflag%>
			</td>
			<td>
				<div align="center">
					<input type="submit" value="发布" name="B30" />
				</div>
			</td>
		</tr>
		<%
			}
				}
		%>

		
		<tr>
			<td align="center" colspan="11">
				<%--<page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="QuestionForm" pageVar="page" jsName="changePage" url="Question.do?method=listassignemnt"/>
				
			--%></td>
		</tr>
	</table>
</html:form>
