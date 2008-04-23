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
<html:form action="/admin/listknow" method="post">
<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr> 
	<td colspan="6">&nbsp;</td>
	</tr>
	<tr> 
	<td colspan="6" align="center" class="TableTitleText">学历信息</td>
	</tr>
	<tr> 
	<td colspan="6" align="right">&nbsp;</td>
	</tr>
	<tr> 
    <td class="f12" colspan="6" align="right">
	<table border="0" cellspacing="1" cellpadding="2">
	<tr>
	<td>
	<goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="XLWF.OPT"/>
	</td>
	<td> <goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="XLWF.OPT"/>
	<td>
	<goa:button styleClass="button"  property="copy" onclick="toUrl('../admin/editknow.do?actionType=modify',true)" value="修改" operation="XLWF.OPT"/>
	</td>
  	<td> <goa:button  styleClass="button" property="delete" value="查看"  onclick="toUrl('../admin/viewknow.do',true)" operation="XLWF.VIEW"/></td>
	</tr>
	</table>
	</td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td align="center" width="30" height="11" class="Listtitle"><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
			<td width="25%" id=".knowledge_code" onclick="onchangeOrderBy(this)" class="Listtitle"><div>学历代码</div></td>
      			<td width="25%" id=".knowledge_name" onclick="onchangeOrderBy(this)" class="Listtitle"><div>学历名称</div></td>
			<td width="" class="Listtitle">备注</td>
    </tr>
    <logic:iterate id="ps" name="KnowledgeForm" property="pageControl.data" type="com.gever.goa.admin.vo.KnowVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('"+context+"/admin/viewknow.do?fid=" +ps.getKnowledge_code()+"',false)"%>">
	<td align="center" class="listcellrow">
	<input type='checkbox' name='fid' value='<bean:write name="ps" property="knowledge_code" filter="true"/>' >    		  
	</td>
	<td class="listcellrow"><bean:write name="ps" property="knowledge_code" filter="true"/>&nbsp;</td>
	<td class="listcellrow"><bean:write name="ps" property="knowledge_name" filter="true"/>&nbsp;</td>	
	<td class="listcellrow4"><bean:write name="ps" property="remarks" filter="true"/>&nbsp;</td>
	</tr>
	</logic:iterate>
</table>
</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="KnowledgeForm" property="pageControl" /> 
		<html:hidden name="KnowledgeForm" property="orderType"/>
	    	<html:hidden name="KnowledgeForm" property="orderFld"/>  
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
