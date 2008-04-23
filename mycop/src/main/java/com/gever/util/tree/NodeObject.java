/*
 * 创建日期 2003-10-10
 */
package com.gever.util.tree;

/**
 * 节点对象,用于GeverTreeNode
 * @author Hu.Walker
 */
public class NodeObject {
	private String className;
	private long id;
	private String name;
	
	public NodeObject(String className,long id,String name) {
		this.className = className;
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	public String toString() {
		return new StringBuffer()
					.append("[NodeObject className=").append(className)
					.append(" id=").append(id)
					.append(" name=").append(name)
					.append("]").toString();
	}
}
