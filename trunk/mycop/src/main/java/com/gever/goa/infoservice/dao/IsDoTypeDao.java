package com.gever.goa.infoservice.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * <p>Title: 信息类型设置DAO接口类</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface IsDoTypeDao {

    public int delBySelect(String[] aryPk, VOInterface vo)
            throws DefaultException;

    public String getPageSql();

    public int insert(VOInterface vo) throws DefaultException;

    public int update(VOInterface vo) throws DefaultException;

    public List queryAll(VOInterface vo) throws DefaultException;

    public List queryByModuleFlag(String paraFlag, VOInterface vo)
            throws DefaultException;
	public String getRemark(String info_type)
			throws DefaultException;

    public List queryByPage(VOInterface vo, long start, long howMany)
            throws DefaultException;

    public long queryByCount(VOInterface vo) throws DefaultException;

    public int copy(String[] keyValue, VOInterface vo) throws DefaultException;

    public void setSqlWhere(String sqlWhere) throws DefaultException;

    public List getTreeData(String paraFlag, String nodeid)
            throws DefaultException;
}