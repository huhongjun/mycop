package com.gever.web.menusetup.dao;

import java.util.*;

import com.gever.web.homepage.vo.UserMenuVO;
import com.gever.exception.db.DAOException;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 0.5
 */

public interface MenuSetupDao {

    /**
     * 查寻第一级菜单
     * @param allMenu 所有菜单列表
     * @return 一级菜单列表
     */

    public Collection queryFirstMenus(Collection menus) throws DAOException;

    /**
     * 查寻下级菜单
     * @param userId 当前用户ID
     * @param nodeId 当前节点ID
     * @return 下级菜单列表
     * @throws DAOException
     */

    public Collection queryNextMenus(String userId, String nodeId) throws DAOException;

    /**
    * 修改菜单
    * @param menuVo 当前菜单VO对象
    * @return 修改成功记录数
    * @throws DAOException
    */

    public int update(UserMenuVO menuVo) throws DAOException;
    /**
    * 新增菜单
    * @param menuVo 当前菜单VO对象
    * @return 修改成功记录数
    * @throws DAOException
    */

    public int insert(UserMenuVO MenuVo) throws DAOException;

    /**
     * 隐蔽菜单
     * @param showMenus 菜单id列表
     * @param hideMenus 隐藏菜单
     * @param userId 用户ID列表
     * @return 修改成功记录数
     * @throws DAOException
     */

    public int hide(String[] showMenus,String[] hideMenus,String userId) throws DAOException;


    /**
    * 移动菜单
    * @param menuIds 菜单列表
    * @param userId 当前用户ID
    * @param nodeId 当前节点
    * @return 搬移的数据
    * @throws DAOException
    */
    public int move(String[] nodeIds,String userId, String curNodeid) throws DAOException;


    /**
     * 得到像Tree
     * @param allMenus 当前用户ID
     * @return Option HTML
     * @throws DAOException
     */
    public String getTreeOption(Collection allMenus) throws DAOException;

    /**
     * 查寻所有的
     * @param userId 当前用户ID
     * @return 返回所有菜单列表
     * @throws DAOException
     */
    public Collection queryAll(String userId) throws DAOException;


    /**
     * 隐蔽菜单
     * @param userId 当前菜单VO对象
     * @param delMenus 要删除的菜单
     * @return 修改成功记录数
     * @throws DAOException
     */

    public int delete(String userId, String[] delMenus) throws DAOException;
    
    /**
     * 删除用户时删除菜单中用户关联数据
     * @author Hu.Walker
     * @param  userId 当前菜单VO对象
     * @return void
     * @throws DAOException
     */
    public void deleteByUserID(String[] userId) throws DAOException;
    
    /**
     * 重新设置菜单
     * @param userId 当前用户id
     * @return 返回是否成功
     * @throws DAOException
     */

    public boolean resetMenus(String userId) throws DAOException;

	/**
     * 重新设置系统默认菜单  2004-11-25 杨帆添加
     * @param userId 当前用户id
     * @return 返回是否成功
     * @throws DAOException
     */

    public boolean resetDefaultMenus(String userId) throws DAOException;

    /**
    * 按主键查询
    * @param menuVo 当前的vo对象
    * @return VO对象
    * @throws DAOException
    */
    public UserMenuVO queryByPk(UserMenuVO MenuVo) throws DAOException ;

    /**
     * 得到下级菜单
     * @param strNodeID 当前用户节点
     * @param aryFiles 所有文件
     * @return 取得下级菜单数据
     */

    public List getNextMenu(String strNodeID, List aryFiles) ;

    /**
     * 查寻可以删除的资料
     * @param userId 当前用户ID
     * @return 可以删除的资料
     * @throws DAOException
     */

    public Collection queryByDelete(String userId) throws DAOException ;

    /**
     * 得到所有的节点的(01-->0101--->010101)
     * @param strNodeID 当前节点
     * @param aryDir 相对应的菜单数
     * @return (01-->0101--->010101)
     */
    public String getFullNodeid(String strNodeID, List aryDir);

}