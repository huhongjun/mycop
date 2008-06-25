<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK"%>
<%@ page import="com.zhjedu.util.DateTimeUtil"%>
<link href="<%=request.getContextPath() %>/css/wxj.css" rel="stylesheet" type="text/css">
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
<title>添加考试</title>
<script language="javascript" src="<%=request.getContextPath()%>/js/meizzDate.js"></script>
<link href="<%=request.getContextPath() %>/css/wxj.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript">
function save(flag){
 	if(document.QuizForm.examType==null||document.QuizForm.examType.value==""){
 		alert("请选择考试类型");
 		document.QuizForm.examType.focus();
 		return;
 	}
 	else if(document.QuizForm.name==null||document.QuizForm.name.value==""){
 		alert("请输入考试名称");
 		document.name.focus();
 		return;
 	}
 	else if(document.QuizForm.password==null||document.QuizForm.password.value==""){
 		alert("请输入监考密码");
 		document.password.focus();
 		return;
 	}
	var scopes = document.all("scope");
	var scopenum = scopes.options.length;
	var scopechar = "";
	for(var j = 0; j < scopenum; j ++){
		var scope = scopes.options[j];
		scopechar = scopechar + "," + scope.value;
		
	}
	document.QuizForm.flag.value = flag;
	document.QuizForm.scopestr.value = scopechar;
	document.QuizForm.submit();
}
function selectscope(){
		var scope = document.all("scope");
		var scopechar = "";
		for(var k = 0; k < scope.options.length; k ++){
			var category = scope.options[k];
			scopechar += category.value + ",";
		}
		var strFeatures = "dialogWidth: 800px; dialogHeight: 600px; center: yes; scroll:no;help: no; status: no; edge: raised;";
	    var result = new Array;
	    result = window.showModalDialog("<%=request.getContextPath()%>/quiz/selectCatScope.jsp","", strFeatures);
	    if(result != null){
	    	for(var i = 0; i < result.length; i ++){
	    		var info = result[i];
	    		var oOption = document.createElement("option");
				oOption.text = info[1];
				oOption.value = info[0];
				oOption.selected = true;
				if(scopechar.indexOf("," + oOption.value + ",") < 0){
					scope.add(oOption);
				}
	    	}
	    }
	}
	
	function removeFromSelect(x){
		var obj = document.all(x);
		for(var k = 0; k < obj.options.length; k ++){
			if(k >= 0 && k <= obj.options.length-1 && obj.options[k].selected){
				obj.options[k] = null;
				k --;
			}
		}
	}
