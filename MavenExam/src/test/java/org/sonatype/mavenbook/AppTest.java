package org.sonatype.mavenbook;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 * @todo: 用于Maven中文测试
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     * FIXME: 中文测试
     */
    public static Test suite()
    {
    	
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * TODO: 测试TagList的待办事项
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
