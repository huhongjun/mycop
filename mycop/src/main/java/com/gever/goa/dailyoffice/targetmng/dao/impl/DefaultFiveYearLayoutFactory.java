package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.FiveYearLayoutDao;
import com.gever.goa.dailyoffice.targetmng.dao.FiveYearLayoutFactory;

/**
 * Title: ����滮�����๤����ʵ����
 * Description: ����滮�����๤����ʵ����
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */
public class DefaultFiveYearLayoutFactory extends FiveYearLayoutFactory {
    public DefaultFiveYearLayoutFactory() {
    }

    public FiveYearLayoutDao createFiveYearLayout(String dbData) {
        return new FiveYearLayoutDaoImpl(dbData);
    }
}
