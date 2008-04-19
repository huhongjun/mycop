package com.gever.goa.book.dao;


import com.gever.exception.DefaultException;
import com.gever.goa.book.vo.BookVO;
import com.gever.util.Codes;
import com.gever.vo.VOInterface;

import junit.framework.TestCase;
import com.gever.util.InitTestUtil;

public class BookDaoTest extends TestCase {

	private BookDao testDAO;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(BookDaoTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		InitTestUtil.init();		
		testDAO = BookFactory.getInstance().createBookDAO("unittest");
	}

	public void testGetPageSql() {
		// TODO Auto-generated method stub

	}

	public void testInsert() throws DefaultException {
			BookVO testVO = new BookVO();
		testVO.setBookid("");
		testVO.setBookname("");
		testVO.setPublisher("");
		testVO.setDeptid("");
	
		int ret;
		ret = testDAO.insert(testVO);
		assertTrue("新增一条记录返回结果应为1",ret==1);
	}
	
		public void testUpdate() throws DefaultException {
			BookVO testVO = new BookVO();
		testVO.setBookid("");
		testVO.setBookname("");
		testVO.setPublisher("");
		testVO.setDeptid("");
	
		int ret;
		ret = testDAO.insert(testVO);
		assertTrue("新增一条记录返回结果应为1",ret==1);
	}
	
		public void testDelete() throws DefaultException {
			BookVO testVO = new BookVO();
		testVO.setBookid("");
		testVO.setBookname("");
		testVO.setPublisher("");
		testVO.setDeptid("");
	
		int ret;
		ret = testDAO.insert(testVO);
		assertTrue("新增一条记录返回结果应为1",ret==1);
	}
	
	
}
