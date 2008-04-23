<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>


<html>
<body class="bodybg">
<html:form action="/infoservice/tradenewslist" method="post">

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
        <td align="center" class="TableTitleText">
		<logic:equal name="IsInfoServeForm3" property="nodeID" value="">行业新闻</logic:equal>
		<goa:write name="IsInfoServeForm3" property="nodeID" filter="true" /></td>
    </tr>
    <tr> 
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="right">
	        <table>
                <tr>	 
                    <td class="InputLabelCell"> 
					主题名称：
					</td>
					<td>
					<html:text styleClass="input2" size="10" name="IsInfoServeForm3" property="paraSimpleQuery"/>
					</td>
					<td>
					<%String clk1="toSimpleQuery('"+context+"/infoservice/tradenewslist.do?paraFlag=5')";%>
					<goa:button styleClass="button"  property="simplequery" onclick="<%=clk1%>" value="查询" operation="HYXW.VIEW"/>
					<logic:notEqual  name="IsInfoServeForm3" property="nodeID" value="">
					<goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="HYXW.OPT"/>
					</logic:notEqual>
					<%String clk2="toUrl('"+context+"/infoservice/tradenewsedit.do?actionType=modify',true)";%>
                    <goa:button styleClass="button"  property="copy" onclick="<%=clk2%>" value="修改" operation="HYXW.OPT" />
					<goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="HYXW.OPT"/>
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
		<td class="Listtitle" id=".title" onclick="onchangeOrderBy(this)"> <div align="left">主题</div></td>
		<td width="15%" class="Listtitle" id=".is_dotype.info_type" onclick="onchangeOrderBy(this)"> <div align="left">类别</div></td>
		<td width="15%" class="Listtitle" id=".user_code" onclick="onchangeOrderBy(this)"> <div align="left">发布人</div></td>
		<td width="15%" class="Listtitle" id=".create_date" onclick="onchangeOrderBy(this)"> <div align="left">发布时间</div></td>
		<td width="10%" class="Listtitle" id=".show_flag" onclick="onchangeOrderBy(this)"> <div align="left">是否显示</div></td>
	</tr>
	<logic:iterate id="ps" name="IsInfoServeForm3" property="pageControl.data" type="com.gever.goa.infoservice.vo.IsInfoServeVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"  ondblclick="javascript:toUrl('<%=context%>/infoservice/tradenewsview.do?fid=<goa:write maxlen="0" name="ps" property="serial_num" filter="true"/>')">
		<td align="left" class="listcellrow">
		<input type='checkbox' name='fid' value='<goa:write maxlen="0" name="ps" property="serial_num" filter="true"/>' >    		  
		</td>
		<td class="listcellrow">
		<goa:write maxlen="35" name="ps" property="title" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<goa:write maxlen="12" name="ps" property="info_type" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<goa:write maxlen="12" name="ps" property="user_name" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<goa:write maxlen="12" name="ps" property="create_date" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow4">
		<logic:equal name="ps" property="show_flag" value="1">
		是
		</logic:equal>
		<logic:equal name="ps" property="show_flag" value="0">
		否
		</logic:equal>
		</td>
	</tr>
	</logic:iterate>
</table>

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
		<td align="center">
		<html:hidden name="IsInfoServeForm3" property="orderType"/>
	    <html:hidden name="IsInfoServeForm3" property="orderFld"/>
		<goa:pagelink name="IsInfoServeForm3" property="pageControl" /> 
		</td>
	</tr>
</table>
</html:form>
</body>
</html>