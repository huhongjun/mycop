/*
 * 创建日期 2004-10-25
 */
package com.gever.menusetup.dao;

import java.util.Collection;

import com.gever.exception.db.DAOException;
import com.gever.web.homepage.vo.UserMenuVO;
import com.gever.web.menusetup.dao.MenuSetupDao;
import com.gever.web.menusetup.dao.MenuSetupFactory;

import junit.framework.TestCase;

/**
 * @author Hu.Walker
 */
public class DefaultMenuSetupDaoTest extends TestCase {
    private MenuSetupDao dao = MenuSetupFactory.getInstance().getMenuSetupDao();
    
    /**
     * Constructor for DefaultMenuSetupDaoTest.
     * @param arg0
     */
    public DefaultMenuSetupDaoTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DefaultMenuSetupDaoTest.class);
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

    public void testQueryAll() {
        try {
            Collection result = dao.queryAll("1");
            assertNotNull(result);
            assertTrue(result.size() > 0);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testQueryFirstMenus() {
    }

    public void testQueryNextMenus() {
        try {
            Collection result = dao.queryNextMenus("1", "0");
            assertNotNull(result);
            assertTrue(result.size() > 0);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testUpdate() {
    }

    public void testInsert() {
    }

    public void testDelete() {
    }

    public void testQueryByPk() {
        UserMenuVO menu = new UserMenuVO();
        menu.setEmpid("1");
        menu.setNodeid("2");
        try {
            menu = dao.queryByPk(menu);
            assertNotNull(menu);
            System.out.println("menu 2:" + menu.getNodename());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testQueryByDelete() {
    }

    public void testHide() {
    }

    public void testResetMenus() {}

    public void testMove() {
    }

    public void testGetNextMenu() {
    }

    public void testGetTreeOption() {
    }

    public void testGetFullNodeid() {
    }

}
