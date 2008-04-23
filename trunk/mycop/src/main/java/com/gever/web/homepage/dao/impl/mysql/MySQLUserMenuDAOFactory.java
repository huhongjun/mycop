/*
 * 创建日期 2004-11-9
 */
package com.gever.web.homepage.dao.impl.mysql;

import com.gever.web.homepage.dao.UserMenuDao;
import com.gever.web.homepage.dao.impl.DefaultUserMenuDAOFactory;

/**
 * @author Hu.Walker
 */
public class MySQLUserMenuDAOFactory extends DefaultUserMenuDAOFactory {
	
	public UserMenuDao getUserMenuDao() {
		return new MySQLUserMenuDAO();
	}
}
