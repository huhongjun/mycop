/*
 * 创建日期 2004-10-25
 */
package com.gever.sysman.organization.dao.impl.mysql;

import com.gever.sysman.organization.dao.SQLFactory;
import com.gever.sysman.organization.dao.impl.DefaultOrganizationFactory;


/**
 * @author Hu.Walker
 */
public class MySQLOrganizationFactory extends DefaultOrganizationFactory {

    /* （非 Javadoc）
     * @see com.gever.menusetup.dao.MenuSetupFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new MySQLSQLFactory();
    }

    /* （非 Javadoc）
     * @see com.gever.menusetup.dao.MenuSetupFactory#getMenuSetupDao()
     */
}
