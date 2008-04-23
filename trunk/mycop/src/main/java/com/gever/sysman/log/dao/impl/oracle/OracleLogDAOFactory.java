/*
 * �������� 2004-11-8
 */
package com.gever.sysman.log.dao.impl.oracle;

import com.gever.sysman.log.dao.LogDAOFactory;
import com.gever.sysman.log.dao.LogDao;

/**
 * @author Hu.Walker
 */
public class OracleLogDAOFactory extends LogDAOFactory {

    /* ���� Javadoc��
     * @see com.gever.sysman.log.dao.LogDAOFactory#getLogDao()
     */
    public LogDao getLogDao() {
        return new OracleLogDaoImpl();
    }

}
