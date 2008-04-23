package com.gever.web.menusetup.dao;

import java.util.*;

import com.gever.web.homepage.vo.UserMenuVO;
import com.gever.exception.db.DAOException;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 0.5
 */

public interface MenuSetupDao {

    /**
     * ��Ѱ��һ���˵�
     * @param allMenu ���в˵��б�
     * @return һ���˵��б�
     */

    public Collection queryFirstMenus(Collection menus) throws DAOException;

    /**
     * ��Ѱ�¼��˵�
     * @param userId ��ǰ�û�ID
     * @param nodeId ��ǰ�ڵ�ID
     * @return �¼��˵��б�
     * @throws DAOException
     */

    public Collection queryNextMenus(String userId, String nodeId) throws DAOException;

    /**
    * �޸Ĳ˵�
    * @param menuVo ��ǰ�˵�VO����
    * @return �޸ĳɹ���¼��
    * @throws DAOException
    */

    public int update(UserMenuVO menuVo) throws DAOException;
    /**
    * �����˵�
    * @param menuVo ��ǰ�˵�VO����
    * @return �޸ĳɹ���¼��
    * @throws DAOException
    */

    public int insert(UserMenuVO MenuVo) throws DAOException;

    /**
     * ���β˵�
     * @param showMenus �˵�id�б�
     * @param hideMenus ���ز˵�
     * @param userId �û�ID�б�
     * @return �޸ĳɹ���¼��
     * @throws DAOException
     */

    public int hide(String[] showMenus,String[] hideMenus,String userId) throws DAOException;


    /**
    * �ƶ��˵�
    * @param menuIds �˵��б�
    * @param userId ��ǰ�û�ID
    * @param nodeId ��ǰ�ڵ�
    * @return ���Ƶ�����
    * @throws DAOException
    */
    public int move(String[] nodeIds,String userId, String curNodeid) throws DAOException;


    /**
     * �õ���Tree
     * @param allMenus ��ǰ�û�ID
     * @return Option HTML
     * @throws DAOException
     */
    public String getTreeOption(Collection allMenus) throws DAOException;

    /**
     * ��Ѱ���е�
     * @param userId ��ǰ�û�ID
     * @return �������в˵��б�
     * @throws DAOException
     */
    public Collection queryAll(String userId) throws DAOException;


    /**
     * ���β˵�
     * @param userId ��ǰ�˵�VO����
     * @param delMenus Ҫɾ���Ĳ˵�
     * @return �޸ĳɹ���¼��
     * @throws DAOException
     */

    public int delete(String userId, String[] delMenus) throws DAOException;
    
    /**
     * ɾ���û�ʱɾ���˵����û���������
     * @author Hu.Walker
     * @param  userId ��ǰ�˵�VO����
     * @return void
     * @throws DAOException
     */
    public void deleteByUserID(String[] userId) throws DAOException;
    
    /**
     * �������ò˵�
     * @param userId ��ǰ�û�id
     * @return �����Ƿ�ɹ�
     * @throws DAOException
     */

    public boolean resetMenus(String userId) throws DAOException;

	/**
     * ��������ϵͳĬ�ϲ˵�  2004-11-25 ����
     * @param userId ��ǰ�û�id
     * @return �����Ƿ�ɹ�
     * @throws DAOException
     */

    public boolean resetDefaultMenus(String userId) throws DAOException;

    /**
    * ��������ѯ
    * @param menuVo ��ǰ��vo����
    * @return VO����
    * @throws DAOException
    */
    public UserMenuVO queryByPk(UserMenuVO MenuVo) throws DAOException ;

    /**
     * �õ��¼��˵�
     * @param strNodeID ��ǰ�û��ڵ�
     * @param aryFiles �����ļ�
     * @return ȡ���¼��˵�����
     */

    public List getNextMenu(String strNodeID, List aryFiles) ;

    /**
     * ��Ѱ����ɾ��������
     * @param userId ��ǰ�û�ID
     * @return ����ɾ��������
     * @throws DAOException
     */

    public Collection queryByDelete(String userId) throws DAOException ;

    /**
     * �õ����еĽڵ��(01-->0101--->010101)
     * @param strNodeID ��ǰ�ڵ�
     * @param aryDir ���Ӧ�Ĳ˵���
     * @return (01-->0101--->010101)
     */
    public String getFullNodeid(String strNodeID, List aryDir);

}