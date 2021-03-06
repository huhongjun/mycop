package com.gever.goa.dailyoffice.targetmng.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * Title: 月度目标Dao接口
 * Description: 月度目标Dao接口
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
 * @author Hu.Walker
 * @version 1.0
 */

public interface MonthTargetDao {
  public int delBySelect(String[] aryPk, VOInterface vo) throws
      DefaultException; //删除

  public String getPageSql(); //翻页

  public int insert(VOInterface vo) throws DefaultException; //新增

  public int update(VOInterface vo) throws DefaultException; //修改

  public List queryAll(VOInterface vo) throws DefaultException; //查询所有

  public List queryByPage(VOInterface vo, long start, long howMany) throws
      DefaultException; //按页查询

  public long queryByCount(VOInterface vo) throws DefaultException; //按数查询

  public int copy(String[] keyValue, VOInterface vo) throws DefaultException; //复制
  public List getTreeData(String nodedid)throws DefaultException;//获取树型结构列表
  public List getTreeData(String nodedid,String curDeptId) throws DefaultException; //获取树型结构列表
}
