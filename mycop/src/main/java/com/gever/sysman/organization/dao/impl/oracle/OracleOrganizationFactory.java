/*
 * �������� 2004-10-19
 */
package com.gever.sysman.organization.dao.impl.oracle;

import com.gever.sysman.organization.dao.SQLFactory;
import com.gever.sysman.organization.dao.UserDAO;
import com.gever.sysman.organization.dao.impl.DefaultOrganizationFactory;

/**
 * @author Hu.Walker
 */
public class OracleOrganizationFactory extends DefaultOrganizationFactory {

    /* ���� Javadoc��
     * @see com.gever.sysman.organization.dao.OrganizationFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new OracleSQLFactory();
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.organization.dao.OrganizationFactory#getUserDAO()
     */
    public UserDAO getUserDAO() {
        return new OracleUserDAO();
    }

}
