/*
 * �������� 2003-10-10
 */
package com.gever.util.queue;

import com.gever.util.tree.EmptyQueueException;


/**
 * ����
 * @author Hu.Walker
 */
public class Queue extends java.util.Vector {
	public Queue() {
		super();
	}
	
	/**
	 * ��
	 * @param obj
	 */
	public synchronized void enqueue(Object obj) {
		super.addElement(obj);
	}
	
	/**
	 * ��
	 * @return
	 */
	public synchronized Object dequeue() {
		/* ������Ϊ�գ�����EmptyQueueException�쳣 */
		if (this.empty())
			throw new EmptyQueueException();
			
		Object x = super.elementAt(0);
		super.removeElementAt(0);
		return x;
	}
	
	/**
	 * ���ص�һ��Ԫ��
	 * @return
	 */
	public synchronized Object front() {
		if (this.empty())
			throw new EmptyQueueException();
			
		return super.elementAt(0);
	}
	
	/**
	 * �ж϶����Ƿ�Ϊ��
	 * @return
	 */
	public boolean empty() {
		return super.isEmpty();
	}
	
	/**
	 * �������
	 */
	public synchronized void clear() {
		super.removeAllElements();
	}
	
	/**
	 * ����
	 * @param obj
	 * @return
	 */
	public int search(Object obj) {
		return super.indexOf(obj);
	}
}
