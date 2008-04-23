/*
 * 创建日期 2004-10-25
 */
package com.gever.sysman.log.dao;

import java.util.Iterator;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.sysman.log.dao.LogDAOFactory;
import com.gever.sysman.log.dao.LogDao;
import com.gever.sysman.log.vo.LogVO;

import junit.framework.TestCase;

/**
 * @author Hu.Walker
 */
public class LogDaoImplTest extends TestCase {
    LogDao dao = LogDAOFactory.getInstance().getLogDao();
    
    /**
     * Constructor for LogDaoImplTest.
     * @param arg0
     */
    public LogDaoImplTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(LogDaoImplTest.class);
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

    public void testAddLog() {
    }

    public void testQueryByPage() {
        try {
            List result = dao.queryByPage(new LogVO(), 10, 10);
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                Object log = (Object) iter.next();
                System.out.println("     LogDaoImplTest.testQueryByPage():" + ((LogVO)log).getId());
            }
        } catch (DefaultException e) {
            e.printStackTrace();
        }
    }

    public void testQueryByCount() {
        long count = dao.queryByCount(new LogVO());
        System.out.println("     LogDaoImplTest.testQueryByCount():" + count);
    }

    public void testGetDialect() {
    }

    public void testQueryAll() {
        try {
            List result = dao.queryAll();
            assertNotNull(result);
            for (Iterator iter = result.iterator(); iter.hasNext();) {
                LogVO log = (LogVO) iter.next();
                
                System.out.println("     LogDaoImplTest.testQueryAll():" + log.getIpaddress());
            }
        } catch (DefaultException e) {
            e.printStackTrace();
        }
    }

    public void testDelBySelect() {
    }

    public void testDelByCond() {
    }

    public void testReplace() {
    }

    public void testReplaceText() {
    	
    }

}
