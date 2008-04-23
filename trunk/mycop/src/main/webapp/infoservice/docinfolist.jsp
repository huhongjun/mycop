<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<html>
<body class="bodybg">
<html:form action="/infoservice/docinfolist" method="post">

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
        <td align="center" class="TableTitleText">
		<logic:equal name="IsInfoManageForm3" property="nodeID" value="">文档资料</logic:equal>
		<goa:write name="IsInfoManageForm3" property="nodeRemark" filter="true" /></td>
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
					<html:text styleClass="input2" size="10" name="IsInfoManageForm3" property="paraSimpleQuery"/>
					</td>
					<td>
					<%String clk1="toSimpleQuery('"+context+"/infoservice/docinfolist.do?paraFlag=7')";%>
					<goa:submit styleClass="button"  property="simplequery" onclick="<%=clk1%>" value="查询" operation="WDZL.VIEW"/>
					<logic:notEqual name="IsInfoManageForm3" property="nodeID" value="">
					<goa:button styleClass="button"  property="add" onclick="doAdd()" value="新增" operation="WDZL.OPT"/>
					</logic:notEqual>
					<%String clk="toUrl('"+context+"/infoservice/docinfoedit.do?actionType=modify',true)";%>
                    <goa:button styleClass="button"  property="copy" onclick="<%=clk%>" value="修改" operation="WDZL.OPT"/>
					<goa:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()" operation="WDZL.OPT"/>
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
		<td width="15%" class="Listtitle" id=".user_code" onclick="onchangeOrderBy(this)"> <div align="left">创建人</div></td>
		<td width="18%" class="Listtitle" id=".create_date" onclick="onchangeOrderBy(this)"> <div align="left">创建日期</div></td>
        <td width="15%" class="Listtitle" id=".file_name" onclick="onchangeOrderBy(this)"> <div align="left">附件</div></td>
		<td width="25%" class="Listtitle" id=".url" onclick="onchangeOrderBy(this)"> <div align="left">网址</div></td>
	</tr>
	<logic:iterate id="ps" name="IsInfoManageForm3" property="pageControl.data" type="com.gever.goa.infoservice.vo.IsInfoManageVO" indexId="index">
	<tr  onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);"  ondblclick="javascript:toUrl('<%=context%>/infoservice/docinfoview.do?fid=<goa:write maxlen="0" name="ps" property="title" filter="true"/>::<goa:write maxlen="0" name="ps" property="info_type" filter="true"/>')">
		<td align="left" class="listcellrow">
		<input type='checkbox' name="fid" value="<goa:write maxlen="0" name="ps" property="title" filter="true"/>::<goa:write maxlen="0" name="ps" property="info_type" filter="true"/>" >    		  
		</td>
		<td class="listcellrow">
		<goa:write maxlen="25" name="ps" property="title" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<goa:write maxlen="0" name="ps" property="user_name" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
		<goa:write maxlen="0" name="ps" property="create_date" filter="true"/>&nbsp;
		</td>
		<td class="listcellrow">
	<logic:equal name="ps" property="file_path" value="">
     无
	</logic:equal>
	<% String strUrl = "";
               String strHref =  ""+context+"/util/downloadfile.jsp?filepath=" 
                   + ps.getFile_path() + "&filename=" + ps.getFile_name();
   
                 String downloadMethod = "download2('" + ps.getFile_path() + "','" + ps.getFile_name()+"')";
  %>
              <goa:link style="cursor:hand" href="<%=strHref%>" onclick ="<%=downloadMethod%>" title="下载附件" ><%=ps.getFile_name()%>
              </goa:link>&nbsp;
		</td>
		<td class="listcellrow4">
		<logic:equal name="ps" property="url" value="">
		无
		</logic:equal>
		<logic:notEqual name="ps" property="url" value="">
		<a href="<goa:write name="ps" property="url" filter="true"/>" target="window_name">
		<goa:write maxlen="25" name="ps" property="url" filter="true"/>&nbsp;</a>
		</logic:notEqual>
		</td>
	</tr>
	</logic:iterate>
</table>

<table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
    <tr> 
		<td align="center">
		<html:hidden name="IsInfoManageForm3" property="orderType"/>
		<html:hidden name="IsInfoManageForm3" property="orderFld"/>
		<goa:pagelink name="IsInfoManageForm3" property="pageControl" /> 
		</td>
	</tr>
</table>
</html:form>
</body>
</html>