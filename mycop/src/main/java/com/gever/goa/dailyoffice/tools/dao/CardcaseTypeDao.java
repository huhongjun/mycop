package com.gever.goa.dailyoffice.tools.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

public interface CardcaseTypeDao {
    public int delBySelect(String[] aryPk, VOInterface vo) throws
            DefaultException;

    public String getPageSql();

    public int insert(VOInterface vo) throws DefaultException;

    public int update(VOInterface vo) throws DefaultException;

    public List queryAll(VOInterface vo) throws DefaultException;

    public List queryByPage(VOInterface vo, long start, long howMany) throws
            DefaultException;

    public long queryByCount(VOInterface vo) throws DefaultException;

    public Object queryByPK(VOInterface vo) throws DefaultException;

    public List queryByUser(VOInterface vo) throws DefaultException;

    public List getTreeData() throws DefaultException;

    public boolean existTypeName(String typeName, String userID) throws
            DefaultException;
}
