<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<select name="scope" style="width:100%;height=100%" multiple>
<logic:iterate indexId="ind" id="element" name="list">
<logic:equal name="element" property="isleaf" value="1">
	<option value="<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></option>
	</logic:equal>
</logic:iterate>
</select>