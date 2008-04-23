package com.gever.goa.dailyoffice.worklog.dao;

import java.util.List;
import java.util.Map;

import com.gever.exception.DefaultException;
import com.gever.vo.VOInterface;

/**
 * <p>Title: ������־Dao�ӿ�</p>
 * <p>Description: ������־Dao�ӿ�</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: �������</p>
 * @author Hu.Walker
 * @version 1.0
 */

public interface WorkLogDao {

    public int delBySelect(String[] aryPk, VOInterface vo) throws DefaultException;//ɾ��
    public String getPageSql();//��ҳ
    public int insert(VOInterface vo) throws DefaultException;//����
    public int update(VOInterface vo) throws DefaultException;//�޸�
    public List queryAll(VOInterface vo) throws DefaultException;//��ѯ����
    public List queryByPage(VOInterface vo, long start, long howMany) throws DefaultException;//��ҳ��ѯ
    public long queryByCount(VOInterface vo) throws DefaultException;//������ѯ
    public int copy(String[] keyValue,VOInterface vo) throws DefaultException;//����

    public boolean findCurPsnCurDayWorkLogIsExist(String userid,String curdate) throws DefaultException;
    //�����жϵ�ǰ���Ƿ��Ѿ���д�˵������־
    //public int insertTemplate(VOInterface vo)throws DefaultException;//���ģ��
    public int findLogNumOfEveryPage() throws DefaultException;
    //��ȡSYS_PARAMETER���е�ÿҳ����־��¼��ʾ��
    public String isCurUserTodayWorkLog(String curUser,String userid,String filldate,String curDate) throws DefaultException;
    //�����жϲ鿴����־�Ƿ��ǵ������־
    public VOInterface getCurDayWorkLog(String userid,String curdate) throws Exception;
    //������ȡ��ǰ���ڵı�������д�Ĺ�����־
    public String getSerialNumByUseridAndCurdate(String userid, String curdate) throws Exception;
    //������ȡ��ǰ���ڵ�ǰ������д����־��Ӧ��serial_number
    public boolean insertLogsIntoBakTable(String logTotalStr,String logGrjb,String logID,String userID) throws DefaultException;
    //����������һ����־ʱ�򱸷ݱ��м�����Ӧ�ļ�¼
    public int updateLogContent(String logID, String log_content, String logGrjb) throws DefaultException;
    //ͨ��������־logID���ĵ�ǰ������־���ݱ��е�����
    public String getLogTotalStrByUseridAndCurdate(String userid, String curdate) throws Exception;
    //������ȡ��ǰ���ڵ�ǰ������д����־��Ӧ����־���ݵ��ַ���
    public List getLogListByUseridAndCurdate(String userid, String curdate) throws Exception;
    //������ȡ��ǰ���ڵ�ǰ������д����־��Ӧ����־���ݵ��ַ����б�
    public Map getLogStrAndGrjbByUseridAndCurdate(String userid, String curdate) throws Exception;
    //������ȡ��ǰ���ڵ�ǰ������д����־��Ӧ����־���ݵ��ַ����͸��˽������ַ���
     public int deleteLogContent(String logID) throws DefaultException;
     //ɾ��LogContent�����������
}

