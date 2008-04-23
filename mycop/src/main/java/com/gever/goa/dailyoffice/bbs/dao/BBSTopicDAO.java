package com.gever.goa.dailyoffice.bbs.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;


public interface BBSTopicDAO {
    public String getPageSql();

    public int insert(VOInterface vo) throws DefaultException;

    public int update(VOInterface vo) throws DefaultException;

    public List queryAll(VOInterface vo) throws DefaultException;

    //public List queryByCustomer(VOInterface vo)throws DefaultException;

   // public List queryByType(VOInterface vo)throws DefaultException;
    public VOInterface queryByPK(VOInterface vo) throws DefaultException;

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException;

    public long queryByCount(VOInterface vo) throws DefaultException;

    public int copy(String[] keyValue, VOInterface vo) throws DefaultException;

    public int addHitTimes(VOInterface vo) throws DefaultException;

    public int updateTopicUser(VOInterface vo)throws DefaultException;
}
