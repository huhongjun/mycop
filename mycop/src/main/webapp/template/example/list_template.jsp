<%@ include file="/WEB-INF/jspf/templates/header.jspf" %>
<table width="95%" align="center" class="ListTable">
  <tr>
    <td class="ListingTitle"><!-- 标题区 --></td>
  </tr>
  <form method="post" action="">
  <tr align="right">
    <td>
      <!-- 按钮操作区 -->
    </td>
  </tr>
  <tr>
    <td>
      <table width="100%" cellpadding="2" cellspacing="0" class="ListBody">
        <tr class="ListTitleBar">
          <td width="10%"><!-- 字段区 --></td>
          <td width="20%"><!-- 字段区 --></td>
          <td width="20%"><!-- 字段区 --></td>
          <td width="10%"><!-- 字段区 --></td>
          <td width="40%"><!-- 字段区 --></td>
        </tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><!-- 内容区 --></td>
          <td class="ListContent"><!-- 内容区 --></td>
          <td class="ListContent"><!-- 内容区 --></td>
          <td class="ListContent"><!-- 内容区 --></td>
	  <td class="ListContent"><!-- 内容区 --></td>
	</tr>	
      </table>
    </td>
  </tr>
  </form>
  <tr>
    <td>
      <!-- 分页区（通常是一个标签） -->
      <!-- GDP分页例子
      <gmenu:allpager name="pageControl" page="/privilege/userAction.do?action=list"  usepic="true"/>
      -->
    </td>
  </tr>
</table>
<%@ include file="/WEB-INF/jspf/templates/footer.jspf" %>
