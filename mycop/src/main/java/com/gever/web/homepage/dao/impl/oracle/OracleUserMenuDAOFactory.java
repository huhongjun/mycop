/*
 * 创建日期 2004-10-25
 */
package com.gever.web.homepage.dao.impl.oracle;

import com.gever.web.homepage.dao.UserMenuDao;
import com.gever.web.homepage.dao.impl.DefaultUserMenuDAOFactory;

/**
 * @author Hu.Walker
 */
public class OracleUserMenuDAOFactory extends DefaultUserMenuDAOFactory {


    /* （非 Javadoc）
     * @see com.gever.homepage.dao.UserMenuDAOFactory#getUserMenuDao()
     */
    public UserMenuDao getUserMenuDao() {
        return new OracleUserMenuDao();
    }

}
