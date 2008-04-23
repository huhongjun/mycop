package com.gever.sysman.organization.dao;

import com.gever.exception.db.DAOException;

import java.util.Collection;
/*
������Ӳ�������DAO�ӿڣ�2004-11-24
*/

public interface DepOrderDAO {
  /**
   * û���жϸ���ID���ڵ����������ʱӦ���жϸ���ID����id1,id2��ֵ
   * @param ID
   * @return
   * @throws com.gever.exception.db.DAOException
   * @roseuid 40BA8E22005D
   */

  public boolean exchangeResID(int id1,int id2) throws DAOException;
  /**
  * ��primaryid��ֵ�Ƶ�aimid��λ��
  * @param ID
  * @return
  * @throws com.gever.exception.db.DAOException
  * @roseuid 40BA8E22005D
  */
public boolean moveResID(int id1,int id2) throws DAOException;
 /**
 * ��primaryid��aimid��λ�ý���
 * @param ID
 * @return
 * @throws com.gever.exception.db.DAOException
 * @roseuid 40BA8E22005D
 */
public boolean exchangePID(int id1,int id2) throws DAOException;
/**
* �Ѱ���Դ����primaryid��aimid��λ�ý���
* @param ID
* @return
* @throws com.gever.db.DAOException
* @roseuid 40BA8E22005D
*/
//public boolean exchangeOptionID(int id1,int id2) throws DAOException;
/**
* �Ѱ���Դ����ͬ��primaryid��aimid��λ�ý���
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
