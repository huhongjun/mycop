package com.gever.goa.dailyoffice.mailmgr.dao.impl;

import com.gever.goa.dailyoffice.mailmgr.dao.ImportAndExportDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailCapacityDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailDirectoryDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailInfoDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.mailmgr.dao.MailboxMgrDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.Pop3ConfigDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.Pop3MailMgrDAO;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: ÌìÐÐÈí¼þ</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class DefaultMailMgrFactory extends MailMgrFactory {
    public DefaultMailMgrFactory() {
    }
    public MailInfoDAO creatMailInfo(String dBData) {
        return new MailInfoDAOImp(dBData);
    }
    public MailDirectoryDAO creatMailDirectory(String dBData) {
        return new MailDirectoryDAOImp(dBData);
    }
    public MailCapacityDAO creatMailCapacity(String dBData) {
        return new MailCapacityDAOImp(dBData);
    }
    public MailInfoDAO createMailInfo(String dbData) {
        return new MailInfoDAOImp(dbData);
    }
    public MailMgrDAO createMailMgr(String dbData) {
        return new MailMgrDAOImp(dbData);
    }

    public Pop3ConfigDAO createPop3Config(String dbData){
        return new Pop3ConfigDAOImp(dbData);
    }

    public ImportAndExportDAO createImportAndExport(String dbData) {
        return new ImportAndExportDAOImp(dbData);
    }

    public MailboxMgrDAO createMailboxMgr(String dbData){
        return new MailboxMgrImp(dbData);
    }
    public Pop3MailMgrDAO creatPop3MailMgr(String dbData) {
        return new Pop3MailMgrDAOImp(dbData);
    }
}
