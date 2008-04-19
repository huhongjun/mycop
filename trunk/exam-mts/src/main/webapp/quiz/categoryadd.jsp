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
<script language="javascript">
function validate(){
	if(this.document.CategoryForm.name.value==""){
		alert("目录名称不能为空");
		return false;
	}
	
	return true;
}
</script>
<head>
<title>添加机考目录</title>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr class="title_01">
    <td width="2%" valign="middle" class="title_fonts">&nbsp;</td>
    <td width="98%" height="40" valign="middle" class="title_fonts"><img src="<%=request.getContextPath()%>/image/ico_01.gif" width="36" height="36" align="middle" /> 添加目录</td>
  </tr>
</table>
<html:form method="post" action="/Category.do?method=saveCategory" target="_parent" onsubmit="if(!validate()){return false;}" >
<%
String parent = "";
if(request.getParameter("parent")!=null){
	parent = request.getParameter("parent");
}
 %>
<html:hidden property="parent" name="category" />
<html:hidden property="id" name="category" />
<table width="97%" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" class="table-pub">
  <tr>
    <td align="center" bgcolor="#FFFFFF"><table border="0" width="100%" id="table3" cellpadding="0" style="border-collapse: collapse" cellspacing="0">
      <tr>
        <td height="71" align="left" valign="top" bgcolor="#FFFFFF"><table width="100%" height="60" border="0" cellpadding="0" cellspacing="1" bgcolor="#CCCCCC" id="table12" style="border-collapse: collapse">
            <tr>
              <td align="center" bgcolor="#FFFFFF" class="wenzi"><table width="100%" height="53" border="0" cellpadding="0" id="table12" style="border-collapse: collapse">
                  <tr>
                    <td height="30" align="right" class="wenzi">目录名称</td>
                    <td height="30" align="left" class="wenzi"><html:text property="name" name="category" /></td>
                  </tr>
                  
                  <tr>
                    <td align="right" class="wenzi">目录简介</td>
                    <td align="left" class="wenzi"><html:textarea property="info" name="category" cols="29" rows="3" /></td>
                  </tr>     
              </table></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td bgcolor="#FFFFFF" valign="top" align="center"><hr noshade="noshade" color="#808080" size="1" />
            <input type=submit value="保存" name=Submit>
            <input type=reset value="重置" name=Submit2>
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</html:form>
</body>
</html>