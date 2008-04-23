/*
 * �������� 2003-10-10
 */
package com.gever.util.tree;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.gever.util.queue.Queue;


/**
 * ���ڵ�,���ڵ�ִ�չ��
 * @author Hu.Walker
 */
public abstract class GeverTreeNode
	extends javax.swing.tree.DefaultMutableTreeNode {

	/**
	 * �ڵ��Ƿ�չ��
	 */
	private boolean explored = false;

	/**
	 * �Ƿ�չ��
	 * @return
	 */
	public boolean isExplored() {
		return explored;
	}

	/**
	 * �����Ƿ�չ��
	 * @param b
	 */
	public void setExplored(boolean b) {
		explored = b;
	}

	/**
	 * չ���˽ڵ�,������Ҫ�����ݿ��ȡ�˽ڵ�ĺ���
	 */
	public abstract void explore();

	/**
	 * �Ӵ˽ڵ���ڵ�Ľڵ㼯��
	 * @return
	 */
	protected abstract List getNodePath();
	
	/**
	 * �жϽڵ㼯�����Ƿ����ĳ�ڵ�
	 * @param nodes
	 * @param node
	 * @return
	 */
	private static boolean contain(List nodes,GeverTreeNode node) {
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			GeverTreeNode element = (GeverTreeNode)it.next();
			if (element.equals(node)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ����rootΪ���ڵ������,չ��ĳ�ڵ�
	 * @param root
	 * @param node
	 * @return
	 */
	public static GeverTreeNode explore(
		GeverTreeNode root,
		GeverTreeNode node) {

		List nodes = node.getNodePath();
		
		Queue q = new com.gever.util.queue.Queue();
		q.enqueue(root);


		while (!q.empty()) {
			GeverTreeNode temp = (GeverTreeNode)q.dequeue();
			if (contain(nodes,temp)) {
				temp.explore();
				if (temp.equals(node)) {
					node.setExplored(true);
				}
			}

			for (Enumeration enum1 = temp.children(); enum1.hasMoreElements();) {
				GeverTreeNode n = (GeverTreeNode)enum1.nextElement();
				q.enqueue(n);
			}
		}
		return root;
	}
	/**
	 * @return
	 */
	public abstract NodeObject getNodeObject();

	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof GeverTreeNode)) {
			return false;
		}

		GeverTreeNode temp = (GeverTreeNode)obj;

		if (temp.getNodeObject().getId() == this.getNodeObject().getId()) {
			return true;
		}
		return false;
	}

}
