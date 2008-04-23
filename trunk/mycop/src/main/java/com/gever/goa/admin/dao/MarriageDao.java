package com.gever.goa.admin.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * <p>Title: »éÒödao</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface MarriageDao {
    public int delBySelect(String[] aryPk, VOInterface vo)throws DefaultException;
    public String getPageSql();
    public int insert(VOInterface vo)throws DefaultException;
    public int update(VOInterface vo) throws DefaultException;
    public List queryAll(VOInterface vo)throws DefaultException;
    public List queryByPage(VOInterface vo, long start, long howMany)throws DefaultException;
    public long queryByCount(VOInterface vo)throws DefaultException;
    public int copy(String[] keyValue,VOInterface vo)throws DefaultException;
}
