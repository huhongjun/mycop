<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<html>
<body class="bodybg">
<html:form action="/admin/listduty" method="post">
<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr> 
	<td colspan="6">&nbsp;</td>
	</tr>
	<tr> 
	<td colspan="6" align="center" class="TableTitleText">ְ����Ϣ</td>
	</tr>
	<tr> 
	<td colspan="6" align="right">&nbsp;</td>
	</tr>
	<tr> 
    <td class="f12" colspan="6" align="right">
	<table border="0" cellspacing="1" cellpadding="2">
	<tr>
	<td>
	<goa:button styleClass="button"  property="add" onclick="doAdd()" value="����" operation="ZWWF.OPT"/>
	</td>
	<td> <goa:button  styleClass="button" property="delete" value="ɾ��"  onclick="doDelete()"operation="ZWWF.OPT"/>
	<td>
	<goa:button styleClass="button"  property="copy" onclick="toUrl('../admin/editduty.do?actionType=modify',true)" value="�޸�"operation="ZWWF.OPT"/>
	</td>
  	<td> <goa:button  styleClass="button" property="delete" value="�鿴"  onclick="toUrl('../admin/viewduty.do',true)"operation="ZWWF.VIEW"/></td>
	</tr>
	</table>
	</td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%" border="0" bordercolor="red" cellpadding="2" cellspacing="0" align="center">
        <tr height="20">
           <td width="30" align="center" height="11" class="Listtitle"><input alt="" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
			<td width="25%" id=".duty_code" onclick="onchangeOrderBy(this)"class="Listtitle"><div>ְ�����</div></td>
      			<td width="25%" id=".duty_name" onclick="onchangeOrderBy(this)"class="Listtitle"><div>ְ������</div></td>
			<td width="" class="Listtitle">��ע</td>
    </tr>
    <logic:iterate id="ps" name="DutyForm" property="pageControl.data" type="com.gever.goa.admin.vo.DutyVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/admin/viewduty.do?fid=" +ps.getDuty_code()+"',false)"%>">
	<td align="center"  class="listcellrow">
	<input type='checkbox' name='fid' value='<bean:write name="ps" property="duty_code" filter="true"/>' >    		  
	</td>
	<td class="listcellrow"><bean:write name="ps" property="duty_code" filter="true"/>&nbsp;</td>
	<td class="listcellrow"><bean:write name="ps" property="duty_name" filter="true"/>&nbsp;</td>	
	<td class="listcellrow4"><bean:write name="ps" property="remarks" filter="true"/>&nbsp;</td>
	</tr>
	</logic:iterate>
</table>
</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="DutyForm" property="pageControl" /> 
		<html:hidden name="DutyForm" property="orderType"/>
	    	<html:hidden name="DutyForm" property="orderFld"/> 
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
