<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<%@ page import="com.zhjedu.util.Constants" %>
<%@ page import="com.zhjedu.util.DateTimeUtil" %>
<%@ page  import="com.fredck.FCKeditor.*" %>
<script language=javascript>
function sbt(){
		var editor=FCKeditorAPI.GetInstance("questioncontext");   
		var content=FCKeditorAPI.GetInstance("questioncontext").GetXHTML(true);//editor.EditorDocument.body.innerHTML; 
	
		var t = false;
		var rtflag = true;
		
		if(content== ""||content==null){
			alert("请输入题目内容!");
			rtflag=false;
			return false;
		}
	    return rtflag;
	}
</script>
<script language="javascript">
	function submitquestion(action){
		var editor=FCKeditorAPI.GetInstance("questioncontext");   
		var content=FCKeditorAPI.GetInstance("questioncontext").GetXHTML(true);//editor.EditorDocument.body.innerHTML; 
	
		var t = false;
		var rtflag = true;
		if(isNaN(document.QuestionForm.defaultgrade.value))   
          {
          	alert("默认分值必须为数字!");   
  			document.QuestionForm.defaultgrade.value="";
  			document.QuestionForm.defaultgrade.focus();
          	rtflag = false;   
          } 
          else{
          	rtflag = true;
          }
		if(content== ""||content==null){
			alert("请输入题目内容!");
			rtflag=false;
			return false;
		}
	    
		QuestionForm.actiontype.value=action;
		QuestionForm.submit();
		return rtflag;
	}
	function addsubquestion(action){
		var editor=FCKeditorAPI.GetInstance("questioncontext");   
		var content=FCKeditorAPI.GetInstance("questioncontext").GetXHTML(true);//editor.EditorDocument.body.innerHTML; 
	
		var t = false;
		var rtflag = true;
		if(isNaN(document.QuestionForm.defaultgrade.value))   
          {
          	alert("默认分值必须为数字!");   
  			document.QuestionForm.defaultgrade.value="";
  			document.QuestionForm.defaultgrade.focus();
          	rtflag = false;  
          	return false;  
          } 
          else{
          	rtflag = true;
          }
		if(content== ""||content==null){
			alert("请输入题目内容!");
			rtflag=false;
			return false;
		}
		QuestionForm.method.value="addsubquestion";
		QuestionForm.actiontype.value=action;
		QuestionForm.submit();
		return rtflag;
	}
	
