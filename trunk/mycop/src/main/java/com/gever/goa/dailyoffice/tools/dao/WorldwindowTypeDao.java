package com.gever.goa.dailyoffice.tools.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;
/**世界之窗模块
 * <p>Title: </p>
 * <p>Description: GOA</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface WorldwindowTypeDao {
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

  public int copy(String[] keyValue, VOInterface vo) throws DefaultException;

  public List getTreeData()throws DefaultException;
}
