<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
		var answers = QuestionForm.answers;
		if(answers!=null){
			for(var l = 0; l < answers.length; l ++){
				if(answers[l].checked == true){
					t = true;
				}
			}	
		}
	
		if(!t){
			alert("请至少选择一个答案!");
			rtflag=false;
			return false;
		}
	    return rtflag;
	}
	</script>
<html:form method="post" action="/Question.do" onsubmit="if(!sbt()){return false;}">
	<html:hidden property="method" value="save"/>
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
	
	<div align="center">
	
    	<table width="100%"  cellpadding="2" cellspacing="0" class="tbsort">
          <tbody>
          	<tr><td align="center" nowrap class="style1" width="100%"><%=(String)request.getAttribute("qname") %>-试题内容</td></tr>
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
       <tr width=100% >
   <td  nowrap colspan=3 align="center" class="style1"  >请选择正确答案
   <div id="hq_content" style="position:absolute; overflow:scroll;width:200px; height:115px; z-index:1; visibility: hidden;">41s89s122s122s47s102s221s132s114s130s132s118s31s255s32s0</div>
   </td>
   
</tr>
<tr width=100% >
    <td colspan=3 align="center" class="main5">
        <html:radio property="answers" name="question" value="1"/>对    <html:radio property="answers" name="question" value="0"/>错
        
    </td>
</tr>
    			
  			
    <div align="center">
		<table width="79%" border="0" cellpadding="2" cellspacing="0" class="tbsort">
		  <tbody>
            <iframe  height="0"  id="reviewframe" src="/resource/question/review.jsp"></iframe>
              <tr class="main5" align="center">
                <td align="center" nowrap></td>
                <td align="center" nowrap>               
                  &nbsp;<input name="Submit" type="submit" class="xbutton" value="确定">
                  <input name="updatasubmit1" type="button" class="xbutton" onClick="javascript:history.go(-1)" value="取消">                                 
                </td>
                <td align="center" nowrap>　</td>
              </tr>
              
          </tbody>
        </table>
	</div>
</html:form>