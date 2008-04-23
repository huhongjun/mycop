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
 * 下一页标签
 * @author Hu.Walker
 */
public class NextPageTag extends BodyTagSupport {
	private String text;
	private long nextPage;
	private PagerTag parent;
	private PageControl pc;

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		parent = (PagerTag)this.getParent();
		pc = (PageControl)pageContext.findAttribute(parent.getName());
		
		long currentPage = pc.getCurrentPage();
		nextPage = currentPage >= pc.getMaxPage() ? pc.getMaxPage() : currentPage + 1;
		
		try {
			JspWriter out = bodyContent.getEnclosingWriter();
			if (pc.getCurrentPage() == pc.getMaxPage()) {
				out.write(text);
			}else {
				String url = URLBuilder.addParameter(parent.getPage(),"page=" + nextPage);
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
