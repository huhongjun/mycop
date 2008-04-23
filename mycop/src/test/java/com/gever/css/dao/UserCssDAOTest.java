package com.gever.css.dao;

import java.util.*;

import com.gever.exception.db.DAOException;
import com.gever.web.css.dao.UserCssDAO;
import com.gever.web.css.dao.UserCssDAOFactory;

import junit.framework.TestCase;

public class UserCssDAOTest extends TestCase {
	
    private UserCssDAO cssDAO = UserCssDAOFactory.getUserCssDAO();

	public static void main(String[] args) {
		junit.textui.TestRunner.run(UserCssDAOTest.class);
	}

	/*
	 * Test method for 'com.gever.css.dao.UserCssDAO.getUserCssGroupId(int)'
	 */
	public void testGetUserCssGroupId() throws DAOException {
		
		String cssGroup = cssDAO.getUserCssGroupId(1);
		assertEquals(cssGroup,null);
	}

	/*
	 * Test method for 'com.gever.css.dao.UserCssDAO.getDefaultCss()'
	 */
	public void testGetDefaultCss() throws Exception {
		Collection css = cssDAO.getDefaultCss();
		assertNotNull(css);
	}

	/*
	 * Test method for 'com.gever.css.dao.UserCssDAO.getCss()'
	 */
	public void testGetCss() throws Exception {
		Map cssMap = cssDAO.getCss();
		assertNotNull(cssMap);
	}
}
