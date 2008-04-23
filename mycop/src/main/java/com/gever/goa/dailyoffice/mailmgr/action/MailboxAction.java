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
 * <p>Description: 名片夹导入导出Action 类</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */

public class MailboxAction extends BaseAction {
    public MailboxAction() {
    }

    protected void initData(GoaActionConfig cfg) throws DefaultException,
        Exception {

        MailboxForm myForm = (MailboxForm) cfg.getBaseForm(); //得到form变量
        ImportAndExportDAO importAndExportDao = MailMgrFactory.getInstance().
            createImportAndExport(super.
                                  dbData); //得到相对应dao的实例
        cfg.setBaseDao( (BaseDAO) importAndExportDao);
        //super.setBaseDao( (BaseDAO) sampleDao); //设置父类dao
        //容错处理,防止vo对象为null
        if (myForm.getVo() == null) {
            myForm.setVo(new CardcaseVO());
        }
    }

    /**
     * 导入名片夹
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
            dbData);//得到CardcaseDAO
        ImportAndExportDAO importAndExportDao = (ImportAndExportDAO) cfg.
            getBaseDao(); //得到ImportAndExportDAO
        VOInterface vo = cfg.getBaseForm().getVo();
        String userId=cfg.getBaseForm().getUserId();//为了便于测试先令USEID为8001
        //String userId = "8001";
        UploadFile uploadFile = new UploadFile();//创建一个上传文件对象
        String realPath = cfg.getRequest().getRealPath("/");
        File file = new File(realPath + uploadFile.getDir());
       // System.out.println(realPath + uploadFile.getDir());
        if (!file.exists()) {
            file.mkdirs();
        }

        uploadFile.saveOneFile(cfg.getRequest(), (MailboxForm) cfg.getBaseForm(),
                               vo, "file_path", "file_name");//上传文件并将file_path,file_name保存到VO里
        //vo.getVaule("file_path")为/uploadFiles/文件名
        //realPath+vo.getVaule("file_path")为文件的全路径
        vo.setValue("file_path", realPath + vo.getValue("file_path"));
       // System.out.println("文件上传的路径为:"+realPath + vo.getValue("file_path"));
        cardcaseDao.insertBatch(importAndExportDao.importMailBox(vo), userId); //将信息保存到数据库中
        File[] list = file.listFiles();
        for (int i = 0; i < list.length; i++) {
            //System.out.println(list[i].getName());
            list[i].delete();
        }
        if (file.delete()) {
            System.out.println("文件导入之后被删除");
        }
        return this.FORWORD_LIST_PAGE;
    }

    /**
     * 显示导出文件的列表
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
     * 导出名片夹信息
     * @param cfg
     * @return
     * @throws DefaultException
     * @throws java.lang.Exception
     */

    protected String toView(GoaActionConfig cfg) throws DefaultException,
        Exception {
        CardcaseDao cardcaseDao = ToolsFactory.getInstance().createCardcaseDao(super.
            dbData);
       String userId=cfg.getBaseForm().getUserId();//为了便于测试先令USEID为8001
        // String userId = "8001";
        List listOfUser = cardcaseDao.queryByUser(userId);
        VOInterface vo = cfg.getBaseForm().getVo();
        String realPath = this.getRealPath(cfg,"download");//得到当前路径
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
     * 删除导出文件
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
        String userName=cfg.getBaseForm().getUserId();//为了便于测试先令USEID为8001
        //String userId = "8001";
        String realPath = cfg.getRequest().getRealPath("/") + "/"+typeOfFile+"/" +
            "/" + userName + "/";
        return realPath;
    }


}