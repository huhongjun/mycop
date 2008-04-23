/*
 * 创建日期 2004-10-25
 */
package com.gever.sysman.privilege.dao;

import java.util.Collection;

import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.dao.ResourceDAO;
import com.gever.sysman.privilege.model.Resource;

import junit.framework.TestCase;

/**
 * @author Hu.Walker
 */
public class DefaultResourceDAOTest extends TestCase {
    private ResourceDAO dao = PrivilegeFactory.getInstance().getResourceDAO();
    
    /**
     * Constructor for DefaultResourceDAOTest.
     * @param arg0
     */
    public DefaultResourceDAOTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DefaultResourceDAOTest.class);
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

    public void testFindResourceByID() {
        try {
            Resource result = dao.findResourceByID(1L);
            assertNotNull(result);
            System.out.println(result.getName());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testFindResourceByIDs() {
        try {
            Collection result = dao.findResourceByIDs(new long[]{0L,1L});
            assertNotNull(result);
            assertEquals(result.size(), 1);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testFindResourceByName() {
        try {
            Resource result = dao.findResourceByName("系统管理");
            assertNotNull(result);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testDeleteResource() {
    }

    public void testGetResourcesTree() {
        try {
            Collection result = dao.getResourcesTree();
            assertNotNull(result);
            assertTrue(result.size() > 0);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 测试 Collection getResources()
     */
    public void testGetResources() {
    }

    public void testGetRootResource() {
        try {
            Resource result = dao.getRootResource();
            assertNotNull(result);
            assertEquals(result.getId(), 0L);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testGetResourceCount() {
    }

    public void testGetChildResourceCount() {
    }

    public void testUpdateResource() {
    }

    /*
     * 测试 Collection getResources(int, int)
     */
    public void testGetResourcesintint() {
    }

    public void testGetChileResources() {
    }

    public void testFindResource() {
    }

    public void testGetChildren() {
    }

    public void testGetOperations() {
    }

    public void testRemoveOperation() {
    }

    public void testGetChilds() {
    }

    public void testGetResourcesAndOperations() {
    }

}
