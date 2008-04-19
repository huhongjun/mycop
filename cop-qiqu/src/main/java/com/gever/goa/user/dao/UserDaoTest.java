package com.gever.goa.user.dao;


import com.gever.exception.DefaultException;
import com.gever.goa.user.vo.UserVO;
import com.gever.util.Codes;
import com.gever.vo.VOInterface;

import junit.framework.TestCase;
import com.gever.util.InitTestUtil;

public class UserDaoTest extends TestCase {

	private UserDao testDAO;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(UserDaoTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		InitTestUtil.init();		
		testDAO = UserFactory.getInstance().createUserDAO("unittest");
	}

	public void testGetPageSql() {
		// TODO Auto-generated method stub

	}

	public void testInsert() throws DefaultException {
			UserVO testVO = new UserVO();
		testVO.setId("");
		testVO.setName("");
		testVO.setEmail("");
	
		int ret;
		ret = testDAO.insert(testVO);
		assertTrue("新增一条记录返回结果应为1",ret==1);
	}
	
		public void testUpdate() throws DefaultException {
			UserVO testVO = new UserVO();
		testVO.setId("");
		testVO.setName("");
		testVO.setEmail("");
	
		int ret;
		ret = testDAO.insert(testVO);
		assertTrue("新增一条记录返回结果应为1",ret==1);
	}
	
		public void testDelete() throws DefaultException {
			UserVO testVO = new UserVO();
		testVO.setId("");
		testVO.setName("");
		testVO.setEmail("");
	
		int ret;
		ret = testDAO.insert(testVO);
		assertTrue("新增一条记录返回结果应为1",ret==1);
	}
	
	
}
