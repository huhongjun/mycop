<%@ include file="/WEB-INF/jspf/templates/header2.jspf" %>
<html>
<head>
<title>用户管理</title>
</head>
<body class="bodybg">
<gmenu:getpathtag/>
<table width="95%" align="center" class="ListTable">
  <tr>
    <td class="ListingTitle">系统用户</td>
  </tr>
  <form method="post" action="/listUser.do">
  <tr align="right">
    <td>
      <input type="submit" value="新增" class="Button"/>
      <input type="reset" value="删除" class="Button"/>
      <input type="button" value="返回" class="Button"/>
      <!-- 带权限验证的按钮例子
      <perm:button property="addbt" styleClass="button" onclick="toAction(this,'toCreate')" value="新建" rescode="GDP-YHGL" optcode="ALL" />
      <perm:button property="delbt" styleClass="button" onclick="return toAction(this,'delete','请问确定要删除所选记录吗？')" value="删除" rescode="GDP-YHGL" optcode="ALL"/>
      <perm:button property="searbt" styleClass="button" onclick="toAction(this, 'toQuery')" value="查询" rescode="GDP-YHGL" optcode="VIEW"/>
      -->      
    </td>
  </tr>
  <tr>
    <td>
      <table width="100%" cellpadding="2" cellspacing="0" class="ListBody">
        <tr class="ListTitleBar">
          <td width="10%">选择</td>
          <td width="20%">帐号</td>
          <td width="20%">姓名</td>
          <td width="10%">年龄</td>
          <td width="40%">地址</td>
        </tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><input type="checkbox"/></td>
          <td class="ListContent">001</td>
          <td class="ListContent">Jack</td>
          <td class="ListContent">20</td>
	  <td class="ListContent">Canton</td>
	</tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><input type="checkbox"/></td>
          <td class="ListContent">002</td>
          <td class="ListContent">Kate</td>
          <td class="ListContent">20</td>
	  <td class="ListContent">Canton</td>
	</tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><input type="checkbox"/></td>
          <td class="ListContent">003</td>
          <td class="ListContent">Rose</td>
          <td class="ListContent">20</td>
	  <td class="ListContent">Canton</td>
	</tr>
	<tr onmouseout="javascript:changeBgColorOnMouseOut(this);" onmouseover="javascript:changeBgColorOnMouseOver(this);">
          <td class="ListContent"><input type="checkbox"/></td>
          <td class="ListContent">004</td>
          <td class="ListContent">Zen</td>
          <td class="ListContent">20</td>
	  <td class="ListContent">Canton</td>
	</tr>		
      </table>
    </td>
  </tr>
  </form>
  <tr>
    <td>
      <!-- 分页区（通常是一个标签） -->
      <!-- GDP分页标签
      <gmenu:allpager name="pageControl" page="/privilege/roleAction.do?action=list"  usepic="true"/>
      -->      
    </td>
  </tr>
</table>
</body>
</html>
<%@ include file="/WEB-INF/jspf/templates/footer2.jspf" %>

<!-- 

CSS样式说明：

名称		说明
================================================
ListTable: 	保留名称（扩展用）
ListingTitle: 	用于列表页面的标题区
ListBody:`	保留名称（扩展用）
ListTitleBar:	用于列表页面的标题栏
ListContent:	用于列表页面的内容区
Button:		用于按钮（按钮类型包括submit,reset,button）

-->