<%@ page contentType="text/xml; charset=GBK" %><%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %><%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %><%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %><%@ taglib uri="/WEB-INF/taglib/gever-pager.tld" prefix="smile" %><?xml version="1.0" encoding="GBK" ?>
<tree>
<logic:present name="xmlstr">
<%=request.getAttribute("xmlstr")%>
</logic:present>
</tree>