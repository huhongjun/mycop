package com.gever.goa.infoservice.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;


/**
 * <p>Title: ��׼ģ��DAO�ӿ���</p>
 * <p>Description: KOBE OFFICE ��Ȩ���У�Υ�߱ؾ���</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: KOBE OFFICE</p>
 * @author Hu.Walker
 * @version 1.0
 */
public interface IsStandardModelDao {
    public int delBySelect(String[] aryPk, VOInterface vo)
        throws DefaultException;

    public String getPageSql();

    public int insert(VOInterface vo) throws DefaultException;

    public int update(VOInterface vo) throws DefaultException;

    public List queryAll(VOInterface vo) throws DefaultException;

    public List queryByPage(VOInterface vo, long start, long howMany)
        throws DefaultException;

    public long queryByCount(VOInterface vo) throws DefaultException;

    public int copy(String[] keyValue, VOInterface vo)
        throws DefaultException;

    public void setSqlWhere(String sqlWhere) throws DefaultException;

    public List getTreeData(String paraFlag, String nodeID)
        throws DefaultException;

    public VOInterface getTemplate(int templateType) throws DefaultException;

    //ȡ��ģ�壬��һ������infotype
    public VOInterface getTemplate(int templateType, String tmpInfotype)
        throws DefaultException;

    //GW ADD ��ȡ�ļ�·��
    public void setRealPath(String realPath);

    //GW ADD �ļ�ɾ��
    public void deleteFile(String path);
}
