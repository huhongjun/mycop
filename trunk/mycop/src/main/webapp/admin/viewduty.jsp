<%@ page contentType="text/html; charset=GB2312"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/gever-goa.tld" prefix="goa" %>
<%String context =request.getContextPath();%>
<script type="text/javascript" src="<%=context%>/jscript/Validator.js"></script>
<script type="text/javascript" src="<%=context%>/jscript/pub.js"></script>
<body class="bodybg">
<html:form action="/admin/viewduty" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">ְ����Ϣ</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="DutyForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			
				<tr> 
				  <td>
				  <table width="400" height="36" border="0" align="center"  cellpadding="2" cellspacing="1">
                  			<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">ְ����룺</td>
					    <td  width="383" height="23" class="f12"><bean:write  name="DutyForm" property="vo.duty_code" filter="true"/></td>
					</tr>
					<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">ְ�����ƣ�</td>
					    <td  width="383" height="23" class="f12"><bean:write   name="DutyForm" property="vo.duty_name" filter="true"/>
						</td>
					</tr>						     
                    			<tr height="23">					     
                    			     <td width="86"  height="23"  nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
				              <td class="f12" class="f12"><goa:write  name="DutyForm" property="vo.remarks" filter="false"/></td>
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
		<html:hidden name="DutyForm" property="fid" />
		<goa:button property="save" value="�޸�" styleClass="button"  onclick="toUrl('../admin/editduty.do?actionType=modify',false)" operation="ZWWF.OPT"/>
        	<goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="ZWWF.VIEW"/></td>
        	
      </tr>
    </table>
    </center>
</div>
</html:form>
</body>

