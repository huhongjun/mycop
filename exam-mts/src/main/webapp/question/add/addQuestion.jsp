<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<script src="../js/edit.js" type="text/javascript"></script>
<script language=javascript src="../js/question.js"></script>
<style type="text/css">
<!--
.style1 {font-size: 12px}
-->
</style>
<link href="<%=request.getContextPath()%>/css/ksmain.css" rel="stylesheet" type="text/css" />
<script>
	function sbt(){
		var flag=false;
        if(isNaN(document.QuestionForm.defaultgrade.value))   
          {
          	alert("默认分值必须为数字!");   
  			document.QuestionForm.defaultgrade.value="";
  			
          	flag = false;   
          } 
          else{
          	flag = true;
          }
         
          return flag;
	}
	
	function contentonload()
	{
   		Composition.document.body.innerHTML=changebyte(hq_content.innerHTML);
   		answerarea.innerHTML=changebyte(hq_option.innerHTML);
   		hq_option.innerHTML="";
   		hq_content.innerHTML="";
	}
</script>
<html:form method="post" action="/Question.do" onsubmit="if(!sbt()){return false;}">
	<html:hidden property="id" name="question"/>
	<html:hidden property="parent" name="question"/>
	<html:hidden property="qtype" value="<%=(String)request.getAttribute("qtype") %>"/>
	<html:hidden property="method" value="addQuestion"/>
	<html:hidden property="course" value="<%=(String)request.getAttribute("course") %>"/>
	<html:hidden property="chapter" value="<%=(String)request.getAttribute("chapter") %>"/>
	<html:hidden property="section" value="<%=(String)request.getAttribute("section") %>"/>
	<html:hidden property="category" value="<%=(String)request.getAttribute("category") %>"/>
	<div align="center"> 
		<table border="0" width="100%" id="table4">
			<tr>
				<td colspan="4">
					<div align="left"><img src="<%=request.getContextPath() %>/image/x-006.gif" width="300" height="70"></div>
				</td>
			</tr>
			<tr>
				<td colspan="4" bgcolor="#CAE4FF">
					<p align="center" class="style1">查看/增加/修改<!--%=(String)request.getAttribute("qname") %--></p>
				</td>
			</tr>
			<tr bgcolor="#EEF7FF">
				<td>
					<p align="right" class="style1">试题来源</p>
				</td>
				<td align="center">
					<p align="left"><html:text property="source" name="question" size="20" style="float: left"/></p>
				</td>
				<td>
					<p align="left"></p>　
				</td>
				<td>
				</td>
			</tr>
			<tr bgcolor="#F7FBFF">
				<td align="right">
					<p align="right" class="style1">试题难度</p>
				</td>
				<td align="center">
					<p align="left">
					<html:select size="1" property="difficulty" name="question">
						<html:option value="0.1">0.1</html:option>
						<html:option value="0.2">0.2</html:option>
						<html:option value="0.3">0.3</html:option>
						<html:option value="0.4">0.4</html:option>
						<html:option value="0.5" >0.5</html:option>
						<html:option value="0.6">0.6</html:option>
						<html:option value="0.7">0.7</html:option>
						<html:option value="0.8">0.8</html:option>
						<html:option value="0.9">0.9</html:option>
						<html:option value="1.0">1.0</html:option>
					</html:select>
					<span class="style1">(数字越大表示难度越大)</span></p>
				</td>
				<td class="style1" align="right">试题区分度</td>
				<td>
					<html:select size="1" property="distinguish" name="question">
						<html:option value="1">1</html:option>
						<html:option value="2">2</html:option>
						<html:option value="3">3</html:option>
						<html:option value="5">4</html:option>
						<html:option value="5">5</html:option>
						<html:option value="6">6</html:option>
						<html:option value="7">7</html:option>
						<html:option value="8">8</html:option>
						<html:option value="9">9</html:option>
						<html:option value="10">10</html:option>
					</html:select>
					<span class="style1">(数字越大表示区分度越大)</span>
				</td>
			</tr>
			<tr bgcolor="#EEF7FF">
				<td align="right">
					<p align="right" class="style1">试题用途</p>
				</td>
				<td align="center">
					<p align="left">
					<html:select size="1" property="purpose" name="question">
						<html:option value="通用">通用</html:option>
						<html:option value="考试">考试</html:option>
						<html:option value="考试">考试</html:option>
						<html:option value="测试">测试</html:option>
					</html:select></p>
					</td>
					<td align="right">
						<p align="right" class="style1">考查能力</p>
					</td>
					<td align="center">
						<p align="left">
						<html:select size="1" property="ability" name="question">
							<html:option value="应用">应用</html:option>
							<html:option value="记忆">记忆</html:option>
							<html:option value="理解">理解</html:option>							
							<html:option value="分析">分析</html:option>
							<html:option value="综合">综合</html:option>
							<html:option value="评价">评价</html:option>
						</html:select></p>
					</td>
				</tr>
			<tr bgcolor="#EEF7FF">
				<td align="right">
					<p align="right" class="style1">默认分值</p>
				</td>
				<td align="center">
					<p align="left"><html:text property="defaultgrade" name="question" size="20" style="float: left"  /><span class="style1">分</span></p>
				</td>
				<td align="right">
						<p align="right" class="style1">试题状态</p>
					</td>
					<td align="center">
						<p align="left">
						<html:select size="1" property="status" name="question">
						<html:option value="0">启用</html:option>
						<html:option value="1">停用</html:option>
						</html:select></p>
					</td>
				
			</tr>
			
			
				<tr bgcolor="#F7FBFF">
					<td align="right">
						<p align="right" class="style1">关键字</p>
					</td>
					<td align="center" cosspan="3">
						<p align="left"><html:text property="questionKey" name="question" size="20" style="float: left"/></p>
					</td>
					
				</tr>
				<tr>
					<td align="right" bgcolor="#EEF7FF">
						<p align="right" class="style1">备&nbsp;&nbsp;&nbsp; 注&nbsp;</p> 
					</td>
					<td align="center" bgcolor="#EEF7FF" colspan="3">
						<p align="left"><html:textarea rows="2" property="memo" name="question" cols="60" /></p>
					</td>
				</tr>
		</table>
	</div><br>
    <div align="center">
		<table width="79%" border="0" cellpadding="2" cellspacing="0" class="tbsort">
		  <tbody>
              <tr class="main5" align="center">
                <td align="center" nowrap></td>
                <td align="center" nowrap>               
                  &nbsp;<input name="Submit"  class="btn_cm_small" type="submit" class="xbutton" value="下一步">
                  <input name="Reset"  class="btn_cm_small" type="reset" class="xbutton" value="重置">
                  <input name="button"  class="btn_cm_small" type="button" class="xbutton" onClick="javascript:history.go(-1)" value="返回">                                 
                </td>
                <td align="center" nowrap>　</td>
              </tr>
              
          </tbody>
        </table>
	</div>
</html:form>