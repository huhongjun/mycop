/*
 * 创建日期 2004-6-2
 */
package com.gever.struts.pager;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.gever.config.Environment;

/**
 * 分页辅助类<br>
 * 使用方式：继承AbstractPageControlHelper,实现两个获取数据的抽象方法,然后调用newPageControl()方法返回一个PageControl<br>
 * @author Hu.Walker
 */
public abstract class AbstractPageControlHelper {
	public AbstractPageControlHelper() {
	}

	/**
	 * 调用getPagerData，getRowCount，返回
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public PageControl newPageControl(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			
		PageControl pageControl = new PageControl();
		pageControl.setRowsPerPage(Environment.getIntProperty("pager.rowsPerPage",20));
		pageControl.setIndexSize(Environment.getIntProperty("pager.indexSize",7));

		long page = 1;
		String pageStr = request.getParameter("page");
		if (pageStr == null || pageStr.length() == 0) {
			pageStr = (String) request.getAttribute("page");
		}
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}

		pageControl.setMaxRowCount(
			getRowCount(mapping, form, request, response));

		page = page < 1 ? 1 : page;
		page =
			page > pageControl.getMaxPage() ? pageControl.getMaxPage() : page;

		long start = pageControl.getRowsPerPage() * (page - 1);

		pageControl.setCurrentPage(page);
		pageControl.setData(
			getPagerData(
				mapping,
				form,
				request,
				response,
				start,
				pageControl.getRowsPerPage()));

		return pageControl;
	}
	
	/**
	 * 获取欲显示的数据
	 * @param request
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param start 起始记录
	 * @param count 欲获取的记录数
	 * @return 查询结果-值对象集合
	 * @exception
	 */
	public abstract Collection getPagerData(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		long start,
		long count) throws Exception;
	
	/**
	 * 获取记录总数,计算页数必须
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 记录总数
	 * @exception
	 */
	public abstract long getRowCount(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception;
}
