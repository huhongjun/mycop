<%@ page contentType="text/html; charset=UTF-8" %>
<link href="<%=request.getContextPath()%>/css/cm-tt.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/cm-tb.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/cm-btn.css" rel="stylesheet" type="text/css" />
<form name="paperForm" method="post" action="<%=request.getContextPath()%>/Paper.do?method=savePaper">
<input type="hidden" name="courseId" value="<%=request.getAttribute("courseId") %>">
<table border="0" width="100%">
  <tr bgcolor="#FFFFFF">
    <td class="tt_bg">添加固定试卷信息</td>
  </tr>
  <tr>
    <td><div align="left">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tb-cm">
        <tr>
          <td colspan="4" class="cm_tt_bg"><strong> 增加固定试卷</strong></td>
        </tr>
        <tr class="bg1">
          <td width="10%"> 试卷名称</td>
          <td><input name="name" size="30" maxlength="50"/>
          </td>
          <td>试卷来源</td>
          <td width="59%">
            <input name="source" size="30" maxlength="50"/>
         </td>
        </tr>
        <tr class="bg2">
          <td width="10%" height="50">备&nbsp;&nbsp;&nbsp; 注</td>
          <td colspan="3"><textarea rows="8" name="description" cols="60" ></textarea></td>
        </tr>
        <tr class="bg1">
          <td align="center" colspan="4"> 　</td>
        </tr>
        <tr class="bg2">
          <td align="left" colspan="4"><p align="center">
            <input class="btn_cm" type="button" value="继续" name="B7" onclick="javascript:sbt()"/>
            <input class="btn_cm" type="button" value="返回" name="B1" onclick="javascript:history.go(-1)"/>
            &nbsp; </p></td>
        </tr>
      </table>
      <p align="center">　</p>
      <p> </p>
    </div></td>
  </tr>
  <tr bgcolor="#FFFFFF">
    <td width="84%" height="21" align="left">&nbsp;</td>
  </tr>
</table>
</form>
<script language="javascript">
function sbt(){
	if(paperForm.name.value == null || paperForm.name.value == ""){
		alert("请输入试卷名称!");
		paperForm.name.focus();
		return;
	}
	paperForm.submit();
}
</script>