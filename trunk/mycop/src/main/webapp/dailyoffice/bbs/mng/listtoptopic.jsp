<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%
String context =request.getContextPath();
%>

<html>
<body class="bodybg">
  <html:form action="dailyoffice/bbs/mng/listtoptopic" method="post">
  <table width="95%" border="0" align="center" cellpadding="1" cellspacing="1">
  <tr> 
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr> 
    <td colspan="6" align="center" class="TableTitleText">主题</td>
  </tr>
  <tr> 
    <td colspan="6" align="right">&nbsp;</td>
  </tr>
  <tr> 
    <td class="f12" colspan="6" align="right">
	 <table border="0" cellspacing="1" cellpadding="2">
        <tr>
		 
          <td> <html:button styleClass="button"  property="add" onclick="doAdd()" value="新增"/>
          </td>
		   <td>
		
		   <html:button styleClass="button"  property="copy" onclick="toUrl('/goa/sample/editsample.do?actionType=modify',true)" value="修改"/>
          </td>
		   <td> <html:button  styleClass="button" property="delete" value="删除"  onclick="doDelete()"/>
		    <td> <html:button  styleClass="button" property="delete" value="查看"  onclick="toUrl('/goa/sample/viewsample.do',true)"/>
		  <td><html:button styleClass="button"  property="copy"  onclick="doCopy()"value="复制"/>
          </td>
         
          </td>
        </tr>
      </table>
	  </td>
  </tr>
  <tr> 
    <td colspan="6">
	<table width="100%" border="0" cellpadding="2" cellspacing="0"  align="center">
        <tr height="20">
            <td width="32" class="Listtitle"><input alt="选择" type="checkbox" name="selectall" onclick="selectAllChk(this)"></td>
      		<td width="10%" class="Listtitle">一级论坛名称</td>
      		<td width="15%" class="Listtitle">状态</td>
            <td width="10%" class="Listtitle">电话</td>
            <td width="10%" class="Listtitle">年龄</td>
			<td width="10%" class="Listtitle">生日</td>
			<td width="15%" class="Listtitle">新增日期</td>
			<td width="10%" class="Listtitle">收入</td>
			<td  class="Listtitle">备注</td>
           		  
    </tr>
    <logic:iterate id="ps" name="TopBoardForm" property="pageControl.data" type="com.gever.goa.dailyoffice.bbs.vo.TopBoardVO" indexId="index">
	<tr onmouseover="javascript:changeBgColorOnMouseOver(this);" onmouseout="javascript:changeBgColorOnMouseOut(this);" ondblclick="<%="toUrl('<%=request.get%>/dailyoffice/bbs/mng/listtoptopic.do?fid=" +ps.getId()+"',false)"%>">
		<td align="center" class="listcellrow">
			<input type='checkbox' name='fid' value='<bean:write name="ps" property="id" filter="true"/>' >    		  
			</td>
		<td class="listcellrow"><bean:write name="ps" property="id" filter="true"/>&nbsp;</td>
		<td class="listcellrow"><bean:write name="ps" property="name" filter="true"/></td>
<td class="listcellrow"><bean:write name="ps" property="tboard_name" filter="true"/>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="" filter="true"/>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="bdate" filter="true"/>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="adddate" filter="true"/>&nbsp;</td>
<td class="listcellrow"><bean:write name="ps" property="amt" filter="true"/></td>
<td class="listcellrow4"><bean:write name="ps" property="memo" filter="true"/>&nbsp;</td>

	
	</tr>
	</logic:iterate>
</table>


</td>
  </tr>
  <tr> 
    <td colspan="8" align="center" class="f12">
		<goa:pagelink name="SampleForm" property="pageControl" /> 
		</td>
	  </tr>
	</table>
</html:form>
</body>
</html>
