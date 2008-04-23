package com.gever.goa.dailyoffice.reportmgr.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: 目标管理DAO </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface TargetDao {
     public List getTreeData(String nodedid) throws DefaultException; //获取树型结构列表
     public int update(VOInterface vo)throws DefaultException;
}
