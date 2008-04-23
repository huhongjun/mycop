/*
 * 创建日期 2004-4-26
 *
 */
package com.gever.struts.pager.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gever.struts.pager.PageControl;
import com.gever.web.util.URLBuilder;

/**
 * 首页标签
 * @author Hu.Walker
 */
public class FirstPageTag extends BodyTagSupport {
	private String text;
	private PagerTag parent;
	private PageControl pageControl = null;

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		parent = (PagerTag)this.getParent();
		pageControl = (PageControl)pageContext.findAttribute(parent.getName());		
		
		try {
			JspWriter out = bodyContent.getEnclosingWriter();
			if (pageControl.getCurrentPage() <= 1) {
				out.write(text);
			}else {
				String url = URLBuilder.addParameter(parent.getPage(),"page=1");
				out.write("<a href=" + url + ">");
				out.write(text + "</a>");
			}
		}catch(IOException e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {
		text = bodyContent.getString();
		return SKIP_BODY;
	}

}
