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
  <table width="85%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="7">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="7" align="center" class="TableTitleText">论坛人员</td>
  </tr>
  <tr> 
    <td colspan="7" align="right">&nbsp;</td>
  </tr>
  <tr><td colspan="7" align="right">
  <table  border="0" cellspacing="1" cellpadding="2" >
  <tr>
    <td class="f12">姓名:
            </td>
            <td>
                <html:text property="searchValue" maxlength="30" size="22" styleClass="input2">
                </html:text>
            </td>
            <td >
                <goa:button  property="search" styleClass="button" value="查询" onclick="toUrl('bbsuser.do',false)" operation="LYWF.VIEW"/>
            </td>
    <td>
     <% String temp="toUrl('"+context+"/dailyoffice/bbs/mng/editbbsuser.do?actionType=modify',true)";%>
    <goa:button styleClass="button"  property="copy" onclick="<%=temp%>" value="修改" operation="LYWF.OPT"/>
</td>
    <td><goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="LYWF.OPT"/></td>
    <% String temp2="toUrl('"+context+"/dailyoffice/bbs/mng/viewbbsuser.do?actionType=modify',true)";%>
    <td><goa:button  styleClass="button" property="delete" value="查看"  onclick="<%=temp2%>" operation="LYWF.VIEW"/></td>
  </tr>
  </table>
  </td></tr>
  <tr> 
    <td colspan="7">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td width="5%" class="Listtitle"align="center"  ><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
   		  <td width="35%" class="Listtitle" id=".NICKNAME" onclick="onchangeOrderBy(this)"> <div align="left">昵称</div></td>
      		<td width="20%" class="Listtitle" id=".SEX_CODE"  onclick="onchangeOrderBy(this)"> <div align="left">性别</div></td>
            <td width="40%" class="Listtitle" id=".USER_STATE"  onclick="onchangeOrderBy(this)"> <div align="left">用户状态</div></td>           		  
    </tr>
    <logic:iterate id="ps" name="BBSUserForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.UserVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/dailyoffice/bbs/mng/viewbbsuser.do?fid=" +ps.getBbs_user_code()+ "',false)"%>">
		<td align="center" class="listcellrow">
			<input type='checkbox' name='fid' value='<goa:write name="ps" property="bbs_user_code" filter="true"/>'>   		  
			</td>
		<td class="listcellrow" align="center"><div align="left"><goa:write name="ps" property="nickname" filter="true"/>&nbsp;</div></td>
		<td class="listcellrow" align="center"><div align="left"><logic:equal value="1" name="ps" property="sex_code"> 男</logic:equal>
              <logic:equal value="0" name="ps" property="sex_code"> 女</logic:equal>
              <logic:greaterThan value="1" name='ps' property='sex_code'>保密</logic:greaterThan>&nbsp;</div></td>
<td class="listcellrow4" align="center">
  <div align="left"><logic:equal value="1" name="ps" property="user_state"> 禁止发言</logic:equal>
      <logic:equal value="0" name="ps" property="user_state"> 允许发言</logic:equal>&nbsp;
  </div></td>
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
<html:hidden name="BBSUserForm" property="orderType"/>
<html:hidden name="BBSUserForm" property="orderFld"/>
		<goa:pagelink name="BBSUserForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>

</body>
</html>
