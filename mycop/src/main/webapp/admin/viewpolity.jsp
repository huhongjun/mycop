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
<html:form action="/admin/viewpolity" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">������ò</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="PolityForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			
				<tr> 
				  <td>
				  <table width="400" height="36" border="0" align="center"  cellpadding="2" cellspacing="1">
                  			<tr height="23">
					    <td  width="120" height="23" nowrap class="InputLabelCell">������ò���룺</td>
					    <td  width="383" height="23" class="f12"><bean:write  name="PolityForm" property="vo.polity_code" filter="true"/></td>
					</tr>
					<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">��&nbsp;��&nbsp;��&nbsp;ò��</td>
					    <td  width="383" height="23" class="f12"><bean:write   name="PolityForm" property="vo.polity_name" filter="true"/>
						</td>
					</tr>	
					<tr height="23">					     
                    			<td width="86"  height="23"  nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
				        <td class="f12" class="f12"><bean:write  name="PolityForm" property="vo.remarks" filter="true"/></td>
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
		<html:hidden name="PolityForm" property="fid" />
		<goa:button property="save" value="�޸�" styleClass="button"  onclick="toUrl('../admin/editpolity.do?actionType=modify',false)" operation="ZZMM.OPT"/>
		<goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="ZZMM.VIEW"/></td>
        	
      </tr>
    </table>
    </center>
</div>
</html:form>
</body>
