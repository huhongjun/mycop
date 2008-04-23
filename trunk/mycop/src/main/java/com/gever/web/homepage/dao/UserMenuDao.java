package com.gever.web.homepage.dao;

import java.util.*;
import com.gever.exception.DefaultException;
import com.gever.exception.db.DAOException;

import org.w3c.dom.*;
/**
 * <p>Title: 用户界面菜单处理</p>
 * <p>Description: 包括取资料及重新设置界面资料</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author 
 * @version 0.5
 */

public interface UserMenuDao {

    /**
     * 查出该用户所有的菜单资料
     * @param userID 用户ID号
     * @return 菜单列表
     * @throws DefaultException
     */
    public List queryAll(String userID) throws DAOException;

    /**
     * 查出该用户的所有菜单(二级菜单(含以下)的主要是一个下拉菜单,
     * 客户处理时需要xml文档中的Document对象)
     * @param userID 用户ID号
     * @return Document对象(菜单)
     * @throws DefaultException
     */
    public Document queryMenus(String userID) throws DAOException;

    /**
     * 对外接口,当用户重新更改过权限时
     * @param userID 用户ID号
     * @param moduleList 当前用户对应资源资料
     * @return 设置菜单是否成功
     * @throws DefaultException
     */
    public boolean resetUserMenus(String userID, Collection moduleList) throws  DAOException;

    /**
     * 检查是否有权限
     * @param userID 当前用户ID号
     * @return 是否有权限
     * @throws DAOException
     */
    public boolean isHaveAcl(String userID) throws DAOException;
}