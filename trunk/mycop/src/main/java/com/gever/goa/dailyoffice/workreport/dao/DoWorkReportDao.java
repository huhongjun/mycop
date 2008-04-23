package com.gever.goa.dailyoffice.workreport.dao;

/**
 * <p>Title:天行办公自动化平台 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

public interface DoWorkReportDao {
  public int delBySelect(String[] aryPk, VOInterface vo) throws
      DefaultException;

  public String getPageSql() ;

  public int insert(VOInterface vo) throws DefaultException;

  public int update(VOInterface vo) throws DefaultException;

  public List queryAll(VOInterface vo) throws DefaultException;

  public List queryByPage(VOInterface vo, long start, long howMany) throws
      DefaultException;

  public long queryByCount(VOInterface vo) throws DefaultException;

  public int copy(String[] keyValue, VOInterface vo) throws DefaultException;

  public List getTreeData() throws DefaultException;
}
