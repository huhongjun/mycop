/*
 * 创建日期 2004-10-28
 */
package com.gever.sysman.privilege.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.PermissionDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;

import junit.framework.TestCase;

/**
 * @author Hu.Walker
 */
public class DefaultPermissionDAOTest extends TestCase {
    private static PermissionDAO dao = PrivilegeFactory.getInstance().getPermissionDAO();
    private static OperationDAO opDAO = PrivilegeFactory.getInstance().getOperationDAO();
    
    
    /**
     * Constructor for DefaultPermissionDAOTest.
     * @param arg0
     */
    public DefaultPermissionDAOTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DefaultPermissionDAOTest.class);
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

    public void testReturnOpts() {
    }

    public void testReturnUserOpts() {
    }

    public void testReturnRess() {
    }

    public void testGetChildNodes() {
    }

    public void testGetParentNodes() {
    }

    public void testGetRoot() {
    }

    public void testUser_process() {
    }

    public void testResUserTree() {
    }

    public void testProcess() {
    }

    public void testResTree() {
    }

    public void testSpace() {
    }

    public void testSavaUserPerm() {
    }

    public void testSavaRolePerm() {
    }

    /*
     * 测试 Collection getUserPerm(String, String[])
     */
    public void testGetUserPermStringStringArray() {
    }

    public void testGetRolePerm() {
    }

    public void testGetUserAndRolePerm() {
    }

    public void testGetAllParentResByid() {
    }

    public void testGetResCollect() {
    }

    public void testGetResCollectByRole() {
    }

    public void testGeResCollectIncludeParents() {
    }

    public void testGetAllResources() {
    }

    public void testSetParentResources() {
    }

    public void testResetMenuRes() {
    }

    /*
     * 测试 String[] getUserPerm(long)
     */
    public void testGetUserPermlong() {
        try {
            String[] result = dao.getUserPerm(1000L);
            assertNotNull(result);
            for (int i = 0; i < result.length; i++) {
                System.out.println("userpem " + i + ":" + result[i]);
            }
            
            Collection rolePerms = opDAO.getUserRolePerm("1000");
            assertNotNull(rolePerms);
            for (Iterator it = rolePerms.iterator(); it.hasNext();) {
                System.out.println(it.next());
                
            }
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
