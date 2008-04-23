/*
 * 创建日期 2003-10-10
 */
package com.gever.util.tree;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import com.gever.util.queue.Queue;


/**
 * 树节点,树节点分次展开
 * @author Hu.Walker
 */
public abstract class GeverTreeNode
	extends javax.swing.tree.DefaultMutableTreeNode {

	/**
	 * 节点是否展开
	 */
	private boolean explored = false;

	/**
	 * 是否展开
	 * @return
	 */
	public boolean isExplored() {
		return explored;
	}

	/**
	 * 设置是否展开
	 * @param b
	 */
	public void setExplored(boolean b) {
		explored = b;
	}

	/**
	 * 展开此节点,可能需要从数据库读取此节点的孩子
	 */
	public abstract void explore();

	/**
	 * 从此节点根节点的节点集合
	 * @return
	 */
	protected abstract List getNodePath();
	
	/**
	 * 判断节点集合中是否包含某节点
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
	 * 在以root为根节点的树中,展开某节点
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
