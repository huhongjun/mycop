package com.gever.goa.dailyoffice.smsmgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.gever.exception.DefaultException;
import com.gever.goa.dailyoffice.smsmgr.dao.SMSCapacityDAO;
import com.gever.goa.dailyoffice.smsmgr.vo.DeptTreeVO;
import com.gever.goa.dailyoffice.smsmgr.vo.SMSCapacityVO;
import com.gever.jdbc.BaseDAO;
import com.gever.jdbc.sqlhelper.DefaultSQLHelper;
import com.gever.jdbc.sqlhelper.SQLHelper;
import com.gever.util.StringUtils;
import com.gever.vo.VOInterface;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: �������
 * </p>
 *
 * @author Hu.Walker
 * @version 1.0
 */

public class SmsCapacityDAOImpl extends BaseDAO implements SMSCapacityDAO {

    private static String TREE_SQL =
        " Select id as nodeid,name as nodename,case "
        + "when (Select COUNT(*) AS CNT from T_DEPARTMENT  b  WHERE "
        + "T_DEPARTMENT.id<>b.id AND b.parent_id=T_DEPARTMENT.id  ) >0 "
        + "then '1' else '0' end as isfolder from T_DEPARTMENT where  1=1 ";

    private static String QUERY_SQL = "SELECT A.ID as userid ,A.NAME as username,  B.NUMBERs, B.ACL,B.SCOPE,b.bz,a.status"
        +
        " FROM t_user AS A  LEFT JOIN SMSCapacity AS B ON A.ID = B.USERID WHERE 1=1 ";

    private static String QUERY_DEL =
        " delete from SMSCapacity WHERE USERID<>-1";

    private static String QUYER_DEL_ALL = "delete from SMSCapacity WHERE 1<>1";

    private static String QUERY_MOD =
        "update SMSCapacity set numbers = ? where userid=-1";
    private static String MOD_SMS_CORP =
        "update SMSCapacity set BZ = ? where userid=-1";
    public SmsCapacityDAOImpl(String dbData) {
        super(dbData);
    }

    public void setOracleSQL() {
        QUERY_SQL = " SELECT A.ID as userid ,A.NAME as username,  B.NUMBERs, B.ACL,B.SCOPE,b.bz,a.status " +
            " FROM t_user  A  , SMSCapacity  B where A.ID = B.USERID(+) ";

    }

    public String getPageSql() {
        return QUERY_SQL;
    }

    /**
     * ���ɲ�����
     *
     * @param nodeid
     *            ��ǰ�ڵ�id
     * @return �������б�
     * @throws DefaultException
     */

    public List getTreeData(String nodeid) throws DefaultException {
        List treeData = new ArrayList();
        String strWhere = "";
        if ("0".equals(nodeid) || StringUtils.isNull(nodeid)) { //�ж�0--�����
            strWhere = " and parent_id = 0 "; //��ȡ��һ����Ŀ¼�ڵ�
        } else {
            strWhere = " and parent_id =" + nodeid; //�����ȡӵ�и��ڵ����Щ�ڵ�
        }
        DeptTreeVO vo = new DeptTreeVO();
        SQLHelper helper = helper = new DefaultSQLHelper(dbData);
        ;
        treeData = (ArrayList) helper.queryByListVo(TREE_SQL + strWhere, vo);
        return treeData;
    }

    /**
     * ����Ա���������������
     *
     * @return ����Ա���������������
     * @throws DefaultException
     */

    public SMSCapacityVO queryDefaultCapacity() throws DefaultException {
        SMSCapacityVO vo = new SMSCapacityVO();
        vo.setUserid("-1");
        SQLHelper helper = new DefaultSQLHelper(dbData);
        return (SMSCapacityVO) helper.queryByPK(vo);

    }

