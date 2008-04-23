package com.gever.web.taglib;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  ����jspҳ���е�java���룺
 *  	request.getContextPath()
 *  	<%=context%>
 *  
 *	Example: <gdp:context> ,ʵ��Ч��������web context��gdp
 */
public class ContextTag  extends TagSupport{
  public ContextTag() {
  }
  public int doStartTag() throws JspException {
    HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
    try{
      this.pageContext.getOut().print(request.getContextPath());
    }catch(IOException ie){
      throw new JspException(ie);
    }
    return TagSupport.SKIP_BODY;
  }

}
