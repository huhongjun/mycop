package com.gever.goa.infoservice.action;

import com.gever.exception.DefaultException;
import com.gever.goa.infoservice.dao.IsCustomerFactory;
import com.gever.goa.infoservice.dao.SysInfoTypeDao;
import com.gever.goa.infoservice.vo.SysInfoTypeVO;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;

/**
 * <p>Title: 类型设置Action</p>
 * <p>Description: KOBE OFFICE 版权所有，违者必究！</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class SysInfoTypeAction
    extends BaseAction {
    private SysInfoTypeDao sysInfoTypeDao = null;

    public SysInfoTypeAction() {
    }

    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {
        SysInfoTypeForm myForm = (SysInfoTypeForm) cfg.getBaseForm();
        sysInfoTypeDao = IsCustomerFactory.getInstance().createSysInfoType(super.
            dbData);
        cfg.setBaseDao( (BaseDAO) sysInfoTypeDao);
        if (myForm.getVo() == null)
            myForm.setVo(new SysInfoTypeVO());
			this.setVoSql(false);
    }

    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {

        String paraSimpleQuery = ( (SysInfoTypeForm) cfg.getBaseForm()).
            getParaSimpleQuery();
        if (paraSimpleQuery == null)
            paraSimpleQuery = "";
        cfg.getBaseForm().setSqlWhere(
            " AND SYS_INFO_TYPE.TYPE_NAME LIKE '%" +
            paraSimpleQuery +
            "%'");

        return super.toList(cfg);
    }

}
