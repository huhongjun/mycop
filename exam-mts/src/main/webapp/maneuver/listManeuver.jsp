<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.*" %>
<%@ page import="com.zhjedu.util.Constants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>手工策略维护</title>
<script type="text/javascript">
	function add(){
		if(form1.scope.value == ""){
			alert("请选择范围！");
			form1.scope.focus();
			return;
		}
		if(form1.questionType.value == ""){
			alert("请选择题型！");
			form1.questionType.focus();
			return;
		}
		form1.submit();
	}
	
	function updateManeuver(maneuverId){
		var FormVar = document.all(maneuverId);
		if(!check(FormVar.hard1Num.value)){
			alert("题数不能为空，且只能为数字！");
			FormVar.hard1Num.focus();
			return;
		}
		if(!check(FormVar.hard2Num.value)){
			alert("题数不能为空，且只能为数字！");
			FormVar.hard2Num.focus();
			return;
		}
		if(!check(FormVar.hard3Num.value)){
			alert("题数不能为空，且只能为数字！");
			FormVar.hard3Num.focus();
			return;
		}
		if(!check(FormVar.hard4Num.value)){
			alert("题数不能为空，且只能为数字！");
			FormVar.hard4Num.focus();
			return;
		}
		if(!check(FormVar.hard5Num.value)){
			alert("题数不能为空，且只能为数字！");
			FormVar.hard5Num.focus();
			return;
		}
		if(!check(FormVar.nohardNum.value)){
			alert("题数不能为空，且只能为数字！");
			FormVar.nohardNum.focus();
			return;
		}
		if(!check(FormVar.score.value)){
			alert("分数不能为空，且只能为数字！");
			FormVar.score.focus();
			return;
		}
		if(parseInt(FormVar.hard1Num.value) > parseInt(FormVar.t_hard1Num.value)){
			alert("你所输入的题数大于题库已有题目数量，如果已有题目数据不能满足你的组卷策略，请先添加试题！");
			FormVar.hard1Num.focus();
			return;
		}
		if(parseInt(FormVar.hard2Num.value) > parseInt(FormVar.t_hard2Num.value)){
			alert("你所输入的题数大于题库已有题目数量，如果已有题目数据不能满足你的组卷策略，请先添加试题！");
			FormVar.hard2Num.focus();
			return;
		}
		if(parseInt(FormVar.hard3Num.value) > parseInt(FormVar.t_hard3Num.value)){
			alert("你所输入的题数大于题库已有题目数量，如果已有题目数据不能满足你的组卷策略，请先添加试题！");
			FormVar.hard3Num.focus();
			return;
		}
		if(parseInt(FormVar.hard4Num.value) > parseInt(FormVar.t_hard4Num.value)){
			alert("你所输入的题数大于题库已有题目数量，如果已有题目数据不能满足你的组卷策略，请先添加试题！");
			FormVar.hard4Num.focus();
			return;
		}
		if(parseInt(FormVar.hard5Num.value) > parseInt(FormVar.t_hard5Num.value)){
			alert("你所输入的题数大于题库已有题目数量，如果已有题目数据不能满足你的组卷策略，请先添加试题！");
			FormVar.hard5Num.focus();
			return;
		}
		if((parseInt(FormVar.hard1Num.value) + parseInt(FormVar.hard2Num.value) + parseInt(FormVar.hard3Num.value) + parseInt(FormVar.hard4Num.value) + parseInt(FormVar.hard5Num.value) + parseInt(FormVar.nohardNum.value)) > parseInt(FormVar.t_nohardNum.value)){
			alert("你所输入的题总数大于题库已有题目总数，如果已有题目数据不能满足你的组卷策略，请先添加试题！");
			FormVar.nohardNum.focus();
			return;
		}
		FormVar.maneuverId.value = maneuverId;
		FormVar.action = "<%=request.getContextPath()%>/Maneuver.do?method=updateManeuver";
		FormVar.submit();
	}
	
	function removeManeuver(maneuverId){
		if(confirm("该操作将删除策略，并且不可恢复，确定继续吗？")){
			var FormVar = document.all(maneuverId);
			FormVar.maneuverId.value = maneuverId;
			FormVar.action = "<%=request.getContextPath()%>/Maneuver.do?method=removeManeuver";
			FormVar.submit();
		}
	}
	
	function check(str){
		if(str == null || str == ""){
			return false;
		}
		var strSource = "0123456789";
		for (i = 0; i < (str.length); i ++){
			temp = strSource.indexOf(str.charAt(i));
			if (temp == -1) {
				return false;
			}
		}
		return true;
	}
	
	function finish(){
		if(confirm("该操作将根据策略生成从题库提取相应的试题并且不可恢复，确定继续吗？")){
			window.location.href = "<%=request.getContextPath()%>/Maneuver.do?method=finishManeuver&courseId=<bean:write name="courseId"/>&quizId=<bean:write name="quizId"/>";
		}
	}
	
