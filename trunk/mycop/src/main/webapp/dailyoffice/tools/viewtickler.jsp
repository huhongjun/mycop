<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>

<body class="bodybg">
<html:form action="/dailyoffice/tools/viewTickler" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span>
    <table width="80%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center"><span class="TableTitleText">备忘录内容</span></td>
      </tr>
      <tr>
        <td>&nbsp;<html:hidden name="TicklerForm" property="actionType"/></td>
      </tr>
      <tr>
        <td align="center">
			<table width="100%" border="0" cellpadding="2" cellspacing="0">
				<tr>
				<td width="10%"></td>
				<td width="80%"></td>
				<td width="10%"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				  <td>

				  <table  border="0" cellpadding="5" cellspacing="0">
                  	<tr>
						<td width="20%"></td>
						<td width="20%"></td>
						<td width="60%"></td>
					</tr>
					<tr>
					    <td height="23"  class="InputLabelCell">编制日期：</td>
					    <td  class="f12" height="23" nowrap><bean:write  name="TicklerForm" property="vo.create_date" filter="true"/></td>
                  			</tr>
					<tr>
					    <td  class="InputLabelCell">内&nbsp;&nbsp;&nbsp;&nbsp;容：</td>
					    <td class="f12" style="word-break:break-all"  colspan="2"><bean:write   name="TicklerForm" property="vo.content" filter="true"/>
						</td>
					</tr>
					</table>
				</td>
				<td>&nbsp;</td>
				</tr>
				<tr>
				<td>&nbsp;</td>
				<td>
					<table height="36" border="0" cellpadding="5" cellspacing="0">
                                    <tr >
									<!--td width="20"></td-->
                                      <td width="20%" class="InputLabelCell">提&nbsp;&nbsp;&nbsp;&nbsp;醒：</td>
                                      <logic:equal value="0" property="vo.remind_flag" name="TicklerForm">
                                      <td  width="2%" align="left">
                                         <input type='checkbox' disabled="true"/>
                                      </td>
                                 
                                      <td width="60%" class="f12">&nbsp;</td>
                                      </logic:equal>
                                      <logic:notEqual value="0" property="vo.remind_flag" name="TicklerForm">
                                      <td  width="2%" align="left">
                                        <input type='checkbox' disabled="true" checked  />
                                      <td width="20%" align="right" class="InputLabelCell" nowrap>提醒时间：</td>
                                      <td width="40%
									  " class="f12" nowrap>
                                        <bean:write  name="TicklerForm" property="vo.awoke_time" filter="true"/>
                                      </td>
                                      </td>
                                      </logic:notEqual>
                                      <td align="right" width="30%" class="InputLabelCell" nowrap>完成：</td>
                                      <td width='3%' align="left">
                                         <logic:equal value="0" property="vo.finish_flag" name="TicklerForm">
                                              <input type='checkbox' disabled="disabled" />
                                            </logic:equal>
                                            <logic:notEqual value="0" property="vo.finish_flag" name="TicklerForm">
                                              <input type='checkbox' disabled="disabled" checked />
                                            </logic:notEqual>
                                      </td>
									  <td width="20"></td>
                                    </tr>
                     </table>
		  			</td>
					<td>&nbsp;</td>
				</tr>

          </table>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
	  <logic:notPresent name="fromModule" scope="request">
      <tr>
        <td align="center">
		<html:hidden name="TicklerForm" property="fid" />
		<%String modifylink="toUrl('"+context+"/dailyoffice/tools/editTickler.do?actionType=modify',false)";%>
		<goa:button property="save" value="修改" styleClass="button"  onclick="<%=modifylink%>" operation="BWL.OPT"/>
        <goa:button property="exit" value="返回" styleClass="button" onclick="doAction('goUrl(index)')" operation="BWL.VIEW"/></td>
      </tr>
	  </logic:notPresent>
	   <logic:present name="fromModule" scope="request">
	   <tr>
			<td align="center">
			<html:button property="exit" value="返回" styleClass="button" onclick="javascript:history.back()"/>
			</td>
	   </tr>
	   </logic:present>
    </table>
    </center>
</div>

</html:form>
</body>

