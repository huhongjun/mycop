package com.zhjedu.exam.service.impl;

import java.io.Serializable;

public class MenuItem implements Serializable, Comparable{
	private static final long serialVersionUID = 1L;
    private String menuNo;
    private String parentNo;
    private String name;
    private String url;
    private String title;
    private String target;
    private String icon;
    private String iconOpen;
    private boolean open;


    public String getName()
    {
        return name;
    }

    public String getUrl()
    {
        return url;
    }

    public String getTarget()
    {
        return target;
    }

    public String getIcon()
    {
        return icon;
    }

    public String getTitle()
    {
        return title;
    }

    public String getIconOpen()
    {
        return iconOpen;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setIconOpen(String iconOpen)
    {
        this.iconOpen = iconOpen;
    }

    public boolean isOpen()
    {
        return open;
    }

    public MenuItem()
    {
    }

    public String toString()
    {
        StringBuffer strBuf = new StringBuffer(64);
        strBuf.append(" menuNo = ").append(menuNo);
        strBuf.append(" parentNo = ").append(parentNo);
        strBuf.append(" name = ").append(name);
        strBuf.append(" url = ").append(url);
        strBuf.append(" title = ").append(title);
        strBuf.append(" target = ").append(target);
        strBuf.append(" icon = ").append(icon);
        strBuf.append(" iconOpen = ").append(iconOpen);
        strBuf.append(" open = ").append(open).append("\n");
        return strBuf.toString();
    }

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}

}
