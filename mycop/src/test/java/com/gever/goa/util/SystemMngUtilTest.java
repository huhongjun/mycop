package com.gever.goa.util;

import java.sql.SQLException;

import com.gever.exception.DefaultException;
import com.gever.sysman.api.OrganizationUtil;

import junit.framework.TestCase;

public class SystemMngUtilTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getDepartmentIDsByUser(int)'
	 */
	public void testGetDepartmentIDsByUser() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getDepartmentNamesByUser(int)'
	 */
	public void testGetDepartmentNamesByUser() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getJobIDsByUser(int)'
	 */
	public void testGetJobIDsByUser() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getJobNamesByUser(int)'
	 */
	public void testGetJobNamesByUser() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getUserNamesByUserdIDs(String)'
	 */
	public void testGetUserNamesByUserdIDsString() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getUserNamesByUserdIDs(String, String)'
	 */
	public void testGetUserNamesByUserdIDsStringString() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getDepartmentStructure()'
	 */
	public void testGetDepartmentStructure() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.findDepartment(String, long, long)'
	 */
	public void testFindDepartment() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getSubDepartments(Department)'
	 */
	public void testGetSubDepartments() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getAllUserIDs()'
	 */
	public void testGetAllUserIDs() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getUserIDsForDepartmentOfUserByUser(int)'
	 */
	public void testGetUserIDsForDepartmentOfUserByUser() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getUserIdByUserName(String)'
	 */
	public void testGetUserIdByUserName() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getUserIdsByUserName(String)'
	 */
	public void testGetUserIdsByUserName() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getUserLevelByUserID(String)'
	 */
	public void testGetUserLevelByUserID() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getDeptNameByDeptID(String)'
	 */
	public void testGetDeptNameByDeptID() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.checkUser(int)'
	 */
	public void testCheckUser() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.getDepartmentSonIdById(String)'
	 */
	public void testGetDepartmentSonIdById() {
		// TODO Auto-generated method stub
		try {
			OrganizationUtil util=new OrganizationUtil();
			System.out.println("thie sondept is:"+ util.getDepartmentSonIdById("1098"));
		} catch (DefaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Test method for 'com.gever.goa.util.SystemMngUtil.isFolder(String)'
	 */
	public void testIsFolder() {
		// TODO Auto-generated method stub

	}

}
