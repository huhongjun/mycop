/*
 * 创建日期 2004-4-19
 *
 */
package com.gever.struts.pager.taglib;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 分页父标签
 * @author Hu.Walker
 */
public class PagerTag extends BodyTagSupport {
	private String name;
	private String page;
	private String scope;
	
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setPage(String string) {
		String contextPath = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
		page = contextPath + string;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("</form>");
		}catch(IOException e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try {
			pageContext.getRequest().setCharacterEncoding("GBK");
			pageContext.getResponse().setContentType("text/html; charset=GBK");
			pageContext.getOut().write("<form name='page_form' action='' method='post'>");
		}catch(UnsupportedCharsetException e) {
			throw new JspException(e.getMessage());
		}catch(IOException e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}

}
