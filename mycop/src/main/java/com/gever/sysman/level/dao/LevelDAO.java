package com.gever.sysman.level.dao;

import com.gever.sysman.level.model.Level;
import com.gever.exception.db.DAOException;

import java.util.Collection;

public interface LevelDAO {
    /**
     * �����㼶
     * 
     * @param User
     *            �㼶ʵ��
     * @param user
     * @throws DAOException
     * @throws com.gever.exception.db.DAOException
     * @roseuid 40B6A88A00CC
     */
    public void createLevel(Level level) throws DAOException;

    /**
     * ����User
     * 
     * @param User�㼶ʵ��
     * @param user
     * @throws DAOException
     * @throws com.gever.exception.db.DAOException
     * @roseuid 40B6A88A0128
     */
    public void updateLevel(Level level) throws DAOException;

    /**
     * ɾ��User
     * 
     * @param user
     *            �㼶ʵ��
     * @throws DAOException
     * @throws com.gever.exception.db.DAOException
     * @roseuid 40B6A88A0178
     */
    public void deleteLevelByIds(String[] ids) throws DAOException;

    /**
     * �����û�ID��ȡUserʵ��
     * 
     * @param id
     *            �û�ID
     * @return �û�ʵ��
     * @throws DAOException
     * @throws com.gever.exception.db.DAOException
     * @roseuid 40B6A88A01C6
     */

    public Collection getLevel() throws DAOException;

    public Collection getLevels(long start, long count) throws DAOException;

    public int getLevelCount() throws DAOException;

    public Level findLevelByID(long id) throws DAOException;

    public Collection findLevelByName() throws DAOException;

    //===============================================================
    //������ӣ�����JSP��ͼ�б�������
    public void setOrderby(String[] s);
    //===============================================================
}