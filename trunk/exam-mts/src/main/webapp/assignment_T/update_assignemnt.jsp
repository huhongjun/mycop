<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<style type="text/css">
<!--
.style1 {font-size: 12px}
-->
</style>
<script type="text/javascript">
 function check()
 {
 	if(QuestionForm.T10.value=="")
 	{
 		alert("考试编号不能为空");
 		return;
 	}else if(QuestionForm.T11.value=="")
 	{
 		alert("考试名称不能为空");
 		return;
 	}
 	else if(QuestionForm.timeopen.value=="")
		 	{
		 		alert("有效期不能为空");
		 		return;
		 	}
		 	else if(QuestionForm.timeclose.value=="")
		 	{
		 		alert("有效期不能为空");
		 		return;
		 	}else if(QuestionForm.timeopen.value>QuestionForm.timeclose.value){
		 		alert("开始时间不能大于结束时间");
		 		return;
		 	}
 	else
 	{
 		window.document.all.QuestionForm.action="<%=request.getContextPath()%>/Question.do?method=updateassignemnt";
 		document.QuestionForm.submit();
 		return true;
 	}
 }
 function fanghui()
		 {
		 	window.document.all.QuestionForm.action="<%=request.getContextPath() %>/Question.do?method=listassignemnt";
		 	QuestionForm.submit();
			return true;
		 }
		 
		  function getdate(){
				var date=window.showModalDialog("<%=request.getContextPath()%>/others/Calendar.htm","日历选择器1","dialogHeight:270px;dialogWidth:330px;center:yes;status:no");
				QuestionForm.timeopen.value=date;
		 }
		 function getdate1(){
				var date=window.showModalDialog("<%=request.getContextPath()%>/others/Calendar.htm","日历选择器1","dialogHeight:270px;dialogWidth:330px;center:yes;status:no");
				QuestionForm.timeclose.value=date;
		 }
</script>
<html:form action="Question.do" method="post">
<% 
	String s=request.getAttribute("id").toString();
%>
<html:hidden property="id" value="<%=s %>"/>
<table border="0" width="100%" id="table4">
  <tr bgcolor="#FFFFFF">
    <td width="74%" height="40"><img src="<%=request.getContextPath() %>/image/x-020.gif" width="300" height="70" /></td>
  </tr>
  <tr>
    <td><div align="left">
      <table border="0" width="100%" id="table4">
        <tr bgcolor="#7FBEE6">
          <td height="30" colspan="4"><p align="center" class="style1"><strong> 修改考试</strong></p></td>
        </tr>
        <tr bgcolor="#EEF7FF">
          <td width="27%"><p align="left" class="style1"> 考试编号</p></td>
          <td align="center"><p align="left">
            <input name="T10" size="20" value="<%=s %>" style="float: left" />
          </p></td>
          <td align="center"><p align="left" class="style1">考试名称</p></td>
          <td width="18%" align="center"><p align="left">
            <input name="T11" size="20" value="<%=request.getAttribute("name") %>" style="float: left" />
          </p></td>
        </tr>
        <tr bgcolor="#F7FBFF">
          <td width="27%" align="left"><span class="style1"> 考试方式</span></td>
          <td width="21%" align="left"><select size="1" name="D1">
            <option> </option>
            <option>固定</option>
            <option>手工</option>
            <option>随机</option>
          </select></td>
          <td width="34%" align="left"><span class="style1"> 范&nbsp;&nbsp;&nbsp; 围</span></td>
          <td align="left"><input name="T13" size="20" style="float: left" />
                <input type="submit" value="选择" name="B14" src="" /></td>
        </tr>
        <tr>
          <td align="center" bgcolor="#EEF7FF" width="27%"><p align="left" class="style1">创 建 人</p></td>
          <td align="center" bgcolor="#EEF7FF" width="21%"><p align="left">
            <input name="T1" size="20" style="float: left" />
          </p></td>
          <td align="center" bgcolor="#EEF7FF" width="34%"><p align="left" class="style1">创建时间</p></td>
          <td align="center" bgcolor="#EEF7FF"><p align="left">
            <input name="T9" size="20" style="float: left" />
          </p></td>
        </tr>
        <tr bgcolor="#F7FBFF">
          <td align="center" width="27%"><p align="left" class="style1">总 分 数</p></td>
          <td align="center" colspan="3"><p align="left">
            <input name="T15" size="20" style="float: left" />
          </p></td>
        </tr>
        <tr bgcolor="#EEF7FF">
          <td align="center" width="27%"><p align="left" class="style1">有 效 期</p></td>
          <td align="center" colspan="3"><p align="left"> </p>
                <p align="left">
                  <input type="text" name="timeopen" value="<%=request.getAttribute("open") %>" size="20" />
                  <input type="button" value="日期" name="open" onclick="getdate();"/>
                  <span class="style1">至</span>
                  <input type="text" name="timeclose" value="<%=request.getAttribute("close") %>" size="20" />
                  <input type="button" value="日期" name="close" onclick="getdate1();"/>
              </p></td>
        </tr>
        <tr bgcolor="#F7FBFF">
          <td align="center" width="27%" height="50"><p align="left" class="style1">备&nbsp;&nbsp;&nbsp; 注</p></td>
          <td align="center" height="50" colspan="3"><p align="left">
            <textarea rows="4" name="S2" cols="60" ></textarea>
          </p></td>
        </tr>
       <tr>
          <td align="center" colspan="4"> 　</td>
        </tr>
        <tr>
          <td align="left" colspan="4"><p align="center">
            <input type="button" value="保存" name="B7" onclick="check()"/>
            <input type="button" value="返回" name="B1" onclick="fanghui()"/>
            <input name="B12" type="submit" onclick="MM_goToURL('parent','chelue_zuoye.htm');return document.MM_returnValue" value="进入策略" src="" />
            &nbsp; </p></td>
        </tr>
      </table>
      <p align="center">　</p>
      <p> </p>
    </div></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td width="84%" height="21" align="left"><div align="left"> </div></td>
  </tr>
</table>
</html:form>
