<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/taglib/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>

<logic:equal parameter="isCA" value="1">
	<tiles:insert definition="gever.gdp.calogin" flush="true" />
</logic:equal>

<logic:notEqual parameter="isCA" value="1">
	<tiles:insert definition="gever.gdp.login" flush="true" />
</logic:notEqual>
