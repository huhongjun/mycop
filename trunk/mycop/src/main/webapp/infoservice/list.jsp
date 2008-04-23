<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>


<html>
<body class="bodybg">
<html:form action="/infoservice/list" method="post">

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
        <td align="center" class="TableTitleText">信息类型</td>
    </tr>
    <tr> 
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="right">
	        <table>
                <tr>		 
                    <td class="f12"> 
					类别名称：
					</td>
					<td >
					<html:text  styleClass="input2" size="10" name="SysInfoTypeForm" property="paraSimpleQuery"/>
					</td>
					<td>
<% 
String temp1="toSimpleQuery('"+context+"/infoservice/list.do?paraFlag=0')";
%>
<% 
String temp2="toUrl('"+context+"/infoservice/dotype.do?actionType=modify',true)";
%>
					<html:button styleClass="button"  property="simplequery" onclick="<%=temp1%>" value="查询"/>
					<html:button styleClass="button"  property="add" onclick="doAdd()" value="新增"/>
                    <html:button styleClass="button"  property="copy" onclick="<%=temp2%>" value="修改"/>
					<html:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()"/>
					</td>
				</tr>
			</table>
	    </td>
    </tr>
</table>

<table width="95%" border="0" cellpadding="2" cellspacing="0"  align="center">
	<tr height="20">
		<td width="3%" align="left" class="Listtitle">
		<input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)">
		</td>
		<td width="40%" class="Listtitle" id=".type_name" onclick="onchangeOrderBy(this)"> <div align="left">类别名称</div></td>
		<td width="40%" class="Listtitle" id=".remark" onclick="onchangeOrderBy(this)"> <div align="left">备注</div></td>  
	</tr>
	<logic:iterate id="ps" name="SysInfoTypeForm" property="pageControl.data" type="com.gever.goa.infoservice.vo.SysInfoTypeVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"  ondblclick="javascript:toUrl('<%=context%>/infoservice/dotypeview.do?fid=<goa:write maxlen="0" name="ps" property="type_code" filter="true"/>')">
		<td align="left" class="listcellrow">
		<input type='checkbox' name='fid' value='<goa:write maxlen="0" name="ps" property="type_code" filter="true"/>' >    		  
		</td>
		<td class="listcellrow">
		<goa:write maxlen="32" name="ps" property="type_name" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow4">
		<goa:write maxlen="32" name="ps" property="remark" filter="true"/>&nbsp;
		</td>
	</tr>
	</logic:iterate>
</table>

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
		<td align="center">
		<html:hidden name="SysInfoTypeForm" property="orderType"/>
	    <html:hidden name="SysInfoTypeForm" property="orderFld"/>
		<goa:pagelink name="SysInfoTypeForm" property="pageControl" /> 
		</td>
	</tr>
</table>
</html:form>
</body>
</html>