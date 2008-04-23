/*
 * 创建日期 2004-10-25
 */
package com.gever.sysman.privilege.dao;

import java.util.Collection;

import com.gever.exception.db.DAOException;
import com.gever.sysman.privilege.dao.OperationDAO;
import com.gever.sysman.privilege.dao.PrivilegeFactory;
import com.gever.sysman.privilege.model.Operation;

import junit.framework.TestCase;

/**
 * @author Hu.Walker
 */
public class DefaultOperationDAOTest extends TestCase {
    private OperationDAO dao ;
    /**
     * Constructor for DefaultOperationDAOTest.
     * @param arg0
     */
    public DefaultOperationDAOTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DefaultOperationDAOTest.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        dao = PrivilegeFactory.getInstance().getOperationDAO();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFindOperationByID() {
        try {
            Operation op = dao.findOperationByID(1L);
            assertNotNull(op);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    public void testFindOperationByCode() {
    }

    public void testUpdateOperation() {
    }

    /*
     * 测试 void deleteOperation(Operation)
     */
    public void testDeleteOperationOperation() {
    }

    public void testGetOptByUserid() {
        try {
            Collection result = dao.getOptByUserid(1L);
            assertNotNull(result);
            assertTrue(result.size() >= 0);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    public void testGetOptByRoleid() {
        try {
            Collection result = dao.getOptByRoleid(1L);
            assertNotNull(result);
            assertTrue(result.size() > 0);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    /*
     * 测试 void deleteOperation(String[])
     */
    public void testDeleteOperationStringArray() {
    }

    public void testCreateOperation() {
    }

    /*
     * 测试 Collection getOperations(Resource)
     */
    public void testGetOperationsResource() {
    }

    /*
     * 测试 Collection getOperations()
     */
    public void testGetOperations() {
        try {
            Collection result = dao.getOperations();
            assertNotNull(result);
            assertEquals(result.size()>0,true);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    /*
     * 测试 Collection getOperations(int, int)
     */
    public void testGetOperationsintint() {
        try {
            Collection result = dao.getOperations(0, Integer.MAX_VALUE);
            assertNotNull(result);
            assertTrue(result.size() >= 12);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    public void testGetOperationsByResid() {
        try {
            Collection result = dao.getOperationsByResid("5");
            assertNotNull(result);
            assertEquals(result.size(), 0);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    public void testFindOperations() {
    }

    public void testGetOperationCount() {
        try {
            int count = dao.getOperationCount();
            assertTrue(count >= 12);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

    public void testGetUserRolePerm() {
        try {
            Collection result = dao.getUserRolePerm("1");
            assertNotNull(result);
            assertTrue(result.size() > 1);
        } catch (DAOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }

}
