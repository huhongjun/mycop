/*
 * 创建日期 2004-4-24
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
 * 上一页标签
 * @author Hu.Walker
 */
public class PrevPageTag extends BodyTagSupport {
	private String text;
	private long prevPage;
	private PagerTag parent;
	private PageControl pc = null;

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		parent = (PagerTag)this.getParent();
		pc = (PageControl)pageContext.findAttribute(parent.getName());
		
		long currentPage = pc.getCurrentPage();
		prevPage = currentPage <= 1 ? 1 : currentPage - 1;
		try {
			JspWriter out = bodyContent.getEnclosingWriter();
			if (pc.getCurrentPage() <= 1) {
				out.write(text);
			}else {
				String url = URLBuilder.addParameter(parent.getPage(),"page=" + prevPage);
				out.write("<a href=" + url + ">");
				out.write(text + "</a>");
			}
		}catch(IOException e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}
	
	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {
		text = bodyContent.getString();
		return SKIP_BODY;
	}

}
