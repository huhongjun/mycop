/*
 * �������� 2004-6-25
 *
 */
package com.gever.sysman.privilege.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.model.Resource;

/**
 * @author Hu.Walker
 *  
 */
public interface PermissionDAO {
    /**
     * ���ݲ������Ϻ͵�ǰ��ɫ�Ĳ���Ȩ�ޣ����ò������ϵ�һ�����ԣ��Ƿ��в���Ȩ�ޣ�
     * 
     * @param opt_collect
     *            ���в����ļ���
     * @param role_res_opt_collect
     *            ��ɫȨ�޼���
     * @return
     */
    public abstract Collection returnOpts(Collection opt_collect,
            Collection role_res_opt_collect);

    /**
     * ����������Ӧ����Ӧ����Դ����
     * 
     * @param res_collect
     * @param opt_isOpt_collect
     * @return
     */
    public abstract Collection returnRess(Collection res_collect,
            Collection opt_isOpt_collect);

    public abstract Collection getChildNodes(Collection node_collect,
            long node_id);

    public abstract Resource getParentNodes(Collection root, long node_pid);

    public abstract Resource getRoot(Collection root);

    /**
     * ������Դ�б������Ȩ���б�˵�
     * 
     * @param res_collect
     * @return
     */
    public abstract String process(Collection res_collect);

    public String user_process(Collection res_collect);

    /**
     * �ݹ������Դ�����б�
     */
    public abstract void resTree(Collection res_collect,
            Collection node_child_collect, Resource res_node,
            StringBuffer result);

    public abstract String space(int i);

    public abstract void savaRolePerm(String roleid,
            Collection role_perm_collect) throws DAOException, SQLException;

    public void savaUserPerm(String userid, Collection user_perm_collect)
            throws DAOException, SQLException;

    public abstract Collection getUserPerm(String userid, String[] res_opt);

    public abstract Collection getRolePerm(String roleid, String[] res_opt);

    /**
     * ��ȡ�û�Ȩ�޺ͽ�ɫȨ�޺Ͳ���ļ���
     * 
     * @param userID
     * @param userOpts
     * @param roleOpts
     * @return
     */
    public abstract Collection getUserAndRolePerm(long userID,
            Collection userOpts, Collection roleOpts);

    /**
     * �����û��ĵ�Ȩ�ޣ�������ɫ�ģ���������Դ������һ�����ԣ��Ƿ��в���Ȩ�ޣ�
     * 
     * @param opt_collect
     *            ���в����ļ���
     * @param user_res_opt_collect
     *            �û�Ȩ�޼���
     * @return
     */
    public abstract Collection returnUserOpts(Collection opt_collect,
            Collection user_res_opt_collect);

    public void resetMenuRes(String userid, Collection userPerm)
            throws DAOException;

    public Collection getResCollect(Collection userPerm) throws DAOException;

    public java.util.Set getAllResources(Collection resources)
            throws DAOException;

    public String[] getUserPerm(long userID) throws DAOException, SQLException;

    public Collection getResCollectByRole(Collection rolePerm)
            throws DAOException;
    
    /**
     * ɾ���û�ʱɾ���û�Ȩ�޹�������
     * @author Hu.Walker
     */
    public void delUserPerm(String[] userId) throws DAOException, SQLException;
}