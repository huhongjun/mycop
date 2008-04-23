package com.gever.goa.dailyoffice.worklog.dao;

import java.sql.SQLException;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.worklog.vo.TeamWorkLogSetVO;
import com.gever.vo.VOInterface;

/**
 * Title: �Ŷ���־Dao�ӿ�
 * Description: �Ŷ���־Dao�ӿ�
 * Copyright: Copyright (c) 2004
 * Company: �������
 * @author Hu.Walker
 * @version 1.0
 */

public interface TeamWorkLogSetDao {
    public int delBySelect(String[] aryPk, VOInterface vo) throws
        DefaultException; //ɾ��

    public String getPageSql(); //��ҳ

    public int insert(VOInterface vo) throws DefaultException; //����

    public int update(VOInterface vo) throws DefaultException; //�޸�

    public List queryAll(VOInterface vo) throws DefaultException; //��ѯ����

    public List queryByPage(VOInterface vo, long start, long howMany) throws
        DefaultException; //��ҳ��ѯ

    public long queryByCount(VOInterface vo) throws DefaultException; //������ѯ

    public int copy(String[] keyValue, VOInterface vo) throws DefaultException; //����

    public List getTreeData() throws DefaultException; //��ȡ���ͽṹ�б�

    public List queryUsersByCurDept(String team_member) throws DefaultException;

    //��ѯ��ǰ�û����Ŷ���־����ĳ���Ŷ��е�ĳ�����ŵĳ�Ա�б�--user_code--��ǰ�û�id--team_type--�Ŷ�����--dept_code--���Ŵ���

    public List queryDeptsByCurUser(VOInterface vo, int type) throws DefaultException;

    //��ѯ��ǰ�û����Ŷ���־����ĳ���Ŷ��еĲ����б�--userid--��ǰ�û�id--type--�Ŷ�����

    public String queryTeamMembers(String user_code, int team_type, VOInterface vo) throws DefaultException;

    //��ѯ��ǰ�û����Ŷ���־����ĳ���Ŷ��е�ĳ�����ŵĳ�Ա�б��ַ���--user_code--��ǰ�û�id--team_type--�Ŷ�����--dept_code--���Ŵ���

    public String queryList(String user_code, int team_type, VOInterface vo) throws
        DefaultException;

    //��ѯ���Ŷ���־��������б�
    public String queryTeamWorkLogListHtml(String user_code, int team_type,
                                           VOInterface vo) throws
        DefaultException;

    //��ѯ���Ŷ���־�б�

    public int save(TeamWorkLogSetVO vo) throws Exception; //�����Ŷ���־����

    public List queryDeptsByCurTeamMember(String teamMembers) throws DefaultException;

    //��ѯ��ǰ�û����Ŷ���־����ĳ���Ŷ��е����г�Ա��Ӧ�Ĳ����б�--teamMembers--�Ŷӳ�Ա�ַ��������Ÿ���

    //������ǵ�ǰ�û����趨���Ŷ���־���е���Щ�Ŷӳ�Ա��������д�˵������־����Щ��Ҫ�����
    //��ʾ�ò����µ���Щ�û���������Щ�û�Ӧ�����������Ŷӳ�Ա�е���Щ
    public String queryTeamWorkLogListHtmlForQuery(String query, String user_code, int team_type,VOInterface vo) throws DefaultException;
    //��ѯ���Ŷ���־��������б�-�����˲�ѯ���ڵ����

    /**
     * �õ���ǰ�Ŷӵ���Ա"1,2,3,4"�ķ���    ���˱�(����)
     * @param userID �û�ID
     * @param type �Ŷ�����
     * @return ��Ա�ִ�
     * @throws DefaultException
     */
    public String getTermMens(String userID, String type) throws
        DefaultException;

    public void setCacheMems(String cacheMems);

    public String getCacheMems();

    public void setContext(String context);
    public List getRootDept()throws DefaultException, SQLException;

}
