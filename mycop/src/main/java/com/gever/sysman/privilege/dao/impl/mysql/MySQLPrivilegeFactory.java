/*
 * 创建日期 2004-11-1
 */
package com.gever.sysman.privilege.dao.impl.mysql;

import com.gever.sysman.privilege.dao.SQLFactory;
import com.gever.sysman.privilege.dao.impl.DefaultPrivilegeFactory;

/**
 * @author Hu.Walker
 */
public class MySQLPrivilegeFactory extends DefaultPrivilegeFactory {

	public SQLFactory  getSQLFactory() {
		return new MySQLSQLFactory();
	}
}
