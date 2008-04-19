<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page"%>
<%@ page import="com.zhjedu.util.DateTimeUtil"%>
<link href="<%=request.getContextPath()%>/css/ksmain.css" rel="stylesheet" type="text/css" />
<form name="searchForm" method="post" action="<%=request.getContextPath()%>/Paper.do?method=listPaper&courseId=<%=request.getAttribute("courseId") %>">
<table width="98%" border="0" cellpadding="0" cellspacing="1">
  <tr>
    <td width="100%" colspan="11"><div class="shijuan_tt_bg">课程固定试卷列表</div></td>
  </tr>
  <tr>
    <td colspan="11" class="td_func">
    <a href="<%=request.getContextPath()%>/Paper.do?method=addPaper&courseId=<%=request.getAttribute("courseId") %>" class="btn_add_bg">添加试卷</a>
    <a href="javascript:editPaper()" class="btn_edit_bg">编辑试卷</a>
    <a href="javascript:removePaper()" class="btn_del_bg">删除试卷</a>
      <span>试卷名称</span>
      <input type="text" name="tname" size="14" value="<%=request.getAttribute("name") %>"/>
      <input  class="btn_cm" type="submit" value="查询" name="B6" /></td>
  </tr>
</table>
</form>
<form name="paperForm" method="post">
<input type="hidden" name="courseId" value="<%=request.getAttribute("courseId") %>">
<input type="hidden" name="tname" value="<%=request.getAttribute("name") %>">
<table width="98%" border="0" cellpadding="0" cellspacing="1" class="tb-cm">
  
  <tr class="cm_tt_bg">
    <td width="5%"><input type="checkbox" name="C6" value="ON" /></td>
    <td width="5%">序号</td>
    <td width="20%">试卷名称</td>
    <td width="15%">试卷来源</td>
    <td width="10%">试卷方式</td>
    <td width="10%">创建日期</td>
    <td width="10%">状态</td>
    <td width="25%">操作</td>
  </tr>
  <%com.zhjedu.util.PageObject po = (com.zhjedu.util.PageObject)request.getAttribute("po");%>   
  <bean:define id="list" name="po" property="datas"/>
  <logic:iterate indexId="ind" id="element" name="list">
  <tr bgcolor="<%if(ind % 2 == 0){%>#F7FBFF<%}else{%>#EEF7FF<%}%>">
    <td><input type="checkbox" name="id" value="<bean:write name="element" property="id"/>" <logic:equal value="0" name="element" property="status">title=""</logic:equal><logic:notEqual value="0" name="element" property="status">title="no"</logic:notEqual>/></td>
    <td align="center"><%=(po.getCurrentPage() - 1) * po.getPageSize() + ind + 1 %></td>
    <td>
    <logic:equal value="0" name="element" property="status">
    <a href="<%=request.getContextPath()%>/Paper.do?method=editPaper&courseId=<%=request.getAttribute("courseId") %>&id=<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></a>
    </logic:equal>
    <logic:notEqual value="0" name="element" property="status">
    <bean:write name="element" property="name"/>
    </logic:notEqual>
    </td>
    <td><bean:write name="element" property="source"/></td>
    <td>固定</td>
    <td><bean:define id="tmp" name="element" property="createdate"/><%=DateTimeUtil.getTime((Long)tmp,2) %></td>
    <td>
    <logic:equal value="0" name="element" property="status">未发布</logic:equal>
    <logic:equal value="1" name="element" property="status">停用</logic:equal>
    <logic:equal value="2" name="element" property="status">已发布</logic:equal>
    </td>
    <td><div align="center">
      <logic:equal value="0" name="element" property="status">
      <input class="btn_cm" type="button" value="试题维护" name="B"/> 
      <input class="btn_cm_small" type="button" value="发布" name="B" onclick="javascript:pubPaper('<bean:write name="element" property="id"/>')"/> 
      <input class="btn_cm_small" type="button" value="停用" name="B" onclick="javascript:stopPaper('<bean:write name="element" property="id"/>')"/> 
      <input class="btn_cm" type="button" value="预览试卷" name="B"/>
      </logic:equal>
      <logic:equal value="2" name="element" property="status">
      <input class="btn_cm" type="button" value="试题维护" name="B" disabled/> 
      <input class="btn_cm_small" type="button" value="发布" name="B" disabled/> 
      <input class="btn_cm_small" type="button" value="停用" name="B" onclick="javascript:stopPaper('<bean:write name="element" property="id"/>')"/> 
      <input class="btn_cm" type="button" value="预览试卷" name="B"/>
      </logic:equal>
      <logic:equal value="1" name="element" property="status">
      <input class="btn_cm" type="button" value="试题维护" name="B" disabled/> 
      <input class="btn_cm_small" type="button" value="发布" name="B" disabled/> 
      <input class="btn_cm_small" type="button" value="停用" name="B" disabled/> 
      <input class="btn_cm" type="button" value="预览试卷" name="B" disabled/>
      </logic:equal>
    </div></td>
  </tr>
  </logic:iterate>
  <tr bgcolor="#F7FBFF">
    <td colspan="11"><div class="page_css">
    <page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="paperForm" pageVar="page" jsName="changePage" url="Paper.do?method=listPaper"/>
    </div></td>
  </tr>
  <tr>
    <td align="center" colspan="11"></td>
  </tr>
