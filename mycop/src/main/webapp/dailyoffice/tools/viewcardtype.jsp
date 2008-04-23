<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<body class="bodybg">
<html:form action="/dailyoffice/tools/viewCardcaseType" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">名片类别</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="CardcaseTypeForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr> 
				  <td>
				  <table width="500" height="36" border="0" cellpadding="5" cellspacing="0">
                  <tr class="listcellrow">
					    <td width="15%" height="23" nowrap class="InputLabelCell">类别名称：</td>
					    <td width="35%" height="23" class="f12"><bean:write  name="CardcaseTypeForm" property="vo.type_name" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="15%" height="23" nowrap class="InputLabelCell">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					    <td width="35%" height="23" class="f12"><bean:write  name="CardcaseTypeForm" property="vo.remark" filter="true"/></td>
					</tr>
					</table>
		  			</td>
				</tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		<html:hidden name="CardcaseTypeForm" property="fid" />
		<%String modifylink="toUrl('"+context+"/dailyoffice/tools/editCardcaseType.do?actionType=modify',false)";%>
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=modifylink%>" operation="MBJ.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="MBJ.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

