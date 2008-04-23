package com.gever.goa.dailyoffice.tools.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

public interface CardcaseDao {
    public int delBySelect(String[] aryPk, VOInterface vo) throws
            DefaultException;

    public String getPageSql();

    public int insert(VOInterface vo) throws DefaultException;

    public int update(VOInterface vo) throws DefaultException;

    public List queryAll(VOInterface vo) throws DefaultException;

    public List queryByCustomer(VOInterface vo) throws DefaultException;

    public List queryByType(VOInterface vo) throws DefaultException;

    public List queryByPage(VOInterface vo, long start, long howMany) throws
            DefaultException;

    public long queryByCount(VOInterface vo) throws DefaultException;

    public int insertBatch(List sameVO, String userID, String typeName) throws
            DefaultException;

    public int insertBatch(List sameVO, String userID) throws DefaultException;

    public List queryByUser(String userID) throws DefaultException;

    /**
     * ͨ���ͻ�email�����Ƭ���е����Ӧ�ͻ�������
     * @param userID String ��Ƭ���������û�id��
     * @param email String �ͻ�email��
     * @throws DefaultException
     * @return String �ͻ�������
     */
    public String getNameByEmail(String userID, String email) throws
            DefaultException;
}
