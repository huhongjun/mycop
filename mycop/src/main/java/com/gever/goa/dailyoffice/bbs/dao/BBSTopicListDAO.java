package com.gever.goa.dailyoffice.bbs.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

public interface BBSTopicListDAO {
    public String getPageSql();

    public int insert(VOInterface vo) throws DefaultException;

    public int update(VOInterface vo) throws DefaultException;

    public List queryAll(VOInterface vo) throws DefaultException;

    public List queryByPage(VOInterface vo, long start, long howMany) throws
            DefaultException;

    public long queryByCount(VOInterface vo) throws DefaultException;

    public int copy(String[] keyValue, VOInterface vo) throws DefaultException;

}
