<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context = request.getContextPath();%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function setType(){
	//alert('/goa/dailyoffice/tools/listCardcaseType.do');
	window.location="<%=context%>/dailyoffice/tools/listCardcaseType.do";
}
//-->
</SCRIPT>
<body class="bodybg">
  <html:form action="/dailyoffice/tools/listCardcase" method="post">
  <html:hidden property="sqlWhere" />
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText">
	<bean:write name="CardcaseForm" property="nodename"/>
	</td>
  </tr>
  <tr>
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
            <td align="right" class="InputLabelCell">姓名:
            </td>
            <td >
                <html:text property="searchValue" maxlength="30" size="22" styleClass="input2">
                </html:text>
            </td>
            <td>
                <goa:button  property="search" styleClass="button" value="查询" onclick="doAction('doSearchByName')" operation="MBJ.VIEW">
            	</goa:button>
            </td>
	<!--判断是否属于公共目录-->
	<logic:equal value="-1" name="CardcaseForm" property="cardType">
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="MBJ.PUBOPT"/>
          </td>
		   <td>
		   <%String modifylink="toUrl('"+context+"/dailyoffice/tools/editCardcase.do?actionType=modify',true)";%>
		   <goa:button styleClass="button"  property="modify" onclick="<%=modifylink%>" value="修改" operation="MBJ.PUBOPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="MBJ.PUBOPT"/>
		   </td>	
	</logic:equal>
	<logic:notEqual value="-1" name="CardcaseForm" property="cardType">        
		  <td>
		   <goa:button styleClass="button"  property="set" onclick="setType()" value="设置" operation="MBJ.VIEW"/>
          </td>
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="MBJ.OPT"/>
          </td>
		   <td>
		   <%String modilink="toUrl('"+context+"/dailyoffice/tools/editCardcase.do?actionType=modify',true)";%>
		   <goa:button styleClass="button"  property="modify" onclick="<%=modilink%>" value="修改" operation="MBJ.OPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="MBJ.OPT"/>
		   </td>
	</logic:notEqual>

		    <td> 
			<%String viewlink="toUrl('"+context+"/dailyoffice/tools/viewCardcase.do',true)";%>
			<goa:button  styleClass="button" property="view" value="查看"  onclick="<%=viewlink%>" operation="MBJ.VIEW"/>
			</td>
          </tr>
      </table>
	  </td>
  </tr>
  <tr>
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td height="11" width="5%" class="Listtitle"><div align="left"><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></div></td>
			<td height="11" width="15%" class="Listtitle"id=".customer" onclick="onchangeOrderBy(this)"><div>姓名</div></td>
			<td height="11" width="15%" class="Listtitle"id=".phone" onclick="onchangeOrderBy(this)"><div>电话</div></td>
      		<td height="11" width="15%" class="Listtitle"id=".mobile" onclick="onchangeOrderBy(this)"><div>手机</div></td>
			<td width="28%" class="Listtitle"id=".company" onclick="onchangeOrderBy(this)"><div>公司名称</div></td>
			<td width="27%" class="Listtitle"id=".e_mail" onclick="onchangeOrderBy(this)"><div>E_MAIL</div></td>
    </tr>

    <logic:iterate id="ps" name="CardcaseForm" property="pageControl.data" type="com.gever.goa.dailyoffice.tools.vo.CardcaseVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"ondblclick="<%="toUrl('"+context+"/dailyoffice/tools/viewCardcase.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="left" class="listcellrow">
			<input type='checkbox' name='fid' value='<bean:write name="ps" property="serial_num" filter="true"/>' >
			</td>
<td class="listcellrow"><bean:write name="ps" property="customer" filter="true"/>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="phone" filter="true"/>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="mobile" filter="true"/>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="company" filter="true"/>&nbsp;</td>
<td class="listcellrow4"><a href=mailto:<bean:write name="ps" property="e_mail" filter="true"/>><bean:write  name="ps" property="e_mail" filter="true"/></a>&nbsp;</td>

	</tr>
	</logic:iterate>
</table>
</td>
  </tr>
  <tr>
    <td colspan="8" align="center" class="f12">
      <html:hidden name="CardcaseForm" property="orderType"/>
      <html:hidden name="CardcaseForm" property="orderFld"/>
		<goa:pagelink name="CardcaseForm" property="pageControl" />
		</td>
	  </tr>
	</table>
</html:form>
</body>

