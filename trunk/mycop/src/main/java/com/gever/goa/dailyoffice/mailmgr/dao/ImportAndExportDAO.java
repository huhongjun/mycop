package com.gever.goa.dailyoffice.mailmgr.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.struts.form.BaseForm;
import com.gever.vo.VOInterface;

/**
 *
 * <p>Title: </p>
 * <p>Description: 名片夹信息导入导出 DAO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 天行软件</p>
 * @author Hu.Walker
 * @version 1.0
 */
public interface ImportAndExportDAO {
    /**
     * 名片夹信息导入
     * @param userID
     * @param filePath
     */
    public List importMailBox(VOInterface vo) throws
        DefaultException, FileNotFoundException, IOException;

    /**
     * 名片夹信息导出
     * @param form
     * @param exportList
     * @throws FileNotFoundException
     */
    public void exportMailBox(BaseForm form, List exportList) throws
        FileNotFoundException;


    public List getFiles(VOInterface vo);

    public void deleteFiles(VOInterface vo,String[] fileName);

}