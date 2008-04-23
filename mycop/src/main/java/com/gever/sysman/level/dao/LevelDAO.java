package com.gever.sysman.level.dao;

import com.gever.sysman.level.model.Level;
import com.gever.exception.db.DAOException;

import java.util.Collection;

public interface LevelDAO {
    /**
     * 创建层级
     * 
     * @param User
     *            层级实例
     * @param user
     * @throws DAOException
     * @throws com.gever.exception.db.DAOException
     * @roseuid 40B6A88A00CC
     */
    public void createLevel(Level level) throws DAOException;

    /**
     * 更新User
     * 
     * @param User层级实例
     * @param user
     * @throws DAOException
     * @throws com.gever.exception.db.DAOException
     * @roseuid 40B6A88A0128
     */
    public void updateLevel(Level level) throws DAOException;

    /**
     * 删除User
     * 
     * @param user
     *            层级实例
     * @throws DAOException
     * @throws com.gever.exception.db.DAOException
     * @roseuid 40B6A88A0178
     */
    public void deleteLevelByIds(String[] ids) throws DAOException;

    /**
     * 根据用户ID获取User实例
     * 
     * @param id
     *            用户ID
     * @return 用户实例
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
    //胡勇添加，增加JSP视图列表排序功能
    public void setOrderby(String[] s);
    //===============================================================
}