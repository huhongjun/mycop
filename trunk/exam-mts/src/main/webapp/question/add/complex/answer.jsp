<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="com.zhjedu.util.Constants" %>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<%@ page  import="com.fredck.FCKeditor.*" %>
<script language=javascript>
function sbt(){
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
	    return rtflag;
	}
</script>

<html:form method="post" action="/Question.do" onsubmit="if(!sbt()){return false;}">
	<html:hidden property="method" value="savesubquestion"/>
	<html:hidden property="parent" value="<%=request.getParameter("parent")%>"/>
	<html:hidden property="createdate" name="question" />
	<html:hidden property="id" name="question" />
	<html:hidden property="qtype" value="<%=Constants.QUESTION_ANSWER %>" />
	<div align="center">
	
    	<table width="100%"  cellpadding="2" cellspacing="0" class="tbsort">
          <tbody>
          	<tr bgcolor="#EEF7FF">
						<td width="15%" align="center">
						<p align="left" class="style1">测试分数</td>
						<td align="center">
						<p align="left">
						<html:text property="defaultgrade" name="question" size="20" style="float: left"/><span class="style1">分</span></td>
					</tr>
					
					
					<tr>
						<td align="center" bgcolor="#EEF7FF" width="15%">
						<p align="left" class="style1">备&nbsp;&nbsp;&nbsp; 注&nbsp; </td>
						<td align="center" bgcolor="#EEF7FF" colspan="3">
						<p align="left"><html:textarea rows="2" property="memo" name="question" cols="60" /></td>
					</tr>
          	<tr><td align="center" nowrap class="style1" width="100%"><%=(String)request.getAttribute("qname") %>-试题内容</td></tr>
          	<tr width=90% >
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
       <div id="answercontet">
       	<tr width="100%" >
   			<td nowrap colspan=3 align="center" class="style1"  >请输入简答题参考答案</td>
		</tr>
		<tr width="100%" >
    		<td colspan=3 align="center" id="answerarea"  class="main5">
       		 	<textarea name="answer" name="question" rows="8" style="width:95%"  ></textarea>
    		</td>
		</tr>
     </div>			
  			
    <div align="center">
		<table width="79%" border="0" cellpadding="2" cellspacing="0" class="tbsort">
		  <tbody>
            <iframe  height="0"  id="reviewframe" src="/resource/question/review.jsp"></iframe>
              <tr class="main5" align="center">
                <td align="center" nowrap></td>
                <td align="center" nowrap>               
                  &nbsp;<input name="Submit" type="submit" class="xbutton" value="确定">
                  <input name="updatasubmit1" type="button" class="xbutton" onClick="back();" value="取消">                                 
                </td>
                <td align="center" nowrap>　</td>
              </tr>
              
          </tbody>
        </table>
	</div>
</html:form>