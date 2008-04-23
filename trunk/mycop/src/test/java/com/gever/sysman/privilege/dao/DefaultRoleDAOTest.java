/*
 * ¥¥Ω®»’∆⁄ 2004-10-25
 */
package com.gever.sysman.privilege.dao;

import java.util.Collection;

import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.RoleDAO;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.sysman.privilege.model.Role;

import junit.framework.TestCase;

/**
 * @author Hu.Walker
 */
public class DefaultRoleDAOTest extends TestCase {
    private RoleDAO dao = PrivilegeFactory.getInstance().getRoleDAO();
    
    /**
     * Constructor for DefaultRoleDAOTest.
     * @param arg0
     */
    public DefaultRoleDAOTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DefaultRoleDAOTest.class);
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

    public void testCreateRole() {
    }

    public void testCreateUserRole() {
    }

    /*
     * ≤‚ ‘ Collection getOtherRolesByID(String, long, long)
     */
    public void testGetOtherRolesByIDStringlonglong() {
    }

    public void testGetOtherRoleCount() {
    }

    /*
     * ≤‚ ‘ Collection getOtherRolesByID(String)
     */
    public void testGetOtherRolesByIDString() {
    }

    public void testGetResources() {
    }

    public void testAddResources() {
    }

    public void testRemoveResource() {
    }

    public void testFindRoleByID() {
        try {
            Role result = dao.findRoleByID(1L);
            assertNotNull(result);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testFindRoleByName() {
        try {
            Role result = dao.findRoleByName("≥¨º∂π‹¿Ì‘±");
            assertNotNull(result);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testHaveRoleName() {
        try {
            boolean result = dao.haveRoleName("≥¨º∂π‹¿Ì‘±");
            assertTrue(" «∑Ò”–SuperUserΩ«…´?",result);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testDeleteRolesByIds() {
    }

    /*
     * ≤‚ ‘ void deleteUserRolesByIds(String, String[])
     */
    public void testDeleteUserRolesByIdsStringStringArray() {
    }

    /*
     * ≤‚ ‘ void deleteUserRolesByIds(String)
     */
    public void testDeleteUserRolesByIdsString() {
    }

    public void testDeleteRole() {
    }

    public void testUpdateRole() {
    }


    /*
     * ≤‚ ‘ Collection getRoles()
     */
    public void testGetRoles() {
        try {
            Collection result = dao.getRoles();
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testGetRoleCount() {
        try {
            int result = dao.getRoleCount();
            assertEquals(result, 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ≤‚ ‘ Collection getRoles(long, long)
     */
    public void testGetRoleslonglong() {
        try {
            Collection result = dao.getRoles(0, 5);
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testFindRole() {
    }

    /*
     * ≤‚ ‘ Collection getUsers(Role, int, int)
     */
    public void testGetUsersRoleintint() {
        try {
            Collection result = dao.getUsers(PrivilegeFactory.getInstance().getRoleDAO().findRoleByID(1L),0,Integer.MAX_VALUE);
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ≤‚ ‘ Collection getUsers(Role)
     */
    public void testGetUsersRole() {
        try {
            Collection result = dao.getUsers(PrivilegeFactory.getInstance().getRoleDAO().findRoleByID(1L));
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testContain() {}

    public void testAddUsers() {
    }

    public void testAddRoles() {
    }

    public void testRemoveUser() {
    }

    /*
     * ≤‚ ‘ Collection getRolesByID(String, long, long)
     */
    public void testGetRolesByIDStringlonglong() {
        try {
            Collection result = dao.getRolesByID("1",0L,5L);
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ≤‚ ‘ Collection getRolesByID(String)
     */
    public void testGetRolesByIDString() {
        try {
            Collection result = dao.getRolesByID("1");
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ≤‚ ‘ Collection getRolesByID(String[])
     */
    public void testGetRolesByIDStringArray() {
        try {
            Collection result = dao.getRolesByID(new String[]{"1"});
            assertNotNull(result);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ≤‚ ‘ Collection getRoles(User)
     */
    public void testGetRolesUser() {
        try {
            UserDAO userDAO = PrivilegeFactory.getInstance().getUserDAO();
            Collection result = dao.getRoles(userDAO.findUserByID(1L));
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    public void testGetUserRoleCount() {
        try {
            int result = dao.getUserRoleCount("1");
            assertEquals(result, 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}
