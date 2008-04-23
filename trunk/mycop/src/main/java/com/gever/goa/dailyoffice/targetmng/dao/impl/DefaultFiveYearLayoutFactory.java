package com.gever.goa.dailyoffice.targetmng.dao.impl;

import com.gever.goa.dailyoffice.targetmng.dao.FiveYearLayoutDao;
import com.gever.goa.dailyoffice.targetmng.dao.FiveYearLayoutFactory;

/**
 * Title: 五年规划抽象类工厂的实现类
 * Description: 五年规划抽象类工厂的实现类
 * Copyright: Copyright (c) 2004
 * Company: 天行软件
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
