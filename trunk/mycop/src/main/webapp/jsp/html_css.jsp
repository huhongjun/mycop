<jsp:include page="/jsp/sys_css.jsp" />
<%
String htmlCssList=(String)request.getAttribute("cssListOutput");
htmlCssList=htmlCssList.replace('\n',' ');
%>
cssArr=('<%=htmlCssList%>');
document.write(cssArr);
