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
<html:form action="/admin/viewunit" method="post">
<div align="center">
  <center>
    <br>
    <span class="TableTitleText"><br>
    </span> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr> 
        <td align="center"><span class="TableTitleText">��λ��Ϣ</span></td>
      </tr>
      <tr> 
        <td>&nbsp;<html:hidden name="UnitForm" property="actionType"/></td>
      </tr>
      <tr> 
        <td align="center">
			
				<tr> 
				  <td>
				  <table width="400" height="36" border="0" align="center" bordercolor="red" cellpadding="2" cellspacing="1">
                  			<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">��λ��ƣ�</td>
					    <td  width="383" height="23" class="f12"><bean:write  name="UnitForm" property="vo.unit_code" filter="true"/></td>
					</tr>
					<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">��λ���ƣ�</td>
					    <td  width="383" height="23" class="f12"><bean:write   name="UnitForm" property="vo.unit_name" filter="true"/>
						</td>
					</tr>						     
                    				
					<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;����</td>
					    <td  width="383" class="f12" height="23" ><bean:write  name="UnitForm" property="vo.phone" filter="true"/></td>
					</tr>
					<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;�棺</td>
					    <td  width="383" class="f12" height="23" ><bean:write   name="UnitForm" property="vo.fax" filter="true"/>
						</td>
					</tr>						     
                    				
					<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">E_MAIL&nbsp;��</td>
					    <td  width="383" class="f12" height="23" ><bean:write  name="UnitForm" property="vo.e_mail" filter="true"/></td>
					</tr>	
					<tr class="listcellrow">
					    <td width="86" height="23" nowrap class="InputLabelCell">������ҳ��</td>
					    <td width="383" height="23" colspan="4" class="f12"><a href="<bean:write  name="UnitForm" property="vo.home_page" filter="true"/>" target="window_name"><bean:write  name="UnitForm" property="vo.home_page" filter="true"/></a>
						</td>
					</tr>
					<tr height="23">
					    <td  width="86" height="23" nowrap class="InputLabelCell">��λ���ͣ�</td>
					    <td width="383" class="f12" height="23" ><logic:equal value="0" property="vo.unit_type"   name="UnitForm">��ҵ</logic:equal>
					      <width="383" class="f12" height="23" ><logic:equal value="1" property="vo.unit_type"  name="UnitForm">����</logic:equal>
					    </td>
					</tr>						     
                    			<tr height="23">					     
                    				<td width="86"  height="23"  nowrap class="InputLabelCell">��&nbsp;&nbsp;&nbsp;&nbsp;ע��</td>
				        	<td class="f12" ><goa:write  name="UnitForm" property="vo.remark" filter="false"/></td>
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
		<html:hidden name="UnitForm" property="fid" />
		<goa:button property="save" value="�޸�" styleClass="button"  onclick="toUrl('../admin/editunit.do?actionType=modify',false)" operation="DWXX.OPT"/>
		<goa:button property="exit" value="����" styleClass="button" onclick="doAction('goUrl(index)')" operation="DWXX.VIEW"/></td>
        	
        	
      </tr>
    </table>
    </center>
</div>
</html:form>
</body>

