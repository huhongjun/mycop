<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/Pager.tld" prefix="page" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<% 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Pragrma","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<head>
<title>目录管理</title>
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
</head>
<script language=javascript>
function addCategory(){
	document.CategoryForm.method.value="addCategory";
	document.CategoryForm.submit();
}
</script>
<body>
<%
String info = "";
if(request.getAttribute("info")!=null){
	info = (String)request.getAttribute("info");
}
			StringBuffer js = new StringBuffer();
			js.append("<script language=\"javascript\" type=\"text/javascript\">\n");
			js.append("function setData() {\n");
			
			if(info!=null&&!info.equals("")){
				
				js.append("alert('" + info +"');\n");
				
			}
			
			js.append("}\n");
			js.append("</script>\n");
			out.println(js);
 %>
<script language="javascript">
	
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="title_01">
    <td width="2%" valign="middle" class="title_fonts">&nbsp;</td>
    <td width="98%" height="40" valign="middle" class="title_fonts"><img src="<%=request.getContextPath()%>/image/ico_01.gif" width="36" height="36" align="middle" /> 目录设置</td>
  </tr>
</table>
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
<table width="97%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" class="table-pub">
  <tr>
    <td align="center" bgcolor="#FFFFFF"><table border="0" width="100%" id="table3" cellpadding="0" style="border-collapse: collapse" cellspacing="0">
      <tr>
        <td height="71" align="left" valign="top" bgcolor="#FFFFFF"><table width="100%" height="60" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" id="table12" style="border-collapse: collapse">
            <tr>
              <td align="center" bgcolor="#FFFFFF" class="wenzi"><table width="100%" height="53" border="0" cellpadding="0" id="table12" style="border-collapse: collapse">
                 
                  <tr>
                    <td height="30" colspan="5" align="left" class="wenzi">&nbsp;&nbsp;&nbsp;
                      <input class="long90-button-bg" type="button" name="Submit22" value="添加目录" onclick="addCategory();"/>
                      <input class="long90-button-bg" type="button" name="Submit22" value="返回" onclick="history.back(-1)"/>
                    </td>
                  </tr>
              </table></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF" align="left" valign="top"><hr noshade="noshade" color="#808080" size="1" />
            <table border="1" width="100%" id="table11" cellspacing="1" bordercolorlight="#DDDDFF" bordercolordark="#FFFFFF">
              <tr class="table_title_01">
                <td width="121" align="center" class="wenzi">类别名称</td>
                <td width="79" align="center" class="wenzi">操作</td>
              </tr>
              <%com.zhjedu.util.PageObject po=(com.zhjedu.util.PageObject)request.getAttribute("po");%>   
  			  <bean:define id="list" name="po" property="datas"/>
			  <logic:iterate indexId="ind" id="element" name="list"> 
              <tr>             
                <td valign="top" class="wenzi"><font class="style1"><a href="<%=request.getContextPath()%>/Quiz.do?method=listCategory&category=<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></a></font></td>
                <td valign="top" class="wenzi"><font class="style1">
                 <a href="<%=request.getContextPath()%>/Quiz.do?method=editCategory&categoryid=<bean:write name="element" property="id"/>&parent=<bean:write name="element" property="parent"/>">修改</a> 
  	    		 <a href=javascript:if(confirm("确定删除该目录吗？")){window.location.href="<%=request.getContextPath()%>/Quiz.do?method=removeCategory&category=<bean:write name="element" property="id"/>&parent=<bean:write name="element" property="parent"/>";} target="_parent">删除</a>
                </font></td>                
              </tr>
              </logic:iterate>
          </table></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF" align="left" valign="top"><table border="0" width="100%" id="table12" cellpadding="0" style="border-collapse: collapse">
            <tr>
              <td><p align="right" class="wenzi"><font class="style1"><page:pager totalrecord="<%=po.getTotalRecord()%>" intPageSize="<%=po.getPageSize()%>" pageCount="<%=po.getTotalPage()%>" page="<%=po.getCurrentPage()%>" formName="CategoryForm" pageVar="page" jsName="changePage" url="Category.do?method=listCategory"/></font></p></td>
            </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</html:form>
</body>
<script type="text/javascript" language="javascript">
	
	setData();
</script>
</html>