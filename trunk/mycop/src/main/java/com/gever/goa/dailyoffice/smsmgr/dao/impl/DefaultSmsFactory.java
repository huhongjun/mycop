package com.gever.goa.dailyoffice.smsmgr.dao.impl;

import com.gever.goa.dailyoffice.smsmgr.dao.SMSCapacityDAO;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsFactory;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsInBoxDAO;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsMgrDAO;
import com.gever.goa.dailyoffice.smsmgr.dao.SmsProduct;

/**
 * 
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultSmsFactory extends SmsFactory {
	public DefaultSmsFactory() {
	}

	public SmsMgrDAO createSmsMgrDAO(String dbData) {
		return new SmsMgrDAOImpl(dbData);
	}

	public SMSCapacityDAO createSmsCapacityDAO(String dbData) {
		return new SmsCapacityDAOImpl(dbData);
	}

	public SmsProduct createSmsProduct(String dbData) {
		return new SmsProductFs(dbData);
	}

	public SmsInBoxDAO createSmsInBoxDAO(String dbData) {
		return new SmsInBoxDAOImpl(dbData);
	}

}