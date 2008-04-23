/*
 * 创建日期 2004-10-26
 */
package com.gever.homepage.dao;

import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;

import com.gever.exception.db.DAOException;
import com.gever.web.homepage.dao.UserMenuDAOFactory;
import com.gever.web.homepage.dao.UserMenuDao;
import com.gever.web.homepage.vo.UserMenuVO;

import junit.framework.TestCase;

/**
 * @author Hu.Walker
 */
public class TestUserMenuDao extends TestCase {
    private static UserMenuDao dao = UserMenuDAOFactory.getInstance().getUserMenuDao();
    
    /**
     * Constructor for DefaultUserMenuDaoTest.
     * @param arg0
     */
    public TestUserMenuDao(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestUserMenuDao.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * 测试 void DefaultUserMenuDao()
     */
    public void testDefaultUserMenuDao() {
    }

    /*
     * 测试 void DefaultUserMenuDao(String)
     */
    public void testDefaultUserMenuDaoString() {
    }

    public void testQueryAll() {
        String userId = "1";
        try {
            System.out.println("UserMenuDao:" + dao.getClass().getName());
            List result = dao.queryAll(userId);
            assertNotNull(result);
            String menus="";
            for (Iterator it = result.iterator(); it.hasNext();) {
                UserMenuVO menu = (UserMenuVO) it.next();
                menus = menus + menu.getNodename();
            }
            System.out.println("用户[" + userId +"]"+ "的全部菜单项:" + menus);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testQueryMenus() throws DAOException {
    	Document docMenu = dao.queryMenus("1");
    	assertNotNull(docMenu);
    }

    public void testResetUserMenus() {
    }

    public void testIsHaveAcl() {
    }

    public void testSetAryMenusTwo() {
    }

    public void testGetAryMenusTwo() {
    }

    public void testGetAryMenusAll() {
    }

    public void testSetAryMenusAll() {
    }

}