</script>
<html:form method="post" action="/Question.do">
	<html:hidden property="method" value="saveIntegrate"/>
	<html:hidden property="id" value="<%=request.getParameter("questionid") %>"/>
	<html:hidden property="parent" value="<%=request.getParameter("parent") %>"/>
	<html:hidden property="course" value="<%=request.getParameter("course") %>"/>
	<html:hidden property="chapter" value="<%=request.getParameter("chapter") %>"/>
	<html:hidden property="section" value="<%=request.getParameter("section") %>"/>
	<html:hidden property="category" value="<%=request.getParameter("category") %>"/>
	<html:hidden property="qtype" value="<%=request.getParameter("qtype") %>" />
	<html:hidden property="source" value="<%=(String)request.getAttribute("source") %>" />
	<html:hidden property="difficulty" value="<%=(String)request.getAttribute("difficulty") %>" />
	<html:hidden property="distinguish" value="<%=(String)request.getAttribute("distinguish") %>" />
	<html:hidden property="purpose" value="<%=(String)request.getAttribute("purpose") %>" />
	<html:hidden property="ability" value="<%=(String)request.getAttribute("ability") %>" />
	<html:hidden property="defaultgrade" value="<%=(String)request.getAttribute("defaultgrade") %>" />
	<html:hidden property="status" value="<%=(String)request.getAttribute("status") %>" />
	<html:hidden property="questionKey" value="<%=(String)request.getAttribute("questionKey") %>" />
	<html:hidden property="memo" value="<%=(String)request.getAttribute("memo") %>" />
	<html:hidden property="createdate" name="question" />
	<input type=hidden name="actiontype"/>

	<div align="center">
	
    	<table width="100%"  cellpadding="2" cellspacing="0" class="tbsort">
          <tbody>
          	<tr><td align="center" nowrap class="style1" width="100%"><!-- %=(String)request.getAttribute("qname") % -->试题内容</td></tr>
          	<tr width=100% >
          		<td colspan=3 bgcolor="menu">
          		
				<logic:equal name = 'question' property='id' value=''>
		      		<input type='hidden' name='questioncontext'>
				</logic:equal>        
				<logic:notEqual name = 'question' property='id' value=''>
		      		<input type='hidden'  name='questioncontext' value='<bean:write name='question' property='questioncontext'/>'>
				</logic:notEqual> 
				<%
					FCKeditor oFCKeditor ;
					oFCKeditor = new FCKeditor( request, "questioncontext" ) ;
					oFCKeditor.setHeight("250");
					oFCKeditor.setBasePath(request.getContextPath()+"/FCKeditor/" ) ;
					oFCKeditor.setValue( "" );
					out.println( oFCKeditor.create() ) ;
				%>
          		
          		</td>
          	</tr>
         </table>
       </div>
	   <div id="integratetype">
       	<tr width="100%" >
   			<td nowrap colspan=3 align="center" class="style1"  >综合题类型:
				<html:select property="alias">
					<html:option value="0">综合题(默认)</html:option>
					<html:option value="1">阅读理解</html:option>
					<html:option value="2">完型填空</html:option>
				</html:select>
			</td>
		</tr>
		
     </div>	
       <div align="center">
           <TABLE width="100%" border="0" class="table4">
            <THEAD>
              <tr>
              	<td height="21" bgcolor="#7FBEE6" width="5%"><p align="center" class="style1"><b>序号</b></p></td>
    
    <td height="21" bgcolor="#7FBEE6" width="10%"><p align="center" class="style1"><b><font class="style1">题型</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="35%"><p align="center"><b><font class="style1">&nbsp;题目描述</font></b></p></td>
  
    <td height="21" bgcolor="#7FBEE6" width="15%"><p align="center" class="style1"><b><font class="style1">关键字</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="5%"><p align="center" class="style1"><b><font class="style1">分值</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="10%"><p align="center" class="style1"><b><font class="style1">难度系数</font></b></p></td>
    <td height="21" bgcolor="#7FBEE6" width="10%"><p align="center" class="style1"><b><font class="style1">修改日期</font></b></p></td>
    <td width="30%" align="center" bgcolor="#7FBEE6" width="10%" nowrap class="style1"><p align="center" class="style1"><b><font class="style1">操作</font></b></p></td>
              </tr>
              <%com.zhjedu.util.PageObject po=(com.zhjedu.util.PageObject)request.getAttribute("po");%>   
  <bean:define id="list" name="po" property="datas"/>
  <logic:iterate indexId="ind" id="element" name="list">
             <tr bgcolor="#EEF7FF"  class="style1">
   
    <td align="center" bgcolor="#EEF7FF"><font class="style1" color="#0000FF"><%=ind+1 %></font></td>
    
    <td align="center" bgcolor="#EEF7FF"><div align="center"><font class="style1" ><font size="2" class="wenzi">
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_SINGLECHOICE %>">单选题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_MULTICHOICE %>">多选题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_JUDGE %>">判断题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_MATCHING %>">匹配题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_INPUTFILL %>">填空题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_ANSWER %>">问答题</logic:equal>
    <logic:equal name="element" property="qtype" value="<%=Constants.QUESTION_INTEGRATE %>">综合题</logic:equal>
    </font></font></div></td>
    <td><font size="2" class="wenzi">
    <span style="width:220px;height:15px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap"> 
		<bean:write name="element" property="questioncontext" filter="false"/> 
	</span> </font>
	</td>
    
    <td><font size="2" class="wenzi"><bean:write name="element" property="questionKey"/></font></td>
    <td align="center"><font size="2" class="style1" color="#0000FF"><bean:write name="element" property="defaultgrade"/></font></td>
    <td align="center"><font size="2" class="style1" color="#0000FF"><bean:write name="element" property="difficulty"/></font></td>
    <bean:define id="tmp" name="element" property="lasteditdate"/>
    <td align="center"><font size="2" class="style1"> <%=DateTimeUtil.getTime((Long)tmp,2) %></font></td>
    <TD width="20%" align="center" nowrap class="style1"><a href="<%=request.getContextPath()%>/Question.do?method=addSubQuestion&questionid=<bean:write name="element" property="id"/>&qtype=<bean:write name="element" property="qtype"/>&parent=<bean:write name="element" property="parent"/>">编辑</a>　<a href="<%=request.getContextPath()%>/Question.do?method=remove&questionid=<bean:write name="element" property="id"/>">删除</a></TD>
  </tr>
              </logic:iterate>
            </THEAD>
           
          </TABLE>
          <TABLE width="79%" border="0" cellpadding="2" cellspacing="0" class="tbsort">
            <tbody>
            <iframe  height="0"  id="reviewframe" src="/resource/question/review.jsp"></iframe>
              <TR  class="main5" align="center">
                <TD align="center" nowrap>　</TD>
                <TD align="center" nowrap>
                
                  <input name="Submit1" type="button" onclick="submitquestion(1);" class="xbutton" value="保存并添加小题">
                  
                  <input name="Submit" type="button"  onclick="submitquestion(2);" class="xbutton" value="确定">
                  <input name="updatasubmit1" type="button" class="xbutton" onClick="javascript:history.go(-1)" value="取消">
                  
                  
                </TD>
                <TD align="center" nowrap>　</TD>
              </TR>
              
            </tbody>
          </TABLE></div>
</html:form>