</table>
</form>
<script language=javascript>
function editPaper(){
	var j = 0;
	var id = paperForm.id;
	if(id != null){
		if(id.length){
			for(var i = 0;i < id.length; i ++){
				if(id[i].checked == true){
					if(id[i].title == "no"){
						alert("你选择的数据不能进行编辑操作!");
						return;
					}else{
						j = j + 1;
					}
				}
			}
		}else{
			if(id.checked == true){
				if(id.title == "no"){
					alert("你选择的数据不能进行编辑操作!");
					return;
				}else{
					j = j + 1;
				}
			}
		}
	}else{
		alert("没有可操作的数据!");
		return;
	}
	if(j == 0){
		alert("请选择要编辑的试卷！");
		return;
	}else if(j > 1){
		alert("一次只允许编辑一个固定试卷！");
		return;
	}else{
		paperForm.action = "<%=request.getContextPath()%>/Paper.do?method=editPaper&courseId=<%=request.getAttribute("courseId") %>";
		paperForm.submit();
	}
	
}
function removePaper(){
	var j = 0;
	var id = paperForm.id;
	if(id != null){
		if(id.length){
			for(var i = 0;i < id.length; i ++){
				if(id[i].checked == true){
					j = j + 1;
				}
			}
		}else{
			if(id.checked == true){
				j = j + 1;
			}
		}
	}else{
		alert("没有可操作的数据!");
		return;
	}
	
	if(j==0){
		alert("请选择要删除的试卷！");
		return;
	}else{
		if(confirm("确定要删除选择的试卷吗?")){
			paperForm.action = "<%=request.getContextPath()%>/Paper.do?method=removePaper&courseId=<%=request.getAttribute("courseId") %>";
			paperForm.submit();
		}
	}
}
function pubPaper(id){
	if(confirm("该操作将发布该试卷，请确认试卷下有试题并且试题总分为100分才能正确进行发布，确定继续吗？")){
		window.location.href="<%=request.getContextPath()%>/Paper.do?method=pubPaper&courseId=<%=request.getAttribute("courseId") %>&id=" + id;
	}
}
function stopPaper(id){
	if(confirm("该操作将停用该试卷，停用后该试卷不允许进行任何操作，确定继续吗？")){
		window.location.href="<%=request.getContextPath()%>/Paper.do?method=stopPaper&courseId=<%=request.getAttribute("courseId") %>&id=" + id;
	}
}
</script>
