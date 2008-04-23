package com.gever.log;

import com.gever.util.log.Log;

import junit.framework.TestCase;

public class LogTest extends TestCase {

	private Log log=Log.getInstance();
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(LogTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		log.setDebug(true);
		log.setUselog4j(false);
	}

	/*
	 * Test method for 'com.gever.log.Log.console(Class, String, Object)'
	 */
	public void testConsole() {
		// TODO Auto-generated method stub
		String sql = " Select id as nodeid,name as nodename,case " +
        "when (Select COUNT(*) AS CNT from T_DEPARTMENT b  WHERE " +
        "T_DEPARTMENT.id<>b.id AND b.parent_id=T_DEPARTMENT.id  ) >0 " +
        "then '1' else '0' end as isfolder from T_DEPARTMENT where  1=1 ";
        
		log.console(this.getClass(),"junit ≤‚ ‘",sql);
	}

}
