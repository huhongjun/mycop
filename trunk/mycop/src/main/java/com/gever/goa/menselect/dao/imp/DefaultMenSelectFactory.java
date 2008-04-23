package com.gever.goa.menselect.dao.imp;

import com.gever.goa.menselect.dao.MenSelectDAO;
import com.gever.goa.menselect.dao.MenSelectFactory;

public class DefaultMenSelectFactory extends MenSelectFactory {
    public DefaultMenSelectFactory() {
    }

    public MenSelectDAO createMenSelect(String dbData) {
        return new MenSelectDAOImp(dbData);
    }
}
