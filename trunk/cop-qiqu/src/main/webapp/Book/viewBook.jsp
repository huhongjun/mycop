<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<html:form action="/Book/viewBook" method="post">
<div align="center">
  <center>
   <br>
	<table  align="center" width="600"  border="0" cellspacing="0" cellpadding="0">
	<tr> 
      <td align="center" colspan="2"><span class="TableTitleText">图书 编辑</span></td>
      </tr>
	<tr>
	<td align="center">
		<table width="98%" align="center" border="0" cellspacing="0" cellpadding="0">
				
							  <tr class="listcellrow" height="23" >
					    <td width="86" nowrap class="InputLabelCell">代码：</td>
					    <td class="f12"><goa:write  name="BookForm" property="vo.bookid" filter="true"/></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					    <td width="86" nowrap class="InputLabelCell">书名：</td>
					    <td class="f12"><goa:write  name="BookForm" property="vo.bookname" filter="true"/></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					    <td width="86" nowrap class="InputLabelCell">出版社：</td>
					    <td class="f12"><goa:write  name="BookForm" property="vo.publisher" filter="true"/></td>
			  </tr>
						  <tr class="listcellrow" height="23" >
					    <td width="86" nowrap class="InputLabelCell">所属部门：</td>
					    <td class="f12"><goa:write  name="BookForm" property="vo.deptid" filter="true"/></td>
			  </tr>
			</table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		        <html:hidden name="BookForm" property="fid" />
		        <goa:button property="save" value="修改" styleClass="button"  onclick=" toUrl('../Book/editBook.do?actionType=modify',false)" operation="TSGL.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="TSGL.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

