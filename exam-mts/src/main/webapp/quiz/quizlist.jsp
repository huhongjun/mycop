<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK"%>
<%@ page import="com.zhjedu.util.Constants"%>
<%@ page import="com.zhjedu.util.DateTimeUtil"%>
<script src="../js/edit.js" type="text/javascript"></script>
<script language=javascript src="../js/question.js"></script>
<link href="<%=request.getContextPath() %>/css/wxj.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/ksmain.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=request.getContextPath()%>/js/meizzDate.js"></script>
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
<script language=javascript>
function check(){
	flag=confirm("你确定要删除此项吗？");
	return flag;
}
function addQuiz(){
	document.QuizForm.method.value="addQuiz";
	document.QuizForm.submit();
}
function cat_question(){
	document.QuizForm.method.value="main_category";
	document.QuizForm.target="_parent";
	document.QuizForm.submit();
}
function listcategory(){  
	document.QuizForm.method.value="mainCategory";
	document.QuizForm.target="_parent";
	document.QuizForm.submit();
}
function search(){
	QuizForm.target = "_self";
	QuizForm.method.value = "search";
	QuizForm.submit();
	return true;
}
function editquiz(){
	var j = 0;
	for(var i=0;i<QuizForm.quizid.length;i++){
		if(QuizForm.quizid[i].checked==true){
			j=j+1;
		}
	}
	
	if(j==0){
		alert("请选择要编辑的考试！");
		return;
	}
	else if(j>1){
		alert("一次只允许编辑一个考试信息！");
		return;
	}
	else{
		QuizForm.target = "_self";
		QuizForm.method.value = "edit";
		QuizForm.submit();
		return true;
	}
	
}
function removequiz(){
  if(check()){
	var j = 0;
	for(var i=0;i<QuizForm.quizid.length;i++){
		if(QuizForm.quizid[i].checked==true){
			j=j+1;
		}
	}
	
	if(j==0){
		alert("请选择要删除的题目！");
		return;
	}
	
	else{
		QuizForm.target = "_self";
		QuizForm.method.value = "remove";
		QuizForm.submit();
		return true;
	}
	}
	
}

function selectscope(quizid){
		
		var scopechar = "";
		
		var strFeatures = "dialogWidth: 900px; dialogHeight: 600px; center: yes; scroll:no;help: no; status: no; edge: raised; resize:yes";
	    var result = new Array;
	    result = window.showModalDialog("<%=request.getContextPath()%>/quiz/selectUserScopeForQuiz.jsp", strFeatures);
	    
	    if(result != null){
	    	
	    	for(var i = 0; i < result.length; i ++){
	    		var info = result[i];
	    		if(i==0){
	    			scopechar = info[0];
	    		}
	    		else{
	    			scopechar = scopechar + "," + info[0];
	    		}
				
	    	}
	    }
	    alert(scopechar);
	    document.QuizForm.ec.value = scopechar;
	    document.QuizForm.method.value = "addQuizToMidEC";
	    document.QuizForm.quiz.value = quizid;
	    
	    document.QuizForm.submit();
	}
</script>

