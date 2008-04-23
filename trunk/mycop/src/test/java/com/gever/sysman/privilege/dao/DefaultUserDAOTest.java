/*
 * 创建日期 2004-10-18
 */
package com.gever.sysman.privilege.dao;

import java.util.Collection;
import java.util.Iterator;

import com.gever.exception.db.DAOException;
import com.gever.sysman.organization.model.User;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.sysman.privilege.model.impl.DefaultUser;

import junit.framework.TestCase;

/**
 * @author Hu.Walker
 */
public class DefaultUserDAOTest extends TestCase {
    UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
    
    /**
     * Constructor for DefaultUserDAOTest.
     * @param arg0
     */
    public DefaultUserDAOTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DefaultUserDAOTest.class);
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

    public void testFindUserByUserName() {
        try {
            User user = userDAO.findUserByUserName("admin");
            System.out.println("user:" + user);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    public void testDefaultUserDAO() {
    }

    public void testGetResources() {
		try {
			User user=new DefaultUser();
			userDAO.getResources(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void testRemoveResource() {
    }

    public void testAddResources() {
    }

    public void testCheckLoginForCA() {
    }

    public void testCheckLogin() {
        try {
            boolean result = userDAO.checkLogin("admin", "");
            assertTrue(result);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testRemoveRole() {
    }

    /*
     * 测试 Collection getUsers(int, int)
     */
    public void testGetUsersintint() {
        try {
            Collection result = userDAO.getUsers(0,30);
            System.out.println("getUsers:" + result);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetUsersForSearch() {
    }

    /*
     * 测试 Collection getUsers(String, int, int)
     */
    public void testGetUsersStringintint() {
        String sql = "SELECT T_USER.ID ,T_USER.USERNAME ,T_USER.PASSWORD ,T_USER_USERTYPE.VALUE AS USERTYPE,T_USER.LEVEL ,T_USER_STATUS.VALUE AS USERSTATUS,T_USER.VALIDDATE ,T_USER.NAME ,T_USER.CODE ,    T_USER.GENDER FROM    T_USER    ,T_USER_STATUS ,    T_USER_USERTYPE WHERE 1=1 AND T_USER.STATUS=T_USER_STATUS.ID AND T_USER.USERTYPE=T_USER_USERTYPE.ID";
        try {
            Collection result = userDAO.getUsers(sql,0,Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 测试 Collection getRoleUsers(String, int, int)
     */
    public void testGetRoleUsersStringintint() {
        try {
            Collection result = userDAO.getRoleUsers("1");
            for (Iterator it = result.iterator(); it.hasNext();) {
                User user = (User) it.next();
                System.out.println("getRoleUsers[roleId=1]:" + user.getName());
            }
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    /*
     * 测试 Collection getRoleUsers(String)
     */
    public void testGetRoleUsersString() {
    }

    public void testGetRoleUserCount() {
    }

    public void testHaveUserName() {
    }

}
