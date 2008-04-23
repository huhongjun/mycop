<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>

<html>
<body class="bodybg">
  <html:form action="/dailyoffice/bbs/mng/bbsuser" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">论坛人员列表</td>
  </tr>
  <tr> 
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		 
          <td> <html:button styleClass="button"  property="add" onclick="doAdd()" value="新增"/>
          </td>
		   <td>
		
		   <html:button styleClass="button"  property="copy" onclick="toUrl('/goa/dailyoffice/bbs/mng/editbbsuser.do?actionType=modify',true)" value="修改"/>
          </td>
		   <td> <html:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()"/>
		   </td>
		    <td> <html:button  styleClass="button" property="delete" value="查看"  onclick="toUrl('/goa//dailyoffice/bbs/mng/viewbbsuser.do',true)"/>
			</td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td width="32" class="Listtitle" ><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
      		<td width="10%" class="Listtitle" id=".NICKNAME" onclick="onchangeOrderBy(this)"> <div align="center">NICKNAME</div></td>
      		<td width="15%" class="Listtitle" id=".SEX_CODE"  onclick="onchangeOrderBy(this)"> <div align="center">SEX_CODE</div></td>
            <td width="10%" class="Listtitle" id=".USER_CODE"  onclick="onchangeOrderBy(this)"> <div align="center">USER_CODE</div></td>
            <td width="10%" class="Listtitle" id=".E_MAIL"  onclick="onchangeOrderBy(this)"> <div align="center">E_MAIL</div></td>
			<td width="10%" class="Listtitle" id=".USER_STATE"  onclick="onchangeOrderBy(this)"> <div align="center">USER_STATE</div></td>
			<td  class="Listtitle" id=".LAST_LOG_TIME"  onclick="onchangeOrderBy(this)"> <div align="center">LAST_LOG_TIME</td>
           		  
    </tr>
    <logic:iterate id="ps" name="UserForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.UserVO" indexId="index">
	<tr bgcolor="#FFFFFF" onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('/goa/dailyoffice/bbs/mng/viewbbsuser.do?fid=" +ps.getBbs_user_code()+ "',false)"%>">
		<td align="center" class="listcellrow">
			<input type='checkbox' name='fid' value='<goa:write name="ps" property="BBS_USER_CODE" filter="true"/>'>   		  
			</td>
		<td class="listcellrow"><goa:write name="ps" property="NICKNAME" filter="true"/>&nbsp;</td>
		<td class="listcellrow"><goa:write name="ps" property="SEX_CODE" filter="true"/></td>
<td class="listcellrow"><goa:write name="ps" property="USER_CODE" filter="true"/>&nbsp;</td>
<td class="listcellrow"><goa:write name="ps" property="E_MAIL" filter="true"/>&nbsp;</td>
<td class="listcellrow"><goa:write name="ps" property="USER_STATE" filter="true"/>&nbsp;</td>
<td class="listcellrow4"><goa:write name="ps" property="LAST_LOG_TIME" filter="true"/>&nbsp;</td>

	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
<html:hidden name="UserForm" property="orderType"/>
<html:hidden name="UserForm" property="orderFld"/>
		<goa:pagelink name="UserForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>

</body>
</html>
