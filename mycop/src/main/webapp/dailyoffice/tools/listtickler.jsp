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
  <html:form action="/dailyoffice/tools/listTickler" method="post">
    <html:hidden property="sqlWhere" />
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="6" align="center" class="TableTitleText">备忘录列表</td>
  </tr>
  <tr>
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr>
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
            <td align="right" class="InputLabelCell">内容:
          </td>
            <td>
                <html:text property="searchValue" maxlength="15" size="22" styleClass="input2">
                </html:text>
            </td>
            <td>
                <goa:button  property="search" styleClass="button" value="查询" onclick="doAction('doSearchByContent')" operation="BWL.VIEW" />
            </td>
          <td> <goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="BWL.OPT"/>
          </td>
		   <td>
			<%String modifylink="toUrl('"+context+"/dailyoffice/tools/editTickler.do?actionType=modify',true)";%>
		   <goa:button styleClass="button"  property="modify" onclick="<%=modifylink%>" value="修改" operation="BWL.OPT"/>
          </td>
		   <td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="BWL.OPT"/>
		    <td> 
			<%String viewlink="toUrl('"+context+"/dailyoffice/tools/viewTickler.do',true)";%>
			<goa:button  styleClass="button" property="delete" value="查看"  onclick="<%=viewlink%>" operation="BWL.VIEW"/>
          </td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr>
  <td><!--放置搜索页面-->
  <table border="0" cellspacing="1" cellpadding="2">
	<tr>

            <td>
            </td>
        </tr>
  </table>
  </td>
  </tr>
  <tr>
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td height="11" width="11%" class="Listtitle" align="center"><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
      		<td height="11" width="18%" class="Listtitle" id=".create_date" onclick="onchangeOrderBy(this)"><div align="left">日期</div></td>
      		<td height="11" width="71%" class="Listtitle" id=".content" onclick="onchangeOrderBy(this)"><div align="left">内容</div></td>
    </tr>

    <logic:iterate id="ps" name="TicklerForm" property="pageControl.data" type="com.gever.goa.dailyoffice.tools.vo.TicklerVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"ondblclick="<%="toUrl('"+context+"/dailyoffice/tools/viewTickler.do?fid=" +ps.getSerial_num()+"',false)"%>">
		<td align="center" class="listcellrow">
			<input type='checkbox' name='fid' value='<goa:write name="ps" property="serial_num" filter="true"/>' >
			</td>
		<td class="listcellrow"><goa:write name="ps" property="create_date" filter="true"/>&nbsp;</td>
        <td class="listcellrow4"><goa:write name="ps" property="content" maxlen="40" filter="true"/>&nbsp;</td>

	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr>
    <td colspan="8" align="center" class="f12">
		<html:hidden name="TicklerForm" property="orderType"/>
	    <html:hidden name="TicklerForm" property="orderFld"/>
		<goa:pagelink name="TicklerForm" property="pageControl" />
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