<html:form action="Quiz.do">
	<html:hidden property="method" value="search" />
	<%
	String category="";
	
	if(request.getParameter("category")!=null&&!request.getParameter("category").equals("")){
		category = request.getParameter("category");
	}
	%>
	<input type=hidden name="category" value="<%=category%>" />
	<input type=hidden name="ec" value="" />
	<input type=hidden name="quiz" value="" />
	 <table border="0" width="100%" id="table4">
                    <tr>
                      <td width="100%" height="30">
                      <p class="style1"> 您现在的位置是：考试中心  / 考试管理 /</p></td>
                    </tr>
                    <tr >
                      <td height="35" align="left"><div align="left"><span class="style3">
                        <input name="B40" type="button" onClick="addQuiz();" value="添加考试">
                        <input name="B41" type="button" onClick="editquiz();" value="修改考试">
                        <input type="button" onClick="removequiz();" value="删除考试" name="B42">                        
                        <input type="button" onClick="listcategory();" value="目录维护" name="B43">
                        <input type="button" onClick="cat_question();" value="题目维护" name="B43">
                      </span></div></td>
                    </tr>
                    <tr>
                      <td align="center">
                      <table width="100%" border="0" id="table4">
                        <tr>
                         
                          <td height="21" bgcolor="#FFFFFF" width="101%" colspan="8">
                              <span class="style1">考试名称</span>    
                              <%
                              String key = "";
                              if(request.getAttribute("key")!=null){
                              	key = (String)request.getAttribute("key");
                              }
                               %>                          
                              <input type="text" name="key" value="<%=key%>">
							  &nbsp;&nbsp;&nbsp; 
							  <span class="style1">考试时间</span> 
							  <%
                              String timeopen = "";
                              if(request.getAttribute("timeopen")!=null){
                              	timeopen = (String)request.getAttribute("timeopen");
                              }
                               %>          
							  <input type="text" name="timeopen" value="<%=timeopen%>" onclick="setday(this)">
        					  
								&nbsp;&nbsp;
        					   <input type="button" onclick="search();" value="查  询" name="B34">
                          </td>
                        </tr>
                        <tr bgcolor="#7FBEE6">
                          <td width="5%" height="21" align="center"> <input type="checkbox" name="quizid"></td>
                          <td width="10%" height="21" align="center"><span class="style1"> 考试名称</span></td>
                          <td width="18%" height="21" align="center"><span class="style1"> 有效期</span></td>
                          <td width="8%" height="21" align="center"><span class="style1"> 创建时间</span></td>
                          <td width="8%" height="21" align="center"><span class="style1"> 作业方式</span></td>
                          <td width="8%" height="21" align="center"><span class="style1"> 记分方式</span></td>
                          
                          <td width="10%" height="21" align="center"><span class="style1"> 状态</span></td>
                          <td width="18%" height="21" align="center"><span class="style1"> 操作</span></td>
                        </tr>
                        <%com.zhjedu.util.PageObject po=(com.zhjedu.util.PageObject)request.getAttribute("po");%>   
  						<bean:define id="list" name="po" property="datas"/>
  						<logic:iterate indexId="ind" id="element" name="list">
                        <tr bgcolor="#EEF7FF" align="center">
                          <td><p align="center">
                            <logic:notEqual name="element" property="invigilator1" value="1">
      						<input type="checkbox" name="quizid" value="<bean:write name="element" property="id"/>" />
      						</logic:notEqual>
    					  </p></td>
                          <td height="21" bgcolor="#EEF7FF">
                          <logic:notEqual name="element" property="invigilator1" value="1">
                          <a href="<%=request.getContextPath()%>/Quiz.do?method=edit&quizid=<bean:write name="element" property="id"/>">
                          <bean:write name="element" property="name"/>
                          </a>
                          </logic:notEqual>
                          
                          <logic:equal name="element" property="invigilator1" value="1">
                          	<bean:write name="element" property="name"/>
                          
                          </logic:equal>
                          </td>
                          <bean:define id="start" name="element" property="timeopen"/>
                          <bean:define id="end" name="element" property="timeclose"/>
                          <bean:define id="create" name="element" property="createdate"/>
                          <td height="21"><span class="style1"><%=DateTimeUtil.getTime((Long)start,2) %>至<%=DateTimeUtil.getTime((Long)end,2) %></span></td>
                          <td height="21"><span class="style1"><%=DateTimeUtil.getTime((Long)create,2) %></span></td>
                          <td height="21"> <span class="style1">　
                          	<logic:equal name="element" property="quizStyle" value="1">固定</logic:equal>
                          	<logic:equal name="element" property="quizStyle" value="2">手工</logic:equal>
                          	<logic:equal name="element" property="quizStyle" value="0">随机</logic:equal>
                          </span></td>
                          <td align="left" height="21"><span class="style1">
                          	<logic:equal name="element" property="grademethod" value="1">最高分</logic:equal>
                          	<logic:equal name="element" property="grademethod" value="2">平均分</logic:equal>
                          	<logic:equal name="element" property="grademethod" value="3">最后得分</logic:equal>
                          </span></td>
                          <!-- <td height="21" bgcolor="#EEF7FF"><span class="style1"><bean:write name="element" property="maxExamNum"/></span></td> -->
                          <td height="21" align="center"><span class="style1">
                          <logic:equal name="element" property="status" value="1">停用</logic:equal>
                          <logic:equal name="element" property="status" value="0">可用</logic:equal>
                          <logic:equal name="element" property="invigilator1" value="1">
      						已发布
      						</logic:equal>
      						<logic:notEqual name="element" property="invigilator1" value="1">
      						未发布
      						</logic:notEqual>
                          </span></td>
                          <td height="21" align="center">
                          <logic:equal name="element" property="invigilator1" value="1">
                          <input class="btn_cm_small" type="button" value="发布" name="B" onclick="javascript:selectscope('<bean:write name="element" property="id"/>')" disabled/>
      						</logic:equal>
      						<logic:notEqual name="element" property="invigilator1" value="1">
                          <input class="btn_cm_small" type="button" value="发布" name="B" onclick="javascript:selectscope('<bean:write name="element" property="id"/>')"/>
      						</logic:notEqual>
                          <input class="btn_cm_small" type="button" value="查看" name="B" onclick="javascript:window.location.href='<%=request.getContextPath() %>/Quiz.do?method=viewQuiz&quiz=<bean:write name="element" property="id"/>'"/>
                          <logic:equal name="element" property="status" value="1">
                          	<input class="btn_cm_small" type="button" value="启用" name="B" onclick="javascript:window.location.href='<%=request.getContextPath() %>/Quiz.do?method=restart&quiz=<bean:write name="element" property="id"/>'"/>
                          </logic:equal>
                          <logic:equal name="element" property="status" value="0">
                          	<input class="btn_cm_small" type="button" value="停用" name="B" onclick="javascript:window.location.href='<%=request.getContextPath() %>/Quiz.do?method=stop&quiz=<bean:write name="element" property="id"/>'"/>
                          </logic:equal>
                          <input class="btn_cm_small" type="button" value="预览" name="B" onclick="javascript:window.open('<%=request.getContextPath() %>/joinExam.do?method=quizYulan&quizId=<bean:write name="element" property="id"/>')"/>
                          
                          </td>
                        </tr>
                        </logic:iterate>                     
                       
                      </table>
                      </td>
                    </tr>
                  </table>
                  <table border="0" width="100%" id="table4">
  <tr>
    <td align="center" colspan="8"> 　</td>
  </tr>
  <tr>
		<td align="center" colspan="8"><span class="style1">
		<page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="QuizForm" pageVar="page" jsName="changePage" url="Question.do?method=list"/>
		</span></td>
  </tr>
</table>
				</html:form>
