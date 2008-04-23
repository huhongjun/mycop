/*
 * 创建日期 2004-4-24
 *
 */
package com.gever.struts.pager;

import java.util.Collection;

/**
 * 包含分页必须数据
 */
public class PageControl{
	private long currentPage = 1;
	private long maxRowCount;
	private int rowsPerPage = 20;
	private int indexSize = 5;
	
	private Collection data;
	
	/**
	 * 获取最大页数
	 * @return
	 */
	public long getMaxPage() {
		return maxRowCount % rowsPerPage == 0 ? maxRowCount / rowsPerPage : maxRowCount / rowsPerPage + 1;
	}
	
	/**
	 * 获取当前页
	 * @return
	 */
	public long getCurrentPage() {
		return currentPage;
	}

	/**
	 * 获取数据
	 * @return
	 */
	public Collection getData() {
		return data;
	}

	/**
	 * 获取记录总数
	 * @return
	 */
	public long getMaxRowCount() {
		return maxRowCount;
	}

	/**
	 * 获取每页显示的记录数
	 * @return
	 */
	public int getRowsPerPage() {
		return rowsPerPage;
	}

	/**
	 * @param i
	 */
	public void setCurrentPage(long i) {
		currentPage = i;
	}

	/**
	 * @param collection
	 */
	public void setData(Collection collection) {
		data = collection;
	}

	/**
	 * @param i
	 */
	public void setMaxRowCount(long i) {
		maxRowCount = i;
	}

	/**
	 * @param i
	 */
	public void setRowsPerPage(int i) {
		rowsPerPage = i;
	}

	/**
	 * @return
	 */
	public int getIndexSize() {
		return indexSize;
	}

	/**
	 * @param i
	 */
	public void setIndexSize(int i) {
		indexSize = i;
	}

}