    /**
     * ����Ա���������������
     *
     * @param userid
     *            �û�id
     * @return ����Ա���������������
     * @throws DefaultException
     */
    public SMSCapacityVO queryCapacityByUserId(String userid) throws
        DefaultException {
        SMSCapacityVO vo = new SMSCapacityVO();
        vo.setUserid(userid);
        SQLHelper helper = new DefaultSQLHelper(dbData);
        return (SMSCapacityVO) helper.queryByPK(vo);

    }

    /**
     * ���������˵Ķ�������
     *
     * @param smsNumber  ��������
     * @param type ����
     * @return �޸ĳɹ����ٱ�
     * @throws DefaultException
     */
    public int updateAll(int smsNumber, int type) throws DefaultException {

        SMSCapacityVO vo = new SMSCapacityVO();
        super.setSqlWhere("");
        List aryData = super.queryAll(vo);
        SQLHelper helper = new DefaultSQLHelper(dbData);
        String[] values = new String[1];
        values[0] = String.valueOf(smsNumber);
        int intRet = 0;

        try {
            helper.begin();
            helper.setAutoID(false);
            //����Ĭ��ֵ
            helper.execUpdate(this.QUERY_MOD, values, "int");

            helper.execUpdate(this.QUERY_DEL);
            for (int idx = 0; idx < aryData.size(); idx++) {
                SMSCapacityVO insVO = new SMSCapacityVO();
                vo = new SMSCapacityVO();
                vo = (SMSCapacityVO) aryData.get(idx);
                insVO.setUserid(vo.getUserid());
                insVO.setNumber(String.valueOf(smsNumber));
                insVO.setScope(String.valueOf(type));
                intRet += helper.insert(insVO);
            }

            helper.commit();

        } catch (DefaultException e) {
            intRet = -1;
            helper.rollback();
            throw new DefaultException("���³���");
        } finally {
            helper.end();
        }

        return intRet;

    }

    /**
     * ���������˵Ķ�������
     * @param aryUser �û�����
     * @param timing ���ڷ���
     * @param smsNumber ��������
     * @param  type ����
     * @return �޸ĳɹ����ٱ�
     * @throws DefaultException
     */
    public int update(String[] aryUser, String[] timing, int smsNumber,
                      int type) throws DefaultException {

        super.setSqlWhere("");
        SQLHelper helper = new DefaultSQLHelper(dbData);
        int intRet = 0;

        try {
            helper.begin();
            helper.setAutoID(false);

            StringBuffer sbWhere = new StringBuffer();
            for (int idx = 0; idx < aryUser.length; idx++) {
                sbWhere.append(" or userid=").append(aryUser[idx]);
            }
            helper.execUpdate(this.QUYER_DEL_ALL + sbWhere.toString());

            for (int idx = 0; idx < aryUser.length; idx++) {
                SMSCapacityVO insVO = new SMSCapacityVO();
                insVO.setUserid(aryUser[idx]);
                insVO.setAcl(timing[idx]);
                insVO.setNumber(String.valueOf(smsNumber));
                insVO.setScope(String.valueOf(type));
                intRet += helper.insert(insVO);
            }
            helper.commit();

        } catch (DefaultException e) {
            intRet = -1;
            helper.rollback();
            throw new DefaultException("���³���");
        } finally {
            helper.end();
        }

        return intRet;

    }

    /**
     * �޸Ĺ�˾����
     * @param vo ��ǰvo����
     * @return �ɹ����¶��ٱ�,-1Ϊʧ��
     * @throws DefaultException
     */
    public int updateCorpName(VOInterface vo) throws DefaultException {
        int intRet = -1;
        SMSCapacityVO svo = (SMSCapacityVO) vo;
        String[] values = {svo.getBz()};
        SQLHelper helper = new DefaultSQLHelper(dbData);
		intRet = helper.execUpdate(this.MOD_SMS_CORP, values, "String");
        return intRet;
    }

    /**
     * �õ���ǰ��˾����
     * @return ��˾����
     * @throws DefaultException
     */
    public String queryCorpName() throws DefaultException {
        SMSCapacityVO vo = queryDefaultCapacity();
        return vo.getBz();
    }
}