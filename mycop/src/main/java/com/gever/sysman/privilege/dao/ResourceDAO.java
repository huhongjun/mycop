//Source file: E:\\lichm\\MyWorks\\GDP\\������\\GDP����\\Sysman\\src\\com\\gever\\sysman\\privilege\\dao\\ResourceDAO.java

package com.gever.sysman.privilege.dao;

import java.util.Collection;

import com.gever.sysman.privilege.model.Resource;
import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.model.Operation;

/**
 * ��ԴResource�����ӿ�
 */
public interface ResourceDAO
{

   /**
    * ����id��ȡResource
    * @param ID
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E22005D
    */
   public Resource findResourceByID(long ID) throws DAOException;

   /**
    * ����name��ȡResource
    * @param name
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E2200BC
    */
   public Resource findResourceByName(String name) throws DAOException;

   /**
    * ����Resource
    * @param resource
    * @throws DAOException
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E22011A
    */
   public void createResource(Resource resource) throws DAOException;

   /**
    * ɾ��Resource
    * @param resource
    * @throws Exception
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E220186
    */
   public void deleteResource(Resource resource) throws DAOException;

   /**
    * ����Resource
    * @param resource
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E2203D8
    */
   public void updateResource(Resource resource) throws DAOException;

   /**
    * ��ȡ���е�Resource
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E2201E5
    */
   public Collection getResources() throws DAOException;
   /**
    * ��ȡ��Դ������ж��Ƿ�Ϊ��Դ���Ƿ�ΪҶ�ӽڵ�
    * @return
    * @throws DAOException
    */
   public Collection getResourcesTree() throws DAOException ;

   /**
    * ��ҳ��ȡResource
    * @param start
    * @param count
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E220251
    */
   public Collection getResources(int start, int count) throws DAOException;
   public Collection getChileResources(String resid,int start, int count) throws DAOException;

   /**
    * ��ѯResource
    * @param searchQuery
    * @param start
    * @param count
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E2202B1
    */
   public Collection findResource(String searchQuery, int start, int count) throws DAOException;

   /**
    * ��ȡResource����
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E22038A
    */
   public int getResourceCount() throws DAOException;
   public int getChildResourceCount(String resid) throws DAOException;
   /**
    * ��ȡĳ��Դ��ӵ�еĲ���
    * @param resource
    * @return
    * @throws DAOException
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E230148
    */
   public Collection getOperations(Resource resource) throws DAOException;


   /**
    * ɾ������
    * @param resource
    * @param privilege
    * @throws DAOException
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E230242
    */
   public void removeOperation(Resource resource, Operation operation) throws DAOException;

    /**
     * ��ȡRoot Resources
     * @return
     */
	public Resource getRootResource() throws DAOException;

    /**
     * ��ȡ������Դ
     * @param resource
     * @return
     * @throws DAOException
     */
	public Collection getChildren(Resource resource) throws DAOException;

	public Collection getChilds(String id) throws DAOException;

    public Collection getResourcesAndOperations() throws DAOException;

    public Collection findResourceByIDs(long[] ids) throws DAOException;
    //===============================================================
    //������ӣ�����JSP��ͼ�б�������
    public void setOrderby(String[] s);
    //===============================================================
}
