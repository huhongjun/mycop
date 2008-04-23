package com.gever.goa.dailyoffice.tools.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.tools.vo.CardcaseTypeVO;
import com.gever.util.InitTestUtil;

import junit.framework.TestCase;

public class CardcaseTypeDaoTest extends TestCase {

	private CardcaseTypeDao cardTypeDAO;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(CardcaseTypeDaoTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		InitTestUtil.init();
        
		cardTypeDAO = ToolsFactory.getInstance().createCardcaseTypeDao("unittest");		

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.delBySelect(String[], VOInterface)'
	 */
	public void testDelBySelect() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.insert(VOInterface)'
	 */
	public void testInsert() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.update(VOInterface)'
	 */
	public void testUpdate() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.queryAll(VOInterface)'
	 */
	public void testQueryAll() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.queryByPage(VOInterface, long, long)'
	 */
	public void testQueryByPage() throws DefaultException {
		// TODO Auto-generated method stub
		CardcaseTypeVO t1 = new CardcaseTypeVO();
		CardcaseTypeVO t2;
		t1.setType_code("01");
		List ls = this.cardTypeDAO.queryByPage(t1,0,10);
		if( ls.size()>0) 
			{
				t2 = (CardcaseTypeVO) ls.get(0);
				assertEquals(t2.getUser_code(), "1");
			}
		else
			assertTrue("没有CardcaseType记录，测试无效",true);
	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.queryByCount(VOInterface)'
	 */
	public void testQueryByCount() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.queryByPK(VOInterface)'
	 */
	public void testQueryByPK() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.queryByUser(VOInterface)'
	 */
	public void testQueryByUser() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.getTreeData()'
	 */
	public void testGetTreeData() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'com.gever.goa.dailyoffice.tools.dao.CardcaseTypeDao.existTypeName(String, String)'
	 */
	public void testExistTypeName() {
		// TODO Auto-generated method stub

	}

}
