<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<html:errors />
<%String context =request.getContextPath();%>

<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<body class="bodybg">
<html:form action="/dailyoffice/bbs/mng/edittopboard" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">一级论坛编辑</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="TopBoardForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			<table border="0" cellpadding="20" cellspacing="0">
				<tr> 
				  <td>
				  <table width="400" height="36" border="0" cellpadding="2" cellspacing="1">
                  <tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">名称</td>
					    <td width="194" height="23"><div align="right" class="style1"></div>					      
					      <div align="right" class="style1"></div>					      <html:text styleClass="input" property="vo.tboard_name" size="20" validatetype="notempty" msg="名称不能为空！" maxlength="50"/></td>
				        <td width="104"><span class="style1">*</span></td>
                  </tr>
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">状态</td>
					    <td height="23" colspan="2">
						<html:select property="vo.tboard_state" >
							<html:option  value="1">活动状态</html:option>
							<html:option  value="0">关闭状态</html:option>
						</html:select>
						</td>
					</tr>
					
					<tr class="listcellrow">
					     
                    <td width="86" height="100" nowrap class="InputLabelCell">备注</td>
					     <td height="100" colspan="2"><html:textarea property="vo.tboard_note" cols="37" rows="4" styleClass="input" maxlength="200"/></td>
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
		<html:button property="save" value="保存" styleClass="button"  onclick="return doSave(TopBoardForm)"/>
		<html:button property="save" value="保存并返回" styleClass="button"  onclick="return doSaveExit(TopBoardForm)"/>
          <html:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')"/></td>
      </tr>
    </table>
  </center>
</div>

</html:form>
</body>