</script>


<style type="text/css">
<!--
.style5 {font-size: 14px; font-weight: bold; color: #0000FF; }
.style6 {
	font-size: 16px;
	font-weight: bold;
	color: #FF0000;
}
.style7 {color: #FF0000}
-->
</style>
</head>

<body>
<table width="100%" height="192"  border="0">
  <tr>
    <td height="188" valign="top">
    <form name="form1" method="post" action="<%=request.getContextPath()%>/Maneuver.do?method=saveManeuver&courseId=<bean:write name="courseId"/>&quizId=<bean:write name="quizId"/>">
    <table width="100%"  border="1" bordercolor="#000066">
      <tr>
        <td height="33" valign="top">选择范围：
          <select name="scope">
          <option value="">请选择..</option>
            <%=request.getAttribute("chapterSelectOptionsHtml") %>
          </select> 
          选择题型： 
          <select name="questionType">
          <option value="">请选择..</option>
          <%=request.getAttribute("questionSelectOptionsHtml") %>
                    </select> <input type="button" name="Submit" value=" 保 存 " onclick="javascript:add()">
          </td>
      </tr>
    </table>
    </form>
      <table width="100%" height="132"  border="1" cellpadding="0" cellspacing="0" bordercolor="#0000FF">
        <tr bordercolor="#000066" bgcolor="#999999">
          <td width="12%" rowspan="2"><div align="center" class="style5">章节名称</div></td>
          <td width="8%" rowspan="2"><div align="center" class="style5">题型</div></td>
          <td height="18" colspan="6" bgcolor="#999999"><div align="center" class="style5">难度</div></td>
          <td width="10%" rowspan="2"><div align="center" class="style5">每题分 / 总分</div></td>
          <td rowspan="2"><div align="center" class="style5">操作</div></td>
        </tr>
        <tr>
          <td width="10%" height="16" bgcolor="#999999"><div align="center" class="style5">容易</div></td>
          <td width="10%" bgcolor="#999999"><div align="center" class="style5">较易</div></td>
          <td width="10%" bgcolor="#999999"><div align="center" class="style5">中等</div></td>
          <td width="10%" bgcolor="#999999"><div align="center" class="style5">较难</div></td>
          <td width="10%" bgcolor="#999999"><div align="center" class="style5">难</div></td>
          <td width="10%" bgcolor="#999999"><div align="center" class="style5">不限</div></td>
        </tr>
<%
	List list = (List)request.getAttribute("list");
	int hard1Num = 0;
	int hard2Num = 0;
	int hard3Num = 0;
	int hard4Num = 0;
	int hard5Num = 0;
	int nohardNum = 0;
	int totalScore = 0;
	if(list != null && list.size() > 0){
		for(int i = 0; i < list.size(); i ++){
			Object[] obj = (Object[])list.get(i);
			String questionType = obj[5].toString();
			int h1 = 0;
			int h2 = 0;
			int h3 = 0;
			int h4 = 0;
			int h5 = 0;
			int no = 0;
			int _score = 0;
			int _totalScore = 0;
			if(obj[6] != null){
				h1 = Integer.parseInt(obj[6].toString());
				hard1Num += h1;
			}
			if(obj[8] != null){
				h2 = Integer.parseInt(obj[8].toString());
				hard2Num += h2;
			}
			if(obj[10] != null){
				h3 = Integer.parseInt(obj[10].toString());
				hard3Num += h3;
			}
			if(obj[12] != null){
				h4 = Integer.parseInt(obj[12].toString());
				hard4Num += h4;
			}
			if(obj[14] != null){
				h5 = Integer.parseInt(obj[14].toString());
				hard5Num += h5;
			}
			if(obj[16] != null){
				no = Integer.parseInt(obj[16].toString());
				nohardNum += no;
			}
			if(obj[18] != null)
				_score = Integer.parseInt(obj[18].toString());
			_totalScore = (h1 + h2 + h3 + h4 + h5 + no) * _score;
			totalScore += _totalScore;
%>
		<form name="<%=obj[0] %>" method="post">
	    <input type="hidden" name="courseId" value="<bean:write name="courseId"/>"/>
	    <input type="hidden" name="quizId" value="<bean:write name="quizId"/>"/>
	    <input type="hidden" name="maneuverId"/>
        <tr>
          <td><div align="center"><%=obj[4] %></div></td>
          <td><div align="center">
          <%if(Constants.QUESTION_SINGLECHOICE.equals(questionType)){%>单选题<%} %>
          <%if(Constants.QUESTION_MULTICHOICE.equals(questionType)){%>多选题<%} %>
          <%if(Constants.QUESTION_JUDGE.equals(questionType)){%>判断题<%} %>
          <%if(Constants.QUESTION_MATCHING.equals(questionType)){%>匹配题<%} %>
          <%if(Constants.QUESTION_INPUTFILL.equals(questionType)){%>填空题<%} %>
          <%if(Constants.QUESTION_ANSWER.equals(questionType)){%>简答题<%} %>
          <%if(Constants.QUESTION_INTEGRATE.equals(questionType)){%>综合题<%} %>&nbsp;
          </div></td>
          <td><div align="center">
            <input name="hard1Num" type="text" size="3" maxlength="2" value="<%=h1 %>"><input name="t_hard1Num" type="hidden" value="<%=obj[7] %>">
          / <span class="style7"><%=obj[7] %></span> </div></td>
          <td><div align="center">
            <input name="hard2Num" type="text" size="3" maxlength="2" value="<%=h2 %>"><input name="t_hard2Num" type="hidden" value="<%=obj[9] %>">
/ <span class="style7"><%=obj[9] %></span></div></td>
          <td><div align="center">
            <input name="hard3Num" type="text" size="3" maxlength="2" value="<%=h3 %>"><input name="t_hard3Num" type="hidden" value="<%=obj[11] %>">
/ <span class="style7"><%=obj[11] %></span></div></td>
          <td><div align="center">
            <input name="hard4Num" type="text" size="3" maxlength="2" value="<%=h4 %>"><input name="t_hard4Num" type="hidden" value="<%=obj[13] %>">
/ <span class="style7"><%=obj[13] %></span></div></td>
          <td><div align="center">
            <input name="hard5Num" type="text" size="3" maxlength="2" value="<%=h5 %>"><input name="t_hard5Num" type="hidden" value="<%=obj[15] %>">
/ <span class="style7"><%=obj[15] %></span></div></td>
          <td><div align="center">
            <input name="nohardNum" type="text" size="3" maxlength="2" value="<%=no %>"><input name="t_nohardNum" type="hidden" value="<%=obj[17] %>">
/ <span class="style7"><%=obj[17] %></span></div></td>
          <td><div align="center">
            <input name="score" type="text" size="3" maxlength="2" value="<%=_score %>">
/ <%=_totalScore %> </div></td>
          <td><div align="center">
            <input type="button" name="Submit" value="保存" onclick="javascript:updateManeuver('<%=obj[0] %>')">
            <input type="button" name="Submit" value="删除" onclick="javascript:removeManeuver('<%=obj[0] %>')">
          </div></td>
        </tr>
        </form>
<%
		}
	}
%>
        <tr>
          <td colspan="2" rowspan="2"><div align="center" class="style6">总计</div></td>
          <td><div align="center" class="style7"><strong><%=hard1Num %>题</strong></div></td>
          <td><div align="center" class="style7"><strong><%=hard2Num %>题</strong></div></td>
          <td><div align="center" class="style7"><strong><%=hard3Num %>题</strong></div></td>
          <td><div align="center" class="style7"><strong><%=hard4Num %>题</strong></div></td>
          <td><div align="center" class="style7"><strong><%=hard5Num %>题</strong></div></td>
          <td><div align="center" class="style7"><strong><%=nohardNum %>题</strong></div></td>
          <td rowspan="2"><div align="center" class="style7"><strong><%=totalScore %>分</strong></div></td>
          <td rowspan="2"><div align="center">
            <input type="button" name="Submit" value=" 完 成 " <%if(totalScore != 100){%>disabled<%} %> onclick="javascript:finish()">
          </div></td>
        </tr>
        <tr>
          <td colspan="6"><div align="center" class="style7"><strong>共<%=hard1Num + hard2Num + hard3Num + hard4Num + hard5Num + nohardNum %>题</strong></div></td>
        </tr>
        <tr>
          <td colspan="10">说明：点击[完成]按钮将作业置为可发布状态，总分达到100分才能执行完成操作。</td>
        </tr>
      </table>
      
      </td>
  </tr>
</table>
</body>
</html>