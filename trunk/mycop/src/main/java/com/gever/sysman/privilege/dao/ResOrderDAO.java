package com.gever.sysman.privilege.dao;
/**
 * 资源排序的接口
 * author 黎彪
 *  time:2004-11-17
 */

import com.gever.exception.db.DAOException;

import java.util.Collection;
public interface ResOrderDAO {
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
* @throws com.gever.exception.db.DAOException
* @roseuid 40BA8E22005D
*/
public boolean exchangeOptionID(int id1,int id2) throws DAOException;
/**
* 把把资源操作同及primaryid和aimid的位置交换
* @param ID
* @return
* @throws com.gever.exception.db.DAOException
* @roseuid 40BA8E22005D
*/
public boolean moveOptionID(int id1,int id2) throws Exception;

/**
 * 初始化资源ORDERID
 * @param parentid int
 * @throws Exception
 */
public void initOrderID(int parentid) throws Exception;
/**
 * 初始化资源操作ORDERID
 * @param parentid int
 * @throws Exception
 */

  public void initOperationOrderID(int parentid) throws Exception;
  /**
  * 得到资源操作排序ID
  * @param parentid int
  * @throws Exception
  */
   public Collection getOptionResOrderId(int resid) throws Exception;
   /**
* 得到资源ID
* @param parentid int
* @throws Exception
*/

    public Collection getResourceId(int resid) throws Exception;
//#########################################以上接口在代码中实现了但没有被使用，他的功能是资源结构调整的代码
    /**
* 得到资源排序ORDERID的调整
* @param parentid int
* @throws Exception
*/
 public boolean moveResourceOrderId(int id1,int id2) throws Exception;
}
