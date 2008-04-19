<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>


<html:form action="/Book/editBook" method="post">
<div align="center">
  <center>
    <br>  
	<table  align="center" width="500"  border="0" cellspacing="0" cellpadding="0">
	<tr> 
      <td align="center" colspan="2"><span class="TableTitleText">图书编辑</span></td>
      </tr>
	<tr>
	<td align="center">
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
		  <tr> 
			<td>&nbsp;<html:hidden name="BookForm" property="actionType"/></td>
		  </tr>
				  <tr class="listcellrow" height="23" >
					     <td width="86" nowrap class="InputLabelCell">代码：</td>
				     <td ><html:text styleClass="input2" property="vo.bookid" size="50" validatetype="notempty" msg="名称不能为空！" maxlength="100"/><font color="red">*</font></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					     <td width="86" nowrap class="InputLabelCell">书名：</td>
				     <td ><html:text styleClass="input2" property="vo.bookname" size="50" validatetype="notempty" msg="名称不能为空！" maxlength="100"/><font color="red">*</font></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					     <td width="86" nowrap class="InputLabelCell">出版社：</td>
				     <td ><html:text styleClass="input2" property="vo.publisher" size="50" validatetype="notempty" msg="名称不能为空！" maxlength="100"/><font color="red">*</font></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					     <td width="86" nowrap class="InputLabelCell">所属部门：</td>
				     <td ><html:text styleClass="input2" property="vo.deptid" size="50" validatetype="notempty" msg="名称不能为空！" maxlength="100"/><font color="red">*</font></td>
			  </tr>
			  <tr>
			<td>&nbsp;</td>
		  </tr>
		  
		</table>
	</td>
	</tr><tr> 
        <td align="center" colspan="2"> 
			          <goa:button property="save" value="保存" styleClass="button"  onclick="return doSave(BookForm)" operation="TSGL.OPT"/>
		          <goa:button property="save" value="保存并返回" styleClass="button"  onclick="return doSaveExit(BookForm)" operation="TSGL.OPT"/>
          <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="TSGL.VIEW"/></td>
      </tr></table>
    </center>
</div>

</html:form>
</body>

