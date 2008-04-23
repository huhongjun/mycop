/*
 * 创建日期 2004-10-19
 */
package com.gever.sysman.organization.dao;

import com.gever.exception.db.DAOException;

import com.gever.sysman.organization.dao.DepartmentDAO;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.model.Department;

import junit.framework.TestCase;

import java.util.Collection;
import java.util.Iterator;


/**
 * @author Hu.Walker
 */
public class DefaultDepartmentDAOTest extends TestCase {
    private DepartmentDAO departmentDAO = OrganizationFactory.getInstance()
                                                             .getDepartmentDAO();

    /**
     * Constructor for DefaultDepartmentDAOTest.
     * @param arg0
     */
    public DefaultDepartmentDAOTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DefaultDepartmentDAOTest.class);
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

    public void testFindDepartment() {
        try {
            Collection result = departmentDAO.findDepartment("0=0", 0, 100);
            System.out.println("findDepartment result:" + result);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testFindDepartmentsForPage() {
        try {
            Collection result = departmentDAO.getDepartmentsForPage(0, 0,
                    Integer.MAX_VALUE);
            assertNotNull(result);

            for (Iterator it = result.iterator(); it.hasNext();) {
                Department department = (Department) it.next();
                System.out.println("findDepartmentForPage deparment:" +
                    department);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void testGetJobsForPage() {
        try {
            Department department = OrganizationFactory.getInstance()
                                                       .createDepartment();
            department.setId(1001);

            Collection result = departmentDAO.getJobs(department);
            System.out.println("jobs:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
