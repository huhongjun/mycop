package com.gever.sysman.organization.dao;

import com.gever.exception.db.DAOException;

import java.util.Collection;
/*
黎彪增加部门排序DAO接口；2004-11-24
*/

public interface DepOrderDAO {
  /**
   * 没有判断父类ID，在调用这个方法时应该判断父类ID交换id1,id2的值
   * @param ID
   * @return
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40BA8E22005D
   */

  public boolean exchangeResID(int id1,int id2) throws DAOException;
  /**
  * 把primaryid的值移到aimid的位置
  * @param ID
  * @return
  * @throws com.gever.exception.db.DAOException
  * @roseuid 40BA8E22005D
  */
public boolean moveResID(int id1,int id2) throws DAOException;
 /**
 * 把primaryid和aimid的位置交换
 * @param ID
 * @return
 * @throws com.gever.exception.db.DAOException
 * @roseuid 40BA8E22005D
 */
public boolean exchangePID(int id1,int id2) throws DAOException;
/**
* 把把资源操作primaryid和aimid的位置交换
* @param ID
* @return
* @throws com.gever.db.DAOException
* @roseuid 40BA8E22005D
*/
//public boolean exchangeOptionID(int id1,int id2) throws DAOException;
/**
* 把把资源操作同及primaryid和aimid的位置交换
* @param ID
* @return
* @throws com.gever.exception.db.DAOException
* @roseuid 40BA8E22005D
*/
//public boolean moveOptionID(int id1,int id2) throws Exception;
 public Collection getDepartmentId(int resid) throws Exception;
 public boolean moveDepartmentOrderId(int id1,int id2) throws Exception ;
 public void initOrderID(int parentid) throws Exception;
}
