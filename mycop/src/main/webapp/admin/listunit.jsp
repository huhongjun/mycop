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
<html:form action="/admin/listunit" method="post">
<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr> 
	<td colspan="6">&nbsp;</td>
	</tr>
	<tr> 
	<td colspan="6" align="center" class="TableTitleText">单位信息</td>
	</tr>
	<tr> 
	<td colspan="6" align="right">&nbsp;</td>
	</tr>
	<tr> 
    <td class="f12" colspan="6" align="right">
	<table border="0" cellspacing="1" cellpadding="2">
	<tr>
	<td> 
<logic:equal name="UnitForm" property="datasize" value="0">
	<goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="DWXX.OPT"/>
</logic:equal>
	</td>
	<td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()"operation="DWXX.OPT"/>
	<td>
	<goa:button styleClass="button"  property="copy" onclick="toUrl('../admin/editunit.do?actionType=modify',true)" value="修改"operation="DWXX.OPT"/>
	</td>
  	<td> <goa:button  styleClass="button" property="delete" value="查看"  onclick="toUrl('../admin/viewunit.do',true)" operation="DWXX.VIEW"/></td>
	</tr>
	</table>
	</td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td align="center" width="67" height="11" class="Listtitle"><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
			<td width="20%" class="Listtitle">单位简称</td>
      			<td width="30%" class="Listtitle">单位名称</td>
			<td width="20%" class="Listtitle">单位电话</td>
			<td width="30%" class="Listtitle">网站</td>
			
    </tr>
    <logic:iterate id="ps" name="UnitForm" property="pageControl.data" type="com.gever.goa.admin.vo.UnitVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/admin/viewunit.do?fid="+ps.getUnit_code()+"',false)"%>">
	<td align="center" class="listcellrow">                                                                              
	<input type='checkbox' name='fid' value='<bean:write name="ps" property="unit_code" filter="true"/>' >    		  
	</td>
	<td class="listcellrow"><bean:write name="ps" property="unit_code" filter="true"/>&nbsp;</td>
	<td nowrap class="listcellrow"><bean:write name="ps" property="unit_name" filter="true"/>&nbsp;</td>	
	<td class="listcellrow"><bean:write name="ps" property="phone" filter="true"/>&nbsp;</td>
	
	  <td class="listcellrow">
	  
	  <a href="<bean:write  name="ps" property="home_page" filter="true"/>" target="window_name"><bean:write  name="ps" property="home_page" filter="true" /></a>&nbsp;</td>
	</tr>
	</logic:iterate>
</table>
</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="UnitForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
