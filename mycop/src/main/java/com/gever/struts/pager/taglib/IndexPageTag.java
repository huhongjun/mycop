/*
 * 创建日期 2004-5-1
 *
 */
package com.gever.struts.pager.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.gever.struts.pager.PageControl;
import com.gever.web.util.URLBuilder;

/**
 * 标签索引形如:2 3 4 5 6
 * @author Hu.Walker
 */
public class IndexPageTag extends TagSupport {
	
	/* （非 Javadoc）
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		PagerTag parent = (PagerTag)this.getParent();
		PageControl pc = (PageControl)pageContext.findAttribute(parent.getName());
		long currentPage = pc.getCurrentPage();
		long maxPage = pc.getMaxPage();
		
		long indexStart = 1;
		long indexEnd = pc.getIndexSize();

		if (maxPage < pc.getIndexSize()) {
			indexStart = 1;
			indexEnd = maxPage;
		}else if (currentPage + pc.getIndexSize() / 2 > maxPage) {
			indexStart = maxPage - pc.getIndexSize() + 1;
			indexEnd = maxPage;
		}else{
			long indexCenter = pc.getIndexSize() / 2 + 1;
			indexCenter = currentPage <= indexCenter ? indexCenter : currentPage;		
			indexStart = indexCenter - pc.getIndexSize() / 2;
			indexEnd = indexCenter + pc.getIndexSize() / 2;
		}
		 
		StringBuffer result = new StringBuffer();
		
		for (int i = 0; i <= (indexEnd - indexStart); i++) {
			long temp = indexStart + i;
			String nbsp = i == (indexEnd - indexStart) ? "" : "&nbsp;";
			
			if (temp == currentPage) {
				result.append("<b>" + temp + "</b>" + nbsp);
			}else {
				String url = URLBuilder.addParameter(parent.getPage(),"page=" + temp);
				result.append("<a href=\"" + url + "\">" + temp + "</a>" + nbsp);
			}
		}
		
		try {
			pageContext.getOut().write(result.toString());
		}catch(IOException e) {
			throw new JspException(e.getMessage());
		}
		return EVAL_PAGE;
	}

}
