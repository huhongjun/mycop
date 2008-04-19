<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<select name="scope" style="width:100%;height=100%" multiple>
<logic:iterate indexId="ind" id="element" name="list">
	<option value="<bean:write name="element" property="ID"/>"><bean:write name="element" property="ecName"/></option>
</logic:iterate>
</select>