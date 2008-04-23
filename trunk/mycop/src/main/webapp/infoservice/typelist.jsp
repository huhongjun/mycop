<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
String operate = (String)session.getAttribute("operate");
session.removeAttribute("operate");
String[] operateID=(String[])session.getAttribute("operateID");
if(operateID==null){
    operateID=new String[0];
}
session.removeAttribute("operateID");
String nodeid=(String)session.getAttribute("nodeid");
if(nodeid==null){
	nodeid="";
}
session.removeAttribute("nodeid");
%>
<SCRIPT LANGUAGE="JavaScript">
<!--
function refreshTree(){
    var operate='<%=operate%>';
	var nodeid="<%=nodeid%>";
	//alert(nodeid);
    if("insert"==operate){
        //alert(operate);
		window.parent.frames["left"].reload(nodeid);
    }else if("update"==operate){
        //alert(operate);
		window.parent.frames["left"].reload(nodeid);
    }else if("delete"==operate){
        //alert(operate);
    	window.parent.frames["left"].reload(nodeid);
		//alert(nodeid);
        		}
        }
		  <logic:present name="ErrorMsg" scope="request" >
    alert('<bean:write name="ErrorMsg" scope="request"/>');
    </logic:present>

//-->
</SCRIPT>

<html>
<body onload='refreshTree()' class="bodybg">
<html:form action="/infoservice/typelist" method="post">

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
        <td align="center" class="TableTitleText">
		<goa:write name="IsDoTypesForm" property="nodeid" filter="ture" />类别</td>
    </tr>
    <tr> 
        <td>&nbsp;</td>
    </tr>
    <tr> 
        <td align="right">
	        <table>
                <tr>		 
                    <td class="InputLabelCell"> 
					类别名称：
					</td>
					<td >
					<html:text styleClass="input2" size="10" name="IsDoTypesForm" property="paraSimpleQuery"/>
					</td>
					<td>
					<%String clk1="toSimpleQuery('"+context+"/infoservice/typelist.do?paraFlag=')";%>
					<goa:button styleClass="button"  property="simplequery" onclick="<%=clk1%>" value="查询" operation="LSSZ.VIEW"/>
					<goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="LSSZ.OPT"/>
					<%String clk2="toUrl('"+context+"/infoservice/lawdotype.do?actionType=modify',true)";%>
                    <goa:button styleClass="button"  property="copy" onclick="<%=clk2%>" value="修改" operation="LSSZ.OPT"/>
					<goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="LSSZ.OPT"/>
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
		<td width="20%" class="Listtitle" id=".info_type" onclick="onchangeOrderBy(this)"> <div align="left">类别名称</div></td>
		<td width="20%" class="Listtitle" id=".parent_type" onclick="onchangeOrderBy(this)"> <div align="left">父类名称</div></td>
		<td width="20%" class="Listtitle" id=".moduleflag" onclick="onchangeOrderBy(this)"> <div align="left">所属模块</div></td>
		<td class="Listtitle" id=".remark" onclick="onchangeOrderBy(this)"> <div align="left">备&nbsp;&nbsp;&nbsp;&nbsp;注</div></td>
	</tr>
	<logic:iterate id="ps" name="IsDoTypesForm" property="pageControl.data" type="com.gever.goa.infoservice.vo.IsDoTypeVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"  ondblclick="javascript:toUrl('<%=context%>/infoservice/lawdotypeview.do?fid=<goa:write maxlen="0" name="ps" property="info_type" filter="true"/>')">
		<td align="left" class="listcellrow">
		<input type='checkbox' name='fid' value="<goa:write maxlen="0" name="ps" property="info_type" filter="true"/>" >    		  
		</td>
		<td class="listcellrow">
		<goa:write maxlen="12" name="ps" property="info_type" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<logic:equal name="ps" property="parent_type" value="">
		无
		</logic:equal>
		<goa:write maxlen="16" name="ps" property="parent_type" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<logic:equal name="ps" property="moduleflag" value="0">资源共享
		</logic:equal>
		<logic:equal name="ps" property="moduleflag" value="1">政策法规
		</logic:equal>
		<logic:equal name="ps" property="moduleflag" value="2">公司动态
		</logic:equal>
		<logic:equal name="ps" property="moduleflag" value="3">部门信息
		</logic:equal>
		<logic:equal name="ps" property="moduleflag" value="4">报告管理
		</logic:equal>
		<logic:equal name="ps" property="moduleflag" value="5">行业新闻
		</logic:equal>
		<logic:equal name="ps" property="moduleflag" value="6">管理制度
		</logic:equal>
		<logic:equal name="ps" property="moduleflag" value="7">文档资料
		</logic:equal>
		<td class="listcellrow4">
		<goa:write maxlen="28" name="ps" property="remark" filter="true"/>&nbsp;
		</td>
	</tr>
	</logic:iterate>
</table>

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
		<td align="center">
		<html:hidden name="IsDoTypesForm" property="orderType"/>
		<html:hidden name="IsDoTypesForm" property="orderFld"/>
		<goa:pagelink name="IsDoTypesForm" property="pageControl" /> 
		</td>
	</tr>
</table>
</html:form>
</body>
</html>
