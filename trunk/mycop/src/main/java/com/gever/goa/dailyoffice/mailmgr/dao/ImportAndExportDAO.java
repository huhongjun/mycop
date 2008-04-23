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
 * <p>Description: ��Ƭ����Ϣ���뵼�� DAO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */
public interface ImportAndExportDAO {
    /**
     * ��Ƭ����Ϣ����
     * @param userID
     * @param filePath
     */
    public List importMailBox(VOInterface vo) throws
        DefaultException, FileNotFoundException, IOException;

    /**
     * ��Ƭ����Ϣ����
     * @param form
     * @param exportList
     * @throws FileNotFoundException
     */
    public void exportMailBox(BaseForm form, List exportList) throws
        FileNotFoundException;


    public List getFiles(VOInterface vo);

    public void deleteFiles(VOInterface vo,String[] fileName);

}