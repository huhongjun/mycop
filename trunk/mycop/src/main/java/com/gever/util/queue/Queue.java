/*
 * 创建日期 2003-10-10
 */
package com.gever.util.queue;

import com.gever.util.tree.EmptyQueueException;


/**
 * 队列
 * @author Hu.Walker
 */
public class Queue extends java.util.Vector {
	public Queue() {
		super();
	}
	
	/**
	 * 入
	 * @param obj
	 */
	public synchronized void enqueue(Object obj) {
		super.addElement(obj);
	}
	
	/**
	 * 出
	 * @return
	 */
	public synchronized Object dequeue() {
		/* 队列若为空，引发EmptyQueueException异常 */
		if (this.empty())
			throw new EmptyQueueException();
			
		Object x = super.elementAt(0);
		super.removeElementAt(0);
		return x;
	}
	
	/**
	 * 返回第一个元素
	 * @return
	 */
	public synchronized Object front() {
		if (this.empty())
			throw new EmptyQueueException();
			
		return super.elementAt(0);
	}
	
	/**
	 * 判断队列是否为空
	 * @return
	 */
	public boolean empty() {
		return super.isEmpty();
	}
	
	/**
	 * 清除队列
	 */
	public synchronized void clear() {
		super.removeAllElements();
	}
	
	/**
	 * 查找
	 * @param obj
	 * @return
	 */
	public int search(Object obj) {
		return super.indexOf(obj);
	}
}
