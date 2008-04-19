<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="FCK" %> 
<%@ page import="com.zhjedu.util.Constants" %>
<%@ page import="com.zhjedu.util.DateTimeUtil" %>
<%@ page  import="com.fredck.FCKeditor.*" %>
<script src="<%=request.getContextPath() %>/js/edit.js" type="text/javascript"></script>
<script language=javascript src="<%=request.getContextPath() %>/js/question.js"></script>


<form method="post" action="<%=request.getContextPath() %>/Question.do" onsubmit="if(!sbt()){return false;}">
	<html:hidden property="method" value="addsubquestion"/>
	
	<input type=hidden name="parent" value="<%=(String)request.getAttribute("parent") %>" />
	<div align="center">
	
    	<table width="100%"  cellpadding="2" cellspacing="0" class="tbsort">
          <tbody>
          	<tr><td align="center" nowrap class="style1" width="100%">试题内容</td></tr>
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
       <div class="block5_2">
			
    	<tr width=100% >
   <td  nowrap colspan=3 >
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
    <TD width="20%" align="center" nowrap class="style1"><a href="<%=request.getContextPath()%>/Question.do?method=addSubQuestion&questionid=<bean:write name="element" property="id"/>&qtype=<bean:write name="element" property="qtype"/>&parent=<bean:write name="element" property="parent"/>">编辑</a>　
    <a href="<%=request.getContextPath()%>/Question.do?method=remove&questionid=<bean:write name="element" property="id"/>">删除</a></TD>
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
                
                  <input name="Submit1" type="submit" class="xbutton" value="添加小题">
                  
                 
                  
                </TD>
                <TD align="center" nowrap>　</TD>
              </TR>
              
            </tbody>
          </TABLE>
   </td>
</tr>
	   </div>
    			
</form>