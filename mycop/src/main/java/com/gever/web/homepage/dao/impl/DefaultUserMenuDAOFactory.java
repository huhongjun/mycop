/*
 * �������� 2004-10-25
 */
package com.gever.web.homepage.dao.impl;

import com.gever.web.homepage.dao.CreateDefaultMenuDAO;
import com.gever.web.homepage.dao.UserMenuDAOFactory;
import com.gever.web.homepage.dao.UserMenuDao;

/**
 * @author Hu.Walker
 */
public class DefaultUserMenuDAOFactory extends UserMenuDAOFactory {

    /* ���� Javadoc��
     * @see com.gever.homepage.dao.UserMenuDAOFactory#getUserMenuDao()
     */
    public UserMenuDao getUserMenuDao() {
        return new DefaultUserMenuDao();
    }
    public CreateDefaultMenuDAO getCreateDefaultMenuDao() {
        return new CreateDefaultUserMenuDAO();
    }


}
