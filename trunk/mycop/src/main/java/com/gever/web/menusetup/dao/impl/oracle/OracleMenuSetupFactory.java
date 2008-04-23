/*
 * 创建日期 2004-10-25
 */
package com.gever.web.menusetup.dao.impl.oracle;

import com.gever.web.menusetup.dao.MenuSetupDao;
import com.gever.web.menusetup.dao.SQLFactory;
import com.gever.web.menusetup.dao.impl.DefaultMenuSetupFactory;

/**
 * @author Hu.Walker
 */
public class OracleMenuSetupFactory extends DefaultMenuSetupFactory {

    /* （非 Javadoc）
     * @see com.gever.menusetup.dao.MenuSetupFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new OracleSQLFactory();
    }

    /* （非 Javadoc）
     * @see com.gever.menusetup.dao.MenuSetupFactory#getMenuSetupDao()
     */
    public MenuSetupDao getMenuSetupDao() {
        return new OracleMenuSetupDao();
    }

}
