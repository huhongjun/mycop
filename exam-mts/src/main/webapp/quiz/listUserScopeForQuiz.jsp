<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<select name="scope" style="width:100%;height=100%" size="5">
<logic:iterate indexId="ind" id="element" name="list">
	<option value="<bean:write name="element" property="ID"/>" title="<bean:write name="element" property="midInstitutionId"/>-<bean:write name="element" property="midRecruitbatchId"/>-<bean:write name="element" property="midStudykindId"/>-<bean:write name="element" property="midSubjectId"/>"><bean:write name="element" property="ecName"/></option>
</logic:iterate>
</select>