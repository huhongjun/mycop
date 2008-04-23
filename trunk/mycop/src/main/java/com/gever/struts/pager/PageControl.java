/*
 * �������� 2004-4-24
 *
 */
package com.gever.struts.pager;

import java.util.Collection;

/**
 * ������ҳ��������
 */
public class PageControl{
	private long currentPage = 1;
	private long maxRowCount;
	private int rowsPerPage = 20;
	private int indexSize = 5;
	
	private Collection data;
	
	/**
	 * ��ȡ���ҳ��
	 * @return
	 */
	public long getMaxPage() {
		return maxRowCount % rowsPerPage == 0 ? maxRowCount / rowsPerPage : maxRowCount / rowsPerPage + 1;
	}
	
	/**
	 * ��ȡ��ǰҳ
	 * @return
	 */
	public long getCurrentPage() {
		return currentPage;
	}

	/**
	 * ��ȡ����
	 * @return
	 */
	public Collection getData() {
		return data;
	}

	/**
	 * ��ȡ��¼����
	 * @return
	 */
	public long getMaxRowCount() {
		return maxRowCount;
	}

	/**
	 * ��ȡÿҳ��ʾ�ļ�¼��
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
