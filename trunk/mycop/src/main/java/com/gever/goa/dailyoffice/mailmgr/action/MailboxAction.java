package com.gever.goa.dailyoffice.mailmgr.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.mailmgr.dao.ImportAndExportDAO;
import com.gever.goa.dailyoffice.mailmgr.dao.MailMgrFactory;
import com.gever.goa.dailyoffice.tools.dao.CardcaseDao;
import com.gever.goa.dailyoffice.tools.dao.ToolsFactory;
import com.gever.goa.dailyoffice.tools.vo.CardcaseVO;
import com.gever.goa.web.util.UploadFile;
import com.gever.jdbc.BaseDAO;
import com.gever.struts.action.BaseAction;
import com.gever.struts.action.GoaActionConfig;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: </p>
 * <p>Description: ��Ƭ�е��뵼��Action ��</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailboxAction extends BaseAction {
    public MailboxAction() {
    }

    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MailboxForm myForm = (MailboxForm) cfg.getBaseForm(); //�õ�form����
        ImportAndExportDAO importAndExportDao = MailMgrFactory.getInstance().
            createImportAndExport(super.
                                  dbData); //�õ����Ӧdao��ʵ��
        cfg.setBaseDao( (BaseDAO) importAndExportDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //���ø���dao
        //�ݴ���,��ֹvo����Ϊnull
        if (myForm.getVo() == null) {
            myForm.setVo(new CardcaseVO());
        }
    }

    /**
     * ������Ƭ��
     * @param cfg
     * @param isBack
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String doInsert(GoaActionConfig cfg, boolean isBack) throws
        DefaultException,
        Exception {
        CardcaseDao cardcaseDao = ToolsFactory.getInstance().createCardcaseDao(super.
            dbData);//�õ�CardcaseDAO
        ImportAndExportDAO importAndExportDao = (ImportAndExportDAO) cfg.
            getBaseDao(); //�õ�ImportAndExportDAO
        VOInterface vo = cfg.getBaseForm().getVo();
        String userId=cfg.getBaseForm().getUserId();//Ϊ�˱��ڲ�������USEIDΪ8001
        //String userId = "8001";
        UploadFile uploadFile = new UploadFile();//����һ���ϴ��ļ�����
        String realPath = cfg.getRequest().getRealPath("/");
        File file = new File(realPath + uploadFile.getDir());
       // System.out.println(realPath + uploadFile.getDir());
        if (!file.exists()) {
            file.mkdirs();
        }

        uploadFile.saveOneFile(cfg.getRequest(), (MailboxForm) cfg.getBaseForm(),
                               vo, "file_path", "file_name");//�ϴ��ļ�����file_path,file_name���浽VO��
        //vo.getVaule("file_path")Ϊ/uploadFiles/�ļ���
        //realPath+vo.getVaule("file_path")Ϊ�ļ���ȫ·��
        vo.setValue("file_path", realPath + vo.getValue("file_path"));
       // System.out.println("�ļ��ϴ���·��Ϊ:"+realPath + vo.getValue("file_path"));
        cardcaseDao.insertBatch(importAndExportDao.importMailBox(vo), userId); //����Ϣ���浽���ݿ���
        File[] list = file.listFiles();
        for (int i = 0; i < list.length; i++) {
            //System.out.println(list[i].getName());
            list[i].delete();
        }
        if (file.delete()) {
            System.out.println("�ļ�����֮��ɾ��");
        }
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * ��ʾ�����ļ����б�
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */
    protected String toList(GoaActionConfig cfg) throws DefaultException,
        Exception {
        ImportAndExportDAO importAndExportDao = (ImportAndExportDAO) cfg.
            getBaseDao();
        VOInterface vo = cfg.getBaseForm().getVo();
        String realPath=this.getRealPath(cfg,"download");
        vo.setValue("file_path", realPath);
        List listOfFile = importAndExportDao.getFiles(vo);
        cfg.getSession().setAttribute("fileList", listOfFile);
        return this.FORWORD_LIST_PAGE;
    }
    /**
     * ������Ƭ����Ϣ
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String toView(GoaActionConfig cfg) throws DefaultException,
        Exception {
        CardcaseDao cardcaseDao = ToolsFactory.getInstance().createCardcaseDao(super.
            dbData);
       String userId=cfg.getBaseForm().getUserId();//Ϊ�˱��ڲ�������USEIDΪ8001
        // String userId = "8001";
        List listOfUser = cardcaseDao.queryByUser(userId);
        VOInterface vo = cfg.getBaseForm().getVo();
        String realPath = this.getRealPath(cfg,"download");//�õ���ǰ·��
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        vo.setValue("file_path", realPath);
        cfg.getBaseForm().setVo(vo);
        ImportAndExportDAO importAndExportDao = (ImportAndExportDAO) cfg.
            getBaseDao();
        importAndExportDao.exportMailBox(cfg.getBaseForm(), listOfUser);
        return this.FORWORD_LIST_PAGE;
    }
    /**
     * ɾ�������ļ�
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String doDelete(GoaActionConfig cfg) throws DefaultException,
        Exception {
        VOInterface vo = cfg.getBaseForm().getVo();
        HttpServletRequest request = cfg.getRequest();

        String[] keyId = request.getParameterValues("fid");
        ImportAndExportDAO importAndExportDao = (ImportAndExportDAO) cfg.
            getBaseDao();
        String realPath=this.getRealPath(cfg,"download");
        vo.setValue("file_path",realPath);
        importAndExportDao.deleteFiles(vo, keyId);
        return this.FORWORD_LIST_PAGE;
    }

    private String getRealPath(GoaActionConfig cfg,String typeOfFile){
        HttpServletRequest request = cfg.getRequest();
        String userName=cfg.getBaseForm().getUserId();//Ϊ�˱��ڲ�������USEIDΪ8001
        //String userId = "8001";
        String realPath = cfg.getRequest().getRealPath("/") + "/"+typeOfFile+"/" +
            "/" + userName + "/";
        return realPath;
    }


}