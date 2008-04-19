<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<%@ page import="com.zhjedu.util.DateTimeUtil"%>
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
<script type="text/JavaScript">
<!--
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script>
<table border="0" width="100%" id="table4">
  
  <tr>
    <td><div align="left">
      <table border="0" width="100%" id="table4">
        <tr bgcolor="#7FBEE6">
          <td width="96%" height="30" colspan="4"><p align="center"> <span class="style1">考试信息 </span>　</p></td>
        </tr>
        <tr bgcolor="#EEF7FF">
          
          <td width="96%" height="28"><span class="style1"> 考试名称：</span></td>
          <td width="96%" height="28" align="left"><html:text property="zjQuiz.name" name="qe" size="29" /></td>
        </tr>
        <tr bgcolor="#F7FBFF">
          <td width="96%" height="24"><span class="style1"> 学生姓名：</span></td>
          <td width="96%" height="24"><html:text property="student.realName" name="qe" size="29" /></td>
          <td width="96%" height="24" align="center"><p align="left" class="style1"> 方&nbsp;&nbsp;&nbsp; 式：</p></td>
          <td height="24" align="left"><html:select property="zjQuiz.examType" name="qe" size="1">
           
            <html:option value="1">固定</html:option>
			<html:option value="2">手工</html:option>
			<html:option value="0">随机</html:option>
          </html:select>
          </td>
        </tr>
        <tr bgcolor="#EEF7FF">
          <td width="96%"><span class="style1"> 允许参加次数</span></td>
          <td width="96%"><html:select property="zjQuiz.maxExamNum" name="qe" size="1" >
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
          </html:select>
           </td>
          <td width="96%" align="center"><p align="left" class="style1">计分方式</p></td>
          <td width="96%" align="left"><html:select property="zjQuiz.grademethod" size="1" name="qe">
            <html:option value="0">最后成绩</html:option>
            <html:option value="1">平均成绩</html:option>
            <html:option value="2">最高成绩</html:option>
            
          </html:select>
         </td>
        </tr>
        <bean:define id="tmp" name="qe" property="timefinish"/>
        <bean:define id="tmp1" name="qe" property="zjQuiz.timeopen"/>
        <bean:define id="tmp2" name="qe" property="zjQuiz.timeclose"/>                  
        <tr bgcolor="#F7FBFF">
          <td width="96%"><span class="style1"> 提交时间：</span></td>
          <td width="96%"><input type="text" value="<%=DateTimeUtil.getTime((Long)tmp,2) %>" name="T5" size="29" /></td>
          <td width="96%" align="center"><p align="left" class="style1"> 考试状态：</p></td>
          <td align="left"><html:select property="status" size="1" name="qe">
            <html:option value="2">未批改</html:option>
            <html:option value="3">已批改</html:option>
            
          </html:select></td>
        </tr>
        <tr bgcolor="#EEF7FF">
          <td width="96%"><span class="style1"> 有 效 期：</span></td>
          <td width="96%"><input type="text" name="T7" size="29" value="<%=DateTimeUtil.getTime((Long)tmp1,2) %>--<%=DateTimeUtil.getTime((Long)tmp2,2) %>" /></td>
          <td align="center" width="96%"><p align="left" class="style1"> 分&nbsp;&nbsp;&nbsp; 数：</p></td>
          <td align="left"><html:text property="grade" name="qe" size="29" /></td>
        </tr>
        <tr bgcolor="#F7FBFF">
          <td width="96%" height="53"><span class="style1"> 备&nbsp;&nbsp;&nbsp; 注：</span></td>
          <td height="53" colspan="3"><html:textarea property="zjQuiz.description" name="qe" rows="3" cols="67"/>
          </td>
        </tr>
       <tr bgcolor="#EEF7FF">
          <td width="96%" height="73"><span class="style1"> 评&nbsp;&nbsp;&nbsp; 语：</span></td>
          <td height="73" colspan="3"><p align="left">
            <html:textarea property="qe.postil" name="qe" rows="3" cols="67"/>
          </p></td>
        </tr>
        <tr>
          <td align="center" colspan="4">&nbsp;
                <input type="button" onclick="MM_goToURL('parent','<%=request.getContextPath() %>/joinExam.do?method=quizQuestionAnswerInfo&userId=<bean:write name="qe" property="userid"/>&quizId=<bean:write name="qe" property="zjQuiz.id"/>');return document.MM_returnValue" value="批改考试" name="B8" />
            &nbsp;&nbsp;&nbsp;
            <input name="B7" type="button" onclick="javascript:history.go(-1)" value="返回" />
            &nbsp; </td>
        </tr>
      </table>
      <p>　</p>
    </div></td>
  </tr>
</table>

