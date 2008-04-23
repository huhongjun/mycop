/*
 * �������� 2004-10-19
 */
package com.gever.sysman.privilege.dao.impl.oracle;

import com.gever.sysman.privilege.dao.SQLFactory;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.sysman.privilege.dao.impl.DefaultPrivilegeFactory;

/**
 * @author Hu.Walker
 */
public class OraclePrivilegeFactory extends DefaultPrivilegeFactory {

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.dao.PrivilegeFactory#getUserDAO()
     */
    public UserDAO getUserDAO() {
        return new OracleUserDAO();
    }

    /* ���� Javadoc��
     * @see com.gever.sysman.privilege.dao.PrivilegeFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new OracleSQLFactory();
    }

}
