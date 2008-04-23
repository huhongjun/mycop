<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<html>
<body class="bodybg">
<html:form action="/infoservice/grouplist" method="post">

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
        <td align="center" class="TableTitleText">群组设置</td>
    </tr>
    <tr> 
        <td >&nbsp;</td>
    </tr>
    <tr> 
        <td align="right">
          <div align="left">          </div>
          <div align="right"></div>
        <div align="right"></div>        <table>
          <tr>
            <td class="InputLabelCell"> 群组名称：
                <html:text size="10" styleClass="input2" name="IsAddressListForm" property="paraSimpleQuery"/>
                
				<logic:equal name="IsAddressListForm" property="paraFlag" value="1">
				<goa:submit styleClass="button"  property="search" operation="ZQSZ.VIEW" onclick="toSimpleQuery('../infoservice/grouplist.do?paraFlag=1')" value="查询"/>
					<goa:button styleClass="button"  property="add" operation="ZQSZ.PUBOPT" onclick="doAdd()" value="新增"/>
					<goa:button styleClass="button"  property="modify" operation="ZQSZ.PUBOPT" onclick="toUrl('../infoservice/group.do?actionType=modify',true)" value="修改"/>
					<goa:button  styleClass="button" property="delete" operation="ZQSZ.PUBOPT" value="删除"  onclick="doDelete()"/>
				</logic:equal>
				<logic:equal name="IsAddressListForm" property="paraFlag" value="0">
				<goa:submit styleClass="button"  property="search" operation="ZQSZ.VIEW" onclick="toSimpleQuery('../infoservice/grouplist.do?paraFlag=0')" value="查询"/>
                <goa:button styleClass="button"  property="add" operation="ZQSZ.OPT" onclick="doAdd()" value="新增"/>
                <goa:button styleClass="button"  property="modify" operation="ZQSZ.OPT" onclick="toUrl('../infoservice/group.do?actionType=modify',true)" value="修改"/>
                <goa:button  styleClass="button" property="delete" operation="ZQSZ.OPT" value="删除"  onclick="doDelete()"/>
				</logic:equal>
            </td>
          </tr>
                </table></td>
    </tr>
</table>

<table width="95%" border="0" cellpadding="2" cellspacing="0"  align="center">
	<tr>
		<td width="32" align="left" class="Listtitle">
		<input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)">
		</td>
		<td width="20%" class="Listtitle" id=".group_name" onclick="onchangeOrderBy(this)"> <div align="left">群组名称</div></td>
		<td class="Listtitle"> <div align="left">成员</div></td> 
	</tr>
	<logic:iterate id="ps" name="IsAddressListForm" property="pageControl.data" type="com.gever.goa.infoservice.vo.IsAddressListVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"  ondblclick="javascript:toUrl('<%=context%>/infoservice/groupview.do?fid=<goa:write maxlen="0" name="ps" property="serial_num" filter="true"/>')">
	
		<td align="left" class="listcellrow">
		<input type='checkbox' name='fid' value='<goa:write maxlen="0" name="ps" property="serial_num" filter="true"/>'>    
		</td>
		
		<td class="listcellrow">
		<goa:write maxlen="16" name="ps" property="group_name" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow4">
		<goa:write maxlen="56" name="ps" property="memberNames" filter="true"/>&nbsp;
		</td>
		
	</tr>
	</logic:iterate>
</table>

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
		<td align="center">
		<html:hidden name="IsAddressListForm" property="orderType"/>
		<html:hidden name="IsAddressListForm" property="orderFld"/>
		<goa:pagelink name="IsAddressListForm" property="pageControl" /> 
		</td>
	</tr>
</table>
</html:form>
</body>
</html>