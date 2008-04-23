/*
 * 创建日期 2004-4-25
 *
 */
package com.gever.struts.pager.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gever.struts.pager.PageControl;

/**
 * @author Hu.Walker
 */
public class ListPageTag extends BodyTagSupport {

	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		PagerTag parent = (PagerTag)this.getParent();
		PageControl pc = (PageControl)pageContext.findAttribute(parent.getName());
		long currentPage = pc.getCurrentPage();
		long maxPage = pc.getMaxPage();
		
		StringBuffer result = new StringBuffer();
		//result.append("<form name='page_form' action='' method='post'>");
		result.append("<select name='page' id='page' onchange='javascript:document.page_form.action=\"" + parent.getPage() + "\";document.page_form.target=\"_self\";document.page_form.submit();'>");
		
		for (int i = 1; i <= maxPage; i++) {
			if (i == currentPage) {
				result.append("<option selected>" + i + "</option>");
			}else {
				result.append("<option >" + i + "</option>");
			}
		}
		
		result.append("</select>");
		//result.append("</form>");
		try {
			pageContext.getOut().write(result.toString());
		}catch(IOException e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}

}
