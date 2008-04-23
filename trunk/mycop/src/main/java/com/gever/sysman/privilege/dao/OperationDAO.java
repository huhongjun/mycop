package com.gever.sysman.privilege.dao;

import com.gever.exception.db.DAOException;

import com.gever.sysman.privilege.model.Operation;
import com.gever.sysman.privilege.model.Resource;

import java.util.Collection;


/**
Operation�����ӿ�
 */
public interface OperationDAO {
    /**
    ����id��ȡOperation
    @param id
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA558036B
     */
    public Operation findOperationByID(long id) throws DAOException;
    public Operation findOperationByCode(String code) throws DAOException;

    public Collection getOptByRoleid(long roleid) throws DAOException;

	public Collection getOptByUserid(long userid) throws DAOException;

	public Collection getOperationsByResid(String id) throws DAOException;
	/**
	 * ��ȡ�û��Ľ�ɫȨ�޼��ϣ�һ���û����ܰ��������ɫ��
	 * @param uid
	 * @return
	 * @throws DAOException
	 */
	public Collection getUserRolePerm(String uid) throws DAOException;

    /**
    ���ݲ�������ȡ��Դ�Ĳ���
    @param resource
    @param name
    @return
    ���²���
    @param operation
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55803C9
     */
    public void updateOperation(Operation operation) throws DAOException;

    /**
    ɾ������
    @param operation
    @throws Exception
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA559003F
     */
    public void deleteOperation(Operation operation) throws DAOException;

	public void deleteOperation(String[] ids) throws DAOException;


    /**
    ����Operation
    @param operation
    @throws Exception
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55900AB
     */
    public void createOperation(Operation operation) throws DAOException;

    /**
    ��ȡָ����Դ��Operation
    @param resource
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA559010A
     */
    public Collection getOperations(Resource resource)
        throws DAOException;

    /**
    ��ȡ���е�Operation
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA5590196
     */
    public Collection getOperations() throws DAOException;

    /**
    ��ҳ��ȡOperation
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55901F4
     */
    public Collection getOperations(int start, int count)
        throws DAOException;

    /**
    ��ѯOperation
    @param searchQuery
    @param start
    @param count
    @return
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA5590263
     */
    public Collection findOperations(String searchQuery, int start, int count)
        throws DAOException;

    /**
    ��ȡ��������
    @return
    @throws DAOException
    @throws com.gever.exception.db.DAOException
    @roseuid 40BAA55902DE
     */
    public int getOperationCount() throws DAOException;
    
    //===============================================================
    //������ӣ�����JSP��ͼ�б�������
    public void setOrderby(String[] s) ;
    //===============================================================
}
