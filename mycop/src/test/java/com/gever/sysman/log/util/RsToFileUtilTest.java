package com.gever.sysman.log.util;

import java.io.File;

import junit.framework.TestCase;

public class RsToFileUtilTest extends TestCase {
	
	private RsToFileUtil rs2file = new RsToFileUtil();
	
	private String type="syslog";
	private String whereClause="";
	private String realPath="";
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(RsToFileUtilTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		whereClause="";
		realPath="webroot\\";
	}

	/*
	 * Test method for 'com.gever.sysman.log.util.RsToFileUtil.execute(String, String, String, String)'
	 */
	public void testExecute() {
		String fileName= new String();
		fileName = rs2file.execute(type,whereClause,realPath);
		File rs = new File(fileName);
		assertTrue(rs.exists());
	}

}
