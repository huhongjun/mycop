<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/taglib/gever-gdp.tld" prefix="gdp" %>
<%Collection cssList = (Collection) (request.getSession(true).getAttribute("cssList"));
  StringBuffer cssListOutput=new StringBuffer();
  if(cssList==null){//�û�δ��¼ʱ��¼��������CSS
	  cssListOutput.append("<link href=\""+request.getContextPath()+"/css/css.css\" rel=\"stylesheet\" type=\"text/css\">"); 
      cssListOutput.append("\n");
	   cssListOutput.append("<link href=\""+request.getContextPath()+"/common/xtreeEx/xtree.css\" rel=\"stylesheet\" type=\"text/css\">");
}else{
  Iterator it = cssList.iterator();
  while(it.hasNext()) {
    cssListOutput.append("<link href=\""+request.getContextPath()+it.next().toString()+"\" rel=\"stylesheet\" type=\"text/css\">");
	cssListOutput.append("\n");
}
 }
  request.setAttribute("cssListOutput",cssListOutput.toString());
%>