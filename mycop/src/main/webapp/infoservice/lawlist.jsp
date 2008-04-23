<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<SCRIPT LANGUAGE="JavaScript">
<!--
	function openUrl(url){
		window.open(url,'','height=400,width=500,resizable=yes,scrollbars=yes');
		window.event.returnValue= false;
	}
//-->
</SCRIPT>
<html>
<body class="bodybg">
<html:form action="/infoservice/lawlist" method="post">

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
        <td align="center" class="TableTitleText">
		<logic:equal name="IsInfoManageForm2" property="nodeID" value="">政策法规</logic:equal>
		<goa:write name="IsInfoManageForm2" property="nodeID" filter="true" /></td>
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
					<td >
					<html:text styleClass="input2" size="10" name="IsInfoManageForm2" property="paraSimpleQuery"/>
					</td>
					<td>
					<%String clk1="toSimpleQuery('"+context+"/infoservice/lawlist.do?paraFlag=1')";%>
					<goa:button styleClass="button"  property="simplequery" onclick="<%=clk1%>" value="查询" operation="ZCFG.VIEW"/>
					<logic:notEqual name="IsInfoManageForm2" property="nodeID" value="">
					<goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="ZCFG.OPT"/>
					</logic:notEqual>
					<%String clk2="toUrl('"+context+"/infoservice/law.do?actionType=modify',true)";%> 
                    <goa:button styleClass="button"  property="copy" onclick="<%=clk2%>" value="修改" operation="ZCFG.OPT"/>
					<goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="ZCFG.OPT"/>
					</td>
				</tr>
			</table>
	    </td>
    </tr>
</table>

<table width="95%" border="0" cellpadding="2" cellspacing="0"  align="center">
	<tr height="10">
		<td width="3%" align="left" class="Listtitle">
		<input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)">
		</td> 
		<td width="65%" class="Listtitle" id=".title" onclick="onchangeOrderBy(this)"> <div align="left">主题</div></td>
		<td width="15%" class="Listtitle" id=".file_name" onclick="onchangeOrderBy(this)"> <div align="left">附件</div></td>
		<td width="20%" class="Listtitle" id=".info_type" onclick="onchangeOrderBy(this)"> <div align="left">类别</div></td>	
	</tr>
	<logic:iterate id="ps" name="IsInfoManageForm2" property="pageControl.data" type="com.gever.goa.infoservice.vo.IsInfoManageVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"  ondblclick="javascript:toUrl('<%=context%>/infoservice/lawview.do?fid=<goa:write maxlen="0" name="ps" property="title" filter="true"/>::<goa:write maxlen="0" name="ps" property="info_type" filter="true"/>')">
		<td align="left" class="listcellrow">
		<input type='checkbox' name="fid" value="<goa:write maxlen="0" name="ps" property="title" filter="true"/>::<goa:write maxlen="0" name="ps" property="info_type" filter="true"/>" >    	  
		</td>
		<td class="listcellrow">
		<goa:write maxlen="50" name="ps" property="title" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<logic:equal name="ps" property="file_path" value="">
		无
		</logic:equal>
<% String strUrl = "";
               String strHref =  context+"/util/downloadfile.jsp?filepath=" 
                   + ps.getFile_path() + "&filename=" + ps.getFile_name();
   
                 String downloadMethod = "download2('" + ps.getFile_path() + "','" + ps.getFile_name()+"')";
  %>
              <goa:link style="cursor:hand" href="<%=strHref%>" onclick ="<%=downloadMethod%>" title="下载附件" ><%=ps.getFile_name()%>
              </goa:link>&nbsp;
		</td>
		<td class="listcellrow4">
		<goa:write maxlen="12" name="ps" property="info_type" filter="true"/>&nbsp;
		</td>
	</tr>
	</logic:iterate>
</table>

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
		<td align="center">
		<html:hidden name="IsInfoManageForm2" property="orderType"/>
		<html:hidden name="IsInfoManageForm2" property="orderFld"/>
		<goa:pagelink name="IsInfoManageForm2" property="pageControl" /> 
		</td>
	</tr>
</table>
</html:form>
</body>
</html>