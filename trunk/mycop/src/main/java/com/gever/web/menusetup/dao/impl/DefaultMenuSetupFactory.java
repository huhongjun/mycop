/*
 * 创建日期 2004-10-25
 */
package com.gever.web.menusetup.dao.impl;

import com.gever.web.menusetup.dao.MenuSetupDao;
import com.gever.web.menusetup.dao.MenuSetupFactory;
import com.gever.web.menusetup.dao.SQLFactory;

/**
 * @author Hu.Walker
 */
public class DefaultMenuSetupFactory extends MenuSetupFactory {

    /* （非 Javadoc）
     * @see com.gever.menusetup.dao.MenuSetupFactory#getMenuSetupDao()
     */
    public MenuSetupDao getMenuSetupDao() {
        return new DefaultMenuSetupDao();
    }

    /* （非 Javadoc）
     * @see com.gever.menusetup.dao.MenuSetupFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new DefaultSQLFactory();
    }

}