</script>
<body>
<html:form method="post" action="/Quiz.do">
	<html:hidden property="method" value="saveQuiz" />
	<html:hidden property="scopestr" value="" />
	<html:hidden property="flag" value="" />
	<html:hidden property="quizType" value="2" /><!-- 2代表的考试 -->
	<html:hidden property="id" name="quiz" />
	<div align="center">
		<table border="0" width="100%" id="table4">
			<tr>
				<td width="100%" height="30">
					<p class="style1">
						您现在的位置是：考试中心 / 考试管理 / 添加 编辑考试 /
					</p>
				</td>
			</tr>
			<tr>
				<td height="30" align="left" valign="middle" bgcolor="#7FBEE6">
					<div align="center">
						<span class="style3">添加/编辑考试</span>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" border="0" id="table4">
						<tr bgcolor="#EEF7FF">
							<td width="11%" height="21">
								<span class="style1"> 考试类型</span>
							</td>
							<td width="90%" height="21">
								<html:select size="1" property="examType" name="quiz">
									<html:option value="2">入学考试</html:option>
								</html:select>
								&nbsp;&nbsp;&nbsp;
								<span class="style1">（默认为空但必须校验）</span>
							</td>
						</tr>
						<tr bgcolor="#F7FBFF">
							<td width="11%" height="21">
								<span class="style1"> 考试名称</span>
							</td>
							<td width="90%" height="21">
								<html:text property="name" name="quiz" />
							</td>
						</tr>
						<tr bgcolor="#EEF7FF">
							<td height="21" width="11%">
								<span class="style1"> 范  围</span>
							</td>
							<td height="21" width="90%">
								<select name="scope" style="width:200px" multiple>
				    <logic:iterate indexId="ind" id="element" name="scopeList">
    	<option value="<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></option>
    	</logic:iterate>
				    </select>
		<a href="javascript:selectscope()" class="c1"><img src="<%=request.getContextPath()%>/image/plan-s1.jpg" width="45" height="21" border="0"/></a>
    	<a href="javascript:removeFromSelect('scope')" class="c1"><img src="<%=request.getContextPath()%>/image/plan-s2.jpg" width="45" height="21" border="0"/></a>
								&nbsp;&nbsp;
								<span class="style1">（选择进入题库树的页面——修改时选择范围取出来的值不可编辑）</span>
							</td>
						</tr>
						<tr bgcolor="#F7FBFF">
							<td height="21" width="11%">
								<span class="style1"> 有 效 期</span>
							</td>
							<td height="21" width="90%">
							   <bean:define id="tmp" name="quiz" property="timeopen"/>
							   <bean:define id="tmp1" name="quiz" property="timeclose"/>
							   <% 
							   		String startDate = "";
							   		String endDate = "";
							   		if(!tmp.toString().equals("0")){
							   			startDate = DateTimeUtil.getTime((Long)tmp,2);
							   		}
							   		if(!tmp1.toString().equals("0")){
							   			endDate = DateTimeUtil.getTime((Long)tmp1,2);
							   		}
							   %>
							   
								<input type="text" name="timeopen" value="<%=startDate %>" onclick="setday(this)"/>
								
								<span class="style1">至</span>
								<input type="text" name="timeclose" value="<%=endDate %>" onclick="setday(this)"/>
								
							</td>
						</tr>
						<tr bgcolor="#EEF7FF">
							<td width="11%" height="21">
								<span class="style1"> 考试时限</span>
							</td>
							<td width="90%" height="21">
								<html:text property="timelimit" name="quiz" />
							</td>
						</tr>
						<tr bgcolor="#F7FBFF">
							<td height="21" width="11%">
								<span class="style1"> 是否可用</span>
							</td>
							<td height="21" width="90%">
								<html:radio property="status" name="quiz" value="0" />
								<span class="style1">可用</span>&nbsp;&nbsp;

								<html:radio property="status" name="quiz" value="1" />
								<span class="style1">不可用</span>

							</td>
						</tr>
						<tr bgcolor="#EEF7FF">
							<td height="21" width="11%">
								<span class="style1"> 迟到时间</span>
							</td>
							<td height="21" width="90%">
								<input type="radio" value="0" checked name="timeLateFlag">
								<span class="style1">不限制</span>
								<input type="radio" value="V1" name="timeLateFlag">
								<span class="style1">限制</span>
								<html:text property="timelate" name="quiz" value="30" size="10"/>
								<span class="style1">分钟</span>
							</td>
						</tr>
						
						<tr bgcolor="#F7FBFF">
							<td height="21" width="11%">
								<span class="style1"> 监考密码</span>
							</td>
							<td height="21" width="90%">
								<html:text property="password" name="quiz"/>
							</td>
						</tr>
						<tr bgcolor="#EEF7FF">
							<td height="21" width="11%">
								<span class="style1"> 试卷描述</span>
							</td>
							<td height="21" width="90%">
								<html:textarea property="description" name="quiz" rows="4" cols="60"/>
							</td>
						</tr>
						<tr>
					<td align="left" colspan="4">
					<p align="center">
						<input type="button" onClick="save('0');"  class="btn_cm_small" value="保存" name="B7" src="" />
						<input type="button" onClick="history.go(-1)"  class="btn_cm_small" value="返回" name="B1" />
						<input name="B12" type="button" onClick="save('1');"  class="btn_cm" value="进入策略" src="" />
									&nbsp;
					</p>
					</td>
			</tr>
			<tr>
				<td align="left" colspan="4">
					<p align="left"><span class="style1"><font color="#FF0000">
						保存按钮 -- 点击将保存考试信息并组建默认策略，使用此功能请确保题库题量足够。<br/>
						进入策略 -- 点击将保存考试信息并进入策略维护界面。</font></span>
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
