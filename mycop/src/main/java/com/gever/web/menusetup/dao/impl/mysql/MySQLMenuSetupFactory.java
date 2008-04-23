/*
 * �������� 2004-10-25
 */
package com.gever.web.menusetup.dao.impl.mysql;

import com.gever.web.menusetup.dao.SQLFactory;
import com.gever.web.menusetup.dao.impl.DefaultMenuSetupFactory;

/**
 * @author Hu.Walker
 */
public class MySQLMenuSetupFactory extends DefaultMenuSetupFactory {

    /* ���� Javadoc��
     * @see com.gever.menusetup.dao.MenuSetupFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new MySQLSQLFactory();
    }

    /* ���� Javadoc��
     * @see com.gever.menusetup.dao.MenuSetupFactory#getMenuSetupDao()
     */
}
