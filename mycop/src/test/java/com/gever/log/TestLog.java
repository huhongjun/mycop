package com.gever.log;

import junit.framework.*;

import com.gever.util.log.Log;

public class TestLog extends TestCase {
    private Log log = null;

    public TestLog(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        /**@todo verify the constructors*/
        log = new Log(false);
    }

    protected void tearDown() throws Exception {
        log = null;
        super.tearDown();
    }

    public void testLog() {
        boolean debug = false;
        log = new Log(debug);
        /**@todo fill in the test code*/
    }

    public void testLog1() {
        boolean debug = false;
        Class clazz = this.getClass();
        log = new Log(debug, clazz);
        /**@todo fill in the test code*/
    }

    public void testGetDebug() {
        boolean expectedReturn = false;
        boolean actualReturn = log.getDebug();
        assertEquals("return value", expectedReturn, actualReturn);
        /**@todo fill in the test code*/
    }



    public void testGetInstance1() {
        //Class clazz = TestSQL.class;
        Log expectedReturn = null;
        Log actualReturn = log.getInstance(Test.class);
        //assertEquals("return value", expectedReturn, actualReturn);
        /**@todo fill in the test code*/
    }

    public void testInit() {
        boolean debug = false;
        Class clazz = null;
        String apropertiesFile = null;
        //log.init(debug, true,clazz, apropertiesFile);
        /**@todo fill in the test code*/
    }

    public void testInit1() {
        boolean debug = true;
        Log.setUselog4j(true);

        String apropertiesFile = "testDir/resource/debug.properties";
        log.init(debug,true, apropertiesFile);
        log.showLog("-----9fdsafkjdsalkf-----------");
        /**@todo fill in the test code*/
    }

    public void testInitLogger() {
        boolean debug = true;
        Log.setUselog4j(true);

        String apropertiesFile = "testDir/resource/debug.properties";
        log.init(debug,true, apropertiesFile);
        log.initLogger( TestLog.class);
        log.showLog("-----9fdsafkjdsalkf-----dddddd------");
        /**@todo fill in the test code*/

        /**@todo fill in the test code*/
    }

}
