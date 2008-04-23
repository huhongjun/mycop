package com.gever.web.taglib;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  减少jsp页面中的java代码：
 *  	request.getContextPath()
 *  	<%=context%>
 *  
 *	Example: <gdp:context> ,实际效果：填入web context如gdp
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
