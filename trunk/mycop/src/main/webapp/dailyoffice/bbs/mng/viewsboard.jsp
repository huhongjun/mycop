<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<body class="bodybg">
<html:form action="/dailyoffice/bbs/mng/viewsboard" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">二级论坛资料查看</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="SBoardForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr> 
				  <td>
				  <table width="400" height="36" border="0" cellpadding="2" cellspacing="1">
                  <tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">名称</td>
					    <td width="383" height="23"><bean:write  name="SBoardForm" property="vo.sboard_name" filter="true"/></td>
					</tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">状态</td>
					    <td width="383" height="23">

						<logic:equal  name="SBoardForm" property="vo.sboard_state" value="1">
							活动状态
						</logic:equal>
						<logic:equal  name="SBoardForm" property="vo.sboard_state" value="0">
							关闭状态
						</logic:equal>						
						</td>
					</tr>
					<tr class="listcellrow4">
					     
                    <td width="86" height="50" nowrap class="InputLabelCell">备注</td>
					     <td width="383" height="50"><bean:write  name="SBoardForm" property="vo.sboard_note" filter="true"/></td>
					</tr>
				  </table>
		  			</td>
				</tr>
          </table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr> 
        <td align="center"> 
		<html:hidden name="SBoardForm" property="fid" />
		<goa:button property="save" value="修改" styleClass="button"  onclick=" toUrl('editsboard.do?actionType=modify',false)" operation="LTWF.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="LTWF.VIEW"/></td>
      </tr>
    </table>
    </center>
</div>

</html:form>
</body>

