//Source file: E:\\lichm\\MyWorks\\GDP\\工作区\\GDP工程\\Sysman\\src\\com\\gever\\sysman\\privilege\\dao\\ResourceDAO.java

package com.gever.sysman.privilege.dao;

import java.util.Collection;

import com.gever.sysman.privilege.model.Resource;
import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.model.Operation;

/**
 * 资源Resource操作接口
 */
public interface ResourceDAO
{

   /**
    * 根据id获取Resource
    * @param ID
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E22005D
    */
   public Resource findResourceByID(long ID) throws DAOException;

   /**
    * 根据name获取Resource
    * @param name
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E2200BC
    */
   public Resource findResourceByName(String name) throws DAOException;

   /**
    * 创建Resource
    * @param resource
    * @throws DAOException
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E22011A
    */
   public void createResource(Resource resource) throws DAOException;

   /**
    * 删除Resource
    * @param resource
    * @throws Exception
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E220186
    */
   public void deleteResource(Resource resource) throws DAOException;

   /**
    * 更新Resource
    * @param resource
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E2203D8
    */
   public void updateResource(Resource resource) throws DAOException;

   /**
    * 获取所有的Resource
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E2201E5
    */
   public Collection getResources() throws DAOException;
   /**
    * 获取资源并添加判断是否为资源项是否为叶子节点
    * @return
    * @throws DAOException
    */
   public Collection getResourcesTree() throws DAOException ;

   /**
    * 分页获取Resource
    * @param start
    * @param count
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E220251
    */
   public Collection getResources(int start, int count) throws DAOException;
   public Collection getChileResources(String resid,int start, int count) throws DAOException;

   /**
    * 查询Resource
    * @param searchQuery
    * @param start
    * @param count
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E2202B1
    */
   public Collection findResource(String searchQuery, int start, int count) throws DAOException;

   /**
    * 获取Resource总数
    * @return
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E22038A
    */
   public int getResourceCount() throws DAOException;
   public int getChildResourceCount(String resid) throws DAOException;
   /**
    * 获取某资源所拥有的操作
    * @param resource
    * @return
    * @throws DAOException
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E230148
    */
   public Collection getOperations(Resource resource) throws DAOException;


   /**
    * 删除操作
    * @param resource
    * @param privilege
    * @throws DAOException
    * @throws com.gever.exception.db.DAOException
    * @roseuid 40BA8E230242
    */
   public void removeOperation(Resource resource, Operation operation) throws DAOException;

    /**
     * 获取Root Resources
     * @return
     */
	public Resource getRootResource() throws DAOException;

    /**
     * 获取孩子资源
     * @param resource
     * @return
     * @throws DAOException
     */
	public Collection getChildren(Resource resource) throws DAOException;

	public Collection getChilds(String id) throws DAOException;

    public Collection getResourcesAndOperations() throws DAOException;

    public Collection findResourceByIDs(long[] ids) throws DAOException;
    //===============================================================
    //胡勇添加，增加JSP视图列表排序功能
    public void setOrderby(String[] s);
    //===============================================================
}
