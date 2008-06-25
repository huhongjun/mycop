<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<script src="../js/edit.js" type="text/javascript"></script>
<script language=javascript src="../js/question.js"></script>
<link href="<%=request.getContextPath()%>/css/ksmain.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=request.getContextPath()%>/js/meizzDate.js"></script>
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
.style11 {color: #164f8e;
	font-weight: bold;
}
.style11 {color: #164f8e; font-weight: bold; font-size: 14px; }
.style12 {color: #0000FF}
.style13 {color: #3b6ba0}
.style14 {	font-size: 36px;
	color: #3B6BA0;
}
.style8 {color: #0000FF; font-size: 12px; }
.style9 {font-size: 14px}
-->
</style>
<script language=javascript>
function addCategory(){
	document.CategoryForm.method.value="addCategory";
	document.CategoryForm.submit();
}
</script>
<%
String info = "";
if(request.getAttribute("info")!=null){
	info = (String)request.getAttribute("info");
}
			StringBuffer js = new StringBuffer();
			js.append("<script language=\"javascript\" type=\"text/javascript\">\n");
			//js.append("function setData() {\n");
			
			if(info!=null&&!info.equals("")){
				
				js.append("alert('" + info +"');\n");
				
			}
			
			//js.append("}\n");
			js.append("</script>\n");
			out.println(js);
 %>
<html:form action="/Category.do" method="post" >
<input type=hidden name="method" value="listCategory"/>
<%
String parent = "";
if(request.getParameter("parent")!=null){
	parent = request.getParameter("parent");
}
if(request.getParameter("category")!=null){
	parent = request.getParameter("category");
}
if(request.getAttribute("parent")!=null){
	parent = (String)request.getAttribute("parent");
}
 %>
<input type=hidden name="parent" value="<%=parent%>" />
	 <table border="0" width="100%" id="table4">
                    <tr>
                      <td width="100%" height="30">
                      <p class="style1"> 您现在的位置是：考试中心  / 目录管理 /  <font color="#ff0000"><%if(!"".equals(parent)){%>[<%=request.getAttribute("catName") %>]<%}else{%>[根目录]<%}%>下目录列表</font></p></td>
                    </tr>
                    <tr >
                      <td height="35" align="left"><div align="left"><span class="style3">
                        <input class="btn_cm" name="B40" type="button" onClick="addCategory();" value="添加目录">
                      </span></div></td>
                    </tr>
                    <tr>
                      <td align="center">
                      <table width="100%" border="0" id="table4">
                        <tr bgcolor="#7FBEE6">
                          <td width="60%" height="21" ><span class="style1"> 类别名称</span></td>
                          <td width="40%" height="21" ><span class="style1"> 操作</span></td>
                        </tr>
                        <%com.zhjedu.util.PageObject po=(com.zhjedu.util.PageObject)request.getAttribute("po");%>   
  			  <bean:define id="list" name="po" property="datas"/>
			  <logic:iterate indexId="ind" id="element" name="list"> 
                        <tr bgcolor="#EEF7FF" align="center">
                          <td align="left" height="21"><span class="style1"><a href="<%=request.getContextPath()%>/Quiz.do?method=listCategory&category=<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></a></span></td>
                          <td height="21" align="left" class="style1">
				 <a href="<%=request.getContextPath()%>/Quiz.do?method=editCategory&categoryid=<bean:write name="element" property="id"/>&parent=<bean:write name="element" property="parent"/>">修改</a> 
  	    		 <a href=javascript:if(confirm("确定删除该目录吗？")){window.location.href="<%=request.getContextPath()%>/Quiz.do?method=removeCategory&category=<bean:write name="element" property="id"/>&parent=<bean:write name="element" property="parent"/>";} target="_parent">删除</a>
                          </td>
                        </tr>
                        </logic:iterate>
                      </table>
                      </td>
                    </tr>
                  </table>
                  <table border="0" width="100%" id="table4">
  <tr>
    <td align="center" colspan="8"> 　</td>
  </tr>
  <tr>
		<td align="center" colspan="8"><span class="style1">
		<page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="CategoryForm" pageVar="page" jsName="changePage" url="Category.do?method=listCategory"/>
		</span></td>
  </tr>
</table>
				</html:form>
