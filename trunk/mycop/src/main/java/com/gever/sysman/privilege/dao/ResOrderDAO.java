package com.gever.sysman.privilege.dao;
/**
 * ��Դ����Ľӿ�
 * author ���
 *  time:2004-11-17
 */

import com.gever.exception.db.DAOException;

import java.util.Collection;
public interface ResOrderDAO {
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
* @throws com.gever.exception.db.DAOException
* @roseuid 40BA8E22005D
*/
public boolean exchangeOptionID(int id1,int id2) throws DAOException;
/**
* �Ѱ���Դ����ͬ��primaryid��aimid��λ�ý���
* @param ID
* @return
* @throws com.gever.exception.db.DAOException
* @roseuid 40BA8E22005D
*/
public boolean moveOptionID(int id1,int id2) throws Exception;

/**
 * ��ʼ����ԴORDERID
 * @param parentid int
 * @throws Exception
 */
public void initOrderID(int parentid) throws Exception;
/**
 * ��ʼ����Դ����ORDERID
 * @param parentid int
 * @throws Exception
 */

  public void initOperationOrderID(int parentid) throws Exception;
  /**
  * �õ���Դ��������ID
  * @param parentid int
  * @throws Exception
  */
   public Collection getOptionResOrderId(int resid) throws Exception;
   /**
* �õ���ԴID
* @param parentid int
* @throws Exception
*/

    public Collection getResourceId(int resid) throws Exception;
//#########################################���Ͻӿ��ڴ�����ʵ���˵�û�б�ʹ�ã����Ĺ�������Դ�ṹ�����Ĵ���
    /**
* �õ���Դ����ORDERID�ĵ���
* @param parentid int
* @throws Exception
*/
 public boolean moveResourceOrderId(int id1,int id2) throws Exception;
}
