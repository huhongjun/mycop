/*
 * 创建日期 2004-4-26
 *
 */
package com.gever.struts.pager.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gever.config.Environment;
import com.gever.struts.pager.PageControl;

/**
 * 分页抽象类,使用时只需继承BasePagerAction,并实现getPagerData和getRowCount<br>
 * 执行之后,BasePagerAction会把一个PageControl对象放入request:request.setAttribute("pageControl",pageControl);
 * @author Hu.Walker
 */
public abstract class BasePagerAction extends Action {
	protected PageControl pageControl = new PageControl();
	
	{
		pageControl.setRowsPerPage(Environment.getIntProperty("pager.rowsPerPage",20));
		pageControl.setIndexSize(Environment.getIntProperty("pager.indexSize",7));
	}
	
	/* （非 Javadoc）
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		long page = 1;
		String pageStr = request.getParameter("page");
		if (pageStr == null || pageStr.length() == 0) {
			pageStr = (String) request.getAttribute("page");
		}
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		
		pageControl.setMaxRowCount(getRowCount(mapping,form,request,response));
		
		page = page < 1 ? 1 : page;
		page = page > pageControl.getMaxPage() ? pageControl.getMaxPage() : page;
		
		long start = pageControl.getRowsPerPage() * (page - 1);
		
		pageControl.setCurrentPage(page);
		pageControl.setData(getPagerData(mapping,form,request,response,start,pageControl.getRowsPerPage()));
		
		request.setAttribute("pageControl",pageControl);
		return mapping.findForward("success");
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
	 */
	public abstract Collection getPagerData(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response,
		long start,
		long count);
	
	/**
	 * 获取记录总数,计算页数必须
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public abstract long getRowCount(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response);
}
