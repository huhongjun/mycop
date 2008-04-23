<%@ include file="/WEB-INF/jspf/templates/header.jspf" %>
<form method="post" action="/privilege/roleAction.do">
<table width="50%" align="center" class="EditTable">
  <tr> 
    <td class="EditTitle">新增用户</td>
  </tr>
  <tr> 
    <td> 
      <table width="100%" class="EditBody">
        <tr> 
          <td width="30%">&nbsp;</td>
          <td width="70%">&nbsp;</td>
        </tr>  
        <tr> 
          <td class="EditLabel">帐号：</td>
          <td class="EditContent"><input type="text" class="Input"/></td>
        </tr>  
        <tr> 
          <td class="EditLabel">姓名：</td>
          <td class="EditContent"><input type="text" class="Input"/></td>
        </tr>  
        <tr> 
          <td class="EditLabel">地址：</td>
          <td class="EditContent"><textarea class="Input"></textarea></td>
        </tr>  
        <tr> 
          <td clospan="2">&nbsp;</td>
        </tr>         
        <tr>
          <td>&nbsp;</td> 
          <td>
            <input type="submit" value="提交" class="Button"/>
            <input type="reset" value="重置" class="Button"/>
            <input type="button" value="返回" class="Button"/>
            <!-- 带权限验证的按钮例子
	    <perm:button property="addbt" styleClass="button" onclick="toAction(this,'toCreate')" value="新建" rescode="GDP-YHGL" optcode="ALL" />
	    <perm:button property="delbt" styleClass="button" onclick="return toAction(this,'delete','请问确定要删除所选记录吗？')" value="删除" rescode="GDP-YHGL" optcode="ALL"/>
	    <perm:button property="searbt" styleClass="button" onclick="toAction(this, 'toQuery')" value="查询" rescode="GDP-YHGL" optcode="VIEW"/>
            -->               
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>      
</form>
<%@ include file="/WEB-INF/jspf/templates/footer.jspf" %>

<!-- 

CSS样式说明：

名称		说明
================================================
EditTable: 	保留名称（扩展用）
EditTitle: 	用于编辑页面的标题区（编辑页面包括新增，更新页面）
EditBody:`	保留名称（扩展用）
EditLabel:	用于编辑页面的标签区
EditContent:	用于编辑页面的内容区
Input:		用于输入框（输入框包括input,textarea）
Button:		用于按钮（按钮类型包括submit,reset,button）

-->
