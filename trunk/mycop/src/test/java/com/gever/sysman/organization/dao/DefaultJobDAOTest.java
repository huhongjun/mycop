/*
 * 创建日期 2004-11-8
 */
package com.gever.sysman.organization.dao;

import java.util.Collection;
import java.util.Iterator;

import com.gever.exception.db.DAOException;
import com.gever.sysman.organization.dao.JobDAO;
import com.gever.sysman.organization.dao.OrganizationFactory;
import com.gever.sysman.organization.model.Job;

import junit.framework.TestCase;


/**
 * @author Hu.Walker
 */
public class DefaultJobDAOTest extends TestCase {
    JobDAO dao = OrganizationFactory.getInstance().getJobDAO();

    /**
     * Constructor for DefaultJobDAOTest.
     * @param arg0
     */
    public DefaultJobDAOTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(DefaultJobDAOTest.class);
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

    public void testFindJobsForPage() {
        try {
            System.out.println(OrganizationFactory.getInstance().getClass().getName());
            System.out.println(dao.getClass().getName());
            Collection result = dao.findJobsForPage(" 0=0 ", 0, 100);
            System.out.println("result:" + result);
            for (Iterator it = result.iterator(); it.hasNext();) {
                Job job = (Job) it.next();
                System.out.println(job.getId() + ":" + job.getName());
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
