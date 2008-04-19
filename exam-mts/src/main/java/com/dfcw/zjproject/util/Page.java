/**  
 * File name : Page.java  
 * Write date : 2002-5-29  
 * Writer : alexander  
 * Copyright 2002 cwap.com, Inc. All rights reserved.  
 */
package com.dfcw.zjproject.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.Serializable;
/**
 * 描述显示结果集的页面
 */
public class Page implements Serializable {
	/**
	 * 开始记录号，从0开始
	 */
	public int start;
	/**
	 * 结束记录号
	 */
	public int end;
	/**
	 * 显示在页面开始记录号，从1开始
	 */
	public int viewStart;
	/**
	 * 显示在页面结束记录号
	 */
	public int viewEnd;
	/**
	 * 是否有上一页的开关
	 */
	public boolean hasPrevious;
	/**
	 * 上一页的页码
	 */
	public int previousPageNumber;
	/**
	 * 是否有下一页的开关
	 */
	public boolean hasNext;
	/**
	 * 下一页的页码
	 */
	public int nextPageNumber;
	/**
	 * 一共有多少行记录
	 */
	public int total;
	/**
	 * 一共有多少页
	 */
	public int totalPage;
	/**
	 * 当前是第几页
	 */
	public int currentPageNumber;
	/**
	 * 每页有多少行
	 */
	public int pageSize;
	/**
	 * 构造器,创建页面
	 * 
	 * @param: l 结果集
	 * @param: num 当前是第几页
	 * @param: size 每页有多少行
	 * @param: total 一共有多少行记录
	 */
	public Page(int num, int size, int total) {
		this.currentPageNumber = num;
		this.pageSize = size;
		this.total = total;
		if (total == 0)
			this.currentPageNumber = 0;
		else
			autoCalculate();
	}
	/**
	 * 自动计算，根据当前页、页大小、总行数计算出其它属性的值
	 */
	private void autoCalculate() {
		start = (currentPageNumber - 1) * pageSize;
		end = start + pageSize;
		if (end > total) {
			end = total;
		}
		viewStart = start + 1;
		viewEnd = end + 1;
		totalPage = (total + pageSize - 1) / pageSize;
		if (currentPageNumber == 1) {
			hasPrevious = false;
			previousPageNumber = 1;
		} else {
			hasPrevious = true;
			previousPageNumber = currentPageNumber - 1;
		}
		if (currentPageNumber == totalPage) {
			hasNext = false;
			nextPageNumber = totalPage;
		} else {
			hasNext = true;
			nextPageNumber = currentPageNumber + 1;
		}
	}
	/**
	 * 获得显示在页面的开始记录号，从1开始
	 * 
	 * @return: int 显示在页面的开始记录号
	 */
	public int getStart() {
		return start;
	}
	/**
	 * 获得显示在页面的结束记录号
	 * 
	 * @return: int 显示在页面的结束记录号
	 */
	public int getEnd() {
		return end;
	}
	/**
	 * 获得显示在页面的开始记录号，从1开始
	 * 
	 * @return: int 显示在页面的开始记录号
	 */
	public int getViewStart() {
		return viewStart;
	}
	/**
	 * 获得显示在页面的结束记录号
	 * 
	 * @return: int 显示在页面的结束记录号
	 */
	public int getViewEnd() {
		return viewEnd;
	}
	/**
	 * 是否有下一页
	 * 
	 * @return: boolean 是否有下一页的开关
	 */
	public boolean hasNextPage() {
		return hasNext;
	}
	/**
	 * 是否有上一页
	 * 
	 * @return: boolean 是否有上一页的开关
	 */
	public boolean hasPreviousPage() {
		return start > 0;
	}
	/**
	 * 获得上一页的页码
	 * 
	 * @return: int 上一页的页码
	 */
	public int getPreviousPageNumber() {
		return previousPageNumber;
	}
	/**
	 * 获得下一页的页码
	 * 
	 * @return: int 下一页的页码
	 */
	public int getNextPageNumber() {
		return nextPageNumber;
	}
	/**
	 * 获得结果集中记录总行数
	 * 
	 * @return: int 一共有多少行记录
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * 获得总页数
	 * 
	 * @return: int 一共有多少页
	 */
	public int getTotalPage() {
		return totalPage;
	}
	/**
	 * 获得当前页码
	 * 
	 * @return: int 当前页码
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	/**
	 * 获得每页多少行记录
	 * 
	 * @return: int 页大小
	 */
	public int getPageSize() {
		return pageSize;
	}
}
