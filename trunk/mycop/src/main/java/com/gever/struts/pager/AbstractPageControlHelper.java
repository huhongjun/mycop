/*
 * �������� 2004-6-2
 */
package com.gever.struts.pager;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.gever.config.Environment;

/**
 * ��ҳ������<br>
 * ʹ�÷�ʽ���̳�AbstractPageControlHelper,ʵ��������ȡ���ݵĳ��󷽷�,Ȼ�����newPageControl()��������һ��PageControl<br>
 * @author Hu.Walker
 */
public abstract class AbstractPageControlHelper {
	public AbstractPageControlHelper() {
	}

	/**
	 * ����getPagerData��getRowCount������
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
	 * ��ȡ����ʾ������
	 * @param request
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param start ��ʼ��¼
	 * @param count ����ȡ�ļ�¼��
	 * @return ��ѯ���-ֵ���󼯺�
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
	 * ��ȡ��¼����,����ҳ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��¼����
	 * @exception
	 */
	public abstract long getRowCount(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception;
}
