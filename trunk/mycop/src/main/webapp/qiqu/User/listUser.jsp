<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%  String context =request.getContextPath();  %>

<html>
<body class="bodybg">
  <html:form action="/User/listUser" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">??�б�</td>
  </tr>
  <tr> 
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		 
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="����"  operation="TSGL.OPT"/>
          </td>
		   <td>
		
		   <goa:button styleClass="button"  property="copy" onclick="toUrl('../qiqu/User/editUser.do?actionType=modify',true)" value="�޸�" operation="TSGL.OPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doDelete()" operation="TSGL.OPT"/>
		    <td> <goa:button  styleClass="button" property="delete" value="�鿴"  onclick="toUrl('../qiqu/User/viewUser.do',true)" operation="TSGL.VIEW"/>
		  <td><goa:button styleClass="button"  property="copy"  onclick="doCopy()"value="����" operation="TSGL.OPT"/>
          </td>
         
          </td>
		
          </td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%"border="0" cellspacing="0" cellpadding="2">
        <tr height="20">
      <td width="32" class="Listtitle" align="center" ><input alt="ѡ��" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
      		<td width="15%" class="Listtitle" id=".id"  onclick="onchangeOrderBy(this)"> <div align="v">??</div></td>
      		<td width="15%" class="Listtitle" id=".name"  onclick="onchangeOrderBy(this)"> <div align="v">??</div></td>
      		<td width="15%" class="Listtitle" id=".email"  onclick="onchangeOrderBy(this)"> <div align="v">??</div></td>
    </tr>
<logic:iterate id="ps" name="UserForm" property="pageControl.data" type="com.gever.goa.user.vo.UserVO" indexId="index">
	   <tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('../qiqu/User/viewUser.do?fid=" +ps.getValue("id")+ "',false)"%>">
		      <td align="center" class="listcellrow">
			         <input type='checkbox' name='fid' value='<goa:write name="ps" property="id" filter="true"/>'>   		  
			      </td>
		
		      <td class="listcellrow"><goa:write name="ps" property="id" filter="true"/></td>
      <td class="listcellrow"><goa:write name="ps" property="name" filter="true"/></td>
      <td class="listcellrow"><goa:write name="ps" property="email" filter="true"/></td>
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
