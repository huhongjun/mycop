/*
 * 创建日期 2004-10-19
 */
package com.gever.sysman.privilege.dao.impl.oracle;

import com.gever.sysman.privilege.dao.SQLFactory;
import com.gever.sysman.privilege.dao.UserDAO;
import com.gever.sysman.privilege.dao.impl.DefaultPrivilegeFactory;

/**
 * @author Hu.Walker
 */
public class OraclePrivilegeFactory extends DefaultPrivilegeFactory {

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PrivilegeFactory#getUserDAO()
     */
    public UserDAO getUserDAO() {
        return new OracleUserDAO();
    }

    /* （非 Javadoc）
     * @see com.gever.sysman.privilege.dao.PrivilegeFactory#getSQLFactory()
     */
    public SQLFactory getSQLFactory() {
        return new OracleSQLFactory();
    }

}
