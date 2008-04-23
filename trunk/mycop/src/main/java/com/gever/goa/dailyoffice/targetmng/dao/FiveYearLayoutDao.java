package com.gever.goa.dailyoffice.targetmng.dao;

import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * Title: ����滮Dao�ӿ�
 * Description: ����滮Dao�ӿ�
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */

public interface FiveYearLayoutDao {
    public int delBySelect(String[] aryPk, VOInterface vo) throws DefaultException; //ɾ��

    public String getPageSql(); //��ҳ

    public int insert(VOInterface vo) throws DefaultException; //����

    public int update(VOInterface vo) throws DefaultException; //�޸�

    public List queryAll(VOInterface vo) throws DefaultException; //��ѯ����

    public List queryByPage(VOInterface vo, long start, long howMany) throws DefaultException; //��ҳ��ѯ

    public long queryByCount(VOInterface vo) throws DefaultException; //������ѯ

    public int copy(String[] keyValue, VOInterface vo) throws DefaultException; //����
}
