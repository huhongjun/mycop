package com.gever.goa.dailyoffice.calendararrange.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.util.InitTestUtil;

import junit.framework.TestCase;

public class CalendarArrangeDaoTest extends TestCase {

	private CalendarArrangeDao testDAO;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(CalendarArrangeDaoTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		InitTestUtil.init();
		testDAO = CalendarArrangeFactory.getInstance().createCalendarArrange("unittest");
	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeDao.queryAll(VOInterface)'
	 */
	public void testQueryAll() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeDao.queryAllInMonth(int, int, String)'
	 */
	public void testQueryAllInMonth() throws DefaultException {
		// TODO Auto-generated method stub
		List ls = testDAO.queryAllInMonth(2006,7,"1");
		assertTrue(ls.size()>0);
	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeDao.queryAllInWeek(int, int, int, String)'
	 */
	public void testQueryAllInWeek() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.calendararrange.dao.CalendarArrangeDao.getTodayCalendarArrange(String)'
	 */
	public void testGetTodayCalendarArrange() {
		// TODO Auto-generated method stub

	}

}
